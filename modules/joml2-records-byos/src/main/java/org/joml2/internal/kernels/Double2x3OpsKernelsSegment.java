// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2x3Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double2x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double2x3OpsKernelsSegment {
    private Double2x3OpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment getColumn_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getColumn_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        double _idxSw0;
        double _idxSw1;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        switch (col) {
            case 0: _idxSw0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset); _idxSw1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L); break;
            case 1: _idxSw0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L); _idxSw1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L); break;
            case 2: _idxSw0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L); _idxSw1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _idxSw0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _idxSw1);
        return dest;
    }

    public static double getRotationAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double2x3OpsKernelsAddress.getRotationAngle_unsafe(_srcBase);
    }

    public static double getRotationAngle_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
    }

    public static java.lang.foreign.MemorySegment getRow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getRow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        switch (row) {
            case 0: _idxSw0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset); _idxSw1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L); _idxSw2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L); break;
            case 1: _idxSw0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L); _idxSw1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L); _idxSw2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _idxSw0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _idxSw1);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _idxSw2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.getTranslation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self12);
        return dest;
    }

    public static double determinant_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double2x3OpsKernelsAddress.determinant_unsafe(_srcBase);
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
        return Double2x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
            double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
            return java.lang.Math.sqrt(java.lang.Math.fma(_self12, _self12, java.lang.Math.fma(_self11, _self11, java.lang.Math.fma(_self10, _self10, java.lang.Math.fma(_self02, _self02, java.lang.Math.fma(_self00, _self00, _self01 * _self01))))));
        } else {
            double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
            double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
            double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
            return java.lang.Math.sqrt(((_self12) * (_self12) + (((_self11) * (_self11) + (((_self10) * (_self10) + (((_self02) * (_self02) + (((_self00) * (_self00) + (_self01 * _self01)))))))))));
        }
    }

    public static java.lang.foreign.MemorySegment invert_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_api_fma(dest, destOffset, src, srcOffset);
        return invert_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code invert_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t3 = -(_self01 * _self10);
        double _t9 = java.lang.Math.fma(_self00, _self11, _t3) - java.lang.Math.fma(_self01, _self10, _t3);
        if (!(java.lang.Math.abs(_t9) > 2.2250738585072014E-308 && java.lang.Math.abs(_t9) < 4.49423283715579E307)) return Double2x3OpsKernelsSegment.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t9_inv = 1.0 / _t9;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _self11 * _t9_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_self10 * _t9_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_self01 * _t9_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self00 * _t9_inv);
        return invert_api_fma_s1fc3f9d_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, -(_self01 * _self12), -(_self02 * _self10), _t9_inv);
    }

    /** Piece 2 of {@code invert_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_fma_s1fc3f9d_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _t4, double _t5, double _t9_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, (java.lang.Math.fma(_self01, _self12, _t4) - java.lang.Math.fma(_self02, _self11, _t4)) * _t9_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, (java.lang.Math.fma(_self02, _self10, _t5) - java.lang.Math.fma(_self00, _self12, _t5)) * _t9_inv);
        return dest;
    }

    /** {@code invert_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t3 = -(_self01 * _self10);
        double _t9 = ((_self00) * (_self11) + (_t3)) - ((_self01) * (_self10) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 2.2250738585072014E-308 && java.lang.Math.abs(_t9) < 4.49423283715579E307)) return Double2x3OpsKernelsSegment.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t9_inv = 1.0 / _t9;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _self11 * _t9_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_self10 * _t9_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_self01 * _t9_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self00 * _t9_inv);
        return invert_api_mulAdd_sc0f983d4_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, -(_self01 * _self12), -(_self02 * _self10), _t9_inv);
    }

    /** Piece 2 of {@code invert_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_mulAdd_sc0f983d4_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _t4, double _t5, double _t9_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, (((_self01) * (_self12) + (_t4)) - ((_self02) * (_self11) + (_t4))) * _t9_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, (((_self02) * (_self10) + (_t5)) - ((_self00) * (_self12) + (_t5))) * _t9_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment invert_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code invert_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t8 = _self11 * _t0;
        double _t9 = _self00 * _t1;
        double _t10 = _self01 * _t1;
        double _t11 = _self10 * _t0;
        double _t12 = _self02 * _t1;
        double _t13 = _self12 * _t0;
        double _t17 = -(_t10 * _t11);
        double _t22_inv = 1.0 / (java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17));
        double _sp1 = _t0 * _t22_inv;
        double _sp0 = _t1 * _t22_inv;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t8 * _sp0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_t11 * _sp0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_t10 * _sp1));
        return invert_degenerate_api_fma_sd3149408_1(dest, destOffset, _t8, _t9, _t10, _t11, _t12, _t13, -(_t10 * _t13), -(_t12 * _t11), _t22_inv, _sp1);
    }

    /** Piece 2 of {@code invert_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_fma_sd3149408_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t18, double _t19, double _t22_inv, double _sp1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t9 * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, (java.lang.Math.fma(_t10, _t13, _t18) - java.lang.Math.fma(_t12, _t8, _t18)) * _t22_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, (java.lang.Math.fma(_t12, _t11, _t19) - java.lang.Math.fma(_t9, _t13, _t19)) * _t22_inv);
        return dest;
    }

    /** {@code invert_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t8 = _self11 * _t0;
        double _t9 = _self00 * _t1;
        double _t10 = _self01 * _t1;
        double _t11 = _self10 * _t0;
        double _t12 = _self02 * _t1;
        double _t13 = _self12 * _t0;
        double _t17 = -(_t10 * _t11);
        double _t22_inv = 1.0 / (((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17)));
        double _sp1 = _t0 * _t22_inv;
        double _sp0 = _t1 * _t22_inv;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t8 * _sp0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -(_t11 * _sp0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_t10 * _sp1));
        return invert_degenerate_api_mulAdd_s99cd5c4f_1(dest, destOffset, _t8, _t9, _t10, _t11, _t12, _t13, -(_t10 * _t13), -(_t12 * _t11), _t22_inv, _sp1);
    }

    /** Piece 2 of {@code invert_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_mulAdd_s99cd5c4f_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t18, double _t19, double _t22_inv, double _sp1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t9 * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, (((_t10) * (_t13) + (_t18)) - ((_t12) * (_t8) + (_t18))) * _t22_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, (((_t12) * (_t11) + (_t19)) - ((_t9) * (_t13) + (_t19))) * _t22_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
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
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other02 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other12 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        double _t6 = -(_other01 * _other10);
        return invertProduct_api_fma_sf85da39d_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12, -(_self01 * _self10), -(_self02 * _self10), -(_self01 * _self12), -(_other01 * _other12), -(_other02 * _other10), java.lang.Math.fma(_other00, _other11, _t6) - java.lang.Math.fma(_other01, _other10, _t6));
    }

    /** Piece 2 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma_sf85da39d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t7, double _t8, double _t9, double _t10, double _t11, double _t24) {
        if (!(java.lang.Math.abs(_t24) > 2.2250738585072014E-308 && java.lang.Math.abs(_t24) < 4.49423283715579E307)) return Double2x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t24_inv = 1.0 / _t24;
        double _t25 = java.lang.Math.fma(_self00, _self11, _t7) - java.lang.Math.fma(_self01, _self10, _t7);
        if (!(java.lang.Math.abs(_t25) > 2.2250738585072014E-308 && java.lang.Math.abs(_t25) < 4.49423283715579E307)) return Double2x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t25_inv = 1.0 / _t25;
        double _t36 = _other11 / _t24;
        double _t37 = _self11 / _t25;
        double _t38 = _self00 / _t25;
        double _t41 = _other00 / _t24;
        double _t42 = -(_other01 * _t24_inv);
        double _t43 = -(_self10 * _t25_inv);
        double _t44 = -(_self01 * _t25_inv);
        double _t52 = _t36 * _t37;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t42, _t43, _t52) + java.lang.Math.fma(_t36, _t37, -_t52));
        return invertProduct_api_fma_sf85da39d_2(dest, destOffset, _other00, _other10, _other01, _other11, _other02, _other12, _t10, _t11, _t24_inv, _t36, _t37, _t38, _t41, _t42, _t43, _t44, -(_other10 * _t24_inv), -((java.lang.Math.fma(_self00, _self12, _t8) - java.lang.Math.fma(_self02, _self10, _t8)) * _t25_inv), -((java.lang.Math.fma(_self02, _self11, _t9) - java.lang.Math.fma(_self01, _self12, _t9)) * _t25_inv), _t41 * _t38, _t44 * _t36, _t43 * _t41);
    }

    /** Piece 3 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma_sf85da39d_2(java.lang.foreign.MemorySegment dest, long destOffset, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t10, double _t11, double _t24_inv, double _t36, double _t37, double _t38, double _t41, double _t42, double _t43, double _t44, double _t45, double _t50, double _t51, double _t53, double _t54, double _t55) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t45, _t37, _t55) + java.lang.Math.fma(_t43, _t41, -_t55));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t42, _t38, _t54) + java.lang.Math.fma(_t44, _t36, -_t54));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t45, _t44, _t53) + java.lang.Math.fma(_t41, _t38, -_t53));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t42, _t50, java.lang.Math.fma(_t51, _t36, -((java.lang.Math.fma(_other02, _other11, _t10) - java.lang.Math.fma(_other01, _other12, _t10)) * _t24_inv))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_t45, _t51, java.lang.Math.fma(_t50, _t41, -((java.lang.Math.fma(_other00, _other12, _t11) - java.lang.Math.fma(_other02, _other10, _t11)) * _t24_inv))));
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other02 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other12 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        double _t6 = -(_other01 * _other10);
        return invertProduct_api_mulAdd_s89cb024c_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12, -(_self01 * _self10), -(_self02 * _self10), -(_self01 * _self12), -(_other01 * _other12), -(_other02 * _other10), ((_other00) * (_other11) + (_t6)) - ((_other01) * (_other10) + (_t6)));
    }

    /** Piece 2 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd_s89cb024c_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t7, double _t8, double _t9, double _t10, double _t11, double _t24) {
        if (!(java.lang.Math.abs(_t24) > 2.2250738585072014E-308 && java.lang.Math.abs(_t24) < 4.49423283715579E307)) return Double2x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t24_inv = 1.0 / _t24;
        double _t25 = ((_self00) * (_self11) + (_t7)) - ((_self01) * (_self10) + (_t7));
        if (!(java.lang.Math.abs(_t25) > 2.2250738585072014E-308 && java.lang.Math.abs(_t25) < 4.49423283715579E307)) return Double2x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t25_inv = 1.0 / _t25;
        double _t36 = _other11 / _t24;
        double _t37 = _self11 / _t25;
        double _t38 = _self00 / _t25;
        double _t41 = _other00 / _t24;
        double _t42 = -(_other01 * _t24_inv);
        double _t43 = -(_self10 * _t25_inv);
        double _t44 = -(_self01 * _t25_inv);
        double _t52 = _t36 * _t37;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_t42) * (_t43) + (_t52)) + ((_t36) * (_t37) - (_t52)));
        return invertProduct_api_mulAdd_s89cb024c_2(dest, destOffset, _other00, _other10, _other01, _other11, _other02, _other12, _t10, _t11, _t24_inv, _t36, _t37, _t38, _t41, _t42, _t43, _t44, -(_other10 * _t24_inv), -((((_self00) * (_self12) + (_t8)) - ((_self02) * (_self10) + (_t8))) * _t25_inv), -((((_self02) * (_self11) + (_t9)) - ((_self01) * (_self12) + (_t9))) * _t25_inv), _t41 * _t38, _t44 * _t36, _t43 * _t41);
    }

    /** Piece 3 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd_s89cb024c_2(java.lang.foreign.MemorySegment dest, long destOffset, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t10, double _t11, double _t24_inv, double _t36, double _t37, double _t38, double _t41, double _t42, double _t43, double _t44, double _t45, double _t50, double _t51, double _t53, double _t54, double _t55) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t45) * (_t37) + (_t55)) + ((_t43) * (_t41) - (_t55)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t42) * (_t38) + (_t54)) + ((_t44) * (_t36) - (_t54)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t45) * (_t44) + (_t53)) + ((_t41) * (_t38) - (_t53)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_t42) * (_t50) + (((_t51) * (_t36) - ((((_other02) * (_other11) + (_t10)) - ((_other01) * (_other12) + (_t10))) * _t24_inv)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_t45) * (_t51) + (((_t50) * (_t41) - ((((_other00) * (_other12) + (_t11)) - ((_other02) * (_other10) + (_t11))) * _t24_inv)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double2x3OpsKernelsSegment.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
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
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other02 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other12 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        return invertProduct_degenerate_api_fma_s41aecf50_1(dest, destOffset, _self02, _self12, _other02, _other12, _t0, _t1, _t2, _t3, _other01 * _t0, _other00 * _t0, _other11 * _t1, _other10 * _t1, _self10 * _t2, _self00 * _t3, _self11 * _t2, _self01 * _t3);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_fma_s41aecf50_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self02, double _self12, double _other02, double _other12, double _t0, double _t1, double _t2, double _t3, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = _self12 * _t2;
        double _t25 = _self02 * _t3;
        double _t26 = _other02 * _t0;
        double _t27 = _other12 * _t1;
        double _t42 = -(_t16 * _t19);
        double _t43 = -(_t23 * _t20);
        double _t44 = -(_t25 * _t20);
        double _t45 = -(_t23 * _t24);
        double _t56 = java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42);
        double _t56_inv = 1.0 / _t56;
        double _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        double _t57_inv = 1.0 / _t57;
        double _t70 = _t18 * _t0 / _t56;
        double _t71 = _t22 * _t3 / _t57;
        double _t72 = _t21 * _t2 / _t57;
        double _t75 = _t17 * _t1 / _t56;
        double _t77 = -(_t20 * _t3 * _t57_inv);
        double _t78 = -(_t23 * _t2 * _t57_inv);
        return invertProduct_degenerate_api_fma_s41aecf50_2(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, -(_t16 * _t27), -(_t26 * _t19), _t56_inv, _t70, _t71, _t72, _t75, -(_t16 * _t1 * _t56_inv), _t77, _t78, -(_t19 * _t0 * _t56_inv), -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv), -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv), _t70 * _t71, _t75 * _t72, _t78 * _t70, _t77 * _t75);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_fma_s41aecf50_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t46, double _t47, double _t56_inv, double _t70, double _t71, double _t72, double _t75, double _t76, double _t77, double _t78, double _t79, double _t84, double _t85, double _t86, double _t87, double _t88, double _t89) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv))));
        return dest;
    }

    /** {@code invertProduct_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other02 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other12 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        return invertProduct_degenerate_api_mulAdd_s5cf93ceb_1(dest, destOffset, _self02, _self12, _other02, _other12, _t0, _t1, _t2, _t3, _other01 * _t0, _other00 * _t0, _other11 * _t1, _other10 * _t1, _self10 * _t2, _self00 * _t3, _self11 * _t2, _self01 * _t3);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd_s5cf93ceb_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self02, double _self12, double _other02, double _other12, double _t0, double _t1, double _t2, double _t3, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = _self12 * _t2;
        double _t25 = _self02 * _t3;
        double _t26 = _other02 * _t0;
        double _t27 = _other12 * _t1;
        double _t42 = -(_t16 * _t19);
        double _t43 = -(_t23 * _t20);
        double _t44 = -(_t25 * _t20);
        double _t45 = -(_t23 * _t24);
        double _t56 = ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42));
        double _t56_inv = 1.0 / _t56;
        double _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        double _t57_inv = 1.0 / _t57;
        double _t70 = _t18 * _t0 / _t56;
        double _t71 = _t22 * _t3 / _t57;
        double _t72 = _t21 * _t2 / _t57;
        double _t75 = _t17 * _t1 / _t56;
        double _t77 = -(_t20 * _t3 * _t57_inv);
        double _t78 = -(_t23 * _t2 * _t57_inv);
        return invertProduct_degenerate_api_mulAdd_s5cf93ceb_2(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, -(_t16 * _t27), -(_t26 * _t19), _t56_inv, _t70, _t71, _t72, _t75, -(_t16 * _t1 * _t56_inv), _t77, _t78, -(_t19 * _t0 * _t56_inv), -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv), -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv), _t70 * _t71, _t75 * _t72, _t78 * _t70, _t77 * _t75);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd_s5cf93ceb_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t46, double _t47, double _t56_inv, double _t70, double _t71, double _t72, double _t75, double _t76, double _t77, double _t78, double _t79, double _t84, double _t85, double _t86, double _t87, double _t88, double _t89) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transpose_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transpose_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eother1 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + (_lo + 1) * 8L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + _lo * 8L) + src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, _eother1 + _eself1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, scalar * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, scalar * _eself1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, -_eself1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eother1 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + (_lo + 1) * 8L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + _lo * 8L));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, _eself1 - _eother1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Double2x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _ev1 = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + (_lo + 1) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + _lo * 8L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, _ev1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Double2x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _m10 = m.get(_JAVA_DOUBLE_UNALIGNED, mOffset + 8L);
        double _m01 = m.get(_JAVA_DOUBLE_UNALIGNED, mOffset + 16L);
        double _m11 = m.get(_JAVA_DOUBLE_UNALIGNED, mOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, m.get(_JAVA_DOUBLE_UNALIGNED, mOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _m10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _m01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _m11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Double2x3OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _lom = _l * 3;
            double _em1 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + (_lom + 1) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + _lom * 8L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, _em1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double tX, double tY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double tX, double tY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, tX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, tY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _tBase = t.address() + tOffset;
        Double2x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _tx = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset);
        double _ty = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset + 8L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _tx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _ty);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        Double2x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L);
            double _eself1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eother1 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + (_lo + 1) * 8L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, java.lang.Math.fma(t, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + _lo * 8L) - _eself0, _eself0));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, java.lang.Math.fma(t, _eother1 - _eself1, _eself1));
        }
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L);
            double _eself1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eother1 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + (_lo + 1) * 8L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, ((t) * (other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + _lo * 8L) - _eself0) + (_eself0)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, ((t) * (_eother1 - _eself1) + (_eself1)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Double2x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mul_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@code mul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _right00 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset);
        double _right10 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 8L);
        double _right01 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 16L);
        double _right11 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 24L);
        double _right02 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 32L);
        double _right12 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        return mul_api_fma_sc13e49cc_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mul_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_fma_sc13e49cc_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _right01, double _right11, double _right02, double _right12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_right02, _self00, java.lang.Math.fma(_right12, _self01, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_right02, _self10, java.lang.Math.fma(_right12, _self11, _self12)));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _right00 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset);
        double _right10 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 8L);
        double _right01 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 16L);
        double _right11 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 24L);
        double _right02 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 32L);
        double _right12 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_right01) * (_self00) + (_right11 * _self01)));
        return mul_api_mulAdd_s52eea597_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mul_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_mulAdd_s52eea597_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _right01, double _right11, double _right02, double _right12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_right02) * (_self00) + (((_right12) * (_self01) + (_self02)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_right02) * (_self10) + (((_right12) * (_self11) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Double2x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x2_api_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x2_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@code mulMat2x2_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x2_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _right00 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset);
        double _right10 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 8L);
        double _right01 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 16L);
        double _right11 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12);
        return dest;
    }

    /** {@code mulMat2x2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _right00 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset);
        double _right10 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 8L);
        double _right01 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 16L);
        double _right11 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + 24L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Double2x3OpsKernelsAddress.mulMat3x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat3x3_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + _lo * 8L);
            double _eright1 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + (_lo + 1) * 8L);
            double _eright2 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + (_lo + 2) * 8L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, java.lang.Math.fma(_eright2, _self02, java.lang.Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, java.lang.Math.fma(_eright2, _self12, java.lang.Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, _eright2);
        }
        return dest;
    }

    /** {@code mulMat3x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat3x3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + _lo * 8L);
            double _eright1 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + (_lo + 1) * 8L);
            double _eright2 = right.get(_JAVA_DOUBLE_UNALIGNED, rightOffset + (_lo + 2) * 8L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, ((_eright2) * (_self02) + (((_eright0) * (_self00) + (_eright1 * _self01)))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, ((_eright2) * (_self12) + (((_eright0) * (_self10) + (_eright1 * _self11)))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, _eright2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other02 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other12 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        return preMul_api_fma_s79ceae4d_1(dest, destOffset, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12);
    }

    /** Piece 2 of {@code preMul_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma_s79ceae4d_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_other00, _self02, java.lang.Math.fma(_other01, _self12, _other02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_other10, _self02, java.lang.Math.fma(_other11, _self12, _other12)));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other02 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other12 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_other00) * (_self01) + (_other01 * _self11)));
        return preMul_api_mulAdd_see9ba77c_1(dest, destOffset, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12);
    }

    /** Piece 2 of {@code preMul_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd_see9ba77c_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_other10) * (_self01) + (_other11 * _self11)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_other00) * (_self02) + (((_other01) * (_self12) + (_other02)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_other10) * (_self02) + (((_other11) * (_self12) + (_other12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x2_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L);
            double _eself1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1));
        }
        return dest;
    }

    /** {@code preMulMat2x2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat2x2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L);
            double _eself1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, ((_other00) * (_eself0) + (_other01 * _eself1)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, ((_other10) * (_eself0) + (_other11 * _eself1)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x3OpsKernelsAddress.preMulMat3x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat3x3_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMulMat3x3_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat3x3_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other20 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other21 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        double _other02 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 48L);
        double _other12 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 56L);
        double _other22 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 64L);
        return preMulMat3x3_api_fma_s4b0959cf_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat3x3_api_fma_s4b0959cf_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_other20, _self00, _other21 * _self10));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_other20, _self01, _other21 * _self11));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 48L, java.lang.Math.fma(_other00, _self02, java.lang.Math.fma(_other01, _self12, _other02)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 56L, java.lang.Math.fma(_other10, _self02, java.lang.Math.fma(_other11, _self12, _other12)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 64L, java.lang.Math.fma(_other20, _self02, java.lang.Math.fma(_other21, _self12, _other22)));
        return dest;
    }

    /** {@code preMulMat3x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat3x3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _other00 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other20 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other01 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other11 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other21 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        double _other02 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 48L);
        double _other12 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 56L);
        double _other22 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 64L);
        return preMulMat3x3_api_mulAdd_sbe6ffc8e_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat3x3_api_mulAdd_sbe6ffc8e_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_other20) * (_self00) + (_other21 * _self10)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_other00) * (_self01) + (_other01 * _self11)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_other10) * (_self01) + (_other11 * _self11)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_other20) * (_self01) + (_other21 * _self11)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 48L, ((_other00) * (_self02) + (((_other01) * (_self12) + (_other02)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 56L, ((_other10) * (_self02) + (((_other11) * (_self12) + (_other12)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 64L, ((_other20) * (_self02) + (((_other21) * (_self12) + (_other22)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double2x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eother1 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + (_lo + 1) * 8L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, java.lang.Math.fma(weight, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + _lo * 8L), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, java.lang.Math.fma(weight, _eother1, _eself1));
        }
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eother1 = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + (_lo + 1) * 8L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, ((weight) * (other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + _lo * 8L)) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, ((weight) * (_eother1) + (_eself1)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        Double2x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t1);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_t1);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        Double2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, vX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, vY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Double2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, v.get(_JAVA_DOUBLE_UNALIGNED, vOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _vy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        long _destBase = dest.address() + destOffset;
        Double2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, s);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        Double2x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, vX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Double2x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _vx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _vy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeView_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double left, double right, double bottom, double top) {
        long _destBase = dest.address() + destOffset;
        Double2x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeView_api(java.lang.foreign.MemorySegment dest, long destOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t0_inv + _t0_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t1_inv + _t1_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, -(left + right) * _t0_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, -(bottom + top) * _t1_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_self02, _t1, _self12 * _t0));
        return dest;
    }

    /** {@code preRotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_self01) * (_t1) + (_self11 * _t0)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_self02) * (_t0) - (_self12 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_self02) * (_t1) + (_self12 * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_fma(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@code preRotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        return preRotateAround_api_fma_s1a6ee7e1_1(dest, destOffset, pivotX, pivotY, _t0, _t1, _self02, _self12, _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0);
    }

    /** Piece 2 of {@code preRotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_fma_s1a6ee7e1_1(java.lang.foreign.MemorySegment dest, long destOffset, double pivotX, double pivotY, double _t0, double _t1, double _self02, double _self12, double _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_self02, _t1, _self12 * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1)));
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_self01) * (_t1) + (_self11 * _t0)));
        return preRotateAround_api_mulAdd_sf8fce8b6_1(dest, destOffset, pivotX, pivotY, _t0, _t1, _self02, _self12, _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0);
    }

    /** Piece 2 of {@code preRotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_mulAdd_sf8fce8b6_1(java.lang.foreign.MemorySegment dest, long destOffset, double pivotX, double pivotY, double _t0, double _t1, double _self02, double _self12, double _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((pivotX) * (_t6) + (pivotY * _t1)) + ((_self02) * (_t0) - (_self12 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_self02) * (_t1) + (_self12 * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double2x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@code preRotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        return preRotateAround_api_fma_s6dc57443_1(dest, destOffset, _t0, _t1, _pivotx, _pivoty, _self02, _self12, _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0);
    }

    /** Piece 2 of {@code preRotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_fma_s6dc57443_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _t1, double _pivotx, double _pivoty, double _self02, double _self12, double _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_pivotx, _t6, _pivoty * _t1) + java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_self02, _t1, _self12 * _t0) + java.lang.Math.fma(_pivoty, _t6, -(_pivotx * _t1)));
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_self01) * (_t1) + (_self11 * _t0)));
        return preRotateAround_api_mulAdd_s94a5bc62_1(dest, destOffset, _t0, _t1, _pivotx, _pivoty, _self02, _self12, _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0);
    }

    /** Piece 2 of {@code preRotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_mulAdd_s94a5bc62_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _t1, double _pivotx, double _pivoty, double _self02, double _self12, double _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_pivotx) * (_t6) + (_pivoty * _t1)) + ((_self02) * (_t0) - (_self12 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_self02) * (_t1) + (_self12 * _t0)) + ((_pivoty) * (_t6) - (_pivotx * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L) * vX);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, _eself1 * vY);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L) * _vx);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, _eself1 * _vy);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, s * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, s * _eself1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = 1.0 - s;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, s * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(s, _self02, pivotX * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(s, _self12, pivotY * _t0));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = 1.0 - s;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, s * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((s) * (_self02) + (pivotX * _t0)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((s) * (_self12) + (pivotY * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = 1.0 - s;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, s * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(s, _self02, _pivotx * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(s, _self12, _pivoty * _t0));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = 1.0 - s;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, s * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((s) * (_self02) + (_pivotx * _t0)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((s) * (_self12) + (_pivoty * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, sX * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, sY * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, sX * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, sY * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(pivotX, 1.0 - sX, sX * _self02));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(pivotY, 1.0 - sY, sY * _self12));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, sX * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, sY * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, sX * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, sY * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((pivotX) * (1.0 - sX) + (sX * _self02)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((pivotY) * (1.0 - sY) + (sY * _self12)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@code preScaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _sx = s.get(_JAVA_DOUBLE_UNALIGNED, sOffset);
        double _sy = s.get(_JAVA_DOUBLE_UNALIGNED, sOffset + 8L);
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _sx * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sy * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sx * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sy * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_pivotx, 1.0 - _sx, _sx * _self02));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_pivoty, 1.0 - _sy, _sy * _self12));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _sx = s.get(_JAVA_DOUBLE_UNALIGNED, sOffset);
        double _sy = s.get(_JAVA_DOUBLE_UNALIGNED, sOffset + 8L);
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _sx * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sy * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sx * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sy * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_pivotx) * (1.0 - _sx) + (_sx * _self02)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_pivoty) * (1.0 - _sy) + (_sy * _self12)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self02 + vX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12 + vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double2x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self02 + _vx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12 + _vy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_fma(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@code rotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        return rotateAround_api_fma_s28f8e8a0_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, java.lang.Math.fma(pivotX, _t9, pivotY * _t1), java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma_s28f8e8a0_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _t10, double _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_self11) * (_t0) - (_self10 * _t1)));
        return rotateAround_api_mulAdd_sc2809e21_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, ((pivotX) * (_t9) + (pivotY * _t1)), ((pivotY) * (_t9) - (pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd_sc2809e21_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _t10, double _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double2x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@code rotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        return rotateAround_api_fma_sfc495708_1(dest, destOffset, _t0, _t1, _self00, _self10, _self01, _self11, _self02, _self12, java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1), java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma_sfc495708_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _t1, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _t10, double _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ((_self11) * (_t0) - (_self10 * _t1)));
        return rotateAround_api_mulAdd_s4045ad03_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, ((_pivotx) * (_t9) + (_pivoty * _t1)), ((_pivoty) * (_t9) - (_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd_s4045ad03_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _t10, double _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * vX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10 * vX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01 * vY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11 * vY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _vx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10 * _vx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01 * _vy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11 * _vy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, s * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self02);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = 1.0 - s;
        double _t1 = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset) * _t0;
        double _t2 = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * _t0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0 = 1.0 - s;
        double _t1 = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset) * _t0;
        double _t2 = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * _t0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, sX * _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, sX * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, sY * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, sY * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, sX * _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, sX * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, sY * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, sY * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@code scaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _sx = s.get(_JAVA_DOUBLE_UNALIGNED, sOffset);
        double _sy = s.get(_JAVA_DOUBLE_UNALIGNED, sOffset + 8L);
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t2 = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * (1.0 - _sy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _sx * _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sx * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sy * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sy * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _sx = s.get(_JAVA_DOUBLE_UNALIGNED, sOffset);
        double _sy = s.get(_JAVA_DOUBLE_UNALIGNED, sOffset + 8L);
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t2 = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * (1.0 - _sy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _sx * _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sx * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sy * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sy * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment view_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double left, double right, double bottom, double top) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.lang.foreign.MemorySegment view_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double left, double right, double bottom, double top) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self00 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self02 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _sp0 * _self00);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp0 * _self10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp1 * _self01);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp1 * _self11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L), vZ, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), vX, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * vY)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self12, vZ, java.lang.Math.fma(_self10, vX, _self11 * vY)));
        return dest;
    }

    /** {@code mulVec3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulVec3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L)) * (vZ) + (((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (vX) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * vY)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self12) * (vZ) + (((_self10) * (vX) + (_self11 * vY)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double2x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L), _vz, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _vx, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * _vy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self12, _vz, java.lang.Math.fma(_self10, _vx, _self11 * _vy)));
        return dest;
    }

    /** {@code mulVec3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulVec3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L)) * (_vz) + (((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_vx) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * _vy)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self12) * (_vz) + (((_self10) * (_vx) + (_self11 * _vy)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
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

    public static java.lang.foreign.MemorySegment transformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
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

    public static java.lang.foreign.MemorySegment transformPosition_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformPosition_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), vX, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), vY, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
        return dest;
    }

    /** {@code transformPosition_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment transformPosition_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (vX) + (((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) * (vY) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformPosition_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _vx, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), _vy, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    /** {@code transformPosition_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment transformPosition_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vx = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _self10 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self12 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_vx) + (((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) * (_vy) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 32L))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
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
}
