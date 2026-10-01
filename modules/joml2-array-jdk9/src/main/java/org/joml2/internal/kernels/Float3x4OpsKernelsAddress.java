// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3x4Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float3x4Ops} and its sibling kernel units. Not public API.
 */
public final class Float3x4OpsKernelsAddress {
    private Float3x4OpsKernelsAddress() {}

    public static long getColumn_unsafe(long dest, long src, int col) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; _idxSw2 = _self20; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; _idxSw2 = _self21; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; _idxSw2 = _self22; break;
            case 3: _idxSw0 = _self03; _idxSw1 = _self13; _idxSw2 = _self23; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        UnsafeOpsHolder.U.putFloat(dest, _idxSw0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _idxSw1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _idxSw2);
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
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
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
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
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
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
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
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
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
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
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
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
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
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
        return getNormalizedRotation_unsafe_sa46d4105_1(dest, _self00, _self02, _self10, _self12, _self20, _self22, _t7, _t8, (1.0f / (float) java.lang.Math.sqrt(_t7)), (1.0f / (float) java.lang.Math.sqrt(_t8)), _t21, _t23, _t27);
    }

    /** Piece 2 of {@code getNormalizedRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_sa46d4105_1(long dest, float _self00, float _self02, float _self10, float _self12, float _self20, float _self22, float _t7, float _t8, float _t10, float _t11, float _t21, float _t23, float _t27) {
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
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        float _idxSw3;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; _idxSw3 = _self03; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; _idxSw3 = _self13; break;
            case 2: _idxSw0 = _self20; _idxSw1 = _self21; _idxSw2 = _self22; _idxSw3 = _self23; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        UnsafeOpsHolder.U.putFloat(dest, _idxSw0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _idxSw1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _idxSw2);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _idxSw3);
        return dest;
    }

    public static long getScale_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        UnsafeOpsHolder.U.putFloat(dest, (float) java.lang.Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) java.lang.Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) java.lang.Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    public static long getTranslation_unsafe(long dest, long src) {
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        UnsafeOpsHolder.U.putFloat(dest, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self23);
        return dest;
    }

    public static long getUnnormalizedRotation_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
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

    public static long invNegativeX_unsafe(long dest, long src) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.invNegativeX_degenerate(dest, src);
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putFloat(dest, -(_t6 * _t13));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t8 * _t13));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t7 * _t13));
        return dest;
    }

    public static long invNegativeX_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.invNegativeX_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invNegativeX_degenerate_unsafe(long dest, long src) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self10 * _t0;
        float _t9 = _self21 * _t1;
        float _t10 = _self11 * _t0;
        float _t11 = _self20 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self12 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        return invNegativeX_degenerate_unsafe_s4d695f9f_1(dest, _t20, _t21, _t22, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code invNegativeX_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invNegativeX_degenerate_unsafe_s4d695f9f_1(long dest, float _t20, float _t21, float _t22, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t21 * _t26));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t22 * _t26));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t20 * _t26));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    public static long invNegativeY_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        float _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        float _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.invNegativeY_degenerate(dest, src);
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putFloat(dest, -(_t6 * _t13));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t8 * _t13));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t7 * _t13));
        return dest;
    }

    public static long invNegativeY_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.invNegativeY_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invNegativeY_degenerate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self01 * _t0;
        float _t9 = _self20 * _t1;
        float _t10 = _self00 * _t0;
        float _t11 = _self21 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self02 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        return invNegativeY_degenerate_unsafe_scc46b860_1(dest, _t20, _t21, _t22, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code invNegativeY_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invNegativeY_degenerate_unsafe_scc46b860_1(long dest, float _t20, float _t21, float _t22, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t22 * _t26));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t21 * _t26));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t20 * _t26));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    public static long invNegativeZ_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        float _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        float _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.invNegativeZ_degenerate(dest, src);
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putFloat(dest, -(_t6 * _t13));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t8 * _t13));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t7 * _t13));
        return dest;
    }

    public static long invNegativeZ_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.invNegativeZ_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invNegativeZ_degenerate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self10, _self11, _self12);
        float _t8 = _self00 * _t0;
        float _t9 = _self11 * _t1;
        float _t10 = _self01 * _t0;
        float _t11 = _self10 * _t1;
        float _t12 = _self12 * _t1;
        float _t13 = _self02 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        return invNegativeZ_degenerate_unsafe_s632f2441_1(dest, _t20, _t21, _t22, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code invNegativeZ_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invNegativeZ_degenerate_unsafe_s632f2441_1(long dest, float _t20, float _t21, float _t22, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t21 * _t26));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t22 * _t26));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t20 * _t26));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    public static long invNormalizedNegativeX_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, -_self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self02);
        return dest;
    }

    public static long invNormalizedNegativeY_unsafe(long dest, long src) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        UnsafeOpsHolder.U.putFloat(dest, -_self10);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self11);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self12);
        return dest;
    }

    public static long invNormalizedNegativeZ_unsafe(long dest, long src) {
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        UnsafeOpsHolder.U.putFloat(dest, -_self20);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self21);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self22);
        return dest;
    }

    public static long invNormalizedPositiveX_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02);
        return dest;
    }

    public static long invNormalizedPositiveY_unsafe(long dest, long src) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        UnsafeOpsHolder.U.putFloat(dest, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self12);
        return dest;
    }

    public static long invNormalizedPositiveZ_unsafe(long dest, long src) {
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        UnsafeOpsHolder.U.putFloat(dest, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self22);
        return dest;
    }

    public static long invPositiveX_unsafe(long dest, long src) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.invPositiveX_degenerate(dest, src);
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putFloat(dest, _t6 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t8 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t7 * _t13);
        return dest;
    }

    public static long invPositiveX_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.invPositiveX_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invPositiveX_degenerate_unsafe(long dest, long src) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self10 * _t0;
        float _t9 = _self21 * _t1;
        float _t10 = _self11 * _t0;
        float _t11 = _self20 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self12 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t21 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t22 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t20 * _t26);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long invPositiveY_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        float _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        float _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.invPositiveY_degenerate(dest, src);
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putFloat(dest, _t6 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t8 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t7 * _t13);
        return dest;
    }

    public static long invPositiveY_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.invPositiveY_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invPositiveY_degenerate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self01 * _t0;
        float _t9 = _self20 * _t1;
        float _t10 = _self00 * _t0;
        float _t11 = _self21 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self02 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t22 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t21 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t20 * _t26);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long invPositiveZ_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        float _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        float _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.invPositiveZ_degenerate(dest, src);
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putFloat(dest, _t6 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t8 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t7 * _t13);
        return dest;
    }

    public static long invPositiveZ_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.invPositiveZ_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invPositiveZ_degenerate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self10, _self11, _self12);
        float _t8 = _self00 * _t0;
        float _t9 = _self11 * _t1;
        float _t10 = _self01 * _t0;
        float _t11 = _self10 * _t1;
        float _t12 = _self12 * _t1;
        float _t13 = _self02 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t21 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t22 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t20 * _t26);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long negativeX_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_self00 * _t3));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_self10 * _t3));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_self20 * _t3));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    public static long negativeY_unsafe(long dest, long src) {
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_self01 * _t3));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_self11 * _t3));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_self21 * _t3));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    public static long negativeZ_unsafe(long dest, long src) {
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_self02 * _t3));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_self12 * _t3));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_self22 * _t3));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    public static long normalizedNegativeX_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, -_self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self20);
        return dest;
    }

    public static long normalizedNegativeY_unsafe(long dest, long src) {
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        UnsafeOpsHolder.U.putFloat(dest, -_self01);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self11);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self21);
        return dest;
    }

    public static long normalizedNegativeZ_unsafe(long dest, long src) {
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        UnsafeOpsHolder.U.putFloat(dest, -_self02);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self12);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self22);
        return dest;
    }

    public static long normalizedPositiveX_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        return dest;
    }

    public static long normalizedPositiveY_unsafe(long dest, long src) {
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        UnsafeOpsHolder.U.putFloat(dest, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self21);
        return dest;
    }

    public static long normalizedPositiveZ_unsafe(long dest, long src) {
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        UnsafeOpsHolder.U.putFloat(dest, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self22);
        return dest;
    }

    public static long origin_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        UnsafeOpsHolder.U.putFloat(dest, -Math.fma(_self20, _self23, Math.fma(_self00, _self03, _self10 * _self13)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -Math.fma(_self21, _self23, Math.fma(_self01, _self03, _self11 * _self13)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -Math.fma(_self22, _self23, Math.fma(_self02, _self03, _self12 * _self13)));
        return dest;
    }

    public static long positiveX_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _self00 * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _self20 * _t3);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long positiveY_unsafe(long dest, long src) {
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _self01 * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _self11 * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _self21 * _t3);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long positiveZ_unsafe(long dest, long src) {
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _self02 * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _self12 * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _self22 * _t3);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static float determinant_unsafe(long src) {
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        return Math.fma(UnsafeOpsHolder.U.getFloat(src + 8L), Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(UnsafeOpsHolder.U.getFloat(src), Math.fma(_self11, _self22, -(_self12 * _self21)), -(UnsafeOpsHolder.U.getFloat(src + 4L) * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static float frobeniusNorm_unsafe(long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        return (float) java.lang.Math.sqrt(Math.fma(_self00, _self00, Math.fma(_self01, _self01, _self02 * _self02)) + Math.fma(_self03, _self03, Math.fma(_self10, _self10, _self11 * _self11)) + (Math.fma(_self12, _self12, Math.fma(_self13, _self13, _self20 * _self20)) + Math.fma(_self21, _self21, Math.fma(_self22, _self22, _self23 * _self23))));
    }

    public static long invert_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        return invert_unsafe_s1ee1b72a_1(dest, src, _self00, _self01, _self02, _self03, _self10, _self12, _self13, _self20, _self22, _self23, Math.fma(_self11, _self22, -(_self12 * _self21)), Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(_self02, _self21, -(_self01 * _self22)), Math.fma(_self01, _self12, -(_self02 * _self11)), Math.fma(_self12, _self20, -(_self10 * _self22)), Math.fma(_self00, _self22, -(_self02 * _self20)), Math.fma(_self02, _self10, -(_self00 * _self12)), Math.fma(_self01, _self20, -(_self00 * _self21)), Math.fma(_self00, _self11, -(_self01 * _self10)));
    }

    /** Piece 2 of {@code invert_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invert_unsafe_s1ee1b72a_1(long dest, long src, float _self00, float _self01, float _self02, float _self03, float _self10, float _self12, float _self13, float _self20, float _self22, float _self23, float _t20, float _t21, float _t23, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        float _t34 = Math.fma(_self02, _t21, Math.fma(_self00, _t20, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(java.lang.Math.abs(_t34) > 1.1754944E-38f && java.lang.Math.abs(_t34) < 8.507059E37f)) return Float3x4OpsKernelsAddress.invert_degenerate(dest, src);
        float _t34_inv = 1.0f / _t34;
        UnsafeOpsHolder.U.putFloat(dest, _t20 * _t34_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t23 * _t34_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t24 * _t34_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -(Math.fma(_self23, _t24, Math.fma(_self03, _t20, _self13 * _t23)) * _t34_inv));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t25 * _t34_inv);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t26 * _t34_inv);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t27 * _t34_inv);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -(Math.fma(_self23, _t27, Math.fma(_self03, _t25, _self13 * _t26)) * _t34_inv));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t21 * _t34_inv);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t28 * _t34_inv);
        return invert_unsafe_s1ee1b72a_2(dest, _self03, _self13, _self23, _t21, _t28, _t29, _t34_inv);
    }

    /** Piece 3 of {@code invert_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invert_unsafe_s1ee1b72a_2(long dest, float _self03, float _self13, float _self23, float _t21, float _t28, float _t29, float _t34_inv) {
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t29 * _t34_inv);
        UnsafeOpsHolder.U.putFloat(dest + 44L, -(Math.fma(_self23, _t29, Math.fma(_self03, _t21, _self13 * _t28)) * _t34_inv));
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t15 = _self11 * _t0;
        float _t16 = _self22 * _t1;
        float _t17 = _self12 * _t0;
        float _t18 = _self21 * _t1;
        float _t19 = _self10 * _t0;
        float _t20 = _self20 * _t1;
        return invert_degenerate_unsafe_sbf3b0613_1(dest, _t0, _t1, _t2, _t15, _t16, _t17, _t18, _t19, _t20, _self02 * _t2, _self00 * _t2, _self01 * _t2, _self23 * _t1, _self13 * _t0, _self03 * _t2, Math.fma(_t15, _t16, -(_t17 * _t18)), Math.fma(_t19, _t18, -(_t15 * _t20)));
    }

    /** Piece 2 of {@code invert_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_sbf3b0613_1(long dest, float _t0, float _t1, float _t2, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t47, float _t48) {
        float _t50 = Math.fma(_t21, _t18, -(_t23 * _t16));
        float _t51 = Math.fma(_t23, _t17, -(_t21 * _t15));
        float _t52 = Math.fma(_t17, _t20, -(_t19 * _t16));
        float _t53 = Math.fma(_t22, _t16, -(_t21 * _t20));
        float _t54 = Math.fma(_t21, _t19, -(_t22 * _t17));
        float _t60_inv = 1.0f / Math.fma(_t48, _t21, Math.fma(_t47, _t22, -(Math.fma(_t19, _t16, -(_t17 * _t20)) * _t23)));
        float _sp2 = _t1 * _t60_inv;
        float _sp1 = _t0 * _t60_inv;
        float _sp0 = _t2 * _t60_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t47 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t50 * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t51 * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -(Math.fma(_t51, _t24, Math.fma(_t50, _t25, _t47 * _t26)) * _t60_inv));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t52 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t53 * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t54 * _sp2);
        return invert_degenerate_unsafe_sbf3b0613_2(dest, _t24, _t25, _t26, _t48, _t52, _t53, _t54, Math.fma(_t23, _t20, -(_t22 * _t18)), Math.fma(_t22, _t15, -(_t23 * _t19)), _t60_inv, _sp2, _sp1, _sp0);
    }

    /** Piece 3 of {@code invert_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_sbf3b0613_2(long dest, float _t24, float _t25, float _t26, float _t48, float _t52, float _t53, float _t54, float _t55, float _t56, float _t60_inv, float _sp2, float _sp1, float _sp0) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, -(Math.fma(_t54, _t24, Math.fma(_t53, _t25, _t52 * _t26)) * _t60_inv));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t48 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t55 * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t56 * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 44L, -(Math.fma(_t56, _t24, Math.fma(_t55, _t25, _t48 * _t26)) * _t60_inv));
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other03 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 24L);
        float _other13 = UnsafeOpsHolder.U.getFloat(other + 28L);
        float _other20 = UnsafeOpsHolder.U.getFloat(other + 32L);
        float _other21 = UnsafeOpsHolder.U.getFloat(other + 36L);
        return invertProduct_unsafe_sec86c98d_4(dest, src, other, _self00, _self01, _self02, _self10, _self11, _self12, _self20, _self21, _self22, _other00, _other01, _other02, _other03, _other10, _other11, _other12, _other13, _other20, _other21);
    }

    /** Part 1 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static float invertProduct_unsafe_sec86c98d_1(float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32) {
        return Math.fma(_t28, (Math.fma(_t29, _t26, -(_t30 * _t24))), Math.fma(_t31, (Math.fma(_t24, _t25, -(_t26 * _t27))), -(_t32 * Math.fma(_t29, _t25, -(_t30 * _t27)))));
    }

    /** Part 2 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_unsafe_sec86c98d_2(long dest, float _t24, float _t25, float _t26, float _t27, float _t28, float _t32, float _t33, float _t34, float _t35, float _t70) {
        float _t56 = Math.fma(_t24, _t25, -(_t26 * _t27));
        float _t59 = Math.fma(_t26, _t28, -(_t32 * _t25));
        float _t60 = Math.fma(_t32, _t27, -(_t24 * _t28));
        float _t70_inv = 1.0f / _t70;
        UnsafeOpsHolder.U.putFloat(dest, _t56 * _t70_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t59 * _t70_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t60 * _t70_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -(Math.fma(_t33, _t60, Math.fma(_t34, _t56, _t35 * _t59)) * _t70_inv));
    }

    /** Part 3 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_sec86c98d_3(long dest, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34, float _t35, float _t70) {
        float _t57 = Math.fma(_t29, _t26, -(_t30 * _t24));
        float _t61 = Math.fma(_t30, _t27, -(_t29 * _t25));
        float _t62 = Math.fma(_t31, _t25, -(_t30 * _t28));
        float _t63 = Math.fma(_t29, _t28, -(_t31 * _t27));
        float _t64 = Math.fma(_t30, _t32, -(_t31 * _t26));
        float _t65 = Math.fma(_t31, _t24, -(_t29 * _t32));
        float _t70_inv = 1.0f / _t70;
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t61 * _t70_inv);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t62 * _t70_inv);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t63 * _t70_inv);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -(Math.fma(_t33, _t63, Math.fma(_t34, _t61, _t35 * _t62)) * _t70_inv));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t57 * _t70_inv);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t64 * _t70_inv);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t65 * _t70_inv);
        UnsafeOpsHolder.U.putFloat(dest + 44L, -(Math.fma(_t33, _t65, Math.fma(_t34, _t57, _t35 * _t64)) * _t70_inv));
        return dest;
    }

    /** Piece 2 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_sec86c98d_4(long dest, long src, long other, float _self00, float _self01, float _self02, float _self10, float _self11, float _self12, float _self20, float _self21, float _self22, float _other00, float _other01, float _other02, float _other03, float _other10, float _other11, float _other12, float _other13, float _other20, float _other21) {
        float _other22 = UnsafeOpsHolder.U.getFloat(other + 40L);
        float _other23 = UnsafeOpsHolder.U.getFloat(other + 44L);
        return invertProduct_unsafe_sec86c98d_5(dest, src, other, _self00, _self01, _self02, _self10, _self11, _self12, _self20, _self21, _self22, _other03, _other13, _other23, Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)), Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21)), Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21)), Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11)), Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01)), Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11)), Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21)), Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01)), Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01)));
    }

    /** Piece 3 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_sec86c98d_5(long dest, long src, long other, float _self00, float _self01, float _self02, float _self10, float _self11, float _self12, float _self20, float _self21, float _self22, float _other03, float _other13, float _other23, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32) {
        float _t33 = Math.fma(_other03, _self20, Math.fma(_other13, _self21, Math.fma(_other23, _self22, UnsafeOpsHolder.U.getFloat(src + 44L))));
        float _t34 = Math.fma(_other03, _self00, Math.fma(_other13, _self01, Math.fma(_other23, _self02, UnsafeOpsHolder.U.getFloat(src + 12L))));
        float _t35 = Math.fma(_other03, _self10, Math.fma(_other13, _self11, Math.fma(_other23, _self12, UnsafeOpsHolder.U.getFloat(src + 28L))));
        float _t70 = invertProduct_unsafe_sec86c98d_1(_t24, _t25, _t26, _t27, _t28, _t29, _t30, _t31, _t32);
        if (!(java.lang.Math.abs(_t70) > 1.1754944E-38f && java.lang.Math.abs(_t70) < 8.507059E37f)) return Float3x4OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        invertProduct_unsafe_sec86c98d_2(dest, _t24, _t25, _t26, _t27, _t28, _t32, _t33, _t34, _t35, _t70);
        return invertProduct_unsafe_sec86c98d_3(dest, _t24, _t25, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t33, _t34, _t35, _t70);
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other03 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 24L);
        float _other13 = UnsafeOpsHolder.U.getFloat(other + 28L);
        return invertProduct_degenerate_unsafe_s3fc4d1ee_1(dest, other, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other01, _other02, _other03, _other10, _other11, _other12, _other13);
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_s3fc4d1ee_1(long dest, long other, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other00, float _other01, float _other02, float _other03, float _other10, float _other11, float _other12, float _other13) {
        float _other20 = UnsafeOpsHolder.U.getFloat(other + 32L);
        float _other21 = UnsafeOpsHolder.U.getFloat(other + 36L);
        float _other22 = UnsafeOpsHolder.U.getFloat(other + 40L);
        float _other23 = UnsafeOpsHolder.U.getFloat(other + 44L);
        float _t24 = Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11));
        float _t25 = Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11));
        float _t26 = Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11));
        float _t27 = Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21));
        float _t28 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        float _t29 = Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21));
        return invertProduct_degenerate_unsafe_s3fc4d1ee_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other03, _other13, _other23, _t24, _t25, _t26, _t27, _t28, _t29, Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01)), Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01)), Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01)), unitScale(_t25, _t24, _t26), unitScale(_t28, _t29, _t27));
    }

    /** Piece 3 of {@code invertProduct_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_s3fc4d1ee_2(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other03, float _other13, float _other23, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t36, float _t37) {
        float _t38 = unitScale(_t30, _t31, _t32);
        float _t48 = _t24 * _t36;
        float _t49 = _t27 * _t37;
        float _t50 = _t29 * _t37;
        float _t51 = _t26 * _t36;
        float _t52 = _t25 * _t36;
        float _t53 = _t28 * _t37;
        float _t54 = _t32 * _t38;
        float _t55 = _t30 * _t38;
        float _t56 = _t31 * _t38;
        return invertProduct_degenerate_unsafe_s3fc4d1ee_3(dest, _t36, _t37, _t38, _t49, _t51, _t52, _t53, _t54, _t55, _t56, Math.fma(_other03, _self20, Math.fma(_other13, _self21, Math.fma(_other23, _self22, _self23))) * _t37, Math.fma(_other03, _self00, Math.fma(_other13, _self01, Math.fma(_other23, _self02, _self03))) * _t38, Math.fma(_other03, _self10, Math.fma(_other13, _self11, Math.fma(_other23, _self12, _self13))) * _t36, Math.fma(_t48, _t49, -(_t50 * _t51)), Math.fma(_t52, _t50, -(_t53 * _t48)), Math.fma(_t50, _t54, -(_t56 * _t49)), Math.fma(_t56, _t51, -(_t48 * _t54)), Math.fma(_t53, _t51, -(_t52 * _t49)), Math.fma(_t55, _t49, -(_t53 * _t54)), Math.fma(_t52, _t54, -(_t55 * _t51)), Math.fma(_t53, _t56, -(_t55 * _t50)), Math.fma(_t55, _t48, -(_t52 * _t56)));
    }

    /** Piece 4 of {@code invertProduct_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_s3fc4d1ee_3(long dest, float _t36, float _t37, float _t38, float _t49, float _t51, float _t52, float _t53, float _t54, float _t55, float _t56, float _t60, float _t61, float _t62, float _t83, float _t84, float _t86, float _t87, float _t88, float _t89, float _t90, float _t91, float _t92) {
        float _t96_inv = 1.0f / Math.fma(_t84, _t54, Math.fma(_t83, _t55, -(Math.fma(_t52, _t49, -(_t53 * _t51)) * _t56)));
        float _sp2 = _t37 * _t96_inv;
        float _sp1 = _t36 * _t96_inv;
        float _sp0 = _t38 * _t96_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t83 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t86 * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t87 * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -(Math.fma(_t87, _t60, Math.fma(_t83, _t61, _t86 * _t62)) * _t96_inv));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t88 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t89 * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t90 * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -(Math.fma(_t90, _t60, Math.fma(_t89, _t62, _t88 * _t61)) * _t96_inv));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t84 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t91 * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t92 * _sp2);
        UnsafeOpsHolder.U.putFloat(dest + 44L, -(Math.fma(_t92, _t60, Math.fma(_t84, _t61, _t91 * _t62)) * _t96_inv));
        return dest;
    }

    public static long transpose_unsafe(long dest, long src) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, _eself);
        }
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, _eother + _eself);
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float scalar) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, scalar * _eself);
        }
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, -_eself);
        }
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, _eself - _eother);
        }
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        for (int _i = 0; _i < 12; _i++) {
            float _ev = UnsafeOpsHolder.U.getFloat(v + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, _ev);
        }
        return dest;
    }

    public static long setMat3x3_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m20 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 12L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 16L);
        float _m21 = UnsafeOpsHolder.U.getFloat(m + 20L);
        float _m02 = UnsafeOpsHolder.U.getFloat(m + 24L);
        float _m12 = UnsafeOpsHolder.U.getFloat(m + 28L);
        float _m22 = UnsafeOpsHolder.U.getFloat(m + 32L);
        UnsafeOpsHolder.U.putFloat(dest, _m00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _m01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _m02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _m10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _m11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _m12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _m20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _m21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _m22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long setMat4x4_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m20 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 20L);
        float _m21 = UnsafeOpsHolder.U.getFloat(m + 24L);
        float _m02 = UnsafeOpsHolder.U.getFloat(m + 32L);
        float _m12 = UnsafeOpsHolder.U.getFloat(m + 36L);
        float _m22 = UnsafeOpsHolder.U.getFloat(m + 40L);
        float _m03 = UnsafeOpsHolder.U.getFloat(m + 48L);
        float _m13 = UnsafeOpsHolder.U.getFloat(m + 52L);
        float _m23 = UnsafeOpsHolder.U.getFloat(m + 56L);
        UnsafeOpsHolder.U.putFloat(dest, _m00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _m01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _m02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _m03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _m10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _m11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _m12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _m13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _m20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _m21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _m22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _m23);
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, float tX, float tY, float tZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, tX);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, tY);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, tZ);
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, long t) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _tx = UnsafeOpsHolder.U.getFloat(t);
        float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
        float _tz = UnsafeOpsHolder.U.getFloat(t + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _tx);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _ty);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _tz);
        return dest;
    }

    public static long makeFromRigid_unsafe(long dest, float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, Math.fma(rRY, rRY, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(rRX, rRY, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(rRX, rRZ, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, rTX);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 2.0f * Math.fma(rRX, rRY, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-2.0f, Math.fma(rRX, rRX, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * Math.fma(rRY, rRZ, -(rRX * rRW)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, rTY);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 2.0f * Math.fma(rRX, rRZ, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 36L, 2.0f * Math.fma(rRX, rRW, rRY * rRZ));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-2.0f, Math.fma(rRX, rRX, rRY * rRY), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 44L, rTZ);
        return dest;
    }

    public static long makeFromTransform_unsafe(long dest, float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(tRX, tRY, -_t4) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(tRX, tRZ, _t5) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 12L, tTX);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(tRX, tRY, _t4) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 28L, tTY);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(tRX, tRZ, -_t5) * _t0);
        return makeFromTransform_unsafe_s411fc2d1_1(dest, tTZ, tRX, tRY, tRZ, tRW, tSZ, _t1, _t2);
    }

    /** Piece 2 of {@code makeFromTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeFromTransform_unsafe_s411fc2d1_1(long dest, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSZ, float _t1, float _t2) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        UnsafeOpsHolder.U.putFloat(dest + 44L, tTZ);
        return dest;
    }

    public static long to3x3_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self22);
        return dest;
    }

    public static long to4x4_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
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
        return to4x4_unsafe_s575d5859_1(dest, _self03, _self13, _self23);
    }

    /** Piece 2 of {@code to4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long to4x4_unsafe_s575d5859_1(long dest, float _self03, float _self13, float _self23) {
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 48L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 52L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 56L, _self23);
        UnsafeOpsHolder.U.putFloat(dest + 60L, 1.0f);
        return dest;
    }

    public static long toDualQuat_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t2 = 1.0f - _self00;
        float _t14 = _self22 + (_self00 + _self11);
        float _t15 = 1.0f + _t14;
        float _t17 = _self11 + (_t2 - _self22);
        float _t18 = _self22 + (_t2 - _self11);
        return toDualQuat_unsafe_s9f6c0bda_1(dest, _self00, _self03, _self11, _self13, _self22, _self23, -_self23, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t14, _t15, _self00 + (1.0f - _self11 - _self22), _t17, _t18, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t18)));
    }

    /** Piece 2 of {@code toDualQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toDualQuat_unsafe_s9f6c0bda_1(long dest, float _self00, float _self03, float _self11, float _self13, float _self22, float _self23, float _t0, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _t18, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t63, _t64, _t65, _t66;
        if (_t14 > 0.0f) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5f * (float) java.lang.Math.sqrt(_t15);
        } else {
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
                _t63 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (_self11 > _self22) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5f * (float) java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                    _t66 = _sp1 * _t8;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5f * (float) java.lang.Math.sqrt(_t18);
                    _t66 = _sp2 * _t10;
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, _t63);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t64);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t65);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t66);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.5f * Math.fma(_t0, _t64, Math.fma(_self03, _t66, _self13 * _t65)));
        return toDualQuat_unsafe_s9f6c0bda_2(dest, _self03, _self13, _self23, _t0, _t63, _t64, _t65, _t66);
    }

    /** Piece 3 of {@code toDualQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toDualQuat_unsafe_s9f6c0bda_2(long dest, float _self03, float _self13, float _self23, float _t0, float _t63, float _t64, float _t65, float _t66) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.5f * Math.fma(_self23, _t63, Math.fma(_self13, _t66, -(_self03 * _t65))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.5f * Math.fma(_self23, _t66, Math.fma(_self03, _t64, -(_self13 * _t63))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.5f * Math.fma(_t0, _t65, Math.fma(-_self13, _t64, -(_self03 * _t63))));
        return dest;
    }

    public static long toRigid_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.toRigid_degenerate(dest, src);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.toRigid_degenerate(dest, src);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.toRigid_degenerate(dest, src);
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
        UnsafeOpsHolder.U.putFloat(dest, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self23);
        return dest;
    }

    public static long toRigid_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.toRigid_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long toRigid_degenerate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
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
        UnsafeOpsHolder.U.putFloat(dest, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self23);
        return dest;
    }

    public static long toTransform_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.toTransform_degenerate(dest, src);
        float _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.toTransform_degenerate(dest, src);
        float _t14 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsAddress.toTransform_degenerate(dest, src);
        return toTransform_unsafe_s96acba85_7(dest, src, _self00, _self01, _self02, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t12, _t13, _t14);
    }

    /** Part 1 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static float toTransform_unsafe_s96acba85_1(float _self00, float _self01, float _self02, float _self10, float _self11, float _self12, float _self20, float _self21, float _self22, float _t12, float _t13, float _t14) {
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t17 = 1.0f / ((float) java.lang.Math.sqrt(_t14));
        float _t19 = _self10 * _t17;
        float _t20 = _self22 * _t16;
        float _t21 = _self12 * _t16;
        float _t22 = _self20 * _t17;
        float _t24 = _self21 * _t15;
        float _t25 = _self11 * _t15;
        return Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), _self01 * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), _self02 * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * (_self00 * _t17)));
    }

    /** Part 2 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static void toTransform_unsafe_s96acba85_2(long dest, long src, float _self01, float _self02, float _self11, float _self12, float _self13, float _self21, float _self22, float _self23, float _t12, float _t13, float _t48, float _t49, float _t50) {
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t20 = _self22 * _t16;
        float _t25 = _self11 * _t15;
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        UnsafeOpsHolder.U.putFloat(dest, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self23);
        UnsafeOpsHolder.U.putFloat(dest + 12L, (Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48))) > 0.0f ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52)))))) * (Math.fma(_self21, _t15, -(_self12 * _t16))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? 0.5f * (float) java.lang.Math.sqrt((Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)))) : _t25 > _t20 ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53)))))) * (Math.fma(_self01, _t15, _t49)) : (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53)))))) * (Math.fma(_self02, _t16, _t50)));
    }

    /** Part 3 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static void toTransform_unsafe_s96acba85_3(long dest, float _self01, float _self02, float _self11, float _self12, float _self21, float _self22, float _t12, float _t13, float _t48, float _t50, float _t49) {
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t20 = _self22 * _t16;
        float _t25 = _self11 * _t15;
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        UnsafeOpsHolder.U.putFloat(dest + 16L, (Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48))) > 0.0f ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52)))))) * (Math.fma(_self02, _t16, -_t50)) : _t48 > (java.lang.Math.max(_t25, _t20)) ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)))))) * (Math.fma(_self01, _t15, _t49)) : _t25 > _t20 ? 0.5f * (float) java.lang.Math.sqrt((Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53)))) : (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53)))))) * (Math.fma(_self12, _t16, (_self21 * _t15))));
    }

    /** Part 4 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static void toTransform_unsafe_s96acba85_4(long dest, float _self01, float _self02, float _self11, float _self12, float _self21, float _self22, float _t12, float _t13, float _t48, float _t49, float _t50) {
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t20 = _self22 * _t16;
        float _t25 = _self11 * _t15;
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        UnsafeOpsHolder.U.putFloat(dest + 20L, (Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48))) > 0.0f ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52)))))) * (Math.fma(-_self01, _t15, _t49)) : _t48 > (java.lang.Math.max(_t25, _t20)) ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)))))) * (Math.fma(_self02, _t16, _t50)) : _t25 > _t20 ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53)))))) * (Math.fma(_self12, _t16, (_self21 * _t15))) : 0.5f * (float) java.lang.Math.sqrt((Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53)))));
    }

    /** Part 5 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static void toTransform_unsafe_s96acba85_5(long dest, float _self01, float _self02, float _self11, float _self12, float _self21, float _self22, float _t12, float _t13, float _t48, float _t50, float _t49) {
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t13));
        float _t20 = _self22 * _t16;
        float _t25 = _self11 * _t15;
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        UnsafeOpsHolder.U.putFloat(dest + 24L, (Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48))) > 0.0f ? 0.5f * (float) java.lang.Math.sqrt((Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52)))) : _t48 > (java.lang.Math.max(_t25, _t20)) ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)))))) * (Math.fma(_self21, _t15, -(_self12 * _t16))) : _t25 > _t20 ? (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53)))))) * (Math.fma(_self02, _t16, -_t50)) : (0.5f * (1.0f / (float) java.lang.Math.sqrt((Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53)))))) * (Math.fma(-_self01, _t15, _t49)));
    }

    /** Part 6 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_s96acba85_6(long dest, float _t12, float _t13, float _t14, float _t47) {
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _t47 < 0.0f ? -_t18 : _t18);
        UnsafeOpsHolder.U.putFloat(dest + 32L, (float) java.lang.Math.sqrt(_t12));
        UnsafeOpsHolder.U.putFloat(dest + 36L, (float) java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** Piece 2 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_s96acba85_7(long dest, long src, float _self00, float _self01, float _self02, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t12, float _t13, float _t14) {
        float _t17 = 1.0f / ((float) java.lang.Math.sqrt(_t14));
        float _t19 = _self10 * _t17;
        float _t22 = _self20 * _t17;
        float _t27 = _self00 * _t17;
        float _t47 = toTransform_unsafe_s96acba85_1(_self00, _self01, _self02, _self10, _self11, _self12, _self20, _self21, _self22, _t12, _t13, _t14);
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
        toTransform_unsafe_s96acba85_2(dest, src, _self01, _self02, _self11, _self12, _self13, _self21, _self22, _self23, _t12, _t13, _t48, _t49, _t50);
        toTransform_unsafe_s96acba85_3(dest, _self01, _self02, _self11, _self12, _self21, _self22, _t12, _t13, _t48, _t50, _t49);
        toTransform_unsafe_s96acba85_4(dest, _self01, _self02, _self11, _self12, _self21, _self22, _t12, _t13, _t48, _t49, _t50);
        toTransform_unsafe_s96acba85_5(dest, _self01, _self02, _self11, _self12, _self21, _self22, _t12, _t13, _t48, _t50, _t49);
        return toTransform_unsafe_s96acba85_6(dest, _t12, _t13, _t14, _t47);
    }

    public static long toTransform_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.toTransform_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long toTransform_degenerate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
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
        UnsafeOpsHolder.U.putFloat(dest, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self23);
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
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
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
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
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
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
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

    public static long decomposeTRS_unsafe(long translation, long rotation, long scale, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = 1.0f / ((float) java.lang.Math.sqrt(_t2));
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
        return decomposeTRS_unsafe_s2318f5ef_5(translation, rotation, scale, src, _self01, _self02, _self11, _self12, _self13, _self21, _self22, _self23, _t2, _t8, _t9, _t10, -Math.fma(_self21, _t8, Math.fma(_self01, _t9, _self11 * _t10)), -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10)));
    }

    /** Part 1 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static float decomposeTRS_unsafe_s2318f5ef_1(float _self01, float _self11, float _self21, float _t8, float _t9, float _t10, float _t20) {
        float _t22 = Math.fma(_t20, _t8, _self21);
        float _t23 = Math.fma(_t20, _t9, _self01);
        float _t24 = Math.fma(_t20, _t10, _self11);
        return Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24));
    }

    /** Part 2 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static float decomposeTRS_unsafe_s2318f5ef_2(float _self02, float _self12, float _self22, float _t8, float _t9, float _t10, float _t21, float _t36, float _t35, float _t37, float _t41) {
        float _t45 = Math.fma(_t21, _t8, Math.fma(_t41, _t36, _self22));
        float _t46 = Math.fma(_t21, _t9, Math.fma(_t41, _t35, _self02));
        float _t47 = Math.fma(_t21, _t10, Math.fma(_t41, _t37, _self12));
        return Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47));
    }

    /** Part 3 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static float decomposeTRS_unsafe_s2318f5ef_3(long translation, long src, float _self13, float _self23, float _t8, float _t9, float _t10, float _t35, float _t37, float _t36, float _t55, float _t56, float _t57) {
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(translation, _self03);
        UnsafeOpsHolder.U.putFloat(translation + 4L, _self13);
        UnsafeOpsHolder.U.putFloat(translation + 8L, _self23);
        return Math.fma(Math.fma(_t35, _t55, -(_t37 * _t56)), _t8, Math.fma(Math.fma(_t37, _t57, -(_t36 * _t55)), _t9, Math.fma(_t36, _t56, -(_t35 * _t57)) * _t10));
    }

    /** Part of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static void decomposeTRS_unsafe_s2318f5ef_524(long rotation, float _t9, float _t10, float _t8, float _t37, float _t36, float _t35, float _t57, float _t55, float _t56, float _t73) {
        float _t74, _t75, _t76;
        if (_t73 < 0.0f) {
            _t74 = -_t9;
            _t75 = -_t10;
            _t76 = -_t8;
        } else {
            _t74 = _t9;
            _t75 = _t10;
            _t76 = _t8;
        }
        float _t77 = _t74 + _t37;
        float _t83 = _t77 + _t57;
        float _t87 = 1.0f + _t83;
        float _t88 = 1.0f + (_t74 - (_t37 + _t57));
        float _t89 = 1.0f + (_t37 - (_t74 + _t57));
        float _t90 = 1.0f + (_t57 - _t77);
        decomposeTRS_unsafe_s2318f5ef_524_s604576da_1(rotation, _t37, _t57, _t36 - _t55, _t36 + _t55, _t74, _t75 + _t35, _t75 - _t35, _t76 + _t56, _t56 - _t76, _t83, _t87, _t88, _t89, _t90, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t87)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t89)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t90)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t88)));
    }

    /** Piece 2 of {@code decomposeTRS_unsafe_s2318f5ef_524}, split to fit the inline budget; reached only through it. */
    private static void decomposeTRS_unsafe_s2318f5ef_524_s604576da_1(long rotation, float _t37, float _t57, float _t61, float _t64, float _t74, float _t78, float _t79, float _t81, float _t82, float _t83, float _t87, float _t88, float _t89, float _t90, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t83 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(rotation, _sp0 * _t61);
            UnsafeOpsHolder.U.putFloat(rotation + 4L, _sp0 * _t82);
            UnsafeOpsHolder.U.putFloat(rotation + 8L, _sp0 * _t79);
            UnsafeOpsHolder.U.putFloat(rotation + 12L, 0.5f * (float) java.lang.Math.sqrt(_t87));
        } else {
            if (_t74 > java.lang.Math.max(_t37, _t57)) {
                UnsafeOpsHolder.U.putFloat(rotation, 0.5f * (float) java.lang.Math.sqrt(_t88));
                UnsafeOpsHolder.U.putFloat(rotation + 4L, _sp3 * _t78);
                UnsafeOpsHolder.U.putFloat(rotation + 8L, _sp3 * _t81);
                UnsafeOpsHolder.U.putFloat(rotation + 12L, _sp3 * _t61);
            } else {
                if (_t37 > _t57) {
                    UnsafeOpsHolder.U.putFloat(rotation, _sp1 * _t78);
                    UnsafeOpsHolder.U.putFloat(rotation + 4L, 0.5f * (float) java.lang.Math.sqrt(_t89));
                    UnsafeOpsHolder.U.putFloat(rotation + 8L, _sp1 * _t64);
                    UnsafeOpsHolder.U.putFloat(rotation + 12L, _sp1 * _t82);
                } else {
                    UnsafeOpsHolder.U.putFloat(rotation, _sp2 * _t81);
                    UnsafeOpsHolder.U.putFloat(rotation + 4L, _sp2 * _t64);
                    UnsafeOpsHolder.U.putFloat(rotation + 8L, 0.5f * (float) java.lang.Math.sqrt(_t90));
                    UnsafeOpsHolder.U.putFloat(rotation + 12L, _sp2 * _t79);
                }
            }
        }
    }

    /** Part 4 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeTRS_unsafe_s2318f5ef_4(long translation, long scale, float _t2, float _t30, float _t50, float _t73) {
        float _t4 = (float) java.lang.Math.sqrt(_t2);
        UnsafeOpsHolder.U.putFloat(scale, _t73 < 0.0f ? -_t4 : _t4);
        UnsafeOpsHolder.U.putFloat(scale + 4L, (float) java.lang.Math.sqrt(_t30));
        UnsafeOpsHolder.U.putFloat(scale + 8L, (float) java.lang.Math.sqrt(_t50));
        return translation;
    }

    /** Piece 2 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeTRS_unsafe_s2318f5ef_5(long translation, long rotation, long scale, long src, float _self01, float _self02, float _self11, float _self12, float _self13, float _self21, float _self22, float _self23, float _t2, float _t8, float _t9, float _t10, float _t20, float _t21) {
        float _t30 = decomposeTRS_unsafe_s2318f5ef_1(_self01, _self11, _self21, _t8, _t9, _t10, _t20);
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t30));
        float _t35, _t36, _t37;
        if (_t30 != 0.0f) {
            _t35 = Math.fma(_t20, _t9, _self01) * _t31;
            _t36 = Math.fma(_t20, _t8, _self21) * _t31;
            _t37 = Math.fma(_t20, _t10, _self11) * _t31;
        } else {
            _t35 = 0.0f;
            _t36 = 0.0f;
            _t37 = 0.0f;
        }
        float _t41 = -Math.fma(Math.fma(_t21, _t8, _self22), _t36, Math.fma(Math.fma(_t21, _t9, _self02), _t35, Math.fma(_t21, _t10, _self12) * _t37));
        float _t50 = decomposeTRS_unsafe_s2318f5ef_2(_self02, _self12, _self22, _t8, _t9, _t10, _t21, _t36, _t35, _t37, _t41);
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        float _t55, _t56, _t57;
        if (_t50 != 0.0f) {
            _t55 = Math.fma(_t21, _t10, Math.fma(_t41, _t37, _self12)) * _t51;
            _t56 = Math.fma(_t21, _t9, Math.fma(_t41, _t35, _self02)) * _t51;
            _t57 = Math.fma(_t21, _t8, Math.fma(_t41, _t36, _self22)) * _t51;
        } else {
            _t55 = 0.0f;
            _t56 = 0.0f;
            _t57 = 0.0f;
        }
        return decomposeTRS_unsafe_s2318f5ef_6(translation, rotation, scale, src, _self13, _self23, _t2, _t8, _t9, _t10, _t30, _t35, _t36, _t37, _t50, _t55, _t56, _t57);
    }

    /** Piece 3 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeTRS_unsafe_s2318f5ef_6(long translation, long rotation, long scale, long src, float _self13, float _self23, float _t2, float _t8, float _t9, float _t10, float _t30, float _t35, float _t36, float _t37, float _t50, float _t55, float _t56, float _t57) {
        float _t73 = decomposeTRS_unsafe_s2318f5ef_3(translation, src, _self13, _self23, _t8, _t9, _t10, _t35, _t37, _t36, _t55, _t56, _t57);
        decomposeTRS_unsafe_s2318f5ef_524(rotation, _t9, _t10, _t8, _t37, _t36, _t35, _t57, _t55, _t56, _t73);
        return decomposeTRS_unsafe_s2318f5ef_4(translation, scale, _t2, _t30, _t50, _t73);
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, float t) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, Math.fma(t, _eother - _eself, _eself));
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long right) {
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right02 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right03 = UnsafeOpsHolder.U.getFloat(right + 12L);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 16L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 20L);
        float _right12 = UnsafeOpsHolder.U.getFloat(right + 24L);
        float _right13 = UnsafeOpsHolder.U.getFloat(right + 28L);
        float _right20 = UnsafeOpsHolder.U.getFloat(right + 32L);
        float _right21 = UnsafeOpsHolder.U.getFloat(right + 36L);
        float _right22 = UnsafeOpsHolder.U.getFloat(right + 40L);
        float _right23 = UnsafeOpsHolder.U.getFloat(right + 44L);
        return mul_unsafe_sc4d3a782_1(dest, src, _right00, _right01, _right02, _right03, _right10, _right11, _right12, _right13, _right20, _right21, _right22, _right23);
    }

    /** Piece 2 of {@code mul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long mul_unsafe_sc4d3a782_1(long dest, long src, float _right00, float _right01, float _right02, float _right03, float _right10, float _right11, float _right12, float _right13, float _right20, float _right21, float _right22, float _right23) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, Math.fma(_right03, _eself0, Math.fma(_right13, _eself1, Math.fma(_right23, _eself2, _eself3))));
        }
        return dest;
    }

    public static long mulMat2x2_unsafe(long dest, long src, long right) {
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 12L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_right00, _eself0, _right10 * _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_right01, _eself0, _right11 * _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mulMat2x3_unsafe(long dest, long src, long right) {
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 12L);
        float _right02 = UnsafeOpsHolder.U.getFloat(right + 16L);
        float _right12 = UnsafeOpsHolder.U.getFloat(right + 20L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_right00, _eself0, _right10 * _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_right01, _eself0, _right11 * _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, Math.fma(_right02, _eself0, Math.fma(_right12, _eself1, _eself3)));
        }
        return dest;
    }

    public static long mulMat3x3_unsafe(long dest, long src, long right) {
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right20 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 12L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 16L);
        float _right21 = UnsafeOpsHolder.U.getFloat(right + 20L);
        float _right02 = UnsafeOpsHolder.U.getFloat(right + 24L);
        float _right12 = UnsafeOpsHolder.U.getFloat(right + 28L);
        float _right22 = UnsafeOpsHolder.U.getFloat(right + 32L);
        return mulMat3x3_unsafe_s3c8f3084_1(dest, src, _right00, _right10, _right20, _right01, _right11, _right21, _right02, _right12, _right22);
    }

    /** Piece 2 of {@code mulMat3x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long mulMat3x3_unsafe_s3c8f3084_1(long dest, long src, float _right00, float _right10, float _right20, float _right01, float _right11, float _right21, float _right02, float _right12, float _right22) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mulMat4x4_unsafe(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        return mulMat4x4_unsafe_saec0f58c_1(dest, right, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code mulMat4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long mulMat4x4_unsafe_saec0f58c_1(long dest, long right, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23) {
        for (int _l = 0; _l < 4; _l++) {
            int _lo = _l * 4;
            float _eright0 = UnsafeOpsHolder.U.getFloat(right + _lo * 4L);
            float _eright1 = UnsafeOpsHolder.U.getFloat(right + (_lo + 1) * 4L);
            float _eright2 = UnsafeOpsHolder.U.getFloat(right + (_lo + 2) * 4L);
            float _eright3 = UnsafeOpsHolder.U.getFloat(right + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_eright3, _self03, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01))));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_eright3, _self13, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11))));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, Math.fma(_eright3, _self23, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21))));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eright3);
        }
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        return preMul_unsafe_sc0fcc75d_1(dest, other, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMul_unsafe_sc0fcc75d_1(long dest, long other, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eother0 = UnsafeOpsHolder.U.getFloat(other + _lo * 4L);
            float _eother1 = UnsafeOpsHolder.U.getFloat(other + (_lo + 1) * 4L);
            float _eother2 = UnsafeOpsHolder.U.getFloat(other + (_lo + 2) * 4L);
            float _eother3 = UnsafeOpsHolder.U.getFloat(other + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_eother2, _self20, Math.fma(_eother0, _self00, _eother1 * _self10)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_eother2, _self21, Math.fma(_eother0, _self01, _eother1 * _self11)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, Math.fma(_eother2, _self22, Math.fma(_eother0, _self02, _eother1 * _self12)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, Math.fma(_eother0, _self03, Math.fma(_eother1, _self13, Math.fma(_eother2, _self23, _eother3))));
        }
        return dest;
    }

    public static long preMulMat2x2_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_other00, _self00, _other01 * _self10));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_other00, _self02, _other01 * _self12));
        return preMulMat2x2_unsafe_sc3bf1023_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other01, _other11);
    }

    /** Piece 2 of {@code preMulMat2x2_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat2x2_unsafe_sc3bf1023_1(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other00, float _other10, float _other01, float _other11) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_other00, _self03, _other01 * _self13));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_other10, _self01, _other11 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_other10, _self02, _other11 * _self12));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_other10, _self03, _other11 * _self13));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long preMulMat2x3_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 20L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_other00, _self00, _other01 * _self10));
        return preMulMat2x3_unsafe_s9a8c7fa2_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other01, _other11, _other02, _other12);
    }

    /** Piece 2 of {@code preMulMat2x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat2x3_unsafe_s9a8c7fa2_1(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other00, float _other10, float _other01, float _other11, float _other02, float _other12) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_other00, _self02, _other01 * _self12));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_other00, _self03, Math.fma(_other01, _self13, _other02)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_other10, _self01, _other11 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_other10, _self02, _other11 * _self12));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_other10, _self03, Math.fma(_other11, _self13, _other12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long preMulMat3x3_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other20 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other21 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 24L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 28L);
        return preMulMat3x3_unsafe_s8133e2b_1(dest, other, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12);
    }

    /** Piece 2 of {@code preMulMat3x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat3x3_unsafe_s8133e2b_1(long dest, long other, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12) {
        float _other22 = UnsafeOpsHolder.U.getFloat(other + 32L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_other02, _self23, Math.fma(_other00, _self03, _other01 * _self13)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_other12, _self23, Math.fma(_other10, _self03, _other11 * _self13)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10)));
        return preMulMat3x3_unsafe_s8133e2b_2(dest, _self01, _self02, _self03, _self11, _self12, _self13, _self21, _self22, _self23, _other20, _other21, _other22);
    }

    /** Piece 3 of {@code preMulMat3x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat3x3_unsafe_s8133e2b_2(long dest, float _self01, float _self02, float _self03, float _self11, float _self12, float _self13, float _self21, float _self22, float _self23, float _other20, float _other21, float _other22) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_other22, _self23, Math.fma(_other20, _self03, _other21 * _self13)));
        return dest;
    }

    public static long preMulMat4x4_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other20 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other30 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _other21 = UnsafeOpsHolder.U.getFloat(other + 24L);
        float _other31 = UnsafeOpsHolder.U.getFloat(other + 28L);
        return preMulMat4x4_unsafe_s18080933_3(dest, other, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31);
    }

    /** Part 1 of {@code preMulMat4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static float preMulMat4x4_unsafe_s18080933_1(long dest, long other, float _self00, float _self01, float _self10, float _self11, float _self20, float _self21, float _other00, float _other10, float _other20, float _other30, float _other01, float _other11, float _other21, float _other31, float _other02, float _other12, float _other22, float _other32) {
        float _other33 = UnsafeOpsHolder.U.getFloat(other + 60L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_other32, _self20, Math.fma(_other30, _self00, _other31 * _self10)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_other32, _self21, Math.fma(_other30, _self01, _other31 * _self11)));
        return _other33;
    }

    /** Part 2 of {@code preMulMat4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat4x4_unsafe_s18080933_2(long dest, float _self02, float _self03, float _self12, float _self13, float _self22, float _self23, float _other00, float _other10, float _other20, float _other30, float _other01, float _other11, float _other21, float _other31, float _other02, float _other12, float _other22, float _other32, float _other03, float _other13, float _other23, float _other33) {
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_other32, _self22, Math.fma(_other30, _self02, _other31 * _self12)));
        UnsafeOpsHolder.U.putFloat(dest + 48L, Math.fma(_other00, _self03, Math.fma(_other01, _self13, Math.fma(_other02, _self23, _other03))));
        UnsafeOpsHolder.U.putFloat(dest + 52L, Math.fma(_other10, _self03, Math.fma(_other11, _self13, Math.fma(_other12, _self23, _other13))));
        UnsafeOpsHolder.U.putFloat(dest + 56L, Math.fma(_other20, _self03, Math.fma(_other21, _self13, Math.fma(_other22, _self23, _other23))));
        UnsafeOpsHolder.U.putFloat(dest + 60L, Math.fma(_other30, _self03, Math.fma(_other31, _self13, Math.fma(_other32, _self23, _other33))));
        return dest;
    }

    /** Piece 2 of {@code preMulMat4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat4x4_unsafe_s18080933_3(long dest, long other, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _other00, float _other10, float _other20, float _other30, float _other01, float _other11, float _other21, float _other31) {
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 32L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 36L);
        float _other22 = UnsafeOpsHolder.U.getFloat(other + 40L);
        float _other32 = UnsafeOpsHolder.U.getFloat(other + 44L);
        float _other03 = UnsafeOpsHolder.U.getFloat(other + 48L);
        float _other13 = UnsafeOpsHolder.U.getFloat(other + 52L);
        float _other23 = UnsafeOpsHolder.U.getFloat(other + 56L);
        float _other33 = preMulMat4x4_unsafe_s18080933_1(dest, other, _self00, _self01, _self10, _self11, _self20, _self21, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32);
        return preMulMat4x4_unsafe_s18080933_2(dest, _self02, _self03, _self12, _self13, _self22, _self23, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32, _other03, _other13, _other23, _other33);
    }

    public static long addScaled_unsafe(long dest, long src, long other, float weight) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            float _eother = UnsafeOpsHolder.U.getFloat(other + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, Math.fma(weight, _eother, _eself));
        }
        return dest;
    }

    public static long composeTRS_unsafe(long dest, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _t0 = scaleX + scaleX;
        float _t1 = scaleY + scaleY;
        float _t2 = scaleZ + scaleZ;
        float _t3 = rotationZ * rotationZ;
        float _t4 = rotationZ * rotationW;
        float _t5 = rotationY * rotationW;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-Math.fma(rotationY, rotationY, _t3), _t0, scaleX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(rotationX, rotationY, -_t4) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(rotationX, rotationZ, _t5) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 12L, translationX);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(rotationX, rotationY, _t4) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-Math.fma(rotationX, rotationX, _t3), _t1, scaleY));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 28L, translationY);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(rotationX, rotationZ, -_t5) * _t0);
        return composeTRS_unsafe_s1d8b6c2c_1(dest, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleZ, _t1, _t2);
    }

    /** Piece 2 of {@code composeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRS_unsafe_s1d8b6c2c_1(long dest, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleZ, float _t1, float _t2) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t2, scaleZ));
        UnsafeOpsHolder.U.putFloat(dest + 44L, translationZ);
        return dest;
    }

    public static long composeTRS_unsafe(long dest, long translation, long rotation, long scale) {
        float _translationx = UnsafeOpsHolder.U.getFloat(translation);
        float _translationy = UnsafeOpsHolder.U.getFloat(translation + 4L);
        float _translationz = UnsafeOpsHolder.U.getFloat(translation + 8L);
        float _rotationx = UnsafeOpsHolder.U.getFloat(rotation);
        float _rotationy = UnsafeOpsHolder.U.getFloat(rotation + 4L);
        float _rotationz = UnsafeOpsHolder.U.getFloat(rotation + 8L);
        float _rotationw = UnsafeOpsHolder.U.getFloat(rotation + 12L);
        float _scalex = UnsafeOpsHolder.U.getFloat(scale);
        float _scaley = UnsafeOpsHolder.U.getFloat(scale + 4L);
        float _scalez = UnsafeOpsHolder.U.getFloat(scale + 8L);
        float _t0 = _scalex + _scalex;
        float _t1 = _scaley + _scaley;
        float _t2 = _scalez + _scalez;
        float _t3 = _rotationz * _rotationz;
        float _t4 = _rotationz * _rotationw;
        float _t5 = _rotationy * _rotationw;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-Math.fma(_rotationy, _rotationy, _t3), _t0, _scalex));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_rotationx, _rotationy, -_t4) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_rotationx, _rotationz, _t5) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _translationx);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_rotationx, _rotationy, _t4) * _t0);
        return composeTRS_unsafe_s7cb404ea_1(dest, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scaley, _scalez, _t0, _t1, _t2, _t3, _t5);
    }

    /** Piece 2 of {@code composeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRS_unsafe_s7cb404ea_1(long dest, float _translationy, float _translationz, float _rotationx, float _rotationy, float _rotationz, float _rotationw, float _scaley, float _scalez, float _t0, float _t1, float _t2, float _t3, float _t5) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-Math.fma(_rotationx, _rotationx, _t3), _t1, _scaley));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t2);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _translationy);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_rotationx, _rotationz, -_t5) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t2, _scalez));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _translationz);
        return dest;
    }

    public static long composeTRSAround_unsafe(long dest, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ, float pivotX, float pivotY, float pivotZ) {
        float _t0 = -pivotY;
        float _t1 = -pivotZ;
        float _t3 = scaleX + scaleX;
        float _t4 = scaleY + scaleY;
        float _t5 = scaleZ + scaleZ;
        float _t6 = rotationZ * rotationZ;
        float _t7 = rotationZ * rotationW;
        float _t8 = rotationY * rotationW;
        float _t15 = Math.fma(rotationY, rotationY, _t6);
        float _t24 = Math.fma(rotationX, rotationZ, _t8) * _t5;
        float _t25 = Math.fma(rotationX, rotationY, _t7) * _t3;
        float _t27 = Math.fma(rotationX, rotationY, -_t7) * _t4;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-_t15, _t3, scaleX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t27);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t24);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(pivotX, Math.fma(_t15, _t3, 1.0f - scaleX), Math.fma(_t0, _t27, Math.fma(_t1, _t24, translationX))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t25);
        return composeTRSAround_unsafe_sac4ca5c0_1(dest, translationY, translationZ, scaleY, scaleZ, pivotY, pivotZ, _t0, _t1, -pivotX, _t4, _t5, Math.fma(rotationX, rotationX, _t6), Math.fma(rotationX, rotationX, rotationY * rotationY), _t25, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t4, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t5, Math.fma(rotationX, rotationZ, -_t8) * _t3);
    }

    /** Piece 2 of {@code composeTRSAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSAround_unsafe_sac4ca5c0_1(long dest, float translationY, float translationZ, float scaleY, float scaleZ, float pivotY, float pivotZ, float _t0, float _t1, float _t2, float _t4, float _t5, float _t18, float _t20, float _t25, float _t26, float _t28, float _t29) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-_t18, _t4, scaleY));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t28);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(pivotY, Math.fma(_t18, _t4, 1.0f - scaleY), Math.fma(_t2, _t25, Math.fma(_t1, _t28, translationY))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t29);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t26);
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-_t20, _t5, scaleZ));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(pivotZ, Math.fma(_t20, _t5, 1.0f - scaleZ), Math.fma(_t2, _t29, Math.fma(_t0, _t26, translationZ))));
        return dest;
    }

    public static long composeTRSAround_unsafe(long dest, long translation, long rotation, long scale, long pivot) {
        float _translationx = UnsafeOpsHolder.U.getFloat(translation);
        float _translationy = UnsafeOpsHolder.U.getFloat(translation + 4L);
        float _translationz = UnsafeOpsHolder.U.getFloat(translation + 8L);
        float _rotationx = UnsafeOpsHolder.U.getFloat(rotation);
        float _rotationy = UnsafeOpsHolder.U.getFloat(rotation + 4L);
        float _rotationz = UnsafeOpsHolder.U.getFloat(rotation + 8L);
        float _rotationw = UnsafeOpsHolder.U.getFloat(rotation + 12L);
        float _scalex = UnsafeOpsHolder.U.getFloat(scale);
        float _scaley = UnsafeOpsHolder.U.getFloat(scale + 4L);
        float _scalez = UnsafeOpsHolder.U.getFloat(scale + 8L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(pivot + 8L);
        float _t3 = _scalex + _scalex;
        float _t5 = _scalez + _scalez;
        float _t6 = _rotationz * _rotationz;
        float _t7 = _rotationz * _rotationw;
        float _t8 = _rotationy * _rotationw;
        return composeTRSAround_unsafe_s92527211_1(dest, _translationx, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scalex, _scaley, _scalez, _pivotx, _pivoty, _pivotz, -_pivoty, -_pivotz, -_pivotx, _t3, _scaley + _scaley, _t5, _t7, _t8, Math.fma(_rotationy, _rotationy, _t6), Math.fma(_rotationx, _rotationx, _t6), Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), Math.fma(_rotationx, _rotationz, _t8) * _t5, Math.fma(_rotationx, _rotationy, _t7) * _t3);
    }

    /** Piece 2 of {@code composeTRSAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSAround_unsafe_s92527211_1(long dest, float _translationx, float _translationy, float _translationz, float _rotationx, float _rotationy, float _rotationz, float _rotationw, float _scalex, float _scaley, float _scalez, float _pivotx, float _pivoty, float _pivotz, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t7, float _t8, float _t15, float _t18, float _t20, float _t24, float _t25) {
        float _t26 = Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t4;
        float _t27 = Math.fma(_rotationx, _rotationy, -_t7) * _t4;
        float _t28 = Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t5;
        float _t29 = Math.fma(_rotationx, _rotationz, -_t8) * _t3;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-_t15, _t3, _scalex));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t27);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t24);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_pivotx, Math.fma(_t15, _t3, 1.0f - _scalex), Math.fma(_t0, _t27, Math.fma(_t1, _t24, _translationx))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t25);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-_t18, _t4, _scaley));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t28);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_pivoty, Math.fma(_t18, _t4, 1.0f - _scaley), Math.fma(_t2, _t25, Math.fma(_t1, _t28, _translationy))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t29);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t26);
        return composeTRSAround_unsafe_s92527211_2(dest, _translationz, _scalez, _pivotz, _t0, _t2, _t5, _t20, _t26, _t29);
    }

    /** Piece 3 of {@code composeTRSAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSAround_unsafe_s92527211_2(long dest, float _translationz, float _scalez, float _pivotz, float _t0, float _t2, float _t5, float _t20, float _t26, float _t29) {
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-_t20, _t5, _scalez));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_pivotz, Math.fma(_t20, _t5, 1.0f - _scalez), Math.fma(_t2, _t29, Math.fma(_t0, _t26, _translationz))));
        return dest;
    }

    public static long composeTRSMul_unsafe(long dest, long m, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m02 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m03 = UnsafeOpsHolder.U.getFloat(m + 12L);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 20L);
        float _m12 = UnsafeOpsHolder.U.getFloat(m + 24L);
        float _m13 = UnsafeOpsHolder.U.getFloat(m + 28L);
        float _m20 = UnsafeOpsHolder.U.getFloat(m + 32L);
        float _m21 = UnsafeOpsHolder.U.getFloat(m + 36L);
        float _m22 = UnsafeOpsHolder.U.getFloat(m + 40L);
        float _m23 = UnsafeOpsHolder.U.getFloat(m + 44L);
        float _t0 = scaleZ + scaleZ;
        float _t1 = scaleX + scaleX;
        float _t2 = scaleY + scaleY;
        float _t3 = rotationY * rotationW;
        float _t5 = rotationZ * rotationW;
        return composeTRSMul_unsafe_sd0f40777_1(dest, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t0, _t1, _t2, _t3, rotationZ * rotationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationY, -_t5) * _t2);
    }

    /** Piece 2 of {@code composeTRSMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSMul_unsafe_sd0f40777_1(long dest, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t0, float _t1, float _t2, float _t3, float _t4, float _t24, float _t25, float _t26, float _t27) {
        float _t28 = Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0;
        float _t30 = Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX);
        float _t31 = Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, translationX))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31)));
        return composeTRSMul_unsafe_sd0f40777_2(dest, translationY, translationZ, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t25, _t26, _t28, Math.fma(rotationX, rotationZ, -_t3) * _t1, _t31, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
    }

    /** Piece 3 of {@code composeTRSMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSMul_unsafe_sd0f40777_2(long dest, float translationY, float translationZ, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t25, float _t26, float _t28, float _t29, float _t31, float _t32) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, translationY))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, translationZ))));
        return dest;
    }

    public static long composeTRSMul_unsafe(long dest, long translation, long rotation, long scale, long m) {
        float _translationx = UnsafeOpsHolder.U.getFloat(translation);
        float _translationy = UnsafeOpsHolder.U.getFloat(translation + 4L);
        float _translationz = UnsafeOpsHolder.U.getFloat(translation + 8L);
        float _rotationx = UnsafeOpsHolder.U.getFloat(rotation);
        float _rotationy = UnsafeOpsHolder.U.getFloat(rotation + 4L);
        float _rotationz = UnsafeOpsHolder.U.getFloat(rotation + 8L);
        float _rotationw = UnsafeOpsHolder.U.getFloat(rotation + 12L);
        float _scalex = UnsafeOpsHolder.U.getFloat(scale);
        float _scaley = UnsafeOpsHolder.U.getFloat(scale + 4L);
        float _scalez = UnsafeOpsHolder.U.getFloat(scale + 8L);
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m02 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m03 = UnsafeOpsHolder.U.getFloat(m + 12L);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 20L);
        float _m12 = UnsafeOpsHolder.U.getFloat(m + 24L);
        float _m13 = UnsafeOpsHolder.U.getFloat(m + 28L);
        float _m20 = UnsafeOpsHolder.U.getFloat(m + 32L);
        float _m21 = UnsafeOpsHolder.U.getFloat(m + 36L);
        return composeTRSMul_unsafe_s3c1255f5_3(dest, m, _translationx, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scalex, _scaley, _scalez, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21);
    }

    /** Part 1 of {@code composeTRSMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static float composeTRSMul_unsafe_s3c1255f5_1(long dest, float _translationx, float _translationy, float _rotationx, float _rotationy, float _scalex, float _scaley, float _scalez, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t0, float _t1, float _t2, float _t4, float _t24, float _t25, float _t27, float _t28) {
        float _t30 = Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex);
        float _t31 = Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, _translationx))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, _translationy))));
        return Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez);
    }

    /** Part 2 of {@code composeTRSMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSMul_unsafe_s3c1255f5_2(long dest, float _translationz, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t26, float _t29, float _t32) {
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, _translationz))));
        return dest;
    }

    /** Piece 2 of {@code composeTRSMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSMul_unsafe_s3c1255f5_3(long dest, long m, float _translationx, float _translationy, float _translationz, float _rotationx, float _rotationy, float _rotationz, float _rotationw, float _scalex, float _scaley, float _scalez, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21) {
        float _m22 = UnsafeOpsHolder.U.getFloat(m + 40L);
        float _m23 = UnsafeOpsHolder.U.getFloat(m + 44L);
        float _t0 = _scalez + _scalez;
        float _t1 = _scalex + _scalex;
        float _t2 = _scaley + _scaley;
        float _t3 = _rotationy * _rotationw;
        float _t5 = _rotationz * _rotationw;
        return composeTRSMul_unsafe_s3c1255f5_2(dest, _translationz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, (Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2), (Math.fma(_rotationx, _rotationz, -_t3) * _t1), composeTRSMul_unsafe_s3c1255f5_1(dest, _translationx, _translationy, _rotationx, _rotationy, _scalex, _scaley, _scalez, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t0, _t1, _t2, (_rotationz * _rotationz), (Math.fma(_rotationx, _rotationz, _t3) * _t0), (Math.fma(_rotationx, _rotationy, _t5) * _t1), (Math.fma(_rotationx, _rotationy, -_t5) * _t2), (Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0)));
    }

    public static long lookAlong_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
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
        return lookAlong_unsafe_s93c4579a_1(dest, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t17, Math.fma(_t17, _t12, upX), Math.fma(_t17, _t13, upY));
    }

    /** Piece 2 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s93c4579a_1(long dest, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t17, float _t18, float _t19) {
        float _t20 = Math.fma(_t17, _t11, upZ);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
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
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        return lookAlong_unsafe_s93c4579a_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s93c4579a_2(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long lookAlong_unsafe(long dest, long src, long dir, long up) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        return lookAlong_unsafe_s6c6e4e50_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _dirx, _diry, _dirz, _upx, _upy, _upz, _t4, (1.0f / (float) java.lang.Math.sqrt(_t4)));
    }

    /** Piece 2 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s6c6e4e50_1(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _dirx, float _diry, float _dirz, float _upx, float _upy, float _upz, float _t4, float _t6) {
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
        return lookAlong_unsafe_s6c6e4e50_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, Math.fma(_t39, _t12, -(_t38 * _t13)), Math.fma(_t37, _t13, -(_t39 * _t11)));
    }

    /** Piece 3 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s6c6e4e50_2(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39, float _t46, float _t47) {
        float _t48 = Math.fma(_t38, _t11, -(_t37 * _t12));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        return lookAlong_unsafe_s6c6e4e50_3(dest, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 4 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s6c6e4e50_3(long dest, float _self20, float _self21, float _self22, float _self23, float _t11, float _t12, float _t13, float _t46, float _t47, float _t48) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long lookAt_lh(long dest, long src, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.lookAt_lh_unsafe(dest, src, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAt_lh_unsafe(long dest, long src, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        return lookAt_lh_unsafe_sa3c671e1_1(dest, eyeX, eyeY, eyeZ, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16);
    }

    /** Piece 2 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_sa3c671e1_1(long dest, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16) {
        float _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        float _t24 = Math.fma(_t23, _t14, upX);
        float _t25 = Math.fma(_t23, _t16, upY);
        float _t26 = Math.fma(_t23, _t15, upZ);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        return lookAt_lh_unsafe_sa3c671e1_2(dest, eyeX, eyeY, eyeZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, Math.fma(_t44, _t16, -(_t45 * _t15)), Math.fma(_t43, _t15, -(_t44 * _t14)), Math.fma(_t45, _t14, -(_t43 * _t16)));
    }

    /** Piece 3 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_sa3c671e1_2(long dest, float eyeX, float eyeY, float eyeZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56) {
        float _t58 = Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45));
        float _t60 = Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        return lookAt_lh_unsafe_sa3c671e1_3(dest, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, _t58, _t60);
    }

    /** Piece 4 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_sa3c671e1_3(long dest, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t58, float _t60) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    public static long lookAt_rh(long dest, long src, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.lookAt_rh_unsafe(dest, src, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAt_rh_unsafe(long dest, long src, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        return lookAt_rh_unsafe_sa4e33503_1(dest, eyeX, eyeY, eyeZ, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self02, -_self12, -_self22, _t17, _t18, _t19);
    }

    /** Piece 2 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_sa4e33503_1(long dest, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19) {
        float _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        float _t27 = Math.fma(_t26, _t19, upY);
        float _t28 = Math.fma(_t26, _t17, upX);
        float _t29 = Math.fma(_t26, _t18, upZ);
        float _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        return lookAt_rh_unsafe_sa4e33503_2(dest, eyeX, eyeY, eyeZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)), _t46, _t47, _t48, Math.fma(_t47, _t18, -(_t48 * _t19)), Math.fma(_t48, _t17, -(_t46 * _t18)), Math.fma(_t46, _t19, -(_t47 * _t17)));
    }

    /** Piece 3 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_sa4e33503_2(long dest, float eyeX, float eyeY, float eyeZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t17, float _t18, float _t19, float _t25, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59) {
        float _t61 = Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47));
        float _t63 = Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59)));
        return lookAt_rh_unsafe_sa4e33503_3(dest, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, _t61, _t63);
    }

    /** Piece 4 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_sa4e33503_3(long dest, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t2, float _t17, float _t18, float _t19, float _t25, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23))));
        return dest;
    }

    public static long lookAt_lh(long dest, long src, long eye, long center, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.lookAt_lh_unsafe(dest, src, eye, center, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAt_lh_unsafe(long dest, long src, long eye, long center, long up) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _eyex = UnsafeOpsHolder.U.getFloat(eye);
        float _eyey = UnsafeOpsHolder.U.getFloat(eye + 4L);
        float _eyez = UnsafeOpsHolder.U.getFloat(eye + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        return lookAt_lh_unsafe_sa70b22ec_1(dest, center, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _upx, _upy, _upz);
    }

    /** Piece 2 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_sa70b22ec_1(long dest, long center, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz) {
        float _t0 = UnsafeOpsHolder.U.getFloat(center + 8L) - _eyez;
        float _t1 = UnsafeOpsHolder.U.getFloat(center) - _eyex;
        float _t2 = UnsafeOpsHolder.U.getFloat(center + 4L) - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        float _t24 = Math.fma(_t23, _t14, _upx);
        float _t25 = Math.fma(_t23, _t16, _upy);
        float _t26 = Math.fma(_t23, _t15, _upz);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        return lookAt_lh_unsafe_sa70b22ec_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t33, _t34, _t35, Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35)));
    }

    /** Piece 3 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_sa70b22ec_2(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t22, float _t33, float _t34, float _t35, float _t38) {
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        float _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        float _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        return lookAt_lh_unsafe_sa70b22ec_3(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)), Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
    }

    /** Piece 4 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_sa70b22ec_3(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t58, float _t60) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    public static long lookAt_rh(long dest, long src, long eye, long center, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.lookAt_rh_unsafe(dest, src, eye, center, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAt_rh_unsafe(long dest, long src, long eye, long center, long up) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _eyex = UnsafeOpsHolder.U.getFloat(eye);
        float _eyey = UnsafeOpsHolder.U.getFloat(eye + 4L);
        float _eyez = UnsafeOpsHolder.U.getFloat(eye + 8L);
        float _t3 = UnsafeOpsHolder.U.getFloat(center + 8L) - _eyez;
        float _t4 = UnsafeOpsHolder.U.getFloat(center) - _eyex;
        float _t5 = UnsafeOpsHolder.U.getFloat(center + 4L) - _eyey;
        return lookAt_rh_unsafe_s19ae3a2e_3(dest, up, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _t3, _t4, _t5, Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5)));
    }

    /** Part 1 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static float lookAt_rh_unsafe_s19ae3a2e_1(long dest, float _self00, float _self01, float _self02, float _self03, float _eyex, float _eyey, float _eyez, float _t19, float _t17, float _t18, float _t25, float _t46, float _t47, float _t48, float _t61) {
        float _t0 = -_self02;
        float _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        float _t63 = Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03))));
        return _t63;
    }

    /** Part 2 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_s19ae3a2e_2(long dest, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t17, float _t18, float _t19, float _t25, float _t46, float _t47, float _t48, float _t61, float _t63) {
        float _t1 = -_self12;
        float _t2 = -_self22;
        float _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23))));
        return dest;
    }

    /** Piece 2 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_s19ae3a2e_3(long dest, long up, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _eyex, float _eyey, float _eyez, float _t3, float _t4, float _t5, float _t12) {
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        float _t27 = Math.fma(_t26, _t19, _upy);
        float _t28 = Math.fma(_t26, _t17, _upx);
        float _t29 = Math.fma(_t26, _t18, _upz);
        float _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        return lookAt_rh_unsafe_s19ae3a2e_4(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _t17, _t18, _t19, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)), _upx, _upy, _upz, _t36, _t37, _t38, _t41, (1.0f / (float) java.lang.Math.sqrt(_t41)));
    }

    /** Piece 3 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_s19ae3a2e_4(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _eyex, float _eyey, float _eyez, float _t17, float _t18, float _t19, float _t25, float _upx, float _upy, float _upz, float _t36, float _t37, float _t38, float _t41, float _t42) {
        float _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t61 = Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47));
        return lookAt_rh_unsafe_s19ae3a2e_2(dest, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t61, lookAt_rh_unsafe_s19ae3a2e_1(dest, _self00, _self01, _self02, _self03, _eyex, _eyey, _eyez, _t19, _t17, _t18, _t25, _t46, _t47, _t48, _t61));
    }

    public static long makeBillboardCylindrical_unsafe(long dest, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
        float _t3 = targetPosZ - objPosZ;
        float _t4 = targetPosX - objPosX;
        float _t5 = targetPosY - objPosY;
        float _t14 = Math.fma(upZ, _t3, Math.fma(upX, _t4, upY * _t5));
        float _t15 = Math.fma(-upY, _t14, _t5);
        float _t16 = Math.fma(-upX, _t14, _t4);
        float _t17 = Math.fma(-upZ, _t14, _t3);
        float _t26 = Math.fma(upX, _t15, -(upY * _t16));
        float _t27 = Math.fma(upY, _t17, -(upZ * _t15));
        float _t28 = Math.fma(upZ, _t16, -(upX * _t17));
        float _t31 = Math.fma(_t26, _t26, Math.fma(_t27, _t27, _t28 * _t28));
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(_t31));
        float _t36, _t37, _t38;
        if (_t31 > Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5)) * Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f) {
            _t36 = _t27 * _t32;
            _t37 = _t28 * _t32;
            _t38 = _t26 * _t32;
        } else {
            _t36 = 0.0f;
            _t37 = 0.0f;
            _t38 = 0.0f;
        }
        return makeBillboardCylindrical_unsafe_s8ca6a70f_1(dest, objPosX, objPosY, objPosZ, upX, upY, upZ, _t36, _t37, _t38, Math.fma(upY, _t36, -(upX * _t37)), Math.fma(upX, _t38, -(upZ * _t36)), Math.fma(upZ, _t37, -(upY * _t38)));
    }

    /** Piece 2 of {@code makeBillboardCylindrical_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardCylindrical_unsafe_s8ca6a70f_1(long dest, float objPosX, float objPosY, float objPosZ, float upX, float upY, float upZ, float _t36, float _t37, float _t38, float _t45, float _t46, float _t47) {
        float _t50 = Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t47 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 24L, _t46 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 40L, _t45 * _t51);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        }
        UnsafeOpsHolder.U.putFloat(dest, _t36);
        UnsafeOpsHolder.U.putFloat(dest + 4L, upX);
        UnsafeOpsHolder.U.putFloat(dest + 12L, objPosX);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 20L, upY);
        UnsafeOpsHolder.U.putFloat(dest + 28L, objPosY);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 36L, upZ);
        UnsafeOpsHolder.U.putFloat(dest + 44L, objPosZ);
        return dest;
    }

    public static long makeBillboardCylindrical_unsafe(long dest, long objPos, long targetPos, long up) {
        float _objPosx = UnsafeOpsHolder.U.getFloat(objPos);
        float _objPosy = UnsafeOpsHolder.U.getFloat(objPos + 4L);
        float _objPosz = UnsafeOpsHolder.U.getFloat(objPos + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t3 = UnsafeOpsHolder.U.getFloat(targetPos + 8L) - _objPosz;
        float _t4 = UnsafeOpsHolder.U.getFloat(targetPos) - _objPosx;
        float _t5 = UnsafeOpsHolder.U.getFloat(targetPos + 4L) - _objPosy;
        float _t14 = Math.fma(_upz, _t3, Math.fma(_upx, _t4, _upy * _t5));
        float _t15 = Math.fma(-_upy, _t14, _t5);
        float _t16 = Math.fma(-_upx, _t14, _t4);
        float _t17 = Math.fma(-_upz, _t14, _t3);
        float _t26 = Math.fma(_upx, _t15, -(_upy * _t16));
        float _t27 = Math.fma(_upy, _t17, -(_upz * _t15));
        float _t28 = Math.fma(_upz, _t16, -(_upx * _t17));
        float _t31 = Math.fma(_t26, _t26, Math.fma(_t27, _t27, _t28 * _t28));
        return makeBillboardCylindrical_unsafe_s21740328_1(dest, _objPosx, _objPosy, _objPosz, _upx, _upy, _upz, _t3, _t4, _t5, _t26, _t27, _t28, _t31, (1.0f / (float) java.lang.Math.sqrt(_t31)));
    }

    /** Piece 2 of {@code makeBillboardCylindrical_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardCylindrical_unsafe_s21740328_1(long dest, float _objPosx, float _objPosy, float _objPosz, float _upx, float _upy, float _upz, float _t3, float _t4, float _t5, float _t26, float _t27, float _t28, float _t31, float _t32) {
        float _t36, _t37, _t38;
        if (_t31 > Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5)) * Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)) * 1.4551915E-11f) {
            _t36 = _t27 * _t32;
            _t37 = _t28 * _t32;
            _t38 = _t26 * _t32;
        } else {
            _t36 = 0.0f;
            _t37 = 0.0f;
            _t38 = 0.0f;
        }
        float _t45 = Math.fma(_upy, _t36, -(_upx * _t37));
        float _t46 = Math.fma(_upx, _t38, -(_upz * _t36));
        float _t47 = Math.fma(_upz, _t37, -(_upy * _t38));
        float _t50 = Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t47 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 24L, _t46 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 40L, _t45 * _t51);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        }
        UnsafeOpsHolder.U.putFloat(dest, _t36);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _upx);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _objPosx);
        return makeBillboardCylindrical_unsafe_s21740328_2(dest, _objPosy, _objPosz, _upy, _upz, _t37, _t38);
    }

    /** Piece 3 of {@code makeBillboardCylindrical_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardCylindrical_unsafe_s21740328_2(long dest, float _objPosy, float _objPosz, float _upy, float _upz, float _t37, float _t38) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t37);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _upy);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _objPosy);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _upz);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _objPosz);
        return dest;
    }

    public static long makeBillboardSpherical_unsafe(long dest, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
        float _t0 = targetPosZ - objPosZ;
        float _t1 = targetPosX - objPosX;
        float _t2 = targetPosY - objPosY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -Math.fma(upZ, _t14, Math.fma(upX, _t15, upY * _t16));
        float _t21 = Math.fma(_t20, _t15, upX);
        float _t22 = Math.fma(_t20, _t16, upY);
        float _t23 = Math.fma(_t20, _t14, upZ);
        float _t30 = Math.fma(_t21, _t16, -(_t22 * _t15));
        float _t31 = Math.fma(_t22, _t14, -(_t23 * _t16));
        float _t32 = Math.fma(_t23, _t15, -(_t21 * _t14));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeBillboardSpherical_unsafe_s6881e6f6_1(dest, objPosX, objPosY, objPosZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeBillboardSpherical_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardSpherical_unsafe_s6881e6f6_1(long dest, float objPosX, float objPosY, float objPosZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t31 * _t36;
            _t41 = _t30 * _t36;
            _t42 = _t32 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(dest, _t40);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t41, _t16, -(_t42 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t15);
        UnsafeOpsHolder.U.putFloat(dest + 12L, objPosX);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t42);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t40, _t14, -(_t41 * _t15)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t16);
        UnsafeOpsHolder.U.putFloat(dest + 28L, objPosY);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t41);
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_t42, _t15, -(_t40 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t14);
        UnsafeOpsHolder.U.putFloat(dest + 44L, objPosZ);
        return dest;
    }

    public static long makeBillboardSpherical_unsafe(long dest, long objPos, long targetPos, long up) {
        float _objPosx = UnsafeOpsHolder.U.getFloat(objPos);
        float _objPosy = UnsafeOpsHolder.U.getFloat(objPos + 4L);
        float _objPosz = UnsafeOpsHolder.U.getFloat(objPos + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t0 = UnsafeOpsHolder.U.getFloat(targetPos + 8L) - _objPosz;
        float _t1 = UnsafeOpsHolder.U.getFloat(targetPos) - _objPosx;
        float _t2 = UnsafeOpsHolder.U.getFloat(targetPos + 4L) - _objPosy;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -Math.fma(_upz, _t14, Math.fma(_upx, _t15, _upy * _t16));
        float _t21 = Math.fma(_t20, _t15, _upx);
        float _t22 = Math.fma(_t20, _t16, _upy);
        float _t23 = Math.fma(_t20, _t14, _upz);
        return makeBillboardSpherical_unsafe_sc497799_1(dest, _objPosx, _objPosy, _objPosz, _upx, _upy, _upz, _t14, _t15, _t16, _t21, _t23, Math.fma(_t21, _t16, -(_t22 * _t15)), Math.fma(_t22, _t14, -(_t23 * _t16)));
    }

    /** Piece 2 of {@code makeBillboardSpherical_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardSpherical_unsafe_sc497799_1(long dest, float _objPosx, float _objPosy, float _objPosz, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t21, float _t23, float _t30, float _t31) {
        float _t32 = Math.fma(_t23, _t15, -(_t21 * _t14));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t31 * _t36;
            _t41 = _t30 * _t36;
            _t42 = _t32 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(dest, _t40);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t41, _t16, -(_t42 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t15);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _objPosx);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t42);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t40, _t14, -(_t41 * _t15)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t16);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _objPosy);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t41);
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_t42, _t15, -(_t40 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t14);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _objPosz);
        return dest;
    }

    public static long makeBillboardSphericalShortest_unsafe(long dest, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ) {
        float _t0 = targetPosZ - objPosZ;
        float _t1 = targetPosX - objPosX;
        float _t2 = targetPosY - objPosY;
        float _t3 = _t2 + _t2;
        float _t6 = Math.fma(_t1, _t1, _t2 * _t2);
        float _t9 = (float) java.lang.Math.sqrt(java.lang.Math.max(Math.fma(_t0, _t0, _t6), 4.7019774E-38f));
        float _t11 = _t0 + _t9;
        float _t12 = Math.fma(_t11, _t11, _t6);
        float _t14 = _t12 / _t9;
        float _t15 = _t12 > 1.1754944E-38f ? _t1 : _t9;
        float _t25_inv = 1.0f / Math.fma(0.25f, _t14 * _t14, Math.fma(_t2, _t2, _t15 * _t15));
        float _sp1 = _t2 * _t25_inv;
        float _sp0 = _t15 * _t25_inv;
        float _t26 = _sp1 * _t3;
        float _t27 = _sp1 * _t14;
        float _t29 = -(_t3 * _sp0);
        float _t30 = _sp0 * _t14;
        float _t32 = 1.0f - (_sp0 + _sp0) * _t15;
        UnsafeOpsHolder.U.putFloat(dest, _t32);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t29);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t30);
        UnsafeOpsHolder.U.putFloat(dest + 12L, objPosX);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t29);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f - _t26);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t27);
        UnsafeOpsHolder.U.putFloat(dest + 28L, objPosY);
        return makeBillboardSphericalShortest_unsafe_sfacfecc8_1(dest, objPosZ, _t26, _t27, _t30, _t32);
    }

    /** Piece 2 of {@code makeBillboardSphericalShortest_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardSphericalShortest_unsafe_sfacfecc8_1(long dest, float objPosZ, float _t26, float _t27, float _t30, float _t32) {
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t30);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -_t27);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t32 - _t26);
        UnsafeOpsHolder.U.putFloat(dest + 44L, objPosZ);
        return dest;
    }

    public static long makeBillboardSphericalShortest_unsafe(long dest, long objPos, long targetPos) {
        float _objPosx = UnsafeOpsHolder.U.getFloat(objPos);
        float _objPosy = UnsafeOpsHolder.U.getFloat(objPos + 4L);
        float _objPosz = UnsafeOpsHolder.U.getFloat(objPos + 8L);
        float _t0 = UnsafeOpsHolder.U.getFloat(targetPos + 8L) - _objPosz;
        float _t1 = UnsafeOpsHolder.U.getFloat(targetPos) - _objPosx;
        float _t2 = UnsafeOpsHolder.U.getFloat(targetPos + 4L) - _objPosy;
        float _t3 = _t2 + _t2;
        float _t6 = Math.fma(_t1, _t1, _t2 * _t2);
        float _t9 = (float) java.lang.Math.sqrt(java.lang.Math.max(Math.fma(_t0, _t0, _t6), 4.7019774E-38f));
        float _t11 = _t0 + _t9;
        float _t12 = Math.fma(_t11, _t11, _t6);
        float _t14 = _t12 / _t9;
        float _t15 = _t12 > 1.1754944E-38f ? _t1 : _t9;
        float _t25_inv = 1.0f / Math.fma(0.25f, _t14 * _t14, Math.fma(_t2, _t2, _t15 * _t15));
        float _sp1 = _t2 * _t25_inv;
        float _sp0 = _t15 * _t25_inv;
        float _t29 = -(_t3 * _sp0);
        float _t30 = _sp0 * _t14;
        float _t32 = 1.0f - (_sp0 + _sp0) * _t15;
        UnsafeOpsHolder.U.putFloat(dest, _t32);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t29);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t30);
        return makeBillboardSphericalShortest_unsafe_s38c4d0a4_1(dest, _objPosx, _objPosy, _objPosz, _sp1 * _t3, _sp1 * _t14, _t29, _t30, _t32);
    }

    /** Piece 2 of {@code makeBillboardSphericalShortest_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardSphericalShortest_unsafe_s38c4d0a4_1(long dest, float _objPosx, float _objPosy, float _objPosz, float _t26, float _t27, float _t29, float _t30, float _t32) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, _objPosx);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t29);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f - _t26);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t27);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _objPosy);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t30);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -_t27);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t32 - _t26);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _objPosz);
        return dest;
    }

    public static long makeFromDualQuat_unsafe(long dest, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = Math.fma(-2.0f, dqRZ * dqRZ, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, _t0, _t6));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-2.0f, _t2, _sp0 * dqRY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(dqRX, dqRZ, _t3));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, 2.0f * Math.fma(dqRX, dqRY, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-2.0f, _t4, _t6));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(-2.0f, _t3, _sp0 * dqRZ));
        return makeFromDualQuat_unsafe_sfc0cc79b_1(dest, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW, _t0, _t4, _t5);
    }

    /** Piece 2 of {@code makeFromDualQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeFromDualQuat_unsafe_sfc0cc79b_1(long dest, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW, float _t0, float _t4, float _t5) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, 2.0f * Math.fma(dqRX, dqRW, _t5));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 2.0f * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))));
        return dest;
    }

    public static long makeLookAt_lh(long dest, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.makeLookAt_lh_unsafe(dest, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeLookAt_lh_unsafe(long dest, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -Math.fma(upZ, _t14, Math.fma(upX, _t15, upY * _t16));
        float _t21 = Math.fma(_t20, _t15, upX);
        float _t22 = Math.fma(_t20, _t16, upY);
        float _t23 = Math.fma(_t20, _t14, upZ);
        float _t30 = Math.fma(_t21, _t16, -(_t22 * _t15));
        float _t31 = Math.fma(_t22, _t14, -(_t23 * _t16));
        float _t32 = Math.fma(_t23, _t15, -(_t21 * _t14));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_lh_unsafe_se7b4067_1(dest, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_lh_unsafe_se7b4067_1(long dest, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = Math.fma(_t42, _t16, -(_t41 * _t14));
        float _t50 = Math.fma(_t40, _t14, -(_t42 * _t15));
        float _t51 = Math.fma(_t41, _t15, -(_t40 * _t16));
        UnsafeOpsHolder.U.putFloat(dest, _t40);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t41);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t42);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t49);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t50);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t51);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t15);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t16);
        return makeLookAt_lh_unsafe_se7b4067_2(dest, eyeX, eyeY, eyeZ, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_lh_unsafe_se7b4067_2(long dest, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16) {
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t14);
        UnsafeOpsHolder.U.putFloat(dest + 44L, -Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16)));
        return dest;
    }

    public static long makeLookAt_rh(long dest, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.makeLookAt_rh_unsafe(dest, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeLookAt_rh_unsafe(long dest, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -Math.fma(upZ, _t14, Math.fma(upX, _t15, upY * _t16));
        float _t21 = Math.fma(_t20, _t16, upY);
        float _t22 = Math.fma(_t20, _t15, upX);
        float _t23 = Math.fma(_t20, _t14, upZ);
        float _t30 = Math.fma(_t21, _t15, -(_t22 * _t16));
        float _t31 = Math.fma(_t22, _t14, -(_t23 * _t15));
        float _t32 = Math.fma(_t23, _t16, -(_t21 * _t14));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_rh_unsafe_sa5058a9d_1(dest, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_rh_unsafe_sa5058a9d_1(long dest, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = Math.fma(_t41, _t14, -(_t42 * _t16));
        float _t50 = Math.fma(_t42, _t15, -(_t40 * _t14));
        float _t51 = Math.fma(_t40, _t16, -(_t41 * _t15));
        UnsafeOpsHolder.U.putFloat(dest, _t40);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t41);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t42);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t49);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t50);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t51);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t15);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -_t16);
        return makeLookAt_rh_unsafe_sa5058a9d_2(dest, eyeX, eyeY, eyeZ, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_rh_unsafe_sa5058a9d_2(long dest, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16) {
        UnsafeOpsHolder.U.putFloat(dest + 40L, -_t14);
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16)));
        return dest;
    }

    public static long makeLookAt_lh(long dest, long eye, long center, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.makeLookAt_lh_unsafe(dest, eye, center, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeLookAt_lh_unsafe(long dest, long eye, long center, long up) {
        float _eyex = UnsafeOpsHolder.U.getFloat(eye);
        float _eyey = UnsafeOpsHolder.U.getFloat(eye + 4L);
        float _eyez = UnsafeOpsHolder.U.getFloat(eye + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t0 = UnsafeOpsHolder.U.getFloat(center + 8L) - _eyez;
        float _t1 = UnsafeOpsHolder.U.getFloat(center) - _eyex;
        float _t2 = UnsafeOpsHolder.U.getFloat(center + 4L) - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -Math.fma(_upz, _t14, Math.fma(_upx, _t15, _upy * _t16));
        float _t21 = Math.fma(_t20, _t15, _upx);
        float _t22 = Math.fma(_t20, _t16, _upy);
        float _t23 = Math.fma(_t20, _t14, _upz);
        return makeLookAt_lh_unsafe_sff827952_1(dest, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, _t21, _t23, Math.fma(_t21, _t16, -(_t22 * _t15)), Math.fma(_t22, _t14, -(_t23 * _t16)));
    }

    /** Piece 2 of {@code makeLookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_lh_unsafe_sff827952_1(long dest, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t21, float _t23, float _t30, float _t31) {
        float _t32 = Math.fma(_t23, _t15, -(_t21 * _t14));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = Math.fma(_t42, _t16, -(_t41 * _t14));
        float _t50 = Math.fma(_t40, _t14, -(_t42 * _t15));
        float _t51 = Math.fma(_t41, _t15, -(_t40 * _t16));
        UnsafeOpsHolder.U.putFloat(dest, _t40);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t41);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t42);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t49);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t50);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t51);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        return makeLookAt_lh_unsafe_sff827952_2(dest, _eyex, _eyey, _eyez, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_lh_unsafe_sff827952_2(long dest, float _eyex, float _eyey, float _eyez, float _t14, float _t15, float _t16) {
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t15);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t16);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t14);
        UnsafeOpsHolder.U.putFloat(dest + 44L, -Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static long makeLookAt_rh(long dest, long eye, long center, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3x4OpsKernelsAddress.makeLookAt_rh_unsafe(dest, eye, center, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeLookAt_rh_unsafe(long dest, long eye, long center, long up) {
        float _eyex = UnsafeOpsHolder.U.getFloat(eye);
        float _eyey = UnsafeOpsHolder.U.getFloat(eye + 4L);
        float _eyez = UnsafeOpsHolder.U.getFloat(eye + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t0 = UnsafeOpsHolder.U.getFloat(center + 8L) - _eyez;
        float _t1 = UnsafeOpsHolder.U.getFloat(center) - _eyex;
        float _t2 = UnsafeOpsHolder.U.getFloat(center + 4L) - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -Math.fma(_upz, _t14, Math.fma(_upx, _t15, _upy * _t16));
        float _t21 = Math.fma(_t20, _t16, _upy);
        float _t22 = Math.fma(_t20, _t15, _upx);
        float _t23 = Math.fma(_t20, _t14, _upz);
        return makeLookAt_rh_unsafe_sc2aff5a8_1(dest, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, _t21, _t23, Math.fma(_t21, _t15, -(_t22 * _t16)), Math.fma(_t22, _t14, -(_t23 * _t15)));
    }

    /** Piece 2 of {@code makeLookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_rh_unsafe_sc2aff5a8_1(long dest, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t21, float _t23, float _t30, float _t31) {
        float _t32 = Math.fma(_t23, _t16, -(_t21 * _t14));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = Math.fma(_t41, _t14, -(_t42 * _t16));
        float _t50 = Math.fma(_t42, _t15, -(_t40 * _t14));
        float _t51 = Math.fma(_t40, _t16, -(_t41 * _t15));
        UnsafeOpsHolder.U.putFloat(dest, _t40);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t41);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t42);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t49);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t50);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t51);
        UnsafeOpsHolder.U.putFloat(dest + 28L, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        return makeLookAt_rh_unsafe_sc2aff5a8_2(dest, _eyex, _eyey, _eyez, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_rh_unsafe_sc2aff5a8_2(long dest, float _eyex, float _eyey, float _eyez, float _t14, float _t15, float _t16) {
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t15);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -_t16);
        UnsafeOpsHolder.U.putFloat(dest + 40L, -_t14);
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static long makeMappingXYZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingXYnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingXZY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingXZnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingXnYZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingXnYnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingXnZY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingXnZnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingYXZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingYXnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingYZX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingYZnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingYnXZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingYnXnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingYnZX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingYnZnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingZXY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingZXnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingZYX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingZYnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingZnXY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingZnXnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingZnYX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingZnYnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnXYZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnXYnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnXZY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnXZnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnXnYZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnXnYnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnXnZY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnXnZnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnYXZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnYXnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnYZX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnYZnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnYnXZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnYnXnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnYnZX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnYnZnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnZXY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnZXnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnZYX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnZYnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnZnXY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnZnXnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnZnYX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeMappingnZnYnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeReflection_unsafe(long dest, float normalX, float normalY, float normalZ) {
        float _sp0 = normalX + normalX;
        float _t6 = -(_sp0 * normalY);
        float _t7 = -(_sp0 * normalZ);
        float _t8 = -((normalY + normalY) * normalZ);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, normalX * normalX, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t6);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t7);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t6);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-2.0f, normalY * normalY, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t8);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t7);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t8);
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-2.0f, normalZ * normalZ, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeReflection_unsafe(long dest, long normal) {
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _sp0 = _normalx + _normalx;
        float _t6 = -(_sp0 * _normaly);
        float _t7 = -(_sp0 * _normalz);
        float _t8 = -((_normaly + _normaly) * _normalz);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, _normalx * _normalx, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t6);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t7);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t6);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-2.0f, _normaly * _normaly, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t8);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t7);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t8);
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-2.0f, _normalz * _normalz, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
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
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t5, _t2, -(axisZ * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(axisY, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(axisZ, _t0, _t5 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t5, axisY * axisY, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t5, _t4, -(axisX * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t5, _t3, -(axisY * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(axisX, _t0, _t5 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_t5, axisZ * axisZ, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, long axis, float angle) {
        float _t0 = Math.sin(angle);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisy;
        float _t3 = _axisx * _axisz;
        float _t4 = _axisy * _axisz;
        float _t5 = 1.0f - _t1;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t5, _axisx * _axisx, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t5, _t2, -(_axisz * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_axisy, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_axisz, _t0, _t5 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t5, _axisy * _axisy, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t5, _t4, -(_axisx * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t5, _t3, -(_axisy * _t0)));
        return makeRotationAxis_unsafe_sf4edd841_1(dest, _t0, _axisx, _axisz, _t1, _t4, _t5);
    }

    /** Piece 2 of {@code makeRotationAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeRotationAxis_unsafe_sf4edd841_1(long dest, float _t0, float _axisx, float _axisz, float _t1, float _t4, float _t5) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_axisx, _t0, _t5 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_t5, _axisz * _axisz, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
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
        return makeRotationLookAlong_unsafe_s3cb72382_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_unsafe_s3cb72382_1(long dest, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t11);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
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
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t12);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t39);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, _t13);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _t38);
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t11);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeRotationQuat_unsafe(long dest, float qX, float qY, float qZ, float qW) {
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(qX, qY, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(qX, qZ, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 2.0f * Math.fma(qX, qY, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * Math.fma(qY, qZ, -(qX * qW)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 2.0f * Math.fma(qX, qZ, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 36L, 2.0f * Math.fma(qX, qW, qY * qZ));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
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
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(_qx, _qy, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(_qx, _qz, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 2.0f * Math.fma(_qx, _qy, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-2.0f, Math.fma(_qx, _qx, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * Math.fma(_qy, _qz, -(_qx * _qw)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 2.0f * Math.fma(_qx, _qz, -_t2));
        return makeRotationQuat_unsafe_s6b2a457a_1(dest, _qx, _qy, _qz, _qw);
    }

    /** Piece 2 of {@code makeRotationQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeRotationQuat_unsafe_s6b2a457a_1(long dest, float _qx, float _qy, float _qz, float _qw) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, 2.0f * Math.fma(_qx, _qw, _qy * _qz));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-2.0f, Math.fma(_qx, _qx, _qy * _qy), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeRotationX_unsafe(long dest, float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
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
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t1 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t6, _t4, _t1 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t5, _t4, -(_t6 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, -(_t2 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t2, _t1, -(_t7 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_t7, _t1, _t2 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t5 * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
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
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t0 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t7, _t3, _t2 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t5 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t7, _t0, -(_t2 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t6, _t3, -(_t0 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t2 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_t6, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeRotationY_unsafe(long dest, float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
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
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t6, _t4, -(_t2 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t1 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t2 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t5 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t7, _t2, -(_t1 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_t7, _t4, _t1 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t5 * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
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
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t2, _t0, -(_t7 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t7, _t2, _t0 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t5 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 24L, -(_t2 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -(_t0 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_t6, _t5, _t2 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_t5, _t3, -(_t6 * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeRotationZ_unsafe(long dest, float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
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
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t1 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t6, _t3, _t0 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t7, _t0, _t1 * _t3));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _t5 * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t0, _t1, -(_t7 * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -(_t0 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t2);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t5 * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
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
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t7, _t2, -(_t1 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t7, _t5, _t2 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _t1 * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t6, _t2, _t5 * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_t6, _t5, -(_t2 * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _t2 * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _t5 * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, float vX, float vY, float vZ) {
        UnsafeOpsHolder.U.putFloat(dest, vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, vY);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, vZ);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _vy);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _vz);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, float s) {
        UnsafeOpsHolder.U.putFloat(dest, s);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, s);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, s);
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, float vX, float vY, float vZ) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, vX);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, vY);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, vZ);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _vy);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 36L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 40L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _vz);
        return dest;
    }

    public static long mapXYZ_unsafe(long dest, long src) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, _eself);
        }
        return dest;
    }

    public static long mapXYnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapXZY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapXZnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapXnYZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapXnYnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapXnZY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapXnZnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapYXZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapYXnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapYZX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapYZnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapYnXZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapYnXnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapYnZX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapYnZnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapZXY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapZXnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapZYX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapZYnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapZnXY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapZnXnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapZnYX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapZnYnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnXYZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnXYnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnXZY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnXZnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnXnYZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnXnYnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnXnZY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnXnZnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnYXZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnYXnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnYZX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnYZnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnYnXZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnYnXnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnYnZX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnYnZnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnZXY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnZXnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnZYX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnZYnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnZnXY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnZnXnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnZnYX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long mapnZnYnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t4 = rotX + rotX;
        float _t5 = rotY + rotY;
        float _t6 = rotZ + rotZ;
        float _t7 = rotW * _t5;
        float _t8 = rotW * _t6;
        float _t10 = rotW * _t4;
        return preRotateAround_unsafe_s3f46ae96_1(dest, rotX, rotY, rotZ, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -rotY, -pivotZ, -rotX, _t4, _t5, _t7, rotZ * _t6, _t10, Math.fma(-rotZ, _t6, 1.0f), Math.fma(rotZ, _t4, _t7), Math.fma(rotY, _t4, _t8), Math.fma(rotZ, _t5, _t10), Math.fma(rotY, _t4, -_t8));
    }

    /** Piece 2 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_s3f46ae96_1(long dest, float rotX, float rotY, float rotZ, float pivotX, float pivotY, float pivotZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t2, float _t3, float _t4, float _t5, float _t7, float _t9, float _t10, float _t14, float _t16, float _t17, float _t18, float _t19) {
        float _t20 = Math.fma(rotZ, _t5, -_t10);
        float _t22 = Math.fma(_t0, _t5, _t14);
        float _t23 = Math.fma(_t3, _t4, _t14);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        return preRotateAround_unsafe_s3f46ae96_2(dest, rotX, rotY, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t2, _t4, _t5, _t9, _t17, _t18, _t20, Math.fma(rotZ, _t4, -_t7), _t23, Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)));
    }

    /** Piece 3 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_s3f46ae96_2(long dest, float rotX, float rotY, float pivotX, float pivotY, float pivotZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t2, float _t4, float _t5, float _t9, float _t17, float _t18, float _t20, float _t21, float _t23, float _t24) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, long rot, long pivot) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _rotx = UnsafeOpsHolder.U.getFloat(rot);
        float _roty = UnsafeOpsHolder.U.getFloat(rot + 4L);
        float _rotz = UnsafeOpsHolder.U.getFloat(rot + 8L);
        float _rotw = UnsafeOpsHolder.U.getFloat(rot + 12L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(pivot + 8L);
        return preRotateAround_unsafe_s3a93b5f4_3(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _rotz, _rotw, _pivotx, _pivoty, _pivotz, -_roty, -_rotx, _rotx + _rotx, _roty + _roty, _rotz + _rotz);
    }

    /** Part 1 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static float preRotateAround_unsafe_s3a93b5f4_1(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _rotx, float _roty, float _pivotx, float _pivoty, float _t0, float _t2, float _t3, float _t4, float _t5, float _t9, float _t16, float _t17, float _t19, float _t20, float _t22, float _t23) {
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))));
        return Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f));
    }

    /** Part 2 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_s3a93b5f4_2(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _rotx, float _roty, float _pivotx, float _pivoty, float _pivotz, float _t4, float _t5, float _t18, float _t21, float _t24) {
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    /** Piece 2 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_s3a93b5f4_3(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _rotx, float _roty, float _rotz, float _rotw, float _pivotx, float _pivoty, float _pivotz, float _t0, float _t3, float _t4, float _t5, float _t6) {
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t10 = _rotw * _t4;
        float _t14 = Math.fma(-_rotz, _t6, 1.0f);
        return preRotateAround_unsafe_s3a93b5f4_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _pivotx, _pivoty, _pivotz, _t4, _t5, Math.fma(_rotz, _t5, _t10), Math.fma(_rotz, _t4, -_t7), preRotateAround_unsafe_s3a93b5f4_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _pivotx, _pivoty, _t0, (-_pivotz), _t3, _t4, _t5, (_rotz * _t6), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_t0, _t5, _t14), Math.fma(_t3, _t4, _t14)));
    }

    public static long preRotateAxis_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisX * axisY;
        float _t11 = 1.0f - _t1;
        return preRotateAxis_unsafe_scfa5642e_1(dest, axisX, axisY, axisZ, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t2, _t4, axisY * axisZ, _t11, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t4));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_scfa5642e_1(long dest, float axisX, float axisY, float axisZ, float _t0, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t2, float _t4, float _t6, float _t11, float _t18, float _t19, float _t20, float _t21, float _t22) {
        float _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        float _t25 = Math.fma(_t11, _t6, -(axisX * _t0));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        return preRotateAxis_unsafe_scfa5642e_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t19, _t20, _t22, Math.fma(axisX, _t0, _t11 * _t6), _t25, Math.fma(_t11, _t2, -(axisY * _t0)));
    }

    /** Piece 3 of {@code preRotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_scfa5642e_2(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t19, float _t20, float _t22, float _t23, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static long preRotateAxis_unsafe(long dest, long src, long axis, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t11 = 1.0f - _t1;
        return preRotateAxis_unsafe_sc466f2a9_1(dest, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _axisx, _axisy, _axisz, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_sc466f2a9_1(long dest, float _t0, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _axisx, float _axisy, float _axisz, float _t2, float _t4, float _t6, float _t11, float _t18, float _t19, float _t20) {
        float _t21 = Math.fma(_axisy, _t0, _t11 * _t2);
        float _t22 = Math.fma(_axisz, _t0, _t11 * _t4);
        float _t24 = Math.fma(_t11, _t4, -(_axisz * _t0));
        float _t25 = Math.fma(_t11, _t6, -(_axisx * _t0));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        return preRotateAxis_unsafe_sc466f2a9_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t19, _t20, _t22, Math.fma(_axisx, _t0, _t11 * _t6), _t25, Math.fma(_t11, _t2, -(_axisy * _t0)));
    }

    /** Piece 3 of {@code preRotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_sc466f2a9_2(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t19, float _t20, float _t22, float _t23, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static long preRotateQuat_unsafe(long dest, long src, float qX, float qY, float qZ, float qW) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        float _t6 = qW * _t4;
        float _t7 = qW * _t5;
        float _t8 = qW * _t3;
        return preRotateQuat_unsafe_s597d567b_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -qY, -qX, _t3, _t4, Math.fma(-qZ, _t5, 1.0f), Math.fma(qZ, _t3, _t6), Math.fma(qY, _t3, _t7), Math.fma(qZ, _t4, _t8), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8), Math.fma(qZ, _t3, -_t6));
    }

    /** Piece 2 of {@code preRotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateQuat_unsafe_s597d567b_1(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t2, float _t3, float _t4, float _t12, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19) {
        float _t20 = Math.fma(_t0, _t4, _t12);
        float _t21 = Math.fma(_t2, _t3, _t12);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        return preRotateQuat_unsafe_s597d567b_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t16, _t19, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
    }

    /** Piece 3 of {@code preRotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateQuat_unsafe_s597d567b_2(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t16, float _t19, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static long preRotateQuat_unsafe(long dest, long src, long q) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _qx = UnsafeOpsHolder.U.getFloat(q);
        float _qy = UnsafeOpsHolder.U.getFloat(q + 4L);
        float _qz = UnsafeOpsHolder.U.getFloat(q + 8L);
        float _qw = UnsafeOpsHolder.U.getFloat(q + 12L);
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        float _t6 = _qw * _t4;
        return preRotateQuat_unsafe_s1861b222_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _qy, _qz, -_qy, -_qx, _t3, _t4, _t6, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f), Math.fma(_qz, _t3, _t6));
    }

    /** Piece 2 of {@code preRotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateQuat_unsafe_s1861b222_1(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _qy, float _qz, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12, float _t14) {
        float _t15 = Math.fma(_qy, _t3, _t7);
        float _t17 = Math.fma(_qy, _t3, -_t7);
        float _t18 = Math.fma(_qz, _t4, -_t8);
        float _t20 = Math.fma(_t0, _t4, _t12);
        float _t21 = Math.fma(_t2, _t3, _t12);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        return preRotateQuat_unsafe_s1861b222_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t15, Math.fma(_qz, _t4, _t8), _t18, Math.fma(_qz, _t3, -_t6), _t21, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
    }

    /** Piece 3 of {@code preRotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateQuat_unsafe_s1861b222_2(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t15, float _t16, float _t18, float _t19, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static long preRotateX_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self10, _t1, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self11, _t1, -(_self21 * _t0)));
        return preRotateX_unsafe_scde22415_1(dest, _t0, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code preRotateX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateX_unsafe_scde22415_1(long dest, float _t0, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t1, -(_self22 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self13, _t1, -(_self23 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self10, _t0, _self20 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self11, _t0, _self21 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self12, _t0, _self22 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self13, _t0, _self23 * _t1));
        return dest;
    }

    public static long preRotateY_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t1, _self20 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self01, _t1, _self21 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t1, _self22 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self03, _t1, _self23 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self10);
        return preRotateY_unsafe_sfd272d7e_1(dest, _t0, _self00, _self01, _self02, _self03, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code preRotateY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateY_unsafe_sfd272d7e_1(long dest, float _t0, float _self00, float _self01, float _self02, float _self03, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self21, _t1, -(_self01 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t1, -(_self02 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self23, _t1, -(_self03 * _t0)));
        return dest;
    }

    public static long preRotateZ_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self01, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t1, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self03, _t1, -(_self13 * _t0)));
        return preRotateZ_unsafe_sd57d7d2b_1(dest, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code preRotateZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateZ_unsafe_sd57d7d2b_1(long dest, float _t0, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, _t0, _self10 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self01, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self02, _t0, _self12 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self03, _t0, _self13 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, float vX, float vY, float vZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        UnsafeOpsHolder.U.putFloat(dest, _self00 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self10 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self11 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self12 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20 * vZ);
        return preScale_unsafe_s8b417900_1(dest, vZ, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preScale_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScale_unsafe_s8b417900_1(long dest, float vZ, float _self21, float _self22, float _self23) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self21 * vZ);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22 * vZ);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23 * vZ);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _self00 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self10 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self11 * _vy);
        return preScale_unsafe_s6e76cf75_1(dest, _self12, _self13, _self20, _self21, _self22, _self23, _vy, _vz);
    }

    /** Piece 2 of {@code preScale_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScale_unsafe_s6e76cf75_1(long dest, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _vy, float _vz) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self12 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20 * _vz);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self21 * _vz);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22 * _vz);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23 * _vz);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, float s) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = UnsafeOpsHolder.U.getFloat(src + _i * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _i * 4L, s * _eself);
        }
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, float s, float pivotX, float pivotY, float pivotZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t0 = 1.0f - s;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(s, _self03, pivotX * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 16L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, s * _self12);
        return preScaleAround_unsafe_sb254d256_1(dest, s, pivotY, pivotZ, _self13, _self20, _self21, _self22, _self23, _t0);
    }

    /** Piece 2 of {@code preScaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_sb254d256_1(long dest, float s, float pivotY, float pivotZ, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(s, _self13, pivotY * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 32L, s * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, s * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, s * _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(s, _self23, pivotZ * _t0));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long pivot, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(pivot + 8L);
        float _t0 = 1.0f - s;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(s, _self03, _pivotx * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 16L, s * _self10);
        return preScaleAround_unsafe_s100adfab_1(dest, s, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _pivoty, _pivotz, _t0);
    }

    /** Piece 2 of {@code preScaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_s100adfab_1(long dest, float s, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _pivoty, float _pivotz, float _t0) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, s * _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(s, _self13, _pivoty * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 32L, s * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, s * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, s * _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(s, _self23, _pivotz * _t0));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        UnsafeOpsHolder.U.putFloat(dest, sX * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, sX * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, sX * _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(pivotX, 1.0f - sX, sX * _self03));
        UnsafeOpsHolder.U.putFloat(dest + 16L, sY * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, sY * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, sY * _self12);
        return preScaleAround_unsafe_sc308a0d1_1(dest, sY, sZ, pivotY, pivotZ, _self13, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preScaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_sc308a0d1_1(long dest, float sY, float sZ, float pivotY, float pivotZ, float _self13, float _self20, float _self21, float _self22, float _self23) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(pivotY, 1.0f - sY, sY * _self13));
        UnsafeOpsHolder.U.putFloat(dest + 32L, sZ * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, sZ * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, sZ * _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(pivotZ, 1.0f - sZ, sZ * _self23));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long s, long pivot) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _sx = UnsafeOpsHolder.U.getFloat(s);
        float _sy = UnsafeOpsHolder.U.getFloat(s + 4L);
        float _sz = UnsafeOpsHolder.U.getFloat(s + 8L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(pivot + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _sx * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _sx * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _sx * _self02);
        return preScaleAround_unsafe_s9ac39297_1(dest, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _sx, _sy, _sz, _pivotx, _pivoty, _pivotz);
    }

    /** Piece 2 of {@code preScaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_s9ac39297_1(long dest, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _sx, float _sy, float _sz, float _pivotx, float _pivoty, float _pivotz) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_pivotx, 1.0f - _sx, _sx * _self03));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _sy * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _sy * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _sy * _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_pivoty, 1.0f - _sy, _sy * _self13));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _sz * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _sz * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _sz * _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_pivotz, 1.0f - _sz, _sz * _self23));
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, float vX, float vY, float vZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03 + vX);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13 + vY);
        return preTranslate_unsafe_s854bb380_1(dest, vZ, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preTranslate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preTranslate_unsafe_s854bb380_1(long dest, float vZ, float _self20, float _self21, float _self22, float _self23) {
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23 + vZ);
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03 + _vx);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self12);
        return preTranslate_unsafe_s2e31ddf5_1(dest, _self13, _self20, _self21, _self22, _self23, _vy, _vz);
    }

    /** Piece 2 of {@code preTranslate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preTranslate_unsafe_s2e31ddf5_1(long dest, float _self13, float _self20, float _self21, float _self22, float _self23, float _vy, float _vz) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13 + _vy);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23 + _vz);
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, float normalX, float normalY, float normalZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _sp0 = normalX + normalX;
        float _t0 = -_self02;
        float _t9 = _sp0 * normalZ;
        float _t10 = _sp0 * normalY;
        float _t12 = Math.fma(-2.0f, normalX * normalX, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        return reflect_unsafe_sad7e3877_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, -_self12, -_self22, _t9, _t10, (normalY + normalY) * normalZ, _t12, Math.fma(-2.0f, normalY * normalY, 1.0f), Math.fma(-2.0f, normalZ * normalZ, 1.0f));
    }

    /** Piece 2 of {@code reflect_unsafe}, split to fit the inline budget; reached only through it. */
    private static long reflect_unsafe_sad7e3877_1(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9))));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, long normal) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _sp0 = _normalx + _normalx;
        return reflect_unsafe_s9cc5500c_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self02, -_self12, -_self22, _sp0 * _normalz, _sp0 * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0f, _normalx * _normalx, 1.0f), Math.fma(-2.0f, _normaly * _normaly, 1.0f), Math.fma(-2.0f, _normalz * _normalz, 1.0f));
    }

    /** Piece 2 of {@code reflect_unsafe}, split to fit the inline budget; reached only through it. */
    private static long reflect_unsafe_s9cc5500c_1(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10))));
        return reflect_unsafe_s9cc5500c_2(dest, _self20, _self21, _self22, _self23, _t9, _t11, _t14);
    }

    /** Piece 3 of {@code reflect_unsafe}, split to fit the inline budget; reached only through it. */
    private static long reflect_unsafe_s9cc5500c_2(long dest, float _self20, float _self21, float _self22, float _self23, float _t9, float _t11, float _t14) {
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9))));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t5 = rotX + rotX;
        float _t6 = rotY + rotY;
        float _t7 = rotZ + rotZ;
        float _t8 = rotW * _t6;
        float _t9 = rotW * _t7;
        float _t10 = rotW * _t5;
        return rotateAround_unsafe_s8271d7db_1(dest, rotX, rotY, rotZ, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -rotY, -rotX, -pivotZ, _t5, _t6, _t9, _t10, rotZ * _t7, Math.fma(-rotZ, _t7, 1.0f), Math.fma(rotY, _t5, _t9), Math.fma(rotZ, _t6, _t10), Math.fma(rotZ, _t5, _t8), Math.fma(rotZ, _t5, -_t8));
    }

    /** Piece 2 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_s8271d7db_1(long dest, float rotX, float rotY, float rotZ, float pivotX, float pivotY, float pivotZ, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t2, float _t3, float _t5, float _t6, float _t9, float _t10, float _t11, float _t16, float _t18, float _t19, float _t20, float _t24) {
        float _t25 = Math.fma(rotY, _t5, -_t9);
        float _t26 = Math.fma(rotZ, _t6, -_t10);
        float _t27 = Math.fma(_t0, _t6, _t16);
        float _t28 = Math.fma(_t2, _t5, _t16);
        float _t29 = Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        return rotateAround_unsafe_s8271d7db_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))), Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
    }

    /** Piece 3 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_s8271d7db_2(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t39, float _t40, float _t41) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23))));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long rot, long pivot) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _rotx = UnsafeOpsHolder.U.getFloat(rot);
        float _roty = UnsafeOpsHolder.U.getFloat(rot + 4L);
        float _rotz = UnsafeOpsHolder.U.getFloat(rot + 8L);
        float _rotw = UnsafeOpsHolder.U.getFloat(rot + 12L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(pivot + 8L);
        return rotateAround_unsafe_sbbb9d1c1_3(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _rotz, _rotw, _pivotx, _pivoty, _pivotz, -_roty, -_rotx, -_pivotz, _rotx + _rotx, _roty + _roty);
    }

    /** Part 1 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static float rotateAround_unsafe_sbbb9d1c1_1(long dest, float _self00, float _self01, float _self02, float _rotx, float _roty, float _pivotx, float _pivoty, float _pivotz, float _t5, float _t6, float _t18, float _t19, float _t24, float _t25, float _t27, float _t28) {
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        return Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
    }

    /** Part 2 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static void rotateAround_unsafe_sbbb9d1c1_2(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t39, float _t40, float _t41) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23))));
    }

    /** Piece 2 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_sbbb9d1c1_3(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _rotx, float _roty, float _rotz, float _rotw, float _pivotx, float _pivoty, float _pivotz, float _t0, float _t2, float _t3, float _t5, float _t6) {
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t9 = _rotw * _t7;
        float _t10 = _rotw * _t5;
        float _t11 = _rotz * _t7;
        float _t16 = Math.fma(-_rotz, _t7, 1.0f);
        float _t18 = Math.fma(_roty, _t5, _t9);
        float _t19 = Math.fma(_rotz, _t6, _t10);
        float _t20 = Math.fma(_rotz, _t5, _t8);
        float _t24 = Math.fma(_rotz, _t5, -_t8);
        float _t25 = Math.fma(_roty, _t5, -_t9);
        float _t26 = Math.fma(_rotz, _t6, -_t10);
        float _t27 = Math.fma(_t0, _t6, _t16);
        float _t28 = Math.fma(_t2, _t5, _t16);
        rotateAround_unsafe_sbbb9d1c1_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)), Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))), Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18))), rotateAround_unsafe_sbbb9d1c1_1(dest, _self00, _self01, _self02, _rotx, _roty, _pivotx, _pivoty, _pivotz, _t5, _t6, _t18, _t19, _t24, _t25, _t27, _t28));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        return rotateAxis_unsafe_sba70055b_1(dest, axisX, axisY, axisZ, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, axisX * axisZ, _t5, _t6, _t11, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6));
    }

    /** Piece 2 of {@code rotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_sba70055b_1(long dest, float axisX, float axisY, float axisZ, float _t0, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t2, float _t5, float _t6, float _t11, float _t18, float _t19, float _t20, float _t21, float _t22) {
        float _t23 = Math.fma(axisY, _t0, _t11 * _t2);
        float _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        float _t25 = Math.fma(_t11, _t5, -(axisZ * _t0));
        float _t26 = Math.fma(_t11, _t6, -(axisX * _t0));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        return rotateAxis_unsafe_sba70055b_2(dest, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26);
    }

    /** Piece 3 of {@code rotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_sba70055b_2(long dest, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t11 = 1.0f - _t1;
        return rotateAxis_unsafe_sa09c96a8_1(dest, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _axisx, _axisy, _axisz, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1));
    }

    /** Piece 2 of {@code rotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_sa09c96a8_1(long dest, float _t0, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _axisx, float _axisy, float _axisz, float _t2, float _t5, float _t6, float _t11, float _t18, float _t19, float _t20) {
        float _t21 = Math.fma(_axisz, _t0, _t11 * _t5);
        float _t22 = Math.fma(_axisx, _t0, _t11 * _t6);
        float _t23 = Math.fma(_axisy, _t0, _t11 * _t2);
        float _t24 = Math.fma(_t11, _t2, -(_axisy * _t0));
        float _t25 = Math.fma(_t11, _t5, -(_axisz * _t0));
        float _t26 = Math.fma(_t11, _t6, -(_axisx * _t0));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        return rotateAxis_unsafe_sa09c96a8_2(dest, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26);
    }

    /** Piece 3 of {@code rotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_sa09c96a8_2(long dest, float _self13, float _self20, float _self21, float _self22, float _self23, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateQuat_unsafe(long dest, long src, float qX, float qY, float qZ, float qW) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        float _t6 = qW * _t4;
        float _t7 = qW * _t5;
        float _t8 = qW * _t3;
        return rotateQuat_unsafe_s118091d6_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -qY, -qX, _t3, _t4, Math.fma(-qZ, _t5, 1.0f), Math.fma(qY, _t3, _t7), Math.fma(qZ, _t4, _t8), Math.fma(qZ, _t3, _t6), Math.fma(qZ, _t3, -_t6), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8));
    }

    /** Piece 2 of {@code rotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateQuat_unsafe_s118091d6_1(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0, float _t2, float _t3, float _t4, float _t12, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19) {
        float _t20 = Math.fma(_t0, _t4, _t12);
        float _t21 = Math.fma(_t2, _t3, _t12);
        float _t22 = Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14)));
        return rotateQuat_unsafe_s118091d6_2(dest, _self20, _self21, _self22, _self23, _t15, _t16, _t18, _t19, _t21, _t22);
    }

    /** Piece 3 of {@code rotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateQuat_unsafe_s118091d6_2(long dest, float _self20, float _self21, float _self22, float _self23, float _t15, float _t16, float _t18, float _t19, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateQuat_unsafe(long dest, long src, long q) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _qx = UnsafeOpsHolder.U.getFloat(q);
        float _qy = UnsafeOpsHolder.U.getFloat(q + 4L);
        float _qz = UnsafeOpsHolder.U.getFloat(q + 8L);
        float _qw = UnsafeOpsHolder.U.getFloat(q + 12L);
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        float _t7 = _qw * _t5;
        return rotateQuat_unsafe_sb82c8fdb_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _qy, _qz, -_qy, -_qx, _t3, _t4, _qw * _t4, _t7, _qw * _t3, Math.fma(-_qz, _t5, 1.0f), Math.fma(_qy, _t3, _t7));
    }

    /** Piece 2 of {@code rotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateQuat_unsafe_sb82c8fdb_1(long dest, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _qy, float _qz, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12, float _t14) {
        float _t15 = Math.fma(_qz, _t4, _t8);
        float _t16 = Math.fma(_qz, _t3, _t6);
        float _t17 = Math.fma(_qz, _t3, -_t6);
        float _t18 = Math.fma(_qy, _t3, -_t7);
        float _t19 = Math.fma(_qz, _t4, -_t8);
        float _t20 = Math.fma(_t0, _t4, _t12);
        float _t21 = Math.fma(_t2, _t3, _t12);
        float _t22 = Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21)));
        return rotateQuat_unsafe_sb82c8fdb_2(dest, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22);
    }

    /** Piece 3 of {@code rotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateQuat_unsafe_sb82c8fdb_2(long dest, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self01, _t1, _self02 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t1, -(_self01 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self11, _t1, _self12 * _t0));
        return rotateX_unsafe_s9e708fd0_1(dest, _t0, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateX_unsafe_s9e708fd0_1(long dest, float _t0, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self21, _t1, _self22 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t1, -(_self21 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateXYZ_unsafe(long dest, long src, float angleX, float angleY, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleY);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t3 = Math.cosFromSin(_t0, angleX);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        return rotateXYZ_unsafe_sbdd7c928_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t3, _t4, _t6, _t7, _t1 * _t5, _t0 * _t5, _t5 * _t4, _t3 * _t5, Math.fma(_t6, _t4, _t1 * _t3), Math.fma(_t7, _t1, _t0 * _t4));
    }

    /** Piece 2 of {@code rotateXYZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_sbdd7c928_1(long dest, float _t0, float _t1, float _t2, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t3, float _t4, float _t6, float _t7, float _t10, float _t11, float _t13, float _t15, float _t18, float _t19) {
        float _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        float _t21 = Math.fma(_t3, _t4, -(_t6 * _t1));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t20, Math.fma(_self00, _t13, _self01 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t19, Math.fma(_self01, _t21, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t15, Math.fma(_self00, _t2, -(_self01 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t20, Math.fma(_self10, _t13, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t19, Math.fma(_self11, _t21, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t15, Math.fma(_self10, _t2, -(_self11 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t20, Math.fma(_self20, _t13, _self21 * _t18)));
        return rotateXYZ_unsafe_sbdd7c928_2(dest, _t2, _self20, _self21, _self22, _self23, _t10, _t11, _t15, _t19, _t21);
    }

    /** Piece 3 of {@code rotateXYZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_sbdd7c928_2(long dest, float _t2, float _self20, float _self21, float _self22, float _self23, float _t10, float _t11, float _t15, float _t19, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t19, Math.fma(_self21, _t21, -(_self20 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t15, Math.fma(_self20, _t2, -(_self21 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateXZY_unsafe(long dest, long src, float angleX, float angleZ, float angleY) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleY);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t3 = Math.cosFromSin(_t2, angleY);
        float _t4 = Math.cosFromSin(_t0, angleX);
        float _t5 = Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        return rotateXZY_unsafe_sa95ff786_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t3, _t4, _t6, _t9, _t0 * _t5, _t2 * _t5, _t3 * _t5, _t4 * _t5, Math.fma(_t9, _t3, _t0 * _t2), Math.fma(_t6, _t2, _t4 * _t3));
    }

    /** Piece 2 of {@code rotateXZY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_sa95ff786_1(long dest, float _t0, float _t1, float _t2, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t3, float _t4, float _t6, float _t9, float _t10, float _t11, float _t15, float _t16, float _t18, float _t19) {
        float _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        float _t21 = Math.fma(_t9, _t2, -(_t0 * _t3));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t20, Math.fma(_self00, _t15, _self01 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t10, Math.fma(_self01, _t16, -(_self00 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t19, Math.fma(_self00, _t11, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t20, Math.fma(_self10, _t15, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t10, Math.fma(_self11, _t16, -(_self10 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t19, Math.fma(_self10, _t11, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t20, Math.fma(_self20, _t15, _self21 * _t18)));
        return rotateXZY_unsafe_sa95ff786_2(dest, _t1, _self20, _self21, _self22, _self23, _t10, _t11, _t16, _t19, _t21);
    }

    /** Piece 3 of {@code rotateXZY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_sa95ff786_2(long dest, float _t1, float _self20, float _self21, float _self22, float _self23, float _t10, float _t11, float _t16, float _t19, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t10, Math.fma(_self21, _t16, -(_self20 * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t19, Math.fma(_self20, _t11, _self21 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t1, -(_self02 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self00, _t0, _self02 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self10, _t1, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self11);
        return rotateY_unsafe_s3a7ae9c7_1(dest, _t0, _self10, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateY_unsafe_s3a7ae9c7_1(long dest, float _t0, float _self10, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self10, _t0, _self12 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t1, -(_self22 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self20, _t0, _self22 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateYXZ_unsafe(long dest, long src, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        return rotateYXZ_unsafe_s3fca413a_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t3, _t4, _t6, _t8, _t2 * _t5, _t1 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t8, _t4, _t1 * _t2));
    }

    /** Piece 2 of {@code rotateYXZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_s3fca413a_1(long dest, float _t0, float _t1, float _t2, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t3, float _t4, float _t6, float _t8, float _t10, float _t12, float _t16, float _t17, float _t18, float _t19) {
        float _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        float _t21 = Math.fma(_t6, _t4, -(_t2 * _t3));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10)));
        return rotateYXZ_unsafe_s3fca413a_2(dest, _t0, _self20, _self21, _self22, _self23, _t12, _t16, _t17, _t19, _t21);
    }

    /** Piece 3 of {@code rotateYXZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_s3fca413a_2(long dest, float _t0, float _self20, float _self21, float _self22, float _self23, float _t12, float _t16, float _t17, float _t19, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0))));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateYZX_unsafe(long dest, long src, float angleY, float angleZ, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t3 = Math.cosFromSin(_t1, angleZ);
        float _t4 = Math.cosFromSin(_t0, angleY);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        return rotateYZX_unsafe_s358828a_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t4, _t5, _t6, _t0 * _t3, _t9, _t2 * _t3, _t4 * _t3, _t5 * _t3, Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t9, _t2, _t0 * _t5));
    }

    /** Piece 2 of {@code rotateYZX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_s358828a_1(long dest, float _t0, float _t1, float _t2, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t4, float _t5, float _t6, float _t7, float _t9, float _t11, float _t13, float _t14, float _t18, float _t19) {
        float _t20 = Math.fma(_t2, _t0, -(_t9 * _t5));
        float _t21 = Math.fma(_t5, _t4, -(_t6 * _t2));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-_self02, _t7, Math.fma(_self00, _t13, _self01 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t18, Math.fma(_self00, _t20, _self01 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t21, Math.fma(_self00, _t19, -(_self01 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(-_self12, _t7, Math.fma(_self10, _t13, _self11 * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t18, Math.fma(_self10, _t20, _self11 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t21, Math.fma(_self10, _t19, -(_self11 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(-_self22, _t7, Math.fma(_self20, _t13, _self21 * _t1)));
        return rotateYZX_unsafe_s358828a_2(dest, _self20, _self21, _self22, _self23, _t11, _t14, _t18, _t19, _t20, _t21);
    }

    /** Piece 3 of {@code rotateYZX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_s358828a_2(long dest, float _self20, float _self21, float _self22, float _self23, float _t11, float _t14, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t18, Math.fma(_self20, _t20, _self21 * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t21, Math.fma(_self20, _t19, -(_self21 * _t11))));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateZ_unsafe(long dest, long src, float angle) {
        float _t0 = Math.sin(angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t1, _self01 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self01, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self10, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self11, _t1, -(_self10 * _t0)));
        return rotateZ_unsafe_s84a477fa_1(dest, _t0, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZ_unsafe_s84a477fa_1(long dest, float _t0, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_self20, _t1, _self21 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self21, _t1, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateZXY_unsafe(long dest, long src, float angleZ, float angleX, float angleY) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleX);
        float _t2 = Math.sin(angleZ);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t3 = Math.cosFromSin(_t1, angleX);
        float _t4 = Math.cosFromSin(_t0, angleY);
        float _t5 = Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t8 = _t1 * _t5;
        return rotateZXY_unsafe_s6e82c462_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t4, _t5, _t6, _t0 * _t3, _t8, _t2 * _t3, _t3 * _t5, _t3 * _t4, Math.fma(_t8, _t0, _t2 * _t4), Math.fma(_t6, _t4, _t0 * _t5));
    }

    /** Piece 2 of {@code rotateZXY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_s6e82c462_1(long dest, float _t0, float _t1, float _t2, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t4, float _t5, float _t6, float _t7, float _t8, float _t10, float _t14, float _t15, float _t18, float _t19) {
        float _t20 = Math.fma(_t4, _t5, -(_t6 * _t0));
        float _t21 = Math.fma(_t0, _t2, -(_t8 * _t4));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-_self02, _t7, Math.fma(_self00, _t20, _self01 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t1, Math.fma(_self01, _t14, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t15, Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(-_self12, _t7, Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t1, Math.fma(_self11, _t14, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t15, Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(-_self22, _t7, Math.fma(_self20, _t20, _self21 * _t18)));
        return rotateZXY_unsafe_s6e82c462_2(dest, _t1, _self20, _self21, _self22, _self23, _t10, _t14, _t15, _t19, _t21);
    }

    /** Piece 3 of {@code rotateZXY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_s6e82c462_2(long dest, float _t1, float _self20, float _self21, float _self22, float _self23, float _t10, float _t14, float _t15, float _t19, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t1, Math.fma(_self21, _t14, -(_self20 * _t10))));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t15, Math.fma(_self20, _t19, _self21 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long rotateZYX_unsafe(long dest, long src, float angleZ, float angleY, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t10 = _t0 * _t4;
        return rotateZYX_unsafe_s71b42f14_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t4, _t5, _t6, _t1 * _t3, _t2 * _t3, _t10, _t3 * _t4, _t5 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t10, _t5, _t2 * _t1));
    }

    /** Piece 2 of {@code rotateZYX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_s71b42f14_1(long dest, float _t0, float _t1, float _t2, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t4, float _t5, float _t6, float _t8, float _t9, float _t10, float _t15, float _t17, float _t18, float _t19) {
        float _t20 = Math.fma(_t10, _t2, -(_t1 * _t5));
        float _t21 = Math.fma(_t6, _t5, -(_t2 * _t4));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-_self02, _t0, Math.fma(_self00, _t15, _self01 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self02, _t9, Math.fma(_self00, _t20, _self01 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self02, _t17, Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self03);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(-_self12, _t0, Math.fma(_self10, _t15, _self11 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self12, _t9, Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_self12, _t17, Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self13);
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(-_self22, _t0, Math.fma(_self20, _t15, _self21 * _t8)));
        return rotateZYX_unsafe_s71b42f14_2(dest, _self20, _self21, _self22, _self23, _t9, _t17, _t18, _t19, _t20, _t21);
    }

    /** Piece 3 of {@code rotateZYX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_s71b42f14_2(long dest, float _self20, float _self21, float _self22, float _self23, float _t9, float _t17, float _t18, float _t19, float _t20, float _t21) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, Math.fma(_self22, _t9, Math.fma(_self20, _t20, _self21 * _t18)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(_self22, _t17, Math.fma(_self20, _t19, _self21 * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 44L, _self23);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, float vX, float vY, float vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0 * vX);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1 * vY);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2 * vZ);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long scale_unsafe(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0 * _vx);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1 * _vy);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2 * _vz);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long scale_unsafe(long dest, long src, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, s * _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, s * _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, s * _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, _eself3);
        }
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, float s, float pivotX, float pivotY, float pivotZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _t3 = pivotZ * _t0;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, s * _self10);
        return scaleAround_unsafe_s7ebc1797_1(dest, s, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1, _t2, _t3);
    }

    /** Piece 2 of {@code scaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_s7ebc1797_1(long dest, float s, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t1, float _t2, float _t3) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, s * _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, s * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, s * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, s * _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long pivot, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        return scaleAround_unsafe_s79e8fc32_1(dest, pivot, s, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, 1.0f - s);
    }

    /** Piece 2 of {@code scaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_s79e8fc32_1(long dest, long pivot, float s, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t0) {
        float _t1 = UnsafeOpsHolder.U.getFloat(pivot) * _t0;
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * _t0;
        float _t3 = UnsafeOpsHolder.U.getFloat(pivot + 8L) * _t0;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, s * _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, s * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, s * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, s * _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        UnsafeOpsHolder.U.putFloat(dest, sX * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, sY * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, sZ * _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, sX * _self10);
        return scaleAround_unsafe_s9f62067e_1(dest, sX, sY, sZ, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t3, _t4, _t5);
    }

    /** Piece 2 of {@code scaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_s9f62067e_1(long dest, float sX, float sY, float sZ, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _t3, float _t4, float _t5) {
        UnsafeOpsHolder.U.putFloat(dest + 20L, sY * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, sZ * _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, sX * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, sY * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, sZ * _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long s, long pivot) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _sx = UnsafeOpsHolder.U.getFloat(s);
        float _sy = UnsafeOpsHolder.U.getFloat(s + 4L);
        float _sz = UnsafeOpsHolder.U.getFloat(s + 8L);
        return scaleAround_unsafe_s12fd168c_1(dest, pivot, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _sx, _sy, _sz);
    }

    /** Piece 2 of {@code scaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_s12fd168c_1(long dest, long pivot, float _self00, float _self01, float _self02, float _self03, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _sx, float _sy, float _sz) {
        float _t3 = UnsafeOpsHolder.U.getFloat(pivot) * (1.0f - _sx);
        float _t4 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * (1.0f - _sy);
        float _t5 = UnsafeOpsHolder.U.getFloat(pivot + 8L) * (1.0f - _sz);
        UnsafeOpsHolder.U.putFloat(dest, _sx * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _sy * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _sz * _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _sx * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _sy * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _sz * _self12);
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 32L, _sx * _self20);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _sy * _self21);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _sz * _self22);
        UnsafeOpsHolder.U.putFloat(dest + 44L, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, float vX, float vY, float vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, Math.fma(_eself0, vX, Math.fma(_eself1, vY, Math.fma(_eself2, vZ, _eself3))));
        }
        return dest;
    }

    public static long translate_unsafe(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eself2 = UnsafeOpsHolder.U.getFloat(src + (_lo + 2) * 4L);
            float _eself3 = UnsafeOpsHolder.U.getFloat(src + (_lo + 3) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eself2);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 3) * 4L, Math.fma(_eself0, _vx, Math.fma(_eself1, _vy, Math.fma(_eself2, _vz, _eself3))));
        }
        return dest;
    }

    public static long mulVec4_unsafe(long dest, long src, float vX, float vY, float vZ, float vW) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self03, vW, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self13, vW, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self23, vW, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY))));
        return dest;
    }

    public static long mulVec4_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _vw = UnsafeOpsHolder.U.getFloat(v + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self03, _vw, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy))));
        return mulVec4_unsafe_sa64475d4_1(dest, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _vx, _vy, _vz, _vw);
    }

    /** Piece 2 of {@code mulVec4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long mulVec4_unsafe_sa64475d4_1(long dest, float _self10, float _self11, float _self12, float _self13, float _self20, float _self21, float _self22, float _self23, float _vx, float _vy, float _vz, float _vw) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self13, _vw, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self23, _vw, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy))));
        return dest;
    }

    public static long transformAabb_unsafe(long dest, long src, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _t3 = minX * _self00;
        float _t4 = maxX * _self00;
        float _t5 = minY * _self01;
        float _t6 = maxY * _self01;
        float _t7 = minZ * _self02;
        float _t8 = maxZ * _self02;
        float _t9 = minX * _self10;
        float _t10 = maxX * _self10;
        float _t11 = minY * _self11;
        float _t12 = maxY * _self11;
        float _t13 = minZ * _self12;
        float _t14 = maxZ * _self12;
        float _t15 = minX * _self20;
        float _t16 = maxX * _self20;
        float _t17 = minY * _self21;
        float _t18 = maxY * _self21;
        float _t19 = minZ * _self22;
        float _t20 = maxZ * _self22;
        if (java.lang.Math.min(java.lang.Math.min(maxX - minX, maxY - minY), maxZ - minZ) < 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, Float.POSITIVE_INFINITY);
            UnsafeOpsHolder.U.putFloat(dest + 4L, Float.POSITIVE_INFINITY);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Float.POSITIVE_INFINITY);
            UnsafeOpsHolder.U.putFloat(dest + 12L, Float.NEGATIVE_INFINITY);
            UnsafeOpsHolder.U.putFloat(dest + 16L, Float.NEGATIVE_INFINITY);
            UnsafeOpsHolder.U.putFloat(dest + 20L, Float.NEGATIVE_INFINITY);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, _self03 + java.lang.Math.min(_t3, _t4) + java.lang.Math.min(_t5, _t6) + java.lang.Math.min(_t7, _t8));
            UnsafeOpsHolder.U.putFloat(dest + 4L, _self13 + java.lang.Math.min(_t9, _t10) + java.lang.Math.min(_t11, _t12) + java.lang.Math.min(_t13, _t14));
            UnsafeOpsHolder.U.putFloat(dest + 8L, _self23 + java.lang.Math.min(_t15, _t16) + java.lang.Math.min(_t17, _t18) + java.lang.Math.min(_t19, _t20));
            UnsafeOpsHolder.U.putFloat(dest + 12L, _self03 + java.lang.Math.max(_t3, _t4) + java.lang.Math.max(_t5, _t6) + java.lang.Math.max(_t7, _t8));
            UnsafeOpsHolder.U.putFloat(dest + 16L, _self13 + java.lang.Math.max(_t9, _t10) + java.lang.Math.max(_t11, _t12) + java.lang.Math.max(_t13, _t14));
            UnsafeOpsHolder.U.putFloat(dest + 20L, _self23 + java.lang.Math.max(_t15, _t16) + java.lang.Math.max(_t17, _t18) + java.lang.Math.max(_t19, _t20));
        }
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, float vX, float vY, float vZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, float vX, float vY, float vZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, vX, Math.fma(_self01, vY, Math.fma(_self02, vZ, _self03))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, vX, Math.fma(_self11, vY, Math.fma(_self12, vZ, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self20, vX, Math.fma(_self21, vY, Math.fma(_self22, vZ, _self23))));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self03 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 24L);
        float _self13 = UnsafeOpsHolder.U.getFloat(src + 28L);
        float _self20 = UnsafeOpsHolder.U.getFloat(src + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(src + 36L);
        float _self22 = UnsafeOpsHolder.U.getFloat(src + 40L);
        float _self23 = UnsafeOpsHolder.U.getFloat(src + 44L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _vx, Math.fma(_self01, _vy, Math.fma(_self02, _vz, _self03))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, Math.fma(_self12, _vz, _self13))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self20, _vx, Math.fma(_self21, _vy, Math.fma(_self22, _vz, _self23))));
        return dest;
    }

    public static void transformPosition_unsafe(long _destBase, long _matrixBase, long _pointsBase, int count) {
        float _m00 = UnsafeOpsHolder.U.getFloat(_matrixBase);
        float _m01 = UnsafeOpsHolder.U.getFloat(_matrixBase + 4L);
        float _m02 = UnsafeOpsHolder.U.getFloat(_matrixBase + 8L);
        float _m03 = UnsafeOpsHolder.U.getFloat(_matrixBase + 12L);
        float _m10 = UnsafeOpsHolder.U.getFloat(_matrixBase + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(_matrixBase + 20L);
        float _m12 = UnsafeOpsHolder.U.getFloat(_matrixBase + 24L);
        float _m13 = UnsafeOpsHolder.U.getFloat(_matrixBase + 28L);
        float _m20 = UnsafeOpsHolder.U.getFloat(_matrixBase + 32L);
        float _m21 = UnsafeOpsHolder.U.getFloat(_matrixBase + 36L);
        float _m22 = UnsafeOpsHolder.U.getFloat(_matrixBase + 40L);
        float _m23 = UnsafeOpsHolder.U.getFloat(_matrixBase + 44L);
        transformPosition_unsafe_s4e1e3b59_1(_destBase, _pointsBase, count, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23);
    }

    /** Piece 2 of {@code transformPosition_unsafe}, split to fit the inline budget; reached only through it. */
    private static void transformPosition_unsafe_s4e1e3b59_1(long _destBase, long _pointsBase, int count, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23) {
        for (int _i = 0; _i < count; _i++) {
            long _pb = _pointsBase + _i * 12L;
            long _db = _destBase + _i * 12L;
            float px = UnsafeOpsHolder.U.getFloat(_pb), py = UnsafeOpsHolder.U.getFloat(_pb + 4L), pz = UnsafeOpsHolder.U.getFloat(_pb + 8L);
            UnsafeOpsHolder.U.putFloat(_db, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            UnsafeOpsHolder.U.putFloat(_db + 4L, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            UnsafeOpsHolder.U.putFloat(_db + 8L, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
    }

    public static void transformPosition_unsafe(long _destBase, long _destStride, long _matrixBase, long _pointsBase, long _pointsStride, int count) {
        float _m00 = UnsafeOpsHolder.U.getFloat(_matrixBase);
        float _m01 = UnsafeOpsHolder.U.getFloat(_matrixBase + 4L);
        float _m02 = UnsafeOpsHolder.U.getFloat(_matrixBase + 8L);
        float _m03 = UnsafeOpsHolder.U.getFloat(_matrixBase + 12L);
        float _m10 = UnsafeOpsHolder.U.getFloat(_matrixBase + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(_matrixBase + 20L);
        float _m12 = UnsafeOpsHolder.U.getFloat(_matrixBase + 24L);
        float _m13 = UnsafeOpsHolder.U.getFloat(_matrixBase + 28L);
        float _m20 = UnsafeOpsHolder.U.getFloat(_matrixBase + 32L);
        float _m21 = UnsafeOpsHolder.U.getFloat(_matrixBase + 36L);
        float _m22 = UnsafeOpsHolder.U.getFloat(_matrixBase + 40L);
        float _m23 = UnsafeOpsHolder.U.getFloat(_matrixBase + 44L);
        transformPosition_unsafe_s31cdd3b8_1(_destBase, _destStride, _pointsBase, _pointsStride, count, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23);
    }

    /** Piece 2 of {@code transformPosition_unsafe}, split to fit the inline budget; reached only through it. */
    private static void transformPosition_unsafe_s31cdd3b8_1(long _destBase, long _destStride, long _pointsBase, long _pointsStride, int count, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23) {
        for (int _i = 0; _i < count; _i++) {
            long _pb = _pointsBase + _i * _pointsStride;
            long _db = _destBase + _i * _destStride;
            float px = UnsafeOpsHolder.U.getFloat(_pb), py = UnsafeOpsHolder.U.getFloat(_pb + 4L), pz = UnsafeOpsHolder.U.getFloat(_pb + 8L);
            UnsafeOpsHolder.U.putFloat(_db, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            UnsafeOpsHolder.U.putFloat(_db + 4L, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            UnsafeOpsHolder.U.putFloat(_db + 8L, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
    }

    public static void transformDirection_unsafe(long _destBase, long _matrixBase, long _pointsBase, int count) {
        float _m00 = UnsafeOpsHolder.U.getFloat(_matrixBase);
        float _m01 = UnsafeOpsHolder.U.getFloat(_matrixBase + 4L);
        float _m02 = UnsafeOpsHolder.U.getFloat(_matrixBase + 8L);
        float _m10 = UnsafeOpsHolder.U.getFloat(_matrixBase + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(_matrixBase + 20L);
        float _m12 = UnsafeOpsHolder.U.getFloat(_matrixBase + 24L);
        float _m20 = UnsafeOpsHolder.U.getFloat(_matrixBase + 32L);
        float _m21 = UnsafeOpsHolder.U.getFloat(_matrixBase + 36L);
        float _m22 = UnsafeOpsHolder.U.getFloat(_matrixBase + 40L);
        for (int _i = 0; _i < count; _i++) {
            long _pb = _pointsBase + _i * 12L;
            long _db = _destBase + _i * 12L;
            float px = UnsafeOpsHolder.U.getFloat(_pb), py = UnsafeOpsHolder.U.getFloat(_pb + 4L), pz = UnsafeOpsHolder.U.getFloat(_pb + 8L);
            UnsafeOpsHolder.U.putFloat(_db, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            UnsafeOpsHolder.U.putFloat(_db + 4L, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            UnsafeOpsHolder.U.putFloat(_db + 8L, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
    }

    public static void transformDirection_unsafe(long _destBase, long _destStride, long _matrixBase, long _pointsBase, long _pointsStride, int count) {
        float _m00 = UnsafeOpsHolder.U.getFloat(_matrixBase);
        float _m01 = UnsafeOpsHolder.U.getFloat(_matrixBase + 4L);
        float _m02 = UnsafeOpsHolder.U.getFloat(_matrixBase + 8L);
        float _m10 = UnsafeOpsHolder.U.getFloat(_matrixBase + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(_matrixBase + 20L);
        float _m12 = UnsafeOpsHolder.U.getFloat(_matrixBase + 24L);
        float _m20 = UnsafeOpsHolder.U.getFloat(_matrixBase + 32L);
        float _m21 = UnsafeOpsHolder.U.getFloat(_matrixBase + 36L);
        float _m22 = UnsafeOpsHolder.U.getFloat(_matrixBase + 40L);
        for (int _i = 0; _i < count; _i++) {
            long _pb = _pointsBase + _i * _pointsStride;
            long _db = _destBase + _i * _destStride;
            float px = UnsafeOpsHolder.U.getFloat(_pb), py = UnsafeOpsHolder.U.getFloat(_pb + 4L), pz = UnsafeOpsHolder.U.getFloat(_pb + 8L);
            UnsafeOpsHolder.U.putFloat(_db, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            UnsafeOpsHolder.U.putFloat(_db + 4L, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            UnsafeOpsHolder.U.putFloat(_db + 8L, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
    }

    public static void lerpComposeTRSMul_unsafe(long _destBase, long _t1Base, long _t2Base, long _q1Base, long _q2Base, long _s1Base, long _s2Base, long _mBase, float alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            long _t1o = _t1Base + _i * 12L;
            long _t2o = _t2Base + _i * 12L;
            long _q1o = _q1Base + _i * 16L;
            long _q2o = _q2Base + _i * 16L;
            long _s1o = _s1Base + _i * 12L;
            long _s2o = _s2Base + _i * 12L;
            long _mo = _mBase + _i * 48L;
            long _do = _destBase + _i * 48L;
            float _ax = UnsafeOpsHolder.U.getFloat(_t1o), _ay = UnsafeOpsHolder.U.getFloat(_t1o + 4L), _az = UnsafeOpsHolder.U.getFloat(_t1o + 8L);
            float _tx = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_t2o) - _ax, _ax);
            float _ty = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_t2o + 4L) - _ay, _ay);
            float _tz = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_t2o + 8L) - _az, _az);
            float _bx = UnsafeOpsHolder.U.getFloat(_s1o), _by = UnsafeOpsHolder.U.getFloat(_s1o + 4L), _bz = UnsafeOpsHolder.U.getFloat(_s1o + 8L);
            float _sx = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_s2o) - _bx, _bx);
            float _sy = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_s2o + 4L) - _by, _by);
            float _sz = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_s2o + 8L) - _bz, _bz);
            float _ux = UnsafeOpsHolder.U.getFloat(_q1o), _uy = UnsafeOpsHolder.U.getFloat(_q1o + 4L), _uz = UnsafeOpsHolder.U.getFloat(_q1o + 8L), _uw = UnsafeOpsHolder.U.getFloat(_q1o + 12L);
            float _vx = UnsafeOpsHolder.U.getFloat(_q2o), _vy = UnsafeOpsHolder.U.getFloat(_q2o + 4L), _vz = UnsafeOpsHolder.U.getFloat(_q2o + 8L), _vw = UnsafeOpsHolder.U.getFloat(_q2o + 12L);
            float _dot = Math.fma(_uw, _vw, Math.fma(_uz, _vz, Math.fma(_ux, _vx, _uy * _vy)));
            float _wx = _dot < 0.0f ? -_vx : _vx, _wy = _dot < 0.0f ? -_vy : _vy, _wz = _dot < 0.0f ? -_vz : _vz, _ww = _dot < 0.0f ? -_vw : _vw;
            float _rx = Math.fma(alpha, _wx - _ux, _ux);
            float _ry = Math.fma(alpha, _wy - _uy, _uy);
            float _rz = Math.fma(alpha, _wz - _uz, _uz);
            float _rw = Math.fma(alpha, _ww - _uw, _uw);
            float _len2 = (_rx * _rx + _ry * _ry) + (_rz * _rz + _rw * _rw);
            float _ninv = _len2 > 0.0f ? 1.0f / (float) java.lang.Math.sqrt(_len2) : 0.0f;
            float _qx = _rx * _ninv, _qy = _ry * _ninv, _qz = _rz * _ninv, _qw = _rw * _ninv;
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            float _m00 = UnsafeOpsHolder.U.getFloat(_mo), _m01 = UnsafeOpsHolder.U.getFloat(_mo + 4L), _m02 = UnsafeOpsHolder.U.getFloat(_mo + 8L), _m03 = UnsafeOpsHolder.U.getFloat(_mo + 12L);
            float _m10 = UnsafeOpsHolder.U.getFloat(_mo + 16L), _m11 = UnsafeOpsHolder.U.getFloat(_mo + 20L), _m12 = UnsafeOpsHolder.U.getFloat(_mo + 24L), _m13 = UnsafeOpsHolder.U.getFloat(_mo + 28L);
            float _m20 = UnsafeOpsHolder.U.getFloat(_mo + 32L), _m21 = UnsafeOpsHolder.U.getFloat(_mo + 36L), _m22 = UnsafeOpsHolder.U.getFloat(_mo + 40L), _m23 = UnsafeOpsHolder.U.getFloat(_mo + 44L);
            float _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            float _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            float _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            UnsafeOpsHolder.U.putFloat(_do, _e00);
            UnsafeOpsHolder.U.putFloat(_do + 4L, _e01);
            UnsafeOpsHolder.U.putFloat(_do + 8L, _e02);
            UnsafeOpsHolder.U.putFloat(_do + 12L, _e03);
            UnsafeOpsHolder.U.putFloat(_do + 16L, _e10);
            UnsafeOpsHolder.U.putFloat(_do + 20L, _e11);
            UnsafeOpsHolder.U.putFloat(_do + 24L, _e12);
            UnsafeOpsHolder.U.putFloat(_do + 28L, _e13);
            UnsafeOpsHolder.U.putFloat(_do + 32L, _e20);
            UnsafeOpsHolder.U.putFloat(_do + 36L, _e21);
            UnsafeOpsHolder.U.putFloat(_do + 40L, _e22);
            UnsafeOpsHolder.U.putFloat(_do + 44L, _e23);
        }
    }

    public static void composeTRSMul_unsafe(long _destBase, long _translationBase, long _rotationBase, long _scaleBase, long _mBase, int count) {
        for (int _i = 0; _i < count; _i++) {
            long _translationo = _translationBase + _i * 12L;
            long _rotationo = _rotationBase + _i * 16L;
            long _scaleo = _scaleBase + _i * 12L;
            long _mo = _mBase + _i * 48L;
            long _do = _destBase + _i * 48L;
            float _tx = UnsafeOpsHolder.U.getFloat(_translationo), _ty = UnsafeOpsHolder.U.getFloat(_translationo + 4L), _tz = UnsafeOpsHolder.U.getFloat(_translationo + 8L);
            float _sx = UnsafeOpsHolder.U.getFloat(_scaleo), _sy = UnsafeOpsHolder.U.getFloat(_scaleo + 4L), _sz = UnsafeOpsHolder.U.getFloat(_scaleo + 8L);
            float _qx = UnsafeOpsHolder.U.getFloat(_rotationo), _qy = UnsafeOpsHolder.U.getFloat(_rotationo + 4L), _qz = UnsafeOpsHolder.U.getFloat(_rotationo + 8L), _qw = UnsafeOpsHolder.U.getFloat(_rotationo + 12L);
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            float _m00 = UnsafeOpsHolder.U.getFloat(_mo), _m01 = UnsafeOpsHolder.U.getFloat(_mo + 4L), _m02 = UnsafeOpsHolder.U.getFloat(_mo + 8L), _m03 = UnsafeOpsHolder.U.getFloat(_mo + 12L);
            float _m10 = UnsafeOpsHolder.U.getFloat(_mo + 16L), _m11 = UnsafeOpsHolder.U.getFloat(_mo + 20L), _m12 = UnsafeOpsHolder.U.getFloat(_mo + 24L), _m13 = UnsafeOpsHolder.U.getFloat(_mo + 28L);
            float _m20 = UnsafeOpsHolder.U.getFloat(_mo + 32L), _m21 = UnsafeOpsHolder.U.getFloat(_mo + 36L), _m22 = UnsafeOpsHolder.U.getFloat(_mo + 40L), _m23 = UnsafeOpsHolder.U.getFloat(_mo + 44L);
            float _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            float _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            float _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            UnsafeOpsHolder.U.putFloat(_do, _e00);
            UnsafeOpsHolder.U.putFloat(_do + 4L, _e01);
            UnsafeOpsHolder.U.putFloat(_do + 8L, _e02);
            UnsafeOpsHolder.U.putFloat(_do + 12L, _e03);
            UnsafeOpsHolder.U.putFloat(_do + 16L, _e10);
            UnsafeOpsHolder.U.putFloat(_do + 20L, _e11);
            UnsafeOpsHolder.U.putFloat(_do + 24L, _e12);
            UnsafeOpsHolder.U.putFloat(_do + 28L, _e13);
            UnsafeOpsHolder.U.putFloat(_do + 32L, _e20);
            UnsafeOpsHolder.U.putFloat(_do + 36L, _e21);
            UnsafeOpsHolder.U.putFloat(_do + 40L, _e22);
            UnsafeOpsHolder.U.putFloat(_do + 44L, _e23);
        }
    }

    public static void composeTRSMulPadded_unsafe(long _destBase, long _translationBase, long _rotationBase, long _scaleBase, long _mBase) {
        float _qx = UnsafeOpsHolder.U.getFloat(_rotationBase), _qy = UnsafeOpsHolder.U.getFloat(_rotationBase + 4L), _qz = UnsafeOpsHolder.U.getFloat(_rotationBase + 8L), _qw = UnsafeOpsHolder.U.getFloat(_rotationBase + 12L);
        float _tx = UnsafeOpsHolder.U.getFloat(_translationBase), _ty = UnsafeOpsHolder.U.getFloat(_translationBase + 4L), _tz = UnsafeOpsHolder.U.getFloat(_translationBase + 8L);
        float _sx = UnsafeOpsHolder.U.getFloat(_scaleBase), _sy = UnsafeOpsHolder.U.getFloat(_scaleBase + 4L), _sz = UnsafeOpsHolder.U.getFloat(_scaleBase + 8L);
        float _m00 = UnsafeOpsHolder.U.getFloat(_mBase), _m01 = UnsafeOpsHolder.U.getFloat(_mBase + 4L), _m02 = UnsafeOpsHolder.U.getFloat(_mBase + 8L), _m03 = UnsafeOpsHolder.U.getFloat(_mBase + 12L);
        float _m10 = UnsafeOpsHolder.U.getFloat(_mBase + 16L), _m11 = UnsafeOpsHolder.U.getFloat(_mBase + 20L), _m12 = UnsafeOpsHolder.U.getFloat(_mBase + 24L), _m13 = UnsafeOpsHolder.U.getFloat(_mBase + 28L);
        composeTRSMulPadded_unsafe_s40a488e9_1(_destBase, _mBase, _qx, _qy, _qz, _qw, _tx, _ty, _tz, _sx, _sy, _sz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13);
    }

    /** Piece 2 of {@code composeTRSMulPadded_unsafe}, split to fit the inline budget; reached only through it. */
    private static void composeTRSMulPadded_unsafe_s40a488e9_1(long _destBase, long _mBase, float _qx, float _qy, float _qz, float _qw, float _tx, float _ty, float _tz, float _sx, float _sy, float _sz, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13) {
        float _m20 = UnsafeOpsHolder.U.getFloat(_mBase + 32L), _m21 = UnsafeOpsHolder.U.getFloat(_mBase + 36L), _m22 = UnsafeOpsHolder.U.getFloat(_mBase + 40L), _m23 = UnsafeOpsHolder.U.getFloat(_mBase + 44L);
        float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        composeTRSMulPadded_unsafe_s40a488e9_2(_destBase, _tx, _ty, _tz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t00, _t01, _t02, _t10, _t11, _t12, _t20, _t21, _t22);
    }

    /** Piece 3 of {@code composeTRSMulPadded_unsafe}, split to fit the inline budget; reached only through it. */
    private static void composeTRSMulPadded_unsafe_s40a488e9_2(long _destBase, float _tx, float _ty, float _tz, float _m00, float _m01, float _m02, float _m03, float _m10, float _m11, float _m12, float _m13, float _m20, float _m21, float _m22, float _m23, float _t00, float _t01, float _t02, float _t10, float _t11, float _t12, float _t20, float _t21, float _t22) {
        float _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
        float _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
        float _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
        UnsafeOpsHolder.U.putFloat(_destBase, _e00);
        UnsafeOpsHolder.U.putFloat(_destBase + 4L, _e01);
        composeTRSMulPadded_unsafe_s40a488e9_3(_destBase, _e02, _e03, _e10, _e11, _e12, _e13, _e20, _e21, _e22, _e23);
    }

    /** Piece 4 of {@code composeTRSMulPadded_unsafe}, split to fit the inline budget; reached only through it. */
    private static void composeTRSMulPadded_unsafe_s40a488e9_3(long _destBase, float _e02, float _e03, float _e10, float _e11, float _e12, float _e13, float _e20, float _e21, float _e22, float _e23) {
        UnsafeOpsHolder.U.putFloat(_destBase + 8L, _e02);
        UnsafeOpsHolder.U.putFloat(_destBase + 12L, _e03);
        UnsafeOpsHolder.U.putFloat(_destBase + 16L, _e10);
        UnsafeOpsHolder.U.putFloat(_destBase + 20L, _e11);
        UnsafeOpsHolder.U.putFloat(_destBase + 24L, _e12);
        UnsafeOpsHolder.U.putFloat(_destBase + 28L, _e13);
        UnsafeOpsHolder.U.putFloat(_destBase + 32L, _e20);
        UnsafeOpsHolder.U.putFloat(_destBase + 36L, _e21);
        UnsafeOpsHolder.U.putFloat(_destBase + 40L, _e22);
        UnsafeOpsHolder.U.putFloat(_destBase + 44L, _e23);
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
