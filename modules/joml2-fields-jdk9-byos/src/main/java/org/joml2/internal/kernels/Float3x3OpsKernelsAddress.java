// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3x3Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float3x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float3x3OpsKernelsAddress {
    private Float3x3OpsKernelsAddress() {}

    public static long getColumn_unsafe(long dest, long src, int col) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; _idxSw2 = _self20; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; _idxSw2 = _self21; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        UnsafeOpsHolder.U.putFloat(dest, _idxSw0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _idxSw1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _idxSw2);
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_unsafe_mulAdd(dest, src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = java.lang.Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 <= java.lang.Math.fma(_self02, _self02, _t1) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(java.lang.Math.fma(_self10, _self22, -_self12 * _self20), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_mulAdd(long dest, long src) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = ((_self12) * (_self12) + (_self22 * _self22));
        if (_t1 <= ((_self02) * (_self02) + (_t1)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(((_self10) * (_self22) - (_self12 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_unsafe_mulAdd(dest, src, epsilon);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = java.lang.Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 <= epsilon * java.lang.Math.fma(_self02, _self02, _t1)) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(java.lang.Math.fma(_self10, _self22, -_self12 * _self20), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = ((_self12) * (_self12) + (_self22 * _self22));
        if (_t1 <= epsilon * ((_self02) * (_self02) + (_t1))) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(((_self10) * (_self22) - (_self12 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_unsafe_mulAdd(dest, src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = java.lang.Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 <= java.lang.Math.fma(_self01, _self01, _t1) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(java.lang.Math.fma(_self10, _self21, -(_self11 * _self20)), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_mulAdd(long dest, long src) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = ((_self11) * (_self11) + (_self21 * _self21));
        if (_t1 <= ((_self01) * (_self01) + (_t1)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(((_self10) * (_self21) - (_self11 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_unsafe_mulAdd(dest, src, epsilon);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = java.lang.Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 <= epsilon * java.lang.Math.fma(_self01, _self01, _t1)) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(java.lang.Math.fma(_self10, _self21, -(_self11 * _self20)), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = ((_self11) * (_self11) + (_self21 * _self21));
        if (_t1 <= epsilon * ((_self01) * (_self01) + (_t1))) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(((_self10) * (_self21) - (_self11 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_unsafe_mulAdd(dest, src);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = java.lang.Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 <= java.lang.Math.fma(_self12, _self12, _t1) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, _self00));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = ((_self02) * (_self02) + (_self22 * _self22));
        if (_t1 <= ((_self12) * (_self12) + (_t1)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, _self00));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(((_self02) * (_self21) - (_self01 * _self22)), ((_self00) * (_self22) - (_self02 * _self20))));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_unsafe_mulAdd(dest, src, epsilon);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = java.lang.Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 <= epsilon * java.lang.Math.fma(_self12, _self12, _t1)) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, _self00));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = ((_self02) * (_self02) + (_self22 * _self22));
        if (_t1 <= epsilon * ((_self12) * (_self12) + (_t1))) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, _self00));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(((_self02) * (_self21) - (_self01 * _self22)), ((_self00) * (_self22) - (_self02 * _self20))));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_unsafe_mulAdd(dest, src);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self20 * _self20);
        if (_t1 <= java.lang.Math.fma(_self10, _self10, _t1) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, _self22));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(java.lang.Math.fma(_self00, _self21, _self01 * -_self20), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = ((_self00) * (_self00) + (_self20 * _self20));
        if (_t1 <= ((_self10) * (_self10) + (_t1)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, _self22));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(((_self00) * (_self21) - (_self01 * _self20)), ((_self00) * (_self22) - (_self02 * _self20))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_unsafe_mulAdd(dest, src, epsilon);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self20 * _self20);
        if (_t1 <= epsilon * java.lang.Math.fma(_self10, _self10, _t1)) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, _self22));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(java.lang.Math.fma(_self00, _self21, _self01 * -_self20), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = ((_self00) * (_self00) + (_self20 * _self20));
        if (_t1 <= epsilon * ((_self10) * (_self10) + (_t1))) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, _self22));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(((_self00) * (_self21) - (_self01 * _self20)), ((_self00) * (_self22) - (_self02 * _self20))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_unsafe_mulAdd(dest, src);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _t1 = java.lang.Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 <= java.lang.Math.fma(_self21, _self21, _t1) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, _self00));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(java.lang.Math.fma(_self02, _self11, -_self01 * _self12), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, _self11));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _t1 = ((_self01) * (_self01) + (_self11 * _self11));
        if (_t1 <= ((_self21) * (_self21) + (_t1)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, _self00));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(((_self02) * (_self11) - (_self01 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, _self11));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_unsafe_mulAdd(dest, src, epsilon);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _t1 = java.lang.Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 <= epsilon * java.lang.Math.fma(_self21, _self21, _t1)) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, _self00));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(java.lang.Math.fma(_self02, _self11, -_self01 * _self12), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, _self11));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _t1 = ((_self01) * (_self01) + (_self11 * _self11));
        if (_t1 <= epsilon * ((_self21) * (_self21) + (_t1))) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, _self00));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(((_self02) * (_self11) - (_self01 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, _self11));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_unsafe_mulAdd(dest, src);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self10 * _self10);
        if (_t1 <= java.lang.Math.fma(_self20, _self20, _t1) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, _self11));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(java.lang.Math.fma(_self02, _self10, -(_self00 * _self12)), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _t1 = ((_self00) * (_self00) + (_self10 * _self10));
        if (_t1 <= ((_self20) * (_self20) + (_t1)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, _self11));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(((_self02) * (_self10) - (_self00 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_unsafe_mulAdd(dest, src, epsilon);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _t1 = java.lang.Math.fma(_self00, _self00, _self10 * _self10);
        if (_t1 <= epsilon * java.lang.Math.fma(_self20, _self20, _t1)) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, _self11));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(java.lang.Math.fma(_self02, _self10, -(_self00 * _self12)), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _t1 = ((_self00) * (_self00) + (_self10 * _self10));
        if (_t1 <= epsilon * ((_self20) * (_self20) + (_t1))) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, _self11));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(((_self02) * (_self10) - (_self00 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getNormalizedRotation_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getNormalizedRotation_unsafe_fma(dest, src);
        return getNormalizedRotation_unsafe_mulAdd(dest, src);
    }

    /** {@code getNormalizedRotation_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getNormalizedRotation_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        float _t7 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        float _t8 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t6));
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
        return getNormalizedRotation_unsafe_fma_s451db3c2_1(dest, _self00, _self10, _self20, _self02, _self12, _self22, _t7, _t8, (1.0f / (float) java.lang.Math.sqrt(_t7)), (1.0f / (float) java.lang.Math.sqrt(_t8)), _t21, _t23, _t27);
    }

    /** Piece 2 of {@code getNormalizedRotation_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_fma_s451db3c2_1(long dest, float _self00, float _self10, float _self20, float _self02, float _self12, float _self22, float _t7, float _t8, float _t10, float _t11, float _t21, float _t23, float _t27) {
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
        float _t58 = _t52 + _t26;
        float _t62 = 1.0f + _t58;
        float _t64 = 1.0f + (_t23 - (_t49 + _t26));
        float _t65 = 1.0f + (_t26 - _t52);
        return getNormalizedRotation_unsafe_fma_s451db3c2_2(dest, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, 1.0f + (_t49 - (_t23 + _t26)), _t64, _t65, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t62)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)));
    }

    /** Piece 3 of {@code getNormalizedRotation_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_fma_s451db3c2_2(long dest, float _t23, float _t26, float _t36, float _t39, float _t49, float _t53, float _t55, float _t56, float _t57, float _t58, float _t62, float _t63, float _t64, float _t65, float _sp0, float _sp1, float _sp2) {
        return getNormalizedRotation_unsafe_fma_s451db3c2_3(dest, _t23, _t26, _t36, _t39, _t49, _t53, _t55, _t56, _t57, _t58, _t62, _t63, _t64, _t65, _sp0, _sp1, _sp2, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)));
    }

    /** Piece 4 of {@code getNormalizedRotation_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_fma_s451db3c2_3(long dest, float _t23, float _t26, float _t36, float _t39, float _t49, float _t53, float _t55, float _t56, float _t57, float _t58, float _t62, float _t63, float _t64, float _t65, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t58 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t36);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t56);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t57);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t63));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t53);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t55);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t53);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t64));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t39);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t56);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t55);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t39);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    /** {@code getNormalizedRotation_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getNormalizedRotation_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        float _t7 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        float _t8 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t7));
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
        return getNormalizedRotation_unsafe_mulAdd_s4ddaf603_1(dest, _self00, _self10, _self20, _t8, (1.0f / (float) java.lang.Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_mulAdd_s4ddaf603_1(long dest, float _self00, float _self10, float _self20, float _t8, float _t11, float _t21, float _t23, float _t27, float _t22, float _t24, float _t26) {
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
        float _t58 = _t52 + _t26;
        float _t62 = 1.0f + _t58;
        float _t63 = 1.0f + (_t49 - (_t23 + _t26));
        float _t64 = 1.0f + (_t23 - (_t49 + _t26));
        float _t65 = 1.0f + (_t26 - _t52);
        return getNormalizedRotation_unsafe_mulAdd_s4ddaf603_2(dest, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t62)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_mulAdd_s4ddaf603_2(long dest, float _t23, float _t26, float _t36, float _t39, float _t49, float _t53, float _t55, float _t56, float _t57, float _t58, float _t62, float _t63, float _t64, float _t65, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t58 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t36);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t56);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t57);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t63));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t53);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t55);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t53);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t64));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t39);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t56);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t55);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t39);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static long getRow_unsafe(long dest, long src, int row) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            case 2: _idxSw0 = _self20; _idxSw1 = _self21; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        UnsafeOpsHolder.U.putFloat(dest, _idxSw0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _idxSw1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _idxSw2);
        return dest;
    }

    public static long getScale_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getScale_unsafe_mulAdd(dest, src);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, (float) java.lang.Math.sqrt(java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) java.lang.Math.sqrt(java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) java.lang.Math.sqrt(java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    /** {@code getScale_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getScale_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, (float) java.lang.Math.sqrt(((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) java.lang.Math.sqrt(((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) java.lang.Math.sqrt(((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))))));
        return dest;
    }

    public static long getTranslation_unsafe(long dest, long src) {
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        UnsafeOpsHolder.U.putFloat(dest, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self12);
        return dest;
    }

    public static long getUnnormalizedRotation_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0 = _self00 + _self11;
        float _t10 = _self22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_self00 - (_self11 + _self22));
        float _t16 = 1.0f + (_self11 - (_self00 + _self22));
        float _t17 = 1.0f + (_self22 - _t0);
        return getUnnormalizedRotation_unsafe_sd9cfa7f4_1(dest, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long getUnnormalizedRotation_unsafe_sd9cfa7f4_1(long dest, float _self00, float _self11, float _self22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t1);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t7);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t9);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t15));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t4);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t6);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t4);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t8);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t7);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t6);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t8);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static long cofactor_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cofactor_unsafe_fma(dest, src);
        return cofactor_unsafe_mulAdd(dest, src);
    }

    /** {@code cofactor_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cofactor_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self11, _self22, -_self12 * _self21));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self01, _self12, _self02 * -_self11));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self12, _self20, -_self10 * _self22));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self00, _self22, -(_self02 * _self20)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self02, _self10, _self00 * -_self12));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self10, _self21, -_self11 * _self20));
        return cofactor_unsafe_fma_s48a7b02a_1(dest, _self00, _self10, _self20, _self01, _self11, _self21);
    }

    /** Piece 2 of {@code cofactor_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long cofactor_unsafe_fma_s48a7b02a_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self01, _self20, -(_self00 * _self21)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self00, _self11, _self01 * -_self10));
        return dest;
    }

    /** {@code cofactor_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cofactor_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, ((_self11) * (_self22) - (_self12 * _self21)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self02) * (_self21) - (_self01 * _self22)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self01) * (_self12) - (_self02 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self12) * (_self20) - (_self10 * _self22)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self00) * (_self22) - (_self02 * _self20)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self02) * (_self10) - (_self00 * _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self10) * (_self21) - (_self11 * _self20)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self01) * (_self20) - (_self00 * _self21)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self00) * (_self11) - (_self01 * _self10)));
        return dest;
    }

    public static float determinant_unsafe(long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return determinant_unsafe_mulAdd(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t3 = -(_self11 * _self20);
        float _t4 = -_self12 * _self21;
        float _t5 = -_self12 * _self20;
        return java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(src + 24L), java.lang.Math.fma(_self10, _self21, _t3) - java.lang.Math.fma(_self11, _self20, _t3), java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(src), java.lang.Math.fma(_self11, _self22, _t4) - java.lang.Math.fma(_self12, _self21, _t4), UnsafeOpsHolder.U.getFloat(src + 12L) * (java.lang.Math.fma(_self12, _self20, _t5) - java.lang.Math.fma(_self10, _self22, _t5))));
    }

    /** {@code determinant_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float determinant_unsafe_mulAdd(long src) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t3 = -(_self11 * _self20);
        float _t4 = -_self12 * _self21;
        float _t5 = -_self12 * _self20;
        return ((UnsafeOpsHolder.U.getFloat(src + 24L)) * (((_self10) * (_self21) + (_t3)) - ((_self11) * (_self20) + (_t3))) + (((UnsafeOpsHolder.U.getFloat(src)) * (((_self11) * (_self22) + (_t4)) - ((_self12) * (_self21) + (_t4))) + (UnsafeOpsHolder.U.getFloat(src + 12L) * (((_self12) * (_self20) + (_t5)) - ((_self10) * (_self22) + (_t5)))))));
    }

    public static float frobeniusNorm_unsafe(long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return frobeniusNorm_unsafe_mulAdd(src);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        return (float) java.lang.Math.sqrt(java.lang.Math.fma(_self00, _self00, _self01 * _self01) + java.lang.Math.fma(_self02, _self02, _self10 * _self10) + (java.lang.Math.fma(_self11, _self11, _self12 * _self12) + java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self21, _self21, _self22 * _self22))));
    }

    /** {@code frobeniusNorm_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float frobeniusNorm_unsafe_mulAdd(long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        return (float) java.lang.Math.sqrt(((_self00) * (_self00) + (_self01 * _self01)) + ((_self02) * (_self02) + (_self10 * _self10)) + (((_self11) * (_self11) + (_self12 * _self12)) + ((_self20) * (_self20) + (((_self21) * (_self21) + (_self22 * _self22))))));
    }

    public static long invert_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_unsafe_fma(dest, src);
        return invert_unsafe_mulAdd(dest, src);
    }

    /** {@code invert_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        float _t12 = -_self12 * _self20;
        float _t26 = java.lang.Math.fma(_self11, _self22, _t10) - java.lang.Math.fma(_self12, _self21, _t10);
        float _t27 = java.lang.Math.fma(_self10, _self21, _t11) - java.lang.Math.fma(_self11, _self20, _t11);
        return invert_unsafe_fma_s33065879_1(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self01 * _self22, -_self02 * _self11, -(_self10 * _self22), -_self02 * _self20, -_self00 * _self12, -_self00 * _self21, -_self01 * _self10, _t26, _t27, java.lang.Math.fma(_self02, _t27, java.lang.Math.fma(_self00, _t26, _self01 * (java.lang.Math.fma(_self12, _self20, _t12) - java.lang.Math.fma(_self10, _self22, _t12)))));
    }

    /** Piece 2 of {@code invert_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invert_unsafe_fma_s33065879_1(long dest, long src, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t33) {
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsAddress.invert_degenerate(dest, src);
        float _t33_inv = 1.0f / _t33;
        UnsafeOpsHolder.U.putFloat(dest, _t26 * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, (java.lang.Math.fma(_self12, _self20, _t15) - java.lang.Math.fma(_self10, _self22, _t15)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t27 * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, (java.lang.Math.fma(_self02, _self21, _t13) - java.lang.Math.fma(_self01, _self22, _t13)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 16L, (java.lang.Math.fma(_self00, _self22, _t16) - java.lang.Math.fma(_self02, _self20, _t16)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 20L, (java.lang.Math.fma(_self01, _self20, _t18) - java.lang.Math.fma(_self00, _self21, _t18)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 24L, (java.lang.Math.fma(_self01, _self12, _t14) - java.lang.Math.fma(_self02, _self11, _t14)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 28L, (java.lang.Math.fma(_self02, _self10, _t17) - java.lang.Math.fma(_self00, _self12, _t17)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 32L, (java.lang.Math.fma(_self00, _self11, _t19) - java.lang.Math.fma(_self01, _self10, _t19)) * _t33_inv);
        return dest;
    }

    /** {@code invert_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        float _t12 = -_self12 * _self20;
        float _t26 = ((_self11) * (_self22) + (_t10)) - ((_self12) * (_self21) + (_t10));
        float _t27 = ((_self10) * (_self21) + (_t11)) - ((_self11) * (_self20) + (_t11));
        return invert_unsafe_mulAdd_s80da8f62_1(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self01 * _self22, -_self02 * _self11, -(_self10 * _self22), -_self02 * _self20, -_self00 * _self12, -_self00 * _self21, -_self01 * _self10, _t26, _t27, ((_self02) * (_t27) + (((_self00) * (_t26) + (_self01 * (((_self12) * (_self20) + (_t12)) - ((_self10) * (_self22) + (_t12))))))));
    }

    /** Piece 2 of {@code invert_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invert_unsafe_mulAdd_s80da8f62_1(long dest, long src, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t33) {
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsAddress.invert_degenerate(dest, src);
        float _t33_inv = 1.0f / _t33;
        UnsafeOpsHolder.U.putFloat(dest, _t26 * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, (((_self12) * (_self20) + (_t15)) - ((_self10) * (_self22) + (_t15))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t27 * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, (((_self02) * (_self21) + (_t13)) - ((_self01) * (_self22) + (_t13))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 16L, (((_self00) * (_self22) + (_t16)) - ((_self02) * (_self20) + (_t16))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 20L, (((_self01) * (_self20) + (_t18)) - ((_self00) * (_self21) + (_t18))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 24L, (((_self01) * (_self12) + (_t14)) - ((_self02) * (_self11) + (_t14))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 28L, (((_self02) * (_self10) + (_t17)) - ((_self00) * (_self12) + (_t17))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 32L, (((_self00) * (_self11) + (_t19)) - ((_self01) * (_self10) + (_t19))) * _t33_inv);
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_unsafe_fma(dest, src);
        return invert_degenerate_unsafe_mulAdd(dest, src);
    }

    /** {@code invert_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_degenerate_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t19 = _self00 * _t2;
        float _t20 = _self01 * _t2;
        return invert_degenerate_unsafe_fma_sb8d74ee8_1(dest, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t20 * _t13, -_t18 * _t12, -(_t16 * _t13), -_t18 * _t17, -_t19 * _t14, -_t19 * _t15, -_t20 * _t16);
    }

    /** Piece 2 of {@code invert_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_fma_sb8d74ee8_1(long dest, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40) {
        float _t47 = java.lang.Math.fma(_t12, _t13, _t31) - java.lang.Math.fma(_t14, _t15, _t31);
        float _t48 = java.lang.Math.fma(_t16, _t15, _t32) - java.lang.Math.fma(_t12, _t17, _t32);
        float _t53_inv = 1.0f / java.lang.Math.fma(_t48, _t18, java.lang.Math.fma(_t47, _t19, (java.lang.Math.fma(_t14, _t17, _t33) - java.lang.Math.fma(_t16, _t13, _t33)) * _t20));
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t47 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, (java.lang.Math.fma(_t14, _t17, _t36) - java.lang.Math.fma(_t16, _t13, _t36)) * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t48 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, (java.lang.Math.fma(_t18, _t15, _t34) - java.lang.Math.fma(_t20, _t13, _t34)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, (java.lang.Math.fma(_t19, _t13, _t37) - java.lang.Math.fma(_t18, _t17, _t37)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, (java.lang.Math.fma(_t20, _t17, _t39) - java.lang.Math.fma(_t19, _t15, _t39)) * _sp1);
        return invert_degenerate_unsafe_fma_sb8d74ee8_2(dest, _t12, _t14, _t16, _t18, _t19, _t20, _t35, _t38, _t40, _t1 * _t53_inv);
    }

    /** Piece 3 of {@code invert_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_fma_sb8d74ee8_2(long dest, float _t12, float _t14, float _t16, float _t18, float _t19, float _t20, float _t35, float _t38, float _t40, float _sp2) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, (java.lang.Math.fma(_t20, _t14, _t35) - java.lang.Math.fma(_t18, _t12, _t35)) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 28L, (java.lang.Math.fma(_t18, _t16, _t38) - java.lang.Math.fma(_t19, _t14, _t38)) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 32L, (java.lang.Math.fma(_t19, _t12, _t40) - java.lang.Math.fma(_t20, _t16, _t40)) * _sp2);
        return dest;
    }

    /** {@code invert_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_degenerate_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t19 = _self00 * _t2;
        float _t20 = _self01 * _t2;
        return invert_degenerate_unsafe_mulAdd_sff6f5c69_1(dest, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t20 * _t13, -_t18 * _t12, -(_t16 * _t13), -_t18 * _t17, -_t19 * _t14, -_t19 * _t15, -_t20 * _t16);
    }

    /** Piece 2 of {@code invert_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_mulAdd_sff6f5c69_1(long dest, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40) {
        float _t47 = ((_t12) * (_t13) + (_t31)) - ((_t14) * (_t15) + (_t31));
        float _t48 = ((_t16) * (_t15) + (_t32)) - ((_t12) * (_t17) + (_t32));
        float _t53_inv = 1.0f / ((_t48) * (_t18) + (((_t47) * (_t19) + ((((_t14) * (_t17) + (_t33)) - ((_t16) * (_t13) + (_t33))) * _t20))));
        float _sp2 = _t1 * _t53_inv;
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t47 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, (((_t14) * (_t17) + (_t36)) - ((_t16) * (_t13) + (_t36))) * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t48 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, (((_t18) * (_t15) + (_t34)) - ((_t20) * (_t13) + (_t34))) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, (((_t19) * (_t13) + (_t37)) - ((_t18) * (_t17) + (_t37))) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, (((_t20) * (_t17) + (_t39)) - ((_t19) * (_t15) + (_t39))) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, (((_t20) * (_t14) + (_t35)) - ((_t18) * (_t12) + (_t35))) * _sp2);
        return invert_degenerate_unsafe_mulAdd_sff6f5c69_2(dest, _t12, _t14, _t16, _t18, _t19, _t20, _t38, _t40, _sp2);
    }

    /** Piece 3 of {@code invert_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_mulAdd_sff6f5c69_2(long dest, float _t12, float _t14, float _t16, float _t18, float _t19, float _t20, float _t38, float _t40, float _sp2) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, (((_t18) * (_t16) + (_t38)) - ((_t19) * (_t14) + (_t38))) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 32L, (((_t19) * (_t12) + (_t40)) - ((_t20) * (_t16) + (_t40))) * _sp2);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_unsafe_fma(dest, src, other);
        return invertProduct_unsafe_mulAdd(dest, src, other);
    }

    /** {@code invertProduct_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_fma(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other20 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other21 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 24L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 28L);
        float _other22 = UnsafeOpsHolder.U.getFloat(other + 32L);
        float _t21 = -(_other11 * _other20);
        return invertProduct_unsafe_fma_saeb649aa_1(dest, src, other, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, java.lang.Math.fma(_other10, _other21, _t21) - java.lang.Math.fma(_other11, _other20, _t21));
    }

    /** Part of {@code invertProduct_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_unsafe_fma_saeb649aa_526(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t81, float _t82, float _t108, float _t84, float _t85, float _t109) {
        float _t20 = -_other02 * _other11;
        float _t128 = (java.lang.Math.fma(_other01, _other12, _t20) - java.lang.Math.fma(_other02, _other11, _t20)) / _t108;
        float _t27 = -_other01 * _other22;
        float _t130 = (java.lang.Math.fma(_other02, _other21, _t27) - java.lang.Math.fma(_other01, _other22, _t27)) / _t108;
        float _t131 = invertProduct_unsafe_fma_saeb649aa_526_sd70f4898_1(dest, _self10, _self20, _self12, _self22, _t82, _t108, _t84, _t85, _t109, _t128, _t130);
        float _t35 = -_other00 * _other12;
        float _t140 = (java.lang.Math.fma(_other02, _other10, _t35) - java.lang.Math.fma(_other00, _other12, _t35)) / _t108;
        float _t36 = -_other02 * _other20;
        float _t141 = (java.lang.Math.fma(_other00, _other22, _t36) - java.lang.Math.fma(_other02, _other20, _t36)) / _t108;
        float _t142 = invertProduct_unsafe_fma_saeb649aa_526_sd70f4898_2(dest, _other10, _other20, _other12, _other22, _t108, _t84, _t85, _t109, _t131, _t140, _t141);
        float _t38 = -_other01 * _other10;
        float _t143 = (java.lang.Math.fma(_other00, _other11, _t38) - java.lang.Math.fma(_other01, _other10, _t38)) / _t108;
        invertProduct_unsafe_fma_saeb649aa_526_sd70f4898_4(dest, _self00, _self10, _self01, _self11, _self02, _self12, _t81, _t82, _t108, _t109, _t128, _t130, _t140, _t141, _t142, _t143, invertProduct_unsafe_fma_saeb649aa_526_sd70f4898_3(dest, _self00, _self20, _self01, _self21, _self02, _self22, _other00, _other20, _other01, _other21, _t81, _t82, _t108, _t84, _t85, _t109, _t128, _t130, _t131, _t140, _t141, _t142, _t143));
    }

    /** Part 1 of {@code invertProduct_unsafe_fma_saeb649aa_526}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_unsafe_fma_saeb649aa_526_sd70f4898_1(long dest, float _self10, float _self20, float _self12, float _self22, float _t82, float _t108, float _t84, float _t85, float _t109, float _t128, float _t130) {
        float _t28 = -_self10 * _self22;
        float _t131 = (java.lang.Math.fma(_self12, _self20, _t28) - java.lang.Math.fma(_self10, _self22, _t28)) / _t109;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t128, (_t84 / _t109), java.lang.Math.fma(_t130, _t131, (_t82 / _t108) * (_t85 / _t109))));
        return _t131;
    }

    /** Part 2 of {@code invertProduct_unsafe_fma_saeb649aa_526}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_unsafe_fma_saeb649aa_526_sd70f4898_2(long dest, float _other10, float _other20, float _other12, float _other22, float _t108, float _t84, float _t85, float _t109, float _t131, float _t140, float _t141) {
        float _t37 = -(_other10 * _other22);
        float _t142 = (java.lang.Math.fma(_other12, _other20, _t37) - java.lang.Math.fma(_other10, _other22, _t37)) / _t108;
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t140, (_t84 / _t109), java.lang.Math.fma(_t141, _t131, _t142 * (_t85 / _t109))));
        return _t142;
    }

    /** Part 3 of {@code invertProduct_unsafe_fma_saeb649aa_526}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_unsafe_fma_saeb649aa_526_sd70f4898_3(long dest, float _self00, float _self20, float _self01, float _self21, float _self02, float _self22, float _other00, float _other20, float _other01, float _other21, float _t81, float _t82, float _t108, float _t84, float _t85, float _t109, float _t128, float _t130, float _t131, float _t140, float _t141, float _t142, float _t143) {
        float _t39 = -_other00 * _other21;
        float _t144 = (java.lang.Math.fma(_other01, _other20, _t39) - java.lang.Math.fma(_other00, _other21, _t39)) / _t108;
        float _t145 = _t81 / _t108;
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t143, (_t84 / _t109), java.lang.Math.fma(_t144, _t131, _t145 * (_t85 / _t109))));
        float _t29 = -_self00 * _self21;
        float _t134 = (java.lang.Math.fma(_self01, _self20, _t29) - java.lang.Math.fma(_self00, _self21, _t29)) / _t109;
        float _t30 = -_self02 * _self20;
        float _t135 = (java.lang.Math.fma(_self00, _self22, _t30) - java.lang.Math.fma(_self02, _self20, _t30)) / _t109;
        float _t31 = -(_self01 * _self22);
        float _t136 = (java.lang.Math.fma(_self02, _self21, _t31) - java.lang.Math.fma(_self01, _self22, _t31)) / _t109;
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t128, _t134, java.lang.Math.fma(_t130, _t135, (_t82 / _t108) * _t136)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t140, _t134, java.lang.Math.fma(_t141, _t135, _t142 * _t136)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t143, _t134, java.lang.Math.fma(_t144, _t135, _t145 * _t136)));
        return _t144;
    }

    /** Part 4 of {@code invertProduct_unsafe_fma_saeb649aa_526}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_unsafe_fma_saeb649aa_526_sd70f4898_4(long dest, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _t81, float _t82, float _t108, float _t109, float _t128, float _t130, float _t140, float _t141, float _t142, float _t143, float _t144) {
        float _t32 = _self01 * -_self10;
        float _t137 = (java.lang.Math.fma(_self00, _self11, _t32) - java.lang.Math.fma(_self01, _self10, _t32)) / _t109;
        float _t33 = -_self00 * _self12;
        float _t138 = (java.lang.Math.fma(_self02, _self10, _t33) - java.lang.Math.fma(_self00, _self12, _t33)) / _t109;
        float _t34 = -_self02 * _self11;
        float _t139 = (java.lang.Math.fma(_self01, _self12, _t34) - java.lang.Math.fma(_self02, _self11, _t34)) / _t109;
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_t128, _t137, java.lang.Math.fma(_t130, _t138, (_t82 / _t108) * _t139)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t140, _t137, java.lang.Math.fma(_t141, _t138, _t142 * _t139)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_t143, _t137, java.lang.Math.fma(_t144, _t138, (_t81 / _t108) * _t139)));
    }

    /** Piece 2 of {@code invertProduct_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_fma_saeb649aa_1(long dest, long src, long other, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t81) {
        float _t22 = -_other12 * _other21;
        float _t82 = java.lang.Math.fma(_other11, _other22, _t22) - java.lang.Math.fma(_other12, _other21, _t22);
        float _t23 = -_other12 * _other20;
        float _t108 = java.lang.Math.fma(_other02, _t81, java.lang.Math.fma(_other00, _t82, _other01 * (java.lang.Math.fma(_other12, _other20, _t23) - java.lang.Math.fma(_other10, _other22, _t23))));
        if (!(java.lang.Math.abs(_t108) > 1.1754944E-38f && java.lang.Math.abs(_t108) < 8.507059E37f)) return Float3x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        float _t24 = -(_self11 * _self20);
        float _t84 = java.lang.Math.fma(_self10, _self21, _t24) - java.lang.Math.fma(_self11, _self20, _t24);
        float _t25 = -_self12 * _self21;
        float _t85 = java.lang.Math.fma(_self11, _self22, _t25) - java.lang.Math.fma(_self12, _self21, _t25);
        float _t26 = -_self12 * _self20;
        float _t109 = java.lang.Math.fma(_self02, _t84, java.lang.Math.fma(_self00, _t85, _self01 * (java.lang.Math.fma(_self12, _self20, _t26) - java.lang.Math.fma(_self10, _self22, _t26))));
        if (!(java.lang.Math.abs(_t109) > 1.1754944E-38f && java.lang.Math.abs(_t109) < 8.507059E37f)) return Float3x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        invertProduct_unsafe_fma_saeb649aa_526(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t81, _t82, _t108, _t84, _t85, _t109);
        return dest;
    }

    /** {@code invertProduct_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_mulAdd(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other20 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other21 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 24L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 28L);
        float _other22 = UnsafeOpsHolder.U.getFloat(other + 32L);
        return invertProduct_unsafe_mulAdd_s7f31bc6f_5(dest, src, other, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, -_other02 * _other11, -_other01 * _other22, -_self10 * _self22, -_self00 * _self21);
    }

    /** Part 1 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_unsafe_mulAdd_s7f31bc6f_1(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        float _t21 = -(_other11 * _other20);
        float _t22 = -_other12 * _other21;
        float _t23 = -_other12 * _other20;
        return ((_other02) * ((((_other10) * (_other21) + (_t21)) - ((_other11) * (_other20) + (_t21)))) + (((_other00) * ((((_other11) * (_other22) + (_t22)) - ((_other12) * (_other21) + (_t22)))) + (_other01 * (((_other12) * (_other20) + (_t23)) - ((_other10) * (_other22) + (_t23)))))));
    }

    /** Part 2 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_unsafe_mulAdd_s7f31bc6f_2(float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        float _t24 = -(_self11 * _self20);
        float _t25 = -_self12 * _self21;
        float _t26 = -_self12 * _self20;
        return ((_self02) * ((((_self10) * (_self21) + (_t24)) - ((_self11) * (_self20) + (_t24)))) + (((_self00) * ((((_self11) * (_self22) + (_t25)) - ((_self12) * (_self21) + (_t25)))) + (_self01 * (((_self12) * (_self20) + (_t26)) - ((_self10) * (_self22) + (_t26)))))));
    }

    /** Part 3 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_unsafe_mulAdd_s7f31bc6f_3(long dest, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other12, float _other22, float _t108, float _t109, float _t128, float _t130, float _t131, float _t140, float _t141, float _t142, float _t143) {
        float _t21 = -(_other11 * _other20);
        float _t22 = -_other12 * _other21;
        float _t24 = -(_self11 * _self20);
        float _t25 = -_self12 * _self21;
        float _t39 = -_other00 * _other21;
        float _t129 = (((_self10) * (_self21) + (_t24)) - ((_self11) * (_self20) + (_t24))) / _t109;
        float _t133 = (((_self11) * (_self22) + (_t25)) - ((_self12) * (_self21) + (_t25))) / _t109;
        float _t144 = (((_other01) * (_other20) + (_t39)) - ((_other00) * (_other21) + (_t39))) / _t108;
        UnsafeOpsHolder.U.putFloat(dest, ((_t128) * (_t129) + (((_t130) * (_t131) + (((((_other11) * (_other22) + (_t22)) - ((_other12) * (_other21) + (_t22))) / _t108) * _t133)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t140) * (_t129) + (((_t141) * (_t131) + (_t142 * _t133)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t143) * (_t129) + (((_t144) * (_t131) + (((((_other10) * (_other21) + (_t21)) - ((_other11) * (_other20) + (_t21))) / _t108) * _t133)))));
        return _t144;
    }

    /** Part 4 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_mulAdd_s7f31bc6f_4(long dest, float _other10, float _other20, float _other11, float _other21, float _other12, float _other22, float _t108, float _t128, float _t130, float _t134, float _t135, float _t136, float _t137, float _t138, float _t139, float _t140, float _t141, float _t142, float _t143, float _t144) {
        float _t21 = -(_other11 * _other20);
        float _t22 = -_other12 * _other21;
        float _t132 = (((_other11) * (_other22) + (_t22)) - ((_other12) * (_other21) + (_t22))) / _t108;
        float _t145 = (((_other10) * (_other21) + (_t21)) - ((_other11) * (_other20) + (_t21))) / _t108;
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t128) * (_t134) + (((_t130) * (_t135) + (_t132 * _t136)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t140) * (_t134) + (((_t141) * (_t135) + (_t142 * _t136)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t143) * (_t134) + (((_t144) * (_t135) + (_t145 * _t136)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_t128) * (_t137) + (((_t130) * (_t138) + (_t132 * _t139)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t140) * (_t137) + (((_t141) * (_t138) + (_t142 * _t139)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_t143) * (_t137) + (((_t144) * (_t138) + (_t145 * _t139)))));
        return dest;
    }

    /** Piece 2 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_mulAdd_s7f31bc6f_5(long dest, long src, long other, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t20, float _t27, float _t28, float _t29) {
        float _t35 = -_other00 * _other12;
        float _t36 = -_other02 * _other20;
        float _t108 = invertProduct_unsafe_mulAdd_s7f31bc6f_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
        if (!(java.lang.Math.abs(_t108) > 1.1754944E-38f && java.lang.Math.abs(_t108) < 8.507059E37f)) return Float3x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        float _t109 = invertProduct_unsafe_mulAdd_s7f31bc6f_2(_self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
        if (!(java.lang.Math.abs(_t109) > 1.1754944E-38f && java.lang.Math.abs(_t109) < 8.507059E37f)) return Float3x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        return invertProduct_unsafe_mulAdd_s7f31bc6f_6(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other12, _other22, _t28, _t29, -_self02 * _self20, -(_self01 * _self22), _self01 * -_self10, -_self00 * _self12, -_self02 * _self11, -(_other10 * _other22), -_other01 * _other10, _t108, _t109, (((_other01) * (_other12) + (_t20)) - ((_other02) * (_other11) + (_t20))) / _t108, (((_other02) * (_other21) + (_t27)) - ((_other01) * (_other22) + (_t27))) / _t108, (((_other02) * (_other10) + (_t35)) - ((_other00) * (_other12) + (_t35))) / _t108, (((_other00) * (_other22) + (_t36)) - ((_other02) * (_other20) + (_t36))) / _t108);
    }

    /** Piece 3 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_mulAdd_s7f31bc6f_6(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other12, float _other22, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34, float _t37, float _t38, float _t108, float _t109, float _t128, float _t130, float _t140, float _t141) {
        float _t142 = (((_other12) * (_other20) + (_t37)) - ((_other10) * (_other22) + (_t37))) / _t108;
        float _t143 = (((_other00) * (_other11) + (_t38)) - ((_other01) * (_other10) + (_t38))) / _t108;
        return invertProduct_unsafe_mulAdd_s7f31bc6f_4(dest, _other10, _other20, _other11, _other21, _other12, _other22, _t108, _t128, _t130, ((((_self01) * (_self20) + (_t29)) - ((_self00) * (_self21) + (_t29))) / _t109), ((((_self00) * (_self22) + (_t30)) - ((_self02) * (_self20) + (_t30))) / _t109), ((((_self02) * (_self21) + (_t31)) - ((_self01) * (_self22) + (_t31))) / _t109), ((((_self00) * (_self11) + (_t32)) - ((_self01) * (_self10) + (_t32))) / _t109), ((((_self02) * (_self10) + (_t33)) - ((_self00) * (_self12) + (_t33))) / _t109), ((((_self01) * (_self12) + (_t34)) - ((_self02) * (_self11) + (_t34))) / _t109), _t140, _t141, _t142, _t143, invertProduct_unsafe_mulAdd_s7f31bc6f_3(dest, _self10, _self20, _self11, _self21, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other12, _other22, _t108, _t109, _t128, _t130, ((((_self12) * (_self20) + (_t28)) - ((_self10) * (_self22) + (_t28))) / _t109), _t140, _t141, _t142, _t143));
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_unsafe_fma(dest, src, other);
        return invertProduct_degenerate_unsafe_mulAdd(dest, src, other);
    }

    /** {@code invertProduct_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_degenerate_unsafe_fma(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other20 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other21 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 24L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 28L);
        float _other22 = UnsafeOpsHolder.U.getFloat(other + 32L);
        return invertProduct_degenerate_unsafe_fma_see1ccc65_10(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Part 1 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_1(float _other10, float _other20, float _other11, float _other21, float _other12, float _other22) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t27 = _other11 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t63 = -(_t27 * _t30);
        return java.lang.Math.fma((_other10 * _t1), (_other21 * _t2), _t63) - java.lang.Math.fma(_t27, _t30, _t63);
    }

    /** Part 2 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_2(float _other10, float _other20, float _other11, float _other21, float _other12, float _other22) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t64 = -_t25 * _t29;
        return java.lang.Math.fma((_other11 * _t1), (_other22 * _t2), _t64) - java.lang.Math.fma(_t25, _t29, _t64);
    }

    /** Part 3 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_3(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t123, float _t124) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t65 = -_t25 * _t30;
        return java.lang.Math.fma(_t123, (_other02 * _t0), java.lang.Math.fma(_t124, (_other00 * _t0), (java.lang.Math.fma(_t25, _t30, _t65) - java.lang.Math.fma((_other10 * _t1), (_other22 * _t2), _t65)) * (_other01 * _t0)));
    }

    /** Part 4 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_4(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t26 = _other02 * _t0;
        float _t27 = _other11 * _t1;
        float _t62 = -_t26 * _t27;
        return (java.lang.Math.fma((_other01 * _t0), (_other12 * _t1), _t62) - java.lang.Math.fma(_t26, _t27, _t62)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_5(float _self10, float _self20, float _self11, float _self21, float _self12, float _self22) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t66 = -_t35 * _t36;
        return java.lang.Math.fma((_self10 * _t3), (_self21 * _t4), _t66) - java.lang.Math.fma(_t35, _t36, _t66);
    }

    /** Part 6 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_6(float _self10, float _self20, float _self11, float _self21, float _self12, float _self22) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t39 = _self12 * _t3;
        float _t67 = -_t39 * _t34;
        return java.lang.Math.fma((_self11 * _t3), (_self22 * _t4), _t67) - java.lang.Math.fma(_t39, _t34, _t67);
    }

    /** Part 7 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_7(float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t126, float _t127) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t36 = _self20 * _t4;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t39 = _self12 * _t3;
        float _t68 = -(_t39 * _t36);
        return java.lang.Math.fma(_t126, (_self02 * _t5), java.lang.Math.fma(_t127, (_self00 * _t5), (java.lang.Math.fma(_t39, _t36, _t68) - java.lang.Math.fma((_self10 * _t3), (_self22 * _t4), _t68)) * (_self01 * _t5)));
    }

    /** Part 8 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_8(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t24 = _other01 * _t0;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t31 = _other22 * _t2;
        float _t69 = -_t24 * _t31;
        return (java.lang.Math.fma((_other02 * _t0), (_other21 * _t2), _t69) - java.lang.Math.fma(_t24, _t31, _t69)) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 9 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_9(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other01, float _other02, float _t124, float _t166, float _t186, float _t126, float _t127, float _t167, float _t188) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t33 = _self10 * _t3;
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t38 = _self22 * _t4;
        float _t70 = -_t33 * _t38;
        float _t189 = (java.lang.Math.fma((_self12 * _t3), (_self20 * _t4), _t70) - java.lang.Math.fma(_t33, _t38, _t70)) * _t5 / _t167;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t186, (_t126 * _t5 / _t167), java.lang.Math.fma(_t188, _t189, (_t124 * (unitScale(_other00, _other01, _other02)) / _t166) * (_t127 * _t5 / _t167))));
        return _t189;
    }

    /** Part of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_fma_see1ccc65_528(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t123, float _t124, float _t166, float _t186, float _t126, float _t127, float _t167, float _t188, float _t189) {
        float _t198 = invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t199 = invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_2(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t200 = invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_3(dest, _self00, _self01, _self02, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t126, _t127, _t167, _t189, _t198, _t199);
        float _t201 = invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t202 = invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_5(dest, _self00, _self01, _self02, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t166, _t126, _t127, _t167, _t189, _t201);
        invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_6(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t123, _t124, _t166, _t186, _t167, _t188, _t198, _t199, _t200, _t201, _t202);
        invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_7(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t123, _t124, _t166, _t186, _t167, _t188, _t198, _t199, _t200, _t201, _t202);
    }

    /** Part 1 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_1(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t32 = _other00 * _t0;
        float _t77 = -_t32 * _t25;
        return (java.lang.Math.fma((_other02 * _t0), (_other10 * _t1), _t77) - java.lang.Math.fma(_t32, _t25, _t77)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 2 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_2(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t26 = _other02 * _t0;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t78 = -_t26 * _t30;
        return (java.lang.Math.fma((_other00 * _t0), (_other22 * _t2), _t78) - java.lang.Math.fma(_t26, _t30, _t78)) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 3 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_3(long dest, float _self00, float _self01, float _self02, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166, float _t126, float _t127, float _t167, float _t189, float _t198, float _t199) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t28 = _other10 * _t1;
        float _t31 = _other22 * _t2;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t79 = -(_t28 * _t31);
        float _t200 = (java.lang.Math.fma((_other12 * _t1), (_other20 * _t2), _t79) - java.lang.Math.fma(_t28, _t31, _t79)) * (unitScale(_other00, _other01, _other02)) / _t166;
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t198, (_t126 * _t5 / _t167), java.lang.Math.fma(_t199, _t189, _t200 * (_t127 * _t5 / _t167))));
        return _t200;
    }

    /** Part 4 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_4(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t24 = _other01 * _t0;
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t28 = _other10 * _t1;
        float _t80 = -_t24 * _t28;
        return (java.lang.Math.fma((_other00 * _t0), (_other11 * _t1), _t80) - java.lang.Math.fma(_t24, _t28, _t80)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_5(long dest, float _self00, float _self01, float _self02, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t123, float _t166, float _t126, float _t127, float _t167, float _t189, float _t201) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t32 = _other00 * _t0;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t81 = -_t32 * _t29;
        float _t202 = (java.lang.Math.fma((_other01 * _t0), (_other20 * _t2), _t81) - java.lang.Math.fma(_t32, _t29, _t81)) * (unitScale(_other10, _other11, _other12)) / _t166;
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t201, (_t126 * _t5 / _t167), java.lang.Math.fma(_t202, _t189, (_t123 * _t0 / _t166) * (_t127 * _t5 / _t167))));
        return _t202;
    }

    /** Part 6 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_6(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other01, float _other02, float _t123, float _t124, float _t166, float _t186, float _t167, float _t188, float _t198, float _t199, float _t200, float _t201, float _t202) {
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
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t186, _t192, java.lang.Math.fma(_t188, _t193, (_t124 * _t0 / _t166) * _t194)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t198, _t192, java.lang.Math.fma(_t199, _t193, _t200 * _t194)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t201, _t192, java.lang.Math.fma(_t202, _t193, (_t123 * _t0 / _t166) * _t194)));
    }

    /** Part 7 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_fma_see1ccc65_528_s8e129938_7(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other01, float _other02, float _t123, float _t124, float _t166, float _t186, float _t167, float _t188, float _t198, float _t199, float _t200, float _t201, float _t202) {
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
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_t186, _t195, java.lang.Math.fma(_t188, _t196, (_t124 * _t0 / _t166) * _t197)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t198, _t195, java.lang.Math.fma(_t199, _t196, _t200 * _t197)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_t201, _t195, java.lang.Math.fma(_t202, _t196, (_t123 * _t0 / _t166) * _t197)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_fma_see1ccc65_10(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        float _t123 = invertProduct_degenerate_unsafe_fma_see1ccc65_1(_other10, _other20, _other11, _other21, _other12, _other22);
        float _t124 = invertProduct_degenerate_unsafe_fma_see1ccc65_2(_other10, _other20, _other11, _other21, _other12, _other22);
        float _t166 = invertProduct_degenerate_unsafe_fma_see1ccc65_3(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t124);
        float _t186 = invertProduct_degenerate_unsafe_fma_see1ccc65_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t126 = invertProduct_degenerate_unsafe_fma_see1ccc65_5(_self10, _self20, _self11, _self21, _self12, _self22);
        float _t127 = invertProduct_degenerate_unsafe_fma_see1ccc65_6(_self10, _self20, _self11, _self21, _self12, _self22);
        float _t167 = invertProduct_degenerate_unsafe_fma_see1ccc65_7(_self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t126, _t127);
        float _t188 = invertProduct_degenerate_unsafe_fma_see1ccc65_8(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        invertProduct_degenerate_unsafe_fma_see1ccc65_528(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t124, _t166, _t186, _t126, _t127, _t167, _t188, invertProduct_degenerate_unsafe_fma_see1ccc65_9(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t124, _t166, _t186, _t126, _t127, _t167, _t188));
        return dest;
    }

    /** {@code invertProduct_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_degenerate_unsafe_mulAdd(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other20 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other21 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 24L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 28L);
        float _other22 = UnsafeOpsHolder.U.getFloat(other + 32L);
        return invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_9(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Part 1 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_1(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t27 = _other11 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t28 = _other10 * _t1;
        float _t29 = _other21 * _t2;
        float _t30 = _other20 * _t2;
        float _t63 = -(_t27 * _t30);
        float _t31 = _other22 * _t2;
        float _t64 = -_t25 * _t29;
        float _t65 = -_t25 * _t30;
        return (((((_t28) * (_t29) + (_t63)) - ((_t27) * (_t30) + (_t63)))) * ((_other02 * _t0)) + ((((((_t27) * (_t31) + (_t64)) - ((_t25) * (_t29) + (_t64)))) * ((_other00 * _t0)) + ((((_t25) * (_t30) + (_t65)) - ((_t28) * (_t31) + (_t65))) * (_other01 * _t0)))));
    }

    /** Part 2 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_2(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t26 = _other02 * _t0;
        float _t27 = _other11 * _t1;
        float _t62 = -_t26 * _t27;
        return ((((_other01 * _t0)) * ((_other12 * _t1)) + (_t62)) - ((_t26) * (_t27) + (_t62))) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 3 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_3(float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t33 = _self10 * _t3;
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t66 = -_t35 * _t36;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t38 = _self22 * _t4;
        float _t39 = _self12 * _t3;
        float _t67 = -_t39 * _t34;
        float _t68 = -(_t39 * _t36);
        return (((((_t33) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66)))) * ((_self02 * _t5)) + ((((((_t35) * (_t38) + (_t67)) - ((_t39) * (_t34) + (_t67)))) * ((_self00 * _t5)) + ((((_t39) * (_t36) + (_t68)) - ((_t33) * (_t38) + (_t68))) * (_self01 * _t5)))));
    }

    /** Part 4 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_4(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t24 = _other01 * _t0;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t31 = _other22 * _t2;
        float _t69 = -_t24 * _t31;
        return ((((_other02 * _t0)) * ((_other21 * _t2)) + (_t69)) - ((_t24) * (_t31) + (_t69))) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_5(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166, float _t186, float _t167, float _t188) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t64 = -_t25 * _t29;
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t33 = _self10 * _t3;
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t66 = -_t35 * _t36;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t38 = _self22 * _t4;
        float _t39 = _self12 * _t3;
        float _t67 = -_t39 * _t34;
        float _t70 = -_t33 * _t38;
        float _t189 = (((_t39) * (_t36) + (_t70)) - ((_t33) * (_t38) + (_t70))) * _t5 / _t167;
        UnsafeOpsHolder.U.putFloat(dest, ((_t186) * (((((_t33) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66))) * _t5 / _t167)) + (((_t188) * (_t189) + ((((((_other11 * _t1)) * ((_other22 * _t2)) + (_t64)) - ((_t25) * (_t29) + (_t64))) * (unitScale(_other00, _other01, _other02)) / _t166) * ((((_t35) * (_t38) + (_t67)) - ((_t39) * (_t34) + (_t67))) * _t5 / _t167))))));
        return _t189;
    }

    /** Part 6 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_6(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t32 = _other00 * _t0;
        float _t77 = -_t32 * _t25;
        return ((((_other02 * _t0)) * ((_other10 * _t1)) + (_t77)) - ((_t32) * (_t25) + (_t77))) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 7 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_7(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t26 = _other02 * _t0;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t78 = -_t26 * _t30;
        return ((((_other00 * _t0)) * ((_other22 * _t2)) + (_t78)) - ((_t26) * (_t30) + (_t78))) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 8 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_8(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166, float _t167, float _t189, float _t198, float _t199) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t28 = _other10 * _t1;
        float _t31 = _other22 * _t2;
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t66 = -_t35 * _t36;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t39 = _self12 * _t3;
        float _t67 = -_t39 * _t34;
        float _t79 = -(_t28 * _t31);
        float _t200 = ((((_other12 * _t1)) * ((_other20 * _t2)) + (_t79)) - ((_t28) * (_t31) + (_t79))) * (unitScale(_other00, _other01, _other02)) / _t166;
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t198) * ((((((_self10 * _t3)) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66))) * _t5 / _t167)) + (((_t199) * (_t189) + (_t200 * ((((_t35) * ((_self22 * _t4)) + (_t67)) - ((_t39) * (_t34) + (_t67))) * _t5 / _t167))))));
        return _t200;
    }

    /** Part of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166, float _t186, float _t167, float _t188, float _t189, float _t198, float _t199, float _t200) {
        float _t190 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t201 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_2(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t202 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_3(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t203 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_4(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t167, _t189, _t201, _t202);
        invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_5(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t186, _t167, _t188, _t198, _t199, _t200, _t190, _t201, _t202, _t203);
        invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_6(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t186, _t167, _t188, _t198, _t199, _t200, _t190, _t201, _t202, _t203);
    }

    /** Part 1 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_1(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t25 = _other12 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t64 = -_t25 * _t29;
        return ((((_other11 * _t1)) * ((_other22 * _t2)) + (_t64)) - ((_t25) * (_t29) + (_t64))) * (unitScale(_other00, _other01, _other02)) / _t166;
    }

    /** Part 2 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_2(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t24 = _other01 * _t0;
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t28 = _other10 * _t1;
        float _t80 = -_t24 * _t28;
        return ((((_other00 * _t0)) * ((_other11 * _t1)) + (_t80)) - ((_t24) * (_t28) + (_t80))) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 3 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_3(float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166) {
        float _t0 = unitScale(_other00, _other01, _other02);
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t29 = _other21 * _t2;
        float _t32 = _other00 * _t0;
        float _t81 = -_t32 * _t29;
        return ((((_other01 * _t0)) * ((_other20 * _t2)) + (_t81)) - ((_t32) * (_t29) + (_t81))) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 4 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_4(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t166, float _t167, float _t189, float _t201, float _t202) {
        float _t1 = unitScale(_other10, _other11, _other12);
        float _t27 = _other11 * _t1;
        float _t2 = unitScale(_other20, _other21, _other22);
        float _t30 = _other20 * _t2;
        float _t63 = -(_t27 * _t30);
        float _t3 = unitScale(_self10, _self11, _self12);
        float _t4 = unitScale(_self20, _self21, _self22);
        float _t34 = _self21 * _t4;
        float _t35 = _self11 * _t3;
        float _t36 = _self20 * _t4;
        float _t66 = -_t35 * _t36;
        float _t5 = unitScale(_self00, _self01, _self02);
        float _t39 = _self12 * _t3;
        float _t67 = -_t39 * _t34;
        float _t203 = ((((_other10 * _t1)) * ((_other21 * _t2)) + (_t63)) - ((_t27) * (_t30) + (_t63))) * (unitScale(_other00, _other01, _other02)) / _t166;
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t201) * ((((((_self10 * _t3)) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66))) * _t5 / _t167)) + (((_t202) * (_t189) + (_t203 * ((((_t35) * ((_self22 * _t4)) + (_t67)) - ((_t39) * (_t34) + (_t67))) * _t5 / _t167))))));
        return _t203;
    }

    /** Part 5 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_5(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t186, float _t167, float _t188, float _t198, float _t199, float _t200, float _t190, float _t201, float _t202, float _t203) {
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
        float _t192 = (((_t41) * (_t36) + (_t71)) - ((_t40) * (_t34) + (_t71))) * _t3 / _t167;
        float _t72 = -(_t37 * _t36);
        float _t193 = (((_t40) * (_t38) + (_t72)) - ((_t37) * (_t36) + (_t72))) * _t3 / _t167;
        float _t73 = -(_t41 * _t38);
        float _t194 = (((_t37) * (_t34) + (_t73)) - ((_t41) * (_t38) + (_t73))) * _t3 / _t167;
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t186) * (_t192) + (((_t188) * (_t193) + (_t190 * _t194)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t198) * (_t192) + (((_t199) * (_t193) + (_t200 * _t194)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t201) * (_t192) + (((_t202) * (_t193) + (_t203 * _t194)))));
    }

    /** Part 6 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_s19f1b458_6(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t186, float _t167, float _t188, float _t198, float _t199, float _t200, float _t190, float _t201, float _t202, float _t203) {
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
        float _t195 = (((_t40) * (_t35) + (_t74)) - ((_t41) * (_t33) + (_t74))) * _t4 / _t167;
        float _t75 = _t40 * -_t39;
        float _t196 = (((_t37) * (_t33) + (_t75)) - ((_t40) * (_t39) + (_t75))) * _t4 / _t167;
        float _t76 = _t37 * -_t35;
        float _t197 = (((_t41) * (_t39) + (_t76)) - ((_t37) * (_t35) + (_t76))) * _t4 / _t167;
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_t186) * (_t195) + (((_t188) * (_t196) + (_t190 * _t197)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t198) * (_t195) + (((_t199) * (_t196) + (_t200 * _t197)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_t201) * (_t195) + (((_t202) * (_t196) + (_t203 * _t197)))));
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_9(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        float _t166 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
        float _t186 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_2(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t167 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_3(_self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
        float _t188 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t189 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_5(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t186, _t167, _t188);
        float _t198 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_6(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        float _t199 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_7(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t186, _t167, _t188, _t189, _t198, _t199, invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_8(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t167, _t189, _t198, _t199));
        return dest;
    }

    public static long normal_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_unsafe_fma(dest, src);
        return normal_unsafe_mulAdd(dest, src);
    }

    /** {@code normal_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normal_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        float _t12 = -_self12 * _self20;
        float _t26 = java.lang.Math.fma(_self11, _self22, _t10) - java.lang.Math.fma(_self12, _self21, _t10);
        float _t27 = java.lang.Math.fma(_self10, _self21, _t11) - java.lang.Math.fma(_self11, _self20, _t11);
        return normal_unsafe_fma_s8eda2bc0_1(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self10 * _self22, -(_self01 * _self22), -_self02 * _self20, -_self00 * _self21, -_self02 * _self11, -_self00 * _self12, _self01 * -_self10, _t26, _t27, java.lang.Math.fma(_self02, _t27, java.lang.Math.fma(_self00, _t26, _self01 * (java.lang.Math.fma(_self12, _self20, _t12) - java.lang.Math.fma(_self10, _self22, _t12)))));
    }

    /** Piece 2 of {@code normal_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long normal_unsafe_fma_s8eda2bc0_1(long dest, long src, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t33) {
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsAddress.normal_degenerate(dest, src);
        float _t33_inv = 1.0f / _t33;
        UnsafeOpsHolder.U.putFloat(dest, _t26 * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, (java.lang.Math.fma(_self02, _self21, _t14) - java.lang.Math.fma(_self01, _self22, _t14)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, (java.lang.Math.fma(_self01, _self12, _t17) - java.lang.Math.fma(_self02, _self11, _t17)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, (java.lang.Math.fma(_self12, _self20, _t13) - java.lang.Math.fma(_self10, _self22, _t13)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 16L, (java.lang.Math.fma(_self00, _self22, _t15) - java.lang.Math.fma(_self02, _self20, _t15)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 20L, (java.lang.Math.fma(_self02, _self10, _t18) - java.lang.Math.fma(_self00, _self12, _t18)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t27 * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 28L, (java.lang.Math.fma(_self01, _self20, _t16) - java.lang.Math.fma(_self00, _self21, _t16)) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 32L, (java.lang.Math.fma(_self00, _self11, _t19) - java.lang.Math.fma(_self01, _self10, _t19)) * _t33_inv);
        return dest;
    }

    /** {@code normal_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normal_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t10 = -_self12 * _self21;
        float _t11 = -(_self11 * _self20);
        float _t12 = -_self12 * _self20;
        float _t26 = ((_self11) * (_self22) + (_t10)) - ((_self12) * (_self21) + (_t10));
        float _t27 = ((_self10) * (_self21) + (_t11)) - ((_self11) * (_self20) + (_t11));
        return normal_unsafe_mulAdd_sa1c3a121_1(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self10 * _self22, -(_self01 * _self22), -_self02 * _self20, -_self00 * _self21, -_self02 * _self11, -_self00 * _self12, _self01 * -_self10, _t26, _t27, ((_self02) * (_t27) + (((_self00) * (_t26) + (_self01 * (((_self12) * (_self20) + (_t12)) - ((_self10) * (_self22) + (_t12))))))));
    }

    /** Piece 2 of {@code normal_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long normal_unsafe_mulAdd_sa1c3a121_1(long dest, long src, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t33) {
        if (!(java.lang.Math.abs(_t33) > 1.1754944E-38f && java.lang.Math.abs(_t33) < 8.507059E37f)) return Float3x3OpsKernelsAddress.normal_degenerate(dest, src);
        float _t33_inv = 1.0f / _t33;
        UnsafeOpsHolder.U.putFloat(dest, _t26 * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, (((_self02) * (_self21) + (_t14)) - ((_self01) * (_self22) + (_t14))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, (((_self01) * (_self12) + (_t17)) - ((_self02) * (_self11) + (_t17))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, (((_self12) * (_self20) + (_t13)) - ((_self10) * (_self22) + (_t13))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 16L, (((_self00) * (_self22) + (_t15)) - ((_self02) * (_self20) + (_t15))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 20L, (((_self02) * (_self10) + (_t18)) - ((_self00) * (_self12) + (_t18))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t27 * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 28L, (((_self01) * (_self20) + (_t16)) - ((_self00) * (_self21) + (_t16))) * _t33_inv);
        UnsafeOpsHolder.U.putFloat(dest + 32L, (((_self00) * (_self11) + (_t19)) - ((_self01) * (_self10) + (_t19))) * _t33_inv);
        return dest;
    }

    public static long normal_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.normal_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long normal_degenerate_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_unsafe_fma(dest, src);
        return normal_degenerate_unsafe_mulAdd(dest, src);
    }

    /** {@code normal_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normal_degenerate_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t19 = _self00 * _t2;
        float _t20 = _self01 * _t2;
        return normal_degenerate_unsafe_fma_s7e5d62f7_1(dest, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t16 * _t13, -(_t20 * _t13), -_t18 * _t17, -_t19 * _t15, -_t18 * _t12, -_t19 * _t14, _t20 * -_t16);
    }

    /** Piece 2 of {@code normal_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long normal_degenerate_unsafe_fma_s7e5d62f7_1(long dest, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40) {
        float _t47 = java.lang.Math.fma(_t12, _t13, _t31) - java.lang.Math.fma(_t14, _t15, _t31);
        float _t48 = java.lang.Math.fma(_t16, _t15, _t32) - java.lang.Math.fma(_t12, _t17, _t32);
        float _t53_inv = 1.0f / java.lang.Math.fma(_t48, _t18, java.lang.Math.fma(_t47, _t19, (java.lang.Math.fma(_t14, _t17, _t33) - java.lang.Math.fma(_t16, _t13, _t33)) * _t20));
        float _sp2 = _t1 * _t53_inv;
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t47 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, (java.lang.Math.fma(_t18, _t15, _t35) - java.lang.Math.fma(_t20, _t13, _t35)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, (java.lang.Math.fma(_t20, _t14, _t38) - java.lang.Math.fma(_t18, _t12, _t38)) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 12L, (java.lang.Math.fma(_t14, _t17, _t34) - java.lang.Math.fma(_t16, _t13, _t34)) * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 16L, (java.lang.Math.fma(_t19, _t13, _t36) - java.lang.Math.fma(_t18, _t17, _t36)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, (java.lang.Math.fma(_t18, _t16, _t39) - java.lang.Math.fma(_t19, _t14, _t39)) * _sp2);
        return normal_degenerate_unsafe_fma_s7e5d62f7_2(dest, _t12, _t15, _t16, _t17, _t19, _t20, _t37, _t40, _t48, _sp2, _sp1, _sp0);
    }

    /** Piece 3 of {@code normal_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long normal_degenerate_unsafe_fma_s7e5d62f7_2(long dest, float _t12, float _t15, float _t16, float _t17, float _t19, float _t20, float _t37, float _t40, float _t48, float _sp2, float _sp1, float _sp0) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t48 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 28L, (java.lang.Math.fma(_t20, _t17, _t37) - java.lang.Math.fma(_t19, _t15, _t37)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 32L, (java.lang.Math.fma(_t19, _t12, _t40) - java.lang.Math.fma(_t20, _t16, _t40)) * _sp2);
        return dest;
    }

    /** {@code normal_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normal_degenerate_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t19 = _self00 * _t2;
        float _t20 = _self01 * _t2;
        return normal_degenerate_unsafe_mulAdd_s138775e8_1(dest, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t16 * _t13, -(_t20 * _t13), -_t18 * _t17, -_t19 * _t15, -_t18 * _t12, -_t19 * _t14, _t20 * -_t16);
    }

    /** Piece 2 of {@code normal_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long normal_degenerate_unsafe_mulAdd_s138775e8_1(long dest, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t31, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40) {
        float _t47 = ((_t12) * (_t13) + (_t31)) - ((_t14) * (_t15) + (_t31));
        float _t48 = ((_t16) * (_t15) + (_t32)) - ((_t12) * (_t17) + (_t32));
        float _t53_inv = 1.0f / ((_t48) * (_t18) + (((_t47) * (_t19) + ((((_t14) * (_t17) + (_t33)) - ((_t16) * (_t13) + (_t33))) * _t20))));
        float _sp2 = _t1 * _t53_inv;
        float _sp1 = _t0 * _t53_inv;
        float _sp0 = _t2 * _t53_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t47 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, (((_t18) * (_t15) + (_t35)) - ((_t20) * (_t13) + (_t35))) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, (((_t20) * (_t14) + (_t38)) - ((_t18) * (_t12) + (_t38))) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 12L, (((_t14) * (_t17) + (_t34)) - ((_t16) * (_t13) + (_t34))) * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 16L, (((_t19) * (_t13) + (_t36)) - ((_t18) * (_t17) + (_t36))) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, (((_t18) * (_t16) + (_t39)) - ((_t19) * (_t14) + (_t39))) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t48 * _sp0);
        return normal_degenerate_unsafe_mulAdd_s138775e8_2(dest, _t12, _t15, _t16, _t17, _t19, _t20, _t37, _t40, _sp2, _sp1);
    }

    /** Piece 3 of {@code normal_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long normal_degenerate_unsafe_mulAdd_s138775e8_2(long dest, float _t12, float _t15, float _t16, float _t17, float _t19, float _t20, float _t37, float _t40, float _sp2, float _sp1) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, (((_t20) * (_t17) + (_t37)) - ((_t19) * (_t15) + (_t37))) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 32L, (((_t19) * (_t12) + (_t40)) - ((_t20) * (_t16) + (_t40))) * _sp2);
        return dest;
    }

    public static float trace_unsafe(long src) {
        return UnsafeOpsHolder.U.getFloat(src + 32L) + (UnsafeOpsHolder.U.getFloat(src) + UnsafeOpsHolder.U.getFloat(src + 16L));
    }

    public static long transpose_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, _eother + _eself);
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float scalar) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, scalar * _eself);
        }
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, -_eself);
        }
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, _eself - _eother);
        }
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        for (int _i = 0; _i < 9; _i++) {
            float _ev = UnsafeOpsHolder.U.getFloat(v + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, _ev);
        }
        return dest;
    }

    public static long setMat2x2_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _m00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _m10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _m01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _m11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long setMat2x3_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 12L);
        float _m02 = UnsafeOpsHolder.U.getFloat(m + 16L);
        float _m12 = UnsafeOpsHolder.U.getFloat(m + 20L);
        UnsafeOpsHolder.U.putFloat(dest, _m00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _m10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _m01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _m11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _m02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _m12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long setMat3x4_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m02 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 20L);
        float _m12 = UnsafeOpsHolder.U.getFloat(m + 24L);
        float _m20 = UnsafeOpsHolder.U.getFloat(m + 32L);
        float _m21 = UnsafeOpsHolder.U.getFloat(m + 36L);
        float _m22 = UnsafeOpsHolder.U.getFloat(m + 40L);
        UnsafeOpsHolder.U.putFloat(dest, _m00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _m10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _m20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _m01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _m11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _m21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _m02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _m12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _m22);
        return dest;
    }

    public static long setMat4x4_unsafe(long dest, long m) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            int _lom = _l * 4;
            float _em0 = UnsafeOpsHolder.U.getFloat(m + _lom * 4L);
            float _em1 = UnsafeOpsHolder.U.getFloat(m + (_lom + 1) * 4L);
            float _em2 = UnsafeOpsHolder.U.getFloat(m + (_lom + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _em0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _em1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _em2);
        }
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, float tX, float tY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, tX);
        UnsafeOpsHolder.U.putFloat(dest + 28L, tY);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, long t) {
        float _tx = UnsafeOpsHolder.U.getFloat(t);
        float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _tx);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _ty);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long makeFromRigid_unsafe(long dest, float rRX, float rRY, float rRZ, float rRW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromRigid_unsafe_mulAdd(dest, rRX, rRY, rRZ, rRW);
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, java.lang.Math.fma(rRY, rRY, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * java.lang.Math.fma(rRX, rRY, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * java.lang.Math.fma(rRX, rRZ, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * java.lang.Math.fma(rRX, rRY, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(rRX, rRX, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * java.lang.Math.fma(rRX, rRW, rRY * rRZ));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * java.lang.Math.fma(rRX, rRZ, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * java.lang.Math.fma(rRY, rRZ, -(rRX * rRW)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(rRX, rRX, rRY * rRY), 1.0f));
        return dest;
    }

    /** {@code makeFromRigid_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeFromRigid_unsafe_mulAdd(long dest, float rRX, float rRY, float rRZ, float rRW) {
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        UnsafeOpsHolder.U.putFloat(dest, ((1.0f) - (2.0f) * (((rRY) * (rRY) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * ((rRX) * (rRY) + (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * ((rRX) * (rRZ) - (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * ((rRX) * (rRY) - (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((1.0f) - (2.0f) * (((rRX) * (rRX) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * ((rRX) * (rRW) + (rRY * rRZ)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * ((rRX) * (rRZ) + (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * ((rRY) * (rRZ) - (rRX * rRW)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((1.0f) - (2.0f) * (((rRX) * (rRX) + (rRY * rRY)))));
        return dest;
    }

    public static long makeFromTransform_unsafe(long dest, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromTransform_unsafe_mulAdd(dest, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-java.lang.Math.fma(tRY, tRY, _t3), _t0, tSX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(tRX, tRY, _t4) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(tRX, tRZ, -_t5) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(tRX, tRY, -_t4) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(-java.lang.Math.fma(tRX, tRX, _t3), _t1, tSY));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(tRX, tRZ, _t5) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(-java.lang.Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        return dest;
    }

    /** {@code makeFromTransform_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeFromTransform_unsafe_mulAdd(long dest, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        UnsafeOpsHolder.U.putFloat(dest, ((tSX) - ((tRY) * (tRY) + (_t3)) * (_t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((tRX) * (tRY) + (_t4)) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((tRX) * (tRZ) - (_t5)) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((tRX) * (tRY) - (_t4)) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((tSY) - ((tRX) * (tRX) + (_t3)) * (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((tRX) * (tRW) + (tRY * tRZ)) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((tRX) * (tRZ) + (_t5)) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((tRY) * (tRZ) - (tRX * tRW)) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((tSZ) - ((tRX) * (tRX) + (tRY * tRY)) * (_t2)));
        return dest;
    }

    public static long to2x2_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        return dest;
    }

    public static long to2x3_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _loself = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _loself * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_loself + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1);
        }
        return dest;
    }

    public static long to3x4_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long to4x4_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return to4x4_unsafe_s575d5859_1(dest);
    }

    /** Piece 2 of {@code to4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long to4x4_unsafe_s575d5859_1(long dest) {
        UnsafeOpsHolder.U.putFloat(dest + 48L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 52L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 56L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 60L, 1.0f);
        return dest;
    }

    public static long toDualQuat_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = 1.0f - _self00;
        float _t13 = _self22 + (_self00 + _self11);
        float _t14 = 1.0f + _t13;
        float _t15 = _self00 + (1.0f - _self11 - _self22);
        float _t16 = _self11 + (_t1 - _self22);
        float _t17 = _self22 + (_t1 - _self11);
        return toDualQuat_unsafe_s9f6c0bda_1(dest, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t13, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code toDualQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toDualQuat_unsafe_s9f6c0bda_1(long dest, float _self00, float _self11, float _self22, float _t3, float _t5, float _t6, float _t7, float _t8, float _t9, float _t13, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t13 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t7);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t9);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t15));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t5);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t6);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t3);
            } else {
                if (_self11 > _self22) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t5);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t8);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t7);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t6);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t8);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t9);
                }
            }
        }
        return toDualQuat_unsafe_s9f6c0bda_2(dest);
    }

    /** Piece 3 of {@code toDualQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toDualQuat_unsafe_s9f6c0bda_2(long dest) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        return dest;
    }

    public static long toRigid_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_unsafe_fma(dest, src);
        return toRigid_unsafe_mulAdd(dest, src);
    }

    /** {@code toRigid_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toRigid_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _ct0 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
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
            UnsafeOpsHolder.U.putFloat(dest + 12L, _sp0 * _t35);
            UnsafeOpsHolder.U.putFloat(dest + 16L, _sp0 * _t56);
            UnsafeOpsHolder.U.putFloat(dest + 20L, _sp0 * _t57);
            UnsafeOpsHolder.U.putFloat(dest + 24L, 0.5f * (float) java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t67));
                UnsafeOpsHolder.U.putFloat(dest + 16L, _sp3 * _t54);
                UnsafeOpsHolder.U.putFloat(dest + 20L, _sp3 * _t55);
                UnsafeOpsHolder.U.putFloat(dest + 24L, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t54);
                    UnsafeOpsHolder.U.putFloat(dest + 16L, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    UnsafeOpsHolder.U.putFloat(dest + 20L, _sp1 * _t31);
                    UnsafeOpsHolder.U.putFloat(dest + 24L, _sp1 * _t56);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t55);
                    UnsafeOpsHolder.U.putFloat(dest + 16L, _sp2 * _t31);
                    UnsafeOpsHolder.U.putFloat(dest + 20L, 0.5f * (float) java.lang.Math.sqrt(_t66));
                    UnsafeOpsHolder.U.putFloat(dest + 24L, _sp2 * _t57);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        return dest;
    }

    /** {@code toRigid_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toRigid_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _ct0 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
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
            UnsafeOpsHolder.U.putFloat(dest + 12L, _sp0 * _t35);
            UnsafeOpsHolder.U.putFloat(dest + 16L, _sp0 * _t56);
            UnsafeOpsHolder.U.putFloat(dest + 20L, _sp0 * _t57);
            UnsafeOpsHolder.U.putFloat(dest + 24L, 0.5f * (float) java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t67));
                UnsafeOpsHolder.U.putFloat(dest + 16L, _sp3 * _t54);
                UnsafeOpsHolder.U.putFloat(dest + 20L, _sp3 * _t55);
                UnsafeOpsHolder.U.putFloat(dest + 24L, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t54);
                    UnsafeOpsHolder.U.putFloat(dest + 16L, 0.5f * (float) java.lang.Math.sqrt(_t65));
                    UnsafeOpsHolder.U.putFloat(dest + 20L, _sp1 * _t31);
                    UnsafeOpsHolder.U.putFloat(dest + 24L, _sp1 * _t56);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t55);
                    UnsafeOpsHolder.U.putFloat(dest + 16L, _sp2 * _t31);
                    UnsafeOpsHolder.U.putFloat(dest + 20L, 0.5f * (float) java.lang.Math.sqrt(_t66));
                    UnsafeOpsHolder.U.putFloat(dest + 24L, _sp2 * _t57);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        return dest;
    }

    public static long toRigid_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.toRigid_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long toRigid_degenerate_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_degenerate_unsafe_fma(dest, src);
        return toRigid_degenerate_unsafe_mulAdd(dest, src);
    }

    /** {@code toRigid_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toRigid_degenerate_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
            UnsafeOpsHolder.U.putFloat(dest + 12L, _sp0 * _t182);
            UnsafeOpsHolder.U.putFloat(dest + 16L, _sp0 * _t201);
            UnsafeOpsHolder.U.putFloat(dest + 20L, _sp0 * _t202);
            UnsafeOpsHolder.U.putFloat(dest + 24L, 0.5f * (float) java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t208));
                UnsafeOpsHolder.U.putFloat(dest + 16L, _sp3 * _t199);
                UnsafeOpsHolder.U.putFloat(dest + 20L, _sp3 * _t200);
                UnsafeOpsHolder.U.putFloat(dest + 24L, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t199);
                    UnsafeOpsHolder.U.putFloat(dest + 16L, 0.5f * (float) java.lang.Math.sqrt(_t209));
                    UnsafeOpsHolder.U.putFloat(dest + 20L, _sp1 * _t184);
                    UnsafeOpsHolder.U.putFloat(dest + 24L, _sp1 * _t201);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t200);
                    UnsafeOpsHolder.U.putFloat(dest + 16L, _sp2 * _t184);
                    UnsafeOpsHolder.U.putFloat(dest + 20L, 0.5f * (float) java.lang.Math.sqrt(_t210));
                    UnsafeOpsHolder.U.putFloat(dest + 24L, _sp2 * _t202);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        return dest;
    }

    /** {@code toRigid_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toRigid_degenerate_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
            UnsafeOpsHolder.U.putFloat(dest + 12L, _sp0 * _t182);
            UnsafeOpsHolder.U.putFloat(dest + 16L, _sp0 * _t201);
            UnsafeOpsHolder.U.putFloat(dest + 20L, _sp0 * _t202);
            UnsafeOpsHolder.U.putFloat(dest + 24L, 0.5f * (float) java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t208));
                UnsafeOpsHolder.U.putFloat(dest + 16L, _sp3 * _t199);
                UnsafeOpsHolder.U.putFloat(dest + 20L, _sp3 * _t200);
                UnsafeOpsHolder.U.putFloat(dest + 24L, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t199);
                    UnsafeOpsHolder.U.putFloat(dest + 16L, 0.5f * (float) java.lang.Math.sqrt(_t209));
                    UnsafeOpsHolder.U.putFloat(dest + 20L, _sp1 * _t184);
                    UnsafeOpsHolder.U.putFloat(dest + 24L, _sp1 * _t201);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t200);
                    UnsafeOpsHolder.U.putFloat(dest + 16L, _sp2 * _t184);
                    UnsafeOpsHolder.U.putFloat(dest + 20L, 0.5f * (float) java.lang.Math.sqrt(_t210));
                    UnsafeOpsHolder.U.putFloat(dest + 24L, _sp2 * _t202);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        return dest;
    }

    public static long toTransform_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_unsafe_fma(dest, src);
        return toTransform_unsafe_mulAdd(dest, src);
    }

    /** {@code toTransform_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toTransform_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t12 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        float _t13 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        float _t14 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        return toTransform_unsafe_fma_sdc3b4542_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, (1.0f / (float) java.lang.Math.sqrt(_t12)), (1.0f / (float) java.lang.Math.sqrt(_t13)), (float) java.lang.Math.sqrt(_t14));
    }

    /** Piece 2 of {@code toTransform_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_fma_sdc3b4542_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18) {
        float _t17 = 1.0f / _t18;
        float _t19 = _self10 * _t17;
        float _t20 = _self22 * _t16;
        float _t21 = _self12 * _t16;
        float _t22 = _self20 * _t17;
        float _t24 = _self21 * _t15;
        float _t25 = _self11 * _t15;
        float _t27 = _self00 * _t17;
        float _t47 = java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _self01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _self02 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
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
        float _t52 = 1.0f + _t48;
        float _t64 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t52));
        return toTransform_unsafe_fma_sdc3b4542_2(dest, _self11, _self22, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, java.lang.Math.fma(_self12, _t16, _t24), java.lang.Math.fma(_self21, _t15, -_t21), Math.max(_t25, _t20), _t47, _t48, _t52, 1.0f - _t48, java.lang.Math.fma(_self01, _t15, _t49), java.lang.Math.fma(_self02, _t16, _t50), java.lang.Math.fma(_self02, _t16, -_t50), java.lang.Math.fma(-_self01, _t15, _t49), java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t48)), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)));
    }

    /** Piece 3 of {@code toTransform_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_fma_sdc3b4542_2(long dest, float _self11, float _self22, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t52, float _t53, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0) {
        float _t66 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t53));
        float _t67 = java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t53));
        float _t68 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        return toTransform_unsafe_fma_sdc3b4542_3(dest, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t55, _t56, _t57, _t58, _t63, _t64, _sp0, _t66, _t67, _sp1, _sp2, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68)));
    }

    /** Piece 4 of {@code toTransform_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_fma_sdc3b4542_3(long dest, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _sp1, float _sp2, float _sp3) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) java.lang.Math.sqrt(_t67));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t63 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t47 < 0.0f ? -_t18 : _t18);
        UnsafeOpsHolder.U.putFloat(dest + 32L, (float) java.lang.Math.sqrt(_t12));
        UnsafeOpsHolder.U.putFloat(dest + 36L, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code toTransform_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toTransform_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t12 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        float _t13 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        float _t14 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        return toTransform_unsafe_mulAdd_sf9411683_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, (1.0f / (float) java.lang.Math.sqrt(_t12)), (1.0f / (float) java.lang.Math.sqrt(_t13)), _t18, 1.0f / _t18);
    }

    /** Piece 2 of {@code toTransform_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_mulAdd_sf9411683_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t17) {
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
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        float _t64 = ((_self11) * (_t15) + (((_self22) * (_t16) + (_t52))));
        return toTransform_unsafe_mulAdd_sf9411683_2(dest, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, ((_self12) * (_t16) + (_t24)), ((_self21) * (_t15) - (_t21)), Math.max(_t25, _t20), _t47, _t48, _t52, ((_self01) * (_t15) + (_t49)), ((_self02) * (_t16) + (_t50)), ((_self02) * (_t16) - (_t50)), ((_t49) - (_self01) * (_t15)), ((_self11) * (_t15) + (((_self22) * (_t16) + (_t48)))), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)), ((_self11) * (_t15) + (((_t1) * (_t16) + (_t53)))), ((_self22) * (_t16) + (((_t0) * (_t15) + (_t53)))));
    }

    /** Piece 3 of {@code toTransform_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_mulAdd_sf9411683_2(long dest, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t52, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67) {
        float _t68 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        return toTransform_unsafe_mulAdd_sf9411683_3(dest, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t56, _t57, _t58, _t63, _t64, _sp0, _t67, _sp1, _sp2, _sp3);
    }

    /** Piece 4 of {@code toTransform_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_mulAdd_sf9411683_3(long dest, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t67, float _sp1, float _sp2, float _sp3) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) java.lang.Math.sqrt(_t67));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t63 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t47 < 0.0f ? -_t18 : _t18);
        UnsafeOpsHolder.U.putFloat(dest + 32L, (float) java.lang.Math.sqrt(_t12));
        UnsafeOpsHolder.U.putFloat(dest + 36L, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    public static long toTransform_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.toTransform_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long toTransform_degenerate_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_degenerate_unsafe_fma(dest, src);
        return toTransform_degenerate_unsafe_mulAdd(dest, src);
    }

    /** {@code toTransform_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toTransform_degenerate_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t196 < 0.0f ? -_t56 : _t56);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    /** {@code toTransform_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toTransform_degenerate_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) java.lang.Math.sqrt(_t213));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t209 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t196 < 0.0f ? -_t56 : _t56);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static long decomposeRotation_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeRotation_unsafe_fma(dest, src);
        return decomposeRotation_unsafe_mulAdd(dest, src);
    }

    /** {@code decomposeRotation_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeRotation_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t20 = -java.lang.Math.fma(_self21, _t7, java.lang.Math.fma(_self01, _t8, _self11 * _t9));
        return decomposeRotation_unsafe_fma_s5297f7fa_1(dest, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t8, _t9, -java.lang.Math.fma(_self22, _t7, java.lang.Math.fma(_self02, _t8, _self12 * _t9)), _t20, -java.lang.Math.fma(java.lang.Math.fma(_t20, _t7, _self21), _t7, java.lang.Math.fma(java.lang.Math.fma(_t20, _t8, _self01), _t8, java.lang.Math.fma(_t20, _t9, _self11) * _t9)));
    }

    /** Piece 2 of {@code decomposeRotation_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_fma_s5297f7fa_1(long dest, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t19, float _t20, float _t30) {
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
        return decomposeRotation_unsafe_fma_s5297f7fa_2(dest, _self02, _self12, _self22, _t7, _t8, _t9, _t19, _t44, _t45, _t46, java.lang.Math.fma(_t8, _t46, -(_t9 * _t45)), java.lang.Math.fma(_t9, _t44, -(_t7 * _t46)), java.lang.Math.fma(_t7, _t45, -(_t8 * _t44)), -java.lang.Math.fma(java.lang.Math.fma(_t19, _t7, _self22), _t44, java.lang.Math.fma(java.lang.Math.fma(_t19, _t8, _self02), _t45, java.lang.Math.fma(_t19, _t9, _self12) * _t46)));
    }

    /** Piece 3 of {@code decomposeRotation_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_fma_s5297f7fa_2(long dest, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t19, float _t44, float _t45, float _t46, float _t60, float _t61, float _t62, float _t68) {
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
        float _t94 = _t81 + _t84;
        float _t98 = 1.0f + _t94;
        float _t99 = 1.0f + (_t78 - (_t84 + _t46));
        float _t100 = 1.0f + (_t46 - (_t78 + _t84));
        float _t101 = 1.0f + (_t84 - _t81);
        return decomposeRotation_unsafe_fma_s5297f7fa_3(dest, _t46, _t78, _t84, _t79 + _t45, _t79 - _t45, _t44 - _t85, _t85 + _t44, _t80 + _t86, _t86 - _t80, _t94, _t98, _t99, _t100, _t101, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t101)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /** Piece 4 of {@code decomposeRotation_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_fma_s5297f7fa_3(long dest, float _t46, float _t78, float _t84, float _t82, float _t83, float _t87, float _t90, float _t92, float _t93, float _t94, float _t98, float _t99, float _t100, float _t101, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t94 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t87);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t93);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t83);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t98));
        } else {
            if (_t78 > Math.max(_t46, _t84)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t99));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t82);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t92);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t87);
            } else {
                if (_t46 > _t84) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t82);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t100));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t90);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t93);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t92);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t90);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t101));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t83);
                }
            }
        }
        return dest;
    }

    /** {@code decomposeRotation_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeRotation_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t20 = -((_self21) * (_t7) + (((_self01) * (_t8) + (_self11 * _t9))));
        float _t30 = -((((_t20) * (_t7) + (_self21))) * (_t7) + (((((_t20) * (_t8) + (_self01))) * (_t8) + (((_t20) * (_t9) + (_self11)) * _t9))));
        return decomposeRotation_unsafe_mulAdd_s6da5ce1b_1(dest, _self01, _self11, _self02, _self12, _self22, _t7, _t8, _t9, -((_self22) * (_t7) + (((_self02) * (_t8) + (_self12 * _t9)))), _t20, _t30, ((_t20) * (_t7) + (((_t30) * (_t7) + (_self21)))));
    }

    /** Piece 2 of {@code decomposeRotation_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_mulAdd_s6da5ce1b_1(long dest, float _self01, float _self11, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t19, float _t20, float _t30, float _t34) {
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
        return decomposeRotation_unsafe_mulAdd_s6da5ce1b_2(dest, _self02, _self12, _self22, _t7, _t8, _t9, _t19, _t44, _t45, _t46, ((_t8) * (_t46) - (_t9 * _t45)), ((_t9) * (_t44) - (_t7 * _t46)), ((_t7) * (_t45) - (_t8 * _t44)), -((((_t19) * (_t7) + (_self22))) * (_t44) + (((((_t19) * (_t8) + (_self02))) * (_t45) + (((_t19) * (_t9) + (_self12)) * _t46)))));
    }

    /** Piece 3 of {@code decomposeRotation_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_mulAdd_s6da5ce1b_2(long dest, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t19, float _t44, float _t45, float _t46, float _t60, float _t61, float _t62, float _t68) {
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
        float _t94 = _t81 + _t84;
        float _t98 = 1.0f + _t94;
        float _t99 = 1.0f + (_t78 - (_t84 + _t46));
        float _t100 = 1.0f + (_t46 - (_t78 + _t84));
        float _t101 = 1.0f + (_t84 - _t81);
        return decomposeRotation_unsafe_mulAdd_s6da5ce1b_3(dest, _t46, _t78, _t84, _t79 + _t45, _t79 - _t45, _t44 - _t85, _t85 + _t44, _t80 + _t86, _t86 - _t80, _t94, _t98, _t99, _t100, _t101, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t98)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t101)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)));
    }

    /** Piece 4 of {@code decomposeRotation_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_mulAdd_s6da5ce1b_3(long dest, float _t46, float _t78, float _t84, float _t82, float _t83, float _t87, float _t90, float _t92, float _t93, float _t94, float _t98, float _t99, float _t100, float _t101, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t94 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t87);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t93);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t83);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t98));
        } else {
            if (_t78 > Math.max(_t46, _t84)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t99));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t82);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t92);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t87);
            } else {
                if (_t46 > _t84) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t82);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t100));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t90);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t93);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t92);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t90);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t101));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t83);
                }
            }
        }
        return dest;
    }

    public static long decomposeScale_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeScale_unsafe_fma(dest, src);
        return decomposeScale_unsafe_mulAdd(dest, src);
    }

    /** {@code decomposeScale_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeScale_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t18 = -java.lang.Math.fma(_self21, _t8, java.lang.Math.fma(_self01, _t9, _self11 * _t10));
        return decomposeScale_unsafe_fma_s340113d2_1(dest, _self01, _self11, _self21, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -java.lang.Math.fma(_self22, _t8, java.lang.Math.fma(_self02, _t9, _self12 * _t10)), _t18, -java.lang.Math.fma(java.lang.Math.fma(_t18, _t8, _self21), _t8, java.lang.Math.fma(java.lang.Math.fma(_t18, _t9, _self01), _t9, java.lang.Math.fma(_t18, _t10, _self11) * _t10)));
    }

    /** Piece 2 of {@code decomposeScale_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeScale_unsafe_fma_s340113d2_1(long dest, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t4, float _t8, float _t9, float _t10, float _t17, float _t18, float _t28) {
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
        float _t52 = java.lang.Math.fma(_t17, _t8, java.lang.Math.fma(_t48, _t42, _self22));
        float _t53 = java.lang.Math.fma(_t17, _t9, java.lang.Math.fma(_t48, _t43, _self02));
        float _t54 = java.lang.Math.fma(_t17, _t10, java.lang.Math.fma(_t48, _t44, _self12));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t52, java.lang.Math.fma(_t9, _t44, -(_t10 * _t43)), java.lang.Math.fma(_t53, java.lang.Math.fma(_t10, _t42, -(_t8 * _t44)), _t54 * java.lang.Math.fma(_t8, _t43, -(_t9 * _t42)))) < 0.0f ? -_t4 : _t4);
        return decomposeScale_unsafe_fma_s340113d2_2(dest, _t37, _t52, _t53, _t54);
    }

    /** Piece 3 of {@code decomposeScale_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeScale_unsafe_fma_s340113d2_2(long dest, float _t37, float _t52, float _t53, float _t54) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) java.lang.Math.sqrt(_t37));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) java.lang.Math.sqrt(java.lang.Math.fma(_t52, _t52, java.lang.Math.fma(_t53, _t53, _t54 * _t54))));
        return dest;
    }

    /** {@code decomposeScale_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeScale_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t18 = -((_self21) * (_t8) + (((_self01) * (_t9) + (_self11 * _t10))));
        float _t28 = -((((_t18) * (_t8) + (_self21))) * (_t8) + (((((_t18) * (_t9) + (_self01))) * (_t9) + (((_t18) * (_t10) + (_self11)) * _t10))));
        return decomposeScale_unsafe_mulAdd_sadcb9cd3_1(dest, _self01, _self11, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -((_self22) * (_t8) + (((_self02) * (_t9) + (_self12 * _t10)))), _t18, _t28, ((_t18) * (_t8) + (((_t28) * (_t8) + (_self21)))));
    }

    /** Piece 2 of {@code decomposeScale_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeScale_unsafe_mulAdd_sadcb9cd3_1(long dest, float _self01, float _self11, float _self02, float _self12, float _self22, float _t4, float _t8, float _t9, float _t10, float _t17, float _t18, float _t28, float _t32) {
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
        float _t52 = ((_t17) * (_t8) + (((_t48) * (_t42) + (_self22))));
        float _t53 = ((_t17) * (_t9) + (((_t48) * (_t43) + (_self02))));
        float _t54 = ((_t17) * (_t10) + (((_t48) * (_t44) + (_self12))));
        UnsafeOpsHolder.U.putFloat(dest, ((_t52) * (((_t9) * (_t44) - (_t10 * _t43))) + (((_t53) * (((_t10) * (_t42) - (_t8 * _t44))) + (_t54 * ((_t8) * (_t43) - (_t9 * _t42)))))) < 0.0f ? -_t4 : _t4);
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) java.lang.Math.sqrt(_t37));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) java.lang.Math.sqrt(((_t52) * (_t52) + (((_t53) * (_t53) + (_t54 * _t54))))));
        return dest;
    }

    public static long decomposeSkew_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSkew_unsafe_fma(dest, src);
        return decomposeSkew_unsafe_mulAdd(dest, src);
    }

    /** {@code decomposeSkew_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSkew_unsafe_fma(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t17 = -_t15;
        return decomposeSkew_unsafe_fma_s6539706a_1(dest, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, _t17, java.lang.Math.fma(java.lang.Math.fma(_t17, _t7, _self21), _t7, java.lang.Math.fma(java.lang.Math.fma(_t17, _t8, _self01), _t8, java.lang.Math.fma(_t17, _t9, _self11) * _t9)));
    }

    /** Piece 2 of {@code decomposeSkew_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_fma_s6539706a_1(long dest, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t14, float _t15, float _t16, float _t17, float _t26) {
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
        float _t62 = java.lang.Math.fma(_t16, _t7, java.lang.Math.fma(_t58, _t43, _self22));
        float _t63 = java.lang.Math.fma(_t16, _t8, java.lang.Math.fma(_t58, _t44, _self02));
        float _t64 = java.lang.Math.fma(_t16, _t9, java.lang.Math.fma(_t58, _t45, _self12));
        float _t71 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t62, _t62, java.lang.Math.fma(_t63, _t63, _t64 * _t64))));
        return decomposeSkew_unsafe_fma_s6539706a_2(dest, _t7, _t8, _t9, (_t15 + _t26) * _t38, _t43, _t44, _t45, _t57, _t62, _t63, _t64, _t71, _t14 * _t71);
    }

    /** Piece 3 of {@code decomposeSkew_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_fma_s6539706a_2(long dest, float _t7, float _t8, float _t9, float _t39, float _t43, float _t44, float _t45, float _t57, float _t62, float _t63, float _t64, float _t71, float _t72) {
        if (java.lang.Math.fma(_t62, java.lang.Math.fma(_t8, _t45, -(_t9 * _t44)), java.lang.Math.fma(_t63, java.lang.Math.fma(_t9, _t43, -(_t7 * _t45)), _t64 * java.lang.Math.fma(_t7, _t44, -(_t8 * _t43)))) < 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, -_t72);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -_t39);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t72);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t39);
        }
        UnsafeOpsHolder.U.putFloat(dest, _t57 * _t71);
        return dest;
    }

    /** {@code decomposeSkew_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSkew_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t17 = -_t15;
        float _t26 = ((((_t17) * (_t7) + (_self21))) * (_t7) + (((((_t17) * (_t8) + (_self01))) * (_t8) + (((_t17) * (_t9) + (_self11)) * _t9))));
        return decomposeSkew_unsafe_mulAdd_s4970e4cb_1(dest, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, _t17, _t26, -_t26);
    }

    /** Piece 2 of {@code decomposeSkew_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_mulAdd_s4970e4cb_1(long dest, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t14, float _t15, float _t16, float _t17, float _t26, float _t27) {
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
        float _t71 = (1.0f / (float) java.lang.Math.sqrt(((_t62) * (_t62) + (((_t63) * (_t63) + (_t64 * _t64))))));
        return decomposeSkew_unsafe_mulAdd_s4970e4cb_2(dest, _t7, _t8, _t9, (_t15 + _t26) * _t38, _t43, _t44, _t45, _t57, _t62, _t63, _t64, _t71, _t14 * _t71);
    }

    /** Piece 3 of {@code decomposeSkew_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_mulAdd_s4970e4cb_2(long dest, float _t7, float _t8, float _t9, float _t39, float _t43, float _t44, float _t45, float _t57, float _t62, float _t63, float _t64, float _t71, float _t72) {
        if (((_t62) * (((_t8) * (_t45) - (_t9 * _t44))) + (((_t63) * (((_t9) * (_t43) - (_t7 * _t45))) + (_t64 * ((_t7) * (_t44) - (_t8 * _t43)))))) < 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, -_t72);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -_t39);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t72);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t39);
        }
        UnsafeOpsHolder.U.putFloat(dest, _t57 * _t71);
        return dest;
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 9; _i++) {
                float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
                float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
                UnsafeOpsHolder.U.putFloat(dest + _i * 4L, java.lang.Math.fma(t, _eother - _eself, _eself));
            }
            return dest;
        } else {
            for (int _i = 0; _i < 9; _i++) {
                float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
                float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
                UnsafeOpsHolder.U.putFloat(dest + _i * 4L, ((t) * (_eother - _eself) + (_eself)));
            }
            return dest;
        }
    }

    public static long mul_unsafe(long dest, long src, long right) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mul_unsafe_fma(dest, src, right);
        return mul_unsafe_mulAdd(dest, src, right);
    }

    /** {@code mul_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mul_unsafe_fma(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = UnsafeOpsHolder.U.getFloat(right + _lo * 4L);
            float _eright1 = UnsafeOpsHolder.U.getFloat(right + (_lo + 1) * 4L);
            float _eright2 = UnsafeOpsHolder.U.getFloat(right + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, java.lang.Math.fma(_eright2, _self02, java.lang.Math.fma(_eright0, _self00, _eright1 * _self01)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, java.lang.Math.fma(_eright2, _self12, java.lang.Math.fma(_eright0, _self10, _eright1 * _self11)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, java.lang.Math.fma(_eright2, _self22, java.lang.Math.fma(_eright0, _self20, _eright1 * _self21)));
        }
        return dest;
    }

    /** {@code mul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mul_unsafe_mulAdd(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = UnsafeOpsHolder.U.getFloat(right + _lo * 4L);
            float _eright1 = UnsafeOpsHolder.U.getFloat(right + (_lo + 1) * 4L);
            float _eright2 = UnsafeOpsHolder.U.getFloat(right + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, ((_eright2) * (_self02) + (((_eright0) * (_self00) + (_eright1 * _self01)))));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, ((_eright2) * (_self12) + (((_eright0) * (_self10) + (_eright1 * _self11)))));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, ((_eright2) * (_self22) + (((_eright0) * (_self20) + (_eright1 * _self21)))));
        }
        return dest;
    }

    public static long mulMat2x2_unsafe(long dest, long src, long right) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x2_unsafe_fma(dest, src, right);
        return mulMat2x2_unsafe_mulAdd(dest, src, right);
    }

    /** {@code mulMat2x2_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulMat2x2_unsafe_fma(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_right00, _self20, _right10 * _self21));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        return mulMat2x2_unsafe_fma_s63e69d83_1(dest, _self20, _self21, _self02, _self12, _self22, _right01, _right11);
    }

    /** Piece 2 of {@code mulMat2x2_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long mulMat2x2_unsafe_fma_s63e69d83_1(long dest, float _self20, float _self21, float _self02, float _self12, float _self22, float _right01, float _right11) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_right01, _self20, _right11 * _self21));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    /** {@code mulMat2x2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulMat2x2_unsafe_mulAdd(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((_right00) * (_self00) + (_right10 * _self01)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_right00) * (_self10) + (_right10 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_right00) * (_self20) + (_right10 * _self21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_right01) * (_self00) + (_right11 * _self01)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_right01) * (_self10) + (_right11 * _self11)));
        return mulMat2x2_unsafe_mulAdd_sc377e3cc_1(dest, _self20, _self21, _self02, _self12, _self22, _right01, _right11);
    }

    /** Piece 2 of {@code mulMat2x2_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long mulMat2x2_unsafe_mulAdd_sc377e3cc_1(long dest, float _self20, float _self21, float _self02, float _self12, float _self22, float _right01, float _right11) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_right01) * (_self20) + (_right11 * _self21)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long mulMat2x3_unsafe(long dest, long src, long right) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x3_unsafe_fma(dest, src, right);
        return mulMat2x3_unsafe_mulAdd(dest, src, right);
    }

    /** {@code mulMat2x3_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulMat2x3_unsafe_fma(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 12L);
        float _right02 = UnsafeOpsHolder.U.getFloat(right + 16L);
        float _right12 = UnsafeOpsHolder.U.getFloat(right + 20L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_right00, _self20, _right10 * _self21));
        return mulMat2x3_unsafe_fma_s34c7aaaa_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long mulMat2x3_unsafe_fma_s34c7aaaa_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _right01, float _right11, float _right02, float _right12) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_right01, _self20, _right11 * _self21));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_right02, _self00, java.lang.Math.fma(_right12, _self01, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_right02, _self10, java.lang.Math.fma(_right12, _self11, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_right02, _self20, java.lang.Math.fma(_right12, _self21, _self22)));
        return dest;
    }

    /** {@code mulMat2x3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulMat2x3_unsafe_mulAdd(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 12L);
        float _right02 = UnsafeOpsHolder.U.getFloat(right + 16L);
        float _right12 = UnsafeOpsHolder.U.getFloat(right + 20L);
        UnsafeOpsHolder.U.putFloat(dest, ((_right00) * (_self00) + (_right10 * _self01)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_right00) * (_self10) + (_right10 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_right00) * (_self20) + (_right10 * _self21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_right01) * (_self00) + (_right11 * _self01)));
        return mulMat2x3_unsafe_mulAdd_sc856cc7_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long mulMat2x3_unsafe_mulAdd_sc856cc7_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _right01, float _right11, float _right02, float _right12) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_right01) * (_self10) + (_right11 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_right01) * (_self20) + (_right11 * _self21)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_right02) * (_self00) + (((_right12) * (_self01) + (_self02)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_right02) * (_self10) + (((_right12) * (_self11) + (_self12)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_right02) * (_self20) + (((_right12) * (_self21) + (_self22)))));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_fma(dest, src, other);
        return preMul_unsafe_mulAdd(dest, src, other);
    }

    /** {@code preMul_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_fma(long dest, long src, long other) {
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other20 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other21 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 24L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 28L);
        float _other22 = UnsafeOpsHolder.U.getFloat(other + 32L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, java.lang.Math.fma(_other02, _eself2, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, java.lang.Math.fma(_other12, _eself2, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, java.lang.Math.fma(_other22, _eself2, java.lang.Math.fma(_other20, _eself0, _other21 * _eself1)));
        }
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, long other) {
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other20 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other21 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 24L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 28L);
        float _other22 = UnsafeOpsHolder.U.getFloat(other + 32L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, ((_other02) * (_eself2) + (((_other00) * (_eself0) + (_other01 * _eself1)))));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, ((_other12) * (_eself2) + (((_other10) * (_eself0) + (_other11 * _eself1)))));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, ((_other22) * (_eself2) + (((_other20) * (_eself0) + (_other21 * _eself1)))));
        }
        return dest;
    }

    public static long preMulMat2x2_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x2_unsafe_mulAdd(dest, src, other);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    /** {@code preMulMat2x2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulMat2x2_unsafe_mulAdd(long dest, long src, long other) {
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, ((_other00) * (_eself0) + (_other01 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, ((_other10) * (_eself0) + (_other11 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static long preMulMat2x3_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x3_unsafe_mulAdd(dest, src, other);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 20L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, java.lang.Math.fma(_other02, _eself2, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, java.lang.Math.fma(_other12, _eself2, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    /** {@code preMulMat2x3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulMat2x3_unsafe_mulAdd(long dest, long src, long other) {
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 20L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, ((_other02) * (_eself2) + (((_other00) * (_eself0) + (_other01 * _eself1)))));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, ((_other12) * (_eself2) + (((_other10) * (_eself0) + (_other11 * _eself1)))));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long other, float weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 9; _i++) {
                float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
                float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
                UnsafeOpsHolder.U.putFloat(dest + _i * 4L, java.lang.Math.fma(weight, _eother, _eself));
            }
            return dest;
        } else {
            for (int _i = 0; _i < 9; _i++) {
                float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
                float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
                UnsafeOpsHolder.U.putFloat(dest + _i * 4L, ((weight) * (_eother) + (_eself)));
            }
            return dest;
        }
    }

    public static long makeOuterProduct_unsafe(long dest, float colX, float colY, float colZ, float rowX, float rowY, float rowZ) {
        UnsafeOpsHolder.U.putFloat(dest, colX * rowX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, colY * rowX);
        UnsafeOpsHolder.U.putFloat(dest + 8L, colZ * rowX);
        UnsafeOpsHolder.U.putFloat(dest + 12L, colX * rowY);
        UnsafeOpsHolder.U.putFloat(dest + 16L, colY * rowY);
        UnsafeOpsHolder.U.putFloat(dest + 20L, colZ * rowY);
        UnsafeOpsHolder.U.putFloat(dest + 24L, colX * rowZ);
        UnsafeOpsHolder.U.putFloat(dest + 28L, colY * rowZ);
        UnsafeOpsHolder.U.putFloat(dest + 32L, colZ * rowZ);
        return dest;
    }

    public static long makeOuterProduct_unsafe(long dest, long col, long row) {
        float _colx = UnsafeOpsHolder.U.getFloat(col);
        float _coly = UnsafeOpsHolder.U.getFloat(col + 4L);
        float _colz = UnsafeOpsHolder.U.getFloat(col + 8L);
        float _rowx = UnsafeOpsHolder.U.getFloat(row);
        float _rowy = UnsafeOpsHolder.U.getFloat(row + 4L);
        float _rowz = UnsafeOpsHolder.U.getFloat(row + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _colx * _rowx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _coly * _rowx);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _colz * _rowx);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _colx * _rowy);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _coly * _rowy);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _colz * _rowy);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _colx * _rowz);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _coly * _rowz);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _colz * _rowz);
        return dest;
    }

    public static long lookAlong_lh(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAlong_lh_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t18 = java.lang.Math.fma(_t17, _t11, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        return lookAlong_lh_unsafe_fma_sc15c32f4_1(dest, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t18, _t20, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sc15c32f4_1(long dest, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t20, float _t27, float _t28) {
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
        float _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        return lookAlong_lh_unsafe_fma_sc15c32f4_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sc15c32f4_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t18 = ((_t17) * (_t11) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t12) + (upZ));
        return lookAlong_lh_unsafe_mulAdd_s588deedb_1(dest, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t11)), ((_t19) * (_t12) - (_t20 * _t13)), ((_t20) * (_t11) - (_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s588deedb_1(long dest, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        float _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        return lookAlong_lh_unsafe_mulAdd_s588deedb_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s588deedb_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAlong_rh_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
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
        return lookAlong_rh_unsafe_fma_s21542cce_2(dest, src, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _t30, _t31, _t32, java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s21542cce_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s21542cce_2(long dest, long src, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35) {
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
        return lookAlong_rh_unsafe_fma_s21542cce_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getFloat(src + 24L)), (-UnsafeOpsHolder.U.getFloat(src + 28L)), (-UnsafeOpsHolder.U.getFloat(src + 32L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
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
        return lookAlong_rh_unsafe_mulAdd_sac4825bd_2(dest, src, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sac4825bd_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        UnsafeOpsHolder.U.putFloat(dest, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sac4825bd_2(long dest, long src, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_unsafe_mulAdd_sac4825bd_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getFloat(src + 24L)), (-UnsafeOpsHolder.U.getFloat(src + 28L)), (-UnsafeOpsHolder.U.getFloat(src + 32L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    public static long lookAlong_lh(long dest, long src, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dir, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAlong_lh_unsafe(long dest, long src, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dir, up);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dir, up);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        return lookAlong_lh_unsafe_fma_s96ce96ea_1(dest, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s96ce96ea_1(long dest, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13) {
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
        float _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        return lookAlong_lh_unsafe_fma_s96ce96ea_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, _t46, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)), java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s96ce96ea_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        return lookAlong_lh_unsafe_mulAdd_se2d36c15_1(dest, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_se2d36c15_1(long dest, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13) {
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
        float _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        return lookAlong_lh_unsafe_mulAdd_se2d36c15_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, _t46, ((_t37) * (_t12) - (_t38 * _t11)), ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_se2d36c15_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dir, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAlong_rh_unsafe(long dest, long src, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dir, up);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dir, up);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, long dir, long up) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
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
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        return lookAlong_rh_unsafe_fma_s5403cc14_2(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _upx, _upy, _upz, java.lang.Math.fma(_t20, _t16, _upy), java.lang.Math.fma(_t20, _t14, _upx), java.lang.Math.fma(_t20, _t15, _upz));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5403cc14_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5403cc14_2(long dest, long src, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t14, float _t15, float _t16, float _upx, float _upy, float _upz, float _t21, float _t22, float _t23) {
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
        return lookAlong_rh_unsafe_fma_s5403cc14_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getFloat(src + 24L)), (-UnsafeOpsHolder.U.getFloat(src + 28L)), (-UnsafeOpsHolder.U.getFloat(src + 32L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, long dir, long up) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
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
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        return lookAlong_rh_unsafe_mulAdd_sfcfb9957_2(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _upx, _upy, _upz, ((_t20) * (_t16) + (_upy)), ((_t20) * (_t14) + (_upx)), ((_t20) * (_t15) + (_upz)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sfcfb9957_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        UnsafeOpsHolder.U.putFloat(dest, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sfcfb9957_2(long dest, long src, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t14, float _t15, float _t16, float _upx, float _upy, float _upz, float _t21, float _t22, float _t23) {
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
        return lookAlong_rh_unsafe_mulAdd_sfcfb9957_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getFloat(src + 24L)), (-UnsafeOpsHolder.U.getFloat(src + 28L)), (-UnsafeOpsHolder.U.getFloat(src + 32L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    public static long lookAlong_lh(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAlong_lh_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t18 = java.lang.Math.fma(_t17, _t11, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        return lookAlong_lh_unsafe_fma_se9067338_1(dest, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t18, _t20, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_se9067338_1(long dest, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t20, float _t27, float _t28) {
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
        float _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        return lookAlong_lh_unsafe_fma_se9067338_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_se9067338_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        float _t18 = ((_t17) * (_t11) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t12) + (upZ));
        return lookAlong_lh_unsafe_mulAdd_sb80cdb81_1(dest, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t11)), ((_t19) * (_t12) - (_t20 * _t13)), ((_t20) * (_t11) - (_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sb80cdb81_1(long dest, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        float _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        return lookAlong_lh_unsafe_mulAdd_sb80cdb81_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sb80cdb81_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t12, float _t13, float _t38, float _t39, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAlong_rh_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
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
        return lookAlong_rh_unsafe_fma_sceb4cb1a_2(dest, src, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _t30, _t31, _t32, java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_sceb4cb1a_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_sceb4cb1a_2(long dest, long src, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35) {
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
        return lookAlong_rh_unsafe_fma_sceb4cb1a_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getFloat(src + 24L)), (-UnsafeOpsHolder.U.getFloat(src + 28L)), (-UnsafeOpsHolder.U.getFloat(src + 32L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
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
        return lookAlong_rh_unsafe_mulAdd_s55703723_2(dest, src, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s55703723_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        UnsafeOpsHolder.U.putFloat(dest, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s55703723_2(long dest, long src, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_unsafe_mulAdd_s55703723_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getFloat(src + 24L)), (-UnsafeOpsHolder.U.getFloat(src + 28L)), (-UnsafeOpsHolder.U.getFloat(src + 32L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    public static long lookAlong_lh(long dest, long src, long dir, long up, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dir, up, epsilon);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAlong_lh_unsafe(long dest, long src, long dir, long up, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dir, up, epsilon);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dir, up, epsilon);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        return lookAlong_lh_unsafe_fma_sd82b6aa6_1(dest, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sd82b6aa6_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13) {
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
        float _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        return lookAlong_lh_unsafe_fma_sd82b6aa6_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, _t46, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)), java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sd82b6aa6_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        return lookAlong_lh_unsafe_mulAdd_sf9449a9b_1(dest, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sf9449a9b_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13) {
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
        float _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        return lookAlong_lh_unsafe_mulAdd_sf9449a9b_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, _t46, ((_t37) * (_t12) - (_t38 * _t11)), ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sf9449a9b_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, long dir, long up, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dir, up, epsilon);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAlong_rh_unsafe(long dest, long src, long dir, long up, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dir, up, epsilon);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dir, up, epsilon);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, long dir, long up, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
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
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        return lookAlong_rh_unsafe_fma_s3ec297d8_2(dest, src, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _upx, _upy, _upz, java.lang.Math.fma(_t20, _t16, _upy), java.lang.Math.fma(_t20, _t14, _upx), java.lang.Math.fma(_t20, _t15, _upz));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s3ec297d8_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        float _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        float _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s3ec297d8_2(long dest, long src, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t14, float _t15, float _t16, float _upx, float _upy, float _upz, float _t21, float _t22, float _t23) {
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
        return lookAlong_rh_unsafe_fma_s3ec297d8_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getFloat(src + 24L)), (-UnsafeOpsHolder.U.getFloat(src + 28L)), (-UnsafeOpsHolder.U.getFloat(src + 32L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, long dir, long up, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
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
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        return lookAlong_rh_unsafe_mulAdd_s1edaf42d_2(dest, src, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _upx, _upy, _upz, ((_t20) * (_t16) + (_upy)), ((_t20) * (_t14) + (_upx)), ((_t20) * (_t15) + (_upz)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s1edaf42d_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t0, float _t1, float _t2, float _t14, float _t15, float _t16, float _t40, float _t41, float _t42) {
        float _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        float _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        float _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        UnsafeOpsHolder.U.putFloat(dest, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s1edaf42d_2(long dest, long src, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t14, float _t15, float _t16, float _upx, float _upy, float _upz, float _t21, float _t22, float _t23) {
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
        return lookAlong_rh_unsafe_mulAdd_s1edaf42d_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getFloat(src + 24L)), (-UnsafeOpsHolder.U.getFloat(src + 28L)), (-UnsafeOpsHolder.U.getFloat(src + 32L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    public static long makeFromDualQuat_unsafe(long dest, float dqRX, float dqRY, float dqRZ, float dqRW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromDualQuat_unsafe_mulAdd(dest, dqRX, dqRY, dqRZ, dqRW);
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = java.lang.Math.fma(-2.0f, dqRZ * dqRZ, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, _t0, _t6));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * java.lang.Math.fma(dqRX, dqRY, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-2.0f, _t3, _sp0 * dqRZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(-2.0f, _t2, _sp0 * dqRY));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(-2.0f, _t4, _t6));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * java.lang.Math.fma(dqRX, dqRW, _t5));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * java.lang.Math.fma(dqRX, dqRZ, _t3));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(-2.0f, _t4, java.lang.Math.fma(-2.0f, _t0, 1.0f)));
        return dest;
    }

    /** {@code makeFromDualQuat_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeFromDualQuat_unsafe_mulAdd(long dest, float dqRX, float dqRY, float dqRZ, float dqRW) {
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = ((1.0f) - (2.0f) * (dqRZ * dqRZ));
        UnsafeOpsHolder.U.putFloat(dest, ((_t6) - (2.0f) * (_t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * ((dqRX) * (dqRY) + (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_sp0 * dqRZ) - (2.0f) * (_t3)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_sp0 * dqRY) - (2.0f) * (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t6) - (2.0f) * (_t4)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * ((dqRX) * (dqRW) + (_t5)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * ((dqRX) * (dqRZ) + (_t3)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t5 + _t5) - (2.0f) * (dqRX * dqRW)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((((1.0f) - (2.0f) * (_t0))) - (2.0f) * (_t4)));
        return dest;
    }

    public static long makeRotation_unsafe(long dest, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putFloat(dest, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_t1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationAxis_unsafe_mulAdd(dest, angle, axisX, axisY, axisZ);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisY;
        float _t4 = axisX * axisZ;
        float _t5 = axisY * axisZ;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, axisX * axisX, _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(axisZ, _t1, _t2 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, _t4, -(axisY * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t2, _t3, -(axisZ * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t2, axisY * axisY, _t0));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(axisX, _t1, _t2 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(axisY, _t1, _t2 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t2, _t5, -(axisX * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_t2, axisZ * axisZ, _t0));
        return dest;
    }

    /** {@code makeRotationAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationAxis_unsafe_mulAdd(long dest, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisY;
        float _t4 = axisX * axisZ;
        float _t5 = axisY * axisZ;
        UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (axisX * axisX) + (_t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((axisZ) * (_t1) + (_t2 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (_t4) - (axisY * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t2) * (_t3) - (axisZ * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t2) * (axisY * axisY) + (_t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((axisX) * (_t1) + (_t2 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((axisY) * (_t1) + (_t2 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t2) * (_t5) - (axisX * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_t2) * (axisZ * axisZ) + (_t0)));
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, long axis, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationAxis_unsafe_mulAdd(dest, axis, angle);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisy;
        float _t4 = _axisx * _axisz;
        float _t5 = _axisy * _axisz;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, _axisx * _axisx, _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_axisz, _t1, _t2 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, _t4, -(_axisy * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t2, _t3, -(_axisz * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t2, _axisy * _axisy, _t0));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_axisx, _t1, _t2 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_axisy, _t1, _t2 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t2, _t5, -(_axisx * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_t2, _axisz * _axisz, _t0));
        return dest;
    }

    /** {@code makeRotationAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationAxis_unsafe_mulAdd(long dest, long axis, float angle) {
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisy;
        float _t4 = _axisx * _axisz;
        float _t5 = _axisy * _axisz;
        UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (_axisx * _axisx) + (_t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_axisz) * (_t1) + (_t2 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (_t4) - (_axisy * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t2) * (_t3) - (_axisz * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t2) * (_axisy * _axisy) + (_t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_axisx) * (_t1) + (_t2 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_axisy) * (_t1) + (_t2 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t2) * (_t5) - (_axisx * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_t2) * (_axisz * _axisz) + (_t0)));
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        return makeRotationLookAlong_lh_unsafe_fma_sbd9c5394_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_sbd9c5394_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t39) * (_t13) - (_t38 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t12);
        return makeRotationLookAlong_lh_unsafe_mulAdd_s412e6e0b_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s412e6e0b_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t37) * (_t11) - (_t39 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t38) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        return makeRotationLookAlong_rh_unsafe_fma_s65b90eb6_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma_s65b90eb6_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t38) * (_t11) - (_t39 * _t13)));
        return makeRotationLookAlong_rh_unsafe_mulAdd_s38d74c95_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s38d74c95_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t39) * (_t12) - (_t37 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t37) * (_t13) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t11);
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dir, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dir, up);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dir, up);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_lh_unsafe_fma_s61209f8a_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s61209f8a_1(long dest, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_lh_unsafe_mulAdd_sbd37ae05_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_sbd37ae05_1(long dest, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t39) * (_t13) - (_t38 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t37) * (_t11) - (_t39 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t38) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dir, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dir, up);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dir, up);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_1(long dest, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_rh_unsafe_mulAdd_s24c40fcf_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s24c40fcf_1(long dest, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t38) * (_t11) - (_t39 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t39) * (_t12) - (_t37 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t37) * (_t13) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t11);
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        return makeRotationLookAlong_lh_unsafe_fma_s8af24258_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s8af24258_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t39) * (_t13) - (_t38 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t12);
        return makeRotationLookAlong_lh_unsafe_mulAdd_s5838e3f1_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s5838e3f1_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t37) * (_t11) - (_t39 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t38) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        return makeRotationLookAlong_rh_unsafe_fma_sa78778c2_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma_sa78778c2_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t38) * (_t11) - (_t39 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t12);
        return makeRotationLookAlong_rh_unsafe_mulAdd_sbb15e81b_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_sbb15e81b_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t39) * (_t12) - (_t37 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t37) * (_t13) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t11);
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, long dir, long up, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dir, up, epsilon);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, long dir, long up, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dir, up, epsilon);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dir, up, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_lh_unsafe_fma_sa328e446_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_sa328e446_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_lh_unsafe_mulAdd_s9a457a8b_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s9a457a8b_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t39) * (_t13) - (_t38 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t37) * (_t11) - (_t39 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t38) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, long dir, long up, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dir, up, epsilon);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, long dir, long up, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dir, up, epsilon);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dir, up, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_rh_unsafe_fma_sbdd3a500_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma_sbdd3a500_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_rh_unsafe_mulAdd_sd94b05_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_sd94b05_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t38) * (_t11) - (_t39 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t39) * (_t12) - (_t37 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_t13);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t37) * (_t13) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t11);
        return dest;
    }

    public static long makeRotationQuat_unsafe(long dest, float qX, float qY, float qZ, float qW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationQuat_unsafe_mulAdd(dest, qX, qY, qZ, qW);
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, java.lang.Math.fma(qY, qY, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * java.lang.Math.fma(qX, qY, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * java.lang.Math.fma(qX, qZ, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * java.lang.Math.fma(qX, qY, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * java.lang.Math.fma(qX, qW, qY * qZ));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * java.lang.Math.fma(qX, qZ, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * java.lang.Math.fma(qY, qZ, -(qX * qW)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(qX, qX, qY * qY), 1.0f));
        return dest;
    }

    /** {@code makeRotationQuat_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationQuat_unsafe_mulAdd(long dest, float qX, float qY, float qZ, float qW) {
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        UnsafeOpsHolder.U.putFloat(dest, ((1.0f) - (2.0f) * (((qY) * (qY) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * ((qX) * (qY) + (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * ((qX) * (qZ) - (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * ((qX) * (qY) - (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((1.0f) - (2.0f) * (((qX) * (qX) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * ((qX) * (qW) + (qY * qZ)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * ((qX) * (qZ) + (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * ((qY) * (qZ) - (qX * qW)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((1.0f) - (2.0f) * (((qX) * (qX) + (qY * qY)))));
        return dest;
    }

    public static long makeRotationQuat_unsafe(long dest, long q) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationQuat_unsafe_mulAdd(dest, q);
        float _qx = UnsafeOpsHolder.U.getFloat(q);
        float _qy = UnsafeOpsHolder.U.getFloat(q + 4L);
        float _qz = UnsafeOpsHolder.U.getFloat(q + 8L);
        float _qw = UnsafeOpsHolder.U.getFloat(q + 12L);
        float _t0 = _qz * _qz;
        float _t1 = _qz * _qw;
        float _t2 = _qy * _qw;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_qy, _qy, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * java.lang.Math.fma(_qx, _qy, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * java.lang.Math.fma(_qx, _qz, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * java.lang.Math.fma(_qx, _qy, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_qx, _qx, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * java.lang.Math.fma(_qx, _qw, _qy * _qz));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * java.lang.Math.fma(_qx, _qz, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * java.lang.Math.fma(_qy, _qz, -(_qx * _qw)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_qx, _qx, _qy * _qy), 1.0f));
        return dest;
    }

    /** {@code makeRotationQuat_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationQuat_unsafe_mulAdd(long dest, long q) {
        float _qx = UnsafeOpsHolder.U.getFloat(q);
        float _qy = UnsafeOpsHolder.U.getFloat(q + 4L);
        float _qz = UnsafeOpsHolder.U.getFloat(q + 8L);
        float _qw = UnsafeOpsHolder.U.getFloat(q + 12L);
        float _t0 = _qz * _qz;
        float _t1 = _qz * _qw;
        float _t2 = _qy * _qw;
        UnsafeOpsHolder.U.putFloat(dest, ((1.0f) - (2.0f) * (((_qy) * (_qy) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * ((_qx) * (_qy) + (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * ((_qx) * (_qz) - (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * ((_qx) * (_qy) - (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((1.0f) - (2.0f) * (((_qx) * (_qx) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * ((_qx) * (_qw) + (_qy * _qz)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * ((_qx) * (_qz) + (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * ((_qy) * (_qz) - (_qx * _qw)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((1.0f) - (2.0f) * (((_qx) * (_qx) + (_qy * _qy)))));
        return dest;
    }

    public static long makeRotationTowards_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationTowards_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_fma(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t39);
        return makeRotationTowards_unsafe_fma_s9adebf8b_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_fma_s9adebf8b_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t38);
        return makeRotationTowards_unsafe_mulAdd_sb15958ee_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd_sb15958ee_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t38) * (_t13) - (_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t37) * (_t11) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t39) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    public static long makeRotationTowards_unsafe(long dest, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_unsafe_fma(dest, dir, up);
        return makeRotationTowards_unsafe_mulAdd(dest, dir, up);
    }

    /** {@code makeRotationTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_fma(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationTowards_unsafe_fma_se4ab2385_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_fma_se4ab2385_1(long dest, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationTowards_unsafe_mulAdd_s1180c334_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd_s1180c334_1(long dest, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t38) * (_t13) - (_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t37) * (_t11) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t39) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    public static long makeRotationTowards_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationTowards_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_fma(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t39);
        return makeRotationTowards_unsafe_fma_sefe75c71_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_fma_sefe75c71_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t38);
        return makeRotationTowards_unsafe_mulAdd_se915e13a_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd_se915e13a_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t38) * (_t13) - (_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t37) * (_t11) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t39) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    public static long makeRotationTowards_unsafe(long dest, long dir, long up, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_unsafe_fma(dest, dir, up, epsilon);
        return makeRotationTowards_unsafe_mulAdd(dest, dir, up, epsilon);
    }

    /** {@code makeRotationTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_fma(long dest, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationTowards_unsafe_fma_s91f5bf0b_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_fma_s91f5bf0b_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd(long dest, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
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
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationTowards_unsafe_mulAdd_s2df80ef8_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd_s2df80ef8_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        UnsafeOpsHolder.U.putFloat(dest, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t38) * (_t13) - (_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t37) * (_t11) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t39) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    public static long makeRotationX_unsafe(long dest, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -_t1);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t0);
        return dest;
    }

    public static long makeRotationXYZ_unsafe(long dest, float angleX, float angleY, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXYZ_unsafe_mulAdd(dest, angleX, angleY, angleZ);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t3;
        float _t7 = _t3 * _t5;
        UnsafeOpsHolder.U.putFloat(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t6, _t1, _t2 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t4, _t2, -(_t7 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_t2 * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t5, _t1, _t6 * -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t7, _t2, _t4 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t3);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -(_t4 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationXYZ_unsafe_mulAdd(long dest, float angleX, float angleY, float angleZ) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t3;
        float _t7 = _t3 * _t5;
        UnsafeOpsHolder.U.putFloat(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t6) * (_t1) + (_t2 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t4) * (_t2) - (_t7 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_t2 * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t5) * (_t1) - (_t6 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t7) * (_t2) + (_t4 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t3);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -(_t4 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t0);
        return dest;
    }

    public static long makeRotationXZY_unsafe(long dest, float angleX, float angleZ, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXZY_unsafe_mulAdd(dest, angleX, angleZ, angleY);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        float _t6 = _t2 * _t4;
        float _t7 = _t5 * _t2;
        UnsafeOpsHolder.U.putFloat(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t6, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t7, _t0, -(_t3 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_t2);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t4 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t5 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t3 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t6, _t3, -(_t5 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_t7, _t3, _t4 * _t0));
        return dest;
    }

    /** {@code makeRotationXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationXZY_unsafe_mulAdd(long dest, float angleX, float angleZ, float angleY) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        float _t6 = _t2 * _t4;
        float _t7 = _t5 * _t2;
        UnsafeOpsHolder.U.putFloat(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t6) * (_t0) + (_t5 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t7) * (_t0) - (_t3 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_t2);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t4 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t5 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t3 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t6) * (_t3) - (_t5 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_t7) * (_t3) + (_t4 * _t0)));
        return dest;
    }

    public static long makeRotationY_unsafe(long dest, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putFloat(dest, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t1);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t0);
        return dest;
    }

    public static long makeRotationYXZ_unsafe(long dest, float angleY, float angleX, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYXZ_unsafe_mulAdd(dest, angleY, angleX, angleZ);
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.cosWithSin(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t6, _t2, _t3 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t2 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t7, _t2, -(_t1 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t6, _t4, -(_t2 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t5 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t7, _t4, _t1 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t1 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t3);
        return dest;
    }

    /** {@code makeRotationYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationYXZ_unsafe_mulAdd(long dest, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.cosWithSin(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, ((_t6) * (_t2) + (_t3 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t2 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t7) * (_t2) - (_t1 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t6) * (_t4) - (_t2 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t5 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t7) * (_t4) + (_t1 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t1 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t3);
        return dest;
    }

    public static long makeRotationYZX_unsafe(long dest, float angleY, float angleZ, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYZX_unsafe_mulAdd(dest, angleY, angleZ, angleX);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t0;
        float _t7 = _t3 * _t4;
        UnsafeOpsHolder.U.putFloat(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t4);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t3 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t2, _t3, -(_t6 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t5 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_t7, _t5, _t2 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_t6, _t2, _t3 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 28L, -_t2 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_t5, _t0, _t7 * -_t2));
        return dest;
    }

    /** {@code makeRotationYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationYZX_unsafe_mulAdd(long dest, float angleY, float angleZ, float angleX) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t4 * _t0;
        float _t7 = _t3 * _t4;
        UnsafeOpsHolder.U.putFloat(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t4);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t3 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t2) * (_t3) - (_t6 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t5 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_t7) * (_t5) + (_t2 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_t6) * (_t2) + (_t3 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, -_t2 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_t5) * (_t0) - (_t7 * _t2)));
        return dest;
    }

    public static long makeRotationZXY_unsafe(long dest, float angleZ, float angleX, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZXY_unsafe_mulAdd(dest, angleZ, angleX, angleY);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleY);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t3;
        float _t7 = _t2 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t0, _t1, _t6 * -_t4));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t7, _t4, _t3 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t4 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -(_t3 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t5 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t2);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_t6, _t0, _t4 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t4, _t3, -(_t7 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationZXY_unsafe_mulAdd(long dest, float angleZ, float angleX, float angleY) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleX);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleY);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t3;
        float _t7 = _t2 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, ((_t0) * (_t1) - (_t6 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t7) * (_t4) + (_t3 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t4 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -(_t3 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t5 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t2);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_t6) * (_t0) + (_t4 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t4) * (_t3) - (_t7 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t0);
        return dest;
    }

    public static long makeRotationZYX_unsafe(long dest, float angleZ, float angleY, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZYX_unsafe_mulAdd(dest, angleZ, angleY, angleX);
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.sinWithCos(angleX);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        UnsafeOpsHolder.U.putFloat(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t4 * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t2);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t6, _t3, -(_t4 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_t7, _t3, _t5 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t3 * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_t6, _t5, _t3 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_t7, _t5, -(_t3 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationZYX_unsafe_mulAdd(long dest, float angleZ, float angleY, float angleX) {
        float _t0 = Math.cosWithSin(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.sinWithCos(angleX);
        float _t4 = Math.sinWithCos(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        UnsafeOpsHolder.U.putFloat(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t4 * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t2);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t6) * (_t3) - (_t4 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_t7) * (_t3) + (_t5 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t3 * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_t6) * (_t5) + (_t3 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_t7) * (_t5) - (_t3 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, float vX, float vY) {
        UnsafeOpsHolder.U.putFloat(dest, vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, vY);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _vy);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, float s) {
        UnsafeOpsHolder.U.putFloat(dest, s);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, s);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, float vX, float vY) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, vX);
        UnsafeOpsHolder.U.putFloat(dest + 28L, vY);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _vy);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long makeView_unsafe(long dest, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        UnsafeOpsHolder.U.putFloat(dest, _t0_inv + _t0_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t1_inv + _t1_inv);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -(left + right) * _t0_inv);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -(bottom + top) * _t1_inv);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long preRotate_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_unsafe_fma(dest, src, angle);
        return preRotate_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code preRotate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotate_unsafe_fma(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self02, _t1, _self12 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    /** {@code preRotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotate_unsafe_mulAdd(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self00) * (_t1) + (_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self01) * (_t0) - (_self11 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self01) * (_t1) + (_self11 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t0) - (_self12 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self02) * (_t1) + (_self12 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, float angle, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_unsafe_fma(dest, src, angle, pivotX, pivotY);
        return preRotateAround_unsafe_mulAdd(dest, src, angle, pivotX, pivotY);
    }

    /** {@code preRotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAround_unsafe_fma(long dest, long src, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        float _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self20, _t11, java.lang.Math.fma(_self00, _t1, _self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self21, _t10, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1))));
        return preRotateAround_unsafe_fma_scfb92c84_1(dest, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_fma_scfb92c84_1(long dest, float _t0, float _t1, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self21, _t11, java.lang.Math.fma(_self01, _t1, _self11 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self22, _t10, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self02, _t1, _self12 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    /** {@code preRotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAround_unsafe_mulAdd(long dest, long src, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        float _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        UnsafeOpsHolder.U.putFloat(dest, ((_self20) * (_t10) + (((_self00) * (_t0) - (_self10 * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self20) * (_t11) + (((_self00) * (_t1) + (_self10 * _t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self21) * (_t10) + (((_self01) * (_t0) - (_self11 * _t1)))));
        return preRotateAround_unsafe_mulAdd_s1373614f_1(dest, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_mulAdd_s1373614f_1(long dest, float _t0, float _t1, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self21) * (_t11) + (((_self01) * (_t1) + (_self11 * _t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self22) * (_t10) + (((_self02) * (_t0) - (_self12 * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self22) * (_t11) + (((_self02) * (_t1) + (_self12 * _t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, long pivot, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_unsafe_fma(dest, src, pivot, angle);
        return preRotateAround_unsafe_mulAdd(dest, src, pivot, angle);
    }

    /** {@code preRotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAround_unsafe_fma(long dest, long src, long pivot, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1);
        float _t11 = java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self20, _t11, java.lang.Math.fma(_self00, _t1, _self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        return preRotateAround_unsafe_fma_sabe86ea9_1(dest, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_fma_sabe86ea9_1(long dest, float _t0, float _t1, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self21, _t10, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self21, _t11, java.lang.Math.fma(_self01, _t1, _self11 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self22, _t10, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self02, _t1, _self12 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    /** {@code preRotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAround_unsafe_mulAdd(long dest, long src, long pivot, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((_pivotx) * (_t9) + (_pivoty * _t1));
        float _t11 = ((_pivoty) * (_t9) - (_pivotx * _t1));
        UnsafeOpsHolder.U.putFloat(dest, ((_self20) * (_t10) + (((_self00) * (_t0) - (_self10 * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self20) * (_t11) + (((_self00) * (_t1) + (_self10 * _t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        return preRotateAround_unsafe_mulAdd_s893ff242_1(dest, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_mulAdd_s893ff242_1(long dest, float _t0, float _t1, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self21) * (_t10) + (((_self01) * (_t0) - (_self11 * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self21) * (_t11) + (((_self01) * (_t1) + (_self11 * _t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self22) * (_t10) + (((_self02) * (_t0) - (_self12 * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self22) * (_t11) + (((_self02) * (_t1) + (_self12 * _t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preRotateAxis_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_unsafe_fma(dest, src, angle, axisX, axisY, axisZ);
        return preRotateAxis_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ);
    }

    /** {@code preRotateAxis_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAxis_unsafe_fma(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = 1.0f - _t1;
        float _t3 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t7 = axisY * axisZ;
        return preRotateAxis_unsafe_fma_s10e9f245_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_t2, axisX * axisX, _t1), java.lang.Math.fma(_t2, axisY * axisY, _t1), java.lang.Math.fma(_t2, axisZ * axisZ, _t1), java.lang.Math.fma(axisY, _t0, _t2 * _t3), java.lang.Math.fma(axisZ, _t0, _t2 * _t5), java.lang.Math.fma(axisX, _t0, _t2 * _t7), java.lang.Math.fma(_t2, _t5, -(axisZ * _t0)), java.lang.Math.fma(_t2, _t7, -(axisX * _t0)), java.lang.Math.fma(_t2, _t3, -(axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_fma_s10e9f245_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    /** {@code preRotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAxis_unsafe_mulAdd(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = 1.0f - _t1;
        float _t3 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t7 = axisY * axisZ;
        return preRotateAxis_unsafe_mulAdd_s1d5e910c_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_t2) * (axisX * axisX) + (_t1)), ((_t2) * (axisY * axisY) + (_t1)), ((_t2) * (axisZ * axisZ) + (_t1)), ((axisY) * (_t0) + (_t2 * _t3)), ((axisZ) * (_t0) + (_t2 * _t5)), ((axisX) * (_t0) + (_t2 * _t7)), ((_t2) * (_t5) - (axisZ * _t0)), ((_t2) * (_t7) - (axisX * _t0)), ((_t2) * (_t3) - (axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_mulAdd_s1d5e910c_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest, ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23)))));
        return dest;
    }

    public static long preRotateAxis_unsafe(long dest, long src, long axis, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_unsafe_fma(dest, src, axis, angle);
        return preRotateAxis_unsafe_mulAdd(dest, src, axis, angle);
    }

    /** {@code preRotateAxis_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAxis_unsafe_fma(long dest, long src, long axis, float angle) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = 1.0f - _t1;
        float _t3 = _axisx * _axisz;
        float _t5 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return preRotateAxis_unsafe_fma_s202c1baa_1(dest, _t0, _axisx, _axisy, _axisz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t3, _t5, _t7, java.lang.Math.fma(_t2, _axisx * _axisx, _t1), java.lang.Math.fma(_t2, _axisy * _axisy, _t1), java.lang.Math.fma(_t2, _axisz * _axisz, _t1), java.lang.Math.fma(_axisy, _t0, _t2 * _t3), java.lang.Math.fma(_axisz, _t0, _t2 * _t5), java.lang.Math.fma(_axisx, _t0, _t2 * _t7));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_fma_s202c1baa_1(long dest, float _t0, float _axisx, float _axisy, float _axisz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3, float _t5, float _t7, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = java.lang.Math.fma(_t2, _t5, -(_axisz * _t0));
        float _t25 = java.lang.Math.fma(_t2, _t7, -(_axisx * _t0));
        float _t26 = java.lang.Math.fma(_t2, _t3, -(_axisy * _t0));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    /** {@code preRotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAxis_unsafe_mulAdd(long dest, long src, long axis, float angle) {
        float _t0 = Math.sinWithCos(angle);
        float _t1 = Math.cosWithSin(angle);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = 1.0f - _t1;
        float _t3 = _axisx * _axisz;
        float _t5 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return preRotateAxis_unsafe_mulAdd_s936637bf_1(dest, _t0, _axisx, _axisy, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t3, _t7, ((_t2) * (_axisx * _axisx) + (_t1)), ((_t2) * (_axisy * _axisy) + (_t1)), ((_t2) * (_axisz * _axisz) + (_t1)), ((_axisy) * (_t0) + (_t2 * _t3)), ((_axisz) * (_t0) + (_t2 * _t5)), ((_axisx) * (_t0) + (_t2 * _t7)), ((_t2) * (_t5) - (_axisz * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_mulAdd_s936637bf_1(long dest, float _t0, float _axisx, float _axisy, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3, float _t7, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
        float _t25 = ((_t2) * (_t7) - (_axisx * _t0));
        float _t26 = ((_t2) * (_t3) - (_axisy * _t0));
        UnsafeOpsHolder.U.putFloat(dest, ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23)))));
        return dest;
    }

    public static long preRotateX_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateX_unsafe_fma(dest, src, angle);
        return preRotateX_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code preRotateX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateX_unsafe_fma(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, _t0, -(_self20 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self10, _t1, _self20 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self11, _t0, -(_self21 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self11, _t1, _self21 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t0, -(_self22 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self12, _t1, _self22 * _t0));
        return dest;
    }

    /** {@code preRotateX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateX_unsafe_mulAdd(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (_t0) - (_self20 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self10) * (_t1) + (_self20 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self11) * (_t0) - (_self21 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self11) * (_t1) + (_self21 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t0) - (_self22 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self12) * (_t1) + (_self22 * _t0)));
        return dest;
    }

    public static long preRotateY_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateY_unsafe_fma(dest, src, angle);
        return preRotateY_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code preRotateY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateY_unsafe_fma(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, _t0, _self20 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self20, _t0, -(_self00 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self01, _t0, _self21 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self21, _t0, -(_self01 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t0, _self22 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t0, -(_self02 * _t1)));
        return dest;
    }

    /** {@code preRotateY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateY_unsafe_mulAdd(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (_t0) + (_self20 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self20) * (_t0) - (_self00 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self01) * (_t0) + (_self21 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self21) * (_t0) - (_self01 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t0) + (_self22 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t0) - (_self02 * _t1)));
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, float vX, float vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0 * vX);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1 * vY);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0 * _vx);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1 * _vy);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, s * _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, s * _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, float s, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_fma(dest, src, s, pivotX, pivotY);
        return preScaleAround_unsafe_mulAdd(dest, src, s, pivotX, pivotY);
    }

    /** {@code preScaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_fma(long dest, long src, float s, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(s, _self00, _self20 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(s, _self10, _self20 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(s, _self01, _self21 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(s, _self11, _self21 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(s, _self02, _self22 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(s, _self12, _self22 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, float s, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        UnsafeOpsHolder.U.putFloat(dest, ((s) * (_self00) + (_self20 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((s) * (_self10) + (_self20 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((s) * (_self01) + (_self21 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((s) * (_self11) + (_self21 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((s) * (_self02) + (_self22 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((s) * (_self12) + (_self22 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long pivot, float s) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_fma(dest, src, pivot, s);
        return preScaleAround_unsafe_mulAdd(dest, src, pivot, s);
    }

    /** {@code preScaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_fma(long dest, long src, long pivot, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0 = 1.0f - s;
        float _t1 = UnsafeOpsHolder.U.getFloat(pivot) * _t0;
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * _t0;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(s, _self00, _self20 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(s, _self10, _self20 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(s, _self01, _self21 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(s, _self11, _self21 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        return preScaleAround_unsafe_fma_s1f4c2072_1(dest, s, _self02, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code preScaleAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_fma_s1f4c2072_1(long dest, float s, float _self02, float _self12, float _self22, float _t1, float _t2) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(s, _self02, _self22 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(s, _self12, _self22 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, long pivot, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0 = 1.0f - s;
        float _t1 = UnsafeOpsHolder.U.getFloat(pivot) * _t0;
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * _t0;
        UnsafeOpsHolder.U.putFloat(dest, ((s) * (_self00) + (_self20 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((s) * (_self10) + (_self20 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((s) * (_self01) + (_self21 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((s) * (_self11) + (_self21 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        return preScaleAround_unsafe_mulAdd_sc6969ceb_1(dest, s, _self02, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code preScaleAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_mulAdd_sc6969ceb_1(long dest, float s, float _self02, float _self12, float _self22, float _t1, float _t2) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((s) * (_self02) + (_self22 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((s) * (_self12) + (_self22 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_fma(dest, src, sX, sY, pivotX, pivotY);
        return preScaleAround_unsafe_mulAdd(dest, src, sX, sY, pivotX, pivotY);
    }

    /** {@code preScaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_fma(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(sX, _self00, _self20 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(sY, _self10, _self20 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(sX, _self01, _self21 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(sY, _self11, _self21 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(sX, _self02, _self22 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(sY, _self12, _self22 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        UnsafeOpsHolder.U.putFloat(dest, ((sX) * (_self00) + (_self20 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((sY) * (_self10) + (_self20 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((sX) * (_self01) + (_self21 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((sY) * (_self11) + (_self21 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((sX) * (_self02) + (_self22 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((sY) * (_self12) + (_self22 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long s, long pivot) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_fma(dest, src, s, pivot);
        return preScaleAround_unsafe_mulAdd(dest, src, s, pivot);
    }

    /** {@code preScaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_fma(long dest, long src, long s, long pivot) {
        float _sx = UnsafeOpsHolder.U.getFloat(s);
        float _sy = UnsafeOpsHolder.U.getFloat(s + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot) * (1.0f - _sx);
        float _t3 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * (1.0f - _sy);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_sx, _self00, _self20 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_sy, _self10, _self20 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_sx, _self01, _self21 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_sy, _self11, _self21 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        return preScaleAround_unsafe_fma_s7c75a1cc_1(dest, _sx, _sy, _self02, _self12, _self22, _t2, _t3);
    }

    /** Piece 2 of {@code preScaleAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_fma_s7c75a1cc_1(long dest, float _sx, float _sy, float _self02, float _self12, float _self22, float _t2, float _t3) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_sx, _self02, _self22 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_sy, _self12, _self22 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, long s, long pivot) {
        float _sx = UnsafeOpsHolder.U.getFloat(s);
        float _sy = UnsafeOpsHolder.U.getFloat(s + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot) * (1.0f - _sx);
        float _t3 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * (1.0f - _sy);
        UnsafeOpsHolder.U.putFloat(dest, ((_sx) * (_self00) + (_self20 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_sy) * (_self10) + (_self20 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_sx) * (_self01) + (_self21 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_sy) * (_self11) + (_self21 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        return preScaleAround_unsafe_mulAdd_sf66134d7_1(dest, _sx, _sy, _self02, _self12, _self22, _t2, _t3);
    }

    /** Piece 2 of {@code preScaleAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_mulAdd_sf66134d7_1(long dest, float _sx, float _sy, float _self02, float _self12, float _self22, float _t2, float _t3) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_sx) * (_self02) + (_self22 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_sy) * (_self12) + (_self22 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, float vX, float vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
                float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
                float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
                UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, java.lang.Math.fma(_eself2, vX, _eself0));
                UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, java.lang.Math.fma(_eself2, vY, _eself1));
                UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            }
            return dest;
        } else {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
                float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
                float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
                UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, ((_eself2) * (vX) + (_eself0)));
                UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, ((_eself2) * (vY) + (_eself1)));
                UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            }
            return dest;
        }
    }

    public static long preTranslate_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preTranslate_unsafe_mulAdd(dest, src, v);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, java.lang.Math.fma(_eself2, _vx, _eself0));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, java.lang.Math.fma(_eself2, _vy, _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    /** {@code preTranslate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preTranslate_unsafe_mulAdd(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, ((_eself2) * (_vx) + (_eself0)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, ((_eself2) * (_vy) + (_eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotate_unsafe_fma(dest, src, angle);
        return rotate_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code rotate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_fma(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    /** {@code rotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_mulAdd(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (_t0) + (_self01 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (_t0) + (_self11 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self20) * (_t0) + (_self21 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self01) * (_t0) - (_self00 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self11) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self21) * (_t0) - (_self20 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, float angle, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_unsafe_fma(dest, src, angle, pivotX, pivotY);
        return rotateAround_unsafe_mulAdd(dest, src, angle, pivotX, pivotY);
    }

    /** {@code rotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_fma(long dest, long src, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        return rotateAround_unsafe_fma_s50bb4c53_1(dest, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(pivotX, _t9, pivotY * _t1), java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_fma_s50bb4c53_1(long dest, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self21, _t11, _self22)));
        return dest;
    }

    /** {@code rotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_mulAdd(long dest, long src, float angle, float pivotX, float pivotY) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (_t0) + (_self01 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (_t0) + (_self11 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self20) * (_t0) + (_self21 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self01) * (_t0) - (_self00 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self11) * (_t0) - (_self10 * _t1)));
        return rotateAround_unsafe_mulAdd_s3c8ab59e_1(dest, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((pivotX) * (_t9) + (pivotY * _t1)), ((pivotY) * (_t9) - (pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_mulAdd_s3c8ab59e_1(long dest, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self21) * (_t0) - (_self20 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self20) * (_t10) + (((_self21) * (_t11) + (_self22)))));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long pivot, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_unsafe_fma(dest, src, pivot, angle);
        return rotateAround_unsafe_mulAdd(dest, src, pivot, angle);
    }

    /** {@code rotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_fma(long dest, long src, long pivot, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        return rotateAround_unsafe_fma_sa70eb536_1(dest, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1), java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_fma_sa70eb536_1(long dest, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self21, _t11, _self22)));
        return dest;
    }

    /** {@code rotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_mulAdd(long dest, long src, long pivot, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (_t0) + (_self01 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (_t0) + (_self11 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self20) * (_t0) + (_self21 * _t1)));
        return rotateAround_unsafe_mulAdd_s763640c7_1(dest, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_pivotx) * (_t9) + (_pivoty * _t1)), ((_pivoty) * (_t9) - (_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_mulAdd_s763640c7_1(long dest, float _t0, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self01) * (_t0) - (_self00 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self11) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self21) * (_t0) - (_self20 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self20) * (_t10) + (((_self21) * (_t11) + (_self22)))));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_fma(dest, src, angle, axisX, axisY, axisZ);
        return rotateAxis_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ);
    }

    /** {@code rotateAxis_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_fma(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisZ;
        float _t6 = axisX * axisY;
        float _t7 = axisY * axisZ;
        return rotateAxis_unsafe_fma_sc882068_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_t2, axisX * axisX, _t0), java.lang.Math.fma(_t2, axisY * axisY, _t0), java.lang.Math.fma(_t2, axisZ * axisZ, _t0), java.lang.Math.fma(axisZ, _t1, _t2 * _t6), java.lang.Math.fma(axisX, _t1, _t2 * _t7), java.lang.Math.fma(axisY, _t1, _t2 * _t3), java.lang.Math.fma(_t2, _t3, -(axisY * _t1)), java.lang.Math.fma(_t2, _t6, -(axisZ * _t1)), java.lang.Math.fma(_t2, _t7, -(axisX * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_fma_sc882068_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = 1.0f - _t0;
        float _t3 = axisX * axisZ;
        float _t6 = axisX * axisY;
        float _t7 = axisY * axisZ;
        return rotateAxis_unsafe_mulAdd_s66781633_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_t2) * (axisX * axisX) + (_t0)), ((_t2) * (axisY * axisY) + (_t0)), ((_t2) * (axisZ * axisZ) + (_t0)), ((axisZ) * (_t1) + (_t2 * _t6)), ((axisX) * (_t1) + (_t2 * _t7)), ((axisY) * (_t1) + (_t2 * _t3)), ((_t2) * (_t3) - (axisY * _t1)), ((_t2) * (_t6) - (axisZ * _t1)), ((_t2) * (_t7) - (axisX * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_mulAdd_s66781633_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26)))));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_fma(dest, src, axis, angle);
        return rotateAxis_unsafe_mulAdd(dest, src, axis, angle);
    }

    /** {@code rotateAxis_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_fma(long dest, long src, long axis, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisz;
        float _t6 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return rotateAxis_unsafe_fma_s3edd86fb_1(dest, _t1, _axisx, _axisy, _axisz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t3, _t6, _t7, java.lang.Math.fma(_t2, _axisx * _axisx, _t0), java.lang.Math.fma(_t2, _axisy * _axisy, _t0), java.lang.Math.fma(_t2, _axisz * _axisz, _t0), java.lang.Math.fma(_axisz, _t1, _t2 * _t6), java.lang.Math.fma(_axisx, _t1, _t2 * _t7), java.lang.Math.fma(_axisy, _t1, _t2 * _t3));
    }

    /** Piece 2 of {@code rotateAxis_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_fma_s3edd86fb_1(long dest, float _t1, float _axisx, float _axisy, float _axisz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t3, float _t6, float _t7, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = java.lang.Math.fma(_t2, _t3, -(_axisy * _t1));
        float _t25 = java.lang.Math.fma(_t2, _t6, -(_axisz * _t1));
        float _t26 = java.lang.Math.fma(_t2, _t7, -(_axisx * _t1));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, long axis, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = 1.0f - _t0;
        float _t3 = _axisx * _axisz;
        float _t6 = _axisx * _axisy;
        float _t7 = _axisy * _axisz;
        return rotateAxis_unsafe_mulAdd_s712da120_1(dest, _t1, _axisx, _axisz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t6, _t7, ((_t2) * (_axisx * _axisx) + (_t0)), ((_t2) * (_axisy * _axisy) + (_t0)), ((_t2) * (_axisz * _axisz) + (_t0)), ((_axisz) * (_t1) + (_t2 * _t6)), ((_axisx) * (_t1) + (_t2 * _t7)), ((_axisy) * (_t1) + (_t2 * _t3)), ((_t2) * (_t3) - (_axisy * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_mulAdd_s712da120_1(long dest, float _t1, float _axisx, float _axisz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t6, float _t7, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
        float _t25 = ((_t2) * (_t6) - (_axisz * _t1));
        float _t26 = ((_t2) * (_t7) - (_axisx * _t1));
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26)))));
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        return rotateTowards_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        return rotateTowards_unsafe_fma_s67b230da_1(dest, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t18, _t20, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)));
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s67b230da_1(long dest, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t20, float _t27, float _t28) {
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
        float _t46 = java.lang.Math.fma(_t39, _t12, -(_t38 * _t13));
        float _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        float _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        return rotateTowards_unsafe_fma_s67b230da_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s67b230da_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        return rotateTowards_unsafe_mulAdd_s95edda99_1(dest, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t12)), ((_t19) * (_t11) - (_t20 * _t13)), ((_t20) * (_t12) - (_t18 * _t11)));
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s95edda99_1(long dest, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        float _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        float _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        float _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        return rotateTowards_unsafe_mulAdd_s95edda99_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s95edda99_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, dir, up);
        return rotateTowards_unsafe_mulAdd(dest, src, dir, up);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        return rotateTowards_unsafe_fma_s1a0a8f90_1(dest, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s1a0a8f90_1(long dest, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13) {
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
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        return rotateTowards_unsafe_fma_s1a0a8f90_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)), java.lang.Math.fma(_t37, _t13, -(_t39 * _t11)), java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s1a0a8f90_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        return rotateTowards_unsafe_mulAdd_s601ef8e3_1(dest, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s601ef8e3_1(long dest, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13) {
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
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        return rotateTowards_unsafe_mulAdd_s601ef8e3_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, ((_t39) * (_t12) - (_t38 * _t13)), ((_t37) * (_t13) - (_t39 * _t11)), ((_t38) * (_t11) - (_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s601ef8e3_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return rotateTowards_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        return rotateTowards_unsafe_fma_s3857d9d6_1(dest, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t18, _t20, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)));
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s3857d9d6_1(long dest, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t20, float _t27, float _t28) {
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
        float _t46 = java.lang.Math.fma(_t39, _t12, -(_t38 * _t13));
        float _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        float _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        return rotateTowards_unsafe_fma_s3857d9d6_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s3857d9d6_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        return rotateTowards_unsafe_mulAdd_s488e2a1f_1(dest, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t12)), ((_t19) * (_t11) - (_t20 * _t13)), ((_t20) * (_t12) - (_t18 * _t11)));
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s488e2a1f_1(long dest, float upX, float upY, float upZ, float epsilon, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
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
        float _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        float _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        float _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        return rotateTowards_unsafe_mulAdd_s488e2a1f_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s488e2a1f_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, long dir, long up, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, dir, up, epsilon);
        return rotateTowards_unsafe_mulAdd(dest, src, dir, up, epsilon);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        return rotateTowards_unsafe_fma_sf24f64e4_1(dest, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_sf24f64e4_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13) {
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
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        return rotateTowards_unsafe_fma_sf24f64e4_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)), java.lang.Math.fma(_t37, _t13, -(_t39 * _t11)), java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_sf24f64e4_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
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
        return rotateTowards_unsafe_mulAdd_scea63709_1(dest, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_scea63709_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13) {
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
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        return rotateTowards_unsafe_mulAdd_scea63709_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, ((_t39) * (_t12) - (_t38 * _t13)), ((_t37) * (_t13) - (_t39 * _t11)), ((_t38) * (_t11) - (_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_scea63709_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateX_unsafe_fma(dest, src, angle);
        return rotateX_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code rotateX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateX_unsafe_fma(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self01, _t0, _self02 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self11, _t0, _self12 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self21, _t0, _self22 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t0, -(_self01 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t0, -(_self11 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t0, -(_self21 * _t1)));
        return dest;
    }

    /** {@code rotateX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateX_unsafe_mulAdd(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self01) * (_t0) + (_self02 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self11) * (_t0) + (_self12 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self21) * (_t0) + (_self22 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t0) - (_self01 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t0) - (_self11 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t0) - (_self21 * _t1)));
        return dest;
    }

    public static long rotateX180_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -_self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -_self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -_self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_self22);
        return dest;
    }

    public static long rotateX270_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_self02);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -_self12);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_self22);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self21);
        return dest;
    }

    public static long rotateX90_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -_self01);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -_self11);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_self21);
        return dest;
    }

    public static long rotateXYZ_unsafe(long dest, long src, float angleX, float angleY, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXYZ_unsafe_fma(dest, src, angleX, angleY, angleZ);
        return rotateXYZ_unsafe_mulAdd(dest, src, angleX, angleY, angleZ);
    }

    /** {@code rotateXYZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXYZ_unsafe_fma(long dest, long src, float angleX, float angleY, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.cosWithSin(angleX);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleY);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t2 * _t3;
        float _t7 = _t5 * _t4;
        float _t8 = _t0 * _t2;
        float _t18 = java.lang.Math.fma(_t8, _t4, _t1 * _t3);
        float _t20 = java.lang.Math.fma(_t0, _t1, -(_t6 * _t4));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t7, _self01 * _t18)));
        return rotateXYZ_unsafe_fma_s2d5fcdf3_1(dest, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t1 * _t5, _t3 * _t5, _t0 * _t5, _t18, java.lang.Math.fma(_t6, _t1, _t0 * _t4), _t20, java.lang.Math.fma(_t3, _t4, -(_t8 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_fma_s2d5fcdf3_1(long dest, float _t2, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t7, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t7, _self21 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self01, _t21, -(_self00 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self11, _t21, -(_self10 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self21, _t21, -(_self20 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t2, -(_self01 * _t13))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t2, -(_self11 * _t13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t2, -(_self21 * _t13))));
        return dest;
    }

    /** {@code rotateXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXYZ_unsafe_mulAdd(long dest, long src, float angleX, float angleY, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.cosWithSin(angleX);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleY);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t2 * _t3;
        float _t7 = _t5 * _t4;
        float _t8 = _t0 * _t2;
        float _t18 = ((_t8) * (_t4) + (_t1 * _t3));
        float _t20 = ((_t0) * (_t1) - (_t6 * _t4));
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t20) + (((_self00) * (_t7) + (_self01 * _t18)))));
        return rotateXYZ_unsafe_mulAdd_sfb9921e8_1(dest, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t1 * _t5, _t3 * _t5, _t0 * _t5, _t18, ((_t6) * (_t1) + (_t0 * _t4)), _t20, ((_t3) * (_t4) - (_t8 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_mulAdd_sfb9921e8_1(long dest, float _t2, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t20) + (((_self10) * (_t7) + (_self11 * _t18)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t20) + (((_self20) * (_t7) + (_self21 * _t18)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t19) + (((_self01) * (_t21) - (_self00 * _t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t19) + (((_self11) * (_t21) - (_self10 * _t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t19) + (((_self21) * (_t21) - (_self20 * _t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t12) + (((_self00) * (_t2) - (_self01 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t12) + (((_self10) * (_t2) - (_self11 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t12) + (((_self20) * (_t2) - (_self21 * _t13)))));
        return dest;
    }

    public static long rotateXZY_unsafe(long dest, long src, float angleX, float angleZ, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXZY_unsafe_fma(dest, src, angleX, angleZ, angleY);
        return rotateXZY_unsafe_mulAdd(dest, src, angleX, angleZ, angleY);
    }

    /** {@code rotateXZY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXZY_unsafe_fma(long dest, long src, float angleX, float angleZ, float angleY) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.cosWithSin(angleZ);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t0 * _t1;
        float _t8 = _t2 * _t5;
        float _t9 = _t1 * _t4;
        float _t18 = java.lang.Math.fma(_t9, _t2, _t0 * _t3);
        float _t20 = java.lang.Math.fma(_t6, _t2, -(_t3 * _t4));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t8, _self01 * _t18)));
        return rotateXZY_unsafe_fma_s801838e9_1(dest, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t8, _t0 * _t5, _t4 * _t5, _t3 * _t5, _t18, java.lang.Math.fma(_t6, _t3, _t4 * _t2), _t20, java.lang.Math.fma(_t9, _t3, -(_t0 * _t2)));
    }

    /** Piece 2 of {@code rotateXZY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_fma_s801838e9_1(long dest, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t8, float _t11, float _t12, float _t14, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t8, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t8, _self21 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self01, _t12, -(_self00 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self11, _t12, -(_self10 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self21, _t12, -(_self20 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t14, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t14, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t14, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXZY_unsafe_mulAdd(long dest, long src, float angleX, float angleZ, float angleY) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.cosWithSin(angleZ);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t0 * _t1;
        float _t8 = _t2 * _t5;
        float _t9 = _t1 * _t4;
        float _t18 = ((_t9) * (_t2) + (_t0 * _t3));
        float _t20 = ((_t6) * (_t2) - (_t3 * _t4));
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t20) + (((_self00) * (_t8) + (_self01 * _t18)))));
        return rotateXZY_unsafe_mulAdd_s3f58af8e_1(dest, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t8, _t0 * _t5, _t4 * _t5, _t3 * _t5, _t18, ((_t6) * (_t3) + (_t4 * _t2)), _t20, ((_t9) * (_t3) - (_t0 * _t2)));
    }

    /** Piece 2 of {@code rotateXZY_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_mulAdd_s3f58af8e_1(long dest, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t8, float _t11, float _t12, float _t14, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t20) + (((_self10) * (_t8) + (_self11 * _t18)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t20) + (((_self20) * (_t8) + (_self21 * _t18)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t11) + (((_self01) * (_t12) - (_self00 * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t11) + (((_self11) * (_t12) - (_self10 * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t11) + (((_self21) * (_t12) - (_self20 * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t19) + (((_self00) * (_t14) + (_self01 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t19) + (((_self10) * (_t14) + (_self11 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t19) + (((_self20) * (_t14) + (_self21 * _t21)))));
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateY_unsafe_fma(dest, src, angle);
        return rotateY_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code rotateY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateY_unsafe_fma(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, _t0, -(_self02 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, _t0, -(_self12 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self20, _t0, -(_self22 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self00, _t1, _self02 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self10, _t1, _self12 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self20, _t1, _self22 * _t0));
        return dest;
    }

    /** {@code rotateY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateY_unsafe_mulAdd(long dest, long src, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (_t0) - (_self02 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (_t0) - (_self12 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self20) * (_t0) - (_self22 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self00) * (_t1) + (_self02 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self10) * (_t1) + (_self12 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self20) * (_t1) + (_self22 * _t0)));
        return dest;
    }

    public static long rotateY180_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, -_self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -_self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -_self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_self22);
        return dest;
    }

    public static long rotateY270_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -_self00);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -_self10);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_self20);
        return dest;
    }

    public static long rotateY90_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, -_self02);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self12);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self22);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20);
        return dest;
    }

    public static long rotateYXZ_unsafe(long dest, long src, float angleY, float angleX, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_unsafe_fma(dest, src, angleY, angleX, angleZ);
        return rotateYXZ_unsafe_mulAdd(dest, src, angleY, angleX, angleZ);
    }

    /** {@code rotateYXZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYXZ_unsafe_fma(long dest, long src, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t2 * _t5;
        float _t18 = java.lang.Math.fma(_t8, _t2, _t1 * _t4);
        float _t20 = java.lang.Math.fma(_t6, _t2, -(_t3 * _t4));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t18, _self01 * _t10)));
        return rotateYXZ_unsafe_fma_s75cbabd_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t5 * _t4, _t5 * _t1, _t3 * _t5, _t18, java.lang.Math.fma(_t6, _t4, _t3 * _t2), _t20, java.lang.Math.fma(_t8, _t4, -(_t2 * _t1)));
    }

    /** Piece 2 of {@code rotateYXZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_fma_s75cbabd_1(long dest, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t13, float _t14, float _t15, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t18, _self11 * _t10)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t18, _self21 * _t10)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t21, _self01 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t21, _self11 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t21, _self21 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t14, java.lang.Math.fma(_self00, _t15, -(_self01 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t14, java.lang.Math.fma(_self10, _t15, -(_self11 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self20, _t15, -(_self21 * _t0))));
        return dest;
    }

    /** {@code rotateYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYXZ_unsafe_mulAdd(long dest, long src, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t2 * _t5;
        float _t18 = ((_t8) * (_t2) + (_t1 * _t4));
        float _t20 = ((_t6) * (_t2) - (_t3 * _t4));
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_t20) + (((_self00) * (_t18) + (_self01 * _t10)))));
        return rotateYXZ_unsafe_mulAdd_s4e66b8a_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t5 * _t4, _t5 * _t1, _t3 * _t5, _t18, ((_t6) * (_t4) + (_t3 * _t2)), _t20, ((_t8) * (_t4) - (_t2 * _t1)));
    }

    /** Piece 2 of {@code rotateYXZ_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_mulAdd_s4e66b8a_1(long dest, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t13, float _t14, float _t15, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_t20) + (((_self10) * (_t18) + (_self11 * _t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_t20) + (((_self20) * (_t18) + (_self21 * _t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t19) + (((_self00) * (_t21) + (_self01 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t19) + (((_self10) * (_t21) + (_self11 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t19) + (((_self20) * (_t21) + (_self21 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t14) + (((_self00) * (_t15) - (_self01 * _t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t14) + (((_self10) * (_t15) - (_self11 * _t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t14) + (((_self20) * (_t15) - (_self21 * _t0)))));
        return dest;
    }

    public static long rotateYZX_unsafe(long dest, long src, float angleY, float angleZ, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYZX_unsafe_fma(dest, src, angleY, angleZ, angleX);
        return rotateYZX_unsafe_mulAdd(dest, src, angleY, angleZ, angleX);
    }

    /** {@code rotateYZX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYZX_unsafe_fma(long dest, long src, float angleY, float angleZ, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t0 * _t1;
        float _t7 = _t2 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t3 * _t2;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, -_t6, java.lang.Math.fma(_self00, _t7, _self01 * _t3)));
        return rotateYZX_unsafe_fma_s8e7e47b5_1(dest, _t3, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, java.lang.Math.fma(_t8, _t4, _t5 * _t2), java.lang.Math.fma(_t10, _t5, _t0 * _t4), java.lang.Math.fma(_t5, _t0, -(_t10 * _t4)), java.lang.Math.fma(_t4, _t2, -(_t8 * _t5)));
    }

    /** Piece 2 of {@code rotateYZX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_fma_s8e7e47b5_1(long dest, float _t3, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t7, float _t11, float _t13, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, -_t6, java.lang.Math.fma(_self10, _t7, _self11 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, -_t6, java.lang.Math.fma(_self20, _t7, _self21 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t18, java.lang.Math.fma(_self00, _t20, _self01 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t18, java.lang.Math.fma(_self10, _t20, _self11 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t18, java.lang.Math.fma(_self20, _t20, _self21 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t21, java.lang.Math.fma(_self00, _t19, -(_self01 * _t13))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t21, java.lang.Math.fma(_self10, _t19, -(_self11 * _t13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self20, _t19, -(_self21 * _t13))));
        return dest;
    }

    /** {@code rotateYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYZX_unsafe_mulAdd(long dest, long src, float angleY, float angleZ, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t0 * _t1;
        float _t7 = _t2 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t3 * _t2;
        UnsafeOpsHolder.U.putFloat(dest, ((((_self00) * (_t7) + (_self01 * _t3))) - (_self02) * (_t6)));
        return rotateYZX_unsafe_mulAdd_s7a45713a_1(dest, _t3, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, ((_t8) * (_t4) + (_t5 * _t2)), ((_t10) * (_t5) + (_t0 * _t4)), ((_t5) * (_t0) - (_t10 * _t4)), ((_t4) * (_t2) - (_t8 * _t5)));
    }

    /** Piece 2 of {@code rotateYZX_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_mulAdd_s7a45713a_1(long dest, float _t3, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t7, float _t11, float _t13, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((((_self10) * (_t7) + (_self11 * _t3))) - (_self12) * (_t6)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((((_self20) * (_t7) + (_self21 * _t3))) - (_self22) * (_t6)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t18) + (((_self00) * (_t20) + (_self01 * _t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t18) + (((_self10) * (_t20) + (_self11 * _t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t18) + (((_self20) * (_t20) + (_self21 * _t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t21) + (((_self00) * (_t19) - (_self01 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t21) + (((_self10) * (_t19) - (_self11 * _t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t21) + (((_self20) * (_t19) - (_self21 * _t13)))));
        return dest;
    }

    public static long rotateZ180_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, -_self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -_self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long rotateZ270_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, -_self01);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self11);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self21);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long rotateZ90_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_self00);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -_self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -_self20);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long rotateZXY_unsafe(long dest, long src, float angleZ, float angleX, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZXY_unsafe_fma(dest, src, angleZ, angleX, angleY);
        return rotateZXY_unsafe_mulAdd(dest, src, angleZ, angleX, angleY);
    }

    /** {@code rotateZXY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZXY_unsafe_fma(long dest, long src, float angleZ, float angleX, float angleY) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleX);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.cosWithSin(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.sinWithCos(angleZ);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t0 * _t1;
        float _t7 = _t4 * _t5;
        float _t8 = _t4 * _t3;
        float _t18 = java.lang.Math.fma(_t8, _t0, _t5 * _t2);
        float _t20 = java.lang.Math.fma(_t2, _t3, -(_t7 * _t0));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, -_t6, java.lang.Math.fma(_self00, _t20, _self01 * _t18)));
        return rotateZXY_unsafe_fma_s7d047c05_1(dest, _t4, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t1 * _t3, _t5 * _t1, _t1 * _t2, _t18, java.lang.Math.fma(_t7, _t2, _t0 * _t3), _t20, java.lang.Math.fma(_t0, _t5, -(_t8 * _t2)));
    }

    /** Piece 2 of {@code rotateZXY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_fma_s7d047c05_1(long dest, float _t4, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t10, float _t11, float _t12, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, -_t6, java.lang.Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, -_t6, java.lang.Math.fma(_self20, _t20, _self21 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t4, java.lang.Math.fma(_self01, _t10, -(_self00 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t4, java.lang.Math.fma(_self11, _t10, -(_self10 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t4, java.lang.Math.fma(_self21, _t10, -(_self20 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZXY_unsafe_mulAdd(long dest, long src, float angleZ, float angleX, float angleY) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleX);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.cosWithSin(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.sinWithCos(angleZ);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t0 * _t1;
        float _t7 = _t4 * _t5;
        float _t8 = _t4 * _t3;
        float _t18 = ((_t8) * (_t0) + (_t5 * _t2));
        float _t20 = ((_t2) * (_t3) - (_t7 * _t0));
        UnsafeOpsHolder.U.putFloat(dest, ((((_self00) * (_t20) + (_self01 * _t18))) - (_self02) * (_t6)));
        return rotateZXY_unsafe_mulAdd_s5d615372_1(dest, _t4, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t1 * _t3, _t5 * _t1, _t1 * _t2, _t18, ((_t7) * (_t2) + (_t0 * _t3)), _t20, ((_t0) * (_t5) - (_t8 * _t2)));
    }

    /** Piece 2 of {@code rotateZXY_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_mulAdd_s5d615372_1(long dest, float _t4, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t10, float _t11, float _t12, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((((_self10) * (_t20) + (_self11 * _t18))) - (_self12) * (_t6)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((((_self20) * (_t20) + (_self21 * _t18))) - (_self22) * (_t6)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t4) + (((_self01) * (_t10) - (_self00 * _t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t4) + (((_self11) * (_t10) - (_self10 * _t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t4) + (((_self21) * (_t10) - (_self20 * _t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t12) + (((_self00) * (_t19) + (_self01 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t12) + (((_self10) * (_t19) + (_self11 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t12) + (((_self20) * (_t19) + (_self21 * _t21)))));
        return dest;
    }

    public static long rotateZYX_unsafe(long dest, long src, float angleZ, float angleY, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZYX_unsafe_fma(dest, src, angleZ, angleY, angleX);
        return rotateZYX_unsafe_mulAdd(dest, src, angleZ, angleY, angleX);
    }

    /** {@code rotateZYX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZYX_unsafe_fma(long dest, long src, float angleZ, float angleY, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.cosWithSin(angleZ);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t1 * _t2;
        float _t7 = _t3 * _t1;
        float _t9 = _t0 * _t2;
        float _t11 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, -_t0, java.lang.Math.fma(_self00, _t6, _self01 * _t7)));
        return rotateZYX_unsafe_fma_s663d2e7_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, java.lang.Math.fma(_t11, _t4, _t5 * _t2), java.lang.Math.fma(_t9, _t5, _t4 * _t3), java.lang.Math.fma(_t9, _t4, -(_t3 * _t5)), java.lang.Math.fma(_t11, _t5, -(_t4 * _t2)));
    }

    /** Piece 2 of {@code rotateZYX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_fma_s663d2e7_1(long dest, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t7, float _t8, float _t13, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, -_t0, java.lang.Math.fma(_self10, _t6, _self11 * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, -_t0, java.lang.Math.fma(_self20, _t6, _self21 * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self02, _t8, java.lang.Math.fma(_self00, _t20, _self01 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(_self12, _t8, java.lang.Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(_self22, _t8, java.lang.Math.fma(_self20, _t20, _self21 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZYX_unsafe_mulAdd(long dest, long src, float angleZ, float angleY, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.cosWithSin(angleZ);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = _t1 * _t2;
        float _t7 = _t3 * _t1;
        float _t9 = _t0 * _t2;
        float _t11 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, ((((_self00) * (_t6) + (_self01 * _t7))) - (_self02) * (_t0)));
        return rotateZYX_unsafe_mulAdd_sa0a2164c_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, ((_t11) * (_t4) + (_t5 * _t2)), ((_t9) * (_t5) + (_t4 * _t3)), ((_t9) * (_t4) - (_t3 * _t5)), ((_t11) * (_t5) - (_t4 * _t2)));
    }

    /** Piece 2 of {@code rotateZYX_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_mulAdd_sa0a2164c_1(long dest, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t6, float _t7, float _t8, float _t13, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((((_self10) * (_t6) + (_self11 * _t7))) - (_self12) * (_t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((((_self20) * (_t6) + (_self21 * _t7))) - (_self22) * (_t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self02) * (_t8) + (((_self00) * (_t20) + (_self01 * _t18)))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((_self12) * (_t8) + (((_self10) * (_t20) + (_self11 * _t18)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((_self22) * (_t8) + (((_self20) * (_t20) + (_self21 * _t18)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self02) * (_t13) + (((_self00) * (_t19) + (_self01 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self12) * (_t13) + (((_self10) * (_t19) + (_self11 * _t21)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self22) * (_t13) + (((_self20) * (_t19) + (_self21 * _t21)))));
        return dest;
    }

    public static long scale_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, s * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, float s, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_fma(dest, src, s, pivotX, pivotY);
        return scaleAround_unsafe_mulAdd(dest, src, s, pivotX, pivotY);
    }

    /** {@code scaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_fma(long dest, long src, float s, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, s * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, float s, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, s * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self20) * (_t1) + (((_self21) * (_t2) + (_self22)))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long pivot, float s) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_fma(dest, src, pivot, s);
        return scaleAround_unsafe_mulAdd(dest, src, pivot, s);
    }

    /** {@code scaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_fma(long dest, long src, long pivot, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0 = 1.0f - s;
        float _t1 = UnsafeOpsHolder.U.getFloat(pivot) * _t0;
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * _t0;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, s * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        return scaleAround_unsafe_fma_scf0deb8f_1(dest, _self10, _self20, _self11, _self21, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code scaleAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_fma_scf0deb8f_1(long dest, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _t1, float _t2) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, long pivot, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0 = 1.0f - s;
        float _t1 = UnsafeOpsHolder.U.getFloat(pivot) * _t0;
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * _t0;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, s * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        return scaleAround_unsafe_mulAdd_s7e3687a4_1(dest, _self10, _self20, _self11, _self21, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code scaleAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_mulAdd_s7e3687a4_1(long dest, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _t1, float _t2) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self20) * (_t1) + (((_self21) * (_t2) + (_self22)))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_fma(dest, src, sX, sY, pivotX, pivotY);
        return scaleAround_unsafe_mulAdd(dest, src, sX, sY, pivotX, pivotY);
    }

    /** {@code scaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_fma(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        UnsafeOpsHolder.U.putFloat(dest, sX * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, sX * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, sX * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, sY * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, sY * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, sY * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self20, _t2, java.lang.Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        UnsafeOpsHolder.U.putFloat(dest, sX * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, sX * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, sX * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, sY * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, sY * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, sY * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self20) * (_t2) + (((_self21) * (_t3) + (_self22)))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long s, long pivot) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_fma(dest, src, s, pivot);
        return scaleAround_unsafe_mulAdd(dest, src, s, pivot);
    }

    /** {@code scaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_fma(long dest, long src, long s, long pivot) {
        float _sx = UnsafeOpsHolder.U.getFloat(s);
        float _sy = UnsafeOpsHolder.U.getFloat(s + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        return scaleAround_unsafe_fma_s5aa5823_1(dest, pivot, _sx, _sy, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code scaleAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_fma_s5aa5823_1(long dest, long pivot, float _sx, float _sy, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot) * (1.0f - _sx);
        float _t3 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * (1.0f - _sy);
        UnsafeOpsHolder.U.putFloat(dest, _sx * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _sx * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _sx * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _sy * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _sy * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _sy * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self20, _t2, java.lang.Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, long s, long pivot) {
        float _sx = UnsafeOpsHolder.U.getFloat(s);
        float _sy = UnsafeOpsHolder.U.getFloat(s + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        return scaleAround_unsafe_mulAdd_s22bc750e_1(dest, pivot, _sx, _sy, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code scaleAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_mulAdd_s22bc750e_1(long dest, long pivot, float _sx, float _sy, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22) {
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot) * (1.0f - _sx);
        float _t3 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * (1.0f - _sy);
        UnsafeOpsHolder.U.putFloat(dest, _sx * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _sx * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _sx * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _sy * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _sy * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _sy * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self20) * (_t2) + (((_self21) * (_t3) + (_self22)))));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, float vX, float vY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_unsafe_mulAdd(dest, src, vX, vY);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self20, vX, java.lang.Math.fma(_self21, vY, _self22)));
        return dest;
    }

    /** {@code translate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long translate_unsafe_mulAdd(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self20) * (vX) + (((_self21) * (vY) + (_self22)))));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_unsafe_mulAdd(dest, src, v);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(_self20, _vx, java.lang.Math.fma(_self21, _vy, _self22)));
        return dest;
    }

    /** {@code translate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long translate_unsafe_mulAdd(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((_self20) * (_vx) + (((_self21) * (_vy) + (_self22)))));
        return dest;
    }

    public static long view_unsafe(long dest, long src, float left, float right, float bottom, float top) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        UnsafeOpsHolder.U.putFloat(dest, _sp0 * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _sp1 * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _sp1 * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        return view_unsafe_s689a6d98_1(dest, _self10, _self20, _self11, _self21, _self12, _self22, _sp2, _sp3);
    }

    /** Piece 2 of {@code view_unsafe}, split to fit the inline budget; reached only through it. */
    private static long view_unsafe_s689a6d98_1(long dest, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _sp2, float _sp3) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22 + (-(_self20 * _sp2) - _self21 * _sp3));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_unsafe_mulAdd(dest, src, vX, vY, vZ);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, vZ, java.lang.Math.fma(_self00, vX, _self01 * vY)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, vZ, java.lang.Math.fma(_self10, vX, _self11 * vY)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, vZ, java.lang.Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    /** {@code mulVec3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulVec3_unsafe_mulAdd(long dest, long src, float vX, float vY, float vZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (vZ) + (((_self00) * (vX) + (_self01 * vY)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (vZ) + (((_self10) * (vX) + (_self11 * vY)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (vZ) + (((_self20) * (vX) + (_self21 * vY)))));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_unsafe_mulAdd(dest, src, v);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self02, _vz, java.lang.Math.fma(_self00, _vx, _self01 * _vy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self12, _vz, java.lang.Math.fma(_self10, _vx, _self11 * _vy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self22, _vz, java.lang.Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    /** {@code mulVec3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulVec3_unsafe_mulAdd(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, ((_self02) * (_vz) + (((_self00) * (_vx) + (_self01 * _vy)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self12) * (_vz) + (((_self10) * (_vx) + (_self11 * _vy)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self22) * (_vz) + (((_self20) * (_vx) + (_self21 * _vy)))));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, float vX, float vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, vX, _self01 * vY));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
            UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (vX) + (_self01 * vY)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static long transformDirection_unsafe(long dest, long src, long v) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = UnsafeOpsHolder.U.getFloat(v);
            float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, _vx, _self01 * _vy));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            float _vx = UnsafeOpsHolder.U.getFloat(v);
            float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
            UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (_vx) + (_self01 * _vy)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (_vx) + (_self11 * _vy)));
            return dest;
        }
    }

    public static long transformPosition_unsafe(long dest, long src, float vX, float vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
            float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
            float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
            return dest;
        } else {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
            float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
            float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
            UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
            return dest;
        }
    }

    public static long transformPosition_unsafe(long dest, long src, long v) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = UnsafeOpsHolder.U.getFloat(v);
            float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
            float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
            float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
            return dest;
        } else {
            float _vx = UnsafeOpsHolder.U.getFloat(v);
            float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
            float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
            float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
            UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
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
