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
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float3x3Ops} and its sibling kernel units. Not public API.
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
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 < Math.fma(_self02, _self02, _t1) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 < Math.fma(_self01, _self01, _t1) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 < Math.fma(_self12, _self12, _t1) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, _self00));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, _self11));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.fma(_self11, _self11, _self12 * _self12);
        if (_t1 < Math.fma(_self10, _self10, _t1) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_self02, _self22));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(-_self12, _self11));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 < Math.fma(_self21, _self21, _t1) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, _self00));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, _self11));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.fma(_self21, _self21, _self22 * _self22);
        if (_t1 < Math.fma(_self20, _self20, _t1) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(-_self01, _self11));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_self21, _self22));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_self10, _self00));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(-_self20, (float) java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getNormalizedRotation_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        float _t7 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        float _t8 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
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
        return getNormalizedRotation_unsafe_sa46d4105_1(dest, _self00, _self10, _self20, _self02, _self12, _self22, _t7, _t8, (1.0f / (float) java.lang.Math.sqrt(_t7)), (1.0f / (float) java.lang.Math.sqrt(_t8)), _t21, _t23, _t27);
    }

    /** Piece 2 of {@code getNormalizedRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_sa46d4105_1(long dest, float _self00, float _self10, float _self20, float _self02, float _self12, float _self22, float _t7, float _t8, float _t10, float _t11, float _t21, float _t23, float _t27) {
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
        if (Math.fma(Math.fma(_t21, _t22, -(_t23 * _t24)), _t25, Math.fma(Math.fma(_t23, _t26, -(_t27 * _t22)), _t28, Math.fma(_t27, _t24, -(_t21 * _t26)) * _t29)) < 0.0f) {
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
        return getNormalizedRotation_unsafe_sa46d4105_2(dest, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, 1.0f + (_t49 - (_t23 + _t26)), _t64, _t65, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t62)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)));
    }

    /** Piece 3 of {@code getNormalizedRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_sa46d4105_2(long dest, float _t23, float _t26, float _t36, float _t39, float _t49, float _t53, float _t55, float _t56, float _t57, float _t58, float _t62, float _t63, float _t64, float _t65, float _sp0, float _sp1, float _sp2) {
        return getNormalizedRotation_unsafe_sa46d4105_3(dest, _t23, _t26, _t36, _t39, _t49, _t53, _t55, _t56, _t57, _t58, _t62, _t63, _t64, _t65, _sp0, _sp1, _sp2, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)));
    }

    /** Piece 4 of {@code getNormalizedRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_sa46d4105_3(long dest, float _t23, float _t26, float _t36, float _t39, float _t49, float _t53, float _t55, float _t56, float _t57, float _t58, float _t62, float _t63, float _t64, float _t65, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t58 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t36);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t56);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t57);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > java.lang.Math.max(_t23, _t26)) {
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
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, (float) java.lang.Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) java.lang.Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) java.lang.Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
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
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
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
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self11, _self22, -(_self12 * _self21)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _self21, -(_self01 * _self22)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self01, _self12, -(_self02 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self12, _self20, -(_self10 * _self22)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, _self22, -(_self02 * _self20)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self02, _self10, -(_self00 * _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self10, _self21, -(_self11 * _self20)));
        return cofactor_unsafe_s9925c80d_1(dest, _self00, _self10, _self20, _self01, _self11, _self21);
    }

    /** Piece 2 of {@code cofactor_unsafe}, split to fit the inline budget; reached only through it. */
    private static long cofactor_unsafe_s9925c80d_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self01, _self20, -(_self00 * _self21)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self00, _self11, -(_self01 * _self10)));
        return dest;
    }

    public static float determinant_unsafe(long src) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        return Math.fma(UnsafeOpsHolder.U.getFloat(src + 24L), Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(UnsafeOpsHolder.U.getFloat(src), Math.fma(_self11, _self22, -(_self12 * _self21)), -(UnsafeOpsHolder.U.getFloat(src + 12L) * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static float frobeniusNorm_unsafe(long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        return (float) java.lang.Math.sqrt(Math.fma(_self00, _self00, _self01 * _self01) + Math.fma(_self02, _self02, _self10 * _self10) + (Math.fma(_self11, _self11, _self12 * _self12) + Math.fma(_self20, _self20, Math.fma(_self21, _self21, _self22 * _self22))));
    }

    public static long invert_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(java.lang.Math.abs(_t13) > 1.1754944E-38f && java.lang.Math.abs(_t13) < 8.507059E37f)) return Float3x3OpsKernelsAddress.invert_degenerate(dest, src);
        float _t13_inv = 1.0f / _t13;
        UnsafeOpsHolder.U.putFloat(dest, _t6 * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t7 * _t13_inv);
        return invert_unsafe_s1ee1b72a_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t13_inv);
    }

    /** Piece 2 of {@code invert_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invert_unsafe_s1ee1b72a_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t13_inv) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv);
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
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
        float _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        float _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        return invert_degenerate_unsafe_sbf3b0613_1(dest, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t27, _t28, 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20))));
    }

    /** Piece 2 of {@code invert_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_sbf3b0613_1(long dest, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t27, float _t28, float _t33_inv) {
        float _sp2 = _t1 * _t33_inv;
        float _sp1 = _t0 * _t33_inv;
        float _sp0 = _t2 * _t33_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t27 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t28 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
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
        return invertProduct_unsafe_sec86c98d_1(dest, src, other, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)));
    }

    /** Piece 2 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_sec86c98d_1(long dest, long src, long other, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t18) {
        float _t19 = Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21));
        float _t20 = Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21));
        float _t21 = Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11));
        float _t22 = Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01));
        float _t23 = Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11));
        float _t24 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        float _t25 = Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01));
        float _t26 = Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01));
        float _t33 = Math.fma(_t18, _t19, -(_t20 * _t21));
        float _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        float _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(java.lang.Math.abs(_t40) > 1.1754944E-38f && java.lang.Math.abs(_t40) < 8.507059E37f)) return Float3x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        float _t40_inv = 1.0f / _t40;
        UnsafeOpsHolder.U.putFloat(dest, _t33 * _t40_inv);
        return invertProduct_unsafe_sec86c98d_2(dest, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t34, _t40_inv);
    }

    /** Piece 3 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_sec86c98d_2(long dest, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t34, float _t40_inv) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t34 * _t40_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv);
        return dest;
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
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
        return invertProduct_degenerate_unsafe_s3fc4d1ee_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_s3fc4d1ee_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22, float _t18) {
        float _t19 = Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11));
        float _t20 = Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11));
        float _t21 = Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21));
        float _t22 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        float _t23 = Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21));
        float _t24 = Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01));
        float _t25 = Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01));
        float _t26 = Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01));
        float _t27 = unitScale(_t19, _t18, _t20);
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t24, _t25, _t26);
        float _t39 = _t18 * _t27;
        float _t40 = _t21 * _t28;
        float _t41 = _t23 * _t28;
        float _t42 = _t20 * _t27;
        float _t43 = _t19 * _t27;
        float _t44 = _t22 * _t28;
        return invertProduct_degenerate_unsafe_s3fc4d1ee_2(dest, _t27, _t28, _t29, _t39, _t40, _t41, _t42, _t43, _t44, _t26 * _t29, _t24 * _t29, _t25 * _t29, Math.fma(_t39, _t40, -(_t41 * _t42)), Math.fma(_t43, _t41, -(_t44 * _t39)));
    }

    /** Piece 3 of {@code invertProduct_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_s3fc4d1ee_2(long dest, float _t27, float _t28, float _t29, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47, float _t54, float _t55) {
        float _t60_inv = 1.0f / Math.fma(_t55, _t45, Math.fma(_t54, _t46, -(Math.fma(_t43, _t40, -(_t44 * _t42)) * _t47)));
        float _sp2 = _t28 * _t60_inv;
        float _sp1 = _t27 * _t60_inv;
        float _sp0 = _t29 * _t60_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t54 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t55 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t47, _t42, -(_t39 * _t45)) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_t43, _t45, -(_t46 * _t42)) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t46, _t39, -(_t43 * _t47)) * _sp2);
        return dest;
    }

    public static long normal_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(java.lang.Math.abs(_t13) > 1.1754944E-38f && java.lang.Math.abs(_t13) < 8.507059E37f)) return Float3x3OpsKernelsAddress.normal_degenerate(dest, src);
        float _t13_inv = 1.0f / _t13;
        UnsafeOpsHolder.U.putFloat(dest, _t6 * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv);
        return normal_unsafe_s7d3762cb_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t13_inv);
    }

    /** Piece 2 of {@code normal_unsafe}, split to fit the inline budget; reached only through it. */
    private static long normal_unsafe_s7d3762cb_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t13_inv) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t7 * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv);
        return dest;
    }

    public static long normal_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x3OpsKernelsAddress.normal_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long normal_degenerate_unsafe(long dest, long src) {
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
        float _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        float _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        return normal_degenerate_unsafe_s315aaff8_1(dest, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t27, _t28, 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20))));
    }

    /** Piece 2 of {@code normal_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long normal_degenerate_unsafe_s315aaff8_1(long dest, float _t0, float _t1, float _t2, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t27, float _t28, float _t33_inv) {
        float _sp2 = _t1 * _t33_inv;
        float _sp1 = _t0 * _t33_inv;
        float _sp0 = _t2 * _t33_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t27 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t28 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2);
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
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _tx = UnsafeOpsHolder.U.getFloat(t);
        float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
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
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, Math.fma(rRY, rRY, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(rRX, rRY, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(rRX, rRZ, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * Math.fma(rRX, rRY, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(-2.0f, Math.fma(rRX, rRX, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * Math.fma(rRX, rRW, rRY * rRZ));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * Math.fma(rRX, rRZ, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * Math.fma(rRY, rRZ, -(rRX * rRW)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(-2.0f, Math.fma(rRX, rRX, rRY * rRY), 1.0f));
        return dest;
    }

    public static long makeFromTransform_unsafe(long dest, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(tRX, tRY, _t4) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(tRX, tRZ, -_t5) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(tRX, tRY, -_t4) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(tRX, tRZ, _t5) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
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
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
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
        float _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _t18 = _self10 * _t17;
        float _t19 = _self22 * _t16;
        float _t20 = _self12 * _t16;
        float _t21 = _self20 * _t17;
        float _t23 = _self21 * _t15;
        float _t24 = _self11 * _t15;
        float _t26 = _self00 * _t17;
        float _t31 = Math.fma(_self12, _t16, _t23);
        float _t35 = Math.fma(_self21, _t15, -_t20);
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), _self01 * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), _self02 * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
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
        float _t54 = Math.fma(_self01, _t15, _t48);
        float _t55 = Math.fma(_self02, _t16, _t49);
        float _t56 = Math.fma(_self02, _t16, -_t49);
        float _t57 = Math.fma(-_self01, _t15, _t48);
        float _t63 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t51));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63));
        float _t65 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t52));
        float _t66 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t52));
        float _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        if (Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t47)) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 12L, _sp0 * _t35);
            UnsafeOpsHolder.U.putFloat(dest + 16L, _sp0 * _t56);
            UnsafeOpsHolder.U.putFloat(dest + 20L, _sp0 * _t57);
            UnsafeOpsHolder.U.putFloat(dest + 24L, 0.5f * (float) java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
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
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
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
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
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
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t165 = Math.fma(_t36, _t105, -(_t37 * _t114));
                    _t167 = Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = Math.fma(_t33, _t36, -(_t35 * _t37));
                    _t167 = Math.fma(_t34, _t37, -(_t33 * _t38));
                    _t170 = Math.fma(_t35, _t38, -(_t34 * _t36));
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
                    _t169 = Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = Math.fma(_t41, _t107, -(_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = Math.fma(_t35, _t40, -(_t33 * _t41));
                    _t171 = Math.fma(_t34, _t41, -(_t35 * _t39));
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
                    _t169 = Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = Math.fma(_t41, _t37, -(_t40 * _t36));
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
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0f) {
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
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
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
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        float _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        float _t14 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        return toTransform_unsafe_s96acba85_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, (1.0f / (float) java.lang.Math.sqrt(_t12)), (1.0f / (float) java.lang.Math.sqrt(_t13)), (float) java.lang.Math.sqrt(_t14));
    }

    /** Piece 2 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_s96acba85_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18) {
        float _t17 = 1.0f / _t18;
        float _t19 = _self10 * _t17;
        float _t20 = _self22 * _t16;
        float _t21 = _self12 * _t16;
        float _t22 = _self20 * _t17;
        float _t24 = _self21 * _t15;
        float _t25 = _self11 * _t15;
        float _t27 = _self00 * _t17;
        float _t47 = Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), _self01 * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), _self02 * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
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
        float _t64 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52));
        return toTransform_unsafe_s96acba85_2(dest, _self11, _self22, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, Math.fma(_self12, _t16, _t24), Math.fma(_self21, _t15, -_t21), java.lang.Math.max(_t25, _t20), _t47, _t48, _t52, 1.0f - _t48, Math.fma(_self01, _t15, _t49), Math.fma(_self02, _t16, _t50), Math.fma(_self02, _t16, -_t50), Math.fma(-_self01, _t15, _t49), Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48)), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)));
    }

    /** Piece 3 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_s96acba85_2(long dest, float _self11, float _self22, float _t0, float _t1, float _t12, float _t13, float _t15, float _t16, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t52, float _t53, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0) {
        float _t66 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53));
        float _t67 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53));
        float _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67));
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        return toTransform_unsafe_s96acba85_3(dest, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t55, _t56, _t57, _t58, _t63, _t64, _sp0, _t66, _t67, _sp1, _sp2, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68)));
    }

    /** Piece 4 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_s96acba85_3(long dest, float _t12, float _t13, float _t18, float _t20, float _t25, float _t32, float _t36, float _t37, float _t47, float _t48, float _t55, float _t56, float _t57, float _t58, float _t63, float _t64, float _sp0, float _t66, float _t67, float _sp1, float _sp2, float _sp3) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) java.lang.Math.sqrt(_t66) : _sp2 * _t32);
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
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
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
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
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
                    _t169 = Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0f) {
                    _t168 = Math.fma(_t38, _t108, -(_t39 * _t117));
                    _t170 = Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = Math.fma(_t35, _t38, -(_t37 * _t39));
                    _t170 = Math.fma(_t36, _t39, -(_t35 * _t40));
                    _t173 = Math.fma(_t37, _t40, -(_t36 * _t38));
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
                    _t172 = Math.fma(_t41, _t118, -(_t43 * _t107));
                    _t175 = Math.fma(_t42, _t107, -(_t41 * _t110));
                    _t176 = Math.fma(_t43, _t110, -(_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = Math.fma(_t37, _t42, -(_t35 * _t43));
                    _t174 = Math.fma(_t36, _t43, -(_t37 * _t41));
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
                    _t172 = Math.fma(_t41, _t38, -(_t43 * _t40));
                    _t175 = Math.fma(_t42, _t40, -(_t41 * _t39));
                    _t176 = Math.fma(_t43, _t39, -(_t42 * _t38));
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
        float _t186 = java.lang.Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
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
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
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
        float _t19 = -Math.fma(_self21, _t7, Math.fma(_self01, _t8, _self11 * _t9));
        float _t21 = Math.fma(_t19, _t7, _self21);
        float _t22 = Math.fma(_t19, _t8, _self01);
        float _t23 = Math.fma(_t19, _t9, _self11);
        return decomposeRotation_unsafe_s46789a7d_1(dest, _self02, _self12, _self22, _t7, _t8, _t9, -Math.fma(_self22, _t7, Math.fma(_self02, _t8, _self12 * _t9)), _t21, _t22, _t23, Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)));
    }

    /** Piece 2 of {@code decomposeRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_s46789a7d_1(long dest, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t20, float _t21, float _t22, float _t23, float _t29) {
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t34, _t35, _t36;
        if (_t29 != 0.0f) {
            _t34 = _t22 * _t30;
            _t35 = _t21 * _t30;
            _t36 = _t23 * _t30;
        } else {
            _t34 = 0.0f;
            _t35 = 0.0f;
            _t36 = 0.0f;
        }
        float _t40 = -Math.fma(Math.fma(_t20, _t7, _self22), _t35, Math.fma(Math.fma(_t20, _t8, _self02), _t34, Math.fma(_t20, _t9, _self12) * _t36));
        float _t44 = Math.fma(_t20, _t7, Math.fma(_t40, _t35, _self22));
        float _t45 = Math.fma(_t20, _t8, Math.fma(_t40, _t34, _self02));
        float _t46 = Math.fma(_t20, _t9, Math.fma(_t40, _t36, _self12));
        float _t49 = Math.fma(_t44, _t44, Math.fma(_t45, _t45, _t46 * _t46));
        float _t50 = (1.0f / (float) java.lang.Math.sqrt(_t49));
        float _t54, _t55, _t56;
        if (_t49 != 0.0f) {
            _t54 = _t46 * _t50;
            _t55 = _t45 * _t50;
            _t56 = _t44 * _t50;
        } else {
            _t54 = 0.0f;
            _t55 = 0.0f;
            _t56 = 0.0f;
        }
        return decomposeRotation_unsafe_s46789a7d_2(dest, _t7, _t8, _t9, _t34, _t35, _t36, _t54, _t55, _t56, _t35 - _t54, _t35 + _t54);
    }

    /** Piece 3 of {@code decomposeRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_s46789a7d_2(long dest, float _t7, float _t8, float _t9, float _t34, float _t35, float _t36, float _t54, float _t55, float _t56, float _t60, float _t63) {
        float _t73, _t74, _t75;
        if (Math.fma(Math.fma(_t34, _t54, -(_t36 * _t55)), _t7, Math.fma(Math.fma(_t36, _t56, -(_t35 * _t54)), _t8, Math.fma(_t35, _t55, -(_t34 * _t56)) * _t9)) < 0.0f) {
            _t73 = -_t8;
            _t74 = -_t9;
            _t75 = -_t7;
        } else {
            _t73 = _t8;
            _t74 = _t9;
            _t75 = _t7;
        }
        float _t76 = _t73 + _t36;
        float _t82 = _t76 + _t56;
        float _t86 = 1.0f + _t82;
        float _t87 = 1.0f + (_t73 - (_t36 + _t56));
        float _t88 = 1.0f + (_t36 - (_t73 + _t56));
        float _t89 = 1.0f + (_t56 - _t76);
        return decomposeRotation_unsafe_s46789a7d_3(dest, _t36, _t56, _t60, _t63, _t73, _t74 + _t34, _t74 - _t34, _t75 + _t55, _t55 - _t75, _t82, _t86, _t87, _t88, _t89, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t86)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t88)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t89)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t87)));
    }

    /** Piece 4 of {@code decomposeRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_s46789a7d_3(long dest, float _t36, float _t56, float _t60, float _t63, float _t73, float _t77, float _t78, float _t80, float _t81, float _t82, float _t86, float _t87, float _t88, float _t89, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t82 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t60);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t81);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t78);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t86));
        } else {
            if (_t73 > java.lang.Math.max(_t36, _t56)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t87));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t77);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t80);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t60);
            } else {
                if (_t36 > _t56) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t77);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t88));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t63);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t81);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t80);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t63);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t89));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t78);
                }
            }
        }
        return dest;
    }

    public static long decomposeScale_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
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
        float _t17 = -Math.fma(_self21, _t8, Math.fma(_self01, _t9, _self11 * _t10));
        float _t19 = Math.fma(_t17, _t8, _self21);
        float _t20 = Math.fma(_t17, _t9, _self01);
        float _t21 = Math.fma(_t17, _t10, _self11);
        return decomposeScale_unsafe_s90429cf5_1(dest, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10)), _t19, _t20, _t21, Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21)));
    }

    /** Piece 2 of {@code decomposeScale_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeScale_unsafe_s90429cf5_1(long dest, float _self02, float _self12, float _self22, float _t4, float _t8, float _t9, float _t10, float _t18, float _t19, float _t20, float _t21, float _t27) {
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t27));
        float _t32, _t33, _t34;
        if (_t27 != 0.0f) {
            _t32 = _t20 * _t28;
            _t33 = _t19 * _t28;
            _t34 = _t21 * _t28;
        } else {
            _t32 = 0.0f;
            _t33 = 0.0f;
            _t34 = 0.0f;
        }
        float _t38 = -Math.fma(Math.fma(_t18, _t8, _self22), _t33, Math.fma(Math.fma(_t18, _t9, _self02), _t32, Math.fma(_t18, _t10, _self12) * _t34));
        float _t42 = Math.fma(_t18, _t8, Math.fma(_t38, _t33, _self22));
        float _t43 = Math.fma(_t18, _t9, Math.fma(_t38, _t32, _self02));
        float _t44 = Math.fma(_t18, _t10, Math.fma(_t38, _t34, _self12));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        float _t48 = (1.0f / (float) java.lang.Math.sqrt(_t47));
        float _t52, _t53, _t54;
        if (_t47 != 0.0f) {
            _t52 = _t44 * _t48;
            _t53 = _t43 * _t48;
            _t54 = _t42 * _t48;
        } else {
            _t52 = 0.0f;
            _t53 = 0.0f;
            _t54 = 0.0f;
        }
        return decomposeScale_unsafe_s90429cf5_2(dest, _t4, _t8, _t9, _t10, _t27, _t32, _t33, _t34, _t47, _t52, _t53, _t54);
    }

    /** Piece 3 of {@code decomposeScale_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeScale_unsafe_s90429cf5_2(long dest, float _t4, float _t8, float _t9, float _t10, float _t27, float _t32, float _t33, float _t34, float _t47, float _t52, float _t53, float _t54) {
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0f ? -_t4 : _t4);
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) java.lang.Math.sqrt(_t27));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) java.lang.Math.sqrt(_t47));
        return dest;
    }

    public static long decomposeSkew_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
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
        float _t14 = Math.fma(_self22, _t7, Math.fma(_self02, _t8, _self12 * _t9));
        float _t15 = Math.fma(_self21, _t7, Math.fma(_self01, _t8, _self11 * _t9));
        float _t17 = -_t15;
        return decomposeSkew_unsafe_s79ad6b4d_1(dest, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, Math.fma(_t17, _t7, _self21), Math.fma(_t17, _t8, _self01), Math.fma(_t17, _t9, _self11));
    }

    /** Piece 2 of {@code decomposeSkew_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_s79ad6b4d_1(long dest, float _self02, float _self12, float _self22, float _t7, float _t8, float _t9, float _t14, float _t15, float _t16, float _t19, float _t20, float _t21) {
        float _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        float _t27 = (1.0f / (float) java.lang.Math.sqrt(_t26));
        float _t32, _t33, _t34;
        if (_t26 != 0.0f) {
            _t32 = _t19 * _t27;
            _t33 = _t20 * _t27;
            _t34 = _t21 * _t27;
        } else {
            _t32 = 0.0f;
            _t33 = 0.0f;
            _t34 = 0.0f;
        }
        float _t37 = Math.fma(Math.fma(_t16, _t7, _self22), _t32, Math.fma(Math.fma(_t16, _t8, _self02), _t33, Math.fma(_t16, _t9, _self12) * _t34));
        float _t38 = -_t37;
        float _t42 = Math.fma(_t16, _t7, Math.fma(_t38, _t32, _self22));
        float _t43 = Math.fma(_t16, _t8, Math.fma(_t38, _t33, _self02));
        float _t44 = Math.fma(_t16, _t9, Math.fma(_t38, _t34, _self12));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        float _t48 = (1.0f / (float) java.lang.Math.sqrt(_t47));
        float _t53, _t54, _t55;
        if (_t47 != 0.0f) {
            _t53 = _t44 * _t48;
            _t54 = _t43 * _t48;
            _t55 = _t42 * _t48;
        } else {
            _t53 = 0.0f;
            _t54 = 0.0f;
            _t55 = 0.0f;
        }
        return decomposeSkew_unsafe_s79ad6b4d_2(dest, _t7, _t8, _t9, _t15 * _t27, _t32, _t33, _t34, _t37, _t48, _t14 * _t48, _t53, _t54, _t55);
    }

    /** Piece 3 of {@code decomposeSkew_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_s79ad6b4d_2(long dest, float _t7, float _t8, float _t9, float _t28, float _t32, float _t33, float _t34, float _t37, float _t48, float _t49, float _t53, float _t54, float _t55) {
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, -_t49);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -_t28);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t49);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t28);
        }
        UnsafeOpsHolder.U.putFloat(dest, _t37 * _t48);
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
        for (int _i = 0; _i < 9; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, Math.fma(t, _eother - _eself, _eself));
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long right) {
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
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21)));
        }
        return dest;
    }

    public static long mulMat2x2_unsafe(long dest, long src, long right) {
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_right00, _self20, _right10 * _self21));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_right01, _self10, _right11 * _self11));
        return mulMat2x2_unsafe_sf068e7fc_1(dest, _self20, _self21, _self02, _self12, _self22, _right01, _right11);
    }

    /** Piece 2 of {@code mulMat2x2_unsafe}, split to fit the inline budget; reached only through it. */
    private static long mulMat2x2_unsafe_sf068e7fc_1(long dest, float _self20, float _self21, float _self02, float _self12, float _self22, float _right01, float _right11) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_right01, _self20, _right11 * _self21));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long mulMat2x3_unsafe(long dest, long src, long right) {
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_right00, _self20, _right10 * _self21));
        return mulMat2x3_unsafe_sa6596eed_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long mulMat2x3_unsafe_sa6596eed_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _right01, float _right11, float _right02, float _right12) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_right01, _self10, _right11 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_right01, _self20, _right11 * _self21));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_right02, _self20, Math.fma(_right12, _self21, _self22)));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
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
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, Math.fma(_other22, _eself2, Math.fma(_other20, _eself0, _other21 * _eself1)));
        }
        return dest;
    }

    public static long preMulMat2x2_unsafe(long dest, long src, long other) {
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_other00, _eself0, _other01 * _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_other10, _eself0, _other11 * _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static long preMulMat2x3_unsafe(long dest, long src, long other) {
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
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long other, float weight) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, Math.fma(weight, _eother, _eself));
        }
        return dest;
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

    public static long lookAlong_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t18 = Math.fma(_t17, _t12, upX);
        float _t19 = Math.fma(_t17, _t13, upY);
        float _t20 = Math.fma(_t17, _t11, upZ);
        return lookAlong_unsafe_s93c4579a_1(dest, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t18, _t20, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s93c4579a_1(long dest, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t18, float _t20, float _t27, float _t28) {
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        float _t46 = Math.fma(_t39, _t12, -(_t38 * _t13));
        float _t47 = Math.fma(_t37, _t13, -(_t39 * _t11));
        float _t48 = Math.fma(_t38, _t11, -(_t37 * _t12));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        return lookAlong_unsafe_s93c4579a_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s93c4579a_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    public static long lookAlong_unsafe(long dest, long src, long dir, long up) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        return lookAlong_unsafe_s6c6e4e50_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _upx, _upy, _upz, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s6c6e4e50_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13) {
        float _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = Math.fma(_t17, _t12, _upx);
        float _t19 = Math.fma(_t17, _t13, _upy);
        float _t20 = Math.fma(_t17, _t11, _upz);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        return lookAlong_unsafe_s6c6e4e50_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, Math.fma(_t39, _t12, -(_t38 * _t13)), Math.fma(_t37, _t13, -(_t39 * _t11)), Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s6c6e4e50_2(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    public static long makeFromDualQuat_unsafe(long dest, float dqRX, float dqRY, float dqRZ, float dqRW) {
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = Math.fma(-2.0f, dqRZ * dqRZ, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, _t0, _t6));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(dqRX, dqRY, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-2.0f, _t3, _sp0 * dqRZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-2.0f, _t2, _sp0 * dqRY));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(-2.0f, _t4, _t6));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * Math.fma(dqRX, dqRW, _t5));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * Math.fma(dqRX, dqRZ, _t3));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f)));
        return dest;
    }

    public static long makeRotation_unsafe(long dest, float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisY;
        float _t3 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = 1.0f - _t1;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t5, axisX * axisX, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(axisZ, _t0, _t5 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t5, _t3, -(axisY * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t5, _t2, -(axisZ * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t5, axisY * axisY, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(axisX, _t0, _t5 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(axisY, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_t5, _t4, -(axisX * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t5, axisZ * axisZ, _t1));
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, long axis, float angle) {
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisy;
        float _t3 = _axisx * _axisz;
        float _t4 = _axisy * _axisz;
        float _t5 = 1.0f - _t1;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t5, _axisx * _axisx, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_axisz, _t0, _t5 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t5, _t3, -(_axisy * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t5, _t2, -(_axisz * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t5, _axisy * _axisy, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_axisx, _t0, _t5 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_axisy, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_t5, _t4, -(_axisx * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t5, _axisz * _axisz, _t1));
        return dest;
    }

    public static long makeRotationLookAlong_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t18 = Math.fma(_t17, _t12, upX);
        float _t19 = Math.fma(_t17, _t13, upY);
        float _t20 = Math.fma(_t17, _t11, upZ);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
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
        return makeRotationLookAlong_unsafe_s3cb72382_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_unsafe_s3cb72382_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    public static long makeRotationLookAlong_unsafe(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = Math.fma(_t17, _t12, _upx);
        float _t19 = Math.fma(_t17, _t13, _upy);
        float _t20 = Math.fma(_t17, _t11, _upz);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_unsafe_sf184e578_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_unsafe_sf184e578_1(long dest, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
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
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t11);
        return dest;
    }

    public static long makeRotationQuat_unsafe(long dest, float qX, float qY, float qZ, float qW) {
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(qX, qY, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(qX, qZ, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * Math.fma(qX, qY, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * Math.fma(qX, qW, qY * qZ));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * Math.fma(qX, qZ, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * Math.fma(qY, qZ, -(qX * qW)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f));
        return dest;
    }

    public static long makeRotationQuat_unsafe(long dest, long q) {
        float _qx = UnsafeOpsHolder.U.getFloat(q);
        float _qy = UnsafeOpsHolder.U.getFloat(q + 4L);
        float _qz = UnsafeOpsHolder.U.getFloat(q + 8L);
        float _qw = UnsafeOpsHolder.U.getFloat(q + 12L);
        float _t0 = _qz * _qz;
        float _t1 = _qz * _qw;
        float _t2 = _qy * _qw;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, Math.fma(_qy, _qy, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(_qx, _qy, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(_qx, _qz, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * Math.fma(_qx, _qy, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(-2.0f, Math.fma(_qx, _qx, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * Math.fma(_qx, _qw, _qy * _qz));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * Math.fma(_qx, _qz, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * Math.fma(_qy, _qz, -(_qx * _qw)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(-2.0f, Math.fma(_qx, _qx, _qy * _qy), 1.0f));
        return dest;
    }

    public static long makeRotationX_unsafe(long dest, float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t1);
        return dest;
    }

    public static long makeRotationXYZ_unsafe(long dest, float angleX, float angleY, float angleZ) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t0;
        float _t7 = _t0 * _t5;
        UnsafeOpsHolder.U.putFloat(dest, _t3 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t6, _t4, _t1 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t2, _t1, -(_t7 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, -(_t1 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t5, _t4, -(_t6 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t7, _t1, _t2 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -(_t2 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t3);
        return dest;
    }

    public static long makeRotationXZY_unsafe(long dest, float angleX, float angleZ, float angleY) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t1 * _t5;
        UnsafeOpsHolder.U.putFloat(dest, _t3 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t7, _t3, _t2 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t6, _t3, -(_t0 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_t1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t5 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t2 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t0 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_t7, _t0, -(_t2 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t6, _t0, _t5 * _t3));
        return dest;
    }

    public static long makeRotationY_unsafe(long dest, float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t1);
        return dest;
    }

    public static long makeRotationYXZ_unsafe(long dest, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t6, _t2, _t3 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t2 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t7, _t2, -(_t1 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t6, _t4, -(_t2 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t5 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t7, _t4, _t1 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t1 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t3);
        return dest;
    }

    public static long makeRotationYZX_unsafe(long dest, float angleY, float angleZ, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t1 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, _t3 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t0 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t2, _t0, -(_t7 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t5 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t6, _t5, _t2 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t7, _t2, _t0 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 28L, -(_t2 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t5, _t3, -(_t6 * _t2)));
        return dest;
    }

    public static long makeRotationZXY_unsafe(long dest, float angleZ, float angleX, float angleY) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t3, _t4, -(_t6 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t7, _t0, _t1 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t0 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 12L, -(_t1 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t5 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t2);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t6, _t3, _t0 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_t0, _t1, -(_t7 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t3);
        return dest;
    }

    public static long makeRotationZYX_unsafe(long dest, float angleZ, float angleY, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t4;
        UnsafeOpsHolder.U.putFloat(dest, _t3 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t1 * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t7, _t2, -(_t1 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t6, _t2, _t5 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t2 * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t7, _t5, _t2 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_t6, _t5, -(_t2 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t5 * _t3);
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
        UnsafeOpsHolder.U.putFloat(dest + 24L, -((left + right) * _t0_inv));
        UnsafeOpsHolder.U.putFloat(dest + 28L, -((bottom + top) * _t1_inv));
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long preRotate_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self00, _t0, _self10 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self01, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self01, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t1, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self02, _t0, _self12 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t2 = Math.sin(0.5f * angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t3 = Math.cosFromSin(_t0, angle);
        float _t8 = (_t2 + _t2) * _t2;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self20, _t9, Math.fma(_self00, _t3, -(_self10 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self20, _t10, Math.fma(_self00, _t0, _self10 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self21, _t9, Math.fma(_self01, _t3, -(_self11 * _t0))));
        return preRotateAround_unsafe_se921fefd_1(dest, _t0, _self01, _self11, _self21, _self02, _self12, _self22, _t3, _t9, _t10);
    }

    /** Piece 2 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_se921fefd_1(long dest, float _t0, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t3, float _t9, float _t10) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self21, _t10, Math.fma(_self01, _t0, _self11 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self22, _t9, Math.fma(_self02, _t3, -(_self12 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self22, _t10, Math.fma(_self02, _t0, _self12 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, long pivot, float angle) {
        float _t0 = Math.sin(angle);
        float _t2 = Math.sin(0.5f * angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _t3 = Math.cosFromSin(_t0, angle);
        float _t8 = (_t2 + _t2) * _t2;
        float _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        float _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self20, _t9, Math.fma(_self00, _t3, -(_self10 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self20, _t10, Math.fma(_self00, _t0, _self10 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        return preRotateAround_unsafe_sf2aeb3ac_1(dest, _t0, _self01, _self11, _self21, _self02, _self12, _self22, _t3, _t9, _t10);
    }

    /** Piece 2 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_sf2aeb3ac_1(long dest, float _t0, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t3, float _t9, float _t10) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self21, _t9, Math.fma(_self01, _t3, -(_self11 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self21, _t10, Math.fma(_self01, _t0, _self11 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self22, _t9, Math.fma(_self02, _t3, -(_self12 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self22, _t10, Math.fma(_self02, _t0, _self12 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preRotateAxis_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        return preRotateAxis_unsafe_scfa5642e_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, _t4, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(_t11, _t2, -(axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_scfa5642e_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    public static long preRotateAxis_unsafe(long dest, long src, long axis, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisz;
        float _t4 = _axisx * _axisy;
        float _t6 = _axisy * _axisz;
        float _t11 = 1.0f - _t1;
        return preRotateAxis_unsafe_sc466f2a9_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _axisx, _axisy, _axisz, _t2, _t4, _t6, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_axisz, _t0, _t11 * _t4), Math.fma(_axisx, _t0, _t11 * _t6));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_sc466f2a9_1(long dest, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _axisx, float _axisy, float _axisz, float _t2, float _t4, float _t6, float _t11, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = Math.fma(_t11, _t4, -(_axisz * _t0));
        float _t25 = Math.fma(_t11, _t6, -(_axisx * _t0));
        float _t26 = Math.fma(_t11, _t2, -(_axisy * _t0));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    public static long preRotateX_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _t1, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self10, _t0, _self20 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self11, _t1, -(_self21 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self11, _t0, _self21 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t1, -(_self22 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self12, _t0, _self22 * _t1));
        return dest;
    }

    public static long preRotateY_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t1, _self20 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self20, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self01, _t1, _self21 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t1, -(_self01 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t1, _self22 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t1, -(_self02 * _t0)));
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(s, _self00, _self20 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(s, _self10, _self20 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(s, _self01, _self21 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(s, _self11, _self21 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(s, _self02, _self22 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(s, _self12, _self22 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long pivot, float s) {
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(s, _self00, _self20 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(s, _self10, _self20 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(s, _self01, _self21 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(s, _self11, _self21 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        return preScaleAround_unsafe_s100adfab_1(dest, s, _self02, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code preScaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_s100adfab_1(long dest, float s, float _self02, float _self12, float _self22, float _t1, float _t2) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(s, _self02, _self22 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(s, _self12, _self22 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(sX, _self00, _self20 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(sY, _self10, _self20 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(sX, _self01, _self21 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(sY, _self11, _self21 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(sX, _self02, _self22 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(sY, _self12, _self22 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long s, long pivot) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _sx = UnsafeOpsHolder.U.getFloat(s);
        float _sy = UnsafeOpsHolder.U.getFloat(s + 4L);
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot) * (1.0f - _sx);
        float _t3 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * (1.0f - _sy);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_sx, _self00, _self20 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_sy, _self10, _self20 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_sx, _self01, _self21 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_sy, _self11, _self21 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        return preScaleAround_unsafe_s9ac39297_1(dest, _self02, _self12, _self22, _sx, _sy, _t2, _t3);
    }

    /** Piece 2 of {@code preScaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_s9ac39297_1(long dest, float _self02, float _self12, float _self22, float _sx, float _sy, float _t2, float _t3) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_sx, _self02, _self22 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_sy, _self12, _self22 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, float vX, float vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_eself2, vX, _eself0));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_eself2, vY, _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_eself2, _vx, _eself0));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_eself2, _vy, _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
        }
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t1, _self01 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self20, _t1, _self21 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self01, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self11, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t1, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t3 = Math.sin(0.5f * angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t8 = (_t3 + _t3) * _t3;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t2, _self01 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _t2, _self11 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self20, _t2, _self21 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self01, _t2, -(_self00 * _t0)));
        return rotateAround_unsafe_sefd3c556_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, Math.fma(pivotX, _t8, pivotY * _t0), Math.fma(pivotY, _t8, -(pivotX * _t0)));
    }

    /** Piece 2 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_sefd3c556_1(long dest, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t9, float _t10) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self11, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t2, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t9, Math.fma(_self21, _t10, _self22)));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long pivot, float angle) {
        float _t0 = Math.sin(angle);
        float _t3 = Math.sin(0.5f * angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t8 = (_t3 + _t3) * _t3;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t2, _self01 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _t2, _self11 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self20, _t2, _self21 * _t0));
        return rotateAround_unsafe_s2c61351f_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, Math.fma(_pivotx, _t8, _pivoty * _t0), Math.fma(_pivoty, _t8, -(_pivotx * _t0)));
    }

    /** Piece 2 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_s2c61351f_1(long dest, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t2, float _t9, float _t10) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self01, _t2, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self11, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t2, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t9, Math.fma(_self21, _t10, _self22)));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        return rotateAxis_unsafe_sba70055b_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)));
    }

    /** Piece 2 of {@code rotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_sba70055b_1(long dest, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisz;
        float _t5 = _axisx * _axisy;
        float _t6 = _axisy * _axisz;
        float _t11 = 1.0f - _t1;
        return rotateAxis_unsafe_sa09c96a8_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _axisx, _axisy, _axisz, _t2, _t5, _t6, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisz, _t0, _t11 * _t5), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_axisy, _t0, _t11 * _t2));
    }

    /** Piece 2 of {@code rotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_sa09c96a8_1(long dest, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _axisx, float _axisy, float _axisz, float _t2, float _t5, float _t6, float _t11, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t24 = Math.fma(_t11, _t2, -(_axisy * _t0));
        float _t25 = Math.fma(_t11, _t5, -(_axisz * _t0));
        float _t26 = Math.fma(_t11, _t6, -(_axisx * _t0));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self01, _t1, _self02 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self11, _t1, _self12 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t1, _self22 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t1, -(_self01 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t1, -(_self21 * _t0)));
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
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleY);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t3 = Math.cosFromSin(_t0, angleX);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        float _t13 = _t5 * _t4;
        float _t18 = Math.fma(_t6, _t4, _t1 * _t3);
        float _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t20, Math.fma(_self00, _t13, _self01 * _t18)));
        return rotateXYZ_unsafe_sbdd7c928_1(dest, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t1 * _t5, _t0 * _t5, _t13, _t3 * _t5, _t18, Math.fma(_t7, _t1, _t0 * _t4), _t20, Math.fma(_t3, _t4, -(_t6 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_sbdd7c928_1(long dest, float _t2, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11, float _t13, float _t15, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, _t20, Math.fma(_self10, _t13, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t20, Math.fma(_self20, _t13, _self21 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self02, _t19, Math.fma(_self01, _t21, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t19, Math.fma(_self11, _t21, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self22, _t19, Math.fma(_self21, _t21, -(_self20 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t15, Math.fma(_self00, _t2, -(_self01 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t15, Math.fma(_self10, _t2, -(_self11 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t15, Math.fma(_self20, _t2, -(_self21 * _t11))));
        return dest;
    }

    public static long rotateXZY_unsafe(long dest, long src, float angleX, float angleZ, float angleY) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleY);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t3 = Math.cosFromSin(_t2, angleY);
        float _t4 = Math.cosFromSin(_t0, angleX);
        float _t5 = Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        float _t15 = _t3 * _t5;
        float _t18 = Math.fma(_t9, _t3, _t0 * _t2);
        float _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t20, Math.fma(_self00, _t15, _self01 * _t18)));
        return rotateXZY_unsafe_sa95ff786_1(dest, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t0 * _t5, _t2 * _t5, _t15, _t4 * _t5, _t18, Math.fma(_t6, _t2, _t4 * _t3), _t20, Math.fma(_t9, _t2, -(_t0 * _t3)));
    }

    /** Piece 2 of {@code rotateXZY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_sa95ff786_1(long dest, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t11, float _t15, float _t16, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, _t20, Math.fma(_self10, _t15, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t20, Math.fma(_self20, _t15, _self21 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self02, _t10, Math.fma(_self01, _t16, -(_self00 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t10, Math.fma(_self11, _t16, -(_self10 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self22, _t10, Math.fma(_self21, _t16, -(_self20 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t19, Math.fma(_self00, _t11, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t19, Math.fma(_self10, _t11, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t19, Math.fma(_self20, _t11, _self21 * _t21)));
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t1, -(_self02 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _t1, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self20, _t1, -(_self22 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self00, _t0, _self02 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t0, _self12 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t0, _self22 * _t1));
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
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t2 * _t5;
        float _t18 = Math.fma(_t6, _t2, _t3 * _t4);
        float _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10)));
        return rotateYXZ_unsafe_s3fca413a_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t1 * _t5, _t5 * _t4, _t5 * _t3, _t18, Math.fma(_t8, _t4, _t1 * _t2), _t20, Math.fma(_t6, _t4, -(_t2 * _t3)));
    }

    /** Piece 2 of {@code rotateYXZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_s3fca413a_1(long dest, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t10, float _t12, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0))));
        return dest;
    }

    public static long rotateYZX_unsafe(long dest, long src, float angleY, float angleZ, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t3 = Math.cosFromSin(_t1, angleZ);
        float _t4 = Math.cosFromSin(_t0, angleY);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        float _t9 = _t1 * _t4;
        float _t13 = _t4 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-_self02, _t7, Math.fma(_self00, _t13, _self01 * _t1)));
        return rotateYZX_unsafe_s358828a_1(dest, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t2 * _t3, _t13, _t5 * _t3, Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t9, _t2, _t0 * _t5), Math.fma(_t2, _t0, -(_t9 * _t5)), Math.fma(_t5, _t4, -(_t6 * _t2)));
    }

    /** Piece 2 of {@code rotateYZX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_s358828a_1(long dest, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t11, float _t13, float _t14, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-_self12, _t7, Math.fma(_self10, _t13, _self11 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-_self22, _t7, Math.fma(_self20, _t13, _self21 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self02, _t18, Math.fma(_self00, _t20, _self01 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t18, Math.fma(_self10, _t20, _self11 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self22, _t18, Math.fma(_self20, _t20, _self21 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t21, Math.fma(_self00, _t19, -(_self01 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t21, Math.fma(_self10, _t19, -(_self11 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t21, Math.fma(_self20, _t19, -(_self21 * _t11))));
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
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleX);
        float _t2 = Math.sin(angleZ);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t3 = Math.cosFromSin(_t1, angleX);
        float _t4 = Math.cosFromSin(_t0, angleY);
        float _t5 = Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t8 = _t1 * _t5;
        return rotateZXY_unsafe_s6e82c462_1(dest, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t0 * _t3, _t2 * _t3, _t3 * _t5, _t3 * _t4, Math.fma(_t8, _t0, _t2 * _t4), Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t4, _t5, -(_t6 * _t0)), Math.fma(_t0, _t2, -(_t8 * _t4)));
    }

    /** Piece 2 of {@code rotateZXY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_s6e82c462_1(long dest, float _t1, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t7, float _t10, float _t14, float _t15, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-_self02, _t7, Math.fma(_self00, _t20, _self01 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-_self12, _t7, Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-_self22, _t7, Math.fma(_self20, _t20, _self21 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self02, _t1, Math.fma(_self01, _t14, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t1, Math.fma(_self11, _t14, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self22, _t1, Math.fma(_self21, _t14, -(_self20 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t15, Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t15, Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t15, Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    public static long rotateZYX_unsafe(long dest, long src, float angleZ, float angleY, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t1 * _t3;
        float _t10 = _t0 * _t4;
        float _t15 = _t3 * _t4;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-_self02, _t0, Math.fma(_self00, _t15, _self01 * _t8)));
        return rotateZYX_unsafe_s71b42f14_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t8, _t2 * _t3, _t15, _t5 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t5, -(_t2 * _t4)));
    }

    /** Piece 2 of {@code rotateZYX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_s71b42f14_1(long dest, float _t0, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _t8, float _t9, float _t15, float _t17, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-_self12, _t0, Math.fma(_self10, _t15, _self11 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-_self22, _t0, Math.fma(_self20, _t15, _self21 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self02, _t9, Math.fma(_self00, _t20, _self01 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t9, Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self22, _t9, Math.fma(_self20, _t20, _self21 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t17, Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self12, _t17, Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t17, Math.fma(_self20, _t19, _self21 * _t21)));
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
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
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
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long pivot, float s) {
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
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        return scaleAround_unsafe_s79e8fc32_1(dest, _self10, _self20, _self11, _self21, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code scaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_s79e8fc32_1(long dest, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _t1, float _t2) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
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
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long s, long pivot) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _sx = UnsafeOpsHolder.U.getFloat(s);
        float _sy = UnsafeOpsHolder.U.getFloat(s + 4L);
        return scaleAround_unsafe_s12fd168c_1(dest, pivot, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _sx, _sy);
    }

    /** Piece 2 of {@code scaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_s12fd168c_1(long dest, long pivot, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _sx, float _sy) {
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot) * (1.0f - _sx);
        float _t3 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * (1.0f - _sy);
        UnsafeOpsHolder.U.putFloat(dest, _sx * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _sx * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _sx * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _sy * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _sy * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _sy * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, float vX, float vY) {
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
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, vX, Math.fma(_self21, vY, _self22)));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _vx, Math.fma(_self21, _vy, _self22)));
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
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, vX, _self01 * vY));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _vx, _self01 * _vy));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
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
