// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x2Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float2x2Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float2x2OpsKernelsSegment {
    private Float2x2OpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment getColumn_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getColumn_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        float _idxSw0;
        float _idxSw1;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        switch (col) {
            case 0: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L); break;
            case 1: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _idxSw0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _idxSw1);
        return dest;
    }

    public static float getRotationAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2x2OpsKernelsAddress.getRotationAngle_unsafe(_srcBase);
    }

    public static float getRotationAngle_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
    }

    public static java.lang.foreign.MemorySegment getRow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getRow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        float _idxSw0;
        float _idxSw1;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        switch (row) {
            case 0: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L); break;
            case 1: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _idxSw0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _idxSw1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cofactor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cofactor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self00);
        return dest;
    }

    public static float determinant_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2x2OpsKernelsAddress.determinant_unsafe(_srcBase);
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
        return Float2x2OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_self11, _self11, java.lang.Math.fma(_self10, _self10, java.lang.Math.fma(_self00, _self00, _self01 * _self01))));
        } else {
            float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            return (float) java.lang.Math.sqrt(((_self11) * (_self11) + (((_self10) * (_self10) + (((_self00) * (_self00) + (_self01 * _self01)))))));
        }
    }

    public static java.lang.foreign.MemorySegment invert_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = -(_self01 * _self10);
        float _t5 = java.lang.Math.fma(_self00, _self11, _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float2x2OpsKernelsSegment.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Float2x2OpsKernelsSegment.invert_degenerate_api(dest, destOffset, src, srcOffset);
        }
        float _t5_inv = 1.0f / _t5;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self11 * _t5_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_self10 * _t5_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_self01 * _t5_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self00 * _t5_inv);
        return dest;
    }

    /** {@code invert_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = -(_self01 * _self10);
        float _t5 = ((_self00) * (_self11) + (_t1)) - ((_self01) * (_self10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float2x2OpsKernelsSegment.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Float2x2OpsKernelsSegment.invert_degenerate_api(dest, destOffset, src, srcOffset);
        }
        float _t5_inv = 1.0f / _t5;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self11 * _t5_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_self10 * _t5_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_self01 * _t5_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self00 * _t5_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float2x2OpsKernelsSegment.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float2x2OpsKernelsSegment.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment invert_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t6 * _sp0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t9 * _sp0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t8 * _sp1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t7 * _sp1);
        return dest;
    }

    /** {@code invert_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t6 * _sp0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t9 * _sp0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t8 * _sp1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t7 * _sp1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x2OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
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
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t2 = -(_other01 * _other10);
        float _t3 = -(_self01 * _self10);
        float _t10 = java.lang.Math.fma(_other00, _other11, _t2) - java.lang.Math.fma(_other01, _other10, _t2);
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return Float2x2OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t11 = java.lang.Math.fma(_self00, _self11, _t3) - java.lang.Math.fma(_self01, _self10, _t3);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return Float2x2OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_fma_sf85da39d_1(dest, destOffset, _self10, _self01, _other10, _other01, 1.0f / _t10, 1.0f / _t11, _other11 / _t10, _self11 / _t11, _self00 / _t11, _other00 / _t10);
    }

    /** Piece 2 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma_sf85da39d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self10, float _self01, float _other10, float _other01, float _t10_inv, float _t11_inv, float _t22, float _t23, float _t24, float _t27) {
        float _t28 = -(_other01 * _t10_inv);
        float _t29 = -(_self10 * _t11_inv);
        float _t30 = -(_self01 * _t11_inv);
        float _t31 = -(_other10 * _t10_inv);
        float _t32 = _t22 * _t23;
        float _t33 = _t27 * _t24;
        float _t34 = _t30 * _t22;
        float _t35 = _t29 * _t27;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33));
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t2 = -(_other01 * _other10);
        float _t3 = -(_self01 * _self10);
        float _t10 = ((_other00) * (_other11) + (_t2)) - ((_other01) * (_other10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return Float2x2OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t11 = ((_self00) * (_self11) + (_t3)) - ((_self01) * (_self10) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return Float2x2OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd_s89cb024c_1(dest, destOffset, _self10, _self01, _other10, _other01, 1.0f / _t10, 1.0f / _t11, _other11 / _t10, _self11 / _t11, _self00 / _t11, _other00 / _t10);
    }

    /** Piece 2 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd_s89cb024c_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self10, float _self01, float _other10, float _other01, float _t10_inv, float _t11_inv, float _t22, float _t23, float _t24, float _t27) {
        float _t28 = -(_other01 * _t10_inv);
        float _t29 = -(_self10 * _t11_inv);
        float _t30 = -(_self01 * _t11_inv);
        float _t31 = -(_other10 * _t10_inv);
        float _t32 = _t22 * _t23;
        float _t33 = _t27 * _t24;
        float _t34 = _t30 * _t22;
        float _t35 = _t29 * _t27;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float2x2OpsKernelsSegment.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x2OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
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
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        float _t12 = _other01 * _t0;
        float _t13 = _other00 * _t0;
        float _t14 = _other11 * _t1;
        float _t15 = _other10 * _t1;
        float _t16 = _self10 * _t2;
        float _t19 = _self01 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        return invertProduct_degenerate_api_fma_s41aecf50_1(dest, destOffset, _t0, _t1, _t2, _t3, _t12, _t13, _t14, _t15, _t16, _self00 * _t3, _self11 * _t2, _t19, -(_t19 * _t16), _t36, 1.0f / _t36);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_fma_s41aecf50_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _t2, float _t3, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t31, float _t36, float _t36_inv) {
        float _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        float _t37_inv = 1.0f / _t37;
        float _t48 = _t14 * _t0 / _t36;
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t54 = -(_t12 * _t1 * _t36_inv);
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        float _t58 = _t48 * _t49;
        float _t59 = _t53 * _t50;
        float _t60 = _t56 * _t48;
        float _t61 = _t55 * _t53;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59));
        return dest;
    }

    /** {@code invertProduct_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        float _t12 = _other01 * _t0;
        float _t13 = _other00 * _t0;
        float _t14 = _other11 * _t1;
        float _t15 = _other10 * _t1;
        float _t16 = _self10 * _t2;
        float _t19 = _self01 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        return invertProduct_degenerate_api_mulAdd_s5cf93ceb_1(dest, destOffset, _t0, _t1, _t2, _t3, _t12, _t13, _t14, _t15, _t16, _self00 * _t3, _self11 * _t2, _t19, -(_t19 * _t16), _t36, 1.0f / _t36);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd_s5cf93ceb_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _t2, float _t3, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t31, float _t36, float _t36_inv) {
        float _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        float _t37_inv = 1.0f / _t37;
        float _t48 = _t14 * _t0 / _t36;
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t54 = -(_t12 * _t1 * _t36_inv);
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        float _t58 = _t48 * _t49;
        float _t59 = _t53 * _t50;
        float _t60 = _t56 * _t48;
        float _t61 = _t55 * _t53;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t54) * (_t55) + (_t58)) + ((_t48) * (_t49) - (_t58)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t57) * (_t49) + (_t61)) + ((_t55) * (_t53) - (_t61)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t54) * (_t50) + (_t60)) + ((_t56) * (_t48) - (_t60)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t57) * (_t56) + (_t59)) + ((_t53) * (_t50) - (_t59)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = -(_self01 * _self10);
        float _t5 = java.lang.Math.fma(_self00, _self11, _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float2x2OpsKernelsSegment.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Float2x2OpsKernelsSegment.normal_degenerate_api(dest, destOffset, src, srcOffset);
        }
        float _t5_inv = 1.0f / _t5;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self11 * _t5_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_self01 * _t5_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_self10 * _t5_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self00 * _t5_inv);
        return dest;
    }

    /** {@code normal_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normal_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = -(_self01 * _self10);
        float _t5 = ((_self00) * (_self11) + (_t1)) - ((_self01) * (_self10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float2x2OpsKernelsSegment.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Float2x2OpsKernelsSegment.normal_degenerate_api(dest, destOffset, src, srcOffset);
        }
        float _t5_inv = 1.0f / _t5;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self11 * _t5_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_self01 * _t5_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_self10 * _t5_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self00 * _t5_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float2x2OpsKernelsSegment.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float2x2OpsKernelsSegment.normal_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment normal_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t6 * _sp0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t8 * _sp1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t9 * _sp0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t7 * _sp1);
        return dest;
    }

    /** {@code normal_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normal_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t6 * _sp0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t8 * _sp1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t9 * _sp0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t7 * _sp1);
        return dest;
    }

    public static float trace_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2x2OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static float trace_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
    }

    public static java.lang.foreign.MemorySegment transpose_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transpose_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) + src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _other10 + _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _other01 + _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _other11 + _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, scalar * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, scalar * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, scalar * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, scalar * _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10 - _other10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01 - _other01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11 - _other11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Float2x2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _v10 = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _v01 = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _v11 = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, v.get(_JAVA_FLOAT_UNALIGNED, vOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _v10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _v01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _v11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Float2x2OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _m10 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m01 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 8L);
        float _m11 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, m.get(_JAVA_FLOAT_UNALIGNED, mOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _m11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Float2x2OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _m10 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m01 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 12L);
        float _m11 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 16L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, m.get(_JAVA_FLOAT_UNALIGNED, mOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _m11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeLDU_unsafe(java.lang.foreign.MemorySegment lower, long lowerOffset, java.lang.foreign.MemorySegment diagonal, long diagonalOffset, java.lang.foreign.MemorySegment upper, long upperOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _lowerBase = lower.address() + lowerOffset;
        long _diagonalBase = diagonal.address() + diagonalOffset;
        long _upperBase = upper.address() + upperOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.decomposeLDU_unsafe(_lowerBase, _diagonalBase, _upperBase, _srcBase);
        return lower;
    }

    public static java.lang.foreign.MemorySegment decomposeLDU_api(java.lang.foreign.MemorySegment lower, long lowerOffset, java.lang.foreign.MemorySegment diagonal, long diagonalOffset, java.lang.foreign.MemorySegment upper, long upperOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _rcp0 = 1.0f / _self00;
        float _sp0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * _rcp0;
        lower.set(_JAVA_FLOAT_UNALIGNED, lowerOffset, 1.0f);
        lower.set(_JAVA_FLOAT_UNALIGNED, lowerOffset + 4L, _sp0);
        lower.set(_JAVA_FLOAT_UNALIGNED, lowerOffset + 8L, 0.0f);
        lower.set(_JAVA_FLOAT_UNALIGNED, lowerOffset + 12L, 1.0f);
        diagonal.set(_JAVA_FLOAT_UNALIGNED, diagonalOffset, _self00);
        diagonal.set(_JAVA_FLOAT_UNALIGNED, diagonalOffset + 4L, 0.0f);
        diagonal.set(_JAVA_FLOAT_UNALIGNED, diagonalOffset + 8L, 0.0f);
        diagonal.set(_JAVA_FLOAT_UNALIGNED, diagonalOffset + 12L, _self11 - _self01 * _sp0);
        upper.set(_JAVA_FLOAT_UNALIGNED, upperOffset, 1.0f);
        upper.set(_JAVA_FLOAT_UNALIGNED, upperOffset + 4L, 0.0f);
        upper.set(_JAVA_FLOAT_UNALIGNED, upperOffset + 8L, _self01 * _rcp0);
        upper.set(_JAVA_FLOAT_UNALIGNED, upperOffset + 12L, 1.0f);
        return lower;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        Float2x2OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) - _self00, _self00));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, _other10 - _self10, _self10));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _other01 - _self01, _self01));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(t, _other11 - _self11, _self11));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) - _self00) + (_self00)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (_other10 - _self10) + (_self10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (_other01 - _self01) + (_self01)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((t) * (_other11 - _self11) + (_self11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Float2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _right00 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset);
        float _right10 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 4L);
        float _right01 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 8L);
        float _right11 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _right00 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset);
        float _right10 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 4L);
        float _right01 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 8L);
        float _right11 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_right01) * (_self10) + (_right11 * _self11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_other00) * (_self01) + (_other01 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_other10) * (_self01) + (_other11 * _self11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2x2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(weight, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(weight, _other10, _self10));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(weight, _other01, _self01));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(weight, _other11, _self11));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((weight) * (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((weight) * (_other10) + (_self10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((weight) * (_other01) + (_self01)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((weight) * (_other11) + (_self11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float colX, float colY, float rowX, float rowY) {
        long _destBase = dest.address() + destOffset;
        Float2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, rowX, rowY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, float colX, float colY, float rowX, float rowY) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, colX * rowX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, colY * rowX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, colX * rowY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, colY * rowY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment col, long colOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        long _destBase = dest.address() + destOffset;
        long _colBase = col.address() + colOffset;
        long _rowBase = row.address() + rowOffset;
        Float2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment col, long colOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _colx = col.get(_JAVA_FLOAT_UNALIGNED, colOffset);
        float _coly = col.get(_JAVA_FLOAT_UNALIGNED, colOffset + 4L);
        float _rowx = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset);
        float _rowy = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset + 4L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _colx * _rowx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _coly * _rowx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _colx * _rowy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _coly * _rowy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        Float2x2OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        Float2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Float2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, v.get(_JAVA_FLOAT_UNALIGNED, vOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _vy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        long _destBase = dest.address() + destOffset;
        Float2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        return dest;
    }

    /** {@code preRotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self01) * (_t1) + (_self11 * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10 * vY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01 * vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11 * vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10 * _vy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01 * _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11 * _vy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self11) * (_t0) - (_self10 * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10 * vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01 * vY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11 * vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10 * _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01 * _vy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11 * _vy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
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

    public static java.lang.foreign.MemorySegment mulVec2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
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
