// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3x3Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double3x3Ops} and its sibling kernel units. Not public API.
 */
public final class Double3x3OpsKernelsByteBuffer {
    private Double3x3OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer getColumn_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        Double3x3OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, col);
        return dest;
    }

    public static java.nio.ByteBuffer getColumn_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; _idxSw2 = _self20; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; _idxSw2 = _self21; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.putDouble(destOffset, _idxSw0);
        dest.putDouble(destOffset + 8, _idxSw1);
        dest.putDouble(destOffset + 16, _idxSw2);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 < Math.fma(_self02, _self02, _t1) * 1.0E-15) {
            dest.putDouble(destOffset, Math.atan2(_self21, _self11));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(-_self12, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(-_self01, _self00));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 < Math.fma(_self01, _self01, _t1) * 1.0E-15) {
            dest.putDouble(destOffset, Math.atan2(-_self12, _self22));
            dest.putDouble(destOffset + 8, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_self21, _self11));
            dest.putDouble(destOffset + 8, Math.atan2(_self02, _self00));
        }
        dest.putDouble(destOffset + 16, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 < Math.fma(_self12, _self12, _t1) * 1.0E-15) {
            dest.putDouble(destOffset + 8, Math.atan2(-_self20, _self00));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(_self02, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(_self10, _self11));
        }
        dest.putDouble(destOffset, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = Math.fma(_self11, _self11, _self12 * _self12);
        if (_t1 < Math.fma(_self10, _self10, _t1) * 1.0E-15) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, Math.atan2(_self02, _self22));
        } else {
            dest.putDouble(destOffset, Math.atan2(-_self12, _self11));
            dest.putDouble(destOffset + 8, Math.atan2(-_self20, _self00));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 < Math.fma(_self21, _self21, _t1) * 1.0E-15) {
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(_self10, _self00));
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(-_self20, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(-_self01, _self11));
        }
        dest.putDouble(destOffset, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = Math.fma(_self21, _self21, _self22 * _self22);
        if (_t1 < Math.fma(_self20, _self20, _t1) * 1.0E-15) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(-_self01, _self11));
        } else {
            dest.putDouble(destOffset, Math.atan2(_self21, _self22));
            dest.putDouble(destOffset + 16, Math.atan2(_self10, _self00));
        }
        dest.putDouble(destOffset + 8, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getNormalizedRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getNormalizedRotation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getNormalizedRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
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
        return getNormalizedRotation_api_s8c15f777_1(dest, destOffset, _self00, _self10, _self20, _t8, (1.0 / java.lang.Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_s8c15f777_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _t8, double _t11, double _t21, double _t23, double _t27, double _t22, double _t24, double _t26) {
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
        return getNormalizedRotation_api_s8c15f777_2(dest, destOffset, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5 * (1.0 / java.lang.Math.sqrt(_t62)), 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getNormalizedRotation_api_s8c15f777_2(java.nio.ByteBuffer dest, int destOffset, double _t23, double _t26, double _t36, double _t39, double _t49, double _t53, double _t55, double _t56, double _t57, double _t58, double _t62, double _t63, double _t64, double _t65, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t58 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t36);
            dest.putDouble(destOffset + 8, _sp0 * _t56);
            dest.putDouble(destOffset + 16, _sp0 * _t57);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > java.lang.Math.max(_t23, _t26)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t63));
                dest.putDouble(destOffset + 8, _sp3 * _t53);
                dest.putDouble(destOffset + 16, _sp3 * _t55);
                dest.putDouble(destOffset + 24, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.putDouble(destOffset, _sp1 * _t53);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t64));
                    dest.putDouble(destOffset + 16, _sp1 * _t39);
                    dest.putDouble(destOffset + 24, _sp1 * _t56);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t55);
                    dest.putDouble(destOffset + 8, _sp2 * _t39);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t65));
                    dest.putDouble(destOffset + 24, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer getRow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        Double3x3OpsKernelsAddress.getRow_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, row);
        return dest;
    }

    public static java.nio.ByteBuffer getRow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            case 2: _idxSw0 = _self20; _idxSw1 = _self21; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.putDouble(destOffset, _idxSw0);
        dest.putDouble(destOffset + 8, _idxSw1);
        dest.putDouble(destOffset + 16, _idxSw2);
        return dest;
    }

    public static java.nio.ByteBuffer getScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getScale_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, java.lang.Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        dest.putDouble(destOffset + 8, java.lang.Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        dest.putDouble(destOffset + 16, java.lang.Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getTranslation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        dest.putDouble(destOffset, _self02);
        dest.putDouble(destOffset + 8, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer getUnnormalizedRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x3OpsKernelsAddress.getUnnormalizedRotation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getUnnormalizedRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = _self00 + _self11;
        double _t10 = _self22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_self00 - (_self11 + _self22));
        double _t16 = 1.0 + (_self11 - (_self00 + _self22));
        double _t17 = 1.0 + (_self22 - _t0);
        return getUnnormalizedRotation_api_s9cd1b5fe_1(dest, destOffset, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getUnnormalizedRotation_api_s9cd1b5fe_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self11, double _self22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t1);
            dest.putDouble(destOffset + 8, _sp0 * _t7);
            dest.putDouble(destOffset + 16, _sp0 * _t9);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.putDouble(destOffset + 8, _sp3 * _t4);
                dest.putDouble(destOffset + 16, _sp3 * _t6);
                dest.putDouble(destOffset + 24, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    dest.putDouble(destOffset, _sp1 * _t4);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.putDouble(destOffset + 16, _sp1 * _t8);
                    dest.putDouble(destOffset + 24, _sp1 * _t7);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t6);
                    dest.putDouble(destOffset + 8, _sp2 * _t8);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.putDouble(destOffset + 24, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer cofactor_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cofactor_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, Math.fma(_self11, _self22, -(_self12 * _self21)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _self21, -(_self01 * _self22)));
        dest.putDouble(destOffset + 16, Math.fma(_self01, _self12, -(_self02 * _self11)));
        dest.putDouble(destOffset + 24, Math.fma(_self12, _self20, -(_self10 * _self22)));
        dest.putDouble(destOffset + 32, Math.fma(_self00, _self22, -(_self02 * _self20)));
        dest.putDouble(destOffset + 40, Math.fma(_self02, _self10, -(_self00 * _self12)));
        dest.putDouble(destOffset + 48, Math.fma(_self10, _self21, -(_self11 * _self20)));
        dest.putDouble(destOffset + 56, Math.fma(_self01, _self20, -(_self00 * _self21)));
        dest.putDouble(destOffset + 64, Math.fma(_self00, _self11, -(_self01 * _self10)));
        return dest;
    }

    public static double determinant_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3x3OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static double determinant_api(java.nio.ByteBuffer src, int srcOffset) {
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        return Math.fma(src.getDouble(srcOffset + 48), Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(src.getDouble(srcOffset), Math.fma(_self11, _self22, -(_self12 * _self21)), -(src.getDouble(srcOffset + 24) * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static double frobeniusNorm_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        return java.lang.Math.sqrt(Math.fma(_self00, _self00, _self01 * _self01) + Math.fma(_self02, _self02, _self10 * _self10) + (Math.fma(_self11, _self11, _self12 * _self12) + Math.fma(_self20, _self20, Math.fma(_self21, _self21, _self22 * _self22))));
    }

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(java.lang.Math.abs(_t13) > 2.2250738585072014E-308 && java.lang.Math.abs(_t13) < 4.49423283715579E307)) return Double3x3OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t13_inv = 1.0 / _t13;
        dest.putDouble(destOffset, _t6 * _t13_inv);
        dest.putDouble(destOffset + 8, Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv);
        dest.putDouble(destOffset + 16, _t7 * _t13_inv);
        dest.putDouble(destOffset + 24, Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv);
        return invert_api_s7e45b2f0_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t13_inv);
    }

    /** Piece 2 of {@code invert_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_api_s7e45b2f0_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t13_inv) {
        dest.putDouble(destOffset + 32, Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv);
        dest.putDouble(destOffset + 40, Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv);
        dest.putDouble(destOffset + 48, Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv);
        dest.putDouble(destOffset + 56, Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv);
        dest.putDouble(destOffset + 64, Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invert_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
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
        double _sp0 = _t2 * _t33_inv;
        dest.putDouble(destOffset, _t27 * _sp0);
        return invert_degenerate_api_sbb651e39_1(dest, destOffset, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t28, _t1 * _t33_inv, _t0 * _t33_inv, _sp0);
    }

    /** Piece 2 of {@code invert_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_sbb651e39_1(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t28, double _sp2, double _sp1, double _sp0) {
        dest.putDouble(destOffset + 8, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0);
        dest.putDouble(destOffset + 16, _t28 * _sp0);
        dest.putDouble(destOffset + 24, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1);
        dest.putDouble(destOffset + 32, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1);
        dest.putDouble(destOffset + 40, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1);
        dest.putDouble(destOffset + 48, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2);
        dest.putDouble(destOffset + 56, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2);
        dest.putDouble(destOffset + 64, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        return invertProduct_api_sfcc5a20b_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)), Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21)), Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21)));
    }

    /** Piece 2 of {@code invertProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_sfcc5a20b_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t18, double _t19, double _t20) {
        double _t21 = Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11));
        double _t22 = Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01));
        double _t23 = Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11));
        double _t24 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        double _t25 = Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01));
        double _t26 = Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01));
        double _t33 = Math.fma(_t18, _t19, -(_t20 * _t21));
        double _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        double _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(java.lang.Math.abs(_t40) > 2.2250738585072014E-308 && java.lang.Math.abs(_t40) < 4.49423283715579E307)) return Double3x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t40_inv = 1.0 / _t40;
        dest.putDouble(destOffset, _t33 * _t40_inv);
        dest.putDouble(destOffset + 8, Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv);
        dest.putDouble(destOffset + 16, _t34 * _t40_inv);
        return invertProduct_api_sfcc5a20b_2(dest, destOffset, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t40_inv);
    }

    /** Piece 3 of {@code invertProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_sfcc5a20b_2(java.nio.ByteBuffer dest, int destOffset, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t40_inv) {
        dest.putDouble(destOffset + 24, Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv);
        dest.putDouble(destOffset + 32, Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv);
        dest.putDouble(destOffset + 40, Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv);
        dest.putDouble(destOffset + 48, Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv);
        dest.putDouble(destOffset + 56, Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv);
        dest.putDouble(destOffset + 64, Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsByteBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        return invertProduct_degenerate_api_sc8eff87c_1(dest, destOffset, _self00, _self20, _self01, _self21, _self02, _self22, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22, Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)), Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11)), Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11)), Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21)));
    }

    /** Piece 2 of {@code invertProduct_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_sc8eff87c_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self20, double _self01, double _self21, double _self02, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22, double _t18, double _t19, double _t20, double _t21) {
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
        return invertProduct_degenerate_api_sc8eff87c_2(dest, destOffset, _t39, _t40, _t41, _t42, _t43, _t44, _t45, _t46, _t47, _t54, _t55, _t28 * _t60_inv, _t27 * _t60_inv, _t29 * _t60_inv);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_sc8eff87c_2(java.nio.ByteBuffer dest, int destOffset, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47, double _t54, double _t55, double _sp2, double _sp1, double _sp0) {
        dest.putDouble(destOffset, _t54 * _sp0);
        dest.putDouble(destOffset + 8, Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0);
        dest.putDouble(destOffset + 16, _t55 * _sp0);
        dest.putDouble(destOffset + 24, Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1);
        dest.putDouble(destOffset + 32, Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1);
        dest.putDouble(destOffset + 40, Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1);
        dest.putDouble(destOffset + 48, Math.fma(_t47, _t42, -(_t39 * _t45)) * _sp2);
        dest.putDouble(destOffset + 56, Math.fma(_t43, _t45, -(_t46 * _t42)) * _sp2);
        dest.putDouble(destOffset + 64, Math.fma(_t46, _t39, -(_t43 * _t47)) * _sp2);
        return dest;
    }

    public static java.nio.ByteBuffer normal_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normal_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(java.lang.Math.abs(_t13) > 2.2250738585072014E-308 && java.lang.Math.abs(_t13) < 4.49423283715579E307)) return Double3x3OpsKernelsByteBuffer.normal_degenerate(dest, destOffset, src, srcOffset);
        double _t13_inv = 1.0 / _t13;
        dest.putDouble(destOffset, _t6 * _t13_inv);
        dest.putDouble(destOffset + 8, Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv);
        dest.putDouble(destOffset + 16, Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv);
        dest.putDouble(destOffset + 24, Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv);
        return normal_api_s7cf5e1b1_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t13_inv);
    }

    /** Piece 2 of {@code normal_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_api_s7cf5e1b1_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t13_inv) {
        dest.putDouble(destOffset + 32, Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv);
        dest.putDouble(destOffset + 40, Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv);
        dest.putDouble(destOffset + 48, _t7 * _t13_inv);
        dest.putDouble(destOffset + 56, Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv);
        dest.putDouble(destOffset + 64, Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv);
        return dest;
    }

    public static java.nio.ByteBuffer normal_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer normal_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normal_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
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
        double _sp0 = _t2 * _t33_inv;
        dest.putDouble(destOffset, _t27 * _sp0);
        return normal_degenerate_api_s6b87b93a_1(dest, destOffset, _t12, _t13, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t28, _t1 * _t33_inv, _t0 * _t33_inv, _sp0);
    }

    /** Piece 2 of {@code normal_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_s6b87b93a_1(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t28, double _sp2, double _sp1, double _sp0) {
        dest.putDouble(destOffset + 8, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1);
        dest.putDouble(destOffset + 16, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2);
        dest.putDouble(destOffset + 24, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0);
        dest.putDouble(destOffset + 32, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1);
        dest.putDouble(destOffset + 40, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2);
        dest.putDouble(destOffset + 48, _t28 * _sp0);
        dest.putDouble(destOffset + 56, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1);
        dest.putDouble(destOffset + 64, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2);
        return dest;
    }

    public static double trace_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3x3OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static double trace_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getDouble(srcOffset + 64) + (src.getDouble(srcOffset) + src.getDouble(srcOffset + 32));
    }

    public static java.nio.ByteBuffer transpose_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer transpose_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self02);
        dest.putDouble(destOffset + 24, _self10);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self12);
        dest.putDouble(destOffset + 48, _self20);
        dest.putDouble(destOffset + 56, _self21);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            double _eother = other.getDouble(otherOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, _eother + _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, scalar * _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, -_eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            double _eother = other.getDouble(otherOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, _eself - _eother);
        }
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _ev = v.getDouble(vOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, _ev);
        }
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m10 = m.getDouble(mOffset + 8);
        double _m01 = m.getDouble(mOffset + 16);
        double _m11 = m.getDouble(mOffset + 24);
        dest.putDouble(destOffset, _m00);
        dest.putDouble(destOffset + 8, _m10);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _m01);
        dest.putDouble(destOffset + 32, _m11);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x3OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m10 = m.getDouble(mOffset + 8);
        double _m01 = m.getDouble(mOffset + 16);
        double _m11 = m.getDouble(mOffset + 24);
        double _m02 = m.getDouble(mOffset + 32);
        double _m12 = m.getDouble(mOffset + 40);
        dest.putDouble(destOffset, _m00);
        dest.putDouble(destOffset + 8, _m10);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _m01);
        dest.putDouble(destOffset + 32, _m11);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, _m02);
        dest.putDouble(destOffset + 56, _m12);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x3OpsKernelsAddress.setMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m01 = m.getDouble(mOffset + 8);
        double _m02 = m.getDouble(mOffset + 16);
        double _m10 = m.getDouble(mOffset + 32);
        double _m11 = m.getDouble(mOffset + 40);
        double _m12 = m.getDouble(mOffset + 48);
        double _m20 = m.getDouble(mOffset + 64);
        double _m21 = m.getDouble(mOffset + 72);
        double _m22 = m.getDouble(mOffset + 80);
        dest.putDouble(destOffset, _m00);
        dest.putDouble(destOffset + 8, _m10);
        dest.putDouble(destOffset + 16, _m20);
        dest.putDouble(destOffset + 24, _m01);
        dest.putDouble(destOffset + 32, _m11);
        dest.putDouble(destOffset + 40, _m21);
        dest.putDouble(destOffset + 48, _m02);
        dest.putDouble(destOffset + 56, _m12);
        dest.putDouble(destOffset + 64, _m22);
        return dest;
    }

    public static java.nio.ByteBuffer setMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x3OpsKernelsAddress.setMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            int _lom = _l * 4;
            double _em0 = m.getDouble(mOffset + _lom * 8);
            double _em1 = m.getDouble(mOffset + (_lom + 1) * 8);
            double _em2 = m.getDouble(mOffset + (_lom + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, _em0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _em1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _em2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double tX, double tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double tX, double tY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, tX);
        dest.putDouble(destOffset + 56, tY);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Double3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self22 = src.getDouble(srcOffset + 64);
        double _tx = t.getDouble(tOffset);
        double _ty = t.getDouble(tOffset + 8);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, _tx);
        dest.putDouble(destOffset + 56, _ty);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromRigid_unsafe(java.nio.ByteBuffer dest, int destOffset, double rRX, double rRY, double rRZ, double rRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeFromRigid_unsafe(_destBase, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromRigid_api(java.nio.ByteBuffer dest, int destOffset, double rRX, double rRY, double rRZ, double rRW) {
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        dest.putDouble(destOffset, Math.fma(-2.0, Math.fma(rRY, rRY, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(rRX, rRY, _t1));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(rRX, rRZ, -_t2));
        dest.putDouble(destOffset + 24, 2.0 * Math.fma(rRX, rRY, -_t1));
        dest.putDouble(destOffset + 32, Math.fma(-2.0, Math.fma(rRX, rRX, _t0), 1.0));
        dest.putDouble(destOffset + 40, 2.0 * Math.fma(rRX, rRW, rRY * rRZ));
        dest.putDouble(destOffset + 48, 2.0 * Math.fma(rRX, rRZ, _t2));
        dest.putDouble(destOffset + 56, 2.0 * Math.fma(rRY, rRZ, -(rRX * rRW)));
        dest.putDouble(destOffset + 64, Math.fma(-2.0, Math.fma(rRX, rRX, rRY * rRY), 1.0));
        return dest;
    }

    public static java.nio.ByteBuffer makeFromTransform_unsafe(java.nio.ByteBuffer dest, int destOffset, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeFromTransform_unsafe(_destBase, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromTransform_api(java.nio.ByteBuffer dest, int destOffset, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        dest.putDouble(destOffset, Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX));
        dest.putDouble(destOffset + 8, Math.fma(tRX, tRY, _t4) * _t0);
        dest.putDouble(destOffset + 16, Math.fma(tRX, tRZ, -_t5) * _t0);
        dest.putDouble(destOffset + 24, Math.fma(tRX, tRY, -_t4) * _t1);
        dest.putDouble(destOffset + 32, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY));
        dest.putDouble(destOffset + 40, Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        dest.putDouble(destOffset + 48, Math.fma(tRX, tRZ, _t5) * _t2);
        dest.putDouble(destOffset + 56, Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        dest.putDouble(destOffset + 64, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        return dest;
    }

    public static java.nio.ByteBuffer to2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self01);
        dest.putDouble(destOffset + 24, _self11);
        return dest;
    }

    public static java.nio.ByteBuffer to2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _loself = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _loself * 8);
            double _eself1 = src.getDouble(srcOffset + (_loself + 1) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer to3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.to3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self02);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _self10);
        dest.putDouble(destOffset + 40, _self11);
        dest.putDouble(destOffset + 48, _self12);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, _self20);
        dest.putDouble(destOffset + 72, _self21);
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer to4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.to4x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _self01);
        dest.putDouble(destOffset + 40, _self11);
        dest.putDouble(destOffset + 48, _self21);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, _self02);
        dest.putDouble(destOffset + 72, _self12);
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, 0.0);
        dest.putDouble(destOffset + 96, 0.0);
        dest.putDouble(destOffset + 104, 0.0);
        dest.putDouble(destOffset + 112, 0.0);
        dest.putDouble(destOffset + 120, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = 1.0 - _self00;
        double _t13 = _self22 + (_self00 + _self11);
        double _t14 = 1.0 + _t13;
        double _t15 = _self00 + (1.0 - _self11 - _self22);
        double _t16 = _self11 + (_t1 - _self22);
        double _t17 = _self22 + (_t1 - _self11);
        return toDualQuat_api_s7675600_1(dest, destOffset, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t13, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code toDualQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toDualQuat_api_s7675600_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self11, double _self22, double _t3, double _t5, double _t6, double _t7, double _t8, double _t9, double _t13, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t13 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t3);
            dest.putDouble(destOffset + 8, _sp0 * _t7);
            dest.putDouble(destOffset + 16, _sp0 * _t9);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.putDouble(destOffset + 8, _sp3 * _t5);
                dest.putDouble(destOffset + 16, _sp3 * _t6);
                dest.putDouble(destOffset + 24, _sp3 * _t3);
            } else {
                if (_self11 > _self22) {
                    dest.putDouble(destOffset, _sp1 * _t5);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.putDouble(destOffset + 16, _sp1 * _t8);
                    dest.putDouble(destOffset + 24, _sp1 * _t7);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t6);
                    dest.putDouble(destOffset + 8, _sp2 * _t8);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.putDouble(destOffset + 24, _sp2 * _t9);
                }
            }
        }
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.toRigid_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        toRigid_api_s6c0c7a5e_515(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _ct0, _ct1, _ct2);
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        return dest;
    }

    /** Part of {@code toRigid_api}, split to fit the inline budget; reached only through it. */
    private static void toRigid_api_s6c0c7a5e_515(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _ct0, double _ct1, double _ct2) {
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _t18 = _self10 * _t17;
        double _t19 = _self22 * _t16;
        double _t20 = _self12 * _t16;
        double _t21 = _self20 * _t17;
        double _t23 = _self21 * _t15;
        double _t24 = _self11 * _t15;
        double _t26 = _self00 * _t17;
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
        double _t63 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t51));
        toRigid_api_s6c0c7a5e_515_s2d91c422_1(dest, destOffset, _self11, _self22, -_self11, -_self22, _t15, _t16, _t19, _t24, Math.fma(_self12, _t16, _t23), Math.fma(_self21, _t15, -_t20), _t47, _t51, 1.0 - _t47, Math.fma(_self01, _t15, _t48), Math.fma(_self02, _t16, _t49), Math.fma(_self02, _t16, -_t49), Math.fma(-_self01, _t15, _t48), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 2 of {@code toRigid_api_s6c0c7a5e_515}, split to fit the inline budget; reached only through it. */
    private static void toRigid_api_s6c0c7a5e_515_s2d91c422_1(java.nio.ByteBuffer dest, int destOffset, double _self11, double _self22, double _t0, double _t1, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t51, double _t52, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0) {
        double _t65 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t52));
        double _t66 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t52));
        double _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        toRigid_api_s6c0c7a5e_515_s2d91c422_2(dest, destOffset, _self11, _self22, _t15, _t16, _t19, _t24, _t31, _t35, _t47, _t54, _t55, _t56, _t57, _t63, _sp0, _t65, _t66, _t67, 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)));
    }

    /** Piece 3 of {@code toRigid_api_s6c0c7a5e_515}, split to fit the inline budget; reached only through it. */
    private static void toRigid_api_s6c0c7a5e_515_s2d91c422_2(java.nio.ByteBuffer dest, int destOffset, double _self11, double _self22, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65, double _t66, double _t67, double _sp1, double _sp2, double _sp3) {
        if (Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t47)) > 0.0) {
            dest.putDouble(destOffset + 24, _sp0 * _t35);
            dest.putDouble(destOffset + 32, _sp0 * _t56);
            dest.putDouble(destOffset + 40, _sp0 * _t57);
            dest.putDouble(destOffset + 48, 0.5 * java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t67));
                dest.putDouble(destOffset + 32, _sp3 * _t54);
                dest.putDouble(destOffset + 40, _sp3 * _t55);
                dest.putDouble(destOffset + 48, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.putDouble(destOffset + 24, _sp1 * _t54);
                    dest.putDouble(destOffset + 32, 0.5 * java.lang.Math.sqrt(_t65));
                    dest.putDouble(destOffset + 40, _sp1 * _t31);
                    dest.putDouble(destOffset + 48, _sp1 * _t56);
                } else {
                    dest.putDouble(destOffset + 24, _sp2 * _t55);
                    dest.putDouble(destOffset + 32, _sp2 * _t31);
                    dest.putDouble(destOffset + 40, 0.5 * java.lang.Math.sqrt(_t66));
                    dest.putDouble(destOffset + 48, _sp2 * _t57);
                }
            }
        }
    }

    public static java.nio.ByteBuffer toRigid_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.toRigid_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.toRigid_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer toRigid_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.toRigid_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
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
            dest.putDouble(destOffset + 24, _sp0 * _t182);
            dest.putDouble(destOffset + 32, _sp0 * _t201);
            dest.putDouble(destOffset + 40, _sp0 * _t202);
            dest.putDouble(destOffset + 48, 0.5 * java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t208));
                dest.putDouble(destOffset + 32, _sp3 * _t199);
                dest.putDouble(destOffset + 40, _sp3 * _t200);
                dest.putDouble(destOffset + 48, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.putDouble(destOffset + 24, _sp1 * _t199);
                    dest.putDouble(destOffset + 32, 0.5 * java.lang.Math.sqrt(_t209));
                    dest.putDouble(destOffset + 40, _sp1 * _t184);
                    dest.putDouble(destOffset + 48, _sp1 * _t201);
                } else {
                    dest.putDouble(destOffset + 24, _sp2 * _t200);
                    dest.putDouble(destOffset + 32, _sp2 * _t184);
                    dest.putDouble(destOffset + 40, 0.5 * java.lang.Math.sqrt(_t210));
                    dest.putDouble(destOffset + 48, _sp2 * _t202);
                }
            }
        }
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.toTransform_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t14 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        return toTransform_api_s67e81cf7_1(dest, destOffset, _self00, _self20, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, (1.0 / java.lang.Math.sqrt(_t12)), _t16, _t18, _t17, _self10 * _t17, _self22 * _t16);
    }

    /** Piece 2 of {@code toTransform_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_s67e81cf7_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t17, double _t19, double _t20) {
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
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        double _t64 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52));
        return toTransform_api_s67e81cf7_2(dest, destOffset, _self22, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, Math.fma(_self12, _t16, _t24), Math.fma(_self21, _t15, -_t21), java.lang.Math.max(_t25, _t20), _t47, _t48, _t52, _t53, Math.fma(_self01, _t15, _t49), Math.fma(_self02, _t16, _t50), Math.fma(_self02, _t16, -_t50), Math.fma(-_self01, _t15, _t49), Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48)), _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53)));
    }

    /** Piece 3 of {@code toTransform_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_s67e81cf7_2(java.nio.ByteBuffer dest, int destOffset, double _self22, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t66) {
        double _t67 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53));
        double _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t68));
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        dest.putDouble(destOffset + 32, _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        return toTransform_api_s67e81cf7_3(dest, destOffset, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t56, _t57, _t58, _t63, _t64, _sp0, _t67, _sp1, _sp2, _sp3);
    }

    /** Piece 4 of {@code toTransform_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_s67e81cf7_3(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t13, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t67, double _sp1, double _sp2, double _sp3) {
        dest.putDouble(destOffset + 40, _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * java.lang.Math.sqrt(_t67));
        dest.putDouble(destOffset + 48, _t63 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        dest.putDouble(destOffset + 56, _t47 < 0.0 ? -_t18 : _t18);
        dest.putDouble(destOffset + 64, java.lang.Math.sqrt(_t12));
        dest.putDouble(destOffset + 72, java.lang.Math.sqrt(_t13));
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.toTransform_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.toTransform_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer toTransform_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.toTransform_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
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
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.putDouble(destOffset + 32, _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        dest.putDouble(destOffset + 40, _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213));
        dest.putDouble(destOffset + 48, _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.putDouble(destOffset + 56, _t196 < 0.0 ? -_t56 : _t56);
        dest.putDouble(destOffset + 64, _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0);
        dest.putDouble(destOffset + 72, _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.decomposeRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
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
        double _t29 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23));
        return decomposeRotation_api_sa81ca14f_1(dest, destOffset, _self02, _self12, _self22, _t7, _t8, _t9, -Math.fma(_self22, _t7, Math.fma(_self02, _t8, _self12 * _t9)), _t21, _t22, _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code decomposeRotation_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_sa81ca14f_1(java.nio.ByteBuffer dest, int destOffset, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t20, double _t21, double _t22, double _t23, double _t29, double _t30) {
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
        return decomposeRotation_api_sa81ca14f_2(dest, destOffset, _t7, _t8, _t9, _t34, _t35, _t36, _t54, _t55, _t56, _t35 - _t54, _t35 + _t54);
    }

    /** Piece 3 of {@code decomposeRotation_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_sa81ca14f_2(java.nio.ByteBuffer dest, int destOffset, double _t7, double _t8, double _t9, double _t34, double _t35, double _t36, double _t54, double _t55, double _t56, double _t60, double _t63) {
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
        return decomposeRotation_api_sa81ca14f_3(dest, destOffset, _t36, _t56, _t60, _t63, _t73, _t74 + _t34, _t74 - _t34, _t75 + _t55, _t55 - _t75, _t82, _t86, _t87, _t88, _t89, 0.5 * (1.0 / java.lang.Math.sqrt(_t86)), 0.5 * (1.0 / java.lang.Math.sqrt(_t88)), 0.5 * (1.0 / java.lang.Math.sqrt(_t89)), 0.5 * (1.0 / java.lang.Math.sqrt(_t87)));
    }

    /** Piece 4 of {@code decomposeRotation_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeRotation_api_sa81ca14f_3(java.nio.ByteBuffer dest, int destOffset, double _t36, double _t56, double _t60, double _t63, double _t73, double _t77, double _t78, double _t80, double _t81, double _t82, double _t86, double _t87, double _t88, double _t89, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t82 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t60);
            dest.putDouble(destOffset + 8, _sp0 * _t81);
            dest.putDouble(destOffset + 16, _sp0 * _t78);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t86));
        } else {
            if (_t73 > java.lang.Math.max(_t36, _t56)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t87));
                dest.putDouble(destOffset + 8, _sp3 * _t77);
                dest.putDouble(destOffset + 16, _sp3 * _t80);
                dest.putDouble(destOffset + 24, _sp3 * _t60);
            } else {
                if (_t36 > _t56) {
                    dest.putDouble(destOffset, _sp1 * _t77);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t88));
                    dest.putDouble(destOffset + 16, _sp1 * _t63);
                    dest.putDouble(destOffset + 24, _sp1 * _t81);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t80);
                    dest.putDouble(destOffset + 8, _sp2 * _t63);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t89));
                    dest.putDouble(destOffset + 24, _sp2 * _t78);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer decomposeScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.decomposeScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
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
        double _t27 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        return decomposeScale_api_sb16b4407_1(dest, destOffset, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10)), _t19, _t20, _t21, _t27, (1.0 / java.lang.Math.sqrt(_t27)));
    }

    /** Piece 2 of {@code decomposeScale_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeScale_api_sb16b4407_1(java.nio.ByteBuffer dest, int destOffset, double _self02, double _self12, double _self22, double _t4, double _t8, double _t9, double _t10, double _t18, double _t19, double _t20, double _t21, double _t27, double _t28) {
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
        dest.putDouble(destOffset, Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0 ? -_t4 : _t4);
        dest.putDouble(destOffset + 8, java.lang.Math.sqrt(_t27));
        dest.putDouble(destOffset + 16, java.lang.Math.sqrt(_t47));
        return dest;
    }

    public static java.nio.ByteBuffer decomposeSkew_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.decomposeSkew_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeSkew_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
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
        double _t19 = Math.fma(_t17, _t7, _self21);
        double _t20 = Math.fma(_t17, _t8, _self01);
        double _t21 = Math.fma(_t17, _t9, _self11);
        double _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        double _t27 = (1.0 / java.lang.Math.sqrt(_t26));
        return decomposeSkew_api_s87c0c1ff_1(dest, destOffset, _self02, _self12, _self22, _t7, _t8, _t9, _t14, -_t14, _t19, _t20, _t21, _t26, _t27, _t15 * _t27);
    }

    /** Piece 2 of {@code decomposeSkew_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_s87c0c1ff_1(java.nio.ByteBuffer dest, int destOffset, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t14, double _t16, double _t19, double _t20, double _t21, double _t26, double _t27, double _t28) {
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
        return decomposeSkew_api_s87c0c1ff_2(dest, destOffset, _t7, _t8, _t9, _t28, _t32, _t33, _t34, _t37, _t48, _t14 * _t48, _t53, _t54, _t55);
    }

    /** Piece 3 of {@code decomposeSkew_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSkew_api_s87c0c1ff_2(java.nio.ByteBuffer dest, int destOffset, double _t7, double _t8, double _t9, double _t28, double _t32, double _t33, double _t34, double _t37, double _t48, double _t49, double _t53, double _t54, double _t55) {
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0) {
            dest.putDouble(destOffset + 8, -_t49);
            dest.putDouble(destOffset + 16, -_t28);
        } else {
            dest.putDouble(destOffset + 8, _t49);
            dest.putDouble(destOffset + 16, _t28);
        }
        dest.putDouble(destOffset, _t37 * _t48);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            double _eother = other.getDouble(otherOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, Math.fma(t, _eother - _eself, _eself));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = right.getDouble(rightOffset + _lo * 8);
            double _eright1 = right.getDouble(rightOffset + (_lo + 1) * 8);
            double _eright2 = right.getDouble(rightOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.putDouble(destOffset + (_lo + 2) * 8, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double3x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        dest.putDouble(destOffset, Math.fma(_right00, _self00, _right10 * _self01));
        dest.putDouble(destOffset + 8, Math.fma(_right00, _self10, _right10 * _self11));
        dest.putDouble(destOffset + 16, Math.fma(_right00, _self20, _right10 * _self21));
        dest.putDouble(destOffset + 24, Math.fma(_right01, _self00, _right11 * _self01));
        dest.putDouble(destOffset + 32, Math.fma(_right01, _self10, _right11 * _self11));
        dest.putDouble(destOffset + 40, Math.fma(_right01, _self20, _right11 * _self21));
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double3x3OpsKernelsAddress.mulMat2x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        double _right02 = right.getDouble(rightOffset + 32);
        double _right12 = right.getDouble(rightOffset + 40);
        dest.putDouble(destOffset, Math.fma(_right00, _self00, _right10 * _self01));
        dest.putDouble(destOffset + 8, Math.fma(_right00, _self10, _right10 * _self11));
        dest.putDouble(destOffset + 16, Math.fma(_right00, _self20, _right10 * _self21));
        dest.putDouble(destOffset + 24, Math.fma(_right01, _self00, _right11 * _self01));
        dest.putDouble(destOffset + 32, Math.fma(_right01, _self10, _right11 * _self11));
        dest.putDouble(destOffset + 40, Math.fma(_right01, _self20, _right11 * _self21));
        return mulMat2x3_api_s1cb2fb63_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _right02, _right12);
    }

    /** Piece 2 of {@code mulMat2x3_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer mulMat2x3_api_s1cb2fb63_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _right02, double _right12) {
        dest.putDouble(destOffset + 48, Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02)));
        dest.putDouble(destOffset + 56, Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12)));
        dest.putDouble(destOffset + 64, Math.fma(_right02, _self20, Math.fma(_right12, _self21, _self22)));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.putDouble(destOffset + (_lo + 2) * 8, Math.fma(_other22, _eself2, Math.fma(_other20, _eself0, _other21 * _eself1)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_other00, _eself0, _other01 * _eself1));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_other10, _eself0, _other11 * _eself1));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.preMulMat2x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        double _other02 = other.getDouble(otherOffset + 32);
        double _other12 = other.getDouble(otherOffset + 40);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            double _eother = other.getDouble(otherOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, Math.fma(weight, _eother, _eself));
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, colZ, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_api(java.nio.ByteBuffer dest, int destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        dest.putDouble(destOffset, colX * rowX);
        dest.putDouble(destOffset + 8, colY * rowX);
        dest.putDouble(destOffset + 16, colZ * rowX);
        dest.putDouble(destOffset + 24, colX * rowY);
        dest.putDouble(destOffset + 32, colY * rowY);
        dest.putDouble(destOffset + 40, colZ * rowY);
        dest.putDouble(destOffset + 48, colX * rowZ);
        dest.putDouble(destOffset + 56, colY * rowZ);
        dest.putDouble(destOffset + 64, colZ * rowZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _colBase = UnsafeOpsHolder.U.getLong(col, UnsafeCopy.BB_ADDRESS_OFFSET) + colOffset;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset;
        Double3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        double _colx = col.getDouble(colOffset);
        double _coly = col.getDouble(colOffset + 8);
        double _colz = col.getDouble(colOffset + 16);
        double _rowx = row.getDouble(rowOffset);
        double _rowy = row.getDouble(rowOffset + 8);
        double _rowz = row.getDouble(rowOffset + 16);
        dest.putDouble(destOffset, _colx * _rowx);
        dest.putDouble(destOffset + 8, _coly * _rowx);
        dest.putDouble(destOffset + 16, _colz * _rowx);
        dest.putDouble(destOffset + 24, _colx * _rowy);
        dest.putDouble(destOffset + 32, _coly * _rowy);
        dest.putDouble(destOffset + 40, _colz * _rowy);
        dest.putDouble(destOffset + 48, _colx * _rowz);
        dest.putDouble(destOffset + 56, _coly * _rowz);
        dest.putDouble(destOffset + 64, _colz * _rowz);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
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
        return lookAlong_api_sf826c81c_1(dest, destOffset, upX, upY, upZ, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_sf826c81c_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
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
        dest.putDouble(destOffset, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putDouble(destOffset + 8, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        dest.putDouble(destOffset + 24, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        return lookAlong_api_sf826c81c_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_sf826c81c_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putDouble(destOffset + 40, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
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
        return lookAlong_api_s2f25056e_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _upx, _upy, _upz, _t11, _t12, _t13, Math.fma(_t17, _t12, _upx), Math.fma(_t17, _t13, _upy), Math.fma(_t17, _t11, _upz));
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_s2f25056e_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20) {
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
        dest.putDouble(destOffset, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putDouble(destOffset + 8, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        return lookAlong_api_s2f25056e_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, Math.fma(_t39, _t12, -(_t38 * _t13)), Math.fma(_t37, _t13, -(_t39 * _t11)), Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_s2f25056e_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        dest.putDouble(destOffset + 24, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putDouble(destOffset + 40, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_api(java.nio.ByteBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW) {
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = Math.fma(-2.0, dqRZ * dqRZ, 1.0);
        dest.putDouble(destOffset, Math.fma(-2.0, _t0, _t6));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(dqRX, dqRY, _t2));
        dest.putDouble(destOffset + 16, Math.fma(-2.0, _t3, _sp0 * dqRZ));
        dest.putDouble(destOffset + 24, Math.fma(-2.0, _t2, _sp0 * dqRY));
        dest.putDouble(destOffset + 32, Math.fma(-2.0, _t4, _t6));
        dest.putDouble(destOffset + 40, 2.0 * Math.fma(dqRX, dqRW, _t5));
        dest.putDouble(destOffset + 48, 2.0 * Math.fma(dqRX, dqRZ, _t3));
        dest.putDouble(destOffset + 56, Math.fma(-2.0, dqRX * dqRW, _t5 + _t5));
        dest.putDouble(destOffset + 64, Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, _t1);
        dest.putDouble(destOffset + 8, _t0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, -_t0);
        dest.putDouble(destOffset + 32, _t1);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
        dest.putDouble(destOffset, Math.fma(_t5, axisX * axisX, _t1));
        dest.putDouble(destOffset + 8, Math.fma(axisZ, _t0, _t5 * _t2));
        dest.putDouble(destOffset + 16, Math.fma(_t5, _t3, -(axisY * _t0)));
        dest.putDouble(destOffset + 24, Math.fma(_t5, _t2, -(axisZ * _t0)));
        dest.putDouble(destOffset + 32, Math.fma(_t5, axisY * axisY, _t1));
        dest.putDouble(destOffset + 40, Math.fma(axisX, _t0, _t5 * _t4));
        dest.putDouble(destOffset + 48, Math.fma(axisY, _t0, _t5 * _t3));
        dest.putDouble(destOffset + 56, Math.fma(_t5, _t4, -(axisX * _t0)));
        dest.putDouble(destOffset + 64, Math.fma(_t5, axisZ * axisZ, _t1));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Double3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisy;
        double _t3 = _axisx * _axisz;
        double _t4 = _axisy * _axisz;
        double _t5 = 1.0 - _t1;
        dest.putDouble(destOffset, Math.fma(_t5, _axisx * _axisx, _t1));
        dest.putDouble(destOffset + 8, Math.fma(_axisz, _t0, _t5 * _t2));
        dest.putDouble(destOffset + 16, Math.fma(_t5, _t3, -(_axisy * _t0)));
        dest.putDouble(destOffset + 24, Math.fma(_t5, _t2, -(_axisz * _t0)));
        dest.putDouble(destOffset + 32, Math.fma(_t5, _axisy * _axisy, _t1));
        dest.putDouble(destOffset + 40, Math.fma(_axisx, _t0, _t5 * _t4));
        dest.putDouble(destOffset + 48, Math.fma(_axisy, _t0, _t5 * _t3));
        dest.putDouble(destOffset + 56, Math.fma(_t5, _t4, -(_axisx * _t0)));
        dest.putDouble(destOffset + 64, Math.fma(_t5, _axisz * _axisz, _t1));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, _t39);
        return makeRotationLookAlong_api_scb8583d0_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_scb8583d0_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 16, _t38);
        dest.putDouble(destOffset + 24, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putDouble(destOffset + 32, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putDouble(destOffset + 40, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 48, _t12);
        dest.putDouble(destOffset + 56, _t13);
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
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
        return makeRotationLookAlong_api_s64af833a_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_s64af833a_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
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
        dest.putDouble(destOffset, _t37);
        dest.putDouble(destOffset + 8, _t39);
        dest.putDouble(destOffset + 16, _t38);
        dest.putDouble(destOffset + 24, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putDouble(destOffset + 32, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putDouble(destOffset + 40, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 48, _t12);
        dest.putDouble(destOffset + 56, _t13);
        dest.putDouble(destOffset + 64, _t11);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_api(java.nio.ByteBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        dest.putDouble(destOffset, Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(qX, qY, _t1));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(qX, qZ, -_t2));
        dest.putDouble(destOffset + 24, 2.0 * Math.fma(qX, qY, -_t1));
        dest.putDouble(destOffset + 32, Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0));
        dest.putDouble(destOffset + 40, 2.0 * Math.fma(qX, qW, qY * qZ));
        dest.putDouble(destOffset + 48, 2.0 * Math.fma(qX, qZ, _t2));
        dest.putDouble(destOffset + 56, 2.0 * Math.fma(qY, qZ, -(qX * qW)));
        dest.putDouble(destOffset + 64, Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset;
        Double3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, _qBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        double _qx = q.getDouble(qOffset);
        double _qy = q.getDouble(qOffset + 8);
        double _qz = q.getDouble(qOffset + 16);
        double _qw = q.getDouble(qOffset + 24);
        double _t0 = _qz * _qz;
        double _t1 = _qz * _qw;
        double _t2 = _qy * _qw;
        dest.putDouble(destOffset, Math.fma(-2.0, Math.fma(_qy, _qy, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(_qx, _qy, _t1));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(_qx, _qz, -_t2));
        dest.putDouble(destOffset + 24, 2.0 * Math.fma(_qx, _qy, -_t1));
        dest.putDouble(destOffset + 32, Math.fma(-2.0, Math.fma(_qx, _qx, _t0), 1.0));
        dest.putDouble(destOffset + 40, 2.0 * Math.fma(_qx, _qw, _qy * _qz));
        dest.putDouble(destOffset + 48, 2.0 * Math.fma(_qx, _qz, _t2));
        dest.putDouble(destOffset + 56, 2.0 * Math.fma(_qy, _qz, -(_qx * _qw)));
        dest.putDouble(destOffset + 64, Math.fma(-2.0, Math.fma(_qx, _qx, _qy * _qy), 1.0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t1);
        dest.putDouble(destOffset + 40, _t0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, -_t0);
        dest.putDouble(destOffset + 64, _t1);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_api(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t0;
        double _t7 = _t0 * _t5;
        dest.putDouble(destOffset, _t3 * _t4);
        dest.putDouble(destOffset + 8, Math.fma(_t6, _t4, _t1 * _t5));
        dest.putDouble(destOffset + 16, Math.fma(_t2, _t1, -(_t7 * _t4)));
        dest.putDouble(destOffset + 24, -(_t1 * _t3));
        dest.putDouble(destOffset + 32, Math.fma(_t5, _t4, -(_t6 * _t1)));
        dest.putDouble(destOffset + 40, Math.fma(_t7, _t1, _t2 * _t4));
        dest.putDouble(destOffset + 48, _t0);
        dest.putDouble(destOffset + 56, -(_t2 * _t3));
        dest.putDouble(destOffset + 64, _t5 * _t3);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_api(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t1 * _t5;
        dest.putDouble(destOffset, _t3 * _t4);
        dest.putDouble(destOffset + 8, Math.fma(_t7, _t3, _t2 * _t0));
        dest.putDouble(destOffset + 16, Math.fma(_t6, _t3, -(_t0 * _t5)));
        dest.putDouble(destOffset + 24, -_t1);
        dest.putDouble(destOffset + 32, _t5 * _t4);
        dest.putDouble(destOffset + 40, _t2 * _t4);
        dest.putDouble(destOffset + 48, _t0 * _t4);
        dest.putDouble(destOffset + 56, Math.fma(_t7, _t0, -(_t2 * _t3)));
        dest.putDouble(destOffset + 64, Math.fma(_t6, _t0, _t5 * _t3));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, _t1);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, -_t0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, _t0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, _t1);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_api(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        dest.putDouble(destOffset, Math.fma(_t6, _t2, _t3 * _t4));
        dest.putDouble(destOffset + 8, _t2 * _t5);
        dest.putDouble(destOffset + 16, Math.fma(_t7, _t2, -(_t1 * _t4)));
        dest.putDouble(destOffset + 24, Math.fma(_t6, _t4, -(_t2 * _t3)));
        dest.putDouble(destOffset + 32, _t5 * _t4);
        dest.putDouble(destOffset + 40, Math.fma(_t7, _t4, _t1 * _t2));
        dest.putDouble(destOffset + 48, _t1 * _t5);
        dest.putDouble(destOffset + 56, -_t0);
        dest.putDouble(destOffset + 64, _t5 * _t3);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_api(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t1 * _t3;
        dest.putDouble(destOffset, _t3 * _t4);
        dest.putDouble(destOffset + 8, _t1);
        dest.putDouble(destOffset + 16, -(_t0 * _t4));
        dest.putDouble(destOffset + 24, Math.fma(_t2, _t0, -(_t7 * _t5)));
        dest.putDouble(destOffset + 32, _t5 * _t4);
        dest.putDouble(destOffset + 40, Math.fma(_t6, _t5, _t2 * _t3));
        dest.putDouble(destOffset + 48, Math.fma(_t7, _t2, _t0 * _t5));
        dest.putDouble(destOffset + 56, -(_t2 * _t4));
        dest.putDouble(destOffset + 64, Math.fma(_t5, _t3, -(_t6 * _t2)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_api(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        dest.putDouble(destOffset, Math.fma(_t3, _t4, -(_t6 * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_t7, _t0, _t1 * _t3));
        dest.putDouble(destOffset + 16, -(_t0 * _t5));
        dest.putDouble(destOffset + 24, -(_t1 * _t5));
        dest.putDouble(destOffset + 32, _t5 * _t4);
        dest.putDouble(destOffset + 40, _t2);
        dest.putDouble(destOffset + 48, Math.fma(_t6, _t3, _t0 * _t4));
        dest.putDouble(destOffset + 56, Math.fma(_t0, _t1, -(_t7 * _t3)));
        dest.putDouble(destOffset + 64, _t5 * _t3);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_api(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t4;
        dest.putDouble(destOffset, _t3 * _t4);
        dest.putDouble(destOffset + 8, _t1 * _t3);
        dest.putDouble(destOffset + 16, -_t0);
        dest.putDouble(destOffset + 24, Math.fma(_t7, _t2, -(_t1 * _t5)));
        dest.putDouble(destOffset + 32, Math.fma(_t6, _t2, _t5 * _t4));
        dest.putDouble(destOffset + 40, _t2 * _t3);
        dest.putDouble(destOffset + 48, Math.fma(_t7, _t5, _t2 * _t1));
        dest.putDouble(destOffset + 56, Math.fma(_t6, _t5, -(_t2 * _t4)));
        dest.putDouble(destOffset + 64, _t5 * _t3);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        dest.putDouble(destOffset, vX);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, vY);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset, _vx);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _vy);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, double s) {
        dest.putDouble(destOffset, s);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, s);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, vX);
        dest.putDouble(destOffset + 56, vY);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, _vx);
        dest.putDouble(destOffset + 56, _vy);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeView_unsafe(java.nio.ByteBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.ByteBuffer makeView_api(java.nio.ByteBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dest.putDouble(destOffset, _t0_inv + _t0_inv);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t1_inv + _t1_inv);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, -((left + right) * _t0_inv));
        dest.putDouble(destOffset + 56, -((bottom + top) * _t1_inv));
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_self00, _t0, _self10 * _t1));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.putDouble(destOffset + 32, Math.fma(_self01, _t0, _self11 * _t1));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t1, -(_self12 * _t0)));
        dest.putDouble(destOffset + 56, Math.fma(_self02, _t0, _self12 * _t1));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.sin(0.5 * angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t3 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t2 + _t2) * _t2;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dest.putDouble(destOffset, Math.fma(_self20, _t9, Math.fma(_self00, _t3, -(_self10 * _t0))));
        dest.putDouble(destOffset + 8, Math.fma(_self20, _t10, Math.fma(_self00, _t0, _self10 * _t3)));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, Math.fma(_self21, _t9, Math.fma(_self01, _t3, -(_self11 * _t0))));
        dest.putDouble(destOffset + 32, Math.fma(_self21, _t10, Math.fma(_self01, _t0, _self11 * _t3)));
        dest.putDouble(destOffset + 40, _self21);
        return preRotateAround_api_s97d5b178_1(dest, destOffset, _t0, _self02, _self12, _self22, _t3, _t9, _t10);
    }

    /** Piece 2 of {@code preRotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_s97d5b178_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self02, double _self12, double _self22, double _t3, double _t9, double _t10) {
        dest.putDouble(destOffset + 48, Math.fma(_self22, _t9, Math.fma(_self02, _t3, -(_self12 * _t0))));
        dest.putDouble(destOffset + 56, Math.fma(_self22, _t10, Math.fma(_self02, _t0, _self12 * _t3)));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.sin(0.5 * angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _t3 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t2 + _t2) * _t2;
        double _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        double _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        dest.putDouble(destOffset, Math.fma(_self20, _t9, Math.fma(_self00, _t3, -(_self10 * _t0))));
        dest.putDouble(destOffset + 8, Math.fma(_self20, _t10, Math.fma(_self00, _t0, _self10 * _t3)));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, Math.fma(_self21, _t9, Math.fma(_self01, _t3, -(_self11 * _t0))));
        return preRotateAround_api_sb0da982d_1(dest, destOffset, _t0, _self01, _self11, _self21, _self02, _self12, _self22, _t3, _t9, _t10);
    }

    /** Piece 2 of {@code preRotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_sb0da982d_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t3, double _t9, double _t10) {
        dest.putDouble(destOffset + 32, Math.fma(_self21, _t10, Math.fma(_self01, _t0, _self11 * _t3)));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, Math.fma(_self22, _t9, Math.fma(_self02, _t3, -(_self12 * _t0))));
        dest.putDouble(destOffset + 56, Math.fma(_self22, _t10, Math.fma(_self02, _t0, _self12 * _t3)));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, axisX * axisX, _t1);
        double _t21 = Math.fma(axisY, _t0, _t11 * _t2);
        double _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        dest.putDouble(destOffset, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        return preRotateAxis_api_sa6f451c6_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), _t21, Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(axisX, _t0, _t11 * _t6), _t24, Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(_t11, _t2, -(axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_sa6f451c6_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset + 8, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putDouble(destOffset + 16, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.putDouble(destOffset + 24, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.putDouble(destOffset + 32, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        dest.putDouble(destOffset + 48, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.putDouble(destOffset + 56, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Double3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t4 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        return preRotateAxis_api_sb49cc6e3_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_axisz, _t0, _t11 * _t4), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_t11, _t4, -(_axisz * _t0)), Math.fma(_t11, _t6, -(_axisx * _t0)), Math.fma(_t11, _t2, -(_axisy * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_sb49cc6e3_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        dest.putDouble(destOffset + 8, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putDouble(destOffset + 16, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.putDouble(destOffset + 24, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.putDouble(destOffset + 32, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        dest.putDouble(destOffset + 48, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.putDouble(destOffset + 56, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, Math.fma(_self10, _t1, -(_self20 * _t0)));
        dest.putDouble(destOffset + 16, Math.fma(_self10, _t0, _self20 * _t1));
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, Math.fma(_self11, _t1, -(_self21 * _t0)));
        dest.putDouble(destOffset + 40, Math.fma(_self11, _t0, _self21 * _t1));
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t1, -(_self22 * _t0)));
        dest.putDouble(destOffset + 64, Math.fma(_self12, _t0, _self22 * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, Math.fma(_self00, _t1, _self20 * _t0));
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, Math.fma(_self20, _t1, -(_self00 * _t0)));
        dest.putDouble(destOffset + 24, Math.fma(_self01, _t1, _self21 * _t0));
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t1, -(_self01 * _t0)));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t1, _self22 * _t0));
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t1, -(_self02 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0 * vX);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1 * vY);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0 * _vx);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1 * _vy);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, s * _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, s * _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest.putDouble(destOffset, Math.fma(s, _self00, _self20 * _t1));
        dest.putDouble(destOffset + 8, Math.fma(s, _self10, _self20 * _t2));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, Math.fma(s, _self01, _self21 * _t1));
        dest.putDouble(destOffset + 32, Math.fma(s, _self11, _self21 * _t2));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, Math.fma(s, _self02, _self22 * _t1));
        dest.putDouble(destOffset + 56, Math.fma(s, _self12, _self22 * _t2));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivot.getDouble(pivotOffset) * _t0;
        double _t2 = pivot.getDouble(pivotOffset + 8) * _t0;
        dest.putDouble(destOffset, Math.fma(s, _self00, _self20 * _t1));
        dest.putDouble(destOffset + 8, Math.fma(s, _self10, _self20 * _t2));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, Math.fma(s, _self01, _self21 * _t1));
        dest.putDouble(destOffset + 32, Math.fma(s, _self11, _self21 * _t2));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, Math.fma(s, _self02, _self22 * _t1));
        dest.putDouble(destOffset + 56, Math.fma(s, _self12, _self22 * _t2));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest.putDouble(destOffset, Math.fma(sX, _self00, _self20 * _t2));
        dest.putDouble(destOffset + 8, Math.fma(sY, _self10, _self20 * _t3));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, Math.fma(sX, _self01, _self21 * _t2));
        dest.putDouble(destOffset + 32, Math.fma(sY, _self11, _self21 * _t3));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, Math.fma(sX, _self02, _self22 * _t2));
        dest.putDouble(destOffset + 56, Math.fma(sY, _self12, _self22 * _t3));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _t2 = pivot.getDouble(pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.getDouble(pivotOffset + 8) * (1.0 - _sy);
        dest.putDouble(destOffset, Math.fma(_sx, _self00, _self20 * _t2));
        dest.putDouble(destOffset + 8, Math.fma(_sy, _self10, _self20 * _t3));
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, Math.fma(_sx, _self01, _self21 * _t2));
        dest.putDouble(destOffset + 32, Math.fma(_sy, _self11, _self21 * _t3));
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, Math.fma(_sx, _self02, _self22 * _t2));
        dest.putDouble(destOffset + 56, Math.fma(_sy, _self12, _self22 * _t3));
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_eself2, vX, _eself0));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_eself2, vY, _eself1));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_eself2, _vx, _eself0));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_eself2, _vy, _eself1));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, Math.fma(_self00, _t1, _self01 * _t0));
        dest.putDouble(destOffset + 8, Math.fma(_self10, _t1, _self11 * _t0));
        dest.putDouble(destOffset + 16, Math.fma(_self20, _t1, _self21 * _t0));
        dest.putDouble(destOffset + 24, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.putDouble(destOffset + 32, Math.fma(_self11, _t1, -(_self10 * _t0)));
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t1, -(_self20 * _t0)));
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t3 = Math.sin(0.5 * angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t3 + _t3) * _t3;
        dest.putDouble(destOffset, Math.fma(_self00, _t2, _self01 * _t0));
        dest.putDouble(destOffset + 8, Math.fma(_self10, _t2, _self11 * _t0));
        dest.putDouble(destOffset + 16, Math.fma(_self20, _t2, _self21 * _t0));
        dest.putDouble(destOffset + 24, Math.fma(_self01, _t2, -(_self00 * _t0)));
        dest.putDouble(destOffset + 32, Math.fma(_self11, _t2, -(_self10 * _t0)));
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t2, -(_self20 * _t0)));
        return rotateAround_api_s86ca51cd_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, Math.fma(pivotX, _t8, pivotY * _t0), Math.fma(pivotY, _t8, -(pivotX * _t0)));
    }

    /** Piece 2 of {@code rotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_s86ca51cd_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t9, double _t10) {
        dest.putDouble(destOffset + 48, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t9, Math.fma(_self21, _t10, _self22)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t3 = Math.sin(0.5 * angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t3 + _t3) * _t3;
        dest.putDouble(destOffset, Math.fma(_self00, _t2, _self01 * _t0));
        dest.putDouble(destOffset + 8, Math.fma(_self10, _t2, _self11 * _t0));
        dest.putDouble(destOffset + 16, Math.fma(_self20, _t2, _self21 * _t0));
        dest.putDouble(destOffset + 24, Math.fma(_self01, _t2, -(_self00 * _t0)));
        dest.putDouble(destOffset + 32, Math.fma(_self11, _t2, -(_self10 * _t0)));
        return rotateAround_api_s88cc714c_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2, Math.fma(_pivotx, _t8, _pivoty * _t0), Math.fma(_pivoty, _t8, -(_pivotx * _t0)));
    }

    /** Piece 2 of {@code rotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_s88cc714c_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t2, double _t9, double _t10) {
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t2, -(_self20 * _t0)));
        dest.putDouble(destOffset + 48, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t9, Math.fma(_self21, _t10, _self22)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, axisX * axisX, _t1);
        double _t21 = Math.fma(axisZ, _t0, _t11 * _t5);
        double _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        dest.putDouble(destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        return rotateAxis_api_sb37da5b_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), _t21, Math.fma(axisX, _t0, _t11 * _t6), Math.fma(axisY, _t0, _t11 * _t2), _t24, Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)));
    }

    /** Piece 2 of {@code rotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_sb37da5b_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset + 8, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putDouble(destOffset + 24, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.putDouble(destOffset + 40, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Double3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        return rotateAxis_api_sa1c3adf2_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisz, _t0, _t11 * _t5), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_t11, _t5, -(_axisz * _t0)), Math.fma(_t11, _t6, -(_axisx * _t0)));
    }

    /** Piece 2 of {@code rotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_sa1c3adf2_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.putDouble(destOffset + 8, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putDouble(destOffset + 24, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.putDouble(destOffset + 40, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, Math.fma(_self01, _t1, _self02 * _t0));
        dest.putDouble(destOffset + 32, Math.fma(_self11, _t1, _self12 * _t0));
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t1, _self22 * _t0));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t1, -(_self01 * _t0)));
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t1, -(_self11 * _t0)));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t1, -(_self21 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX180_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateX180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX180_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, -_self01);
        dest.putDouble(destOffset + 32, -_self11);
        dest.putDouble(destOffset + 40, -_self21);
        dest.putDouble(destOffset + 48, -_self02);
        dest.putDouble(destOffset + 56, -_self12);
        dest.putDouble(destOffset + 64, -_self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX270_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateX270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX270_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, -_self02);
        dest.putDouble(destOffset + 32, -_self12);
        dest.putDouble(destOffset + 40, -_self22);
        dest.putDouble(destOffset + 48, _self01);
        dest.putDouble(destOffset + 56, _self11);
        dest.putDouble(destOffset + 64, _self21);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX90_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateX90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX90_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self02);
        dest.putDouble(destOffset + 32, _self12);
        dest.putDouble(destOffset + 40, _self22);
        dest.putDouble(destOffset + 48, -_self01);
        dest.putDouble(destOffset + 56, -_self11);
        dest.putDouble(destOffset + 64, -_self21);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        double _t13 = _t5 * _t4;
        double _t18 = Math.fma(_t6, _t4, _t1 * _t3);
        double _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        dest.putDouble(destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t13, _self01 * _t18)));
        return rotateXYZ_api_sf844a819_1(dest, destOffset, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t1 * _t5, _t0 * _t5, _t13, _t3 * _t5, _t18, Math.fma(_t7, _t1, _t0 * _t4), _t20, Math.fma(_t3, _t4, -(_t6 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_sf844a819_1(java.nio.ByteBuffer dest, int destOffset, double _t2, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11, double _t13, double _t15, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 8, Math.fma(_self12, _t20, Math.fma(_self10, _t13, _self11 * _t18)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t20, Math.fma(_self20, _t13, _self21 * _t18)));
        dest.putDouble(destOffset + 24, Math.fma(_self02, _t19, Math.fma(_self01, _t21, -(_self00 * _t10))));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t19, Math.fma(_self11, _t21, -(_self10 * _t10))));
        dest.putDouble(destOffset + 40, Math.fma(_self22, _t19, Math.fma(_self21, _t21, -(_self20 * _t10))));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t15, Math.fma(_self00, _t2, -(_self01 * _t11))));
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t15, Math.fma(_self10, _t2, -(_self11 * _t11))));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t15, Math.fma(_self20, _t2, -(_self21 * _t11))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        double _t15 = _t3 * _t5;
        double _t18 = Math.fma(_t9, _t3, _t0 * _t2);
        double _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        dest.putDouble(destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t15, _self01 * _t18)));
        return rotateXZY_api_s2462d199_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t0 * _t5, _t2 * _t5, _t15, _t4 * _t5, _t18, Math.fma(_t6, _t2, _t4 * _t3), _t20, Math.fma(_t9, _t2, -(_t0 * _t3)));
    }

    /** Piece 2 of {@code rotateXZY_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_s2462d199_1(java.nio.ByteBuffer dest, int destOffset, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11, double _t15, double _t16, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 8, Math.fma(_self12, _t20, Math.fma(_self10, _t15, _self11 * _t18)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t20, Math.fma(_self20, _t15, _self21 * _t18)));
        dest.putDouble(destOffset + 24, Math.fma(_self02, _t10, Math.fma(_self01, _t16, -(_self00 * _t1))));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t10, Math.fma(_self11, _t16, -(_self10 * _t1))));
        dest.putDouble(destOffset + 40, Math.fma(_self22, _t10, Math.fma(_self21, _t16, -(_self20 * _t1))));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t19, Math.fma(_self00, _t11, _self01 * _t21)));
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t19, Math.fma(_self10, _t11, _self11 * _t21)));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t19, Math.fma(_self20, _t11, _self21 * _t21)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, Math.fma(_self00, _t1, -(_self02 * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_self10, _t1, -(_self12 * _t0)));
        dest.putDouble(destOffset + 16, Math.fma(_self20, _t1, -(_self22 * _t0)));
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, Math.fma(_self00, _t0, _self02 * _t1));
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t0, _self12 * _t1));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t0, _self22 * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY180_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateY180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY180_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, -_self00);
        dest.putDouble(destOffset + 8, -_self10);
        dest.putDouble(destOffset + 16, -_self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, -_self02);
        dest.putDouble(destOffset + 56, -_self12);
        dest.putDouble(destOffset + 64, -_self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY270_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateY270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY270_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self02);
        dest.putDouble(destOffset + 8, _self12);
        dest.putDouble(destOffset + 16, _self22);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, -_self00);
        dest.putDouble(destOffset + 56, -_self10);
        dest.putDouble(destOffset + 64, -_self20);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY90_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateY90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY90_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, -_self02);
        dest.putDouble(destOffset + 8, -_self12);
        dest.putDouble(destOffset + 16, -_self22);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, _self00);
        dest.putDouble(destOffset + 56, _self10);
        dest.putDouble(destOffset + 64, _self20);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t2 * _t5;
        double _t18 = Math.fma(_t6, _t2, _t3 * _t4);
        double _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        dest.putDouble(destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10)));
        return rotateYXZ_api_sbfc926d_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t10, _t1 * _t5, _t5 * _t4, _t5 * _t3, _t18, Math.fma(_t8, _t4, _t1 * _t2), _t20, Math.fma(_t6, _t4, -(_t2 * _t3)));
    }

    /** Piece 2 of {@code rotateYXZ_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_sbfc926d_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t12, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 8, Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10)));
        dest.putDouble(destOffset + 24, Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16)));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16)));
        dest.putDouble(destOffset + 40, Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16)));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0))));
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0))));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t3 = Math.cosFromSin(_t1, angleZ);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        double _t9 = _t1 * _t4;
        double _t13 = _t4 * _t3;
        dest.putDouble(destOffset, Math.fma(-_self02, _t7, Math.fma(_self00, _t13, _self01 * _t1)));
        dest.putDouble(destOffset + 8, Math.fma(-_self12, _t7, Math.fma(_self10, _t13, _self11 * _t1)));
        return rotateYZX_api_s42d1eb59_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t2 * _t3, _t13, _t5 * _t3, Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t9, _t2, _t0 * _t5), Math.fma(_t2, _t0, -(_t9 * _t5)), Math.fma(_t5, _t4, -(_t6 * _t2)));
    }

    /** Piece 2 of {@code rotateYZX_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_s42d1eb59_1(java.nio.ByteBuffer dest, int destOffset, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t11, double _t13, double _t14, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, Math.fma(-_self22, _t7, Math.fma(_self20, _t13, _self21 * _t1)));
        dest.putDouble(destOffset + 24, Math.fma(_self02, _t18, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t18, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.putDouble(destOffset + 40, Math.fma(_self22, _t18, Math.fma(_self20, _t20, _self21 * _t14)));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t21, Math.fma(_self00, _t19, -(_self01 * _t11))));
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t21, Math.fma(_self10, _t19, -(_self11 * _t11))));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t21, Math.fma(_self20, _t19, -(_self21 * _t11))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ180_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateZ180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ180_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, -_self00);
        dest.putDouble(destOffset + 8, -_self10);
        dest.putDouble(destOffset + 16, -_self20);
        dest.putDouble(destOffset + 24, -_self01);
        dest.putDouble(destOffset + 32, -_self11);
        dest.putDouble(destOffset + 40, -_self21);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ270_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateZ270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ270_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, -_self01);
        dest.putDouble(destOffset + 8, -_self11);
        dest.putDouble(destOffset + 16, -_self21);
        dest.putDouble(destOffset + 24, _self00);
        dest.putDouble(destOffset + 32, _self10);
        dest.putDouble(destOffset + 40, _self20);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ90_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateZ90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ90_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self01);
        dest.putDouble(destOffset + 8, _self11);
        dest.putDouble(destOffset + 16, _self21);
        dest.putDouble(destOffset + 24, -_self00);
        dest.putDouble(destOffset + 32, -_self10);
        dest.putDouble(destOffset + 40, -_self20);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t7 = _t0 * _t3;
        double _t8 = _t1 * _t5;
        double _t18 = Math.fma(_t8, _t0, _t2 * _t4);
        double _t20 = Math.fma(_t4, _t5, -(_t6 * _t0));
        dest.putDouble(destOffset, Math.fma(-_self02, _t7, Math.fma(_self00, _t20, _self01 * _t18)));
        return rotateZXY_api_s50246ed_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t7, _t2 * _t3, _t3 * _t5, _t3 * _t4, _t18, Math.fma(_t6, _t4, _t0 * _t5), _t20, Math.fma(_t0, _t2, -(_t8 * _t4)));
    }

    /** Piece 2 of {@code rotateZXY_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_s50246ed_1(java.nio.ByteBuffer dest, int destOffset, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t7, double _t10, double _t14, double _t15, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 8, Math.fma(-_self12, _t7, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.putDouble(destOffset + 16, Math.fma(-_self22, _t7, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.putDouble(destOffset + 24, Math.fma(_self02, _t1, Math.fma(_self01, _t14, -(_self00 * _t10))));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t1, Math.fma(_self11, _t14, -(_self10 * _t10))));
        dest.putDouble(destOffset + 40, Math.fma(_self22, _t1, Math.fma(_self21, _t14, -(_self20 * _t10))));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t15, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t15, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t15, Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t1 * _t3;
        double _t10 = _t0 * _t4;
        double _t15 = _t3 * _t4;
        dest.putDouble(destOffset, Math.fma(-_self02, _t0, Math.fma(_self00, _t15, _self01 * _t8)));
        dest.putDouble(destOffset + 8, Math.fma(-_self12, _t0, Math.fma(_self10, _t15, _self11 * _t8)));
        return rotateZYX_api_s7fbcba89_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t8, _t2 * _t3, _t15, _t5 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t5, -(_t2 * _t4)));
    }

    /** Piece 2 of {@code rotateZYX_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_s7fbcba89_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t8, double _t9, double _t15, double _t17, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset + 16, Math.fma(-_self22, _t0, Math.fma(_self20, _t15, _self21 * _t8)));
        dest.putDouble(destOffset + 24, Math.fma(_self02, _t9, Math.fma(_self00, _t20, _self01 * _t18)));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t9, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.putDouble(destOffset + 40, Math.fma(_self22, _t9, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t17, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.putDouble(destOffset + 56, Math.fma(_self12, _t17, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t17, Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00 * vX);
        dest.putDouble(destOffset + 8, _self10 * vX);
        dest.putDouble(destOffset + 16, _self20 * vX);
        dest.putDouble(destOffset + 24, _self01 * vY);
        dest.putDouble(destOffset + 32, _self11 * vY);
        dest.putDouble(destOffset + 40, _self21 * vY);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset, _self00 * _vx);
        dest.putDouble(destOffset + 8, _self10 * _vx);
        dest.putDouble(destOffset + 16, _self20 * _vx);
        dest.putDouble(destOffset + 24, _self01 * _vy);
        dest.putDouble(destOffset + 32, _self11 * _vy);
        dest.putDouble(destOffset + 40, _self21 * _vy);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self20);
        dest.putDouble(destOffset + 24, s * _self01);
        dest.putDouble(destOffset + 32, s * _self11);
        dest.putDouble(destOffset + 40, s * _self21);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self20);
        dest.putDouble(destOffset + 24, s * _self01);
        dest.putDouble(destOffset + 32, s * _self11);
        dest.putDouble(destOffset + 40, s * _self21);
        dest.putDouble(destOffset + 48, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0 = 1.0 - s;
        double _t1 = pivot.getDouble(pivotOffset) * _t0;
        double _t2 = pivot.getDouble(pivotOffset + 8) * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self20);
        dest.putDouble(destOffset + 24, s * _self01);
        dest.putDouble(destOffset + 32, s * _self11);
        dest.putDouble(destOffset + 40, s * _self21);
        dest.putDouble(destOffset + 48, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest.putDouble(destOffset, sX * _self00);
        dest.putDouble(destOffset + 8, sX * _self10);
        dest.putDouble(destOffset + 16, sX * _self20);
        dest.putDouble(destOffset + 24, sY * _self01);
        dest.putDouble(destOffset + 32, sY * _self11);
        dest.putDouble(destOffset + 40, sY * _self21);
        dest.putDouble(destOffset + 48, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _t2 = pivot.getDouble(pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.getDouble(pivotOffset + 8) * (1.0 - _sy);
        dest.putDouble(destOffset, _sx * _self00);
        dest.putDouble(destOffset + 8, _sx * _self10);
        dest.putDouble(destOffset + 16, _sx * _self20);
        dest.putDouble(destOffset + 24, _sy * _self01);
        dest.putDouble(destOffset + 32, _sy * _self11);
        dest.putDouble(destOffset + 40, _sy * _self21);
        dest.putDouble(destOffset + 48, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        dest.putDouble(destOffset + 56, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        dest.putDouble(destOffset + 64, Math.fma(_self20, vX, Math.fma(_self21, vY, _self22)));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        dest.putDouble(destOffset + 56, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _vx, Math.fma(_self21, _vy, _self22)));
        return dest;
    }

    public static java.nio.ByteBuffer view_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.ByteBuffer view_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        dest.putDouble(destOffset, _sp0 * _self00);
        dest.putDouble(destOffset + 8, _sp0 * _self10);
        dest.putDouble(destOffset + 16, _sp0 * _self20);
        dest.putDouble(destOffset + 24, _sp1 * _self01);
        dest.putDouble(destOffset + 32, _sp1 * _self11);
        dest.putDouble(destOffset + 40, _sp1 * _self21);
        dest.putDouble(destOffset + 48, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        dest.putDouble(destOffset + 56, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        dest.putDouble(destOffset + 64, _self22 + (-(_self20 * _sp2) - _self21 * _sp3));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        dest.putDouble(destOffset + 8, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self20 = src.getDouble(srcOffset + 16);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self21 = src.getDouble(srcOffset + 40);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _self22 = src.getDouble(srcOffset + 64);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        dest.putDouble(destOffset, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        dest.putDouble(destOffset + 8, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        dest.putDouble(destOffset, Math.fma(_self00, vX, _self01 * vY));
        dest.putDouble(destOffset + 8, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset, Math.fma(_self00, _vx, _self01 * _vy));
        dest.putDouble(destOffset + 8, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        dest.putDouble(destOffset, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        dest.putDouble(destOffset + 8, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 24);
        double _self11 = src.getDouble(srcOffset + 32);
        double _self02 = src.getDouble(srcOffset + 48);
        double _self12 = src.getDouble(srcOffset + 56);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        dest.putDouble(destOffset + 8, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
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
