// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3x3Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double3x3Ops} and its sibling kernel units. Not public API.
 */
public final class Double3x3OpsKernelsAddress {
    private Double3x3OpsKernelsAddress() {}

    public static long getColumn_unsafe(long dest, long src, int col) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; _idxSw2 = _self20; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; _idxSw2 = _self21; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        UnsafeOpsHolder.U.putDouble(dest + 0L, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _idxSw2);
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 < Math.fma(_self02, _self02, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 < Math.fma(_self01, _self01, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 < Math.fma(_self12, _self12, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self11));
        }
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan2(-_self12, Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.fma(_self11, _self11, _self12 * _self12);
        if (_t1 < Math.fma(_self10, _self10, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan2(-_self12, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 < Math.fma(_self21, _self21, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        }
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan2(_self21, Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.fma(_self21, _self21, _self22 * _self22);
        if (_t1 < Math.fma(_self20, _self20, _t1) * 1.0E-15) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan2(_self21, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, Math.sqrt(_t1)));
        return dest;
    }

    public static long getNormalizedRotation_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        double _t7 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        double _t8 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t9 = (1.0 / Math.sqrt(_t6));
        double _t10 = (1.0 / Math.sqrt(_t7));
        double _t11 = (1.0 / Math.sqrt(_t8));
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
        double _t36 = _t27 - _t22;
        double _t39 = _t27 + _t22;
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
        double _t53 = _t50 + _t21;
        double _t55 = _t51 + _t24;
        double _t56 = _t24 - _t51;
        double _t57 = _t50 - _t21;
        double _t58 = _t52 + _t26;
        double _t62 = 1.0 + _t58;
        double _t63 = 1.0 + (_t49 - (_t23 + _t26));
        double _t64 = 1.0 + (_t23 - (_t49 + _t26));
        double _t65 = 1.0 + (_t26 - _t52);
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t62));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t64));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t65));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t63));
        if (_t58 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, _sp0 * _t36);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t56);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t57);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                UnsafeOpsHolder.U.putDouble(dest + 0L, 0.5 * Math.sqrt(_t63));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t53);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t55);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, _sp1 * _t53);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * Math.sqrt(_t64));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t39);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t56);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, _sp2 * _t55);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t39);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * Math.sqrt(_t65));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static long getRow_unsafe(long dest, long src, int row) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            case 2: _idxSw0 = _self20; _idxSw1 = _self21; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        UnsafeOpsHolder.U.putDouble(dest + 0L, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _idxSw2);
        return dest;
    }

    public static long getScale_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    public static long getTranslation_unsafe(long dest, long src) {
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self12);
        return dest;
    }

    public static long getUnnormalizedRotation_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0 = _self00 + _self11;
        double _t1 = _self21 - _self12;
        double _t4 = _self01 + _self10;
        double _t6 = _self02 + _self20;
        double _t7 = _self02 - _self20;
        double _t8 = _self12 + _self21;
        double _t9 = _self10 - _self01;
        double _t10 = _self22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_self00 - (_self11 + _self22));
        double _t16 = 1.0 + (_self11 - (_self00 + _self22));
        double _t17 = 1.0 + (_self22 - _t0);
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t14));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t16));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t17));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t15));
        if (_t10 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, _sp0 * _t1);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t7);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t9);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                UnsafeOpsHolder.U.putDouble(dest + 0L, 0.5 * Math.sqrt(_t15));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t4);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t6);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, _sp1 * _t4);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * Math.sqrt(_t16));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t7);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, _sp2 * _t6);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * Math.sqrt(_t17));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static long cofactor_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self11, _self22, -(_self12 * _self21)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _self21, -(_self01 * _self22)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self01, _self12, -(_self02 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self12, _self20, -(_self10 * _self22)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, _self22, -(_self02 * _self20)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self02, _self10, -(_self00 * _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self10, _self21, -(_self11 * _self20)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self01, _self20, -(_self00 * _self21)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self00, _self11, -(_self01 * _self10)));
        return dest;
    }

    public static double determinant_unsafe(long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        return Math.fma(_self02, Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(_self00, Math.fma(_self11, _self22, -(_self12 * _self21)), -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static double frobeniusNorm_unsafe(long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        return Math.sqrt(Math.fma(_self00, _self00, _self01 * _self01) + Math.fma(_self02, _self02, _self10 * _self10) + (Math.fma(_self11, _self11, _self12 * _self12) + Math.fma(_self20, _self20, Math.fma(_self21, _self21, _self22 * _self22))));
    }

    public static long invert_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(Math.abs(_t13) > 2.2250738585072014E-308 && Math.abs(_t13) < 4.49423283715579E307)) return Double3x3OpsKernelsAddress.invert_degenerate(dest, src);
        double _t13_inv = 1.0 / _t13;
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t6 * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t7 * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv);
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t2 = unitScale(_self00, _self01, _self02);
        double _t12 = _self11 * _t0;
        double _t13 = _self22 * _t1;
        double _t14 = _self12 * _t0;
        double _t15 = _self21 * _t1;
        double _t16 = _self10 * _t0;
        double _t17 = _self20 * _t1;
        double _t18 = _self02 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self01 * _t2;
        double _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        double _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        double _t33_inv = 1.0 / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        double _sp2 = _t1 * _t33_inv;
        double _sp1 = _t0 * _t33_inv;
        double _sp0 = _t2 * _t33_inv;
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t27 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t28 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 56L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 64L);
        double _t18 = Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11));
        double _t19 = Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21));
        double _t20 = Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21));
        double _t21 = Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11));
        double _t22 = Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01));
        double _t23 = Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11));
        double _t24 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        double _t25 = Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01));
        double _t26 = Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01));
        double _t33 = Math.fma(_t18, _t19, -(_t20 * _t21));
        double _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        double _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(Math.abs(_t40) > 2.2250738585072014E-308 && Math.abs(_t40) < 4.49423283715579E307)) return Double3x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        double _t40_inv = 1.0 / _t40;
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t33 * _t40_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t34 * _t40_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv);
        return dest;
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 56L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 64L);
        double _t18 = Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11));
        double _t19 = Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11));
        double _t20 = Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11));
        double _t21 = Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21));
        double _t22 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        double _t23 = Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21));
        double _t24 = Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01));
        double _t25 = Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01));
        double _t26 = Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01));
        double _t27 = unitScale(_t19, _t18, _t20);
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t24, _t25, _t26);
        double _t39 = _t18 * _t27;
        double _t40 = _t21 * _t28;
        double _t41 = _t23 * _t28;
        double _t42 = _t20 * _t27;
        double _t43 = _t19 * _t27;
        double _t44 = _t22 * _t28;
        double _t45 = _t26 * _t29;
        double _t46 = _t24 * _t29;
        double _t47 = _t25 * _t29;
        double _t54 = Math.fma(_t39, _t40, -(_t41 * _t42));
        double _t55 = Math.fma(_t43, _t41, -(_t44 * _t39));
        double _t60_inv = 1.0 / Math.fma(_t55, _t45, Math.fma(_t54, _t46, -(Math.fma(_t43, _t40, -(_t44 * _t42)) * _t47)));
        double _sp2 = _t28 * _t60_inv;
        double _sp1 = _t27 * _t60_inv;
        double _sp0 = _t29 * _t60_inv;
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t54 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t55 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t47, _t42, -(_t39 * _t45)) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_t43, _t45, -(_t46 * _t42)) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t46, _t39, -(_t43 * _t47)) * _sp2);
        return dest;
    }

    public static long normal_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(Math.abs(_t13) > 2.2250738585072014E-308 && Math.abs(_t13) < 4.49423283715579E307)) return Double3x3OpsKernelsAddress.normal_degenerate(dest, src);
        double _t13_inv = 1.0 / _t13;
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t6 * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t7 * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv);
        return dest;
    }

    public static long normal_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.normal_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long normal_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t2 = unitScale(_self00, _self01, _self02);
        double _t12 = _self11 * _t0;
        double _t13 = _self22 * _t1;
        double _t14 = _self12 * _t0;
        double _t15 = _self21 * _t1;
        double _t16 = _self10 * _t0;
        double _t17 = _self20 * _t1;
        double _t18 = _self02 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self01 * _t2;
        double _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        double _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        double _t33_inv = 1.0 / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        double _sp2 = _t1 * _t33_inv;
        double _sp1 = _t0 * _t33_inv;
        double _sp0 = _t2 * _t33_inv;
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t27 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t28 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2);
        return dest;
    }

    public static double trace_unsafe(long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        return _self22 + (_self00 + _self11);
    }

    public static long transpose_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, _eother + _eself);
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double scalar) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, scalar * _eself);
        }
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, -_eself);
        }
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, _eself - _eother);
        }
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        for (int _i = 0; _i < 9; _i++) {
            double _ev = UnsafeOpsHolder.U.getDouble(v + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, _ev);
        }
        return dest;
    }

    public static long setMat2x2_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m + 0L);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 24L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _m00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _m10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _m01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _m11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long setMat2x3_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m + 0L);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 24L);
        double _m02 = UnsafeOpsHolder.U.getDouble(m + 32L);
        double _m12 = UnsafeOpsHolder.U.getDouble(m + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _m00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _m10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _m01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _m11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _m02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _m12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long setMat3x4_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m + 0L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m02 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 32L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 40L);
        double _m12 = UnsafeOpsHolder.U.getDouble(m + 48L);
        double _m20 = UnsafeOpsHolder.U.getDouble(m + 64L);
        double _m21 = UnsafeOpsHolder.U.getDouble(m + 72L);
        double _m22 = UnsafeOpsHolder.U.getDouble(m + 80L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _m00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _m10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _m20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _m01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _m11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _m21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _m02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _m12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _m22);
        return dest;
    }

    public static long setMat4x4_unsafe(long dest, long m) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            int _lom = _l * 4;
            double _em0 = UnsafeOpsHolder.U.getDouble(m + _lom * 8L);
            double _em1 = UnsafeOpsHolder.U.getDouble(m + (_lom + 1) * 8L);
            double _em2 = UnsafeOpsHolder.U.getDouble(m + (_lom + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _em0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _em1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _em2);
        }
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, double tX, double tY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, tX);
        UnsafeOpsHolder.U.putDouble(dest + 56L, tY);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, long t) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _tx = UnsafeOpsHolder.U.getDouble(t + 0L);
        double _ty = UnsafeOpsHolder.U.getDouble(t + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _tx);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _ty);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long makeFromRigid_unsafe(long dest, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-2.0, Math.fma(rRY, rRY, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * Math.fma(rRX, rRY, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * Math.fma(rRX, rRZ, -_t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * Math.fma(rRX, rRY, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(-2.0, Math.fma(rRX, rRX, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * Math.fma(rRX, rRW, rRY * rRZ));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * Math.fma(rRX, rRZ, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * Math.fma(rRY, rRZ, -(rRX * rRW)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(-2.0, Math.fma(rRX, rRX, rRY * rRY), 1.0));
        return dest;
    }

    public static long makeFromTransform_unsafe(long dest, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(tRX, tRY, _t4) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(tRX, tRZ, -_t5) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(tRX, tRY, -_t4) * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(tRX, tRZ, _t5) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        return dest;
    }

    public static long to2x2_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        return dest;
    }

    public static long to2x3_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _loself = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _loself * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_loself + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1);
        }
        return dest;
    }

    public static long to3x4_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _self22);
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long to4x4_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
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
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 96L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 104L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 112L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 120L, 1.0);
        return dest;
    }

    public static long toDualQuat_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = 1.0 - _self00;
        double _t3 = _self21 - _self12;
        double _t5 = _self01 + _self10;
        double _t6 = _self02 + _self20;
        double _t7 = _self02 - _self20;
        double _t8 = _self12 + _self21;
        double _t9 = _self10 - _self01;
        double _t13 = _self22 + (_self00 + _self11);
        double _t14 = 1.0 + _t13;
        double _t15 = _self00 + (1.0 - _self11 - _self22);
        double _t16 = _self11 + (_t1 - _self22);
        double _t17 = _self22 + (_t1 - _self11);
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t14));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t16));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t17));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t15));
        if (_t13 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, _sp0 * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t7);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t9);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                UnsafeOpsHolder.U.putDouble(dest + 0L, 0.5 * Math.sqrt(_t15));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t5);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t6);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t3);
            } else {
                if (_self11 > _self22) {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, _sp1 * _t5);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * Math.sqrt(_t16));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t7);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, _sp2 * _t6);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * Math.sqrt(_t17));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t9);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        return dest;
    }

    public static long toRigid_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0 = -_self11;
        double _t1 = -_self22;
        double _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t15 = (1.0 / Math.sqrt(_ct0));
        double _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t16 = (1.0 / Math.sqrt(_ct1));
        double _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t17 = (1.0 / Math.sqrt(_ct2));
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
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t63));
        double _t65 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t52));
        double _t66 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t52));
        double _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t65));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t66));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t67));
        if (Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t47)) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 24L, _sp0 * _t35);
            UnsafeOpsHolder.U.putDouble(dest + 32L, _sp0 * _t56);
            UnsafeOpsHolder.U.putDouble(dest + 40L, _sp0 * _t57);
            UnsafeOpsHolder.U.putDouble(dest + 48L, 0.5 * Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * Math.sqrt(_t67));
                UnsafeOpsHolder.U.putDouble(dest + 32L, _sp3 * _t54);
                UnsafeOpsHolder.U.putDouble(dest + 40L, _sp3 * _t55);
                UnsafeOpsHolder.U.putDouble(dest + 48L, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t54);
                    UnsafeOpsHolder.U.putDouble(dest + 32L, 0.5 * Math.sqrt(_t65));
                    UnsafeOpsHolder.U.putDouble(dest + 40L, _sp1 * _t31);
                    UnsafeOpsHolder.U.putDouble(dest + 48L, _sp1 * _t56);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t55);
                    UnsafeOpsHolder.U.putDouble(dest + 32L, _sp2 * _t31);
                    UnsafeOpsHolder.U.putDouble(dest + 40L, 0.5 * Math.sqrt(_t66));
                    UnsafeOpsHolder.U.putDouble(dest + 48L, _sp2 * _t57);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest + 0L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        return dest;
    }

    public static long toRigid_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.toRigid_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long toRigid_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
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
        double _t30 = (1.0 / Math.sqrt(_t29));
        double _t31 = (1.0 / Math.sqrt(_t28));
        double _t32 = (1.0 / Math.sqrt(_t27));
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
        if (Math.abs(_t33) < Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0;
            _t87 = -_t34;
        } else {
            _t72 = 0.0;
            _t75 = -_t35;
            _t87 = _t33;
        }
        double _t73, _t76, _t88;
        if (Math.abs(_t37) < Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0;
            _t88 = -_t38;
        } else {
            _t73 = 0.0;
            _t76 = -_t36;
            _t88 = _t37;
        }
        double _t74, _t77, _t89;
        if (Math.abs(_t40) < Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
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
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t208));
        if (_t206 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 24L, _sp0 * _t182);
            UnsafeOpsHolder.U.putDouble(dest + 32L, _sp0 * _t201);
            UnsafeOpsHolder.U.putDouble(dest + 40L, _sp0 * _t202);
            UnsafeOpsHolder.U.putDouble(dest + 48L, 0.5 * Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * Math.sqrt(_t208));
                UnsafeOpsHolder.U.putDouble(dest + 32L, _sp3 * _t199);
                UnsafeOpsHolder.U.putDouble(dest + 40L, _sp3 * _t200);
                UnsafeOpsHolder.U.putDouble(dest + 48L, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t199);
                    UnsafeOpsHolder.U.putDouble(dest + 32L, 0.5 * Math.sqrt(_t209));
                    UnsafeOpsHolder.U.putDouble(dest + 40L, _sp1 * _t184);
                    UnsafeOpsHolder.U.putDouble(dest + 48L, _sp1 * _t201);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t200);
                    UnsafeOpsHolder.U.putDouble(dest + 32L, _sp2 * _t184);
                    UnsafeOpsHolder.U.putDouble(dest + 40L, 0.5 * Math.sqrt(_t210));
                    UnsafeOpsHolder.U.putDouble(dest + 48L, _sp2 * _t202);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest + 0L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        return dest;
    }

    public static long toTransform_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0 = -_self11;
        double _t1 = -_self22;
        double _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        double _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        double _t14 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        double _t15 = (1.0 / Math.sqrt(_t12));
        double _t16 = (1.0 / Math.sqrt(_t13));
        double _t18 = Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _t19 = _self10 * _t17;
        double _t20 = _self22 * _t16;
        double _t21 = _self12 * _t16;
        double _t22 = _self20 * _t17;
        double _t24 = _self21 * _t15;
        double _t25 = _self11 * _t15;
        double _t27 = _self00 * _t17;
        double _t32 = Math.fma(_self12, _t16, _t24);
        double _t36 = Math.fma(_self21, _t15, -_t21);
        double _t37 = Math.max(_t25, _t20);
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
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        double _t55 = Math.fma(_self01, _t15, _t49);
        double _t56 = Math.fma(_self02, _t16, _t50);
        double _t57 = Math.fma(_self02, _t16, -_t50);
        double _t58 = Math.fma(-_self01, _t15, _t49);
        double _t63 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48));
        double _t64 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52));
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t64));
        double _t66 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53));
        double _t67 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53));
        double _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t67));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t68));
        UnsafeOpsHolder.U.putDouble(dest + 0L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * Math.sqrt(_t66) : _sp2 * _t32);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * Math.sqrt(_t67));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t63 > 0.0 ? 0.5 * Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t47 < 0.0 ? -_t18 : _t18);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.sqrt(_t12));
        UnsafeOpsHolder.U.putDouble(dest + 72L, Math.sqrt(_t13));
        return dest;
    }

    public static long toTransform_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.toTransform_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long toTransform_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
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
        double _t30 = (1.0 / Math.sqrt(_t29));
        double _t31 = (1.0 / Math.sqrt(_t28));
        double _t32 = (1.0 / Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (Math.abs(_t35) < Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (Math.abs(_t39) < Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (Math.abs(_t42) < Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
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
        double _t186 = Math.max(_t170, _t174);
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
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t211));
        UnsafeOpsHolder.U.putDouble(dest + 0L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * Math.sqrt(_t212) : _sp2 * _t187);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * Math.sqrt(_t213));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t209 > 0.0 ? 0.5 * Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t196 < 0.0 ? -_t56 : _t56);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t27 <= 0.0 ? 0.0 : Math.sqrt(_t27) / _t0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t28 <= 0.0 ? 0.0 : Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static long decomposeRotation_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / Math.sqrt(_t2));
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
        double _t20 = -Math.fma(_self22, _t7, Math.fma(_self02, _t8, _self12 * _t9));
        double _t21 = Math.fma(_t19, _t7, _self21);
        double _t22 = Math.fma(_t19, _t8, _self01);
        double _t23 = Math.fma(_t19, _t9, _self11);
        double _t29 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23));
        double _t30 = (1.0 / Math.sqrt(_t29));
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
        double _t50 = (1.0 / Math.sqrt(_t49));
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
        double _t60 = _t35 - _t54;
        double _t63 = _t35 + _t54;
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
        double _t77 = _t74 + _t34;
        double _t78 = _t74 - _t34;
        double _t80 = _t75 + _t55;
        double _t81 = _t55 - _t75;
        double _t82 = _t76 + _t56;
        double _t86 = 1.0 + _t82;
        double _t87 = 1.0 + (_t73 - (_t36 + _t56));
        double _t88 = 1.0 + (_t36 - (_t73 + _t56));
        double _t89 = 1.0 + (_t56 - _t76);
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t86));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t88));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t89));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t87));
        if (_t82 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, _sp0 * _t60);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t81);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t78);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * Math.sqrt(_t86));
        } else {
            if (_t73 > Math.max(_t36, _t56)) {
                UnsafeOpsHolder.U.putDouble(dest + 0L, 0.5 * Math.sqrt(_t87));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t77);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t80);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t60);
            } else {
                if (_t36 > _t56) {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, _sp1 * _t77);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * Math.sqrt(_t88));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t63);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t81);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, _sp2 * _t80);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t63);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * Math.sqrt(_t89));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t78);
                }
            }
        }
        return dest;
    }

    public static long decomposeScale_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t4 = Math.sqrt(_t2);
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
        double _t18 = -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10));
        double _t19 = Math.fma(_t17, _t8, _self21);
        double _t20 = Math.fma(_t17, _t9, _self01);
        double _t21 = Math.fma(_t17, _t10, _self11);
        double _t27 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        double _t28 = (1.0 / Math.sqrt(_t27));
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
        double _t48 = (1.0 / Math.sqrt(_t47));
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
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0 ? -_t4 : _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.sqrt(_t27));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.sqrt(_t47));
        return dest;
    }

    public static long decomposeSkew_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / Math.sqrt(_t2));
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
        double _t16 = -_t14;
        double _t17 = -_t15;
        double _t19 = Math.fma(_t17, _t7, _self21);
        double _t20 = Math.fma(_t17, _t8, _self01);
        double _t21 = Math.fma(_t17, _t9, _self11);
        double _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        double _t27 = (1.0 / Math.sqrt(_t26));
        double _t28 = _t15 * _t27;
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
        double _t48 = (1.0 / Math.sqrt(_t47));
        double _t49 = _t14 * _t48;
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
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, -_t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -_t28);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t28);
        }
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t37 * _t48);
        return dest;
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, double t) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, Math.fma(t, _eother - _eself, _eself));
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = UnsafeOpsHolder.U.getDouble(right + _lo * 8L);
            double _eright1 = UnsafeOpsHolder.U.getDouble(right + (_lo + 1) * 8L);
            double _eright2 = UnsafeOpsHolder.U.getDouble(right + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21)));
        }
        return dest;
    }

    public static long mulMat2x2_unsafe(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right + 0L);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_right00, _self20, _right10 * _self21));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_right01, _self10, _right11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_right01, _self20, _right11 * _self21));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long mulMat2x3_unsafe(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right + 0L);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        double _right02 = UnsafeOpsHolder.U.getDouble(right + 32L);
        double _right12 = UnsafeOpsHolder.U.getDouble(right + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_right00, _self20, _right10 * _self21));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_right01, _self10, _right11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_right01, _self20, _right11 * _self21));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_right02, _self20, Math.fma(_right12, _self21, _self22)));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        double _other00 = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 56L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 64L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, Math.fma(_other22, _eself2, Math.fma(_other20, _eself0, _other21 * _eself1)));
        }
        return dest;
    }

    public static long preMulMat2x2_unsafe(long dest, long src, long other) {
        double _other00 = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_other00, _eself0, _other01 * _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_other10, _eself0, _other11 * _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static long preMulMat2x3_unsafe(long dest, long src, long other) {
        double _other00 = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 40L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long other, double weight) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
            double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _i * 8L, Math.fma(weight, _eother, _eself));
        }
        return dest;
    }

    public static long makeOuterProduct_unsafe(long dest, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, colX * rowX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, colY * rowX);
        UnsafeOpsHolder.U.putDouble(dest + 16L, colZ * rowX);
        UnsafeOpsHolder.U.putDouble(dest + 24L, colX * rowY);
        UnsafeOpsHolder.U.putDouble(dest + 32L, colY * rowY);
        UnsafeOpsHolder.U.putDouble(dest + 40L, colZ * rowY);
        UnsafeOpsHolder.U.putDouble(dest + 48L, colX * rowZ);
        UnsafeOpsHolder.U.putDouble(dest + 56L, colY * rowZ);
        UnsafeOpsHolder.U.putDouble(dest + 64L, colZ * rowZ);
        return dest;
    }

    public static long makeOuterProduct_unsafe(long dest, long col, long row) {
        double _colx = UnsafeOpsHolder.U.getDouble(col + 0L);
        double _coly = UnsafeOpsHolder.U.getDouble(col + 8L);
        double _colz = UnsafeOpsHolder.U.getDouble(col + 16L);
        double _rowx = UnsafeOpsHolder.U.getDouble(row + 0L);
        double _rowy = UnsafeOpsHolder.U.getDouble(row + 8L);
        double _rowz = UnsafeOpsHolder.U.getDouble(row + 16L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _colx * _rowx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _coly * _rowx);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _colz * _rowx);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _colx * _rowy);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _coly * _rowy);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _colz * _rowy);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _colx * _rowz);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _coly * _rowz);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _colz * _rowz);
        return dest;
    }

    public static long lookAlong_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / Math.sqrt(_t4));
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
        double _t33 = (1.0 / Math.sqrt(_t32));
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
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    public static long lookAlong_unsafe(long dest, long src, long dir, long up) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _dirx = UnsafeOpsHolder.U.getDouble(dir + 0L);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up + 0L);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / Math.sqrt(_t4));
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
        double _t33 = (1.0 / Math.sqrt(_t32));
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
        double _t46 = Math.fma(_t39, _t12, -(_t38 * _t13));
        double _t47 = Math.fma(_t37, _t13, -(_t39 * _t11));
        double _t48 = Math.fma(_t38, _t11, -(_t37 * _t12));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
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
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-2.0, _t0, _t6));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * Math.fma(dqRX, dqRY, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(-2.0, _t3, _sp0 * dqRZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-2.0, _t2, _sp0 * dqRY));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(-2.0, _t4, _t6));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * Math.fma(dqRX, dqRW, _t5));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * Math.fma(dqRX, dqRZ, _t3));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(-2.0, dqRX * dqRW, _t5 + _t5));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0)));
        return dest;
    }

    public static long makeRotation_unsafe(long dest, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t5, axisX * axisX, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(axisZ, _t0, _t5 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t5, _t3, -(axisY * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t5, _t2, -(axisZ * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t5, axisY * axisY, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(axisX, _t0, _t5 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(axisY, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_t5, _t4, -(axisX * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t5, axisZ * axisZ, _t1));
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, long axis, double angle) {
        double _axisx = UnsafeOpsHolder.U.getDouble(axis + 0L);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisy;
        double _t3 = _axisx * _axisz;
        double _t4 = _axisy * _axisz;
        double _t5 = 1.0 - _t1;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t5, _axisx * _axisx, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_axisz, _t0, _t5 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t5, _t3, -(_axisy * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t5, _t2, -(_axisz * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t5, _axisy * _axisy, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_axisx, _t0, _t5 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_axisy, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_t5, _t4, -(_axisx * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t5, _axisz * _axisz, _t1));
        return dest;
    }

    public static long makeRotationLookAlong_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / Math.sqrt(_t4));
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
        double _t33 = (1.0 / Math.sqrt(_t32));
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
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    public static long makeRotationLookAlong_unsafe(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir + 0L);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up + 0L);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / Math.sqrt(_t4));
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
        double _t33 = (1.0 / Math.sqrt(_t32));
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
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    public static long makeRotationQuat_unsafe(long dest, double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * Math.fma(qX, qY, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * Math.fma(qX, qZ, -_t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * Math.fma(qX, qY, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * Math.fma(qX, qW, qY * qZ));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * Math.fma(qX, qZ, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * Math.fma(qY, qZ, -(qX * qW)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0));
        return dest;
    }

    public static long makeRotationQuat_unsafe(long dest, long q) {
        double _qx = UnsafeOpsHolder.U.getDouble(q + 0L);
        double _qy = UnsafeOpsHolder.U.getDouble(q + 8L);
        double _qz = UnsafeOpsHolder.U.getDouble(q + 16L);
        double _qw = UnsafeOpsHolder.U.getDouble(q + 24L);
        double _t0 = _qz * _qz;
        double _t1 = _qz * _qw;
        double _t2 = _qy * _qw;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-2.0, Math.fma(_qy, _qy, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * Math.fma(_qx, _qy, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * Math.fma(_qx, _qz, -_t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * Math.fma(_qx, _qy, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(-2.0, Math.fma(_qx, _qx, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * Math.fma(_qx, _qw, _qy * _qz));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * Math.fma(_qx, _qz, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * Math.fma(_qy, _qz, -(_qx * _qw)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(-2.0, Math.fma(_qx, _qx, _qy * _qy), 1.0));
        return dest;
    }

    public static long makeRotationX_unsafe(long dest, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t1);
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
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t3 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t6, _t4, _t1 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t2, _t1, -(_t7 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, -(_t1 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t5, _t4, -(_t6 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t7, _t1, _t2 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -(_t2 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t3);
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
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t3 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t7, _t3, _t2 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t6, _t3, -(_t0 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_t1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t5 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t2 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t0 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_t7, _t0, -(_t2 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t6, _t0, _t5 * _t3));
        return dest;
    }

    public static long makeRotationY_unsafe(long dest, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t1);
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
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t6, _t2, _t3 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t2 * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t7, _t2, -(_t1 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t6, _t4, -(_t2 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t5 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t7, _t4, _t1 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t1 * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t3);
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
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t3 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t0 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t2, _t0, -(_t7 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t5 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_t6, _t5, _t2 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t7, _t2, _t0 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 56L, -(_t2 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_t5, _t3, -(_t6 * _t2)));
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
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t3, _t4, -(_t6 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t7, _t0, _t1 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t0 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 24L, -(_t1 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t5 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t2);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t6, _t3, _t0 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_t0, _t1, -(_t7 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t3);
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
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t3 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t1 * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t7, _t2, -(_t1 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_t6, _t2, _t5 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t2 * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_t7, _t5, _t2 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_t6, _t5, -(_t2 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t3);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, double vX, double vY) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, vY);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _vy);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, double s) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, s);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, s);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, double vX, double vY) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, vX);
        UnsafeOpsHolder.U.putDouble(dest + 56L, vY);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _vy);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long makeView_unsafe(long dest, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t0_inv + _t0_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t1_inv + _t1_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -((left + right) * _t0_inv));
        UnsafeOpsHolder.U.putDouble(dest + 56L, -((bottom + top) * _t1_inv));
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long preRotate_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self00, _t0, _self10 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self01, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self01, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t1, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self02, _t0, _self12 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.sin(0.5 * angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t3 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t2 + _t2) * _t2;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self20, _t9, Math.fma(_self00, _t3, -(_self10 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self20, _t10, Math.fma(_self00, _t0, _self10 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self21, _t9, Math.fma(_self01, _t3, -(_self11 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self21, _t10, Math.fma(_self01, _t0, _self11 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self22, _t9, Math.fma(_self02, _t3, -(_self12 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self22, _t10, Math.fma(_self02, _t0, _self12 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, long pivot, double angle) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.sin(0.5 * angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t3 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t2 + _t2) * _t2;
        double _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        double _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self20, _t9, Math.fma(_self00, _t3, -(_self10 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self20, _t10, Math.fma(_self00, _t0, _self10 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self21, _t9, Math.fma(_self01, _t3, -(_self11 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self21, _t10, Math.fma(_self01, _t0, _self11 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self22, _t9, Math.fma(_self02, _t3, -(_self12 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self22, _t10, Math.fma(_self02, _t0, _self12 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preRotateAxis_unsafe(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, axisX * axisX, _t1);
        double _t19 = Math.fma(_t11, axisY * axisY, _t1);
        double _t20 = Math.fma(_t11, axisZ * axisZ, _t1);
        double _t21 = Math.fma(axisY, _t0, _t11 * _t2);
        double _t22 = Math.fma(axisZ, _t0, _t11 * _t4);
        double _t23 = Math.fma(axisX, _t0, _t11 * _t6);
        double _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        double _t25 = Math.fma(_t11, _t6, -(axisX * _t0));
        double _t26 = Math.fma(_t11, _t2, -(axisY * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    public static long preRotateAxis_unsafe(long dest, long src, long axis, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis + 0L);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t4 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, _axisx * _axisx, _t1);
        double _t19 = Math.fma(_t11, _axisy * _axisy, _t1);
        double _t20 = Math.fma(_t11, _axisz * _axisz, _t1);
        double _t21 = Math.fma(_axisy, _t0, _t11 * _t2);
        double _t22 = Math.fma(_axisz, _t0, _t11 * _t4);
        double _t23 = Math.fma(_axisx, _t0, _t11 * _t6);
        double _t24 = Math.fma(_t11, _t4, -(_axisz * _t0));
        double _t25 = Math.fma(_t11, _t6, -(_axisx * _t0));
        double _t26 = Math.fma(_t11, _t2, -(_axisy * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    public static long preRotateX_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _t1, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self10, _t0, _self20 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self11, _t1, -(_self21 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self11, _t0, _self21 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t1, -(_self22 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self12, _t0, _self22 * _t1));
        return dest;
    }

    public static long preRotateY_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t1, _self20 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self20, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self01, _t1, _self21 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t1, -(_self01 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t1, _self22 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t1, -(_self02 * _t0)));
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0 * vX);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1 * vY);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0 * _vx);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1 * _vy);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, s * _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, s * _eself1);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, double s, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(s, _self00, _self20 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(s, _self10, _self20 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(s, _self01, _self21 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(s, _self11, _self21 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(s, _self02, _self22 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(s, _self12, _self22 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long pivot, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t0 = 1.0 - s;
        double _t1 = _pivotx * _t0;
        double _t2 = _pivoty * _t0;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(s, _self00, _self20 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(s, _self10, _self20 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(s, _self01, _self21 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(s, _self11, _self21 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(s, _self02, _self22 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(s, _self12, _self22 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(sX, _self00, _self20 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(sY, _self10, _self20 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(sX, _self01, _self21 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(sY, _self11, _self21 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(sX, _self02, _self22 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(sY, _self12, _self22 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long s, long pivot) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _sx = UnsafeOpsHolder.U.getDouble(s + 0L);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t2 = _pivotx * (1.0 - _sx);
        double _t3 = _pivoty * (1.0 - _sy);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_sx, _self00, _self20 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_sy, _self10, _self20 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_sx, _self01, _self21 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_sy, _self11, _self21 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_sx, _self02, _self22 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_sy, _self12, _self22 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_eself2, vX, _eself0));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_eself2, vY, _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_eself2, _vx, _eself0));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_eself2, _vy, _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t1, _self01 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self20, _t1, _self21 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self01, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self11, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t1, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t3 = Math.sin(0.5 * angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t2, _self01 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _t2, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self20, _t2, _self21 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self01, _t2, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self11, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t2, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t9, Math.fma(_self21, _t10, _self22)));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long pivot, double angle) {
        double _t0 = Math.sin(angle);
        double _t3 = Math.sin(0.5 * angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        double _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t2, _self01 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _t2, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self20, _t2, _self21 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self01, _t2, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self11, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t2, -(_self20 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t9, Math.fma(_self21, _t10, _self22)));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, axisX * axisX, _t1);
        double _t19 = Math.fma(_t11, axisY * axisY, _t1);
        double _t20 = Math.fma(_t11, axisZ * axisZ, _t1);
        double _t21 = Math.fma(axisZ, _t0, _t11 * _t5);
        double _t22 = Math.fma(axisX, _t0, _t11 * _t6);
        double _t23 = Math.fma(axisY, _t0, _t11 * _t2);
        double _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        double _t25 = Math.fma(_t11, _t5, -(axisZ * _t0));
        double _t26 = Math.fma(_t11, _t6, -(axisX * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis + 0L);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, _axisx * _axisx, _t1);
        double _t19 = Math.fma(_t11, _axisy * _axisy, _t1);
        double _t20 = Math.fma(_t11, _axisz * _axisz, _t1);
        double _t21 = Math.fma(_axisz, _t0, _t11 * _t5);
        double _t22 = Math.fma(_axisx, _t0, _t11 * _t6);
        double _t23 = Math.fma(_axisy, _t0, _t11 * _t2);
        double _t24 = Math.fma(_t11, _t2, -(_axisy * _t0));
        double _t25 = Math.fma(_t11, _t5, -(_axisz * _t0));
        double _t26 = Math.fma(_t11, _t6, -(_axisx * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self01, _t1, _self02 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self11, _t1, _self12 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self21, _t1, _self22 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t1, -(_self01 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t1, -(_self21 * _t0)));
        return dest;
    }

    public static long rotateX180_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -_self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -_self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -_self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_self22);
        return dest;
    }

    public static long rotateX270_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_self02);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -_self12);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_self22);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self21);
        return dest;
    }

    public static long rotateX90_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self22);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -_self01);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -_self11);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_self21);
        return dest;
    }

    public static long rotateXYZ_unsafe(long dest, long src, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        double _t10 = _t1 * _t5;
        double _t11 = _t0 * _t5;
        double _t13 = _t5 * _t4;
        double _t15 = _t3 * _t5;
        double _t18 = Math.fma(_t6, _t4, _t1 * _t3);
        double _t19 = Math.fma(_t7, _t1, _t0 * _t4);
        double _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        double _t21 = Math.fma(_t3, _t4, -(_t6 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self02, _t20, Math.fma(_self00, _t13, _self01 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, _t20, Math.fma(_self10, _t13, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t20, Math.fma(_self20, _t13, _self21 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self02, _t19, Math.fma(_self01, _t21, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t19, Math.fma(_self11, _t21, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self22, _t19, Math.fma(_self21, _t21, -(_self20 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t15, Math.fma(_self00, _t2, -(_self01 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t15, Math.fma(_self10, _t2, -(_self11 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t15, Math.fma(_self20, _t2, -(_self21 * _t11))));
        return dest;
    }

    public static long rotateXZY_unsafe(long dest, long src, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        double _t10 = _t0 * _t5;
        double _t11 = _t2 * _t5;
        double _t15 = _t3 * _t5;
        double _t16 = _t4 * _t5;
        double _t18 = Math.fma(_t9, _t3, _t0 * _t2);
        double _t19 = Math.fma(_t6, _t2, _t4 * _t3);
        double _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        double _t21 = Math.fma(_t9, _t2, -(_t0 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self02, _t20, Math.fma(_self00, _t15, _self01 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, _t20, Math.fma(_self10, _t15, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t20, Math.fma(_self20, _t15, _self21 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self02, _t10, Math.fma(_self01, _t16, -(_self00 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t10, Math.fma(_self11, _t16, -(_self10 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self22, _t10, Math.fma(_self21, _t16, -(_self20 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t19, Math.fma(_self00, _t11, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t19, Math.fma(_self10, _t11, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t19, Math.fma(_self20, _t11, _self21 * _t21)));
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t1, -(_self02 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _t1, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self20, _t1, -(_self22 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self00, _t0, _self02 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t0, _self12 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t0, _self22 * _t1));
        return dest;
    }

    public static long rotateY180_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, -_self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -_self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -_self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_self22);
        return dest;
    }

    public static long rotateY270_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self22);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -_self00);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -_self10);
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_self20);
        return dest;
    }

    public static long rotateY90_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, -_self02);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self12);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self22);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self20);
        return dest;
    }

    public static long rotateYXZ_unsafe(long dest, long src, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t2 * _t5;
        double _t12 = _t1 * _t5;
        double _t16 = _t5 * _t4;
        double _t17 = _t5 * _t3;
        double _t18 = Math.fma(_t6, _t2, _t3 * _t4);
        double _t19 = Math.fma(_t8, _t4, _t1 * _t2);
        double _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        double _t21 = Math.fma(_t6, _t4, -(_t2 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0))));
        return dest;
    }

    public static long rotateYZX_unsafe(long dest, long src, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t3 = Math.cosFromSin(_t1, angleZ);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        double _t9 = _t1 * _t4;
        double _t11 = _t2 * _t3;
        double _t13 = _t4 * _t3;
        double _t14 = _t5 * _t3;
        double _t18 = Math.fma(_t6, _t5, _t2 * _t4);
        double _t19 = Math.fma(_t9, _t2, _t0 * _t5);
        double _t20 = Math.fma(_t2, _t0, -(_t9 * _t5));
        double _t21 = Math.fma(_t5, _t4, -(_t6 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-_self02, _t7, Math.fma(_self00, _t13, _self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-_self12, _t7, Math.fma(_self10, _t13, _self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(-_self22, _t7, Math.fma(_self20, _t13, _self21 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self02, _t18, Math.fma(_self00, _t20, _self01 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t18, Math.fma(_self10, _t20, _self11 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self22, _t18, Math.fma(_self20, _t20, _self21 * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t21, Math.fma(_self00, _t19, -(_self01 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t21, Math.fma(_self10, _t19, -(_self11 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t21, Math.fma(_self20, _t19, -(_self21 * _t11))));
        return dest;
    }

    public static long rotateZ180_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, -_self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -_self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long rotateZ270_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, -_self01);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self11);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self21);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long rotateZ90_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_self00);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -_self10);
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_self20);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long rotateZXY_unsafe(long dest, long src, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t7 = _t0 * _t3;
        double _t8 = _t1 * _t5;
        double _t10 = _t2 * _t3;
        double _t14 = _t3 * _t5;
        double _t15 = _t3 * _t4;
        double _t18 = Math.fma(_t8, _t0, _t2 * _t4);
        double _t19 = Math.fma(_t6, _t4, _t0 * _t5);
        double _t20 = Math.fma(_t4, _t5, -(_t6 * _t0));
        double _t21 = Math.fma(_t0, _t2, -(_t8 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-_self02, _t7, Math.fma(_self00, _t20, _self01 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-_self12, _t7, Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(-_self22, _t7, Math.fma(_self20, _t20, _self21 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self02, _t1, Math.fma(_self01, _t14, -(_self00 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t1, Math.fma(_self11, _t14, -(_self10 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self22, _t1, Math.fma(_self21, _t14, -(_self20 * _t10))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t15, Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t15, Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t15, Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    public static long rotateZYX_unsafe(long dest, long src, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t1 * _t3;
        double _t9 = _t2 * _t3;
        double _t10 = _t0 * _t4;
        double _t15 = _t3 * _t4;
        double _t17 = _t5 * _t3;
        double _t18 = Math.fma(_t6, _t2, _t5 * _t4);
        double _t19 = Math.fma(_t10, _t5, _t2 * _t1);
        double _t20 = Math.fma(_t10, _t2, -(_t1 * _t5));
        double _t21 = Math.fma(_t6, _t5, -(_t2 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-_self02, _t0, Math.fma(_self00, _t15, _self01 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-_self12, _t0, Math.fma(_self10, _t15, _self11 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(-_self22, _t0, Math.fma(_self20, _t15, _self21 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self02, _t9, Math.fma(_self00, _t20, _self01 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self12, _t9, Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self22, _t9, Math.fma(_self20, _t20, _self21 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self02, _t17, Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self12, _t17, Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self22, _t17, Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    public static long scale_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, s * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, double s, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        UnsafeOpsHolder.U.putDouble(dest + 0L, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, s * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long pivot, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t0 = 1.0 - s;
        double _t1 = _pivotx * _t0;
        double _t2 = _pivoty * _t0;
        UnsafeOpsHolder.U.putDouble(dest + 0L, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, s * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        UnsafeOpsHolder.U.putDouble(dest + 0L, sX * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, sX * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, sX * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, sY * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, sY * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, sY * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long s, long pivot) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _sx = UnsafeOpsHolder.U.getDouble(s + 0L);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t2 = _pivotx * (1.0 - _sx);
        double _t3 = _pivoty * (1.0 - _sy);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _sx * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sx * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sx * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sy * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _sy * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _sy * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, vX, Math.fma(_self21, vY, _self22)));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_self20, _vx, Math.fma(_self21, _vy, _self22)));
        return dest;
    }

    public static long view_unsafe(long dest, long src, double left, double right, double bottom, double top) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _sp0 * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _sp1 * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _sp1 * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22 + (-(_self20 * _sp2) - _self21 * _sp3));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, double vX, double vY, double vZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, vX, _self01 * vY));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _vx, _self01 * _vy));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
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

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
