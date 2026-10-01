// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3x3Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double3x3Ops} and its sibling kernel units. Not public API.
 */
public final class Double3x3OpsKernelsSegment {
    private Double3x3OpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment getColumn_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getColumn_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; _idxSw2 = _self20; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; _idxSw2 = _self21; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _idxSw0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _idxSw1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _idxSw2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getEulerAnglesXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 < Math.fma(_self02, _self02, _t1) * 1.0E-15) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(_self21, _self11));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(-_self12, _self22));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(-_self01, _self00));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getEulerAnglesXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 < Math.fma(_self01, _self01, _t1) * 1.0E-15) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(-_self12, _self22));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(_self21, _self11));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_self02, _self00));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getEulerAnglesYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 < Math.fma(_self12, _self12, _t1) * 1.0E-15) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(-_self20, _self00));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_self02, _self22));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_self10, _self11));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getEulerAnglesYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.fma(_self11, _self11, _self12 * _self12);
        if (_t1 < Math.fma(_self10, _self10, _t1) * 1.0E-15) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_self02, _self22));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(-_self12, _self11));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(-_self20, _self00));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getEulerAnglesZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 < Math.fma(_self21, _self21, _t1) * 1.0E-15) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_self10, _self00));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(-_self20, _self22));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(-_self01, _self11));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getEulerAnglesZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.fma(_self21, _self21, _self22 * _self22);
        if (_t1 < Math.fma(_self20, _self20, _t1) * 1.0E-15) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(-_self01, _self11));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(_self21, _self22));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_self10, _self00));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getNormalizedRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getNormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getNormalizedRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t6 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        double _t7 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        double _t8 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t11 = (1.0 / java.lang.Math.sqrt(_t8));
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
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t62));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t64));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t65));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t63));
        if (_t58 > 0.0) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp0 * _t36);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp0 * _t56);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp0 * _t57);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > java.lang.Math.max(_t23, _t26)) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * java.lang.Math.sqrt(_t63));
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp3 * _t53);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp3 * _t55);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp1 * _t53);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * java.lang.Math.sqrt(_t64));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp1 * _t39);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp1 * _t56);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp2 * _t55);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp2 * _t39);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * java.lang.Math.sqrt(_t65));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment getRow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getRow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            case 2: _idxSw0 = _self20; _idxSw1 = _self21; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _idxSw0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _idxSw1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _idxSw2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getTranslation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getUnnormalizedRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.getUnnormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getUnnormalizedRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
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
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t14));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t16));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t17));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t15));
        if (_t10 > 0.0) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp0 * _t1);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp0 * _t7);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp0 * _t9);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp3 * _t4);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp3 * _t6);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp1 * _t4);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp1 * _t8);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp1 * _t7);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp2 * _t6);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp2 * _t8);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment cofactor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cofactor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self11, _self22, -(_self12 * _self21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self02, _self21, -(_self01 * _self22)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self01, _self12, -(_self02 * _self11)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self12, _self20, -(_self10 * _self22)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self00, _self22, -(_self02 * _self20)));
        return cofactor_api_s150e592b_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12);
    }

    /** Piece 2 of {@code cofactor_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cofactor_api_s150e592b_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self02, _self10, -(_self00 * _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self10, _self21, -(_self11 * _self20)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self01, _self20, -(_self00 * _self21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self00, _self11, -(_self01 * _self10)));
        return dest;
    }

    public static double determinant_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double3x3OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static double determinant_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        return Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), Math.fma(_self11, _self22, -(_self12 * _self21)), -(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static double frobeniusNorm_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double3x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        return java.lang.Math.sqrt(Math.fma(_self00, _self00, _self01 * _self01) + Math.fma(_self02, _self02, _self10 * _self10) + (Math.fma(_self11, _self11, _self12 * _self12) + Math.fma(_self20, _self20, Math.fma(_self21, _self21, _self22 * _self22))));
    }

    public static java.lang.foreign.MemorySegment invert_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(java.lang.Math.abs(_t13) > 2.2250738585072014E-308 && java.lang.Math.abs(_t13) < 4.49423283715579E307)) return Double3x3OpsKernelsSegment.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t13_inv = 1.0 / _t13;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t6 * _t13_inv);
        return invert_api_s54f69afc_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t13_inv);
    }

    /** Piece 2 of {@code invert_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_s54f69afc_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t13_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t7 * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment invert_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t2 = unitScale(_self00, _self01, _self02);
        double _t12 = _self11 * _t0;
        double _t13 = _self22 * _t1;
        double _t14 = _self12 * _t0;
        double _t15 = _self21 * _t1;
        double _t16 = _self10 * _t0;
        double _t17 = _self20 * _t1;
        return invert_degenerate_api_sadb6d9d_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _self02 * _t2, _self00 * _t2, _self01 * _t2, Math.fma(_t12, _t13, -(_t14 * _t15)), Math.fma(_t16, _t15, -(_t12 * _t17)));
    }

    /** Piece 2 of {@code invert_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invert_degenerate_api_sadb6d9d_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _t1, double _t2, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t27, double _t28) {
        double _t33_inv = 1.0 / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        double _sp2 = _t1 * _t33_inv;
        double _sp1 = _t0 * _t33_inv;
        double _sp0 = _t2 * _t33_inv;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t27 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t28 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _other00 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other20 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other01 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other11 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other21 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        return invertProduct_api_s1bfcea86_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21);
    }

    /** Piece 2 of {@code invertProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_s1bfcea86_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21) {
        double _other02 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 48L);
        double _other12 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 56L);
        double _other22 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 64L);
        double _t18 = Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11));
        double _t19 = Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21));
        double _t20 = Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21));
        double _t21 = Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11));
        double _t23 = Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11));
        double _t24 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        return invertProduct_api_s1bfcea86_2(dest, destOffset, src, srcOffset, other, otherOffset, _t18, _t19, _t20, _t21, Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01)), _t23, _t24, Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01)), Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01)), Math.fma(_t18, _t19, -(_t20 * _t21)), Math.fma(_t23, _t20, -(_t24 * _t18)));
    }

    /** Piece 3 of {@code invertProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_s1bfcea86_2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t33, double _t34) {
        double _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(java.lang.Math.abs(_t40) > 2.2250738585072014E-308 && java.lang.Math.abs(_t40) < 4.49423283715579E307)) return Double3x3OpsKernelsSegment.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t40_inv = 1.0 / _t40;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t33 * _t40_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t34 * _t40_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv);
        return invertProduct_api_s1bfcea86_3(dest, destOffset, _t18, _t21, _t22, _t23, _t25, _t26, _t40_inv);
    }

    /** Piece 4 of {@code invertProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_s1bfcea86_3(java.lang.foreign.MemorySegment dest, long destOffset, double _t18, double _t21, double _t22, double _t23, double _t25, double _t26, double _t40_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double3x3OpsKernelsSegment.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsSegment.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _other00 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other20 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other01 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other11 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other21 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        return invertProduct_degenerate_api_sca85497b_1(dest, destOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_sca85497b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment other, long otherOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21) {
        double _other02 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 48L);
        double _other12 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 56L);
        double _other22 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 64L);
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
        return invertProduct_degenerate_api_sca85497b_2(dest, destOffset, _t19, _t20, _t22, _t24, _t25, _t26, _t27, _t28, unitScale(_t24, _t25, _t26), _t18 * _t27, _t21 * _t28, _t23 * _t28);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_sca85497b_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t19, double _t20, double _t22, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39, double _t40, double _t41) {
        double _t42 = _t20 * _t27;
        double _t43 = _t19 * _t27;
        double _t44 = _t22 * _t28;
        double _t45 = _t26 * _t29;
        double _t46 = _t24 * _t29;
        double _t47 = _t25 * _t29;
        double _t54 = Math.fma(_t39, _t40, -(_t41 * _t42));
        double _t55 = Math.fma(_t43, _t41, -(_t44 * _t39));
        double _t60_inv = 1.0 / Math.fma(_t55, _t45, Math.fma(_t54, _t46, -(Math.fma(_t43, _t40, -(_t44 * _t42)) * _t47)));
        double _sp1 = _t27 * _t60_inv;
        double _sp0 = _t29 * _t60_inv;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t54 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t55 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1);
        return invertProduct_degenerate_api_sca85497b_3(dest, destOffset, _t39, _t42, _t43, _t45, _t46, _t47, _t28 * _t60_inv);
    }

    /** Piece 4 of {@code invertProduct_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_degenerate_api_sca85497b_3(java.lang.foreign.MemorySegment dest, long destOffset, double _t39, double _t42, double _t43, double _t45, double _t46, double _t47, double _sp2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_t47, _t42, -(_t39 * _t45)) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_t43, _t45, -(_t46 * _t42)) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_t46, _t39, -(_t43 * _t47)) * _sp2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(java.lang.Math.abs(_t13) > 2.2250738585072014E-308 && java.lang.Math.abs(_t13) < 4.49423283715579E307)) return Double3x3OpsKernelsSegment.normal_degenerate(dest, destOffset, src, srcOffset);
        double _t13_inv = 1.0 / _t13;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t6 * _t13_inv);
        return normal_api_s35780115_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t13_inv);
    }

    /** Piece 2 of {@code normal_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment normal_api_s35780115_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t13_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _t7 * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.normal_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment normal_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normal_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t2 = unitScale(_self00, _self01, _self02);
        double _t12 = _self11 * _t0;
        double _t13 = _self22 * _t1;
        double _t14 = _self12 * _t0;
        double _t15 = _self21 * _t1;
        double _t16 = _self10 * _t0;
        double _t17 = _self20 * _t1;
        return normal_degenerate_api_s112b22de_1(dest, destOffset, _t0, _t1, _t2, _t12, _t13, _t14, _t15, _t16, _t17, _self02 * _t2, _self00 * _t2, _self01 * _t2, Math.fma(_t12, _t13, -(_t14 * _t15)), Math.fma(_t16, _t15, -(_t12 * _t17)));
    }

    /** Piece 2 of {@code normal_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment normal_degenerate_api_s112b22de_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _t1, double _t2, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t27, double _t28) {
        double _t33_inv = 1.0 / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        double _sp2 = _t1 * _t33_inv;
        double _sp1 = _t0 * _t33_inv;
        double _sp0 = _t2 * _t33_inv;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t27 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _t28 * _sp0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2);
        return dest;
    }

    public static double trace_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double3x3OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static double trace_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L) + (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L));
    }

    public static java.lang.foreign.MemorySegment transpose_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transpose_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + _i * 8L) + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        for (int _i = 0; _i < 9; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, scalar * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _i = 0; _i < 9; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + _i * 8L));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Double3x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        for (int _i = 0; _i < 9; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + _i * 8L));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Double3x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        double _m10 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 8L);
        double _m01 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 16L);
        double _m11 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _m10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _m01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _m11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Double3x3OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        double _m10 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 8L);
        double _m01 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 16L);
        double _m11 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 24L);
        double _m02 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 32L);
        double _m12 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 40L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _m10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _m01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _m11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _m02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _m12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Double3x3OpsKernelsAddress.setMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat3x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        double _m01 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 8L);
        double _m02 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 16L);
        double _m10 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 32L);
        double _m11 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 40L);
        double _m12 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 48L);
        double _m20 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 64L);
        double _m21 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 72L);
        double _m22 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + 80L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _m10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _m20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _m01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _m11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _m21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _m02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _m12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _m22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat4x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        Double3x3OpsKernelsAddress.setMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment setMat4x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            int _lom = _l * 4;
            double _em1 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + (_lom + 1) * 8L);
            double _em2 = m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + (_lom + 2) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, m.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, mOffset + _lom * 8L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, _em1);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, _em2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double tX, double tY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double tX, double tY) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, tX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, tY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _tBase = t.address() + tOffset;
        Double3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _tx = t.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, tOffset);
        double _ty = t.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, tOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _tx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _ty);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromRigid_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double rRX, double rRY, double rRZ, double rRW) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeFromRigid_unsafe(_destBase, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromRigid_api(java.lang.foreign.MemorySegment dest, long destOffset, double rRX, double rRY, double rRZ, double rRW) {
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-2.0, Math.fma(rRY, rRY, _t0), 1.0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 2.0 * Math.fma(rRX, rRY, _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 2.0 * Math.fma(rRX, rRZ, -_t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 2.0 * Math.fma(rRX, rRY, -_t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(-2.0, Math.fma(rRX, rRX, _t0), 1.0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 2.0 * Math.fma(rRX, rRW, rRY * rRZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 2.0 * Math.fma(rRX, rRZ, _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 2.0 * Math.fma(rRY, rRZ, -(rRX * rRW)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(-2.0, Math.fma(rRX, rRX, rRY * rRY), 1.0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromTransform_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeFromTransform_unsafe(_destBase, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromTransform_api(java.lang.foreign.MemorySegment dest, long destOffset, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(tRX, tRY, _t4) * _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(tRX, tRZ, -_t5) * _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(tRX, tRY, -_t4) * _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(tRX, tRZ, _t5) * _t2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _loself = _l * 3;
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_loself + 1) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _loself * 8L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, _eself1);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.to3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to3x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self20);
        return to3x4_api_s6bac1064_1(dest, destOffset, _self21, _self22);
    }

    /** Piece 2 of {@code to3x4_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment to3x4_api_s6bac1064_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self21, double _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 72L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 80L, _self22);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 88L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to4x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.to4x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment to4x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 72L, _self12);
        return to4x4_api_s4e2d311f_1(dest, destOffset, _self22);
    }

    /** Piece 2 of {@code to4x4_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment to4x4_api_s4e2d311f_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 80L, _self22);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 88L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 96L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 104L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 112L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 120L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toDualQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toDualQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
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
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t14));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t16));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t17));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t15));
        if (_t13 > 0.0) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp0 * _t3);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp0 * _t7);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp0 * _t9);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp3 * _t5);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp3 * _t6);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp3 * _t3);
            } else {
                if (_self11 > _self22) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp1 * _t5);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp1 * _t8);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp1 * _t7);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp2 * _t6);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp2 * _t8);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp2 * _t9);
                }
            }
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toRigid_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.toRigid_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toRigid_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t0 = -_self11;
        double _t1 = -_self22;
        double _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsSegment.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsSegment.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsSegment.toRigid_degenerate(dest, destOffset, src, srcOffset);
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
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp0 * _t35);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _sp0 * _t56);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _sp0 * _t57);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.5 * java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * java.lang.Math.sqrt(_t67));
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _sp3 * _t54);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _sp3 * _t55);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp1 * _t54);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 0.5 * java.lang.Math.sqrt(_t65));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _sp1 * _t31);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _sp1 * _t56);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp2 * _t55);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _sp2 * _t31);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.5 * java.lang.Math.sqrt(_t66));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _sp2 * _t57);
                }
            }
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toRigid_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.toRigid_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.toRigid_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment toRigid_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.toRigid_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toRigid_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
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
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp0 * _t182);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _sp0 * _t201);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _sp0 * _t202);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.5 * java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * java.lang.Math.sqrt(_t208));
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _sp3 * _t199);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _sp3 * _t200);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp1 * _t199);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 0.5 * java.lang.Math.sqrt(_t209));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _sp1 * _t184);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _sp1 * _t201);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp2 * _t200);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _sp2 * _t184);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.5 * java.lang.Math.sqrt(_t210));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _sp2 * _t202);
                }
            }
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toTransform_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.toTransform_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toTransform_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsSegment.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsSegment.toTransform_degenerate(dest, destOffset, src, srcOffset);
        return toTransform_api_s929f5dc3_1(dest, destOffset, src, srcOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10)));
    }

    /** Piece 2 of {@code toTransform_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_api_s929f5dc3_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t0, double _t1, double _t12, double _t13, double _t14) {
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsSegment.toTransform_degenerate(dest, destOffset, src, srcOffset);
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
        return toTransform_api_s929f5dc3_2(dest, destOffset, _self01, _self11, _self22, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, Math.fma(_self12, _t16, _t24), Math.fma(_self21, _t15, -_t21), java.lang.Math.max(_t25, _t20), _t47, _t48, _t49, 1.0 + _t48, 1.0 - _t48, Math.fma(_self01, _t15, _t49), Math.fma(_self02, _t16, _t50), Math.fma(_self02, _t16, -_t50));
    }

    /** Piece 3 of {@code toTransform_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_api_s929f5dc3_2(java.lang.foreign.MemorySegment dest, long destOffset, double _self01, double _self11, double _self22, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t49, double _t52, double _t53, double _t55, double _t56, double _t57) {
        double _t63 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48));
        double _t64 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t64));
        double _t66 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53));
        double _t67 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53));
        double _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        return toTransform_api_s929f5dc3_3(dest, destOffset, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t55, _t56, _t57, Math.fma(-_self01, _t15, _t49), _t63, _t64, _sp0, _t66, _t67, _sp1, _sp2, 0.5 * (1.0 / java.lang.Math.sqrt(_t68)));
    }

    /** Piece 4 of {@code toTransform_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toTransform_api_s929f5dc3_3(java.lang.foreign.MemorySegment dest, long destOffset, double _t12, double _t13, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t55, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t66, double _t67, double _sp1, double _sp2, double _sp3) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * java.lang.Math.sqrt(_t67));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _t63 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _t47 < 0.0 ? -_t18 : _t18);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, java.lang.Math.sqrt(_t12));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 72L, java.lang.Math.sqrt(_t13));
        return dest;
    }

    public static java.lang.foreign.MemorySegment toTransform_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.toTransform_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.toTransform_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment toTransform_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.toTransform_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toTransform_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _t196 < 0.0 ? -_t56 : _t56);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 72L, _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.decomposeRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
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
        double _t20 = -Math.fma(_self22, _t7, Math.fma(_self02, _t8, _self12 * _t9));
        double _t21 = Math.fma(_t19, _t7, _self21);
        double _t22 = Math.fma(_t19, _t8, _self01);
        double _t23 = Math.fma(_t19, _t9, _self11);
        double _t29 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23));
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
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t86));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t88));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t89));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t87));
        if (_t82 > 0.0) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp0 * _t60);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp0 * _t81);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp0 * _t78);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * java.lang.Math.sqrt(_t86));
        } else {
            if (_t73 > java.lang.Math.max(_t36, _t56)) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * java.lang.Math.sqrt(_t87));
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp3 * _t77);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp3 * _t80);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp3 * _t60);
            } else {
                if (_t36 > _t56) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp1 * _t77);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * java.lang.Math.sqrt(_t88));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp1 * _t63);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp1 * _t81);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp2 * _t80);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp2 * _t63);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * java.lang.Math.sqrt(_t89));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp2 * _t78);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.decomposeScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
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
        return decomposeScale_api_se103cfd3_1(dest, destOffset, _self11, _self02, _self12, _self22, _t4, _t8, _t9, _t10, _t17, -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10)), Math.fma(_t17, _t8, _self21), Math.fma(_t17, _t9, _self01));
    }

    /** Piece 2 of {@code decomposeScale_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeScale_api_se103cfd3_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self11, double _self02, double _self12, double _self22, double _t4, double _t8, double _t9, double _t10, double _t17, double _t18, double _t19, double _t20) {
        double _t21 = Math.fma(_t17, _t10, _self11);
        double _t27 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
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
        return decomposeScale_api_se103cfd3_2(dest, destOffset, _t4, _t8, _t9, _t10, _t27, _t32, _t33, _t34, _t47, _t52, _t53, _t54);
    }

    /** Piece 3 of {@code decomposeScale_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeScale_api_se103cfd3_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t4, double _t8, double _t9, double _t10, double _t27, double _t32, double _t33, double _t34, double _t47, double _t52, double _t53, double _t54) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0 ? -_t4 : _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.sqrt(_t27));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.sqrt(_t47));
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeSkew_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.decomposeSkew_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeSkew_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
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
        return decomposeSkew_api_s2d311f6b_1(dest, destOffset, _self01, _self11, _self02, _self12, _self22, _t7, _t8, _t9, _t14, _t15, -_t14, _t17, Math.fma(_t17, _t7, _self21));
    }

    /** Piece 2 of {@code decomposeSkew_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSkew_api_s2d311f6b_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self01, double _self11, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t14, double _t15, double _t16, double _t17, double _t19) {
        double _t20 = Math.fma(_t17, _t8, _self01);
        double _t21 = Math.fma(_t17, _t9, _self11);
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
        return decomposeSkew_api_s2d311f6b_2(dest, destOffset, _t7, _t8, _t9, _t15 * _t27, _t32, _t33, _t34, _t37, _t48, _t14 * _t48, _t53, _t54, _t55);
    }

    /** Piece 3 of {@code decomposeSkew_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSkew_api_s2d311f6b_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t7, double _t8, double _t9, double _t28, double _t32, double _t33, double _t34, double _t37, double _t48, double _t49, double _t53, double _t54, double _t55) {
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -_t49);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_t28);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t49);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t28);
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t37 * _t48);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, Math.fma(t, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + _i * 8L) - _eself, _eself));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Double3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        return mul_api_sd7a85aff_1(dest, destOffset, right, rightOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code mul_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_sd7a85aff_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment right, long rightOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + _lo * 8L);
            double _eright1 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + (_lo + 1) * 8L);
            double _eright2 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + (_lo + 2) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Double3x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _right00 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset);
        double _right10 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 8L);
        double _right01 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 16L);
        double _right11 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_right00, _self00, _right10 * _self01));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_right00, _self10, _right10 * _self11));
        return mulMat2x2_api_s51a12965_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right00, _right10, _right01, _right11);
    }

    /** Piece 2 of {@code mulMat2x2_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x2_api_s51a12965_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _right00, double _right10, double _right01, double _right11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_right00, _self20, _right10 * _self21));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_right01, _self00, _right11 * _self01));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_right01, _self10, _right11 * _self11));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_right01, _self20, _right11 * _self21));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        Double3x3OpsKernelsAddress.mulMat2x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _right00 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset);
        double _right10 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 8L);
        double _right01 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 16L);
        double _right11 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 24L);
        double _right02 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 32L);
        double _right12 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 40L);
        return mulMat2x3_api_sb92eea6_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right00, _right10, _right01, _right11, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x3_api_sb92eea6_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _right00, double _right10, double _right01, double _right11, double _right02, double _right12) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_right00, _self00, _right10 * _self01));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_right00, _self10, _right10 * _self11));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_right00, _self20, _right10 * _self21));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_right01, _self00, _right11 * _self01));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_right01, _self10, _right11 * _self11));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_right01, _self20, _right11 * _self21));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_right02, _self20, Math.fma(_right12, _self21, _self22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _other00 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other20 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other01 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other11 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other21 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        double _other02 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 48L);
        double _other12 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 56L);
        double _other22 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 64L);
        return preMul_api_sfe1b7856_1(dest, destOffset, src, srcOffset, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMul_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_sfe1b7856_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L);
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eself2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 2) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, Math.fma(_other22, _eself2, Math.fma(_other20, _eself0, _other21 * _eself1)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _other00 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L);
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eself2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 2) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, Math.fma(_other00, _eself0, _other01 * _eself1));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, Math.fma(_other10, _eself0, _other11 * _eself1));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3x3OpsKernelsAddress.preMulMat2x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _other00 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _other10 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _other01 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _other11 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _other02 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 32L);
        double _other12 = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 40L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L);
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eself2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 2) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        for (int _i = 0; _i < 9; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, Math.fma(weight, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + _i * 8L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, colZ, rowX, rowY, rowZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, colX * rowX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, colY * rowX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, colZ * rowX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, colX * rowY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, colY * rowY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, colZ * rowY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, colX * rowZ);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, colY * rowZ);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, colZ * rowZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment col, long colOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        long _destBase = dest.address() + destOffset;
        long _colBase = col.address() + colOffset;
        long _rowBase = row.address() + rowOffset;
        Double3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeOuterProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment col, long colOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        double _colx = col.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, colOffset);
        double _coly = col.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, colOffset + 8L);
        double _colz = col.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, colOffset + 16L);
        double _rowx = row.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rowOffset);
        double _rowy = row.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rowOffset + 8L);
        double _rowz = row.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rowOffset + 16L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _colx * _rowx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _coly * _rowx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _colz * _rowx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _colx * _rowy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _coly * _rowy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _colz * _rowy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _colx * _rowz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _coly * _rowz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _colz * _rowz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
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
        return lookAlong_api_seadb8ed8_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, Math.fma(_t17, _t12, upX), Math.fma(_t17, _t13, upY), Math.fma(_t17, _t11, upZ));
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_api_seadb8ed8_1(java.lang.foreign.MemorySegment dest, long destOffset, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        return lookAlong_api_seadb8ed8_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, Math.fma(_t39, _t12, -(_t38 * _t13)), Math.fma(_t37, _t13, -(_t39 * _t11)), Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_api_seadb8ed8_2(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Double3x3OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _dirx = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset);
        double _diry = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset + 8L);
        double _dirz = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset + 16L);
        double _upx = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset);
        double _upy = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 8L);
        double _upz = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 16L);
        return lookAlong_api_s22d8a986_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _dirx, _diry, _dirz, _upx, _upy, _upz, Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry)));
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_api_s22d8a986_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _dirx, double _diry, double _dirz, double _upx, double _upy, double _upz, double _t4) {
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
        return lookAlong_api_s22d8a986_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t37, _t38, _t39, Math.fma(_t39, _t12, -(_t38 * _t13)), Math.fma(_t37, _t13, -(_t39 * _t11)));
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_api_s22d8a986_2(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47) {
        double _t48 = Math.fma(_t38, _t11, -(_t37 * _t12));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromDualQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double dqRX, double dqRY, double dqRZ, double dqRW) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromDualQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, double dqRX, double dqRY, double dqRZ, double dqRW) {
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = Math.fma(-2.0, dqRZ * dqRZ, 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-2.0, _t0, _t6));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 2.0 * Math.fma(dqRX, dqRY, _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(-2.0, _t3, _sp0 * dqRZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(-2.0, _t2, _sp0 * dqRY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(-2.0, _t4, _t6));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 2.0 * Math.fma(dqRX, dqRW, _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 2.0 * Math.fma(dqRX, dqRZ, _t3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(-2.0, dqRX * dqRW, _t5 + _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, -_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t5, axisX * axisX, _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(axisZ, _t0, _t5 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t5, _t3, -(axisY * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t5, _t2, -(axisZ * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_t5, axisY * axisY, _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(axisX, _t0, _t5 * _t4));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(axisY, _t0, _t5 * _t3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_t5, _t4, -(axisX * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_t5, axisZ * axisZ, _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _axisBase = axis.address() + axisOffset;
        Double3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisy;
        double _t3 = _axisx * _axisz;
        double _t4 = _axisy * _axisz;
        double _t5 = 1.0 - _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t5, _axisx * _axisx, _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_axisz, _t0, _t5 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t5, _t3, -(_axisy * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t5, _t2, -(_axisz * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_t5, _axisy * _axisy, _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_axisx, _t0, _t5 * _t4));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_axisy, _t0, _t5 * _t3));
        return makeRotationAxis_api_s56902183_1(dest, destOffset, _t0, _axisx, _axisz, _t1, _t4, _t5);
    }

    /** Piece 2 of {@code makeRotationAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationAxis_api_s56902183_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _axisx, double _axisz, double _t1, double _t4, double _t5) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_t5, _t4, -(_axisx * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_t5, _axisz * _axisz, _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_api(java.lang.foreign.MemorySegment dest, long destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t37);
        return makeRotationLookAlong_api_sd6dd463b_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_api_sd6dd463b_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t39);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t38);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _t12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _t13);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        double _dirx = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset);
        double _diry = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset + 8L);
        double _dirz = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset + 16L);
        double _upx = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset);
        double _upy = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 8L);
        double _upz = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 16L);
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
        return makeRotationLookAlong_api_se5e157c3_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_api_se5e157c3_1(java.lang.foreign.MemorySegment dest, long destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t37);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t39);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t38);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _t12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _t13);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _t11);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 2.0 * Math.fma(qX, qY, _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 2.0 * Math.fma(qX, qZ, -_t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 2.0 * Math.fma(qX, qY, -_t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 2.0 * Math.fma(qX, qW, qY * qZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 2.0 * Math.fma(qX, qZ, _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 2.0 * Math.fma(qY, qZ, -(qX * qW)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        long _destBase = dest.address() + destOffset;
        long _qBase = q.address() + qOffset;
        Double3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, _qBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        double _qx = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset);
        double _qy = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 8L);
        double _qz = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 16L);
        double _qw = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 24L);
        double _t0 = _qz * _qz;
        double _t1 = _qz * _qw;
        double _t2 = _qy * _qw;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-2.0, Math.fma(_qy, _qy, _t0), 1.0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 2.0 * Math.fma(_qx, _qy, _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 2.0 * Math.fma(_qx, _qz, -_t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 2.0 * Math.fma(_qx, _qy, -_t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(-2.0, Math.fma(_qx, _qx, _t0), 1.0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 2.0 * Math.fma(_qx, _qw, _qy * _qz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 2.0 * Math.fma(_qx, _qz, _t2));
        return makeRotationQuat_api_sedb3a301_1(dest, destOffset, _qx, _qy, _qz, _qw);
    }

    /** Piece 2 of {@code makeRotationQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationQuat_api_sedb3a301_1(java.lang.foreign.MemorySegment dest, long destOffset, double _qx, double _qy, double _qz, double _qw) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 2.0 * Math.fma(_qy, _qz, -(_qx * _qw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(-2.0, Math.fma(_qx, _qx, _qy * _qy), 1.0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationX_api(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, -_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _t1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angleX, double angleY, double angleZ) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t0;
        double _t7 = _t0 * _t5;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t3 * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t6, _t4, _t1 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t2, _t1, -(_t7 * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, -(_t1 * _t3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_t5, _t4, -(_t6 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_t7, _t1, _t2 * _t4));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, -(_t2 * _t3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _t5 * _t3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angleX, double angleZ, double angleY) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t1 * _t5;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t3 * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t7, _t3, _t2 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t6, _t3, -(_t0 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, -_t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _t5 * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _t2 * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _t0 * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_t7, _t0, -(_t2 * _t3)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_t6, _t0, _t5 * _t3));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationY_api(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _t1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angleY, double angleX, double angleZ) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t6, _t2, _t3 * _t4));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t2 * _t5);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t7, _t2, -(_t1 * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t6, _t4, -(_t2 * _t3)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _t5 * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_t7, _t4, _t1 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _t1 * _t5);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, -_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _t5 * _t3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angleY, double angleZ, double angleX) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t1 * _t3;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t3 * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_t0 * _t4));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t2, _t0, -(_t7 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _t5 * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_t6, _t5, _t2 * _t3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_t7, _t2, _t0 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, -(_t2 * _t4));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_t5, _t3, -(_t6 * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angleZ, double angleX, double angleY) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t3, _t4, -(_t6 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t7, _t0, _t1 * _t3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -(_t0 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, -(_t1 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _t5 * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _t2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_t6, _t3, _t0 * _t4));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_t0, _t1, -(_t7 * _t3)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _t5 * _t3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double angleZ, double angleY, double angleX) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t4;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t3 * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t1 * _t3);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t7, _t2, -(_t1 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_t6, _t2, _t5 * _t4));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _t2 * _t3);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_t7, _t5, _t2 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_t6, _t5, -(_t2 * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _t5 * _t3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, vY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _vy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeScaling_api(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, vY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Double3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _vx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _vy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeView_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double left, double right, double bottom, double top) {
        long _destBase = dest.address() + destOffset;
        Double3x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeView_api(java.lang.foreign.MemorySegment dest, long destOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t0_inv + _t0_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _t1_inv + _t1_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, -((left + right) * _t0_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, -((bottom + top) * _t1_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self00, _t0, _self10 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self01, _t0, _self11 * _t1));
        return preRotate_api_sa2f9bf2a_1(dest, destOffset, _t0, _self21, _self02, _self12, _self22, _t1);
    }

    /** Piece 2 of {@code preRotate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotate_api_sa2f9bf2a_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self21, double _self02, double _self12, double _self22, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t1, -(_self12 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self02, _t0, _self12 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.sin(0.5 * angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t3 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t2 + _t2) * _t2;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self20, _t9, Math.fma(_self00, _t3, -(_self10 * _t0))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self20, _t10, Math.fma(_self00, _t0, _self10 * _t3)));
        return preRotateAround_api_sacec7564_1(dest, destOffset, _t0, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t3, _t9, _t10);
    }

    /** Piece 2 of {@code preRotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_sacec7564_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t3, double _t9, double _t10) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self21, _t9, Math.fma(_self01, _t3, -(_self11 * _t0))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self21, _t10, Math.fma(_self01, _t0, _self11 * _t3)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self22, _t9, Math.fma(_self02, _t3, -(_self12 * _t0))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self22, _t10, Math.fma(_self02, _t0, _self12 * _t3)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.sin(0.5 * angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _t3 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t2 + _t2) * _t2;
        double _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self20, _t9, Math.fma(_self00, _t3, -(_self10 * _t0))));
        return preRotateAround_api_sa9f64dcc_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t3, _t9, Math.fma(_pivoty, _t8, -(_pivotx * _t0)));
    }

    /** Piece 2 of {@code preRotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_api_sa9f64dcc_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t3, double _t9, double _t10) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self20, _t10, Math.fma(_self00, _t0, _self10 * _t3)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self21, _t9, Math.fma(_self01, _t3, -(_self11 * _t0))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self21, _t10, Math.fma(_self01, _t0, _self11 * _t3)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self22, _t9, Math.fma(_self02, _t3, -(_self12 * _t0))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self22, _t10, Math.fma(_self02, _t0, _self12 * _t3)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        return preRotateAxis_api_sff1a99a2_1(dest, destOffset, axisX, axisY, axisZ, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t4, _t6, _t11, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(axisX, _t0, _t11 * _t6));
    }

    /** Piece 2 of {@code preRotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_sff1a99a2_1(java.lang.foreign.MemorySegment dest, long destOffset, double axisX, double axisY, double axisZ, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t2, double _t4, double _t6, double _t11, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        double _t25 = Math.fma(_t11, _t6, -(axisX * _t0));
        double _t26 = Math.fma(_t11, _t2, -(axisY * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        return preRotateAxis_api_sff1a99a2_2(dest, destOffset, _self02, _self12, _self22, _t19, _t20, _t22, _t23, _t25, _t26);
    }

    /** Piece 3 of {@code preRotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_sff1a99a2_2(java.lang.foreign.MemorySegment dest, long destOffset, double _self02, double _self12, double _self22, double _t19, double _t20, double _t22, double _t23, double _t25, double _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        Double3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t11 = 1.0 - _t1;
        return preRotateAxis_api_sd2149f2e_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _axisx, _axisy, _axisz, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1));
    }

    /** Piece 2 of {@code preRotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_sd2149f2e_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _axisx, double _axisy, double _axisz, double _t2, double _t4, double _t6, double _t11, double _t18, double _t19, double _t20) {
        double _t21 = Math.fma(_axisy, _t0, _t11 * _t2);
        double _t22 = Math.fma(_axisz, _t0, _t11 * _t4);
        double _t23 = Math.fma(_axisx, _t0, _t11 * _t6);
        double _t24 = Math.fma(_t11, _t4, -(_axisz * _t0));
        double _t25 = Math.fma(_t11, _t6, -(_axisx * _t0));
        double _t26 = Math.fma(_t11, _t2, -(_axisy * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        return preRotateAxis_api_sd2149f2e_2(dest, destOffset, _self02, _self12, _self22, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26);
    }

    /** Piece 3 of {@code preRotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAxis_api_sd2149f2e_2(java.lang.foreign.MemorySegment dest, long destOffset, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self10, _t1, -(_self20 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self10, _t0, _self20 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self11, _t1, -(_self21 * _t0)));
        return preRotateX_api_s4670e54c_1(dest, destOffset, _t0, _self11, _self21, _self02, _self12, _self22, _t1);
    }

    /** Piece 2 of {@code preRotateX_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateX_api_s4670e54c_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self11, double _self21, double _self02, double _self12, double _self22, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self11, _t0, _self21 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t1, -(_self22 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self12, _t0, _self22 * _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self00, _t1, _self20 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self20, _t1, -(_self00 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self01, _t1, _self21 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        return preRotateY_api_s80e39df1_1(dest, destOffset, _t0, _self01, _self21, _self02, _self12, _self22, _t1);
    }

    /** Piece 2 of {@code preRotateY_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateY_api_s80e39df1_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self01, double _self21, double _self02, double _self12, double _self22, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self21, _t1, -(_self01 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t1, _self22 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t1, -(_self02 * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eself2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 2) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L) * vX);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, _eself1 * vY);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eself2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 2) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L) * _vx);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, _eself1 * _vy);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eself2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 2) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, s * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, s * _eself1);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(s, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _self20 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(s, _self10, _self20 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(s, _self01, _self21 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(s, _self11, _self21 * _t2));
        return preScaleAround_api_s9cb7425d_1(dest, destOffset, s, _self21, _self02, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code preScaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_s9cb7425d_1(java.lang.foreign.MemorySegment dest, long destOffset, double s, double _self21, double _self02, double _self12, double _self22, double _t1, double _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(s, _self02, _self22 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(s, _self12, _self22 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t0 = 1.0 - s;
        double _t1 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset) * _t0;
        double _t2 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(s, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _self20 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(s, _self10, _self20 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(s, _self01, _self21 * _t1));
        return preScaleAround_api_se66678c7_1(dest, destOffset, s, _self11, _self21, _self02, _self12, _self22, _t1, _t2);
    }

    /** Piece 2 of {@code preScaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_se66678c7_1(java.lang.foreign.MemorySegment dest, long destOffset, double s, double _self11, double _self21, double _self02, double _self12, double _self22, double _t1, double _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(s, _self11, _self21 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(s, _self02, _self22 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(s, _self12, _self22 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(sX, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _self20 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(sY, _self10, _self20 * _t3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(sX, _self01, _self21 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(sY, _self11, _self21 * _t3));
        return preScaleAround_api_sbd13f2ae_1(dest, destOffset, sX, sY, _self21, _self02, _self12, _self22, _t2, _t3);
    }

    /** Piece 2 of {@code preScaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_sbd13f2ae_1(java.lang.foreign.MemorySegment dest, long destOffset, double sX, double sY, double _self21, double _self02, double _self12, double _self22, double _t2, double _t3) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(sX, _self02, _self22 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(sY, _self12, _self22 * _t3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _sx = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset);
        double _sy = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset + 8L);
        double _t2 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * (1.0 - _sy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_sx, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _self20 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_sy, _self10, _self20 * _t3));
        return preScaleAround_api_s27bd41ba_1(dest, destOffset, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _sx, _sy, _t2, _t3);
    }

    /** Piece 2 of {@code preScaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preScaleAround_api_s27bd41ba_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _sx, double _sy, double _t2, double _t3) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_sx, _self01, _self21 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_sy, _self11, _self21 * _t3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_sx, _self02, _self22 * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_sy, _self12, _self22 * _t3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eself2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 2) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, Math.fma(_eself2, vX, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, Math.fma(_eself2, vY, _eself1));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 1) * 8L);
            double _eself2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + (_lo + 2) * 8L);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _lo * 8L, Math.fma(_eself2, _vx, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _lo * 8L)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 1) * 8L, Math.fma(_eself2, _vy, _eself1));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + (_lo + 2) * 8L, _eself2);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self00, _t1, _self01 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self10, _t1, _self11 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self20, _t1, _self21 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self01, _t1, -(_self00 * _t0)));
        return rotate_api_s2a1c767_1(dest, destOffset, _t0, _self10, _self20, _self11, _self21, _self02, _self12, _self22, _t1);
    }

    /** Piece 2 of {@code rotate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_s2a1c767_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self10, double _self20, double _self11, double _self21, double _self02, double _self12, double _self22, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self11, _t1, -(_self10 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self21, _t1, -(_self20 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t3 = Math.sin(0.5 * angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t3 + _t3) * _t3;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self00, _t2, _self01 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self10, _t2, _self11 * _t0));
        return rotateAround_api_sa65dd61_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, Math.fma(pivotX, _t8, pivotY * _t0), Math.fma(pivotY, _t8, -(pivotX * _t0)));
    }

    /** Piece 2 of {@code rotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_sa65dd61_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t2, double _t9, double _t10) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self20, _t2, _self21 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self01, _t2, -(_self00 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self11, _t2, -(_self10 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self21, _t2, -(_self20 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self20, _t9, Math.fma(_self21, _t10, _self22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t3 = Math.sin(0.5 * angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t3 + _t3) * _t3;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self00, _t2, _self01 * _t0));
        return rotateAround_api_se87110c3_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, Math.fma(_pivotx, _t8, _pivoty * _t0), Math.fma(_pivoty, _t8, -(_pivotx * _t0)));
    }

    /** Piece 2 of {@code rotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_se87110c3_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t2, double _t9, double _t10) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self10, _t2, _self11 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self20, _t2, _self21 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self01, _t2, -(_self00 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self11, _t2, -(_self10 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self21, _t2, -(_self20 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self20, _t9, Math.fma(_self21, _t10, _self22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        return rotateAxis_api_s477a935f_1(dest, destOffset, axisX, axisY, axisZ, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, _t5, _t6, _t11, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(axisY, _t0, _t11 * _t2));
    }

    /** Piece 2 of {@code rotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_s477a935f_1(java.lang.foreign.MemorySegment dest, long destOffset, double axisX, double axisY, double axisZ, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t2, double _t5, double _t6, double _t11, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        double _t25 = Math.fma(_t11, _t5, -(axisZ * _t0));
        double _t26 = Math.fma(_t11, _t6, -(axisX * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        return rotateAxis_api_s477a935f_2(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _t20, _t23, _t26);
    }

    /** Piece 3 of {@code rotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_s477a935f_2(java.lang.foreign.MemorySegment dest, long destOffset, double _self10, double _self20, double _self11, double _self21, double _self12, double _self22, double _t20, double _t23, double _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        Double3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t11 = 1.0 - _t1;
        return rotateAxis_api_sed18b749_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _axisx, _axisy, _axisz, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1));
    }

    /** Piece 2 of {@code rotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_sed18b749_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _axisx, double _axisy, double _axisz, double _t2, double _t5, double _t6, double _t11, double _t18, double _t19, double _t20) {
        double _t21 = Math.fma(_axisz, _t0, _t11 * _t5);
        double _t22 = Math.fma(_axisx, _t0, _t11 * _t6);
        double _t24 = Math.fma(_t11, _t2, -(_axisy * _t0));
        double _t25 = Math.fma(_t11, _t5, -(_axisz * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        return rotateAxis_api_sed18b749_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t20, Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_t11, _t6, -(_axisx * _t0)));
    }

    /** Piece 3 of {@code rotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_sed18b749_2(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t20, double _t23, double _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self01, _t1, _self02 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self11, _t1, _self12 * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self21, _t1, _self22 * _t0));
        return rotateX_api_sb16dcf3b_1(dest, destOffset, _t0, _self01, _self11, _self21, _self02, _self12, _self22, _t1);
    }

    /** Piece 2 of {@code rotateX_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateX_api_sb16dcf3b_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t1, -(_self01 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t1, -(_self11 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t1, -(_self21 * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX180_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateX180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX180_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, -_self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, -_self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, -_self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, -_self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, -_self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, -_self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX270_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateX270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX270_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, -_self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, -_self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, -_self22);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self21);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX90_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateX90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX90_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self22);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, -_self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, -_self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, -_self21);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleX, double angleY, double angleZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        return rotateXYZ_api_s183094d_1(dest, destOffset, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t1 * _t5, _t0 * _t5, _t5 * _t4, _t3 * _t5, Math.fma(_t6, _t4, _t1 * _t3), Math.fma(_t7, _t1, _t0 * _t4), Math.fma(_t0, _t1, -(_t7 * _t4)), Math.fma(_t3, _t4, -(_t6 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXYZ_api_s183094d_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t2, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11, double _t13, double _t15, double _t18, double _t19, double _t20, double _t21) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t13, _self01 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self12, _t20, Math.fma(_self10, _t13, _self11 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self22, _t20, Math.fma(_self20, _t13, _self21 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self02, _t19, Math.fma(_self01, _t21, -(_self00 * _t10))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self12, _t19, Math.fma(_self11, _t21, -(_self10 * _t10))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self22, _t19, Math.fma(_self21, _t21, -(_self20 * _t10))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t15, Math.fma(_self00, _t2, -(_self01 * _t11))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t15, Math.fma(_self10, _t2, -(_self11 * _t11))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t15, Math.fma(_self20, _t2, -(_self21 * _t11))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleX, double angleZ, double angleY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        return rotateXZY_api_s87a3c685_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t0 * _t5, _t2 * _t5, _t3 * _t5, _t4 * _t5, Math.fma(_t9, _t3, _t0 * _t2), Math.fma(_t6, _t2, _t4 * _t3), Math.fma(_t6, _t3, -(_t2 * _t4)), Math.fma(_t9, _t2, -(_t0 * _t3)));
    }

    /** Piece 2 of {@code rotateXZY_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXZY_api_s87a3c685_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11, double _t15, double _t16, double _t18, double _t19, double _t20, double _t21) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t15, _self01 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self12, _t20, Math.fma(_self10, _t15, _self11 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self22, _t20, Math.fma(_self20, _t15, _self21 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self02, _t10, Math.fma(_self01, _t16, -(_self00 * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self12, _t10, Math.fma(_self11, _t16, -(_self10 * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self22, _t10, Math.fma(_self21, _t16, -(_self20 * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t19, Math.fma(_self00, _t11, _self01 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t19, Math.fma(_self10, _t11, _self11 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t19, Math.fma(_self20, _t11, _self21 * _t21)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self00, _t1, -(_self02 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self10, _t1, -(_self12 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self20, _t1, -(_self22 * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        return rotateY_api_s32ddbd36_1(dest, destOffset, _t0, _self00, _self10, _self20, _self21, _self02, _self12, _self22, _t1);
    }

    /** Piece 2 of {@code rotateY_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateY_api_s32ddbd36_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self00, double _self10, double _self20, double _self21, double _self02, double _self12, double _self22, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self00, _t0, _self02 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self10, _t0, _self12 * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self20, _t0, _self22 * _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY180_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateY180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY180_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -_self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, -_self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, -_self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, -_self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY270_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateY270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY270_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self22);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, -_self00);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, -_self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, -_self20);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY90_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateY90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY90_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -_self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_self22);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self00);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self20);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleX, double angleZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        return rotateYXZ_api_s1f1a6be9_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2 * _t5, _t1 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)), Math.fma(_t6, _t4, -(_t2 * _t3)));
    }

    /** Piece 2 of {@code rotateYXZ_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYXZ_api_s1f1a6be9_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t12, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleZ, double angleX) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t3 = Math.cosFromSin(_t1, angleZ);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        return rotateYZX_api_s910923e5_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t0 * _t3, _t2 * _t3, _t4 * _t3, _t5 * _t3, Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t9, _t2, _t0 * _t5), Math.fma(_t2, _t0, -(_t9 * _t5)), Math.fma(_t5, _t4, -(_t6 * _t2)));
    }

    /** Piece 2 of {@code rotateYZX_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYZX_api_s910923e5_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t11, double _t13, double _t14, double _t18, double _t19, double _t20, double _t21) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-_self02, _t7, Math.fma(_self00, _t13, _self01 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-_self12, _t7, Math.fma(_self10, _t13, _self11 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(-_self22, _t7, Math.fma(_self20, _t13, _self21 * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self02, _t18, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self12, _t18, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self22, _t18, Math.fma(_self20, _t20, _self21 * _t14)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t21, Math.fma(_self00, _t19, -(_self01 * _t11))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t21, Math.fma(_self10, _t19, -(_self11 * _t11))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t21, Math.fma(_self20, _t19, -(_self21 * _t11))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ180_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateZ180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ180_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -_self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, -_self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, -_self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, -_self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ270_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateZ270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ270_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -_self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self00);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ90_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateZ90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ90_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, -_self00);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, -_self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, -_self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleZ, double angleX, double angleY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t8 = _t1 * _t5;
        return rotateZXY_api_sb071c6e9_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t0 * _t3, _t2 * _t3, _t3 * _t5, _t3 * _t4, Math.fma(_t8, _t0, _t2 * _t4), Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t4, _t5, -(_t6 * _t0)), Math.fma(_t0, _t2, -(_t8 * _t4)));
    }

    /** Piece 2 of {@code rotateZXY_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZXY_api_sb071c6e9_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t10, double _t14, double _t15, double _t18, double _t19, double _t20, double _t21) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-_self02, _t7, Math.fma(_self00, _t20, _self01 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-_self12, _t7, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(-_self22, _t7, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self02, _t1, Math.fma(_self01, _t14, -(_self00 * _t10))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self12, _t1, Math.fma(_self11, _t14, -(_self10 * _t10))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self22, _t1, Math.fma(_self21, _t14, -(_self20 * _t10))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t15, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t15, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t15, Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleZ, double angleY, double angleX) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t10 = _t0 * _t4;
        return rotateZYX_api_s4c013d1d_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t1 * _t3, _t2 * _t3, _t3 * _t4, _t5 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t5, -(_t2 * _t4)));
    }

    /** Piece 2 of {@code rotateZYX_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZYX_api_s4c013d1d_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t8, double _t9, double _t15, double _t17, double _t18, double _t19, double _t20, double _t21) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-_self02, _t0, Math.fma(_self00, _t15, _self01 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-_self12, _t0, Math.fma(_self10, _t15, _self11 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(-_self22, _t0, Math.fma(_self20, _t15, _self21 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_self02, _t9, Math.fma(_self00, _t20, _self01 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, Math.fma(_self12, _t9, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, Math.fma(_self22, _t9, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self02, _t17, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self12, _t17, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self22, _t17, Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) * vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10 * vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20 * vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01 * vY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11 * vY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21 * vY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) * _vx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10 * _vx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20 * _vx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01 * _vy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11 * _vy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21 * _vy);
        return scale_api_sf1c5038f_1(dest, destOffset, _self02, _self12, _self22);
    }

    /** Piece 2 of {@code scale_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scale_api_sf1c5038f_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self02, double _self12, double _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, s * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, s * _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, s * _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self02);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t0 = 1.0 - s;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, s * _self00);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, s * _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, s * _self21);
        return scaleAround_api_sf357172a_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, pivotX * _t0, pivotY * _t0);
    }

    /** Piece 2 of {@code scaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_sf357172a_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t1, double _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        return scaleAround_api_s45ec8f4a_1(dest, destOffset, pivot, pivotOffset, s, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, 1.0 - s);
    }

    /** Piece 2 of {@code scaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_s45ec8f4a_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t0) {
        double _t1 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset) * _t0;
        double _t2 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, s * _self00);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s * _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s * _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s * _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, s * _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, s * _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, sX * _self00);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, sX * _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, sX * _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, sY * _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, sY * _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, sY * _self21);
        return scaleAround_api_sa1158e91_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, pivotX * (1.0 - sX), pivotY * (1.0 - sY));
    }

    /** Piece 2 of {@code scaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_sa1158e91_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t2, double _t3) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _sx = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset);
        double _sy = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset + 8L);
        return scaleAround_api_se6ae82e1_1(dest, destOffset, pivot, pivotOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _sx, _sy);
    }

    /** Piece 2 of {@code scaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_api_se6ae82e1_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _sx, double _sy) {
        double _t2 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * (1.0 - _sy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sx * _self00);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sx * _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sx * _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sy * _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _sy * _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _sy * _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _self00);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        return translate_api_sf7fd5a85_1(dest, destOffset, vX, vY, _self10, _self20, _self11, _self21, _self12, _self22);
    }

    /** Piece 2 of {@code translate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_sf7fd5a85_1(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY, double _self10, double _self20, double _self11, double _self21, double _self12, double _self22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self20, vX, Math.fma(_self21, vY, _self22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _self00);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _self01);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        return translate_api_scde1381b_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _vx, _vy);
    }

    /** Piece 2 of {@code translate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment translate_api_scde1381b_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _vx, double _vy) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self20, _vx, Math.fma(_self21, _vy, _self22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment view_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double left, double right, double bottom, double top) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.lang.foreign.MemorySegment view_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double left, double right, double bottom, double top) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _sp0 * _self00);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _sp0 * _self10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _sp0 * _self20);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _sp1 * _self01);
        return view_api_sa0c745a_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _sp1, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code view_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment view_api_sa0c745a_1(java.lang.foreign.MemorySegment dest, long destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _sp1, double _sp2, double _sp3) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _sp1 * _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _sp1 * _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22 + (-(_self20 * _sp2) - _self21 * _sp3));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), vZ, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), vX, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) * vY)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulVec3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), _vz, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _vx, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) * _vy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), vX, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) * vY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _vx, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) * _vy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), vX, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), vY, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        Double3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _vx, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), _vy, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
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
