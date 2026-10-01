// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3x3Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float3x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float3x3OpsKernelsSegment {
    private Float3x3OpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment getColumn_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getColumn_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        switch (col) {
            case 0: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L); _idxSw2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L); break;
            case 1: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L); _idxSw2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L); break;
            case 2: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L); _idxSw2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _idxSw0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _idxSw1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _idxSw2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = java.lang.Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 <= java.lang.Math.fma(_self02, _self02, _t1) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, _self11));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(java.lang.Math.fma(_self10, _self22, -_self12 * _self20), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = ((_self12) * (_self12) + (_self22 * _self22));
        if (_t1 <= ((_self02) * (_self02) + (_t1)) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, _self11));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(((_self10) * (_self22) - (_self12 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesXYZ_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = java.lang.Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 <= epsilon * java.lang.Math.fma(_self02, _self02, _t1)) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, _self11));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(java.lang.Math.fma(_self10, _self22, -_self12 * _self20), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = ((_self12) * (_self12) + (_self22 * _self22));
        if (_t1 <= epsilon * ((_self02) * (_self02) + (_t1))) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, _self11));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(((_self10) * (_self22) - (_self12 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = java.lang.Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 <= java.lang.Math.fma(_self01, _self01, _t1) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, _self11));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(java.lang.Math.fma(_self10, _self21, -(_self11 * _self20)), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXZY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = ((_self11) * (_self11) + (_self21 * _self21));
        if (_t1 <= ((_self01) * (_self01) + (_t1)) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, _self11));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(((_self10) * (_self21) - (_self11 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesXZY_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = java.lang.Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 <= epsilon * java.lang.Math.fma(_self01, _self01, _t1)) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, _self11));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(java.lang.Math.fma(_self10, _self21, -(_self11 * _self20)), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXZY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = ((_self11) * (_self11) + (_self21 * _self21));
        if (_t1 <= epsilon * ((_self01) * (_self01) + (_t1))) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, _self11));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(((_self10) * (_self21) - (_self11 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = java.lang.Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 <= java.lang.Math.fma(_self12, _self12, _t1) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, _self00));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = ((_self02) * (_self02) + (_self22 * _self22));
        if (_t1 <= ((_self12) * (_self12) + (_t1)) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, _self00));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(((_self02) * (_self21) - (_self01 * _self22)), ((_self00) * (_self22) - (_self02 * _self20))));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesYXZ_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = java.lang.Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 <= epsilon * java.lang.Math.fma(_self12, _self12, _t1)) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, _self00));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = ((_self02) * (_self02) + (_self22 * _self22));
        if (_t1 <= epsilon * ((_self12) * (_self12) + (_t1))) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, _self00));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, _self22));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(((_self02) * (_self21) - (_self01 * _self22)), ((_self00) * (_self22) - (_self02 * _self20))));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self20 * _self20);
        if (_t1 <= java.lang.Math.fma(_self10, _self10, _t1) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, _self22));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(java.lang.Math.fma(_self00, _self21, _self01 * -_self20), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, _self00));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYZX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = ((_self00) * (_self00) + (_self20 * _self20));
        if (_t1 <= ((_self10) * (_self10) + (_t1)) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, _self22));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(((_self00) * (_self21) - (_self01 * _self20)), ((_self00) * (_self22) - (_self02 * _self20))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, _self00));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesYZX_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self20 * _self20);
        if (_t1 <= epsilon * java.lang.Math.fma(_self10, _self10, _t1)) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, _self22));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(java.lang.Math.fma(_self00, _self21, _self01 * -_self20), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, _self00));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYZX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = ((_self00) * (_self00) + (_self20 * _self20));
        if (_t1 <= epsilon * ((_self10) * (_self10) + (_t1))) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_self02, _self22));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(((_self00) * (_self21) - (_self01 * _self20)), ((_self00) * (_self22) - (_self02 * _self20))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, _self00));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t1 = java.lang.Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 <= java.lang.Math.fma(_self21, _self21, _t1) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, _self00));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(java.lang.Math.fma(_self02, _self11, -_self01 * _self12), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, _self11));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZXY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t1 = ((_self01) * (_self01) + (_self11 * _self11));
        if (_t1 <= ((_self21) * (_self21) + (_t1)) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, _self00));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(((_self02) * (_self11) - (_self01 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, _self11));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesZXY_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t1 = java.lang.Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 <= epsilon * java.lang.Math.fma(_self21, _self21, _t1)) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, _self00));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(java.lang.Math.fma(_self02, _self11, -_self01 * _self12), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, _self11));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZXY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t1 = ((_self01) * (_self01) + (_self11 * _self11));
        if (_t1 <= epsilon * ((_self21) * (_self21) + (_t1))) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, _self00));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(((_self02) * (_self11) - (_self01 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, _self11));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self10 * _self10);
        if (_t1 <= java.lang.Math.fma(_self20, _self20, _t1) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, _self11));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(java.lang.Math.fma(_self02, _self10, -(_self00 * _self12)), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, _self00));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZYX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t1 = ((_self00) * (_self00) + (_self10 * _self10));
        if (_t1 <= ((_self20) * (_self20) + (_t1)) * 2.2737368E-13f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, _self11));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(((_self02) * (_self10) - (_self00 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, _self00));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getEulerAnglesZYX_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self10 * _self10);
        if (_t1 <= epsilon * java.lang.Math.fma(_self20, _self20, _t1)) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, _self11));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(java.lang.Math.fma(_self02, _self10, -(_self00 * _self12)), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, _self00));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZYX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t1 = ((_self00) * (_self00) + (_self10 * _self10));
        if (_t1 <= epsilon * ((_self20) * (_self20) + (_t1))) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(-_self01, _self11));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(((_self02) * (_self10) - (_self00 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_self10, _self00));
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getNormalizedRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getNormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getNormalizedRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getNormalizedRotation_api_fma(dest, destOffset, src, srcOffset);
        return getNormalizedRotation_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getNormalizedRotation_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getNormalizedRotation_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t6 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        float _t7 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        float _t8 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t21, _t23, _t27;
        if (_t6 != 0.0f) {
            _t21 = _self01 * _t9;
            _t23 = _self11 * _t9;
            _t27 = _self21 * _t9;
        } else {
            _t21 = 0.0f;
            _t23 = 0.0f;
            _t27 = 0.0f;
        }
        float _t22, _t24, _t26;
        if (_t7 != 0.0f) {
            _t22 = _self12 * _t10;
            _t24 = _self02 * _t10;
            _t26 = _self22 * _t10;
        } else {
            _t22 = 0.0f;
            _t24 = 0.0f;
            _t26 = 0.0f;
        }
        float _t25, _t28, _t29;
        if (_t8 != 0.0f) {
            _t25 = _self20 * _t11;
            _t28 = _self00 * _t11;
            _t29 = _self10 * _t11;
        } else {
            _t25 = 0.0f;
            _t28 = 0.0f;
            _t29 = 0.0f;
        }
        float _t36 = _t27 - _t22;
        float _t39 = _t27 + _t22;
        float _t49, _t50, _t51;
        if (java.lang.Math.fma(java.lang.Math.fma(_t21, _t22, -(_t23 * _t24)), _t25, java.lang.Math.fma(java.lang.Math.fma(_t23, _t26, -(_t27 * _t22)), _t28, java.lang.Math.fma(_t27, _t24, -(_t21 * _t26)) * _t29)) < 0.0f) {
            _t49 = -_t28;
            _t50 = -_t29;
            _t51 = -_t25;
        } else {
            _t49 = _t28;
            _t50 = _t29;
            _t51 = _t25;
        }
        float _t52 = _t49 + _t23;
        float _t53 = _t50 + _t21;
        float _t55 = _t51 + _t24;
        float _t56 = _t24 - _t51;
        float _t57 = _t50 - _t21;
        float _t58 = _t52 + _t26;
        float _t62 = 1.0f + _t58;
        float _t63 = 1.0f + (_t49 - (_t23 + _t26));
        float _t64 = 1.0f + (_t23 - (_t49 + _t26));
        float _t65 = 1.0f + (_t26 - _t52);
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t62));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63));
        if (_t58 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t36);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t56);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t57);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t63));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t53);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t55);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t53);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t64));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t39);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t56);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t55);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t39);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    /** {@code getNormalizedRotation_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getNormalizedRotation_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t6 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        float _t7 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        float _t8 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t21, _t23, _t27;
        if (_t6 != 0.0f) {
            _t21 = _self01 * _t9;
            _t23 = _self11 * _t9;
            _t27 = _self21 * _t9;
        } else {
            _t21 = 0.0f;
            _t23 = 0.0f;
            _t27 = 0.0f;
        }
        float _t22, _t24, _t26;
        if (_t7 != 0.0f) {
            _t22 = _self12 * _t10;
            _t24 = _self02 * _t10;
            _t26 = _self22 * _t10;
        } else {
            _t22 = 0.0f;
            _t24 = 0.0f;
            _t26 = 0.0f;
        }
        float _t25, _t28, _t29;
        if (_t8 != 0.0f) {
            _t25 = _self20 * _t11;
            _t28 = _self00 * _t11;
            _t29 = _self10 * _t11;
        } else {
            _t25 = 0.0f;
            _t28 = 0.0f;
            _t29 = 0.0f;
        }
        float _t36 = _t27 - _t22;
        float _t39 = _t27 + _t22;
        float _t49, _t50, _t51;
        if (((((_t21) * (_t22) - (_t23 * _t24))) * (_t25) + (((((_t23) * (_t26) - (_t27 * _t22))) * (_t28) + (((_t27) * (_t24) - (_t21 * _t26)) * _t29)))) < 0.0f) {
            _t49 = -_t28;
            _t50 = -_t29;
            _t51 = -_t25;
        } else {
            _t49 = _t28;
            _t50 = _t29;
            _t51 = _t25;
        }
        float _t52 = _t49 + _t23;
        float _t53 = _t50 + _t21;
        float _t55 = _t51 + _t24;
        float _t56 = _t24 - _t51;
        float _t57 = _t50 - _t21;
        float _t58 = _t52 + _t26;
        float _t62 = 1.0f + _t58;
        float _t63 = 1.0f + (_t49 - (_t23 + _t26));
        float _t64 = 1.0f + (_t23 - (_t49 + _t26));
        float _t65 = 1.0f + (_t26 - _t52);
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t62));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63));
        if (_t58 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t36);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t56);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t57);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t63));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t53);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t55);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t53);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t64));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t39);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t56);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t55);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t39);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment getRow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getRow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        switch (row) {
            case 0: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L); _idxSw2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L); break;
            case 1: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L); _idxSw2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L); break;
            case 2: _idxSw0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L); _idxSw1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L); _idxSw2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _idxSw0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _idxSw1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _idxSw2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getScale_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, (float) java.lang.Math.sqrt(java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, (float) java.lang.Math.sqrt(java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, (float) java.lang.Math.sqrt(java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    /** {@code getScale_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getScale_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, (float) java.lang.Math.sqrt(((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, (float) java.lang.Math.sqrt(((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, (float) java.lang.Math.sqrt(((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getTranslation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getUnnormalizedRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.getUnnormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getUnnormalizedRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = _self00 + _self11;
        float _t1 = _self21 - _self12;
        float _t4 = _self01 + _self10;
        float _t6 = _self02 + _self20;
        float _t7 = _self02 - _self20;
        float _t8 = _self12 + _self21;
        float _t9 = _self10 - _self01;
        float _t10 = _self22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_self00 - (_self11 + _self22));
        float _t16 = 1.0f + (_self11 - (_self00 + _self22));
        float _t17 = 1.0f + (_self22 - _t0);
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t10 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t1);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t7);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t9);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t4);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t6);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t4);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t8);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t7);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t6);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t8);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment cofactor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cofactor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cofactor_api_fma(dest, destOffset, src, srcOffset);
        return cofactor_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code cofactor_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cofactor_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self11, _self22, -_self12 * _self21));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self01, _self12, _self02 * -_self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self12, _self20, -_self10 * _self22));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self00, _self22, -(_self02 * _self20)));
        return cofactor_api_fma_s988402ce_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12);
    }

    /** Piece 2 of {@code cofactor_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cofactor_api_fma_s988402ce_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self02, _self10, _self00 * -_self12));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self10, _self21, -_self11 * _self20));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self01, _self20, -(_self00 * _self21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self00, _self11, _self01 * -_self10));
        return dest;
    }

    /** {@code cofactor_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cofactor_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self11) * (_self22) - (_self12 * _self21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self02) * (_self21) - (_self01 * _self22)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self01) * (_self12) - (_self02 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self12) * (_self20) - (_self10 * _self22)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self00) * (_self22) - (_self02 * _self20)));
        return cofactor_api_mulAdd_s3a20e72d_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12);
    }

    /** Piece 2 of {@code cofactor_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cofactor_api_mulAdd_s3a20e72d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self02) * (_self10) - (_self00 * _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self10) * (_self21) - (_self11 * _self20)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self01) * (_self20) - (_self00 * _self21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self00) * (_self11) - (_self01 * _self10)));
        return dest;
    }

    public static float determinant_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3x3OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static float determinant_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return determinant_api_mulAdd(src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t3 = -(_self11 * _self20);
        float _t4 = -_self12 * _self21;
        float _t5 = -_self12 * _self20;
        return java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L), java.lang.Math.fma(_self10, _self21, _t3) - java.lang.Math.fma(_self11, _self20, _t3), java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), java.lang.Math.fma(_self11, _self22, _t4) - java.lang.Math.fma(_self12, _self21, _t4), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * (java.lang.Math.fma(_self12, _self20, _t5) - java.lang.Math.fma(_self10, _self22, _t5))));
    }

    /** {@code determinant_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float determinant_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t3 = -(_self11 * _self20);
        float _t4 = -_self12 * _self21;
        float _t5 = -_self12 * _self20;
        return ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L)) * (((_self10) * (_self21) + (_t3)) - ((_self11) * (_self20) + (_t3))) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (((_self11) * (_self22) + (_t4)) - ((_self12) * (_self21) + (_t4))) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * (((_self12) * (_self20) + (_t5)) - ((_self10) * (_self22) + (_t5)))))));
    }

    public static float frobeniusNorm_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return frobeniusNorm_api_mulAdd(src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return (float) java.lang.Math.sqrt(java.lang.Math.fma(_self00, _self00, _self01 * _self01) + java.lang.Math.fma(_self02, _self02, _self10 * _self10) + (java.lang.Math.fma(_self11, _self11, _self12 * _self12) + java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self21, _self21, _self22 * _self22))));
    }

    /** {@code frobeniusNorm_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float frobeniusNorm_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return (float) java.lang.Math.sqrt(((_self00) * (_self00) + (_self01 * _self01)) + ((_self02) * (_self02) + (_self10 * _self10)) + (((_self11) * (_self11) + (_self12 * _self12)) + ((_self20) * (_self20) + (((_self21) * (_self21) + (_self22 * _self22))))));
    }

    public static java.lang.foreign.MemorySegment invert_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        return invert_api_fma_s1fc3f9d_1(dest, destOffset, src, srcOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self12 * _self20, -_self01 * _self22, -_self02 * _self11, -(_self10 * _self22), -_self02 * _self20, -_self00 * _self12, -_self00 * _self21, -_self01 * _self10, java.lang.Math.fma(_self11, _self22, _t10) - java.lang.Math.fma(_self12, _self21, _t10), java.lang.Math.fma(_self10, _self21, _t11) - java.lang.Math.fma(_self11, _self20, _t11));
    }

    /** Piece 2 of {@code invert_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_fma_s1fc3f9d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27) {
        float _t33 = java.lang.Math.fma(_self02, _t27, java.lang.Math.fma(_self00, _t26, _self01 * (java.lang.Math.fma(_self12, _self20, _t12) - java.lang.Math.fma(_self10, _self22, _t12))));
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsSegment.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t33_inv = 1.0f / _t33;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t26 * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, (java.lang.Math.fma(_self12, _self20, _t15) - java.lang.Math.fma(_self10, _self22, _t15)) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t27 * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, (java.lang.Math.fma(_self02, _self21, _t13) - java.lang.Math.fma(_self01, _self22, _t13)) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, (java.lang.Math.fma(_self00, _self22, _t16) - java.lang.Math.fma(_self02, _self20, _t16)) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, (java.lang.Math.fma(_self01, _self20, _t18) - java.lang.Math.fma(_self00, _self21, _t18)) * _t33_inv);
        return invert_api_fma_s1fc3f9d_2(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _t14, _t17, _t19, _t33_inv);
    }

    /** Piece 3 of {@code invert_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_fma_s1fc3f9d_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _t14, float _t17, float _t19, float _t33_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, (java.lang.Math.fma(_self01, _self12, _t14) - java.lang.Math.fma(_self02, _self11, _t14)) * _t33_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, (java.lang.Math.fma(_self02, _self10, _t17) - java.lang.Math.fma(_self00, _self12, _t17)) * _t33_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, (java.lang.Math.fma(_self00, _self11, _t19) - java.lang.Math.fma(_self01, _self10, _t19)) * _t33_inv);
        return dest;
    }

    /** {@code invert_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        return invert_api_mulAdd_sc0f983d4_1(dest, destOffset, src, srcOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self12 * _self20, -_self01 * _self22, -_self02 * _self11, -(_self10 * _self22), -_self02 * _self20, -_self00 * _self12, -_self00 * _self21, -_self01 * _self10, ((_self11) * (_self22) + (_t10)) - ((_self12) * (_self21) + (_t10)), ((_self10) * (_self21) + (_t11)) - ((_self11) * (_self20) + (_t11)));
    }

    /** Piece 2 of {@code invert_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_mulAdd_sc0f983d4_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27) {
        float _t33 = ((_self02) * (_t27) + (((_self00) * (_t26) + (_self01 * (((_self12) * (_self20) + (_t12)) - ((_self10) * (_self22) + (_t12)))))));
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsSegment.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t33_inv = 1.0f / _t33;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t26 * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, (((_self12) * (_self20) + (_t15)) - ((_self10) * (_self22) + (_t15))) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t27 * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, (((_self02) * (_self21) + (_t13)) - ((_self01) * (_self22) + (_t13))) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, (((_self00) * (_self22) + (_t16)) - ((_self02) * (_self20) + (_t16))) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, (((_self01) * (_self20) + (_t18)) - ((_self00) * (_self21) + (_t18))) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, (((_self01) * (_self12) + (_t14)) - ((_self02) * (_self11) + (_t14))) * _t33_inv);
        return invert_api_mulAdd_sc0f983d4_2(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _t17, _t19, _t33_inv);
    }

    /** Piece 3 of {@code invert_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_mulAdd_sc0f983d4_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _t17, float _t19, float _t33_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, (((_self02) * (_self10) + (_t17)) - ((_self00) * (_self12) + (_t17))) * _t33_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, (((_self00) * (_self11) + (_t19)) - ((_self01) * (_self10) + (_t19))) * _t33_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3x3OpsKernelsSegment.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment invert_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t12 = _self11 * _t0;
        float _t13 = _self22 * _t1;
        float _t14 = _self12 * _t0;
        float _t15 = _self21 * _t1;
        float _t16 = _self10 * _t0;
        float _t17 = _self20 * _t1;
        float _t18 = _self02 * _t2;
        float _t20 = _self01 * _t2;
        return invert_degenerate_api_fma_sd3149408_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _self00 * _t2, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t20 * _t13, -_t18 * _t12, -(_t16 * _t13));
    }

    /** Piece 2 of {@code invert_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_fma_sd3149408_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36) {
        float _t37 = -_t18 * _t17;
        float _t47 = java.lang.Math.fma(_t12, _t13, _t31) - java.lang.Math.fma(_t14, _t15, _t31);
        float _t48 = java.lang.Math.fma(_t16, _t15, _t32) - java.lang.Math.fma(_t12, _t17, _t32);
        float _t53_inv = 1.0f / java.lang.Math.fma(_t48, _t18, java.lang.Math.fma(_t47, _t19, (java.lang.Math.fma(_t14, _t17, _t33) - java.lang.Math.fma(_t16, _t13, _t33)) * _t20));
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t47 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (java.lang.Math.fma(_t14, _t17, _t36) - java.lang.Math.fma(_t16, _t13, _t36)) * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t48 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, (java.lang.Math.fma(_t18, _t15, _t34) - java.lang.Math.fma(_t20, _t13, _t34)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, (java.lang.Math.fma(_t19, _t13, _t37) - java.lang.Math.fma(_t18, _t17, _t37)) * _sp1);
        return invert_degenerate_api_fma_sd3149408_2(dest, destOffset, _t12, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t35, -_t19 * _t14, -_t19 * _t15, -_t20 * _t16, _t1 * _t53_inv, _sp1);
    }

    /** Piece 3 of {@code invert_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_fma_sd3149408_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t12, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t35, float _t38, float _t39, float _t40, float _sp2, float _sp1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, (java.lang.Math.fma(_t20, _t17, _t39) - java.lang.Math.fma(_t19, _t15, _t39)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, (java.lang.Math.fma(_t20, _t14, _t35) - java.lang.Math.fma(_t18, _t12, _t35)) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, (java.lang.Math.fma(_t18, _t16, _t38) - java.lang.Math.fma(_t19, _t14, _t38)) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, (java.lang.Math.fma(_t19, _t12, _t40) - java.lang.Math.fma(_t20, _t16, _t40)) * _sp2);
        return dest;
    }

    /** {@code invert_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t12 = _self11 * _t0;
        float _t13 = _self22 * _t1;
        float _t14 = _self12 * _t0;
        float _t15 = _self21 * _t1;
        float _t16 = _self10 * _t0;
        float _t17 = _self20 * _t1;
        float _t18 = _self02 * _t2;
        float _t20 = _self01 * _t2;
        return invert_degenerate_api_mulAdd_s99cd5c4f_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _self00 * _t2, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t20 * _t13, -_t18 * _t12, -(_t16 * _t13));
    }

    /** Piece 2 of {@code invert_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_mulAdd_s99cd5c4f_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36) {
        float _t37 = -_t18 * _t17;
        float _t47 = ((_t12) * (_t13) + (_t31)) - ((_t14) * (_t15) + (_t31));
        float _t48 = ((_t16) * (_t15) + (_t32)) - ((_t12) * (_t17) + (_t32));
        float _t53_inv = 1.0f / ((_t48) * (_t18) + (((_t47) * (_t19) + ((((_t14) * (_t17) + (_t33)) - ((_t16) * (_t13) + (_t33))) * _t20))));
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t47 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (((_t14) * (_t17) + (_t36)) - ((_t16) * (_t13) + (_t36))) * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t48 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, (((_t18) * (_t15) + (_t34)) - ((_t20) * (_t13) + (_t34))) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, (((_t19) * (_t13) + (_t37)) - ((_t18) * (_t17) + (_t37))) * _sp1);
        return invert_degenerate_api_mulAdd_s99cd5c4f_2(dest, destOffset, _t12, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t35, -_t19 * _t14, -_t19 * _t15, -_t20 * _t16, _t1 * _t53_inv, _sp1);
    }

    /** Piece 3 of {@code invert_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_mulAdd_s99cd5c4f_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t12, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t35, float _t38, float _t39, float _t40, float _sp2, float _sp1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, (((_t20) * (_t17) + (_t39)) - ((_t19) * (_t15) + (_t39))) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, (((_t20) * (_t14) + (_t35)) - ((_t18) * (_t12) + (_t35))) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, (((_t18) * (_t16) + (_t38)) - ((_t19) * (_t14) + (_t38))) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, (((_t19) * (_t12) + (_t40)) - ((_t20) * (_t16) + (_t40))) * _sp2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other20 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other21 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        return invertProduct_api_fma_sf85da39d_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21);
    }

    /** Piece 2 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma_sf85da39d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21) {
        float _other02 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 24L);
        float _other12 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 28L);
        float _other22 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 32L);
        float _t21 = -(_other11 * _other20);
        float _t22 = -_other12 * _other21;
        float _t24 = -(_self11 * _self20);
        float _t25 = -_self12 * _self21;
        return invertProduct_api_fma_sf85da39d_2(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, -_other02 * _other11, -_other12 * _other20, -_self12 * _self20, -_other01 * _other22, -_self10 * _self22, -_self00 * _self21, -_self02 * _self20, -(_self01 * _self22), _self01 * -_self10, -_self00 * _self12, -_self02 * _self11, -_other00 * _other12, -_other02 * _other20, -(_other10 * _other22), -_other01 * _other10, -_other00 * _other21, java.lang.Math.fma(_other10, _other21, _t21) - java.lang.Math.fma(_other11, _other20, _t21), java.lang.Math.fma(_other11, _other22, _t22) - java.lang.Math.fma(_other12, _other21, _t22), java.lang.Math.fma(_self10, _self21, _t24) - java.lang.Math.fma(_self11, _self20, _t24), java.lang.Math.fma(_self11, _self22, _t25) - java.lang.Math.fma(_self12, _self21, _t25));
    }

    /** Piece 3 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma_sf85da39d_2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t20, float _t23, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t81, float _t82, float _t84, float _t85) {
        float _t108 = java.lang.Math.fma(_other02, _t81, java.lang.Math.fma(_other00, _t82, _other01 * (java.lang.Math.fma(_other12, _other20, _t23) - java.lang.Math.fma(_other10, _other22, _t23))));
        if (!(java.lang.Math.abs(_t108) > 1.1754944E-38f && java.lang.Math.abs(_t108) < 8.507059E37f)) return Float3x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t109 = java.lang.Math.fma(_self02, _t84, java.lang.Math.fma(_self00, _t85, _self01 * (java.lang.Math.fma(_self12, _self20, _t26) - java.lang.Math.fma(_self10, _self22, _t26))));
        if (!(java.lang.Math.abs(_t109) > 1.1754944E-38f && java.lang.Math.abs(_t109) < 8.507059E37f)) return Float3x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_fma_sf85da39d_3(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t29, _t30, _t31, _t32, _t33, _t34, _t35, _t36, _t37, _t38, _t39, _t81, _t108, _t109, (java.lang.Math.fma(_other01, _other12, _t20) - java.lang.Math.fma(_other02, _other11, _t20)) / _t108, _t84 / _t109, (java.lang.Math.fma(_other02, _other21, _t27) - java.lang.Math.fma(_other01, _other22, _t27)) / _t108, (java.lang.Math.fma(_self12, _self20, _t28) - java.lang.Math.fma(_self10, _self22, _t28)) / _t109, _t82 / _t108, _t85 / _t109);
    }

    /** Piece 4 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma_sf85da39d_3(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t81, float _t108, float _t109, float _t128, float _t129, float _t130, float _t131, float _t132, float _t133) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t128, _t129, java.lang.Math.fma(_t130, _t131, _t132 * _t133)));
        return invertProduct_api_fma_sf85da39d_4(dest, destOffset, _t128, _t129, _t130, _t131, _t132, _t133, (java.lang.Math.fma(_self01, _self20, _t29) - java.lang.Math.fma(_self00, _self21, _t29)) / _t109, (java.lang.Math.fma(_self00, _self22, _t30) - java.lang.Math.fma(_self02, _self20, _t30)) / _t109, (java.lang.Math.fma(_self02, _self21, _t31) - java.lang.Math.fma(_self01, _self22, _t31)) / _t109, (java.lang.Math.fma(_self00, _self11, _t32) - java.lang.Math.fma(_self01, _self10, _t32)) / _t109, (java.lang.Math.fma(_self02, _self10, _t33) - java.lang.Math.fma(_self00, _self12, _t33)) / _t109, (java.lang.Math.fma(_self01, _self12, _t34) - java.lang.Math.fma(_self02, _self11, _t34)) / _t109, (java.lang.Math.fma(_other02, _other10, _t35) - java.lang.Math.fma(_other00, _other12, _t35)) / _t108, (java.lang.Math.fma(_other00, _other22, _t36) - java.lang.Math.fma(_other02, _other20, _t36)) / _t108, (java.lang.Math.fma(_other12, _other20, _t37) - java.lang.Math.fma(_other10, _other22, _t37)) / _t108, (java.lang.Math.fma(_other00, _other11, _t38) - java.lang.Math.fma(_other01, _other10, _t38)) / _t108, (java.lang.Math.fma(_other01, _other20, _t39) - java.lang.Math.fma(_other00, _other21, _t39)) / _t108, _t81 / _t108);
    }

    /** Piece 5 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma_sf85da39d_4(java.lang.foreign.MemorySegment dest, long destOffset, float _t128, float _t129, float _t130, float _t131, float _t132, float _t133, float _t134, float _t135, float _t136, float _t137, float _t138, float _t139, float _t140, float _t141, float _t142, float _t143, float _t144, float _t145) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t140, _t129, java.lang.Math.fma(_t141, _t131, _t142 * _t133)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t143, _t129, java.lang.Math.fma(_t144, _t131, _t145 * _t133)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t128, _t134, java.lang.Math.fma(_t130, _t135, _t132 * _t136)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t140, _t134, java.lang.Math.fma(_t141, _t135, _t142 * _t136)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t143, _t134, java.lang.Math.fma(_t144, _t135, _t145 * _t136)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t128, _t137, java.lang.Math.fma(_t130, _t138, _t132 * _t139)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t140, _t137, java.lang.Math.fma(_t141, _t138, _t142 * _t139)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t143, _t137, java.lang.Math.fma(_t144, _t138, _t145 * _t139)));
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other20 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other21 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        return invertProduct_api_mulAdd_s89cb024c_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21);
    }

    /** Piece 2 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd_s89cb024c_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21) {
        float _other02 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 24L);
        float _other12 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 28L);
        float _other22 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 32L);
        float _t21 = -(_other11 * _other20);
        float _t22 = -_other12 * _other21;
        float _t24 = -(_self11 * _self20);
        float _t25 = -_self12 * _self21;
        return invertProduct_api_mulAdd_s89cb024c_2(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, -_other02 * _other11, -_other12 * _other20, -_self12 * _self20, -_other01 * _other22, -_self10 * _self22, -_self00 * _self21, -_self02 * _self20, -(_self01 * _self22), _self01 * -_self10, -_self00 * _self12, -_self02 * _self11, -_other00 * _other12, -_other02 * _other20, -(_other10 * _other22), -_other01 * _other10, -_other00 * _other21, ((_other10) * (_other21) + (_t21)) - ((_other11) * (_other20) + (_t21)), ((_other11) * (_other22) + (_t22)) - ((_other12) * (_other21) + (_t22)), ((_self10) * (_self21) + (_t24)) - ((_self11) * (_self20) + (_t24)), ((_self11) * (_self22) + (_t25)) - ((_self12) * (_self21) + (_t25)));
    }

    /** Piece 3 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd_s89cb024c_2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t20, float _t23, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t81, float _t82, float _t84, float _t85) {
        float _t108 = ((_other02) * (_t81) + (((_other00) * (_t82) + (_other01 * (((_other12) * (_other20) + (_t23)) - ((_other10) * (_other22) + (_t23)))))));
        if (!(java.lang.Math.abs(_t108) > 1.1754944E-38f && java.lang.Math.abs(_t108) < 8.507059E37f)) return Float3x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t109 = ((_self02) * (_t84) + (((_self00) * (_t85) + (_self01 * (((_self12) * (_self20) + (_t26)) - ((_self10) * (_self22) + (_t26)))))));
        if (!(java.lang.Math.abs(_t109) > 1.1754944E-38f && java.lang.Math.abs(_t109) < 8.507059E37f)) return Float3x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd_s89cb024c_3(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t30, _t31, _t32, _t33, _t34, _t35, _t36, _t37, _t38, _t39, _t81, _t108, _t109, (((_other01) * (_other12) + (_t20)) - ((_other02) * (_other11) + (_t20))) / _t108, _t84 / _t109, (((_other02) * (_other21) + (_t27)) - ((_other01) * (_other22) + (_t27))) / _t108, (((_self12) * (_self20) + (_t28)) - ((_self10) * (_self22) + (_t28))) / _t109, _t82 / _t108, _t85 / _t109, (((_self01) * (_self20) + (_t29)) - ((_self00) * (_self21) + (_t29))) / _t109);
    }

    /** Piece 4 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd_s89cb024c_3(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t81, float _t108, float _t109, float _t128, float _t129, float _t130, float _t131, float _t132, float _t133, float _t134) {
        float _t140 = (((_other02) * (_other10) + (_t35)) - ((_other00) * (_other12) + (_t35))) / _t108;
        float _t141 = (((_other00) * (_other22) + (_t36)) - ((_other02) * (_other20) + (_t36))) / _t108;
        float _t142 = (((_other12) * (_other20) + (_t37)) - ((_other10) * (_other22) + (_t37))) / _t108;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t128) * (_t129) + (((_t130) * (_t131) + (_t132 * _t133)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t140) * (_t129) + (((_t141) * (_t131) + (_t142 * _t133)))));
        return invertProduct_api_mulAdd_s89cb024c_4(dest, destOffset, _t128, _t129, _t130, _t131, _t132, _t133, _t134, (((_self00) * (_self22) + (_t30)) - ((_self02) * (_self20) + (_t30))) / _t109, (((_self02) * (_self21) + (_t31)) - ((_self01) * (_self22) + (_t31))) / _t109, (((_self00) * (_self11) + (_t32)) - ((_self01) * (_self10) + (_t32))) / _t109, (((_self02) * (_self10) + (_t33)) - ((_self00) * (_self12) + (_t33))) / _t109, (((_self01) * (_self12) + (_t34)) - ((_self02) * (_self11) + (_t34))) / _t109, _t140, _t141, _t142, (((_other00) * (_other11) + (_t38)) - ((_other01) * (_other10) + (_t38))) / _t108, (((_other01) * (_other20) + (_t39)) - ((_other00) * (_other21) + (_t39))) / _t108, _t81 / _t108);
    }

    /** Piece 5 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd_s89cb024c_4(java.lang.foreign.MemorySegment dest, long destOffset, float _t128, float _t129, float _t130, float _t131, float _t132, float _t133, float _t134, float _t135, float _t136, float _t137, float _t138, float _t139, float _t140, float _t141, float _t142, float _t143, float _t144, float _t145) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t143) * (_t129) + (((_t144) * (_t131) + (_t145 * _t133)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t128) * (_t134) + (((_t130) * (_t135) + (_t132 * _t136)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t140) * (_t134) + (((_t141) * (_t135) + (_t142 * _t136)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t143) * (_t134) + (((_t144) * (_t135) + (_t145 * _t136)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_t128) * (_t137) + (((_t130) * (_t138) + (_t132 * _t139)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t140) * (_t137) + (((_t141) * (_t138) + (_t142 * _t139)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_t143) * (_t137) + (((_t144) * (_t138) + (_t145 * _t139)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float3x3OpsKernelsSegment.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other20 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other21 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        return invertProduct_degenerate_api_fma_s41aecf50_10(dest, destOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21);
    }

    /** Part 1 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_1(float _other10, float _other20, float _other11, float _other21, float _other12, float _other22) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t27 = _other11 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t63 = -(_t27 * _t30);
        return java.lang.Math.fma((_other10 * _t1), (_other21 * _t2), _t63) - java.lang.Math.fma(_t27, _t30, _t63);
    }

    /** Part 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_2(float _other10, float _other20, float _other11, float _other21, float _other12, float _other22) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t64 = -_t25 * _t29;
        return java.lang.Math.fma((_other11 * _t1), (_other22 * _t2), _t64) - java.lang.Math.fma(_t25, _t29, _t64);
    }

    /** Part 3 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_3(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t123, float _t124) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t65 = -_t25 * _t30;
        return java.lang.Math.fma(_t123, (_other02 * _t0), java.lang.Math.fma(_t124, (_other00 * _t0), (java.lang.Math.fma(_t25, _t30, _t65) - java.lang.Math.fma((_other10 * _t1), (_other22 * _t2), _t65)) * (_other01 * _t0)));
    }

    /** Part 4 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_4(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t26 = _other02 * _t0;
        float _t27 = _other11 * _t1;
        float _t62 = -_t26 * _t27;
        return (java.lang.Math.fma((_other01 * _t0), (_other12 * _t1), _t62) - java.lang.Math.fma(_t26, _t27, _t62)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_5(float _self10, float _self20, float _self11, float _self21, float _self12, float _self22) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t66 = -_t35 * _t36;
        return java.lang.Math.fma((_self10 * _t3), (_self21 * _t4), _t66) - java.lang.Math.fma(_t35, _t36, _t66);
    }

    /** Part 6 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_6(float _self10, float _self20, float _self11, float _self21, float _self12, float _self22) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t39 = _self12 * _t3;
        float _t67 = -_t39 * _t34;
        return java.lang.Math.fma((_self11 * _t3), (_self22 * _t4), _t67) - java.lang.Math.fma(_t39, _t34, _t67);
    }

    /** Part 7 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_7(float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t126, float _t127) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t36 = _self20 * _t4;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t39 = _self12 * _t3;
        float _t68 = -(_t39 * _t36);
        return java.lang.Math.fma(_t126, (_self02 * _t5), java.lang.Math.fma(_t127, (_self00 * _t5), (java.lang.Math.fma(_t39, _t36, _t68) - java.lang.Math.fma((_self10 * _t3), (_self22 * _t4), _t68)) * (_self01 * _t5)));
    }

    /** Part 8 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_8(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t24 = _other01 * _t0;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t31 = _other22 * _t2;
        float _t69 = -_t24 * _t31;
        return (java.lang.Math.fma((_other02 * _t0), (_other21 * _t2), _t69) - java.lang.Math.fma(_t24, _t31, _t69)) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 9 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_9(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other01, float _other02, float _t124, float _t166, float _t186, float _t126, float _t127, float _t167, float _t188) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t33 = _self10 * _t3;
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t38 = _self22 * _t4;
        float _t70 = -_t33 * _t38;
        float _t189 = (java.lang.Math.fma((_self12 * _t3), (_self20 * _t4), _t70) - java.lang.Math.fma(_t33, _t38, _t70)) * _t5 / _t167;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t186, (_t126 * _t5 / _t167), java.lang.Math.fma(_t188, _t189, (_t124 * (unitScale(_other00, _other01, _other02)) / _t166) * (_t127 * _t5 / _t167))));
        return _t189;
    }

    /** Part of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_fma_s41aecf50_528(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t123, float _t124, float _t166, float _t186, float _t126, float _t127, float _t167, float _t188, float _t189) {
        float _t198 = invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t199 = invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_2(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t200 = invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_3(dest, destOffset, _self00, _self01, _self02, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t126, _t127, _t167, _t189, _t198, _t199);
        float _t201 = invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t202 = invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_5(dest, destOffset, _self00, _self01, _self02, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t166, _t126, _t127, _t167, _t189, _t201);
        invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_6(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t123, _t124, _t166, _t186, _t167, _t188, _t198, _t199, _t200, _t201, _t202);
        invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_7(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t123, _t124, _t166, _t186, _t167, _t188, _t198, _t199, _t200, _t201, _t202);
    }

    /** Part 1 of {@code invertProduct_degenerate_api_fma_s41aecf50_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_1(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t32 = _other00 * _t0;
        float _t77 = -_t32 * _t25;
        return (java.lang.Math.fma((_other02 * _t0), (_other10 * _t1), _t77) - java.lang.Math.fma(_t32, _t25, _t77)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 2 of {@code invertProduct_degenerate_api_fma_s41aecf50_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_2(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t26 = _other02 * _t0;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t78 = -_t26 * _t30;
        return (java.lang.Math.fma((_other00 * _t0), (_other22 * _t2), _t78) - java.lang.Math.fma(_t26, _t30, _t78)) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 3 of {@code invertProduct_degenerate_api_fma_s41aecf50_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_3(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self01, float _self02, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166, float _t126, float _t127, float _t167, float _t189, float _t198, float _t199) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t28 = _other10 * _t1;
        float _t31 = _other22 * _t2;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t79 = -(_t28 * _t31);
        float _t200 = (java.lang.Math.fma((_other12 * _t1), (_other20 * _t2), _t79) - java.lang.Math.fma(_t28, _t31, _t79)) * (unitScale(_other00, _other01, _other02)) / _t166;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t198, (_t126 * _t5 / _t167), java.lang.Math.fma(_t199, _t189, _t200 * (_t127 * _t5 / _t167))));
        return _t200;
    }

    /** Part 4 of {@code invertProduct_degenerate_api_fma_s41aecf50_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_4(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t24 = _other01 * _t0;
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t28 = _other10 * _t1;
        float _t80 = -_t24 * _t28;
        return (java.lang.Math.fma((_other00 * _t0), (_other11 * _t1), _t80) - java.lang.Math.fma(_t24, _t28, _t80)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_api_fma_s41aecf50_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_5(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self01, float _self02, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t123, float _t166, float _t126, float _t127, float _t167, float _t189, float _t201) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t32 = _other00 * _t0;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t81 = -_t32 * _t29;
        float _t202 = (java.lang.Math.fma((_other01 * _t0), (_other20 * _t2), _t81) - java.lang.Math.fma(_t32, _t29, _t81)) * (unitScale(_other10, _other11, _other12)) / _t166;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t201, (_t126 * _t5 / _t167), java.lang.Math.fma(_t202, _t189, (_t123 * _t0 / _t166) * (_t127 * _t5 / _t167))));
        return _t202;
    }

    /** Part 6 of {@code invertProduct_degenerate_api_fma_s41aecf50_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_6(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other01, float _other02, float _t123, float _t124, float _t166, float _t186, float _t167, float _t188, float _t198, float _t199, float _t200, float _t201, float _t202) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t36 = _self20 * _t4;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t37 = _self02 * _t5;
        float _t38 = _self22 * _t4;
        float _t40 = _self00 * _t5;
        float _t41 = _self01 * _t5;
        float _t71 = -(_t40 * _t34);
        float _t192 = (java.lang.Math.fma(_t41, _t36, _t71) - java.lang.Math.fma(_t40, _t34, _t71)) * _t3 / _t167;
        float _t72 = -(_t37 * _t36);
        float _t193 = (java.lang.Math.fma(_t40, _t38, _t72) - java.lang.Math.fma(_t37, _t36, _t72)) * _t3 / _t167;
        float _t73 = -(_t41 * _t38);
        float _t194 = (java.lang.Math.fma(_t37, _t34, _t73) - java.lang.Math.fma(_t41, _t38, _t73)) * _t3 / _t167;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t186, _t192, java.lang.Math.fma(_t188, _t193, (_t124 * _t0 / _t166) * _t194)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t198, _t192, java.lang.Math.fma(_t199, _t193, _t200 * _t194)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t201, _t192, java.lang.Math.fma(_t202, _t193, (_t123 * _t0 / _t166) * _t194)));
    }

    /** Part 7 of {@code invertProduct_degenerate_api_fma_s41aecf50_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_api_fma_s41aecf50_528_sf1facd1b_7(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other01, float _other02, float _t123, float _t124, float _t166, float _t186, float _t167, float _t188, float _t198, float _t199, float _t200, float _t201, float _t202) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t33 = _self10 * _t3;
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t35 = _self11 * _t3;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t37 = _self02 * _t5;
        float _t39 = _self12 * _t3;
        float _t40 = _self00 * _t5;
        float _t41 = _self01 * _t5;
        float _t74 = _t41 * -_t33;
        float _t195 = (java.lang.Math.fma(_t40, _t35, _t74) - java.lang.Math.fma(_t41, _t33, _t74)) * _t4 / _t167;
        float _t75 = _t40 * -_t39;
        float _t196 = (java.lang.Math.fma(_t37, _t33, _t75) - java.lang.Math.fma(_t40, _t39, _t75)) * _t4 / _t167;
        float _t76 = _t37 * -_t35;
        float _t197 = (java.lang.Math.fma(_t41, _t39, _t76) - java.lang.Math.fma(_t37, _t35, _t76)) * _t4 / _t167;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t186, _t195, java.lang.Math.fma(_t188, _t196, (_t124 * _t0 / _t166) * _t197)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t198, _t195, java.lang.Math.fma(_t199, _t196, _t200 * _t197)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t201, _t195, java.lang.Math.fma(_t202, _t196, (_t123 * _t0 / _t166) * _t197)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_fma_s41aecf50_10(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21) {
        float _other02 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 24L);
        float _other12 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 28L);
        float _other22 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 32L);
        return invertProduct_degenerate_api_fma_s41aecf50_11(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_fma_s41aecf50_11(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        float _t123 = invertProduct_degenerate_api_fma_s41aecf50_1(_other10, _other20, _other11, _other21, _other12, _other22);
        float _t124 = invertProduct_degenerate_api_fma_s41aecf50_2(_other10, _other20, _other11, _other21, _other12, _other22);
        float _t166 = invertProduct_degenerate_api_fma_s41aecf50_3(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t124);
        float _t186 = invertProduct_degenerate_api_fma_s41aecf50_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t126 = invertProduct_degenerate_api_fma_s41aecf50_5(_self10, _self20, _self11, _self21, _self12, _self22);
        float _t127 = invertProduct_degenerate_api_fma_s41aecf50_6(_self10, _self20, _self11, _self21, _self12, _self22);
        float _t167 = invertProduct_degenerate_api_fma_s41aecf50_7(_self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t126, _t127);
        float _t188 = invertProduct_degenerate_api_fma_s41aecf50_8(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        invertProduct_degenerate_api_fma_s41aecf50_528(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t124, _t166, _t186, _t126, _t127, _t167, _t188, invertProduct_degenerate_api_fma_s41aecf50_9(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t124, _t166, _t186, _t126, _t127, _t167, _t188));
        return dest;
    }

    /** {@code invertProduct_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other20 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other21 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        return invertProduct_degenerate_api_mulAdd_s5cf93ceb_1(dest, destOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd_s5cf93ceb_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21) {
        float _other02 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 24L);
        float _other12 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 28L);
        float _other22 = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 32L);
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t25 = _other12 * _t1;
        float _t26 = _other02 * _t0;
        float _t27 = _other11 * _t1;
        float _t29 = _other21 * _t2;
        float _t30 = _other20 * _t2;
        float _t34 = _self21 * _t4;
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t39 = _self12 * _t3;
        return invertProduct_degenerate_api_mulAdd_s5cf93ceb_2(dest, destOffset, _t0, _t1, _t2, _t3, _t4, _t5, _other01 * _t0, _t25, _t26, _t27, _other10 * _t1, _t29, _t30, _other22 * _t2, _other00 * _t0, _self10 * _t3, _t34, _t35, _t36, _self02 * _t5, _self22 * _t4, _t39, _self00 * _t5, _self01 * _t5, -_t26 * _t27, -(_t27 * _t30), -_t25 * _t29, -_t25 * _t30, -_t35 * _t36, -_t39 * _t34, -(_t39 * _t36));
    }

    /** Piece 3 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd_s5cf93ceb_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40, float _t41, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t68) {
        float _t123 = ((_t28) * (_t29) + (_t63)) - ((_t27) * (_t30) + (_t63));
        float _t124 = ((_t27) * (_t31) + (_t64)) - ((_t25) * (_t29) + (_t64));
        float _t126 = ((_t33) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66));
        float _t127 = ((_t35) * (_t38) + (_t67)) - ((_t39) * (_t34) + (_t67));
        float _t166 = ((_t123) * (_t26) + (((_t124) * (_t32) + ((((_t25) * (_t30) + (_t65)) - ((_t28) * (_t31) + (_t65))) * _t24))));
        return invertProduct_degenerate_api_mulAdd_s5cf93ceb_3(dest, destOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t24, _t25, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t33, _t34, _t35, _t36, _t37, _t38, _t39, _t40, _t41, -_t24 * _t31, -_t33 * _t38, -(_t40 * _t34), -(_t37 * _t36), -(_t41 * _t38), _t41 * -_t33, _t40 * -_t39, _t37 * -_t35, -_t32 * _t25, -_t26 * _t30, -(_t28 * _t31), -_t24 * _t28, -_t32 * _t29, _t123, _t124, _t126, _t127, _t166, ((_t126) * (_t37) + (((_t127) * (_t40) + ((((_t39) * (_t36) + (_t68)) - ((_t33) * (_t38) + (_t68))) * _t41)))), (((_t24) * (_t25) + (_t62)) - ((_t26) * (_t27) + (_t62))) * _t2 / _t166);
    }

    /** Piece 4 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd_s5cf93ceb_3(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40, float _t41, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79, float _t80, float _t81, float _t123, float _t124, float _t126, float _t127, float _t166, float _t167, float _t186) {
        return invertProduct_degenerate_api_mulAdd_s5cf93ceb_4(dest, destOffset, _t0, _t1, _t2, _t24, _t27, _t28, _t29, _t30, _t32, _t80, _t81, _t123, _t166, _t186, _t126 * _t5 / _t167, (((_t26) * (_t29) + (_t69)) - ((_t24) * (_t31) + (_t69))) * _t1 / _t166, (((_t39) * (_t36) + (_t70)) - ((_t33) * (_t38) + (_t70))) * _t5 / _t167, _t124 * _t0 / _t166, _t127 * _t5 / _t167, (((_t41) * (_t36) + (_t71)) - ((_t40) * (_t34) + (_t71))) * _t3 / _t167, (((_t40) * (_t38) + (_t72)) - ((_t37) * (_t36) + (_t72))) * _t3 / _t167, (((_t37) * (_t34) + (_t73)) - ((_t41) * (_t38) + (_t73))) * _t3 / _t167, (((_t40) * (_t35) + (_t74)) - ((_t41) * (_t33) + (_t74))) * _t4 / _t167, (((_t37) * (_t33) + (_t75)) - ((_t40) * (_t39) + (_t75))) * _t4 / _t167, (((_t41) * (_t39) + (_t76)) - ((_t37) * (_t35) + (_t76))) * _t4 / _t167, (((_t26) * (_t28) + (_t77)) - ((_t32) * (_t25) + (_t77))) * _t2 / _t166, (((_t32) * (_t31) + (_t78)) - ((_t26) * (_t30) + (_t78))) * _t1 / _t166, (((_t25) * (_t30) + (_t79)) - ((_t28) * (_t31) + (_t79))) * _t0 / _t166);
    }

    /** Piece 5 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd_s5cf93ceb_4(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _t2, float _t24, float _t27, float _t28, float _t29, float _t30, float _t32, float _t80, float _t81, float _t123, float _t166, float _t186, float _t187, float _t188, float _t189, float _t190, float _t191, float _t192, float _t193, float _t194, float _t195, float _t196, float _t197, float _t198, float _t199, float _t200) {
        float _t201 = (((_t32) * (_t27) + (_t80)) - ((_t24) * (_t28) + (_t80))) * _t2 / _t166;
        float _t202 = (((_t24) * (_t30) + (_t81)) - ((_t32) * (_t29) + (_t81))) * _t1 / _t166;
        float _t203 = _t123 * _t0 / _t166;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t186) * (_t187) + (((_t188) * (_t189) + (_t190 * _t191)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t198) * (_t187) + (((_t199) * (_t189) + (_t200 * _t191)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t201) * (_t187) + (((_t202) * (_t189) + (_t203 * _t191)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t186) * (_t192) + (((_t188) * (_t193) + (_t190 * _t194)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t198) * (_t192) + (((_t199) * (_t193) + (_t200 * _t194)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t201) * (_t192) + (((_t202) * (_t193) + (_t203 * _t194)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_t186) * (_t195) + (((_t188) * (_t196) + (_t190 * _t197)))));
        return invertProduct_degenerate_api_mulAdd_s5cf93ceb_5(dest, destOffset, _t195, _t196, _t197, _t198, _t199, _t200, _t201, _t202, _t203);
    }

    /** Piece 6 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_mulAdd_s5cf93ceb_5(java.lang.foreign.MemorySegment dest, long destOffset, float _t195, float _t196, float _t197, float _t198, float _t199, float _t200, float _t201, float _t202, float _t203) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t198) * (_t195) + (((_t199) * (_t196) + (_t200 * _t197)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_t201) * (_t195) + (((_t202) * (_t196) + (_t203 * _t197)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_api_fma(dest, destOffset, src, srcOffset);
        return normal_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code normal_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normal_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        return normal_api_fma_s2c1f1020_1(dest, destOffset, src, srcOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self12 * _self20, -_self10 * _self22, -(_self01 * _self22), -_self02 * _self20, -_self00 * _self21, -_self02 * _self11, -_self00 * _self12, _self01 * -_self10, java.lang.Math.fma(_self11, _self22, _t10) - java.lang.Math.fma(_self12, _self21, _t10), java.lang.Math.fma(_self10, _self21, _t11) - java.lang.Math.fma(_self11, _self20, _t11));
    }

    /** Piece 2 of {@code normal_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment normal_api_fma_s2c1f1020_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27) {
        float _t33 = java.lang.Math.fma(_self02, _t27, java.lang.Math.fma(_self00, _t26, _self01 * (java.lang.Math.fma(_self12, _self20, _t12) - java.lang.Math.fma(_self10, _self22, _t12))));
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsSegment.normal_degenerate(dest, destOffset, src, srcOffset);
        float _t33_inv = 1.0f / _t33;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t26 * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, (java.lang.Math.fma(_self02, _self21, _t14) - java.lang.Math.fma(_self01, _self22, _t14)) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, (java.lang.Math.fma(_self01, _self12, _t17) - java.lang.Math.fma(_self02, _self11, _t17)) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, (java.lang.Math.fma(_self12, _self20, _t13) - java.lang.Math.fma(_self10, _self22, _t13)) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, (java.lang.Math.fma(_self00, _self22, _t15) - java.lang.Math.fma(_self02, _self20, _t15)) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, (java.lang.Math.fma(_self02, _self10, _t18) - java.lang.Math.fma(_self00, _self12, _t18)) * _t33_inv);
        return normal_api_fma_s2c1f1020_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _t16, _t19, _t27, _t33_inv);
    }

    /** Piece 3 of {@code normal_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment normal_api_fma_s2c1f1020_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t16, float _t19, float _t27, float _t33_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t27 * _t33_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, (java.lang.Math.fma(_self01, _self20, _t16) - java.lang.Math.fma(_self00, _self21, _t16)) * _t33_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, (java.lang.Math.fma(_self00, _self11, _t19) - java.lang.Math.fma(_self01, _self10, _t19)) * _t33_inv);
        return dest;
    }

    /** {@code normal_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normal_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        return normal_api_mulAdd_sea5f4e7_1(dest, destOffset, src, srcOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self12 * _self20, -_self10 * _self22, -(_self01 * _self22), -_self02 * _self20, -_self00 * _self21, -_self02 * _self11, -_self00 * _self12, _self01 * -_self10, ((_self11) * (_self22) + (_t10)) - ((_self12) * (_self21) + (_t10)), ((_self10) * (_self21) + (_t11)) - ((_self11) * (_self20) + (_t11)));
    }

    /** Piece 2 of {@code normal_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment normal_api_mulAdd_sea5f4e7_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27) {
        float _t33 = ((_self02) * (_t27) + (((_self00) * (_t26) + (_self01 * (((_self12) * (_self20) + (_t12)) - ((_self10) * (_self22) + (_t12)))))));
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsSegment.normal_degenerate(dest, destOffset, src, srcOffset);
        float _t33_inv = 1.0f / _t33;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t26 * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, (((_self02) * (_self21) + (_t14)) - ((_self01) * (_self22) + (_t14))) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, (((_self01) * (_self12) + (_t17)) - ((_self02) * (_self11) + (_t17))) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, (((_self12) * (_self20) + (_t13)) - ((_self10) * (_self22) + (_t13))) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, (((_self00) * (_self22) + (_t15)) - ((_self02) * (_self20) + (_t15))) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, (((_self02) * (_self10) + (_t18)) - ((_self00) * (_self12) + (_t18))) * _t33_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t27 * _t33_inv);
        return normal_api_mulAdd_sea5f4e7_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _t16, _t19, _t33_inv);
    }

    /** Piece 3 of {@code normal_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment normal_api_mulAdd_sea5f4e7_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t16, float _t19, float _t33_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, (((_self01) * (_self20) + (_t16)) - ((_self00) * (_self21) + (_t16))) * _t33_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, (((_self00) * (_self11) + (_t19)) - ((_self01) * (_self10) + (_t19))) * _t33_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3x3OpsKernelsSegment.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return normal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment normal_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return normal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code normal_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normal_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t12 = _self11 * _t0;
        float _t13 = _self22 * _t1;
        float _t14 = _self12 * _t0;
        float _t15 = _self21 * _t1;
        float _t16 = _self10 * _t0;
        float _t17 = _self20 * _t1;
        float _t18 = _self02 * _t2;
        float _t20 = _self01 * _t2;
        return normal_degenerate_api_fma_s6c0a90b7_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _self00 * _t2, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t16 * _t13, -(_t20 * _t13), -_t18 * _t17);
    }

    /** Piece 2 of {@code normal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment normal_degenerate_api_fma_s6c0a90b7_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36) {
        float _t38 = -_t18 * _t12;
        float _t47 = java.lang.Math.fma(_t12, _t13, _t31) - java.lang.Math.fma(_t14, _t15, _t31);
        float _t48 = java.lang.Math.fma(_t16, _t15, _t32) - java.lang.Math.fma(_t12, _t17, _t32);
        float _t53_inv = 1.0f / java.lang.Math.fma(_t48, _t18, java.lang.Math.fma(_t47, _t19, (java.lang.Math.fma(_t14, _t17, _t33) - java.lang.Math.fma(_t16, _t13, _t33)) * _t20));
        float _sp2 = _t1 * _t53_inv;
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t47 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (java.lang.Math.fma(_t18, _t15, _t35) - java.lang.Math.fma(_t20, _t13, _t35)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (java.lang.Math.fma(_t20, _t14, _t38) - java.lang.Math.fma(_t18, _t12, _t38)) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, (java.lang.Math.fma(_t14, _t17, _t34) - java.lang.Math.fma(_t16, _t13, _t34)) * _sp0);
        return normal_degenerate_api_fma_s6c0a90b7_2(dest, destOffset, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t36, -_t19 * _t15, -_t19 * _t14, _t20 * -_t16, _t48, _sp2, _sp1, _sp0);
    }

    /** Piece 3 of {@code normal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment normal_degenerate_api_fma_s6c0a90b7_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t36, float _t37, float _t39, float _t40, float _t48, float _sp2, float _sp1, float _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, (java.lang.Math.fma(_t19, _t13, _t36) - java.lang.Math.fma(_t18, _t17, _t36)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, (java.lang.Math.fma(_t18, _t16, _t39) - java.lang.Math.fma(_t19, _t14, _t39)) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t48 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, (java.lang.Math.fma(_t20, _t17, _t37) - java.lang.Math.fma(_t19, _t15, _t37)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, (java.lang.Math.fma(_t19, _t12, _t40) - java.lang.Math.fma(_t20, _t16, _t40)) * _sp2);
        return dest;
    }

    /** {@code normal_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normal_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t12 = _self11 * _t0;
        float _t13 = _self22 * _t1;
        float _t14 = _self12 * _t0;
        float _t15 = _self21 * _t1;
        float _t16 = _self10 * _t0;
        float _t17 = _self20 * _t1;
        float _t18 = _self02 * _t2;
        float _t20 = _self01 * _t2;
        return normal_degenerate_api_mulAdd_sbc7973a6_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _self00 * _t2, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t16 * _t13, -(_t20 * _t13), -_t18 * _t17);
    }

    /** Piece 2 of {@code normal_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment normal_degenerate_api_mulAdd_sbc7973a6_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36) {
        float _t38 = -_t18 * _t12;
        float _t47 = ((_t12) * (_t13) + (_t31)) - ((_t14) * (_t15) + (_t31));
        float _t48 = ((_t16) * (_t15) + (_t32)) - ((_t12) * (_t17) + (_t32));
        float _t53_inv = 1.0f / ((_t48) * (_t18) + (((_t47) * (_t19) + ((((_t14) * (_t17) + (_t33)) - ((_t16) * (_t13) + (_t33))) * _t20))));
        float _sp2 = _t1 * _t53_inv;
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t47 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (((_t18) * (_t15) + (_t35)) - ((_t20) * (_t13) + (_t35))) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (((_t20) * (_t14) + (_t38)) - ((_t18) * (_t12) + (_t38))) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, (((_t14) * (_t17) + (_t34)) - ((_t16) * (_t13) + (_t34))) * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, (((_t19) * (_t13) + (_t36)) - ((_t18) * (_t17) + (_t36))) * _sp1);
        return normal_degenerate_api_mulAdd_sbc7973a6_2(dest, destOffset, _t12, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -_t19 * _t15, -_t19 * _t14, _t20 * -_t16, _t48, _sp2, _sp1, _sp0);
    }

    /** Piece 3 of {@code normal_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment normal_degenerate_api_mulAdd_sbc7973a6_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t12, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t37, float _t39, float _t40, float _t48, float _sp2, float _sp1, float _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, (((_t18) * (_t16) + (_t39)) - ((_t19) * (_t14) + (_t39))) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t48 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, (((_t20) * (_t17) + (_t37)) - ((_t19) * (_t15) + (_t37))) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, (((_t19) * (_t12) + (_t40)) - ((_t20) * (_t16) + (_t40))) * _sp2);
        return dest;
    }

    public static float trace_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3x3OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static float trace_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) + src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L));
    }

    public static java.lang.foreign.MemorySegment transpose_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transpose_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + _i * 4L) + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        for (int _i = 0; _i < 9; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, scalar * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _i = 0; _i < 9; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + _i * 4L));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Float3x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        for (int _i = 0; _i < 9; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + _i * 4L));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Float3x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _m10 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m01 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 8L);
        float _m11 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, m.get(_JAVA_FLOAT_UNALIGNED, mOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Float3x3OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _m10 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m01 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 8L);
        float _m11 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 12L);
        float _m02 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 16L);
        float _m12 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 20L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, m.get(_JAVA_FLOAT_UNALIGNED, mOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Float3x3OpsKernelsAddress.setMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _m01 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m02 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 8L);
        float _m10 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 16L);
        float _m11 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 20L);
        float _m12 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 24L);
        float _m20 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 32L);
        float _m21 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 36L);
        float _m22 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 40L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, m.get(_JAVA_FLOAT_UNALIGNED, mOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _m10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _m20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _m01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _m11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _m21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _m02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _m12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _m22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat4x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Float3x3OpsKernelsAddress.setMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat4x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            int _lom = _l * 4;
            float _em1 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + (_lom + 1) * 4L);
            float _em2 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + (_lom + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, m.get(_JAVA_FLOAT_UNALIGNED, mOffset + _lom * 4L));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, _em1);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _em2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float tX, float tY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float tX, float tY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, tX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, tY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _tBase = t.address() + tOffset;
        Float3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _tx = t.get(_JAVA_FLOAT_UNALIGNED, tOffset);
        float _ty = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _tx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _ty);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromRigid_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float rRX, float rRY, float rRZ, float rRW) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeFromRigid_unsafe(_destBase, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromRigid_api(java.lang.foreign.MemorySegment dest, long destOffset, float rRX, float rRY, float rRZ, float rRW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromRigid_api_mulAdd(dest, destOffset, rRX, rRY, rRZ, rRW);
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(rRY, rRY, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * java.lang.Math.fma(rRX, rRY, _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * java.lang.Math.fma(rRX, rRZ, -_t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 2.0f * java.lang.Math.fma(rRX, rRY, -_t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(rRX, rRX, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 2.0f * java.lang.Math.fma(rRX, rRW, rRY * rRZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * java.lang.Math.fma(rRX, rRZ, _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 2.0f * java.lang.Math.fma(rRY, rRZ, -(rRX * rRW)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(rRX, rRX, rRY * rRY), 1.0f));
        return dest;
    }

    /** {@code makeFromRigid_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeFromRigid_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float rRX, float rRY, float rRZ, float rRW) {
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((1.0f) - (2.0f) * (((rRY) * (rRY) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * ((rRX) * (rRY) + (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * ((rRX) * (rRZ) - (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 2.0f * ((rRX) * (rRY) - (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((1.0f) - (2.0f) * (((rRX) * (rRX) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 2.0f * ((rRX) * (rRW) + (rRY * rRZ)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * ((rRX) * (rRZ) + (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 2.0f * ((rRY) * (rRZ) - (rRX * rRW)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((1.0f) - (2.0f) * (((rRX) * (rRX) + (rRY * rRY)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromTransform_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeFromTransform_unsafe(_destBase, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromTransform_api(java.lang.foreign.MemorySegment dest, long destOffset, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeFromTransform_api_fma(dest, destOffset, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return makeFromTransform_api_mulAdd(dest, destOffset, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
    }

    /** {@code makeFromTransform_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeFromTransform_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-java.lang.Math.fma(tRY, tRY, _t3), _t0, tSX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(tRX, tRY, _t4) * _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(tRX, tRZ, -_t5) * _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(tRX, tRY, -_t4) * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-java.lang.Math.fma(tRX, tRX, _t3), _t1, tSY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(tRX, tRZ, _t5) * _t2);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(-java.lang.Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        return dest;
    }

    /** {@code makeFromTransform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeFromTransform_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((tSX) - ((tRY) * (tRY) + (_t3)) * (_t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((tRX) * (tRY) + (_t4)) * _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((tRX) * (tRZ) - (_t5)) * _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((tRX) * (tRY) - (_t4)) * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((tSY) - ((tRX) * (tRX) + (_t3)) * (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((tRX) * (tRW) + (tRY * tRZ)) * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((tRX) * (tRZ) + (_t5)) * _t2);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((tRY) * (tRZ) - (tRX * tRW)) * _t2);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((tSZ) - ((tRX) * (tRX) + (tRY * tRY)) * (_t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _loself = _l * 3;
            float _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (_loself + 1) * 4L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _loself * 4L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, _eself1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.to3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self20);
        return to3x4_api_s6bac1064_1(dest, destOffset, _self21, _self22);
    }

    /** Piece 2 of {@code to3x4_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment to3x4_api_s6bac1064_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self21, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 36L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 40L, _self22);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 44L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to4x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.to4x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to4x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 36L, _self12);
        return to4x4_api_s4e2d311f_1(dest, destOffset, _self22);
    }

    /** Piece 2 of {@code to4x4_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment to4x4_api_s4e2d311f_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self22) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 40L, _self22);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 44L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 48L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 52L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 56L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 60L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toDualQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toDualQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t1 = 1.0f - _self00;
        float _t3 = _self21 - _self12;
        float _t5 = _self01 + _self10;
        float _t6 = _self02 + _self20;
        float _t7 = _self02 - _self20;
        float _t8 = _self12 + _self21;
        float _t9 = _self10 - _self01;
        float _t13 = _self22 + (_self00 + _self11);
        float _t14 = 1.0f + _t13;
        float _t15 = _self00 + (1.0f - _self11 - _self22);
        float _t16 = _self11 + (_t1 - _self22);
        float _t17 = _self22 + (_t1 - _self11);
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t13 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t3);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t7);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t9);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t5);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t6);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t3);
            } else {
                if (_self11 > _self22) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t5);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t8);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t7);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t6);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t8);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t9);
                }
            }
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toRigid_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.toRigid_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toRigid_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_api_fma(dest, destOffset, src, srcOffset);
        return toRigid_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toRigid_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toRigid_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _ct0 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _t18 = _self10 * _t17;
        float _t19 = _self22 * _t16;
        float _t20 = _self12 * _t16;
        float _t21 = _self20 * _t17;
        float _t23 = _self21 * _t15;
        float _t24 = _self11 * _t15;
        float _t26 = _self00 * _t17;
        float _t31 = java.lang.Math.fma(_self12, _t16, _t23);
        float _t35 = java.lang.Math.fma(_self21, _t15, -_t20);
        float _t47, _t48, _t49;
        if (java.lang.Math.fma(-java.lang.Math.fma(_t18, _t19, -(_t20 * _t21)), _self01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t18, _t23, -(_t24 * _t21)), _self02 * _t16, java.lang.Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        float _t51 = 1.0f + _t47;
        float _t52 = 1.0f - _t47;
        float _t54 = java.lang.Math.fma(_self01, _t15, _t48);
        float _t55 = java.lang.Math.fma(_self02, _t16, _t49);
        float _t56 = java.lang.Math.fma(_self02, _t16, -_t49);
        float _t57 = java.lang.Math.fma(-_self01, _t15, _t48);
        float _t63 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t51));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63));
        float _t65 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        float _t66 = java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t52));
        float _t67 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t51));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        if (java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t47)) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp0 * _t35);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp0 * _t56);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp0 * _t57);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.5f * (float) java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t67));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp3 * _t54);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp3 * _t55);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t54);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp1 * _t31);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp1 * _t56);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t55);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp2 * _t31);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.5f * (float) java.lang.Math.sqrt(_t66));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp2 * _t57);
                }
            }
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        return dest;
    }

    /** {@code toRigid_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toRigid_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _ct0 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _t18 = _self10 * _t17;
        float _t19 = _self22 * _t16;
        float _t20 = _self12 * _t16;
        float _t21 = _self20 * _t17;
        float _t23 = _self21 * _t15;
        float _t24 = _self11 * _t15;
        float _t26 = _self00 * _t17;
        float _t31 = ((_self12) * (_t16) + (_t23));
        float _t35 = ((_self21) * (_t15) - (_t20));
        float _t47, _t48, _t49;
        if (((((((_t18) * (_t23) - (_t24 * _t21))) * (_self02 * _t16) + (((_t24) * (_t19) - (_t20 * _t23)) * _t26))) - ((_t18) * (_t19) - (_t20 * _t21)) * (_self01 * _t15)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        float _t51 = 1.0f + _t47;
        float _t52 = 1.0f - _t47;
        float _t54 = ((_self01) * (_t15) + (_t48));
        float _t55 = ((_self02) * (_t16) + (_t49));
        float _t56 = ((_self02) * (_t16) - (_t49));
        float _t57 = ((_t48) - (_self01) * (_t15));
        float _t63 = ((_self11) * (_t15) + (((_self22) * (_t16) + (_t51))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63));
        float _t65 = ((_self11) * (_t15) + (((_t1) * (_t16) + (_t52))));
        float _t66 = ((_self22) * (_t16) + (((_t0) * (_t15) + (_t52))));
        float _t67 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t51))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        if (((_self11) * (_t15) + (((_self22) * (_t16) + (_t47)))) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp0 * _t35);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp0 * _t56);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp0 * _t57);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.5f * (float) java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t67));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp3 * _t54);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp3 * _t55);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t54);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp1 * _t31);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp1 * _t56);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t55);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp2 * _t31);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.5f * (float) java.lang.Math.sqrt(_t66));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp2 * _t57);
                }
            }
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toRigid_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3x3OpsKernelsSegment.toRigid_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toRigid_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment toRigid_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.toRigid_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toRigid_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toRigid_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toRigid_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toRigid_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = unitScale(_self01, _self11, _self21);
        float _t1 = unitScale(_self02, _self12, _self22);
        float _t2 = unitScale(_self00, _self10, _self20);
        float _t12 = _self21 * _t0;
        float _t13 = _self01 * _t0;
        float _t14 = _self11 * _t0;
        float _t15 = _self22 * _t1;
        float _t16 = _self02 * _t1;
        float _t17 = _self12 * _t1;
        float _t18 = _self20 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self10 * _t2;
        float _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
        float _t33 = _t30 * _t18;
        float _t34 = _t30 * _t19;
        float _t35 = _t30 * _t20;
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t89, _t89, _t74 * _t74))));
        float _t102 = _t99 * _t72;
        float _t103 = _t100 * _t73;
        float _t104 = _t101 * _t74;
        float _t105 = _t100 * _t76;
        float _t106 = _t99 * _t75;
        float _t107 = _t101 * _t77;
        float _t114 = _t100 * _t88;
        float _t115 = _t101 * _t89;
        float _t116 = _t99 * _t87;
        float _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t167 = 1.0f;
                    _t170 = 0.0f;
                    _t166 = 0.0f;
                    _t168 = 0.0f;
                    _t171 = 1.0f;
                    _t169 = 0.0f;
                    _t172 = 0.0f;
                    _t173 = 1.0f;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = java.lang.Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = java.lang.Math.fma(_t35, _t106, -_t33 * _t116);
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = java.lang.Math.fma(_t36, _t105, -_t37 * _t114);
                    _t167 = java.lang.Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = java.lang.Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = java.lang.Math.fma(_t33, _t36, _t35 * -_t37);
                    _t167 = java.lang.Math.fma(_t34, _t37, -_t33 * _t38);
                    _t170 = java.lang.Math.fma(_t35, _t38, -(_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = java.lang.Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = java.lang.Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = java.lang.Math.fma(_t41, _t107, -_t40 * _t115);
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = java.lang.Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = java.lang.Math.fma(_t35, _t40, -_t33 * _t41);
                    _t171 = java.lang.Math.fma(_t34, _t41, -(_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = java.lang.Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = java.lang.Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = java.lang.Math.fma(_t41, _t37, -_t40 * _t36);
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
        float _t194, _t195, _t196;
        if (java.lang.Math.fma(java.lang.Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, java.lang.Math.fma(java.lang.Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, java.lang.Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0f) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        float _t199 = _t195 + _t165;
        float _t200 = _t196 + _t168;
        float _t201 = _t168 - _t196;
        float _t202 = _t195 - _t165;
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208));
        if (_t206 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp0 * _t182);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp0 * _t201);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp0 * _t202);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.5f * (float) java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t208));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp3 * _t199);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp3 * _t200);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t199);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.5f * (float) java.lang.Math.sqrt(_t209));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp1 * _t184);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp1 * _t201);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t200);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp2 * _t184);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.5f * (float) java.lang.Math.sqrt(_t210));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp2 * _t202);
                }
            }
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        return dest;
    }

    /** {@code toRigid_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toRigid_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = unitScale(_self01, _self11, _self21);
        float _t1 = unitScale(_self02, _self12, _self22);
        float _t2 = unitScale(_self00, _self10, _self20);
        float _t12 = _self21 * _t0;
        float _t13 = _self01 * _t0;
        float _t14 = _self11 * _t0;
        float _t15 = _self22 * _t1;
        float _t16 = _self02 * _t1;
        float _t17 = _self12 * _t1;
        float _t18 = _self20 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self10 * _t2;
        float _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        float _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
        float _t33 = _t30 * _t18;
        float _t34 = _t30 * _t19;
        float _t35 = _t30 * _t20;
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(((_t75) * (_t75) + (((_t87) * (_t87) + (_t72 * _t72))))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(((_t76) * (_t76) + (((_t88) * (_t88) + (_t73 * _t73))))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(((_t77) * (_t77) + (((_t89) * (_t89) + (_t74 * _t74))))));
        float _t102 = _t99 * _t72;
        float _t103 = _t100 * _t73;
        float _t104 = _t101 * _t74;
        float _t105 = _t100 * _t76;
        float _t106 = _t99 * _t75;
        float _t107 = _t101 * _t77;
        float _t114 = _t100 * _t88;
        float _t115 = _t101 * _t89;
        float _t116 = _t99 * _t87;
        float _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t167 = 1.0f;
                    _t170 = 0.0f;
                    _t166 = 0.0f;
                    _t168 = 0.0f;
                    _t171 = 1.0f;
                    _t169 = 0.0f;
                    _t172 = 0.0f;
                    _t173 = 1.0f;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = ((_t33) * (_t102) - (_t34 * _t106));
                    _t168 = ((_t35) * (_t106) - (_t33 * _t116));
                    _t171 = ((_t34) * (_t116) - (_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = ((_t36) * (_t105) - (_t37 * _t114));
                    _t167 = ((_t37) * (_t103) - (_t38 * _t105));
                    _t170 = ((_t38) * (_t114) - (_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = ((_t33) * (_t36) - (_t35 * _t37));
                    _t167 = ((_t34) * (_t37) - (_t33 * _t38));
                    _t170 = ((_t35) * (_t38) - (_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = ((_t39) * (_t115) - (_t41 * _t104));
                    _t172 = ((_t40) * (_t104) - (_t39 * _t107));
                    _t173 = ((_t41) * (_t107) - (_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = ((_t33) * (_t39) - (_t34 * _t40));
                    _t168 = ((_t35) * (_t40) - (_t33 * _t41));
                    _t171 = ((_t34) * (_t41) - (_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = ((_t39) * (_t36) - (_t41 * _t38));
                    _t172 = ((_t40) * (_t38) - (_t39 * _t37));
                    _t173 = ((_t41) * (_t37) - (_t40 * _t36));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
        float _t194, _t195, _t196;
        if (((((_t165) * (_t166) - (_t167 * _t168))) * (_t169) + (((((_t170) * (_t168) - (_t165 * _t171))) * (_t172) + (((_t167) * (_t171) - (_t170 * _t166)) * _t173)))) < 0.0f) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        float _t199 = _t195 + _t165;
        float _t200 = _t196 + _t168;
        float _t201 = _t168 - _t196;
        float _t202 = _t195 - _t165;
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208));
        if (_t206 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp0 * _t182);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp0 * _t201);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp0 * _t202);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.5f * (float) java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t208));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp3 * _t199);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp3 * _t200);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t199);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.5f * (float) java.lang.Math.sqrt(_t209));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp1 * _t184);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp1 * _t201);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t200);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp2 * _t184);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.5f * (float) java.lang.Math.sqrt(_t210));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _sp2 * _t202);
                }
            }
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toTransform_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.toTransform_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toTransform_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_api_fma(dest, destOffset, src, srcOffset);
        return toTransform_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toTransform_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t12 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toTransform_degenerate(dest, destOffset, src, srcOffset);
        return toTransform_api_fma_s3f983bc6_7(dest, destOffset, src, srcOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10)));
    }

    /** Part 1 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static float toTransform_api_fma_s3f983bc6_1(float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t14) {
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t17 = 1.0f / ((float) java.lang.Math.sqrt(_t14));
        float _t19 = _self10 * _t17;
        float _t20 = _self22 * _t16;
        float _t21 = _self12 * _t16;
        float _t22 = _self20 * _t17;
        float _t24 = _self21 * _t15;
        float _t25 = _self11 * _t15;
        return java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _self01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _self02 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * (_self00 * _t17)));
    }

    /** Part 2 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static void toTransform_api_fma_s3f983bc6_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t48, float _t49, float _t50) {
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t20 = _self22 * _t16;
        float _t25 = _self11 * _t15;
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, (java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t48))) > 0.0f ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t52)))))) * (java.lang.Math.fma(_self21, _t15, -(_self12 * _t16))) : _t48 > (Math.max(_t25, _t20)) ? 0.5f * (float) java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))) : _t25 > _t20 ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_self01, _t15, _t49)) : (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(_self02, _t16, _t50)));
    }

    /** Part 3 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static void toTransform_api_fma_s3f983bc6_3(java.lang.foreign.MemorySegment dest, long destOffset, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t48, float _t50, float _t49) {
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t20 = _self22 * _t16;
        float _t25 = _self11 * _t15;
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, (java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t48))) > 0.0f ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t52)))))) * (java.lang.Math.fma(_self02, _t16, -_t50)) : _t48 > (Math.max(_t25, _t20)) ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))))) * (java.lang.Math.fma(_self01, _t15, _t49)) : _t25 > _t20 ? 0.5f * (float) java.lang.Math.sqrt((java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t53)))) : (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(_self12, _t16, (_self21 * _t15))));
    }

    /** Part 4 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static void toTransform_api_fma_s3f983bc6_4(java.lang.foreign.MemorySegment dest, long destOffset, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t48, float _t49, float _t50) {
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t20 = _self22 * _t16;
        float _t25 = _self11 * _t15;
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, (java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t48))) > 0.0f ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t52)))))) * (java.lang.Math.fma(-_self01, _t15, _t49)) : _t48 > (Math.max(_t25, _t20)) ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))))) * (java.lang.Math.fma(_self02, _t16, _t50)) : _t25 > _t20 ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_self12, _t16, (_self21 * _t15))) : 0.5f * (float) java.lang.Math.sqrt((java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t53)))));
    }

    /** Part 5 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static void toTransform_api_fma_s3f983bc6_5(java.lang.foreign.MemorySegment dest, long destOffset, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t48, float _t50, float _t49) {
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t20 = _self22 * _t16;
        float _t25 = _self11 * _t15;
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, (java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t48))) > 0.0f ? 0.5f * (float) java.lang.Math.sqrt((java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t52)))) : _t48 > (Math.max(_t25, _t20)) ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52)))))) * (java.lang.Math.fma(_self21, _t15, -(_self12 * _t16))) : _t25 > _t20 ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t53)))))) * (java.lang.Math.fma(_self02, _t16, -_t50)) : (0.5f * (1.0f / (float) java.lang.Math.sqrt((java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t53)))))) * (java.lang.Math.fma(-_self01, _t15, _t49)));
    }

    /** Part 6 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_api_fma_s3f983bc6_6(java.lang.foreign.MemorySegment dest, long destOffset, float _t12, float _t13, float _t14, float _t47) {
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t47 < 0.0f ? -_t18 : _t18);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, (float) java.lang.Math.sqrt(_t12));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 36L, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** Piece 2 of {@code toTransform_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_api_fma_s3f983bc6_7(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t14) {
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t17 = 1.0f / ((float) java.lang.Math.sqrt(_t14));
        float _t19 = _self10 * _t17;
        float _t22 = _self20 * _t17;
        float _t27 = _self00 * _t17;
        float _t47 = toTransform_api_fma_s3f983bc6_1(_self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t14);
        float _t48, _t49, _t50;
        if (_t47 < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        toTransform_api_fma_s3f983bc6_2(dest, destOffset, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t48, _t49, _t50);
        toTransform_api_fma_s3f983bc6_3(dest, destOffset, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t48, _t50, _t49);
        toTransform_api_fma_s3f983bc6_4(dest, destOffset, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t48, _t49, _t50);
        toTransform_api_fma_s3f983bc6_5(dest, destOffset, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t48, _t50, _t49);
        return toTransform_api_fma_s3f983bc6_6(dest, destOffset, _t12, _t13, _t14, _t47);
    }

    /** {@code toTransform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t12 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toTransform_degenerate(dest, destOffset, src, srcOffset);
        return toTransform_api_mulAdd_s560b0485_1(dest, destOffset, src, srcOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10)))));
    }

    /** Piece 2 of {@code toTransform_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_api_mulAdd_s560b0485_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t0, float _t1, float _t12, float _t13, float _t14) {
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsSegment.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _t19 = _self10 * _t17;
        float _t20 = _self22 * _t16;
        float _t21 = _self12 * _t16;
        float _t22 = _self20 * _t17;
        float _t24 = _self21 * _t15;
        float _t25 = _self11 * _t15;
        float _t27 = _self00 * _t17;
        float _t47 = ((((((_t19) * (_t24) - (_t25 * _t22))) * (_self02 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))) - ((_t19) * (_t20) - (_t21 * _t22)) * (_self01 * _t15));
        float _t48, _t49, _t50;
        if (_t47 < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        return toTransform_api_mulAdd_s560b0485_2(dest, destOffset, _self11, _self22, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, ((_self12) * (_t16) + (_t24)), ((_self21) * (_t15) - (_t21)), Math.max(_t25, _t20), _t47, _t48, 1.0f + _t48, 1.0f - _t48, ((_self01) * (_t15) + (_t49)), ((_self02) * (_t16) + (_t50)), ((_self02) * (_t16) - (_t50)), ((_t49) - (_self01) * (_t15)));
    }

    /** Piece 3 of {@code toTransform_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_api_mulAdd_s560b0485_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self11, float _self22, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t52, float _t53, float _t55, float _t56, float _t57, float _t58) {
        float _t63 = ((_self11) * (_t15) + (((_self22) * (_t16) + (_t48))));
        float _t64 = ((_self11) * (_t15) + (((_self22) * (_t16) + (_t52))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64));
        float _t66 = ((_self11) * (_t15) + (((_t1) * (_t16) + (_t53))));
        float _t67 = ((_self22) * (_t16) + (((_t0) * (_t15) + (_t53))));
        float _t68 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        return toTransform_api_mulAdd_s560b0485_3(dest, destOffset, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t55, _t56, _t57, _t58, _t63, _t64, _sp0, _t66, _t67, _sp1, _sp2, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68)));
    }

    /** Piece 4 of {@code toTransform_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_api_mulAdd_s560b0485_3(java.lang.foreign.MemorySegment dest, long destOffset, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _sp1, float _sp2, float _sp3) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) java.lang.Math.sqrt(_t67));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t63 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t47 < 0.0f ? -_t18 : _t18);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, (float) java.lang.Math.sqrt(_t12));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 36L, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    public static java.lang.foreign.MemorySegment toTransform_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3x3OpsKernelsSegment.toTransform_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toTransform_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment toTransform_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.toTransform_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toTransform_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return toTransform_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toTransform_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = unitScale(_self01, _self11, _self21);
        float _t1 = unitScale(_self02, _self12, _self22);
        float _t2 = unitScale(_self00, _self10, _self20);
        float _t12 = _self21 * _t0;
        float _t13 = _self01 * _t0;
        float _t14 = _self11 * _t0;
        float _t15 = _self22 * _t1;
        float _t16 = _self02 * _t1;
        float _t17 = _self12 * _t1;
        float _t18 = _self20 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self10 * _t2;
        float _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
        float _t105 = _t102 * _t75;
        float _t106 = _t103 * _t76;
        float _t107 = _t104 * _t77;
        float _t108 = _t103 * _t79;
        float _t109 = _t102 * _t78;
        float _t110 = _t104 * _t80;
        float _t117 = _t103 * _t91;
        float _t118 = _t104 * _t92;
        float _t119 = _t102 * _t90;
        float _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t170 = 1.0f;
                    _t173 = 0.0f;
                    _t169 = 0.0f;
                    _t171 = 0.0f;
                    _t174 = 1.0f;
                    _t172 = 0.0f;
                    _t175 = 0.0f;
                    _t176 = 1.0f;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = java.lang.Math.fma(_t38, _t108, -_t39 * _t117);
                    _t170 = java.lang.Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = java.lang.Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = java.lang.Math.fma(_t35, _t38, _t37 * -_t39);
                    _t170 = java.lang.Math.fma(_t36, _t39, -_t35 * _t40);
                    _t173 = java.lang.Math.fma(_t37, _t40, -(_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = java.lang.Math.fma(_t41, _t118, -(_t43 * _t107));
                    _t175 = java.lang.Math.fma(_t42, _t107, -(_t41 * _t110));
                    _t176 = java.lang.Math.fma(_t43, _t110, -_t42 * _t118);
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = java.lang.Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = java.lang.Math.fma(_t37, _t42, -_t35 * _t43);
                    _t174 = java.lang.Math.fma(_t36, _t43, -(_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = java.lang.Math.fma(_t41, _t38, -(_t43 * _t40));
                    _t175 = java.lang.Math.fma(_t42, _t40, -(_t41 * _t39));
                    _t176 = java.lang.Math.fma(_t43, _t39, -_t42 * _t38);
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        float _t185 = _t173 - _t169;
        float _t186 = Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = java.lang.Math.fma(java.lang.Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, java.lang.Math.fma(java.lang.Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, java.lang.Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        float _t197, _t198, _t199;
        if (_t196 < 0.0f) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        float _t202 = _t198 + _t168;
        float _t203 = _t199 + _t171;
        float _t204 = _t171 - _t199;
        float _t205 = _t198 - _t168;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t196 < 0.0f ? -_t56 : _t56);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 36L, _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    /** {@code toTransform_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = unitScale(_self01, _self11, _self21);
        float _t1 = unitScale(_self02, _self12, _self22);
        float _t2 = unitScale(_self00, _self10, _self20);
        float _t12 = _self21 * _t0;
        float _t13 = _self01 * _t0;
        float _t14 = _self11 * _t0;
        float _t15 = _self22 * _t1;
        float _t16 = _self02 * _t1;
        float _t17 = _self12 * _t1;
        float _t18 = _self20 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self10 * _t2;
        float _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        float _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t27));
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
        float _t105 = _t102 * _t75;
        float _t106 = _t103 * _t76;
        float _t107 = _t104 * _t77;
        float _t108 = _t103 * _t79;
        float _t109 = _t102 * _t78;
        float _t110 = _t104 * _t80;
        float _t117 = _t103 * _t91;
        float _t118 = _t104 * _t92;
        float _t119 = _t102 * _t90;
        float _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t170 = 1.0f;
                    _t173 = 0.0f;
                    _t169 = 0.0f;
                    _t171 = 0.0f;
                    _t174 = 1.0f;
                    _t172 = 0.0f;
                    _t175 = 0.0f;
                    _t176 = 1.0f;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                    _t174 = ((_t36) * (_t119) - (_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = ((_t38) * (_t108) - (_t39 * _t117));
                    _t170 = ((_t39) * (_t106) - (_t40 * _t108));
                    _t173 = ((_t40) * (_t117) - (_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = ((_t35) * (_t38) - (_t37 * _t39));
                    _t170 = ((_t36) * (_t39) - (_t35 * _t40));
                    _t173 = ((_t37) * (_t40) - (_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = ((_t41) * (_t118) - (_t43 * _t107));
                    _t175 = ((_t42) * (_t107) - (_t41 * _t110));
                    _t176 = ((_t43) * (_t110) - (_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = ((_t35) * (_t41) - (_t36 * _t42));
                    _t171 = ((_t37) * (_t42) - (_t35 * _t43));
                    _t174 = ((_t36) * (_t43) - (_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = ((_t41) * (_t38) - (_t43 * _t40));
                    _t175 = ((_t42) * (_t40) - (_t41 * _t39));
                    _t176 = ((_t43) * (_t39) - (_t42 * _t38));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        float _t185 = _t173 - _t169;
        float _t186 = Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = ((((_t168) * (_t169) - (_t170 * _t171))) * (_t172) + (((((_t173) * (_t171) - (_t168 * _t174))) * (_t175) + (((_t170) * (_t174) - (_t173 * _t169)) * _t176))));
        float _t197, _t198, _t199;
        if (_t196 < 0.0f) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        float _t202 = _t198 + _t168;
        float _t203 = _t199 + _t171;
        float _t204 = _t171 - _t199;
        float _t205 = _t198 - _t168;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t196 < 0.0f ? -_t56 : _t56);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 36L, _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.decomposeRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeRotation_api_fma(dest, destOffset, src, srcOffset);
        return decomposeRotation_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code decomposeRotation_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeRotation_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t19 = -java.lang.Math.fma(_self22, _t7, java.lang.Math.fma(_self02, _t8, _self12 * _t9));
        float _t20 = -java.lang.Math.fma(_self21, _t7, java.lang.Math.fma(_self01, _t8, _self11 * _t9));
        float _t30 = -java.lang.Math.fma(java.lang.Math.fma(_t20, _t7, _self21), _t7, java.lang.Math.fma(java.lang.Math.fma(_t20, _t8, _self01), _t8, java.lang.Math.fma(_t20, _t9, _self11) * _t9));
        float _t34 = java.lang.Math.fma(_t20, _t7, java.lang.Math.fma(_t30, _t7, _self21));
        float _t35 = java.lang.Math.fma(_t20, _t8, java.lang.Math.fma(_t30, _t8, _self01));
        float _t36 = java.lang.Math.fma(_t20, _t9, java.lang.Math.fma(_t30, _t9, _self11));
        float _t39 = java.lang.Math.fma(_t34, _t34, java.lang.Math.fma(_t35, _t35, _t36 * _t36));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t39));
        float _t44, _t45, _t46;
        if (_t39 != 0.0f) {
            _t44 = _t34 * _t40;
            _t45 = _t35 * _t40;
            _t46 = _t36 * _t40;
        } else {
            _t44 = 0.0f;
            _t45 = 0.0f;
            _t46 = 0.0f;
        }
        float _t60 = java.lang.Math.fma(_t8, _t46, -(_t9 * _t45));
        float _t61 = java.lang.Math.fma(_t9, _t44, -(_t7 * _t46));
        float _t62 = java.lang.Math.fma(_t7, _t45, -(_t8 * _t44));
        float _t68 = -java.lang.Math.fma(java.lang.Math.fma(_t19, _t7, _self22), _t44, java.lang.Math.fma(java.lang.Math.fma(_t19, _t8, _self02), _t45, java.lang.Math.fma(_t19, _t9, _self12) * _t46));
        float _t78, _t79, _t80, _t84, _t85, _t86;
        if (java.lang.Math.fma(java.lang.Math.fma(_t19, _t7, java.lang.Math.fma(_t68, _t44, _self22)), _t60, java.lang.Math.fma(java.lang.Math.fma(_t19, _t8, java.lang.Math.fma(_t68, _t45, _self02)), _t61, java.lang.Math.fma(_t19, _t9, java.lang.Math.fma(_t68, _t46, _self12)) * _t62)) < 0.0f) {
            _t78 = -_t8;
            _t79 = -_t9;
            _t80 = -_t7;
            _t84 = java.lang.Math.fma(_t9, _t45, -_t8 * _t46);
            _t85 = java.lang.Math.fma(_t8, _t44, -_t7 * _t45);
            _t86 = java.lang.Math.fma(_t7, _t46, -_t9 * _t44);
        } else {
            _t78 = _t8;
            _t79 = _t9;
            _t80 = _t7;
            _t84 = _t60;
            _t85 = _t62;
            _t86 = _t61;
        }
        float _t81 = _t78 + _t46;
        float _t82 = _t79 + _t45;
        float _t83 = _t79 - _t45;
        float _t87 = _t44 - _t85;
        float _t90 = _t85 + _t44;
        float _t92 = _t80 + _t86;
        float _t93 = _t86 - _t80;
        float _t94 = _t81 + _t84;
        float _t98 = 1.0f + _t94;
        float _t99 = 1.0f + (_t78 - (_t84 + _t46));
        float _t100 = 1.0f + (_t46 - (_t78 + _t84));
        float _t101 = 1.0f + (_t84 - _t81);
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t101));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99));
        if (_t94 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t87);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t93);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t83);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t98));
        } else {
            if (_t78 > Math.max(_t46, _t84)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t99));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t82);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t92);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t87);
            } else {
                if (_t46 > _t84) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t82);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t100));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t90);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t93);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t92);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t90);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t101));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t83);
                }
            }
        }
        return dest;
    }

    /** {@code decomposeRotation_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeRotation_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t19 = -((_self22) * (_t7) + (((_self02) * (_t8) + (_self12 * _t9))));
        float _t20 = -((_self21) * (_t7) + (((_self01) * (_t8) + (_self11 * _t9))));
        float _t30 = -((((_t20) * (_t7) + (_self21))) * (_t7) + (((((_t20) * (_t8) + (_self01))) * (_t8) + (((_t20) * (_t9) + (_self11)) * _t9))));
        float _t34 = ((_t20) * (_t7) + (((_t30) * (_t7) + (_self21))));
        float _t35 = ((_t20) * (_t8) + (((_t30) * (_t8) + (_self01))));
        float _t36 = ((_t20) * (_t9) + (((_t30) * (_t9) + (_self11))));
        float _t39 = ((_t34) * (_t34) + (((_t35) * (_t35) + (_t36 * _t36))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t39));
        float _t44, _t45, _t46;
        if (_t39 != 0.0f) {
            _t44 = _t34 * _t40;
            _t45 = _t35 * _t40;
            _t46 = _t36 * _t40;
        } else {
            _t44 = 0.0f;
            _t45 = 0.0f;
            _t46 = 0.0f;
        }
        float _t60 = ((_t8) * (_t46) - (_t9 * _t45));
        float _t61 = ((_t9) * (_t44) - (_t7 * _t46));
        float _t62 = ((_t7) * (_t45) - (_t8 * _t44));
        float _t68 = -((((_t19) * (_t7) + (_self22))) * (_t44) + (((((_t19) * (_t8) + (_self02))) * (_t45) + (((_t19) * (_t9) + (_self12)) * _t46))));
        float _t78, _t79, _t80, _t84, _t85, _t86;
        if (((((_t19) * (_t7) + (((_t68) * (_t44) + (_self22))))) * (_t60) + (((((_t19) * (_t8) + (((_t68) * (_t45) + (_self02))))) * (_t61) + (((_t19) * (_t9) + (((_t68) * (_t46) + (_self12)))) * _t62)))) < 0.0f) {
            _t78 = -_t8;
            _t79 = -_t9;
            _t80 = -_t7;
            _t84 = ((_t9) * (_t45) - (_t8 * _t46));
            _t85 = ((_t8) * (_t44) - (_t7 * _t45));
            _t86 = ((_t7) * (_t46) - (_t9 * _t44));
        } else {
            _t78 = _t8;
            _t79 = _t9;
            _t80 = _t7;
            _t84 = _t60;
            _t85 = _t62;
            _t86 = _t61;
        }
        float _t81 = _t78 + _t46;
        float _t82 = _t79 + _t45;
        float _t83 = _t79 - _t45;
        float _t87 = _t44 - _t85;
        float _t90 = _t85 + _t44;
        float _t92 = _t80 + _t86;
        float _t93 = _t86 - _t80;
        float _t94 = _t81 + _t84;
        float _t98 = 1.0f + _t94;
        float _t99 = 1.0f + (_t78 - (_t84 + _t46));
        float _t100 = 1.0f + (_t46 - (_t78 + _t84));
        float _t101 = 1.0f + (_t84 - _t81);
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t101));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99));
        if (_t94 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t87);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t93);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t83);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t98));
        } else {
            if (_t78 > Math.max(_t46, _t84)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t99));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t82);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t92);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t87);
            } else {
                if (_t46 > _t84) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t82);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t100));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t90);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t93);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t92);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t90);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t101));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t83);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.decomposeScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeScale_api_fma(dest, destOffset, src, srcOffset);
        return decomposeScale_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code decomposeScale_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeScale_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        float _t4 = (float) java.lang.Math.sqrt(_t2);
        float _t3 = 1.0f / _t4;
        float _t8, _t9, _t10;
        if (_t2 != 0.0f) {
            _t8 = _self20 * _t3;
            _t9 = _self00 * _t3;
            _t10 = _self10 * _t3;
        } else {
            _t8 = 0.0f;
            _t9 = 0.0f;
            _t10 = 0.0f;
        }
        return decomposeScale_api_fma_scc58aeb6_1(dest, destOffset, _self01, _self11, _self21, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -java.lang.Math.fma(_self22, _t8, java.lang.Math.fma(_self02, _t9, _self12 * _t10)), -java.lang.Math.fma(_self21, _t8, java.lang.Math.fma(_self01, _t9, _self11 * _t10)));
    }

    /** Piece 2 of {@code decomposeScale_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeScale_api_fma_scc58aeb6_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t4, float _t8, float _t9, float _t10, float _t17, float _t18) {
        float _t28 = -java.lang.Math.fma(java.lang.Math.fma(_t18, _t8, _self21), _t8, java.lang.Math.fma(java.lang.Math.fma(_t18, _t9, _self01), _t9, java.lang.Math.fma(_t18, _t10, _self11) * _t10));
        float _t32 = java.lang.Math.fma(_t18, _t8, java.lang.Math.fma(_t28, _t8, _self21));
        float _t33 = java.lang.Math.fma(_t18, _t9, java.lang.Math.fma(_t28, _t9, _self01));
        float _t34 = java.lang.Math.fma(_t18, _t10, java.lang.Math.fma(_t28, _t10, _self11));
        float _t37 = java.lang.Math.fma(_t32, _t32, java.lang.Math.fma(_t33, _t33, _t34 * _t34));
        float _t38 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t42, _t43, _t44;
        if (_t37 != 0.0f) {
            _t42 = _t32 * _t38;
            _t43 = _t33 * _t38;
            _t44 = _t34 * _t38;
        } else {
            _t42 = 0.0f;
            _t43 = 0.0f;
            _t44 = 0.0f;
        }
        float _t48 = -java.lang.Math.fma(java.lang.Math.fma(_t17, _t8, _self22), _t42, java.lang.Math.fma(java.lang.Math.fma(_t17, _t9, _self02), _t43, java.lang.Math.fma(_t17, _t10, _self12) * _t44));
        return decomposeScale_api_fma_scc58aeb6_2(dest, destOffset, _t4, _t8, _t9, _t10, _t37, _t42, _t43, _t44, java.lang.Math.fma(_t17, _t8, java.lang.Math.fma(_t48, _t42, _self22)), java.lang.Math.fma(_t17, _t9, java.lang.Math.fma(_t48, _t43, _self02)), java.lang.Math.fma(_t17, _t10, java.lang.Math.fma(_t48, _t44, _self12)));
    }

    /** Piece 3 of {@code decomposeScale_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeScale_api_fma_scc58aeb6_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t4, float _t8, float _t9, float _t10, float _t37, float _t42, float _t43, float _t44, float _t52, float _t53, float _t54) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t52, java.lang.Math.fma(_t9, _t44, -(_t10 * _t43)), java.lang.Math.fma(_t53, java.lang.Math.fma(_t10, _t42, -(_t8 * _t44)), _t54 * java.lang.Math.fma(_t8, _t43, -(_t9 * _t42)))) < 0.0f ? -_t4 : _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (float) java.lang.Math.sqrt(_t37));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t52, _t52, java.lang.Math.fma(_t53, _t53, _t54 * _t54))));
        return dest;
    }

    /** {@code decomposeScale_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeScale_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        float _t4 = (float) java.lang.Math.sqrt(_t2);
        float _t3 = 1.0f / _t4;
        float _t8, _t9, _t10;
        if (_t2 != 0.0f) {
            _t8 = _self20 * _t3;
            _t9 = _self00 * _t3;
            _t10 = _self10 * _t3;
        } else {
            _t8 = 0.0f;
            _t9 = 0.0f;
            _t10 = 0.0f;
        }
        return decomposeScale_api_mulAdd_sbd9016b5_1(dest, destOffset, _self01, _self11, _self21, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -((_self22) * (_t8) + (((_self02) * (_t9) + (_self12 * _t10)))), -((_self21) * (_t8) + (((_self01) * (_t9) + (_self11 * _t10)))));
    }

    /** Piece 2 of {@code decomposeScale_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeScale_api_mulAdd_sbd9016b5_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t4, float _t8, float _t9, float _t10, float _t17, float _t18) {
        float _t28 = -((((_t18) * (_t8) + (_self21))) * (_t8) + (((((_t18) * (_t9) + (_self01))) * (_t9) + (((_t18) * (_t10) + (_self11)) * _t10))));
        float _t32 = ((_t18) * (_t8) + (((_t28) * (_t8) + (_self21))));
        float _t33 = ((_t18) * (_t9) + (((_t28) * (_t9) + (_self01))));
        float _t34 = ((_t18) * (_t10) + (((_t28) * (_t10) + (_self11))));
        float _t37 = ((_t32) * (_t32) + (((_t33) * (_t33) + (_t34 * _t34))));
        float _t38 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t42, _t43, _t44;
        if (_t37 != 0.0f) {
            _t42 = _t32 * _t38;
            _t43 = _t33 * _t38;
            _t44 = _t34 * _t38;
        } else {
            _t42 = 0.0f;
            _t43 = 0.0f;
            _t44 = 0.0f;
        }
        float _t48 = -((((_t17) * (_t8) + (_self22))) * (_t42) + (((((_t17) * (_t9) + (_self02))) * (_t43) + (((_t17) * (_t10) + (_self12)) * _t44))));
        return decomposeScale_api_mulAdd_sbd9016b5_2(dest, destOffset, _t4, _t8, _t9, _t10, _t37, _t42, _t43, _t44, ((_t17) * (_t8) + (((_t48) * (_t42) + (_self22)))), ((_t17) * (_t9) + (((_t48) * (_t43) + (_self02)))), ((_t17) * (_t10) + (((_t48) * (_t44) + (_self12)))));
    }

    /** Piece 3 of {@code decomposeScale_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeScale_api_mulAdd_sbd9016b5_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t4, float _t8, float _t9, float _t10, float _t37, float _t42, float _t43, float _t44, float _t52, float _t53, float _t54) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t52) * (((_t9) * (_t44) - (_t10 * _t43))) + (((_t53) * (((_t10) * (_t42) - (_t8 * _t44))) + (_t54 * ((_t8) * (_t43) - (_t9 * _t42)))))) < 0.0f ? -_t4 : _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (float) java.lang.Math.sqrt(_t37));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (float) java.lang.Math.sqrt(((_t52) * (_t52) + (((_t53) * (_t53) + (_t54 * _t54))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeSkew_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.decomposeSkew_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeSkew_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSkew_api_fma(dest, destOffset, src, srcOffset);
        return decomposeSkew_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code decomposeSkew_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSkew_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t14 = java.lang.Math.fma(_self22, _t7, java.lang.Math.fma(_self02, _t8, _self12 * _t9));
        float _t15 = java.lang.Math.fma(_self21, _t7, java.lang.Math.fma(_self01, _t8, _self11 * _t9));
        return decomposeSkew_api_fma_sf3d9630e_1(dest, destOffset, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, -_t15);
    }

    /** Piece 2 of {@code decomposeSkew_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSkew_api_fma_sf3d9630e_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t14, float _t15, float _t16, float _t17) {
        float _t26 = java.lang.Math.fma(java.lang.Math.fma(_t17, _t7, _self21), _t7, java.lang.Math.fma(java.lang.Math.fma(_t17, _t8, _self01), _t8, java.lang.Math.fma(_t17, _t9, _self11) * _t9));
        float _t27 = -_t26;
        float _t32 = java.lang.Math.fma(_t17, _t7, java.lang.Math.fma(_t27, _t7, _self21));
        float _t33 = java.lang.Math.fma(_t17, _t8, java.lang.Math.fma(_t27, _t8, _self01));
        float _t34 = java.lang.Math.fma(_t17, _t9, java.lang.Math.fma(_t27, _t9, _self11));
        float _t37 = java.lang.Math.fma(_t32, _t32, java.lang.Math.fma(_t33, _t33, _t34 * _t34));
        float _t38 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43, _t44, _t45;
        if (_t37 != 0.0f) {
            _t43 = _t32 * _t38;
            _t44 = _t33 * _t38;
            _t45 = _t34 * _t38;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t57 = java.lang.Math.fma(java.lang.Math.fma(_t16, _t7, _self22), _t43, java.lang.Math.fma(java.lang.Math.fma(_t16, _t8, _self02), _t44, java.lang.Math.fma(_t16, _t9, _self12) * _t45));
        float _t58 = -_t57;
        return decomposeSkew_api_fma_sf3d9630e_2(dest, destOffset, _t7, _t8, _t9, _t14, (_t15 + _t26) * _t38, _t43, _t44, _t45, _t57, java.lang.Math.fma(_t16, _t7, java.lang.Math.fma(_t58, _t43, _self22)), java.lang.Math.fma(_t16, _t8, java.lang.Math.fma(_t58, _t44, _self02)), java.lang.Math.fma(_t16, _t9, java.lang.Math.fma(_t58, _t45, _self12)));
    }

    /** Piece 3 of {@code decomposeSkew_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSkew_api_fma_sf3d9630e_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t7, float _t8, float _t9, float _t14, float _t39, float _t43, float _t44, float _t45, float _t57, float _t62, float _t63, float _t64) {
        float _t71 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t62, _t62, java.lang.Math.fma(_t63, _t63, _t64 * _t64))));
        float _t72 = _t14 * _t71;
        if (java.lang.Math.fma(_t62, java.lang.Math.fma(_t8, _t45, -(_t9 * _t44)), java.lang.Math.fma(_t63, java.lang.Math.fma(_t9, _t43, -(_t7 * _t45)), _t64 * java.lang.Math.fma(_t7, _t44, -(_t8 * _t43)))) < 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_t72);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t39);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t72);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t39);
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t57 * _t71);
        return dest;
    }

    /** {@code decomposeSkew_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSkew_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t14 = ((_self22) * (_t7) + (((_self02) * (_t8) + (_self12 * _t9))));
        float _t15 = ((_self21) * (_t7) + (((_self01) * (_t8) + (_self11 * _t9))));
        return decomposeSkew_api_mulAdd_s74e2436d_1(dest, destOffset, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, -_t15);
    }

    /** Piece 2 of {@code decomposeSkew_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSkew_api_mulAdd_s74e2436d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t14, float _t15, float _t16, float _t17) {
        float _t26 = ((((_t17) * (_t7) + (_self21))) * (_t7) + (((((_t17) * (_t8) + (_self01))) * (_t8) + (((_t17) * (_t9) + (_self11)) * _t9))));
        float _t27 = -_t26;
        float _t32 = ((_t17) * (_t7) + (((_t27) * (_t7) + (_self21))));
        float _t33 = ((_t17) * (_t8) + (((_t27) * (_t8) + (_self01))));
        float _t34 = ((_t17) * (_t9) + (((_t27) * (_t9) + (_self11))));
        float _t37 = ((_t32) * (_t32) + (((_t33) * (_t33) + (_t34 * _t34))));
        float _t38 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43, _t44, _t45;
        if (_t37 != 0.0f) {
            _t43 = _t32 * _t38;
            _t44 = _t33 * _t38;
            _t45 = _t34 * _t38;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t57 = ((((_t16) * (_t7) + (_self22))) * (_t43) + (((((_t16) * (_t8) + (_self02))) * (_t44) + (((_t16) * (_t9) + (_self12)) * _t45))));
        float _t58 = -_t57;
        float _t62 = ((_t16) * (_t7) + (((_t58) * (_t43) + (_self22))));
        float _t63 = ((_t16) * (_t8) + (((_t58) * (_t44) + (_self02))));
        float _t64 = ((_t16) * (_t9) + (((_t58) * (_t45) + (_self12))));
        return decomposeSkew_api_mulAdd_s74e2436d_2(dest, destOffset, _t7, _t8, _t9, _t14, (_t15 + _t26) * _t38, _t43, _t44, _t45, _t57, _t62, _t63, _t64, (1.0f / (float) java.lang.Math.sqrt(((_t62) * (_t62) + (((_t63) * (_t63) + (_t64 * _t64)))))));
    }

    /** Piece 3 of {@code decomposeSkew_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSkew_api_mulAdd_s74e2436d_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t7, float _t8, float _t9, float _t14, float _t39, float _t43, float _t44, float _t45, float _t57, float _t62, float _t63, float _t64, float _t71) {
        float _t72 = _t14 * _t71;
        if (((_t62) * (((_t8) * (_t45) - (_t9 * _t44))) + (((_t63) * (((_t9) * (_t43) - (_t7 * _t45))) + (_t64 * ((_t7) * (_t44) - (_t8 * _t43)))))) < 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_t72);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t39);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t72);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t39);
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t57 * _t71);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 9; _i++) {
                float _eself = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, java.lang.Math.fma(t, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + _i * 4L) - _eself, _eself));
            }
            return dest;
        } else {
            for (int _i = 0; _i < 9; _i++) {
                float _eself = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, ((t) * (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + _i * 4L) - _eself) + (_eself)));
            }
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Float3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return mul_api_fma_sc13e49cc_1(dest, destOffset, right, rightOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code mul_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_fma_sc13e49cc_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment right, long rightOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + _lo * 4L);
            float _eright1 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + (_lo + 1) * 4L);
            float _eright2 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, java.lang.Math.fma(_eright2, _self02, java.lang.Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, java.lang.Math.fma(_eright2, _self12, java.lang.Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, java.lang.Math.fma(_eright2, _self22, java.lang.Math.fma(_eright0, _self20, _eright1 * _self21)));
        }
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return mul_api_mulAdd_s52eea597_1(dest, destOffset, right, rightOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code mul_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_mulAdd_s52eea597_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment right, long rightOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + _lo * 4L);
            float _eright1 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + (_lo + 1) * 4L);
            float _eright2 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, ((_eright2) * (_self02) + (((_eright0) * (_self00) + (_eright1 * _self01)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, ((_eright2) * (_self12) + (((_eright0) * (_self10) + (_eright1 * _self11)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, ((_eright2) * (_self22) + (((_eright0) * (_self20) + (_eright1 * _self21)))));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Float3x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _right00 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset);
        float _right10 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 4L);
        float _right01 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 8L);
        float _right11 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        return mulMat2x2_api_fma_s2cd17dea_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right00, _right10, _right01, _right11);
    }

    /** Piece 2 of {@code mulMat2x2_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x2_api_fma_s2cd17dea_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _right00, float _right10, float _right01, float _right11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_right00, _self20, _right10 * _self21));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_right01, _self20, _right11 * _self21));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    /** {@code mulMat2x2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _right00 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset);
        float _right10 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 4L);
        float _right01 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 8L);
        float _right11 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_right00) * (_self10) + (_right10 * _self11)));
        return mulMat2x2_api_mulAdd_s1270916d_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right00, _right10, _right01, _right11);
    }

    /** Piece 2 of {@code mulMat2x2_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x2_api_mulAdd_s1270916d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _right00, float _right10, float _right01, float _right11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_right00) * (_self20) + (_right10 * _self21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_right01) * (_self20) + (_right11 * _self21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Float3x3OpsKernelsAddress.mulMat2x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x3_api_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x3_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@code mulMat2x3_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x3_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _right00 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset);
        float _right10 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 4L);
        float _right01 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 8L);
        float _right11 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 12L);
        float _right02 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 16L);
        float _right12 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 20L);
        return mulMat2x3_api_fma_s1aa3010d_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right00, _right10, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x3_api_fma_s1aa3010d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _right00, float _right10, float _right01, float _right11, float _right02, float _right12) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_right00, _self20, _right10 * _self21));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_right01, _self20, _right11 * _self21));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_right02, _self00, java.lang.Math.fma(_right12, _self01, _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_right02, _self10, java.lang.Math.fma(_right12, _self11, _self12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_right02, _self20, java.lang.Math.fma(_right12, _self21, _self22)));
        return dest;
    }

    /** {@code mulMat2x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _right00 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset);
        float _right10 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 4L);
        float _right01 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 8L);
        float _right11 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 12L);
        float _right02 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 16L);
        float _right12 = right.get(_JAVA_FLOAT_UNALIGNED, rightOffset + 20L);
        return mulMat2x3_api_mulAdd_s27b96504_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right00, _right10, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x3_api_mulAdd_s27b96504_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _right00, float _right10, float _right01, float _right11, float _right02, float _right12) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_right00) * (_self20) + (_right10 * _self21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_right01) * (_self20) + (_right11 * _self21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_right02) * (_self00) + (((_right12) * (_self01) + (_self02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_right02) * (_self10) + (((_right12) * (_self11) + (_self12)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_right02) * (_self20) + (((_right12) * (_self21) + (_self22)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other20 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other21 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 24L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 28L);
        float _other22 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 32L);
        return preMul_api_fma_s79ceae4d_1(dest, destOffset, src, srcOffset, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMul_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma_s79ceae4d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L);
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, java.lang.Math.fma(_other02, _eself2, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, java.lang.Math.fma(_other12, _eself2, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, java.lang.Math.fma(_other22, _eself2, java.lang.Math.fma(_other20, _eself0, _other21 * _eself1)));
        }
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other20 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other21 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 24L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 28L);
        float _other22 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 32L);
        return preMul_api_mulAdd_see9ba77c_1(dest, destOffset, src, srcOffset, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMul_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd_see9ba77c_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L);
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, ((_other02) * (_eself2) + (((_other00) * (_eself0) + (_other01 * _eself1)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, ((_other12) * (_eself2) + (((_other10) * (_eself0) + (_other11 * _eself1)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, ((_other22) * (_eself2) + (((_other20) * (_eself0) + (_other21 * _eself1)))));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
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
            int _lo = _l * 3;
            float _eself0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L);
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eself2);
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
            int _lo = _l * 3;
            float _eself0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L);
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, ((_other00) * (_eself0) + (_other01 * _eself1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, ((_other10) * (_eself0) + (_other11 * _eself1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3x3OpsKernelsAddress.preMulMat2x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x3_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x3_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMulMat2x3_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat2x3_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L);
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, java.lang.Math.fma(_other02, _eself2, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, java.lang.Math.fma(_other12, _eself2, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    /** {@code preMulMat2x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulMat2x3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _other00 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _other10 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _other01 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _other11 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _other02 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 16L);
        float _other12 = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 20L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L);
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, ((_other02) * (_eself2) + (((_other00) * (_eself0) + (_other01 * _eself1)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, ((_other12) * (_eself2) + (((_other10) * (_eself0) + (_other11 * _eself1)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 9; _i++) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, java.lang.Math.fma(weight, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + _i * 4L), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L)));
            }
            return dest;
        } else {
            for (int _i = 0; _i < 9; _i++) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, ((weight) * (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + _i * 4L)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L))));
            }
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float colX, float colY, float colZ, float rowX, float rowY, float rowZ) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, colZ, rowX, rowY, rowZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, float colX, float colY, float colZ, float rowX, float rowY, float rowZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, colX * rowX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, colY * rowX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, colZ * rowX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, colX * rowY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, colY * rowY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, colZ * rowY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, colX * rowZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, colY * rowZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, colZ * rowZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment col, long colOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        long _destBase = dest.address() + destOffset;
        long _colBase = col.address() + colOffset;
        long _rowBase = row.address() + rowOffset;
        Float3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment col, long colOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _colx = col.get(_JAVA_FLOAT_UNALIGNED, colOffset);
        float _coly = col.get(_JAVA_FLOAT_UNALIGNED, colOffset + 4L);
        float _colz = col.get(_JAVA_FLOAT_UNALIGNED, colOffset + 8L);
        float _rowx = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset);
        float _rowy = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset + 4L);
        float _rowz = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _colx * _rowx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _coly * _rowx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _colz * _rowx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _colx * _rowy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _coly * _rowy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _colz * _rowy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _colx * _rowz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _coly * _rowz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _colz * _rowz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3x3OpsKernelsSegment.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        return lookAlong_lh_api_fma_s5cdc5700_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t17, _t11, upX), java.lang.Math.fma(_t17, _t13, upY), java.lang.Math.fma(_t17, _t12, upZ));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_s5cdc5700_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        return lookAlong_lh_api_fma_s5cdc5700_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, _t46, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)), java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_s5cdc5700_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        return lookAlong_lh_api_mulAdd_s97dd42dd_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t11) + (upX)), ((_t17) * (_t13) + (upY)), ((_t17) * (_t12) + (upZ)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s97dd42dd_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        return lookAlong_lh_api_mulAdd_s97dd42dd_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, ((_t37) * (_t12) - (_t38 * _t11)), ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s97dd42dd_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3x3OpsKernelsSegment.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _t1 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t2 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, upY);
        float _t22 = java.lang.Math.fma(_t20, _t14, upX);
        float _t23 = java.lang.Math.fma(_t20, _t15, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _t1 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t2 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        float _t21 = ((_t20) * (_t16) + (upY));
        float _t22 = ((_t20) * (_t14) + (upX));
        float _t23 = ((_t20) * (_t15) + (upZ));
        float _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        float _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && dir.isNative() && up.isNative()) return Float3x3OpsKernelsSegment.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return lookAlong_lh_api_fma_s10d093be_3(dest, destOffset, _dirx, _diry, _dirz, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry)));
    }

    /** Part 1 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static float lookAlong_lh_api_fma_s10d093be_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t13, float _t12, float _t11, float _t38, float _t39, float _t37) {
        float _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        float _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        float _t48 = java.lang.Math.fma(_t39, _t11, -(_t37 * _t13));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        return _t48;
    }

    /** Part 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_s10d093be_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _t12, float _t38, float _t48) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_s10d093be_3(java.lang.foreign.MemorySegment dest, long destOffset, float _dirx, float _diry, float _dirz, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t4) {
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t11, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        return lookAlong_lh_api_fma_s10d093be_2(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _t12, _t38, lookAlong_lh_api_fma_s10d093be_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t13, _t12, _t11, _t38, _t39, _t37));
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return lookAlong_lh_api_mulAdd_s6c5cea49_1(dest, destOffset, _dirx, _diry, _dirz, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry)))));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s6c5cea49_1(java.lang.foreign.MemorySegment dest, long destOffset, float _dirx, float _diry, float _dirz, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t4) {
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t11) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t12) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        return lookAlong_lh_api_mulAdd_s6c5cea49_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, ((_t38) * (_t13) - (_t39 * _t12)), ((_t37) * (_t12) - (_t38 * _t11)), ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s6c5cea49_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && dir.isNative() && up.isNative()) return Float3x3OpsKernelsSegment.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _t1 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t2 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t7 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t14, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t15, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _t1 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t2 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t7 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        float _t21 = ((_t20) * (_t16) + (_upy));
        float _t22 = ((_t20) * (_t14) + (_upx));
        float _t23 = ((_t20) * (_t15) + (_upz));
        float _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        float _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3x3OpsKernelsSegment.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        return lookAlong_lh_api_fma_sbdcd15d4_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t17, _t11, upX), java.lang.Math.fma(_t17, _t13, upY), java.lang.Math.fma(_t17, _t12, upZ));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_sbdcd15d4_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        return lookAlong_lh_api_fma_sbdcd15d4_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, _t46, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)), java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_sbdcd15d4_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        return lookAlong_lh_api_mulAdd_s3331d9c3_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t11) + (upX)), ((_t17) * (_t13) + (upY)), ((_t17) * (_t12) + (upZ)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s3331d9c3_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        return lookAlong_lh_api_mulAdd_s3331d9c3_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, ((_t37) * (_t12) - (_t38 * _t11)), ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s3331d9c3_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3x3OpsKernelsSegment.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _t1 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t2 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, upY);
        float _t22 = java.lang.Math.fma(_t20, _t14, upX);
        float _t23 = java.lang.Math.fma(_t20, _t15, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _t1 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t2 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        float _t21 = ((_t20) * (_t16) + (upY));
        float _t22 = ((_t20) * (_t14) + (upX));
        float _t23 = ((_t20) * (_t15) + (upZ));
        float _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        float _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && dir.isNative() && up.isNative()) return Float3x3OpsKernelsSegment.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return lookAlong_lh_api_fma_sf4e8934a_3(dest, destOffset, epsilon, _dirx, _diry, _dirz, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry)));
    }

    /** Part 1 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static float lookAlong_lh_api_fma_sf4e8934a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t13, float _t12, float _t11, float _t38, float _t39, float _t37) {
        float _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        float _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        float _t48 = java.lang.Math.fma(_t39, _t11, -(_t37 * _t13));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        return _t48;
    }

    /** Part 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_sf4e8934a_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _t12, float _t38, float _t48) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_sf4e8934a_3(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _dirx, float _diry, float _dirz, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t4) {
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t11, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        return lookAlong_lh_api_fma_sf4e8934a_2(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _t12, _t38, lookAlong_lh_api_fma_sf4e8934a_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t13, _t12, _t11, _t38, _t39, _t37));
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return lookAlong_lh_api_mulAdd_s55c70dcf_1(dest, destOffset, epsilon, _dirx, _diry, _dirz, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry)))));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s55c70dcf_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _dirx, float _diry, float _dirz, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t4) {
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t11) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t12) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        return lookAlong_lh_api_mulAdd_s55c70dcf_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, ((_t38) * (_t13) - (_t39 * _t12)), ((_t37) * (_t12) - (_t38 * _t11)), ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s55c70dcf_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && dir.isNative() && up.isNative()) return Float3x3OpsKernelsSegment.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _t1 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t2 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t7 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        float _t21 = java.lang.Math.fma(_t20, _t16, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t14, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t15, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _t0 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _t1 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _t2 = -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t7 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t14, _t15, _t16;
        if (_t7 != 0.0f) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        float _t21 = ((_t20) * (_t16) + (_upy));
        float _t22 = ((_t20) * (_t14) + (_upx));
        float _t23 = ((_t20) * (_t15) + (_upz));
        float _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        float _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromDualQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromDualQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeFromDualQuat_api_fma(dest, destOffset, dqRX, dqRY, dqRZ, dqRW);
        return makeFromDualQuat_api_mulAdd(dest, destOffset, dqRX, dqRY, dqRZ, dqRW);
    }

    /** {@code makeFromDualQuat_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeFromDualQuat_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = java.lang.Math.fma(-2.0f, dqRZ * dqRZ, 1.0f);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, _t0, _t6));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * java.lang.Math.fma(dqRX, dqRY, _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0f, _t3, _sp0 * dqRZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(-2.0f, _t2, _sp0 * dqRY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-2.0f, _t4, _t6));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 2.0f * java.lang.Math.fma(dqRX, dqRW, _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * java.lang.Math.fma(dqRX, dqRZ, _t3));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f)));
        return dest;
    }

    /** {@code makeFromDualQuat_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeFromDualQuat_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = ((1.0f) - (2.0f) * (dqRZ * dqRZ));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t6) - (2.0f) * (_t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * ((dqRX) * (dqRY) + (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_sp0 * dqRZ) - (2.0f) * (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_sp0 * dqRY) - (2.0f) * (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t6) - (2.0f) * (_t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 2.0f * ((dqRX) * (dqRW) + (_t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * ((dqRX) * (dqRZ) + (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t5 + _t5) - (2.0f) * (dqRX * dqRW)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationAxis_api_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisY;
        float _t4 = axisX * axisZ;
        float _t5 = axisY * axisZ;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t2, axisX * axisX, _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(axisZ, _t1, _t2 * _t3));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t4, -(axisY * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t2, _t3, -(axisZ * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t2, axisY * axisY, _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(axisX, _t1, _t2 * _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(axisY, _t1, _t2 * _t4));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t2, _t5, -(axisX * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t2, axisZ * axisZ, _t0));
        return dest;
    }

    /** {@code makeRotationAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisY;
        float _t4 = axisX * axisZ;
        float _t5 = axisY * axisZ;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t2) * (axisX * axisX) + (_t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((axisZ) * (_t1) + (_t2 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (_t4) - (axisY * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t2) * (_t3) - (axisZ * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t2) * (axisY * axisY) + (_t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((axisX) * (_t1) + (_t2 * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((axisY) * (_t1) + (_t2 * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t2) * (_t5) - (axisX * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_t2) * (axisZ * axisZ) + (_t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _axisBase = axis.address() + axisOffset;
        Float3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationAxis_api_fma(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_api_mulAdd(dest, destOffset, axis, axisOffset, angle);
    }

    /** {@code makeRotationAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationAxis_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisy;
        float _t4 = _axisx * _axisz;
        float _t5 = _axisy * _axisz;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _axisx * _axisx, _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_axisz, _t1, _t2 * _t3));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t4, -(_axisy * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t2, _t3, -(_axisz * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t2, _axisy * _axisy, _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_axisx, _t1, _t2 * _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_axisy, _t1, _t2 * _t4));
        return makeRotationAxis_api_fma_s4864c609_1(dest, destOffset, _t0, _t1, _axisx, _axisz, _t2, _t5);
    }

    /** Piece 2 of {@code makeRotationAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationAxis_api_fma_s4864c609_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _axisx, float _axisz, float _t2, float _t5) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t2, _t5, -(_axisx * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t2, _axisz * _axisz, _t0));
        return dest;
    }

    /** {@code makeRotationAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisy;
        float _t4 = _axisx * _axisz;
        float _t5 = _axisy * _axisz;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t2) * (_axisx * _axisx) + (_t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_axisz) * (_t1) + (_t2 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (_t4) - (_axisy * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t2) * (_t3) - (_axisz * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t2) * (_axisy * _axisy) + (_t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_axisx) * (_t1) + (_t2 * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_axisy) * (_t1) + (_t2 * _t4)));
        return makeRotationAxis_api_mulAdd_sfe15e054_1(dest, destOffset, _t0, _t1, _axisx, _axisz, _t2, _t5);
    }

    /** Piece 2 of {@code makeRotationAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationAxis_api_mulAdd_sfe15e054_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _axisx, float _axisz, float _t2, float _t5) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t2) * (_t5) - (_axisx * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_t2) * (_axisz * _axisz) + (_t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Float3x3OpsKernelsSegment.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        return makeRotationLookAlong_lh_api_fma_sc6f378a7_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma_sc6f378a7_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t39) * (_t13) - (_t38 * _t11)));
        return makeRotationLookAlong_lh_api_mulAdd_s809ebd84_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd_s809ebd84_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Float3x3OpsKernelsSegment.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t13, upY);
        float _t19 = java.lang.Math.fma(_t17, _t12, upX);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        return makeRotationLookAlong_rh_api_fma_s3c2d759_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma_s3c2d759_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t13) + (upY));
        float _t19 = ((_t17) * (_t12) + (upX));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t38) * (_t11) - (_t39 * _t13)));
        return makeRotationLookAlong_rh_api_mulAdd_s318368b6_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd_s318368b6_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && dir.isNative() && up.isNative()) return Float3x3OpsKernelsSegment.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        return makeRotationLookAlong_lh_api_fma_sa7bf1d3b_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma_sa7bf1d3b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        return makeRotationLookAlong_lh_api_mulAdd_sa2237a3a_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd_sa2237a3a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32) {
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t39) * (_t13) - (_t38 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && dir.isNative() && up.isNative()) return Float3x3OpsKernelsSegment.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t19 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        return makeRotationLookAlong_rh_api_fma_s92d845d_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t12, -(_t19 * _t13)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t13, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma_s92d845d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t13) + (_upy));
        float _t19 = ((_t17) * (_t12) + (_upx));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        return makeRotationLookAlong_rh_api_mulAdd_s2b733054_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd_s2b733054_1(java.lang.foreign.MemorySegment dest, long destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32) {
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t38) * (_t11) - (_t39 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Float3x3OpsKernelsSegment.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        return makeRotationLookAlong_lh_api_fma_s6ddc043d_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma_s6ddc043d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t39) * (_t13) - (_t38 * _t11)));
        return makeRotationLookAlong_lh_api_mulAdd_s9b767cc8_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd_s9b767cc8_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Float3x3OpsKernelsSegment.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t13, upY);
        float _t19 = java.lang.Math.fma(_t17, _t12, upX);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        return makeRotationLookAlong_rh_api_fma_se08bee5f_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma_se08bee5f_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t13) + (upY));
        float _t19 = ((_t17) * (_t12) + (upX));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t38) * (_t11) - (_t39 * _t13)));
        return makeRotationLookAlong_rh_api_mulAdd_s600f76c2_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd_s600f76c2_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && dir.isNative() && up.isNative()) return Float3x3OpsKernelsSegment.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        return makeRotationLookAlong_lh_api_fma_s28a96de1_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma_s28a96de1_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        return makeRotationLookAlong_lh_api_mulAdd_sb9109bb6_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd_sb9109bb6_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32) {
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t39) * (_t13) - (_t38 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t37) * (_t11) - (_t39 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t38) * (_t12) - (_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && dir.isNative() && up.isNative()) return Float3x3OpsKernelsSegment.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t19 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        return makeRotationLookAlong_rh_api_fma_sfeaaa243_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t12, -(_t19 * _t13)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t13, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma_sfeaaa243_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t13) + (_upy));
        float _t19 = ((_t17) * (_t12) + (_upx));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        return makeRotationLookAlong_rh_api_mulAdd_s3f254c98_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd_s3f254c98_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32) {
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t38) * (_t11) - (_t39 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t39) * (_t12) - (_t37 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t37) * (_t13) - (_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, float qX, float qY, float qZ, float qW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationQuat_api_mulAdd(dest, destOffset, qX, qY, qZ, qW);
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(qY, qY, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * java.lang.Math.fma(qX, qY, _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * java.lang.Math.fma(qX, qZ, -_t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 2.0f * java.lang.Math.fma(qX, qY, -_t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 2.0f * java.lang.Math.fma(qX, qW, qY * qZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * java.lang.Math.fma(qX, qZ, _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 2.0f * java.lang.Math.fma(qY, qZ, -(qX * qW)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, qY * qY), 1.0f));
        return dest;
    }

    /** {@code makeRotationQuat_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationQuat_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float qX, float qY, float qZ, float qW) {
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((1.0f) - (2.0f) * (((qY) * (qY) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * ((qX) * (qY) + (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * ((qX) * (qZ) - (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 2.0f * ((qX) * (qY) - (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((1.0f) - (2.0f) * (((qX) * (qX) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 2.0f * ((qX) * (qW) + (qY * qZ)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * ((qX) * (qZ) + (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 2.0f * ((qY) * (qZ) - (qX * qW)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((1.0f) - (2.0f) * (((qX) * (qX) + (qY * qY)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        long _destBase = dest.address() + destOffset;
        long _qBase = q.address() + qOffset;
        Float3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, _qBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationQuat_api_fma(dest, destOffset, q, qOffset);
        return makeRotationQuat_api_mulAdd(dest, destOffset, q, qOffset);
    }

    /** {@code makeRotationQuat_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationQuat_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _qx = q.get(_JAVA_FLOAT_UNALIGNED, qOffset);
        float _qy = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 4L);
        float _qz = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 8L);
        float _qw = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 12L);
        float _t0 = _qz * _qz;
        float _t1 = _qz * _qw;
        float _t2 = _qy * _qw;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_qy, _qy, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * java.lang.Math.fma(_qx, _qy, _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * java.lang.Math.fma(_qx, _qz, -_t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 2.0f * java.lang.Math.fma(_qx, _qy, -_t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_qx, _qx, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 2.0f * java.lang.Math.fma(_qx, _qw, _qy * _qz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * java.lang.Math.fma(_qx, _qz, _t2));
        return makeRotationQuat_api_fma_s30468784_1(dest, destOffset, _qx, _qy, _qz, _qw);
    }

    /** Piece 2 of {@code makeRotationQuat_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationQuat_api_fma_s30468784_1(java.lang.foreign.MemorySegment dest, long destOffset, float _qx, float _qy, float _qz, float _qw) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, 2.0f * java.lang.Math.fma(_qy, _qz, -(_qx * _qw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_qx, _qx, _qy * _qy), 1.0f));
        return dest;
    }

    /** {@code makeRotationQuat_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationQuat_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _qx = q.get(_JAVA_FLOAT_UNALIGNED, qOffset);
        float _qy = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 4L);
        float _qz = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 8L);
        float _qw = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 12L);
        float _t0 = _qz * _qz;
        float _t1 = _qz * _qw;
        float _t2 = _qy * _qw;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((1.0f) - (2.0f) * (((_qy) * (_qy) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * ((_qx) * (_qy) + (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * ((_qx) * (_qz) - (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 2.0f * ((_qx) * (_qy) - (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((1.0f) - (2.0f) * (((_qx) * (_qx) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 2.0f * ((_qx) * (_qw) + (_qy * _qz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * ((_qx) * (_qz) + (_t2)));
        return makeRotationQuat_api_mulAdd_s659eae53_1(dest, destOffset, _qx, _qy, _qz, _qw);
    }

    /** Piece 2 of {@code makeRotationQuat_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationQuat_api_mulAdd_s659eae53_1(java.lang.foreign.MemorySegment dest, long destOffset, float _qx, float _qy, float _qz, float _qw) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, 2.0f * ((_qy) * (_qz) - (_qx * _qw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((1.0f) - (2.0f) * (((_qx) * (_qx) + (_qy * _qy)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTowards_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTowards_api(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        return makeRotationTowards_api_fma_s1b2c6136_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_fma_s1b2c6136_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t39);
        return makeRotationTowards_api_mulAdd_sc565ab73_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_mulAdd_sc565ab73_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTowards_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTowards_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        return makeRotationTowards_api_fma_s61ea3dd4_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_fma_s61ea3dd4_1(java.lang.foreign.MemorySegment dest, long destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        return makeRotationTowards_api_mulAdd_s2dc3932f_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_mulAdd_s2dc3932f_1(java.lang.foreign.MemorySegment dest, long destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32) {
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTowards_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTowards_api(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        return makeRotationTowards_api_fma_s9da84c42_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_fma_s9da84c42_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t39);
        return makeRotationTowards_api_mulAdd_se8229d9_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_mulAdd_se8229d9_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTowards_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.makeRotationTowards_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTowards_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationTowards_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        return makeRotationTowards_api_fma_s3bb49918_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)), java.lang.Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code makeRotationTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_fma_s3bb49918_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        return makeRotationTowards_api_mulAdd_s4ab57065_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))));
    }

    /** Piece 2 of {@code makeRotationTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTowards_api_mulAdd_s4ab57065_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32) {
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t37);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t39);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t38);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t38) * (_t13) - (_t39 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t37) * (_t11) - (_t38 * _t12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t39) * (_t12) - (_t37 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationX_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -_t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleY, float angleZ) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleY, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXYZ_api_mulAdd(dest, destOffset, angleX, angleY, angleZ);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t3;
        float _t7 = _t3 * _t5;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t6, _t1, _t2 * _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t4, _t2, -(_t7 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_t2 * _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t5, _t1, _t6 * -_t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t7, _t2, _t4 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t3);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -(_t4 * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationXYZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleY, float angleZ) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t3;
        float _t7 = _t3 * _t5;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t6) * (_t1) + (_t2 * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t4) * (_t2) - (_t7 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_t2 * _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t5) * (_t1) - (_t6 * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t7) * (_t2) + (_t4 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t3);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -(_t4 * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t5 * _t0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleZ, float angleY) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleZ, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXZY_api_mulAdd(dest, destOffset, angleX, angleZ, angleY);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        float _t6 = _t2 * _t4;
        float _t7 = _t5 * _t2;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t6, _t0, _t5 * _t3));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t7, _t0, -(_t3 * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_t2);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t4 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t5 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t3 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t6, _t3, -(_t5 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t7, _t3, _t4 * _t0));
        return dest;
    }

    /** {@code makeRotationXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationXZY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleZ, float angleY) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        float _t6 = _t2 * _t4;
        float _t7 = _t5 * _t2;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t6) * (_t0) + (_t5 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t7) * (_t0) - (_t3 * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_t2);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t4 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t5 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t3 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t6) * (_t3) - (_t5 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_t7) * (_t3) + (_t4 * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationY_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYXZ_api_mulAdd(dest, destOffset, angleY, angleX, angleZ);
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.cosWithSin(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t6, _t2, _t3 * _t4));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t2 * _t5);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t7, _t2, -(_t1 * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t6, _t4, -(_t2 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t5 * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t7, _t4, _t1 * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t1 * _t5);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t5 * _t3);
        return dest;
    }

    /** {@code makeRotationYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationYXZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.cosWithSin(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t6) * (_t2) + (_t3 * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t2 * _t5);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t7) * (_t2) - (_t1 * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t6) * (_t4) - (_t2 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t5 * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t7) * (_t4) + (_t1 * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _t1 * _t5);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t5 * _t3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleZ, float angleX) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleZ, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYZX_api_mulAdd(dest, destOffset, angleY, angleZ, angleX);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t0;
        float _t7 = _t3 * _t4;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t3 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t2, _t3, -(_t6 * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t5 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_t7, _t5, _t2 * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t6, _t2, _t3 * _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -_t2 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_t5, _t0, _t7 * -_t2));
        return dest;
    }

    /** {@code makeRotationYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationYZX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleZ, float angleX) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t0;
        float _t7 = _t3 * _t4;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t3 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t2) * (_t3) - (_t6 * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t5 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_t7) * (_t5) + (_t2 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_t6) * (_t2) + (_t3 * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -_t2 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_t5) * (_t0) - (_t7 * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleX, float angleY) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleX, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZXY_api_mulAdd(dest, destOffset, angleZ, angleX, angleY);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleY);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t3;
        float _t7 = _t2 * _t1;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t0, _t1, _t6 * -_t4));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t7, _t4, _t3 * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t4 * _t5);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -(_t3 * _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t5 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t2);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t6, _t0, _t4 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t4, _t3, -(_t7 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationZXY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleX, float angleY) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleY);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t3;
        float _t7 = _t2 * _t1;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t0) * (_t1) - (_t6 * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t7) * (_t4) + (_t3 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t4 * _t5);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -(_t3 * _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t5 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t2);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_t6) * (_t0) + (_t4 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t4) * (_t3) - (_t7 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t5 * _t0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleY, float angleX) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleY, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZYX_api_mulAdd(dest, destOffset, angleZ, angleY, angleX);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.sinWithCos(angleX);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t4 * _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t2);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t6, _t3, -(_t4 * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t7, _t3, _t5 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t3 * _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_t6, _t5, _t3 * _t4));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_t7, _t5, -(_t3 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationZYX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleY, float angleX) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.sinWithCos(angleX);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0 * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t4 * _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_t2);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t6) * (_t3) - (_t4 * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_t7) * (_t3) + (_t5 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _t3 * _t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_t6) * (_t5) + (_t3 * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_t7) * (_t5) - (_t3 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _t5 * _t0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, vY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Float3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, v.get(_JAVA_FLOAT_UNALIGNED, vOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _vy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, s);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, vY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Float3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _vy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeView_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float left, float right, float bottom, float top) {
        long _destBase = dest.address() + destOffset;
        Float3x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeView_api(java.lang.foreign.MemorySegment dest, long destOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t0_inv + _t0_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _t1_inv + _t1_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, -(left + right) * _t0_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -(bottom + top) * _t1_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_api_fma(dest, destOffset, src, srcOffset, angle);
        return preRotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    /** {@code preRotate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        return preRotate_api_fma_s33a05b26_1(dest, destOffset, _t0, _t1, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code preRotate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotate_api_fma_s33a05b26_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self21, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self02, _t1, _self12 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    /** {@code preRotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self01) * (_t1) + (_self11 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        return preRotate_api_mulAdd_s88f4881_1(dest, destOffset, _t0, _t1, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code preRotate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotate_api_mulAdd_s88f4881_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t0) - (_self12 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self02) * (_t1) + (_self12 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1))));
        return preRotateAround_api_fma_s9520ea30_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1)));
    }

    /** Piece 2 of {@code preRotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_fma_s9520ea30_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self20, _t11, java.lang.Math.fma(_self00, _t1, _self10 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self21, _t10, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self21, _t11, java.lang.Math.fma(_self01, _t1, _self11 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self22, _t10, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self02, _t1, _self12 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        float _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self20) * (_t10) + (((_self00) * (_t0) - (_self10 * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self20) * (_t11) + (((_self00) * (_t1) + (_self10 * _t0)))));
        return preRotateAround_api_mulAdd_sdda5616d_1(dest, destOffset, _t0, _t1, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_mulAdd_sdda5616d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self21) * (_t10) + (((_self01) * (_t0) - (_self11 * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self21) * (_t11) + (((_self01) * (_t1) + (_self11 * _t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self22) * (_t10) + (((_self02) * (_t0) - (_self12 * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self22) * (_t11) + (((_self02) * (_t1) + (_self12 * _t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1))));
        return preRotateAround_api_fma_s3ff13a0e_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1)));
    }

    /** Piece 2 of {@code preRotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_fma_s3ff13a0e_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self20, _t11, java.lang.Math.fma(_self00, _t1, _self10 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self21, _t10, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self21, _t11, java.lang.Math.fma(_self01, _t1, _self11 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self22, _t10, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self02, _t1, _self12 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((_pivotx) * (_t9) + (_pivoty * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self20) * (_t10) + (((_self00) * (_t0) - (_self10 * _t1)))));
        return preRotateAround_api_mulAdd_sc635cf9_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, ((_pivoty) * (_t9) - (_pivotx * _t1)));
    }

    /** Piece 2 of {@code preRotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_mulAdd_sc635cf9_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self20) * (_t11) + (((_self00) * (_t1) + (_self10 * _t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self21) * (_t10) + (((_self01) * (_t0) - (_self11 * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self21) * (_t11) + (((_self01) * (_t1) + (_self11 * _t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self22) * (_t10) + (((_self02) * (_t0) - (_self12 * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self22) * (_t11) + (((_self02) * (_t1) + (_self12 * _t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_api_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@code preRotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = 1.0f - _t1;
        float _t3 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t7 = axisY * axisZ;
        return preRotateAxis_api_fma_sb11f0df1_1(dest, destOffset, axisX, axisY, axisZ, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t3, _t5, _t7, java.lang.Math.fma(_t2, axisX * axisX, _t1), java.lang.Math.fma(_t2, axisY * axisY, _t1), java.lang.Math.fma(_t2, axisZ * axisZ, _t1), java.lang.Math.fma(axisY, _t0, _t2 * _t3), java.lang.Math.fma(axisZ, _t0, _t2 * _t5), java.lang.Math.fma(axisX, _t0, _t2 * _t7));
    }

    /** Piece 2 of {@code preRotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_fma_sb11f0df1_1(java.lang.foreign.MemorySegment dest, long destOffset, float axisX, float axisY, float axisZ, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3, float _t5, float _t7, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = java.lang.Math.fma(_t2, _t5, -(axisZ * _t0));
        float _t25 = java.lang.Math.fma(_t2, _t7, -(axisX * _t0));
        float _t26 = java.lang.Math.fma(_t2, _t3, -(axisY * _t0));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24)));
        return preRotateAxis_api_fma_sb11f0df1_2(dest, destOffset, _self02, _self12, _self22, _t19, _t20, _t22, _t23, _t25, _t26);
    }

    /** Piece 3 of {@code preRotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_fma_sb11f0df1_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self02, float _self12, float _self22, float _t19, float _t20, float _t22, float _t23, float _t25, float _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    /** {@code preRotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = 1.0f - _t1;
        float _t3 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t7 = axisY * axisZ;
        return preRotateAxis_api_mulAdd_s64fb8e5e_1(dest, destOffset, axisX, axisY, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t3, _t7, ((_t2) * (axisX * axisX) + (_t1)), ((_t2) * (axisY * axisY) + (_t1)), ((_t2) * (axisZ * axisZ) + (_t1)), ((axisY) * (_t0) + (_t2 * _t3)), ((axisZ) * (_t0) + (_t2 * _t5)), ((axisX) * (_t0) + (_t2 * _t7)), ((_t2) * (_t5) - (axisZ * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_mulAdd_s64fb8e5e_1(java.lang.foreign.MemorySegment dest, long destOffset, float axisX, float axisY, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3, float _t7, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
        float _t25 = ((_t2) * (_t7) - (axisX * _t0));
        float _t26 = ((_t2) * (_t3) - (axisY * _t0));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        Float3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@code preRotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t1 = Math.cosWithSin(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = 1.0f - _t1;
        return preRotateAxis_api_fma_s1b2feda0_1(dest, destOffset, Math.sinWithCos(angle), _axisx, _axisy, _axisz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, java.lang.Math.fma(_t2, _axisx * _axisx, _t1), java.lang.Math.fma(_t2, _axisy * _axisy, _t1), java.lang.Math.fma(_t2, _axisz * _axisz, _t1));
    }

    /** Piece 2 of {@code preRotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_fma_s1b2feda0_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _axisx, float _axisy, float _axisz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3, float _t5, float _t7, float _t18, float _t19, float _t20) {
        float _t21 = java.lang.Math.fma(_axisy, _t0, _t2 * _t3);
        float _t22 = java.lang.Math.fma(_axisz, _t0, _t2 * _t5);
        float _t23 = java.lang.Math.fma(_axisx, _t0, _t2 * _t7);
        float _t24 = java.lang.Math.fma(_t2, _t5, -(_axisz * _t0));
        float _t25 = java.lang.Math.fma(_t2, _t7, -(_axisx * _t0));
        float _t26 = java.lang.Math.fma(_t2, _t3, -(_axisy * _t0));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23)));
        return preRotateAxis_api_fma_s1b2feda0_2(dest, destOffset, _self02, _self12, _self22, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26);
    }

    /** Piece 3 of {@code preRotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_fma_s1b2feda0_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    /** {@code preRotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t1 = Math.cosWithSin(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = 1.0f - _t1;
        return preRotateAxis_api_mulAdd_s7790bd97_1(dest, destOffset, Math.sinWithCos(angle), _axisx, _axisy, _axisz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, ((_t2) * (_axisx * _axisx) + (_t1)), ((_t2) * (_axisy * _axisy) + (_t1)), ((_t2) * (_axisz * _axisz) + (_t1)));
    }

    /** Piece 2 of {@code preRotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_mulAdd_s7790bd97_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _axisx, float _axisy, float _axisz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3, float _t5, float _t7, float _t18, float _t19, float _t20) {
        float _t21 = ((_axisy) * (_t0) + (_t2 * _t3));
        float _t22 = ((_axisz) * (_t0) + (_t2 * _t5));
        float _t23 = ((_axisx) * (_t0) + (_t2 * _t7));
        float _t24 = ((_t2) * (_t5) - (_axisz * _t0));
        float _t25 = ((_t2) * (_t7) - (_axisx * _t0));
        float _t26 = ((_t2) * (_t3) - (_axisy * _t0));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23)))));
        return preRotateAxis_api_mulAdd_s7790bd97_2(dest, destOffset, _self02, _self12, _self22, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26);
    }

    /** Piece 3 of {@code preRotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_mulAdd_s7790bd97_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateX_api_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateX_api_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    /** {@code preRotateX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateX_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _t0, -(_self20 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self10, _t1, _self20 * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self11, _t0, -(_self21 * _t1)));
        return preRotateX_api_fma_sfbf5559c_1(dest, destOffset, _t0, _t1, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code preRotateX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateX_api_fma_sfbf5559c_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self11, float _self21, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self11, _t1, _self21 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t0, -(_self22 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self12, _t1, _self22 * _t0));
        return dest;
    }

    /** {@code preRotateX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_t0) - (_self20 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self10) * (_t1) + (_self20 * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self11) * (_t0) - (_self21 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self11) * (_t1) + (_self21 * _t0)));
        return preRotateX_api_mulAdd_s3763f24f_1(dest, destOffset, _t0, _t1, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code preRotateX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateX_api_mulAdd_s3763f24f_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t0) - (_self22 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self12) * (_t1) + (_self22 * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateY_api_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateY_api_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    /** {@code preRotateY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateY_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self20 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self20, _t0, -(_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self01, _t0, _self21 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        return preRotateY_api_fma_sf1718ddf_1(dest, destOffset, _t0, _t1, _self01, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code preRotateY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateY_api_fma_sf1718ddf_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self01, float _self21, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self21, _t0, -(_self01 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t0, _self22 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t0, -(_self02 * _t1)));
        return dest;
    }

    /** {@code preRotateY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self20 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self20) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self01) * (_t0) + (_self21 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self21) * (_t0) - (_self01 * _t1)));
        return preRotateY_api_mulAdd_s9304ea4e_1(dest, destOffset, _t0, _t1, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code preRotateY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateY_api_mulAdd_s9304ea4e_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t0) + (_self22 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t0) - (_self02 * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L) * vX);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, _eself1 * vY);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L) * _vx);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, _eself1 * _vy);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, s * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, s * _eself1);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_fma(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@code preScaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(s, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _self20 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(s, _self10, _self20 * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(s, _self01, _self21 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(s, _self11, _self21 * _t2));
        return preScaleAround_api_fma_s268f9ca3_1(dest, destOffset, s, _self21, _self02, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code preScaleAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_fma_s268f9ca3_1(java.lang.foreign.MemorySegment dest, long destOffset, float s, float _self21, float _self02, float _self12, float _self22, float _t1, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(s, _self02, _self22 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(s, _self12, _self22 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((s) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (_self20 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((s) * (_self10) + (_self20 * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((s) * (_self01) + (_self21 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((s) * (_self11) + (_self21 * _t2)));
        return preScaleAround_api_mulAdd_se64b3ef4_1(dest, destOffset, s, _self21, _self02, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code preScaleAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd_se64b3ef4_1(java.lang.foreign.MemorySegment dest, long destOffset, float s, float _self21, float _self02, float _self12, float _self22, float _t1, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((s) * (_self02) + (_self22 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((s) * (_self12) + (_self22 * _t2)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@code preScaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = 1.0f - s;
        float _t1 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * _t0;
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(s, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _self20 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(s, _self10, _self20 * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(s, _self01, _self21 * _t1));
        return preScaleAround_api_fma_s19c235f3_1(dest, destOffset, s, _self11, _self21, _self02, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code preScaleAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_fma_s19c235f3_1(java.lang.foreign.MemorySegment dest, long destOffset, float s, float _self11, float _self21, float _self02, float _self12, float _self22, float _t1, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(s, _self11, _self21 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(s, _self02, _self22 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(s, _self12, _self22 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = 1.0f - s;
        float _t1 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * _t0;
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((s) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (_self20 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((s) * (_self10) + (_self20 * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((s) * (_self01) + (_self21 * _t1)));
        return preScaleAround_api_mulAdd_see1136e_1(dest, destOffset, s, _self11, _self21, _self02, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code preScaleAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd_see1136e_1(java.lang.foreign.MemorySegment dest, long destOffset, float s, float _self11, float _self21, float _self02, float _self12, float _self22, float _t1, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((s) * (_self11) + (_self21 * _t2)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((s) * (_self02) + (_self22 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((s) * (_self12) + (_self22 * _t2)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_fma(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@code preScaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(sX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _self20 * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(sY, _self10, _self20 * _t3));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(sX, _self01, _self21 * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(sY, _self11, _self21 * _t3));
        return preScaleAround_api_fma_sa53b5ca1_1(dest, destOffset, sX, sY, _self21, _self02, _self12, _self22, _t2, _t3);
    }

    /** Piece 2 of {@code preScaleAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_fma_sa53b5ca1_1(java.lang.foreign.MemorySegment dest, long destOffset, float sX, float sY, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(sX, _self02, _self22 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(sY, _self12, _self22 * _t3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((sX) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (_self20 * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((sY) * (_self10) + (_self20 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((sX) * (_self01) + (_self21 * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((sY) * (_self11) + (_self21 * _t3)));
        return preScaleAround_api_mulAdd_s350e4768_1(dest, destOffset, sX, sY, _self21, _self02, _self12, _self22, _t2, _t3);
    }

    /** Piece 2 of {@code preScaleAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd_s350e4768_1(java.lang.foreign.MemorySegment dest, long destOffset, float sX, float sY, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((sX) * (_self02) + (_self22 * _t2)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((sY) * (_self12) + (_self22 * _t3)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
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
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * (1.0f - _sy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_sx, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _self20 * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_sy, _self10, _self20 * _t3));
        return preScaleAround_api_fma_s8ce117c7_1(dest, destOffset, _sx, _sy, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t3);
    }

    /** Piece 2 of {@code preScaleAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_fma_s8ce117c7_1(java.lang.foreign.MemorySegment dest, long destOffset, float _sx, float _sy, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_sx, _self01, _self21 * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_sy, _self11, _self21 * _t3));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_sx, _self02, _self22 * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_sy, _self12, _self22 * _t3));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _sx = s.get(_JAVA_FLOAT_UNALIGNED, sOffset);
        float _sy = s.get(_JAVA_FLOAT_UNALIGNED, sOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * (1.0f - _sy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_sx) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (_self20 * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_sy) * (_self10) + (_self20 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        return preScaleAround_api_mulAdd_s17d1dcea_1(dest, destOffset, _sx, _sy, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t3);
    }

    /** Piece 2 of {@code preScaleAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_mulAdd_s17d1dcea_1(java.lang.foreign.MemorySegment dest, long destOffset, float _sx, float _sy, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_sx) * (_self01) + (_self21 * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_sy) * (_self11) + (_self21 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_sx) * (_self02) + (_self22 * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_sy) * (_self12) + (_self22 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preTranslate_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, java.lang.Math.fma(_eself2, vX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, java.lang.Math.fma(_eself2, vY, _eself1));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    /** {@code preTranslate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preTranslate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, ((_eself2) * (vX) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, ((_eself2) * (vY) + (_eself1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preTranslate_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, java.lang.Math.fma(_eself2, _vx, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, java.lang.Math.fma(_eself2, _vy, _eself1));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    /** {@code preTranslate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preTranslate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 1) * 4L);
            float _eself2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + (_lo + 2) * 4L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + _lo * 4L, ((_eself2) * (_vx) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + _lo * 4L))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 1) * 4L, ((_eself2) * (_vy) + (_eself1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_fma(dest, destOffset, src, srcOffset, angle);
        return rotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    /** {@code rotate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        return rotate_api_fma_se8f09f71_1(dest, destOffset, _t0, _t1, _self10, _self20, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code rotate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_fma_se8f09f71_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self10, float _self20, float _self11, float _self21, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self20) * (_t0) + (_self21 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self11) * (_t0) - (_self10 * _t1)));
        return rotate_api_mulAdd_scbdeeac0_1(dest, destOffset, _t0, _t1, _self20, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code rotate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd_scbdeeac0_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self20, float _self21, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self21) * (_t0) - (_self20 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        return rotateAround_api_fma_sa1aae0b_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(pivotX, _t9, pivotY * _t1), java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma_sa1aae0b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self21, _t11, _self22)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_t0) + (_self11 * _t1)));
        return rotateAround_api_mulAdd_sfe9ce470_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((pivotX) * (_t9) + (pivotY * _t1)), ((pivotY) * (_t9) - (pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd_sfe9ce470_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self20) * (_t0) + (_self21 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self21) * (_t0) - (_self20 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self20) * (_t10) + (((_self21) * (_t11) + (_self22)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        return rotateAround_api_fma_s6881e44b_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1), java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma_s6881e44b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self21, _t11, _self22)));
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        return rotateAround_api_mulAdd_s8d02fece_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_pivotx) * (_t9) + (_pivoty * _t1)), ((_pivoty) * (_t9) - (_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd_s8d02fece_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self20) * (_t0) + (_self21 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self21) * (_t0) - (_self20 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self20) * (_t10) + (((_self21) * (_t11) + (_self22)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@code rotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisZ;
        float _t6 = axisX * axisY;
        float _t7 = axisY * axisZ;
        return rotateAxis_api_fma_s9c8ff010_1(dest, destOffset, axisX, axisY, axisZ, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t3, _t6, _t7, java.lang.Math.fma(_t2, axisX * axisX, _t0), java.lang.Math.fma(_t2, axisY * axisY, _t0), java.lang.Math.fma(_t2, axisZ * axisZ, _t0), java.lang.Math.fma(axisZ, _t1, _t2 * _t6), java.lang.Math.fma(axisX, _t1, _t2 * _t7), java.lang.Math.fma(axisY, _t1, _t2 * _t3));
    }

    /** Piece 2 of {@code rotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_fma_s9c8ff010_1(java.lang.foreign.MemorySegment dest, long destOffset, float axisX, float axisY, float axisZ, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3, float _t6, float _t7, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = java.lang.Math.fma(_t2, _t3, -(axisY * _t1));
        float _t25 = java.lang.Math.fma(_t2, _t6, -(axisZ * _t1));
        float _t26 = java.lang.Math.fma(_t2, _t7, -(axisX * _t1));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26)));
        return rotateAxis_api_fma_s9c8ff010_2(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _t20, _t23, _t26);
    }

    /** Piece 3 of {@code rotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_fma_s9c8ff010_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _t20, float _t23, float _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisZ;
        float _t6 = axisX * axisY;
        float _t7 = axisY * axisZ;
        return rotateAxis_api_mulAdd_s83ffe19_1(dest, destOffset, axisX, axisZ, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t6, _t7, ((_t2) * (axisX * axisX) + (_t0)), ((_t2) * (axisY * axisY) + (_t0)), ((_t2) * (axisZ * axisZ) + (_t0)), ((axisZ) * (_t1) + (_t2 * _t6)), ((axisX) * (_t1) + (_t2 * _t7)), ((axisY) * (_t1) + (_t2 * _t3)), ((_t2) * (_t3) - (axisY * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd_s83ffe19_1(java.lang.foreign.MemorySegment dest, long destOffset, float axisX, float axisZ, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t6, float _t7, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
        float _t25 = ((_t2) * (_t6) - (axisZ * _t1));
        float _t26 = ((_t2) * (_t7) - (axisX * _t1));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        Float3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@code rotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = 1.0f - _t0;
        return rotateAxis_api_fma_s2940bfe9_1(dest, destOffset, Math.sinWithCos(angle), _axisx, _axisy, _axisz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, java.lang.Math.fma(_t2, _axisx * _axisx, _t0), java.lang.Math.fma(_t2, _axisy * _axisy, _t0), java.lang.Math.fma(_t2, _axisz * _axisz, _t0));
    }

    /** Piece 2 of {@code rotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_fma_s2940bfe9_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t1, float _axisx, float _axisy, float _axisz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3, float _t6, float _t7, float _t18, float _t19, float _t20) {
        float _t21 = java.lang.Math.fma(_axisz, _t1, _t2 * _t6);
        float _t22 = java.lang.Math.fma(_axisx, _t1, _t2 * _t7);
        float _t24 = java.lang.Math.fma(_t2, _t3, -(_axisy * _t1));
        float _t25 = java.lang.Math.fma(_t2, _t6, -(_axisz * _t1));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19)));
        return rotateAxis_api_fma_s2940bfe9_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t20, java.lang.Math.fma(_axisy, _t1, _t2 * _t3), java.lang.Math.fma(_t2, _t7, -(_axisx * _t1)));
    }

    /** Piece 3 of {@code rotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_fma_s2940bfe9_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t20, float _t23, float _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t2 = 1.0f - _t0;
        return rotateAxis_api_mulAdd_s306cec98_1(dest, destOffset, Math.sinWithCos(angle), _axisx, _axisy, _axisz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, ((_t2) * (_axisx * _axisx) + (_t0)), ((_t2) * (_axisy * _axisy) + (_t0)), ((_t2) * (_axisz * _axisz) + (_t0)));
    }

    /** Piece 2 of {@code rotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd_s306cec98_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t1, float _axisx, float _axisy, float _axisz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3, float _t6, float _t7, float _t18, float _t19, float _t20) {
        float _t21 = ((_axisz) * (_t1) + (_t2 * _t6));
        float _t22 = ((_axisx) * (_t1) + (_t2 * _t7));
        float _t24 = ((_t2) * (_t3) - (_axisy * _t1));
        float _t25 = ((_t2) * (_t6) - (_axisz * _t1));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19)))));
        return rotateAxis_api_mulAdd_s306cec98_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t20, ((_axisy) * (_t1) + (_t2 * _t3)), ((_t2) * (_t7) - (_axisx * _t1)));
    }

    /** Piece 3 of {@code rotateAxis_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd_s306cec98_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t20, float _t23, float _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        return rotateTowards_api_fma_sb2b6f81a_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t17, _t12, upX), java.lang.Math.fma(_t17, _t13, upY), java.lang.Math.fma(_t17, _t11, upZ));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_sb2b6f81a_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        return rotateTowards_api_fma_sb2b6f81a_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)), java.lang.Math.fma(_t37, _t13, -(_t39 * _t11)), java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_sb2b6f81a_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        return rotateTowards_api_mulAdd_s4589d06f_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t12) + (upX)), ((_t17) * (_t13) + (upY)), ((_t17) * (_t11) + (upZ)));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s4589d06f_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        return rotateTowards_api_mulAdd_s4589d06f_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t39) * (_t12) - (_t38 * _t13)), ((_t37) * (_t13) - (_t39 * _t11)), ((_t38) * (_t11) - (_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s4589d06f_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return rotateTowards_api_fma_sf6dd6e00_1(dest, destOffset, _dirx, _diry, _dirz, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry)));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_sf6dd6e00_1(java.lang.foreign.MemorySegment dest, long destOffset, float _dirx, float _diry, float _dirz, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t4) {
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        return rotateTowards_api_fma_sf6dd6e00_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_sf6dd6e00_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46) {
        float _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        float _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        return rotateTowards_api_fma_sf6dd6e00_3(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 4 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_sf6dd6e00_3(java.lang.foreign.MemorySegment dest, long destOffset, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _t11, float _t12, float _t13) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return rotateTowards_api_mulAdd_s7e047ee3_1(dest, destOffset, _dirx, _diry, _dirz, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry)))));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s7e047ee3_1(java.lang.foreign.MemorySegment dest, long destOffset, float _dirx, float _diry, float _dirz, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t4) {
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        return rotateTowards_api_mulAdd_s7e047ee3_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, ((_t39) * (_t12) - (_t38 * _t13)), ((_t37) * (_t13) - (_t39 * _t11)), ((_t38) * (_t11) - (_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s7e047ee3_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        return rotateTowards_api_fma_s4c25b696_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t17, _t12, upX), java.lang.Math.fma(_t17, _t13, upY), java.lang.Math.fma(_t17, _t11, upZ));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_s4c25b696_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        return rotateTowards_api_fma_s4c25b696_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)), java.lang.Math.fma(_t37, _t13, -(_t39 * _t11)), java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_s4c25b696_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        return rotateTowards_api_mulAdd_s63e9b425_1(dest, destOffset, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t17) * (_t12) + (upX)), ((_t17) * (_t13) + (upY)), ((_t17) * (_t11) + (upZ)));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s63e9b425_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        return rotateTowards_api_mulAdd_s63e9b425_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t39) * (_t12) - (_t38 * _t13)), ((_t37) * (_t13) - (_t39 * _t11)), ((_t38) * (_t11) - (_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s63e9b425_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Float3x3OpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return rotateTowards_api_fma_sbb2742d4_1(dest, destOffset, epsilon, _dirx, _diry, _dirz, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry)));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_sbb2742d4_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _dirx, float _diry, float _dirz, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t4) {
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        return rotateTowards_api_fma_sbb2742d4_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_sbb2742d4_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46) {
        float _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        float _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        return rotateTowards_api_fma_sbb2742d4_3(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 4 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_sbb2742d4_3(java.lang.foreign.MemorySegment dest, long destOffset, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _t11, float _t12, float _t13) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return rotateTowards_api_mulAdd_s3382d909_1(dest, destOffset, epsilon, _dirx, _diry, _dirz, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry)))));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s3382d909_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _dirx, float _diry, float _dirz, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t4) {
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        return rotateTowards_api_mulAdd_s3382d909_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, ((_t39) * (_t12) - (_t38 * _t13)), ((_t37) * (_t13) - (_t39 * _t11)), ((_t38) * (_t11) - (_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s3382d909_2(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateX_api_fma(dest, destOffset, src, srcOffset, angle);
        return rotateX_api_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    /** {@code rotateX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateX_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self01, _t0, _self02 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self11, _t0, _self12 * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self21, _t0, _self22 * _t1));
        return rotateX_api_fma_s9f9e4b3d_1(dest, destOffset, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code rotateX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateX_api_fma_s9f9e4b3d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t0, -(_self01 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t0, -(_self11 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t0, -(_self21 * _t1)));
        return dest;
    }

    /** {@code rotateX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self01) * (_t0) + (_self02 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self11) * (_t0) + (_self12 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self21) * (_t0) + (_self22 * _t1)));
        return rotateX_api_mulAdd_sca46583c_1(dest, destOffset, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code rotateX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateX_api_mulAdd_sca46583c_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t0) - (_self01 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t0) - (_self11 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t0) - (_self21 * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX180_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateX180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX180_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, -_self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, -_self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -_self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX270_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateX270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX270_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, -_self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_self22);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self21);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX90_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateX90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX90_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self22);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, -_self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -_self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_self21);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleY, float angleZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleY, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXYZ_api_fma(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return rotateXYZ_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@code rotateXYZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXYZ_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.cosWithSin(angleX);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleY);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t6 = _t2 * _t3;
        float _t8 = _t0 * _t2;
        return rotateXYZ_api_fma_sc2339cc7_1(dest, destOffset, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t5 * _t4, _t1 * _t5, _t3 * _t5, _t0 * _t5, java.lang.Math.fma(_t8, _t4, _t1 * _t3), java.lang.Math.fma(_t6, _t1, _t0 * _t4), java.lang.Math.fma(_t0, _t1, -(_t6 * _t4)), java.lang.Math.fma(_t3, _t4, -(_t8 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXYZ_api_fma_sc2339cc7_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t2, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t7, _self01 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t7, _self11 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t7, _self21 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self01, _t21, -(_self00 * _t11))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self11, _t21, -(_self10 * _t11))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self21, _t21, -(_self20 * _t11))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t2, -(_self01 * _t13))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t2, -(_self11 * _t13))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t2, -(_self21 * _t13))));
        return dest;
    }

    /** {@code rotateXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXYZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.cosWithSin(angleX);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleY);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t6 = _t2 * _t3;
        float _t8 = _t0 * _t2;
        return rotateXYZ_api_mulAdd_s39d68ffe_1(dest, destOffset, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t5 * _t4, _t1 * _t5, _t3 * _t5, _t0 * _t5, ((_t8) * (_t4) + (_t1 * _t3)), ((_t6) * (_t1) + (_t0 * _t4)), ((_t0) * (_t1) - (_t6 * _t4)), ((_t3) * (_t4) - (_t8 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXYZ_api_mulAdd_s39d68ffe_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t2, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t20) + (((_self00) * (_t7) + (_self01 * _t18)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t20) + (((_self10) * (_t7) + (_self11 * _t18)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t20) + (((_self20) * (_t7) + (_self21 * _t18)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t19) + (((_self01) * (_t21) - (_self00 * _t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t19) + (((_self11) * (_t21) - (_self10 * _t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t19) + (((_self21) * (_t21) - (_self20 * _t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t12) + (((_self00) * (_t2) - (_self01 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t12) + (((_self10) * (_t2) - (_self11 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t12) + (((_self20) * (_t2) - (_self21 * _t13)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleZ, float angleY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleZ, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXZY_api_fma(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return rotateXZY_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@code rotateXZY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXZY_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.cosWithSin(angleZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        return rotateXZY_api_fma_s52ae3281_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2 * _t5, _t0 * _t5, _t4 * _t5, _t3 * _t5, java.lang.Math.fma(_t9, _t2, _t0 * _t3), java.lang.Math.fma(_t6, _t3, _t4 * _t2), java.lang.Math.fma(_t6, _t2, -(_t3 * _t4)), java.lang.Math.fma(_t9, _t3, -(_t0 * _t2)));
    }

    /** Piece 2 of {@code rotateXZY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXZY_api_fma_s52ae3281_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t8, float _t11, float _t12, float _t14, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t8, _self01 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t8, _self11 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t8, _self21 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self01, _t12, -(_self00 * _t1))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self11, _t12, -(_self10 * _t1))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self21, _t12, -(_self20 * _t1))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t14, _self01 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t14, _self11 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t14, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXZY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.cosWithSin(angleZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        return rotateXZY_api_mulAdd_s74d50030_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2 * _t5, _t0 * _t5, _t4 * _t5, _t3 * _t5, ((_t9) * (_t2) + (_t0 * _t3)), ((_t6) * (_t3) + (_t4 * _t2)), ((_t6) * (_t2) - (_t3 * _t4)), ((_t9) * (_t3) - (_t0 * _t2)));
    }

    /** Piece 2 of {@code rotateXZY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXZY_api_mulAdd_s74d50030_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t8, float _t11, float _t12, float _t14, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t20) + (((_self00) * (_t8) + (_self01 * _t18)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t20) + (((_self10) * (_t8) + (_self11 * _t18)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t20) + (((_self20) * (_t8) + (_self21 * _t18)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t11) + (((_self01) * (_t12) - (_self00 * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t11) + (((_self11) * (_t12) - (_self10 * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t11) + (((_self21) * (_t12) - (_self20 * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t19) + (((_self00) * (_t14) + (_self01 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t19) + (((_self10) * (_t14) + (_self11 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t19) + (((_self20) * (_t14) + (_self21 * _t21)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateY_api_fma(dest, destOffset, src, srcOffset, angle);
        return rotateY_api_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    /** {@code rotateY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateY_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self00, _t0, -(_self02 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _t0, -(_self12 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self20, _t0, -(_self22 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        return rotateY_api_fma_sb41551ca_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code rotateY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateY_api_fma_sb41551ca_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self00, float _self10, float _self20, float _self21, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self00, _t1, _self02 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self10, _t1, _self12 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self20, _t1, _self22 * _t0));
        return dest;
    }

    /** {@code rotateY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self00) * (_t0) - (_self02 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_t0) - (_self12 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self20) * (_t0) - (_self22 * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        return rotateY_api_mulAdd_sccacd895_1(dest, destOffset, _t0, _t1, _self00, _self10, _self20, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code rotateY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateY_api_mulAdd_sccacd895_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _self00, float _self10, float _self20, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self00) * (_t1) + (_self02 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self10) * (_t1) + (_self12 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self20) * (_t1) + (_self22 * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY180_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateY180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY180_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, -_self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -_self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY270_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateY270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY270_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self22);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, -_self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, -_self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, -_self20);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY90_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateY90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY90_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_self22);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self20);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_api_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@code rotateYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYXZ_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        return rotateYXZ_api_fma_s206fa85d_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2 * _t5, _t5 * _t4, _t5 * _t1, _t3 * _t5, java.lang.Math.fma(_t8, _t2, _t1 * _t4), java.lang.Math.fma(_t6, _t4, _t3 * _t2), java.lang.Math.fma(_t6, _t2, -(_t3 * _t4)), java.lang.Math.fma(_t8, _t4, -(_t2 * _t1)));
    }

    /** Piece 2 of {@code rotateYXZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYXZ_api_fma_s206fa85d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t13, float _t14, float _t15, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t18, _self01 * _t10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t18, _self11 * _t10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t18, _self21 * _t10)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t21, _self01 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t21, _self11 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t21, _self21 * _t13)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t14, java.lang.Math.fma(_self00, _t15, -(_self01 * _t0))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t14, java.lang.Math.fma(_self10, _t15, -(_self11 * _t0))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self20, _t15, -(_self21 * _t0))));
        return dest;
    }

    /** {@code rotateYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYXZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        return rotateYXZ_api_mulAdd_sa96a4214_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2 * _t5, _t5 * _t4, _t5 * _t1, _t3 * _t5, ((_t8) * (_t2) + (_t1 * _t4)), ((_t6) * (_t4) + (_t3 * _t2)), ((_t6) * (_t2) - (_t3 * _t4)), ((_t8) * (_t4) - (_t2 * _t1)));
    }

    /** Piece 2 of {@code rotateYXZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYXZ_api_mulAdd_sa96a4214_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t13, float _t14, float _t15, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_self02) * (_t20) + (((_self00) * (_t18) + (_self01 * _t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_t20) + (((_self10) * (_t18) + (_self11 * _t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_t20) + (((_self20) * (_t18) + (_self21 * _t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t19) + (((_self00) * (_t21) + (_self01 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t19) + (((_self10) * (_t21) + (_self11 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t19) + (((_self20) * (_t21) + (_self21 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t14) + (((_self00) * (_t15) - (_self01 * _t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t14) + (((_self10) * (_t15) - (_self11 * _t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t14) + (((_self20) * (_t15) - (_self21 * _t0)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleZ, float angleX) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleZ, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYZX_api_fma(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return rotateYZX_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@code rotateYZX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYZX_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t8 = _t0 * _t3;
        float _t10 = _t3 * _t2;
        return rotateYZX_api_fma_s4f01f7f5_1(dest, destOffset, _t3, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t0 * _t1, _t2 * _t1, _t4 * _t1, _t5 * _t1, java.lang.Math.fma(_t8, _t4, _t5 * _t2), java.lang.Math.fma(_t10, _t5, _t0 * _t4), java.lang.Math.fma(_t5, _t0, -(_t10 * _t4)), java.lang.Math.fma(_t4, _t2, -(_t8 * _t5)));
    }

    /** Piece 2 of {@code rotateYZX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYZX_api_fma_s4f01f7f5_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t3, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t7, float _t11, float _t13, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, -_t6, java.lang.Math.fma(_self00, _t7, _self01 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, -_t6, java.lang.Math.fma(_self10, _t7, _self11 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, -_t6, java.lang.Math.fma(_self20, _t7, _self21 * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t18, java.lang.Math.fma(_self00, _t20, _self01 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t18, java.lang.Math.fma(_self10, _t20, _self11 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t18, java.lang.Math.fma(_self20, _t20, _self21 * _t11)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t21, java.lang.Math.fma(_self00, _t19, -(_self01 * _t13))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t21, java.lang.Math.fma(_self10, _t19, -(_self11 * _t13))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self20, _t19, -(_self21 * _t13))));
        return dest;
    }

    /** {@code rotateYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYZX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t8 = _t0 * _t3;
        float _t10 = _t3 * _t2;
        return rotateYZX_api_mulAdd_s8d04c974_1(dest, destOffset, _t3, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t0 * _t1, _t2 * _t1, _t4 * _t1, _t5 * _t1, ((_t8) * (_t4) + (_t5 * _t2)), ((_t10) * (_t5) + (_t0 * _t4)), ((_t5) * (_t0) - (_t10 * _t4)), ((_t4) * (_t2) - (_t8 * _t5)));
    }

    /** Piece 2 of {@code rotateYZX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYZX_api_mulAdd_s8d04c974_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t3, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t7, float _t11, float _t13, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((((_self00) * (_t7) + (_self01 * _t3))) - (_self02) * (_t6)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((((_self10) * (_t7) + (_self11 * _t3))) - (_self12) * (_t6)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((((_self20) * (_t7) + (_self21 * _t3))) - (_self22) * (_t6)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t18) + (((_self00) * (_t20) + (_self01 * _t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t18) + (((_self10) * (_t20) + (_self11 * _t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t18) + (((_self20) * (_t20) + (_self21 * _t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t21) + (((_self00) * (_t19) - (_self01 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t21) + (((_self10) * (_t19) - (_self11 * _t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t21) + (((_self20) * (_t19) - (_self21 * _t13)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ180_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateZ180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ180_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, -_self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ270_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateZ270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ270_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ90_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateZ90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ90_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, -_self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, -_self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleX, float angleY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleX, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZXY_api_fma(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return rotateZXY_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@code rotateZXY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZXY_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleX);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.cosWithSin(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.sinWithCos(angleZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t7 = _t4 * _t5;
        float _t8 = _t4 * _t3;
        return rotateZXY_api_fma_s326912a5_1(dest, destOffset, _t4, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t0 * _t1, _t1 * _t3, _t5 * _t1, _t1 * _t2, java.lang.Math.fma(_t8, _t0, _t5 * _t2), java.lang.Math.fma(_t7, _t2, _t0 * _t3), java.lang.Math.fma(_t2, _t3, -(_t7 * _t0)), java.lang.Math.fma(_t0, _t5, -(_t8 * _t2)));
    }

    /** Piece 2 of {@code rotateZXY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZXY_api_fma_s326912a5_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t4, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t10, float _t11, float _t12, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, -_t6, java.lang.Math.fma(_self00, _t20, _self01 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, -_t6, java.lang.Math.fma(_self10, _t20, _self11 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, -_t6, java.lang.Math.fma(_self20, _t20, _self21 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t4, java.lang.Math.fma(_self01, _t10, -(_self00 * _t11))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t4, java.lang.Math.fma(_self11, _t10, -(_self10 * _t11))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t4, java.lang.Math.fma(_self21, _t10, -(_self20 * _t11))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t19, _self01 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t19, _self11 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZXY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleX);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.cosWithSin(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.sinWithCos(angleZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t7 = _t4 * _t5;
        float _t8 = _t4 * _t3;
        return rotateZXY_api_mulAdd_s550b58bc_1(dest, destOffset, _t4, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t0 * _t1, _t1 * _t3, _t5 * _t1, _t1 * _t2, ((_t8) * (_t0) + (_t5 * _t2)), ((_t7) * (_t2) + (_t0 * _t3)), ((_t2) * (_t3) - (_t7 * _t0)), ((_t0) * (_t5) - (_t8 * _t2)));
    }

    /** Piece 2 of {@code rotateZXY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZXY_api_mulAdd_s550b58bc_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t4, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t10, float _t11, float _t12, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((((_self00) * (_t20) + (_self01 * _t18))) - (_self02) * (_t6)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((((_self10) * (_t20) + (_self11 * _t18))) - (_self12) * (_t6)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((((_self20) * (_t20) + (_self21 * _t18))) - (_self22) * (_t6)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t4) + (((_self01) * (_t10) - (_self00 * _t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t4) + (((_self11) * (_t10) - (_self10 * _t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t4) + (((_self21) * (_t10) - (_self20 * _t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t12) + (((_self00) * (_t19) + (_self01 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t12) + (((_self10) * (_t19) + (_self11 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t12) + (((_self20) * (_t19) + (_self21 * _t21)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleY, float angleX) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleY, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZYX_api_fma(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return rotateZYX_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@code rotateZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZYX_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.cosWithSin(angleZ);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t9 = _t0 * _t2;
        float _t11 = _t0 * _t3;
        return rotateZYX_api_fma_sc38897a3_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t1 * _t2, _t3 * _t1, _t4 * _t1, _t5 * _t1, java.lang.Math.fma(_t11, _t4, _t5 * _t2), java.lang.Math.fma(_t9, _t5, _t4 * _t3), java.lang.Math.fma(_t9, _t4, -(_t3 * _t5)), java.lang.Math.fma(_t11, _t5, -(_t4 * _t2)));
    }

    /** Piece 2 of {@code rotateZYX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZYX_api_fma_sc38897a3_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t7, float _t8, float _t13, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_self02, -_t0, java.lang.Math.fma(_self00, _t6, _self01 * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, -_t0, java.lang.Math.fma(_self10, _t6, _self11 * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, -_t0, java.lang.Math.fma(_self20, _t6, _self21 * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_self02, _t8, java.lang.Math.fma(_self00, _t20, _self01 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_self12, _t8, java.lang.Math.fma(_self10, _t20, _self11 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(_self22, _t8, java.lang.Math.fma(_self20, _t20, _self21 * _t18)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t19, _self01 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t19, _self11 * _t21)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZYX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.cosWithSin(angleZ);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t9 = _t0 * _t2;
        float _t11 = _t0 * _t3;
        return rotateZYX_api_mulAdd_s842a822a_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t1 * _t2, _t3 * _t1, _t4 * _t1, _t5 * _t1, ((_t11) * (_t4) + (_t5 * _t2)), ((_t9) * (_t5) + (_t4 * _t3)), ((_t9) * (_t4) - (_t3 * _t5)), ((_t11) * (_t5) - (_t4 * _t2)));
    }

    /** Piece 2 of {@code rotateZYX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZYX_api_mulAdd_s842a822a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t7, float _t8, float _t13, float _t18, float _t19, float _t20, float _t21) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((((_self00) * (_t6) + (_self01 * _t7))) - (_self02) * (_t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((((_self10) * (_t6) + (_self11 * _t7))) - (_self12) * (_t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((((_self20) * (_t6) + (_self21 * _t7))) - (_self22) * (_t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_self02) * (_t8) + (((_self00) * (_t20) + (_self01 * _t18)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((_self12) * (_t8) + (((_self10) * (_t20) + (_self11 * _t18)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((_self22) * (_t8) + (((_self20) * (_t20) + (_self21 * _t18)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self02) * (_t13) + (((_self00) * (_t19) + (_self01 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self12) * (_t13) + (((_self10) * (_t19) + (_self11 * _t21)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self22) * (_t13) + (((_self20) * (_t19) + (_self21 * _t21)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10 * vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20 * vX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01 * vY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11 * vY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21 * vY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10 * _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20 * _vx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01 * _vy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11 * _vy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21 * _vy);
        return scale_api_sf1c5038f_1(dest, destOffset, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code scale_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scale_api_sf1c5038f_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, s * _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_fma(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@code scaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = 1.0f - s;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, s * _self21);
        return scaleAround_api_fma_sc0937ce2_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, pivotX * _t0, pivotY * _t0);
    }

    /** Piece 2 of {@code scaleAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_fma_sc0937ce2_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t1, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0 = 1.0f - s;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, s * _self21);
        return scaleAround_api_mulAdd_sa6611dc7_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, pivotX * _t0, pivotY * _t0);
    }

    /** Piece 2 of {@code scaleAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd_sa6611dc7_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t1, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self20) * (_t1) + (((_self21) * (_t2) + (_self22)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@code scaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return scaleAround_api_fma_s44aaad18_1(dest, destOffset, pivot, pivotOffset, s, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, 1.0f - s);
    }

    /** Piece 2 of {@code scaleAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_fma_s44aaad18_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t0) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t1 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * _t0;
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, s * _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return scaleAround_api_mulAdd_s312deed7_1(dest, destOffset, pivot, pivotOffset, s, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, 1.0f - s);
    }

    /** Piece 2 of {@code scaleAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd_s312deed7_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t0) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t1 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * _t0;
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, s * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, s * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, s * _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, s * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, s * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, s * _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self20) * (_t1) + (((_self21) * (_t2) + (_self22)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_fma(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@code scaleAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, sX * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, sX * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, sX * _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, sY * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, sY * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, sY * _self21);
        return scaleAround_api_fma_s296aaa5e_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, pivotX * (1.0f - sX), pivotY * (1.0f - sY));
    }

    /** Piece 2 of {@code scaleAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_fma_s296aaa5e_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self20, _t2, java.lang.Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, sX * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, sX * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, sX * _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, sY * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, sY * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, sY * _self21);
        return scaleAround_api_mulAdd_scc9265f5_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, pivotX * (1.0f - sX), pivotY * (1.0f - sY));
    }

    /** Piece 2 of {@code scaleAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd_scc9265f5_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self20) * (_t2) + (((_self21) * (_t3) + (_self22)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
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
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return scaleAround_api_fma_sf0698318_1(dest, destOffset, pivot, pivotOffset, _sx, _sy, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code scaleAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_fma_sf0698318_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float _sx, float _sy, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * (1.0f - _sy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sx * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sx * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sx * _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sy * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sy * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sy * _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self20, _t2, java.lang.Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _sx = s.get(_JAVA_FLOAT_UNALIGNED, sOffset);
        float _sy = s.get(_JAVA_FLOAT_UNALIGNED, sOffset + 4L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        return scaleAround_api_mulAdd_s9e3b2eab_1(dest, destOffset, pivot, pivotOffset, _sx, _sy, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code scaleAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_mulAdd_s9e3b2eab_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float _sx, float _sy, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t2 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * (1.0f - _sy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sx * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sx * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sx * _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sy * _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sy * _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sy * _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self20) * (_t2) + (((_self21) * (_t3) + (_self22)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_fma(dest, destOffset, src, srcOffset, vX, vY);
        return translate_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@code translate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
        return translate_api_fma_sbd8db87e_1(dest, destOffset, vX, vY, _self10, _self20, _self11, _self21, _self12, _self22);
    }

    /** Piece 2 of {@code translate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_fma_sbd8db87e_1(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self20, vX, java.lang.Math.fma(_self21, vY, _self22)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
        return translate_api_mulAdd_s52aae813_1(dest, destOffset, vX, vY, _self10, _self20, _self11, _self21, _self12, _self22);
    }

    /** Piece 2 of {@code translate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_mulAdd_s52aae813_1(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self20) * (vX) + (((_self21) * (vY) + (_self22)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return translate_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@code translate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        return translate_api_fma_s650cc8a8_1(dest, destOffset, _vx, _vy, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code translate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_fma_s650cc8a8_1(java.lang.foreign.MemorySegment dest, long destOffset, float _vx, float _vy, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(_self20, _vx, java.lang.Math.fma(_self21, _vy, _self22)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _self01);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        return translate_api_mulAdd_sd24e22b_1(dest, destOffset, _vx, _vy, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code translate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_mulAdd_sd24e22b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _vx, float _vy, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((_self20) * (_vx) + (((_self21) * (_vy) + (_self22)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment view_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float left, float right, float bottom, float top) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.lang.foreign.MemorySegment view_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float left, float right, float bottom, float top) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self00 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _self00);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _self10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _self20);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _self01);
        return view_api_se1b4419a_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _sp1, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code view_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment view_api_se1b4419a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _sp1, float _sp2, float _sp3) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, _sp1 * _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _sp1 * _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22 + (-(_self20 * _sp2) - _self21 * _sp3));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L), vZ, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), vX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * vY)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, vZ, java.lang.Math.fma(_self10, vX, _self11 * vY)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, vZ, java.lang.Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    /** {@code mulVec3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulVec3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L)) * (vZ) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (vX) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * vY)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (vZ) + (((_self10) * (vX) + (_self11 * vY)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (vZ) + (((_self20) * (vX) + (_self21 * vY)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_api_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return mulVec3_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@code mulVec3_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulVec3_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L), _vz, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _vx, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * _vy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self12, _vz, java.lang.Math.fma(_self10, _vx, _self11 * _vy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_self22, _vz, java.lang.Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    /** {@code mulVec3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mulVec3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L)) * (_vz) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_vx) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * _vy)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self12) * (_vz) + (((_self10) * (_vx) + (_self11 * _vy)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_self22) * (_vz) + (((_self20) * (_vx) + (_self21 * _vy)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), vX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * vY));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (vX) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * vY)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment transformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
            float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _vx, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * _vy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
            float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
            float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_vx) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * _vy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (_vx) + (_self11 * _vy)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment transformPosition_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformPosition_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), vX, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L), vY, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
        return dest;
    }

    /** {@code transformPosition_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment transformPosition_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (vX) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L)) * (vY) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Float3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformPosition_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _vx, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L), _vy, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    /** {@code transformPosition_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment transformPosition_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _self10 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self11 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self12 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_vx) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L)) * (_vy) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 24L))))));
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
