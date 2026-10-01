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
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double3x4Ops} and its sibling kernel units. Not public API.
 */
public final class Double3x4OpsKernelsByteBuffer {
    private Double3x4OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer getColumn_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        Double3x4OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, col);
        return dest;
    }

    public static java.nio.ByteBuffer getColumn_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
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
        dest.putDouble(destOffset, _idxSw0);
        dest.putDouble(destOffset + 8, _idxSw1);
        dest.putDouble(destOffset + 16, _idxSw2);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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
        Double3x4OpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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
        Double3x4OpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self22 = src.getDouble(srcOffset + 80);
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
        Double3x4OpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self22 = src.getDouble(srcOffset + 80);
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
        Double3x4OpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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
        Double3x4OpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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
        Double3x4OpsKernelsAddress.getNormalizedRotation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getNormalizedRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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
        Double3x4OpsKernelsAddress.getRow_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, row);
        return dest;
    }

    public static java.nio.ByteBuffer getRow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
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
        dest.putDouble(destOffset, _idxSw0);
        dest.putDouble(destOffset + 8, _idxSw1);
        dest.putDouble(destOffset + 16, _idxSw2);
        dest.putDouble(destOffset + 24, _idxSw3);
        return dest;
    }

    public static java.nio.ByteBuffer getScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getScale_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        dest.putDouble(destOffset, java.lang.Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        dest.putDouble(destOffset + 8, java.lang.Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        dest.putDouble(destOffset + 16, java.lang.Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getTranslation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self03 = src.getDouble(srcOffset + 24);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self23 = src.getDouble(srcOffset + 88);
        dest.putDouble(destOffset, _self03);
        dest.putDouble(destOffset + 8, _self13);
        dest.putDouble(destOffset + 16, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer getUnnormalizedRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getUnnormalizedRotation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getUnnormalizedRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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

    public static java.nio.ByteBuffer invNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.invNegativeX_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.putDouble(destOffset, -(_t6 * _t13));
        dest.putDouble(destOffset + 8, -(_t8 * _t13));
        dest.putDouble(destOffset + 16, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.invNegativeX_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsByteBuffer.invNegativeX_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invNegativeX_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNegativeX_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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
            dest.putDouble(destOffset, -(_t21 * _t26));
            dest.putDouble(destOffset + 8, -(_t22 * _t26));
            dest.putDouble(destOffset + 16, -(_t20 * _t26));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        double _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        double _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.invNegativeY_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.putDouble(destOffset, -(_t6 * _t13));
        dest.putDouble(destOffset + 8, -(_t8 * _t13));
        dest.putDouble(destOffset + 16, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.invNegativeY_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsByteBuffer.invNegativeY_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invNegativeY_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNegativeY_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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
            dest.putDouble(destOffset, -(_t22 * _t26));
            dest.putDouble(destOffset + 8, -(_t21 * _t26));
            dest.putDouble(destOffset + 16, -(_t20 * _t26));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        double _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        double _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.invNegativeZ_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.putDouble(destOffset, -(_t6 * _t13));
        dest.putDouble(destOffset + 8, -(_t8 * _t13));
        dest.putDouble(destOffset + 16, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.invNegativeZ_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsByteBuffer.invNegativeZ_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invNegativeZ_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNegativeZ_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
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
            dest.putDouble(destOffset, -(_t21 * _t26));
            dest.putDouble(destOffset + 8, -(_t22 * _t26));
            dest.putDouble(destOffset + 16, -(_t20 * _t26));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNormalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, -_self00);
        dest.putDouble(destOffset + 8, -_self01);
        dest.putDouble(destOffset + 16, -_self02);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        dest.putDouble(destOffset, -_self10);
        dest.putDouble(destOffset + 8, -_self11);
        dest.putDouble(destOffset + 16, -_self12);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        dest.putDouble(destOffset, -_self20);
        dest.putDouble(destOffset + 8, -_self21);
        dest.putDouble(destOffset + 16, -_self22);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self02);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        dest.putDouble(destOffset, _self10);
        dest.putDouble(destOffset + 8, _self11);
        dest.putDouble(destOffset + 16, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        dest.putDouble(destOffset, _self20);
        dest.putDouble(destOffset + 8, _self21);
        dest.putDouble(destOffset + 16, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.invPositiveX_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.putDouble(destOffset, _t6 * _t13);
        dest.putDouble(destOffset + 8, _t8 * _t13);
        dest.putDouble(destOffset + 16, _t7 * _t13);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.invPositiveX_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsByteBuffer.invPositiveX_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invPositiveX_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invPositiveX_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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
            dest.putDouble(destOffset, _t21 * _t26);
            dest.putDouble(destOffset + 8, _t22 * _t26);
            dest.putDouble(destOffset + 16, _t20 * _t26);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        double _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        double _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.invPositiveY_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.putDouble(destOffset, _t6 * _t13);
        dest.putDouble(destOffset + 8, _t8 * _t13);
        dest.putDouble(destOffset + 16, _t7 * _t13);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.invPositiveY_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsByteBuffer.invPositiveY_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invPositiveY_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invPositiveY_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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
            dest.putDouble(destOffset, _t22 * _t26);
            dest.putDouble(destOffset + 8, _t21 * _t26);
            dest.putDouble(destOffset + 16, _t20 * _t26);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        double _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        double _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.invPositiveZ_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.putDouble(destOffset, _t6 * _t13);
        dest.putDouble(destOffset + 8, _t8 * _t13);
        dest.putDouble(destOffset + 16, _t7 * _t13);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.invPositiveZ_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsByteBuffer.invPositiveZ_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invPositiveZ_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invPositiveZ_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
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
            dest.putDouble(destOffset, _t21 * _t26);
            dest.putDouble(destOffset + 8, _t22 * _t26);
            dest.putDouble(destOffset + 16, _t20 * _t26);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self20 = src.getDouble(srcOffset + 64);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.putDouble(destOffset, -(_self00 * _t3));
            dest.putDouble(destOffset + 8, -(_self10 * _t3));
            dest.putDouble(destOffset + 16, -(_self20 * _t3));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.negativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self01 = src.getDouble(srcOffset + 8);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self21 = src.getDouble(srcOffset + 72);
        double _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.putDouble(destOffset, -(_self01 * _t3));
            dest.putDouble(destOffset + 8, -(_self11 * _t3));
            dest.putDouble(destOffset + 16, -(_self21 * _t3));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.negativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self02 = src.getDouble(srcOffset + 16);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self22 = src.getDouble(srcOffset + 80);
        double _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.putDouble(destOffset, -(_self02 * _t3));
            dest.putDouble(destOffset + 8, -(_self12 * _t3));
            dest.putDouble(destOffset + 16, -(_self22 * _t3));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.normalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self20 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, -_self00);
        dest.putDouble(destOffset + 8, -_self10);
        dest.putDouble(destOffset + 16, -_self20);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self01 = src.getDouble(srcOffset + 8);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self21 = src.getDouble(srcOffset + 72);
        dest.putDouble(destOffset, -_self01);
        dest.putDouble(destOffset + 8, -_self11);
        dest.putDouble(destOffset + 16, -_self21);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self02 = src.getDouble(srcOffset + 16);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self22 = src.getDouble(srcOffset + 80);
        dest.putDouble(destOffset, -_self02);
        dest.putDouble(destOffset + 8, -_self12);
        dest.putDouble(destOffset + 16, -_self22);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self20 = src.getDouble(srcOffset + 64);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self01 = src.getDouble(srcOffset + 8);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self21 = src.getDouble(srcOffset + 72);
        dest.putDouble(destOffset, _self01);
        dest.putDouble(destOffset + 8, _self11);
        dest.putDouble(destOffset + 16, _self21);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self02 = src.getDouble(srcOffset + 16);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self22 = src.getDouble(srcOffset + 80);
        dest.putDouble(destOffset, _self02);
        dest.putDouble(destOffset + 8, _self12);
        dest.putDouble(destOffset + 16, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer origin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.origin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer origin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        dest.putDouble(destOffset, -Math.fma(_self20, _self23, Math.fma(_self00, _self03, _self10 * _self13)));
        dest.putDouble(destOffset + 8, -Math.fma(_self21, _self23, Math.fma(_self01, _self03, _self11 * _self13)));
        dest.putDouble(destOffset + 16, -Math.fma(_self22, _self23, Math.fma(_self02, _self03, _self12 * _self13)));
        return dest;
    }

    public static java.nio.ByteBuffer positiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self20 = src.getDouble(srcOffset + 64);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.putDouble(destOffset, _self00 * _t3);
            dest.putDouble(destOffset + 8, _self10 * _t3);
            dest.putDouble(destOffset + 16, _self20 * _t3);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer positiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.positiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self01 = src.getDouble(srcOffset + 8);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self21 = src.getDouble(srcOffset + 72);
        double _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.putDouble(destOffset, _self01 * _t3);
            dest.putDouble(destOffset + 8, _self11 * _t3);
            dest.putDouble(destOffset + 16, _self21 * _t3);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer positiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.positiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self02 = src.getDouble(srcOffset + 16);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self22 = src.getDouble(srcOffset + 80);
        double _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.putDouble(destOffset, _self02 * _t3);
            dest.putDouble(destOffset + 8, _self12 * _t3);
            dest.putDouble(destOffset + 16, _self22 * _t3);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static double determinant_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3x4OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static double determinant_api(java.nio.ByteBuffer src, int srcOffset) {
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        return Math.fma(src.getDouble(srcOffset + 16), Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(src.getDouble(srcOffset), Math.fma(_self11, _self22, -(_self12 * _self21)), -(src.getDouble(srcOffset + 8) * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static double frobeniusNorm_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3x4OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        return java.lang.Math.sqrt(Math.fma(_self00, _self00, Math.fma(_self01, _self01, _self02 * _self02)) + Math.fma(_self03, _self03, Math.fma(_self10, _self10, _self11 * _self11)) + (Math.fma(_self12, _self12, Math.fma(_self13, _self13, _self20 * _self20)) + Math.fma(_self21, _self21, Math.fma(_self22, _self22, _self23 * _self23))));
    }

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t20 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t21 = Math.fma(_self10, _self21, -(_self11 * _self20));
        return invert_api_s7e45b2f0_1(dest, destOffset, src, srcOffset, _self03, _self13, _self23, _t20, _t21, Math.fma(_self02, _self21, -(_self01 * _self22)), Math.fma(_self01, _self12, -(_self02 * _self11)), Math.fma(_self12, _self20, -(_self10 * _self22)), Math.fma(_self00, _self22, -(_self02 * _self20)), Math.fma(_self02, _self10, -(_self00 * _self12)), Math.fma(_self01, _self20, -(_self00 * _self21)), Math.fma(_self00, _self11, -(_self01 * _self10)), Math.fma(_self02, _t21, Math.fma(_self00, _t20, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20))))));
    }

    /** Piece 2 of {@code invert_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_api_s7e45b2f0_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double _self03, double _self13, double _self23, double _t20, double _t21, double _t23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t34) {
        if (!(java.lang.Math.abs(_t34) > 2.2250738585072014E-308 && java.lang.Math.abs(_t34) < 4.49423283715579E307)) return Double3x4OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t34_inv = 1.0 / _t34;
        dest.putDouble(destOffset, _t20 * _t34_inv);
        dest.putDouble(destOffset + 8, _t23 * _t34_inv);
        dest.putDouble(destOffset + 16, _t24 * _t34_inv);
        dest.putDouble(destOffset + 24, -(Math.fma(_self23, _t24, Math.fma(_self03, _t20, _self13 * _t23)) * _t34_inv));
        dest.putDouble(destOffset + 32, _t25 * _t34_inv);
        dest.putDouble(destOffset + 40, _t26 * _t34_inv);
        dest.putDouble(destOffset + 48, _t27 * _t34_inv);
        dest.putDouble(destOffset + 56, -(Math.fma(_self23, _t27, Math.fma(_self03, _t25, _self13 * _t26)) * _t34_inv));
        dest.putDouble(destOffset + 64, _t21 * _t34_inv);
        dest.putDouble(destOffset + 72, _t28 * _t34_inv);
        dest.putDouble(destOffset + 80, _t29 * _t34_inv);
        dest.putDouble(destOffset + 88, -(Math.fma(_self23, _t29, Math.fma(_self03, _t21, _self13 * _t28)) * _t34_inv));
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsByteBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invert_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t2 = unitScale(_self00, _self01, _self02);
        double _t15 = _self11 * _t0;
        double _t16 = _self22 * _t1;
        double _t17 = _self12 * _t0;
        double _t18 = _self21 * _t1;
        double _t19 = _self10 * _t0;
        double _t20 = _self20 * _t1;
        double _t21 = _self02 * _t2;
        double _t23 = _self01 * _t2;
        return invert_degenerate_api_sbb651e39_1(dest, destOffset, _t0, _t1, _t2, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _self00 * _t2, _t23, _self23 * _t1, _self13 * _t0, _self03 * _t2, Math.fma(_t15, _t16, -(_t17 * _t18)), Math.fma(_t19, _t18, -(_t15 * _t20)), Math.fma(_t21, _t18, -(_t23 * _t16)), Math.fma(_t23, _t17, -(_t21 * _t15)));
    }

    /** Piece 2 of {@code invert_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_sbb651e39_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _t2, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t47, double _t48, double _t50, double _t51) {
        double _t52 = Math.fma(_t17, _t20, -(_t19 * _t16));
        double _t53 = Math.fma(_t22, _t16, -(_t21 * _t20));
        double _t54 = Math.fma(_t21, _t19, -(_t22 * _t17));
        double _t60_inv = 1.0 / Math.fma(_t48, _t21, Math.fma(_t47, _t22, -(Math.fma(_t19, _t16, -(_t17 * _t20)) * _t23)));
        double _sp2 = _t1 * _t60_inv;
        double _sp1 = _t0 * _t60_inv;
        double _sp0 = _t2 * _t60_inv;
        dest.putDouble(destOffset, _t47 * _sp0);
        dest.putDouble(destOffset + 8, _t50 * _sp1);
        dest.putDouble(destOffset + 16, _t51 * _sp2);
        dest.putDouble(destOffset + 24, -(Math.fma(_t51, _t24, Math.fma(_t50, _t25, _t47 * _t26)) * _t60_inv));
        dest.putDouble(destOffset + 32, _t52 * _sp0);
        dest.putDouble(destOffset + 40, _t53 * _sp1);
        dest.putDouble(destOffset + 48, _t54 * _sp2);
        dest.putDouble(destOffset + 56, -(Math.fma(_t54, _t24, Math.fma(_t53, _t25, _t52 * _t26)) * _t60_inv));
        dest.putDouble(destOffset + 64, _t48 * _sp0);
        return invert_degenerate_api_sbb651e39_2(dest, destOffset, _t24, _t25, _t26, _t48, Math.fma(_t23, _t20, -(_t22 * _t18)), Math.fma(_t22, _t15, -(_t23 * _t19)), _t60_inv, _sp2, _sp1);
    }

    /** Piece 3 of {@code invert_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_sbb651e39_2(java.nio.ByteBuffer dest, int destOffset, double _t24, double _t25, double _t26, double _t48, double _t55, double _t56, double _t60_inv, double _sp2, double _sp1) {
        dest.putDouble(destOffset + 72, _t55 * _sp1);
        dest.putDouble(destOffset + 80, _t56 * _sp2);
        dest.putDouble(destOffset + 88, -(Math.fma(_t56, _t24, Math.fma(_t55, _t25, _t48 * _t26)) * _t60_inv));
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x4OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _other00 = other.getDouble(otherOffset);
        double _other01 = other.getDouble(otherOffset + 8);
        double _other02 = other.getDouble(otherOffset + 16);
        double _other03 = other.getDouble(otherOffset + 24);
        double _other10 = other.getDouble(otherOffset + 32);
        double _other11 = other.getDouble(otherOffset + 40);
        double _other12 = other.getDouble(otherOffset + 48);
        double _other13 = other.getDouble(otherOffset + 56);
        double _other20 = other.getDouble(otherOffset + 64);
        double _other21 = other.getDouble(otherOffset + 72);
        double _other22 = other.getDouble(otherOffset + 80);
        double _other23 = other.getDouble(otherOffset + 88);
        return invertProduct_api_sfcc5a20b_4(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self01, _self02, _self10, _self11, _self12, _self20, _self21, _self22, _other00, _other01, _other02, _other03, _other10, _other11, _other12, _other13, _other20, _other21, _other22, _other23, Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)));
    }

    /** Part 1 of {@code invertProduct_api}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_api_sfcc5a20b_1(double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32) {
        return Math.fma(_t28, (Math.fma(_t29, _t26, -(_t30 * _t24))), Math.fma(_t31, (Math.fma(_t24, _t25, -(_t26 * _t27))), -(_t32 * Math.fma(_t29, _t25, -(_t30 * _t27)))));
    }

    /** Part 2 of {@code invertProduct_api}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_api_sfcc5a20b_2(java.nio.ByteBuffer dest, int destOffset, double _t24, double _t25, double _t26, double _t27, double _t28, double _t32, double _t33, double _t34, double _t35, double _t70) {
        double _t56 = Math.fma(_t24, _t25, -(_t26 * _t27));
        double _t59 = Math.fma(_t26, _t28, -(_t32 * _t25));
        double _t60 = Math.fma(_t32, _t27, -(_t24 * _t28));
        double _t70_inv = 1.0 / _t70;
        dest.putDouble(destOffset, _t56 * _t70_inv);
        dest.putDouble(destOffset + 8, _t59 * _t70_inv);
        dest.putDouble(destOffset + 16, _t60 * _t70_inv);
        dest.putDouble(destOffset + 24, -(Math.fma(_t33, _t60, Math.fma(_t34, _t56, _t35 * _t59)) * _t70_inv));
    }

    /** Part 3 of {@code invertProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_sfcc5a20b_3(java.nio.ByteBuffer dest, int destOffset, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35, double _t70) {
        double _t57 = Math.fma(_t29, _t26, -(_t30 * _t24));
        double _t61 = Math.fma(_t30, _t27, -(_t29 * _t25));
        double _t62 = Math.fma(_t31, _t25, -(_t30 * _t28));
        double _t63 = Math.fma(_t29, _t28, -(_t31 * _t27));
        double _t64 = Math.fma(_t30, _t32, -(_t31 * _t26));
        double _t65 = Math.fma(_t31, _t24, -(_t29 * _t32));
        double _t70_inv = 1.0 / _t70;
        dest.putDouble(destOffset + 32, _t61 * _t70_inv);
        dest.putDouble(destOffset + 40, _t62 * _t70_inv);
        dest.putDouble(destOffset + 48, _t63 * _t70_inv);
        dest.putDouble(destOffset + 56, -(Math.fma(_t33, _t63, Math.fma(_t34, _t61, _t35 * _t62)) * _t70_inv));
        dest.putDouble(destOffset + 64, _t57 * _t70_inv);
        dest.putDouble(destOffset + 72, _t64 * _t70_inv);
        dest.putDouble(destOffset + 80, _t65 * _t70_inv);
        dest.putDouble(destOffset + 88, -(Math.fma(_t33, _t65, Math.fma(_t34, _t57, _t35 * _t64)) * _t70_inv));
        return dest;
    }

    /** Piece 2 of {@code invertProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_sfcc5a20b_4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self01, double _self02, double _self10, double _self11, double _self12, double _self20, double _self21, double _self22, double _other00, double _other01, double _other02, double _other03, double _other10, double _other11, double _other12, double _other13, double _other20, double _other21, double _other22, double _other23, double _t24) {
        return invertProduct_api_sfcc5a20b_5(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self01, _self02, _self10, _self11, _self12, _self20, _self21, _self22, _other03, _other13, _other23, _t24, Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21)), Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21)), Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11)), Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01)), Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11)), Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21)), Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01)), Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01)));
    }

    /** Piece 3 of {@code invertProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_sfcc5a20b_5(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self01, double _self02, double _self10, double _self11, double _self12, double _self20, double _self21, double _self22, double _other03, double _other13, double _other23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32) {
        double _t33 = Math.fma(_other03, _self20, Math.fma(_other13, _self21, Math.fma(_other23, _self22, src.getDouble(srcOffset + 88))));
        double _t34 = Math.fma(_other03, _self00, Math.fma(_other13, _self01, Math.fma(_other23, _self02, src.getDouble(srcOffset + 24))));
        double _t35 = Math.fma(_other03, _self10, Math.fma(_other13, _self11, Math.fma(_other23, _self12, src.getDouble(srcOffset + 56))));
        double _t70 = invertProduct_api_sfcc5a20b_1(_t24, _t25, _t26, _t27, _t28, _t29, _t30, _t31, _t32);
        if (!(java.lang.Math.abs(_t70) > 2.2250738585072014E-308 && java.lang.Math.abs(_t70) < 4.49423283715579E307)) return Double3x4OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        invertProduct_api_sfcc5a20b_2(dest, destOffset, _t24, _t25, _t26, _t27, _t28, _t32, _t33, _t34, _t35, _t70);
        return invertProduct_api_sfcc5a20b_3(dest, destOffset, _t24, _t25, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t33, _t34, _t35, _t70);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x4OpsKernelsByteBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x4OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _other00 = other.getDouble(otherOffset);
        double _other01 = other.getDouble(otherOffset + 8);
        double _other02 = other.getDouble(otherOffset + 16);
        double _other03 = other.getDouble(otherOffset + 24);
        double _other10 = other.getDouble(otherOffset + 32);
        double _other11 = other.getDouble(otherOffset + 40);
        double _other12 = other.getDouble(otherOffset + 48);
        double _other13 = other.getDouble(otherOffset + 56);
        double _other20 = other.getDouble(otherOffset + 64);
        double _other21 = other.getDouble(otherOffset + 72);
        double _other22 = other.getDouble(otherOffset + 80);
        return invertProduct_degenerate_api_sc8eff87c_1(dest, destOffset, other, otherOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other01, _other02, _other03, _other10, _other11, _other12, _other13, _other20, _other21, _other22);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_sc8eff87c_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other01, double _other02, double _other03, double _other10, double _other11, double _other12, double _other13, double _other20, double _other21, double _other22) {
        double _other23 = other.getDouble(otherOffset + 88);
        double _t24 = Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11));
        double _t25 = Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11));
        double _t26 = Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11));
        double _t27 = Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21));
        double _t28 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        double _t29 = Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21));
        double _t30 = Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01));
        double _t31 = Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01));
        double _t32 = Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01));
        double _t36 = unitScale(_t25, _t24, _t26);
        double _t37 = unitScale(_t28, _t29, _t27);
        return invertProduct_degenerate_api_sc8eff87c_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other03, _other13, _other23, _t30, _t31, _t32, _t36, _t37, unitScale(_t30, _t31, _t32), _t24 * _t36, _t27 * _t37, _t29 * _t37, _t26 * _t36, _t25 * _t36, _t28 * _t37);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_sc8eff87c_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other03, double _other13, double _other23, double _t30, double _t31, double _t32, double _t36, double _t37, double _t38, double _t48, double _t49, double _t50, double _t51, double _t52, double _t53) {
        double _t54 = _t32 * _t38;
        double _t55 = _t30 * _t38;
        double _t56 = _t31 * _t38;
        double _t83 = Math.fma(_t48, _t49, -(_t50 * _t51));
        double _t84 = Math.fma(_t52, _t50, -(_t53 * _t48));
        double _t96_inv = 1.0 / Math.fma(_t84, _t54, Math.fma(_t83, _t55, -(Math.fma(_t52, _t49, -(_t53 * _t51)) * _t56)));
        double _sp0 = _t38 * _t96_inv;
        dest.putDouble(destOffset, _t83 * _sp0);
        return invertProduct_degenerate_api_sc8eff87c_3(dest, destOffset, Math.fma(_other03, _self20, Math.fma(_other13, _self21, Math.fma(_other23, _self22, _self23))) * _t37, Math.fma(_other03, _self00, Math.fma(_other13, _self01, Math.fma(_other23, _self02, _self03))) * _t38, Math.fma(_other03, _self10, Math.fma(_other13, _self11, Math.fma(_other23, _self12, _self13))) * _t36, _t83, _t84, Math.fma(_t50, _t54, -(_t56 * _t49)), Math.fma(_t56, _t51, -(_t48 * _t54)), Math.fma(_t53, _t51, -(_t52 * _t49)), Math.fma(_t55, _t49, -(_t53 * _t54)), Math.fma(_t52, _t54, -(_t55 * _t51)), Math.fma(_t53, _t56, -(_t55 * _t50)), Math.fma(_t55, _t48, -(_t52 * _t56)), _t96_inv, _t37 * _t96_inv, _t36 * _t96_inv, _sp0);
    }

    /** Piece 4 of {@code invertProduct_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_sc8eff87c_3(java.nio.ByteBuffer dest, int destOffset, double _t60, double _t61, double _t62, double _t83, double _t84, double _t86, double _t87, double _t88, double _t89, double _t90, double _t91, double _t92, double _t96_inv, double _sp2, double _sp1, double _sp0) {
        dest.putDouble(destOffset + 8, _t86 * _sp1);
        dest.putDouble(destOffset + 16, _t87 * _sp2);
        dest.putDouble(destOffset + 24, -(Math.fma(_t87, _t60, Math.fma(_t83, _t61, _t86 * _t62)) * _t96_inv));
        dest.putDouble(destOffset + 32, _t88 * _sp0);
        dest.putDouble(destOffset + 40, _t89 * _sp1);
        dest.putDouble(destOffset + 48, _t90 * _sp2);
        dest.putDouble(destOffset + 56, -(Math.fma(_t90, _t60, Math.fma(_t89, _t62, _t88 * _t61)) * _t96_inv));
        dest.putDouble(destOffset + 64, _t84 * _sp0);
        dest.putDouble(destOffset + 72, _t91 * _sp1);
        dest.putDouble(destOffset + 80, _t92 * _sp2);
        dest.putDouble(destOffset + 88, -(Math.fma(_t92, _t60, Math.fma(_t84, _t61, _t91 * _t62)) * _t96_inv));
        return dest;
    }

    public static java.nio.ByteBuffer transpose_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer transpose_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            double _eother = other.getDouble(otherOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, _eother + _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, scalar * _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, -_eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            double _eother = other.getDouble(otherOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, _eself - _eother);
        }
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x4OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _ev = v.getDouble(vOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, _ev);
        }
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x4OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m10 = m.getDouble(mOffset + 8);
        double _m20 = m.getDouble(mOffset + 16);
        double _m01 = m.getDouble(mOffset + 24);
        double _m11 = m.getDouble(mOffset + 32);
        double _m21 = m.getDouble(mOffset + 40);
        double _m02 = m.getDouble(mOffset + 48);
        double _m12 = m.getDouble(mOffset + 56);
        double _m22 = m.getDouble(mOffset + 64);
        dest.putDouble(destOffset, _m00);
        dest.putDouble(destOffset + 8, _m01);
        dest.putDouble(destOffset + 16, _m02);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _m10);
        dest.putDouble(destOffset + 40, _m11);
        dest.putDouble(destOffset + 48, _m12);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, _m20);
        dest.putDouble(destOffset + 72, _m21);
        dest.putDouble(destOffset + 80, _m22);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer setMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x4OpsKernelsAddress.setMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m10 = m.getDouble(mOffset + 8);
        double _m20 = m.getDouble(mOffset + 16);
        double _m01 = m.getDouble(mOffset + 32);
        double _m11 = m.getDouble(mOffset + 40);
        double _m21 = m.getDouble(mOffset + 48);
        double _m02 = m.getDouble(mOffset + 64);
        double _m12 = m.getDouble(mOffset + 72);
        double _m22 = m.getDouble(mOffset + 80);
        double _m03 = m.getDouble(mOffset + 96);
        double _m13 = m.getDouble(mOffset + 104);
        double _m23 = m.getDouble(mOffset + 112);
        dest.putDouble(destOffset, _m00);
        dest.putDouble(destOffset + 8, _m01);
        dest.putDouble(destOffset + 16, _m02);
        dest.putDouble(destOffset + 24, _m03);
        dest.putDouble(destOffset + 32, _m10);
        dest.putDouble(destOffset + 40, _m11);
        dest.putDouble(destOffset + 48, _m12);
        dest.putDouble(destOffset + 56, _m13);
        dest.putDouble(destOffset + 64, _m20);
        dest.putDouble(destOffset + 72, _m21);
        dest.putDouble(destOffset + 80, _m22);
        dest.putDouble(destOffset + 88, _m23);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double tX, double tY, double tZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY, tZ);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double tX, double tY, double tZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self02);
        dest.putDouble(destOffset + 24, tX);
        dest.putDouble(destOffset + 32, _self10);
        dest.putDouble(destOffset + 40, _self11);
        dest.putDouble(destOffset + 48, _self12);
        dest.putDouble(destOffset + 56, tY);
        dest.putDouble(destOffset + 64, _self20);
        dest.putDouble(destOffset + 72, _self21);
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, tZ);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Double3x4OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _tx = t.getDouble(tOffset);
        double _ty = t.getDouble(tOffset + 8);
        double _tz = t.getDouble(tOffset + 16);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self02);
        dest.putDouble(destOffset + 24, _tx);
        dest.putDouble(destOffset + 32, _self10);
        dest.putDouble(destOffset + 40, _self11);
        dest.putDouble(destOffset + 48, _self12);
        dest.putDouble(destOffset + 56, _ty);
        dest.putDouble(destOffset + 64, _self20);
        dest.putDouble(destOffset + 72, _self21);
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, _tz);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromRigid_unsafe(java.nio.ByteBuffer dest, int destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeFromRigid_unsafe(_destBase, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromRigid_api(java.nio.ByteBuffer dest, int destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        dest.putDouble(destOffset, Math.fma(-2.0, Math.fma(rRY, rRY, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(rRX, rRY, -_t1));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(rRX, rRZ, _t2));
        dest.putDouble(destOffset + 24, rTX);
        dest.putDouble(destOffset + 32, 2.0 * Math.fma(rRX, rRY, _t1));
        dest.putDouble(destOffset + 40, Math.fma(-2.0, Math.fma(rRX, rRX, _t0), 1.0));
        dest.putDouble(destOffset + 48, 2.0 * Math.fma(rRY, rRZ, -(rRX * rRW)));
        dest.putDouble(destOffset + 56, rTY);
        dest.putDouble(destOffset + 64, 2.0 * Math.fma(rRX, rRZ, -_t2));
        dest.putDouble(destOffset + 72, 2.0 * Math.fma(rRX, rRW, rRY * rRZ));
        dest.putDouble(destOffset + 80, Math.fma(-2.0, Math.fma(rRX, rRX, rRY * rRY), 1.0));
        dest.putDouble(destOffset + 88, rTZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromTransform_unsafe(java.nio.ByteBuffer dest, int destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeFromTransform_unsafe(_destBase, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromTransform_api(java.nio.ByteBuffer dest, int destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        dest.putDouble(destOffset, Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX));
        dest.putDouble(destOffset + 8, Math.fma(tRX, tRY, -_t4) * _t1);
        dest.putDouble(destOffset + 16, Math.fma(tRX, tRZ, _t5) * _t2);
        dest.putDouble(destOffset + 24, tTX);
        dest.putDouble(destOffset + 32, Math.fma(tRX, tRY, _t4) * _t0);
        dest.putDouble(destOffset + 40, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY));
        dest.putDouble(destOffset + 48, Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        dest.putDouble(destOffset + 56, tTY);
        dest.putDouble(destOffset + 64, Math.fma(tRX, tRZ, -_t5) * _t0);
        dest.putDouble(destOffset + 72, Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        dest.putDouble(destOffset + 80, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        dest.putDouble(destOffset + 88, tTZ);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self20);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self21);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer to4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.to4x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
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
        dest.putDouble(destOffset + 96, _self03);
        dest.putDouble(destOffset + 104, _self13);
        dest.putDouble(destOffset + 112, _self23);
        dest.putDouble(destOffset + 120, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t2 = 1.0 - _self00;
        double _t14 = _self22 + (_self00 + _self11);
        double _t15 = 1.0 + _t14;
        double _t16 = _self00 + (1.0 - _self11 - _self22);
        double _t17 = _self11 + (_t2 - _self22);
        double _t18 = _self22 + (_t2 - _self11);
        return toDualQuat_api_s7675600_1(dest, destOffset, _self00, _self03, _self11, _self13, _self22, _self23, -_self23, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t14, _t15, _t16, _t17, _t18, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)));
    }

    /** Piece 2 of {@code toDualQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toDualQuat_api_s7675600_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self03, double _self11, double _self13, double _self22, double _self23, double _t0, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _t18, double _sp0, double _sp1, double _sp2, double _sp3) {
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
        dest.putDouble(destOffset, _t63);
        dest.putDouble(destOffset + 8, _t64);
        dest.putDouble(destOffset + 16, _t65);
        dest.putDouble(destOffset + 24, _t66);
        dest.putDouble(destOffset + 32, 0.5 * Math.fma(_t0, _t64, Math.fma(_self03, _t66, _self13 * _t65)));
        dest.putDouble(destOffset + 40, 0.5 * Math.fma(_self23, _t63, Math.fma(_self13, _t66, -(_self03 * _t65))));
        return toDualQuat_api_s7675600_2(dest, destOffset, _self03, _self13, _self23, _t0, _t63, _t64, _t65, _t66);
    }

    /** Piece 3 of {@code toDualQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toDualQuat_api_s7675600_2(java.nio.ByteBuffer dest, int destOffset, double _self03, double _self13, double _self23, double _t0, double _t63, double _t64, double _t65, double _t66) {
        dest.putDouble(destOffset + 48, 0.5 * Math.fma(_self23, _t66, Math.fma(_self03, _t64, -(_self13 * _t63))));
        dest.putDouble(destOffset + 56, 0.5 * Math.fma(_t0, _t65, Math.fma(-_self13, _t64, -(_self03 * _t63))));
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.toRigid_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        return toRigid_api_s6c0c7a5e_1(dest, destOffset, src, srcOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self11, -_self22, (1.0 / java.lang.Math.sqrt(_ct0)), (1.0 / java.lang.Math.sqrt(_ct1)), Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10)));
    }

    /** Piece 2 of {@code toRigid_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_s6c0c7a5e_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t15, double _t16, double _ct2) {
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
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
        return toRigid_api_s6c0c7a5e_2(dest, destOffset, _self03, _self11, _self13, _self22, _self23, _t0, _t1, _t15, _t16, _t19, _t24, Math.fma(_self12, _t16, _t23), Math.fma(_self21, _t15, -_t20), _t47, _t51, 1.0 - _t47, Math.fma(_self01, _t15, _t48), Math.fma(_self02, _t16, _t49), Math.fma(_self02, _t16, -_t49), Math.fma(-_self01, _t15, _t48), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code toRigid_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_s6c0c7a5e_2(java.nio.ByteBuffer dest, int destOffset, double _self03, double _self11, double _self13, double _self22, double _self23, double _t0, double _t1, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t51, double _t52, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0) {
        double _t65 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t52));
        double _t66 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t52));
        double _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        return toRigid_api_s6c0c7a5e_3(dest, destOffset, _self03, _self11, _self13, _self22, _self23, _t15, _t16, _t19, _t24, _t31, _t35, _t47, _t54, _t55, _t56, _t57, _t63, _sp0, _t65, _t66, _t67, 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)));
    }

    /** Piece 4 of {@code toRigid_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_s6c0c7a5e_3(java.nio.ByteBuffer dest, int destOffset, double _self03, double _self11, double _self13, double _self22, double _self23, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65, double _t66, double _t67, double _sp1, double _sp2, double _sp3) {
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
        return toRigid_api_s6c0c7a5e_4(dest, destOffset, _self03, _self13, _self23);
    }

    /** Piece 5 of {@code toRigid_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toRigid_api_s6c0c7a5e_4(java.nio.ByteBuffer dest, int destOffset, double _self03, double _self13, double _self23) {
        dest.putDouble(destOffset, _self03);
        dest.putDouble(destOffset + 8, _self13);
        dest.putDouble(destOffset + 16, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.toRigid_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsByteBuffer.toRigid_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer toRigid_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.toRigid_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
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
        dest.putDouble(destOffset, _self03);
        dest.putDouble(destOffset + 8, _self13);
        dest.putDouble(destOffset + 16, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.toTransform_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t14 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        return toTransform_api_s67e81cf7_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self11, -_self22, _t12, _t13, _t14, (1.0 / java.lang.Math.sqrt(_t12)));
    }

    /** Piece 2 of {@code toTransform_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_s67e81cf7_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t12, double _t13, double _t14, double _t15) {
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
        return toTransform_api_s67e81cf7_2(dest, destOffset, _self03, _self11, _self13, _self22, _self23, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, Math.fma(_self12, _t16, _t24), Math.fma(_self21, _t15, -_t21), java.lang.Math.max(_t25, _t20), _t47, _t48, 1.0 + _t48, 1.0 - _t48, Math.fma(_self01, _t15, _t49), Math.fma(_self02, _t16, _t50), Math.fma(_self02, _t16, -_t50), Math.fma(-_self01, _t15, _t49), Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48)));
    }

    /** Piece 3 of {@code toTransform_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_s67e81cf7_2(java.nio.ByteBuffer dest, int destOffset, double _self03, double _self11, double _self13, double _self22, double _self23, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t63) {
        double _t64 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t64));
        double _t66 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53));
        double _t67 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53));
        double _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        dest.putDouble(destOffset, _self03);
        dest.putDouble(destOffset + 8, _self13);
        dest.putDouble(destOffset + 16, _self23);
        dest.putDouble(destOffset + 24, _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        return toTransform_api_s67e81cf7_3(dest, destOffset, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t55, _t56, _t57, _t58, _t63, _t64, _sp0, _t66, _t67, _sp1, _sp2, 0.5 * (1.0 / java.lang.Math.sqrt(_t68)));
    }

    /** Piece 4 of {@code toTransform_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toTransform_api_s67e81cf7_3(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t13, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t55, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t66, double _t67, double _sp1, double _sp2, double _sp3) {
        dest.putDouble(destOffset + 32, _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        dest.putDouble(destOffset + 40, _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * java.lang.Math.sqrt(_t67));
        dest.putDouble(destOffset + 48, _t63 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        dest.putDouble(destOffset + 56, _t47 < 0.0 ? -_t18 : _t18);
        dest.putDouble(destOffset + 64, java.lang.Math.sqrt(_t12));
        dest.putDouble(destOffset + 72, java.lang.Math.sqrt(_t13));
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.toTransform_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsByteBuffer.toTransform_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer toTransform_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.toTransform_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
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
        dest.putDouble(destOffset, _self03);
        dest.putDouble(destOffset + 8, _self13);
        dest.putDouble(destOffset + 16, _self23);
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
        Double3x4OpsKernelsAddress.decomposeRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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
        Double3x4OpsKernelsAddress.decomposeScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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
        Double3x4OpsKernelsAddress.decomposeSkew_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeSkew_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
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

    public static java.nio.ByteBuffer decomposeTRS_unsafe(java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.decomposeTRS_unsafe(_translationBase, _rotationBase, _scaleBase, _srcBase);
        return translation;
    }

    public static java.nio.ByteBuffer decomposeTRS_api(java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
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
        double _t20 = -Math.fma(_self21, _t8, Math.fma(_self01, _t9, _self11 * _t10));
        return decomposeTRS_api_s209c7d2e_1(translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, _self01, _self02, _self03, _self11, _self12, _self13, _self22, _self23, _t4, _t8, _t9, _t10, _t20, -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10)), Math.fma(_t20, _t8, _self21));
    }

    /** Piece 2 of {@code decomposeTRS_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeTRS_api_s209c7d2e_1(java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, double _self01, double _self02, double _self03, double _self11, double _self12, double _self13, double _self22, double _self23, double _t4, double _t8, double _t9, double _t10, double _t20, double _t21, double _t22) {
        double _t23 = Math.fma(_t20, _t9, _self01);
        double _t24 = Math.fma(_t20, _t10, _self11);
        double _t30 = Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t30));
        double _t35, _t36, _t37;
        if (_t30 != 0.0) {
            _t35 = _t23 * _t31;
            _t36 = _t22 * _t31;
            _t37 = _t24 * _t31;
        } else {
            _t35 = 0.0;
            _t36 = 0.0;
            _t37 = 0.0;
        }
        double _t41 = -Math.fma(Math.fma(_t21, _t8, _self22), _t36, Math.fma(Math.fma(_t21, _t9, _self02), _t35, Math.fma(_t21, _t10, _self12) * _t37));
        double _t45 = Math.fma(_t21, _t8, Math.fma(_t41, _t36, _self22));
        double _t46 = Math.fma(_t21, _t9, Math.fma(_t41, _t35, _self02));
        double _t47 = Math.fma(_t21, _t10, Math.fma(_t41, _t37, _self12));
        double _t50 = Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        double _t55, _t56, _t57;
        if (_t50 != 0.0) {
            _t55 = _t47 * _t51;
            _t56 = _t46 * _t51;
            _t57 = _t45 * _t51;
        } else {
            _t55 = 0.0;
            _t56 = 0.0;
            _t57 = 0.0;
        }
        return decomposeTRS_api_s209c7d2e_2(translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, _self03, _self13, _self23, _t4, _t8, _t9, _t10, _t30, _t35, _t36, _t37, _t50, _t55, _t56, _t57, _t36 - _t55);
    }

    /** Piece 3 of {@code decomposeTRS_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeTRS_api_s209c7d2e_2(java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, double _self03, double _self13, double _self23, double _t4, double _t8, double _t9, double _t10, double _t30, double _t35, double _t36, double _t37, double _t50, double _t55, double _t56, double _t57, double _t61) {
        double _t73 = Math.fma(Math.fma(_t35, _t55, -(_t37 * _t56)), _t8, Math.fma(Math.fma(_t37, _t57, -(_t36 * _t55)), _t9, Math.fma(_t36, _t56, -(_t35 * _t57)) * _t10));
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
        translation.putDouble(translationOffset, _self03);
        translation.putDouble(translationOffset + 8, _self13);
        translation.putDouble(translationOffset + 16, _self23);
        return decomposeTRS_api_s209c7d2e_3(translation, rotation, rotationOffset, scale, scaleOffset, _t4, _t30, _t37, _t50, _t57, _t61, _t36 + _t55, _t73, _t74, _t75 + _t35, _t75 - _t35, _t76 + _t56, _t56 - _t76, _t83, _t87, _t88, _t89, _t90, 0.5 * (1.0 / java.lang.Math.sqrt(_t87)), 0.5 * (1.0 / java.lang.Math.sqrt(_t89)), 0.5 * (1.0 / java.lang.Math.sqrt(_t90)), 0.5 * (1.0 / java.lang.Math.sqrt(_t88)));
    }

    /** Piece 4 of {@code decomposeTRS_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeTRS_api_s209c7d2e_3(java.nio.ByteBuffer translation, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, double _t4, double _t30, double _t37, double _t50, double _t57, double _t61, double _t64, double _t73, double _t74, double _t78, double _t79, double _t81, double _t82, double _t83, double _t87, double _t88, double _t89, double _t90, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t83 > 0.0) {
            rotation.putDouble(rotationOffset, _sp0 * _t61);
            rotation.putDouble(rotationOffset + 8, _sp0 * _t82);
            rotation.putDouble(rotationOffset + 16, _sp0 * _t79);
            rotation.putDouble(rotationOffset + 24, 0.5 * java.lang.Math.sqrt(_t87));
        } else {
            if (_t74 > java.lang.Math.max(_t37, _t57)) {
                rotation.putDouble(rotationOffset, 0.5 * java.lang.Math.sqrt(_t88));
                rotation.putDouble(rotationOffset + 8, _sp3 * _t78);
                rotation.putDouble(rotationOffset + 16, _sp3 * _t81);
                rotation.putDouble(rotationOffset + 24, _sp3 * _t61);
            } else {
                if (_t37 > _t57) {
                    rotation.putDouble(rotationOffset, _sp1 * _t78);
                    rotation.putDouble(rotationOffset + 8, 0.5 * java.lang.Math.sqrt(_t89));
                    rotation.putDouble(rotationOffset + 16, _sp1 * _t64);
                    rotation.putDouble(rotationOffset + 24, _sp1 * _t82);
                } else {
                    rotation.putDouble(rotationOffset, _sp2 * _t81);
                    rotation.putDouble(rotationOffset + 8, _sp2 * _t64);
                    rotation.putDouble(rotationOffset + 16, 0.5 * java.lang.Math.sqrt(_t90));
                    rotation.putDouble(rotationOffset + 24, _sp2 * _t79);
                }
            }
        }
        return decomposeTRS_api_s209c7d2e_4(translation, scale, scaleOffset, _t4, _t30, _t50, _t73);
    }

    /** Piece 5 of {@code decomposeTRS_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeTRS_api_s209c7d2e_4(java.nio.ByteBuffer translation, java.nio.ByteBuffer scale, int scaleOffset, double _t4, double _t30, double _t50, double _t73) {
        scale.putDouble(scaleOffset, _t73 < 0.0 ? -_t4 : _t4);
        scale.putDouble(scaleOffset + 8, java.lang.Math.sqrt(_t30));
        scale.putDouble(scaleOffset + 16, java.lang.Math.sqrt(_t50));
        return translation;
    }

    public static java.nio.ByteBuffer makeIdentity_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        for (int _i = 0; _i < 12; _i++) {
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
        Double3x4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _right00 = right.getDouble(rightOffset);
        double _right01 = right.getDouble(rightOffset + 8);
        double _right02 = right.getDouble(rightOffset + 16);
        double _right03 = right.getDouble(rightOffset + 24);
        double _right10 = right.getDouble(rightOffset + 32);
        double _right11 = right.getDouble(rightOffset + 40);
        double _right12 = right.getDouble(rightOffset + 48);
        double _right13 = right.getDouble(rightOffset + 56);
        double _right20 = right.getDouble(rightOffset + 64);
        double _right21 = right.getDouble(rightOffset + 72);
        double _right22 = right.getDouble(rightOffset + 80);
        double _right23 = right.getDouble(rightOffset + 88);
        return mul_api_se87c1ea8_1(dest, destOffset, src, srcOffset, _right00, _right01, _right02, _right03, _right10, _right11, _right12, _right13, _right20, _right21, _right22, _right23);
    }

    /** Piece 2 of {@code mul_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer mul_api_se87c1ea8_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double _right00, double _right01, double _right02, double _right03, double _right10, double _right11, double _right12, double _right13, double _right20, double _right21, double _right22, double _right23) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            dest.putDouble(destOffset + (_lo + 2) * 8, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            dest.putDouble(destOffset + (_lo + 3) * 8, Math.fma(_right03, _eself0, Math.fma(_right13, _eself1, Math.fma(_right23, _eself2, _eself3))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double3x4OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_right00, _eself0, _right10 * _eself1));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_right01, _eself0, _right11 * _eself1));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double3x4OpsKernelsAddress.mulMat2x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        double _right02 = right.getDouble(rightOffset + 32);
        double _right12 = right.getDouble(rightOffset + 40);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_right00, _eself0, _right10 * _eself1));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_right01, _eself0, _right11 * _eself1));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, Math.fma(_right02, _eself0, Math.fma(_right12, _eself1, _eself3)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double3x4OpsKernelsAddress.mulMat3x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right20 = right.getDouble(rightOffset + 16);
        double _right01 = right.getDouble(rightOffset + 24);
        double _right11 = right.getDouble(rightOffset + 32);
        double _right21 = right.getDouble(rightOffset + 40);
        double _right02 = right.getDouble(rightOffset + 48);
        double _right12 = right.getDouble(rightOffset + 56);
        double _right22 = right.getDouble(rightOffset + 64);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            dest.putDouble(destOffset + (_lo + 2) * 8, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double3x4OpsKernelsAddress.mulMat4x4_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        return mulMat4x4_api_s8ece91a_1(dest, destOffset, right, rightOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code mulMat4x4_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer mulMat4x4_api_s8ece91a_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer right, int rightOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23) {
        for (int _l = 0; _l < 4; _l++) {
            int _lo = _l * 4;
            double _eright0 = right.getDouble(rightOffset + _lo * 8);
            double _eright1 = right.getDouble(rightOffset + (_lo + 1) * 8);
            double _eright2 = right.getDouble(rightOffset + (_lo + 2) * 8);
            double _eright3 = right.getDouble(rightOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_eright3, _self03, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01))));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_eright3, _self13, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11))));
            dest.putDouble(destOffset + (_lo + 2) * 8, Math.fma(_eright3, _self23, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21))));
            dest.putDouble(destOffset + (_lo + 3) * 8, _eright3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x4OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        return preMul_api_saefb21fb_1(dest, destOffset, other, otherOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preMul_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMul_api_saefb21fb_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eother0 = other.getDouble(otherOffset + _lo * 8);
            double _eother1 = other.getDouble(otherOffset + (_lo + 1) * 8);
            double _eother2 = other.getDouble(otherOffset + (_lo + 2) * 8);
            double _eother3 = other.getDouble(otherOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, Math.fma(_eother2, _self20, Math.fma(_eother0, _self00, _eother1 * _self10)));
            dest.putDouble(destOffset + (_lo + 1) * 8, Math.fma(_eother2, _self21, Math.fma(_eother0, _self01, _eother1 * _self11)));
            dest.putDouble(destOffset + (_lo + 2) * 8, Math.fma(_eother2, _self22, Math.fma(_eother0, _self02, _eother1 * _self12)));
            dest.putDouble(destOffset + (_lo + 3) * 8, Math.fma(_eother0, _self03, Math.fma(_eother1, _self13, Math.fma(_eother2, _self23, _eother3))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x4OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, Math.fma(_other00, _self00, _other01 * _self10));
        dest.putDouble(destOffset + 8, Math.fma(_other00, _self01, _other01 * _self11));
        dest.putDouble(destOffset + 16, Math.fma(_other00, _self02, _other01 * _self12));
        dest.putDouble(destOffset + 24, Math.fma(_other00, _self03, _other01 * _self13));
        dest.putDouble(destOffset + 32, Math.fma(_other10, _self00, _other11 * _self10));
        return preMulMat2x2_api_s18058459_1(dest, destOffset, _self01, _self02, _self03, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other10, _other11);
    }

    /** Piece 2 of {@code preMulMat2x2_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMulMat2x2_api_s18058459_1(java.nio.ByteBuffer dest, int destOffset, double _self01, double _self02, double _self03, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other10, double _other11) {
        dest.putDouble(destOffset + 40, Math.fma(_other10, _self01, _other11 * _self11));
        dest.putDouble(destOffset + 48, Math.fma(_other10, _self02, _other11 * _self12));
        dest.putDouble(destOffset + 56, Math.fma(_other10, _self03, _other11 * _self13));
        dest.putDouble(destOffset + 64, _self20);
        dest.putDouble(destOffset + 72, _self21);
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x4OpsKernelsAddress.preMulMat2x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        double _other02 = other.getDouble(otherOffset + 32);
        double _other12 = other.getDouble(otherOffset + 40);
        dest.putDouble(destOffset, Math.fma(_other00, _self00, _other01 * _self10));
        dest.putDouble(destOffset + 8, Math.fma(_other00, _self01, _other01 * _self11));
        dest.putDouble(destOffset + 16, Math.fma(_other00, _self02, _other01 * _self12));
        dest.putDouble(destOffset + 24, Math.fma(_other00, _self03, Math.fma(_other01, _self13, _other02)));
        return preMulMat2x3_api_sc77f3968_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other10, _other11, _other12);
    }

    /** Piece 2 of {@code preMulMat2x3_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMulMat2x3_api_sc77f3968_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other10, double _other11, double _other12) {
        dest.putDouble(destOffset + 32, Math.fma(_other10, _self00, _other11 * _self10));
        dest.putDouble(destOffset + 40, Math.fma(_other10, _self01, _other11 * _self11));
        dest.putDouble(destOffset + 48, Math.fma(_other10, _self02, _other11 * _self12));
        dest.putDouble(destOffset + 56, Math.fma(_other10, _self03, Math.fma(_other11, _self13, _other12)));
        dest.putDouble(destOffset + 64, _self20);
        dest.putDouble(destOffset + 72, _self21);
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x4OpsKernelsAddress.preMulMat3x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        dest.putDouble(destOffset, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        return preMulMat3x3_api_sf5563e81_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMulMat3x3_api_sf5563e81_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        dest.putDouble(destOffset + 8, Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11)));
        dest.putDouble(destOffset + 16, Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12)));
        dest.putDouble(destOffset + 24, Math.fma(_other02, _self23, Math.fma(_other00, _self03, _other01 * _self13)));
        dest.putDouble(destOffset + 32, Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10)));
        dest.putDouble(destOffset + 40, Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11)));
        dest.putDouble(destOffset + 48, Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12)));
        dest.putDouble(destOffset + 56, Math.fma(_other12, _self23, Math.fma(_other10, _self03, _other11 * _self13)));
        dest.putDouble(destOffset + 64, Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10)));
        dest.putDouble(destOffset + 72, Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11)));
        dest.putDouble(destOffset + 80, Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12)));
        dest.putDouble(destOffset + 88, Math.fma(_other22, _self23, Math.fma(_other20, _self03, _other21 * _self13)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x4OpsKernelsAddress.preMulMat4x4_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other30 = other.getDouble(otherOffset + 24);
        double _other01 = other.getDouble(otherOffset + 32);
        double _other11 = other.getDouble(otherOffset + 40);
        double _other21 = other.getDouble(otherOffset + 48);
        double _other31 = other.getDouble(otherOffset + 56);
        double _other02 = other.getDouble(otherOffset + 64);
        double _other12 = other.getDouble(otherOffset + 72);
        double _other22 = other.getDouble(otherOffset + 80);
        return preMulMat4x4_api_sbdd22149_1(dest, destOffset, other, otherOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat4x4_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMulMat4x4_api_sbdd22149_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other10, double _other20, double _other30, double _other01, double _other11, double _other21, double _other31, double _other02, double _other12, double _other22) {
        double _other32 = other.getDouble(otherOffset + 88);
        double _other03 = other.getDouble(otherOffset + 96);
        double _other13 = other.getDouble(otherOffset + 104);
        double _other23 = other.getDouble(otherOffset + 112);
        double _other33 = other.getDouble(otherOffset + 120);
        dest.putDouble(destOffset, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        dest.putDouble(destOffset + 8, Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10)));
        dest.putDouble(destOffset + 16, Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10)));
        dest.putDouble(destOffset + 24, Math.fma(_other32, _self20, Math.fma(_other30, _self00, _other31 * _self10)));
        dest.putDouble(destOffset + 32, Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11)));
        dest.putDouble(destOffset + 40, Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11)));
        dest.putDouble(destOffset + 48, Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11)));
        return preMulMat4x4_api_sbdd22149_2(dest, destOffset, _self01, _self02, _self03, _self11, _self12, _self13, _self21, _self22, _self23, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32, _other03, _other13, _other23, _other33);
    }

    /** Piece 3 of {@code preMulMat4x4_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMulMat4x4_api_sbdd22149_2(java.nio.ByteBuffer dest, int destOffset, double _self01, double _self02, double _self03, double _self11, double _self12, double _self13, double _self21, double _self22, double _self23, double _other00, double _other10, double _other20, double _other30, double _other01, double _other11, double _other21, double _other31, double _other02, double _other12, double _other22, double _other32, double _other03, double _other13, double _other23, double _other33) {
        dest.putDouble(destOffset + 56, Math.fma(_other32, _self21, Math.fma(_other30, _self01, _other31 * _self11)));
        dest.putDouble(destOffset + 64, Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12)));
        dest.putDouble(destOffset + 72, Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12)));
        dest.putDouble(destOffset + 80, Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12)));
        dest.putDouble(destOffset + 88, Math.fma(_other32, _self22, Math.fma(_other30, _self02, _other31 * _self12)));
        dest.putDouble(destOffset + 96, Math.fma(_other00, _self03, Math.fma(_other01, _self13, Math.fma(_other02, _self23, _other03))));
        dest.putDouble(destOffset + 104, Math.fma(_other10, _self03, Math.fma(_other11, _self13, Math.fma(_other12, _self23, _other13))));
        dest.putDouble(destOffset + 112, Math.fma(_other20, _self03, Math.fma(_other21, _self13, Math.fma(_other22, _self23, _other23))));
        dest.putDouble(destOffset + 120, Math.fma(_other30, _self03, Math.fma(_other31, _self13, Math.fma(_other32, _self23, _other33))));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3x4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            double _eother = other.getDouble(otherOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, Math.fma(weight, _eother, _eself));
        }
        return dest;
    }

    public static java.nio.ByteBuffer composeTRS_unsafe(java.nio.ByteBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.composeTRS_unsafe(_destBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRS_api(java.nio.ByteBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleX + scaleX;
        double _t1 = scaleY + scaleY;
        double _t2 = scaleZ + scaleZ;
        double _t3 = rotationZ * rotationZ;
        double _t4 = rotationZ * rotationW;
        double _t5 = rotationY * rotationW;
        dest.putDouble(destOffset, Math.fma(-Math.fma(rotationY, rotationY, _t3), _t0, scaleX));
        dest.putDouble(destOffset + 8, Math.fma(rotationX, rotationY, -_t4) * _t1);
        dest.putDouble(destOffset + 16, Math.fma(rotationX, rotationZ, _t5) * _t2);
        dest.putDouble(destOffset + 24, translationX);
        dest.putDouble(destOffset + 32, Math.fma(rotationX, rotationY, _t4) * _t0);
        dest.putDouble(destOffset + 40, Math.fma(-Math.fma(rotationX, rotationX, _t3), _t1, scaleY));
        dest.putDouble(destOffset + 48, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t2);
        dest.putDouble(destOffset + 56, translationY);
        dest.putDouble(destOffset + 64, Math.fma(rotationX, rotationZ, -_t5) * _t0);
        dest.putDouble(destOffset + 72, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t1);
        dest.putDouble(destOffset + 80, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t2, scaleZ));
        dest.putDouble(destOffset + 88, translationZ);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRS_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        Double3x4OpsKernelsAddress.composeTRS_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRS_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset) {
        double _translationx = translation.getDouble(translationOffset);
        double _translationy = translation.getDouble(translationOffset + 8);
        double _translationz = translation.getDouble(translationOffset + 16);
        double _rotationx = rotation.getDouble(rotationOffset);
        double _rotationy = rotation.getDouble(rotationOffset + 8);
        double _rotationz = rotation.getDouble(rotationOffset + 16);
        double _rotationw = rotation.getDouble(rotationOffset + 24);
        double _scalex = scale.getDouble(scaleOffset);
        double _scaley = scale.getDouble(scaleOffset + 8);
        double _scalez = scale.getDouble(scaleOffset + 16);
        double _t0 = _scalex + _scalex;
        double _t1 = _scaley + _scaley;
        double _t2 = _scalez + _scalez;
        double _t3 = _rotationz * _rotationz;
        double _t4 = _rotationz * _rotationw;
        double _t5 = _rotationy * _rotationw;
        dest.putDouble(destOffset, Math.fma(-Math.fma(_rotationy, _rotationy, _t3), _t0, _scalex));
        dest.putDouble(destOffset + 8, Math.fma(_rotationx, _rotationy, -_t4) * _t1);
        dest.putDouble(destOffset + 16, Math.fma(_rotationx, _rotationz, _t5) * _t2);
        dest.putDouble(destOffset + 24, _translationx);
        dest.putDouble(destOffset + 32, Math.fma(_rotationx, _rotationy, _t4) * _t0);
        dest.putDouble(destOffset + 40, Math.fma(-Math.fma(_rotationx, _rotationx, _t3), _t1, _scaley));
        return composeTRS_api_seb523285_1(dest, destOffset, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scalez, _t0, _t1, _t2, _t5);
    }

    /** Piece 2 of {@code composeTRS_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRS_api_seb523285_1(java.nio.ByteBuffer dest, int destOffset, double _translationy, double _translationz, double _rotationx, double _rotationy, double _rotationz, double _rotationw, double _scalez, double _t0, double _t1, double _t2, double _t5) {
        dest.putDouble(destOffset + 48, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t2);
        dest.putDouble(destOffset + 56, _translationy);
        dest.putDouble(destOffset + 64, Math.fma(_rotationx, _rotationz, -_t5) * _t0);
        dest.putDouble(destOffset + 72, Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t1);
        dest.putDouble(destOffset + 80, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t2, _scalez));
        dest.putDouble(destOffset + 88, _translationz);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSAround_unsafe(java.nio.ByteBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.composeTRSAround_unsafe(_destBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSAround_api(java.nio.ByteBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ, double pivotX, double pivotY, double pivotZ) {
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
        dest.putDouble(destOffset, Math.fma(-_t15, _t3, scaleX));
        dest.putDouble(destOffset + 8, _t27);
        dest.putDouble(destOffset + 16, _t24);
        dest.putDouble(destOffset + 24, Math.fma(pivotX, Math.fma(_t15, _t3, 1.0 - scaleX), Math.fma(_t0, _t27, Math.fma(_t1, _t24, translationX))));
        dest.putDouble(destOffset + 32, _t25);
        return composeTRSAround_api_sa972dba1_1(dest, destOffset, translationY, translationZ, scaleY, scaleZ, pivotY, pivotZ, _t0, _t1, -pivotX, _t4, _t5, Math.fma(rotationX, rotationX, _t6), Math.fma(rotationX, rotationX, rotationY * rotationY), _t25, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t4, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t5, Math.fma(rotationX, rotationZ, -_t8) * _t3);
    }

    /** Piece 2 of {@code composeTRSAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSAround_api_sa972dba1_1(java.nio.ByteBuffer dest, int destOffset, double translationY, double translationZ, double scaleY, double scaleZ, double pivotY, double pivotZ, double _t0, double _t1, double _t2, double _t4, double _t5, double _t18, double _t20, double _t25, double _t26, double _t28, double _t29) {
        dest.putDouble(destOffset + 40, Math.fma(-_t18, _t4, scaleY));
        dest.putDouble(destOffset + 48, _t28);
        dest.putDouble(destOffset + 56, Math.fma(pivotY, Math.fma(_t18, _t4, 1.0 - scaleY), Math.fma(_t2, _t25, Math.fma(_t1, _t28, translationY))));
        dest.putDouble(destOffset + 64, _t29);
        dest.putDouble(destOffset + 72, _t26);
        dest.putDouble(destOffset + 80, Math.fma(-_t20, _t5, scaleZ));
        dest.putDouble(destOffset + 88, Math.fma(pivotZ, Math.fma(_t20, _t5, 1.0 - scaleZ), Math.fma(_t2, _t29, Math.fma(_t0, _t26, translationZ))));
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x4OpsKernelsAddress.composeTRSAround_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _translationx = translation.getDouble(translationOffset);
        double _translationy = translation.getDouble(translationOffset + 8);
        double _translationz = translation.getDouble(translationOffset + 16);
        double _rotationx = rotation.getDouble(rotationOffset);
        double _rotationy = rotation.getDouble(rotationOffset + 8);
        double _rotationz = rotation.getDouble(rotationOffset + 16);
        double _rotationw = rotation.getDouble(rotationOffset + 24);
        double _scalex = scale.getDouble(scaleOffset);
        double _scaley = scale.getDouble(scaleOffset + 8);
        double _scalez = scale.getDouble(scaleOffset + 16);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _pivotz = pivot.getDouble(pivotOffset + 16);
        double _t3 = _scalex + _scalex;
        double _t4 = _scaley + _scaley;
        double _t5 = _scalez + _scalez;
        double _t6 = _rotationz * _rotationz;
        double _t7 = _rotationz * _rotationw;
        double _t8 = _rotationy * _rotationw;
        return composeTRSAround_api_sab953252_1(dest, destOffset, _translationx, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scalex, _scaley, _scalez, _pivotx, _pivoty, _pivotz, -_pivoty, -_pivotz, -_pivotx, _t3, _t4, _t5, _t7, _t8, Math.fma(_rotationy, _rotationy, _t6), Math.fma(_rotationx, _rotationx, _t6), Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), Math.fma(_rotationx, _rotationz, _t8) * _t5, Math.fma(_rotationx, _rotationy, _t7) * _t3, Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t4);
    }

    /** Piece 2 of {@code composeTRSAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSAround_api_sab953252_1(java.nio.ByteBuffer dest, int destOffset, double _translationx, double _translationy, double _translationz, double _rotationx, double _rotationy, double _rotationz, double _rotationw, double _scalex, double _scaley, double _scalez, double _pivotx, double _pivoty, double _pivotz, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t7, double _t8, double _t15, double _t18, double _t20, double _t24, double _t25, double _t26) {
        double _t27 = Math.fma(_rotationx, _rotationy, -_t7) * _t4;
        double _t28 = Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t5;
        double _t29 = Math.fma(_rotationx, _rotationz, -_t8) * _t3;
        dest.putDouble(destOffset, Math.fma(-_t15, _t3, _scalex));
        dest.putDouble(destOffset + 8, _t27);
        dest.putDouble(destOffset + 16, _t24);
        dest.putDouble(destOffset + 24, Math.fma(_pivotx, Math.fma(_t15, _t3, 1.0 - _scalex), Math.fma(_t0, _t27, Math.fma(_t1, _t24, _translationx))));
        dest.putDouble(destOffset + 32, _t25);
        dest.putDouble(destOffset + 40, Math.fma(-_t18, _t4, _scaley));
        dest.putDouble(destOffset + 48, _t28);
        dest.putDouble(destOffset + 56, Math.fma(_pivoty, Math.fma(_t18, _t4, 1.0 - _scaley), Math.fma(_t2, _t25, Math.fma(_t1, _t28, _translationy))));
        dest.putDouble(destOffset + 64, _t29);
        dest.putDouble(destOffset + 72, _t26);
        dest.putDouble(destOffset + 80, Math.fma(-_t20, _t5, _scalez));
        dest.putDouble(destOffset + 88, Math.fma(_pivotz, Math.fma(_t20, _t5, 1.0 - _scalez), Math.fma(_t2, _t29, Math.fma(_t0, _t26, _translationz))));
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x4OpsKernelsAddress.composeTRSMul_unsafe(_destBase, _mBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _m00 = m.getDouble(mOffset);
        double _m01 = m.getDouble(mOffset + 8);
        double _m02 = m.getDouble(mOffset + 16);
        double _m03 = m.getDouble(mOffset + 24);
        double _m10 = m.getDouble(mOffset + 32);
        double _m11 = m.getDouble(mOffset + 40);
        double _m12 = m.getDouble(mOffset + 48);
        double _m13 = m.getDouble(mOffset + 56);
        double _m20 = m.getDouble(mOffset + 64);
        double _m21 = m.getDouble(mOffset + 72);
        double _m22 = m.getDouble(mOffset + 80);
        double _m23 = m.getDouble(mOffset + 88);
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t4 = rotationZ * rotationZ;
        double _t5 = rotationZ * rotationW;
        return composeTRSMul_api_s7c5112ae_1(dest, destOffset, translationX, translationY, translationZ, rotationX, rotationY, scaleY, scaleZ, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t0, _t2, _t4, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX));
    }

    /** Piece 2 of {@code composeTRSMul_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSMul_api_s7c5112ae_1(java.nio.ByteBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double scaleY, double scaleZ, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t0, double _t2, double _t4, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30) {
        double _t31 = Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY);
        dest.putDouble(destOffset, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        dest.putDouble(destOffset + 8, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        dest.putDouble(destOffset + 16, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        dest.putDouble(destOffset + 24, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, translationX))));
        dest.putDouble(destOffset + 32, Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31)));
        dest.putDouble(destOffset + 40, Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31)));
        dest.putDouble(destOffset + 48, Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31)));
        dest.putDouble(destOffset + 56, Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, translationY))));
        return composeTRSMul_api_s7c5112ae_2(dest, destOffset, translationZ, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t26, _t29, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
    }

    /** Piece 3 of {@code composeTRSMul_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSMul_api_s7c5112ae_2(java.nio.ByteBuffer dest, int destOffset, double translationZ, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t26, double _t29, double _t32) {
        dest.putDouble(destOffset + 64, Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26)));
        dest.putDouble(destOffset + 72, Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26)));
        dest.putDouble(destOffset + 80, Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26)));
        dest.putDouble(destOffset + 88, Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, translationZ))));
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double3x4OpsKernelsAddress.composeTRSMul_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset) {
        double _translationx = translation.getDouble(translationOffset);
        double _translationy = translation.getDouble(translationOffset + 8);
        double _translationz = translation.getDouble(translationOffset + 16);
        double _rotationx = rotation.getDouble(rotationOffset);
        double _rotationy = rotation.getDouble(rotationOffset + 8);
        double _rotationz = rotation.getDouble(rotationOffset + 16);
        double _rotationw = rotation.getDouble(rotationOffset + 24);
        double _scalex = scale.getDouble(scaleOffset);
        double _scaley = scale.getDouble(scaleOffset + 8);
        double _scalez = scale.getDouble(scaleOffset + 16);
        double _m00 = m.getDouble(mOffset);
        double _m01 = m.getDouble(mOffset + 8);
        double _m02 = m.getDouble(mOffset + 16);
        double _m03 = m.getDouble(mOffset + 24);
        double _m10 = m.getDouble(mOffset + 32);
        double _m11 = m.getDouble(mOffset + 40);
        double _m12 = m.getDouble(mOffset + 48);
        double _m13 = m.getDouble(mOffset + 56);
        double _m20 = m.getDouble(mOffset + 64);
        double _m21 = m.getDouble(mOffset + 72);
        double _m22 = m.getDouble(mOffset + 80);
        double _m23 = m.getDouble(mOffset + 88);
        return composeTRSMul_api_sc8f2783d_1(dest, destOffset, _translationx, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scalex, _scaley, _scalez, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _scalez + _scalez, _scalex + _scalex);
    }

    /** Piece 2 of {@code composeTRSMul_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSMul_api_sc8f2783d_1(java.nio.ByteBuffer dest, int destOffset, double _translationx, double _translationy, double _translationz, double _rotationx, double _rotationy, double _rotationz, double _rotationw, double _scalex, double _scaley, double _scalez, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t0, double _t1) {
        double _t2 = _scaley + _scaley;
        double _t3 = _rotationy * _rotationw;
        double _t4 = _rotationz * _rotationz;
        double _t5 = _rotationz * _rotationw;
        double _t24 = Math.fma(_rotationx, _rotationz, _t3) * _t0;
        double _t27 = Math.fma(_rotationx, _rotationy, -_t5) * _t2;
        double _t30 = Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex);
        dest.putDouble(destOffset, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        dest.putDouble(destOffset + 8, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        dest.putDouble(destOffset + 16, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        return composeTRSMul_api_sc8f2783d_2(dest, destOffset, _translationx, _translationy, _translationz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t24, Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, _t27, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(_rotationx, _rotationz, -_t3) * _t1, _t30, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez));
    }

    /** Piece 3 of {@code composeTRSMul_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSMul_api_sc8f2783d_2(java.nio.ByteBuffer dest, int destOffset, double _translationx, double _translationy, double _translationz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32) {
        dest.putDouble(destOffset + 24, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, _translationx))));
        dest.putDouble(destOffset + 32, Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31)));
        dest.putDouble(destOffset + 40, Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31)));
        dest.putDouble(destOffset + 48, Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31)));
        dest.putDouble(destOffset + 56, Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, _translationy))));
        dest.putDouble(destOffset + 64, Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26)));
        dest.putDouble(destOffset + 72, Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26)));
        dest.putDouble(destOffset + 80, Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26)));
        dest.putDouble(destOffset + 88, Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, _translationz))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
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
        return lookAlong_api_sf826c81c_1(dest, destOffset, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t18, _t19, Math.fma(_t17, _t11, upZ), Math.fma(_t18, _t13, -(_t19 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_sf826c81c_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20, double _t27) {
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
        dest.putDouble(destOffset, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putDouble(destOffset + 24, _self03);
        return lookAlong_api_sf826c81c_2(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_sf826c81c_2(java.nio.ByteBuffer dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x4OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
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
        return lookAlong_api_s2f25056e_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _upx, _upy, _upz, _t11, _t12, _t13);
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_s2f25056e_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13) {
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
        dest.putDouble(destOffset, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        return lookAlong_api_s2f25056e_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, Math.fma(_t39, _t12, -(_t38 * _t13)), Math.fma(_t37, _t13, -(_t39 * _t11)), Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_s2f25056e_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.lookAt_lh_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Double3x4OpsKernelsByteBuffer.lookAt_lh_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAt_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.lookAt_lh_unsafe(_destBase, _srcBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
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
        double _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        return lookAt_lh_api_scdace6f2_3(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, Math.fma(_t23, _t14, upX), Math.fma(_t23, _t16, upY), Math.fma(_t23, _t15, upZ));
    }

    /** Part 1 of {@code lookAt_lh_api}, split to fit the inline budget; reached only through it. */
    private static double lookAt_lh_api_scdace6f2_1(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double _self00, double _self01, double _self02, double _t14, double _t16, double _t43, double _t45, double _t54, double _t55, double _t56) {
        dest.putDouble(destOffset, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        return Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55));
    }

    /** Part 2 of {@code lookAt_lh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAt_lh_api_scdace6f2_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t15, double _t14, double _t16, double _t22, double _t44, double _t43, double _t45, double _t54, double _t55, double _t56, double _t58, double _t60) {
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        dest.putDouble(destOffset + 24, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        dest.putDouble(destOffset + 56, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        dest.putDouble(destOffset + 88, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    /** Piece 2 of {@code lookAt_lh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAt_lh_api_scdace6f2_3(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t24, double _t25, double _t26) {
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
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        return lookAt_lh_api_scdace6f2_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t15, _t14, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t44, _t43, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)), lookAt_lh_api_scdace6f2_1(dest, destOffset, eyeX, eyeY, eyeZ, _self00, _self01, _self02, _t14, _t16, _t43, _t45, _t54, _t55, _t56));
    }

    public static java.nio.ByteBuffer lookAt_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.lookAt_rh_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Double3x4OpsKernelsByteBuffer.lookAt_rh_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAt_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.lookAt_rh_unsafe(_destBase, _srcBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
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
        return lookAt_rh_api_s2749276c_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self02, -_self12, -_self22, _t17, _t18, _t19, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)), -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19)));
    }

    /** Piece 2 of {@code lookAt_rh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAt_rh_api_s2749276c_1(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t26) {
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
        return lookAt_rh_api_s2749276c_2(dest, destOffset, eyeX, eyeY, eyeZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, Math.fma(_t47, _t18, -(_t48 * _t19)), Math.fma(_t48, _t17, -(_t46 * _t18)), Math.fma(_t46, _t19, -(_t47 * _t17)), Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)));
    }

    /** Piece 3 of {@code lookAt_rh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAt_rh_api_s2749276c_2(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61) {
        double _t63 = Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58));
        dest.putDouble(destOffset, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        dest.putDouble(destOffset + 8, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        dest.putDouble(destOffset + 16, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        dest.putDouble(destOffset + 24, Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03))));
        dest.putDouble(destOffset + 32, Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57)));
        dest.putDouble(destOffset + 40, Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58)));
        dest.putDouble(destOffset + 48, Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59)));
        dest.putDouble(destOffset + 56, Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13))));
        return lookAt_rh_api_s2749276c_3(dest, destOffset, _self20, _self21, _self22, _self23, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, _t61, _t63);
    }

    /** Piece 4 of {@code lookAt_rh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAt_rh_api_s2749276c_3(java.nio.ByteBuffer dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t2, double _t17, double _t18, double _t19, double _t25, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        dest.putDouble(destOffset + 64, Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57)));
        dest.putDouble(destOffset + 72, Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58)));
        dest.putDouble(destOffset + 80, Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59)));
        dest.putDouble(destOffset + 88, Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.lookAt_lh_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Double3x4OpsKernelsByteBuffer.lookAt_lh_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAt_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x4OpsKernelsAddress.lookAt_lh_unsafe(_destBase, _srcBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _eyex = eye.getDouble(eyeOffset);
        double _eyey = eye.getDouble(eyeOffset + 8);
        double _eyez = eye.getDouble(eyeOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t0 = center.getDouble(centerOffset + 16) - _eyez;
        double _t1 = center.getDouble(centerOffset) - _eyex;
        double _t2 = center.getDouble(centerOffset + 8) - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        return lookAt_lh_api_s9d68f3d1_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t0, _t1, _t2, _t9, (1.0 / java.lang.Math.sqrt(_t9)));
    }

    /** Piece 2 of {@code lookAt_lh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAt_lh_api_s9d68f3d1_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t0, double _t1, double _t2, double _t9, double _t10) {
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
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
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
        return lookAt_lh_api_s9d68f3d1_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45);
    }

    /** Piece 3 of {@code lookAt_lh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAt_lh_api_s9d68f3d1_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45) {
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        double _t58 = Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45));
        double _t60 = Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55));
        dest.putDouble(destOffset, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        dest.putDouble(destOffset + 24, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        return lookAt_lh_api_s9d68f3d1_3(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, _t58, _t60);
    }

    /** Piece 4 of {@code lookAt_lh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAt_lh_api_s9d68f3d1_3(java.nio.ByteBuffer dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t58, double _t60) {
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        dest.putDouble(destOffset + 56, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        dest.putDouble(destOffset + 88, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.lookAt_rh_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Double3x4OpsKernelsByteBuffer.lookAt_rh_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAt_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x4OpsKernelsAddress.lookAt_rh_unsafe(_destBase, _srcBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _eyex = eye.getDouble(eyeOffset);
        double _eyey = eye.getDouble(eyeOffset + 8);
        double _eyez = eye.getDouble(eyeOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t3 = center.getDouble(centerOffset + 16) - _eyez;
        double _t4 = center.getDouble(centerOffset) - _eyex;
        double _t5 = center.getDouble(centerOffset + 8) - _eyey;
        return lookAt_rh_api_s3d185577_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _upx, _upy, _upz, -_self02, -_self12, -_self22, _t3, _t4, _t5, Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5)));
    }

    /** Piece 2 of {@code lookAt_rh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAt_rh_api_s3d185577_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t12) {
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
        double _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        double _t27 = Math.fma(_t26, _t19, _upy);
        double _t28 = Math.fma(_t26, _t17, _upx);
        double _t29 = Math.fma(_t26, _t18, _upz);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        return lookAt_rh_api_s3d185577_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t0, _t1, _t2, _t17, _t18, _t19, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)), _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 3 of {@code lookAt_rh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAt_rh_api_s3d185577_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t36, double _t37, double _t38, double _t41, double _t42) {
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
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        dest.putDouble(destOffset, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        dest.putDouble(destOffset + 8, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        dest.putDouble(destOffset + 16, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        return lookAt_rh_api_s3d185577_3(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
    }

    /** Piece 4 of {@code lookAt_rh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAt_rh_api_s3d185577_3(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        dest.putDouble(destOffset + 24, Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03))));
        dest.putDouble(destOffset + 32, Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57)));
        dest.putDouble(destOffset + 40, Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58)));
        dest.putDouble(destOffset + 48, Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59)));
        dest.putDouble(destOffset + 56, Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13))));
        dest.putDouble(destOffset + 64, Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57)));
        dest.putDouble(destOffset + 72, Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58)));
        dest.putDouble(destOffset + 80, Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59)));
        dest.putDouble(destOffset + 88, Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardCylindrical_unsafe(java.nio.ByteBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeBillboardCylindrical_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardCylindrical_api(java.nio.ByteBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
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
        return makeBillboardCylindrical_api_see96d7c2_1(dest, destOffset, objPosX, objPosY, objPosZ, upX, upY, upZ, _t36, _t37, _t38, Math.fma(upY, _t36, -(upX * _t37)), Math.fma(upX, _t38, -(upZ * _t36)), Math.fma(upZ, _t37, -(upY * _t38)));
    }

    /** Piece 2 of {@code makeBillboardCylindrical_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeBillboardCylindrical_api_see96d7c2_1(java.nio.ByteBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double upX, double upY, double upZ, double _t36, double _t37, double _t38, double _t45, double _t46, double _t47) {
        double _t50 = Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            dest.putDouble(destOffset + 16, _t47 * _t51);
            dest.putDouble(destOffset + 48, _t46 * _t51);
            dest.putDouble(destOffset + 80, _t45 * _t51);
        } else {
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 48, 0.0);
            dest.putDouble(destOffset + 80, 0.0);
        }
        dest.putDouble(destOffset, _t36);
        dest.putDouble(destOffset + 8, upX);
        dest.putDouble(destOffset + 24, objPosX);
        dest.putDouble(destOffset + 32, _t37);
        dest.putDouble(destOffset + 40, upY);
        dest.putDouble(destOffset + 56, objPosY);
        dest.putDouble(destOffset + 64, _t38);
        dest.putDouble(destOffset + 72, upZ);
        dest.putDouble(destOffset + 88, objPosZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardCylindrical_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + objPosOffset;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + targetPosOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x4OpsKernelsAddress.makeBillboardCylindrical_unsafe(_destBase, _objPosBase, _targetPosBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardCylindrical_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset, java.nio.ByteBuffer up, int upOffset) {
        double _objPosx = objPos.getDouble(objPosOffset);
        double _objPosy = objPos.getDouble(objPosOffset + 8);
        double _objPosz = objPos.getDouble(objPosOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t3 = targetPos.getDouble(targetPosOffset + 16) - _objPosz;
        double _t4 = targetPos.getDouble(targetPosOffset) - _objPosx;
        double _t5 = targetPos.getDouble(targetPosOffset + 8) - _objPosy;
        double _t14 = Math.fma(_upz, _t3, Math.fma(_upx, _t4, _upy * _t5));
        double _t15 = Math.fma(-_upy, _t14, _t5);
        double _t16 = Math.fma(-_upx, _t14, _t4);
        double _t17 = Math.fma(-_upz, _t14, _t3);
        double _t26 = Math.fma(_upx, _t15, -(_upy * _t16));
        double _t27 = Math.fma(_upy, _t17, -(_upz * _t15));
        double _t28 = Math.fma(_upz, _t16, -(_upx * _t17));
        double _t31 = Math.fma(_t26, _t26, Math.fma(_t27, _t27, _t28 * _t28));
        return makeBillboardCylindrical_api_s17ca5e8f_1(dest, destOffset, _objPosx, _objPosy, _objPosz, _upx, _upy, _upz, _t3, _t4, _t5, _t26, _t27, _t28, _t31, (1.0 / java.lang.Math.sqrt(_t31)));
    }

    /** Piece 2 of {@code makeBillboardCylindrical_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeBillboardCylindrical_api_s17ca5e8f_1(java.nio.ByteBuffer dest, int destOffset, double _objPosx, double _objPosy, double _objPosz, double _upx, double _upy, double _upz, double _t3, double _t4, double _t5, double _t26, double _t27, double _t28, double _t31, double _t32) {
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
            dest.putDouble(destOffset + 16, _t47 * _t51);
            dest.putDouble(destOffset + 48, _t46 * _t51);
            dest.putDouble(destOffset + 80, _t45 * _t51);
        } else {
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 48, 0.0);
            dest.putDouble(destOffset + 80, 0.0);
        }
        dest.putDouble(destOffset, _t36);
        dest.putDouble(destOffset + 8, _upx);
        dest.putDouble(destOffset + 24, _objPosx);
        dest.putDouble(destOffset + 32, _t37);
        dest.putDouble(destOffset + 40, _upy);
        return makeBillboardCylindrical_api_s17ca5e8f_2(dest, destOffset, _objPosy, _objPosz, _upz, _t38);
    }

    /** Piece 3 of {@code makeBillboardCylindrical_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeBillboardCylindrical_api_s17ca5e8f_2(java.nio.ByteBuffer dest, int destOffset, double _objPosy, double _objPosz, double _upz, double _t38) {
        dest.putDouble(destOffset + 56, _objPosy);
        dest.putDouble(destOffset + 64, _t38);
        dest.putDouble(destOffset + 72, _upz);
        dest.putDouble(destOffset + 88, _objPosz);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSpherical_unsafe(java.nio.ByteBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeBillboardSpherical_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSpherical_api(java.nio.ByteBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
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
        return makeBillboardSpherical_api_s8083b001_1(dest, destOffset, objPosX, objPosY, objPosZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeBillboardSpherical_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeBillboardSpherical_api_s8083b001_1(java.nio.ByteBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
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
        dest.putDouble(destOffset, _t40);
        dest.putDouble(destOffset + 8, Math.fma(_t41, _t16, -(_t42 * _t14)));
        dest.putDouble(destOffset + 16, _t15);
        dest.putDouble(destOffset + 24, objPosX);
        dest.putDouble(destOffset + 32, _t42);
        dest.putDouble(destOffset + 40, Math.fma(_t40, _t14, -(_t41 * _t15)));
        dest.putDouble(destOffset + 48, _t16);
        dest.putDouble(destOffset + 56, objPosY);
        dest.putDouble(destOffset + 64, _t41);
        dest.putDouble(destOffset + 72, Math.fma(_t42, _t15, -(_t40 * _t16)));
        dest.putDouble(destOffset + 80, _t14);
        dest.putDouble(destOffset + 88, objPosZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSpherical_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + objPosOffset;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + targetPosOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x4OpsKernelsAddress.makeBillboardSpherical_unsafe(_destBase, _objPosBase, _targetPosBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSpherical_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset, java.nio.ByteBuffer up, int upOffset) {
        double _objPosx = objPos.getDouble(objPosOffset);
        double _objPosy = objPos.getDouble(objPosOffset + 8);
        double _objPosz = objPos.getDouble(objPosOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t0 = targetPos.getDouble(targetPosOffset + 16) - _objPosz;
        double _t1 = targetPos.getDouble(targetPosOffset) - _objPosx;
        double _t2 = targetPos.getDouble(targetPosOffset + 8) - _objPosy;
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
        return makeBillboardSpherical_api_s16d79ebe_1(dest, destOffset, _objPosx, _objPosy, _objPosz, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t16, -(_t22 * _t15)), Math.fma(_t22, _t14, -(_t23 * _t16)), Math.fma(_t23, _t15, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeBillboardSpherical_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeBillboardSpherical_api_s16d79ebe_1(java.nio.ByteBuffer dest, int destOffset, double _objPosx, double _objPosy, double _objPosz, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
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
        dest.putDouble(destOffset, _t40);
        dest.putDouble(destOffset + 8, Math.fma(_t41, _t16, -(_t42 * _t14)));
        dest.putDouble(destOffset + 16, _t15);
        dest.putDouble(destOffset + 24, _objPosx);
        dest.putDouble(destOffset + 32, _t42);
        dest.putDouble(destOffset + 40, Math.fma(_t40, _t14, -(_t41 * _t15)));
        dest.putDouble(destOffset + 48, _t16);
        dest.putDouble(destOffset + 56, _objPosy);
        dest.putDouble(destOffset + 64, _t41);
        dest.putDouble(destOffset + 72, Math.fma(_t42, _t15, -(_t40 * _t16)));
        dest.putDouble(destOffset + 80, _t14);
        dest.putDouble(destOffset + 88, _objPosz);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSphericalShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeBillboardSphericalShortest_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSphericalShortest_api(java.nio.ByteBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ) {
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
        dest.putDouble(destOffset, _t32);
        dest.putDouble(destOffset + 8, _t29);
        dest.putDouble(destOffset + 16, _t30);
        dest.putDouble(destOffset + 24, objPosX);
        dest.putDouble(destOffset + 32, _t29);
        dest.putDouble(destOffset + 40, 1.0 - _t26);
        dest.putDouble(destOffset + 48, _t27);
        dest.putDouble(destOffset + 56, objPosY);
        return makeBillboardSphericalShortest_api_sc83a090_1(dest, destOffset, objPosZ, _t26, _t27, _t30, _t32);
    }

    /** Piece 2 of {@code makeBillboardSphericalShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeBillboardSphericalShortest_api_sc83a090_1(java.nio.ByteBuffer dest, int destOffset, double objPosZ, double _t26, double _t27, double _t30, double _t32) {
        dest.putDouble(destOffset + 64, -_t30);
        dest.putDouble(destOffset + 72, -_t27);
        dest.putDouble(destOffset + 80, _t32 - _t26);
        dest.putDouble(destOffset + 88, objPosZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSphericalShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + objPosOffset;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + targetPosOffset;
        Double3x4OpsKernelsAddress.makeBillboardSphericalShortest_unsafe(_destBase, _objPosBase, _targetPosBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSphericalShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset) {
        double _objPosx = objPos.getDouble(objPosOffset);
        double _objPosy = objPos.getDouble(objPosOffset + 8);
        double _objPosz = objPos.getDouble(objPosOffset + 16);
        double _t0 = targetPos.getDouble(targetPosOffset + 16) - _objPosz;
        double _t1 = targetPos.getDouble(targetPosOffset) - _objPosx;
        double _t2 = targetPos.getDouble(targetPosOffset + 8) - _objPosy;
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
        dest.putDouble(destOffset, _t32);
        dest.putDouble(destOffset + 8, _t29);
        dest.putDouble(destOffset + 16, _t30);
        dest.putDouble(destOffset + 24, _objPosx);
        dest.putDouble(destOffset + 32, _t29);
        return makeBillboardSphericalShortest_api_s58aa23f2_1(dest, destOffset, _objPosy, _objPosz, _sp1 * _t3, _sp1 * _t14, _t30, _t32);
    }

    /** Piece 2 of {@code makeBillboardSphericalShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeBillboardSphericalShortest_api_s58aa23f2_1(java.nio.ByteBuffer dest, int destOffset, double _objPosy, double _objPosz, double _t26, double _t27, double _t30, double _t32) {
        dest.putDouble(destOffset + 40, 1.0 - _t26);
        dest.putDouble(destOffset + 48, _t27);
        dest.putDouble(destOffset + 56, _objPosy);
        dest.putDouble(destOffset + 64, -_t30);
        dest.putDouble(destOffset + 72, -_t27);
        dest.putDouble(destOffset + 80, _t32 - _t26);
        dest.putDouble(destOffset + 88, _objPosz);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_api(java.nio.ByteBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = Math.fma(-2.0, dqRZ * dqRZ, 1.0);
        dest.putDouble(destOffset, Math.fma(-2.0, _t0, _t6));
        dest.putDouble(destOffset + 8, Math.fma(-2.0, _t2, _sp0 * dqRY));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(dqRX, dqRZ, _t3));
        dest.putDouble(destOffset + 24, 2.0 * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))));
        dest.putDouble(destOffset + 32, 2.0 * Math.fma(dqRX, dqRY, _t2));
        dest.putDouble(destOffset + 40, Math.fma(-2.0, _t4, _t6));
        dest.putDouble(destOffset + 48, Math.fma(-2.0, dqRX * dqRW, _t5 + _t5));
        dest.putDouble(destOffset + 56, 2.0 * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))));
        dest.putDouble(destOffset + 64, Math.fma(-2.0, _t3, _sp0 * dqRZ));
        return makeFromDualQuat_api_s8c720189_1(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW, _t0, _t4, _t5);
    }

    /** Piece 2 of {@code makeFromDualQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeFromDualQuat_api_s8c720189_1(java.nio.ByteBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW, double _t0, double _t4, double _t5) {
        dest.putDouble(destOffset + 72, 2.0 * Math.fma(dqRX, dqRW, _t5));
        dest.putDouble(destOffset + 80, Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0)));
        dest.putDouble(destOffset + 88, 2.0 * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))));
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_lh(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.makeLookAt_lh_unsafe(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Double3x4OpsKernelsByteBuffer.makeLookAt_lh_api(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeLookAt_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeLookAt_lh_unsafe(_destBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_lh_api(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
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
        return makeLookAt_lh_api_s7aa9cac4_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_lh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeLookAt_lh_api_s7aa9cac4_1(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
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
        dest.putDouble(destOffset, _t40);
        dest.putDouble(destOffset + 8, _t41);
        dest.putDouble(destOffset + 16, _t42);
        dest.putDouble(destOffset + 24, -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41)));
        dest.putDouble(destOffset + 32, _t49);
        dest.putDouble(destOffset + 40, _t50);
        dest.putDouble(destOffset + 48, _t51);
        dest.putDouble(destOffset + 56, -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50)));
        dest.putDouble(destOffset + 64, _t15);
        dest.putDouble(destOffset + 72, _t16);
        dest.putDouble(destOffset + 80, _t14);
        dest.putDouble(destOffset + 88, -Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_rh(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.makeLookAt_rh_unsafe(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Double3x4OpsKernelsByteBuffer.makeLookAt_rh_api(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeLookAt_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeLookAt_rh_unsafe(_destBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_rh_api(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
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
        return makeLookAt_rh_api_sc883478a_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_rh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeLookAt_rh_api_sc883478a_1(java.nio.ByteBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
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
        dest.putDouble(destOffset, _t40);
        dest.putDouble(destOffset + 8, _t41);
        dest.putDouble(destOffset + 16, _t42);
        dest.putDouble(destOffset + 24, -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41)));
        dest.putDouble(destOffset + 32, _t49);
        dest.putDouble(destOffset + 40, _t50);
        dest.putDouble(destOffset + 48, _t51);
        dest.putDouble(destOffset + 56, -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50)));
        dest.putDouble(destOffset + 64, -_t15);
        dest.putDouble(destOffset + 72, -_t16);
        dest.putDouble(destOffset + 80, -_t14);
        dest.putDouble(destOffset + 88, Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.makeLookAt_lh_unsafe(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Double3x4OpsKernelsByteBuffer.makeLookAt_lh_api(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeLookAt_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x4OpsKernelsAddress.makeLookAt_lh_unsafe(_destBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        double _eyex = eye.getDouble(eyeOffset);
        double _eyey = eye.getDouble(eyeOffset + 8);
        double _eyez = eye.getDouble(eyeOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t0 = center.getDouble(centerOffset + 16) - _eyez;
        double _t1 = center.getDouble(centerOffset) - _eyex;
        double _t2 = center.getDouble(centerOffset + 8) - _eyey;
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
        return makeLookAt_lh_api_s989fd73f_1(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t16, -(_t22 * _t15)), Math.fma(_t22, _t14, -(_t23 * _t16)), Math.fma(_t23, _t15, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_lh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeLookAt_lh_api_s989fd73f_1(java.nio.ByteBuffer dest, int destOffset, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
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
        dest.putDouble(destOffset, _t40);
        dest.putDouble(destOffset + 8, _t41);
        dest.putDouble(destOffset + 16, _t42);
        dest.putDouble(destOffset + 24, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        dest.putDouble(destOffset + 32, _t49);
        dest.putDouble(destOffset + 40, _t50);
        dest.putDouble(destOffset + 48, _t51);
        dest.putDouble(destOffset + 56, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        dest.putDouble(destOffset + 64, _t15);
        return makeLookAt_lh_api_s989fd73f_2(dest, destOffset, _eyex, _eyey, _eyez, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_lh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeLookAt_lh_api_s989fd73f_2(java.nio.ByteBuffer dest, int destOffset, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16) {
        dest.putDouble(destOffset + 72, _t16);
        dest.putDouble(destOffset + 80, _t14);
        dest.putDouble(destOffset + 88, -Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsByteBuffer.makeLookAt_rh_unsafe(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Double3x4OpsKernelsByteBuffer.makeLookAt_rh_api(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeLookAt_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x4OpsKernelsAddress.makeLookAt_rh_unsafe(_destBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        double _eyex = eye.getDouble(eyeOffset);
        double _eyey = eye.getDouble(eyeOffset + 8);
        double _eyez = eye.getDouble(eyeOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t0 = center.getDouble(centerOffset + 16) - _eyez;
        double _t1 = center.getDouble(centerOffset) - _eyex;
        double _t2 = center.getDouble(centerOffset + 8) - _eyey;
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
        return makeLookAt_rh_api_s102b3c79_1(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t15, -(_t22 * _t16)), Math.fma(_t22, _t14, -(_t23 * _t15)), Math.fma(_t23, _t16, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_rh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeLookAt_rh_api_s102b3c79_1(java.nio.ByteBuffer dest, int destOffset, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
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
        dest.putDouble(destOffset, _t40);
        dest.putDouble(destOffset + 8, _t41);
        dest.putDouble(destOffset + 16, _t42);
        dest.putDouble(destOffset + 24, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        dest.putDouble(destOffset + 32, _t49);
        dest.putDouble(destOffset + 40, _t50);
        dest.putDouble(destOffset + 48, _t51);
        dest.putDouble(destOffset + 56, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        dest.putDouble(destOffset + 64, -_t15);
        return makeLookAt_rh_api_s102b3c79_2(dest, destOffset, _eyex, _eyey, _eyez, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_rh_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeLookAt_rh_api_s102b3c79_2(java.nio.ByteBuffer dest, int destOffset, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16) {
        dest.putDouble(destOffset + 72, -_t16);
        dest.putDouble(destOffset + 80, -_t14);
        dest.putDouble(destOffset + 88, Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingXYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXYZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingXYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXYnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, -1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXZY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingXZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXZY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXZnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingXZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXZnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, -1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnYZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingXnYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnYZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, -1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingXnYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnYnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, -1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, -1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnZY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingXnZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnZY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, -1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnZnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingXnZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnZnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, -1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, -1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingYXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYXZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingYXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYXnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, -1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYZX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingYZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYZX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYZnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingYZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYZnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, -1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnXZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingYnXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnXZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, -1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingYnXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnXnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, -1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, -1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnZX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingYnZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnZX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, -1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnZnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingYnZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnZnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, -1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, -1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZXY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingZXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZXY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZXnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingZXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZXnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, -1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZYX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingZYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZYX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZYnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingZYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZYnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, -1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnXY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingZnXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnXY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, -1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnXnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingZnXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnXnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, -1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, -1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnYX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingZnYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnYX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, -1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnYnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingZnYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnYnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, -1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, -1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnXYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXYZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, -1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnXYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXYnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, -1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, -1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXZY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnXZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXZY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, -1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXZnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnXZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXZnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, -1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, -1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnYZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnXnYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnYZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, -1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, -1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnXnYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnYnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, -1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, -1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, -1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnZY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnXnZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnZY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, -1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, -1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnZnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnXnZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnZnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, -1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, -1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, -1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnYXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYXZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, -1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnYXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYXnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, -1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, -1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYZX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnYZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYZX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, -1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYZnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnYZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYZnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, -1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, -1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnXZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnYnXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnXZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, -1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, -1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnYnXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnXnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, -1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, -1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, -1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnZX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnYnZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnZX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, -1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, -1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnZnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnYnZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnZnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, -1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, -1.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, -1.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZXY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnZXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZXY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZXnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnZXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZXnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, -1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZYX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnZYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZYX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZYnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnZYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZYnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, -1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnXY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnZnXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnXY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, -1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnXnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnZnXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnXnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, -1.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, -1.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnYX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnZnYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnYX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, -1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnYnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeMappingnZnYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnYnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, -1.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, -1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -1.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 0.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeReflection_unsafe(java.nio.ByteBuffer dest, int destOffset, double normalX, double normalY, double normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeReflection_unsafe(_destBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeReflection_api(java.nio.ByteBuffer dest, int destOffset, double normalX, double normalY, double normalZ) {
        double _sp0 = normalX + normalX;
        double _t6 = -(_sp0 * normalY);
        double _t7 = -(_sp0 * normalZ);
        double _t8 = -((normalY + normalY) * normalZ);
        dest.putDouble(destOffset, Math.fma(-2.0, normalX * normalX, 1.0));
        dest.putDouble(destOffset + 8, _t6);
        dest.putDouble(destOffset + 16, _t7);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t6);
        dest.putDouble(destOffset + 40, Math.fma(-2.0, normalY * normalY, 1.0));
        dest.putDouble(destOffset + 48, _t8);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, _t7);
        dest.putDouble(destOffset + 72, _t8);
        dest.putDouble(destOffset + 80, Math.fma(-2.0, normalZ * normalZ, 1.0));
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeReflection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Double3x4OpsKernelsAddress.makeReflection_unsafe(_destBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeReflection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer normal, int normalOffset) {
        double _normalx = normal.getDouble(normalOffset);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _normalz = normal.getDouble(normalOffset + 16);
        double _sp0 = _normalx + _normalx;
        double _t6 = -(_sp0 * _normaly);
        double _t7 = -(_sp0 * _normalz);
        double _t8 = -((_normaly + _normaly) * _normalz);
        dest.putDouble(destOffset, Math.fma(-2.0, _normalx * _normalx, 1.0));
        dest.putDouble(destOffset + 8, _t6);
        dest.putDouble(destOffset + 16, _t7);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t6);
        dest.putDouble(destOffset + 40, Math.fma(-2.0, _normaly * _normaly, 1.0));
        dest.putDouble(destOffset + 48, _t8);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, _t7);
        dest.putDouble(destOffset + 72, _t8);
        dest.putDouble(destOffset + 80, Math.fma(-2.0, _normalz * _normalz, 1.0));
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
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
        dest.putDouble(destOffset + 8, Math.fma(_t5, _t2, -(axisZ * _t0)));
        dest.putDouble(destOffset + 16, Math.fma(axisY, _t0, _t5 * _t3));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, Math.fma(axisZ, _t0, _t5 * _t2));
        dest.putDouble(destOffset + 40, Math.fma(_t5, axisY * axisY, _t1));
        dest.putDouble(destOffset + 48, Math.fma(_t5, _t4, -(axisX * _t0)));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, Math.fma(_t5, _t3, -(axisY * _t0)));
        dest.putDouble(destOffset + 72, Math.fma(axisX, _t0, _t5 * _t4));
        dest.putDouble(destOffset + 80, Math.fma(_t5, axisZ * axisZ, _t1));
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Double3x4OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
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
        dest.putDouble(destOffset + 8, Math.fma(_t5, _t2, -(_axisz * _t0)));
        dest.putDouble(destOffset + 16, Math.fma(_axisy, _t0, _t5 * _t3));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, Math.fma(_axisz, _t0, _t5 * _t2));
        dest.putDouble(destOffset + 40, Math.fma(_t5, _axisy * _axisy, _t1));
        dest.putDouble(destOffset + 48, Math.fma(_t5, _t4, -(_axisx * _t0)));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, Math.fma(_t5, _t3, -(_axisy * _t0)));
        dest.putDouble(destOffset + 72, Math.fma(_axisx, _t0, _t5 * _t4));
        dest.putDouble(destOffset + 80, Math.fma(_t5, _axisz * _axisz, _t1));
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
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
        return makeRotationLookAlong_api_scb8583d0_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_scb8583d0_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.putDouble(destOffset + 8, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putDouble(destOffset + 16, _t12);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t39);
        dest.putDouble(destOffset + 40, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putDouble(destOffset + 48, _t13);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, _t38);
        dest.putDouble(destOffset + 72, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 80, _t11);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Double3x4OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
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
        dest.putDouble(destOffset + 8, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putDouble(destOffset + 16, _t12);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t39);
        dest.putDouble(destOffset + 40, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putDouble(destOffset + 48, _t13);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, _t38);
        dest.putDouble(destOffset + 72, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putDouble(destOffset + 80, _t11);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_api(java.nio.ByteBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        dest.putDouble(destOffset, Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(qX, qY, -_t1));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(qX, qZ, _t2));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 2.0 * Math.fma(qX, qY, _t1));
        dest.putDouble(destOffset + 40, Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0));
        dest.putDouble(destOffset + 48, 2.0 * Math.fma(qY, qZ, -(qX * qW)));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 2.0 * Math.fma(qX, qZ, -_t2));
        dest.putDouble(destOffset + 72, 2.0 * Math.fma(qX, qW, qY * qZ));
        dest.putDouble(destOffset + 80, Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0));
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset;
        Double3x4OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, _qBase);
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
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(_qx, _qy, -_t1));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(_qx, _qz, _t2));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 2.0 * Math.fma(_qx, _qy, _t1));
        dest.putDouble(destOffset + 40, Math.fma(-2.0, Math.fma(_qx, _qx, _t0), 1.0));
        dest.putDouble(destOffset + 48, 2.0 * Math.fma(_qy, _qz, -(_qx * _qw)));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 2.0 * Math.fma(_qx, _qz, -_t2));
        dest.putDouble(destOffset + 72, 2.0 * Math.fma(_qx, _qw, _qy * _qz));
        dest.putDouble(destOffset + 80, Math.fma(-2.0, Math.fma(_qx, _qx, _qy * _qy), 1.0));
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, _t1);
        dest.putDouble(destOffset + 48, -_t0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, _t0);
        dest.putDouble(destOffset + 80, _t1);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
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
        dest.putDouble(destOffset + 8, -(_t1 * _t3));
        dest.putDouble(destOffset + 16, _t0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, Math.fma(_t6, _t4, _t1 * _t5));
        dest.putDouble(destOffset + 40, Math.fma(_t5, _t4, -(_t6 * _t1)));
        dest.putDouble(destOffset + 48, -(_t2 * _t3));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, Math.fma(_t2, _t1, -(_t7 * _t4)));
        dest.putDouble(destOffset + 72, Math.fma(_t7, _t1, _t2 * _t4));
        dest.putDouble(destOffset + 80, _t5 * _t3);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
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
        dest.putDouble(destOffset + 8, -_t1);
        dest.putDouble(destOffset + 16, _t0 * _t4);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, Math.fma(_t7, _t3, _t2 * _t0));
        dest.putDouble(destOffset + 40, _t5 * _t4);
        dest.putDouble(destOffset + 48, Math.fma(_t7, _t0, -(_t2 * _t3)));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, Math.fma(_t6, _t3, -(_t0 * _t5)));
        dest.putDouble(destOffset + 72, _t2 * _t4);
        dest.putDouble(destOffset + 80, Math.fma(_t6, _t0, _t5 * _t3));
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, _t1);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, _t0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -_t0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, _t1);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
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
        dest.putDouble(destOffset + 8, Math.fma(_t6, _t4, -(_t2 * _t3)));
        dest.putDouble(destOffset + 16, _t1 * _t5);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t2 * _t5);
        dest.putDouble(destOffset + 40, _t5 * _t4);
        dest.putDouble(destOffset + 48, -_t0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, Math.fma(_t7, _t2, -(_t1 * _t4)));
        dest.putDouble(destOffset + 72, Math.fma(_t7, _t4, _t1 * _t2));
        dest.putDouble(destOffset + 80, _t5 * _t3);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
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
        dest.putDouble(destOffset + 8, Math.fma(_t2, _t0, -(_t7 * _t5)));
        dest.putDouble(destOffset + 16, Math.fma(_t7, _t2, _t0 * _t5));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t1);
        dest.putDouble(destOffset + 40, _t5 * _t4);
        dest.putDouble(destOffset + 48, -(_t2 * _t4));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -(_t0 * _t4));
        dest.putDouble(destOffset + 72, Math.fma(_t6, _t5, _t2 * _t3));
        dest.putDouble(destOffset + 80, Math.fma(_t5, _t3, -(_t6 * _t2)));
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, _t1);
        dest.putDouble(destOffset + 8, -_t0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t0);
        dest.putDouble(destOffset + 40, _t1);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
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
        dest.putDouble(destOffset + 8, -(_t1 * _t5));
        dest.putDouble(destOffset + 16, Math.fma(_t6, _t3, _t0 * _t4));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, Math.fma(_t7, _t0, _t1 * _t3));
        dest.putDouble(destOffset + 40, _t5 * _t4);
        dest.putDouble(destOffset + 48, Math.fma(_t0, _t1, -(_t7 * _t3)));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -(_t0 * _t5));
        dest.putDouble(destOffset + 72, _t2);
        dest.putDouble(destOffset + 80, _t5 * _t3);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
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
        dest.putDouble(destOffset + 8, Math.fma(_t7, _t2, -(_t1 * _t5)));
        dest.putDouble(destOffset + 16, Math.fma(_t7, _t5, _t2 * _t1));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, _t1 * _t3);
        dest.putDouble(destOffset + 40, Math.fma(_t6, _t2, _t5 * _t4));
        dest.putDouble(destOffset + 48, Math.fma(_t6, _t5, -(_t2 * _t4)));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, -_t0);
        dest.putDouble(destOffset + 72, _t2 * _t3);
        dest.putDouble(destOffset + 80, _t5 * _t3);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY, double vZ) {
        dest.putDouble(destOffset, vX);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, vY);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, vZ);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        dest.putDouble(destOffset, _vx);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, _vy);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, _vz);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, double s) {
        dest.putDouble(destOffset, s);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, s);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, s);
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY, double vZ) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, vX);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, vY);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x4OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _vx);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 1.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, _vy);
        dest.putDouble(destOffset + 64, 0.0);
        dest.putDouble(destOffset + 72, 0.0);
        dest.putDouble(destOffset + 80, 1.0);
        dest.putDouble(destOffset + 88, _vz);
        return dest;
    }

    public static java.nio.ByteBuffer mapXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapXYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXYnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXZnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapXZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXZnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXnYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapXnYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXnYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXnYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapXnYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXnYnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXnZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapXnZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXnZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXnZnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapXnZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXnZnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapYXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYXnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYZnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapYZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYZnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYnXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapYnXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYnXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYnXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapYnXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYnXnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYnZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapYnZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYnZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYnZnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapYnZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYnZnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZXnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapZXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZXnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZYnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapZYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZYnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZnXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapZnXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZnXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZnXnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapZnXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZnXnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZnYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapZnYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZnYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZnYnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapZnYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZnYnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnXYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXYnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXZnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnXZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXZnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnXnYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnXnYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnYnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnXnZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnZnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnXnZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnZnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnYXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYXnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYZnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnYZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYZnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnYnXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnYnXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnXnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnYnZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnZnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnYnZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnZnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZXnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnZXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZXnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZYnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnZYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZYnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnZnXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnXnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnZnXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnXnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnZnYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnYnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mapnZnYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnYnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself2);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t0 = -rotY;
        double _t4 = rotX + rotX;
        double _t5 = rotY + rotY;
        double _t6 = rotZ + rotZ;
        double _t7 = rotW * _t5;
        double _t8 = rotW * _t6;
        double _t10 = rotW * _t4;
        double _t14 = Math.fma(-rotZ, _t6, 1.0);
        return preRotateAround_api_s95a55b07_1(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, -pivotZ, -rotX, _t4, _t5, rotZ * _t6, _t14, Math.fma(rotZ, _t4, _t7), Math.fma(rotY, _t4, _t8), Math.fma(rotZ, _t5, _t10), Math.fma(rotY, _t4, -_t8), Math.fma(rotZ, _t5, -_t10), Math.fma(rotZ, _t4, -_t7), Math.fma(_t0, _t5, _t14));
    }

    /** Piece 2 of {@code preRotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_s95a55b07_1(java.nio.ByteBuffer dest, int destOffset, double rotX, double rotY, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t2, double _t3, double _t4, double _t5, double _t9, double _t14, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        double _t23 = Math.fma(_t3, _t4, _t14);
        dest.putDouble(destOffset, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putDouble(destOffset + 8, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putDouble(destOffset + 24, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))));
        dest.putDouble(destOffset + 32, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        dest.putDouble(destOffset + 48, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        return preRotateAround_api_s95a55b07_2(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t2, _t4, _t5, _t9, _t17, _t18, _t20, _t21, _t23, Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)));
    }

    /** Piece 3 of {@code preRotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_s95a55b07_2(java.nio.ByteBuffer dest, int destOffset, double rotX, double rotY, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t2, double _t4, double _t5, double _t9, double _t17, double _t18, double _t20, double _t21, double _t23, double _t24) {
        dest.putDouble(destOffset + 56, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        dest.putDouble(destOffset + 72, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        dest.putDouble(destOffset + 88, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer rot, int rotOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rotBase = UnsafeOpsHolder.U.getLong(rot, UnsafeCopy.BB_ADDRESS_OFFSET) + rotOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x4OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _rotBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer rot, int rotOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _rotx = rot.getDouble(rotOffset);
        double _roty = rot.getDouble(rotOffset + 8);
        double _rotz = rot.getDouble(rotOffset + 16);
        double _rotw = rot.getDouble(rotOffset + 24);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _pivotz = pivot.getDouble(pivotOffset + 16);
        double _t4 = _rotx + _rotx;
        double _t5 = _roty + _roty;
        double _t6 = _rotz + _rotz;
        return preRotateAround_api_sbaf145cd_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _rotz, _pivotx, _pivoty, _pivotz, -_roty, -_pivotz, -_rotx, _t4, _t5, _rotw * _t5, _rotw * _t6, _rotz * _t6, _rotw * _t4, Math.fma(-_rotz, _t6, 1.0));
    }

    /** Piece 2 of {@code preRotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_sbaf145cd_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _rotz, double _pivotx, double _pivoty, double _pivotz, double _t0, double _t2, double _t3, double _t4, double _t5, double _t7, double _t8, double _t9, double _t10, double _t14) {
        double _t16 = Math.fma(_rotz, _t4, _t7);
        double _t19 = Math.fma(_roty, _t4, -_t8);
        double _t22 = Math.fma(_t0, _t5, _t14);
        dest.putDouble(destOffset, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putDouble(destOffset + 8, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putDouble(destOffset + 24, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))));
        return preRotateAround_api_sbaf145cd_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _pivotx, _pivoty, _pivotz, _t2, _t4, _t5, _t9, Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7), Math.fma(_t3, _t4, _t14), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)));
    }

    /** Piece 3 of {@code preRotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_sbaf145cd_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _pivotx, double _pivoty, double _pivotz, double _t2, double _t4, double _t5, double _t9, double _t17, double _t18, double _t20, double _t21, double _t23, double _t24) {
        dest.putDouble(destOffset + 32, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        dest.putDouble(destOffset + 48, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        dest.putDouble(destOffset + 56, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        dest.putDouble(destOffset + 72, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        dest.putDouble(destOffset + 88, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        return preRotateAxis_api_sa6f451c6_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, _t4, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(_t11, _t2, -(axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_sa6f451c6_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        dest.putDouble(destOffset + 8, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.putDouble(destOffset + 24, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        dest.putDouble(destOffset + 32, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putDouble(destOffset + 48, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putDouble(destOffset + 56, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.putDouble(destOffset + 72, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        return preRotateAxis_api_sa6f451c6_2(dest, destOffset, _self02, _self03, _self12, _self13, _self22, _self23, _t20, _t23, _t26);
    }

    /** Piece 3 of {@code preRotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_sa6f451c6_2(java.nio.ByteBuffer dest, int destOffset, double _self02, double _self03, double _self12, double _self13, double _self22, double _self23, double _t20, double _t23, double _t26) {
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        dest.putDouble(destOffset + 88, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Double3x4OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t4 = _axisx * _axisy;
        double _t11 = 1.0 - _t1;
        return preRotateAxis_api_sb49cc6e3_1(dest, destOffset, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _axisx, _axisy, _axisz, _t2, _t4, _axisy * _axisz, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_axisz, _t0, _t11 * _t4));
    }

    /** Piece 2 of {@code preRotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_sb49cc6e3_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _axisx, double _axisy, double _axisz, double _t2, double _t4, double _t6, double _t11, double _t18, double _t19, double _t20, double _t21, double _t22) {
        double _t24 = Math.fma(_t11, _t4, -(_axisz * _t0));
        double _t25 = Math.fma(_t11, _t6, -(_axisx * _t0));
        dest.putDouble(destOffset, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        dest.putDouble(destOffset + 8, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.putDouble(destOffset + 24, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        dest.putDouble(destOffset + 32, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putDouble(destOffset + 48, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putDouble(destOffset + 56, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        return preRotateAxis_api_sb49cc6e3_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t20, Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_t11, _t2, -(_axisy * _t0)));
    }

    /** Piece 3 of {@code preRotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateAxis_api_sb49cc6e3_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t20, double _t23, double _t26) {
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.putDouble(destOffset + 72, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        dest.putDouble(destOffset + 88, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.preRotateQuat_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t0 = -qY;
        double _t2 = -qX;
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        double _t12 = Math.fma(-qZ, _t5, 1.0);
        return preRotateQuat_api_sc3e13301_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, Math.fma(qZ, _t3, _t6), Math.fma(qY, _t3, _t7), Math.fma(qZ, _t4, _t8), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8), Math.fma(qZ, _t3, -_t6), Math.fma(_t0, _t4, _t12), Math.fma(_t2, _t3, _t12), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 2 of {@code preRotateQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateQuat_api_sc3e13301_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        dest.putDouble(destOffset + 8, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        dest.putDouble(destOffset + 24, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        dest.putDouble(destOffset + 32, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        dest.putDouble(destOffset + 48, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        dest.putDouble(destOffset + 56, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        dest.putDouble(destOffset + 72, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        return preRotateQuat_api_sc3e13301_2(dest, destOffset, _self02, _self03, _self12, _self13, _self22, _self23, _t16, _t19, _t22);
    }

    /** Piece 3 of {@code preRotateQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateQuat_api_sc3e13301_2(java.nio.ByteBuffer dest, int destOffset, double _self02, double _self03, double _self12, double _self13, double _self22, double _self23, double _t16, double _t19, double _t22) {
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        dest.putDouble(destOffset + 88, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset;
        Double3x4OpsKernelsAddress.preRotateQuat_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _qx = q.getDouble(qOffset);
        double _qy = q.getDouble(qOffset + 8);
        double _qz = q.getDouble(qOffset + 16);
        double _qw = q.getDouble(qOffset + 24);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        double _t6 = _qw * _t4;
        double _t7 = _qw * _t5;
        double _t8 = _qw * _t3;
        return preRotateQuat_api_sc79e817b_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _qz, -_qy, -_qx, _t3, _t4, _t6, Math.fma(-_qz, _t5, 1.0), Math.fma(_qz, _t3, _t6), Math.fma(_qy, _t3, _t7), Math.fma(_qz, _t4, _t8), Math.fma(_qy, _t3, -_t7), Math.fma(_qz, _t4, -_t8));
    }

    /** Piece 2 of {@code preRotateQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateQuat_api_sc79e817b_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _qz, double _t0, double _t2, double _t3, double _t4, double _t6, double _t12, double _t14, double _t15, double _t16, double _t17, double _t18) {
        double _t20 = Math.fma(_t0, _t4, _t12);
        double _t21 = Math.fma(_t2, _t3, _t12);
        dest.putDouble(destOffset, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        dest.putDouble(destOffset + 8, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        dest.putDouble(destOffset + 24, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        dest.putDouble(destOffset + 32, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        dest.putDouble(destOffset + 40, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        dest.putDouble(destOffset + 48, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        dest.putDouble(destOffset + 56, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        return preRotateQuat_api_sc79e817b_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t16, Math.fma(_qz, _t3, -_t6), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 3 of {@code preRotateQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateQuat_api_sc79e817b_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t16, double _t19, double _t22) {
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        dest.putDouble(destOffset + 72, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        dest.putDouble(destOffset + 88, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self02);
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_self10, _t1, -(_self20 * _t0)));
        dest.putDouble(destOffset + 40, Math.fma(_self11, _t1, -(_self21 * _t0)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t1, -(_self22 * _t0)));
        dest.putDouble(destOffset + 56, Math.fma(_self13, _t1, -(_self23 * _t0)));
        dest.putDouble(destOffset + 64, Math.fma(_self10, _t0, _self20 * _t1));
        return preRotateX_api_s5c4ddfe8_1(dest, destOffset, _t0, _self11, _self12, _self13, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code preRotateX_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateX_api_s5c4ddfe8_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self11, double _self12, double _self13, double _self21, double _self22, double _self23, double _t1) {
        dest.putDouble(destOffset + 72, Math.fma(_self11, _t0, _self21 * _t1));
        dest.putDouble(destOffset + 80, Math.fma(_self12, _t0, _self22 * _t1));
        dest.putDouble(destOffset + 88, Math.fma(_self13, _t0, _self23 * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, Math.fma(_self00, _t1, _self20 * _t0));
        dest.putDouble(destOffset + 8, Math.fma(_self01, _t1, _self21 * _t0));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t1, _self22 * _t0));
        dest.putDouble(destOffset + 24, Math.fma(_self03, _t1, _self23 * _t0));
        dest.putDouble(destOffset + 32, _self10);
        dest.putDouble(destOffset + 40, _self11);
        dest.putDouble(destOffset + 48, _self12);
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t1, -(_self00 * _t0)));
        return preRotateY_api_sc5787135_1(dest, destOffset, _t0, _self01, _self02, _self03, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code preRotateY_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateY_api_sc5787135_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self01, double _self02, double _self03, double _self21, double _self22, double _self23, double _t1) {
        dest.putDouble(destOffset + 72, Math.fma(_self21, _t1, -(_self01 * _t0)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t1, -(_self02 * _t0)));
        dest.putDouble(destOffset + 88, Math.fma(_self23, _t1, -(_self03 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t1, -(_self12 * _t0)));
        dest.putDouble(destOffset + 24, Math.fma(_self03, _t1, -(_self13 * _t0)));
        dest.putDouble(destOffset + 32, Math.fma(_self00, _t0, _self10 * _t1));
        dest.putDouble(destOffset + 40, Math.fma(_self01, _t0, _self11 * _t1));
        dest.putDouble(destOffset + 48, Math.fma(_self02, _t0, _self12 * _t1));
        return preRotateZ_api_scb0a5bc6_1(dest, destOffset, _t0, _self03, _self13, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code preRotateZ_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preRotateZ_api_scb0a5bc6_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self03, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1) {
        dest.putDouble(destOffset + 56, Math.fma(_self03, _t0, _self13 * _t1));
        dest.putDouble(destOffset + 64, _self20);
        dest.putDouble(destOffset + 72, _self21);
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        dest.putDouble(destOffset, _self00 * vX);
        dest.putDouble(destOffset + 8, _self01 * vX);
        dest.putDouble(destOffset + 16, _self02 * vX);
        dest.putDouble(destOffset + 24, _self03 * vX);
        dest.putDouble(destOffset + 32, _self10 * vY);
        dest.putDouble(destOffset + 40, _self11 * vY);
        dest.putDouble(destOffset + 48, _self12 * vY);
        dest.putDouble(destOffset + 56, _self13 * vY);
        dest.putDouble(destOffset + 64, _self20 * vZ);
        dest.putDouble(destOffset + 72, _self21 * vZ);
        dest.putDouble(destOffset + 80, _self22 * vZ);
        dest.putDouble(destOffset + 88, _self23 * vZ);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        dest.putDouble(destOffset, _self00 * _vx);
        dest.putDouble(destOffset + 8, _self01 * _vx);
        dest.putDouble(destOffset + 16, _self02 * _vx);
        dest.putDouble(destOffset + 24, _self03 * _vx);
        dest.putDouble(destOffset + 32, _self10 * _vy);
        dest.putDouble(destOffset + 40, _self11 * _vy);
        dest.putDouble(destOffset + 48, _self12 * _vy);
        dest.putDouble(destOffset + 56, _self13 * _vy);
        return preScale_api_sc54b95af_1(dest, destOffset, _self20, _self21, _self22, _self23, _vz);
    }

    /** Piece 2 of {@code preScale_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preScale_api_sc54b95af_1(java.nio.ByteBuffer dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _vz) {
        dest.putDouble(destOffset + 64, _self20 * _vz);
        dest.putDouble(destOffset + 72, _self21 * _vz);
        dest.putDouble(destOffset + 80, _self22 * _vz);
        dest.putDouble(destOffset + 88, _self23 * _vz);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src.getDouble(srcOffset + _i * 8);
            dest.putDouble(destOffset + _i * 8, s * _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t0 = 1.0 - s;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self01);
        dest.putDouble(destOffset + 16, s * _self02);
        dest.putDouble(destOffset + 24, Math.fma(s, _self03, pivotX * _t0));
        dest.putDouble(destOffset + 32, s * _self10);
        dest.putDouble(destOffset + 40, s * _self11);
        dest.putDouble(destOffset + 48, s * _self12);
        dest.putDouble(destOffset + 56, Math.fma(s, _self13, pivotY * _t0));
        dest.putDouble(destOffset + 64, s * _self20);
        return preScaleAround_api_s9b62942_1(dest, destOffset, s, pivotZ, _self21, _self22, _self23, _t0);
    }

    /** Piece 2 of {@code preScaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_s9b62942_1(java.nio.ByteBuffer dest, int destOffset, double s, double pivotZ, double _self21, double _self22, double _self23, double _t0) {
        dest.putDouble(destOffset + 72, s * _self21);
        dest.putDouble(destOffset + 80, s * _self22);
        dest.putDouble(destOffset + 88, Math.fma(s, _self23, pivotZ * _t0));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _pivotz = pivot.getDouble(pivotOffset + 16);
        double _t0 = 1.0 - s;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self01);
        dest.putDouble(destOffset + 16, s * _self02);
        dest.putDouble(destOffset + 24, Math.fma(s, _self03, _pivotx * _t0));
        dest.putDouble(destOffset + 32, s * _self10);
        dest.putDouble(destOffset + 40, s * _self11);
        dest.putDouble(destOffset + 48, s * _self12);
        dest.putDouble(destOffset + 56, Math.fma(s, _self13, _pivoty * _t0));
        return preScaleAround_api_s15bf7e10_1(dest, destOffset, s, _self20, _self21, _self22, _self23, _pivotz, _t0);
    }

    /** Piece 2 of {@code preScaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_s15bf7e10_1(java.nio.ByteBuffer dest, int destOffset, double s, double _self20, double _self21, double _self22, double _self23, double _pivotz, double _t0) {
        dest.putDouble(destOffset + 64, s * _self20);
        dest.putDouble(destOffset + 72, s * _self21);
        dest.putDouble(destOffset + 80, s * _self22);
        dest.putDouble(destOffset + 88, Math.fma(s, _self23, _pivotz * _t0));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        dest.putDouble(destOffset, sX * _self00);
        dest.putDouble(destOffset + 8, sX * _self01);
        dest.putDouble(destOffset + 16, sX * _self02);
        dest.putDouble(destOffset + 24, Math.fma(pivotX, 1.0 - sX, sX * _self03));
        dest.putDouble(destOffset + 32, sY * _self10);
        dest.putDouble(destOffset + 40, sY * _self11);
        dest.putDouble(destOffset + 48, sY * _self12);
        dest.putDouble(destOffset + 56, Math.fma(pivotY, 1.0 - sY, sY * _self13));
        dest.putDouble(destOffset + 64, sZ * _self20);
        return preScaleAround_api_sa8318c5f_1(dest, destOffset, sZ, pivotZ, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preScaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_sa8318c5f_1(java.nio.ByteBuffer dest, int destOffset, double sZ, double pivotZ, double _self21, double _self22, double _self23) {
        dest.putDouble(destOffset + 72, sZ * _self21);
        dest.putDouble(destOffset + 80, sZ * _self22);
        dest.putDouble(destOffset + 88, Math.fma(pivotZ, 1.0 - sZ, sZ * _self23));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _sz = s.getDouble(sOffset + 16);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _pivotz = pivot.getDouble(pivotOffset + 16);
        dest.putDouble(destOffset, _sx * _self00);
        dest.putDouble(destOffset + 8, _sx * _self01);
        dest.putDouble(destOffset + 16, _sx * _self02);
        dest.putDouble(destOffset + 24, Math.fma(_pivotx, 1.0 - _sx, _sx * _self03));
        dest.putDouble(destOffset + 32, _sy * _self10);
        dest.putDouble(destOffset + 40, _sy * _self11);
        return preScaleAround_api_s23660d7e_1(dest, destOffset, _self12, _self13, _self20, _self21, _self22, _self23, _sy, _sz, _pivoty, _pivotz);
    }

    /** Piece 2 of {@code preScaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_s23660d7e_1(java.nio.ByteBuffer dest, int destOffset, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _sy, double _sz, double _pivoty, double _pivotz) {
        dest.putDouble(destOffset + 48, _sy * _self12);
        dest.putDouble(destOffset + 56, Math.fma(_pivoty, 1.0 - _sy, _sy * _self13));
        dest.putDouble(destOffset + 64, _sz * _self20);
        dest.putDouble(destOffset + 72, _sz * _self21);
        dest.putDouble(destOffset + 80, _sz * _self22);
        dest.putDouble(destOffset + 88, Math.fma(_pivotz, 1.0 - _sz, _sz * _self23));
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self02);
        dest.putDouble(destOffset + 24, _self03 + vX);
        dest.putDouble(destOffset + 32, _self10);
        dest.putDouble(destOffset + 40, _self11);
        dest.putDouble(destOffset + 48, _self12);
        dest.putDouble(destOffset + 56, _self13 + vY);
        dest.putDouble(destOffset + 64, _self20);
        dest.putDouble(destOffset + 72, _self21);
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, _self23 + vZ);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x4OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self02);
        dest.putDouble(destOffset + 24, _self03 + _vx);
        dest.putDouble(destOffset + 32, _self10);
        dest.putDouble(destOffset + 40, _self11);
        dest.putDouble(destOffset + 48, _self12);
        dest.putDouble(destOffset + 56, _self13 + _vy);
        dest.putDouble(destOffset + 64, _self20);
        dest.putDouble(destOffset + 72, _self21);
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, _self23 + _vz);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _sp0 = normalX + normalX;
        double _t0 = -_self02;
        double _t9 = _sp0 * normalZ;
        double _t10 = _sp0 * normalY;
        double _t11 = (normalY + normalY) * normalZ;
        double _t12 = Math.fma(-2.0, normalX * normalX, 1.0);
        double _t13 = Math.fma(-2.0, normalY * normalY, 1.0);
        dest.putDouble(destOffset, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        dest.putDouble(destOffset + 8, Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10))));
        return reflect_api_s9150dda8_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self12, -_self22, _t9, _t10, _t11, _t12, _t13, Math.fma(-2.0, normalZ * normalZ, 1.0));
    }

    /** Piece 2 of {@code reflect_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer reflect_api_s9150dda8_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9))));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10))));
        dest.putDouble(destOffset + 40, Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10))));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9))));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10))));
        dest.putDouble(destOffset + 72, Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10))));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9))));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Double3x4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _normalx = normal.getDouble(normalOffset);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _normalz = normal.getDouble(normalOffset + 16);
        double _sp0 = _normalx + _normalx;
        double _t0 = -_self02;
        double _t9 = _sp0 * _normalz;
        double _t10 = _sp0 * _normaly;
        double _t12 = Math.fma(-2.0, _normalx * _normalx, 1.0);
        dest.putDouble(destOffset, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        return reflect_api_s7ac2285b_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, -_self12, -_self22, _t9, _t10, (_normaly + _normaly) * _normalz, _t12, Math.fma(-2.0, _normaly * _normaly, 1.0), Math.fma(-2.0, _normalz * _normalz, 1.0));
    }

    /** Piece 2 of {@code reflect_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer reflect_api_s7ac2285b_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        dest.putDouble(destOffset + 8, Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10))));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9))));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10))));
        dest.putDouble(destOffset + 40, Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10))));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9))));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10))));
        dest.putDouble(destOffset + 72, Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10))));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9))));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t0 = -rotY;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t16 = Math.fma(-rotZ, _t7, 1.0);
        return rotateAround_api_s52e2d758_1(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, -rotX, -pivotZ, _t5, _t6, rotZ * _t7, _t16, Math.fma(rotY, _t5, _t9), Math.fma(rotZ, _t6, _t10), Math.fma(rotZ, _t5, _t8), Math.fma(rotZ, _t5, -_t8), Math.fma(rotY, _t5, -_t9), Math.fma(rotZ, _t6, -_t10), Math.fma(_t0, _t6, _t16));
    }

    /** Piece 2 of {@code rotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_s52e2d758_1(java.nio.ByteBuffer dest, int destOffset, double rotX, double rotY, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t2, double _t3, double _t5, double _t6, double _t11, double _t16, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27) {
        double _t28 = Math.fma(_t2, _t5, _t16);
        double _t29 = Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0));
        double _t39 = Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25)));
        double _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        double _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        dest.putDouble(destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        dest.putDouble(destOffset + 24, Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03))));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18)));
        return rotateAround_api_s52e2d758_2(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, _t39, _t40, _t41);
    }

    /** Piece 3 of {@code rotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_s52e2d758_2(java.nio.ByteBuffer dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39, double _t40, double _t41) {
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26)));
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13))));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26)));
        dest.putDouble(destOffset + 88, Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer rot, int rotOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rotBase = UnsafeOpsHolder.U.getLong(rot, UnsafeCopy.BB_ADDRESS_OFFSET) + rotOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x4OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _rotBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer rot, int rotOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _rotx = rot.getDouble(rotOffset);
        double _roty = rot.getDouble(rotOffset + 8);
        double _rotz = rot.getDouble(rotOffset + 16);
        double _rotw = rot.getDouble(rotOffset + 24);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _pivotz = pivot.getDouble(pivotOffset + 16);
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        return rotateAround_api_sacbbb9ec_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _rotz, _pivotx, _pivoty, _pivotz, -_roty, -_rotx, -_pivotz, _t5, _t6, _rotw * _t6, _rotw * _t7, _rotw * _t5, _rotz * _t7, Math.fma(-_rotz, _t7, 1.0));
    }

    /** Piece 2 of {@code rotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_sacbbb9ec_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _rotz, double _pivotx, double _pivoty, double _pivotz, double _t0, double _t2, double _t3, double _t5, double _t6, double _t8, double _t9, double _t10, double _t11, double _t16) {
        double _t18 = Math.fma(_roty, _t5, _t9);
        double _t19 = Math.fma(_rotz, _t6, _t10);
        double _t20 = Math.fma(_rotz, _t5, _t8);
        double _t24 = Math.fma(_rotz, _t5, -_t8);
        double _t25 = Math.fma(_roty, _t5, -_t9);
        double _t26 = Math.fma(_rotz, _t6, -_t10);
        double _t27 = Math.fma(_t0, _t6, _t16);
        double _t28 = Math.fma(_t2, _t5, _t16);
        dest.putDouble(destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        return rotateAround_api_sacbbb9ec_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)), Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))), Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18))), Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24))));
    }

    /** Piece 3 of {@code rotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_sacbbb9ec_2(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39, double _t40, double _t41) {
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        dest.putDouble(destOffset + 24, Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03))));
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26)));
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13))));
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26)));
        dest.putDouble(destOffset + 88, Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        return rotateAxis_api_sb37da5b_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)));
    }

    /** Piece 2 of {@code rotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_sb37da5b_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.putDouble(destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Double3x4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t5 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        return rotateAxis_api_sa1c3adf2_1(dest, destOffset, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _axisx, _axisy, _axisz, _axisx * _axisz, _t5, _t6, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisz, _t0, _t11 * _t5), Math.fma(_axisx, _t0, _t11 * _t6));
    }

    /** Piece 2 of {@code rotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_sa1c3adf2_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _axisx, double _axisy, double _axisz, double _t2, double _t5, double _t6, double _t11, double _t18, double _t19, double _t20, double _t21, double _t22) {
        double _t23 = Math.fma(_axisy, _t0, _t11 * _t2);
        double _t24 = Math.fma(_t11, _t2, -(_axisy * _t0));
        double _t25 = Math.fma(_t11, _t5, -(_axisz * _t0));
        double _t26 = Math.fma(_t11, _t6, -(_axisx * _t0));
        dest.putDouble(destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        return rotateAxis_api_sa1c3adf2_2(dest, destOffset, _self20, _self21, _self22, _self23, _t19, _t20, _t22, _t23, _t25, _t26);
    }

    /** Piece 3 of {@code rotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_sa1c3adf2_2(java.nio.ByteBuffer dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t19, double _t20, double _t22, double _t23, double _t25, double _t26) {
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateQuat_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.ByteBuffer rotateQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t0 = -qY;
        double _t2 = -qX;
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        double _t12 = Math.fma(-qZ, _t5, 1.0);
        return rotateQuat_api_s8d908c98_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, Math.fma(qY, _t3, _t7), Math.fma(qZ, _t4, _t8), Math.fma(qZ, _t3, _t6), Math.fma(qZ, _t3, -_t6), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8), Math.fma(_t0, _t4, _t12), Math.fma(_t2, _t3, _t12), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 2 of {@code rotateQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateQuat_api_s8d908c98_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19)));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19)));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19)));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset;
        Double3x4OpsKernelsAddress.rotateQuat_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _qx = q.getDouble(qOffset);
        double _qy = q.getDouble(qOffset + 8);
        double _qz = q.getDouble(qOffset + 16);
        double _qw = q.getDouble(qOffset + 24);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        double _t6 = _qw * _t4;
        double _t7 = _qw * _t5;
        double _t8 = _qw * _t3;
        return rotateQuat_api_s26c0571e_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _qz, -_qy, -_qx, _t3, _t4, _t8, Math.fma(-_qz, _t5, 1.0), Math.fma(_qy, _t3, _t7), Math.fma(_qz, _t4, _t8), Math.fma(_qz, _t3, _t6), Math.fma(_qz, _t3, -_t6), Math.fma(_qy, _t3, -_t7));
    }

    /** Piece 2 of {@code rotateQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateQuat_api_s26c0571e_1(java.nio.ByteBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _qz, double _t0, double _t2, double _t3, double _t4, double _t8, double _t12, double _t14, double _t15, double _t16, double _t17, double _t18) {
        double _t19 = Math.fma(_qz, _t4, -_t8);
        double _t20 = Math.fma(_t0, _t4, _t12);
        double _t21 = Math.fma(_t2, _t3, _t12);
        double _t22 = Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0));
        dest.putDouble(destOffset, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19)));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19)));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14)));
        return rotateQuat_api_s26c0571e_2(dest, destOffset, _self20, _self21, _self22, _self23, _t15, _t16, _t18, _t19, _t21, _t22);
    }

    /** Piece 3 of {@code rotateQuat_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateQuat_api_s26c0571e_2(java.nio.ByteBuffer dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t15, double _t16, double _t18, double _t19, double _t21, double _t22) {
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19)));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, Math.fma(_self01, _t1, _self02 * _t0));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t1, -(_self01 * _t0)));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, _self10);
        dest.putDouble(destOffset + 40, Math.fma(_self11, _t1, _self12 * _t0));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t1, -(_self11 * _t0)));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, _self20);
        return rotateX_api_s9a683707_1(dest, destOffset, _t0, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateX_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateX_api_s9a683707_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self21, double _self22, double _self23, double _t1) {
        dest.putDouble(destOffset + 72, Math.fma(_self21, _t1, _self22 * _t0));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t1, -(_self21 * _t0)));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        return rotateXYZ_api_sf844a819_1(dest, destOffset, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1 * _t5, _t0 * _t5, _t5 * _t4, _t3 * _t5, Math.fma(_t6, _t4, _t1 * _t3), Math.fma(_t7, _t1, _t0 * _t4), Math.fma(_t0, _t1, -(_t7 * _t4)), Math.fma(_t3, _t4, -(_t6 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_sf844a819_1(java.nio.ByteBuffer dest, int destOffset, double _t2, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t10, double _t11, double _t13, double _t15, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t13, _self01 * _t18)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t19, Math.fma(_self01, _t21, -(_self00 * _t10))));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t15, Math.fma(_self00, _t2, -(_self01 * _t11))));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t20, Math.fma(_self10, _t13, _self11 * _t18)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t19, Math.fma(_self11, _t21, -(_self10 * _t10))));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t15, Math.fma(_self10, _t2, -(_self11 * _t11))));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t20, Math.fma(_self20, _t13, _self21 * _t18)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t19, Math.fma(_self21, _t21, -(_self20 * _t10))));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t15, Math.fma(_self20, _t2, -(_self21 * _t11))));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        return rotateXZY_api_s2462d199_1(dest, destOffset, _t1, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0 * _t5, _t2 * _t5, _t3 * _t5, _t4 * _t5, Math.fma(_t9, _t3, _t0 * _t2), Math.fma(_t6, _t2, _t4 * _t3), Math.fma(_t6, _t3, -(_t2 * _t4)), Math.fma(_t9, _t2, -(_t0 * _t3)));
    }

    /** Piece 2 of {@code rotateXZY_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_s2462d199_1(java.nio.ByteBuffer dest, int destOffset, double _t1, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t10, double _t11, double _t15, double _t16, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t15, _self01 * _t18)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t10, Math.fma(_self01, _t16, -(_self00 * _t1))));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t19, Math.fma(_self00, _t11, _self01 * _t21)));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t20, Math.fma(_self10, _t15, _self11 * _t18)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t10, Math.fma(_self11, _t16, -(_self10 * _t1))));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t19, Math.fma(_self10, _t11, _self11 * _t21)));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t20, Math.fma(_self20, _t15, _self21 * _t18)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t10, Math.fma(_self21, _t16, -(_self20 * _t1))));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t19, Math.fma(_self20, _t11, _self21 * _t21)));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, Math.fma(_self00, _t1, -(_self02 * _t0)));
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, Math.fma(_self00, _t0, _self02 * _t1));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_self10, _t1, -(_self12 * _t0)));
        dest.putDouble(destOffset + 40, _self11);
        dest.putDouble(destOffset + 48, Math.fma(_self10, _t0, _self12 * _t1));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t1, -(_self22 * _t0)));
        return rotateY_api_sf762e84a_1(dest, destOffset, _t0, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateY_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateY_api_sf762e84a_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self20, double _self21, double _self22, double _self23, double _t1) {
        dest.putDouble(destOffset + 72, _self21);
        dest.putDouble(destOffset + 80, Math.fma(_self20, _t0, _self22 * _t1));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        return rotateYXZ_api_sbfc926d_1(dest, destOffset, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t2 * _t5, _t1 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)), Math.fma(_t6, _t4, -(_t2 * _t3)));
    }

    /** Piece 2 of {@code rotateYXZ_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_sbfc926d_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t10, double _t12, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0))));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0))));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0))));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t3 = Math.cosFromSin(_t1, angleZ);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        return rotateYZX_api_s42d1eb59_1(dest, destOffset, _t1, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0 * _t3, _t2 * _t3, _t4 * _t3, _t5 * _t3, Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t9, _t2, _t0 * _t5), Math.fma(_t2, _t0, -(_t9 * _t5)), Math.fma(_t5, _t4, -(_t6 * _t2)));
    }

    /** Piece 2 of {@code rotateYZX_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_s42d1eb59_1(java.nio.ByteBuffer dest, int destOffset, double _t1, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t7, double _t11, double _t13, double _t14, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset, Math.fma(-_self02, _t7, Math.fma(_self00, _t13, _self01 * _t1)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t18, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t21, Math.fma(_self00, _t19, -(_self01 * _t11))));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(-_self12, _t7, Math.fma(_self10, _t13, _self11 * _t1)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t18, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t21, Math.fma(_self10, _t19, -(_self11 * _t11))));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(-_self22, _t7, Math.fma(_self20, _t13, _self21 * _t1)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t18, Math.fma(_self20, _t20, _self21 * _t14)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t21, Math.fma(_self20, _t19, -(_self21 * _t11))));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, Math.fma(_self00, _t1, _self01 * _t0));
        dest.putDouble(destOffset + 8, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.putDouble(destOffset + 16, _self02);
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(_self10, _t1, _self11 * _t0));
        dest.putDouble(destOffset + 40, Math.fma(_self11, _t1, -(_self10 * _t0)));
        dest.putDouble(destOffset + 48, _self12);
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(_self20, _t1, _self21 * _t0));
        return rotateZ_api_sc3134579_1(dest, destOffset, _t0, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateZ_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZ_api_sc3134579_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self20, double _self21, double _self22, double _self23, double _t1) {
        dest.putDouble(destOffset + 72, Math.fma(_self21, _t1, -(_self20 * _t0)));
        dest.putDouble(destOffset + 80, _self22);
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t8 = _t1 * _t5;
        return rotateZXY_api_s50246ed_1(dest, destOffset, _t1, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0 * _t3, _t2 * _t3, _t3 * _t5, _t3 * _t4, Math.fma(_t8, _t0, _t2 * _t4), Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t4, _t5, -(_t6 * _t0)), Math.fma(_t0, _t2, -(_t8 * _t4)));
    }

    /** Piece 2 of {@code rotateZXY_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_s50246ed_1(java.nio.ByteBuffer dest, int destOffset, double _t1, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t7, double _t10, double _t14, double _t15, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset, Math.fma(-_self02, _t7, Math.fma(_self00, _t20, _self01 * _t18)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t1, Math.fma(_self01, _t14, -(_self00 * _t10))));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t15, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(-_self12, _t7, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t1, Math.fma(_self11, _t14, -(_self10 * _t10))));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t15, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(-_self22, _t7, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t1, Math.fma(_self21, _t14, -(_self20 * _t10))));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t15, Math.fma(_self20, _t19, _self21 * _t21)));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t10 = _t0 * _t4;
        return rotateZYX_api_s7fbcba89_1(dest, destOffset, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1 * _t3, _t2 * _t3, _t3 * _t4, _t5 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t5, -(_t2 * _t4)));
    }

    /** Piece 2 of {@code rotateZYX_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_s7fbcba89_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t8, double _t9, double _t15, double _t17, double _t18, double _t19, double _t20, double _t21) {
        dest.putDouble(destOffset, Math.fma(-_self02, _t0, Math.fma(_self00, _t15, _self01 * _t8)));
        dest.putDouble(destOffset + 8, Math.fma(_self02, _t9, Math.fma(_self00, _t20, _self01 * _t18)));
        dest.putDouble(destOffset + 16, Math.fma(_self02, _t17, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.putDouble(destOffset + 24, _self03);
        dest.putDouble(destOffset + 32, Math.fma(-_self12, _t0, Math.fma(_self10, _t15, _self11 * _t8)));
        dest.putDouble(destOffset + 40, Math.fma(_self12, _t9, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.putDouble(destOffset + 48, Math.fma(_self12, _t17, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.putDouble(destOffset + 56, _self13);
        dest.putDouble(destOffset + 64, Math.fma(-_self22, _t0, Math.fma(_self20, _t15, _self21 * _t8)));
        dest.putDouble(destOffset + 72, Math.fma(_self22, _t9, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.putDouble(destOffset + 80, Math.fma(_self22, _t17, Math.fma(_self20, _t19, _self21 * _t21)));
        dest.putDouble(destOffset + 88, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0 * vX);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1 * vY);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2 * vZ);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0 * _vx);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1 * _vy);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2 * _vz);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, s * _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, s * _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, s * _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _t3 = pivotZ * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self01);
        dest.putDouble(destOffset + 16, s * _self02);
        dest.putDouble(destOffset + 24, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        dest.putDouble(destOffset + 32, s * _self10);
        dest.putDouble(destOffset + 40, s * _self11);
        dest.putDouble(destOffset + 48, s * _self12);
        return scaleAround_api_sa0117a9f_1(dest, destOffset, s, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1, _t2, _t3);
    }

    /** Piece 2 of {@code scaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_sa0117a9f_1(java.nio.ByteBuffer dest, int destOffset, double s, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t3) {
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        dest.putDouble(destOffset + 64, s * _self20);
        dest.putDouble(destOffset + 72, s * _self21);
        dest.putDouble(destOffset + 80, s * _self22);
        dest.putDouble(destOffset + 88, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t0 = 1.0 - s;
        double _t1 = pivot.getDouble(pivotOffset) * _t0;
        double _t2 = pivot.getDouble(pivotOffset + 8) * _t0;
        double _t3 = pivot.getDouble(pivotOffset + 16) * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self01);
        dest.putDouble(destOffset + 16, s * _self02);
        dest.putDouble(destOffset + 24, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        dest.putDouble(destOffset + 32, s * _self10);
        dest.putDouble(destOffset + 40, s * _self11);
        dest.putDouble(destOffset + 48, s * _self12);
        return scaleAround_api_s13ad5423_1(dest, destOffset, s, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1, _t2, _t3);
    }

    /** Piece 2 of {@code scaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_s13ad5423_1(java.nio.ByteBuffer dest, int destOffset, double s, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t3) {
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        dest.putDouble(destOffset + 64, s * _self20);
        dest.putDouble(destOffset + 72, s * _self21);
        dest.putDouble(destOffset + 80, s * _self22);
        dest.putDouble(destOffset + 88, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        dest.putDouble(destOffset, sX * _self00);
        dest.putDouble(destOffset + 8, sY * _self01);
        dest.putDouble(destOffset + 16, sZ * _self02);
        dest.putDouble(destOffset + 24, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        dest.putDouble(destOffset + 32, sX * _self10);
        dest.putDouble(destOffset + 40, sY * _self11);
        dest.putDouble(destOffset + 48, sZ * _self12);
        return scaleAround_api_sbb9ce50c_1(dest, destOffset, sX, sY, sZ, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t3, _t4, _t5);
    }

    /** Piece 2 of {@code scaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_sbb9ce50c_1(java.nio.ByteBuffer dest, int destOffset, double sX, double sY, double sZ, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t3, double _t4, double _t5) {
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        dest.putDouble(destOffset + 64, sX * _self20);
        dest.putDouble(destOffset + 72, sY * _self21);
        dest.putDouble(destOffset + 80, sZ * _self22);
        dest.putDouble(destOffset + 88, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _sz = s.getDouble(sOffset + 16);
        double _t3 = pivot.getDouble(pivotOffset) * (1.0 - _sx);
        double _t4 = pivot.getDouble(pivotOffset + 8) * (1.0 - _sy);
        double _t5 = pivot.getDouble(pivotOffset + 16) * (1.0 - _sz);
        dest.putDouble(destOffset, _sx * _self00);
        dest.putDouble(destOffset + 8, _sy * _self01);
        dest.putDouble(destOffset + 16, _sz * _self02);
        dest.putDouble(destOffset + 24, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        return scaleAround_api_sbf8d1eb9_1(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _sx, _sy, _sz, _t3, _t4, _t5);
    }

    /** Piece 2 of {@code scaleAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_sbf8d1eb9_1(java.nio.ByteBuffer dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _sx, double _sy, double _sz, double _t3, double _t4, double _t5) {
        dest.putDouble(destOffset + 32, _sx * _self10);
        dest.putDouble(destOffset + 40, _sy * _self11);
        dest.putDouble(destOffset + 48, _sz * _self12);
        dest.putDouble(destOffset + 56, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        dest.putDouble(destOffset + 64, _sx * _self20);
        dest.putDouble(destOffset + 72, _sy * _self21);
        dest.putDouble(destOffset + 80, _sz * _self22);
        dest.putDouble(destOffset + 88, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, Math.fma(_eself0, vX, Math.fma(_eself1, vY, Math.fma(_eself2, vZ, _eself3))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x4OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eself2 = src.getDouble(srcOffset + (_lo + 2) * 8);
            double _eself3 = src.getDouble(srcOffset + (_lo + 3) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1);
            dest.putDouble(destOffset + (_lo + 2) * 8, _eself2);
            dest.putDouble(destOffset + (_lo + 3) * 8, Math.fma(_eself0, _vx, Math.fma(_eself1, _vy, Math.fma(_eself2, _vz, _eself3))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulVec4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ, double vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.mulVec4_unsafe(_destBase, _srcBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ, double vW) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        dest.putDouble(destOffset, Math.fma(_self03, vW, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY))));
        dest.putDouble(destOffset + 8, Math.fma(_self13, vW, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY))));
        dest.putDouble(destOffset + 16, Math.fma(_self23, vW, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY))));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x4OpsKernelsAddress.mulVec4_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _vw = v.getDouble(vOffset + 24);
        dest.putDouble(destOffset, Math.fma(_self03, _vw, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy))));
        dest.putDouble(destOffset + 8, Math.fma(_self13, _vw, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy))));
        dest.putDouble(destOffset + 16, Math.fma(_self23, _vw, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy))));
        return dest;
    }

    public static java.nio.ByteBuffer transformAabb_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.transformAabb_unsafe(_destBase, _srcBase, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.ByteBuffer transformAabb_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        return transformAabb_api_s98bd54f2_1(dest, destOffset, minX, minY, minZ, maxX, maxY, maxZ, _self03, _self13, _self23, minX * _self00, maxX * _self00, minY * _self01, maxY * _self01, minZ * _self02, maxZ * _self02, minX * _self10, maxX * _self10, minY * _self11, maxY * _self11, minZ * _self12, maxZ * _self12, minX * _self20, maxX * _self20, minY * _self21, maxY * _self21, minZ * _self22, maxZ * _self22);
    }

    /** Piece 2 of {@code transformAabb_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer transformAabb_api_s98bd54f2_1(java.nio.ByteBuffer dest, int destOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, double _self03, double _self13, double _self23, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20) {
        if (java.lang.Math.min(java.lang.Math.min(maxX - minX, maxY - minY), maxZ - minZ) < 0.0) {
            dest.putDouble(destOffset, Double.POSITIVE_INFINITY);
            dest.putDouble(destOffset + 8, Double.POSITIVE_INFINITY);
            dest.putDouble(destOffset + 16, Double.POSITIVE_INFINITY);
            dest.putDouble(destOffset + 24, Double.NEGATIVE_INFINITY);
            dest.putDouble(destOffset + 32, Double.NEGATIVE_INFINITY);
            dest.putDouble(destOffset + 40, Double.NEGATIVE_INFINITY);
        } else {
            dest.putDouble(destOffset, _self03 + java.lang.Math.min(_t3, _t4) + java.lang.Math.min(_t5, _t6) + java.lang.Math.min(_t7, _t8));
            dest.putDouble(destOffset + 8, _self13 + java.lang.Math.min(_t9, _t10) + java.lang.Math.min(_t11, _t12) + java.lang.Math.min(_t13, _t14));
            dest.putDouble(destOffset + 16, _self23 + java.lang.Math.min(_t15, _t16) + java.lang.Math.min(_t17, _t18) + java.lang.Math.min(_t19, _t20));
            dest.putDouble(destOffset + 24, _self03 + java.lang.Math.max(_t3, _t4) + java.lang.Math.max(_t5, _t6) + java.lang.Math.max(_t7, _t8));
            dest.putDouble(destOffset + 32, _self13 + java.lang.Math.max(_t9, _t10) + java.lang.Math.max(_t11, _t12) + java.lang.Math.max(_t13, _t14));
            dest.putDouble(destOffset + 40, _self23 + java.lang.Math.max(_t15, _t16) + java.lang.Math.max(_t17, _t18) + java.lang.Math.max(_t19, _t20));
        }
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        dest.putDouble(destOffset, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        dest.putDouble(destOffset + 8, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        dest.putDouble(destOffset, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        dest.putDouble(destOffset + 8, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        dest.putDouble(destOffset + 16, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        dest.putDouble(destOffset, Math.fma(_self00, vX, Math.fma(_self01, vY, Math.fma(_self02, vZ, _self03))));
        dest.putDouble(destOffset + 8, Math.fma(_self10, vX, Math.fma(_self11, vY, Math.fma(_self12, vZ, _self13))));
        dest.putDouble(destOffset + 16, Math.fma(_self20, vX, Math.fma(_self21, vY, Math.fma(_self22, vZ, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self01 = src.getDouble(srcOffset + 8);
        double _self02 = src.getDouble(srcOffset + 16);
        double _self03 = src.getDouble(srcOffset + 24);
        double _self10 = src.getDouble(srcOffset + 32);
        double _self11 = src.getDouble(srcOffset + 40);
        double _self12 = src.getDouble(srcOffset + 48);
        double _self13 = src.getDouble(srcOffset + 56);
        double _self20 = src.getDouble(srcOffset + 64);
        double _self21 = src.getDouble(srcOffset + 72);
        double _self22 = src.getDouble(srcOffset + 80);
        double _self23 = src.getDouble(srcOffset + 88);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        dest.putDouble(destOffset, Math.fma(_self00, _vx, Math.fma(_self01, _vy, Math.fma(_self02, _vz, _self03))));
        dest.putDouble(destOffset + 8, Math.fma(_self10, _vx, Math.fma(_self11, _vy, Math.fma(_self12, _vz, _self13))));
        dest.putDouble(destOffset + 16, Math.fma(_self20, _vx, Math.fma(_self21, _vy, Math.fma(_self22, _vz, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset;
        Double3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _matrixBase, _pointsBase, count);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int count) {
        double _m00 = matrix.getDouble(matrixOffset);
        double _m01 = matrix.getDouble(matrixOffset + 8);
        double _m02 = matrix.getDouble(matrixOffset + 16);
        double _m03 = matrix.getDouble(matrixOffset + 24);
        double _m10 = matrix.getDouble(matrixOffset + 32);
        double _m11 = matrix.getDouble(matrixOffset + 40);
        double _m12 = matrix.getDouble(matrixOffset + 48);
        double _m13 = matrix.getDouble(matrixOffset + 56);
        double _m20 = matrix.getDouble(matrixOffset + 64);
        double _m21 = matrix.getDouble(matrixOffset + 72);
        double _m22 = matrix.getDouble(matrixOffset + 80);
        double _m23 = matrix.getDouble(matrixOffset + 88);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * 24;
            int _do = destOffset + _i * 24;
            double px = points.getDouble(_po), py = points.getDouble(_po + 8), pz = points.getDouble(_po + 16);
            dest.putDouble(_do, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            dest.putDouble(_do + 8, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            dest.putDouble(_do + 16, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, int destStride, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int pointsStride, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset;
        Double3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, destStride, _matrixBase, _pointsBase, pointsStride, count);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, int destStride, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int pointsStride, int count) {
        double _m00 = matrix.getDouble(matrixOffset);
        double _m01 = matrix.getDouble(matrixOffset + 8);
        double _m02 = matrix.getDouble(matrixOffset + 16);
        double _m03 = matrix.getDouble(matrixOffset + 24);
        double _m10 = matrix.getDouble(matrixOffset + 32);
        double _m11 = matrix.getDouble(matrixOffset + 40);
        double _m12 = matrix.getDouble(matrixOffset + 48);
        double _m13 = matrix.getDouble(matrixOffset + 56);
        double _m20 = matrix.getDouble(matrixOffset + 64);
        double _m21 = matrix.getDouble(matrixOffset + 72);
        double _m22 = matrix.getDouble(matrixOffset + 80);
        double _m23 = matrix.getDouble(matrixOffset + 88);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * pointsStride;
            int _do = destOffset + _i * destStride;
            double px = points.getDouble(_po), py = points.getDouble(_po + 8), pz = points.getDouble(_po + 16);
            dest.putDouble(_do, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            dest.putDouble(_do + 8, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            dest.putDouble(_do + 16, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset;
        Double3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _matrixBase, _pointsBase, count);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int count) {
        double _m00 = matrix.getDouble(matrixOffset);
        double _m01 = matrix.getDouble(matrixOffset + 8);
        double _m02 = matrix.getDouble(matrixOffset + 16);
        double _m10 = matrix.getDouble(matrixOffset + 32);
        double _m11 = matrix.getDouble(matrixOffset + 40);
        double _m12 = matrix.getDouble(matrixOffset + 48);
        double _m20 = matrix.getDouble(matrixOffset + 64);
        double _m21 = matrix.getDouble(matrixOffset + 72);
        double _m22 = matrix.getDouble(matrixOffset + 80);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * 24;
            int _do = destOffset + _i * 24;
            double px = points.getDouble(_po), py = points.getDouble(_po + 8), pz = points.getDouble(_po + 16);
            dest.putDouble(_do, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            dest.putDouble(_do + 8, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            dest.putDouble(_do + 16, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, int destStride, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int pointsStride, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset;
        Double3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, destStride, _matrixBase, _pointsBase, pointsStride, count);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, int destStride, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int pointsStride, int count) {
        double _m00 = matrix.getDouble(matrixOffset);
        double _m01 = matrix.getDouble(matrixOffset + 8);
        double _m02 = matrix.getDouble(matrixOffset + 16);
        double _m10 = matrix.getDouble(matrixOffset + 32);
        double _m11 = matrix.getDouble(matrixOffset + 40);
        double _m12 = matrix.getDouble(matrixOffset + 48);
        double _m20 = matrix.getDouble(matrixOffset + 64);
        double _m21 = matrix.getDouble(matrixOffset + 72);
        double _m22 = matrix.getDouble(matrixOffset + 80);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * pointsStride;
            int _do = destOffset + _i * destStride;
            double px = points.getDouble(_po), py = points.getDouble(_po + 8), pz = points.getDouble(_po + 16);
            dest.putDouble(_do, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            dest.putDouble(_do + 8, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            dest.putDouble(_do + 16, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer lerpComposeTRSMul_fmaUnsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer t1, int t1Offset, java.nio.ByteBuffer t2, int t2Offset, java.nio.ByteBuffer q1, int q1Offset, java.nio.ByteBuffer q2, int q2Offset, java.nio.ByteBuffer s1, int s1Offset, java.nio.ByteBuffer s2, int s2Offset, java.nio.ByteBuffer m, int mOffset, double alpha, int count) {
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        long _t2Base = UnsafeOpsHolder.U.getLong(t2, UnsafeCopy.BB_ADDRESS_OFFSET) + t2Offset;
        long _q1Base = UnsafeOpsHolder.U.getLong(q1, UnsafeCopy.BB_ADDRESS_OFFSET) + q1Offset;
        long _q2Base = UnsafeOpsHolder.U.getLong(q2, UnsafeCopy.BB_ADDRESS_OFFSET) + q2Offset;
        long _s1Base = UnsafeOpsHolder.U.getLong(s1, UnsafeCopy.BB_ADDRESS_OFFSET) + s1Offset;
        long _s2Base = UnsafeOpsHolder.U.getLong(s2, UnsafeCopy.BB_ADDRESS_OFFSET) + s2Offset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.lerpComposeTRSMul_fmaUnsafe(_destBase, _t1Base, _t2Base, _q1Base, _q2Base, _s1Base, _s2Base, _mBase, alpha, count);
        return dest;
    }

    public static java.nio.ByteBuffer lerpComposeTRSMul_mulAddUnsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer t1, int t1Offset, java.nio.ByteBuffer t2, int t2Offset, java.nio.ByteBuffer q1, int q1Offset, java.nio.ByteBuffer q2, int q2Offset, java.nio.ByteBuffer s1, int s1Offset, java.nio.ByteBuffer s2, int s2Offset, java.nio.ByteBuffer m, int mOffset, double alpha, int count) {
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        long _t2Base = UnsafeOpsHolder.U.getLong(t2, UnsafeCopy.BB_ADDRESS_OFFSET) + t2Offset;
        long _q1Base = UnsafeOpsHolder.U.getLong(q1, UnsafeCopy.BB_ADDRESS_OFFSET) + q1Offset;
        long _q2Base = UnsafeOpsHolder.U.getLong(q2, UnsafeCopy.BB_ADDRESS_OFFSET) + q2Offset;
        long _s1Base = UnsafeOpsHolder.U.getLong(s1, UnsafeCopy.BB_ADDRESS_OFFSET) + s1Offset;
        long _s2Base = UnsafeOpsHolder.U.getLong(s2, UnsafeCopy.BB_ADDRESS_OFFSET) + s2Offset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.lerpComposeTRSMul_mulAddUnsafe(_destBase, _t1Base, _t2Base, _q1Base, _q2Base, _s1Base, _s2Base, _mBase, alpha, count);
        return dest;
    }

    public static java.nio.ByteBuffer lerpComposeTRSMul_fmaApi(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer t1, int t1Offset, java.nio.ByteBuffer t2, int t2Offset, java.nio.ByteBuffer q1, int q1Offset, java.nio.ByteBuffer q2, int q2Offset, java.nio.ByteBuffer s1, int s1Offset, java.nio.ByteBuffer s2, int s2Offset, java.nio.ByteBuffer m, int mOffset, double alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _t1o = t1Offset + _i * 24;
            int _t2o = t2Offset + _i * 24;
            int _q1o = q1Offset + _i * 32;
            int _q2o = q2Offset + _i * 32;
            int _s1o = s1Offset + _i * 24;
            int _s2o = s2Offset + _i * 24;
            int _mo = mOffset + _i * 96;
            int _do = destOffset + _i * 96;
            double _ax = t1.getDouble(_t1o), _ay = t1.getDouble(_t1o + 8), _az = t1.getDouble(_t1o + 16);
            double _tx = Math.fma(alpha, t2.getDouble(_t2o) - _ax, _ax);
            double _ty = Math.fma(alpha, t2.getDouble(_t2o + 8) - _ay, _ay);
            double _tz = Math.fma(alpha, t2.getDouble(_t2o + 16) - _az, _az);
            double _bx = s1.getDouble(_s1o), _by = s1.getDouble(_s1o + 8), _bz = s1.getDouble(_s1o + 16);
            double _sx = Math.fma(alpha, s2.getDouble(_s2o) - _bx, _bx);
            double _sy = Math.fma(alpha, s2.getDouble(_s2o + 8) - _by, _by);
            double _sz = Math.fma(alpha, s2.getDouble(_s2o + 16) - _bz, _bz);
            double _ux = q1.getDouble(_q1o), _uy = q1.getDouble(_q1o + 8), _uz = q1.getDouble(_q1o + 16), _uw = q1.getDouble(_q1o + 24);
            double _vx = q2.getDouble(_q2o), _vy = q2.getDouble(_q2o + 8), _vz = q2.getDouble(_q2o + 16), _vw = q2.getDouble(_q2o + 24);
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
            double _m00 = m.getDouble(_mo), _m01 = m.getDouble(_mo + 8), _m02 = m.getDouble(_mo + 16), _m03 = m.getDouble(_mo + 24);
            double _m10 = m.getDouble(_mo + 32), _m11 = m.getDouble(_mo + 40), _m12 = m.getDouble(_mo + 48), _m13 = m.getDouble(_mo + 56);
            double _m20 = m.getDouble(_mo + 64), _m21 = m.getDouble(_mo + 72), _m22 = m.getDouble(_mo + 80), _m23 = m.getDouble(_mo + 88);
            double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest.putDouble(_do, _e00);
            dest.putDouble(_do + 8, _e01);
            dest.putDouble(_do + 16, _e02);
            dest.putDouble(_do + 24, _e03);
            dest.putDouble(_do + 32, _e10);
            dest.putDouble(_do + 40, _e11);
            dest.putDouble(_do + 48, _e12);
            dest.putDouble(_do + 56, _e13);
            dest.putDouble(_do + 64, _e20);
            dest.putDouble(_do + 72, _e21);
            dest.putDouble(_do + 80, _e22);
            dest.putDouble(_do + 88, _e23);
        }
        return dest;
    }

    public static java.nio.ByteBuffer lerpComposeTRSMul_mulAddApi(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer t1, int t1Offset, java.nio.ByteBuffer t2, int t2Offset, java.nio.ByteBuffer q1, int q1Offset, java.nio.ByteBuffer q2, int q2Offset, java.nio.ByteBuffer s1, int s1Offset, java.nio.ByteBuffer s2, int s2Offset, java.nio.ByteBuffer m, int mOffset, double alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _t1o = t1Offset + _i * 24;
            int _t2o = t2Offset + _i * 24;
            int _q1o = q1Offset + _i * 32;
            int _q2o = q2Offset + _i * 32;
            int _s1o = s1Offset + _i * 24;
            int _s2o = s2Offset + _i * 24;
            int _mo = mOffset + _i * 96;
            int _do = destOffset + _i * 96;
            double _ax = t1.getDouble(_t1o), _ay = t1.getDouble(_t1o + 8), _az = t1.getDouble(_t1o + 16);
            double _bx = s1.getDouble(_s1o), _by = s1.getDouble(_s1o + 8), _bz = s1.getDouble(_s1o + 16);
            double _sx = alpha * (s2.getDouble(_s2o) - _bx) + _bx;
            double _sy = alpha * (s2.getDouble(_s2o + 8) - _by) + _by;
            double _sz = alpha * (s2.getDouble(_s2o + 16) - _bz) + _bz;
            double _ux = q1.getDouble(_q1o), _uy = q1.getDouble(_q1o + 8), _uz = q1.getDouble(_q1o + 16), _uw = q1.getDouble(_q1o + 24);
            double _vx = q2.getDouble(_q2o), _vy = q2.getDouble(_q2o + 8), _vz = q2.getDouble(_q2o + 16), _vw = q2.getDouble(_q2o + 24);
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
            double _m00 = m.getDouble(_mo), _m01 = m.getDouble(_mo + 8), _m02 = m.getDouble(_mo + 16), _m03 = m.getDouble(_mo + 24);
            double _m10 = m.getDouble(_mo + 32), _m11 = m.getDouble(_mo + 40), _m12 = m.getDouble(_mo + 48), _m13 = m.getDouble(_mo + 56);
            double _m20 = m.getDouble(_mo + 64), _m21 = m.getDouble(_mo + 72), _m22 = m.getDouble(_mo + 80), _m23 = m.getDouble(_mo + 88);
            double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + (alpha * (t2.getDouble(_t2o) - _ax) + _ax);
            double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + (alpha * (t2.getDouble(_t2o + 8) - _ay) + _ay);
            double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + (alpha * (t2.getDouble(_t2o + 16) - _az) + _az);
            dest.putDouble(_do, _e00);
            dest.putDouble(_do + 8, _e01);
            dest.putDouble(_do + 16, _e02);
            dest.putDouble(_do + 24, _e03);
            dest.putDouble(_do + 32, _e10);
            dest.putDouble(_do + 40, _e11);
            dest.putDouble(_do + 48, _e12);
            dest.putDouble(_do + 56, _e13);
            dest.putDouble(_do + 64, _e20);
            dest.putDouble(_do + 72, _e21);
            dest.putDouble(_do + 80, _e22);
            dest.putDouble(_do + 88, _e23);
        }
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_fmaUnsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset, int count) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.composeTRSMul_fmaUnsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase, count);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_mulAddUnsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset, int count) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.composeTRSMul_mulAddUnsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase, count);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_fmaApi(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _translationo = translationOffset + _i * 24;
            int _rotationo = rotationOffset + _i * 32;
            int _scaleo = scaleOffset + _i * 24;
            int _mo = mOffset + _i * 96;
            int _do = destOffset + _i * 96;
            double _tx = translation.getDouble(_translationo), _ty = translation.getDouble(_translationo + 8), _tz = translation.getDouble(_translationo + 16);
            double _sx = scale.getDouble(_scaleo), _sy = scale.getDouble(_scaleo + 8), _sz = scale.getDouble(_scaleo + 16);
            double _qx = rotation.getDouble(_rotationo), _qy = rotation.getDouble(_rotationo + 8), _qz = rotation.getDouble(_rotationo + 16), _qw = rotation.getDouble(_rotationo + 24);
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = m.getDouble(_mo), _m01 = m.getDouble(_mo + 8), _m02 = m.getDouble(_mo + 16), _m03 = m.getDouble(_mo + 24);
            double _m10 = m.getDouble(_mo + 32), _m11 = m.getDouble(_mo + 40), _m12 = m.getDouble(_mo + 48), _m13 = m.getDouble(_mo + 56);
            double _m20 = m.getDouble(_mo + 64), _m21 = m.getDouble(_mo + 72), _m22 = m.getDouble(_mo + 80), _m23 = m.getDouble(_mo + 88);
            double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest.putDouble(_do, _e00);
            dest.putDouble(_do + 8, _e01);
            dest.putDouble(_do + 16, _e02);
            dest.putDouble(_do + 24, _e03);
            dest.putDouble(_do + 32, _e10);
            dest.putDouble(_do + 40, _e11);
            dest.putDouble(_do + 48, _e12);
            dest.putDouble(_do + 56, _e13);
            dest.putDouble(_do + 64, _e20);
            dest.putDouble(_do + 72, _e21);
            dest.putDouble(_do + 80, _e22);
            dest.putDouble(_do + 88, _e23);
        }
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_mulAddApi(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _translationo = translationOffset + _i * 24;
            int _rotationo = rotationOffset + _i * 32;
            int _scaleo = scaleOffset + _i * 24;
            int _mo = mOffset + _i * 96;
            int _do = destOffset + _i * 96;
            double _tx = translation.getDouble(_translationo), _ty = translation.getDouble(_translationo + 8), _tz = translation.getDouble(_translationo + 16);
            double _sx = scale.getDouble(_scaleo), _sy = scale.getDouble(_scaleo + 8), _sz = scale.getDouble(_scaleo + 16);
            double _qx = rotation.getDouble(_rotationo), _qy = rotation.getDouble(_rotationo + 8), _qz = rotation.getDouble(_rotationo + 16), _qw = rotation.getDouble(_rotationo + 24);
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = m.getDouble(_mo), _m01 = m.getDouble(_mo + 8), _m02 = m.getDouble(_mo + 16), _m03 = m.getDouble(_mo + 24);
            double _m10 = m.getDouble(_mo + 32), _m11 = m.getDouble(_mo + 40), _m12 = m.getDouble(_mo + 48), _m13 = m.getDouble(_mo + 56);
            double _m20 = m.getDouble(_mo + 64), _m21 = m.getDouble(_mo + 72), _m22 = m.getDouble(_mo + 80), _m23 = m.getDouble(_mo + 88);
            double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + _tx;
            double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + _ty;
            double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + _tz;
            dest.putDouble(_do, _e00);
            dest.putDouble(_do + 8, _e01);
            dest.putDouble(_do + 16, _e02);
            dest.putDouble(_do + 24, _e03);
            dest.putDouble(_do + 32, _e10);
            dest.putDouble(_do + 40, _e11);
            dest.putDouble(_do + 48, _e12);
            dest.putDouble(_do + 56, _e13);
            dest.putDouble(_do + 64, _e20);
            dest.putDouble(_do + 72, _e21);
            dest.putDouble(_do + 80, _e22);
            dest.putDouble(_do + 88, _e23);
        }
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMulPadded_fmaUnsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.composeTRSMulPadded_fmaUnsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMulPadded_mulAddUnsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3x4OpsKernelsAddress.composeTRSMulPadded_mulAddUnsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMulPadded_fmaApi(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset) {
        double _qx = rotation.getDouble(rotationOffset), _qy = rotation.getDouble(rotationOffset + 8), _qz = rotation.getDouble(rotationOffset + 16), _qw = rotation.getDouble(rotationOffset + 24);
        double _tx = translation.getDouble(translationOffset), _ty = translation.getDouble(translationOffset + 8), _tz = translation.getDouble(translationOffset + 16);
        double _sx = scale.getDouble(scaleOffset), _sy = scale.getDouble(scaleOffset + 8), _sz = scale.getDouble(scaleOffset + 16);
        double _m00 = m.getDouble(mOffset), _m01 = m.getDouble(mOffset + 8), _m02 = m.getDouble(mOffset + 16), _m03 = m.getDouble(mOffset + 24);
        double _m10 = m.getDouble(mOffset + 32), _m11 = m.getDouble(mOffset + 40), _m12 = m.getDouble(mOffset + 48), _m13 = m.getDouble(mOffset + 56);
        double _m20 = m.getDouble(mOffset + 64), _m21 = m.getDouble(mOffset + 72), _m22 = m.getDouble(mOffset + 80), _m23 = m.getDouble(mOffset + 88);
        return composeTRSMulPadded_fmaApi_s8af8ac81_1(dest, destOffset, _qx, _qy, _qz, _qw, _tx, _ty, _tz, _sx, _sy, _sz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23);
    }

    /** Piece 2 of {@code composeTRSMulPadded_fmaApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSMulPadded_fmaApi_s8af8ac81_1(java.nio.ByteBuffer dest, int destOffset, double _qx, double _qy, double _qz, double _qw, double _tx, double _ty, double _tz, double _sx, double _sy, double _sz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23) {
        double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        return composeTRSMulPadded_fmaApi_s8af8ac81_2(dest, destOffset, _tx, _ty, _tz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t00, _t01, _t02, _t10, _t11, _t12, _t20, _t21, _t22);
    }

    /** Piece 3 of {@code composeTRSMulPadded_fmaApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSMulPadded_fmaApi_s8af8ac81_2(java.nio.ByteBuffer dest, int destOffset, double _tx, double _ty, double _tz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t00, double _t01, double _t02, double _t10, double _t11, double _t12, double _t20, double _t21, double _t22) {
        double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
        double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
        double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
        dest.putDouble(destOffset, _e00);
        dest.putDouble(destOffset + 8, _e01);
        return composeTRSMulPadded_fmaApi_s8af8ac81_3(dest, destOffset, _e02, _e03, _e10, _e11, _e12, _e13, _e20, _e21, _e22, _e23);
    }

    /** Piece 4 of {@code composeTRSMulPadded_fmaApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSMulPadded_fmaApi_s8af8ac81_3(java.nio.ByteBuffer dest, int destOffset, double _e02, double _e03, double _e10, double _e11, double _e12, double _e13, double _e20, double _e21, double _e22, double _e23) {
        dest.putDouble(destOffset + 16, _e02);
        dest.putDouble(destOffset + 24, _e03);
        dest.putDouble(destOffset + 32, _e10);
        dest.putDouble(destOffset + 40, _e11);
        dest.putDouble(destOffset + 48, _e12);
        dest.putDouble(destOffset + 56, _e13);
        dest.putDouble(destOffset + 64, _e20);
        dest.putDouble(destOffset + 72, _e21);
        dest.putDouble(destOffset + 80, _e22);
        dest.putDouble(destOffset + 88, _e23);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMulPadded_mulAddApi(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset) {
        double _qx = rotation.getDouble(rotationOffset), _qy = rotation.getDouble(rotationOffset + 8), _qz = rotation.getDouble(rotationOffset + 16), _qw = rotation.getDouble(rotationOffset + 24);
        double _tx = translation.getDouble(translationOffset), _ty = translation.getDouble(translationOffset + 8), _tz = translation.getDouble(translationOffset + 16);
        double _sx = scale.getDouble(scaleOffset), _sy = scale.getDouble(scaleOffset + 8), _sz = scale.getDouble(scaleOffset + 16);
        double _m00 = m.getDouble(mOffset), _m01 = m.getDouble(mOffset + 8), _m02 = m.getDouble(mOffset + 16), _m03 = m.getDouble(mOffset + 24);
        double _m10 = m.getDouble(mOffset + 32), _m11 = m.getDouble(mOffset + 40), _m12 = m.getDouble(mOffset + 48), _m13 = m.getDouble(mOffset + 56);
        double _m20 = m.getDouble(mOffset + 64), _m21 = m.getDouble(mOffset + 72), _m22 = m.getDouble(mOffset + 80), _m23 = m.getDouble(mOffset + 88);
        return composeTRSMulPadded_mulAddApi_s8831785a_1(dest, destOffset, _qx, _qy, _qz, _qw, _tx, _ty, _tz, _sx, _sy, _sz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23);
    }

    /** Piece 2 of {@code composeTRSMulPadded_mulAddApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSMulPadded_mulAddApi_s8831785a_1(java.nio.ByteBuffer dest, int destOffset, double _qx, double _qy, double _qz, double _qw, double _tx, double _ty, double _tz, double _sx, double _sy, double _sz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23) {
        double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        return composeTRSMulPadded_mulAddApi_s8831785a_2(dest, destOffset, _tx, _ty, _tz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t00, _t01, _t02, _t10, _t11, _t12, _t20, _t21, _t22);
    }

    /** Piece 3 of {@code composeTRSMulPadded_mulAddApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSMulPadded_mulAddApi_s8831785a_2(java.nio.ByteBuffer dest, int destOffset, double _tx, double _ty, double _tz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t00, double _t01, double _t02, double _t10, double _t11, double _t12, double _t20, double _t21, double _t22) {
        double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + _tx;
        double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + _ty;
        double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + _tz;
        dest.putDouble(destOffset, _e00);
        dest.putDouble(destOffset + 8, _e01);
        dest.putDouble(destOffset + 16, _e02);
        dest.putDouble(destOffset + 24, _e03);
        dest.putDouble(destOffset + 32, _e10);
        return composeTRSMulPadded_mulAddApi_s8831785a_3(dest, destOffset, _e11, _e12, _e13, _e20, _e21, _e22, _e23);
    }

    /** Piece 4 of {@code composeTRSMulPadded_mulAddApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer composeTRSMulPadded_mulAddApi_s8831785a_3(java.nio.ByteBuffer dest, int destOffset, double _e11, double _e12, double _e13, double _e20, double _e21, double _e22, double _e23) {
        dest.putDouble(destOffset + 40, _e11);
        dest.putDouble(destOffset + 48, _e12);
        dest.putDouble(destOffset + 56, _e13);
        dest.putDouble(destOffset + 64, _e20);
        dest.putDouble(destOffset + 72, _e21);
        dest.putDouble(destOffset + 80, _e22);
        dest.putDouble(destOffset + 88, _e23);
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
