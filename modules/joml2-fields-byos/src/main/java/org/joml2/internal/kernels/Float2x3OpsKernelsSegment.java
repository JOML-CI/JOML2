// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x3Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float2x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float2x3OpsKernelsSegment {
    private Float2x3OpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment getColumn_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getColumn_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        float _idxSw0;
        float _idxSw1;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        switch (col) {
            case 0: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L); break;
            case 1: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L); break;
            case 2: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _idxSw0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _idxSw1);
        return dest;
    }

    public static float getRotationAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2x3OpsKernelsAddress.getRotationAngle_unsafe(_srcBase);
    }

    public static float getRotationAngle_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
    }

    public static java.lang.foreign.MemorySegment getRow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getRow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        switch (row) {
            case 0: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L); _idxSw2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L); break;
            case 1: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L); _idxSw2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _idxSw0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _idxSw1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _idxSw2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.getTranslation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self12);
        return dest;
    }

    public static float determinant_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2x3OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static float determinant_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t1 = -(_self01 * _self10);
            return java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L), _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        } else {
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t1 = -(_self01 * _self10);
            return ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L)) + (_t1)) - ((_self01) * (_self10) + (_t1));
        }
    }

    public static float frobeniusNorm_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
            float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_self12, _self12, java.lang.Math.fma(_self11, _self11, java.lang.Math.fma(_self10, _self10, java.lang.Math.fma(_self02, _self02, java.lang.Math.fma(_self00, _self00, _self01 * _self01))))));
        } else {
            float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
            float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
            return (float) java.lang.Math.sqrt(((_self12) * (_self12) + (((_self11) * (_self11) + (((_self10) * (_self10) + (((_self02) * (_self02) + (((_self00) * (_self00) + (_self01 * _self01)))))))))));
        }
    }

    public static java.lang.foreign.MemorySegment invert_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_api_fma(dest, destOffset, src, srcOffset);
        return invert_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code invert_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t3 = -(_self01 * _self10);
        float _t9 = java.lang.Math.fma(_self00, _self11, _t3) - java.lang.Math.fma(_self01, _self10, _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return Float2x3OpsKernelsSegment.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t9_inv = 1.0f / _t9;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self11 * _t9_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_self10 * _t9_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_self01 * _t9_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self00 * _t9_inv);
        return invert_api_fma_s1fc3f9d_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, -(_self01 * _self12), -(_self02 * _self10), _t9_inv);
    }

    /** Piece 2 of {@code invert_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_fma_s1fc3f9d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _t4, float _t5, float _t9_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, (java.lang.Math.fma(_self01, _self12, _t4) - java.lang.Math.fma(_self02, _self11, _t4)) * _t9_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, (java.lang.Math.fma(_self02, _self10, _t5) - java.lang.Math.fma(_self00, _self12, _t5)) * _t9_inv);
        return dest;
    }

    /** {@code invert_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t3 = -(_self01 * _self10);
        float _t9 = ((_self00) * (_self11) + (_t3)) - ((_self01) * (_self10) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return Float2x3OpsKernelsSegment.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t9_inv = 1.0f / _t9;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self11 * _t9_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_self10 * _t9_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_self01 * _t9_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self00 * _t9_inv);
        return invert_api_mulAdd_sc0f983d4_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, -(_self01 * _self12), -(_self02 * _self10), _t9_inv);
    }

    /** Piece 2 of {@code invert_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_mulAdd_sc0f983d4_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _t4, float _t5, float _t9_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, (((_self01) * (_self12) + (_t4)) - ((_self02) * (_self11) + (_t4))) * _t9_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, (((_self02) * (_self10) + (_t5)) - ((_self00) * (_self12) + (_t5))) * _t9_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float2x3OpsKernelsSegment.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment invert_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code invert_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t8 = _self11 * _t0;
        float _t9 = _self00 * _t1;
        float _t10 = _self01 * _t1;
        float _t11 = _self10 * _t0;
        float _t12 = _self02 * _t1;
        float _t13 = _self12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t22_inv = 1.0f / (java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t8 * _sp0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t11 * _sp0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t10 * _sp1));
        return invert_degenerate_api_fma_sd3149408_1(dest, destOffset, _t8, _t9, _t10, _t11, _t12, _t13, -(_t10 * _t13), -(_t12 * _t11), _t22_inv, _sp1);
    }

    /** Piece 2 of {@code invert_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_fma_sd3149408_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t18, float _t19, float _t22_inv, float _sp1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t9 * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, (java.lang.Math.fma(_t10, _t13, _t18) - java.lang.Math.fma(_t12, _t8, _t18)) * _t22_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, (java.lang.Math.fma(_t12, _t11, _t19) - java.lang.Math.fma(_t9, _t13, _t19)) * _t22_inv);
        return dest;
    }

    /** {@code invert_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t8 = _self11 * _t0;
        float _t9 = _self00 * _t1;
        float _t10 = _self01 * _t1;
        float _t11 = _self10 * _t0;
        float _t12 = _self02 * _t1;
        float _t13 = _self12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t22_inv = 1.0f / (((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17)));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t8 * _sp0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t11 * _sp0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t10 * _sp1));
        return invert_degenerate_api_mulAdd_s99cd5c4f_1(dest, destOffset, _t8, _t9, _t10, _t11, _t12, _t13, -(_t10 * _t13), -(_t12 * _t11), _t22_inv, _sp1);
    }

    /** Piece 2 of {@code invert_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_mulAdd_s99cd5c4f_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t18, float _t19, float _t22_inv, float _sp1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t9 * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, (((_t10) * (_t13) + (_t18)) - ((_t12) * (_t8) + (_t18))) * _t22_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, (((_t12) * (_t11) + (_t19)) - ((_t9) * (_t13) + (_t19))) * _t22_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        float _t6 = -(_other01 * _other10);
        return invertProduct_api_fma_sf85da39d_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12, -(_self01 * _self10), -(_self02 * _self10), -(_self01 * _self12), -(_other01 * _other12), -(_other02 * _other10), java.lang.Math.fma(_other00, _other11, _t6) - java.lang.Math.fma(_other01, _other10, _t6));
    }

    /** Piece 2 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma_sf85da39d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _other00, float _other10, float _other01, float _other11, float _other02, float _other12, float _t7, float _t8, float _t9, float _t10, float _t11, float _t24) {
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return Float2x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t24_inv = 1.0f / _t24;
        float _t25 = java.lang.Math.fma(_self00, _self11, _t7) - java.lang.Math.fma(_self01, _self10, _t7);
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return Float2x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t25_inv = 1.0f / _t25;
        float _t36 = _other11 / _t24;
        float _t37 = _self11 / _t25;
        float _t38 = _self00 / _t25;
        float _t41 = _other00 / _t24;
        float _t42 = -(_other01 * _t24_inv);
        float _t43 = -(_self10 * _t25_inv);
        float _t44 = -(_self01 * _t25_inv);
        float _t52 = _t36 * _t37;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t42, _t43, _t52) + java.lang.Math.fma(_t36, _t37, -_t52));
        return invertProduct_api_fma_sf85da39d_2(dest, destOffset, _other00, _other10, _other01, _other11, _other02, _other12, _t10, _t11, _t24_inv, _t36, _t37, _t38, _t41, _t42, _t43, _t44, -(_other10 * _t24_inv), -((java.lang.Math.fma(_self00, _self12, _t8) - java.lang.Math.fma(_self02, _self10, _t8)) * _t25_inv), -((java.lang.Math.fma(_self02, _self11, _t9) - java.lang.Math.fma(_self01, _self12, _t9)) * _t25_inv), _t41 * _t38, _t44 * _t36, _t43 * _t41);
    }

    /** Piece 3 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma_sf85da39d_2(java.lang.foreign.MemorySegment dest, long destOffset, float _other00, float _other10, float _other01, float _other11, float _other02, float _other12, float _t10, float _t11, float _t24_inv, float _t36, float _t37, float _t38, float _t41, float _t42, float _t43, float _t44, float _t45, float _t50, float _t51, float _t53, float _t54, float _t55) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t45, _t37, _t55) + java.lang.Math.fma(_t43, _t41, -_t55));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t42, _t38, _t54) + java.lang.Math.fma(_t44, _t36, -_t54));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t45, _t44, _t53) + java.lang.Math.fma(_t41, _t38, -_t53));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t42, _t50, java.lang.Math.fma(_t51, _t36, -((java.lang.Math.fma(_other02, _other11, _t10) - java.lang.Math.fma(_other01, _other12, _t10)) * _t24_inv))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t45, _t51, java.lang.Math.fma(_t50, _t41, -((java.lang.Math.fma(_other00, _other12, _t11) - java.lang.Math.fma(_other02, _other10, _t11)) * _t24_inv))));
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        float _t6 = -(_other01 * _other10);
        return invertProduct_api_mulAdd_s89cb024c_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12, -(_self01 * _self10), -(_self02 * _self10), -(_self01 * _self12), -(_other01 * _other12), -(_other02 * _other10), ((_other00) * (_other11) + (_t6)) - ((_other01) * (_other10) + (_t6)));
    }

    /** Piece 2 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd_s89cb024c_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _other00, float _other10, float _other01, float _other11, float _other02, float _other12, float _t7, float _t8, float _t9, float _t10, float _t11, float _t24) {
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return Float2x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t24_inv = 1.0f / _t24;
        float _t25 = ((_self00) * (_self11) + (_t7)) - ((_self01) * (_self10) + (_t7));
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return Float2x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t25_inv = 1.0f / _t25;
        float _t36 = _other11 / _t24;
        float _t37 = _self11 / _t25;
        float _t38 = _self00 / _t25;
        float _t41 = _other00 / _t24;
        float _t42 = -(_other01 * _t24_inv);
        float _t43 = -(_self10 * _t25_inv);
        float _t44 = -(_self01 * _t25_inv);
        float _t52 = _t36 * _t37;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t42) * (_t43) + (_t52)) + ((_t36) * (_t37) - (_t52)));
        return invertProduct_api_mulAdd_s89cb024c_2(dest, destOffset, _other00, _other10, _other01, _other11, _other02, _other12, _t10, _t11, _t24_inv, _t36, _t37, _t38, _t41, _t42, _t43, _t44, -(_other10 * _t24_inv), -((((_self00) * (_self12) + (_t8)) - ((_self02) * (_self10) + (_t8))) * _t25_inv), -((((_self02) * (_self11) + (_t9)) - ((_self01) * (_self12) + (_t9))) * _t25_inv), _t41 * _t38, _t44 * _t36, _t43 * _t41);
    }

    /** Piece 3 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd_s89cb024c_2(java.lang.foreign.MemorySegment dest, long destOffset, float _other00, float _other10, float _other01, float _other11, float _other02, float _other12, float _t10, float _t11, float _t24_inv, float _t36, float _t37, float _t38, float _t41, float _t42, float _t43, float _t44, float _t45, float _t50, float _t51, float _t53, float _t54, float _t55) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t45) * (_t37) + (_t55)) + ((_t43) * (_t41) - (_t55)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t42) * (_t38) + (_t54)) + ((_t44) * (_t36) - (_t54)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t45) * (_t44) + (_t53)) + ((_t41) * (_t38) - (_t53)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t42) * (_t50) + (((_t51) * (_t36) - ((((_other02) * (_other11) + (_t10)) - ((_other01) * (_other12) + (_t10))) * _t24_inv)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t45) * (_t51) + (((_t50) * (_t41) - ((((_other00) * (_other12) + (_t11)) - ((_other02) * (_other10) + (_t11))) * _t24_inv)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float2x3OpsKernelsSegment.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        return invertProduct_degenerate_api_fma_s41aecf50_1(dest, destOffset, _self02, _self12, _other02, _other12, _t0, _t1, _t2, _t3, _other01 * _t0, _other00 * _t0, _other11 * _t1, _other10 * _t1, _self10 * _t2, _self00 * _t3, _self11 * _t2, _self01 * _t3);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_fma_s41aecf50_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self02, float _self12, float _other02, float _other12, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = _self12 * _t2;
        float _t25 = _self02 * _t3;
        float _t26 = _other02 * _t0;
        float _t27 = _other12 * _t1;
        float _t42 = -(_t16 * _t19);
        float _t43 = -(_t23 * _t20);
        float _t44 = -(_t25 * _t20);
        float _t45 = -(_t23 * _t24);
        float _t56 = java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42);
        float _t56_inv = 1.0f / _t56;
        float _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        float _t57_inv = 1.0f / _t57;
        float _t70 = _t18 * _t0 / _t56;
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        return invertProduct_degenerate_api_fma_s41aecf50_2(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, -(_t16 * _t27), -(_t26 * _t19), _t56_inv, _t70, _t71, _t72, _t75, -(_t16 * _t1 * _t56_inv), _t77, _t78, -(_t19 * _t0 * _t56_inv), -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv), -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv), _t70 * _t71, _t75 * _t72, _t78 * _t70, _t77 * _t75);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_fma_s41aecf50_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t46, float _t47, float _t56_inv, float _t70, float _t71, float _t72, float _t75, float _t76, float _t77, float _t78, float _t79, float _t84, float _t85, float _t86, float _t87, float _t88, float _t89) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv))));
        return dest;
    }

    /** {@code invertProduct_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        return invertProduct_degenerate_api_mulAdd_s5cf93ceb_1(dest, destOffset, _self02, _self12, _other02, _other12, _t0, _t1, _t2, _t3, _other01 * _t0, _other00 * _t0, _other11 * _t1, _other10 * _t1, _self10 * _t2, _self00 * _t3, _self11 * _t2, _self01 * _t3);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd_s5cf93ceb_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self02, float _self12, float _other02, float _other12, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = _self12 * _t2;
        float _t25 = _self02 * _t3;
        float _t26 = _other02 * _t0;
        float _t27 = _other12 * _t1;
        float _t42 = -(_t16 * _t19);
        float _t43 = -(_t23 * _t20);
        float _t44 = -(_t25 * _t20);
        float _t45 = -(_t23 * _t24);
        float _t56 = ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42));
        float _t56_inv = 1.0f / _t56;
        float _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        float _t57_inv = 1.0f / _t57;
        float _t70 = _t18 * _t0 / _t56;
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        return invertProduct_degenerate_api_mulAdd_s5cf93ceb_2(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, -(_t16 * _t27), -(_t26 * _t19), _t56_inv, _t70, _t71, _t72, _t75, -(_t16 * _t1 * _t56_inv), _t77, _t78, -(_t19 * _t0 * _t56_inv), -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv), -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv), _t70 * _t71, _t75 * _t72, _t78 * _t70, _t77 * _t75);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd_s5cf93ceb_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t46, float _t47, float _t56_inv, float _t70, float _t71, float _t72, float _t75, float _t76, float _t77, float _t78, float _t79, float _t84, float _t85, float _t86, float _t87, float _t88, float _t89) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transpose_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transpose_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eother1 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + (_lo + 1) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + _lo * 4L) + src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, _eother1 + _eself1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, scalar * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, scalar * _eself1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, -_eself1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eother1 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + (_lo + 1) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + _lo * 4L));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, _eself1 - _eother1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Float2x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _ev1 = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + (_lo + 1) * 4L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + _lo * 4L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, _ev1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Float2x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _m10 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m01 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 8L);
        float _m11 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, m.get(_JAVA_FLOAT_UNALIGNED, mOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Float2x3OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _lom = _l * 3;
            float _em1 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + (_lom + 1) * 4L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + _lom * 4L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, _em1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float tX, float tY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float tX, float tY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, tX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, tY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _tBase = t.address() + tOffset;
        Float2x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _tx = t.get(_JAVA_FLOAT_UNALIGNED, tOffset);
        float _ty = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _tx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _ty);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        Float2x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L);
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eother1 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + (_lo + 1) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, java.lang.Math.fma(t, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + _lo * 4L) - _eself0, _eself0));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, java.lang.Math.fma(t, _eother1 - _eself1, _eself1));
        }
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L);
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eother1 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + (_lo + 1) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, ((t) * (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + _lo * 4L) - _eself0) + (_eself0)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, ((t) * (_eother1 - _eself1) + (_eself1)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Float2x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mul_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@code mul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _right00 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset);
        float _right10 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 4L);
        float _right01 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 8L);
        float _right11 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 12L);
        float _right02 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 16L);
        float _right12 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        return mul_api_fma_sc13e49cc_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mul_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_fma_sc13e49cc_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _right01, float _right11, float _right02, float _right12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_right02, _self00, java.lang.Math.fma(_right12, _self01, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_right02, _self10, java.lang.Math.fma(_right12, _self11, _self12)));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _right00 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset);
        float _right10 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 4L);
        float _right01 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 8L);
        float _right11 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 12L);
        float _right02 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 16L);
        float _right12 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_right01) * (_self00) + (_right11 * _self01)));
        return mul_api_mulAdd_s52eea597_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mul_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_mulAdd_s52eea597_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _right01, float _right11, float _right02, float _right12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_right02) * (_self00) + (((_right12) * (_self01) + (_self02)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_right02) * (_self10) + (((_right12) * (_self11) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Float2x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x2_api_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x2_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@code mulMat2x2_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x2_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _right00 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset);
        float _right10 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 4L);
        float _right01 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 8L);
        float _right11 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12);
        return dest;
    }

    /** {@code mulMat2x2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _right00 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset);
        float _right10 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 4L);
        float _right01 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 8L);
        float _right11 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Float2x3OpsKernelsAddress.mulMat3x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat3x3_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + _lo * 4L);
            float _eright1 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + (_lo + 1) * 4L);
            float _eright2 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, java.lang.Math.fma(_eright2, _self02, java.lang.Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, java.lang.Math.fma(_eright2, _self12, java.lang.Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eright2);
        }
        return dest;
    }

    /** {@code mulMat3x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat3x3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + _lo * 4L);
            float _eright1 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + (_lo + 1) * 4L);
            float _eright2 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, ((_eright2) * (_self02) + (((_eright0) * (_self00) + (_eright1 * _self01)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, ((_eright2) * (_self12) + (((_eright0) * (_self10) + (_eright1 * _self11)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eright2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        return preMul_api_fma_s79ceae4d_1(dest, destOffset, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12);
    }

    /** Piece 2 of {@code preMul_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma_s79ceae4d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self01, float _self11, float _self02, float _self12, float _other00, float _other10, float _other01, float _other11, float _other02, float _other12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_other00, _self02, java.lang.Math.fma(_other01, _self12, _other02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_other10, _self02, java.lang.Math.fma(_other11, _self12, _other12)));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_other00) * (_self01) + (_other01 * _self11)));
        return preMul_api_mulAdd_see9ba77c_1(dest, destOffset, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12);
    }

    /** Piece 2 of {@code preMul_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd_see9ba77c_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self01, float _self11, float _self02, float _self12, float _other00, float _other10, float _other01, float _other11, float _other02, float _other12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_other10) * (_self01) + (_other11 * _self11)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_other00) * (_self02) + (((_other01) * (_self12) + (_other02)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_other10) * (_self02) + (((_other11) * (_self12) + (_other12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x2_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L);
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1));
        }
        return dest;
    }

    /** {@code preMulMat2x2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat2x2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L);
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, ((_other00) * (_eself0) + (_other01 * _eself1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, ((_other10) * (_eself0) + (_other11 * _eself1)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x3OpsKernelsAddress.preMulMat3x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat3x3_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMulMat3x3_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat3x3_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other20 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other21 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 24L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 28L);
        float _other22 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 32L);
        return preMulMat3x3_api_fma_s4b0959cf_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat3x3_api_fma_s4b0959cf_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_other20, _self00, _other21 * _self10));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_other20, _self01, _other21 * _self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_other00, _self02, java.lang.Math.fma(_other01, _self12, _other02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_other10, _self02, java.lang.Math.fma(_other11, _self12, _other12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_other20, _self02, java.lang.Math.fma(_other21, _self12, _other22)));
        return dest;
    }

    /** {@code preMulMat3x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat3x3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other20 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other21 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 24L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 28L);
        float _other22 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 32L);
        return preMulMat3x3_api_mulAdd_sbe6ffc8e_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat3x3_api_mulAdd_sbe6ffc8e_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_other20) * (_self00) + (_other21 * _self10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_other00) * (_self01) + (_other01 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_other10) * (_self01) + (_other11 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_other20) * (_self01) + (_other21 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_other00) * (_self02) + (((_other01) * (_self12) + (_other02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_other10) * (_self02) + (((_other11) * (_self12) + (_other12)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_other20) * (_self02) + (((_other21) * (_self12) + (_other22)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eother1 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + (_lo + 1) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, java.lang.Math.fma(weight, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + _lo * 4L), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, java.lang.Math.fma(weight, _eother1, _eself1));
        }
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eother1 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + (_lo + 1) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, ((weight) * (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + _lo * 4L)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, ((weight) * (_eother1) + (_eself1)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        Float2x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        Float2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, vY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Float2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, v.get(_JAVA_FLOAT_UNALIGNED, vOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _vy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        long _destBase = dest.address() + destOffset;
        Float2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        Float2x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Float2x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _vy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeView_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float left, float right, float bottom, float top) {
        long _destBase = dest.address() + destOffset;
        Float2x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeView_api(java.lang.foreign.MemorySegment dest, long destOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0_inv + _t0_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t1_inv + _t1_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, -(left + right) * _t0_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -(bottom + top) * _t1_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self02, _t1, _self12 * _t0));
        return dest;
    }

    /** {@code preRotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self01) * (_t1) + (_self11 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self02) * (_t0) - (_self12 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self02) * (_t1) + (_self12 * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_fma(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@code preRotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        return preRotateAround_api_fma_s9520ea30_1(dest, destOffset, pivotX, pivotY, _t0, _t1, _self02, _self12, _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0);
    }

    /** Piece 2 of {@code preRotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_fma_s9520ea30_1(java.lang.foreign.MemorySegment dest, long destOffset, float pivotX, float pivotY, float _t0, float _t1, float _self02, float _self12, float _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self02, _t1, _self12 * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1)));
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self01) * (_t1) + (_self11 * _t0)));
        return preRotateAround_api_mulAdd_sdda5616d_1(dest, destOffset, pivotX, pivotY, _t0, _t1, _self02, _self12, _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0);
    }

    /** Piece 2 of {@code preRotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_mulAdd_sdda5616d_1(java.lang.foreign.MemorySegment dest, long destOffset, float pivotX, float pivotY, float _t0, float _t1, float _self02, float _self12, float _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((pivotX) * (_t6) + (pivotY * _t1)) + ((_self02) * (_t0) - (_self12 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self02) * (_t1) + (_self12 * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float2x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@code preRotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        return preRotateAround_api_fma_s3ff13a0e_1(dest, destOffset, _t0, _t1, _pivotx, _pivoty, _self02, _self12, _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0);
    }

    /** Piece 2 of {@code preRotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_fma_s3ff13a0e_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _pivotx, float _pivoty, float _self02, float _self12, float _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_pivotx, _t6, _pivoty * _t1) + java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self02, _t1, _self12 * _t0) + java.lang.Math.fma(_pivoty, _t6, -(_pivotx * _t1)));
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self01) * (_t1) + (_self11 * _t0)));
        return preRotateAround_api_mulAdd_sc635cf9_1(dest, destOffset, _t0, _t1, _pivotx, _pivoty, _self02, _self12, _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0);
    }

    /** Piece 2 of {@code preRotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_mulAdd_sc635cf9_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _pivotx, float _pivoty, float _self02, float _self12, float _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_pivotx) * (_t6) + (_pivoty * _t1)) + ((_self02) * (_t0) - (_self12 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self02) * (_t1) + (_self12 * _t0)) + ((_pivoty) * (_t6) - (_pivotx * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L) * vX);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, _eself1 * vY);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L) * _vx);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, _eself1 * _vy);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, s * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, s * _eself1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = 1.0f - s;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(s, _self02, pivotX * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(s, _self12, pivotY * _t0));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = 1.0f - s;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((s) * (_self02) + (pivotX * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((s) * (_self12) + (pivotY * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = 1.0f - s;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(s, _self02, _pivotx * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(s, _self12, _pivoty * _t0));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = 1.0f - s;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((s) * (_self02) + (_pivotx * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((s) * (_self12) + (_pivoty * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, sX * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, sY * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, sX * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, sY * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(pivotX, 1.0f - sX, sX * _self02));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(pivotY, 1.0f - sY, sY * _self12));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, sX * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, sY * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, sX * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, sY * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((pivotX) * (1.0f - sX) + (sX * _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((pivotY) * (1.0f - sY) + (sY * _self12)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@code preScaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _sx = s.get(_JAVA_FLOAT_UNALIGNED, sOffset);
        float _sy = s.get(_JAVA_FLOAT_UNALIGNED, sOffset + 4L);
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sx * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sy * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sx * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sy * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_pivotx, 1.0f - _sx, _sx * _self02));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_pivoty, 1.0f - _sy, _sy * _self12));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _sx = s.get(_JAVA_FLOAT_UNALIGNED, sOffset);
        float _sy = s.get(_JAVA_FLOAT_UNALIGNED, sOffset + 4L);
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sx * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sy * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sx * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sy * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_pivotx) * (1.0f - _sx) + (_sx * _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_pivoty) * (1.0f - _sy) + (_sy * _self12)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self02 + vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12 + vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float2x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self02 + _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12 + _vy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_fma(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@code rotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        return rotateAround_api_fma_sa1aae0b_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, java.lang.Math.fma(pivotX, _t9, pivotY * _t1), java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma_sa1aae0b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _t10, float _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self11) * (_t0) - (_self10 * _t1)));
        return rotateAround_api_mulAdd_sfe9ce470_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, ((pivotX) * (_t9) + (pivotY * _t1)), ((pivotY) * (_t9) - (pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd_sfe9ce470_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _t10, float _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float2x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@code rotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        return rotateAround_api_fma_s6881e44b_1(dest, destOffset, _t0, _t1, _self00, _self10, _self01, _self11, _self02, _self12, java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1), java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma_s6881e44b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _t10, float _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self11) * (_t0) - (_self10 * _t1)));
        return rotateAround_api_mulAdd_s8d02fece_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, ((_pivotx) * (_t9) + (_pivoty * _t1)), ((_pivoty) * (_t9) - (_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd_s8d02fece_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _t10, float _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10 * vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01 * vY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11 * vY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10 * _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01 * _vy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11 * _vy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = 1.0f - s;
        float _t1 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * _t0;
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = 1.0f - s;
        float _t1 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * _t0;
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, sX * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, sX * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, sY * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, sY * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, sX * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, sX * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, sY * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, sY * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@code scaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _sx = s.get(_JAVA_FLOAT_UNALIGNED, sOffset);
        float _sy = s.get(_JAVA_FLOAT_UNALIGNED, sOffset + 4L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * (1.0f - _sy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sx * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sx * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sy * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sy * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _sx = s.get(_JAVA_FLOAT_UNALIGNED, sOffset);
        float _sy = s.get(_JAVA_FLOAT_UNALIGNED, sOffset + 4L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * (1.0f - _sy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sx * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sx * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sy * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sy * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment view_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float left, float right, float bottom, float top) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.lang.foreign.MemorySegment view_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float left, float right, float bottom, float top) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L), vZ, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), vX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * vY)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, vZ, java.lang.Math.fma(_self10, vX, _self11 * vY)));
        return dest;
    }

    /** {@code mulVec3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulVec3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L)) * (vZ) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (vX) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * vY)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (vZ) + (((_self10) * (vX) + (_self11 * vY)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float2x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L), _vz, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _vx, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * _vy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _vz, java.lang.Math.fma(_self10, _vx, _self11 * _vy)));
        return dest;
    }

    /** {@code mulVec3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulVec3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L)) * (_vz) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_vx) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * _vy)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_vz) + (((_self10) * (_vx) + (_self11 * _vy)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), vX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * vY));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (vX) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * vY)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment transformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
            float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _vx, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * _vy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
            float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_vx) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * _vy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_vx) + (_self11 * _vy)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment transformPosition_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformPosition_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), vX, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), vY, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
        return dest;
    }

    /** {@code transformPosition_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment transformPosition_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (vX) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) * (vY) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformPosition_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _vx, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), _vy, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    /** {@code transformPosition_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment transformPosition_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_vx) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) * (_vy) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
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
}
