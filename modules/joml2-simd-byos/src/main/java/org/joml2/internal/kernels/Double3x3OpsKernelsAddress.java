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
 * to keep generated sources IDE-sized; public only because {@code Double3x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double3x3OpsKernelsAddress {
    private Double3x3OpsKernelsAddress() {}

    public static long getColumn_unsafe(long dest, long src, int col) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        UnsafeOpsHolder.U.putDouble(dest, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _idxSw2);
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_unsafe_mulAdd(dest, src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = java.lang.Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 <= java.lang.Math.fma(_self02, _self02, _t1) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(java.lang.Math.fma(_self10, _self22, -_self12 * _self20), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_mulAdd(long dest, long src) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = ((_self12) * (_self12) + (_self22 * _self22));
        if (_t1 <= ((_self02) * (_self02) + (_t1)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(((_self10) * (_self22) - (_self12 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_unsafe_mulAdd(dest, src, epsilon);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = java.lang.Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 <= epsilon * java.lang.Math.fma(_self02, _self02, _t1)) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(java.lang.Math.fma(_self10, _self22, -_self12 * _self20), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = ((_self12) * (_self12) + (_self22 * _self22));
        if (_t1 <= epsilon * ((_self02) * (_self02) + (_t1))) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(((_self10) * (_self22) - (_self12 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_unsafe_mulAdd(dest, src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = java.lang.Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 <= java.lang.Math.fma(_self01, _self01, _t1) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(java.lang.Math.fma(_self10, _self21, -(_self11 * _self20)), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_mulAdd(long dest, long src) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = ((_self11) * (_self11) + (_self21 * _self21));
        if (_t1 <= ((_self01) * (_self01) + (_t1)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(((_self10) * (_self21) - (_self11 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_unsafe_mulAdd(dest, src, epsilon);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = java.lang.Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 <= epsilon * java.lang.Math.fma(_self01, _self01, _t1)) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(java.lang.Math.fma(_self10, _self21, -(_self11 * _self20)), java.lang.Math.fma(_self11, _self22, -_self12 * _self21)));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = ((_self11) * (_self11) + (_self21 * _self21));
        if (_t1 <= epsilon * ((_self01) * (_self01) + (_t1))) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, _self11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(((_self10) * (_self21) - (_self11 * _self20)), ((_self11) * (_self22) - (_self12 * _self21))));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_unsafe_mulAdd(dest, src);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = java.lang.Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 <= java.lang.Math.fma(_self12, _self12, _t1) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = ((_self02) * (_self02) + (_self22 * _self22));
        if (_t1 <= ((_self12) * (_self12) + (_t1)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(((_self02) * (_self21) - (_self01 * _self22)), ((_self00) * (_self22) - (_self02 * _self20))));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_unsafe_mulAdd(dest, src, epsilon);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = java.lang.Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 <= epsilon * java.lang.Math.fma(_self12, _self12, _t1)) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = ((_self02) * (_self02) + (_self22 * _self22));
        if (_t1 <= epsilon * ((_self12) * (_self12) + (_t1))) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(((_self02) * (_self21) - (_self01 * _self22)), ((_self00) * (_self22) - (_self02 * _self20))));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_unsafe_mulAdd(dest, src);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = java.lang.Math.fma(_self00, _self00, _self20 * _self20);
        if (_t1 <= java.lang.Math.fma(_self10, _self10, _t1) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(java.lang.Math.fma(_self00, _self21, _self01 * -_self20), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = ((_self00) * (_self00) + (_self20 * _self20));
        if (_t1 <= ((_self10) * (_self10) + (_t1)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(((_self00) * (_self21) - (_self01 * _self20)), ((_self00) * (_self22) - (_self02 * _self20))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_unsafe_mulAdd(dest, src, epsilon);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = java.lang.Math.fma(_self00, _self00, _self20 * _self20);
        if (_t1 <= epsilon * java.lang.Math.fma(_self10, _self10, _t1)) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(java.lang.Math.fma(_self00, _self21, _self01 * -_self20), java.lang.Math.fma(_self00, _self22, _self02 * -_self20)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = ((_self00) * (_self00) + (_self20 * _self20));
        if (_t1 <= epsilon * ((_self10) * (_self10) + (_t1))) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_self02, _self22));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(((_self00) * (_self21) - (_self01 * _self20)), ((_self00) * (_self22) - (_self02 * _self20))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_unsafe_mulAdd(dest, src);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _t1 = java.lang.Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 <= java.lang.Math.fma(_self21, _self21, _t1) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(java.lang.Math.fma(_self02, _self11, -_self01 * _self12), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _t1 = ((_self01) * (_self01) + (_self11 * _self11));
        if (_t1 <= ((_self21) * (_self21) + (_t1)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(((_self02) * (_self11) - (_self01 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_unsafe_mulAdd(dest, src, epsilon);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _t1 = java.lang.Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 <= epsilon * java.lang.Math.fma(_self21, _self21, _t1)) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(java.lang.Math.fma(_self02, _self11, -_self01 * _self12), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _t1 = ((_self01) * (_self01) + (_self11 * _self11));
        if (_t1 <= epsilon * ((_self21) * (_self21) + (_t1))) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(((_self02) * (_self11) - (_self01 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_unsafe_mulAdd(dest, src);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _t1 = java.lang.Math.fma(_self00, _self00, _self10 * _self10);
        if (_t1 <= java.lang.Math.fma(_self20, _self20, _t1) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(java.lang.Math.fma(_self02, _self10, -(_self00 * _self12)), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _t1 = ((_self00) * (_self00) + (_self10 * _self10));
        if (_t1 <= ((_self20) * (_self20) + (_t1)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(((_self02) * (_self10) - (_self00 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_unsafe_mulAdd(dest, src, epsilon);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _t1 = java.lang.Math.fma(_self00, _self00, _self10 * _self10);
        if (_t1 <= epsilon * java.lang.Math.fma(_self20, _self20, _t1)) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(java.lang.Math.fma(_self02, _self10, -(_self00 * _self12)), java.lang.Math.fma(_self00, _self11, -_self01 * _self10)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _t1 = ((_self00) * (_self00) + (_self10 * _self10));
        if (_t1 <= epsilon * ((_self20) * (_self20) + (_t1))) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(-_self01, _self11));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(((_self02) * (_self10) - (_self00 * _self12)), ((_self00) * (_self11) - (_self01 * _self10))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_self10, _self00));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static long getNormalizedRotation_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getNormalizedRotation_unsafe_fma(dest, src);
        return getNormalizedRotation_unsafe_mulAdd(dest, src);
    }

    /** {@code getNormalizedRotation_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getNormalizedRotation_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        double _t7 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        double _t8 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
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
        return getNormalizedRotation_unsafe_fma_s451db3c2_1(dest, _self00, _self10, _self20, _t8, (1.0 / java.lang.Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_fma_s451db3c2_1(long dest, double _self00, double _self10, double _self20, double _t8, double _t11, double _t21, double _t23, double _t27, double _t22, double _t24, double _t26) {
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
        if (java.lang.Math.fma(java.lang.Math.fma(_t21, _t22, -(_t23 * _t24)), _t25, java.lang.Math.fma(java.lang.Math.fma(_t23, _t26, -(_t27 * _t22)), _t28, java.lang.Math.fma(_t27, _t24, -(_t21 * _t26)) * _t29)) < 0.0) {
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
        return getNormalizedRotation_unsafe_fma_s451db3c2_2(dest, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5 * (1.0 / java.lang.Math.sqrt(_t62)), 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_fma_s451db3c2_2(long dest, double _t23, double _t26, double _t36, double _t39, double _t49, double _t53, double _t55, double _t56, double _t57, double _t58, double _t62, double _t63, double _t64, double _t65, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t58 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t36);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t56);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t57);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
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

    /** {@code getNormalizedRotation_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getNormalizedRotation_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        double _t7 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        double _t8 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
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
        return getNormalizedRotation_unsafe_mulAdd_s4ddaf603_1(dest, _self00, _self10, _self20, _t8, (1.0 / java.lang.Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_mulAdd_s4ddaf603_1(long dest, double _self00, double _self10, double _self20, double _t8, double _t11, double _t21, double _t23, double _t27, double _t22, double _t24, double _t26) {
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
        if (((((_t21) * (_t22) - (_t23 * _t24))) * (_t25) + (((((_t23) * (_t26) - (_t27 * _t22))) * (_t28) + (((_t27) * (_t24) - (_t21 * _t26)) * _t29)))) < 0.0) {
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
        return getNormalizedRotation_unsafe_mulAdd_s4ddaf603_2(dest, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5 * (1.0 / java.lang.Math.sqrt(_t62)), 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long getNormalizedRotation_unsafe_mulAdd_s4ddaf603_2(long dest, double _t23, double _t26, double _t36, double _t39, double _t49, double _t53, double _t55, double _t56, double _t57, double _t58, double _t62, double _t63, double _t64, double _t65, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t58 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t36);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t56);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t57);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
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
        UnsafeOpsHolder.U.putDouble(dest, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _idxSw2);
        return dest;
    }

    public static long getScale_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getScale_unsafe_mulAdd(dest, src);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.sqrt(java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.sqrt(java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.sqrt(java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    /** {@code getScale_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getScale_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.sqrt(((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.sqrt(((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.sqrt(((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))))));
        return dest;
    }

    public static long getTranslation_unsafe(long dest, long src) {
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        UnsafeOpsHolder.U.putDouble(dest, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self12);
        return dest;
    }

    public static long getUnnormalizedRotation_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
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
            if (_self00 > Math.max(_self11, _self22)) {
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

    public static long cofactor_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cofactor_unsafe_fma(dest, src);
        return cofactor_unsafe_mulAdd(dest, src);
    }

    /** {@code cofactor_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cofactor_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self11, _self22, -_self12 * _self21));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self02, _self21, -(_self01 * _self22)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self01, _self12, _self02 * -_self11));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self12, _self20, -_self10 * _self22));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self00, _self22, -(_self02 * _self20)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self02, _self10, _self00 * -_self12));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self10, _self21, -_self11 * _self20));
        return cofactor_unsafe_fma_s48a7b02a_1(dest, _self00, _self10, _self20, _self01, _self11, _self21);
    }

    /** Piece 2 of {@code cofactor_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long cofactor_unsafe_fma_s48a7b02a_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self01, _self20, -(_self00 * _self21)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self00, _self11, _self01 * -_self10));
        return dest;
    }

    /** {@code cofactor_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cofactor_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, ((_self11) * (_self22) - (_self12 * _self21)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self02) * (_self21) - (_self01 * _self22)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self01) * (_self12) - (_self02 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self12) * (_self20) - (_self10 * _self22)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self00) * (_self22) - (_self02 * _self20)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self02) * (_self10) - (_self00 * _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self10) * (_self21) - (_self11 * _self20)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self01) * (_self20) - (_self00 * _self21)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self00) * (_self11) - (_self01 * _self10)));
        return dest;
    }

    public static double determinant_unsafe(long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return determinant_unsafe_mulAdd(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t3 = -(_self11 * _self20);
        double _t4 = -_self12 * _self21;
        double _t5 = -_self12 * _self20;
        return java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(src + 48L), java.lang.Math.fma(_self10, _self21, _t3) - java.lang.Math.fma(_self11, _self20, _t3), java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(src), java.lang.Math.fma(_self11, _self22, _t4) - java.lang.Math.fma(_self12, _self21, _t4), UnsafeOpsHolder.U.getDouble(src + 24L) * (java.lang.Math.fma(_self12, _self20, _t5) - java.lang.Math.fma(_self10, _self22, _t5))));
    }

    /** {@code determinant_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double determinant_unsafe_mulAdd(long src) {
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t3 = -(_self11 * _self20);
        double _t4 = -_self12 * _self21;
        double _t5 = -_self12 * _self20;
        return ((UnsafeOpsHolder.U.getDouble(src + 48L)) * (((_self10) * (_self21) + (_t3)) - ((_self11) * (_self20) + (_t3))) + (((UnsafeOpsHolder.U.getDouble(src)) * (((_self11) * (_self22) + (_t4)) - ((_self12) * (_self21) + (_t4))) + (UnsafeOpsHolder.U.getDouble(src + 24L) * (((_self12) * (_self20) + (_t5)) - ((_self10) * (_self22) + (_t5)))))));
    }

    public static double frobeniusNorm_unsafe(long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return frobeniusNorm_unsafe_mulAdd(src);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        return java.lang.Math.sqrt(java.lang.Math.fma(_self00, _self00, _self01 * _self01) + java.lang.Math.fma(_self02, _self02, _self10 * _self10) + (java.lang.Math.fma(_self11, _self11, _self12 * _self12) + java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self21, _self21, _self22 * _self22))));
    }

    /** {@code frobeniusNorm_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double frobeniusNorm_unsafe_mulAdd(long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        return java.lang.Math.sqrt(((_self00) * (_self00) + (_self01 * _self01)) + ((_self02) * (_self02) + (_self10 * _self10)) + (((_self11) * (_self11) + (_self12 * _self12)) + ((_self20) * (_self20) + (((_self21) * (_self21) + (_self22 * _self22))))));
    }

    public static long invert_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_unsafe_fma(dest, src);
        return invert_unsafe_mulAdd(dest, src);
    }

    /** {@code invert_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t10 = -_self12 * _self21;
        double _t11 = -(_self11 * _self20);
        double _t12 = -_self12 * _self20;
        double _t26 = java.lang.Math.fma(_self11, _self22, _t10) - java.lang.Math.fma(_self12, _self21, _t10);
        double _t27 = java.lang.Math.fma(_self10, _self21, _t11) - java.lang.Math.fma(_self11, _self20, _t11);
        return invert_unsafe_fma_s33065879_1(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self01 * _self22, -_self02 * _self11, -(_self10 * _self22), -_self02 * _self20, -_self00 * _self12, -_self00 * _self21, -_self01 * _self10, _t26, _t27, java.lang.Math.fma(_self02, _t27, java.lang.Math.fma(_self00, _t26, _self01 * (java.lang.Math.fma(_self12, _self20, _t12) - java.lang.Math.fma(_self10, _self22, _t12)))));
    }

    /** Piece 2 of {@code invert_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invert_unsafe_fma_s33065879_1(long dest, long src, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t33) {
        if (!(java.lang.Math.abs(_t33) > 2.2250738585072014E-308 && java.lang.Math.abs(_t33) < 4.49423283715579E307)) return Double3x3OpsKernelsAddress.invert_degenerate(dest, src);
        double _t33_inv = 1.0 / _t33;
        UnsafeOpsHolder.U.putDouble(dest, _t26 * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, (java.lang.Math.fma(_self12, _self20, _t15) - java.lang.Math.fma(_self10, _self22, _t15)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t27 * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, (java.lang.Math.fma(_self02, _self21, _t13) - java.lang.Math.fma(_self01, _self22, _t13)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, (java.lang.Math.fma(_self00, _self22, _t16) - java.lang.Math.fma(_self02, _self20, _t16)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (java.lang.Math.fma(_self01, _self20, _t18) - java.lang.Math.fma(_self00, _self21, _t18)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 48L, (java.lang.Math.fma(_self01, _self12, _t14) - java.lang.Math.fma(_self02, _self11, _t14)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 56L, (java.lang.Math.fma(_self02, _self10, _t17) - java.lang.Math.fma(_self00, _self12, _t17)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 64L, (java.lang.Math.fma(_self00, _self11, _t19) - java.lang.Math.fma(_self01, _self10, _t19)) * _t33_inv);
        return dest;
    }

    /** {@code invert_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t10 = -_self12 * _self21;
        double _t11 = -(_self11 * _self20);
        double _t12 = -_self12 * _self20;
        double _t26 = ((_self11) * (_self22) + (_t10)) - ((_self12) * (_self21) + (_t10));
        double _t27 = ((_self10) * (_self21) + (_t11)) - ((_self11) * (_self20) + (_t11));
        return invert_unsafe_mulAdd_s80da8f62_1(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self01 * _self22, -_self02 * _self11, -(_self10 * _self22), -_self02 * _self20, -_self00 * _self12, -_self00 * _self21, -_self01 * _self10, _t26, _t27, ((_self02) * (_t27) + (((_self00) * (_t26) + (_self01 * (((_self12) * (_self20) + (_t12)) - ((_self10) * (_self22) + (_t12))))))));
    }

    /** Piece 2 of {@code invert_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invert_unsafe_mulAdd_s80da8f62_1(long dest, long src, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t33) {
        if (!(java.lang.Math.abs(_t33) > 2.2250738585072014E-308 && java.lang.Math.abs(_t33) < 4.49423283715579E307)) return Double3x3OpsKernelsAddress.invert_degenerate(dest, src);
        double _t33_inv = 1.0 / _t33;
        UnsafeOpsHolder.U.putDouble(dest, _t26 * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, (((_self12) * (_self20) + (_t15)) - ((_self10) * (_self22) + (_t15))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t27 * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, (((_self02) * (_self21) + (_t13)) - ((_self01) * (_self22) + (_t13))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, (((_self00) * (_self22) + (_t16)) - ((_self02) * (_self20) + (_t16))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (((_self01) * (_self20) + (_t18)) - ((_self00) * (_self21) + (_t18))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 48L, (((_self01) * (_self12) + (_t14)) - ((_self02) * (_self11) + (_t14))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 56L, (((_self02) * (_self10) + (_t17)) - ((_self00) * (_self12) + (_t17))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 64L, (((_self00) * (_self11) + (_t19)) - ((_self01) * (_self10) + (_t19))) * _t33_inv);
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        Double3x3OpsKernelsSegment.invert_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_unsafe_fma(dest, src);
        return invert_degenerate_unsafe_mulAdd(dest, src);
    }

    /** {@code invert_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_degenerate_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        return invert_degenerate_unsafe_fma_sb8d74ee8_1(dest, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t20 * _t13, -_t18 * _t12, -(_t16 * _t13), -_t18 * _t17, -_t19 * _t14, -_t19 * _t15, -_t20 * _t16);
    }

    /** Piece 2 of {@code invert_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_fma_sb8d74ee8_1(long dest, double _t0, double _t1, double _t2, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t31, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40) {
        double _t47 = java.lang.Math.fma(_t12, _t13, _t31) - java.lang.Math.fma(_t14, _t15, _t31);
        double _t48 = java.lang.Math.fma(_t16, _t15, _t32) - java.lang.Math.fma(_t12, _t17, _t32);
        double _t53_inv = 1.0 / java.lang.Math.fma(_t48, _t18, java.lang.Math.fma(_t47, _t19, (java.lang.Math.fma(_t14, _t17, _t33) - java.lang.Math.fma(_t16, _t13, _t33)) * _t20));
        double _sp1 = _t0 * _t53_inv;
        double _sp0 = _t2 * _t53_inv;
        UnsafeOpsHolder.U.putDouble(dest, _t47 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, (java.lang.Math.fma(_t14, _t17, _t36) - java.lang.Math.fma(_t16, _t13, _t36)) * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t48 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, (java.lang.Math.fma(_t18, _t15, _t34) - java.lang.Math.fma(_t20, _t13, _t34)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, (java.lang.Math.fma(_t19, _t13, _t37) - java.lang.Math.fma(_t18, _t17, _t37)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (java.lang.Math.fma(_t20, _t17, _t39) - java.lang.Math.fma(_t19, _t15, _t39)) * _sp1);
        return invert_degenerate_unsafe_fma_sb8d74ee8_2(dest, _t12, _t14, _t16, _t18, _t19, _t20, _t35, _t38, _t40, _t1 * _t53_inv);
    }

    /** Piece 3 of {@code invert_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_fma_sb8d74ee8_2(long dest, double _t12, double _t14, double _t16, double _t18, double _t19, double _t20, double _t35, double _t38, double _t40, double _sp2) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, (java.lang.Math.fma(_t20, _t14, _t35) - java.lang.Math.fma(_t18, _t12, _t35)) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 56L, (java.lang.Math.fma(_t18, _t16, _t38) - java.lang.Math.fma(_t19, _t14, _t38)) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 64L, (java.lang.Math.fma(_t19, _t12, _t40) - java.lang.Math.fma(_t20, _t16, _t40)) * _sp2);
        return dest;
    }

    /** {@code invert_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_degenerate_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        return invert_degenerate_unsafe_mulAdd_sff6f5c69_1(dest, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t20 * _t13, -_t18 * _t12, -(_t16 * _t13), -_t18 * _t17, -_t19 * _t14, -_t19 * _t15, -_t20 * _t16);
    }

    /** Piece 2 of {@code invert_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_mulAdd_sff6f5c69_1(long dest, double _t0, double _t1, double _t2, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t31, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40) {
        double _t47 = ((_t12) * (_t13) + (_t31)) - ((_t14) * (_t15) + (_t31));
        double _t48 = ((_t16) * (_t15) + (_t32)) - ((_t12) * (_t17) + (_t32));
        double _t53_inv = 1.0 / ((_t48) * (_t18) + (((_t47) * (_t19) + ((((_t14) * (_t17) + (_t33)) - ((_t16) * (_t13) + (_t33))) * _t20))));
        double _sp2 = _t1 * _t53_inv;
        double _sp1 = _t0 * _t53_inv;
        double _sp0 = _t2 * _t53_inv;
        UnsafeOpsHolder.U.putDouble(dest, _t47 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, (((_t14) * (_t17) + (_t36)) - ((_t16) * (_t13) + (_t36))) * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t48 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, (((_t18) * (_t15) + (_t34)) - ((_t20) * (_t13) + (_t34))) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, (((_t19) * (_t13) + (_t37)) - ((_t18) * (_t17) + (_t37))) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (((_t20) * (_t17) + (_t39)) - ((_t19) * (_t15) + (_t39))) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, (((_t20) * (_t14) + (_t35)) - ((_t18) * (_t12) + (_t35))) * _sp2);
        return invert_degenerate_unsafe_mulAdd_sff6f5c69_2(dest, _t12, _t14, _t16, _t18, _t19, _t20, _t38, _t40, _sp2);
    }

    /** Piece 3 of {@code invert_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invert_degenerate_unsafe_mulAdd_sff6f5c69_2(long dest, double _t12, double _t14, double _t16, double _t18, double _t19, double _t20, double _t38, double _t40, double _sp2) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, (((_t18) * (_t16) + (_t38)) - ((_t19) * (_t14) + (_t38))) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 64L, (((_t19) * (_t12) + (_t40)) - ((_t20) * (_t16) + (_t40))) * _sp2);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_unsafe_fma(dest, src, other);
        return invertProduct_unsafe_mulAdd(dest, src, other);
    }

    /** {@code invertProduct_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_fma(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 56L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 64L);
        double _t21 = -(_other11 * _other20);
        return invertProduct_unsafe_fma_saeb649aa_1(dest, src, other, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, java.lang.Math.fma(_other10, _other21, _t21) - java.lang.Math.fma(_other11, _other20, _t21));
    }

    /** Part of {@code invertProduct_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_unsafe_fma_saeb649aa_526(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t81, double _t82, double _t108, double _t84, double _t85, double _t109) {
        double _t20 = -_other02 * _other11;
        double _t128 = (java.lang.Math.fma(_other01, _other12, _t20) - java.lang.Math.fma(_other02, _other11, _t20)) / _t108;
        double _t27 = -_other01 * _other22;
        double _t130 = (java.lang.Math.fma(_other02, _other21, _t27) - java.lang.Math.fma(_other01, _other22, _t27)) / _t108;
        double _t131 = invertProduct_unsafe_fma_saeb649aa_526_s242548b6_1(dest, _self10, _self20, _self12, _self22, _t82, _t108, _t84, _t85, _t109, _t128, _t130);
        double _t35 = -_other00 * _other12;
        double _t140 = (java.lang.Math.fma(_other02, _other10, _t35) - java.lang.Math.fma(_other00, _other12, _t35)) / _t108;
        double _t36 = -_other02 * _other20;
        double _t141 = (java.lang.Math.fma(_other00, _other22, _t36) - java.lang.Math.fma(_other02, _other20, _t36)) / _t108;
        double _t142 = invertProduct_unsafe_fma_saeb649aa_526_s242548b6_2(dest, _other10, _other20, _other12, _other22, _t108, _t84, _t85, _t109, _t131, _t140, _t141);
        double _t38 = -_other01 * _other10;
        double _t143 = (java.lang.Math.fma(_other00, _other11, _t38) - java.lang.Math.fma(_other01, _other10, _t38)) / _t108;
        invertProduct_unsafe_fma_saeb649aa_526_s242548b6_4(dest, _self00, _self10, _self01, _self11, _self02, _self12, _t81, _t82, _t108, _t109, _t128, _t130, _t140, _t141, _t142, _t143, invertProduct_unsafe_fma_saeb649aa_526_s242548b6_3(dest, _self00, _self20, _self01, _self21, _self02, _self22, _other00, _other20, _other01, _other21, _t81, _t82, _t108, _t84, _t85, _t109, _t128, _t130, _t131, _t140, _t141, _t142, _t143));
    }

    /** Part 1 of {@code invertProduct_unsafe_fma_saeb649aa_526}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_unsafe_fma_saeb649aa_526_s242548b6_1(long dest, double _self10, double _self20, double _self12, double _self22, double _t82, double _t108, double _t84, double _t85, double _t109, double _t128, double _t130) {
        double _t28 = -_self10 * _self22;
        double _t131 = (java.lang.Math.fma(_self12, _self20, _t28) - java.lang.Math.fma(_self10, _self22, _t28)) / _t109;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t128, (_t84 / _t109), java.lang.Math.fma(_t130, _t131, (_t82 / _t108) * (_t85 / _t109))));
        return _t131;
    }

    /** Part 2 of {@code invertProduct_unsafe_fma_saeb649aa_526}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_unsafe_fma_saeb649aa_526_s242548b6_2(long dest, double _other10, double _other20, double _other12, double _other22, double _t108, double _t84, double _t85, double _t109, double _t131, double _t140, double _t141) {
        double _t37 = -(_other10 * _other22);
        double _t142 = (java.lang.Math.fma(_other12, _other20, _t37) - java.lang.Math.fma(_other10, _other22, _t37)) / _t108;
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t140, (_t84 / _t109), java.lang.Math.fma(_t141, _t131, _t142 * (_t85 / _t109))));
        return _t142;
    }

    /** Part 3 of {@code invertProduct_unsafe_fma_saeb649aa_526}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_unsafe_fma_saeb649aa_526_s242548b6_3(long dest, double _self00, double _self20, double _self01, double _self21, double _self02, double _self22, double _other00, double _other20, double _other01, double _other21, double _t81, double _t82, double _t108, double _t84, double _t85, double _t109, double _t128, double _t130, double _t131, double _t140, double _t141, double _t142, double _t143) {
        double _t39 = -_other00 * _other21;
        double _t144 = (java.lang.Math.fma(_other01, _other20, _t39) - java.lang.Math.fma(_other00, _other21, _t39)) / _t108;
        double _t145 = _t81 / _t108;
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t143, (_t84 / _t109), java.lang.Math.fma(_t144, _t131, _t145 * (_t85 / _t109))));
        double _t29 = -_self00 * _self21;
        double _t134 = (java.lang.Math.fma(_self01, _self20, _t29) - java.lang.Math.fma(_self00, _self21, _t29)) / _t109;
        double _t30 = -_self02 * _self20;
        double _t135 = (java.lang.Math.fma(_self00, _self22, _t30) - java.lang.Math.fma(_self02, _self20, _t30)) / _t109;
        double _t31 = -(_self01 * _self22);
        double _t136 = (java.lang.Math.fma(_self02, _self21, _t31) - java.lang.Math.fma(_self01, _self22, _t31)) / _t109;
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t128, _t134, java.lang.Math.fma(_t130, _t135, (_t82 / _t108) * _t136)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t140, _t134, java.lang.Math.fma(_t141, _t135, _t142 * _t136)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t143, _t134, java.lang.Math.fma(_t144, _t135, _t145 * _t136)));
        return _t144;
    }

    /** Part 4 of {@code invertProduct_unsafe_fma_saeb649aa_526}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_unsafe_fma_saeb649aa_526_s242548b6_4(long dest, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _t81, double _t82, double _t108, double _t109, double _t128, double _t130, double _t140, double _t141, double _t142, double _t143, double _t144) {
        double _t32 = _self01 * -_self10;
        double _t137 = (java.lang.Math.fma(_self00, _self11, _t32) - java.lang.Math.fma(_self01, _self10, _t32)) / _t109;
        double _t33 = -_self00 * _self12;
        double _t138 = (java.lang.Math.fma(_self02, _self10, _t33) - java.lang.Math.fma(_self00, _self12, _t33)) / _t109;
        double _t34 = -_self02 * _self11;
        double _t139 = (java.lang.Math.fma(_self01, _self12, _t34) - java.lang.Math.fma(_self02, _self11, _t34)) / _t109;
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_t128, _t137, java.lang.Math.fma(_t130, _t138, (_t82 / _t108) * _t139)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t140, _t137, java.lang.Math.fma(_t141, _t138, _t142 * _t139)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_t143, _t137, java.lang.Math.fma(_t144, _t138, (_t81 / _t108) * _t139)));
    }

    /** Piece 2 of {@code invertProduct_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_fma_saeb649aa_1(long dest, long src, long other, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t81) {
        double _t22 = -_other12 * _other21;
        double _t82 = java.lang.Math.fma(_other11, _other22, _t22) - java.lang.Math.fma(_other12, _other21, _t22);
        double _t23 = -_other12 * _other20;
        double _t108 = java.lang.Math.fma(_other02, _t81, java.lang.Math.fma(_other00, _t82, _other01 * (java.lang.Math.fma(_other12, _other20, _t23) - java.lang.Math.fma(_other10, _other22, _t23))));
        if (!(java.lang.Math.abs(_t108) > 2.2250738585072014E-308 && java.lang.Math.abs(_t108) < 4.49423283715579E307)) return Double3x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        double _t24 = -(_self11 * _self20);
        double _t84 = java.lang.Math.fma(_self10, _self21, _t24) - java.lang.Math.fma(_self11, _self20, _t24);
        double _t25 = -_self12 * _self21;
        double _t85 = java.lang.Math.fma(_self11, _self22, _t25) - java.lang.Math.fma(_self12, _self21, _t25);
        double _t26 = -_self12 * _self20;
        double _t109 = java.lang.Math.fma(_self02, _t84, java.lang.Math.fma(_self00, _t85, _self01 * (java.lang.Math.fma(_self12, _self20, _t26) - java.lang.Math.fma(_self10, _self22, _t26))));
        if (!(java.lang.Math.abs(_t109) > 2.2250738585072014E-308 && java.lang.Math.abs(_t109) < 4.49423283715579E307)) return Double3x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        invertProduct_unsafe_fma_saeb649aa_526(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t81, _t82, _t108, _t84, _t85, _t109);
        return dest;
    }

    /** {@code invertProduct_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_mulAdd(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 56L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 64L);
        return invertProduct_unsafe_mulAdd_s7f31bc6f_5(dest, src, other, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, -_other02 * _other11, -_other01 * _other22, -_self10 * _self22, -_self00 * _self21);
    }

    /** Part 1 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_unsafe_mulAdd_s7f31bc6f_1(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        double _t21 = -(_other11 * _other20);
        double _t22 = -_other12 * _other21;
        double _t23 = -_other12 * _other20;
        return ((_other02) * ((((_other10) * (_other21) + (_t21)) - ((_other11) * (_other20) + (_t21)))) + (((_other00) * ((((_other11) * (_other22) + (_t22)) - ((_other12) * (_other21) + (_t22)))) + (_other01 * (((_other12) * (_other20) + (_t23)) - ((_other10) * (_other22) + (_t23)))))));
    }

    /** Part 2 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_unsafe_mulAdd_s7f31bc6f_2(double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22) {
        double _t24 = -(_self11 * _self20);
        double _t25 = -_self12 * _self21;
        double _t26 = -_self12 * _self20;
        return ((_self02) * ((((_self10) * (_self21) + (_t24)) - ((_self11) * (_self20) + (_t24)))) + (((_self00) * ((((_self11) * (_self22) + (_t25)) - ((_self12) * (_self21) + (_t25)))) + (_self01 * (((_self12) * (_self20) + (_t26)) - ((_self10) * (_self22) + (_t26)))))));
    }

    /** Part 3 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_unsafe_mulAdd_s7f31bc6f_3(long dest, double _self10, double _self20, double _self11, double _self21, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other12, double _other22, double _t108, double _t109, double _t128, double _t130, double _t131, double _t140, double _t141, double _t142, double _t143) {
        double _t21 = -(_other11 * _other20);
        double _t22 = -_other12 * _other21;
        double _t24 = -(_self11 * _self20);
        double _t25 = -_self12 * _self21;
        double _t39 = -_other00 * _other21;
        double _t129 = (((_self10) * (_self21) + (_t24)) - ((_self11) * (_self20) + (_t24))) / _t109;
        double _t133 = (((_self11) * (_self22) + (_t25)) - ((_self12) * (_self21) + (_t25))) / _t109;
        double _t144 = (((_other01) * (_other20) + (_t39)) - ((_other00) * (_other21) + (_t39))) / _t108;
        UnsafeOpsHolder.U.putDouble(dest, ((_t128) * (_t129) + (((_t130) * (_t131) + (((((_other11) * (_other22) + (_t22)) - ((_other12) * (_other21) + (_t22))) / _t108) * _t133)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t140) * (_t129) + (((_t141) * (_t131) + (_t142 * _t133)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t143) * (_t129) + (((_t144) * (_t131) + (((((_other10) * (_other21) + (_t21)) - ((_other11) * (_other20) + (_t21))) / _t108) * _t133)))));
        return _t144;
    }

    /** Part 4 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_mulAdd_s7f31bc6f_4(long dest, double _other10, double _other20, double _other11, double _other21, double _other12, double _other22, double _t108, double _t128, double _t130, double _t134, double _t135, double _t136, double _t137, double _t138, double _t139, double _t140, double _t141, double _t142, double _t143, double _t144) {
        double _t21 = -(_other11 * _other20);
        double _t22 = -_other12 * _other21;
        double _t132 = (((_other11) * (_other22) + (_t22)) - ((_other12) * (_other21) + (_t22))) / _t108;
        double _t145 = (((_other10) * (_other21) + (_t21)) - ((_other11) * (_other20) + (_t21))) / _t108;
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t128) * (_t134) + (((_t130) * (_t135) + (_t132 * _t136)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t140) * (_t134) + (((_t141) * (_t135) + (_t142 * _t136)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t143) * (_t134) + (((_t144) * (_t135) + (_t145 * _t136)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_t128) * (_t137) + (((_t130) * (_t138) + (_t132 * _t139)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t140) * (_t137) + (((_t141) * (_t138) + (_t142 * _t139)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_t143) * (_t137) + (((_t144) * (_t138) + (_t145 * _t139)))));
        return dest;
    }

    /** Piece 2 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_mulAdd_s7f31bc6f_5(long dest, long src, long other, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t20, double _t27, double _t28, double _t29) {
        double _t35 = -_other00 * _other12;
        double _t36 = -_other02 * _other20;
        double _t108 = invertProduct_unsafe_mulAdd_s7f31bc6f_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
        if (!(java.lang.Math.abs(_t108) > 2.2250738585072014E-308 && java.lang.Math.abs(_t108) < 4.49423283715579E307)) return Double3x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        double _t109 = invertProduct_unsafe_mulAdd_s7f31bc6f_2(_self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
        if (!(java.lang.Math.abs(_t109) > 2.2250738585072014E-308 && java.lang.Math.abs(_t109) < 4.49423283715579E307)) return Double3x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        return invertProduct_unsafe_mulAdd_s7f31bc6f_6(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other12, _other22, _t28, _t29, -_self02 * _self20, -(_self01 * _self22), _self01 * -_self10, -_self00 * _self12, -_self02 * _self11, -(_other10 * _other22), -_other01 * _other10, _t108, _t109, (((_other01) * (_other12) + (_t20)) - ((_other02) * (_other11) + (_t20))) / _t108, (((_other02) * (_other21) + (_t27)) - ((_other01) * (_other22) + (_t27))) / _t108, (((_other02) * (_other10) + (_t35)) - ((_other00) * (_other12) + (_t35))) / _t108, (((_other00) * (_other22) + (_t36)) - ((_other02) * (_other20) + (_t36))) / _t108);
    }

    /** Piece 3 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_mulAdd_s7f31bc6f_6(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other12, double _other22, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34, double _t37, double _t38, double _t108, double _t109, double _t128, double _t130, double _t140, double _t141) {
        double _t142 = (((_other12) * (_other20) + (_t37)) - ((_other10) * (_other22) + (_t37))) / _t108;
        double _t143 = (((_other00) * (_other11) + (_t38)) - ((_other01) * (_other10) + (_t38))) / _t108;
        return invertProduct_unsafe_mulAdd_s7f31bc6f_4(dest, _other10, _other20, _other11, _other21, _other12, _other22, _t108, _t128, _t130, ((((_self01) * (_self20) + (_t29)) - ((_self00) * (_self21) + (_t29))) / _t109), ((((_self00) * (_self22) + (_t30)) - ((_self02) * (_self20) + (_t30))) / _t109), ((((_self02) * (_self21) + (_t31)) - ((_self01) * (_self22) + (_t31))) / _t109), ((((_self00) * (_self11) + (_t32)) - ((_self01) * (_self10) + (_t32))) / _t109), ((((_self02) * (_self10) + (_t33)) - ((_self00) * (_self12) + (_t33))) / _t109), ((((_self01) * (_self12) + (_t34)) - ((_self02) * (_self11) + (_t34))) / _t109), _t140, _t141, _t142, _t143, invertProduct_unsafe_mulAdd_s7f31bc6f_3(dest, _self10, _self20, _self11, _self21, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other12, _other22, _t108, _t109, _t128, _t130, ((((_self12) * (_self20) + (_t28)) - ((_self10) * (_self22) + (_t28))) / _t109), _t140, _t141, _t142, _t143));
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        Double3x3OpsKernelsSegment.invertProduct_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 72L), 0L);
        return dest;
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_unsafe_fma(dest, src, other);
        return invertProduct_degenerate_unsafe_mulAdd(dest, src, other);
    }

    /** {@code invertProduct_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_degenerate_unsafe_fma(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 56L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 64L);
        return invertProduct_degenerate_unsafe_fma_see1ccc65_10(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Part 1 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_1(double _other10, double _other20, double _other11, double _other21, double _other12, double _other22) {
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t27 = _other11 * _t1;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t30 = _other20 * _t2;
        double _t63 = -(_t27 * _t30);
        return java.lang.Math.fma((_other10 * _t1), (_other21 * _t2), _t63) - java.lang.Math.fma(_t27, _t30, _t63);
    }

    /** Part 2 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_2(double _other10, double _other20, double _other11, double _other21, double _other12, double _other22) {
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t25 = _other12 * _t1;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t29 = _other21 * _t2;
        double _t64 = -_t25 * _t29;
        return java.lang.Math.fma((_other11 * _t1), (_other22 * _t2), _t64) - java.lang.Math.fma(_t25, _t29, _t64);
    }

    /** Part 3 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_3(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t123, double _t124) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t25 = _other12 * _t1;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t30 = _other20 * _t2;
        double _t65 = -_t25 * _t30;
        return java.lang.Math.fma(_t123, (_other02 * _t0), java.lang.Math.fma(_t124, (_other00 * _t0), (java.lang.Math.fma(_t25, _t30, _t65) - java.lang.Math.fma((_other10 * _t1), (_other22 * _t2), _t65)) * (_other01 * _t0)));
    }

    /** Part 4 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_4(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t26 = _other02 * _t0;
        double _t27 = _other11 * _t1;
        double _t62 = -_t26 * _t27;
        return (java.lang.Math.fma((_other01 * _t0), (_other12 * _t1), _t62) - java.lang.Math.fma(_t26, _t27, _t62)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_5(double _self10, double _self20, double _self11, double _self21, double _self12, double _self22) {
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t35 = _self11 * _t3;
        double _t36 = _self20 * _t4;
        double _t66 = -_t35 * _t36;
        return java.lang.Math.fma((_self10 * _t3), (_self21 * _t4), _t66) - java.lang.Math.fma(_t35, _t36, _t66);
    }

    /** Part 6 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_6(double _self10, double _self20, double _self11, double _self21, double _self12, double _self22) {
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t34 = _self21 * _t4;
        double _t39 = _self12 * _t3;
        double _t67 = -_t39 * _t34;
        return java.lang.Math.fma((_self11 * _t3), (_self22 * _t4), _t67) - java.lang.Math.fma(_t39, _t34, _t67);
    }

    /** Part 7 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_7(double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t126, double _t127) {
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t36 = _self20 * _t4;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t39 = _self12 * _t3;
        double _t68 = -(_t39 * _t36);
        return java.lang.Math.fma(_t126, (_self02 * _t5), java.lang.Math.fma(_t127, (_self00 * _t5), (java.lang.Math.fma(_t39, _t36, _t68) - java.lang.Math.fma((_self10 * _t3), (_self22 * _t4), _t68)) * (_self01 * _t5)));
    }

    /** Part 8 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_8(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t24 = _other01 * _t0;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t31 = _other22 * _t2;
        double _t69 = -_t24 * _t31;
        return (java.lang.Math.fma((_other02 * _t0), (_other21 * _t2), _t69) - java.lang.Math.fma(_t24, _t31, _t69)) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 9 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_9(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other01, double _other02, double _t124, double _t166, double _t186, double _t126, double _t127, double _t167, double _t188) {
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t33 = _self10 * _t3;
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t38 = _self22 * _t4;
        double _t70 = -_t33 * _t38;
        double _t189 = (java.lang.Math.fma((_self12 * _t3), (_self20 * _t4), _t70) - java.lang.Math.fma(_t33, _t38, _t70)) * _t5 / _t167;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t186, (_t126 * _t5 / _t167), java.lang.Math.fma(_t188, _t189, (_t124 * (unitScale(_other00, _other01, _other02)) / _t166) * (_t127 * _t5 / _t167))));
        return _t189;
    }

    /** Part of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_fma_see1ccc65_528(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t123, double _t124, double _t166, double _t186, double _t126, double _t127, double _t167, double _t188, double _t189) {
        double _t198 = invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t199 = invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_2(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t200 = invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_3(dest, _self00, _self01, _self02, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t126, _t127, _t167, _t189, _t198, _t199);
        double _t201 = invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t202 = invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_5(dest, _self00, _self01, _self02, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t166, _t126, _t127, _t167, _t189, _t201);
        invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_6(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t123, _t124, _t166, _t186, _t167, _t188, _t198, _t199, _t200, _t201, _t202);
        invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_7(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t123, _t124, _t166, _t186, _t167, _t188, _t198, _t199, _t200, _t201, _t202);
    }

    /** Part 1 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_1(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t25 = _other12 * _t1;
        double _t32 = _other00 * _t0;
        double _t77 = -_t32 * _t25;
        return (java.lang.Math.fma((_other02 * _t0), (_other10 * _t1), _t77) - java.lang.Math.fma(_t32, _t25, _t77)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 2 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_2(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t26 = _other02 * _t0;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t30 = _other20 * _t2;
        double _t78 = -_t26 * _t30;
        return (java.lang.Math.fma((_other00 * _t0), (_other22 * _t2), _t78) - java.lang.Math.fma(_t26, _t30, _t78)) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 3 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_3(long dest, double _self00, double _self01, double _self02, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166, double _t126, double _t127, double _t167, double _t189, double _t198, double _t199) {
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t28 = _other10 * _t1;
        double _t31 = _other22 * _t2;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t79 = -(_t28 * _t31);
        double _t200 = (java.lang.Math.fma((_other12 * _t1), (_other20 * _t2), _t79) - java.lang.Math.fma(_t28, _t31, _t79)) * (unitScale(_other00, _other01, _other02)) / _t166;
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t198, (_t126 * _t5 / _t167), java.lang.Math.fma(_t199, _t189, _t200 * (_t127 * _t5 / _t167))));
        return _t200;
    }

    /** Part 4 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_4(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t24 = _other01 * _t0;
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t28 = _other10 * _t1;
        double _t80 = -_t24 * _t28;
        return (java.lang.Math.fma((_other00 * _t0), (_other11 * _t1), _t80) - java.lang.Math.fma(_t24, _t28, _t80)) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_5(long dest, double _self00, double _self01, double _self02, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t123, double _t166, double _t126, double _t127, double _t167, double _t189, double _t201) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t29 = _other21 * _t2;
        double _t32 = _other00 * _t0;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t81 = -_t32 * _t29;
        double _t202 = (java.lang.Math.fma((_other01 * _t0), (_other20 * _t2), _t81) - java.lang.Math.fma(_t32, _t29, _t81)) * (unitScale(_other10, _other11, _other12)) / _t166;
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t201, (_t126 * _t5 / _t167), java.lang.Math.fma(_t202, _t189, (_t123 * _t0 / _t166) * (_t127 * _t5 / _t167))));
        return _t202;
    }

    /** Part 6 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_6(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other01, double _other02, double _t123, double _t124, double _t166, double _t186, double _t167, double _t188, double _t198, double _t199, double _t200, double _t201, double _t202) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t34 = _self21 * _t4;
        double _t36 = _self20 * _t4;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t37 = _self02 * _t5;
        double _t38 = _self22 * _t4;
        double _t40 = _self00 * _t5;
        double _t41 = _self01 * _t5;
        double _t71 = -(_t40 * _t34);
        double _t192 = (java.lang.Math.fma(_t41, _t36, _t71) - java.lang.Math.fma(_t40, _t34, _t71)) * _t3 / _t167;
        double _t72 = -(_t37 * _t36);
        double _t193 = (java.lang.Math.fma(_t40, _t38, _t72) - java.lang.Math.fma(_t37, _t36, _t72)) * _t3 / _t167;
        double _t73 = -(_t41 * _t38);
        double _t194 = (java.lang.Math.fma(_t37, _t34, _t73) - java.lang.Math.fma(_t41, _t38, _t73)) * _t3 / _t167;
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t186, _t192, java.lang.Math.fma(_t188, _t193, (_t124 * _t0 / _t166) * _t194)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t198, _t192, java.lang.Math.fma(_t199, _t193, _t200 * _t194)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t201, _t192, java.lang.Math.fma(_t202, _t193, (_t123 * _t0 / _t166) * _t194)));
    }

    /** Part 7 of {@code invertProduct_degenerate_unsafe_fma_see1ccc65_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_fma_see1ccc65_528_sde5201c7_7(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other01, double _other02, double _t123, double _t124, double _t166, double _t186, double _t167, double _t188, double _t198, double _t199, double _t200, double _t201, double _t202) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t33 = _self10 * _t3;
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t35 = _self11 * _t3;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t37 = _self02 * _t5;
        double _t39 = _self12 * _t3;
        double _t40 = _self00 * _t5;
        double _t41 = _self01 * _t5;
        double _t74 = _t41 * -_t33;
        double _t195 = (java.lang.Math.fma(_t40, _t35, _t74) - java.lang.Math.fma(_t41, _t33, _t74)) * _t4 / _t167;
        double _t75 = _t40 * -_t39;
        double _t196 = (java.lang.Math.fma(_t37, _t33, _t75) - java.lang.Math.fma(_t40, _t39, _t75)) * _t4 / _t167;
        double _t76 = _t37 * -_t35;
        double _t197 = (java.lang.Math.fma(_t41, _t39, _t76) - java.lang.Math.fma(_t37, _t35, _t76)) * _t4 / _t167;
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_t186, _t195, java.lang.Math.fma(_t188, _t196, (_t124 * _t0 / _t166) * _t197)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t198, _t195, java.lang.Math.fma(_t199, _t196, _t200 * _t197)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_t201, _t195, java.lang.Math.fma(_t202, _t196, (_t123 * _t0 / _t166) * _t197)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_fma_see1ccc65_10(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        double _t123 = invertProduct_degenerate_unsafe_fma_see1ccc65_1(_other10, _other20, _other11, _other21, _other12, _other22);
        double _t124 = invertProduct_degenerate_unsafe_fma_see1ccc65_2(_other10, _other20, _other11, _other21, _other12, _other22);
        double _t166 = invertProduct_degenerate_unsafe_fma_see1ccc65_3(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t124);
        double _t186 = invertProduct_degenerate_unsafe_fma_see1ccc65_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t126 = invertProduct_degenerate_unsafe_fma_see1ccc65_5(_self10, _self20, _self11, _self21, _self12, _self22);
        double _t127 = invertProduct_degenerate_unsafe_fma_see1ccc65_6(_self10, _self20, _self11, _self21, _self12, _self22);
        double _t167 = invertProduct_degenerate_unsafe_fma_see1ccc65_7(_self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t126, _t127);
        double _t188 = invertProduct_degenerate_unsafe_fma_see1ccc65_8(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        invertProduct_degenerate_unsafe_fma_see1ccc65_528(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t123, _t124, _t166, _t186, _t126, _t127, _t167, _t188, invertProduct_degenerate_unsafe_fma_see1ccc65_9(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other01, _other02, _t124, _t166, _t186, _t126, _t127, _t167, _t188));
        return dest;
    }

    /** {@code invertProduct_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_degenerate_unsafe_mulAdd(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 56L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 64L);
        return invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_9(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Part 1 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_1(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t25 = _other12 * _t1;
        double _t27 = _other11 * _t1;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t28 = _other10 * _t1;
        double _t29 = _other21 * _t2;
        double _t30 = _other20 * _t2;
        double _t63 = -(_t27 * _t30);
        double _t31 = _other22 * _t2;
        double _t64 = -_t25 * _t29;
        double _t65 = -_t25 * _t30;
        return (((((_t28) * (_t29) + (_t63)) - ((_t27) * (_t30) + (_t63)))) * ((_other02 * _t0)) + ((((((_t27) * (_t31) + (_t64)) - ((_t25) * (_t29) + (_t64)))) * ((_other00 * _t0)) + ((((_t25) * (_t30) + (_t65)) - ((_t28) * (_t31) + (_t65))) * (_other01 * _t0)))));
    }

    /** Part 2 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_2(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t26 = _other02 * _t0;
        double _t27 = _other11 * _t1;
        double _t62 = -_t26 * _t27;
        return ((((_other01 * _t0)) * ((_other12 * _t1)) + (_t62)) - ((_t26) * (_t27) + (_t62))) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 3 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_3(double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22) {
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t33 = _self10 * _t3;
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t34 = _self21 * _t4;
        double _t35 = _self11 * _t3;
        double _t36 = _self20 * _t4;
        double _t66 = -_t35 * _t36;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t38 = _self22 * _t4;
        double _t39 = _self12 * _t3;
        double _t67 = -_t39 * _t34;
        double _t68 = -(_t39 * _t36);
        return (((((_t33) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66)))) * ((_self02 * _t5)) + ((((((_t35) * (_t38) + (_t67)) - ((_t39) * (_t34) + (_t67)))) * ((_self00 * _t5)) + ((((_t39) * (_t36) + (_t68)) - ((_t33) * (_t38) + (_t68))) * (_self01 * _t5)))));
    }

    /** Part 4 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_4(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t24 = _other01 * _t0;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t31 = _other22 * _t2;
        double _t69 = -_t24 * _t31;
        return ((((_other02 * _t0)) * ((_other21 * _t2)) + (_t69)) - ((_t24) * (_t31) + (_t69))) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 5 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_5(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166, double _t186, double _t167, double _t188) {
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t25 = _other12 * _t1;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t29 = _other21 * _t2;
        double _t64 = -_t25 * _t29;
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t33 = _self10 * _t3;
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t34 = _self21 * _t4;
        double _t35 = _self11 * _t3;
        double _t36 = _self20 * _t4;
        double _t66 = -_t35 * _t36;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t38 = _self22 * _t4;
        double _t39 = _self12 * _t3;
        double _t67 = -_t39 * _t34;
        double _t70 = -_t33 * _t38;
        double _t189 = (((_t39) * (_t36) + (_t70)) - ((_t33) * (_t38) + (_t70))) * _t5 / _t167;
        UnsafeOpsHolder.U.putDouble(dest, ((_t186) * (((((_t33) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66))) * _t5 / _t167)) + (((_t188) * (_t189) + ((((((_other11 * _t1)) * ((_other22 * _t2)) + (_t64)) - ((_t25) * (_t29) + (_t64))) * (unitScale(_other00, _other01, _other02)) / _t166) * ((((_t35) * (_t38) + (_t67)) - ((_t39) * (_t34) + (_t67))) * _t5 / _t167))))));
        return _t189;
    }

    /** Part 6 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_6(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t25 = _other12 * _t1;
        double _t32 = _other00 * _t0;
        double _t77 = -_t32 * _t25;
        return ((((_other02 * _t0)) * ((_other10 * _t1)) + (_t77)) - ((_t32) * (_t25) + (_t77))) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 7 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_7(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t26 = _other02 * _t0;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t30 = _other20 * _t2;
        double _t78 = -_t26 * _t30;
        return ((((_other00 * _t0)) * ((_other22 * _t2)) + (_t78)) - ((_t26) * (_t30) + (_t78))) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 8 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_8(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166, double _t167, double _t189, double _t198, double _t199) {
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t28 = _other10 * _t1;
        double _t31 = _other22 * _t2;
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t34 = _self21 * _t4;
        double _t35 = _self11 * _t3;
        double _t36 = _self20 * _t4;
        double _t66 = -_t35 * _t36;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t39 = _self12 * _t3;
        double _t67 = -_t39 * _t34;
        double _t79 = -(_t28 * _t31);
        double _t200 = ((((_other12 * _t1)) * ((_other20 * _t2)) + (_t79)) - ((_t28) * (_t31) + (_t79))) * (unitScale(_other00, _other01, _other02)) / _t166;
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t198) * ((((((_self10 * _t3)) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66))) * _t5 / _t167)) + (((_t199) * (_t189) + (_t200 * ((((_t35) * ((_self22 * _t4)) + (_t67)) - ((_t39) * (_t34) + (_t67))) * _t5 / _t167))))));
        return _t200;
    }

    /** Part of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166, double _t186, double _t167, double _t188, double _t189, double _t198, double _t199, double _t200) {
        double _t190 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t201 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_2(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t202 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_3(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t203 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_4(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t167, _t189, _t201, _t202);
        invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_5(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t186, _t167, _t188, _t198, _t199, _t200, _t190, _t201, _t202, _t203);
        invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_6(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t186, _t167, _t188, _t198, _t199, _t200, _t190, _t201, _t202, _t203);
    }

    /** Part 1 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_1(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t25 = _other12 * _t1;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t29 = _other21 * _t2;
        double _t64 = -_t25 * _t29;
        return ((((_other11 * _t1)) * ((_other22 * _t2)) + (_t64)) - ((_t25) * (_t29) + (_t64))) * (unitScale(_other00, _other01, _other02)) / _t166;
    }

    /** Part 2 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_2(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t24 = _other01 * _t0;
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t28 = _other10 * _t1;
        double _t80 = -_t24 * _t28;
        return ((((_other00 * _t0)) * ((_other11 * _t1)) + (_t80)) - ((_t24) * (_t28) + (_t80))) * (unitScale(_other20, _other21, _other22)) / _t166;
    }

    /** Part 3 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_3(double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166) {
        double _t0 = unitScale(_other00, _other01, _other02);
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t29 = _other21 * _t2;
        double _t32 = _other00 * _t0;
        double _t81 = -_t32 * _t29;
        return ((((_other01 * _t0)) * ((_other20 * _t2)) + (_t81)) - ((_t32) * (_t29) + (_t81))) * (unitScale(_other10, _other11, _other12)) / _t166;
    }

    /** Part 4 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_4(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t166, double _t167, double _t189, double _t201, double _t202) {
        double _t1 = unitScale(_other10, _other11, _other12);
        double _t27 = _other11 * _t1;
        double _t2 = unitScale(_other20, _other21, _other22);
        double _t30 = _other20 * _t2;
        double _t63 = -(_t27 * _t30);
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t34 = _self21 * _t4;
        double _t35 = _self11 * _t3;
        double _t36 = _self20 * _t4;
        double _t66 = -_t35 * _t36;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t39 = _self12 * _t3;
        double _t67 = -_t39 * _t34;
        double _t203 = ((((_other10 * _t1)) * ((_other21 * _t2)) + (_t63)) - ((_t27) * (_t30) + (_t63))) * (unitScale(_other00, _other01, _other02)) / _t166;
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t201) * ((((((_self10 * _t3)) * (_t34) + (_t66)) - ((_t35) * (_t36) + (_t66))) * _t5 / _t167)) + (((_t202) * (_t189) + (_t203 * ((((_t35) * ((_self22 * _t4)) + (_t67)) - ((_t39) * (_t34) + (_t67))) * _t5 / _t167))))));
        return _t203;
    }

    /** Part 5 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_5(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t186, double _t167, double _t188, double _t198, double _t199, double _t200, double _t190, double _t201, double _t202, double _t203) {
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t34 = _self21 * _t4;
        double _t36 = _self20 * _t4;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t37 = _self02 * _t5;
        double _t38 = _self22 * _t4;
        double _t40 = _self00 * _t5;
        double _t41 = _self01 * _t5;
        double _t71 = -(_t40 * _t34);
        double _t192 = (((_t41) * (_t36) + (_t71)) - ((_t40) * (_t34) + (_t71))) * _t3 / _t167;
        double _t72 = -(_t37 * _t36);
        double _t193 = (((_t40) * (_t38) + (_t72)) - ((_t37) * (_t36) + (_t72))) * _t3 / _t167;
        double _t73 = -(_t41 * _t38);
        double _t194 = (((_t37) * (_t34) + (_t73)) - ((_t41) * (_t38) + (_t73))) * _t3 / _t167;
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t186) * (_t192) + (((_t188) * (_t193) + (_t190 * _t194)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t198) * (_t192) + (((_t199) * (_t193) + (_t200 * _t194)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t201) * (_t192) + (((_t202) * (_t193) + (_t203 * _t194)))));
    }

    /** Part 6 of {@code invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528_sb81efc2a_6(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t186, double _t167, double _t188, double _t198, double _t199, double _t200, double _t190, double _t201, double _t202, double _t203) {
        double _t3 = unitScale(_self10, _self11, _self12);
        double _t33 = _self10 * _t3;
        double _t4 = unitScale(_self20, _self21, _self22);
        double _t35 = _self11 * _t3;
        double _t5 = unitScale(_self00, _self01, _self02);
        double _t37 = _self02 * _t5;
        double _t39 = _self12 * _t3;
        double _t40 = _self00 * _t5;
        double _t41 = _self01 * _t5;
        double _t74 = _t41 * -_t33;
        double _t195 = (((_t40) * (_t35) + (_t74)) - ((_t41) * (_t33) + (_t74))) * _t4 / _t167;
        double _t75 = _t40 * -_t39;
        double _t196 = (((_t37) * (_t33) + (_t75)) - ((_t40) * (_t39) + (_t75))) * _t4 / _t167;
        double _t76 = _t37 * -_t35;
        double _t197 = (((_t41) * (_t39) + (_t76)) - ((_t37) * (_t35) + (_t76))) * _t4 / _t167;
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_t186) * (_t195) + (((_t188) * (_t196) + (_t190 * _t197)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t198) * (_t195) + (((_t199) * (_t196) + (_t200 * _t197)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_t201) * (_t195) + (((_t202) * (_t196) + (_t203 * _t197)))));
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_9(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        double _t166 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_1(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
        double _t186 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_2(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t167 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_3(_self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
        double _t188 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_4(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t189 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_5(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t186, _t167, _t188);
        double _t198 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_6(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        double _t199 = invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_7(_other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166);
        invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_528(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t186, _t167, _t188, _t189, _t198, _t199, invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_8(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, _t166, _t167, _t189, _t198, _t199));
        return dest;
    }

    public static long normal_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_unsafe_fma(dest, src);
        return normal_unsafe_mulAdd(dest, src);
    }

    /** {@code normal_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normal_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t10 = -_self12 * _self21;
        double _t11 = -(_self11 * _self20);
        double _t12 = -_self12 * _self20;
        double _t26 = java.lang.Math.fma(_self11, _self22, _t10) - java.lang.Math.fma(_self12, _self21, _t10);
        double _t27 = java.lang.Math.fma(_self10, _self21, _t11) - java.lang.Math.fma(_self11, _self20, _t11);
        return normal_unsafe_fma_s8eda2bc0_1(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self10 * _self22, -(_self01 * _self22), -_self02 * _self20, -_self00 * _self21, -_self02 * _self11, -_self00 * _self12, _self01 * -_self10, _t26, _t27, java.lang.Math.fma(_self02, _t27, java.lang.Math.fma(_self00, _t26, _self01 * (java.lang.Math.fma(_self12, _self20, _t12) - java.lang.Math.fma(_self10, _self22, _t12)))));
    }

    /** Piece 2 of {@code normal_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long normal_unsafe_fma_s8eda2bc0_1(long dest, long src, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t33) {
        if (!(java.lang.Math.abs(_t33) > 2.2250738585072014E-308 && java.lang.Math.abs(_t33) < 4.49423283715579E307)) return Double3x3OpsKernelsAddress.normal_degenerate(dest, src);
        double _t33_inv = 1.0 / _t33;
        UnsafeOpsHolder.U.putDouble(dest, _t26 * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, (java.lang.Math.fma(_self02, _self21, _t14) - java.lang.Math.fma(_self01, _self22, _t14)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, (java.lang.Math.fma(_self01, _self12, _t17) - java.lang.Math.fma(_self02, _self11, _t17)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, (java.lang.Math.fma(_self12, _self20, _t13) - java.lang.Math.fma(_self10, _self22, _t13)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, (java.lang.Math.fma(_self00, _self22, _t15) - java.lang.Math.fma(_self02, _self20, _t15)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (java.lang.Math.fma(_self02, _self10, _t18) - java.lang.Math.fma(_self00, _self12, _t18)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t27 * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 56L, (java.lang.Math.fma(_self01, _self20, _t16) - java.lang.Math.fma(_self00, _self21, _t16)) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 64L, (java.lang.Math.fma(_self00, _self11, _t19) - java.lang.Math.fma(_self01, _self10, _t19)) * _t33_inv);
        return dest;
    }

    /** {@code normal_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normal_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t10 = -_self12 * _self21;
        double _t11 = -(_self11 * _self20);
        double _t12 = -_self12 * _self20;
        double _t26 = ((_self11) * (_self22) + (_t10)) - ((_self12) * (_self21) + (_t10));
        double _t27 = ((_self10) * (_self21) + (_t11)) - ((_self11) * (_self20) + (_t11));
        return normal_unsafe_mulAdd_sa1c3a121_1(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self10 * _self22, -(_self01 * _self22), -_self02 * _self20, -_self00 * _self21, -_self02 * _self11, -_self00 * _self12, _self01 * -_self10, _t26, _t27, ((_self02) * (_t27) + (((_self00) * (_t26) + (_self01 * (((_self12) * (_self20) + (_t12)) - ((_self10) * (_self22) + (_t12))))))));
    }

    /** Piece 2 of {@code normal_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long normal_unsafe_mulAdd_sa1c3a121_1(long dest, long src, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t33) {
        if (!(java.lang.Math.abs(_t33) > 2.2250738585072014E-308 && java.lang.Math.abs(_t33) < 4.49423283715579E307)) return Double3x3OpsKernelsAddress.normal_degenerate(dest, src);
        double _t33_inv = 1.0 / _t33;
        UnsafeOpsHolder.U.putDouble(dest, _t26 * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, (((_self02) * (_self21) + (_t14)) - ((_self01) * (_self22) + (_t14))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, (((_self01) * (_self12) + (_t17)) - ((_self02) * (_self11) + (_t17))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, (((_self12) * (_self20) + (_t13)) - ((_self10) * (_self22) + (_t13))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, (((_self00) * (_self22) + (_t15)) - ((_self02) * (_self20) + (_t15))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (((_self02) * (_self10) + (_t18)) - ((_self00) * (_self12) + (_t18))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t27 * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 56L, (((_self01) * (_self20) + (_t16)) - ((_self00) * (_self21) + (_t16))) * _t33_inv);
        UnsafeOpsHolder.U.putDouble(dest + 64L, (((_self00) * (_self11) + (_t19)) - ((_self01) * (_self10) + (_t19))) * _t33_inv);
        return dest;
    }

    public static long normal_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.normal_degenerate_unsafe(dest, src);
        Double3x3OpsKernelsSegment.normal_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    public static long normal_degenerate_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_unsafe_fma(dest, src);
        return normal_degenerate_unsafe_mulAdd(dest, src);
    }

    /** {@code normal_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normal_degenerate_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        return normal_degenerate_unsafe_fma_s7e5d62f7_1(dest, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t16 * _t13, -(_t20 * _t13), -_t18 * _t17, -_t19 * _t15, -_t18 * _t12, -_t19 * _t14, _t20 * -_t16);
    }

    /** Piece 2 of {@code normal_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long normal_degenerate_unsafe_fma_s7e5d62f7_1(long dest, double _t0, double _t1, double _t2, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t31, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40) {
        double _t47 = java.lang.Math.fma(_t12, _t13, _t31) - java.lang.Math.fma(_t14, _t15, _t31);
        double _t48 = java.lang.Math.fma(_t16, _t15, _t32) - java.lang.Math.fma(_t12, _t17, _t32);
        double _t53_inv = 1.0 / java.lang.Math.fma(_t48, _t18, java.lang.Math.fma(_t47, _t19, (java.lang.Math.fma(_t14, _t17, _t33) - java.lang.Math.fma(_t16, _t13, _t33)) * _t20));
        double _sp2 = _t1 * _t53_inv;
        double _sp1 = _t0 * _t53_inv;
        double _sp0 = _t2 * _t53_inv;
        UnsafeOpsHolder.U.putDouble(dest, _t47 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, (java.lang.Math.fma(_t18, _t15, _t35) - java.lang.Math.fma(_t20, _t13, _t35)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, (java.lang.Math.fma(_t20, _t14, _t38) - java.lang.Math.fma(_t18, _t12, _t38)) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 24L, (java.lang.Math.fma(_t14, _t17, _t34) - java.lang.Math.fma(_t16, _t13, _t34)) * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, (java.lang.Math.fma(_t19, _t13, _t36) - java.lang.Math.fma(_t18, _t17, _t36)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (java.lang.Math.fma(_t18, _t16, _t39) - java.lang.Math.fma(_t19, _t14, _t39)) * _sp2);
        return normal_degenerate_unsafe_fma_s7e5d62f7_2(dest, _t12, _t15, _t16, _t17, _t19, _t20, _t37, _t40, _t48, _sp2, _sp1, _sp0);
    }

    /** Piece 3 of {@code normal_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long normal_degenerate_unsafe_fma_s7e5d62f7_2(long dest, double _t12, double _t15, double _t16, double _t17, double _t19, double _t20, double _t37, double _t40, double _t48, double _sp2, double _sp1, double _sp0) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t48 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, (java.lang.Math.fma(_t20, _t17, _t37) - java.lang.Math.fma(_t19, _t15, _t37)) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 64L, (java.lang.Math.fma(_t19, _t12, _t40) - java.lang.Math.fma(_t20, _t16, _t40)) * _sp2);
        return dest;
    }

    /** {@code normal_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normal_degenerate_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        return normal_degenerate_unsafe_mulAdd_s138775e8_1(dest, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, -_t14 * _t15, -(_t12 * _t17), -_t14 * _t17, -_t16 * _t13, -(_t20 * _t13), -_t18 * _t17, -_t19 * _t15, -_t18 * _t12, -_t19 * _t14, _t20 * -_t16);
    }

    /** Piece 2 of {@code normal_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long normal_degenerate_unsafe_mulAdd_s138775e8_1(long dest, double _t0, double _t1, double _t2, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t31, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40) {
        double _t47 = ((_t12) * (_t13) + (_t31)) - ((_t14) * (_t15) + (_t31));
        double _t48 = ((_t16) * (_t15) + (_t32)) - ((_t12) * (_t17) + (_t32));
        double _t53_inv = 1.0 / ((_t48) * (_t18) + (((_t47) * (_t19) + ((((_t14) * (_t17) + (_t33)) - ((_t16) * (_t13) + (_t33))) * _t20))));
        double _sp2 = _t1 * _t53_inv;
        double _sp1 = _t0 * _t53_inv;
        double _sp0 = _t2 * _t53_inv;
        UnsafeOpsHolder.U.putDouble(dest, _t47 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, (((_t18) * (_t15) + (_t35)) - ((_t20) * (_t13) + (_t35))) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, (((_t20) * (_t14) + (_t38)) - ((_t18) * (_t12) + (_t38))) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 24L, (((_t14) * (_t17) + (_t34)) - ((_t16) * (_t13) + (_t34))) * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, (((_t19) * (_t13) + (_t36)) - ((_t18) * (_t17) + (_t36))) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, (((_t18) * (_t16) + (_t39)) - ((_t19) * (_t14) + (_t39))) * _sp2);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t48 * _sp0);
        return normal_degenerate_unsafe_mulAdd_s138775e8_2(dest, _t12, _t15, _t16, _t17, _t19, _t20, _t37, _t40, _sp2, _sp1);
    }

    /** Piece 3 of {@code normal_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long normal_degenerate_unsafe_mulAdd_s138775e8_2(long dest, double _t12, double _t15, double _t16, double _t17, double _t19, double _t20, double _t37, double _t40, double _sp2, double _sp1) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, (((_t20) * (_t17) + (_t37)) - ((_t19) * (_t15) + (_t37))) * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 64L, (((_t19) * (_t12) + (_t40)) - ((_t20) * (_t16) + (_t40))) * _sp2);
        return dest;
    }

    public static double trace_unsafe(long src) {
        return UnsafeOpsHolder.U.getDouble(src + 64L) + (UnsafeOpsHolder.U.getDouble(src) + UnsafeOpsHolder.U.getDouble(src + 32L));
    }

    public static long transpose_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
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
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _m00);
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
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 24L);
        double _m02 = UnsafeOpsHolder.U.getDouble(m + 32L);
        double _m12 = UnsafeOpsHolder.U.getDouble(m + 40L);
        UnsafeOpsHolder.U.putDouble(dest, _m00);
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
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m02 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 32L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 40L);
        double _m12 = UnsafeOpsHolder.U.getDouble(m + 48L);
        double _m20 = UnsafeOpsHolder.U.getDouble(m + 64L);
        double _m21 = UnsafeOpsHolder.U.getDouble(m + 72L);
        double _m22 = UnsafeOpsHolder.U.getDouble(m + 80L);
        UnsafeOpsHolder.U.putDouble(dest, _m00);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
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
        double _tx = UnsafeOpsHolder.U.getDouble(t);
        double _ty = UnsafeOpsHolder.U.getDouble(t + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
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

    public static long makeFromRigid_unsafe(long dest, double rRX, double rRY, double rRZ, double rRW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromRigid_unsafe_mulAdd(dest, rRX, rRY, rRZ, rRW);
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, java.lang.Math.fma(rRY, rRY, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * java.lang.Math.fma(rRX, rRY, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * java.lang.Math.fma(rRX, rRZ, -_t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * java.lang.Math.fma(rRX, rRY, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(-2.0, java.lang.Math.fma(rRX, rRX, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * java.lang.Math.fma(rRX, rRW, rRY * rRZ));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * java.lang.Math.fma(rRX, rRZ, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * java.lang.Math.fma(rRY, rRZ, -(rRX * rRW)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(-2.0, java.lang.Math.fma(rRX, rRX, rRY * rRY), 1.0));
        return dest;
    }

    /** {@code makeFromRigid_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeFromRigid_unsafe_mulAdd(long dest, double rRX, double rRY, double rRZ, double rRW) {
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        UnsafeOpsHolder.U.putDouble(dest, ((1.0) - (2.0) * (((rRY) * (rRY) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * ((rRX) * (rRY) + (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * ((rRX) * (rRZ) - (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * ((rRX) * (rRY) - (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((1.0) - (2.0) * (((rRX) * (rRX) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * ((rRX) * (rRW) + (rRY * rRZ)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * ((rRX) * (rRZ) + (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * ((rRY) * (rRZ) - (rRX * rRW)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((1.0) - (2.0) * (((rRX) * (rRX) + (rRY * rRY)))));
        return dest;
    }

    public static long makeFromTransform_unsafe(long dest, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromTransform_unsafe_mulAdd(dest, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-java.lang.Math.fma(tRY, tRY, _t3), _t0, tSX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(tRX, tRY, _t4) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(tRX, tRZ, -_t5) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(tRX, tRY, -_t4) * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(-java.lang.Math.fma(tRX, tRX, _t3), _t1, tSY));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(tRX, tRZ, _t5) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(-java.lang.Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        return dest;
    }

    /** {@code makeFromTransform_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeFromTransform_unsafe_mulAdd(long dest, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        UnsafeOpsHolder.U.putDouble(dest, ((tSX) - ((tRY) * (tRY) + (_t3)) * (_t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((tRX) * (tRY) + (_t4)) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((tRX) * (tRZ) - (_t5)) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((tRX) * (tRY) - (_t4)) * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((tSY) - ((tRX) * (tRX) + (_t3)) * (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((tRX) * (tRW) + (tRY * tRZ)) * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((tRX) * (tRZ) + (_t5)) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((tRY) * (tRZ) - (tRX * tRW)) * _t2);
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((tSZ) - ((tRX) * (tRX) + (tRY * tRY)) * (_t2)));
        return dest;
    }

    public static long to2x2_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
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
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return to4x4_unsafe_s575d5859_1(dest);
    }

    /** Piece 2 of {@code to4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long to4x4_unsafe_s575d5859_1(long dest) {
        UnsafeOpsHolder.U.putDouble(dest + 96L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 104L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 112L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 120L, 1.0);
        return dest;
    }

    public static long toDualQuat_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t1 = 1.0 - _self00;
        double _t13 = _self22 + (_self00 + _self11);
        double _t14 = 1.0 + _t13;
        double _t15 = _self00 + (1.0 - _self11 - _self22);
        double _t16 = _self11 + (_t1 - _self22);
        double _t17 = _self22 + (_t1 - _self11);
        return toDualQuat_unsafe_s9f6c0bda_1(dest, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t13, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code toDualQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toDualQuat_unsafe_s9f6c0bda_1(long dest, double _self00, double _self11, double _self22, double _t3, double _t5, double _t6, double _t7, double _t8, double _t9, double _t13, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t13 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t7);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t9);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t15));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t5);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t6);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t3);
            } else {
                if (_self11 > _self22) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t5);
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
        return toDualQuat_unsafe_s9f6c0bda_2(dest);
    }

    /** Piece 3 of {@code toDualQuat_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toDualQuat_unsafe_s9f6c0bda_2(long dest) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        return dest;
    }

    public static long toRigid_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_unsafe_fma(dest, src);
        return toRigid_unsafe_mulAdd(dest, src);
    }

    /** {@code toRigid_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toRigid_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        double _ct0 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _ct1 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _t18 = _self10 * _t17;
        double _t19 = _self22 * _t16;
        double _t20 = _self12 * _t16;
        double _t21 = _self20 * _t17;
        double _t23 = _self21 * _t15;
        double _t24 = _self11 * _t15;
        double _t26 = _self00 * _t17;
        double _t31 = java.lang.Math.fma(_self12, _t16, _t23);
        double _t35 = java.lang.Math.fma(_self21, _t15, -_t20);
        double _t47, _t48, _t49;
        if (java.lang.Math.fma(-java.lang.Math.fma(_t18, _t19, -(_t20 * _t21)), _self01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t18, _t23, -(_t24 * _t21)), _self02 * _t16, java.lang.Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0) {
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
        double _t54 = java.lang.Math.fma(_self01, _t15, _t48);
        double _t55 = java.lang.Math.fma(_self02, _t16, _t49);
        double _t56 = java.lang.Math.fma(_self02, _t16, -_t49);
        double _t57 = java.lang.Math.fma(-_self01, _t15, _t48);
        double _t63 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t51));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t63));
        double _t65 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        double _t66 = java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t52));
        double _t67 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t51));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t65));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        if (java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t47)) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 24L, _sp0 * _t35);
            UnsafeOpsHolder.U.putDouble(dest + 32L, _sp0 * _t56);
            UnsafeOpsHolder.U.putDouble(dest + 40L, _sp0 * _t57);
            UnsafeOpsHolder.U.putDouble(dest + 48L, 0.5 * java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
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
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        return dest;
    }

    /** {@code toRigid_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toRigid_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        double _ct0 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _ct1 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toRigid_degenerate(dest, src);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _t18 = _self10 * _t17;
        double _t19 = _self22 * _t16;
        double _t20 = _self12 * _t16;
        double _t21 = _self20 * _t17;
        double _t23 = _self21 * _t15;
        double _t24 = _self11 * _t15;
        double _t26 = _self00 * _t17;
        double _t31 = ((_self12) * (_t16) + (_t23));
        double _t35 = ((_self21) * (_t15) - (_t20));
        double _t47, _t48, _t49;
        if (((((((_t18) * (_t23) - (_t24 * _t21))) * (_self02 * _t16) + (((_t24) * (_t19) - (_t20 * _t23)) * _t26))) - ((_t18) * (_t19) - (_t20 * _t21)) * (_self01 * _t15)) < 0.0) {
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
        double _t54 = ((_self01) * (_t15) + (_t48));
        double _t55 = ((_self02) * (_t16) + (_t49));
        double _t56 = ((_self02) * (_t16) - (_t49));
        double _t57 = ((_t48) - (_self01) * (_t15));
        double _t63 = ((_self11) * (_t15) + (((_self22) * (_t16) + (_t51))));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t63));
        double _t65 = ((_self11) * (_t15) + (((_t1) * (_t16) + (_t52))));
        double _t66 = ((_self22) * (_t16) + (((_t0) * (_t15) + (_t52))));
        double _t67 = ((_t0) * (_t15) + (((_t1) * (_t16) + (_t51))));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t65));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        if (((_self11) * (_t15) + (((_self22) * (_t16) + (_t47)))) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 24L, _sp0 * _t35);
            UnsafeOpsHolder.U.putDouble(dest + 32L, _sp0 * _t56);
            UnsafeOpsHolder.U.putDouble(dest + 40L, _sp0 * _t57);
            UnsafeOpsHolder.U.putDouble(dest + 48L, 0.5 * java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
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
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        return dest;
    }

    public static long toRigid_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.toRigid_degenerate_unsafe(dest, src);
        Double3x3OpsKernelsSegment.toRigid_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 56L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    public static long toRigid_degenerate_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toRigid_degenerate_unsafe_fma(dest, src);
        return toRigid_degenerate_unsafe_mulAdd(dest, src);
    }

    /** {@code toRigid_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toRigid_degenerate_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
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
        double _t99 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t89, _t89, _t74 * _t74))));
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
                    _t166 = java.lang.Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = java.lang.Math.fma(_t35, _t106, -_t33 * _t116);
                    _t171 = java.lang.Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
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
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
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
                if (_t29 <= 0.0) {
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
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (java.lang.Math.fma(java.lang.Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, java.lang.Math.fma(java.lang.Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, java.lang.Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
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
            if (_t194 > Math.max(_t167, _t171)) {
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
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        return dest;
    }

    /** {@code toRigid_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toRigid_degenerate_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
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
        double _t99 = (1.0 / java.lang.Math.sqrt(((_t75) * (_t75) + (((_t87) * (_t87) + (_t72 * _t72))))));
        double _t100 = (1.0 / java.lang.Math.sqrt(((_t76) * (_t76) + (((_t88) * (_t88) + (_t73 * _t73))))));
        double _t101 = (1.0 / java.lang.Math.sqrt(((_t77) * (_t77) + (((_t89) * (_t89) + (_t74 * _t74))))));
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
                    _t166 = ((_t33) * (_t102) - (_t34 * _t106));
                    _t168 = ((_t35) * (_t106) - (_t33 * _t116));
                    _t171 = ((_t34) * (_t116) - (_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
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
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
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
                if (_t29 <= 0.0) {
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
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (((((_t165) * (_t166) - (_t167 * _t168))) * (_t169) + (((((_t170) * (_t168) - (_t165 * _t171))) * (_t172) + (((_t167) * (_t171) - (_t170 * _t166)) * _t173)))) < 0.0) {
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
            if (_t194 > Math.max(_t167, _t171)) {
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
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        return dest;
    }

    public static long toTransform_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_unsafe_fma(dest, src);
        return toTransform_unsafe_mulAdd(dest, src);
    }

    /** {@code toTransform_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toTransform_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t12 = java.lang.Math.fma(_self21, _self21, java.lang.Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        double _t13 = java.lang.Math.fma(_self22, _self22, java.lang.Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        double _t14 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        double _t18 = java.lang.Math.sqrt(_t14);
        return toTransform_unsafe_fma_sdc3b4542_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, (1.0 / java.lang.Math.sqrt(_t12)), (1.0 / java.lang.Math.sqrt(_t13)), _t18, 1.0 / _t18);
    }

    /** Piece 2 of {@code toTransform_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_fma_sdc3b4542_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t17) {
        double _t19 = _self10 * _t17;
        double _t20 = _self22 * _t16;
        double _t21 = _self12 * _t16;
        double _t22 = _self20 * _t17;
        double _t24 = _self21 * _t15;
        double _t25 = _self11 * _t15;
        double _t27 = _self00 * _t17;
        double _t47 = java.lang.Math.fma(-java.lang.Math.fma(_t19, _t20, -(_t21 * _t22)), _self01 * _t15, java.lang.Math.fma(java.lang.Math.fma(_t19, _t24, -(_t25 * _t22)), _self02 * _t16, java.lang.Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
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
        double _t64 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t52));
        return toTransform_unsafe_fma_sdc3b4542_2(dest, _self11, _self22, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, java.lang.Math.fma(_self12, _t16, _t24), java.lang.Math.fma(_self21, _t15, -_t21), Math.max(_t25, _t20), _t47, _t48, _t52, 1.0 - _t48, java.lang.Math.fma(_self01, _t15, _t49), java.lang.Math.fma(_self02, _t16, _t50), java.lang.Math.fma(_self02, _t16, -_t50), java.lang.Math.fma(-_self01, _t15, _t49), java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_self22, _t16, _t48)), _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)));
    }

    /** Piece 3 of {@code toTransform_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_fma_sdc3b4542_2(long dest, double _self11, double _self22, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0) {
        double _t66 = java.lang.Math.fma(_self11, _t15, java.lang.Math.fma(_t1, _t16, _t53));
        double _t67 = java.lang.Math.fma(_self22, _t16, java.lang.Math.fma(_t0, _t15, _t53));
        double _t68 = java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_t1, _t16, _t52));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t68));
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        return toTransform_unsafe_fma_sdc3b4542_3(dest, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t56, _t57, _t58, _t63, _t64, _sp0, _t67, _sp1, _sp2, _sp3);
    }

    /** Piece 4 of {@code toTransform_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_fma_sdc3b4542_3(long dest, double _t12, double _t13, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t67, double _sp1, double _sp2, double _sp3) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * java.lang.Math.sqrt(_t67));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t63 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t47 < 0.0 ? -_t18 : _t18);
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.sqrt(_t12));
        UnsafeOpsHolder.U.putDouble(dest + 72L, java.lang.Math.sqrt(_t13));
        return dest;
    }

    /** {@code toTransform_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toTransform_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t12 = ((_self21) * (_self21) + (((_self01) * (_self01) + (_self11 * _self11))));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        double _t13 = ((_self22) * (_self22) + (((_self02) * (_self02) + (_self12 * _self12))));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        double _t14 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsAddress.toTransform_degenerate(dest, src);
        double _t18 = java.lang.Math.sqrt(_t14);
        return toTransform_unsafe_mulAdd_sf9411683_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, (1.0 / java.lang.Math.sqrt(_t12)), (1.0 / java.lang.Math.sqrt(_t13)), _t18, 1.0 / _t18);
    }

    /** Piece 2 of {@code toTransform_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_mulAdd_sf9411683_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t17) {
        double _t19 = _self10 * _t17;
        double _t20 = _self22 * _t16;
        double _t21 = _self12 * _t16;
        double _t22 = _self20 * _t17;
        double _t24 = _self21 * _t15;
        double _t25 = _self11 * _t15;
        double _t27 = _self00 * _t17;
        double _t47 = ((((((_t19) * (_t24) - (_t25 * _t22))) * (_self02 * _t16) + (((_t25) * (_t20) - (_t21 * _t24)) * _t27))) - ((_t19) * (_t20) - (_t21 * _t22)) * (_self01 * _t15));
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
        double _t64 = ((_self11) * (_t15) + (((_self22) * (_t16) + (_t52))));
        return toTransform_unsafe_mulAdd_sf9411683_2(dest, _t12, _t13, _t18, _t20, _t25, ((_self12) * (_t16) + (_t24)), ((_self21) * (_t15) - (_t21)), Math.max(_t25, _t20), _t47, _t48, ((_self01) * (_t15) + (_t49)), ((_self02) * (_t16) + (_t50)), ((_self02) * (_t16) - (_t50)), ((_t49) - (_self01) * (_t15)), ((_self11) * (_t15) + (((_self22) * (_t16) + (_t48)))), _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), ((_self11) * (_t15) + (((_t1) * (_t16) + (_t53)))), ((_self22) * (_t16) + (((_t0) * (_t15) + (_t53)))), ((_t0) * (_t15) + (((_t1) * (_t16) + (_t52)))));
    }

    /** Piece 3 of {@code toTransform_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_mulAdd_sf9411683_2(long dest, double _t12, double _t13, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t55, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t66, double _t67, double _t68) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t68));
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * java.lang.Math.sqrt(_t67));
        return toTransform_unsafe_mulAdd_sf9411683_3(dest, _t12, _t13, _t18, _t20, _t25, _t36, _t37, _t47, _t48, _t57, _t58, _t63, _t64, _sp1, _sp2, _sp3);
    }

    /** Piece 4 of {@code toTransform_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long toTransform_unsafe_mulAdd_sf9411683_3(long dest, double _t12, double _t13, double _t18, double _t20, double _t25, double _t36, double _t37, double _t47, double _t48, double _t57, double _t58, double _t63, double _t64, double _sp1, double _sp2, double _sp3) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t63 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t47 < 0.0 ? -_t18 : _t18);
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.sqrt(_t12));
        UnsafeOpsHolder.U.putDouble(dest + 72L, java.lang.Math.sqrt(_t13));
        return dest;
    }

    public static long toTransform_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.toTransform_degenerate_unsafe(dest, src);
        Double3x3OpsKernelsSegment.toTransform_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 80L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    public static long toTransform_degenerate_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toTransform_degenerate_unsafe_fma(dest, src);
        return toTransform_degenerate_unsafe_mulAdd(dest, src);
    }

    /** {@code toTransform_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toTransform_degenerate_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        double _t27 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20));
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
        double _t102 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t78, _t78, java.lang.Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t92, _t92, _t77 * _t77))));
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
                    _t169 = java.lang.Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = java.lang.Math.fma(_t37, _t109, -_t35 * _t119);
                    _t174 = java.lang.Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
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
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
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
                if (_t29 <= 0.0) {
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
        double _t185 = _t173 - _t169;
        double _t186 = Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = java.lang.Math.fma(java.lang.Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, java.lang.Math.fma(java.lang.Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, java.lang.Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
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
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t196 < 0.0 ? -_t56 : _t56);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    /** {@code toTransform_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toTransform_degenerate_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        double _t27 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = ((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))));
        double _t29 = ((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))));
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
        double _t102 = (1.0 / java.lang.Math.sqrt(((_t78) * (_t78) + (((_t90) * (_t90) + (_t75 * _t75))))));
        double _t103 = (1.0 / java.lang.Math.sqrt(((_t79) * (_t79) + (((_t91) * (_t91) + (_t76 * _t76))))));
        double _t104 = (1.0 / java.lang.Math.sqrt(((_t80) * (_t80) + (((_t92) * (_t92) + (_t77 * _t77))))));
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
                    _t169 = ((_t35) * (_t105) - (_t36 * _t109));
                    _t171 = ((_t37) * (_t109) - (_t35 * _t119));
                    _t174 = ((_t36) * (_t119) - (_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
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
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
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
                if (_t29 <= 0.0) {
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
        double _t185 = _t173 - _t169;
        double _t186 = Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = ((((_t168) * (_t169) - (_t170 * _t171))) * (_t172) + (((((_t173) * (_t171) - (_t168 * _t174))) * (_t175) + (((_t170) * (_t174) - (_t173 * _t169)) * _t176))));
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
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeRotation_unsafe_fma(dest, src);
        return decomposeRotation_unsafe_mulAdd(dest, src);
    }

    /** {@code decomposeRotation_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeRotation_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
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
        double _t20 = -java.lang.Math.fma(_self21, _t7, java.lang.Math.fma(_self01, _t8, _self11 * _t9));
        return decomposeRotation_unsafe_fma_s5297f7fa_1(dest, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t8, _t9, -java.lang.Math.fma(_self22, _t7, java.lang.Math.fma(_self02, _t8, _self12 * _t9)), _t20, -java.lang.Math.fma(java.lang.Math.fma(_t20, _t7, _self21), _t7, java.lang.Math.fma(java.lang.Math.fma(_t20, _t8, _self01), _t8, java.lang.Math.fma(_t20, _t9, _self11) * _t9)));
    }

    /** Piece 2 of {@code decomposeRotation_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_fma_s5297f7fa_1(long dest, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t19, double _t20, double _t30) {
        double _t34 = java.lang.Math.fma(_t20, _t7, java.lang.Math.fma(_t30, _t7, _self21));
        double _t35 = java.lang.Math.fma(_t20, _t8, java.lang.Math.fma(_t30, _t8, _self01));
        double _t36 = java.lang.Math.fma(_t20, _t9, java.lang.Math.fma(_t30, _t9, _self11));
        double _t39 = java.lang.Math.fma(_t34, _t34, java.lang.Math.fma(_t35, _t35, _t36 * _t36));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t39));
        double _t44, _t45, _t46;
        if (_t39 != 0.0) {
            _t44 = _t34 * _t40;
            _t45 = _t35 * _t40;
            _t46 = _t36 * _t40;
        } else {
            _t44 = 0.0;
            _t45 = 0.0;
            _t46 = 0.0;
        }
        return decomposeRotation_unsafe_fma_s5297f7fa_2(dest, _self02, _self12, _self22, _t7, _t8, _t9, _t19, _t44, _t45, _t46, java.lang.Math.fma(_t8, _t46, -(_t9 * _t45)), java.lang.Math.fma(_t9, _t44, -(_t7 * _t46)), java.lang.Math.fma(_t7, _t45, -(_t8 * _t44)), -java.lang.Math.fma(java.lang.Math.fma(_t19, _t7, _self22), _t44, java.lang.Math.fma(java.lang.Math.fma(_t19, _t8, _self02), _t45, java.lang.Math.fma(_t19, _t9, _self12) * _t46)));
    }

    /** Piece 3 of {@code decomposeRotation_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_fma_s5297f7fa_2(long dest, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t19, double _t44, double _t45, double _t46, double _t60, double _t61, double _t62, double _t68) {
        double _t78, _t79, _t80, _t84, _t85, _t86;
        if (java.lang.Math.fma(java.lang.Math.fma(_t19, _t7, java.lang.Math.fma(_t68, _t44, _self22)), _t60, java.lang.Math.fma(java.lang.Math.fma(_t19, _t8, java.lang.Math.fma(_t68, _t45, _self02)), _t61, java.lang.Math.fma(_t19, _t9, java.lang.Math.fma(_t68, _t46, _self12)) * _t62)) < 0.0) {
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
        double _t81 = _t78 + _t46;
        double _t94 = _t81 + _t84;
        double _t98 = 1.0 + _t94;
        double _t99 = 1.0 + (_t78 - (_t84 + _t46));
        double _t100 = 1.0 + (_t46 - (_t78 + _t84));
        double _t101 = 1.0 + (_t84 - _t81);
        return decomposeRotation_unsafe_fma_s5297f7fa_3(dest, _t46, _t78, _t84, _t79 + _t45, _t79 - _t45, _t44 - _t85, _t85 + _t44, _t80 + _t86, _t86 - _t80, _t94, _t98, _t99, _t100, _t101, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t101)), 0.5 * (1.0 / java.lang.Math.sqrt(_t99)));
    }

    /** Piece 4 of {@code decomposeRotation_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_fma_s5297f7fa_3(long dest, double _t46, double _t78, double _t84, double _t82, double _t83, double _t87, double _t90, double _t92, double _t93, double _t94, double _t98, double _t99, double _t100, double _t101, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t94 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t87);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t93);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t83);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t98));
        } else {
            if (_t78 > Math.max(_t46, _t84)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t99));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t82);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t92);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t87);
            } else {
                if (_t46 > _t84) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t82);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t100));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t90);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t93);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t92);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t90);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t101));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t83);
                }
            }
        }
        return dest;
    }

    /** {@code decomposeRotation_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeRotation_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
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
        double _t20 = -((_self21) * (_t7) + (((_self01) * (_t8) + (_self11 * _t9))));
        double _t30 = -((((_t20) * (_t7) + (_self21))) * (_t7) + (((((_t20) * (_t8) + (_self01))) * (_t8) + (((_t20) * (_t9) + (_self11)) * _t9))));
        return decomposeRotation_unsafe_mulAdd_s6da5ce1b_1(dest, _self11, _self02, _self12, _self22, _t7, _t8, _t9, -((_self22) * (_t7) + (((_self02) * (_t8) + (_self12 * _t9)))), _t20, _t30, ((_t20) * (_t7) + (((_t30) * (_t7) + (_self21)))), ((_t20) * (_t8) + (((_t30) * (_t8) + (_self01)))));
    }

    /** Piece 2 of {@code decomposeRotation_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_mulAdd_s6da5ce1b_1(long dest, double _self11, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t19, double _t20, double _t30, double _t34, double _t35) {
        double _t36 = ((_t20) * (_t9) + (((_t30) * (_t9) + (_self11))));
        double _t39 = ((_t34) * (_t34) + (((_t35) * (_t35) + (_t36 * _t36))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t39));
        double _t44, _t45, _t46;
        if (_t39 != 0.0) {
            _t44 = _t34 * _t40;
            _t45 = _t35 * _t40;
            _t46 = _t36 * _t40;
        } else {
            _t44 = 0.0;
            _t45 = 0.0;
            _t46 = 0.0;
        }
        return decomposeRotation_unsafe_mulAdd_s6da5ce1b_2(dest, _self02, _self12, _self22, _t7, _t8, _t9, _t19, _t44, _t45, _t46, ((_t8) * (_t46) - (_t9 * _t45)), ((_t9) * (_t44) - (_t7 * _t46)), ((_t7) * (_t45) - (_t8 * _t44)), -((((_t19) * (_t7) + (_self22))) * (_t44) + (((((_t19) * (_t8) + (_self02))) * (_t45) + (((_t19) * (_t9) + (_self12)) * _t46)))));
    }

    /** Piece 3 of {@code decomposeRotation_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_mulAdd_s6da5ce1b_2(long dest, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t19, double _t44, double _t45, double _t46, double _t60, double _t61, double _t62, double _t68) {
        double _t78, _t79, _t80, _t84, _t85, _t86;
        if (((((_t19) * (_t7) + (((_t68) * (_t44) + (_self22))))) * (_t60) + (((((_t19) * (_t8) + (((_t68) * (_t45) + (_self02))))) * (_t61) + (((_t19) * (_t9) + (((_t68) * (_t46) + (_self12)))) * _t62)))) < 0.0) {
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
        double _t81 = _t78 + _t46;
        double _t94 = _t81 + _t84;
        double _t98 = 1.0 + _t94;
        double _t99 = 1.0 + (_t78 - (_t84 + _t46));
        double _t100 = 1.0 + (_t46 - (_t78 + _t84));
        double _t101 = 1.0 + (_t84 - _t81);
        return decomposeRotation_unsafe_mulAdd_s6da5ce1b_3(dest, _t46, _t78, _t84, _t79 + _t45, _t79 - _t45, _t44 - _t85, _t85 + _t44, _t80 + _t86, _t86 - _t80, _t94, _t98, _t99, _t100, _t101, 0.5 * (1.0 / java.lang.Math.sqrt(_t98)), 0.5 * (1.0 / java.lang.Math.sqrt(_t100)), 0.5 * (1.0 / java.lang.Math.sqrt(_t101)), 0.5 * (1.0 / java.lang.Math.sqrt(_t99)));
    }

    /** Piece 4 of {@code decomposeRotation_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeRotation_unsafe_mulAdd_s6da5ce1b_3(long dest, double _t46, double _t78, double _t84, double _t82, double _t83, double _t87, double _t90, double _t92, double _t93, double _t94, double _t98, double _t99, double _t100, double _t101, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t94 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t87);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t93);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t83);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t98));
        } else {
            if (_t78 > Math.max(_t46, _t84)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t99));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t82);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t92);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t87);
            } else {
                if (_t46 > _t84) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t82);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t100));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t90);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t93);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t92);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t90);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t101));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t83);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
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
        double _t18 = -java.lang.Math.fma(_self21, _t8, java.lang.Math.fma(_self01, _t9, _self11 * _t10));
        return decomposeScale_unsafe_fma_s340113d2_1(dest, _self01, _self11, _self21, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -java.lang.Math.fma(_self22, _t8, java.lang.Math.fma(_self02, _t9, _self12 * _t10)), _t18, -java.lang.Math.fma(java.lang.Math.fma(_t18, _t8, _self21), _t8, java.lang.Math.fma(java.lang.Math.fma(_t18, _t9, _self01), _t9, java.lang.Math.fma(_t18, _t10, _self11) * _t10)));
    }

    /** Piece 2 of {@code decomposeScale_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeScale_unsafe_fma_s340113d2_1(long dest, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t4, double _t8, double _t9, double _t10, double _t17, double _t18, double _t28) {
        double _t32 = java.lang.Math.fma(_t18, _t8, java.lang.Math.fma(_t28, _t8, _self21));
        double _t33 = java.lang.Math.fma(_t18, _t9, java.lang.Math.fma(_t28, _t9, _self01));
        double _t34 = java.lang.Math.fma(_t18, _t10, java.lang.Math.fma(_t28, _t10, _self11));
        double _t37 = java.lang.Math.fma(_t32, _t32, java.lang.Math.fma(_t33, _t33, _t34 * _t34));
        double _t38 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t42, _t43, _t44;
        if (_t37 != 0.0) {
            _t42 = _t32 * _t38;
            _t43 = _t33 * _t38;
            _t44 = _t34 * _t38;
        } else {
            _t42 = 0.0;
            _t43 = 0.0;
            _t44 = 0.0;
        }
        double _t48 = -java.lang.Math.fma(java.lang.Math.fma(_t17, _t8, _self22), _t42, java.lang.Math.fma(java.lang.Math.fma(_t17, _t9, _self02), _t43, java.lang.Math.fma(_t17, _t10, _self12) * _t44));
        double _t52 = java.lang.Math.fma(_t17, _t8, java.lang.Math.fma(_t48, _t42, _self22));
        double _t53 = java.lang.Math.fma(_t17, _t9, java.lang.Math.fma(_t48, _t43, _self02));
        double _t54 = java.lang.Math.fma(_t17, _t10, java.lang.Math.fma(_t48, _t44, _self12));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t52, java.lang.Math.fma(_t9, _t44, -(_t10 * _t43)), java.lang.Math.fma(_t53, java.lang.Math.fma(_t10, _t42, -(_t8 * _t44)), _t54 * java.lang.Math.fma(_t8, _t43, -(_t9 * _t42)))) < 0.0 ? -_t4 : _t4);
        return decomposeScale_unsafe_fma_s340113d2_2(dest, _t37, _t52, _t53, _t54);
    }

    /** Piece 3 of {@code decomposeScale_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeScale_unsafe_fma_s340113d2_2(long dest, double _t37, double _t52, double _t53, double _t54) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.sqrt(_t37));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.sqrt(java.lang.Math.fma(_t52, _t52, java.lang.Math.fma(_t53, _t53, _t54 * _t54))));
        return dest;
    }

    /** {@code decomposeScale_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeScale_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
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
        double _t18 = -((_self21) * (_t8) + (((_self01) * (_t9) + (_self11 * _t10))));
        double _t28 = -((((_t18) * (_t8) + (_self21))) * (_t8) + (((((_t18) * (_t9) + (_self01))) * (_t9) + (((_t18) * (_t10) + (_self11)) * _t10))));
        return decomposeScale_unsafe_mulAdd_sadcb9cd3_1(dest, _self01, _self11, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -((_self22) * (_t8) + (((_self02) * (_t9) + (_self12 * _t10)))), _t18, _t28, ((_t18) * (_t8) + (((_t28) * (_t8) + (_self21)))));
    }

    /** Piece 2 of {@code decomposeScale_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeScale_unsafe_mulAdd_sadcb9cd3_1(long dest, double _self01, double _self11, double _self02, double _self12, double _self22, double _t4, double _t8, double _t9, double _t10, double _t17, double _t18, double _t28, double _t32) {
        double _t33 = ((_t18) * (_t9) + (((_t28) * (_t9) + (_self01))));
        double _t34 = ((_t18) * (_t10) + (((_t28) * (_t10) + (_self11))));
        double _t37 = ((_t32) * (_t32) + (((_t33) * (_t33) + (_t34 * _t34))));
        double _t38 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t42, _t43, _t44;
        if (_t37 != 0.0) {
            _t42 = _t32 * _t38;
            _t43 = _t33 * _t38;
            _t44 = _t34 * _t38;
        } else {
            _t42 = 0.0;
            _t43 = 0.0;
            _t44 = 0.0;
        }
        double _t48 = -((((_t17) * (_t8) + (_self22))) * (_t42) + (((((_t17) * (_t9) + (_self02))) * (_t43) + (((_t17) * (_t10) + (_self12)) * _t44))));
        double _t52 = ((_t17) * (_t8) + (((_t48) * (_t42) + (_self22))));
        double _t53 = ((_t17) * (_t9) + (((_t48) * (_t43) + (_self02))));
        double _t54 = ((_t17) * (_t10) + (((_t48) * (_t44) + (_self12))));
        UnsafeOpsHolder.U.putDouble(dest, ((_t52) * (((_t9) * (_t44) - (_t10 * _t43))) + (((_t53) * (((_t10) * (_t42) - (_t8 * _t44))) + (_t54 * ((_t8) * (_t43) - (_t9 * _t42)))))) < 0.0 ? -_t4 : _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.sqrt(_t37));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.sqrt(((_t52) * (_t52) + (((_t53) * (_t53) + (_t54 * _t54))))));
        return dest;
    }

    public static long decomposeSkew_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSkew_unsafe_fma(dest, src);
        return decomposeSkew_unsafe_mulAdd(dest, src);
    }

    /** {@code decomposeSkew_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSkew_unsafe_fma(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = java.lang.Math.fma(_self20, _self20, java.lang.Math.fma(_self00, _self00, _self10 * _self10));
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
        double _t14 = java.lang.Math.fma(_self22, _t7, java.lang.Math.fma(_self02, _t8, _self12 * _t9));
        double _t15 = java.lang.Math.fma(_self21, _t7, java.lang.Math.fma(_self01, _t8, _self11 * _t9));
        double _t17 = -_t15;
        return decomposeSkew_unsafe_fma_s6539706a_1(dest, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, _t17, java.lang.Math.fma(java.lang.Math.fma(_t17, _t7, _self21), _t7, java.lang.Math.fma(java.lang.Math.fma(_t17, _t8, _self01), _t8, java.lang.Math.fma(_t17, _t9, _self11) * _t9)));
    }

    /** Piece 2 of {@code decomposeSkew_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_fma_s6539706a_1(long dest, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t14, double _t15, double _t16, double _t17, double _t26) {
        double _t27 = -_t26;
        double _t32 = java.lang.Math.fma(_t17, _t7, java.lang.Math.fma(_t27, _t7, _self21));
        double _t33 = java.lang.Math.fma(_t17, _t8, java.lang.Math.fma(_t27, _t8, _self01));
        double _t34 = java.lang.Math.fma(_t17, _t9, java.lang.Math.fma(_t27, _t9, _self11));
        double _t37 = java.lang.Math.fma(_t32, _t32, java.lang.Math.fma(_t33, _t33, _t34 * _t34));
        double _t38 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43, _t44, _t45;
        if (_t37 != 0.0) {
            _t43 = _t32 * _t38;
            _t44 = _t33 * _t38;
            _t45 = _t34 * _t38;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t57 = java.lang.Math.fma(java.lang.Math.fma(_t16, _t7, _self22), _t43, java.lang.Math.fma(java.lang.Math.fma(_t16, _t8, _self02), _t44, java.lang.Math.fma(_t16, _t9, _self12) * _t45));
        double _t58 = -_t57;
        double _t62 = java.lang.Math.fma(_t16, _t7, java.lang.Math.fma(_t58, _t43, _self22));
        double _t63 = java.lang.Math.fma(_t16, _t8, java.lang.Math.fma(_t58, _t44, _self02));
        double _t64 = java.lang.Math.fma(_t16, _t9, java.lang.Math.fma(_t58, _t45, _self12));
        double _t71 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t62, _t62, java.lang.Math.fma(_t63, _t63, _t64 * _t64))));
        return decomposeSkew_unsafe_fma_s6539706a_2(dest, _t7, _t8, _t9, (_t15 + _t26) * _t38, _t43, _t44, _t45, _t57, _t62, _t63, _t64, _t71, _t14 * _t71);
    }

    /** Piece 3 of {@code decomposeSkew_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_fma_s6539706a_2(long dest, double _t7, double _t8, double _t9, double _t39, double _t43, double _t44, double _t45, double _t57, double _t62, double _t63, double _t64, double _t71, double _t72) {
        if (java.lang.Math.fma(_t62, java.lang.Math.fma(_t8, _t45, -(_t9 * _t44)), java.lang.Math.fma(_t63, java.lang.Math.fma(_t9, _t43, -(_t7 * _t45)), _t64 * java.lang.Math.fma(_t7, _t44, -(_t8 * _t43)))) < 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, -_t72);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -_t39);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t72);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t39);
        }
        UnsafeOpsHolder.U.putDouble(dest, _t57 * _t71);
        return dest;
    }

    /** {@code decomposeSkew_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSkew_unsafe_mulAdd(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = ((_self20) * (_self20) + (((_self00) * (_self00) + (_self10 * _self10))));
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
        double _t14 = ((_self22) * (_t7) + (((_self02) * (_t8) + (_self12 * _t9))));
        double _t15 = ((_self21) * (_t7) + (((_self01) * (_t8) + (_self11 * _t9))));
        double _t17 = -_t15;
        double _t26 = ((((_t17) * (_t7) + (_self21))) * (_t7) + (((((_t17) * (_t8) + (_self01))) * (_t8) + (((_t17) * (_t9) + (_self11)) * _t9))));
        return decomposeSkew_unsafe_mulAdd_s4970e4cb_1(dest, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, _t17, _t26, -_t26);
    }

    /** Piece 2 of {@code decomposeSkew_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_mulAdd_s4970e4cb_1(long dest, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t14, double _t15, double _t16, double _t17, double _t26, double _t27) {
        double _t32 = ((_t17) * (_t7) + (((_t27) * (_t7) + (_self21))));
        double _t33 = ((_t17) * (_t8) + (((_t27) * (_t8) + (_self01))));
        double _t34 = ((_t17) * (_t9) + (((_t27) * (_t9) + (_self11))));
        double _t37 = ((_t32) * (_t32) + (((_t33) * (_t33) + (_t34 * _t34))));
        double _t38 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43, _t44, _t45;
        if (_t37 != 0.0) {
            _t43 = _t32 * _t38;
            _t44 = _t33 * _t38;
            _t45 = _t34 * _t38;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t57 = ((((_t16) * (_t7) + (_self22))) * (_t43) + (((((_t16) * (_t8) + (_self02))) * (_t44) + (((_t16) * (_t9) + (_self12)) * _t45))));
        double _t58 = -_t57;
        double _t62 = ((_t16) * (_t7) + (((_t58) * (_t43) + (_self22))));
        double _t63 = ((_t16) * (_t8) + (((_t58) * (_t44) + (_self02))));
        double _t64 = ((_t16) * (_t9) + (((_t58) * (_t45) + (_self12))));
        double _t71 = (1.0 / java.lang.Math.sqrt(((_t62) * (_t62) + (((_t63) * (_t63) + (_t64 * _t64))))));
        return decomposeSkew_unsafe_mulAdd_s4970e4cb_2(dest, _t7, _t8, _t9, (_t15 + _t26) * _t38, _t43, _t44, _t45, _t57, _t62, _t63, _t64, _t71, _t14 * _t71);
    }

    /** Piece 3 of {@code decomposeSkew_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeSkew_unsafe_mulAdd_s4970e4cb_2(long dest, double _t7, double _t8, double _t9, double _t39, double _t43, double _t44, double _t45, double _t57, double _t62, double _t63, double _t64, double _t71, double _t72) {
        if (((_t62) * (((_t8) * (_t45) - (_t9 * _t44))) + (((_t63) * (((_t9) * (_t43) - (_t7 * _t45))) + (_t64 * ((_t7) * (_t44) - (_t8 * _t43)))))) < 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, -_t72);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -_t39);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t72);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t39);
        }
        UnsafeOpsHolder.U.putDouble(dest, _t57 * _t71);
        return dest;
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 9; _i++) {
                double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
                double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, java.lang.Math.fma(t, _eother - _eself, _eself));
            }
            return dest;
        } else {
            for (int _i = 0; _i < 9; _i++) {
                double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
                double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, ((t) * (_eother - _eself) + (_eself)));
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, java.lang.Math.fma(_eright2, _self02, java.lang.Math.fma(_eright0, _self00, _eright1 * _self01)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, java.lang.Math.fma(_eright2, _self12, java.lang.Math.fma(_eright0, _self10, _eright1 * _self11)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, java.lang.Math.fma(_eright2, _self22, java.lang.Math.fma(_eright0, _self20, _eright1 * _self21)));
        }
        return dest;
    }

    /** {@code mul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mul_unsafe_mulAdd(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, ((_eright2) * (_self02) + (((_eright0) * (_self00) + (_eright1 * _self01)))));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, ((_eright2) * (_self12) + (((_eright0) * (_self10) + (_eright1 * _self11)))));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, ((_eright2) * (_self22) + (((_eright0) * (_self20) + (_eright1 * _self21)))));
        }
        return dest;
    }

    public static long mulMat2x2_unsafe(long dest, long src, long right) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x2_unsafe_fma(dest, src, right);
        return mulMat2x2_unsafe_mulAdd(dest, src, right);
    }

    /** {@code mulMat2x2_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulMat2x2_unsafe_fma(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_right00, _self20, _right10 * _self21));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        return mulMat2x2_unsafe_fma_s63e69d83_1(dest, _self20, _self21, _self02, _self12, _self22, _right01, _right11);
    }

    /** Piece 2 of {@code mulMat2x2_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long mulMat2x2_unsafe_fma_s63e69d83_1(long dest, double _self20, double _self21, double _self02, double _self12, double _self22, double _right01, double _right11) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_right01, _self20, _right11 * _self21));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    /** {@code mulMat2x2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulMat2x2_unsafe_mulAdd(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        UnsafeOpsHolder.U.putDouble(dest, ((_right00) * (_self00) + (_right10 * _self01)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_right00) * (_self10) + (_right10 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_right00) * (_self20) + (_right10 * _self21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_right01) * (_self00) + (_right11 * _self01)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_right01) * (_self10) + (_right11 * _self11)));
        return mulMat2x2_unsafe_mulAdd_sc377e3cc_1(dest, _self20, _self21, _self02, _self12, _self22, _right01, _right11);
    }

    /** Piece 2 of {@code mulMat2x2_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long mulMat2x2_unsafe_mulAdd_sc377e3cc_1(long dest, double _self20, double _self21, double _self02, double _self12, double _self22, double _right01, double _right11) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_right01) * (_self20) + (_right11 * _self21)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long mulMat2x3_unsafe(long dest, long src, long right) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x3_unsafe_fma(dest, src, right);
        return mulMat2x3_unsafe_mulAdd(dest, src, right);
    }

    /** {@code mulMat2x3_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulMat2x3_unsafe_fma(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        double _right02 = UnsafeOpsHolder.U.getDouble(right + 32L);
        double _right12 = UnsafeOpsHolder.U.getDouble(right + 40L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_right00, _self20, _right10 * _self21));
        return mulMat2x3_unsafe_fma_s34c7aaaa_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long mulMat2x3_unsafe_fma_s34c7aaaa_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _right01, double _right11, double _right02, double _right12) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_right01, _self20, _right11 * _self21));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_right02, _self00, java.lang.Math.fma(_right12, _self01, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_right02, _self10, java.lang.Math.fma(_right12, _self11, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_right02, _self20, java.lang.Math.fma(_right12, _self21, _self22)));
        return dest;
    }

    /** {@code mulMat2x3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulMat2x3_unsafe_mulAdd(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        double _right02 = UnsafeOpsHolder.U.getDouble(right + 32L);
        double _right12 = UnsafeOpsHolder.U.getDouble(right + 40L);
        UnsafeOpsHolder.U.putDouble(dest, ((_right00) * (_self00) + (_right10 * _self01)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_right00) * (_self10) + (_right10 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_right00) * (_self20) + (_right10 * _self21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_right01) * (_self00) + (_right11 * _self01)));
        return mulMat2x3_unsafe_mulAdd_sc856cc7_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long mulMat2x3_unsafe_mulAdd_sc856cc7_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _right01, double _right11, double _right02, double _right12) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_right01) * (_self10) + (_right11 * _self11)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_right01) * (_self20) + (_right11 * _self21)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_right02) * (_self00) + (((_right12) * (_self01) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_right02) * (_self10) + (((_right12) * (_self11) + (_self12)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_right02) * (_self20) + (((_right12) * (_self21) + (_self22)))));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_fma(dest, src, other);
        return preMul_unsafe_mulAdd(dest, src, other);
    }

    /** {@code preMul_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_fma(long dest, long src, long other) {
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
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
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, java.lang.Math.fma(_other02, _eself2, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, java.lang.Math.fma(_other12, _eself2, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, java.lang.Math.fma(_other22, _eself2, java.lang.Math.fma(_other20, _eself0, _other21 * _eself1)));
        }
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, long other) {
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
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
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, ((_other02) * (_eself2) + (((_other00) * (_eself0) + (_other01 * _eself1)))));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, ((_other12) * (_eself2) + (((_other10) * (_eself0) + (_other11 * _eself1)))));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, ((_other22) * (_eself2) + (((_other20) * (_eself0) + (_other21 * _eself1)))));
        }
        return dest;
    }

    public static long preMulMat2x2_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x2_unsafe_mulAdd(dest, src, other);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    /** {@code preMulMat2x2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulMat2x2_unsafe_mulAdd(long dest, long src, long other) {
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, ((_other00) * (_eself0) + (_other01 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, ((_other10) * (_eself0) + (_other11 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static long preMulMat2x3_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat2x3_unsafe_mulAdd(dest, src, other);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
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
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, java.lang.Math.fma(_other02, _eself2, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, java.lang.Math.fma(_other12, _eself2, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    /** {@code preMulMat2x3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulMat2x3_unsafe_mulAdd(long dest, long src, long other) {
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
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
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, ((_other02) * (_eself2) + (((_other00) * (_eself0) + (_other01 * _eself1)))));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, ((_other12) * (_eself2) + (((_other10) * (_eself0) + (_other11 * _eself1)))));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long other, double weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _i = 0; _i < 9; _i++) {
                double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
                double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, java.lang.Math.fma(weight, _eother, _eself));
            }
            return dest;
        } else {
            for (int _i = 0; _i < 9; _i++) {
                double _eself = UnsafeOpsHolder.U.getDouble(src + _i * 8L);
                double _eother = UnsafeOpsHolder.U.getDouble(other + _i * 8L);
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, ((weight) * (_eother) + (_eself)));
            }
            return dest;
        }
    }

    public static long makeOuterProduct_unsafe(long dest, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        UnsafeOpsHolder.U.putDouble(dest, colX * rowX);
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
        double _colx = UnsafeOpsHolder.U.getDouble(col);
        double _coly = UnsafeOpsHolder.U.getDouble(col + 8L);
        double _colz = UnsafeOpsHolder.U.getDouble(col + 16L);
        double _rowx = UnsafeOpsHolder.U.getDouble(row);
        double _rowy = UnsafeOpsHolder.U.getDouble(row + 8L);
        double _rowz = UnsafeOpsHolder.U.getDouble(row + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _colx * _rowx);
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

    public static long lookAlong_lh(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        Double3x3OpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t11, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        return lookAlong_lh_unsafe_fma_sdf0896b4_1(dest, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t18, _t20, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sdf0896b4_1(long dest, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t18, double _t20, double _t27, double _t28) {
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        double _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        return lookAlong_lh_unsafe_fma_sdf0896b4_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sdf0896b4_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        double _t18 = ((_t17) * (_t11) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t12) + (upZ));
        return lookAlong_lh_unsafe_mulAdd_sfb4f7db7_1(dest, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t11)), ((_t19) * (_t12) - (_t20 * _t13)), ((_t20) * (_t11) - (_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sfb4f7db7_1(long dest, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        double _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        return lookAlong_lh_unsafe_mulAdd_sfb4f7db7_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sfb4f7db7_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        Double3x3OpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        double _t21 = java.lang.Math.fma(_t20, _t16, upY);
        double _t22 = java.lang.Math.fma(_t20, _t14, upX);
        double _t23 = java.lang.Math.fma(_t20, _t15, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        return lookAlong_rh_unsafe_fma_s5d422ade_2(dest, src, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _t30, _t31, _t32, java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void lookAlong_rh_unsafe_fma_s5d422ade_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t0, double _t1, double _t2, double _t14, double _t15, double _t16, double _t40, double _t41, double _t42) {
        double _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        double _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        double _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5d422ade_2(long dest, long src, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35) {
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_unsafe_fma_s5d422ade_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getDouble(src + 48L)), (-UnsafeOpsHolder.U.getDouble(src + 56L)), (-UnsafeOpsHolder.U.getDouble(src + 64L)), _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        double _t21 = ((_t20) * (_t16) + (upY));
        double _t22 = ((_t20) * (_t14) + (upX));
        double _t23 = ((_t20) * (_t15) + (upZ));
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_unsafe_mulAdd_sa8b101a1_2(dest, src, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sa8b101a1_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t0, double _t1, double _t2, double _t14, double _t15, double _t16, double _t40, double _t41, double _t42) {
        double _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        double _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        double _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        UnsafeOpsHolder.U.putDouble(dest, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sa8b101a1_2(long dest, long src, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return lookAlong_rh_unsafe_mulAdd_sa8b101a1_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getDouble(src + 48L)), (-UnsafeOpsHolder.U.getDouble(src + 56L)), (-UnsafeOpsHolder.U.getDouble(src + 64L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    public static long lookAlong_lh(long dest, long src, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dir, up);
        Double3x3OpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dir, up);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dir, up);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        return lookAlong_lh_unsafe_fma_s96ce96ea_1(dest, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s96ce96ea_1(long dest, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13) {
        double _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t11, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        double _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        return lookAlong_lh_unsafe_fma_s96ce96ea_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, _t46, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)), java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s96ce96ea_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        return lookAlong_lh_unsafe_mulAdd_se2d36c15_1(dest, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_se2d36c15_1(long dest, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13) {
        double _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t11) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t12) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        double _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        return lookAlong_lh_unsafe_mulAdd_se2d36c15_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, _t46, ((_t37) * (_t12) - (_t38 * _t11)), ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_se2d36c15_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dir, up);
        Double3x3OpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dir, up);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dir, up);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, long dir, long up) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _t7 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        return lookAlong_rh_unsafe_fma_s5403cc14_2(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _upx, _upy, _upz, java.lang.Math.fma(_t20, _t16, _upy), java.lang.Math.fma(_t20, _t14, _upx), java.lang.Math.fma(_t20, _t15, _upz));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void lookAlong_rh_unsafe_fma_s5403cc14_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t0, double _t1, double _t2, double _t14, double _t15, double _t16, double _t40, double _t41, double _t42) {
        double _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        double _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        double _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5403cc14_2(long dest, long src, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t14, double _t15, double _t16, double _upx, double _upy, double _upz, double _t21, double _t22, double _t23) {
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_unsafe_fma_s5403cc14_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getDouble(src + 48L)), (-UnsafeOpsHolder.U.getDouble(src + 56L)), (-UnsafeOpsHolder.U.getDouble(src + 64L)), _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, long dir, long up) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _t7 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        return lookAlong_rh_unsafe_mulAdd_sfcfb9957_2(dest, src, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _upx, _upy, _upz, ((_t20) * (_t16) + (_upy)), ((_t20) * (_t14) + (_upx)), ((_t20) * (_t15) + (_upz)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sfcfb9957_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t0, double _t1, double _t2, double _t14, double _t15, double _t16, double _t40, double _t41, double _t42) {
        double _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        double _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        double _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        UnsafeOpsHolder.U.putDouble(dest, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sfcfb9957_2(long dest, long src, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t14, double _t15, double _t16, double _upx, double _upy, double _upz, double _t21, double _t22, double _t23) {
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return lookAlong_rh_unsafe_mulAdd_sfcfb9957_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getDouble(src + 48L)), (-UnsafeOpsHolder.U.getDouble(src + 56L)), (-UnsafeOpsHolder.U.getDouble(src + 64L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    public static long lookAlong_lh(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        Double3x3OpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t11, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        return lookAlong_lh_unsafe_fma_sf304bc97_1(dest, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t18, _t20, java.lang.Math.fma(_t18, _t13, -(_t19 * _t11)), java.lang.Math.fma(_t19, _t12, -(_t20 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sf304bc97_1(long dest, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t18, double _t20, double _t27, double _t28) {
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        double _t47 = java.lang.Math.fma(_t37, _t12, -(_t38 * _t11));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        return lookAlong_lh_unsafe_fma_sf304bc97_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sf304bc97_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        double _t18 = ((_t17) * (_t11) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t12) + (upZ));
        return lookAlong_lh_unsafe_mulAdd_s136adba4_1(dest, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t11)), ((_t19) * (_t12) - (_t20 * _t13)), ((_t20) * (_t11) - (_t18 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s136adba4_1(long dest, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        double _t47 = ((_t37) * (_t12) - (_t38 * _t11));
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        return lookAlong_lh_unsafe_mulAdd_s136adba4_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t12, _t13, _t38, _t39, _t47, ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s136adba4_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        Double3x3OpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        double _t21 = java.lang.Math.fma(_t20, _t16, upY);
        double _t22 = java.lang.Math.fma(_t20, _t14, upX);
        double _t23 = java.lang.Math.fma(_t20, _t15, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        return lookAlong_rh_unsafe_fma_s8ac55e65_2(dest, src, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _t30, _t31, _t32, java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void lookAlong_rh_unsafe_fma_s8ac55e65_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t0, double _t1, double _t2, double _t14, double _t15, double _t16, double _t40, double _t41, double _t42) {
        double _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        double _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        double _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s8ac55e65_2(long dest, long src, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35) {
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_unsafe_fma_s8ac55e65_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getDouble(src + 48L)), (-UnsafeOpsHolder.U.getDouble(src + 56L)), (-UnsafeOpsHolder.U.getDouble(src + 64L)), _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = dirX * _t9;
            _t15 = dirZ * _t9;
            _t16 = dirY * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        double _t21 = ((_t20) * (_t16) + (upY));
        double _t22 = ((_t20) * (_t14) + (upX));
        double _t23 = ((_t20) * (_t15) + (upZ));
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_unsafe_mulAdd_s3d7cbd42_2(dest, src, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s3d7cbd42_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t0, double _t1, double _t2, double _t14, double _t15, double _t16, double _t40, double _t41, double _t42) {
        double _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        double _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        double _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        UnsafeOpsHolder.U.putDouble(dest, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s3d7cbd42_2(long dest, long src, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return lookAlong_rh_unsafe_mulAdd_s3d7cbd42_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getDouble(src + 48L)), (-UnsafeOpsHolder.U.getDouble(src + 56L)), (-UnsafeOpsHolder.U.getDouble(src + 64L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    public static long lookAlong_lh(long dest, long src, long dir, long up, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dir, up, epsilon);
        Double3x3OpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L, epsilon);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, long dir, long up, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dir, up, epsilon);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dir, up, epsilon);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        return lookAlong_lh_unsafe_fma_s13a9f121_1(dest, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s13a9f121_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13) {
        double _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t11, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        double _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t38, _t13, -(_t39 * _t12));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t37, _self01 * _t46)));
        return lookAlong_lh_unsafe_fma_s13a9f121_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, _t46, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)), java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s13a9f121_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t37, _self11 * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t37, _self21 * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t39, _self01 * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t39, _self11 * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t39, _self21 * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t38, _self01 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t38, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t38, _self21 * _t48)));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        return lookAlong_lh_unsafe_mulAdd_s1044c1e6_1(dest, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s1044c1e6_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13) {
        double _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t11) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t12) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        double _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t38) * (_t13) - (_t39 * _t12));
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t11) + (((_self00) * (_t37) + (_self01 * _t46)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t11) + (((_self10) * (_t37) + (_self11 * _t46)))));
        return lookAlong_lh_unsafe_mulAdd_s1044c1e6_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, _t46, ((_t37) * (_t12) - (_t38 * _t11)), ((_t39) * (_t11) - (_t37 * _t13)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s1044c1e6_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t11) + (((_self20) * (_t37) + (_self21 * _t46)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t13) + (((_self00) * (_t39) + (_self01 * _t47)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t13) + (((_self10) * (_t39) + (_self11 * _t47)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t13) + (((_self20) * (_t39) + (_self21 * _t47)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t12) + (((_self00) * (_t38) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t12) + (((_self10) * (_t38) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t12) + (((_self20) * (_t38) + (_self21 * _t48)))));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, long dir, long up, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dir, up, epsilon);
        Double3x3OpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L, epsilon);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, long dir, long up, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dir, up, epsilon);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dir, up, epsilon);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, long dir, long up, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _t7 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t20 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        return lookAlong_rh_unsafe_fma_s7c0d0f7_2(dest, src, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _upx, _upy, _upz, java.lang.Math.fma(_t20, _t16, _upy), java.lang.Math.fma(_t20, _t14, _upx), java.lang.Math.fma(_t20, _t15, _upz));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void lookAlong_rh_unsafe_fma_s7c0d0f7_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t0, double _t1, double _t2, double _t14, double _t15, double _t16, double _t40, double _t41, double _t42) {
        double _t49 = java.lang.Math.fma(_t41, _t15, -(_t42 * _t16));
        double _t50 = java.lang.Math.fma(_t42, _t14, -(_t40 * _t15));
        double _t51 = java.lang.Math.fma(_t40, _t16, -(_t41 * _t14));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t0, _t14, java.lang.Math.fma(_self00, _t40, _self01 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t1, _t14, java.lang.Math.fma(_self10, _t40, _self11 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, _t14, java.lang.Math.fma(_self20, _t40, _self21 * _t49)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_self00, _t41, _self01 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t1, _t16, java.lang.Math.fma(_self10, _t41, _self11 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t2, _t16, java.lang.Math.fma(_self20, _t41, _self21 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_t0, _t15, java.lang.Math.fma(_self00, _t42, _self01 * _t51)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t1, _t15, java.lang.Math.fma(_self10, _t42, _self11 * _t51)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_t2, _t15, java.lang.Math.fma(_self20, _t42, _self21 * _t51)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s7c0d0f7_2(long dest, long src, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t14, double _t15, double _t16, double _upx, double _upy, double _upz, double _t21, double _t22, double _t23) {
        double _t30 = java.lang.Math.fma(_t21, _t14, -(_t22 * _t16));
        double _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t14));
        double _t32 = java.lang.Math.fma(_t23, _t16, -(_t21 * _t15));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        lookAlong_rh_unsafe_fma_s7c0d0f7_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getDouble(src + 48L)), (-UnsafeOpsHolder.U.getDouble(src + 56L)), (-UnsafeOpsHolder.U.getDouble(src + 64L)), _t14, _t15, _t16, _t40, _t41, _t42);
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, long dir, long up, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _t7 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t14, _t15, _t16;
        if (_t7 != 0.0) {
            _t14 = _dirx * _t9;
            _t15 = _dirz * _t9;
            _t16 = _diry * _t9;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t20 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        return lookAlong_rh_unsafe_mulAdd_s8bb0b544_2(dest, src, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _t14, _t15, _t16, _upx, _upy, _upz, ((_t20) * (_t16) + (_upy)), ((_t20) * (_t14) + (_upx)), ((_t20) * (_t15) + (_upz)));
    }

    /** Part 1 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s8bb0b544_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t0, double _t1, double _t2, double _t14, double _t15, double _t16, double _t40, double _t41, double _t42) {
        double _t49 = ((_t41) * (_t15) - (_t42 * _t16));
        double _t50 = ((_t42) * (_t14) - (_t40 * _t15));
        double _t51 = ((_t40) * (_t16) - (_t41 * _t14));
        UnsafeOpsHolder.U.putDouble(dest, ((_t0) * (_t14) + (((_self00) * (_t40) + (_self01 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t1) * (_t14) + (((_self10) * (_t40) + (_self11 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (_t14) + (((_self20) * (_t40) + (_self21 * _t49)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t0) * (_t16) + (((_self00) * (_t41) + (_self01 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t1) * (_t16) + (((_self10) * (_t41) + (_self11 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t2) * (_t16) + (((_self20) * (_t41) + (_self21 * _t50)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_t0) * (_t15) + (((_self00) * (_t42) + (_self01 * _t51)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t1) * (_t15) + (((_self10) * (_t42) + (_self11 * _t51)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_t2) * (_t15) + (((_self20) * (_t42) + (_self21 * _t51)))));
        return dest;
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s8bb0b544_2(long dest, long src, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t14, double _t15, double _t16, double _upx, double _upy, double _upz, double _t21, double _t22, double _t23) {
        double _t30 = ((_t21) * (_t14) - (_t22 * _t16));
        double _t31 = ((_t22) * (_t15) - (_t23 * _t14));
        double _t32 = ((_t23) * (_t16) - (_t21 * _t15));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return lookAlong_rh_unsafe_mulAdd_s8bb0b544_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, (-UnsafeOpsHolder.U.getDouble(src + 48L)), (-UnsafeOpsHolder.U.getDouble(src + 56L)), (-UnsafeOpsHolder.U.getDouble(src + 64L)), _t14, _t15, _t16, _t40, _t41, _t42);
    }

    public static long makeFromDualQuat_unsafe(long dest, double dqRX, double dqRY, double dqRZ, double dqRW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeFromDualQuat_unsafe_mulAdd(dest, dqRX, dqRY, dqRZ, dqRW);
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = java.lang.Math.fma(-2.0, dqRZ * dqRZ, 1.0);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, _t0, _t6));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * java.lang.Math.fma(dqRX, dqRY, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-2.0, _t3, _sp0 * dqRZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(-2.0, _t2, _sp0 * dqRY));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(-2.0, _t4, _t6));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * java.lang.Math.fma(dqRX, dqRW, _t5));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * java.lang.Math.fma(dqRX, dqRZ, _t3));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(-2.0, dqRX * dqRW, _t5 + _t5));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(-2.0, _t4, java.lang.Math.fma(-2.0, _t0, 1.0)));
        return dest;
    }

    /** {@code makeFromDualQuat_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeFromDualQuat_unsafe_mulAdd(long dest, double dqRX, double dqRY, double dqRZ, double dqRW) {
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = ((1.0) - (2.0) * (dqRZ * dqRZ));
        UnsafeOpsHolder.U.putDouble(dest, ((_t6) - (2.0) * (_t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * ((dqRX) * (dqRY) + (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_sp0 * dqRZ) - (2.0) * (_t3)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_sp0 * dqRY) - (2.0) * (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t6) - (2.0) * (_t4)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * ((dqRX) * (dqRW) + (_t5)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * ((dqRX) * (dqRZ) + (_t3)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t5 + _t5) - (2.0) * (dqRX * dqRW)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((((1.0) - (2.0) * (_t0))) - (2.0) * (_t4)));
        return dest;
    }

    public static long makeRotation_unsafe(long dest, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putDouble(dest, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_t1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, double angle, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationAxis_unsafe_mulAdd(dest, angle, axisX, axisY, axisZ);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = axisX * axisY;
        double _t4 = axisX * axisZ;
        double _t5 = axisY * axisZ;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t2, axisX * axisX, _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(axisZ, _t1, _t2 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, _t4, -(axisY * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t2, _t3, -(axisZ * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t2, axisY * axisY, _t0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(axisX, _t1, _t2 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(axisY, _t1, _t2 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t2, _t5, -(axisX * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_t2, axisZ * axisZ, _t0));
        return dest;
    }

    /** {@code makeRotationAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationAxis_unsafe_mulAdd(long dest, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = axisX * axisY;
        double _t4 = axisX * axisZ;
        double _t5 = axisY * axisZ;
        UnsafeOpsHolder.U.putDouble(dest, ((_t2) * (axisX * axisX) + (_t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((axisZ) * (_t1) + (_t2 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (_t4) - (axisY * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t2) * (_t3) - (axisZ * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t2) * (axisY * axisY) + (_t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((axisX) * (_t1) + (_t2 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((axisY) * (_t1) + (_t2 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t2) * (_t5) - (axisX * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_t2) * (axisZ * axisZ) + (_t0)));
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, long axis, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationAxis_unsafe_mulAdd(dest, axis, angle);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = _axisx * _axisy;
        double _t4 = _axisx * _axisz;
        double _t5 = _axisy * _axisz;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t2, _axisx * _axisx, _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_axisz, _t1, _t2 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, _t4, -(_axisy * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t2, _t3, -(_axisz * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t2, _axisy * _axisy, _t0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_axisx, _t1, _t2 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_axisy, _t1, _t2 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t2, _t5, -(_axisx * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_t2, _axisz * _axisz, _t0));
        return dest;
    }

    /** {@code makeRotationAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationAxis_unsafe_mulAdd(long dest, long axis, double angle) {
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = _axisx * _axisy;
        double _t4 = _axisx * _axisz;
        double _t5 = _axisy * _axisz;
        UnsafeOpsHolder.U.putDouble(dest, ((_t2) * (_axisx * _axisx) + (_t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_axisz) * (_t1) + (_t2 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (_t4) - (_axisy * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t2) * (_t3) - (_axisz * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t2) * (_axisy * _axisy) + (_t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_axisx) * (_t1) + (_t2 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_axisy) * (_t1) + (_t2 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t2) * (_t5) - (_axisx * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_t2) * (_axisz * _axisz) + (_t0)));
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ);
        Double3x3OpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        return makeRotationLookAlong_lh_unsafe_fma_s1ac5f254_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s1ac5f254_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t39) * (_t13) - (_t38 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t12);
        return makeRotationLookAlong_lh_unsafe_mulAdd_s1fb56b27_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s1fb56b27_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t37) * (_t11) - (_t39 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t38) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ);
        Double3x3OpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t13, upY);
        double _t19 = java.lang.Math.fma(_t17, _t12, upX);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        return makeRotationLookAlong_rh_unsafe_fma_s528c9166_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma_s528c9166_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t13) + (upY));
        double _t19 = ((_t17) * (_t12) + (upX));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t38) * (_t11) - (_t39 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t12);
        return makeRotationLookAlong_rh_unsafe_mulAdd_s3a18c539_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s3a18c539_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t39) * (_t12) - (_t37 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t37) * (_t13) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t11);
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dir, up);
        Double3x3OpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dir, up);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dir, up);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_lh_unsafe_fma_s61209f8a_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s61209f8a_1(long dest, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_lh_unsafe_mulAdd_sbd37ae05_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_sbd37ae05_1(long dest, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t39) * (_t13) - (_t38 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t37) * (_t11) - (_t39 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t38) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dir, up);
        Double3x3OpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dir, up);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dir, up);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t19 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_1(long dest, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t13) + (_upy));
        double _t19 = ((_t17) * (_t12) + (_upx));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_rh_unsafe_mulAdd_s24c40fcf_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s24c40fcf_1(long dest, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t38) * (_t11) - (_t39 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t39) * (_t12) - (_t37 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t37) * (_t13) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t11);
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        Double3x3OpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        return makeRotationLookAlong_lh_unsafe_fma_s4464a5b7_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s4464a5b7_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t39) * (_t13) - (_t38 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t12);
        return makeRotationLookAlong_lh_unsafe_mulAdd_s111723f4_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s111723f4_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t37) * (_t11) - (_t39 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t38) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        Double3x3OpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t13, upY);
        double _t19 = java.lang.Math.fma(_t17, _t12, upX);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        return makeRotationLookAlong_rh_unsafe_fma_se775cc5d_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma_se775cc5d_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t13) + (upY));
        double _t19 = ((_t17) * (_t12) + (upX));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t38) * (_t11) - (_t39 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t12);
        return makeRotationLookAlong_rh_unsafe_mulAdd_s9d84302a_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s9d84302a_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t39) * (_t12) - (_t37 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t37) * (_t13) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t11);
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, long dir, long up, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dir, up, epsilon);
        Double3x3OpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, long dir, long up, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dir, up, epsilon);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dir, up, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_lh_unsafe_fma_sd8be2141_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_sd8be2141_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t39, _t13, -(_t38 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t37, _t11, -(_t39 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t38, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_lh_unsafe_mulAdd_s8588a676_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s8588a676_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t39) * (_t13) - (_t38 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t37) * (_t11) - (_t39 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t38) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, long dir, long up, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dir, up, epsilon);
        Double3x3OpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, long dir, long up, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dir, up, epsilon);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dir, up, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t19 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t38, _t11, _t39 * -_t13));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t39, _t12, _t37 * -_t11));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t37, _t13, _t38 * -_t12));
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t11);
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t13) + (_upy));
        double _t19 = ((_t17) * (_t12) + (_upx));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t12) - (_t19 * _t13));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t13) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationLookAlong_rh_unsafe_mulAdd_sa724bb6c_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_sa724bb6c_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t38) * (_t11) - (_t39 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t12);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t39) * (_t12) - (_t37 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -_t13);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t37) * (_t13) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, -_t11);
        return dest;
    }

    public static long makeRotationQuat_unsafe(long dest, double qX, double qY, double qZ, double qW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationQuat_unsafe_mulAdd(dest, qX, qY, qZ, qW);
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, java.lang.Math.fma(qY, qY, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * java.lang.Math.fma(qX, qY, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * java.lang.Math.fma(qX, qZ, -_t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * java.lang.Math.fma(qX, qY, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(-2.0, java.lang.Math.fma(qX, qX, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * java.lang.Math.fma(qX, qW, qY * qZ));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * java.lang.Math.fma(qX, qZ, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * java.lang.Math.fma(qY, qZ, -(qX * qW)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(-2.0, java.lang.Math.fma(qX, qX, qY * qY), 1.0));
        return dest;
    }

    /** {@code makeRotationQuat_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationQuat_unsafe_mulAdd(long dest, double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        UnsafeOpsHolder.U.putDouble(dest, ((1.0) - (2.0) * (((qY) * (qY) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * ((qX) * (qY) + (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * ((qX) * (qZ) - (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * ((qX) * (qY) - (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((1.0) - (2.0) * (((qX) * (qX) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * ((qX) * (qW) + (qY * qZ)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * ((qX) * (qZ) + (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * ((qY) * (qZ) - (qX * qW)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((1.0) - (2.0) * (((qX) * (qX) + (qY * qY)))));
        return dest;
    }

    public static long makeRotationQuat_unsafe(long dest, long q) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationQuat_unsafe_fma(dest, q);
        return makeRotationQuat_unsafe_mulAdd(dest, q);
    }

    /** {@code makeRotationQuat_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationQuat_unsafe_fma(long dest, long q) {
        double _qx = UnsafeOpsHolder.U.getDouble(q);
        double _qy = UnsafeOpsHolder.U.getDouble(q + 8L);
        double _qz = UnsafeOpsHolder.U.getDouble(q + 16L);
        double _qw = UnsafeOpsHolder.U.getDouble(q + 24L);
        double _t0 = _qz * _qz;
        double _t1 = _qz * _qw;
        double _t2 = _qy * _qw;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, java.lang.Math.fma(_qy, _qy, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * java.lang.Math.fma(_qx, _qy, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * java.lang.Math.fma(_qx, _qz, -_t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * java.lang.Math.fma(_qx, _qy, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_qx, _qx, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * java.lang.Math.fma(_qx, _qw, _qy * _qz));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * java.lang.Math.fma(_qx, _qz, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * java.lang.Math.fma(_qy, _qz, -(_qx * _qw)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_qx, _qx, _qy * _qy), 1.0));
        return dest;
    }

    /** {@code makeRotationQuat_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationQuat_unsafe_mulAdd(long dest, long q) {
        double _qx = UnsafeOpsHolder.U.getDouble(q);
        double _qy = UnsafeOpsHolder.U.getDouble(q + 8L);
        double _qz = UnsafeOpsHolder.U.getDouble(q + 16L);
        double _qw = UnsafeOpsHolder.U.getDouble(q + 24L);
        double _t0 = _qz * _qz;
        double _t1 = _qz * _qw;
        double _t2 = _qy * _qw;
        UnsafeOpsHolder.U.putDouble(dest, ((1.0) - (2.0) * (((_qy) * (_qy) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * ((_qx) * (_qy) + (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * ((_qx) * (_qz) - (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * ((_qx) * (_qy) - (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((1.0) - (2.0) * (((_qx) * (_qx) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * ((_qx) * (_qw) + (_qy * _qz)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * ((_qx) * (_qz) + (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * ((_qy) * (_qz) - (_qx * _qw)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((1.0) - (2.0) * (((_qx) * (_qx) + (_qy * _qy)))));
        return dest;
    }

    public static long makeRotationTowards_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationTowards_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_fma(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t39);
        return makeRotationTowards_unsafe_fma_s8a5730a7_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_fma_s8a5730a7_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t38);
        return makeRotationTowards_unsafe_mulAdd_sc37f517e_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd_sc37f517e_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t38) * (_t13) - (_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t37) * (_t11) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t39) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    public static long makeRotationTowards_unsafe(long dest, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_unsafe_fma(dest, dir, up);
        return makeRotationTowards_unsafe_mulAdd(dest, dir, up);
    }

    /** {@code makeRotationTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_fma(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationTowards_unsafe_fma_se4ab2385_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_fma_se4ab2385_1(long dest, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationTowards_unsafe_mulAdd_s1180c334_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd_s1180c334_1(long dest, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t38) * (_t13) - (_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t37) * (_t11) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t39) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    public static long makeRotationTowards_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationTowards_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_fma(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t39);
        return makeRotationTowards_unsafe_fma_s7ef54974_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_fma_s7ef54974_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t38);
        return makeRotationTowards_unsafe_mulAdd_sfa0a4f05_1(dest, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd_sfa0a4f05_1(long dest, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t38) * (_t13) - (_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t37) * (_t11) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t39) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    public static long makeRotationTowards_unsafe(long dest, long dir, long up, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTowards_unsafe_fma(dest, dir, up, epsilon);
        return makeRotationTowards_unsafe_mulAdd(dest, dir, up, epsilon);
    }

    /** {@code makeRotationTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_fma(long dest, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationTowards_unsafe_fma_sf0047bf6_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_fma_sf0047bf6_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t38, _t13, -(_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t37, _t11, -(_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t39, _t12, -(_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    /** {@code makeRotationTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd(long dest, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return makeRotationTowards_unsafe_mulAdd_s9ba10917_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTowards_unsafe_mulAdd_s9ba10917_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, _t37);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t39);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t38);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t38) * (_t13) - (_t39 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t37) * (_t11) - (_t38 * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t39) * (_t12) - (_t37 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t12);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _t13);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t11);
        return dest;
    }

    public static long makeRotationX_unsafe(long dest, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -_t1);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t0);
        return dest;
    }

    public static long makeRotationXYZ_unsafe(long dest, double angleX, double angleY, double angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXYZ_unsafe_mulAdd(dest, angleX, angleY, angleZ);
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t4 * _t3;
        double _t7 = _t3 * _t5;
        UnsafeOpsHolder.U.putDouble(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t6, _t1, _t2 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t4, _t2, -(_t7 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_t2 * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t5, _t1, _t6 * -_t2));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t7, _t2, _t4 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t3);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -(_t4 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationXYZ_unsafe_mulAdd(long dest, double angleX, double angleY, double angleZ) {
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t4 * _t3;
        double _t7 = _t3 * _t5;
        UnsafeOpsHolder.U.putDouble(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t6) * (_t1) + (_t2 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t4) * (_t2) - (_t7 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_t2 * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t5) * (_t1) - (_t6 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t7) * (_t2) + (_t4 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t3);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -(_t4 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t0);
        return dest;
    }

    public static long makeRotationXZY_unsafe(long dest, double angleX, double angleZ, double angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXZY_unsafe_mulAdd(dest, angleX, angleZ, angleY);
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.sinWithCos(angleX);
        double _t6 = _t2 * _t4;
        double _t7 = _t5 * _t2;
        UnsafeOpsHolder.U.putDouble(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t6, _t0, _t5 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t7, _t0, -(_t3 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_t2);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t4 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t5 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t3 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t6, _t3, -(_t5 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_t7, _t3, _t4 * _t0));
        return dest;
    }

    /** {@code makeRotationXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationXZY_unsafe_mulAdd(long dest, double angleX, double angleZ, double angleY) {
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.sinWithCos(angleX);
        double _t6 = _t2 * _t4;
        double _t7 = _t5 * _t2;
        UnsafeOpsHolder.U.putDouble(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t6) * (_t0) + (_t5 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t7) * (_t0) - (_t3 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_t2);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t4 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t5 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t3 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t6) * (_t3) - (_t5 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_t7) * (_t3) + (_t4 * _t0)));
        return dest;
    }

    public static long makeRotationY_unsafe(long dest, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putDouble(dest, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t1);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t0);
        return dest;
    }

    public static long makeRotationYXZ_unsafe(long dest, double angleY, double angleX, double angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYXZ_unsafe_mulAdd(dest, angleY, angleX, angleZ);
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleY);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.cosWithSin(angleY);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t6, _t2, _t3 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t2 * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t7, _t2, -(_t1 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t6, _t4, -(_t2 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t5 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t7, _t4, _t1 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t1 * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t3);
        return dest;
    }

    /** {@code makeRotationYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationYXZ_unsafe_mulAdd(long dest, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleY);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.cosWithSin(angleY);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        UnsafeOpsHolder.U.putDouble(dest, ((_t6) * (_t2) + (_t3 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t2 * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t7) * (_t2) - (_t1 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t6) * (_t4) - (_t2 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t5 * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t7) * (_t4) + (_t1 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _t1 * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t3);
        return dest;
    }

    public static long makeRotationYZX_unsafe(long dest, double angleY, double angleZ, double angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYZX_unsafe_mulAdd(dest, angleY, angleZ, angleX);
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleX);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.sinWithCos(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t4 * _t0;
        double _t7 = _t3 * _t4;
        UnsafeOpsHolder.U.putDouble(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t4);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t3 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t2, _t3, -(_t6 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t5 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_t7, _t5, _t2 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_t6, _t2, _t3 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 56L, -_t2 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_t5, _t0, _t7 * -_t2));
        return dest;
    }

    /** {@code makeRotationYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationYZX_unsafe_mulAdd(long dest, double angleY, double angleZ, double angleX) {
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleX);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.sinWithCos(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t4 * _t0;
        double _t7 = _t3 * _t4;
        UnsafeOpsHolder.U.putDouble(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t4);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t3 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t2) * (_t3) - (_t6 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t5 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_t7) * (_t5) + (_t2 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_t6) * (_t2) + (_t3 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, -_t2 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_t5) * (_t0) - (_t7 * _t2)));
        return dest;
    }

    public static long makeRotationZXY_unsafe(long dest, double angleZ, double angleX, double angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZXY_unsafe_mulAdd(dest, angleZ, angleX, angleY);
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleX);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.sinWithCos(angleY);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t2 * _t3;
        double _t7 = _t2 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t0, _t1, _t6 * -_t4));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t7, _t4, _t3 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t4 * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -(_t3 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t5 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t2);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_t6, _t0, _t4 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t4, _t3, -(_t7 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationZXY_unsafe_mulAdd(long dest, double angleZ, double angleX, double angleY) {
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleX);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.sinWithCos(angleY);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t2 * _t3;
        double _t7 = _t2 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, ((_t0) * (_t1) - (_t6 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t7) * (_t4) + (_t3 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t4 * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -(_t3 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t5 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t2);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_t6) * (_t0) + (_t4 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t4) * (_t3) - (_t7 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t0);
        return dest;
    }

    public static long makeRotationZYX_unsafe(long dest, double angleZ, double angleY, double angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZYX_unsafe_mulAdd(dest, angleZ, angleY, angleX);
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleY);
        double _t3 = Math.sinWithCos(angleX);
        double _t4 = Math.sinWithCos(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        UnsafeOpsHolder.U.putDouble(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t4 * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t2);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t6, _t3, -(_t4 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_t7, _t3, _t5 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t3 * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_t6, _t5, _t3 * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_t7, _t5, -(_t3 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t0);
        return dest;
    }

    /** {@code makeRotationZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationZYX_unsafe_mulAdd(long dest, double angleZ, double angleY, double angleX) {
        double _t0 = Math.cosWithSin(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.sinWithCos(angleY);
        double _t3 = Math.sinWithCos(angleX);
        double _t4 = Math.sinWithCos(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        UnsafeOpsHolder.U.putDouble(dest, _t0 * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t4 * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t2);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t6) * (_t3) - (_t4 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_t7) * (_t3) + (_t5 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _t3 * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_t6) * (_t5) + (_t3 * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_t7) * (_t5) - (_t3 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _t5 * _t0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, double vX, double vY) {
        UnsafeOpsHolder.U.putDouble(dest, vX);
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
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest, _vx);
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
        UnsafeOpsHolder.U.putDouble(dest, s);
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
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
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
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
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
        UnsafeOpsHolder.U.putDouble(dest, _t0_inv + _t0_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _t1_inv + _t1_inv);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, -(left + right) * _t0_inv);
        UnsafeOpsHolder.U.putDouble(dest + 56L, -(bottom + top) * _t1_inv);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long preRotate_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_unsafe_fma(dest, src, angle);
        return preRotate_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code preRotate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotate_unsafe_fma(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self02, _t1, _self12 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    /** {@code preRotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotate_unsafe_mulAdd(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self00) * (_t1) + (_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self01) * (_t0) - (_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self01) * (_t1) + (_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t0) - (_self12 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self02) * (_t1) + (_self12 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, double angle, double pivotX, double pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_unsafe_fma(dest, src, angle, pivotX, pivotY);
        return preRotateAround_unsafe_mulAdd(dest, src, angle, pivotX, pivotY);
    }

    /** {@code preRotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAround_unsafe_fma(long dest, long src, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        double _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self20, _t11, java.lang.Math.fma(_self00, _t1, _self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self21, _t10, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1))));
        return preRotateAround_unsafe_fma_sb37d9cd5_1(dest, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_fma_sb37d9cd5_1(long dest, double _t0, double _t1, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self21, _t11, java.lang.Math.fma(_self01, _t1, _self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self22, _t10, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self02, _t1, _self12 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    /** {@code preRotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAround_unsafe_mulAdd(long dest, long src, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        double _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        UnsafeOpsHolder.U.putDouble(dest, ((_self20) * (_t10) + (((_self00) * (_t0) - (_self10 * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self20) * (_t11) + (((_self00) * (_t1) + (_self10 * _t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self21) * (_t10) + (((_self01) * (_t0) - (_self11 * _t1)))));
        return preRotateAround_unsafe_mulAdd_sd62d2a04_1(dest, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_mulAdd_sd62d2a04_1(long dest, double _t0, double _t1, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self21) * (_t11) + (((_self01) * (_t1) + (_self11 * _t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self22) * (_t10) + (((_self02) * (_t0) - (_self12 * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self22) * (_t11) + (((_self02) * (_t1) + (_self12 * _t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, long pivot, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_unsafe_fma(dest, src, pivot, angle);
        return preRotateAround_unsafe_mulAdd(dest, src, pivot, angle);
    }

    /** {@code preRotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAround_unsafe_fma(long dest, long src, long pivot, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1);
        double _t11 = java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self20, _t11, java.lang.Math.fma(_self00, _t1, _self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        return preRotateAround_unsafe_fma_seabe97b2_1(dest, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_fma_seabe97b2_1(long dest, double _t0, double _t1, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self21, _t10, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self21, _t11, java.lang.Math.fma(_self01, _t1, _self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self22, _t10, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self02, _t1, _self12 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    /** {@code preRotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAround_unsafe_mulAdd(long dest, long src, long pivot, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = ((_pivotx) * (_t9) + (_pivoty * _t1));
        double _t11 = ((_pivoty) * (_t9) - (_pivotx * _t1));
        UnsafeOpsHolder.U.putDouble(dest, ((_self20) * (_t10) + (((_self00) * (_t0) - (_self10 * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self20) * (_t11) + (((_self00) * (_t1) + (_self10 * _t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        return preRotateAround_unsafe_mulAdd_s3294ded7_1(dest, _t0, _t1, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t11);
    }

    /** Piece 2 of {@code preRotateAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preRotateAround_unsafe_mulAdd_s3294ded7_1(long dest, double _t0, double _t1, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self21) * (_t10) + (((_self01) * (_t0) - (_self11 * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self21) * (_t11) + (((_self01) * (_t1) + (_self11 * _t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self22) * (_t10) + (((_self02) * (_t0) - (_self12 * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self22) * (_t11) + (((_self02) * (_t1) + (_self12 * _t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preRotateAxis_unsafe(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_unsafe_fma(dest, src, angle, axisX, axisY, axisZ);
        return preRotateAxis_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ);
    }

    /** {@code preRotateAxis_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAxis_unsafe_fma(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = 1.0 - _t1;
        double _t3 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t7 = axisY * axisZ;
        return preRotateAxis_unsafe_fma_sa20b47db_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_t2, axisX * axisX, _t1), java.lang.Math.fma(_t2, axisY * axisY, _t1), java.lang.Math.fma(_t2, axisZ * axisZ, _t1), java.lang.Math.fma(axisY, _t0, _t2 * _t3), java.lang.Math.fma(axisZ, _t0, _t2 * _t5), java.lang.Math.fma(axisX, _t0, _t2 * _t7), java.lang.Math.fma(_t2, _t5, -(axisZ * _t0)), java.lang.Math.fma(_t2, _t7, -(axisX * _t0)), java.lang.Math.fma(_t2, _t3, -(axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_fma_sa20b47db_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    /** {@code preRotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAxis_unsafe_mulAdd(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = 1.0 - _t1;
        double _t3 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t7 = axisY * axisZ;
        return preRotateAxis_unsafe_mulAdd_sedc0982_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_t2) * (axisX * axisX) + (_t1)), ((_t2) * (axisY * axisY) + (_t1)), ((_t2) * (axisZ * axisZ) + (_t1)), ((axisY) * (_t0) + (_t2 * _t3)), ((axisZ) * (_t0) + (_t2 * _t5)), ((axisX) * (_t0) + (_t2 * _t7)), ((_t2) * (_t5) - (axisZ * _t0)), ((_t2) * (_t7) - (axisX * _t0)), ((_t2) * (_t3) - (axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_mulAdd_sedc0982_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest, ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23)))));
        return dest;
    }

    public static long preRotateAxis_unsafe(long dest, long src, long axis, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAxis_unsafe_fma(dest, src, axis, angle);
        return preRotateAxis_unsafe_mulAdd(dest, src, axis, angle);
    }

    /** {@code preRotateAxis_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAxis_unsafe_fma(long dest, long src, long axis, double angle) {
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = 1.0 - _t1;
        double _t3 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t7 = _axisy * _axisz;
        return preRotateAxis_unsafe_fma_sfee34b8f_1(dest, _t0, _axisx, _axisy, _axisz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t3, _t5, _t7, java.lang.Math.fma(_t2, _axisx * _axisx, _t1), java.lang.Math.fma(_t2, _axisy * _axisy, _t1), java.lang.Math.fma(_t2, _axisz * _axisz, _t1), java.lang.Math.fma(_axisy, _t0, _t2 * _t3), java.lang.Math.fma(_axisz, _t0, _t2 * _t5), java.lang.Math.fma(_axisx, _t0, _t2 * _t7));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_fma_sfee34b8f_1(long dest, double _t0, double _axisx, double _axisy, double _axisz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t2, double _t3, double _t5, double _t7, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = java.lang.Math.fma(_t2, _t5, -(_axisz * _t0));
        double _t25 = java.lang.Math.fma(_t2, _t7, -(_axisx * _t0));
        double _t26 = java.lang.Math.fma(_t2, _t3, -(_axisy * _t0));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self20, _t21, java.lang.Math.fma(_self00, _t18, _self10 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self20, _t25, java.lang.Math.fma(_self00, _t22, _self10 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self20, _t20, java.lang.Math.fma(_self00, _t26, _self10 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self21, _t21, java.lang.Math.fma(_self01, _t18, _self11 * _t24)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self21, _t25, java.lang.Math.fma(_self01, _t22, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self21, _t20, java.lang.Math.fma(_self01, _t26, _self11 * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self02, _t18, _self12 * _t24)));
        return preRotateAxis_unsafe_fma_sfee34b8f_2(dest, _self02, _self12, _self22, _t19, _t20, _t22, _t23, _t25, _t26);
    }

    /** Piece 3 of {@code preRotateAxis_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_fma_sfee34b8f_2(long dest, double _self02, double _self12, double _self22, double _t19, double _t20, double _t22, double _t23, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self22, _t25, java.lang.Math.fma(_self02, _t22, _self12 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    /** {@code preRotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateAxis_unsafe_mulAdd(long dest, long src, long axis, double angle) {
        double _t0 = Math.sinWithCos(angle);
        double _t1 = Math.cosWithSin(angle);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = 1.0 - _t1;
        double _t3 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t7 = _axisy * _axisz;
        return preRotateAxis_unsafe_mulAdd_sb5a4803c_1(dest, _t0, _axisx, _axisy, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t3, _t7, ((_t2) * (_axisx * _axisx) + (_t1)), ((_t2) * (_axisy * _axisy) + (_t1)), ((_t2) * (_axisz * _axisz) + (_t1)), ((_axisy) * (_t0) + (_t2 * _t3)), ((_axisz) * (_t0) + (_t2 * _t5)), ((_axisx) * (_t0) + (_t2 * _t7)), ((_t2) * (_t5) - (_axisz * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preRotateAxis_unsafe_mulAdd_sb5a4803c_1(long dest, double _t0, double _axisx, double _axisy, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t2, double _t3, double _t7, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        double _t25 = ((_t2) * (_t7) - (_axisx * _t0));
        double _t26 = ((_t2) * (_t3) - (_axisy * _t0));
        UnsafeOpsHolder.U.putDouble(dest, ((_self20) * (_t21) + (((_self00) * (_t18) + (_self10 * _t24)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self20) * (_t25) + (((_self00) * (_t22) + (_self10 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self20) * (_t20) + (((_self00) * (_t26) + (_self10 * _t23)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self21) * (_t21) + (((_self01) * (_t18) + (_self11 * _t24)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self21) * (_t25) + (((_self01) * (_t22) + (_self11 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self21) * (_t20) + (((_self01) * (_t26) + (_self11 * _t23)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self22) * (_t21) + (((_self02) * (_t18) + (_self12 * _t24)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self22) * (_t25) + (((_self02) * (_t22) + (_self12 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t20) + (((_self02) * (_t26) + (_self12 * _t23)))));
        return dest;
    }

    public static long preRotateX_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateX_unsafe_fma(dest, src, angle);
        return preRotateX_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code preRotateX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateX_unsafe_fma(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _t0, -(_self20 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self10, _t1, _self20 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self11, _t0, -(_self21 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self11, _t1, _self21 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t0, -(_self22 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self12, _t1, _self22 * _t0));
        return dest;
    }

    /** {@code preRotateX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateX_unsafe_mulAdd(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_t0) - (_self20 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self10) * (_t1) + (_self20 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self11) * (_t0) - (_self21 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self11) * (_t1) + (_self21 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t0) - (_self22 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self12) * (_t1) + (_self22 * _t0)));
        return dest;
    }

    public static long preRotateY_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preRotateY_unsafe_fma(dest, src, angle);
        return preRotateY_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code preRotateY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateY_unsafe_fma(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, _self20 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self20, _t0, -(_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self01, _t0, _self21 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self21, _t0, -(_self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t0, _self22 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t0, -(_self02 * _t1)));
        return dest;
    }

    /** {@code preRotateY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateY_unsafe_mulAdd(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) + (_self20 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self20) * (_t0) - (_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self01) * (_t0) + (_self21 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self21) * (_t0) - (_self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t0) + (_self22 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t0) - (_self02 * _t1)));
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
        double _vx = UnsafeOpsHolder.U.getDouble(v);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_fma(dest, src, s, pivotX, pivotY);
        return preScaleAround_unsafe_mulAdd(dest, src, s, pivotX, pivotY);
    }

    /** {@code preScaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_fma(long dest, long src, double s, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(s, _self00, _self20 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(s, _self10, _self20 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(s, _self01, _self21 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(s, _self11, _self21 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(s, _self02, _self22 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(s, _self12, _self22 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, double s, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        UnsafeOpsHolder.U.putDouble(dest, ((s) * (_self00) + (_self20 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((s) * (_self10) + (_self20 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((s) * (_self01) + (_self21 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((s) * (_self11) + (_self21 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((s) * (_self02) + (_self22 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((s) * (_self12) + (_self22 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long pivot, double s) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_fma(dest, src, pivot, s);
        return preScaleAround_unsafe_mulAdd(dest, src, pivot, s);
    }

    /** {@code preScaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_fma(long dest, long src, long pivot, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0 = 1.0 - s;
        double _t1 = UnsafeOpsHolder.U.getDouble(pivot) * _t0;
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * _t0;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(s, _self00, _self20 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(s, _self10, _self20 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(s, _self01, _self21 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(s, _self11, _self21 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        return preScaleAround_unsafe_fma_s8b31814b_1(dest, s, _self02, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code preScaleAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_fma_s8b31814b_1(long dest, double s, double _self02, double _self12, double _self22, double _t1, double _t2) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(s, _self02, _self22 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(s, _self12, _self22 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, long pivot, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0 = 1.0 - s;
        double _t1 = UnsafeOpsHolder.U.getDouble(pivot) * _t0;
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * _t0;
        UnsafeOpsHolder.U.putDouble(dest, ((s) * (_self00) + (_self20 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((s) * (_self10) + (_self20 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((s) * (_self01) + (_self21 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((s) * (_self11) + (_self21 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        return preScaleAround_unsafe_mulAdd_s77633410_1(dest, s, _self02, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code preScaleAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_mulAdd_s77633410_1(long dest, double s, double _self02, double _self12, double _self22, double _t1, double _t2) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((s) * (_self02) + (_self22 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((s) * (_self12) + (_self22 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_fma(dest, src, sX, sY, pivotX, pivotY);
        return preScaleAround_unsafe_mulAdd(dest, src, sX, sY, pivotX, pivotY);
    }

    /** {@code preScaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_fma(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(sX, _self00, _self20 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(sY, _self10, _self20 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(sX, _self01, _self21 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(sY, _self11, _self21 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(sX, _self02, _self22 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(sY, _self12, _self22 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        UnsafeOpsHolder.U.putDouble(dest, ((sX) * (_self00) + (_self20 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((sY) * (_self10) + (_self20 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((sX) * (_self01) + (_self21 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((sY) * (_self11) + (_self21 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((sX) * (_self02) + (_self22 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((sY) * (_self12) + (_self22 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long s, long pivot) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_unsafe_fma(dest, src, s, pivot);
        return preScaleAround_unsafe_mulAdd(dest, src, s, pivot);
    }

    /** {@code preScaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_fma(long dest, long src, long s, long pivot) {
        double _sx = UnsafeOpsHolder.U.getDouble(s);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot) * (1.0 - _sx);
        double _t3 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * (1.0 - _sy);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_sx, _self00, _self20 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_sy, _self10, _self20 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_sx, _self01, _self21 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_sy, _self11, _self21 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        return preScaleAround_unsafe_fma_s7c75a1cc_1(dest, _sx, _sy, _self02, _self12, _self22, _t2, _t3);
    }

    /** Piece 2 of {@code preScaleAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_fma_s7c75a1cc_1(long dest, double _sx, double _sy, double _self02, double _self12, double _self22, double _t2, double _t3) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_sx, _self02, _self22 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_sy, _self12, _self22 * _t3));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    /** {@code preScaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preScaleAround_unsafe_mulAdd(long dest, long src, long s, long pivot) {
        double _sx = UnsafeOpsHolder.U.getDouble(s);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot) * (1.0 - _sx);
        double _t3 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * (1.0 - _sy);
        UnsafeOpsHolder.U.putDouble(dest, ((_sx) * (_self00) + (_self20 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_sy) * (_self10) + (_self20 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_sx) * (_self01) + (_self21 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_sy) * (_self11) + (_self21 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        return preScaleAround_unsafe_mulAdd_sf66134d7_1(dest, _sx, _sy, _self02, _self12, _self22, _t2, _t3);
    }

    /** Piece 2 of {@code preScaleAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preScaleAround_unsafe_mulAdd_sf66134d7_1(long dest, double _sx, double _sy, double _self02, double _self12, double _self22, double _t2, double _t3) {
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_sx) * (_self02) + (_self22 * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_sy) * (_self12) + (_self22 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
                double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
                double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
                UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, java.lang.Math.fma(_eself2, vX, _eself0));
                UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, java.lang.Math.fma(_eself2, vY, _eself1));
                UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            }
            return dest;
        } else {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 3;
                double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
                double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
                double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
                UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, ((_eself2) * (vX) + (_eself0)));
                UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, ((_eself2) * (vY) + (_eself1)));
                UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
            }
            return dest;
        }
    }

    public static long preTranslate_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preTranslate_unsafe_mulAdd(dest, src, v);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, java.lang.Math.fma(_eself2, _vx, _eself0));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, java.lang.Math.fma(_eself2, _vy, _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    /** {@code preTranslate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preTranslate_unsafe_mulAdd(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eself2 = UnsafeOpsHolder.U.getDouble(src + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, ((_eself2) * (_vx) + (_eself0)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, ((_eself2) * (_vy) + (_eself1)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotate_unsafe_fma(dest, src, angle);
        return rotate_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code rotate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_fma(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    /** {@code rotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_mulAdd(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) + (_self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_t0) + (_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self20) * (_t0) + (_self21 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self01) * (_t0) - (_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self11) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self21) * (_t0) - (_self20 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22);
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, double angle, double pivotX, double pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_unsafe_fma(dest, src, angle, pivotX, pivotY);
        return rotateAround_unsafe_mulAdd(dest, src, angle, pivotX, pivotY);
    }

    /** {@code rotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_fma(long dest, long src, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        return rotateAround_unsafe_fma_s1a448398_1(dest, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(pivotX, _t9, pivotY * _t1), java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_fma_s1a448398_1(long dest, double _t0, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self21, _t11, _self22)));
        return dest;
    }

    /** {@code rotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_mulAdd(long dest, long src, double angle, double pivotX, double pivotY) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) + (_self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_t0) + (_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self20) * (_t0) + (_self21 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self01) * (_t0) - (_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self11) * (_t0) - (_self10 * _t1)));
        return rotateAround_unsafe_mulAdd_s5f2cf13_1(dest, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((pivotX) * (_t9) + (pivotY * _t1)), ((pivotY) * (_t9) - (pivotX * _t1)));
    }

    /** Piece 2 of {@code rotateAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_mulAdd_s5f2cf13_1(long dest, double _t0, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self21) * (_t0) - (_self20 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self20) * (_t10) + (((_self21) * (_t11) + (_self22)))));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long pivot, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_unsafe_fma(dest, src, pivot, angle);
        return rotateAround_unsafe_mulAdd(dest, src, pivot, angle);
    }

    /** {@code rotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_fma(long dest, long src, long pivot, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self20, _t0, _self21 * _t1));
        return rotateAround_unsafe_fma_s2dc3d2bb_1(dest, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1), java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_fma_s2dc3d2bb_1(long dest, double _t0, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self21, _t0, -(_self20 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self20, _t10, java.lang.Math.fma(_self21, _t11, _self22)));
        return dest;
    }

    /** {@code rotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_mulAdd(long dest, long src, long pivot, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) + (_self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_t0) + (_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self20) * (_t0) + (_self21 * _t1)));
        return rotateAround_unsafe_mulAdd_sc89c27f4_1(dest, _t0, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_pivotx) * (_t9) + (_pivoty * _t1)), ((_pivoty) * (_t9) - (_pivotx * _t1)));
    }

    /** Piece 2 of {@code rotateAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateAround_unsafe_mulAdd_sc89c27f4_1(long dest, double _t0, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self01) * (_t0) - (_self00 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self11) * (_t0) - (_self10 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self21) * (_t0) - (_self20 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self20) * (_t10) + (((_self21) * (_t11) + (_self22)))));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_fma(dest, src, angle, axisX, axisY, axisZ);
        return rotateAxis_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ);
    }

    /** {@code rotateAxis_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_fma(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = 1.0 - _t0;
        double _t3 = axisX * axisZ;
        double _t6 = axisX * axisY;
        double _t7 = axisY * axisZ;
        return rotateAxis_unsafe_fma_sc14cface_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, java.lang.Math.fma(_t2, axisX * axisX, _t0), java.lang.Math.fma(_t2, axisY * axisY, _t0), java.lang.Math.fma(_t2, axisZ * axisZ, _t0), java.lang.Math.fma(axisZ, _t1, _t2 * _t6), java.lang.Math.fma(axisX, _t1, _t2 * _t7), java.lang.Math.fma(axisY, _t1, _t2 * _t3), java.lang.Math.fma(_t2, _t3, -(axisY * _t1)), java.lang.Math.fma(_t2, _t6, -(axisZ * _t1)), java.lang.Math.fma(_t2, _t7, -(axisX * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_fma_sc14cface_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = 1.0 - _t0;
        double _t3 = axisX * axisZ;
        double _t6 = axisX * axisY;
        double _t7 = axisY * axisZ;
        return rotateAxis_unsafe_mulAdd_s23863f49_1(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, ((_t2) * (axisX * axisX) + (_t0)), ((_t2) * (axisY * axisY) + (_t0)), ((_t2) * (axisZ * axisZ) + (_t0)), ((axisZ) * (_t1) + (_t2 * _t6)), ((axisX) * (_t1) + (_t2 * _t7)), ((axisY) * (_t1) + (_t2 * _t3)), ((_t2) * (_t3) - (axisY * _t1)), ((_t2) * (_t6) - (axisZ * _t1)), ((_t2) * (_t7) - (axisX * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_mulAdd_s23863f49_1(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26)))));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_fma(dest, src, axis, angle);
        return rotateAxis_unsafe_mulAdd(dest, src, axis, angle);
    }

    /** {@code rotateAxis_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_fma(long dest, long src, long axis, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = 1.0 - _t0;
        double _t3 = _axisx * _axisz;
        double _t6 = _axisx * _axisy;
        double _t7 = _axisy * _axisz;
        return rotateAxis_unsafe_fma_s17f9f718_1(dest, _t1, _axisx, _axisy, _axisz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t3, _t6, _t7, java.lang.Math.fma(_t2, _axisx * _axisx, _t0), java.lang.Math.fma(_t2, _axisy * _axisy, _t0), java.lang.Math.fma(_t2, _axisz * _axisz, _t0), java.lang.Math.fma(_axisz, _t1, _t2 * _t6), java.lang.Math.fma(_axisx, _t1, _t2 * _t7), java.lang.Math.fma(_axisy, _t1, _t2 * _t3));
    }

    /** Piece 2 of {@code rotateAxis_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_fma_s17f9f718_1(long dest, double _t1, double _axisx, double _axisy, double _axisz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t2, double _t3, double _t6, double _t7, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = java.lang.Math.fma(_t2, _t3, -(_axisy * _t1));
        double _t25 = java.lang.Math.fma(_t2, _t6, -(_axisz * _t1));
        double _t26 = java.lang.Math.fma(_t2, _t7, -(_axisx * _t1));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t24, java.lang.Math.fma(_self00, _t18, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t24, java.lang.Math.fma(_self10, _t18, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t24, java.lang.Math.fma(_self20, _t18, _self21 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t22, java.lang.Math.fma(_self00, _t25, _self01 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t22, java.lang.Math.fma(_self10, _t25, _self11 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t22, java.lang.Math.fma(_self20, _t25, _self21 * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t23, _self01 * _t26)));
        return rotateAxis_unsafe_fma_s17f9f718_2(dest, _self10, _self20, _self11, _self21, _self12, _self22, _t20, _t23, _t26);
    }

    /** Piece 3 of {@code rotateAxis_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_fma_s17f9f718_2(long dest, double _self10, double _self20, double _self11, double _self21, double _self12, double _self22, double _t20, double _t23, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t23, _self11 * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, long axis, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t2 = 1.0 - _t0;
        double _t3 = _axisx * _axisz;
        double _t6 = _axisx * _axisy;
        double _t7 = _axisy * _axisz;
        return rotateAxis_unsafe_mulAdd_s8dac881_1(dest, _t1, _axisx, _axisz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t6, _t7, ((_t2) * (_axisx * _axisx) + (_t0)), ((_t2) * (_axisy * _axisy) + (_t0)), ((_t2) * (_axisz * _axisz) + (_t0)), ((_axisz) * (_t1) + (_t2 * _t6)), ((_axisx) * (_t1) + (_t2 * _t7)), ((_axisy) * (_t1) + (_t2 * _t3)), ((_t2) * (_t3) - (_axisy * _t1)));
    }

    /** Piece 2 of {@code rotateAxis_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateAxis_unsafe_mulAdd_s8dac881_1(long dest, double _t1, double _axisx, double _axisz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t2, double _t6, double _t7, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        double _t25 = ((_t2) * (_t6) - (_axisz * _t1));
        double _t26 = ((_t2) * (_t7) - (_axisx * _t1));
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t24) + (((_self00) * (_t18) + (_self01 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t24) + (((_self10) * (_t18) + (_self11 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t24) + (((_self20) * (_t18) + (_self21 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t22) + (((_self00) * (_t25) + (_self01 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t22) + (((_self10) * (_t25) + (_self11 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t22) + (((_self20) * (_t25) + (_self21 * _t19)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t20) + (((_self00) * (_t23) + (_self01 * _t26)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t20) + (((_self10) * (_t23) + (_self11 * _t26)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t20) + (((_self20) * (_t23) + (_self21 * _t26)))));
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        return rotateTowards_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        return rotateTowards_unsafe_fma_s69b37eea_1(dest, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t18, _t20, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)));
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s69b37eea_1(long dest, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t18, double _t20, double _t27, double _t28) {
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t39, _t12, -(_t38 * _t13));
        double _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        double _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        return rotateTowards_unsafe_fma_s69b37eea_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s69b37eea_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        return rotateTowards_unsafe_mulAdd_s4f71c3bd_1(dest, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t12)), ((_t19) * (_t11) - (_t20 * _t13)), ((_t20) * (_t12) - (_t18 * _t11)));
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s4f71c3bd_1(long dest, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        double _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        double _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        return rotateTowards_unsafe_mulAdd_s4f71c3bd_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s4f71c3bd_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, dir, up);
        return rotateTowards_unsafe_mulAdd(dest, src, dir, up);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        return rotateTowards_unsafe_fma_s1a0a8f90_1(dest, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s1a0a8f90_1(long dest, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13) {
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        return rotateTowards_unsafe_fma_s1a0a8f90_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)), java.lang.Math.fma(_t37, _t13, -(_t39 * _t11)), java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s1a0a8f90_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        return rotateTowards_unsafe_mulAdd_s601ef8e3_1(dest, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s601ef8e3_1(long dest, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13) {
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        return rotateTowards_unsafe_mulAdd_s601ef8e3_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, ((_t39) * (_t12) - (_t38 * _t13)), ((_t37) * (_t13) - (_t39 * _t11)), ((_t38) * (_t11) - (_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s601ef8e3_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return rotateTowards_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, upX);
        double _t19 = java.lang.Math.fma(_t17, _t13, upY);
        double _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        return rotateTowards_unsafe_fma_s5598d921_1(dest, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t18, _t20, java.lang.Math.fma(_t18, _t13, -(_t19 * _t12)), java.lang.Math.fma(_t19, _t11, -(_t20 * _t13)));
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s5598d921_1(long dest, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t18, double _t20, double _t27, double _t28) {
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = java.lang.Math.fma(_t39, _t12, -(_t38 * _t13));
        double _t47 = java.lang.Math.fma(_t37, _t13, -(_t39 * _t11));
        double _t48 = java.lang.Math.fma(_t38, _t11, -(_t37 * _t12));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        return rotateTowards_unsafe_fma_s5598d921_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s5598d921_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t18 = ((_t17) * (_t12) + (upX));
        double _t19 = ((_t17) * (_t13) + (upY));
        double _t20 = ((_t17) * (_t11) + (upZ));
        return rotateTowards_unsafe_mulAdd_s9241c3be_1(dest, upX, upY, upZ, epsilon, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, ((_t18) * (_t13) - (_t19 * _t12)), ((_t19) * (_t11) - (_t20 * _t13)), ((_t20) * (_t12) - (_t18 * _t11)));
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s9241c3be_1(long dest, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = ((_t39) * (_t12) - (_t38 * _t13));
        double _t47 = ((_t37) * (_t13) - (_t39 * _t11));
        double _t48 = ((_t38) * (_t11) - (_t37 * _t12));
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        return rotateTowards_unsafe_mulAdd_s9241c3be_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s9241c3be_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, long dir, long up, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, dir, up, epsilon);
        return rotateTowards_unsafe_mulAdd(dest, src, dir, up, epsilon);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        return rotateTowards_unsafe_fma_s322c2cb3_1(dest, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s322c2cb3_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13) {
        double _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        double _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        double _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        double _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t37, java.lang.Math.fma(_self00, _t38, _self01 * _t39)));
        return rotateTowards_unsafe_fma_s322c2cb3_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13)), java.lang.Math.fma(_t37, _t13, -(_t39 * _t11)), java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s322c2cb3_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t37, java.lang.Math.fma(_self10, _t38, _self11 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t37, java.lang.Math.fma(_self20, _t38, _self21 * _t39)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t46, java.lang.Math.fma(_self00, _t47, _self01 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t46, java.lang.Math.fma(_self10, _t47, _self11 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t46, java.lang.Math.fma(_self20, _t47, _self21 * _t48)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self00, _t12, _self01 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self10, _t12, _self11 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        return rotateTowards_unsafe_mulAdd_s46a61b30_1(dest, epsilon, _upx, _upy, _upz, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s46a61b30_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13) {
        double _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t18 = ((_t17) * (_t12) + (_upx));
        double _t19 = ((_t17) * (_t13) + (_upy));
        double _t20 = ((_t17) * (_t11) + (_upz));
        double _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        double _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        double _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        double _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t37) + (((_self00) * (_t38) + (_self01 * _t39)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t37) + (((_self10) * (_t38) + (_self11 * _t39)))));
        return rotateTowards_unsafe_mulAdd_s46a61b30_2(dest, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, ((_t39) * (_t12) - (_t38 * _t13)), ((_t37) * (_t13) - (_t39 * _t11)), ((_t38) * (_t11) - (_t37 * _t12)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s46a61b30_2(long dest, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t37) + (((_self20) * (_t38) + (_self21 * _t39)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t46) + (((_self00) * (_t47) + (_self01 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t46) + (((_self10) * (_t47) + (_self11 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t46) + (((_self20) * (_t47) + (_self21 * _t48)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t11) + (((_self00) * (_t12) + (_self01 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t11) + (((_self10) * (_t12) + (_self11 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t11) + (((_self20) * (_t12) + (_self21 * _t13)))));
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateX_unsafe_fma(dest, src, angle);
        return rotateX_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code rotateX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateX_unsafe_fma(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self01, _t0, _self02 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self11, _t0, _self12 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self21, _t0, _self22 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t0, -(_self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t0, -(_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t0, -(_self21 * _t1)));
        return dest;
    }

    /** {@code rotateX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateX_unsafe_mulAdd(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self01) * (_t0) + (_self02 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self11) * (_t0) + (_self12 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self21) * (_t0) + (_self22 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t0) - (_self01 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t0) - (_self11 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t0) - (_self21 * _t1)));
        return dest;
    }

    public static long rotateX180_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXYZ_unsafe_fma(dest, src, angleX, angleY, angleZ);
        return rotateXYZ_unsafe_mulAdd(dest, src, angleX, angleY, angleZ);
    }

    /** {@code rotateXYZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXYZ_unsafe_fma(long dest, long src, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleZ);
        double _t2 = Math.sinWithCos(angleY);
        double _t3 = Math.cosWithSin(angleX);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t2 * _t3;
        double _t7 = _t5 * _t4;
        double _t8 = _t0 * _t2;
        double _t18 = java.lang.Math.fma(_t8, _t4, _t1 * _t3);
        double _t20 = java.lang.Math.fma(_t0, _t1, -(_t6 * _t4));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t7, _self01 * _t18)));
        return rotateXYZ_unsafe_fma_sd7cb3c3a_1(dest, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t1 * _t5, _t3 * _t5, _t0 * _t5, _t18, java.lang.Math.fma(_t6, _t1, _t0 * _t4), _t20, java.lang.Math.fma(_t3, _t4, -(_t8 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_fma_sd7cb3c3a_1(long dest, double _t2, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t7, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t7, _self21 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self01, _t21, -(_self00 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self11, _t21, -(_self10 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self21, _t21, -(_self20 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t2, -(_self01 * _t13))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t2, -(_self11 * _t13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t2, -(_self21 * _t13))));
        return dest;
    }

    /** {@code rotateXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXYZ_unsafe_mulAdd(long dest, long src, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleZ);
        double _t2 = Math.sinWithCos(angleY);
        double _t3 = Math.cosWithSin(angleX);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t2 * _t3;
        double _t7 = _t5 * _t4;
        double _t8 = _t0 * _t2;
        double _t18 = ((_t8) * (_t4) + (_t1 * _t3));
        double _t20 = ((_t0) * (_t1) - (_t6 * _t4));
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t20) + (((_self00) * (_t7) + (_self01 * _t18)))));
        return rotateXYZ_unsafe_mulAdd_s47b2086b_1(dest, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t1 * _t5, _t3 * _t5, _t0 * _t5, _t18, ((_t6) * (_t1) + (_t0 * _t4)), _t20, ((_t3) * (_t4) - (_t8 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_mulAdd_s47b2086b_1(long dest, double _t2, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t20) + (((_self10) * (_t7) + (_self11 * _t18)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t20) + (((_self20) * (_t7) + (_self21 * _t18)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t19) + (((_self01) * (_t21) - (_self00 * _t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t19) + (((_self11) * (_t21) - (_self10 * _t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t19) + (((_self21) * (_t21) - (_self20 * _t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t12) + (((_self00) * (_t2) - (_self01 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t12) + (((_self10) * (_t2) - (_self11 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t12) + (((_self20) * (_t2) - (_self21 * _t13)))));
        return dest;
    }

    public static long rotateXZY_unsafe(long dest, long src, double angleX, double angleZ, double angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXZY_unsafe_fma(dest, src, angleX, angleZ, angleY);
        return rotateXZY_unsafe_mulAdd(dest, src, angleX, angleZ, angleY);
    }

    /** {@code rotateXZY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXZY_unsafe_fma(long dest, long src, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleZ);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.cosWithSin(angleZ);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t0 * _t1;
        double _t8 = _t2 * _t5;
        double _t9 = _t1 * _t4;
        double _t18 = java.lang.Math.fma(_t9, _t2, _t0 * _t3);
        double _t20 = java.lang.Math.fma(_t6, _t2, -(_t3 * _t4));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t8, _self01 * _t18)));
        return rotateXZY_unsafe_fma_sa958745e_1(dest, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t8, _t0 * _t5, _t4 * _t5, _t3 * _t5, _t18, java.lang.Math.fma(_t6, _t3, _t4 * _t2), _t20, java.lang.Math.fma(_t9, _t3, -(_t0 * _t2)));
    }

    /** Piece 2 of {@code rotateXZY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_fma_sa958745e_1(long dest, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t8, double _t11, double _t12, double _t14, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t8, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t8, _self21 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t11, java.lang.Math.fma(_self01, _t12, -(_self00 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t11, java.lang.Math.fma(_self11, _t12, -(_self10 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t11, java.lang.Math.fma(_self21, _t12, -(_self20 * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t14, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t14, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t14, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXZY_unsafe_mulAdd(long dest, long src, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.sinWithCos(angleZ);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.cosWithSin(angleZ);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t0 * _t1;
        double _t8 = _t2 * _t5;
        double _t9 = _t1 * _t4;
        double _t18 = ((_t9) * (_t2) + (_t0 * _t3));
        double _t20 = ((_t6) * (_t2) - (_t3 * _t4));
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t20) + (((_self00) * (_t8) + (_self01 * _t18)))));
        return rotateXZY_unsafe_mulAdd_sf480cfa7_1(dest, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t8, _t0 * _t5, _t4 * _t5, _t3 * _t5, _t18, ((_t6) * (_t3) + (_t4 * _t2)), _t20, ((_t9) * (_t3) - (_t0 * _t2)));
    }

    /** Piece 2 of {@code rotateXZY_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_mulAdd_sf480cfa7_1(long dest, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t8, double _t11, double _t12, double _t14, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t20) + (((_self10) * (_t8) + (_self11 * _t18)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t20) + (((_self20) * (_t8) + (_self21 * _t18)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t11) + (((_self01) * (_t12) - (_self00 * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t11) + (((_self11) * (_t12) - (_self10 * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t11) + (((_self21) * (_t12) - (_self20 * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t19) + (((_self00) * (_t14) + (_self01 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t19) + (((_self10) * (_t14) + (_self11 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t19) + (((_self20) * (_t14) + (_self21 * _t21)))));
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateY_unsafe_fma(dest, src, angle);
        return rotateY_unsafe_mulAdd(dest, src, angle);
    }

    /** {@code rotateY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateY_unsafe_fma(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _t0, -(_self02 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _t0, -(_self12 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self20, _t0, -(_self22 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self00, _t1, _self02 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self10, _t1, _self12 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self20, _t1, _self22 * _t0));
        return dest;
    }

    /** {@code rotateY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateY_unsafe_mulAdd(long dest, long src, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_t0) - (_self02 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_t0) - (_self12 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self20) * (_t0) - (_self22 * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self00) * (_t1) + (_self02 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self10) * (_t1) + (_self12 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self20) * (_t1) + (_self22 * _t0)));
        return dest;
    }

    public static long rotateY180_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, -_self00);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self02);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, -_self02);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_unsafe_fma(dest, src, angleY, angleX, angleZ);
        return rotateYXZ_unsafe_mulAdd(dest, src, angleY, angleX, angleZ);
    }

    /** {@code rotateYXZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYXZ_unsafe_fma(long dest, long src, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.cosWithSin(angleY);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t2 * _t5;
        double _t18 = java.lang.Math.fma(_t8, _t2, _t1 * _t4);
        double _t20 = java.lang.Math.fma(_t6, _t2, -(_t3 * _t4));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _t20, java.lang.Math.fma(_self00, _t18, _self01 * _t10)));
        return rotateYXZ_unsafe_fma_s9e7c9846_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t5 * _t4, _t5 * _t1, _t3 * _t5, _t18, java.lang.Math.fma(_t6, _t4, _t3 * _t2), _t20, java.lang.Math.fma(_t8, _t4, -(_t2 * _t1)));
    }

    /** Piece 2 of {@code rotateYXZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_fma_s9e7c9846_1(long dest, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t13, double _t14, double _t15, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _t20, java.lang.Math.fma(_self10, _t18, _self11 * _t10)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _t20, java.lang.Math.fma(_self20, _t18, _self21 * _t10)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t19, java.lang.Math.fma(_self00, _t21, _self01 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t19, java.lang.Math.fma(_self10, _t21, _self11 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t19, java.lang.Math.fma(_self20, _t21, _self21 * _t13)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t14, java.lang.Math.fma(_self00, _t15, -(_self01 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t14, java.lang.Math.fma(_self10, _t15, -(_self11 * _t0))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t14, java.lang.Math.fma(_self20, _t15, -(_self21 * _t0))));
        return dest;
    }

    /** {@code rotateYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYXZ_unsafe_mulAdd(long dest, long src, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sinWithCos(angleX);
        double _t1 = Math.cosWithSin(angleY);
        double _t2 = Math.sinWithCos(angleZ);
        double _t3 = Math.sinWithCos(angleY);
        double _t4 = Math.cosWithSin(angleZ);
        double _t5 = Math.cosWithSin(angleX);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t2 * _t5;
        double _t18 = ((_t8) * (_t2) + (_t1 * _t4));
        double _t20 = ((_t6) * (_t2) - (_t3 * _t4));
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_t20) + (((_self00) * (_t18) + (_self01 * _t10)))));
        return rotateYXZ_unsafe_mulAdd_s88dbf03b_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t5 * _t4, _t5 * _t1, _t3 * _t5, _t18, ((_t6) * (_t4) + (_t3 * _t2)), _t20, ((_t8) * (_t4) - (_t2 * _t1)));
    }

    /** Piece 2 of {@code rotateYXZ_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_mulAdd_s88dbf03b_1(long dest, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t13, double _t14, double _t15, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_t20) + (((_self10) * (_t18) + (_self11 * _t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_t20) + (((_self20) * (_t18) + (_self21 * _t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t19) + (((_self00) * (_t21) + (_self01 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t19) + (((_self10) * (_t21) + (_self11 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t19) + (((_self20) * (_t21) + (_self21 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t14) + (((_self00) * (_t15) - (_self01 * _t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t14) + (((_self10) * (_t15) - (_self11 * _t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t14) + (((_self20) * (_t15) - (_self21 * _t0)))));
        return dest;
    }

    public static long rotateYZX_unsafe(long dest, long src, double angleY, double angleZ, double angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYZX_unsafe_fma(dest, src, angleY, angleZ, angleX);
        return rotateYZX_unsafe_mulAdd(dest, src, angleY, angleZ, angleX);
    }

    /** {@code rotateYZX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYZX_unsafe_fma(long dest, long src, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.sinWithCos(angleX);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t0 * _t1;
        double _t7 = _t2 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t3 * _t2;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, -_t6, java.lang.Math.fma(_self00, _t7, _self01 * _t3)));
        return rotateYZX_unsafe_fma_sed0e3746_1(dest, _t3, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, java.lang.Math.fma(_t8, _t4, _t5 * _t2), java.lang.Math.fma(_t10, _t5, _t0 * _t4), java.lang.Math.fma(_t5, _t0, -(_t10 * _t4)), java.lang.Math.fma(_t4, _t2, -(_t8 * _t5)));
    }

    /** Piece 2 of {@code rotateYZX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_fma_sed0e3746_1(long dest, double _t3, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t7, double _t11, double _t13, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, -_t6, java.lang.Math.fma(_self10, _t7, _self11 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, -_t6, java.lang.Math.fma(_self20, _t7, _self21 * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t18, java.lang.Math.fma(_self00, _t20, _self01 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t18, java.lang.Math.fma(_self10, _t20, _self11 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t18, java.lang.Math.fma(_self20, _t20, _self21 * _t11)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t21, java.lang.Math.fma(_self00, _t19, -(_self01 * _t13))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t21, java.lang.Math.fma(_self10, _t19, -(_self11 * _t13))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t21, java.lang.Math.fma(_self20, _t19, -(_self21 * _t13))));
        return dest;
    }

    /** {@code rotateYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYZX_unsafe_mulAdd(long dest, long src, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleZ);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.cosWithSin(angleX);
        double _t5 = Math.sinWithCos(angleX);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t0 * _t1;
        double _t7 = _t2 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t3 * _t2;
        UnsafeOpsHolder.U.putDouble(dest, ((((_self00) * (_t7) + (_self01 * _t3))) - (_self02) * (_t6)));
        return rotateYZX_unsafe_mulAdd_s628a647f_1(dest, _t3, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, ((_t8) * (_t4) + (_t5 * _t2)), ((_t10) * (_t5) + (_t0 * _t4)), ((_t5) * (_t0) - (_t10 * _t4)), ((_t4) * (_t2) - (_t8 * _t5)));
    }

    /** Piece 2 of {@code rotateYZX_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_mulAdd_s628a647f_1(long dest, double _t3, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t7, double _t11, double _t13, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((((_self10) * (_t7) + (_self11 * _t3))) - (_self12) * (_t6)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((((_self20) * (_t7) + (_self21 * _t3))) - (_self22) * (_t6)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t18) + (((_self00) * (_t20) + (_self01 * _t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t18) + (((_self10) * (_t20) + (_self11 * _t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t18) + (((_self20) * (_t20) + (_self21 * _t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t21) + (((_self00) * (_t19) - (_self01 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t21) + (((_self10) * (_t19) - (_self11 * _t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t21) + (((_self20) * (_t19) - (_self21 * _t13)))));
        return dest;
    }

    public static long rotateZ180_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, -_self00);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, -_self01);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self01);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZXY_unsafe_fma(dest, src, angleZ, angleX, angleY);
        return rotateZXY_unsafe_mulAdd(dest, src, angleZ, angleX, angleY);
    }

    /** {@code rotateZXY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZXY_unsafe_fma(long dest, long src, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleX);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.cosWithSin(angleZ);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.sinWithCos(angleZ);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t0 * _t1;
        double _t7 = _t4 * _t5;
        double _t8 = _t4 * _t3;
        double _t18 = java.lang.Math.fma(_t8, _t0, _t5 * _t2);
        double _t20 = java.lang.Math.fma(_t2, _t3, -(_t7 * _t0));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, -_t6, java.lang.Math.fma(_self00, _t20, _self01 * _t18)));
        return rotateZXY_unsafe_fma_s8382a436_1(dest, _t4, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t1 * _t3, _t5 * _t1, _t1 * _t2, _t18, java.lang.Math.fma(_t7, _t2, _t0 * _t3), _t20, java.lang.Math.fma(_t0, _t5, -(_t8 * _t2)));
    }

    /** Piece 2 of {@code rotateZXY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_fma_s8382a436_1(long dest, double _t4, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t10, double _t11, double _t12, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, -_t6, java.lang.Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, -_t6, java.lang.Math.fma(_self20, _t20, _self21 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t4, java.lang.Math.fma(_self01, _t10, -(_self00 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t4, java.lang.Math.fma(_self11, _t10, -(_self10 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t4, java.lang.Math.fma(_self21, _t10, -(_self20 * _t11))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t12, java.lang.Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t12, java.lang.Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t12, java.lang.Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZXY_unsafe_mulAdd(long dest, long src, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleX);
        double _t2 = Math.cosWithSin(angleY);
        double _t3 = Math.cosWithSin(angleZ);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.sinWithCos(angleZ);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t0 * _t1;
        double _t7 = _t4 * _t5;
        double _t8 = _t4 * _t3;
        double _t18 = ((_t8) * (_t0) + (_t5 * _t2));
        double _t20 = ((_t2) * (_t3) - (_t7 * _t0));
        UnsafeOpsHolder.U.putDouble(dest, ((((_self00) * (_t20) + (_self01 * _t18))) - (_self02) * (_t6)));
        return rotateZXY_unsafe_mulAdd_sef63e53b_1(dest, _t4, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t1 * _t3, _t5 * _t1, _t1 * _t2, _t18, ((_t7) * (_t2) + (_t0 * _t3)), _t20, ((_t0) * (_t5) - (_t8 * _t2)));
    }

    /** Piece 2 of {@code rotateZXY_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_mulAdd_sef63e53b_1(long dest, double _t4, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t10, double _t11, double _t12, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((((_self10) * (_t20) + (_self11 * _t18))) - (_self12) * (_t6)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((((_self20) * (_t20) + (_self21 * _t18))) - (_self22) * (_t6)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t4) + (((_self01) * (_t10) - (_self00 * _t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t4) + (((_self11) * (_t10) - (_self10 * _t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t4) + (((_self21) * (_t10) - (_self20 * _t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t12) + (((_self00) * (_t19) + (_self01 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t12) + (((_self10) * (_t19) + (_self11 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t12) + (((_self20) * (_t19) + (_self21 * _t21)))));
        return dest;
    }

    public static long rotateZYX_unsafe(long dest, long src, double angleZ, double angleY, double angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZYX_unsafe_fma(dest, src, angleZ, angleY, angleX);
        return rotateZYX_unsafe_mulAdd(dest, src, angleZ, angleY, angleX);
    }

    /** {@code rotateZYX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZYX_unsafe_fma(long dest, long src, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleY);
        double _t2 = Math.cosWithSin(angleZ);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.cosWithSin(angleX);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t1 * _t2;
        double _t7 = _t3 * _t1;
        double _t9 = _t0 * _t2;
        double _t11 = _t0 * _t3;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, -_t0, java.lang.Math.fma(_self00, _t6, _self01 * _t7)));
        return rotateZYX_unsafe_fma_s1d3346aa_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, java.lang.Math.fma(_t11, _t4, _t5 * _t2), java.lang.Math.fma(_t9, _t5, _t4 * _t3), java.lang.Math.fma(_t9, _t4, -(_t3 * _t5)), java.lang.Math.fma(_t11, _t5, -(_t4 * _t2)));
    }

    /** Piece 2 of {@code rotateZYX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_fma_s1d3346aa_1(long dest, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t7, double _t8, double _t13, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, -_t0, java.lang.Math.fma(_self10, _t6, _self11 * _t7)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, -_t0, java.lang.Math.fma(_self20, _t6, _self21 * _t7)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_self02, _t8, java.lang.Math.fma(_self00, _t20, _self01 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(_self12, _t8, java.lang.Math.fma(_self10, _t20, _self11 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(_self22, _t8, java.lang.Math.fma(_self20, _t20, _self21 * _t18)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self02, _t13, java.lang.Math.fma(_self00, _t19, _self01 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self12, _t13, java.lang.Math.fma(_self10, _t19, _self11 * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self22, _t13, java.lang.Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    /** {@code rotateZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZYX_unsafe_mulAdd(long dest, long src, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sinWithCos(angleY);
        double _t1 = Math.cosWithSin(angleY);
        double _t2 = Math.cosWithSin(angleZ);
        double _t3 = Math.sinWithCos(angleZ);
        double _t4 = Math.sinWithCos(angleX);
        double _t5 = Math.cosWithSin(angleX);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t6 = _t1 * _t2;
        double _t7 = _t3 * _t1;
        double _t9 = _t0 * _t2;
        double _t11 = _t0 * _t3;
        UnsafeOpsHolder.U.putDouble(dest, ((((_self00) * (_t6) + (_self01 * _t7))) - (_self02) * (_t0)));
        return rotateZYX_unsafe_mulAdd_s20ba1663_1(dest, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t6, _t7, _t4 * _t1, _t5 * _t1, ((_t11) * (_t4) + (_t5 * _t2)), ((_t9) * (_t5) + (_t4 * _t3)), ((_t9) * (_t4) - (_t3 * _t5)), ((_t11) * (_t5) - (_t4 * _t2)));
    }

    /** Piece 2 of {@code rotateZYX_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_mulAdd_s20ba1663_1(long dest, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t6, double _t7, double _t8, double _t13, double _t18, double _t19, double _t20, double _t21) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((((_self10) * (_t6) + (_self11 * _t7))) - (_self12) * (_t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((((_self20) * (_t6) + (_self21 * _t7))) - (_self22) * (_t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_self02) * (_t8) + (((_self00) * (_t20) + (_self01 * _t18)))));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((_self12) * (_t8) + (((_self10) * (_t20) + (_self11 * _t18)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((_self22) * (_t8) + (((_self20) * (_t20) + (_self21 * _t18)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self02) * (_t13) + (((_self00) * (_t19) + (_self01 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self12) * (_t13) + (((_self10) * (_t19) + (_self11 * _t21)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self22) * (_t13) + (((_self20) * (_t19) + (_self21 * _t21)))));
        return dest;
    }

    public static long scale_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00 * vX);
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
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00 * _vx);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_fma(dest, src, s, pivotX, pivotY);
        return scaleAround_unsafe_mulAdd(dest, src, s, pivotX, pivotY);
    }

    /** {@code scaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_fma(long dest, long src, double s, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, s * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, double s, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, s * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self20) * (_t1) + (((_self21) * (_t2) + (_self22)))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long pivot, double s) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_fma(dest, src, pivot, s);
        return scaleAround_unsafe_mulAdd(dest, src, pivot, s);
    }

    /** {@code scaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_fma(long dest, long src, long pivot, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0 = 1.0 - s;
        double _t1 = UnsafeOpsHolder.U.getDouble(pivot) * _t0;
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * _t0;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, s * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        return scaleAround_unsafe_fma_sff6efae4_1(dest, _self10, _self20, _self11, _self21, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code scaleAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_fma_sff6efae4_1(long dest, double _self10, double _self20, double _self11, double _self21, double _self12, double _self22, double _t1, double _t2) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self20, _t1, java.lang.Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, long pivot, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        double _t0 = 1.0 - s;
        double _t1 = UnsafeOpsHolder.U.getDouble(pivot) * _t0;
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * _t0;
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, s * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        return scaleAround_unsafe_mulAdd_scb691d51_1(dest, _self10, _self20, _self11, _self21, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code scaleAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_mulAdd_scb691d51_1(long dest, double _self10, double _self20, double _self11, double _self21, double _self12, double _self22, double _t1, double _t2) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self20) * (_t1) + (((_self21) * (_t2) + (_self22)))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_fma(dest, src, sX, sY, pivotX, pivotY);
        return scaleAround_unsafe_mulAdd(dest, src, sX, sY, pivotX, pivotY);
    }

    /** {@code scaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_fma(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        UnsafeOpsHolder.U.putDouble(dest, sX * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, sX * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, sX * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, sY * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, sY * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, sY * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self20, _t2, java.lang.Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        UnsafeOpsHolder.U.putDouble(dest, sX * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, sX * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, sX * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, sY * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, sY * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, sY * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self20) * (_t2) + (((_self21) * (_t3) + (_self22)))));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long s, long pivot) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_unsafe_fma(dest, src, s, pivot);
        return scaleAround_unsafe_mulAdd(dest, src, s, pivot);
    }

    /** {@code scaleAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_fma(long dest, long src, long s, long pivot) {
        double _sx = UnsafeOpsHolder.U.getDouble(s);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        return scaleAround_unsafe_fma_s5aa5823_1(dest, pivot, _sx, _sy, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code scaleAround_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_fma_s5aa5823_1(long dest, long pivot, double _sx, double _sy, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22) {
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot) * (1.0 - _sx);
        double _t3 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * (1.0 - _sy);
        UnsafeOpsHolder.U.putDouble(dest, _sx * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sx * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sx * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sy * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _sy * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _sy * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self20, _t2, java.lang.Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    /** {@code scaleAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long scaleAround_unsafe_mulAdd(long dest, long src, long s, long pivot) {
        double _sx = UnsafeOpsHolder.U.getDouble(s);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        return scaleAround_unsafe_mulAdd_s22bc750e_1(dest, pivot, _sx, _sy, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code scaleAround_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long scaleAround_unsafe_mulAdd_s22bc750e_1(long dest, long pivot, double _sx, double _sy, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22) {
        double _t2 = UnsafeOpsHolder.U.getDouble(pivot) * (1.0 - _sx);
        double _t3 = UnsafeOpsHolder.U.getDouble(pivot + 8L) * (1.0 - _sy);
        UnsafeOpsHolder.U.putDouble(dest, _sx * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sx * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sx * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sy * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _sy * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _sy * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self20) * (_t2) + (((_self21) * (_t3) + (_self22)))));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, double vX, double vY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_unsafe_mulAdd(dest, src, vX, vY);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self20, vX, java.lang.Math.fma(_self21, vY, _self22)));
        return dest;
    }

    /** {@code translate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long translate_unsafe_mulAdd(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self20) * (vX) + (((_self21) * (vY) + (_self22)))));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_unsafe_mulAdd(dest, src, v);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(_self20, _vx, java.lang.Math.fma(_self21, _vy, _self22)));
        return dest;
    }

    /** {@code translate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long translate_unsafe_mulAdd(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
        UnsafeOpsHolder.U.putDouble(dest + 56L, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((_self20) * (_vx) + (((_self21) * (_vy) + (_self22)))));
        return dest;
    }

    public static long view_unsafe(long dest, long src, double left, double right, double bottom, double top) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
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
        UnsafeOpsHolder.U.putDouble(dest, _sp0 * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _self20);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _sp1 * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _sp1 * _self21);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        return view_unsafe_sf56a40c0_1(dest, _self10, _self20, _self11, _self21, _self12, _self22, _sp2, _sp3);
    }

    /** Piece 2 of {@code view_unsafe}, split to fit the inline budget; reached only through it. */
    private static long view_unsafe_sf56a40c0_1(long dest, double _self10, double _self20, double _self11, double _self21, double _self12, double _self22, double _sp2, double _sp3) {
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        UnsafeOpsHolder.U.putDouble(dest + 64L, _self22 + (-(_self20 * _sp2) - _self21 * _sp3));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, double vX, double vY, double vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_unsafe_mulAdd(dest, src, vX, vY, vZ);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, vZ, java.lang.Math.fma(_self00, vX, _self01 * vY)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, vZ, java.lang.Math.fma(_self10, vX, _self11 * vY)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, vZ, java.lang.Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    /** {@code mulVec3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulVec3_unsafe_mulAdd(long dest, long src, double vX, double vY, double vZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (vZ) + (((_self00) * (vX) + (_self01 * vY)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (vZ) + (((_self10) * (vX) + (_self11 * vY)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (vZ) + (((_self20) * (vX) + (_self21 * vY)))));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulVec3_unsafe_mulAdd(dest, src, v);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self02, _vz, java.lang.Math.fma(_self00, _vx, _self01 * _vy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self12, _vz, java.lang.Math.fma(_self10, _vx, _self11 * _vy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_self22, _vz, java.lang.Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    /** {@code mulVec3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mulVec3_unsafe_mulAdd(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(src + 64L);
        UnsafeOpsHolder.U.putDouble(dest, ((_self02) * (_vz) + (((_self00) * (_vx) + (_self01 * _vy)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self12) * (_vz) + (((_self10) * (_vx) + (_self11 * _vy)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_self22) * (_vz) + (((_self20) * (_vx) + (_self21 * _vy)))));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, vX, _self01 * vY));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
            UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (vX) + (_self01 * vY)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static long transformDirection_unsafe(long dest, long src, long v) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = UnsafeOpsHolder.U.getDouble(v);
            double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _vx, _self01 * _vy));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            double _vx = UnsafeOpsHolder.U.getDouble(v);
            double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
            UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_vx) + (_self01 * _vy)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_vx) + (_self11 * _vy)));
            return dest;
        }
    }

    public static long transformPosition_unsafe(long dest, long src, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
            return dest;
        } else {
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
            UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
            return dest;
        }
    }

    public static long transformPosition_unsafe(long dest, long src, long v) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = UnsafeOpsHolder.U.getDouble(v);
            double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
            return dest;
        } else {
            double _vx = UnsafeOpsHolder.U.getDouble(v);
            double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
            double _self00 = UnsafeOpsHolder.U.getDouble(src);
            double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _self01 = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _self11 = UnsafeOpsHolder.U.getDouble(src + 32L);
            double _self02 = UnsafeOpsHolder.U.getDouble(src + 48L);
            double _self12 = UnsafeOpsHolder.U.getDouble(src + 56L);
            UnsafeOpsHolder.U.putDouble(dest, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
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
