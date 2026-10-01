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
 * to keep generated sources IDE-sized; public only because {@code Double4Ops}
 * and its SIMD cell call it from their own packages. Not public API.
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, otherX + src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, otherY + _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, otherZ + _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, otherW + _selfw);
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) + src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _othery + _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _otherz + _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _otherw + _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) / scalar);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy / scalar);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz / scalar);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw / scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) / otherX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy / otherY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz / otherZ);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw / otherW);
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) / other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy / _othery);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz / _otherz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw / _otherw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double b, double cX, double cY, double cZ, double cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double b, double cX, double cY, double cZ, double cW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_mulAdd(dest, destOffset, src, srcOffset, b, cX, cY, cZ, cW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), b, cX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, b, cY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, b, cZ));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_selfw, b, cW));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double b, double cX, double cY, double cZ, double cW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (b) + (cX)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (b) + (cY)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (b) + (cZ)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) * (b) + (cW)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_mulAdd(dest, destOffset, src, srcOffset, c, cOffset, b);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        double _cw = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), b, c.get(_JAVA_DOUBLE_UNALIGNED, cOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, b, _cy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, b, _cz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_selfw, b, _cw));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, double b) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        double _cw = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (b) + (c.get(_JAVA_DOUBLE_UNALIGNED, cOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (b) + (_cy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (b) + (_cz)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) * (b) + (_cw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_mulAdd(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), bX, cX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, bY, cY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, bZ, cZ));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_selfw, bW, cW));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (bX) + (cX)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (bY) + (cY)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (bZ) + (cZ)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) * (bW) + (cW)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return fma_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@code fma_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _bw = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 24L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        double _cw = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), b.get(_JAVA_DOUBLE_UNALIGNED, bOffset), c.get(_JAVA_DOUBLE_UNALIGNED, cOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _by, _cy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _bz, _cz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _bw = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 24L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        double _cw = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (b.get(_JAVA_DOUBLE_UNALIGNED, bOffset)) + (c.get(_JAVA_DOUBLE_UNALIGNED, cOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_by) + (_cy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_bz) + (_cz)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) * (_bw) + (_cw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, scalar * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, scalar * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, scalar * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, scalar * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, otherX * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, otherY * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, otherZ * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, otherW * _selfw);
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _othery * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _otherz * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _otherw * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, -src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -_selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, -_selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy - otherY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz - otherZ);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw - otherW);
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy - _othery);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz - _otherz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw - _otherw);
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
        double _t4 = java.lang.Math.sqrt(1.0 - u);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.cosWithSin(_t1) * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.sinWithCos(_t1) * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.cosWithSin(_t3) * _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.sinWithCos(_t3) * _t0);
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        double _vw = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, v.get(_JAVA_DOUBLE_UNALIGNED, vOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _vy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _vz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _vw);
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return bezier_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code bezier_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
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
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(p1W, _t7, _selfw * _t8) + java.lang.Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    /** {@code bezier_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
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
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((p1W) * (_t7) + (_selfw * _t8)) + ((p2W) * (_t6) + (p3W * _t2)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezier_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezier_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t3 = _t0 * _t0;
        return bezier_api_fma_s43e0830_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, t * _t1, 3.0 * _t0 * _t1, 3.0 * t * _t3, _t0 * _t3);
    }

    /** Piece 2 of {@code bezier_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_fma_s43e0830_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double _selfy, double _selfz, double _selfw, double _p1y, double _p1z, double _p1w, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t2, double _t6, double _t7, double _t8) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset), _t7, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t8) + java.lang.Math.fma(p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset), _t6, p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset) * _t2));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_p1w, _t7, _selfw * _t8) + java.lang.Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    /** {@code bezier_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t3 = _t0 * _t0;
        return bezier_api_mulAdd_s7b645471_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, t * _t1, 3.0 * _t0 * _t1, 3.0 * t * _t3, _t0 * _t3);
    }

    /** Piece 2 of {@code bezier_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd_s7b645471_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double _selfy, double _selfz, double _selfw, double _p1y, double _p1z, double _p1w, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t2, double _t6, double _t7, double _t8) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset)) * (_t7) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t8)) + ((p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset)) * (_t6) + (p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset) * _t2)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_p1w) * (_t7) + (_selfw * _t8)) + ((_p2w) * (_t6) + (_p3w * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, _selfz * _t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(p2W, _t0, java.lang.Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p2X) * (_t0) + (((p1X) * (_t3) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((p2Z) * (_t0) + (((p1Z) * (_t3) + (_selfz * _t4)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((p2W) * (_t0) + (((p1W) * (_t3) + (_selfw * _t4)))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@code bezier2_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset), _t0, java.lang.Math.fma(p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset), _t3, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        return bezier2_api_fma_s189550a7_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t0, _t3, _t4);
    }

    /** Piece 2 of {@code bezier2_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_fma_s189550a7_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfz, double _selfw, double _p1z, double _p1w, double _p2z, double _p2w, double _t0, double _t3, double _t4) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_p2w, _t0, java.lang.Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset)) * (_t0) + (((p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset)) * (_t3) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        return bezier2_api_mulAdd_sc4c15c20_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t0, _t3, _t4);
    }

    /** Piece 2 of {@code bezier2_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_mulAdd_sc4c15c20_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfz, double _selfw, double _p1z, double _p1w, double _p2z, double _p2w, double _t0, double _t3, double _t4) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_p2w) * (_t0) + (((_p1w) * (_t3) + (_selfw * _t4)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p1X - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t2, (p2X - p1X) * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p1X - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t2) + ((p2X - p1X) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((p1W - _selfw) * (_t2) + ((p2W - p1W) * _t1)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2Tangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@code bezier2Tangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_p1x - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t2, (p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset) - _p1x) * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        return bezier2Tangent_api_fma_s3cab3e48_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t1, _t2);
    }

    /** Piece 2 of {@code bezier2Tangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_fma_s3cab3e48_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfz, double _selfw, double _p1z, double _p1w, double _p2z, double _p2w, double _t1, double _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_p1x - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t2) + ((p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset) - _p1x) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
        return bezier2Tangent_api_mulAdd_sa1747645_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t1, _t2);
    }

    /** Piece 2 of {@code bezier2Tangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_mulAdd_sa1747645_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfz, double _selfw, double _p1z, double _p1w, double _p2z, double _p2w, double _t1, double _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_p1w - _selfw) * (_t2) + ((_p2w - _p1w) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return bezierTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code bezierTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(p3W - p2W, _t2, java.lang.Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p3X - p2X) * (_t2) + (((p1X - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t6) + ((p2X - p1X) * _t5)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((p3W - p2W) * (_t2) + (((p1W - _selfw) * (_t6) + ((p2W - p1W) * _t5)))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezierTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezierTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        double _t1 = 1.0 - t;
        return bezierTangent_api_fma_s35f9af59_1(dest, destOffset, src, srcOffset, p3, p3Offset, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t1, 3.0 * t * t, 6.0 * t * _t1);
    }

    /** Piece 2 of {@code bezierTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_fma_s35f9af59_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p3, long p3Offset, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t1, double _t2, double _t5) {
        double _t6 = 3.0 * _t1 * _t1;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset) - _p2x, _t2, java.lang.Math.fma(_p1x - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, (_p2x - _p1x) * _t5)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_p3w - _p2w, _t2, java.lang.Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        double _t1 = 1.0 - t;
        return bezierTangent_api_mulAdd_s840b67c2_1(dest, destOffset, src, srcOffset, p3, p3Offset, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t1, 3.0 * t * t, 6.0 * t * _t1);
    }

    /** Piece 2 of {@code bezierTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd_s840b67c2_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p3, long p3Offset, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t1, double _t2, double _t5) {
        double _t6 = 3.0 * _t1 * _t1;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset) - _p2x) * (_t2) + (((_p1x - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t6) + ((_p2x - _p1x) * _t5)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_p3w - _p2w) * (_t2) + (((_p1w - _selfw) * (_t6) + ((_p2w - _p1w) * _t5)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRom_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRom_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * (java.lang.Math.fma(2.0, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * (java.lang.Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_api_fma_sf0521cd4_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma_sf0521cd4_1(java.lang.foreign.MemorySegment dest, long destOffset, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * (java.lang.Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * (java.lang.Math.fma(2.0, p1W, t * (p2W - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1W, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0, p2W, java.lang.Math.fma(3.0, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * (((2.0) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) * (_t0) + (((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X)) * _t1))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * (((2.0) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) * (_t0) + (((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y)) * _t1))));
        return catmullRom_api_mulAdd_sf82d2295_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd_sf82d2295_1(java.lang.foreign.MemorySegment dest, long destOffset, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * (((2.0) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) * (_t0) + (((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z)) * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * (((2.0) * (p1W) + (t * (p2W - _selfw))) + ((((((2.0) * (_selfw) + (((4.0) * (p2W) - (p3W))))) - (5.0) * (p1W))) * (_t0) + (((((3.0) * (p1W) + (p3W - _selfw))) - (3.0) * (p2W)) * _t1))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRom_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRom_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3x = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        return catmullRom_api_fma_s302f430b_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma_s302f430b_1(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w) {
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * (java.lang.Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * (java.lang.Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * (java.lang.Math.fma(2.0, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)) * _t1)));
        return catmullRom_api_fma_s302f430b_2(dest, destOffset, t, _selfw, _p1w, _p2w, _p3w, _t0, _t1);
    }

    /** Piece 3 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma_s302f430b_2(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfw, double _p1w, double _p2w, double _p3w, double _t0, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * (java.lang.Math.fma(2.0, _p1w, t * (_p2w - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1w, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, _p2w, -_p3w))), _t0, java.lang.Math.fma(-3.0, _p2w, java.lang.Math.fma(3.0, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3x = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        return catmullRom_api_mulAdd_sdc0792b4_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRom_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd_sdc0792b4_1(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w) {
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * (((2.0) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) * (_t0) + (((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x)) * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * (((2.0) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) * (_t0) + (((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y)) * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * (((2.0) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) * (_t0) + (((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z)) * _t1))));
        return catmullRom_api_mulAdd_sdc0792b4_2(dest, destOffset, t, _selfw, _p1w, _p2w, _p3w, _t0, _t1);
    }

    /** Piece 3 of {@code catmullRom_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd_sdc0792b4_2(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfw, double _p1w, double _p2w, double _p3w, double _t0, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * (((2.0) * (_p1w) + (t * (_p2w - _selfw))) + ((((((2.0) * (_selfw) + (((4.0) * (_p2w) - (_p3w))))) - (5.0) * (_p1w))) * (_t0) + (((((3.0) * (_p1w) + (_p3w - _selfw))) - (3.0) * (_p2w)) * _t1))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRomTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRomTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return catmullRomTangent_api_fma_s7da72ecd_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma_s7da72ecd_1(java.lang.foreign.MemorySegment dest, long destOffset, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1W, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, p2W, -p3W))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2W, java.lang.Math.fma(3.0, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) + (((3.0 * ((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X))) * (_t0) + (p2X - _selfx)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) + (((3.0 * ((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        return catmullRomTangent_api_mulAdd_s3a24048a_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd_s3a24048a_1(java.lang.foreign.MemorySegment dest, long destOffset, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) + (((3.0 * ((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z))) * (_t0) + (p2Z - _selfz)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfw) + (((4.0) * (p2W) - (p3W))))) - (5.0) * (p1W))) + (((3.0 * ((((3.0) * (p1W) + (p3W - _selfw))) - (3.0) * (p2W))) * (_t0) + (p2W - _selfw)))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRomTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRomTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3x = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        return catmullRomTangent_api_fma_sdb93b2bc_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRomTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma_sdb93b2bc_1(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w) {
        double _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return catmullRomTangent_api_fma_sdb93b2bc_2(dest, destOffset, t, _selfw, _p1w, _p2w, _p3w, _t0);
    }

    /** Piece 3 of {@code catmullRomTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma_sdb93b2bc_2(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfw, double _p1w, double _p2w, double _p3w, double _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1w, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, _p2w, -_p3w))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2w, java.lang.Math.fma(3.0, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3x = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        return catmullRomTangent_api_mulAdd_s9177d4dd_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRomTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd_s9177d4dd_1(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w) {
        double _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) + (((3.0 * ((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) + (((3.0 * ((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) + (((3.0 * ((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z))) * (_t0) + (_p2z - _selfz)))));
        return catmullRomTangent_api_mulAdd_s9177d4dd_2(dest, destOffset, t, _selfw, _p1w, _p2w, _p3w, _t0);
    }

    /** Piece 3 of {@code catmullRomTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd_s9177d4dd_2(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfw, double _p1w, double _p2w, double _p3w, double _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfw) + (((4.0) * (_p2w) - (_p3w))))) - (5.0) * (_p1w))) + (((3.0 * ((((3.0) * (_p1w) + (_p3w - _selfw))) - (3.0) * (_p2w))) * (_t0) + (_p2w - _selfw)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_api_fma(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return hermite_api_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
    }

    /** {@code hermite_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_selfw, _t10, t0W * _t7) + java.lang.Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    /** {@code hermite_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * ((t) * (t) - (t));
        double _t7 = ((t - 2.0) * (_t0) + (t));
        double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
        double _t10 = ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) * (_t10) + (t0W * _t7)) + ((t1W) * (_t5) + (v1W * _t9)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_api_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_api_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermite_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0y = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 8L);
        double _t0z = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 16L);
        double _t0w = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 24L);
        double _v1y = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 8L);
        double _v1z = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 16L);
        double _v1w = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 24L);
        double _t1y = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 8L);
        double _t1z = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 16L);
        double _t1w = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 24L);
        double _t0 = t * t;
        return hermite_api_fma_s2863995b_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t0, t * _t0, t * java.lang.Math.fma(t, t, -t), java.lang.Math.fma(t - 2.0, _t0, t));
    }

    /** Piece 2 of {@code hermite_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_fma_s2863995b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double _selfy, double _selfz, double _selfw, double _t0y, double _t0z, double _t0w, double _v1y, double _v1z, double _v1w, double _t1y, double _t1z, double _t1w, double _t0, double _t2, double _t5, double _t7) {
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t10, t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset) * _t7) + java.lang.Math.fma(t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset), _t5, v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset) * _t9));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_selfw, _t10, _t0w * _t7) + java.lang.Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    /** {@code hermite_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0y = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 8L);
        double _t0z = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 16L);
        double _t0w = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 24L);
        double _v1y = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 8L);
        double _v1z = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 16L);
        double _v1w = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 24L);
        double _t1y = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 8L);
        double _t1z = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 16L);
        double _t1w = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 24L);
        double _t0 = t * t;
        return hermite_api_mulAdd_s22893c70_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t0, t * _t0, t * ((t) * (t) - (t)), ((t - 2.0) * (_t0) + (t)));
    }

    /** Piece 2 of {@code hermite_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd_s22893c70_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double _selfy, double _selfz, double _selfw, double _t0y, double _t0z, double _t0w, double _v1y, double _v1z, double _v1w, double _t1y, double _t1z, double _t1w, double _t0, double _t2, double _t5, double _t7) {
        double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
        double _t10 = ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0))));
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t10) + (t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset) * _t7)) + ((t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset)) * (_t5) + (v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset) * _t9)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) * (_t10) + (_t0w * _t7)) + ((_t1w) * (_t5) + (_v1w * _t9)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_api_fma(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return hermiteTangent_api_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
    }

    /** {@code hermiteTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_selfw, _t6, t0W * _t9) + java.lang.Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    /** {@code hermiteTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t6 = 6.0 * ((t) * (t) - (t));
        double _t7 = 6.0 * ((t) - (t) * (t));
        double _t8 = ((3.0) * (_t0) - (t + t));
        double _t9 = ((3.0) * (_t0) + (((1.0) - (4.0) * (t))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) * (_t6) + (t0W * _t9)) + ((t1W) * (_t8) + (v1W * _t7)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_api_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_api_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermiteTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0y = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 8L);
        double _t0z = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 16L);
        double _t0w = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 24L);
        double _v1y = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 8L);
        double _v1z = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 16L);
        double _v1w = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 24L);
        double _t1y = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 8L);
        double _t1z = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 16L);
        double _t1w = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 24L);
        return hermiteTangent_api_fma_s19be2160_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, t * t, 6.0 * java.lang.Math.fma(t, t, -t), 6.0 * java.lang.Math.fma(-t, t, t));
    }

    /** Piece 2 of {@code hermiteTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_fma_s19be2160_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t, double _selfy, double _selfz, double _selfw, double _t0y, double _t0z, double _t0w, double _v1y, double _v1z, double _v1w, double _t1y, double _t1z, double _t1w, double _t0, double _t6, double _t7) {
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset) * _t9) + java.lang.Math.fma(t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset), _t8, v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset) * _t7));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_selfw, _t6, _t0w * _t9) + java.lang.Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    /** {@code hermiteTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0y = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 8L);
        double _t0z = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 16L);
        double _t0w = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 24L);
        double _v1y = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 8L);
        double _v1z = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 16L);
        double _v1w = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 24L);
        double _t1y = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 8L);
        double _t1z = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 16L);
        double _t1w = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 24L);
        double _t0 = t * t;
        return hermiteTangent_api_mulAdd_sdcabaefd_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t0, 6.0 * ((t) * (t) - (t)), 6.0 * ((t) - (t) * (t)), ((3.0) * (_t0) - (t + t)));
    }

    /** Piece 2 of {@code hermiteTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd_sdcabaefd_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t, double _selfy, double _selfz, double _selfw, double _t0y, double _t0z, double _t0w, double _v1y, double _v1z, double _v1w, double _t1y, double _t1z, double _t1w, double _t0, double _t6, double _t7, double _t8) {
        double _t9 = ((3.0) * (_t0) + (((1.0) - (4.0) * (t))));
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t6) + (t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset) * _t9)) + ((t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset)) * (_t8) + (v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset) * _t7)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) * (_t6) + (_t0w * _t9)) + ((_t1w) * (_t8) + (_v1w * _t7)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (otherY - _selfy) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((t) * (otherW - _selfw) + (_selfw)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) - _selfx) + (_selfx)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (_othery - _selfy) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((t) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(tZ, otherZ - _selfz, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(tW, otherW - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((tX) * (otherX - _selfx) + (_selfx)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((tY) * (otherY - _selfy) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((tZ) * (otherZ - _selfz) + (_selfz)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((tW) * (otherW - _selfw) + (_selfw)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    /** {@code lerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _ty = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset + 8L);
        double _tz = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset + 16L);
        double _tw = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t.get(_JAVA_DOUBLE_UNALIGNED, tOffset), other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_tz, _otherz - _selfz, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _ty = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset + 8L);
        double _tz = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset + 16L);
        double _tw = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t.get(_JAVA_DOUBLE_UNALIGNED, tOffset)) * (other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) - _selfx) + (_selfx)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_ty) * (_othery - _selfy) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_tz) * (_otherz - _selfz) + (_selfz)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_tw) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_api_fma_s778833b6_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s778833b6_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28) {
        double _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        double _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        double _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        double _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        double _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        double _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        double _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        double _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        double _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        double _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        return slerp_api_fma_s778833b6_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s778833b6_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t16, double _t19, double _t21, double _t24, double _t49, double _t50, double _t51, double _t52, double _t64, double _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_api_mulAdd_s674704f3_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s674704f3_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28) {
        double _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        double _t40 = ((otherW) * (_t14) - (_t31 * _t19));
        double _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        double _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        double _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        double _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        double _t49 = ((_t48) * (_t19) + (_t40));
        double _t50 = ((_t48) * (_t21) + (_t41));
        double _t51 = ((_t48) * (_t16) + (_t42));
        double _t52 = ((_t48) * (_t24) + (_t43));
        double _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _t64 = _t28 * Math.cosWithSin(_t61);
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        return slerp_api_mulAdd_s674704f3_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s674704f3_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t19, double _t21, double _t24, double _t49, double _t50, double _t52, double _t64, double _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 5.048709793414476E-29) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((t) * (otherW - _selfw) + (_selfw)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_api_fma_s3f688c7b_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t9, _t10, (1.0 / java.lang.Math.sqrt(_t9)), (1.0 / java.lang.Math.sqrt(_t10)));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s3f688c7b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _otherx, double _othery, double _otherz, double _otherw, double _t9, double _t10, double _t11, double _t14) {
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        double _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        double _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        double _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        double _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        double _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        double _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        double _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        double _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        double _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        return slerp_api_fma_s3f688c7b_2(dest, destOffset, src, srcOffset, other, otherOffset, t, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), _t31, _t49, _t50, _t51, _t52, java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52))));
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s3f688c7b_2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double _t16, double _t19, double _t21, double _t24, double _t28, double _t31, double _t49, double _t50, double _t51, double _t52, double _t57) {
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _t64 = _t28 * Math.cosWithSin(_t61);
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_api_mulAdd_se0fa7f4_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _selfy, _selfz, _otherx, _othery, _otherz, _otherw, _t9, _t10, _t11, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11);
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_se0fa7f4_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _otherw, double _t9, double _t10, double _t11, double _t14, double _t16, double _t19) {
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        double _t28 = t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9);
        double _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        double _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        double _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        double _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        double _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        double _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        double _t49 = ((_t48) * (_t19) + (_t40));
        double _t50 = ((_t48) * (_t21) + (_t41));
        double _t51 = ((_t48) * (_t16) + (_t42));
        double _t52 = ((_t48) * (_t24) + (_t43));
        double _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        return slerp_api_mulAdd_se0fa7f4_2(dest, destOffset, _t16, _t19, _t21, _t24, _t28, _t49, _t50, _t51, _t52, _t57, _t61, _t28 * Math.cosWithSin(_t61));
    }

    /** Piece 3 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_se0fa7f4_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t16, double _t19, double _t21, double _t24, double _t28, double _t49, double _t50, double _t51, double _t52, double _t57, double _t61, double _t64) {
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
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
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 5.048709793414476E-29) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (_othery - _selfy) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((t) * (_otherw - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_api_fma_sbc14a8cd_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_sbc14a8cd_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28) {
        double _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        double _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        double _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        double _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        double _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        double _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        double _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        double _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        double _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        double _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        return slerp_api_fma_sbc14a8cd_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_sbc14a8cd_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t16, double _t19, double _t21, double _t24, double _t49, double _t50, double _t51, double _t52, double _t64, double _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_api_mulAdd_s9485a260_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s9485a260_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28) {
        double _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        double _t40 = ((otherW) * (_t14) - (_t31 * _t19));
        double _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        double _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        double _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        double _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        double _t49 = ((_t48) * (_t19) + (_t40));
        double _t50 = ((_t48) * (_t21) + (_t41));
        double _t51 = ((_t48) * (_t16) + (_t42));
        double _t52 = ((_t48) * (_t24) + (_t43));
        double _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _t64 = _t28 * Math.cosWithSin(_t61);
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        return slerp_api_mulAdd_s9485a260_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s9485a260_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t19, double _t21, double _t24, double _t49, double _t50, double _t52, double _t64, double _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((t) * (otherW - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
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
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_api_fma_s5fd9d918_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t9, _t10, (1.0 / java.lang.Math.sqrt(_t9)));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s5fd9d918_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _otherx, double _othery, double _otherz, double _otherw, double _t9, double _t10, double _t11) {
        double _t14 = (1.0 / java.lang.Math.sqrt(_t10));
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        double _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        double _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        double _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        double _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        double _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        double _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        double _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        double _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        double _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        return slerp_api_fma_s5fd9d918_2(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), _t31, _t49, _t50, _t51, _t52, java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52))));
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s5fd9d918_2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon, double _t16, double _t19, double _t21, double _t24, double _t28, double _t31, double _t49, double _t50, double _t51, double _t52, double _t57) {
        if (!(_t57 > epsilon && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _t64 = _t28 * Math.cosWithSin(_t61);
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_api_mulAdd_s4619cc57_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t9, _t10, (1.0 / java.lang.Math.sqrt(_t9)), (1.0 / java.lang.Math.sqrt(_t10)));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s4619cc57_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _otherx, double _othery, double _otherz, double _otherw, double _t9, double _t10, double _t11, double _t14) {
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        double _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        double _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        double _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        double _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        double _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        double _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        double _t49 = ((_t48) * (_t19) + (_t40));
        double _t50 = ((_t48) * (_t21) + (_t41));
        double _t51 = ((_t48) * (_t16) + (_t42));
        double _t52 = ((_t48) * (_t24) + (_t43));
        double _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_api_mulAdd_s4619cc57_2(dest, destOffset, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), _t49, _t50, _t51, _t52, _t57, t * Math.atan2(java.lang.Math.sqrt(_t57), _t31));
    }

    /** Piece 3 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s4619cc57_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t16, double _t19, double _t21, double _t24, double _t28, double _t49, double _t50, double _t51, double _t52, double _t57, double _t61) {
        double _t64 = _t28 * Math.cosWithSin(_t61);
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
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
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (_othery - _selfy) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((t) * (_otherw - _selfw) + (_selfw)));
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.abs(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.abs(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.abs(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.acos(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.acos(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.acos(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.acos(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double scalar) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, scalar);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(scalar, bX, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(scalar, bY, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(scalar, bZ, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(scalar, bW, _selfw));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((scalar) * (bX) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((scalar) * (bY) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((scalar) * (bZ) + (_selfz)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((scalar) * (bW) + (_selfw)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _bw = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(scalar, b.get(_JAVA_DOUBLE_UNALIGNED, bOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(scalar, _by, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(scalar, _bz, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(scalar, _bw, _selfw));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _bw = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((scalar) * (b.get(_JAVA_DOUBLE_UNALIGNED, bOffset)) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((scalar) * (_by) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((scalar) * (_bz) + (_selfz)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((scalar) * (_bw) + (_selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(bX, cX, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(bY, cY, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(bZ, cZ, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(bW, cW, _selfw));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((bX) * (cX) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((bY) * (cY) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((bZ) * (cZ) + (_selfz)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((bW) * (cW) + (_selfw)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@code addScaled_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _bw = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 24L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        double _cw = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(b.get(_JAVA_DOUBLE_UNALIGNED, bOffset), c.get(_JAVA_DOUBLE_UNALIGNED, cOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_by, _cy, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_bz, _cz, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _bw = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 24L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        double _cw = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((b.get(_JAVA_DOUBLE_UNALIGNED, bOffset)) * (c.get(_JAVA_DOUBLE_UNALIGNED, cOffset)) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_by) * (_cy) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_bz) * (_cz) + (_selfz)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_bw) * (_cw) + (_selfw)));
        return dest;
    }

    public static double angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_api_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t6 = otherZ * -_selfw;
        double _t7 = otherY * -_selfw;
        double _t8 = -(otherY * _selfz);
        double _t9 = -otherX * _selfw;
        double _t10 = -otherX * _selfy;
        double _t11 = -otherX * _selfz;
        double _t24 = java.lang.Math.fma(otherW, _selfz, _t6) - java.lang.Math.fma(otherZ, _selfw, _t6);
        double _t25 = java.lang.Math.fma(otherW, _selfy, _t7) - java.lang.Math.fma(otherY, _selfw, _t7);
        double _t26 = java.lang.Math.fma(otherZ, _selfy, _t8) - java.lang.Math.fma(otherY, _selfz, _t8);
        double _t27 = java.lang.Math.fma(otherW, _selfx, _t9) - java.lang.Math.fma(otherX, _selfw, _t9);
        double _t28 = java.lang.Math.fma(otherY, _selfx, _t10) - java.lang.Math.fma(otherX, _selfy, _t10);
        double _t29 = java.lang.Math.fma(otherZ, _selfx, _t11) - java.lang.Math.fma(otherX, _selfz, _t11);
        return angleBetween_api_fma_s589942d3_1(src, srcOffset, otherX, otherY, otherZ, otherW, _selfx, _selfy, _selfz, _selfw, java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29))))));
    }

    /** Piece 2 of {@code angleBetween_api_fma}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_api_fma_s589942d3_1(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double _selfx, double _selfy, double _selfz, double _selfw, double _ct0) {
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherW, _selfw, java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    /** {@code angleBetween_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t6 = otherZ * -_selfw;
        double _t7 = otherY * -_selfw;
        double _t8 = -(otherY * _selfz);
        double _t9 = -otherX * _selfw;
        double _t10 = -otherX * _selfy;
        double _t11 = -otherX * _selfz;
        double _t24 = ((otherW) * (_selfz) + (_t6)) - ((otherZ) * (_selfw) + (_t6));
        double _t25 = ((otherW) * (_selfy) + (_t7)) - ((otherY) * (_selfw) + (_t7));
        double _t26 = ((otherZ) * (_selfy) + (_t8)) - ((otherY) * (_selfz) + (_t8));
        double _t27 = ((otherW) * (_selfx) + (_t9)) - ((otherX) * (_selfw) + (_t9));
        double _t28 = ((otherY) * (_selfx) + (_t10)) - ((otherX) * (_selfy) + (_t10));
        double _t29 = ((otherZ) * (_selfx) + (_t11)) - ((otherX) * (_selfz) + (_t11));
        return angleBetween_api_mulAdd_sbb52a7a_1(src, srcOffset, otherX, otherY, otherZ, otherW, _selfx, _selfy, _selfz, _selfw, ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))))))))));
    }

    /** Piece 2 of {@code angleBetween_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_api_mulAdd_sbb52a7a_1(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double _selfx, double _selfy, double _selfz, double _selfw, double _ct0) {
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2(java.lang.Math.sqrt(_ct0), ((otherW) * (_selfw) + (((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))))));
    }

    public static double angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double4OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = otherW * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = otherZ * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = otherY * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = otherX * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        double _t31 = -_t22 * _t23;
        double _t32 = -_t22 * _t17;
        double _t33 = _t22 * -_t19;
        return angleBetween_degenerate_api_fma_sd32b9046_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -(_t20 * _t17), _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32), java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_api_fma_sd32b9046_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t34, double _t35, double _t48, double _t49, double _t50, double _t51) {
        double _t52 = java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34);
        double _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = otherW * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = otherZ * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = otherY * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = otherX * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        double _t31 = -_t22 * _t23;
        double _t32 = -_t22 * _t17;
        double _t33 = _t22 * -_t19;
        return angleBetween_degenerate_api_mulAdd_sa6ce06e1_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -(_t20 * _t17), _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_api_mulAdd_sa6ce06e1_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t34, double _t35, double _t48, double _t49, double _t50, double _t51) {
        double _t52 = ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34));
        double _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static double angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double4OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_api_fma(src, srcOffset, other, otherOffset);
        return angleBetween_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    /** {@code angleBetween_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t6 = _otherz * -_selfw;
        double _t7 = _othery * -_selfw;
        double _t8 = -(_othery * _selfz);
        double _t9 = -_otherx * _selfw;
        double _t10 = -_otherx * _selfy;
        return angleBetween_api_fma_sf1a2de56_1(src, srcOffset, other, otherOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, -_otherx * _selfz, java.lang.Math.fma(_otherw, _selfz, _t6) - java.lang.Math.fma(_otherz, _selfw, _t6), java.lang.Math.fma(_otherw, _selfy, _t7) - java.lang.Math.fma(_othery, _selfw, _t7), java.lang.Math.fma(_otherz, _selfy, _t8) - java.lang.Math.fma(_othery, _selfz, _t8), java.lang.Math.fma(_otherw, _selfx, _t9) - java.lang.Math.fma(_otherx, _selfw, _t9), java.lang.Math.fma(_othery, _selfx, _t10) - java.lang.Math.fma(_otherx, _selfy, _t10));
    }

    /** Piece 2 of {@code angleBetween_api_fma}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_api_fma_sf1a2de56_1(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _otherx, double _othery, double _otherz, double _otherw, double _t11, double _t24, double _t25, double _t26, double _t27, double _t28) {
        double _t29 = java.lang.Math.fma(_otherz, _selfx, _t11) - java.lang.Math.fma(_otherx, _selfz, _t11);
        double _ct0 = java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherw, _selfw, java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    /** {@code angleBetween_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t6 = _otherz * -_selfw;
        double _t7 = _othery * -_selfw;
        double _t8 = -(_othery * _selfz);
        double _t9 = -_otherx * _selfw;
        double _t10 = -_otherx * _selfy;
        double _t11 = -_otherx * _selfz;
        return angleBetween_api_mulAdd_s810623dd_1(src, srcOffset, other, otherOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, ((_otherw) * (_selfz) + (_t6)) - ((_otherz) * (_selfw) + (_t6)), ((_otherw) * (_selfy) + (_t7)) - ((_othery) * (_selfw) + (_t7)), ((_otherz) * (_selfy) + (_t8)) - ((_othery) * (_selfz) + (_t8)), ((_otherw) * (_selfx) + (_t9)) - ((_otherx) * (_selfw) + (_t9)), ((_othery) * (_selfx) + (_t10)) - ((_otherx) * (_selfy) + (_t10)), ((_otherz) * (_selfx) + (_t11)) - ((_otherx) * (_selfz) + (_t11)));
    }

    /** Piece 2 of {@code angleBetween_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_api_mulAdd_s810623dd_1(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _otherx, double _othery, double _otherz, double _otherw, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29) {
        double _ct0 = ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))))))))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(java.lang.Math.sqrt(_ct0), ((_otherw) * (_selfw) + (((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))))));
    }

    public static double angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Double4OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, other, otherOffset);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
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
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = _otherw * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = _otherz * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = _othery * _t6;
        double _t22 = _otherx * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        return angleBetween_degenerate_api_fma_s753ecfb1_1(_t16, _t17, _t18, _t19, _t20, _selfx * _t7, _t22, _t23, -_t22 * _t23, -_t22 * _t17, _t22 * -_t19, -(_t20 * _t17), _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_api_fma_s753ecfb1_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t31, double _t32, double _t33, double _t34, double _t35, double _t48) {
        double _t49 = java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31);
        double _t50 = java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32);
        double _t51 = java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33);
        double _t52 = java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34);
        double _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = _otherw * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = _otherz * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = _othery * _t6;
        double _t22 = _otherx * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        return angleBetween_degenerate_api_mulAdd_s987d4298_1(_t16, _t17, _t18, _t19, _t20, _selfx * _t7, _t22, _t23, -_t22 * _t23, -_t22 * _t17, _t22 * -_t19, -(_t20 * _t17), _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_api_mulAdd_s987d4298_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t31, double _t32, double _t33, double _t34, double _t35, double _t48) {
        double _t49 = ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31));
        double _t50 = ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32));
        double _t51 = ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33));
        double _t52 = ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34));
        double _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static java.lang.foreign.MemorySegment asin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment asin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.asin(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.asin(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.asin(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.asin(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.atan(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double x) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double x) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), x));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_selfy, x));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_selfz, x));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.atan2(_selfw, x));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double xX, double xY, double xZ, double xW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ, xW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double xX, double xY, double xZ, double xW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), xX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_selfy, xY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_selfz, xZ));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.atan2(_selfw, xW));
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _xy = x.get(_JAVA_DOUBLE_UNALIGNED, xOffset + 8L);
        double _xz = x.get(_JAVA_DOUBLE_UNALIGNED, xOffset + 16L);
        double _xw = x.get(_JAVA_DOUBLE_UNALIGNED, xOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), x.get(_JAVA_DOUBLE_UNALIGNED, xOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_selfy, _xy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_selfz, _xz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.atan2(_selfw, _xw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.cbrt(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.cbrt(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.cbrt(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.cbrt(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.ceil(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.ceil(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.ceil(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.ceil(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double min, double max) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double min, double max) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), min), max));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfy, min), max));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(Math.max(_selfz, min), max));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.min(Math.max(_selfw, min), max));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), minX), maxX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfy, minY), maxY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(Math.max(_selfz, minZ), maxZ));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.min(Math.max(_selfw, minW), maxW));
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _miny = min.get(_JAVA_DOUBLE_UNALIGNED, minOffset + 8L);
        double _minz = min.get(_JAVA_DOUBLE_UNALIGNED, minOffset + 16L);
        double _minw = min.get(_JAVA_DOUBLE_UNALIGNED, minOffset + 24L);
        double _maxy = max.get(_JAVA_DOUBLE_UNALIGNED, maxOffset + 8L);
        double _maxz = max.get(_JAVA_DOUBLE_UNALIGNED, maxOffset + 16L);
        double _maxw = max.get(_JAVA_DOUBLE_UNALIGNED, maxOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), min.get(_JAVA_DOUBLE_UNALIGNED, minOffset)), max.get(_JAVA_DOUBLE_UNALIGNED, maxOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfy, _miny), _maxy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(Math.max(_selfz, _minz), _maxz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.min(Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static double compAdd_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static double compAdd_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) + src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)));
    }

    public static double compMax_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static double compMax_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return Math.max(Math.max(Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L));
    }

    public static double compMin_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static double compMin_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return Math.min(Math.min(Math.min(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L));
    }

    public static double compMul_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static double compMul_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sign) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sign) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), sign));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.copySign(_selfy, sign));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.copySign(_selfz, sign));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.copySign(_selfw, sign));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double signX, double signY, double signZ, double signW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ, signW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double signX, double signY, double signZ, double signW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), signX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.copySign(_selfy, signY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.copySign(_selfz, signZ));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.copySign(_selfw, signW));
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _signy = sign.get(_JAVA_DOUBLE_UNALIGNED, signOffset + 8L);
        double _signz = sign.get(_JAVA_DOUBLE_UNALIGNED, signOffset + 16L);
        double _signw = sign.get(_JAVA_DOUBLE_UNALIGNED, signOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), sign.get(_JAVA_DOUBLE_UNALIGNED, signOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.copySign(_selfy, _signy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.copySign(_selfz, _signz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.cos(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.cos(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.cos(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.cos(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.cosh(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.cosh(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.cosh(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.cosh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_api_fma(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return cross_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
    }

    /** {@code cross_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t6 = -vZ * wY;
        double _t7 = -vW * wZ;
        double _t8 = -vW * wY;
        double _t9 = -vZ * wX;
        double _t10 = -vW * wX;
        double _t11 = -(vY * wX);
        double _t24 = java.lang.Math.fma(vY, wZ, _t6) - java.lang.Math.fma(vZ, wY, _t6);
        double _t25 = java.lang.Math.fma(vZ, wW, _t7) - java.lang.Math.fma(vW, wZ, _t7);
        double _t26 = java.lang.Math.fma(vY, wW, _t8) - java.lang.Math.fma(vW, wY, _t8);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        return cross_api_fma_s459006bf_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t24, _t25, _t26, java.lang.Math.fma(vX, wZ, _t9) - java.lang.Math.fma(vZ, wX, _t9), java.lang.Math.fma(vX, wW, _t10) - java.lang.Math.fma(vW, wX, _t10), java.lang.Math.fma(vX, wY, _t11) - java.lang.Math.fma(vY, wX, _t11));
    }

    /** Piece 2 of {@code cross_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_fma_s459006bf_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t6 = -vZ * wY;
        double _t7 = -vW * wZ;
        double _t8 = -vW * wY;
        double _t9 = -vZ * wX;
        double _t10 = -vW * wX;
        double _t11 = -(vY * wX);
        double _t24 = ((vY) * (wZ) + (_t6)) - ((vZ) * (wY) + (_t6));
        double _t25 = ((vZ) * (wW) + (_t7)) - ((vW) * (wZ) + (_t7));
        double _t26 = ((vY) * (wW) + (_t8)) - ((vW) * (wY) + (_t8));
        double _t27 = ((vX) * (wZ) + (_t9)) - ((vZ) * (wX) + (_t9));
        double _t28 = ((vX) * (wW) + (_t10)) - ((vW) * (wX) + (_t10));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        return cross_api_mulAdd_sc87aeece_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t24, _t26, _t27, _t28, ((vX) * (wY) + (_t11)) - ((vY) * (wX) + (_t11)));
    }

    /** Piece 2 of {@code cross_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd_sc87aeece_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t24, double _t26, double _t27, double _t28, double _t29) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_api_fma(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
        return cross_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
    }

    /** {@code cross_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset, java.lang.foreign.MemorySegment w, long wOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        double _vw = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 24L);
        double _wx = w.get(_JAVA_DOUBLE_UNALIGNED, wOffset);
        double _wy = w.get(_JAVA_DOUBLE_UNALIGNED, wOffset + 8L);
        double _wz = w.get(_JAVA_DOUBLE_UNALIGNED, wOffset + 16L);
        double _ww = w.get(_JAVA_DOUBLE_UNALIGNED, wOffset + 24L);
        double _t6 = -_vz * _wy;
        double _t7 = -_vw * _wz;
        return cross_api_fma_sf7de294d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _vx, _vy, _vz, _vw, _wx, _wy, _wz, _ww, -_vw * _wy, -_vz * _wx, -_vw * _wx, -(_vy * _wx), java.lang.Math.fma(_vy, _wz, _t6) - java.lang.Math.fma(_vz, _wy, _t6), java.lang.Math.fma(_vz, _ww, _t7) - java.lang.Math.fma(_vw, _wz, _t7));
    }

    /** Piece 2 of {@code cross_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_fma_sf7de294d_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _vx, double _vy, double _vz, double _vw, double _wx, double _wy, double _wz, double _ww, double _t8, double _t9, double _t10, double _t11, double _t24, double _t25) {
        double _t26 = java.lang.Math.fma(_vy, _ww, _t8) - java.lang.Math.fma(_vw, _wy, _t8);
        double _t27 = java.lang.Math.fma(_vx, _wz, _t9) - java.lang.Math.fma(_vz, _wx, _t9);
        double _t28 = java.lang.Math.fma(_vx, _ww, _t10) - java.lang.Math.fma(_vw, _wx, _t10);
        double _t29 = java.lang.Math.fma(_vx, _wy, _t11) - java.lang.Math.fma(_vy, _wx, _t11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset, java.lang.foreign.MemorySegment w, long wOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        double _vw = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 24L);
        double _wx = w.get(_JAVA_DOUBLE_UNALIGNED, wOffset);
        double _wy = w.get(_JAVA_DOUBLE_UNALIGNED, wOffset + 8L);
        double _wz = w.get(_JAVA_DOUBLE_UNALIGNED, wOffset + 16L);
        double _ww = w.get(_JAVA_DOUBLE_UNALIGNED, wOffset + 24L);
        double _t6 = -_vz * _wy;
        double _t7 = -_vw * _wz;
        return cross_api_mulAdd_s99389cc0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _vx, _vy, _vz, _vw, _wx, _wy, _wz, _ww, -_vw * _wy, -_vz * _wx, -_vw * _wx, -(_vy * _wx), ((_vy) * (_wz) + (_t6)) - ((_vz) * (_wy) + (_t6)), ((_vz) * (_ww) + (_t7)) - ((_vw) * (_wz) + (_t7)));
    }

    /** Piece 2 of {@code cross_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd_s99389cc0_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _vx, double _vy, double _vz, double _vw, double _wx, double _wy, double _wz, double _ww, double _t8, double _t9, double _t10, double _t11, double _t24, double _t25) {
        double _t26 = ((_vy) * (_ww) + (_t8)) - ((_vw) * (_wy) + (_t8));
        double _t27 = ((_vx) * (_wz) + (_t9)) - ((_vz) * (_wx) + (_t9));
        double _t28 = ((_vx) * (_ww) + (_t10)) - ((_vw) * (_wx) + (_t10));
        double _t29 = ((_vx) * (_wy) + (_t11)) - ((_vy) * (_wx) + (_t11));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.toDegrees(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.toDegrees(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.toDegrees(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.toDegrees(_selfw));
        return dest;
    }

    public static double distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double distance_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - otherW;
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ;
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX;
            double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - otherW;
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ;
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX;
            double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY;
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
        }
    }

    public static double distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double4OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static double distance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return distance_api_mulAdd(src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
    }

    /** {@code distance_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double distance_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
    }

    public static double distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - otherW;
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ;
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX;
            double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - otherW;
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ;
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX;
            double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }

    public static double distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static double distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return distanceSquared_api_mulAdd(src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
    }

    /** {@code distanceSquared_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double distanceSquared_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
    }

    public static double dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double dot_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherW, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), java.lang.Math.fma(otherZ, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), java.lang.Math.fma(otherX, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), otherY * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L))));
        } else {
            return ((otherW) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)) + (((otherZ) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) + (((otherX) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) + (otherY * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)))))));
        }
    }

    public static double dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double4OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), java.lang.Math.fma(other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), java.lang.Math.fma(other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L) * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L))));
        } else {
            return ((other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L)) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)) + (((other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L)) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) + (((other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset)) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) + (other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L) * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)))))));
        }
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.exp(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.exp(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.exp(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.exp(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(2.0, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(2.0, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(2.0, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.pow(2.0, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.expm1(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.expm1(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.expm1(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.expm1(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_api_mulAdd(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0 ? 1.0 : -1.0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t4);
        return dest;
    }

    /** {@code faceforward_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment faceforward_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0 ? 1.0 : -1.0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t4);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_api_fma(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return faceforward_api_mulAdd(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
    }

    /** {@code faceforward_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment faceforward_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = java.lang.Math.fma(I.get(_JAVA_DOUBLE_UNALIGNED, IOffset + 24L), Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset + 24L), java.lang.Math.fma(I.get(_JAVA_DOUBLE_UNALIGNED, IOffset + 16L), Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset + 16L), java.lang.Math.fma(I.get(_JAVA_DOUBLE_UNALIGNED, IOffset), Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset), I.get(_JAVA_DOUBLE_UNALIGNED, IOffset + 8L) * Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset + 8L)))) < 0.0 ? 1.0 : -1.0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t4);
        return dest;
    }

    /** {@code faceforward_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment faceforward_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = ((I.get(_JAVA_DOUBLE_UNALIGNED, IOffset + 24L)) * (Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset + 24L)) + (((I.get(_JAVA_DOUBLE_UNALIGNED, IOffset + 16L)) * (Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset + 16L)) + (((I.get(_JAVA_DOUBLE_UNALIGNED, IOffset)) * (Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset)) + (I.get(_JAVA_DOUBLE_UNALIGNED, IOffset + 8L) * Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset + 8L))))))) < 0.0 ? 1.0 : -1.0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t4);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.floor(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.floor(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.floor(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.floor(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(_selfz - Math.floor(_selfz), 0.9999999999999999));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.min(_selfw - Math.floor(_selfw), 0.9999999999999999));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), y));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.hypot(_selfy, y));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.hypot(_selfz, y));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.hypot(_selfw, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ, double yW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ, double yW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), yX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.hypot(_selfy, yY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.hypot(_selfz, yZ));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.hypot(_selfw, yW));
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _yy = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset + 8L);
        double _yz = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset + 16L);
        double _yw = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), y.get(_JAVA_DOUBLE_UNALIGNED, yOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.hypot(_selfy, _yy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.hypot(_selfz, _yz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.hypot(_selfw, _yw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 1.0 / src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 1.0 / _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 1.0 / _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 1.0 / _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, (1.0 / java.lang.Math.sqrt(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, (1.0 / java.lang.Math.sqrt(_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, (1.0 / java.lang.Math.sqrt(_selfz)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, (1.0 / java.lang.Math.sqrt(_selfw)));
        return dest;
    }

    public static double length_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static double length_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            return java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))));
        } else {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            return java.lang.Math.sqrt(((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))))));
        }
    }

    public static double lengthSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            return java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            return ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        }
    }

    public static java.lang.foreign.MemorySegment log_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.log(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.log(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.log10(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log10(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log10(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.log10(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.log1p(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log1p(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log1p(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.log1p(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.log2(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log2(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log2(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.log2(_selfw));
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset)) + java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L)) + java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L)) + java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L));
    }

    public static double manhattanLength_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static double manhattanLength_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) + java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)) + java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) + java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L));
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), scalar));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.max(_selfy, scalar));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.max(_selfz, scalar));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.max(_selfw, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), otherX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.max(_selfy, otherY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.max(_selfz, otherZ));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.max(_selfw, otherW));
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.max(_selfy, _othery));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.max(_selfz, _otherz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.max(_selfw, _otherw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), scalar));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(_selfy, scalar));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(_selfz, scalar));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.min(_selfw, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), otherX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(_selfy, otherY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(_selfz, otherZ));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.min(_selfw, otherW));
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(_selfy, _othery));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(_selfz, _otherz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.min(_selfw, _otherw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, flooredMod(_selfx, y));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, flooredMod(_selfy, y));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, flooredMod(_selfz, y));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, flooredMod(_selfw, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ, double yW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ, double yW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, flooredMod(_selfx, yX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, flooredMod(_selfy, yY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, flooredMod(_selfz, yZ));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, flooredMod(_selfw, yW));
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _yx = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset);
        double _yy = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset + 8L);
        double _yz = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset + 16L);
        double _yw = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, flooredMod(_selfx, _yx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, flooredMod(_selfy, _yy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, flooredMod(_selfz, _yz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, flooredMod(_selfw, _yw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.nextDown(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.nextDown(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.nextDown(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.nextDown(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.nextUp(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.nextUp(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.nextUp(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.nextUp(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _selfx * _t4);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t4);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t4);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t4);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        }
        return dest;
    }

    /** {@code normalize_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normalize_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _selfx * _t4);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t4);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t4);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t4);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizeMul_api_mulAdd(dest, destOffset, src, srcOffset, length);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _t5 = length * (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _selfx * _t5);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t5);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t5);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t5);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        }
        return dest;
    }

    /** {@code normalizeMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normalizeMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double length) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        double _t5 = length * (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _selfx * _t5);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t5);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t5);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t5);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, rowX * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, rowX * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, rowX * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, rowX * _selfw);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, rowY * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, rowY * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 48L, rowY * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 56L, rowY * _selfw);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 64L, rowZ * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 72L, rowZ * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 80L, rowZ * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 88L, rowZ * _selfw);
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _rowx = row.get(_JAVA_DOUBLE_UNALIGNED, rowOffset);
        double _rowy = row.get(_JAVA_DOUBLE_UNALIGNED, rowOffset + 8L);
        double _rowz = row.get(_JAVA_DOUBLE_UNALIGNED, rowOffset + 16L);
        double _roww = row.get(_JAVA_DOUBLE_UNALIGNED, rowOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _rowx * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _rowx * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _rowx * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _rowx * _selfw);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _rowy * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _rowy * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _rowy * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _rowy * _selfw);
        return outerProduct_api_s908a9879_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _rowz, _roww);
    }

    /** Piece 2 of {@code outerProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment outerProduct_api_s908a9879_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _rowz, double _roww) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _rowz * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 72L, _rowz * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 80L, _rowz * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 88L, _rowz * _selfw);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 96L, _roww * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 104L, _roww * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 112L, _roww * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 120L, _roww * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponent) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponent) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), exponent));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(_selfy, exponent));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(_selfz, exponent));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.pow(_selfw, exponent));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponentX, double exponentY, double exponentZ, double exponentW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponentX, double exponentY, double exponentZ, double exponentW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), exponentX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(_selfy, exponentY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(_selfz, exponentZ));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.pow(_selfw, exponentW));
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _exponenty = exponent.get(_JAVA_DOUBLE_UNALIGNED, exponentOffset + 8L);
        double _exponentz = exponent.get(_JAVA_DOUBLE_UNALIGNED, exponentOffset + 16L);
        double _exponentw = exponent.get(_JAVA_DOUBLE_UNALIGNED, exponentOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), exponent.get(_JAVA_DOUBLE_UNALIGNED, exponentOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(_selfy, _exponenty));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(_selfz, _exponentz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double ontoX, double ontoY, double ontoZ, double ontoW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double ontoX, double ontoY, double ontoZ, double ontoW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_api_mulAdd(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ, ontoW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t9 = java.lang.Math.fma(ontoW, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), java.lang.Math.fma(ontoZ, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), java.lang.Math.fma(ontoX, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), ontoY * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ontoX * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ontoY * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ontoZ * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ontoW * _t9);
        return dest;
    }

    /** {@code project_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment project_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double ontoX, double ontoY, double ontoZ, double ontoW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t9 = ((ontoW) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)) + (((ontoZ) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) + (((ontoX) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) + (ontoY * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L))))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ontoX * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ontoY * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ontoZ * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ontoW * _t9);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_api_mulAdd(dest, destOffset, src, srcOffset, onto, ontoOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _ontox = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset);
        double _ontoy = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset + 8L);
        double _ontoz = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset + 16L);
        double _ontow = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset + 24L);
        double _t9 = java.lang.Math.fma(_ontow, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), java.lang.Math.fma(_ontoz, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), java.lang.Math.fma(_ontox, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _ontoy * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)))) / java.lang.Math.fma(_ontow, _ontow, java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _ontox * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _ontoy * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _ontoz * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _ontow * _t9);
        return dest;
    }

    /** {@code project_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment project_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _ontox = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset);
        double _ontoy = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset + 8L);
        double _ontoz = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset + 16L);
        double _ontow = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset + 24L);
        double _t9 = ((_ontow) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)) + (((_ontoz) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) + (((_ontox) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) + (_ontoy * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L))))))) / ((_ontow) * (_ontow) + (((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _ontox * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _ontoy * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _ontoz * _t9);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _ontow * _t9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_api_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t3 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-normalX, _t3, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-normalY, _t3, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-normalZ, _t3, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(-normalW, _t3, _selfw));
        return dest;
    }

    /** {@code projectOnPlane_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment projectOnPlane_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t3 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) - (normalX) * (_t3)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) - (normalY) * (_t3)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) - (normalZ) * (_t3)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) - (normalW) * (_t3)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _normalw = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 24L);
        double _t3 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-_normalx, _t3, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-_normaly, _t3, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-_normalz, _t3, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    /** {@code projectOnPlane_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment projectOnPlane_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _normalw = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 24L);
        double _t3 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) - (_normalx) * (_t3)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) - (_normaly) * (_t3)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) - (_normalz) * (_t3)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) - (_normalw) * (_t3)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.toRadians(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.toRadians(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.toRadians(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.toRadians(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_api_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = 2.0 * java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-normalX, _t4, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-normalY, _t4, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-normalZ, _t4, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(-normalW, _t4, _selfw));
        return dest;
    }

    /** {@code reflect_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = 2.0 * ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) - (normalX) * (_t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) - (normalY) * (_t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) - (normalZ) * (_t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) - (normalW) * (_t4)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _normalw = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 24L);
        double _t4 = 2.0 * java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-_normalx, _t4, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-_normaly, _t4, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-_normalz, _t4, _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    /** {@code reflect_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _normalw = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 24L);
        double _t4 = 2.0 * ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) - (_normalx) * (_t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) - (_normaly) * (_t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) - (_normalz) * (_t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_selfw) - (_normalw) * (_t4)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return refract_api_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return refract_api_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    /** {@code refract_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        double _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0), eta * eta, 1.0);
        double _t11 = java.lang.Math.fma(eta, _t4, java.lang.Math.sqrt(Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(eta, _selfx, -(normalX * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(eta, _selfy, -(normalY * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(eta, _selfz, -(normalZ * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        }
        return dest;
    }

    /** {@code refract_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        double _t8 = ((((_t4) * (_t4) - (1.0))) * (eta * eta) + (1.0));
        double _t11 = ((eta) * (_t4) + (java.lang.Math.sqrt(Math.max(0.0, _t8))));
        if (_t8 >= 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((eta) * (_selfx) - (normalX * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((eta) * (_selfy) - (normalY * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((eta) * (_selfz) - (normalZ * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((eta) * (_selfw) - (normalW * _t11)));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return refract_api_fma(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return refract_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    /** {@code refract_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, double eta) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _normalw = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 24L);
        double _t4 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        double _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0), eta * eta, 1.0);
        return refract_api_fma_s996dacaa_1(dest, destOffset, eta, _selfx, _selfy, _selfz, _selfw, _normalx, _normaly, _normalz, _normalw, _t8, java.lang.Math.fma(eta, _t4, java.lang.Math.sqrt(Math.max(0.0, _t8))));
    }

    /** Piece 2 of {@code refract_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_fma_s996dacaa_1(java.lang.foreign.MemorySegment dest, long destOffset, double eta, double _selfx, double _selfy, double _selfz, double _selfw, double _normalx, double _normaly, double _normalz, double _normalw, double _t8, double _t11) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (_t8 >= 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(eta, _selfx, -(_normalx * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(eta, _selfy, -(_normaly * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(eta, _selfz, -(_normalz * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        }
        return dest;
    }

    /** {@code refract_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, double eta) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _normalw = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 24L);
        double _t4 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        double _t8 = ((((_t4) * (_t4) - (1.0))) * (eta * eta) + (1.0));
        return refract_api_mulAdd_s7fced9e7_1(dest, destOffset, eta, _selfx, _selfy, _selfz, _selfw, _normalx, _normaly, _normalz, _normalw, _t8, ((eta) * (_t4) + (java.lang.Math.sqrt(Math.max(0.0, _t8)))));
    }

    /** Piece 2 of {@code refract_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_mulAdd_s7fced9e7_1(java.lang.foreign.MemorySegment dest, long destOffset, double eta, double _selfx, double _selfy, double _selfz, double _selfw, double _normalx, double _normaly, double _normalz, double _normalw, double _t8, double _t11) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (_t8 >= 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((eta) * (_selfx) - (_normalx * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((eta) * (_selfy) - (_normaly * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((eta) * (_selfz) - (_normalz * _t11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((eta) * (_selfw) - (_normalw * _t11)));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.rint(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.rint(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.rint(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.rint(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.signum(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.signum(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.signum(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.signum(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.sin(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.sin(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.sin(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.sin(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.sinh(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.sinh(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.sinh(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.sinh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0, double edge1) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0, double edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0, edge1);
        double _t0_inv = 1.0 / (edge1 - edge0);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t13 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        double _t14 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - edge0) * _t0_inv));
        double _t15 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - edge0) * _t0_inv));
        double _t16 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - edge0) * _t0_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-2.0, _t15, 3.0) * _t15 * _t15);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t13 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        double _t14 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - edge0) * _t0_inv));
        double _t15 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - edge0) * _t0_inv));
        double _t16 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - edge0) * _t0_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((3.0) - (2.0) * (_t13)) * _t13 * _t13);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((3.0) - (2.0) * (_t14)) * _t14 * _t14);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((3.0) - (2.0) * (_t15)) * _t15 * _t15);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((3.0) - (2.0) * (_t16)) * _t16 * _t16);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t16 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        double _t17 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - edge0Y) / (edge1Y - edge0Y)));
        double _t18 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - edge0Z) / (edge1Z - edge0Z)));
        double _t19 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - edge0W) / (edge1W - edge0W)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t16 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        double _t17 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - edge0Y) / (edge1Y - edge0Y)));
        double _t18 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - edge0Z) / (edge1Z - edge0Z)));
        double _t19 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - edge0W) / (edge1W - edge0W)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((3.0) - (2.0) * (_t16)) * _t16 * _t16);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((3.0) - (2.0) * (_t17)) * _t17 * _t17);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((3.0) - (2.0) * (_t18)) * _t18 * _t18);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((3.0) - (2.0) * (_t19)) * _t19 * _t19);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_fma(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@code smoothstep_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        double _edge0x = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset);
        double _edge0y = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset + 8L);
        double _edge0z = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset + 16L);
        double _edge0w = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset + 24L);
        return smoothstep_api_fma_sda050f3b_1(dest, destOffset, src, srcOffset, edge1, edge1Offset, _edge0x, _edge0y, _edge0z, _edge0w);
    }

    /** Piece 2 of {@code smoothstep_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_fma_sda050f3b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge1, long edge1Offset, double _edge0x, double _edge0y, double _edge0z, double _edge0w) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t16 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset) - _edge0x)));
        double _t17 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _edge0y) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset + 8L) - _edge0y)));
        double _t18 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _edge0z) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset + 16L) - _edge0z)));
        double _t19 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - _edge0w) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset + 24L) - _edge0w)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        double _edge0x = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset);
        double _edge0y = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset + 8L);
        double _edge0z = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset + 16L);
        double _edge0w = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset + 24L);
        return smoothstep_api_mulAdd_sca174fee_1(dest, destOffset, src, srcOffset, edge1, edge1Offset, _edge0x, _edge0y, _edge0z, _edge0w);
    }

    /** Piece 2 of {@code smoothstep_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd_sca174fee_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge1, long edge1Offset, double _edge0x, double _edge0y, double _edge0z, double _edge0w) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t16 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset) - _edge0x)));
        double _t17 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _edge0y) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset + 8L) - _edge0y)));
        double _t18 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _edge0z) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset + 16L) - _edge0z)));
        double _t19 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - _edge0w) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset + 24L) - _edge0w)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((3.0) - (2.0) * (_t16)) * _t16 * _t16);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((3.0) - (2.0) * (_t17)) * _t17 * _t17);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((3.0) - (2.0) * (_t18)) * _t18 * _t18);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((3.0) - (2.0) * (_t19)) * _t19 * _t19);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.sqrt(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.sqrt(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.sqrt(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.sqrt(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) < edge ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy < edge ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz < edge ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw < edge ? 0.0 : 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edgeX, double edgeY, double edgeZ, double edgeW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edgeX, double edgeY, double edgeZ, double edgeW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) < edgeX ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy < edgeY ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz < edgeZ ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw < edgeW ? 0.0 : 1.0);
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
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _edgey = edge.get(_JAVA_DOUBLE_UNALIGNED, edgeOffset + 8L);
        double _edgez = edge.get(_JAVA_DOUBLE_UNALIGNED, edgeOffset + 16L);
        double _edgew = edge.get(_JAVA_DOUBLE_UNALIGNED, edgeOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) < edge.get(_JAVA_DOUBLE_UNALIGNED, edgeOffset) ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy < _edgey ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz < _edgez ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw < _edgew ? 0.0 : 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.tan(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.tan(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.tan(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.tan(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.tanh(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.tanh(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.tanh(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.tanh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz >= 0.0 ? Math.floor(_selfz) : Math.ceil(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw >= 0.0 ? Math.floor(_selfw) : Math.ceil(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.ulp(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.ulp(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.ulp(_selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.ulp(_selfw));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L);
        double _mat30 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 24L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat31 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 56L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        double _mat32 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 88L);
        double _mat13 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 104L);
        double _mat23 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 112L);
        return preMul_api_fma_s24c057fd_1(dest, destOffset, mat, matOffset, _selfx, _selfy, _selfz, _selfw, _mat10, _mat20, _mat30, _mat11, _mat21, _mat31, _mat12, _mat22, _mat32, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMul_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma_s24c057fd_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment mat, long matOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _mat10, double _mat20, double _mat30, double _mat11, double _mat21, double _mat31, double _mat12, double _mat22, double _mat32, double _mat13, double _mat23) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _mat33 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 120L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 96L), _selfw, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L), _selfz, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset), _selfx, mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L) * _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat13, _selfw, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_mat23, _selfw, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_mat33, _selfw, java.lang.Math.fma(_mat32, _selfz, java.lang.Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L);
        double _mat30 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 24L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat31 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 56L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        double _mat32 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 88L);
        double _mat13 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 104L);
        double _mat23 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 112L);
        return preMul_api_mulAdd_s79fccc84_1(dest, destOffset, mat, matOffset, _selfx, _selfy, _selfz, _selfw, _mat10, _mat20, _mat30, _mat11, _mat21, _mat31, _mat12, _mat22, _mat32, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMul_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd_s79fccc84_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment mat, long matOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _mat10, double _mat20, double _mat30, double _mat11, double _mat21, double _mat31, double _mat12, double _mat22, double _mat32, double _mat13, double _mat23) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _mat33 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 120L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 96L)) * (_selfw) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L)) * (_selfz) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset)) * (_selfx) + (mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L) * _selfy)))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_mat13) * (_selfw) + (((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_mat23) * (_selfw) + (((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_mat33) * (_selfw) + (((_mat32) * (_selfz) + (((_mat30) * (_selfx) + (_mat31 * _selfy)))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(quatX, _selfy, -(quatY * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(quatZ, _selfx, -(quatX * _selfz));
        double _t11 = 2.0 * java.lang.Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, _selfz))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t9 = 2.0 * ((quatX) * (_selfy) - (quatY * _selfx));
        double _t10 = 2.0 * ((quatZ) * (_selfx) - (quatX * _selfz));
        double _t11 = 2.0 * ((quatY) * (_selfz) - (quatZ * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((quatY) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatZ) * (_t10)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((quatZ) * (_t11) + (((((quatW) * (_t10) + (_selfy))) - (quatX) * (_t9)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfz))) - (quatY) * (_t11)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_fma(dest, destOffset, src, srcOffset, quat, quatOffset);
        return rotate_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@code rotate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _quatx = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset);
        double _quaty = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 8L);
        double _quatz = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 16L);
        double _quatw = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        double _t11 = 2.0 * java.lang.Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_quaty, _t9, java.lang.Math.fma(-_quatz, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_quatz, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfy))));
        return rotate_api_fma_se326eea1_1(dest, destOffset, _selfz, _selfw, _quatx, _quaty, _quatw, _t9, _t10, _t11);
    }

    /** Piece 2 of {@code rotate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_fma_se326eea1_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfz, double _selfw, double _quatx, double _quaty, double _quatw, double _t9, double _t10, double _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quaty, _t11, java.lang.Math.fma(_quatw, _t9, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
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
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t2) * (axisX * _t5) + (((_selfx) * (_t0) + (((axisY) * (_selfz) - (axisZ * _selfy)) * _t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t2) * (axisY * _t5) + (((_selfy) * (_t0) + (((axisZ) * (_selfx) - (axisX * _selfz)) * _t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t2) * (axisZ * _t5) + (((_selfz) * (_t0) + (((axisX) * (_selfy) - (axisY * _selfx)) * _t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@code rotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _axisx = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisy = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisz = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _axisx * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _axisy * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t1)));
        return rotateAxis_api_fma_s122590f2_1(dest, destOffset, _axisx, _axisy, _axisz, _selfx, _selfy, _selfz, _selfw, _t0, _t1, _t2, _t5);
    }

    /** Piece 2 of {@code rotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_fma_s122590f2_1(java.lang.foreign.MemorySegment dest, long destOffset, double _axisx, double _axisy, double _axisz, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t1, double _t2, double _t5) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t2, _axisz * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _axisx = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisy = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisz = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t2) * (_axisx * _t5) + (((_selfx) * (_t0) + (((_axisy) * (_selfz) - (_axisz * _selfy)) * _t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t2) * (_axisy * _t5) + (((_selfy) * (_t0) + (((_axisz) * (_selfx) - (_axisx * _selfz)) * _t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t2) * (_axisz * _t5) + (((_selfz) * (_t0) + (((_axisx) * (_selfy) - (_axisy * _selfx)) * _t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(quatX, _selfz, -(quatZ * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(quatY, _selfx, -(quatX * _selfy));
        double _t11 = 2.0 * java.lang.Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfz))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    /** {@code rotateInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t9 = 2.0 * ((quatX) * (_selfz) - (quatZ * _selfx));
        double _t10 = 2.0 * ((quatY) * (_selfx) - (quatX * _selfy));
        double _t11 = 2.0 * ((quatZ) * (_selfy) - (quatY * _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((quatZ) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatY) * (_t10)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfy))) - (quatZ) * (_t11)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((quatY) * (_t11) + (((((quatW) * (_t10) + (_selfz))) - (quatX) * (_t9)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_api_fma(dest, destOffset, src, srcOffset, quat, quatOffset);
        return rotateInverse_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@code rotateInverse_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _quatx = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset);
        double _quaty = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 8L);
        double _quatz = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 16L);
        double _quatw = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        double _t11 = 2.0 * java.lang.Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_quatz, _t9, java.lang.Math.fma(-_quaty, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quatz, _t11, java.lang.Math.fma(_quatw, _t9, _selfy))));
        return rotateInverse_api_fma_s8c83a4c9_1(dest, destOffset, _selfz, _selfw, _quatx, _quaty, _quatw, _t9, _t10, _t11);
    }

    /** Piece 2 of {@code rotateInverse_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_fma_s8c83a4c9_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfz, double _selfw, double _quatx, double _quaty, double _quatw, double _t9, double _t10, double _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_quaty, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    /** {@code rotateInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
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
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateX_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t0, -(_selfz * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfy, _t1, _selfz * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    /** {@code rotateX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_t0) - (_selfz * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfy) * (_t1) + (_selfz * _t0)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateY_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t0, _selfz * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _t0, -(_selfx * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    /** {@code rotateY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) * (_t0) + (_selfz * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_t0) - (_selfx * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateZ_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    /** {@code rotateZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) * (_t0) - (_selfy * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfx) * (_t1) + (_selfy * _t0)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
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
