// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3x4Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double3x4Ops} and its sibling kernel units. Not public API.
 */
public final class Double3x4OpsKernelsAddress {
    private Double3x4OpsKernelsAddress() {}

    public static long getColumn_unsafe(long dest, long src, int col) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; _idxSw2 = _self20; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; _idxSw2 = _self21; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; _idxSw2 = _self22; break;
            case 3: _idxSw0 = _self03; _idxSw1 = _self13; _idxSw2 = _self23; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        UnsafeOpsHolder.U.putDouble(dest, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _idxSw2);
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t1 = Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 < Math.fma(_self02, _self02, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t1 = Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 < Math.fma(_self01, _self01, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t1 = Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 < Math.fma(_self12, _self12, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self11));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t1 = Math.fma(_self11, _self11, _self12 * _self12);
        if (_t1 < Math.fma(_self10, _self10, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t1 = Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 < Math.fma(_self21, _self21, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t1 = Math.fma(_self21, _self21, _self22 * _self22);
        if (_t1 < Math.fma(_self20, _self20, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getNormalizedRotation_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t6 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        double _t7 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        double _t8 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t21, _t23, _t27;
        if (_t6 != 0.0) {
            _t21 = _self01 * _t9;
            _t23 = _self11 * _t9;
            _t27 = _self21 * _t9;
        } else {
            _t21 = 0.0;
            _t23 = 0.0;
            _t27 = 0.0;
        }
        double _t22, _t24, _t26;
        if (_t7 != 0.0) {
            _t22 = _self12 * _t10;
            _t24 = _self02 * _t10;
            _t26 = _self22 * _t10;
        } else {
            _t22 = 0.0;
            _t24 = 0.0;
            _t26 = 0.0;
        }
        return getNormalizedRotation_unsafe_sa46d4105_1(dest, _self00, _self10, _self20, _t8, (1.0 / java.lang.Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_sa46d4105_1(long dest, double _self00, double _self10, double _self20, double _t8, double _t11, double _t21, double _t23, double _t27, double _t22, double _t24, double _t26) {
        double _t25, _t28, _t29;
        if (_t8 != 0.0) {
            _t25 = _self20 * _t11;
            _t28 = _self00 * _t11;
            _t29 = _self10 * _t11;
        } else {
            _t25 = 0.0;
            _t28 = 0.0;
            _t29 = 0.0;
        }
        double _t49, _t50, _t51;
        if (Math.fma(Math.fma(_t21, _t22, -(_t23 * _t24)), _t25, Math.fma(Math.fma(_t23, _t26, -(_t27 * _t22)), _t28, Math.fma(_t27, _t24, -(_t21 * _t26)) * _t29)) < 0.0) {
            _t49 = -_t28;
            _t50 = -_t29;
            _t51 = -_t25;
        } else {
            _t49 = _t28;
            _t50 = _t29;
            _t51 = _t25;
        }
        double _t52 = _t49 + _t23;
        double _t58 = _t52 + _t26;
        double _t62 = 1.0 + _t58;
        double _t63 = 1.0 + (_t49 - (_t23 + _t26));
        double _t64 = 1.0 + (_t23 - (_t49 + _t26));
        double _t65 = 1.0 + (_t26 - _t52);
        return getNormalizedRotation_unsafe_sa46d4105_2(dest, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5 * (1.0 / java.lang.Math.sqrt(_t62)), 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_sa46d4105_2(long dest, double _t23, double _t26, double _t36, double _t39, double _t49, double _t53, double _t55, double _t56, double _t57, double _t58, double _t62, double _t63, double _t64, double _t65, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t58 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t36);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t56);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t57);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > java.lang.Math.max(_t23, _t26)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t63));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t53);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t55);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t53);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t64));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t39);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t56);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t55);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t39);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t65));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static long getRow_unsafe(long dest, long src, int row) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        double _idxSw3;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; _idxSw3 = _self03; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; _idxSw3 = _self13; break;
            case 2: _idxSw0 = _self20; _idxSw1 = _self21; _idxSw2 = _self22; _idxSw3 = _self23; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        UnsafeOpsHolder.U.putDouble(dest, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _idxSw2);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _idxSw3);
        return dest;
    }

    public static long getScale_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    public static long getTranslation_unsafe(long dest, long src) {
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        UnsafeOpsHolder.U.putDouble(dest, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self23);
        return dest;
    }

    public static long getUnnormalizedRotation_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t0 = _self00 + _self11;
        double _t10 = _self22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_self00 - (_self11 + _self22));
        double _t16 = 1.0 + (_self11 - (_self00 + _self22));
        double _t17 = 1.0 + (_self22 - _t0);
        return getUnnormalizedRotation_unsafe_sd9cfa7f4_1(dest, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long getUnnormalizedRotation_unsafe_sd9cfa7f4_1(long dest, double _self00, double _self11, double _self22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t1);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t7);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t9);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t15));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t4);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t6);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t4);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t16));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t7);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t6);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t17));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static long invNegativeX_unsafe(long dest, long src) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.invNegativeX_degenerate(dest, src);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putDouble(dest, -(_t6 * _t13));
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t8 * _t13));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t7 * _t13));
        return dest;
    }

    public static long invNegativeX_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.invNegativeX_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invNegativeX_degenerate_unsafe(long dest, long src) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t8 = _self10 * _t0;
        double _t9 = _self21 * _t1;
        double _t10 = _self11 * _t0;
        double _t11 = _self20 * _t1;
        double _t12 = _self22 * _t1;
        double _t13 = _self12 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        return invNegativeX_degenerate_unsafe_s4d695f9f_1(dest, _t20, _t21, _t22, _t25, (1.0 / java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code invNegativeX_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invNegativeX_degenerate_unsafe_s4d695f9f_1(long dest, double _t20, double _t21, double _t22, double _t25, double _t26) {
        if (_t25 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t21 * _t26));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t22 * _t26));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t20 * _t26));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    public static long invNegativeY_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        double _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        double _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.invNegativeY_degenerate(dest, src);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putDouble(dest, -(_t6 * _t13));
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t8 * _t13));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t7 * _t13));
        return dest;
    }

    public static long invNegativeY_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.invNegativeY_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invNegativeY_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t0 = unitScale(_self00, _self01, _self02);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t8 = _self01 * _t0;
        double _t9 = _self20 * _t1;
        double _t10 = _self00 * _t0;
        double _t11 = _self21 * _t1;
        double _t12 = _self22 * _t1;
        double _t13 = _self02 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        return invNegativeY_degenerate_unsafe_scc46b860_1(dest, _t20, _t21, _t22, _t25, (1.0 / java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code invNegativeY_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invNegativeY_degenerate_unsafe_scc46b860_1(long dest, double _t20, double _t21, double _t22, double _t25, double _t26) {
        if (_t25 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t22 * _t26));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t21 * _t26));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t20 * _t26));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    public static long invNegativeZ_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        double _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        double _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.invNegativeZ_degenerate(dest, src);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putDouble(dest, -(_t6 * _t13));
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t8 * _t13));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t7 * _t13));
        return dest;
    }

    public static long invNegativeZ_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.invNegativeZ_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invNegativeZ_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _t0 = unitScale(_self00, _self01, _self02);
        double _t1 = unitScale(_self10, _self11, _self12);
        double _t8 = _self00 * _t0;
        double _t9 = _self11 * _t1;
        double _t10 = _self01 * _t0;
        double _t11 = _self10 * _t1;
        double _t12 = _self12 * _t1;
        double _t13 = _self02 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        return invNegativeZ_degenerate_unsafe_s632f2441_1(dest, _t20, _t21, _t22, _t25, (1.0 / java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code invNegativeZ_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invNegativeZ_degenerate_unsafe_s632f2441_1(long dest, double _t20, double _t21, double _t22, double _t25, double _t26) {
        if (_t25 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t21 * _t26));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t22 * _t26));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t20 * _t26));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    public static long invNormalizedNegativeX_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, -_self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self02);
        return dest;
    }

    public static long invNormalizedNegativeY_unsafe(long dest, long src) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        UnsafeOpsHolder.U.putDouble(dest, -_self10);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self11);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self12);
        return dest;
    }

    public static long invNormalizedNegativeZ_unsafe(long dest, long src) {
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        UnsafeOpsHolder.U.putDouble(dest, -_self20);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self21);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self22);
        return dest;
    }

    public static long invNormalizedPositiveX_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02);
        return dest;
    }

    public static long invNormalizedPositiveY_unsafe(long dest, long src) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        UnsafeOpsHolder.U.putDouble(dest, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self12);
        return dest;
    }

    public static long invNormalizedPositiveZ_unsafe(long dest, long src) {
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        UnsafeOpsHolder.U.putDouble(dest, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self22);
        return dest;
    }

    public static long invPositiveX_unsafe(long dest, long src) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.invPositiveX_degenerate(dest, src);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putDouble(dest, _t6 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t8 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t7 * _t13);
        return dest;
    }

    public static long invPositiveX_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.invPositiveX_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invPositiveX_degenerate_unsafe(long dest, long src) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t8 = _self10 * _t0;
        double _t9 = _self21 * _t1;
        double _t10 = _self11 * _t0;
        double _t11 = _self20 * _t1;
        double _t12 = _self22 * _t1;
        double _t13 = _self12 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t21 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t22 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t20 * _t26);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long invPositiveY_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        double _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        double _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.invPositiveY_degenerate(dest, src);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putDouble(dest, _t6 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t8 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t7 * _t13);
        return dest;
    }

    public static long invPositiveY_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.invPositiveY_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invPositiveY_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t0 = unitScale(_self00, _self01, _self02);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t8 = _self01 * _t0;
        double _t9 = _self20 * _t1;
        double _t10 = _self00 * _t0;
        double _t11 = _self21 * _t1;
        double _t12 = _self22 * _t1;
        double _t13 = _self02 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t22 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t21 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t20 * _t26);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long invPositiveZ_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        double _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        double _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.invPositiveZ_degenerate(dest, src);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putDouble(dest, _t6 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t8 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t7 * _t13);
        return dest;
    }

    public static long invPositiveZ_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.invPositiveZ_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invPositiveZ_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _t0 = unitScale(_self00, _self01, _self02);
        double _t1 = unitScale(_self10, _self11, _self12);
        double _t8 = _self00 * _t0;
        double _t9 = _self11 * _t1;
        double _t10 = _self01 * _t0;
        double _t11 = _self10 * _t1;
        double _t12 = _self12 * _t1;
        double _t13 = _self02 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t21 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t22 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t20 * _t26);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long negativeX_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_self00 * _t3));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_self10 * _t3));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_self20 * _t3));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    public static long negativeY_unsafe(long dest, long src) {
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_self01 * _t3));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_self11 * _t3));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_self21 * _t3));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    public static long negativeZ_unsafe(long dest, long src) {
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_self02 * _t3));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_self12 * _t3));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_self22 * _t3));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    public static long normalizedNegativeX_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, -_self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self20);
        return dest;
    }

    public static long normalizedNegativeY_unsafe(long dest, long src) {
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        UnsafeOpsHolder.U.putDouble(dest, -_self01);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self11);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self21);
        return dest;
    }

    public static long normalizedNegativeZ_unsafe(long dest, long src) {
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        UnsafeOpsHolder.U.putDouble(dest, -_self02);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self12);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self22);
        return dest;
    }

    public static long normalizedPositiveX_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        return dest;
    }

    public static long normalizedPositiveY_unsafe(long dest, long src) {
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        UnsafeOpsHolder.U.putDouble(dest, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self21);
        return dest;
    }

    public static long normalizedPositiveZ_unsafe(long dest, long src) {
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        UnsafeOpsHolder.U.putDouble(dest, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self22);
        return dest;
    }

    public static long origin_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        UnsafeOpsHolder.U.putDouble(dest, -Math.fma(_self20, _self23, Math.fma(_self00, _self03, _self10 * _self13)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, -Math.fma(_self21, _self23, Math.fma(_self01, _self03, _self11 * _self13)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -Math.fma(_self22, _self23, Math.fma(_self02, _self03, _self12 * _self13)));
        return dest;
    }

    public static long positiveX_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _self00 * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _self20 * _t3);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long positiveY_unsafe(long dest, long src) {
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _self01 * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _self11 * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _self21 * _t3);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long positiveZ_unsafe(long dest, long src) {
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _self02 * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _self12 * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _self22 * _t3);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static double determinant_unsafe(long src) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        return Math.fma(UnsafeOpsHolder.U.getDouble(src + 16L), Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(UnsafeOpsHolder.U.getDouble(src), Math.fma(_self11, _self22, -(_self12 * _self21)), -(UnsafeOpsHolder.U.getDouble(src + 8L) * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static double frobeniusNorm_unsafe(long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        return java.lang.Math.sqrt(Math.fma(_self00, _self00, Math.fma(_self01, _self01, _self02 * _self02)) + Math.fma(_self03, _self03, Math.fma(_self10, _self10, _self11 * _self11)) + (Math.fma(_self12, _self12, Math.fma(_self13, _self13, _self20 * _self20)) + Math.fma(_self21, _self21, Math.fma(_self22, _self22, _self23 * _self23))));
    }

    public static long invert_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        return invert_unsafe_s1ee1b72a_1(dest, src, _self00, _self01, _self02, _self03, _self10, _self12, _self13, _self20, _self22, _self23, Math.fma(_self11, _self22, -(_self12 * _self21)), Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(_self02, _self21, -(_self01 * _self22)), Math.fma(_self01, _self12, -(_self02 * _self11)), Math.fma(_self12, _self20, -(_self10 * _self22)), Math.fma(_self00, _self22, -(_self02 * _self20)), Math.fma(_self02, _self10, -(_self00 * _self12)), Math.fma(_self01, _self20, -(_self00 * _self21)), Math.fma(_self00, _self11, -(_self01 * _self10)));
    }

    /** Piece 2 of {@code invert_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invert_unsafe_s1ee1b72a_1(long dest, long src, double _self00, double _self01, double _self02, double _self03, double _self10, double _self12, double _self13, double _self20, double _self22, double _self23, double _t20, double _t21, double _t23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29) {
        double _t34 = Math.fma(_self02, _t21, Math.fma(_self00, _t20, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(java.lang.Math.abs(_t34) > 2.2250738585072014E-308 && java.lang.Math.abs(_t34) < 4.49423283715579E307)) return Double3x4OpsKernelsAddress.invert_degenerate(dest, src);
        double _t34_inv = 1.0 / _t34;
        UnsafeOpsHolder.U.putDouble(dest, _t20 * _t34_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t23 * _t34_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t24 * _t34_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -(Math.fma(_self23, _t24, Math.fma(_self03, _t20, _self13 * _t23)) * _t34_inv));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t25 * _t34_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t26 * _t34_inv);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t27 * _t34_inv);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -(Math.fma(_self23, _t27, Math.fma(_self03, _t25, _self13 * _t26)) * _t34_inv));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t21 * _t34_inv);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t28 * _t34_inv);
        return invert_unsafe_s1ee1b72a_2(dest, _self03, _self13, _self23, _t21, _t28, _t29, _t34_inv);
    }

    /** Piece 3 of {@code invert_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invert_unsafe_s1ee1b72a_2(long dest, double _self03, double _self13, double _self23, double _t21, double _t28, double _t29, double _t34_inv) {
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t29 * _t34_inv);
        UnsafeOpsHolder.U.putDouble(dest + 88L, -(Math.fma(_self23, _t29, Math.fma(_self03, _t21, _self13 * _t28)) * _t34_inv));
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t2 = unitScale(_self00, _self01, _self02);
        double _t15 = _self11 * _t0;
        double _t16 = _self22 * _t1;
        double _t17 = _self12 * _t0;
        double _t18 = _self21 * _t1;
        double _t19 = _self10 * _t0;
        double _t20 = _self20 * _t1;
        return invert_degenerate_unsafe_sbf3b0613_1(dest, _t0, _t1, _t2, _t15, _t16, _t17, _t18, _t19, _t20, _self02 * _t2, _self00 * _t2, _self01 * _t2, _self23 * _t1, _self13 * _t0, _self03 * _t2, Math.fma(_t15, _t16, -(_t17 * _t18)), Math.fma(_t19, _t18, -(_t15 * _t20)));
    }

    /** Piece 2 of {@code invert_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_sbf3b0613_1(long dest, double _t0, double _t1, double _t2, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t47, double _t48) {
        double _t50 = Math.fma(_t21, _t18, -(_t23 * _t16));
        double _t51 = Math.fma(_t23, _t17, -(_t21 * _t15));
        double _t52 = Math.fma(_t17, _t20, -(_t19 * _t16));
        double _t53 = Math.fma(_t22, _t16, -(_t21 * _t20));
        double _t60_inv = 1.0 / Math.fma(_t48, _t21, Math.fma(_t47, _t22, -(Math.fma(_t19, _t16, -(_t17 * _t20)) * _t23)));
        double _sp2 = _t1 * _t60_inv;
        double _sp1 = _t0 * _t60_inv;
        double _sp0 = _t2 * _t60_inv;
        UnsafeOpsHolder.U.putDouble(dest, _t47 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t50 * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t51 * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -(Math.fma(_t51, _t24, Math.fma(_t50, _t25, _t47 * _t26)) * _t60_inv));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t52 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t53 * _sp1);
        return invert_degenerate_unsafe_sbf3b0613_2(dest, _t24, _t25, _t26, _t48, _t52, _t53, Math.fma(_t21, _t19, -(_t22 * _t17)), Math.fma(_t23, _t20, -(_t22 * _t18)), Math.fma(_t22, _t15, -(_t23 * _t19)), _t60_inv, _sp2, _sp1, _sp0);
    }

    /** Piece 3 of {@code invert_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_sbf3b0613_2(long dest, double _t24, double _t25, double _t26, double _t48, double _t52, double _t53, double _t54, double _t55, double _t56, double _t60_inv, double _sp2, double _sp1, double _sp0) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t54 * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -(Math.fma(_t54, _t24, Math.fma(_t53, _t25, _t52 * _t26)) * _t60_inv));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t48 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t55 * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t56 * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 88L, -(Math.fma(_t56, _t24, Math.fma(_t55, _t25, _t48 * _t26)) * _t60_inv));
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other03 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other13 = UnsafeOpsHolder.U.getDouble(other + 56L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 64L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 72L);
        return invertProduct_unsafe_sec86c98d_4(dest, src, other, _self00, _self01, _self02, _self10, _self11, _self12, _self20, _self21, _self22, _other00, _other01, _other02, _other03, _other10, _other11, _other12, _other13, _other20, _other21);
    }

    /** Part 1 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_unsafe_sec86c98d_1(double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32) {
        return Math.fma(_t28, (Math.fma(_t29, _t26, -(_t30 * _t24))), Math.fma(_t31, (Math.fma(_t24, _t25, -(_t26 * _t27))), -(_t32 * Math.fma(_t29, _t25, -(_t30 * _t27)))));
    }

    /** Part 2 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_unsafe_sec86c98d_2(long dest, double _t24, double _t25, double _t26, double _t27, double _t28, double _t32, double _t33, double _t34, double _t35, double _t70) {
        double _t56 = Math.fma(_t24, _t25, -(_t26 * _t27));
        double _t59 = Math.fma(_t26, _t28, -(_t32 * _t25));
        double _t60 = Math.fma(_t32, _t27, -(_t24 * _t28));
        double _t70_inv = 1.0 / _t70;
        UnsafeOpsHolder.U.putDouble(dest, _t56 * _t70_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t59 * _t70_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t60 * _t70_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -(Math.fma(_t33, _t60, Math.fma(_t34, _t56, _t35 * _t59)) * _t70_inv));
    }

    /** Part 3 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_sec86c98d_3(long dest, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35, double _t70) {
        double _t57 = Math.fma(_t29, _t26, -(_t30 * _t24));
        double _t61 = Math.fma(_t30, _t27, -(_t29 * _t25));
        double _t62 = Math.fma(_t31, _t25, -(_t30 * _t28));
        double _t63 = Math.fma(_t29, _t28, -(_t31 * _t27));
        double _t64 = Math.fma(_t30, _t32, -(_t31 * _t26));
        double _t65 = Math.fma(_t31, _t24, -(_t29 * _t32));
        double _t70_inv = 1.0 / _t70;
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t61 * _t70_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t62 * _t70_inv);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t63 * _t70_inv);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -(Math.fma(_t33, _t63, Math.fma(_t34, _t61, _t35 * _t62)) * _t70_inv));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t57 * _t70_inv);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t64 * _t70_inv);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t65 * _t70_inv);
        UnsafeOpsHolder.U.putDouble(dest + 88L, -(Math.fma(_t33, _t65, Math.fma(_t34, _t57, _t35 * _t64)) * _t70_inv));
        return dest;
    }

    /** Piece 2 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_sec86c98d_4(long dest, long src, long other, double _self00, double _self01, double _self02, double _self10, double _self11, double _self12, double _self20, double _self21, double _self22, double _other00, double _other01, double _other02, double _other03, double _other10, double _other11, double _other12, double _other13, double _other20, double _other21) {
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 80L);
        double _other23 = UnsafeOpsHolder.U.getDouble(other + 88L);
        return invertProduct_unsafe_sec86c98d_5(dest, src, other, _self00, _self01, _self02, _self10, _self11, _self12, _self20, _self21, _self22, _other03, _other13, _other23, Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)), Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21)), Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21)), Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11)), Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01)), Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11)), Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21)), Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01)), Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01)));
    }

    /** Piece 3 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_sec86c98d_5(long dest, long src, long other, double _self00, double _self01, double _self02, double _self10, double _self11, double _self12, double _self20, double _self21, double _self22, double _other03, double _other13, double _other23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32) {
        double _t33 = Math.fma(_other03, _self20, Math.fma(_other13, _self21, Math.fma(_other23, _self22, UnsafeOpsHolder.U.getDouble(src + 88L))));
        double _t34 = Math.fma(_other03, _self00, Math.fma(_other13, _self01, Math.fma(_other23, _self02, UnsafeOpsHolder.U.getDouble(src + 24L))));
        double _t35 = Math.fma(_other03, _self10, Math.fma(_other13, _self11, Math.fma(_other23, _self12, UnsafeOpsHolder.U.getDouble(src + 56L))));
        double _t70 = invertProduct_unsafe_sec86c98d_1(_t24, _t25, _t26, _t27, _t28, _t29, _t30, _t31, _t32);
        if (!(java.lang.Math.abs(_t70) > 2.2250738585072014E-308 && java.lang.Math.abs(_t70) < 4.49423283715579E307)) return Double3x4OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        invertProduct_unsafe_sec86c98d_2(dest, _t24, _t25, _t26, _t27, _t28, _t32, _t33, _t34, _t35, _t70);
        return invertProduct_unsafe_sec86c98d_3(dest, _t24, _t25, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t33, _t34, _t35, _t70);
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other03 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other13 = UnsafeOpsHolder.U.getDouble(other + 56L);
        return invertProduct_degenerate_unsafe_s3fc4d1ee_1(dest, other, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other01, _other02, _other03, _other10, _other11, _other12, _other13);
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_s3fc4d1ee_1(long dest, long other, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other01, double _other02, double _other03, double _other10, double _other11, double _other12, double _other13) {
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 64L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 72L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 80L);
        double _other23 = UnsafeOpsHolder.U.getDouble(other + 88L);
        double _t24 = Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11));
        double _t25 = Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11));
        double _t26 = Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11));
        double _t27 = Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21));
        double _t28 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        double _t29 = Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21));
        return invertProduct_degenerate_unsafe_s3fc4d1ee_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other03, _other13, _other23, _t24, _t25, _t26, _t27, _t28, _t29, Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01)), Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01)), Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01)), unitScale(_t25, _t24, _t26), unitScale(_t28, _t29, _t27));
    }

    /** Piece 3 of {@code invertProduct_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_s3fc4d1ee_2(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other03, double _other13, double _other23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t36, double _t37) {
        double _t38 = unitScale(_t30, _t31, _t32);
        double _t48 = _t24 * _t36;
        double _t49 = _t27 * _t37;
        double _t50 = _t29 * _t37;
        double _t51 = _t26 * _t36;
        double _t52 = _t25 * _t36;
        double _t53 = _t28 * _t37;
        double _t54 = _t32 * _t38;
        double _t55 = _t30 * _t38;
        double _t56 = _t31 * _t38;
        return invertProduct_degenerate_unsafe_s3fc4d1ee_3(dest, _t36, _t37, _t38, _t49, _t51, _t52, _t53, _t54, _t55, _t56, Math.fma(_other03, _self20, Math.fma(_other13, _self21, Math.fma(_other23, _self22, _self23))) * _t37, Math.fma(_other03, _self00, Math.fma(_other13, _self01, Math.fma(_other23, _self02, _self03))) * _t38, Math.fma(_other03, _self10, Math.fma(_other13, _self11, Math.fma(_other23, _self12, _self13))) * _t36, Math.fma(_t48, _t49, -(_t50 * _t51)), Math.fma(_t52, _t50, -(_t53 * _t48)), Math.fma(_t50, _t54, -(_t56 * _t49)), Math.fma(_t56, _t51, -(_t48 * _t54)), Math.fma(_t53, _t51, -(_t52 * _t49)), Math.fma(_t55, _t49, -(_t53 * _t54)), Math.fma(_t52, _t54, -(_t55 * _t51)), Math.fma(_t53, _t56, -(_t55 * _t50)), Math.fma(_t55, _t48, -(_t52 * _t56)));
    }

    /** Piece 4 of {@code invertProduct_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_s3fc4d1ee_3(long dest, double _t36, double _t37, double _t38, double _t49, double _t51, double _t52, double _t53, double _t54, double _t55, double _t56, double _t60, double _t61, double _t62, double _t83, double _t84, double _t86, double _t87, double _t88, double _t89, double _t90, double _t91, double _t92) {
        double _t96_inv = 1.0 / Math.fma(_t84, _t54, Math.fma(_t83, _t55, -(Math.fma(_t52, _t49, -(_t53 * _t51)) * _t56)));
        double _sp2 = _t37 * _t96_inv;
        double _sp1 = _t36 * _t96_inv;
        double _sp0 = _t38 * _t96_inv;
        UnsafeOpsHolder.U.putDouble(dest, _t83 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t86 * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t87 * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -(Math.fma(_t87, _t60, Math.fma(_t83, _t61, _t86 * _t62)) * _t96_inv));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t88 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t89 * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t90 * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -(Math.fma(_t90, _t60, Math.fma(_t89, _t62, _t88 * _t61)) * _t96_inv));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t84 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t91 * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t92 * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 88L, -(Math.fma(_t92, _t60, Math.fma(_t84, _t61, _t91 * _t62)) * _t96_inv));
        return dest;
    }

    public static long transpose_unsafe(long dest, long src) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, _eself);
        }
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, _eother + _eself);
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double scalar) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, scalar * _eself);
        }
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, -_eself);
        }
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, _eself - _eother);
        }
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        for (int _i = 0; _i < 12; _i++) {
            double _ev = UnsafeOpsHolder.U.getDouble(v + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, _ev);
        }
        return dest;
    }

    public static long setMat3x3_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m20 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 24L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 32L);
        double _m21 = UnsafeOpsHolder.U.getDouble(m + 40L);
        double _m02 = UnsafeOpsHolder.U.getDouble(m + 48L);
        double _m12 = UnsafeOpsHolder.U.getDouble(m + 56L);
        double _m22 = UnsafeOpsHolder.U.getDouble(m + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _m00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _m01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _m02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _m10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _m11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _m12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _m20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _m21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _m22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long setMat4x4_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m20 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 32L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 40L);
        double _m21 = UnsafeOpsHolder.U.getDouble(m + 48L);
        double _m02 = UnsafeOpsHolder.U.getDouble(m + 64L);
        double _m12 = UnsafeOpsHolder.U.getDouble(m + 72L);
        double _m22 = UnsafeOpsHolder.U.getDouble(m + 80L);
        double _m03 = UnsafeOpsHolder.U.getDouble(m + 96L);
        double _m13 = UnsafeOpsHolder.U.getDouble(m + 104L);
        double _m23 = UnsafeOpsHolder.U.getDouble(m + 112L);
        UnsafeOpsHolder.U.putDouble(dest, _m00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _m01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _m02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _m03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _m10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _m11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _m12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _m13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _m20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _m21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _m22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _m23);
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, double tX, double tY, double tZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, tX);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, tY);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, tZ);
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, long t) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _tx = UnsafeOpsHolder.U.getDouble(t);
        double _ty = UnsafeOpsHolder.U.getDouble(t + 8L);
        double _tz = UnsafeOpsHolder.U.getDouble(t + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _tx);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _ty);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _tz);
        return dest;
    }

    public static long makeFromRigid_unsafe(long dest, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-2.0, Math.fma(rRY, rRY, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * Math.fma(rRX, rRY, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * Math.fma(rRX, rRZ, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, rTX);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 2.0 * Math.fma(rRX, rRY, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(-2.0, Math.fma(rRX, rRX, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * Math.fma(rRY, rRZ, -(rRX * rRW)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, rTY);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 2.0 * Math.fma(rRX, rRZ, -_t2));
        UnsafeOpsHolder.U.putDouble(dest + 72L, 2.0 * Math.fma(rRX, rRW, rRY * rRZ));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(-2.0, Math.fma(rRX, rRX, rRY * rRY), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 88L, rTZ);
        return dest;
    }

    public static long makeFromTransform_unsafe(long dest, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(tRX, tRY, -_t4) * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(tRX, tRZ, _t5) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 24L, tTX);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(tRX, tRY, _t4) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 56L, tTY);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(tRX, tRZ, -_t5) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        return makeFromTransform_unsafe_sf57b2f49_1(dest, tTZ, tRX, tRY, tSZ, _t2);
    }

    /** Piece 2 of {@code makeFromTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeFromTransform_unsafe_sf57b2f49_1(long dest, double tTZ, double tRX, double tRY, double tSZ, double _t2) {
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        UnsafeOpsHolder.U.putDouble(dest + 88L, tTZ);
        return dest;
    }

    public static long to3x3_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long to4x4_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22);
        return to4x4_unsafe_s575d5859_1(dest, _self03, _self13, _self23);
    }

    /** Piece 2 of {@code to4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long to4x4_unsafe_s575d5859_1(long dest, double _self03, double _self13, double _self23) {
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 96L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 104L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 112L, _self23);
        UnsafeOpsHolder.U.putDouble(dest + 120L, 1.0);
        return dest;
    }

    public static long toDualQuat_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t2 = 1.0 - _self00;
        double _t14 = _self22 + (_self00 + _self11);
        double _t15 = 1.0 + _t14;
        double _t17 = _self11 + (_t2 - _self22);
        double _t18 = _self22 + (_t2 - _self11);
        return toDualQuat_unsafe_s9f6c0bda_1(dest, _self00, _self03, _self11, _self13, _self22, _self23, -_self23, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t14, _t15, _self00 + (1.0 - _self11 - _self22), _t17, _t18, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t18)));
    }

    /** Piece 2 of {@code toDualQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toDualQuat_unsafe_s9f6c0bda_1(long dest, double _self00, double _self03, double _self11, double _self13, double _self22, double _self23, double _t0, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _t18, double _sp0, double _sp1, double _sp2) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t16));
        double _t63, _t64, _t65, _t66;
        if (_t14 > 0.0) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5 * java.lang.Math.sqrt(_t15);
        } else {
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
                _t63 = 0.5 * java.lang.Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (_self11 > _self22) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5 * java.lang.Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                    _t66 = _sp1 * _t8;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5 * java.lang.Math.sqrt(_t18);
                    _t66 = _sp2 * _t10;
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, _t63);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t64);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t65);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t66);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.5 * Math.fma(_t0, _t64, Math.fma(_self03, _t66, _self13 * _t65)));
        return toDualQuat_unsafe_s9f6c0bda_2(dest, _self03, _self13, _self23, _t0, _t63, _t64, _t65, _t66);
    }

    /** Piece 3 of {@code toDualQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toDualQuat_unsafe_s9f6c0bda_2(long dest, double _self03, double _self13, double _self23, double _t0, double _t63, double _t64, double _t65, double _t66) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.5 * Math.fma(_self23, _t63, Math.fma(_self13, _t66, -(_self03 * _t65))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.5 * Math.fma(_self23, _t66, Math.fma(_self03, _t64, -(_self13 * _t63))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.5 * Math.fma(_t0, _t65, Math.fma(-_self13, _t64, -(_self03 * _t63))));
        return dest;
    }

    public static long toRigid_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t0 = -_self11;
        double _t1 = -_self22;
        double _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _t18 = _self10 * _t17;
        double _t19 = _self22 * _t16;
        double _t20 = _self12 * _t16;
        double _t21 = _self20 * _t17;
        double _t23 = _self21 * _t15;
        double _t24 = _self11 * _t15;
        double _t26 = _self00 * _t17;
        double _t31 = Math.fma(_self12, _t16, _t23);
        double _t35 = Math.fma(_self21, _t15, -_t20);
        double _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), _self01 * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), _self02 * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        double _t51 = 1.0 + _t47;
        double _t52 = 1.0 - _t47;
        double _t54 = Math.fma(_self01, _t15, _t48);
        double _t55 = Math.fma(_self02, _t16, _t49);
        double _t56 = Math.fma(_self02, _t16, -_t49);
        double _t57 = Math.fma(-_self01, _t15, _t48);
        double _t63 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t51));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t63));
        double _t65 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t52));
        double _t66 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t52));
        double _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t65));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        if (Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t47)) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 24L, _sp0 * _t35);
            UnsafeOpsHolder.U.putDouble(dest + 32L, _sp0 * _t56);
            UnsafeOpsHolder.U.putDouble(dest + 40L, _sp0 * _t57);
            UnsafeOpsHolder.U.putDouble(dest + 48L, 0.5 * java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t67));
                UnsafeOpsHolder.U.putDouble(dest + 32L, _sp3 * _t54);
                UnsafeOpsHolder.U.putDouble(dest + 40L, _sp3 * _t55);
                UnsafeOpsHolder.U.putDouble(dest + 48L, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t54);
                    UnsafeOpsHolder.U.putDouble(dest + 32L, 0.5 * java.lang.Math.sqrt(_t65));
                    UnsafeOpsHolder.U.putDouble(dest + 40L, _sp1 * _t31);
                    UnsafeOpsHolder.U.putDouble(dest + 48L, _sp1 * _t56);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t55);
                    UnsafeOpsHolder.U.putDouble(dest + 32L, _sp2 * _t31);
                    UnsafeOpsHolder.U.putDouble(dest + 40L, 0.5 * java.lang.Math.sqrt(_t66));
                    UnsafeOpsHolder.U.putDouble(dest + 48L, _sp2 * _t57);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self23);
        return dest;
    }

    public static long toRigid_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.toRigid_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long toRigid_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t0 = unitScale(_self01, _self11, _self21);
        double _t1 = unitScale(_self02, _self12, _self22);
        double _t2 = unitScale(_self00, _self10, _self20);
        double _t12 = _self21 * _t0;
        double _t13 = _self01 * _t0;
        double _t14 = _self11 * _t0;
        double _t15 = _self22 * _t1;
        double _t16 = _self02 * _t1;
        double _t17 = _self12 * _t1;
        double _t18 = _self20 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self10 * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t33 = _t30 * _t18;
        double _t34 = _t30 * _t19;
        double _t35 = _t30 * _t20;
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
        double _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0;
            _t87 = -_t34;
        } else {
            _t72 = 0.0;
            _t75 = -_t35;
            _t87 = _t33;
        }
        double _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0;
            _t88 = -_t38;
        } else {
            _t73 = 0.0;
            _t76 = -_t36;
            _t88 = _t37;
        }
        double _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        double _t102 = _t99 * _t72;
        double _t103 = _t100 * _t73;
        double _t104 = _t101 * _t74;
        double _t105 = _t100 * _t76;
        double _t106 = _t99 * _t75;
        double _t107 = _t101 * _t77;
        double _t114 = _t100 * _t88;
        double _t115 = _t101 * _t89;
        double _t116 = _t99 * _t87;
        double _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t167 = 1.0;
                    _t170 = 0.0;
                    _t166 = 0.0;
                    _t168 = 0.0;
                    _t171 = 1.0;
                    _t169 = 0.0;
                    _t172 = 0.0;
                    _t173 = 1.0;
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
                if (_t29 <= 0.0) {
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
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
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
                if (_t29 <= 0.0) {
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
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        double _t199 = _t195 + _t165;
        double _t200 = _t196 + _t168;
        double _t201 = _t168 - _t196;
        double _t202 = _t195 - _t165;
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t208));
        if (_t206 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 24L, _sp0 * _t182);
            UnsafeOpsHolder.U.putDouble(dest + 32L, _sp0 * _t201);
            UnsafeOpsHolder.U.putDouble(dest + 40L, _sp0 * _t202);
            UnsafeOpsHolder.U.putDouble(dest + 48L, 0.5 * java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t208));
                UnsafeOpsHolder.U.putDouble(dest + 32L, _sp3 * _t199);
                UnsafeOpsHolder.U.putDouble(dest + 40L, _sp3 * _t200);
                UnsafeOpsHolder.U.putDouble(dest + 48L, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t199);
                    UnsafeOpsHolder.U.putDouble(dest + 32L, 0.5 * java.lang.Math.sqrt(_t209));
                    UnsafeOpsHolder.U.putDouble(dest + 40L, _sp1 * _t184);
                    UnsafeOpsHolder.U.putDouble(dest + 48L, _sp1 * _t201);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t200);
                    UnsafeOpsHolder.U.putDouble(dest + 32L, _sp2 * _t184);
                    UnsafeOpsHolder.U.putDouble(dest + 40L, 0.5 * java.lang.Math.sqrt(_t210));
                    UnsafeOpsHolder.U.putDouble(dest + 48L, _sp2 * _t202);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self23);
        return dest;
    }

    public static long toTransform_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.toTransform_degenerate(dest, src);
        double _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.toTransform_degenerate(dest, src);
        return toTransform_unsafe_s96acba85_1(dest, src, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self11, -_self22, _t12, _t13, Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10)));
    }

    /** Piece 2 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_s96acba85_1(long dest, long src, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t12, double _t13, double _t14) {
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsAddress.toTransform_degenerate(dest, src);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _t19 = _self10 * _t17;
        double _t20 = _self22 * _t16;
        double _t21 = _self12 * _t16;
        double _t22 = _self20 * _t17;
        double _t24 = _self21 * _t15;
        double _t25 = _self11 * _t15;
        double _t27 = _self00 * _t17;
        double _t47 = Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), _self01 * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), _self02 * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
        double _t48, _t49, _t50;
        if (_t47 < 0.0) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
        }
        return toTransform_unsafe_s96acba85_2(dest, _self01, _self03, _self11, _self13, _self22, _self23, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, Math.fma(_self12, _t16, _t24), Math.fma(_self21, _t15, -_t21), java.lang.Math.max(_t25, _t20), _t47, _t48, _t49, 1.0 + _t48, 1.0 - _t48, Math.fma(_self01, _t15, _t49), Math.fma(_self02, _t16, _t50), Math.fma(_self02, _t16, -_t50));
    }

    /** Piece 3 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_s96acba85_2(long dest, double _self01, double _self03, double _self11, double _self13, double _self22, double _self23, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t49, double _t52, double _t53, double _t55, double _t56, double _t57) {
        double _t63 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48));
        double _t64 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t64));
        double _t66 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53));
        double _t67 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53));
        double _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        UnsafeOpsHolder.U.putDouble(dest, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self23);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        return toTransform_unsafe_s96acba85_3(dest, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t55, _t56, _t57, Math.fma(-_self01, _t15, _t49), _t63, _t64, _sp0, _t66, _t67, _sp1, _sp2, 0.5 * (1.0 / java.lang.Math.sqrt(_t68)));
    }

    /** Piece 4 of {@code toTransform_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_s96acba85_3(long dest, double _t12, double _t13, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t55, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t66, double _t67, double _sp1, double _sp2, double _sp3) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * java.lang.Math.sqrt(_t67));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t63 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t47 < 0.0 ? -_t18 : _t18);
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.sqrt(_t12));
        UnsafeOpsHolder.U.putDouble(dest + 72L, java.lang.Math.sqrt(_t13));
        return dest;
    }

    public static long toTransform_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.toTransform_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long toTransform_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t0 = unitScale(_self01, _self11, _self21);
        double _t1 = unitScale(_self02, _self12, _self22);
        double _t2 = unitScale(_self00, _self10, _self20);
        double _t12 = _self21 * _t0;
        double _t13 = _self01 * _t0;
        double _t14 = _self11 * _t0;
        double _t15 = _self22 * _t1;
        double _t16 = _self02 * _t1;
        double _t17 = _self12 * _t1;
        double _t18 = _self20 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self10 * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / java.lang.Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        double _t105 = _t102 * _t75;
        double _t106 = _t103 * _t76;
        double _t107 = _t104 * _t77;
        double _t108 = _t103 * _t79;
        double _t109 = _t102 * _t78;
        double _t110 = _t104 * _t80;
        double _t117 = _t103 * _t91;
        double _t118 = _t104 * _t92;
        double _t119 = _t102 * _t90;
        double _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t170 = 1.0;
                    _t173 = 0.0;
                    _t169 = 0.0;
                    _t171 = 0.0;
                    _t174 = 1.0;
                    _t172 = 0.0;
                    _t175 = 0.0;
                    _t176 = 1.0;
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
                if (_t29 <= 0.0) {
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
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
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
                if (_t29 <= 0.0) {
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
        double _t185 = _t173 - _t169;
        double _t186 = java.lang.Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        double _t197, _t198, _t199;
        if (_t196 < 0.0) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        double _t202 = _t198 + _t168;
        double _t203 = _t199 + _t171;
        double _t204 = _t171 - _t199;
        double _t205 = _t198 - _t168;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t211));
        UnsafeOpsHolder.U.putDouble(dest, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self23);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t196 < 0.0 ? -_t56 : _t56);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static long decomposeRotation_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        double _t7, _t8, _t9;
        if (_t2 != 0.0) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0;
            _t8 = 0.0;
            _t9 = 0.0;
        }
        double _t19 = -Math.fma(_self21, _t7, Math.fma(_self01, _t8, _self11 * _t9));
        double _t21 = Math.fma(_t19, _t7, _self21);
        double _t22 = Math.fma(_t19, _t8, _self01);
        double _t23 = Math.fma(_t19, _t9, _self11);
        return decomposeRotation_unsafe_s46789a7d_1(dest, _self02, _self12, _self22, _t7, _t8, _t9, -Math.fma(_self22, _t7, Math.fma(_self02, _t8, _self12 * _t9)), _t21, _t22, _t23, Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)));
    }

    /** Piece 2 of {@code decomposeRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_s46789a7d_1(long dest, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t20, double _t21, double _t22, double _t23, double _t29) {
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t34, _t35, _t36;
        if (_t29 != 0.0) {
            _t34 = _t22 * _t30;
            _t35 = _t21 * _t30;
            _t36 = _t23 * _t30;
        } else {
            _t34 = 0.0;
            _t35 = 0.0;
            _t36 = 0.0;
        }
        double _t40 = -Math.fma(Math.fma(_t20, _t7, _self22), _t35, Math.fma(Math.fma(_t20, _t8, _self02), _t34, Math.fma(_t20, _t9, _self12) * _t36));
        double _t44 = Math.fma(_t20, _t7, Math.fma(_t40, _t35, _self22));
        double _t45 = Math.fma(_t20, _t8, Math.fma(_t40, _t34, _self02));
        double _t46 = Math.fma(_t20, _t9, Math.fma(_t40, _t36, _self12));
        double _t49 = Math.fma(_t44, _t44, Math.fma(_t45, _t45, _t46 * _t46));
        double _t50 = (1.0 / java.lang.Math.sqrt(_t49));
        double _t54, _t55, _t56;
        if (_t49 != 0.0) {
            _t54 = _t46 * _t50;
            _t55 = _t45 * _t50;
            _t56 = _t44 * _t50;
        } else {
            _t54 = 0.0;
            _t55 = 0.0;
            _t56 = 0.0;
        }
        return decomposeRotation_unsafe_s46789a7d_2(dest, _t7, _t8, _t9, _t34, _t35, _t36, _t54, _t55, _t56, _t35 - _t54, _t35 + _t54);
    }

    /** Piece 3 of {@code decomposeRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_s46789a7d_2(long dest, double _t7, double _t8, double _t9, double _t34, double _t35, double _t36, double _t54, double _t55, double _t56, double _t60, double _t63) {
        double _t73, _t74, _t75;
        if (Math.fma(Math.fma(_t34, _t54, -(_t36 * _t55)), _t7, Math.fma(Math.fma(_t36, _t56, -(_t35 * _t54)), _t8, Math.fma(_t35, _t55, -(_t34 * _t56)) * _t9)) < 0.0) {
            _t73 = -_t8;
            _t74 = -_t9;
            _t75 = -_t7;
        } else {
            _t73 = _t8;
            _t74 = _t9;
            _t75 = _t7;
        }
        double _t76 = _t73 + _t36;
        double _t82 = _t76 + _t56;
        double _t86 = 1.0 + _t82;
        double _t87 = 1.0 + (_t73 - (_t36 + _t56));
        double _t88 = 1.0 + (_t36 - (_t73 + _t56));
        double _t89 = 1.0 + (_t56 - _t76);
        return decomposeRotation_unsafe_s46789a7d_3(dest, _t36, _t56, _t60, _t63, _t73, _t74 + _t34, _t74 - _t34, _t75 + _t55, _t55 - _t75, _t82, _t86, _t87, _t88, _t89, 0.5 * (1.0 / java.lang.Math.sqrt(_t86)), 0.5 * (1.0 / java.lang.Math.sqrt(_t88)), 0.5 * (1.0 / java.lang.Math.sqrt(_t89)), 0.5 * (1.0 / java.lang.Math.sqrt(_t87)));
    }

    /** Piece 4 of {@code decomposeRotation_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_s46789a7d_3(long dest, double _t36, double _t56, double _t60, double _t63, double _t73, double _t77, double _t78, double _t80, double _t81, double _t82, double _t86, double _t87, double _t88, double _t89, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t82 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t60);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t81);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t78);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t86));
        } else {
            if (_t73 > java.lang.Math.max(_t36, _t56)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t87));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t77);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t80);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t60);
            } else {
                if (_t36 > _t56) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t77);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t88));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t63);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t81);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t80);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t63);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t89));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t78);
                }
            }
        }
        return dest;
    }

    public static long decomposeScale_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t4 = java.lang.Math.sqrt(_t2);
        double _t3 = 1.0 / _t4;
        double _t8, _t9, _t10;
        if (_t2 != 0.0) {
            _t8 = _self20 * _t3;
            _t9 = _self00 * _t3;
            _t10 = _self10 * _t3;
        } else {
            _t8 = 0.0;
            _t9 = 0.0;
            _t10 = 0.0;
        }
        double _t17 = -Math.fma(_self21, _t8, Math.fma(_self01, _t9, _self11 * _t10));
        double _t19 = Math.fma(_t17, _t8, _self21);
        double _t20 = Math.fma(_t17, _t9, _self01);
        double _t21 = Math.fma(_t17, _t10, _self11);
        return decomposeScale_unsafe_s90429cf5_1(dest, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10)), _t19, _t20, _t21, Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21)));
    }

    /** Piece 2 of {@code decomposeScale_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeScale_unsafe_s90429cf5_1(long dest, double _self02, double _self12, double _self22, double _t4, double _t8, double _t9, double _t10, double _t18, double _t19, double _t20, double _t21, double _t27) {
        double _t28 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t32, _t33, _t34;
        if (_t27 != 0.0) {
            _t32 = _t20 * _t28;
            _t33 = _t19 * _t28;
            _t34 = _t21 * _t28;
        } else {
            _t32 = 0.0;
            _t33 = 0.0;
            _t34 = 0.0;
        }
        double _t38 = -Math.fma(Math.fma(_t18, _t8, _self22), _t33, Math.fma(Math.fma(_t18, _t9, _self02), _t32, Math.fma(_t18, _t10, _self12) * _t34));
        double _t42 = Math.fma(_t18, _t8, Math.fma(_t38, _t33, _self22));
        double _t43 = Math.fma(_t18, _t9, Math.fma(_t38, _t32, _self02));
        double _t44 = Math.fma(_t18, _t10, Math.fma(_t38, _t34, _self12));
        double _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        double _t48 = (1.0 / java.lang.Math.sqrt(_t47));
        double _t52, _t53, _t54;
        if (_t47 != 0.0) {
            _t52 = _t44 * _t48;
            _t53 = _t43 * _t48;
            _t54 = _t42 * _t48;
        } else {
            _t52 = 0.0;
            _t53 = 0.0;
            _t54 = 0.0;
        }
        return decomposeScale_unsafe_s90429cf5_2(dest, _t4, _t8, _t9, _t10, _t27, _t32, _t33, _t34, _t47, _t52, _t53, _t54);
    }

    /** Piece 3 of {@code decomposeScale_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeScale_unsafe_s90429cf5_2(long dest, double _t4, double _t8, double _t9, double _t10, double _t27, double _t32, double _t33, double _t34, double _t47, double _t52, double _t53, double _t54) {
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0 ? -_t4 : _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.sqrt(_t27));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.sqrt(_t47));
        return dest;
    }

    public static long decomposeSkew_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        double _t7, _t8, _t9;
        if (_t2 != 0.0) {
            _t7 = _self20 * _t3;
            _t8 = _self00 * _t3;
            _t9 = _self10 * _t3;
        } else {
            _t7 = 0.0;
            _t8 = 0.0;
            _t9 = 0.0;
        }
        double _t14 = Math.fma(_self22, _t7, Math.fma(_self02, _t8, _self12 * _t9));
        double _t15 = Math.fma(_self21, _t7, Math.fma(_self01, _t8, _self11 * _t9));
        double _t17 = -_t15;
        return decomposeSkew_unsafe_s79ad6b4d_1(dest, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, Math.fma(_t17, _t7, _self21), Math.fma(_t17, _t8, _self01), Math.fma(_t17, _t9, _self11));
    }

    /** Piece 2 of {@code decomposeSkew_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_s79ad6b4d_1(long dest, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t14, double _t15, double _t16, double _t19, double _t20, double _t21) {
        double _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        double _t27 = (1.0 / java.lang.Math.sqrt(_t26));
        double _t32, _t33, _t34;
        if (_t26 != 0.0) {
            _t32 = _t19 * _t27;
            _t33 = _t20 * _t27;
            _t34 = _t21 * _t27;
        } else {
            _t32 = 0.0;
            _t33 = 0.0;
            _t34 = 0.0;
        }
        double _t37 = Math.fma(Math.fma(_t16, _t7, _self22), _t32, Math.fma(Math.fma(_t16, _t8, _self02), _t33, Math.fma(_t16, _t9, _self12) * _t34));
        double _t38 = -_t37;
        double _t42 = Math.fma(_t16, _t7, Math.fma(_t38, _t32, _self22));
        double _t43 = Math.fma(_t16, _t8, Math.fma(_t38, _t33, _self02));
        double _t44 = Math.fma(_t16, _t9, Math.fma(_t38, _t34, _self12));
        double _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        double _t48 = (1.0 / java.lang.Math.sqrt(_t47));
        double _t53, _t54, _t55;
        if (_t47 != 0.0) {
            _t53 = _t44 * _t48;
            _t54 = _t43 * _t48;
            _t55 = _t42 * _t48;
        } else {
            _t53 = 0.0;
            _t54 = 0.0;
            _t55 = 0.0;
        }
        return decomposeSkew_unsafe_s79ad6b4d_2(dest, _t7, _t8, _t9, _t15 * _t27, _t32, _t33, _t34, _t37, _t48, _t14 * _t48, _t53, _t54, _t55);
    }

    /** Piece 3 of {@code decomposeSkew_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_s79ad6b4d_2(long dest, double _t7, double _t8, double _t9, double _t28, double _t32, double _t33, double _t34, double _t37, double _t48, double _t49, double _t53, double _t54, double _t55) {
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, -_t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -_t28);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t28);
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37 * _t48);
        return dest;
    }

    public static long decomposeTRS_unsafe(long translation, long rotation, long scale, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = 1.0 / java.lang.Math.sqrt(_t2);
        double _t8, _t9, _t10;
        if (_t2 != 0.0) {
            _t8 = _self20 * _t3;
            _t9 = _self00 * _t3;
            _t10 = _self10 * _t3;
        } else {
            _t8 = 0.0;
            _t9 = 0.0;
            _t10 = 0.0;
        }
        return decomposeTRS_unsafe_s2318f5ef_5(translation, rotation, scale, src, _self01, _self02, _self11, _self12, _self13, _self21, _self22, _self23, _t2, _t8, _t9, _t10, -Math.fma(_self21, _t8, Math.fma(_self01, _t9, _self11 * _t10)), -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10)));
    }

    /** Part 1 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static double decomposeTRS_unsafe_s2318f5ef_1(double _self01, double _self11, double _self21, double _t8, double _t9, double _t10, double _t20) {
        double _t22 = Math.fma(_t20, _t8, _self21);
        double _t23 = Math.fma(_t20, _t9, _self01);
        double _t24 = Math.fma(_t20, _t10, _self11);
        return Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24));
    }

    /** Part 2 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static double decomposeTRS_unsafe_s2318f5ef_2(double _self02, double _self12, double _self22, double _t8, double _t9, double _t10, double _t21, double _t36, double _t35, double _t37, double _t41) {
        double _t45 = Math.fma(_t21, _t8, Math.fma(_t41, _t36, _self22));
        double _t46 = Math.fma(_t21, _t9, Math.fma(_t41, _t35, _self02));
        double _t47 = Math.fma(_t21, _t10, Math.fma(_t41, _t37, _self12));
        return Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47));
    }

    /** Part 3 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static double decomposeTRS_unsafe_s2318f5ef_3(long translation, long src, double _self13, double _self23, double _t8, double _t9, double _t10, double _t35, double _t37, double _t36, double _t55, double _t56, double _t57) {
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(translation, _self03);
        UnsafeOpsHolder.U.putDouble(translation + 8L, _self13);
        UnsafeOpsHolder.U.putDouble(translation + 16L, _self23);
        return Math.fma(Math.fma(_t35, _t55, -(_t37 * _t56)), _t8, Math.fma(Math.fma(_t37, _t57, -(_t36 * _t55)), _t9, Math.fma(_t36, _t56, -(_t35 * _t57)) * _t10));
    }

    /** Part of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static void decomposeTRS_unsafe_s2318f5ef_524(long rotation, double _t9, double _t10, double _t8, double _t37, double _t36, double _t35, double _t57, double _t55, double _t56, double _t73) {
        double _t74, _t75, _t76;
        if (_t73 < 0.0) {
            _t74 = -_t9;
            _t75 = -_t10;
            _t76 = -_t8;
        } else {
            _t74 = _t9;
            _t75 = _t10;
            _t76 = _t8;
        }
        double _t77 = _t74 + _t37;
        double _t83 = _t77 + _t57;
        double _t87 = 1.0 + _t83;
        double _t88 = 1.0 + (_t74 - (_t37 + _t57));
        double _t89 = 1.0 + (_t37 - (_t74 + _t57));
        double _t90 = 1.0 + (_t57 - _t77);
        decomposeTRS_unsafe_s2318f5ef_524_s17758a6c_1(rotation, _t37, _t57, _t36 - _t55, _t36 + _t55, _t74, _t75 + _t35, _t75 - _t35, _t76 + _t56, _t56 - _t76, _t83, _t87, _t88, _t89, _t90, 0.5 * (1.0 / java.lang.Math.sqrt(_t87)), 0.5 * (1.0 / java.lang.Math.sqrt(_t89)), 0.5 * (1.0 / java.lang.Math.sqrt(_t90)), 0.5 * (1.0 / java.lang.Math.sqrt(_t88)));
    }

    /** Piece 2 of {@code decomposeTRS_unsafe_s2318f5ef_524}, split to fit the inline budget; reached only through it. */
    private static void decomposeTRS_unsafe_s2318f5ef_524_s17758a6c_1(long rotation, double _t37, double _t57, double _t61, double _t64, double _t74, double _t78, double _t79, double _t81, double _t82, double _t83, double _t87, double _t88, double _t89, double _t90, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t83 > 0.0) {
            UnsafeOpsHolder.U.putDouble(rotation, _sp0 * _t61);
            UnsafeOpsHolder.U.putDouble(rotation + 8L, _sp0 * _t82);
            UnsafeOpsHolder.U.putDouble(rotation + 16L, _sp0 * _t79);
            UnsafeOpsHolder.U.putDouble(rotation + 24L, 0.5 * java.lang.Math.sqrt(_t87));
        } else {
            if (_t74 > java.lang.Math.max(_t37, _t57)) {
                UnsafeOpsHolder.U.putDouble(rotation, 0.5 * java.lang.Math.sqrt(_t88));
                UnsafeOpsHolder.U.putDouble(rotation + 8L, _sp3 * _t78);
                UnsafeOpsHolder.U.putDouble(rotation + 16L, _sp3 * _t81);
                UnsafeOpsHolder.U.putDouble(rotation + 24L, _sp3 * _t61);
            } else {
                if (_t37 > _t57) {
                    UnsafeOpsHolder.U.putDouble(rotation, _sp1 * _t78);
                    UnsafeOpsHolder.U.putDouble(rotation + 8L, 0.5 * java.lang.Math.sqrt(_t89));
                    UnsafeOpsHolder.U.putDouble(rotation + 16L, _sp1 * _t64);
                    UnsafeOpsHolder.U.putDouble(rotation + 24L, _sp1 * _t82);
                } else {
                    UnsafeOpsHolder.U.putDouble(rotation, _sp2 * _t81);
                    UnsafeOpsHolder.U.putDouble(rotation + 8L, _sp2 * _t64);
                    UnsafeOpsHolder.U.putDouble(rotation + 16L, 0.5 * java.lang.Math.sqrt(_t90));
                    UnsafeOpsHolder.U.putDouble(rotation + 24L, _sp2 * _t79);
                }
            }
        }
    }

    /** Part 4 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeTRS_unsafe_s2318f5ef_4(long translation, long scale, double _t2, double _t30, double _t50, double _t73) {
        double _t4 = java.lang.Math.sqrt(_t2);
        UnsafeOpsHolder.U.putDouble(scale, _t73 < 0.0 ? -_t4 : _t4);
        UnsafeOpsHolder.U.putDouble(scale + 8L, java.lang.Math.sqrt(_t30));
        UnsafeOpsHolder.U.putDouble(scale + 16L, java.lang.Math.sqrt(_t50));
        return translation;
    }

    /** Piece 2 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeTRS_unsafe_s2318f5ef_5(long translation, long rotation, long scale, long src, double _self01, double _self02, double _self11, double _self12, double _self13, double _self21, double _self22, double _self23, double _t2, double _t8, double _t9, double _t10, double _t20, double _t21) {
        double _t30 = decomposeTRS_unsafe_s2318f5ef_1(_self01, _self11, _self21, _t8, _t9, _t10, _t20);
        double _t31 = (1.0 / java.lang.Math.sqrt(_t30));
        double _t35, _t36, _t37;
        if (_t30 != 0.0) {
            _t35 = Math.fma(_t20, _t9, _self01) * _t31;
            _t36 = Math.fma(_t20, _t8, _self21) * _t31;
            _t37 = Math.fma(_t20, _t10, _self11) * _t31;
        } else {
            _t35 = 0.0;
            _t36 = 0.0;
            _t37 = 0.0;
        }
        double _t41 = -Math.fma(Math.fma(_t21, _t8, _self22), _t36, Math.fma(Math.fma(_t21, _t9, _self02), _t35, Math.fma(_t21, _t10, _self12) * _t37));
        double _t50 = decomposeTRS_unsafe_s2318f5ef_2(_self02, _self12, _self22, _t8, _t9, _t10, _t21, _t36, _t35, _t37, _t41);
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        double _t55, _t56, _t57;
        if (_t50 != 0.0) {
            _t55 = Math.fma(_t21, _t10, Math.fma(_t41, _t37, _self12)) * _t51;
            _t56 = Math.fma(_t21, _t9, Math.fma(_t41, _t35, _self02)) * _t51;
            _t57 = Math.fma(_t21, _t8, Math.fma(_t41, _t36, _self22)) * _t51;
        } else {
            _t55 = 0.0;
            _t56 = 0.0;
            _t57 = 0.0;
        }
        return decomposeTRS_unsafe_s2318f5ef_6(translation, rotation, scale, src, _self13, _self23, _t2, _t8, _t9, _t10, _t30, _t35, _t36, _t37, _t50, _t55, _t56, _t57);
    }

    /** Piece 3 of {@code decomposeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeTRS_unsafe_s2318f5ef_6(long translation, long rotation, long scale, long src, double _self13, double _self23, double _t2, double _t8, double _t9, double _t10, double _t30, double _t35, double _t36, double _t37, double _t50, double _t55, double _t56, double _t57) {
        double _t73 = decomposeTRS_unsafe_s2318f5ef_3(translation, src, _self13, _self23, _t8, _t9, _t10, _t35, _t37, _t36, _t55, _t56, _t57);
        decomposeTRS_unsafe_s2318f5ef_524(rotation, _t9, _t10, _t8, _t37, _t36, _t35, _t57, _t55, _t56, _t73);
        return decomposeTRS_unsafe_s2318f5ef_4(translation, scale, _t2, _t30, _t50, _t73);
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, double t) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, Math.fma(t, _eother - _eself, _eself));
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long right) {
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right02 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right03 = UnsafeOpsHolder.U.getDouble(right + 24L);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 32L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 40L);
        double _right12 = UnsafeOpsHolder.U.getDouble(right + 48L);
        double _right13 = UnsafeOpsHolder.U.getDouble(right + 56L);
        double _right20 = UnsafeOpsHolder.U.getDouble(right + 64L);
        double _right21 = UnsafeOpsHolder.U.getDouble(right + 72L);
        double _right22 = UnsafeOpsHolder.U.getDouble(right + 80L);
        double _right23 = UnsafeOpsHolder.U.getDouble(right + 88L);
        return mul_unsafe_sc4d3a782_1(dest, src, _right00, _right01, _right02, _right03, _right10, _right11, _right12, _right13, _right20, _right21, _right22, _right23);
    }

    /** Piece 2 of {@code mul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long mul_unsafe_sc4d3a782_1(long dest, long src, double _right00, double _right01, double _right02, double _right03, double _right10, double _right11, double _right12, double _right13, double _right20, double _right21, double _right22, double _right23) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, Math.fma(_right03, _eself0, Math.fma(_right13, _eself1, Math.fma(_right23, _eself2, _eself3))));
        }
        return dest;
    }

    public static long mulMat2x2_unsafe(long dest, long src, long right) {
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_right00, _eself0, _right10 * _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_right01, _eself0, _right11 * _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mulMat2x3_unsafe(long dest, long src, long right) {
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        double _right02 = UnsafeOpsHolder.U.getDouble(right + 32L);
        double _right12 = UnsafeOpsHolder.U.getDouble(right + 40L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_right00, _eself0, _right10 * _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_right01, _eself0, _right11 * _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, Math.fma(_right02, _eself0, Math.fma(_right12, _eself1, _eself3)));
        }
        return dest;
    }

    public static long mulMat3x3_unsafe(long dest, long src, long right) {
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right20 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 24L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 32L);
        double _right21 = UnsafeOpsHolder.U.getDouble(right + 40L);
        double _right02 = UnsafeOpsHolder.U.getDouble(right + 48L);
        double _right12 = UnsafeOpsHolder.U.getDouble(right + 56L);
        double _right22 = UnsafeOpsHolder.U.getDouble(right + 64L);
        return mulMat3x3_unsafe_s3c8f3084_1(dest, src, _right00, _right10, _right20, _right01, _right11, _right21, _right02, _right12, _right22);
    }

    /** Piece 2 of {@code mulMat3x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long mulMat3x3_unsafe_s3c8f3084_1(long dest, long src, double _right00, double _right10, double _right20, double _right01, double _right11, double _right21, double _right02, double _right12, double _right22) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mulMat4x4_unsafe(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        return mulMat4x4_unsafe_saec0f58c_1(dest, right, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code mulMat4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long mulMat4x4_unsafe_saec0f58c_1(long dest, long right, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23) {
        for (int _l = 0; _l < 4; _l++) {
            int _lo = _l * 4;
            double _eright0 = UnsafeOpsHolder.U.getDouble(right + _lo * 8L);
            double _eright1 = UnsafeOpsHolder.U.getDouble(right + (_lo + 1) * 8L);
            double _eright2 = UnsafeOpsHolder.U.getDouble(right + (_lo + 2) * 8L);
            double _eright3 = UnsafeOpsHolder.U.getDouble(right + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_eright3, _self03, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01))));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_eright3, _self13, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11))));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, Math.fma(_eright3, _self23, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21))));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eright3);
        }
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        return preMul_unsafe_sc0fcc75d_1(dest, other, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMul_unsafe_sc0fcc75d_1(long dest, long other, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eother0 = UnsafeOpsHolder.U.getDouble(other + _lo * 8L);
            double _eother1 = UnsafeOpsHolder.U.getDouble(other + (_lo + 1) * 8L);
            double _eother2 = UnsafeOpsHolder.U.getDouble(other + (_lo + 2) * 8L);
            double _eother3 = UnsafeOpsHolder.U.getDouble(other + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_eother2, _self20, Math.fma(_eother0, _self00, _eother1 * _self10)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_eother2, _self21, Math.fma(_eother0, _self01, _eother1 * _self11)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, Math.fma(_eother2, _self22, Math.fma(_eother0, _self02, _eother1 * _self12)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, Math.fma(_eother0, _self03, Math.fma(_eother1, _self13, Math.fma(_eother2, _self23, _eother3))));
        }
        return dest;
    }

    public static long preMulMat2x2_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_other00, _self00, _other01 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_other00, _self02, _other01 * _self12));
        return preMulMat2x2_unsafe_sc3bf1023_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other01, _other11);
    }

    /** Piece 2 of {@code preMulMat2x2_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat2x2_unsafe_sc3bf1023_1(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other10, double _other01, double _other11) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_other00, _self03, _other01 * _self13));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_other10, _self01, _other11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_other10, _self02, _other11 * _self12));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_other10, _self03, _other11 * _self13));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long preMulMat2x3_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 40L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_other00, _self00, _other01 * _self10));
        return preMulMat2x3_unsafe_s9a8c7fa2_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other01, _other11, _other02, _other12);
    }

    /** Piece 2 of {@code preMulMat2x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat2x3_unsafe_s9a8c7fa2_1(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_other00, _self02, _other01 * _self12));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_other00, _self03, Math.fma(_other01, _self13, _other02)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_other10, _self01, _other11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_other10, _self02, _other11 * _self12));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_other10, _self03, Math.fma(_other11, _self13, _other12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long preMulMat3x3_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 56L);
        return preMulMat3x3_unsafe_s8133e2b_1(dest, other, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12);
    }

    /** Piece 2 of {@code preMulMat3x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat3x3_unsafe_s8133e2b_1(long dest, long other, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12) {
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 64L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_other02, _self23, Math.fma(_other00, _self03, _other01 * _self13)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_other12, _self23, Math.fma(_other10, _self03, _other11 * _self13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10)));
        return preMulMat3x3_unsafe_s8133e2b_2(dest, _self01, _self02, _self03, _self11, _self12, _self13, _self21, _self22, _self23, _other20, _other21, _other22);
    }

    /** Piece 3 of {@code preMulMat3x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat3x3_unsafe_s8133e2b_2(long dest, double _self01, double _self02, double _self03, double _self11, double _self12, double _self13, double _self21, double _self22, double _self23, double _other20, double _other21, double _other22) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_other22, _self23, Math.fma(_other20, _self03, _other21 * _self13)));
        return dest;
    }

    public static long preMulMat4x4_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other30 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other31 = UnsafeOpsHolder.U.getDouble(other + 56L);
        return preMulMat4x4_unsafe_s18080933_3(dest, other, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31);
    }

    /** Part 1 of {@code preMulMat4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static double preMulMat4x4_unsafe_s18080933_1(long dest, long other, double _self00, double _self01, double _self10, double _self11, double _self20, double _self21, double _other00, double _other10, double _other20, double _other30, double _other01, double _other11, double _other21, double _other31, double _other02, double _other12, double _other22, double _other32) {
        double _other33 = UnsafeOpsHolder.U.getDouble(other + 120L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_other32, _self20, Math.fma(_other30, _self00, _other31 * _self10)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_other32, _self21, Math.fma(_other30, _self01, _other31 * _self11)));
        return _other33;
    }

    /** Part 2 of {@code preMulMat4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat4x4_unsafe_s18080933_2(long dest, double _self02, double _self03, double _self12, double _self13, double _self22, double _self23, double _other00, double _other10, double _other20, double _other30, double _other01, double _other11, double _other21, double _other31, double _other02, double _other12, double _other22, double _other32, double _other03, double _other13, double _other23, double _other33) {
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_other32, _self22, Math.fma(_other30, _self02, _other31 * _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 96L, Math.fma(_other00, _self03, Math.fma(_other01, _self13, Math.fma(_other02, _self23, _other03))));
        UnsafeOpsHolder.U.putDouble(dest + 104L, Math.fma(_other10, _self03, Math.fma(_other11, _self13, Math.fma(_other12, _self23, _other13))));
        UnsafeOpsHolder.U.putDouble(dest + 112L, Math.fma(_other20, _self03, Math.fma(_other21, _self13, Math.fma(_other22, _self23, _other23))));
        UnsafeOpsHolder.U.putDouble(dest + 120L, Math.fma(_other30, _self03, Math.fma(_other31, _self13, Math.fma(_other32, _self23, _other33))));
        return dest;
    }

    /** Piece 2 of {@code preMulMat4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat4x4_unsafe_s18080933_3(long dest, long other, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other10, double _other20, double _other30, double _other01, double _other11, double _other21, double _other31) {
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 64L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 72L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 80L);
        double _other32 = UnsafeOpsHolder.U.getDouble(other + 88L);
        double _other03 = UnsafeOpsHolder.U.getDouble(other + 96L);
        double _other13 = UnsafeOpsHolder.U.getDouble(other + 104L);
        double _other23 = UnsafeOpsHolder.U.getDouble(other + 112L);
        double _other33 = preMulMat4x4_unsafe_s18080933_1(dest, other, _self00, _self01, _self10, _self11, _self20, _self21, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32);
        return preMulMat4x4_unsafe_s18080933_2(dest, _self02, _self03, _self12, _self13, _self22, _self23, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32, _other03, _other13, _other23, _other33);
    }

    public static long addScaled_unsafe(long dest, long src, long other, double weight) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, Math.fma(weight, _eother, _eself));
        }
        return dest;
    }

    public static long composeTRS_unsafe(long dest, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleX + scaleX;
        double _t1 = scaleY + scaleY;
        double _t2 = scaleZ + scaleZ;
        double _t3 = rotationZ * rotationZ;
        double _t4 = rotationZ * rotationW;
        double _t5 = rotationY * rotationW;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-Math.fma(rotationY, rotationY, _t3), _t0, scaleX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(rotationX, rotationY, -_t4) * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(rotationX, rotationZ, _t5) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 24L, translationX);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(rotationX, rotationY, _t4) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(-Math.fma(rotationX, rotationX, _t3), _t1, scaleY));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 56L, translationY);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(rotationX, rotationZ, -_t5) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t1);
        return composeTRS_unsafe_s442377e0_1(dest, translationZ, rotationX, rotationY, scaleZ, _t2);
    }

    /** Piece 2 of {@code composeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRS_unsafe_s442377e0_1(long dest, double translationZ, double rotationX, double rotationY, double scaleZ, double _t2) {
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t2, scaleZ));
        UnsafeOpsHolder.U.putDouble(dest + 88L, translationZ);
        return dest;
    }

    public static long composeTRS_unsafe(long dest, long translation, long rotation, long scale) {
        double _translationx = UnsafeOpsHolder.U.getDouble(translation);
        double _translationy = UnsafeOpsHolder.U.getDouble(translation + 8L);
        double _translationz = UnsafeOpsHolder.U.getDouble(translation + 16L);
        double _rotationx = UnsafeOpsHolder.U.getDouble(rotation);
        double _rotationy = UnsafeOpsHolder.U.getDouble(rotation + 8L);
        double _rotationz = UnsafeOpsHolder.U.getDouble(rotation + 16L);
        double _rotationw = UnsafeOpsHolder.U.getDouble(rotation + 24L);
        double _scalex = UnsafeOpsHolder.U.getDouble(scale);
        double _scaley = UnsafeOpsHolder.U.getDouble(scale + 8L);
        double _scalez = UnsafeOpsHolder.U.getDouble(scale + 16L);
        double _t0 = _scalex + _scalex;
        double _t1 = _scaley + _scaley;
        double _t2 = _scalez + _scalez;
        double _t3 = _rotationz * _rotationz;
        double _t4 = _rotationz * _rotationw;
        double _t5 = _rotationy * _rotationw;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-Math.fma(_rotationy, _rotationy, _t3), _t0, _scalex));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_rotationx, _rotationy, -_t4) * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_rotationx, _rotationz, _t5) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _translationx);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_rotationx, _rotationy, _t4) * _t0);
        return composeTRS_unsafe_s7cb404ea_1(dest, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scaley, _scalez, _t0, _t1, _t2, _t3, _t5);
    }

    /** Piece 2 of {@code composeTRS_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRS_unsafe_s7cb404ea_1(long dest, double _translationy, double _translationz, double _rotationx, double _rotationy, double _rotationz, double _rotationw, double _scaley, double _scalez, double _t0, double _t1, double _t2, double _t3, double _t5) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(-Math.fma(_rotationx, _rotationx, _t3), _t1, _scaley));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _translationy);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_rotationx, _rotationz, -_t5) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t2, _scalez));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _translationz);
        return dest;
    }

    public static long composeTRSAround_unsafe(long dest, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -pivotY;
        double _t1 = -pivotZ;
        double _t3 = scaleX + scaleX;
        double _t4 = scaleY + scaleY;
        double _t5 = scaleZ + scaleZ;
        double _t6 = rotationZ * rotationZ;
        double _t7 = rotationZ * rotationW;
        double _t8 = rotationY * rotationW;
        double _t15 = Math.fma(rotationY, rotationY, _t6);
        double _t24 = Math.fma(rotationX, rotationZ, _t8) * _t5;
        double _t25 = Math.fma(rotationX, rotationY, _t7) * _t3;
        double _t27 = Math.fma(rotationX, rotationY, -_t7) * _t4;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-_t15, _t3, scaleX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t27);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t24);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(pivotX, Math.fma(_t15, _t3, 1.0 - scaleX), Math.fma(_t0, _t27, Math.fma(_t1, _t24, translationX))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t25);
        return composeTRSAround_unsafe_s148c307_1(dest, translationY, translationZ, scaleY, scaleZ, pivotY, pivotZ, _t0, _t1, -pivotX, _t4, _t5, Math.fma(rotationX, rotationX, _t6), Math.fma(rotationX, rotationX, rotationY * rotationY), _t25, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t4, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t5, Math.fma(rotationX, rotationZ, -_t8) * _t3);
    }

    /** Piece 2 of {@code composeTRSAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSAround_unsafe_s148c307_1(long dest, double translationY, double translationZ, double scaleY, double scaleZ, double pivotY, double pivotZ, double _t0, double _t1, double _t2, double _t4, double _t5, double _t18, double _t20, double _t25, double _t26, double _t28, double _t29) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(-_t18, _t4, scaleY));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t28);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(pivotY, Math.fma(_t18, _t4, 1.0 - scaleY), Math.fma(_t2, _t25, Math.fma(_t1, _t28, translationY))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t29);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t26);
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(-_t20, _t5, scaleZ));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(pivotZ, Math.fma(_t20, _t5, 1.0 - scaleZ), Math.fma(_t2, _t29, Math.fma(_t0, _t26, translationZ))));
        return dest;
    }

    public static long composeTRSAround_unsafe(long dest, long translation, long rotation, long scale, long pivot) {
        double _translationx = UnsafeOpsHolder.U.getDouble(translation);
        double _translationy = UnsafeOpsHolder.U.getDouble(translation + 8L);
        double _translationz = UnsafeOpsHolder.U.getDouble(translation + 16L);
        double _rotationx = UnsafeOpsHolder.U.getDouble(rotation);
        double _rotationy = UnsafeOpsHolder.U.getDouble(rotation + 8L);
        double _rotationz = UnsafeOpsHolder.U.getDouble(rotation + 16L);
        double _rotationw = UnsafeOpsHolder.U.getDouble(rotation + 24L);
        double _scalex = UnsafeOpsHolder.U.getDouble(scale);
        double _scaley = UnsafeOpsHolder.U.getDouble(scale + 8L);
        double _scalez = UnsafeOpsHolder.U.getDouble(scale + 16L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(pivot + 16L);
        double _t3 = _scalex + _scalex;
        double _t5 = _scalez + _scalez;
        double _t6 = _rotationz * _rotationz;
        double _t7 = _rotationz * _rotationw;
        double _t8 = _rotationy * _rotationw;
        return composeTRSAround_unsafe_s92527211_1(dest, _translationx, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scalex, _scaley, _scalez, _pivotx, _pivoty, _pivotz, -_pivoty, -_pivotz, -_pivotx, _t3, _scaley + _scaley, _t5, _t7, _t8, Math.fma(_rotationy, _rotationy, _t6), Math.fma(_rotationx, _rotationx, _t6), Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), Math.fma(_rotationx, _rotationz, _t8) * _t5, Math.fma(_rotationx, _rotationy, _t7) * _t3);
    }

    /** Piece 2 of {@code composeTRSAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSAround_unsafe_s92527211_1(long dest, double _translationx, double _translationy, double _translationz, double _rotationx, double _rotationy, double _rotationz, double _rotationw, double _scalex, double _scaley, double _scalez, double _pivotx, double _pivoty, double _pivotz, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t7, double _t8, double _t15, double _t18, double _t20, double _t24, double _t25) {
        double _t26 = Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t4;
        double _t27 = Math.fma(_rotationx, _rotationy, -_t7) * _t4;
        double _t28 = Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t5;
        double _t29 = Math.fma(_rotationx, _rotationz, -_t8) * _t3;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-_t15, _t3, _scalex));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t27);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t24);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_pivotx, Math.fma(_t15, _t3, 1.0 - _scalex), Math.fma(_t0, _t27, Math.fma(_t1, _t24, _translationx))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t25);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(-_t18, _t4, _scaley));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t28);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_pivoty, Math.fma(_t18, _t4, 1.0 - _scaley), Math.fma(_t2, _t25, Math.fma(_t1, _t28, _translationy))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t29);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t26);
        return composeTRSAround_unsafe_s92527211_2(dest, _translationz, _scalez, _pivotz, _t0, _t2, _t5, _t20, _t26, _t29);
    }

    /** Piece 3 of {@code composeTRSAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSAround_unsafe_s92527211_2(long dest, double _translationz, double _scalez, double _pivotz, double _t0, double _t2, double _t5, double _t20, double _t26, double _t29) {
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(-_t20, _t5, _scalez));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_pivotz, Math.fma(_t20, _t5, 1.0 - _scalez), Math.fma(_t2, _t29, Math.fma(_t0, _t26, _translationz))));
        return dest;
    }

    public static long composeTRSMul_unsafe(long dest, long m, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m02 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m03 = UnsafeOpsHolder.U.getDouble(m + 24L);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 32L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 40L);
        double _m12 = UnsafeOpsHolder.U.getDouble(m + 48L);
        double _m13 = UnsafeOpsHolder.U.getDouble(m + 56L);
        double _m20 = UnsafeOpsHolder.U.getDouble(m + 64L);
        double _m21 = UnsafeOpsHolder.U.getDouble(m + 72L);
        double _m22 = UnsafeOpsHolder.U.getDouble(m + 80L);
        double _m23 = UnsafeOpsHolder.U.getDouble(m + 88L);
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t5 = rotationZ * rotationW;
        return composeTRSMul_unsafe_s30a3ba13_1(dest, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t0, _t1, _t2, _t3, rotationZ * rotationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationY, -_t5) * _t2);
    }

    /** Piece 2 of {@code composeTRSMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSMul_unsafe_s30a3ba13_1(long dest, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t0, double _t1, double _t2, double _t3, double _t4, double _t24, double _t25, double _t26, double _t27) {
        double _t28 = Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0;
        double _t30 = Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX);
        double _t31 = Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, translationX))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31)));
        return composeTRSMul_unsafe_s30a3ba13_2(dest, translationY, translationZ, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t25, _t26, _t28, Math.fma(rotationX, rotationZ, -_t3) * _t1, _t31, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
    }

    /** Piece 3 of {@code composeTRSMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSMul_unsafe_s30a3ba13_2(long dest, double translationY, double translationZ, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t25, double _t26, double _t28, double _t29, double _t31, double _t32) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, translationY))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, translationZ))));
        return dest;
    }

    public static long composeTRSMul_unsafe(long dest, long translation, long rotation, long scale, long m) {
        double _translationx = UnsafeOpsHolder.U.getDouble(translation);
        double _translationy = UnsafeOpsHolder.U.getDouble(translation + 8L);
        double _translationz = UnsafeOpsHolder.U.getDouble(translation + 16L);
        double _rotationx = UnsafeOpsHolder.U.getDouble(rotation);
        double _rotationy = UnsafeOpsHolder.U.getDouble(rotation + 8L);
        double _rotationz = UnsafeOpsHolder.U.getDouble(rotation + 16L);
        double _rotationw = UnsafeOpsHolder.U.getDouble(rotation + 24L);
        double _scalex = UnsafeOpsHolder.U.getDouble(scale);
        double _scaley = UnsafeOpsHolder.U.getDouble(scale + 8L);
        double _scalez = UnsafeOpsHolder.U.getDouble(scale + 16L);
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m02 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m03 = UnsafeOpsHolder.U.getDouble(m + 24L);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 32L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 40L);
        double _m12 = UnsafeOpsHolder.U.getDouble(m + 48L);
        double _m13 = UnsafeOpsHolder.U.getDouble(m + 56L);
        double _m20 = UnsafeOpsHolder.U.getDouble(m + 64L);
        double _m21 = UnsafeOpsHolder.U.getDouble(m + 72L);
        return composeTRSMul_unsafe_s3c1255f5_3(dest, m, _translationx, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scalex, _scaley, _scalez, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21);
    }

    /** Part 1 of {@code composeTRSMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static double composeTRSMul_unsafe_s3c1255f5_1(long dest, double _translationx, double _translationy, double _rotationx, double _rotationy, double _scalex, double _scaley, double _scalez, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t0, double _t1, double _t2, double _t4, double _t24, double _t25, double _t27, double _t28) {
        double _t30 = Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex);
        double _t31 = Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, _translationx))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, _translationy))));
        return Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez);
    }

    /** Part 2 of {@code composeTRSMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSMul_unsafe_s3c1255f5_2(long dest, double _translationz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t26, double _t29, double _t32) {
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, _translationz))));
        return dest;
    }

    /** Piece 2 of {@code composeTRSMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long composeTRSMul_unsafe_s3c1255f5_3(long dest, long m, double _translationx, double _translationy, double _translationz, double _rotationx, double _rotationy, double _rotationz, double _rotationw, double _scalex, double _scaley, double _scalez, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21) {
        double _m22 = UnsafeOpsHolder.U.getDouble(m + 80L);
        double _m23 = UnsafeOpsHolder.U.getDouble(m + 88L);
        double _t0 = _scalez + _scalez;
        double _t1 = _scalex + _scalex;
        double _t2 = _scaley + _scaley;
        double _t3 = _rotationy * _rotationw;
        double _t5 = _rotationz * _rotationw;
        return composeTRSMul_unsafe_s3c1255f5_2(dest, _translationz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, (Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2), (Math.fma(_rotationx, _rotationz, -_t3) * _t1), composeTRSMul_unsafe_s3c1255f5_1(dest, _translationx, _translationy, _rotationx, _rotationy, _scalex, _scaley, _scalez, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t0, _t1, _t2, (_rotationz * _rotationz), (Math.fma(_rotationx, _rotationz, _t3) * _t0), (Math.fma(_rotationx, _rotationy, _t5) * _t1), (Math.fma(_rotationx, _rotationy, -_t5) * _t2), (Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0)));
    }

    public static long lookAlong_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        return lookAlong_unsafe_s9c8999aa_1(dest, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t17, Math.fma(_t17, _t12, upX), Math.fma(_t17, _t13, upY));
    }

    /** Piece 2 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s9c8999aa_1(long dest, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13, double _t17, double _t18, double _t19) {
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = Math.fma(_t39, _t12, -(_t38 * _t13));
        double _t47 = Math.fma(_t37, _t13, -(_t39 * _t11));
        double _t48 = Math.fma(_t38, _t11, -(_t37 * _t12));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        return lookAlong_unsafe_s9c8999aa_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s9c8999aa_2(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long lookAlong_unsafe(long dest, long src, long dir, long up) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        return lookAlong_unsafe_s6c6e4e50_3(dest, up, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13);
    }

    /** Part 1 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static void lookAlong_unsafe_s6c6e4e50_1(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _t12, double _t13, double _t11, double _t39, double _t38, double _t37) {
        double _t46 = Math.fma(_t39, _t12, -(_t38 * _t13));
        double _t47 = Math.fma(_t37, _t13, -(_t39 * _t11));
        double _t48 = Math.fma(_t38, _t11, -(_t37 * _t12));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
    }

    /** Part 2 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s6c6e4e50_2(long dest, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13) {
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    /** Piece 2 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s6c6e4e50_3(long dest, long up, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13) {
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_unsafe_s6c6e4e50_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _t12, _t13, _t11, _t39, _t38, _t37);
        return lookAlong_unsafe_s6c6e4e50_2(dest, _self20, _self21, _self22, _self23, _t11, _t12, _t13);
    }

    public static long lookAt_lh(long dest, long src, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.lookAt_lh_unsafe(dest, src, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAt_lh_unsafe(long dest, long src, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        return lookAt_lh_unsafe_s29985a9c_1(dest, eyeX, eyeY, eyeZ, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16);
    }

    /** Piece 2 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_s29985a9c_1(long dest, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16) {
        double _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        double _t24 = Math.fma(_t23, _t14, upX);
        double _t25 = Math.fma(_t23, _t16, upY);
        double _t26 = Math.fma(_t23, _t15, upZ);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        return lookAt_lh_unsafe_s29985a9c_2(dest, eyeX, eyeY, eyeZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, Math.fma(_t44, _t16, -(_t45 * _t15)), Math.fma(_t43, _t15, -(_t44 * _t14)), Math.fma(_t45, _t14, -(_t43 * _t16)));
    }

    /** Piece 3 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_s29985a9c_2(long dest, double eyeX, double eyeY, double eyeZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56) {
        double _t58 = Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45));
        double _t60 = Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        return lookAt_lh_unsafe_s29985a9c_3(dest, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, _t58, _t60);
    }

    /** Piece 4 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_s29985a9c_3(long dest, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t58, double _t60) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    public static long lookAt_rh(long dest, long src, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.lookAt_rh_unsafe(dest, src, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAt_rh_unsafe(long dest, long src, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        return lookAt_rh_unsafe_sa30fb092_1(dest, eyeX, eyeY, eyeZ, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self02, -_self12, -_self22, _t17, _t18, _t19);
    }

    /** Piece 2 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_sa30fb092_1(long dest, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19) {
        double _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        double _t27 = Math.fma(_t26, _t19, upY);
        double _t28 = Math.fma(_t26, _t17, upX);
        double _t29 = Math.fma(_t26, _t18, upZ);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        return lookAt_rh_unsafe_sa30fb092_2(dest, eyeX, eyeY, eyeZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)), _t46, _t47, _t48, Math.fma(_t47, _t18, -(_t48 * _t19)), Math.fma(_t48, _t17, -(_t46 * _t18)), Math.fma(_t46, _t19, -(_t47 * _t17)));
    }

    /** Piece 3 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_sa30fb092_2(long dest, double eyeX, double eyeY, double eyeZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59) {
        double _t61 = Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47));
        double _t63 = Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59)));
        return lookAt_rh_unsafe_sa30fb092_3(dest, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, _t61, _t63);
    }

    /** Piece 4 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_sa30fb092_3(long dest, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t2, double _t17, double _t18, double _t19, double _t25, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23))));
        return dest;
    }

    public static long lookAt_lh(long dest, long src, long eye, long center, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.lookAt_lh_unsafe(dest, src, eye, center, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAt_lh_unsafe(long dest, long src, long eye, long center, long up) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _eyex = UnsafeOpsHolder.U.getDouble(eye);
        double _eyey = UnsafeOpsHolder.U.getDouble(eye + 8L);
        double _eyez = UnsafeOpsHolder.U.getDouble(eye + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        return lookAt_lh_unsafe_sa70b22ec_1(dest, center, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _upx, _upy, _upz);
    }

    /** Piece 2 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_sa70b22ec_1(long dest, long center, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz) {
        double _t0 = UnsafeOpsHolder.U.getDouble(center + 16L) - _eyez;
        double _t1 = UnsafeOpsHolder.U.getDouble(center) - _eyex;
        double _t2 = UnsafeOpsHolder.U.getDouble(center + 8L) - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        double _t24 = Math.fma(_t23, _t14, _upx);
        double _t25 = Math.fma(_t23, _t16, _upy);
        double _t26 = Math.fma(_t23, _t15, _upz);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        return lookAt_lh_unsafe_sa70b22ec_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t33, _t34, _t35, Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35)));
    }

    /** Piece 3 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_sa70b22ec_2(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t22, double _t33, double _t34, double _t35, double _t38) {
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        return lookAt_lh_unsafe_sa70b22ec_3(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)), Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
    }

    /** Piece 4 of {@code lookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_lh_unsafe_sa70b22ec_3(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t58, double _t60) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    public static long lookAt_rh(long dest, long src, long eye, long center, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.lookAt_rh_unsafe(dest, src, eye, center, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long lookAt_rh_unsafe(long dest, long src, long eye, long center, long up) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _eyex = UnsafeOpsHolder.U.getDouble(eye);
        double _eyey = UnsafeOpsHolder.U.getDouble(eye + 8L);
        double _eyez = UnsafeOpsHolder.U.getDouble(eye + 16L);
        double _t3 = UnsafeOpsHolder.U.getDouble(center + 16L) - _eyez;
        double _t4 = UnsafeOpsHolder.U.getDouble(center) - _eyex;
        double _t5 = UnsafeOpsHolder.U.getDouble(center + 8L) - _eyey;
        return lookAt_rh_unsafe_s19ae3a2e_3(dest, up, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _t3, _t4, _t5, Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5)));
    }

    /** Part 1 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static double lookAt_rh_unsafe_s19ae3a2e_1(long dest, double _self00, double _self01, double _self02, double _self03, double _eyex, double _eyey, double _eyez, double _t19, double _t17, double _t18, double _t25, double _t46, double _t47, double _t48, double _t61) {
        double _t0 = -_self02;
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        double _t63 = Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03))));
        return _t63;
    }

    /** Part 2 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_s19ae3a2e_2(long dest, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t17, double _t18, double _t19, double _t25, double _t46, double _t47, double _t48, double _t61, double _t63) {
        double _t1 = -_self12;
        double _t2 = -_self22;
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23))));
        return dest;
    }

    /** Piece 2 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_s19ae3a2e_3(long dest, long up, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _t3, double _t4, double _t5, double _t12) {
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        double _t27 = Math.fma(_t26, _t19, _upy);
        double _t28 = Math.fma(_t26, _t17, _upx);
        double _t29 = Math.fma(_t26, _t18, _upz);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        return lookAt_rh_unsafe_s19ae3a2e_4(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _t17, _t18, _t19, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)), _upx, _upy, _upz, _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 3 of {@code lookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAt_rh_unsafe_s19ae3a2e_4(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _t17, double _t18, double _t19, double _t25, double _upx, double _upy, double _upz, double _t36, double _t37, double _t38, double _t41, double _t42) {
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t61 = Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47));
        return lookAt_rh_unsafe_s19ae3a2e_2(dest, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t61, lookAt_rh_unsafe_s19ae3a2e_1(dest, _self00, _self01, _self02, _self03, _eyex, _eyey, _eyez, _t19, _t17, _t18, _t25, _t46, _t47, _t48, _t61));
    }

    public static long makeBillboardCylindrical_unsafe(long dest, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
        double _t3 = targetPosZ - objPosZ;
        double _t4 = targetPosX - objPosX;
        double _t5 = targetPosY - objPosY;
        double _t14 = Math.fma(upZ, _t3, Math.fma(upX, _t4, upY * _t5));
        double _t15 = Math.fma(-upY, _t14, _t5);
        double _t16 = Math.fma(-upX, _t14, _t4);
        double _t17 = Math.fma(-upZ, _t14, _t3);
        double _t26 = Math.fma(upX, _t15, -(upY * _t16));
        double _t27 = Math.fma(upY, _t17, -(upZ * _t15));
        double _t28 = Math.fma(upZ, _t16, -(upX * _t17));
        double _t31 = Math.fma(_t26, _t26, Math.fma(_t27, _t27, _t28 * _t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t31));
        double _t36, _t37, _t38;
        if (_t31 > Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5)) * Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29) {
            _t36 = _t27 * _t32;
            _t37 = _t28 * _t32;
            _t38 = _t26 * _t32;
        } else {
            _t36 = 0.0;
            _t37 = 0.0;
            _t38 = 0.0;
        }
        return makeBillboardCylindrical_unsafe_s3e4c67e4_1(dest, objPosX, objPosY, objPosZ, upX, upY, upZ, _t36, _t37, _t38, Math.fma(upY, _t36, -(upX * _t37)), Math.fma(upX, _t38, -(upZ * _t36)), Math.fma(upZ, _t37, -(upY * _t38)));
    }

    /** Piece 2 of {@code makeBillboardCylindrical_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardCylindrical_unsafe_s3e4c67e4_1(long dest, double objPosX, double objPosY, double objPosZ, double upX, double upY, double upZ, double _t36, double _t37, double _t38, double _t45, double _t46, double _t47) {
        double _t50 = Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t47 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 48L, _t46 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 80L, _t45 * _t51);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        }
        UnsafeOpsHolder.U.putDouble(dest, _t36);
        UnsafeOpsHolder.U.putDouble(dest + 8L, upX);
        UnsafeOpsHolder.U.putDouble(dest + 24L, objPosX);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 40L, upY);
        UnsafeOpsHolder.U.putDouble(dest + 56L, objPosY);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 72L, upZ);
        UnsafeOpsHolder.U.putDouble(dest + 88L, objPosZ);
        return dest;
    }

    public static long makeBillboardCylindrical_unsafe(long dest, long objPos, long targetPos, long up) {
        double _objPosx = UnsafeOpsHolder.U.getDouble(objPos);
        double _objPosy = UnsafeOpsHolder.U.getDouble(objPos + 8L);
        double _objPosz = UnsafeOpsHolder.U.getDouble(objPos + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t3 = UnsafeOpsHolder.U.getDouble(targetPos + 16L) - _objPosz;
        double _t4 = UnsafeOpsHolder.U.getDouble(targetPos) - _objPosx;
        double _t5 = UnsafeOpsHolder.U.getDouble(targetPos + 8L) - _objPosy;
        double _t14 = Math.fma(_upz, _t3, Math.fma(_upx, _t4, _upy * _t5));
        double _t15 = Math.fma(-_upy, _t14, _t5);
        double _t16 = Math.fma(-_upx, _t14, _t4);
        double _t17 = Math.fma(-_upz, _t14, _t3);
        double _t26 = Math.fma(_upx, _t15, -(_upy * _t16));
        double _t27 = Math.fma(_upy, _t17, -(_upz * _t15));
        double _t28 = Math.fma(_upz, _t16, -(_upx * _t17));
        double _t31 = Math.fma(_t26, _t26, Math.fma(_t27, _t27, _t28 * _t28));
        return makeBillboardCylindrical_unsafe_s21740328_1(dest, _objPosx, _objPosy, _objPosz, _upx, _upy, _upz, _t3, _t4, _t5, _t26, _t27, _t28, _t31, (1.0 / java.lang.Math.sqrt(_t31)));
    }

    /** Piece 2 of {@code makeBillboardCylindrical_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardCylindrical_unsafe_s21740328_1(long dest, double _objPosx, double _objPosy, double _objPosz, double _upx, double _upy, double _upz, double _t3, double _t4, double _t5, double _t26, double _t27, double _t28, double _t31, double _t32) {
        double _t36, _t37, _t38;
        if (_t31 > Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5)) * Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)) * 5.048709793414476E-29) {
            _t36 = _t27 * _t32;
            _t37 = _t28 * _t32;
            _t38 = _t26 * _t32;
        } else {
            _t36 = 0.0;
            _t37 = 0.0;
            _t38 = 0.0;
        }
        double _t45 = Math.fma(_upy, _t36, -(_upx * _t37));
        double _t46 = Math.fma(_upx, _t38, -(_upz * _t36));
        double _t47 = Math.fma(_upz, _t37, -(_upy * _t38));
        double _t50 = Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t47 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 48L, _t46 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 80L, _t45 * _t51);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        }
        UnsafeOpsHolder.U.putDouble(dest, _t36);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _upx);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _objPosx);
        return makeBillboardCylindrical_unsafe_s21740328_2(dest, _objPosy, _objPosz, _upy, _upz, _t37, _t38);
    }

    /** Piece 3 of {@code makeBillboardCylindrical_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardCylindrical_unsafe_s21740328_2(long dest, double _objPosy, double _objPosz, double _upy, double _upz, double _t37, double _t38) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _upy);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _objPosy);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _upz);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _objPosz);
        return dest;
    }

    public static long makeBillboardSpherical_unsafe(long dest, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
        double _t0 = targetPosZ - objPosZ;
        double _t1 = targetPosX - objPosX;
        double _t2 = targetPosY - objPosY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -Math.fma(upZ, _t14, Math.fma(upX, _t15, upY * _t16));
        double _t21 = Math.fma(_t20, _t15, upX);
        double _t22 = Math.fma(_t20, _t16, upY);
        double _t23 = Math.fma(_t20, _t14, upZ);
        double _t30 = Math.fma(_t21, _t16, -(_t22 * _t15));
        double _t31 = Math.fma(_t22, _t14, -(_t23 * _t16));
        double _t32 = Math.fma(_t23, _t15, -(_t21 * _t14));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeBillboardSpherical_unsafe_se39b7473_1(dest, objPosX, objPosY, objPosZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeBillboardSpherical_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardSpherical_unsafe_se39b7473_1(long dest, double objPosX, double objPosY, double objPosZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t31 * _t36;
            _t41 = _t30 * _t36;
            _t42 = _t32 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t40);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t41, _t16, -(_t42 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t15);
        UnsafeOpsHolder.U.putDouble(dest + 24L, objPosX);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t42);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t40, _t14, -(_t41 * _t15)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t16);
        UnsafeOpsHolder.U.putDouble(dest + 56L, objPosY);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t41);
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_t42, _t15, -(_t40 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t14);
        UnsafeOpsHolder.U.putDouble(dest + 88L, objPosZ);
        return dest;
    }

    public static long makeBillboardSpherical_unsafe(long dest, long objPos, long targetPos, long up) {
        double _objPosx = UnsafeOpsHolder.U.getDouble(objPos);
        double _objPosy = UnsafeOpsHolder.U.getDouble(objPos + 8L);
        double _objPosz = UnsafeOpsHolder.U.getDouble(objPos + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t0 = UnsafeOpsHolder.U.getDouble(targetPos + 16L) - _objPosz;
        double _t1 = UnsafeOpsHolder.U.getDouble(targetPos) - _objPosx;
        double _t2 = UnsafeOpsHolder.U.getDouble(targetPos + 8L) - _objPosy;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -Math.fma(_upz, _t14, Math.fma(_upx, _t15, _upy * _t16));
        double _t21 = Math.fma(_t20, _t15, _upx);
        double _t22 = Math.fma(_t20, _t16, _upy);
        double _t23 = Math.fma(_t20, _t14, _upz);
        return makeBillboardSpherical_unsafe_sc497799_1(dest, _objPosx, _objPosy, _objPosz, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t16, -(_t22 * _t15)), Math.fma(_t22, _t14, -(_t23 * _t16)), Math.fma(_t23, _t15, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeBillboardSpherical_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardSpherical_unsafe_sc497799_1(long dest, double _objPosx, double _objPosy, double _objPosz, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t31 * _t36;
            _t41 = _t30 * _t36;
            _t42 = _t32 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t40);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t41, _t16, -(_t42 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t15);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _objPosx);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t42);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t40, _t14, -(_t41 * _t15)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t16);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _objPosy);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t41);
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_t42, _t15, -(_t40 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t14);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _objPosz);
        return dest;
    }

    public static long makeBillboardSphericalShortest_unsafe(long dest, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ) {
        double _t0 = targetPosZ - objPosZ;
        double _t1 = targetPosX - objPosX;
        double _t2 = targetPosY - objPosY;
        double _t3 = _t2 + _t2;
        double _t6 = Math.fma(_t1, _t1, _t2 * _t2);
        double _t9 = java.lang.Math.sqrt(java.lang.Math.max(Math.fma(_t0, _t0, _t6), 8.900295434028806E-308));
        double _t11 = _t0 + _t9;
        double _t12 = Math.fma(_t11, _t11, _t6);
        double _t14 = _t12 / _t9;
        double _t15 = _t12 > 2.2250738585072014E-308 ? _t1 : _t9;
        double _t25_inv = 1.0 / Math.fma(0.25, _t14 * _t14, Math.fma(_t2, _t2, _t15 * _t15));
        double _sp1 = _t2 * _t25_inv;
        double _sp0 = _t15 * _t25_inv;
        double _t26 = _sp1 * _t3;
        double _t27 = _sp1 * _t14;
        double _t29 = -(_t3 * _sp0);
        double _t30 = _sp0 * _t14;
        double _t32 = 1.0 - (_sp0 + _sp0) * _t15;
        UnsafeOpsHolder.U.putDouble(dest, _t32);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t29);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t30);
        UnsafeOpsHolder.U.putDouble(dest + 24L, objPosX);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t29);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0 - _t26);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t27);
        UnsafeOpsHolder.U.putDouble(dest + 56L, objPosY);
        return makeBillboardSphericalShortest_unsafe_s2ea0b73a_1(dest, objPosZ, _t26, _t27, _t30, _t32);
    }

    /** Piece 2 of {@code makeBillboardSphericalShortest_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardSphericalShortest_unsafe_s2ea0b73a_1(long dest, double objPosZ, double _t26, double _t27, double _t30, double _t32) {
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t30);
        UnsafeOpsHolder.U.putDouble(dest + 72L, -_t27);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t32 - _t26);
        UnsafeOpsHolder.U.putDouble(dest + 88L, objPosZ);
        return dest;
    }

    public static long makeBillboardSphericalShortest_unsafe(long dest, long objPos, long targetPos) {
        double _objPosx = UnsafeOpsHolder.U.getDouble(objPos);
        double _objPosy = UnsafeOpsHolder.U.getDouble(objPos + 8L);
        double _objPosz = UnsafeOpsHolder.U.getDouble(objPos + 16L);
        double _t0 = UnsafeOpsHolder.U.getDouble(targetPos + 16L) - _objPosz;
        double _t1 = UnsafeOpsHolder.U.getDouble(targetPos) - _objPosx;
        double _t2 = UnsafeOpsHolder.U.getDouble(targetPos + 8L) - _objPosy;
        double _t3 = _t2 + _t2;
        double _t6 = Math.fma(_t1, _t1, _t2 * _t2);
        double _t9 = java.lang.Math.sqrt(java.lang.Math.max(Math.fma(_t0, _t0, _t6), 8.900295434028806E-308));
        double _t11 = _t0 + _t9;
        double _t12 = Math.fma(_t11, _t11, _t6);
        double _t14 = _t12 / _t9;
        double _t15 = _t12 > 2.2250738585072014E-308 ? _t1 : _t9;
        double _t25_inv = 1.0 / Math.fma(0.25, _t14 * _t14, Math.fma(_t2, _t2, _t15 * _t15));
        double _sp1 = _t2 * _t25_inv;
        double _sp0 = _t15 * _t25_inv;
        double _t29 = -(_t3 * _sp0);
        double _t30 = _sp0 * _t14;
        double _t32 = 1.0 - (_sp0 + _sp0) * _t15;
        UnsafeOpsHolder.U.putDouble(dest, _t32);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t29);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t30);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _objPosx);
        return makeBillboardSphericalShortest_unsafe_s38c4d0a4_1(dest, _objPosy, _objPosz, _sp1 * _t3, _sp1 * _t14, _t29, _t30, _t32);
    }

    /** Piece 2 of {@code makeBillboardSphericalShortest_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeBillboardSphericalShortest_unsafe_s38c4d0a4_1(long dest, double _objPosy, double _objPosz, double _t26, double _t27, double _t29, double _t30, double _t32) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t29);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0 - _t26);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t27);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _objPosy);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t30);
        UnsafeOpsHolder.U.putDouble(dest + 72L, -_t27);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t32 - _t26);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _objPosz);
        return dest;
    }

    public static long makeFromDualQuat_unsafe(long dest, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = Math.fma(-2.0, dqRZ * dqRZ, 1.0);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-2.0, _t0, _t6));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-2.0, _t2, _sp0 * dqRY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * Math.fma(dqRX, dqRZ, _t3));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, 2.0 * Math.fma(dqRX, dqRY, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(-2.0, _t4, _t6));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(-2.0, dqRX * dqRW, _t5 + _t5));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))));
        return makeFromDualQuat_unsafe_s3de11ba7_1(dest, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW, _sp0, _t0, _t3, _t4, _t5);
    }

    /** Piece 2 of {@code makeFromDualQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeFromDualQuat_unsafe_s3de11ba7_1(long dest, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW, double _sp0, double _t0, double _t3, double _t4, double _t5) {
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(-2.0, _t3, _sp0 * dqRZ));
        UnsafeOpsHolder.U.putDouble(dest + 72L, 2.0 * Math.fma(dqRX, dqRW, _t5));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 2.0 * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))));
        return dest;
    }

    public static long makeLookAt_lh(long dest, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.makeLookAt_lh_unsafe(dest, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeLookAt_lh_unsafe(long dest, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -Math.fma(upZ, _t14, Math.fma(upX, _t15, upY * _t16));
        double _t21 = Math.fma(_t20, _t15, upX);
        double _t22 = Math.fma(_t20, _t16, upY);
        double _t23 = Math.fma(_t20, _t14, upZ);
        double _t30 = Math.fma(_t21, _t16, -(_t22 * _t15));
        double _t31 = Math.fma(_t22, _t14, -(_t23 * _t16));
        double _t32 = Math.fma(_t23, _t15, -(_t21 * _t14));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_lh_unsafe_s9c499eb6_1(dest, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_lh_unsafe_s9c499eb6_1(long dest, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = Math.fma(_t42, _t16, -(_t41 * _t14));
        double _t50 = Math.fma(_t40, _t14, -(_t42 * _t15));
        double _t51 = Math.fma(_t41, _t15, -(_t40 * _t16));
        UnsafeOpsHolder.U.putDouble(dest, _t40);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t41);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t42);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t49);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t50);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t51);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t15);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t16);
        return makeLookAt_lh_unsafe_s9c499eb6_2(dest, eyeX, eyeY, eyeZ, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_lh_unsafe_s9c499eb6_2(long dest, double eyeX, double eyeY, double eyeZ, double _t14, double _t15, double _t16) {
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t14);
        UnsafeOpsHolder.U.putDouble(dest + 88L, -Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16)));
        return dest;
    }

    public static long makeLookAt_rh(long dest, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.makeLookAt_rh_unsafe(dest, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeLookAt_rh_unsafe(long dest, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -Math.fma(upZ, _t14, Math.fma(upX, _t15, upY * _t16));
        double _t21 = Math.fma(_t20, _t16, upY);
        double _t22 = Math.fma(_t20, _t15, upX);
        double _t23 = Math.fma(_t20, _t14, upZ);
        double _t30 = Math.fma(_t21, _t15, -(_t22 * _t16));
        double _t31 = Math.fma(_t22, _t14, -(_t23 * _t15));
        double _t32 = Math.fma(_t23, _t16, -(_t21 * _t14));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_rh_unsafe_s89e6cb28_1(dest, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_rh_unsafe_s89e6cb28_1(long dest, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = Math.fma(_t41, _t14, -(_t42 * _t16));
        double _t50 = Math.fma(_t42, _t15, -(_t40 * _t14));
        double _t51 = Math.fma(_t40, _t16, -(_t41 * _t15));
        UnsafeOpsHolder.U.putDouble(dest, _t40);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t41);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t42);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t49);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t50);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t51);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t15);
        UnsafeOpsHolder.U.putDouble(dest + 72L, -_t16);
        return makeLookAt_rh_unsafe_s89e6cb28_2(dest, eyeX, eyeY, eyeZ, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_rh_unsafe_s89e6cb28_2(long dest, double eyeX, double eyeY, double eyeZ, double _t14, double _t15, double _t16) {
        UnsafeOpsHolder.U.putDouble(dest + 80L, -_t14);
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16)));
        return dest;
    }

    public static long makeLookAt_lh(long dest, long eye, long center, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.makeLookAt_lh_unsafe(dest, eye, center, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeLookAt_lh_unsafe(long dest, long eye, long center, long up) {
        double _eyex = UnsafeOpsHolder.U.getDouble(eye);
        double _eyey = UnsafeOpsHolder.U.getDouble(eye + 8L);
        double _eyez = UnsafeOpsHolder.U.getDouble(eye + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t0 = UnsafeOpsHolder.U.getDouble(center + 16L) - _eyez;
        double _t1 = UnsafeOpsHolder.U.getDouble(center) - _eyex;
        double _t2 = UnsafeOpsHolder.U.getDouble(center + 8L) - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -Math.fma(_upz, _t14, Math.fma(_upx, _t15, _upy * _t16));
        double _t21 = Math.fma(_t20, _t15, _upx);
        double _t22 = Math.fma(_t20, _t16, _upy);
        double _t23 = Math.fma(_t20, _t14, _upz);
        return makeLookAt_lh_unsafe_sff827952_1(dest, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t16, -(_t22 * _t15)), Math.fma(_t22, _t14, -(_t23 * _t16)), Math.fma(_t23, _t15, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_lh_unsafe_sff827952_1(long dest, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = Math.fma(_t42, _t16, -(_t41 * _t14));
        double _t50 = Math.fma(_t40, _t14, -(_t42 * _t15));
        double _t51 = Math.fma(_t41, _t15, -(_t40 * _t16));
        UnsafeOpsHolder.U.putDouble(dest, _t40);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t41);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t42);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t49);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t50);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t51);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t15);
        return makeLookAt_lh_unsafe_sff827952_2(dest, _eyex, _eyey, _eyez, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_lh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_lh_unsafe_sff827952_2(long dest, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t16);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t14);
        UnsafeOpsHolder.U.putDouble(dest + 88L, -Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static long makeLookAt_rh(long dest, long eye, long center, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x4OpsKernelsAddress.makeLookAt_rh_unsafe(dest, eye, center, up);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long makeLookAt_rh_unsafe(long dest, long eye, long center, long up) {
        double _eyex = UnsafeOpsHolder.U.getDouble(eye);
        double _eyey = UnsafeOpsHolder.U.getDouble(eye + 8L);
        double _eyez = UnsafeOpsHolder.U.getDouble(eye + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t0 = UnsafeOpsHolder.U.getDouble(center + 16L) - _eyez;
        double _t1 = UnsafeOpsHolder.U.getDouble(center) - _eyex;
        double _t2 = UnsafeOpsHolder.U.getDouble(center + 8L) - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -Math.fma(_upz, _t14, Math.fma(_upx, _t15, _upy * _t16));
        double _t21 = Math.fma(_t20, _t16, _upy);
        double _t22 = Math.fma(_t20, _t15, _upx);
        double _t23 = Math.fma(_t20, _t14, _upz);
        return makeLookAt_rh_unsafe_sc2aff5a8_1(dest, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t15, -(_t22 * _t16)), Math.fma(_t22, _t14, -(_t23 * _t15)), Math.fma(_t23, _t16, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_rh_unsafe_sc2aff5a8_1(long dest, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = Math.fma(_t41, _t14, -(_t42 * _t16));
        double _t50 = Math.fma(_t42, _t15, -(_t40 * _t14));
        double _t51 = Math.fma(_t40, _t16, -(_t41 * _t15));
        UnsafeOpsHolder.U.putDouble(dest, _t40);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t41);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t42);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t49);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t50);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t51);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t15);
        return makeLookAt_rh_unsafe_sc2aff5a8_2(dest, _eyex, _eyey, _eyez, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_rh_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeLookAt_rh_unsafe_sc2aff5a8_2(long dest, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, -_t16);
        UnsafeOpsHolder.U.putDouble(dest + 80L, -_t14);
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static long makeMappingXYZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingXYnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingXZY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingXZnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingXnYZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingXnYnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingXnZY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingXnZnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingYXZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingYXnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingYZX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingYZnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingYnXZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingYnXnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingYnZX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingYnZnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingZXY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingZXnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingZYX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingZYnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingZnXY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingZnXnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingZnYX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingZnYnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnXYZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnXYnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnXZY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnXZnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnXnYZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnXnYnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnXnZY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnXnZnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnYXZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnYXnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnYZX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnYZnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnYnXZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnYnXnZ_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnYnZX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnYnZnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnZXY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnZXnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnZYX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnZYnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnZnXY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnZnXnY_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnZnYX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeMappingnZnYnX_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -1.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeReflection_unsafe(long dest, double normalX, double normalY, double normalZ) {
        double _sp0 = normalX + normalX;
        double _t6 = -(_sp0 * normalY);
        double _t7 = -(_sp0 * normalZ);
        double _t8 = -((normalY + normalY) * normalZ);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-2.0, normalX * normalX, 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t6);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t7);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t6);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(-2.0, normalY * normalY, 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t8);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t7);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t8);
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(-2.0, normalZ * normalZ, 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeReflection_unsafe(long dest, long normal) {
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _sp0 = _normalx + _normalx;
        double _t6 = -(_sp0 * _normaly);
        double _t7 = -(_sp0 * _normalz);
        double _t8 = -((_normaly + _normaly) * _normalz);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-2.0, _normalx * _normalx, 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t6);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t7);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t6);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(-2.0, _normaly * _normaly, 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t8);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t7);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t8);
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(-2.0, _normalz * _normalz, 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t5, axisX * axisX, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t5, _t2, -(axisZ * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(axisY, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(axisZ, _t0, _t5 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t5, axisY * axisY, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t5, _t4, -(axisX * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t5, _t3, -(axisY * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(axisX, _t0, _t5 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_t5, axisZ * axisZ, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, long axis, double angle) {
        double _t0 = Math.sin(angle);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisy;
        double _t3 = _axisx * _axisz;
        double _t4 = _axisy * _axisz;
        double _t5 = 1.0 - _t1;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t5, _axisx * _axisx, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t5, _t2, -(_axisz * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_axisy, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_axisz, _t0, _t5 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t5, _axisy * _axisy, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t5, _t4, -(_axisx * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t5, _t3, -(_axisy * _t0)));
        return makeRotationAxis_unsafe_scc01a1ba_1(dest, _t0, _axisx, _axisz, _t1, _t4, _t5);
    }

    /** Piece 2 of {@code makeRotationAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeRotationAxis_unsafe_scc01a1ba_1(long dest, double _t0, double _axisx, double _axisz, double _t1, double _t4, double _t5) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_axisx, _t0, _t5 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_t5, _axisz * _axisz, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationLookAlong_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        return makeRotationLookAlong_unsafe_s73151e32_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_unsafe_s73151e32_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t11);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationLookAlong_unsafe(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_unsafe_sf184e578_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_unsafe_sf184e578_1(long dest, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t11);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationQuat_unsafe(long dest, double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * Math.fma(qX, qY, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * Math.fma(qX, qZ, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 2.0 * Math.fma(qX, qY, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * Math.fma(qY, qZ, -(qX * qW)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 2.0 * Math.fma(qX, qZ, -_t2));
        UnsafeOpsHolder.U.putDouble(dest + 72L, 2.0 * Math.fma(qX, qW, qY * qZ));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationQuat_unsafe(long dest, long q) {
        double _qx = UnsafeOpsHolder.U.getDouble(q);
        double _qy = UnsafeOpsHolder.U.getDouble(q + 8L);
        double _qz = UnsafeOpsHolder.U.getDouble(q + 16L);
        double _qw = UnsafeOpsHolder.U.getDouble(q + 24L);
        double _t0 = _qz * _qz;
        double _t1 = _qz * _qw;
        double _t2 = _qy * _qw;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-2.0, Math.fma(_qy, _qy, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * Math.fma(_qx, _qy, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * Math.fma(_qx, _qz, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 2.0 * Math.fma(_qx, _qy, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(-2.0, Math.fma(_qx, _qx, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * Math.fma(_qy, _qz, -(_qx * _qw)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 2.0 * Math.fma(_qx, _qz, -_t2));
        return makeRotationQuat_unsafe_s6b2a457a_1(dest, _qx, _qy, _qz, _qw);
    }

    /** Piece 2 of {@code makeRotationQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeRotationQuat_unsafe_s6b2a457a_1(long dest, double _qx, double _qy, double _qz, double _qw) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, 2.0 * Math.fma(_qx, _qw, _qy * _qz));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(-2.0, Math.fma(_qx, _qx, _qy * _qy), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationX_unsafe(long dest, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationXYZ_unsafe(long dest, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t0;
        double _t7 = _t0 * _t5;
        UnsafeOpsHolder.U.putDouble(dest, _t3 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t1 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t6, _t4, _t1 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t5, _t4, -(_t6 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, -(_t2 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t2, _t1, -(_t7 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_t7, _t1, _t2 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t5 * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationXZY_unsafe(long dest, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t1 * _t5;
        UnsafeOpsHolder.U.putDouble(dest, _t3 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_t1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t0 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t7, _t3, _t2 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t5 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t7, _t0, -(_t2 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t6, _t3, -(_t0 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t2 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_t6, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationY_unsafe(long dest, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationYXZ_unsafe(long dest, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t6, _t2, _t3 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t6, _t4, -(_t2 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t1 * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t2 * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t5 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t7, _t2, -(_t1 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_t7, _t4, _t1 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t5 * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationYZX_unsafe(long dest, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t1 * _t3;
        UnsafeOpsHolder.U.putDouble(dest, _t3 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t2, _t0, -(_t7 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t7, _t2, _t0 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t5 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -(_t2 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -(_t0 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_t6, _t5, _t2 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_t5, _t3, -(_t6 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationZ_unsafe(long dest, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationZXY_unsafe(long dest, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t3, _t4, -(_t6 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t1 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t6, _t3, _t0 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t7, _t0, _t1 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t5 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t0, _t1, -(_t7 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -(_t0 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t2);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t5 * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeRotationZYX_unsafe(long dest, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t4;
        UnsafeOpsHolder.U.putDouble(dest, _t3 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t7, _t2, -(_t1 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t7, _t5, _t2 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t1 * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t6, _t2, _t5 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t6, _t5, -(_t2 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t2 * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _t5 * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, double vX, double vY, double vZ) {
        UnsafeOpsHolder.U.putDouble(dest, vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, vY);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, vZ);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _vy);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _vz);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, double s) {
        UnsafeOpsHolder.U.putDouble(dest, s);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, s);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, s);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, double vX, double vY, double vZ) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, vX);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, vY);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, vZ);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _vy);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 80L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _vz);
        return dest;
    }

    public static long mapXYZ_unsafe(long dest, long src) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, _eself);
        }
        return dest;
    }

    public static long mapXYnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapXZY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapXZnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapXnYZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapXnYnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapXnZY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapXnZnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapYXZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapYXnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapYZX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapYZnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapYnXZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapYnXnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapYnZX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapYnZnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapZXY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapZXnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapZYX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapZYnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapZnXY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapZnXnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapZnYX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapZnYnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnXYZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnXYnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnXZY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnXZnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnXnYZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnXnYnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnXnZY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnXnZnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnYXZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnYXnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnYZX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnYZnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnYnXZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnYnXnZ_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnYnZX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnYnZnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnZXY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnZXnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnZYX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnZYnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnZnXY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnZnXnY_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnZnYX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long mapnZnYnX_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t4 = rotX + rotX;
        double _t5 = rotY + rotY;
        double _t6 = rotZ + rotZ;
        double _t7 = rotW * _t5;
        double _t8 = rotW * _t6;
        double _t10 = rotW * _t4;
        return preRotateAround_unsafe_s55c744ed_1(dest, rotX, rotY, rotZ, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -rotY, -pivotZ, -rotX, _t4, _t5, _t7, rotZ * _t6, _t10, Math.fma(-rotZ, _t6, 1.0), Math.fma(rotZ, _t4, _t7), Math.fma(rotY, _t4, _t8), Math.fma(rotZ, _t5, _t10), Math.fma(rotY, _t4, -_t8));
    }

    /** Piece 2 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_s55c744ed_1(long dest, double rotX, double rotY, double rotZ, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t2, double _t3, double _t4, double _t5, double _t7, double _t9, double _t10, double _t14, double _t16, double _t17, double _t18, double _t19) {
        double _t20 = Math.fma(rotZ, _t5, -_t10);
        double _t22 = Math.fma(_t0, _t5, _t14);
        double _t23 = Math.fma(_t3, _t4, _t14);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        return preRotateAround_unsafe_s55c744ed_2(dest, rotX, rotY, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t2, _t4, _t5, _t9, _t17, _t18, _t20, Math.fma(rotZ, _t4, -_t7), _t23, Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)));
    }

    /** Piece 3 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_s55c744ed_2(long dest, double rotX, double rotY, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t2, double _t4, double _t5, double _t9, double _t17, double _t18, double _t20, double _t21, double _t23, double _t24) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, long rot, long pivot) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _rotx = UnsafeOpsHolder.U.getDouble(rot);
        double _roty = UnsafeOpsHolder.U.getDouble(rot + 8L);
        double _rotz = UnsafeOpsHolder.U.getDouble(rot + 16L);
        double _rotw = UnsafeOpsHolder.U.getDouble(rot + 24L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(pivot + 16L);
        return preRotateAround_unsafe_s3a93b5f4_3(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _rotz, _rotw, _pivotx, _pivoty, _pivotz, -_roty, -_rotx, _rotx + _rotx, _roty + _roty, _rotz + _rotz);
    }

    /** Part 1 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static double preRotateAround_unsafe_s3a93b5f4_1(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _pivotx, double _pivoty, double _t0, double _t2, double _t3, double _t4, double _t5, double _t9, double _t16, double _t17, double _t19, double _t20, double _t22, double _t23) {
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))));
        return Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0));
    }

    /** Part 2 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_s3a93b5f4_2(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _pivotx, double _pivoty, double _pivotz, double _t4, double _t5, double _t18, double _t21, double _t24) {
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    /** Piece 2 of {@code preRotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_s3a93b5f4_3(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _rotz, double _rotw, double _pivotx, double _pivoty, double _pivotz, double _t0, double _t3, double _t4, double _t5, double _t6) {
        double _t7 = _rotw * _t5;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t4;
        double _t14 = Math.fma(-_rotz, _t6, 1.0);
        return preRotateAround_unsafe_s3a93b5f4_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _pivotx, _pivoty, _pivotz, _t4, _t5, Math.fma(_rotz, _t5, _t10), Math.fma(_rotz, _t4, -_t7), preRotateAround_unsafe_s3a93b5f4_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _pivotx, _pivoty, _t0, (-_pivotz), _t3, _t4, _t5, (_rotz * _t6), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_t0, _t5, _t14), Math.fma(_t3, _t4, _t14)));
    }

    public static long preRotateAxis_unsafe(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisX * axisY;
        double _t11 = 1.0 - _t1;
        return preRotateAxis_unsafe_sdece3e6c_1(dest, axisX, axisY, axisZ, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t2, _t4, axisY * axisZ, _t11, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t4));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_sdece3e6c_1(long dest, double axisX, double axisY, double axisZ, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t2, double _t4, double _t6, double _t11, double _t18, double _t19, double _t20, double _t21, double _t22) {
        double _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        double _t25 = Math.fma(_t11, _t6, -(axisX * _t0));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        return preRotateAxis_unsafe_sdece3e6c_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t19, _t20, _t22, Math.fma(axisX, _t0, _t11 * _t6), _t25, Math.fma(_t11, _t2, -(axisY * _t0)));
    }

    /** Piece 3 of {@code preRotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_sdece3e6c_2(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t19, double _t20, double _t22, double _t23, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static long preRotateAxis_unsafe(long dest, long src, long axis, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t11 = 1.0 - _t1;
        return preRotateAxis_unsafe_s79e863b2_1(dest, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _axisx, _axisy, _axisz, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_s79e863b2_1(long dest, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _axisx, double _axisy, double _axisz, double _t2, double _t4, double _t6, double _t11, double _t18, double _t19, double _t20) {
        double _t21 = Math.fma(_axisy, _t0, _t11 * _t2);
        double _t22 = Math.fma(_axisz, _t0, _t11 * _t4);
        double _t24 = Math.fma(_t11, _t4, -(_axisz * _t0));
        double _t25 = Math.fma(_t11, _t6, -(_axisx * _t0));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        return preRotateAxis_unsafe_s79e863b2_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t19, _t20, _t22, Math.fma(_axisx, _t0, _t11 * _t6), _t25, Math.fma(_t11, _t2, -(_axisy * _t0)));
    }

    /** Piece 3 of {@code preRotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_s79e863b2_2(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t19, double _t20, double _t22, double _t23, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static long preRotateQuat_unsafe(long dest, long src, double qX, double qY, double qZ, double qW) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        return preRotateQuat_unsafe_sc626dde3_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -qY, -qX, _t3, _t4, Math.fma(-qZ, _t5, 1.0), Math.fma(qZ, _t3, _t6), Math.fma(qY, _t3, _t7), Math.fma(qZ, _t4, _t8), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8), Math.fma(qZ, _t3, -_t6));
    }

    /** Piece 2 of {@code preRotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateQuat_unsafe_sc626dde3_1(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t2, double _t3, double _t4, double _t12, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19) {
        double _t20 = Math.fma(_t0, _t4, _t12);
        double _t21 = Math.fma(_t2, _t3, _t12);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        return preRotateQuat_unsafe_sc626dde3_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t16, _t19, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 3 of {@code preRotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateQuat_unsafe_sc626dde3_2(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t16, double _t19, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static long preRotateQuat_unsafe(long dest, long src, long q) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _qx = UnsafeOpsHolder.U.getDouble(q);
        double _qy = UnsafeOpsHolder.U.getDouble(q + 8L);
        double _qz = UnsafeOpsHolder.U.getDouble(q + 16L);
        double _qw = UnsafeOpsHolder.U.getDouble(q + 24L);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        double _t6 = _qw * _t4;
        return preRotateQuat_unsafe_s1861b222_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _qy, _qz, -_qy, -_qx, _t3, _t4, _t6, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0), Math.fma(_qz, _t3, _t6));
    }

    /** Piece 2 of {@code preRotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateQuat_unsafe_s1861b222_1(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _qy, double _qz, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12, double _t14) {
        double _t15 = Math.fma(_qy, _t3, _t7);
        double _t17 = Math.fma(_qy, _t3, -_t7);
        double _t18 = Math.fma(_qz, _t4, -_t8);
        double _t20 = Math.fma(_t0, _t4, _t12);
        double _t21 = Math.fma(_t2, _t3, _t12);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        return preRotateQuat_unsafe_s1861b222_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t15, Math.fma(_qz, _t4, _t8), _t18, Math.fma(_qz, _t3, -_t6), _t21, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 3 of {@code preRotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateQuat_unsafe_s1861b222_2(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t15, double _t16, double _t18, double _t19, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static long preRotateX_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self10, _t1, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self11, _t1, -(_self21 * _t0)));
        return preRotateX_unsafe_sb1baf87e_1(dest, _t0, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code preRotateX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateX_unsafe_sb1baf87e_1(long dest, double _t0, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t1, -(_self22 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self13, _t1, -(_self23 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self10, _t0, _self20 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self11, _t0, _self21 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self12, _t0, _self22 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self13, _t0, _self23 * _t1));
        return dest;
    }

    public static long preRotateY_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self00, _t1, _self20 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self01, _t1, _self21 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t1, _self22 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self03, _t1, _self23 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self10);
        return preRotateY_unsafe_se78570d3_1(dest, _t0, _self00, _self01, _self02, _self03, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code preRotateY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateY_unsafe_se78570d3_1(long dest, double _t0, double _self00, double _self01, double _self02, double _self03, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self21, _t1, -(_self01 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t1, -(_self02 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self23, _t1, -(_self03 * _t0)));
        return dest;
    }

    public static long preRotateZ_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self00, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self01, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t1, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self03, _t1, -(_self13 * _t0)));
        return preRotateZ_unsafe_sd5410768_1(dest, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code preRotateZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preRotateZ_unsafe_sd5410768_1(long dest, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, _t0, _self10 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self01, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t0, _self12 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self03, _t0, _self13 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, double vX, double vY, double vZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        UnsafeOpsHolder.U.putDouble(dest, _self00 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self10 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self11 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self12 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20 * vZ);
        return preScale_unsafe_s52c8957_1(dest, vZ, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preScale_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScale_unsafe_s52c8957_1(long dest, double vZ, double _self21, double _self22, double _self23) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self21 * vZ);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22 * vZ);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23 * vZ);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _self00 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self10 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self11 * _vy);
        return preScale_unsafe_s6e76cf75_1(dest, _self12, _self13, _self20, _self21, _self22, _self23, _vy, _vz);
    }

    /** Piece 2 of {@code preScale_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScale_unsafe_s6e76cf75_1(long dest, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _vy, double _vz) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self12 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20 * _vz);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self21 * _vz);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22 * _vz);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23 * _vz);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, double s) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, s * _eself);
        }
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, double s, double pivotX, double pivotY, double pivotZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t0 = 1.0 - s;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(s, _self03, pivotX * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 32L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, s * _self12);
        return preScaleAround_unsafe_s5365dad8_1(dest, s, pivotY, pivotZ, _self13, _self20, _self21, _self22, _self23, _t0);
    }

    /** Piece 2 of {@code preScaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_s5365dad8_1(long dest, double s, double pivotY, double pivotZ, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(s, _self13, pivotY * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 64L, s * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, s * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, s * _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(s, _self23, pivotZ * _t0));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long pivot, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(pivot + 16L);
        double _t0 = 1.0 - s;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(s, _self03, _pivotx * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 32L, s * _self10);
        return preScaleAround_unsafe_s1f4fb5d0_1(dest, s, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _pivoty, _pivotz, _t0);
    }

    /** Piece 2 of {@code preScaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_s1f4fb5d0_1(long dest, double s, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _pivoty, double _pivotz, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, s * _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(s, _self13, _pivoty * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 64L, s * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, s * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, s * _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(s, _self23, _pivotz * _t0));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        UnsafeOpsHolder.U.putDouble(dest, sX * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, sX * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, sX * _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(pivotX, 1.0 - sX, sX * _self03));
        UnsafeOpsHolder.U.putDouble(dest + 32L, sY * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, sY * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, sY * _self12);
        return preScaleAround_unsafe_s88b4c0dd_1(dest, sY, sZ, pivotY, pivotZ, _self13, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preScaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_s88b4c0dd_1(long dest, double sY, double sZ, double pivotY, double pivotZ, double _self13, double _self20, double _self21, double _self22, double _self23) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(pivotY, 1.0 - sY, sY * _self13));
        UnsafeOpsHolder.U.putDouble(dest + 64L, sZ * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, sZ * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, sZ * _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(pivotZ, 1.0 - sZ, sZ * _self23));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long s, long pivot) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _sx = UnsafeOpsHolder.U.getDouble(s);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _sz = UnsafeOpsHolder.U.getDouble(s + 16L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(pivot + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _sx * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sx * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sx * _self02);
        return preScaleAround_unsafe_s9ac39297_1(dest, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _sx, _sy, _sz, _pivotx, _pivoty, _pivotz);
    }

    /** Piece 2 of {@code preScaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_s9ac39297_1(long dest, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _sx, double _sy, double _sz, double _pivotx, double _pivoty, double _pivotz) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_pivotx, 1.0 - _sx, _sx * _self03));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _sy * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _sy * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _sy * _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_pivoty, 1.0 - _sy, _sy * _self13));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _sz * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _sz * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _sz * _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_pivotz, 1.0 - _sz, _sz * _self23));
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, double vX, double vY, double vZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03 + vX);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13 + vY);
        return preTranslate_unsafe_sfc132cd7_1(dest, vZ, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preTranslate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preTranslate_unsafe_sfc132cd7_1(long dest, double vZ, double _self20, double _self21, double _self22, double _self23) {
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23 + vZ);
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03 + _vx);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self12);
        return preTranslate_unsafe_s2e31ddf5_1(dest, _self13, _self20, _self21, _self22, _self23, _vy, _vz);
    }

    /** Piece 2 of {@code preTranslate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preTranslate_unsafe_s2e31ddf5_1(long dest, double _self13, double _self20, double _self21, double _self22, double _self23, double _vy, double _vz) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13 + _vy);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23 + _vz);
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, double normalX, double normalY, double normalZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _sp0 = normalX + normalX;
        double _t0 = -_self02;
        double _t9 = _sp0 * normalZ;
        double _t10 = _sp0 * normalY;
        double _t12 = Math.fma(-2.0, normalX * normalX, 1.0);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        return reflect_unsafe_sbbf52fda_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, -_self12, -_self22, _t9, _t10, (normalY + normalY) * normalZ, _t12, Math.fma(-2.0, normalY * normalY, 1.0), Math.fma(-2.0, normalZ * normalZ, 1.0));
    }

    /** Piece 2 of {@code reflect_unsafe}, split to fit the inline budget; reached only through it. */
    private static long reflect_unsafe_sbbf52fda_1(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9))));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, long normal) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _sp0 = _normalx + _normalx;
        return reflect_unsafe_s9cc5500c_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self02, -_self12, -_self22, _sp0 * _normalz, _sp0 * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0, _normalx * _normalx, 1.0), Math.fma(-2.0, _normaly * _normaly, 1.0), Math.fma(-2.0, _normalz * _normalz, 1.0));
    }

    /** Piece 2 of {@code reflect_unsafe}, split to fit the inline budget; reached only through it. */
    private static long reflect_unsafe_s9cc5500c_1(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10))));
        return reflect_unsafe_s9cc5500c_2(dest, _self20, _self21, _self22, _self23, _t9, _t11, _t14);
    }

    /** Piece 3 of {@code reflect_unsafe}, split to fit the inline budget; reached only through it. */
    private static long reflect_unsafe_s9cc5500c_2(long dest, double _self20, double _self21, double _self22, double _self23, double _t9, double _t11, double _t14) {
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9))));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        return rotateAround_unsafe_s967f81ae_1(dest, rotX, rotY, rotZ, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -rotY, -rotX, -pivotZ, _t5, _t6, _t9, _t10, rotZ * _t7, Math.fma(-rotZ, _t7, 1.0), Math.fma(rotY, _t5, _t9), Math.fma(rotZ, _t6, _t10), Math.fma(rotZ, _t5, _t8), Math.fma(rotZ, _t5, -_t8));
    }

    /** Piece 2 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_s967f81ae_1(long dest, double rotX, double rotY, double rotZ, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t2, double _t3, double _t5, double _t6, double _t9, double _t10, double _t11, double _t16, double _t18, double _t19, double _t20, double _t24) {
        double _t25 = Math.fma(rotY, _t5, -_t9);
        double _t26 = Math.fma(rotZ, _t6, -_t10);
        double _t27 = Math.fma(_t0, _t6, _t16);
        double _t28 = Math.fma(_t2, _t5, _t16);
        double _t29 = Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        return rotateAround_unsafe_s967f81ae_2(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))), Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
    }

    /** Piece 3 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_s967f81ae_2(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39, double _t40, double _t41) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23))));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long rot, long pivot) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _rotx = UnsafeOpsHolder.U.getDouble(rot);
        double _roty = UnsafeOpsHolder.U.getDouble(rot + 8L);
        double _rotz = UnsafeOpsHolder.U.getDouble(rot + 16L);
        double _rotw = UnsafeOpsHolder.U.getDouble(rot + 24L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(pivot + 16L);
        return rotateAround_unsafe_sbbb9d1c1_3(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _rotz, _rotw, _pivotx, _pivoty, _pivotz, -_roty, -_rotx, -_pivotz, _rotx + _rotx, _roty + _roty);
    }

    /** Part 1 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static double rotateAround_unsafe_sbbb9d1c1_1(long dest, double _self00, double _self01, double _self02, double _self03, double _rotx, double _roty, double _pivotx, double _pivoty, double _pivotz, double _t5, double _t6, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39, double _t40) {
        double _t41 = Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03))));
        return _t41;
    }

    /** Part 2 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_sbbb9d1c1_2(long dest, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39, double _t40, double _t41) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23))));
        return dest;
    }

    /** Piece 2 of {@code rotateAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_sbbb9d1c1_3(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _rotz, double _rotw, double _pivotx, double _pivoty, double _pivotz, double _t0, double _t2, double _t3, double _t5, double _t6) {
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t11 = _rotz * _t7;
        double _t16 = Math.fma(-_rotz, _t7, 1.0);
        double _t18 = Math.fma(_roty, _t5, _t9);
        double _t19 = Math.fma(_rotz, _t6, _t10);
        double _t20 = Math.fma(_rotz, _t5, _t8);
        double _t24 = Math.fma(_rotz, _t5, -_t8);
        double _t25 = Math.fma(_roty, _t5, -_t9);
        double _t26 = Math.fma(_rotz, _t6, -_t10);
        double _t27 = Math.fma(_t0, _t6, _t16);
        double _t28 = Math.fma(_t2, _t5, _t16);
        double _t29 = Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0));
        double _t39 = Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25)));
        double _t40 = Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)));
        return rotateAround_unsafe_sbbb9d1c1_2(dest, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, _t39, _t40, rotateAround_unsafe_sbbb9d1c1_1(dest, _self00, _self01, _self02, _self03, _rotx, _roty, _pivotx, _pivoty, _pivotz, _t5, _t6, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, _t39, _t40));
    }

    public static long rotateAxis_unsafe(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        return rotateAxis_unsafe_sb5952c71_1(dest, axisX, axisY, axisZ, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, axisX * axisZ, _t5, _t6, _t11, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6));
    }

    /** Piece 2 of {@code rotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_sb5952c71_1(long dest, double axisX, double axisY, double axisZ, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t2, double _t5, double _t6, double _t11, double _t18, double _t19, double _t20, double _t21, double _t22) {
        double _t23 = Math.fma(axisY, _t0, _t11 * _t2);
        double _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        double _t25 = Math.fma(_t11, _t5, -(axisZ * _t0));
        double _t26 = Math.fma(_t11, _t6, -(axisX * _t0));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        return rotateAxis_unsafe_sb5952c71_2(dest, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26);
    }

    /** Piece 3 of {@code rotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_sb5952c71_2(long dest, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t11 = 1.0 - _t1;
        return rotateAxis_unsafe_s9e8f6b59_1(dest, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _axisx, _axisy, _axisz, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1));
    }

    /** Piece 2 of {@code rotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_s9e8f6b59_1(long dest, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _axisx, double _axisy, double _axisz, double _t2, double _t5, double _t6, double _t11, double _t18, double _t19, double _t20) {
        double _t21 = Math.fma(_axisz, _t0, _t11 * _t5);
        double _t22 = Math.fma(_axisx, _t0, _t11 * _t6);
        double _t23 = Math.fma(_axisy, _t0, _t11 * _t2);
        double _t24 = Math.fma(_t11, _t2, -(_axisy * _t0));
        double _t25 = Math.fma(_t11, _t5, -(_axisz * _t0));
        double _t26 = Math.fma(_t11, _t6, -(_axisx * _t0));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        return rotateAxis_unsafe_s9e8f6b59_2(dest, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26);
    }

    /** Piece 3 of {@code rotateAxis_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_s9e8f6b59_2(long dest, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateQuat_unsafe(long dest, long src, double qX, double qY, double qZ, double qW) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        return rotateQuat_unsafe_s365b1802_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -qY, -qX, _t3, _t4, Math.fma(-qZ, _t5, 1.0), Math.fma(qY, _t3, _t7), Math.fma(qZ, _t4, _t8), Math.fma(qZ, _t3, _t6), Math.fma(qZ, _t3, -_t6), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8));
    }

    /** Piece 2 of {@code rotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateQuat_unsafe_s365b1802_1(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t2, double _t3, double _t4, double _t12, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19) {
        double _t20 = Math.fma(_t0, _t4, _t12);
        double _t21 = Math.fma(_t2, _t3, _t12);
        double _t22 = Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14)));
        return rotateQuat_unsafe_s365b1802_2(dest, _self20, _self21, _self22, _self23, _t15, _t16, _t18, _t19, _t21, _t22);
    }

    /** Piece 3 of {@code rotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateQuat_unsafe_s365b1802_2(long dest, double _self20, double _self21, double _self22, double _self23, double _t15, double _t16, double _t18, double _t19, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateQuat_unsafe(long dest, long src, long q) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _qx = UnsafeOpsHolder.U.getDouble(q);
        double _qy = UnsafeOpsHolder.U.getDouble(q + 8L);
        double _qz = UnsafeOpsHolder.U.getDouble(q + 16L);
        double _qw = UnsafeOpsHolder.U.getDouble(q + 24L);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        double _t7 = _qw * _t5;
        return rotateQuat_unsafe_sb82c8fdb_1(dest, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _qy, _qz, -_qy, -_qx, _t3, _t4, _qw * _t4, _t7, _qw * _t3, Math.fma(-_qz, _t5, 1.0), Math.fma(_qy, _t3, _t7));
    }

    /** Piece 2 of {@code rotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateQuat_unsafe_sb82c8fdb_1(long dest, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _qy, double _qz, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12, double _t14) {
        double _t15 = Math.fma(_qz, _t4, _t8);
        double _t16 = Math.fma(_qz, _t3, _t6);
        double _t17 = Math.fma(_qz, _t3, -_t6);
        double _t18 = Math.fma(_qy, _t3, -_t7);
        double _t19 = Math.fma(_qz, _t4, -_t8);
        double _t20 = Math.fma(_t0, _t4, _t12);
        double _t21 = Math.fma(_t2, _t3, _t12);
        double _t22 = Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21)));
        return rotateQuat_unsafe_sb82c8fdb_2(dest, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22);
    }

    /** Piece 3 of {@code rotateQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateQuat_unsafe_sb82c8fdb_2(long dest, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self01, _t1, _self02 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t1, -(_self01 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self11, _t1, _self12 * _t0));
        return rotateX_unsafe_sf85b8e11_1(dest, _t0, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateX_unsafe_sf85b8e11_1(long dest, double _t0, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self21, _t1, _self22 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t1, -(_self21 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateXYZ_unsafe(long dest, long src, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        return rotateXYZ_unsafe_s467179ab_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t3, _t4, _t6, _t7, _t1 * _t5, _t0 * _t5, _t5 * _t4, _t3 * _t5, Math.fma(_t6, _t4, _t1 * _t3), Math.fma(_t7, _t1, _t0 * _t4));
    }

    /** Piece 2 of {@code rotateXYZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_s467179ab_1(long dest, double _t0, double _t1, double _t2, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t3, double _t4, double _t6, double _t7, double _t10, double _t11, double _t13, double _t15, double _t18, double _t19) {
        double _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        double _t21 = Math.fma(_t3, _t4, -(_t6 * _t1));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t20, Math.fma(_self00, _t13, _self01 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t19, Math.fma(_self01, _t21, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t15, Math.fma(_self00, _t2, -(_self01 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t20, Math.fma(_self10, _t13, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t19, Math.fma(_self11, _t21, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t15, Math.fma(_self10, _t2, -(_self11 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t20, Math.fma(_self20, _t13, _self21 * _t18)));
        return rotateXYZ_unsafe_s467179ab_2(dest, _t2, _self20, _self21, _self22, _self23, _t10, _t11, _t15, _t19, _t21);
    }

    /** Piece 3 of {@code rotateXYZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_s467179ab_2(long dest, double _t2, double _self20, double _self21, double _self22, double _self23, double _t10, double _t11, double _t15, double _t19, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t19, Math.fma(_self21, _t21, -(_self20 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t15, Math.fma(_self20, _t2, -(_self21 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateXZY_unsafe(long dest, long src, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        return rotateXZY_unsafe_s6454b7cf_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t3, _t4, _t6, _t9, _t0 * _t5, _t2 * _t5, _t3 * _t5, _t4 * _t5, Math.fma(_t9, _t3, _t0 * _t2), Math.fma(_t6, _t2, _t4 * _t3));
    }

    /** Piece 2 of {@code rotateXZY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_s6454b7cf_1(long dest, double _t0, double _t1, double _t2, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t3, double _t4, double _t6, double _t9, double _t10, double _t11, double _t15, double _t16, double _t18, double _t19) {
        double _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        double _t21 = Math.fma(_t9, _t2, -(_t0 * _t3));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t20, Math.fma(_self00, _t15, _self01 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t10, Math.fma(_self01, _t16, -(_self00 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t19, Math.fma(_self00, _t11, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t20, Math.fma(_self10, _t15, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t10, Math.fma(_self11, _t16, -(_self10 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t19, Math.fma(_self10, _t11, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t20, Math.fma(_self20, _t15, _self21 * _t18)));
        return rotateXZY_unsafe_s6454b7cf_2(dest, _t1, _self20, _self21, _self22, _self23, _t10, _t11, _t16, _t19, _t21);
    }

    /** Piece 3 of {@code rotateXZY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_s6454b7cf_2(long dest, double _t1, double _self20, double _self21, double _self22, double _self23, double _t10, double _t11, double _t16, double _t19, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t10, Math.fma(_self21, _t16, -(_self20 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t19, Math.fma(_self20, _t11, _self21 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self00, _t1, -(_self02 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self00, _t0, _self02 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self10, _t1, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self11);
        return rotateY_unsafe_sc0b232f4_1(dest, _t0, _self10, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateY_unsafe_sc0b232f4_1(long dest, double _t0, double _self10, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self10, _t0, _self12 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t1, -(_self22 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self20, _t0, _self22 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateYXZ_unsafe(long dest, long src, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        return rotateYXZ_unsafe_s3778630b_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t3, _t4, _t6, _t8, _t2 * _t5, _t1 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t8, _t4, _t1 * _t2));
    }

    /** Piece 2 of {@code rotateYXZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_s3778630b_1(long dest, double _t0, double _t1, double _t2, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t3, double _t4, double _t6, double _t8, double _t10, double _t12, double _t16, double _t17, double _t18, double _t19) {
        double _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        double _t21 = Math.fma(_t6, _t4, -(_t2 * _t3));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10)));
        return rotateYXZ_unsafe_s3778630b_2(dest, _t0, _self20, _self21, _self22, _self23, _t12, _t16, _t17, _t19, _t21);
    }

    /** Piece 3 of {@code rotateYXZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_s3778630b_2(long dest, double _t0, double _self20, double _self21, double _self22, double _self23, double _t12, double _t16, double _t17, double _t19, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateYZX_unsafe(long dest, long src, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t3 = Math.cosFromSin(_t1, angleZ);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        return rotateYZX_unsafe_s65cb9af_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t4, _t5, _t6, _t0 * _t3, _t9, _t2 * _t3, _t4 * _t3, _t5 * _t3, Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t9, _t2, _t0 * _t5));
    }

    /** Piece 2 of {@code rotateYZX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_s65cb9af_1(long dest, double _t0, double _t1, double _t2, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t4, double _t5, double _t6, double _t7, double _t9, double _t11, double _t13, double _t14, double _t18, double _t19) {
        double _t20 = Math.fma(_t2, _t0, -(_t9 * _t5));
        double _t21 = Math.fma(_t5, _t4, -(_t6 * _t2));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-_self02, _t7, Math.fma(_self00, _t13, _self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t18, Math.fma(_self00, _t20, _self01 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t21, Math.fma(_self00, _t19, -(_self01 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(-_self12, _t7, Math.fma(_self10, _t13, _self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t18, Math.fma(_self10, _t20, _self11 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t21, Math.fma(_self10, _t19, -(_self11 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(-_self22, _t7, Math.fma(_self20, _t13, _self21 * _t1)));
        return rotateYZX_unsafe_s65cb9af_2(dest, _self20, _self21, _self22, _self23, _t11, _t14, _t18, _t19, _t20, _t21);
    }

    /** Piece 3 of {@code rotateYZX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_s65cb9af_2(long dest, double _self20, double _self21, double _self22, double _self23, double _t11, double _t14, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t18, Math.fma(_self20, _t20, _self21 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t21, Math.fma(_self20, _t19, -(_self21 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateZ_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self00, _t1, _self01 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self01, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self10, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self11, _t1, -(_self10 * _t0)));
        return rotateZ_unsafe_s7a8709ff_1(dest, _t0, _self12, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZ_unsafe_s7a8709ff_1(long dest, double _t0, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t1, _self21 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self21, _t1, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateZXY_unsafe(long dest, long src, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t8 = _t1 * _t5;
        return rotateZXY_unsafe_sd4a6890b_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t4, _t5, _t6, _t0 * _t3, _t8, _t2 * _t3, _t3 * _t5, _t3 * _t4, Math.fma(_t8, _t0, _t2 * _t4), Math.fma(_t6, _t4, _t0 * _t5));
    }

    /** Piece 2 of {@code rotateZXY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_sd4a6890b_1(long dest, double _t0, double _t1, double _t2, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t4, double _t5, double _t6, double _t7, double _t8, double _t10, double _t14, double _t15, double _t18, double _t19) {
        double _t20 = Math.fma(_t4, _t5, -(_t6 * _t0));
        double _t21 = Math.fma(_t0, _t2, -(_t8 * _t4));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-_self02, _t7, Math.fma(_self00, _t20, _self01 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t1, Math.fma(_self01, _t14, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t15, Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(-_self12, _t7, Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t1, Math.fma(_self11, _t14, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t15, Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(-_self22, _t7, Math.fma(_self20, _t20, _self21 * _t18)));
        return rotateZXY_unsafe_sd4a6890b_2(dest, _t1, _self20, _self21, _self22, _self23, _t10, _t14, _t15, _t19, _t21);
    }

    /** Piece 3 of {@code rotateZXY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_sd4a6890b_2(long dest, double _t1, double _self20, double _self21, double _self22, double _self23, double _t10, double _t14, double _t15, double _t19, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t1, Math.fma(_self21, _t14, -(_self20 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t15, Math.fma(_self20, _t19, _self21 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long rotateZYX_unsafe(long dest, long src, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t10 = _t0 * _t4;
        return rotateZYX_unsafe_sd549c4fb_1(dest, _t0, _t1, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t4, _t5, _t6, _t1 * _t3, _t2 * _t3, _t10, _t3 * _t4, _t5 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t10, _t5, _t2 * _t1));
    }

    /** Piece 2 of {@code rotateZYX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_sd549c4fb_1(long dest, double _t0, double _t1, double _t2, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t4, double _t5, double _t6, double _t8, double _t9, double _t10, double _t15, double _t17, double _t18, double _t19) {
        double _t20 = Math.fma(_t10, _t2, -(_t1 * _t5));
        double _t21 = Math.fma(_t6, _t5, -(_t2 * _t4));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-_self02, _t0, Math.fma(_self00, _t15, _self01 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _t9, Math.fma(_self00, _t20, _self01 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self02, _t17, Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self03);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(-_self12, _t0, Math.fma(_self10, _t15, _self11 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self12, _t9, Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self12, _t17, Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(-_self22, _t0, Math.fma(_self20, _t15, _self21 * _t8)));
        return rotateZYX_unsafe_sd549c4fb_2(dest, _self20, _self21, _self22, _self23, _t9, _t17, _t18, _t19, _t20, _t21);
    }

    /** Piece 3 of {@code rotateZYX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_sd549c4fb_2(long dest, double _self20, double _self21, double _self22, double _self23, double _t9, double _t17, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.fma(_self22, _t9, Math.fma(_self20, _t20, _self21 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, Math.fma(_self22, _t17, Math.fma(_self20, _t19, _self21 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 88L, _self23);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, double vX, double vY, double vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0 * vX);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1 * vY);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2 * vZ);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long scale_unsafe(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0 * _vx);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1 * _vy);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2 * _vz);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long scale_unsafe(long dest, long src, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, s * _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, s * _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, s * _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, _eself3);
        }
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, double s, double pivotX, double pivotY, double pivotZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _t3 = pivotZ * _t0;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, s * _self10);
        return scaleAround_unsafe_sdee5f791_1(dest, s, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1, _t2, _t3);
    }

    /** Piece 2 of {@code scaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_sdee5f791_1(long dest, double s, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t3) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, s * _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, s * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, s * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, s * _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long pivot, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        return scaleAround_unsafe_sb2a0c50b_1(dest, pivot, s, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, 1.0 - s);
    }

    /** Piece 2 of {@code scaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_sb2a0c50b_1(long dest, long pivot, double s, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0) {
        double _t1 = UnsafeOpsHolder.U.getDouble(pivot) * _t0;
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * _t0;
        double _t3 = UnsafeOpsHolder.U.getDouble(pivot + 16L) * _t0;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, s * _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, s * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, s * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, s * _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        UnsafeOpsHolder.U.putDouble(dest, sX * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, sY * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, sZ * _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, sX * _self10);
        return scaleAround_unsafe_s7c8d755e_1(dest, sX, sY, sZ, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t3, _t4, _t5);
    }

    /** Piece 2 of {@code scaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_s7c8d755e_1(long dest, double sX, double sY, double sZ, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t3, double _t4, double _t5) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, sY * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, sZ * _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, sX * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, sY * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, sZ * _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long s, long pivot) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _sx = UnsafeOpsHolder.U.getDouble(s);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _sz = UnsafeOpsHolder.U.getDouble(s + 16L);
        return scaleAround_unsafe_s12fd168c_1(dest, pivot, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _sx, _sy, _sz);
    }

    /** Piece 2 of {@code scaleAround_unsafe}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_s12fd168c_1(long dest, long pivot, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _sx, double _sy, double _sz) {
        double _t3 = UnsafeOpsHolder.U.getDouble(pivot) * (1.0 - _sx);
        double _t4 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * (1.0 - _sy);
        double _t5 = UnsafeOpsHolder.U.getDouble(pivot + 16L) * (1.0 - _sz);
        UnsafeOpsHolder.U.putDouble(dest, _sx * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sy * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sz * _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _sx * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _sy * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _sz * _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _sx * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _sy * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _sz * _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, double vX, double vY, double vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, Math.fma(_eself0, vX, Math.fma(_eself1, vY, Math.fma(_eself2, vZ, _eself3))));
        }
        return dest;
    }

    public static long translate_unsafe(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            double _eself3 = UnsafeOpsHolder.U.getDouble(src + (_lo + 3) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 3) * 8L, Math.fma(_eself0, _vx, Math.fma(_eself1, _vy, Math.fma(_eself2, _vz, _eself3))));
        }
        return dest;
    }

    public static long mulVec4_unsafe(long dest, long src, double vX, double vY, double vZ, double vW) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self03, vW, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self13, vW, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self23, vW, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY))));
        return dest;
    }

    public static long mulVec4_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _vw = UnsafeOpsHolder.U.getDouble(v + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self03, _vw, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy))));
        return mulVec4_unsafe_sa64475d4_1(dest, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _vx, _vy, _vz, _vw);
    }

    /** Piece 2 of {@code mulVec4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long mulVec4_unsafe_sa64475d4_1(long dest, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _vx, double _vy, double _vz, double _vw) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self13, _vw, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self23, _vw, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy))));
        return dest;
    }

    public static long transformAabb_unsafe(long dest, long src, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _t3 = minX * _self00;
        double _t4 = maxX * _self00;
        double _t5 = minY * _self01;
        double _t6 = maxY * _self01;
        double _t7 = minZ * _self02;
        double _t8 = maxZ * _self02;
        double _t9 = minX * _self10;
        double _t10 = maxX * _self10;
        double _t11 = minY * _self11;
        double _t12 = maxY * _self11;
        double _t13 = minZ * _self12;
        double _t14 = maxZ * _self12;
        double _t15 = minX * _self20;
        double _t16 = maxX * _self20;
        double _t17 = minY * _self21;
        double _t18 = maxY * _self21;
        double _t19 = minZ * _self22;
        double _t20 = maxZ * _self22;
        if (java.lang.Math.min(java.lang.Math.min(maxX - minX, maxY - minY), maxZ - minZ) < 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, Double.POSITIVE_INFINITY);
            UnsafeOpsHolder.U.putDouble(dest + 8L, Double.POSITIVE_INFINITY);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Double.POSITIVE_INFINITY);
            UnsafeOpsHolder.U.putDouble(dest + 24L, Double.NEGATIVE_INFINITY);
            UnsafeOpsHolder.U.putDouble(dest + 32L, Double.NEGATIVE_INFINITY);
            UnsafeOpsHolder.U.putDouble(dest + 40L, Double.NEGATIVE_INFINITY);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, _self03 + java.lang.Math.min(_t3, _t4) + java.lang.Math.min(_t5, _t6) + java.lang.Math.min(_t7, _t8));
            UnsafeOpsHolder.U.putDouble(dest + 8L, _self13 + java.lang.Math.min(_t9, _t10) + java.lang.Math.min(_t11, _t12) + java.lang.Math.min(_t13, _t14));
            UnsafeOpsHolder.U.putDouble(dest + 16L, _self23 + java.lang.Math.min(_t15, _t16) + java.lang.Math.min(_t17, _t18) + java.lang.Math.min(_t19, _t20));
            UnsafeOpsHolder.U.putDouble(dest + 24L, _self03 + java.lang.Math.max(_t3, _t4) + java.lang.Math.max(_t5, _t6) + java.lang.Math.max(_t7, _t8));
            UnsafeOpsHolder.U.putDouble(dest + 32L, _self13 + java.lang.Math.max(_t9, _t10) + java.lang.Math.max(_t11, _t12) + java.lang.Math.max(_t13, _t14));
            UnsafeOpsHolder.U.putDouble(dest + 40L, _self23 + java.lang.Math.max(_t15, _t16) + java.lang.Math.max(_t17, _t18) + java.lang.Math.max(_t19, _t20));
        }
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, double vX, double vY, double vZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, double vX, double vY, double vZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self00, vX, Math.fma(_self01, vY, Math.fma(_self02, vZ, _self03))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, vX, Math.fma(_self11, vY, Math.fma(_self12, vZ, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self20, vX, Math.fma(_self21, vY, Math.fma(_self22, vZ, _self23))));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self03 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self13 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 72L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 80L);
        double _self23 = UnsafeOpsHolder.U.getDouble(src + 88L);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self00, _vx, Math.fma(_self01, _vy, Math.fma(_self02, _vz, _self03))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, Math.fma(_self12, _vz, _self13))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self20, _vx, Math.fma(_self21, _vy, Math.fma(_self22, _vz, _self23))));
        return dest;
    }

    public static void transformPosition_unsafe(long _destBase, long _matrixBase, long _pointsBase, int count) {
        double _m00 = UnsafeOpsHolder.U.getDouble(_matrixBase);
        double _m01 = UnsafeOpsHolder.U.getDouble(_matrixBase + 8L);
        double _m02 = UnsafeOpsHolder.U.getDouble(_matrixBase + 16L);
        double _m03 = UnsafeOpsHolder.U.getDouble(_matrixBase + 24L);
        double _m10 = UnsafeOpsHolder.U.getDouble(_matrixBase + 32L);
        double _m11 = UnsafeOpsHolder.U.getDouble(_matrixBase + 40L);
        double _m12 = UnsafeOpsHolder.U.getDouble(_matrixBase + 48L);
        double _m13 = UnsafeOpsHolder.U.getDouble(_matrixBase + 56L);
        double _m20 = UnsafeOpsHolder.U.getDouble(_matrixBase + 64L);
        double _m21 = UnsafeOpsHolder.U.getDouble(_matrixBase + 72L);
        double _m22 = UnsafeOpsHolder.U.getDouble(_matrixBase + 80L);
        double _m23 = UnsafeOpsHolder.U.getDouble(_matrixBase + 88L);
        transformPosition_unsafe_s4e1e3b59_1(_destBase, _pointsBase, count, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23);
    }

    /** Piece 2 of {@code transformPosition_unsafe}, split to fit the inline budget; reached only through it. */
    private static void transformPosition_unsafe_s4e1e3b59_1(long _destBase, long _pointsBase, int count, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23) {
        for (int _i = 0; _i < count; _i++) {
            long _pb = _pointsBase + _i * 24L;
            long _db = _destBase + _i * 24L;
            double px = UnsafeOpsHolder.U.getDouble(_pb), py = UnsafeOpsHolder.U.getDouble(_pb + 8L), pz = UnsafeOpsHolder.U.getDouble(_pb + 16L);
            UnsafeOpsHolder.U.putDouble(_db, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            UnsafeOpsHolder.U.putDouble(_db + 8L, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            UnsafeOpsHolder.U.putDouble(_db + 16L, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
    }

    public static void transformPosition_unsafe(long _destBase, long _destStride, long _matrixBase, long _pointsBase, long _pointsStride, int count) {
        double _m00 = UnsafeOpsHolder.U.getDouble(_matrixBase);
        double _m01 = UnsafeOpsHolder.U.getDouble(_matrixBase + 8L);
        double _m02 = UnsafeOpsHolder.U.getDouble(_matrixBase + 16L);
        double _m03 = UnsafeOpsHolder.U.getDouble(_matrixBase + 24L);
        double _m10 = UnsafeOpsHolder.U.getDouble(_matrixBase + 32L);
        double _m11 = UnsafeOpsHolder.U.getDouble(_matrixBase + 40L);
        double _m12 = UnsafeOpsHolder.U.getDouble(_matrixBase + 48L);
        double _m13 = UnsafeOpsHolder.U.getDouble(_matrixBase + 56L);
        double _m20 = UnsafeOpsHolder.U.getDouble(_matrixBase + 64L);
        double _m21 = UnsafeOpsHolder.U.getDouble(_matrixBase + 72L);
        double _m22 = UnsafeOpsHolder.U.getDouble(_matrixBase + 80L);
        double _m23 = UnsafeOpsHolder.U.getDouble(_matrixBase + 88L);
        transformPosition_unsafe_s31cdd3b8_1(_destBase, _destStride, _pointsBase, _pointsStride, count, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23);
    }

    /** Piece 2 of {@code transformPosition_unsafe}, split to fit the inline budget; reached only through it. */
    private static void transformPosition_unsafe_s31cdd3b8_1(long _destBase, long _destStride, long _pointsBase, long _pointsStride, int count, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23) {
        for (int _i = 0; _i < count; _i++) {
            long _pb = _pointsBase + _i * _pointsStride;
            long _db = _destBase + _i * _destStride;
            double px = UnsafeOpsHolder.U.getDouble(_pb), py = UnsafeOpsHolder.U.getDouble(_pb + 8L), pz = UnsafeOpsHolder.U.getDouble(_pb + 16L);
            UnsafeOpsHolder.U.putDouble(_db, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            UnsafeOpsHolder.U.putDouble(_db + 8L, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            UnsafeOpsHolder.U.putDouble(_db + 16L, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
    }

    public static void transformDirection_unsafe(long _destBase, long _matrixBase, long _pointsBase, int count) {
        double _m00 = UnsafeOpsHolder.U.getDouble(_matrixBase);
        double _m01 = UnsafeOpsHolder.U.getDouble(_matrixBase + 8L);
        double _m02 = UnsafeOpsHolder.U.getDouble(_matrixBase + 16L);
        double _m10 = UnsafeOpsHolder.U.getDouble(_matrixBase + 32L);
        double _m11 = UnsafeOpsHolder.U.getDouble(_matrixBase + 40L);
        double _m12 = UnsafeOpsHolder.U.getDouble(_matrixBase + 48L);
        double _m20 = UnsafeOpsHolder.U.getDouble(_matrixBase + 64L);
        double _m21 = UnsafeOpsHolder.U.getDouble(_matrixBase + 72L);
        double _m22 = UnsafeOpsHolder.U.getDouble(_matrixBase + 80L);
        for (int _i = 0; _i < count; _i++) {
            long _pb = _pointsBase + _i * 24L;
            long _db = _destBase + _i * 24L;
            double px = UnsafeOpsHolder.U.getDouble(_pb), py = UnsafeOpsHolder.U.getDouble(_pb + 8L), pz = UnsafeOpsHolder.U.getDouble(_pb + 16L);
            UnsafeOpsHolder.U.putDouble(_db, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            UnsafeOpsHolder.U.putDouble(_db + 8L, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            UnsafeOpsHolder.U.putDouble(_db + 16L, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
    }

    public static void transformDirection_unsafe(long _destBase, long _destStride, long _matrixBase, long _pointsBase, long _pointsStride, int count) {
        double _m00 = UnsafeOpsHolder.U.getDouble(_matrixBase);
        double _m01 = UnsafeOpsHolder.U.getDouble(_matrixBase + 8L);
        double _m02 = UnsafeOpsHolder.U.getDouble(_matrixBase + 16L);
        double _m10 = UnsafeOpsHolder.U.getDouble(_matrixBase + 32L);
        double _m11 = UnsafeOpsHolder.U.getDouble(_matrixBase + 40L);
        double _m12 = UnsafeOpsHolder.U.getDouble(_matrixBase + 48L);
        double _m20 = UnsafeOpsHolder.U.getDouble(_matrixBase + 64L);
        double _m21 = UnsafeOpsHolder.U.getDouble(_matrixBase + 72L);
        double _m22 = UnsafeOpsHolder.U.getDouble(_matrixBase + 80L);
        for (int _i = 0; _i < count; _i++) {
            long _pb = _pointsBase + _i * _pointsStride;
            long _db = _destBase + _i * _destStride;
            double px = UnsafeOpsHolder.U.getDouble(_pb), py = UnsafeOpsHolder.U.getDouble(_pb + 8L), pz = UnsafeOpsHolder.U.getDouble(_pb + 16L);
            UnsafeOpsHolder.U.putDouble(_db, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            UnsafeOpsHolder.U.putDouble(_db + 8L, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            UnsafeOpsHolder.U.putDouble(_db + 16L, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
    }

    public static void lerpComposeTRSMul_fmaUnsafe(long _destBase, long _t1Base, long _t2Base, long _q1Base, long _q2Base, long _s1Base, long _s2Base, long _mBase, double alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            long _t1o = _t1Base + _i * 24L;
            long _t2o = _t2Base + _i * 24L;
            long _q1o = _q1Base + _i * 32L;
            long _q2o = _q2Base + _i * 32L;
            long _s1o = _s1Base + _i * 24L;
            long _s2o = _s2Base + _i * 24L;
            long _mo = _mBase + _i * 96L;
            long _do = _destBase + _i * 96L;
            double _ax = UnsafeOpsHolder.U.getDouble(_t1o), _ay = UnsafeOpsHolder.U.getDouble(_t1o + 8L), _az = UnsafeOpsHolder.U.getDouble(_t1o + 16L);
            double _tx = Math.fma(alpha, UnsafeOpsHolder.U.getDouble(_t2o) - _ax, _ax);
            double _ty = Math.fma(alpha, UnsafeOpsHolder.U.getDouble(_t2o + 8L) - _ay, _ay);
            double _tz = Math.fma(alpha, UnsafeOpsHolder.U.getDouble(_t2o + 16L) - _az, _az);
            double _bx = UnsafeOpsHolder.U.getDouble(_s1o), _by = UnsafeOpsHolder.U.getDouble(_s1o + 8L), _bz = UnsafeOpsHolder.U.getDouble(_s1o + 16L);
            double _sx = Math.fma(alpha, UnsafeOpsHolder.U.getDouble(_s2o) - _bx, _bx);
            double _sy = Math.fma(alpha, UnsafeOpsHolder.U.getDouble(_s2o + 8L) - _by, _by);
            double _sz = Math.fma(alpha, UnsafeOpsHolder.U.getDouble(_s2o + 16L) - _bz, _bz);
            double _ux = UnsafeOpsHolder.U.getDouble(_q1o), _uy = UnsafeOpsHolder.U.getDouble(_q1o + 8L), _uz = UnsafeOpsHolder.U.getDouble(_q1o + 16L), _uw = UnsafeOpsHolder.U.getDouble(_q1o + 24L);
            double _vx = UnsafeOpsHolder.U.getDouble(_q2o), _vy = UnsafeOpsHolder.U.getDouble(_q2o + 8L), _vz = UnsafeOpsHolder.U.getDouble(_q2o + 16L), _vw = UnsafeOpsHolder.U.getDouble(_q2o + 24L);
            double _dot = Math.fma(_uw, _vw, Math.fma(_uz, _vz, Math.fma(_ux, _vx, _uy * _vy)));
            double _wx = _dot < 0.0 ? -_vx : _vx, _wy = _dot < 0.0 ? -_vy : _vy, _wz = _dot < 0.0 ? -_vz : _vz, _ww = _dot < 0.0 ? -_vw : _vw;
            double _rx = Math.fma(alpha, _wx - _ux, _ux);
            double _ry = Math.fma(alpha, _wy - _uy, _uy);
            double _rz = Math.fma(alpha, _wz - _uz, _uz);
            double _rw = Math.fma(alpha, _ww - _uw, _uw);
            double _len2 = (_rx * _rx + _ry * _ry) + (_rz * _rz + _rw * _rw);
            double _ninv = _len2 > 0.0 ? 1.0 / java.lang.Math.sqrt(_len2) : 0.0;
            double _qx = _rx * _ninv, _qy = _ry * _ninv, _qz = _rz * _ninv, _qw = _rw * _ninv;
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = UnsafeOpsHolder.U.getDouble(_mo), _m01 = UnsafeOpsHolder.U.getDouble(_mo + 8L), _m02 = UnsafeOpsHolder.U.getDouble(_mo + 16L), _m03 = UnsafeOpsHolder.U.getDouble(_mo + 24L);
            double _m10 = UnsafeOpsHolder.U.getDouble(_mo + 32L), _m11 = UnsafeOpsHolder.U.getDouble(_mo + 40L), _m12 = UnsafeOpsHolder.U.getDouble(_mo + 48L), _m13 = UnsafeOpsHolder.U.getDouble(_mo + 56L);
            double _m20 = UnsafeOpsHolder.U.getDouble(_mo + 64L), _m21 = UnsafeOpsHolder.U.getDouble(_mo + 72L), _m22 = UnsafeOpsHolder.U.getDouble(_mo + 80L), _m23 = UnsafeOpsHolder.U.getDouble(_mo + 88L);
            double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            UnsafeOpsHolder.U.putDouble(_do, _e00);
            UnsafeOpsHolder.U.putDouble(_do + 8L, _e01);
            UnsafeOpsHolder.U.putDouble(_do + 16L, _e02);
            UnsafeOpsHolder.U.putDouble(_do + 24L, _e03);
            UnsafeOpsHolder.U.putDouble(_do + 32L, _e10);
            UnsafeOpsHolder.U.putDouble(_do + 40L, _e11);
            UnsafeOpsHolder.U.putDouble(_do + 48L, _e12);
            UnsafeOpsHolder.U.putDouble(_do + 56L, _e13);
            UnsafeOpsHolder.U.putDouble(_do + 64L, _e20);
            UnsafeOpsHolder.U.putDouble(_do + 72L, _e21);
            UnsafeOpsHolder.U.putDouble(_do + 80L, _e22);
            UnsafeOpsHolder.U.putDouble(_do + 88L, _e23);
        }
    }

    public static void lerpComposeTRSMul_mulAddUnsafe(long _destBase, long _t1Base, long _t2Base, long _q1Base, long _q2Base, long _s1Base, long _s2Base, long _mBase, double alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            long _t1o = _t1Base + _i * 24L;
            long _t2o = _t2Base + _i * 24L;
            long _q1o = _q1Base + _i * 32L;
            long _q2o = _q2Base + _i * 32L;
            long _s1o = _s1Base + _i * 24L;
            long _s2o = _s2Base + _i * 24L;
            long _mo = _mBase + _i * 96L;
            long _do = _destBase + _i * 96L;
            double _ax = UnsafeOpsHolder.U.getDouble(_t1o), _ay = UnsafeOpsHolder.U.getDouble(_t1o + 8L), _az = UnsafeOpsHolder.U.getDouble(_t1o + 16L);
            double _bx = UnsafeOpsHolder.U.getDouble(_s1o), _by = UnsafeOpsHolder.U.getDouble(_s1o + 8L), _bz = UnsafeOpsHolder.U.getDouble(_s1o + 16L);
            double _sx = alpha * (UnsafeOpsHolder.U.getDouble(_s2o) - _bx) + _bx;
            double _sy = alpha * (UnsafeOpsHolder.U.getDouble(_s2o + 8L) - _by) + _by;
            double _sz = alpha * (UnsafeOpsHolder.U.getDouble(_s2o + 16L) - _bz) + _bz;
            double _ux = UnsafeOpsHolder.U.getDouble(_q1o), _uy = UnsafeOpsHolder.U.getDouble(_q1o + 8L), _uz = UnsafeOpsHolder.U.getDouble(_q1o + 16L), _uw = UnsafeOpsHolder.U.getDouble(_q1o + 24L);
            double _vx = UnsafeOpsHolder.U.getDouble(_q2o), _vy = UnsafeOpsHolder.U.getDouble(_q2o + 8L), _vz = UnsafeOpsHolder.U.getDouble(_q2o + 16L), _vw = UnsafeOpsHolder.U.getDouble(_q2o + 24L);
            double _dot = _uw * _vw + (_uz * _vz + (_ux * _vx + (_uy * _vy)));
            double _wx = _dot < 0.0 ? -_vx : _vx, _wy = _dot < 0.0 ? -_vy : _vy, _wz = _dot < 0.0 ? -_vz : _vz, _ww = _dot < 0.0 ? -_vw : _vw;
            double _rx = alpha * (_wx - _ux) + _ux;
            double _ry = alpha * (_wy - _uy) + _uy;
            double _rz = alpha * (_wz - _uz) + _uz;
            double _rw = alpha * (_ww - _uw) + _uw;
            double _len2 = (_rx * _rx + _ry * _ry) + (_rz * _rz + _rw * _rw);
            double _ninv = _len2 > 0.0 ? 1.0 / java.lang.Math.sqrt(_len2) : 0.0;
            double _qx = _rx * _ninv, _qy = _ry * _ninv, _qz = _rz * _ninv, _qw = _rw * _ninv;
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = UnsafeOpsHolder.U.getDouble(_mo), _m01 = UnsafeOpsHolder.U.getDouble(_mo + 8L), _m02 = UnsafeOpsHolder.U.getDouble(_mo + 16L), _m03 = UnsafeOpsHolder.U.getDouble(_mo + 24L);
            double _m10 = UnsafeOpsHolder.U.getDouble(_mo + 32L), _m11 = UnsafeOpsHolder.U.getDouble(_mo + 40L), _m12 = UnsafeOpsHolder.U.getDouble(_mo + 48L), _m13 = UnsafeOpsHolder.U.getDouble(_mo + 56L);
            double _m20 = UnsafeOpsHolder.U.getDouble(_mo + 64L), _m21 = UnsafeOpsHolder.U.getDouble(_mo + 72L), _m22 = UnsafeOpsHolder.U.getDouble(_mo + 80L), _m23 = UnsafeOpsHolder.U.getDouble(_mo + 88L);
            double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + (alpha * (UnsafeOpsHolder.U.getDouble(_t2o) - _ax) + _ax);
            double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + (alpha * (UnsafeOpsHolder.U.getDouble(_t2o + 8L) - _ay) + _ay);
            double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + (alpha * (UnsafeOpsHolder.U.getDouble(_t2o + 16L) - _az) + _az);
            UnsafeOpsHolder.U.putDouble(_do, _e00);
            UnsafeOpsHolder.U.putDouble(_do + 8L, _e01);
            UnsafeOpsHolder.U.putDouble(_do + 16L, _e02);
            UnsafeOpsHolder.U.putDouble(_do + 24L, _e03);
            UnsafeOpsHolder.U.putDouble(_do + 32L, _e10);
            UnsafeOpsHolder.U.putDouble(_do + 40L, _e11);
            UnsafeOpsHolder.U.putDouble(_do + 48L, _e12);
            UnsafeOpsHolder.U.putDouble(_do + 56L, _e13);
            UnsafeOpsHolder.U.putDouble(_do + 64L, _e20);
            UnsafeOpsHolder.U.putDouble(_do + 72L, _e21);
            UnsafeOpsHolder.U.putDouble(_do + 80L, _e22);
            UnsafeOpsHolder.U.putDouble(_do + 88L, _e23);
        }
    }

    public static void composeTRSMul_fmaUnsafe(long _destBase, long _translationBase, long _rotationBase, long _scaleBase, long _mBase, int count) {
        for (int _i = 0; _i < count; _i++) {
            long _translationo = _translationBase + _i * 24L;
            long _rotationo = _rotationBase + _i * 32L;
            long _scaleo = _scaleBase + _i * 24L;
            long _mo = _mBase + _i * 96L;
            long _do = _destBase + _i * 96L;
            double _tx = UnsafeOpsHolder.U.getDouble(_translationo), _ty = UnsafeOpsHolder.U.getDouble(_translationo + 8L), _tz = UnsafeOpsHolder.U.getDouble(_translationo + 16L);
            double _sx = UnsafeOpsHolder.U.getDouble(_scaleo), _sy = UnsafeOpsHolder.U.getDouble(_scaleo + 8L), _sz = UnsafeOpsHolder.U.getDouble(_scaleo + 16L);
            double _qx = UnsafeOpsHolder.U.getDouble(_rotationo), _qy = UnsafeOpsHolder.U.getDouble(_rotationo + 8L), _qz = UnsafeOpsHolder.U.getDouble(_rotationo + 16L), _qw = UnsafeOpsHolder.U.getDouble(_rotationo + 24L);
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = UnsafeOpsHolder.U.getDouble(_mo), _m01 = UnsafeOpsHolder.U.getDouble(_mo + 8L), _m02 = UnsafeOpsHolder.U.getDouble(_mo + 16L), _m03 = UnsafeOpsHolder.U.getDouble(_mo + 24L);
            double _m10 = UnsafeOpsHolder.U.getDouble(_mo + 32L), _m11 = UnsafeOpsHolder.U.getDouble(_mo + 40L), _m12 = UnsafeOpsHolder.U.getDouble(_mo + 48L), _m13 = UnsafeOpsHolder.U.getDouble(_mo + 56L);
            double _m20 = UnsafeOpsHolder.U.getDouble(_mo + 64L), _m21 = UnsafeOpsHolder.U.getDouble(_mo + 72L), _m22 = UnsafeOpsHolder.U.getDouble(_mo + 80L), _m23 = UnsafeOpsHolder.U.getDouble(_mo + 88L);
            double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            UnsafeOpsHolder.U.putDouble(_do, _e00);
            UnsafeOpsHolder.U.putDouble(_do + 8L, _e01);
            UnsafeOpsHolder.U.putDouble(_do + 16L, _e02);
            UnsafeOpsHolder.U.putDouble(_do + 24L, _e03);
            UnsafeOpsHolder.U.putDouble(_do + 32L, _e10);
            UnsafeOpsHolder.U.putDouble(_do + 40L, _e11);
            UnsafeOpsHolder.U.putDouble(_do + 48L, _e12);
            UnsafeOpsHolder.U.putDouble(_do + 56L, _e13);
            UnsafeOpsHolder.U.putDouble(_do + 64L, _e20);
            UnsafeOpsHolder.U.putDouble(_do + 72L, _e21);
            UnsafeOpsHolder.U.putDouble(_do + 80L, _e22);
            UnsafeOpsHolder.U.putDouble(_do + 88L, _e23);
        }
    }

    public static void composeTRSMul_mulAddUnsafe(long _destBase, long _translationBase, long _rotationBase, long _scaleBase, long _mBase, int count) {
        for (int _i = 0; _i < count; _i++) {
            long _translationo = _translationBase + _i * 24L;
            long _rotationo = _rotationBase + _i * 32L;
            long _scaleo = _scaleBase + _i * 24L;
            long _mo = _mBase + _i * 96L;
            long _do = _destBase + _i * 96L;
            double _tx = UnsafeOpsHolder.U.getDouble(_translationo), _ty = UnsafeOpsHolder.U.getDouble(_translationo + 8L), _tz = UnsafeOpsHolder.U.getDouble(_translationo + 16L);
            double _sx = UnsafeOpsHolder.U.getDouble(_scaleo), _sy = UnsafeOpsHolder.U.getDouble(_scaleo + 8L), _sz = UnsafeOpsHolder.U.getDouble(_scaleo + 16L);
            double _qx = UnsafeOpsHolder.U.getDouble(_rotationo), _qy = UnsafeOpsHolder.U.getDouble(_rotationo + 8L), _qz = UnsafeOpsHolder.U.getDouble(_rotationo + 16L), _qw = UnsafeOpsHolder.U.getDouble(_rotationo + 24L);
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = UnsafeOpsHolder.U.getDouble(_mo), _m01 = UnsafeOpsHolder.U.getDouble(_mo + 8L), _m02 = UnsafeOpsHolder.U.getDouble(_mo + 16L), _m03 = UnsafeOpsHolder.U.getDouble(_mo + 24L);
            double _m10 = UnsafeOpsHolder.U.getDouble(_mo + 32L), _m11 = UnsafeOpsHolder.U.getDouble(_mo + 40L), _m12 = UnsafeOpsHolder.U.getDouble(_mo + 48L), _m13 = UnsafeOpsHolder.U.getDouble(_mo + 56L);
            double _m20 = UnsafeOpsHolder.U.getDouble(_mo + 64L), _m21 = UnsafeOpsHolder.U.getDouble(_mo + 72L), _m22 = UnsafeOpsHolder.U.getDouble(_mo + 80L), _m23 = UnsafeOpsHolder.U.getDouble(_mo + 88L);
            double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + _tx;
            double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + _ty;
            double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + _tz;
            UnsafeOpsHolder.U.putDouble(_do, _e00);
            UnsafeOpsHolder.U.putDouble(_do + 8L, _e01);
            UnsafeOpsHolder.U.putDouble(_do + 16L, _e02);
            UnsafeOpsHolder.U.putDouble(_do + 24L, _e03);
            UnsafeOpsHolder.U.putDouble(_do + 32L, _e10);
            UnsafeOpsHolder.U.putDouble(_do + 40L, _e11);
            UnsafeOpsHolder.U.putDouble(_do + 48L, _e12);
            UnsafeOpsHolder.U.putDouble(_do + 56L, _e13);
            UnsafeOpsHolder.U.putDouble(_do + 64L, _e20);
            UnsafeOpsHolder.U.putDouble(_do + 72L, _e21);
            UnsafeOpsHolder.U.putDouble(_do + 80L, _e22);
            UnsafeOpsHolder.U.putDouble(_do + 88L, _e23);
        }
    }

    public static void composeTRSMulPadded_fmaUnsafe(long _destBase, long _translationBase, long _rotationBase, long _scaleBase, long _mBase) {
        double _qx = UnsafeOpsHolder.U.getDouble(_rotationBase), _qy = UnsafeOpsHolder.U.getDouble(_rotationBase + 8L), _qz = UnsafeOpsHolder.U.getDouble(_rotationBase + 16L), _qw = UnsafeOpsHolder.U.getDouble(_rotationBase + 24L);
        double _tx = UnsafeOpsHolder.U.getDouble(_translationBase), _ty = UnsafeOpsHolder.U.getDouble(_translationBase + 8L), _tz = UnsafeOpsHolder.U.getDouble(_translationBase + 16L);
        double _sx = UnsafeOpsHolder.U.getDouble(_scaleBase), _sy = UnsafeOpsHolder.U.getDouble(_scaleBase + 8L), _sz = UnsafeOpsHolder.U.getDouble(_scaleBase + 16L);
        double _m00 = UnsafeOpsHolder.U.getDouble(_mBase), _m01 = UnsafeOpsHolder.U.getDouble(_mBase + 8L), _m02 = UnsafeOpsHolder.U.getDouble(_mBase + 16L), _m03 = UnsafeOpsHolder.U.getDouble(_mBase + 24L);
        double _m10 = UnsafeOpsHolder.U.getDouble(_mBase + 32L), _m11 = UnsafeOpsHolder.U.getDouble(_mBase + 40L), _m12 = UnsafeOpsHolder.U.getDouble(_mBase + 48L), _m13 = UnsafeOpsHolder.U.getDouble(_mBase + 56L);
        composeTRSMulPadded_fmaUnsafe_s2ef8a757_1(_destBase, _mBase, _qx, _qy, _qz, _qw, _tx, _ty, _tz, _sx, _sy, _sz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13);
    }

    /** Piece 2 of {@code composeTRSMulPadded_fmaUnsafe}, split to fit the inline budget; reached only through it. */
    private static void composeTRSMulPadded_fmaUnsafe_s2ef8a757_1(long _destBase, long _mBase, double _qx, double _qy, double _qz, double _qw, double _tx, double _ty, double _tz, double _sx, double _sy, double _sz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13) {
        double _m20 = UnsafeOpsHolder.U.getDouble(_mBase + 64L), _m21 = UnsafeOpsHolder.U.getDouble(_mBase + 72L), _m22 = UnsafeOpsHolder.U.getDouble(_mBase + 80L), _m23 = UnsafeOpsHolder.U.getDouble(_mBase + 88L);
        double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        composeTRSMulPadded_fmaUnsafe_s2ef8a757_2(_destBase, _tx, _ty, _tz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t00, _t01, _t02, _t10, _t11, _t12, _t20, _t21, _t22);
    }

    /** Piece 3 of {@code composeTRSMulPadded_fmaUnsafe}, split to fit the inline budget; reached only through it. */
    private static void composeTRSMulPadded_fmaUnsafe_s2ef8a757_2(long _destBase, double _tx, double _ty, double _tz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t00, double _t01, double _t02, double _t10, double _t11, double _t12, double _t20, double _t21, double _t22) {
        double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
        double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
        double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
        UnsafeOpsHolder.U.putDouble(_destBase, _e00);
        UnsafeOpsHolder.U.putDouble(_destBase + 8L, _e01);
        composeTRSMulPadded_fmaUnsafe_s2ef8a757_3(_destBase, _e02, _e03, _e10, _e11, _e12, _e13, _e20, _e21, _e22, _e23);
    }

    /** Piece 4 of {@code composeTRSMulPadded_fmaUnsafe}, split to fit the inline budget; reached only through it. */
    private static void composeTRSMulPadded_fmaUnsafe_s2ef8a757_3(long _destBase, double _e02, double _e03, double _e10, double _e11, double _e12, double _e13, double _e20, double _e21, double _e22, double _e23) {
        UnsafeOpsHolder.U.putDouble(_destBase + 16L, _e02);
        UnsafeOpsHolder.U.putDouble(_destBase + 24L, _e03);
        UnsafeOpsHolder.U.putDouble(_destBase + 32L, _e10);
        UnsafeOpsHolder.U.putDouble(_destBase + 40L, _e11);
        UnsafeOpsHolder.U.putDouble(_destBase + 48L, _e12);
        UnsafeOpsHolder.U.putDouble(_destBase + 56L, _e13);
        UnsafeOpsHolder.U.putDouble(_destBase + 64L, _e20);
        UnsafeOpsHolder.U.putDouble(_destBase + 72L, _e21);
        UnsafeOpsHolder.U.putDouble(_destBase + 80L, _e22);
        UnsafeOpsHolder.U.putDouble(_destBase + 88L, _e23);
    }

    public static void composeTRSMulPadded_mulAddUnsafe(long _destBase, long _translationBase, long _rotationBase, long _scaleBase, long _mBase) {
        double _qx = UnsafeOpsHolder.U.getDouble(_rotationBase), _qy = UnsafeOpsHolder.U.getDouble(_rotationBase + 8L), _qz = UnsafeOpsHolder.U.getDouble(_rotationBase + 16L), _qw = UnsafeOpsHolder.U.getDouble(_rotationBase + 24L);
        double _tx = UnsafeOpsHolder.U.getDouble(_translationBase), _ty = UnsafeOpsHolder.U.getDouble(_translationBase + 8L), _tz = UnsafeOpsHolder.U.getDouble(_translationBase + 16L);
        double _sx = UnsafeOpsHolder.U.getDouble(_scaleBase), _sy = UnsafeOpsHolder.U.getDouble(_scaleBase + 8L), _sz = UnsafeOpsHolder.U.getDouble(_scaleBase + 16L);
        double _m00 = UnsafeOpsHolder.U.getDouble(_mBase), _m01 = UnsafeOpsHolder.U.getDouble(_mBase + 8L), _m02 = UnsafeOpsHolder.U.getDouble(_mBase + 16L), _m03 = UnsafeOpsHolder.U.getDouble(_mBase + 24L);
        double _m10 = UnsafeOpsHolder.U.getDouble(_mBase + 32L), _m11 = UnsafeOpsHolder.U.getDouble(_mBase + 40L), _m12 = UnsafeOpsHolder.U.getDouble(_mBase + 48L), _m13 = UnsafeOpsHolder.U.getDouble(_mBase + 56L);
        composeTRSMulPadded_mulAddUnsafe_sc9b543b4_1(_destBase, _mBase, _qx, _qy, _qz, _qw, _tx, _ty, _tz, _sx, _sy, _sz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13);
    }

    /** Piece 2 of {@code composeTRSMulPadded_mulAddUnsafe}, split to fit the inline budget; reached only through it. */
    private static void composeTRSMulPadded_mulAddUnsafe_sc9b543b4_1(long _destBase, long _mBase, double _qx, double _qy, double _qz, double _qw, double _tx, double _ty, double _tz, double _sx, double _sy, double _sz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13) {
        double _m20 = UnsafeOpsHolder.U.getDouble(_mBase + 64L), _m21 = UnsafeOpsHolder.U.getDouble(_mBase + 72L), _m22 = UnsafeOpsHolder.U.getDouble(_mBase + 80L), _m23 = UnsafeOpsHolder.U.getDouble(_mBase + 88L);
        double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        composeTRSMulPadded_mulAddUnsafe_sc9b543b4_2(_destBase, _tx, _ty, _tz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t00, _t01, _t02, _t10, _t11, _t12, _t20, _t21, _t22);
    }

    /** Piece 3 of {@code composeTRSMulPadded_mulAddUnsafe}, split to fit the inline budget; reached only through it. */
    private static void composeTRSMulPadded_mulAddUnsafe_sc9b543b4_2(long _destBase, double _tx, double _ty, double _tz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t00, double _t01, double _t02, double _t10, double _t11, double _t12, double _t20, double _t21, double _t22) {
        double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + _tx;
        double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + _ty;
        double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + _tz;
        UnsafeOpsHolder.U.putDouble(_destBase, _e00);
        UnsafeOpsHolder.U.putDouble(_destBase + 8L, _e01);
        UnsafeOpsHolder.U.putDouble(_destBase + 16L, _e02);
        UnsafeOpsHolder.U.putDouble(_destBase + 24L, _e03);
        composeTRSMulPadded_mulAddUnsafe_sc9b543b4_3(_destBase, _e10, _e11, _e12, _e13, _e20, _e21, _e22, _e23);
    }

    /** Piece 4 of {@code composeTRSMulPadded_mulAddUnsafe}, split to fit the inline budget; reached only through it. */
    private static void composeTRSMulPadded_mulAddUnsafe_sc9b543b4_3(long _destBase, double _e10, double _e11, double _e12, double _e13, double _e20, double _e21, double _e22, double _e23) {
        UnsafeOpsHolder.U.putDouble(_destBase + 32L, _e10);
        UnsafeOpsHolder.U.putDouble(_destBase + 40L, _e11);
        UnsafeOpsHolder.U.putDouble(_destBase + 48L, _e12);
        UnsafeOpsHolder.U.putDouble(_destBase + 56L, _e13);
        UnsafeOpsHolder.U.putDouble(_destBase + 64L, _e20);
        UnsafeOpsHolder.U.putDouble(_destBase + 72L, _e21);
        UnsafeOpsHolder.U.putDouble(_destBase + 80L, _e22);
        UnsafeOpsHolder.U.putDouble(_destBase + 88L, _e23);
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
