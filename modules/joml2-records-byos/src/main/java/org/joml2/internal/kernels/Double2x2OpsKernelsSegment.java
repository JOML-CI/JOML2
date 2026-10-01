// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2x2Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double2x2Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double2x2OpsKernelsSegment {
    private Double2x2OpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment getColumn_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getColumn_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        double _idxSw0;
        double _idxSw1;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        switch (col) {
            case 0: _idxSw0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset); _idxSw1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L); break;
            case 1: _idxSw0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L); _idxSw1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _idxSw0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _idxSw1);
        return dest;
    }

    public static double getRotationAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double2x2OpsKernelsAddress.getRotationAngle_unsafe(_srcBase);
    }

    public static double getRotationAngle_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
    }

    public static java.lang.foreign.MemorySegment getRow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getRow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        double _idxSw0;
        double _idxSw1;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        switch (row) {
            case 0: _idxSw0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset); _idxSw1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L); break;
            case 1: _idxSw0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L); _idxSw1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _idxSw0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _idxSw1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cofactor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cofactor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -_self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self00);
        return dest;
    }

    public static double determinant_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double2x2OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static double determinant_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t1 = -(_self01 * _self10);
            return java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        } else {
            double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t1 = -(_self01 * _self10);
            return ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)) + (_t1)) - ((_self01) * (_self10) + (_t1));
        }
    }

    public static double frobeniusNorm_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double2x2OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            return java.lang.Math.sqrt(java.lang.Math.fma(_self11, _self11, java.lang.Math.fma(_self10, _self10, java.lang.Math.fma(_self00, _self00, _self01 * _self01))));
        } else {
            double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            return java.lang.Math.sqrt(((_self11) * (_self11) + (((_self10) * (_self10) + (((_self00) * (_self00) + (_self01 * _self01)))))));
        }
    }

    public static java.lang.foreign.MemorySegment invert_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t1 = -(_self01 * _self10);
        double _t5 = java.lang.Math.fma(_self00, _self11, _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x2OpsKernelsSegment.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Double2x2OpsKernelsSegment.invert_degenerate_api(dest, destOffset, src, srcOffset);
        }
        double _t5_inv = 1.0 / _t5;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _self11 * _t5_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_self10 * _t5_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_self01 * _t5_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self00 * _t5_inv);
        return dest;
    }

    /** {@code invert_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t1 = -(_self01 * _self10);
        double _t5 = ((_self00) * (_self11) + (_t1)) - ((_self01) * (_self10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x2OpsKernelsSegment.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Double2x2OpsKernelsSegment.invert_degenerate_api(dest, destOffset, src, srcOffset);
        }
        double _t5_inv = 1.0 / _t5;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _self11 * _t5_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_self10 * _t5_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_self01 * _t5_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self00 * _t5_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x2OpsKernelsSegment.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsSegment.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment invert_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t6 * _sp0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_t9 * _sp0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_t8 * _sp1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t7 * _sp1);
        return dest;
    }

    /** {@code invert_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t6 * _sp0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_t9 * _sp0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_t8 * _sp1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t7 * _sp1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x2OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t2 = -(_other01 * _other10);
        double _t3 = -(_self01 * _self10);
        double _t10 = java.lang.Math.fma(_other00, _other11, _t2) - java.lang.Math.fma(_other01, _other10, _t2);
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return Double2x2OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t11 = java.lang.Math.fma(_self00, _self11, _t3) - java.lang.Math.fma(_self01, _self10, _t3);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return Double2x2OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_fma_sf85da39d_1(dest, destOffset, _self10, _self01, _other10, _other01, 1.0 / _t10, 1.0 / _t11, _other11 / _t10, _self11 / _t11, _self00 / _t11, _other00 / _t10);
    }

    /** Piece 2 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma_sf85da39d_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self10, double _self01, double _other10, double _other01, double _t10_inv, double _t11_inv, double _t22, double _t23, double _t24, double _t27) {
        double _t28 = -(_other01 * _t10_inv);
        double _t29 = -(_self10 * _t11_inv);
        double _t30 = -(_self01 * _t11_inv);
        double _t31 = -(_other10 * _t10_inv);
        double _t32 = _t22 * _t23;
        double _t33 = _t27 * _t24;
        double _t34 = _t30 * _t22;
        double _t35 = _t29 * _t27;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33));
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t2 = -(_other01 * _other10);
        double _t3 = -(_self01 * _self10);
        double _t10 = ((_other00) * (_other11) + (_t2)) - ((_other01) * (_other10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return Double2x2OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t11 = ((_self00) * (_self11) + (_t3)) - ((_self01) * (_self10) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return Double2x2OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd_s89cb024c_1(dest, destOffset, _self10, _self01, _other10, _other01, 1.0 / _t10, 1.0 / _t11, _other11 / _t10, _self11 / _t11, _self00 / _t11, _other00 / _t10);
    }

    /** Piece 2 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd_s89cb024c_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self10, double _self01, double _other10, double _other01, double _t10_inv, double _t11_inv, double _t22, double _t23, double _t24, double _t27) {
        double _t28 = -(_other01 * _t10_inv);
        double _t29 = -(_self10 * _t11_inv);
        double _t30 = -(_self01 * _t11_inv);
        double _t31 = -(_other10 * _t10_inv);
        double _t32 = _t22 * _t23;
        double _t33 = _t27 * _t24;
        double _t34 = _t30 * _t22;
        double _t35 = _t29 * _t27;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double2x2OpsKernelsSegment.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x2OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t12 = _other01 * _t0;
        double _t13 = _other00 * _t0;
        double _t14 = _other11 * _t1;
        double _t15 = _other10 * _t1;
        double _t16 = _self10 * _t2;
        double _t19 = _self01 * _t3;
        double _t30 = -(_t12 * _t15);
        double _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        return invertProduct_degenerate_api_fma_s41aecf50_1(dest, destOffset, _t0, _t1, _t2, _t3, _t12, _t13, _t14, _t15, _t16, _self00 * _t3, _self11 * _t2, _t19, -(_t19 * _t16), _t36, 1.0 / _t36);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_fma_s41aecf50_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _t1, double _t2, double _t3, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t31, double _t36, double _t36_inv) {
        double _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        double _t37_inv = 1.0 / _t37;
        double _t48 = _t14 * _t0 / _t36;
        double _t49 = _t18 * _t3 / _t37;
        double _t50 = _t17 * _t2 / _t37;
        double _t53 = _t13 * _t1 / _t36;
        double _t54 = -(_t12 * _t1 * _t36_inv);
        double _t55 = -(_t16 * _t3 * _t37_inv);
        double _t56 = -(_t19 * _t2 * _t37_inv);
        double _t57 = -(_t15 * _t0 * _t36_inv);
        double _t58 = _t48 * _t49;
        double _t59 = _t53 * _t50;
        double _t60 = _t56 * _t48;
        double _t61 = _t55 * _t53;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59));
        return dest;
    }

    /** {@code invertProduct_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t12 = _other01 * _t0;
        double _t13 = _other00 * _t0;
        double _t14 = _other11 * _t1;
        double _t15 = _other10 * _t1;
        double _t16 = _self10 * _t2;
        double _t19 = _self01 * _t3;
        double _t30 = -(_t12 * _t15);
        double _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        return invertProduct_degenerate_api_mulAdd_s5cf93ceb_1(dest, destOffset, _t0, _t1, _t2, _t3, _t12, _t13, _t14, _t15, _t16, _self00 * _t3, _self11 * _t2, _t19, -(_t19 * _t16), _t36, 1.0 / _t36);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd_s5cf93ceb_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _t1, double _t2, double _t3, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t31, double _t36, double _t36_inv) {
        double _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        double _t37_inv = 1.0 / _t37;
        double _t48 = _t14 * _t0 / _t36;
        double _t49 = _t18 * _t3 / _t37;
        double _t50 = _t17 * _t2 / _t37;
        double _t53 = _t13 * _t1 / _t36;
        double _t54 = -(_t12 * _t1 * _t36_inv);
        double _t55 = -(_t16 * _t3 * _t37_inv);
        double _t56 = -(_t19 * _t2 * _t37_inv);
        double _t57 = -(_t15 * _t0 * _t36_inv);
        double _t58 = _t48 * _t49;
        double _t59 = _t53 * _t50;
        double _t60 = _t56 * _t48;
        double _t61 = _t55 * _t53;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_t54) * (_t55) + (_t58)) + ((_t48) * (_t49) - (_t58)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t57) * (_t49) + (_t61)) + ((_t55) * (_t53) - (_t61)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t54) * (_t50) + (_t60)) + ((_t56) * (_t48) - (_t60)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t57) * (_t56) + (_t59)) + ((_t53) * (_t50) - (_t59)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t1 = -(_self01 * _self10);
        double _t5 = java.lang.Math.fma(_self00, _self11, _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x2OpsKernelsSegment.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Double2x2OpsKernelsSegment.normal_degenerate_api(dest, destOffset, src, srcOffset);
        }
        double _t5_inv = 1.0 / _t5;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _self11 * _t5_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_self01 * _t5_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_self10 * _t5_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self00 * _t5_inv);
        return dest;
    }

    /** {@code normal_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normal_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t1 = -(_self01 * _self10);
        double _t5 = ((_self00) * (_self11) + (_t1)) - ((_self01) * (_self10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x2OpsKernelsSegment.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Double2x2OpsKernelsSegment.normal_degenerate_api(dest, destOffset, src, srcOffset);
        }
        double _t5_inv = 1.0 / _t5;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _self11 * _t5_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_self01 * _t5_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_self10 * _t5_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self00 * _t5_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x2OpsKernelsSegment.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsSegment.normal_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment normal_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t6 * _sp0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_t8 * _sp1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_t9 * _sp0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t7 * _sp1);
        return dest;
    }

    /** {@code normal_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normal_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t6 * _sp0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_t8 * _sp1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_t9 * _sp0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t7 * _sp1);
        return dest;
    }

    public static double trace_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double2x2OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static double trace_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
    }

    public static java.lang.foreign.MemorySegment transpose_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transpose_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) + src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _other10 + _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _other01 + _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _other11 + _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, scalar * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, scalar * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, scalar * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, scalar * _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, -src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -_self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, -_self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10 - _other10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01 - _other01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11 - _other11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Double2x2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _v10 = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _v01 = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        double _v11 = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, v.get(_JAVA_DOUBLE_UNALIGNED, vOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _v10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _v01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _v11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Double2x2OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _m10 = m.get(_JAVA_DOUBLE_UNALIGNED, mOffset + 8L);
        double _m01 = m.get(_JAVA_DOUBLE_UNALIGNED, mOffset + 16L);
        double _m11 = m.get(_JAVA_DOUBLE_UNALIGNED, mOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, m.get(_JAVA_DOUBLE_UNALIGNED, mOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _m11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Double2x2OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _m10 = m.get(_JAVA_DOUBLE_UNALIGNED, mOffset + 8L);
        double _m01 = m.get(_JAVA_DOUBLE_UNALIGNED, mOffset + 24L);
        double _m11 = m.get(_JAVA_DOUBLE_UNALIGNED, mOffset + 32L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, m.get(_JAVA_DOUBLE_UNALIGNED, mOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _m11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeLDU_unsafe(java.lang.foreign.MemorySegment lower, long lowerOffset, java.lang.foreign.MemorySegment diagonal, long diagonalOffset, java.lang.foreign.MemorySegment upper, long upperOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _lowerBase = lower.address() + lowerOffset;
        long _diagonalBase = diagonal.address() + diagonalOffset;
        long _upperBase = upper.address() + upperOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.decomposeLDU_unsafe(_lowerBase, _diagonalBase, _upperBase, _srcBase);
        return lower;
    }

    public static java.lang.foreign.MemorySegment decomposeLDU_api(java.lang.foreign.MemorySegment lower, long lowerOffset, java.lang.foreign.MemorySegment diagonal, long diagonalOffset, java.lang.foreign.MemorySegment upper, long upperOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _rcp0 = 1.0 / _self00;
        double _sp0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) * _rcp0;
        lower.set(_JAVA_DOUBLE_UNALIGNED, lowerOffset, 1.0);
        lower.set(_JAVA_DOUBLE_UNALIGNED, lowerOffset + 8L, _sp0);
        lower.set(_JAVA_DOUBLE_UNALIGNED, lowerOffset + 16L, 0.0);
        lower.set(_JAVA_DOUBLE_UNALIGNED, lowerOffset + 24L, 1.0);
        diagonal.set(_JAVA_DOUBLE_UNALIGNED, diagonalOffset, _self00);
        diagonal.set(_JAVA_DOUBLE_UNALIGNED, diagonalOffset + 8L, 0.0);
        diagonal.set(_JAVA_DOUBLE_UNALIGNED, diagonalOffset + 16L, 0.0);
        diagonal.set(_JAVA_DOUBLE_UNALIGNED, diagonalOffset + 24L, _self11 - _self01 * _sp0);
        upper.set(_JAVA_DOUBLE_UNALIGNED, upperOffset, 1.0);
        upper.set(_JAVA_DOUBLE_UNALIGNED, upperOffset + 8L, 0.0);
        upper.set(_JAVA_DOUBLE_UNALIGNED, upperOffset + 16L, _self01 * _rcp0);
        upper.set(_JAVA_DOUBLE_UNALIGNED, upperOffset + 24L, 1.0);
        return lower;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        Double2x2OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) - _self00, _self00));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _other10 - _self10, _self10));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, _other01 - _self01, _self01));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(t, _other11 - _self11, _self11));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) - _self00) + (_self00)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (_other10 - _self10) + (_self10)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (_other01 - _self01) + (_self01)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((t) * (_other11 - _self11) + (_self11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Double2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _right00 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset);
        double _right10 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 8L);
        double _right01 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 16L);
        double _right11 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _right00 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset);
        double _right10 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 8L);
        double _right01 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 16L);
        double _right11 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_right01) * (_self10) + (_right11 * _self11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_other00) * (_self01) + (_other01 * _self11)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_other10) * (_self01) + (_other11 * _self11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(weight, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(weight, _other10, _self10));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(weight, _other01, _self01));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(weight, _other11, _self11));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((weight) * (other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset)) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((weight) * (_other10) + (_self10)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((weight) * (_other01) + (_self01)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((weight) * (_other11) + (_self11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double colX, double colY, double rowX, double rowY) {
        long _destBase = dest.address() + destOffset;
        Double2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, rowX, rowY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, double colX, double colY, double rowX, double rowY) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, colX * rowX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, colY * rowX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, colX * rowY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, colY * rowY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment col, long colOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        long _destBase = dest.address() + destOffset;
        long _colBase = col.address() + colOffset;
        long _rowBase = row.address() + rowOffset;
        Double2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment col, long colOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _colx = col.get(_JAVA_DOUBLE_UNALIGNED, colOffset);
        double _coly = col.get(_JAVA_DOUBLE_UNALIGNED, colOffset + 8L);
        double _rowx = row.get(_JAVA_DOUBLE_UNALIGNED, rowOffset);
        double _rowy = row.get(_JAVA_DOUBLE_UNALIGNED, rowOffset + 8L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _colx * _rowx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _coly * _rowx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _colx * _rowy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _coly * _rowy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        Double2x2OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, v.get(_JAVA_DOUBLE_UNALIGNED, vOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _vy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        long _destBase = dest.address() + destOffset;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        return dest;
    }

    /** {@code preRotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_self01) * (_t1) + (_self11 * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * vX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10 * vY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01 * vX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11 * vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _vx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10 * _vy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01 * _vx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11 * _vy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, s * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_self11) * (_t0) - (_self10 * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * vX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10 * vX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01 * vY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11 * vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _vx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10 * _vx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01 * _vy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11 * _vy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), vX, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * vY));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (vX) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * vY)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment mulVec2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
            double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
            double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _vx, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * _vy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
            double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
            double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_vx) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * _vy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self10) * (_vx) + (_self11 * _vy)));
            return dest;
        }
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
}
