// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3x4Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float3x4Ops} and its sibling kernel units. Not public API.
 */
public final class Float3x4OpsKernelsByteBuffer {
    private Float3x4OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer getColumn_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.nio.ByteBuffer getColumn_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
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
        dest.putFloat(destOffset + 0, _idxSw0);
        dest.putFloat(destOffset + 4, _idxSw1);
        dest.putFloat(destOffset + 8, _idxSw2);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getEulerAnglesXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t1 = Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 < Math.fma(_self02, _self02, _t1) * 1.0E-7f) {
            dest.putFloat(destOffset + 0, (float) Math.atan2(_self21, _self11));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset + 0, (float) Math.atan2(-_self12, _self22));
            dest.putFloat(destOffset + 8, (float) Math.atan2(-_self01, _self00));
        }
        dest.putFloat(destOffset + 4, (float) Math.atan2(_self02, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getEulerAnglesXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t1 = Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 < Math.fma(_self01, _self01, _t1) * 1.0E-7f) {
            dest.putFloat(destOffset + 0, (float) Math.atan2(-_self12, _self22));
            dest.putFloat(destOffset + 4, 0.0f);
        } else {
            dest.putFloat(destOffset + 0, (float) Math.atan2(_self21, _self11));
            dest.putFloat(destOffset + 4, (float) Math.atan2(_self02, _self00));
        }
        dest.putFloat(destOffset + 8, (float) Math.atan2(-_self01, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getEulerAnglesYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t1 = Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 < Math.fma(_self12, _self12, _t1) * 1.0E-7f) {
            dest.putFloat(destOffset + 4, (float) Math.atan2(-_self20, _self00));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset + 4, (float) Math.atan2(_self02, _self22));
            dest.putFloat(destOffset + 8, (float) Math.atan2(_self10, _self11));
        }
        dest.putFloat(destOffset + 0, (float) Math.atan2(-_self12, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getEulerAnglesYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t1 = Math.fma(_self11, _self11, _self12 * _self12);
        if (_t1 < Math.fma(_self10, _self10, _t1) * 1.0E-7f) {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 4, (float) Math.atan2(_self02, _self22));
        } else {
            dest.putFloat(destOffset + 0, (float) Math.atan2(-_self12, _self11));
            dest.putFloat(destOffset + 4, (float) Math.atan2(-_self20, _self00));
        }
        dest.putFloat(destOffset + 8, (float) Math.atan2(_self10, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getEulerAnglesZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t1 = Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 < Math.fma(_self21, _self21, _t1) * 1.0E-7f) {
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, (float) Math.atan2(_self10, _self00));
        } else {
            dest.putFloat(destOffset + 4, (float) Math.atan2(-_self20, _self22));
            dest.putFloat(destOffset + 8, (float) Math.atan2(-_self01, _self11));
        }
        dest.putFloat(destOffset + 0, (float) Math.atan2(_self21, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getEulerAnglesZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t1 = Math.fma(_self21, _self21, _self22 * _self22);
        if (_t1 < Math.fma(_self20, _self20, _t1) * 1.0E-7f) {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 8, (float) Math.atan2(-_self01, _self11));
        } else {
            dest.putFloat(destOffset + 0, (float) Math.atan2(_self21, _self22));
            dest.putFloat(destOffset + 8, (float) Math.atan2(_self10, _self00));
        }
        dest.putFloat(destOffset + 4, (float) Math.atan2(-_self20, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.ByteBuffer getNormalizedRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getNormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer getNormalizedRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t6 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        float _t7 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        float _t8 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t9 = (1.0f / (float) Math.sqrt(_t6));
        float _t10 = (1.0f / (float) Math.sqrt(_t7));
        float _t11 = (1.0f / (float) Math.sqrt(_t8));
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
        float _t53 = _t50 + _t21;
        float _t55 = _t51 + _t24;
        float _t56 = _t24 - _t51;
        float _t57 = _t50 - _t21;
        float _t58 = _t52 + _t26;
        float _t62 = 1.0f + _t58;
        float _t63 = 1.0f + (_t49 - (_t23 + _t26));
        float _t64 = 1.0f + (_t23 - (_t49 + _t26));
        float _t65 = 1.0f + (_t26 - _t52);
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t62));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t64));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t65));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t63));
        if (_t58 > 0.0f) {
            dest.putFloat(destOffset + 0, _sp0 * _t36);
            dest.putFloat(destOffset + 4, _sp0 * _t56);
            dest.putFloat(destOffset + 8, _sp0 * _t57);
            dest.putFloat(destOffset + 12, 0.5f * (float) Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dest.putFloat(destOffset + 0, 0.5f * (float) Math.sqrt(_t63));
                dest.putFloat(destOffset + 4, _sp3 * _t53);
                dest.putFloat(destOffset + 8, _sp3 * _t55);
                dest.putFloat(destOffset + 12, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.putFloat(destOffset + 0, _sp1 * _t53);
                    dest.putFloat(destOffset + 4, 0.5f * (float) Math.sqrt(_t64));
                    dest.putFloat(destOffset + 8, _sp1 * _t39);
                    dest.putFloat(destOffset + 12, _sp1 * _t56);
                } else {
                    dest.putFloat(destOffset + 0, _sp2 * _t55);
                    dest.putFloat(destOffset + 4, _sp2 * _t39);
                    dest.putFloat(destOffset + 8, 0.5f * (float) Math.sqrt(_t65));
                    dest.putFloat(destOffset + 12, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer getRow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.nio.ByteBuffer getRow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
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
        dest.putFloat(destOffset + 0, _idxSw0);
        dest.putFloat(destOffset + 4, _idxSw1);
        dest.putFloat(destOffset + 8, _idxSw2);
        dest.putFloat(destOffset + 12, _idxSw3);
        return dest;
    }

    public static java.nio.ByteBuffer getScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer getScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        dest.putFloat(destOffset + 0, (float) Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        dest.putFloat(destOffset + 4, (float) Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        dest.putFloat(destOffset + 8, (float) Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getTranslation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self03 = src.getFloat(srcOffset + 12);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self23 = src.getFloat(srcOffset + 44);
        dest.putFloat(destOffset + 0, _self03);
        dest.putFloat(destOffset + 4, _self13);
        dest.putFloat(destOffset + 8, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer getUnnormalizedRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.getUnnormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer getUnnormalizedRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
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
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t14));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t16));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t17));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t15));
        if (_t10 > 0.0f) {
            dest.putFloat(destOffset + 0, _sp0 * _t1);
            dest.putFloat(destOffset + 4, _sp0 * _t7);
            dest.putFloat(destOffset + 8, _sp0 * _t9);
            dest.putFloat(destOffset + 12, 0.5f * (float) Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                dest.putFloat(destOffset + 0, 0.5f * (float) Math.sqrt(_t15));
                dest.putFloat(destOffset + 4, _sp3 * _t4);
                dest.putFloat(destOffset + 8, _sp3 * _t6);
                dest.putFloat(destOffset + 12, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    dest.putFloat(destOffset + 0, _sp1 * _t4);
                    dest.putFloat(destOffset + 4, 0.5f * (float) Math.sqrt(_t16));
                    dest.putFloat(destOffset + 8, _sp1 * _t8);
                    dest.putFloat(destOffset + 12, _sp1 * _t7);
                } else {
                    dest.putFloat(destOffset + 0, _sp2 * _t6);
                    dest.putFloat(destOffset + 4, _sp2 * _t8);
                    dest.putFloat(destOffset + 8, 0.5f * (float) Math.sqrt(_t17));
                    dest.putFloat(destOffset + 12, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.invNegativeX_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.putFloat(destOffset + 0, -(_t6 * _t13));
        dest.putFloat(destOffset + 4, -(_t8 * _t13));
        dest.putFloat(destOffset + 8, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.invNegativeX_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsByteBuffer.invNegativeX_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invNegativeX_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNegativeX_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
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
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest.putFloat(destOffset + 0, -(_t21 * _t26));
            dest.putFloat(destOffset + 4, -(_t22 * _t26));
            dest.putFloat(destOffset + 8, -(_t20 * _t26));
        } else {
            dest.putFloat(destOffset + 0, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        float _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        float _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.invNegativeY_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.putFloat(destOffset + 0, -(_t6 * _t13));
        dest.putFloat(destOffset + 4, -(_t8 * _t13));
        dest.putFloat(destOffset + 8, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.invNegativeY_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsByteBuffer.invNegativeY_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invNegativeY_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNegativeY_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
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
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest.putFloat(destOffset + 0, -(_t22 * _t26));
            dest.putFloat(destOffset + 4, -(_t21 * _t26));
            dest.putFloat(destOffset + 8, -(_t20 * _t26));
        } else {
            dest.putFloat(destOffset + 0, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        float _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        float _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.invNegativeZ_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.putFloat(destOffset + 0, -(_t6 * _t13));
        dest.putFloat(destOffset + 4, -(_t8 * _t13));
        dest.putFloat(destOffset + 8, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.invNegativeZ_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsByteBuffer.invNegativeZ_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invNegativeZ_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNegativeZ_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
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
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest.putFloat(destOffset + 0, -(_t21 * _t26));
            dest.putFloat(destOffset + 4, -(_t22 * _t26));
            dest.putFloat(destOffset + 8, -(_t20 * _t26));
        } else {
            dest.putFloat(destOffset + 0, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNormalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset + 0, -_self00);
        dest.putFloat(destOffset + 4, -_self01);
        dest.putFloat(destOffset + 8, -_self02);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        dest.putFloat(destOffset + 0, -_self10);
        dest.putFloat(destOffset + 4, -_self11);
        dest.putFloat(destOffset + 8, -_self12);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        dest.putFloat(destOffset + 0, -_self20);
        dest.putFloat(destOffset + 4, -_self21);
        dest.putFloat(destOffset + 8, -_self22);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self01);
        dest.putFloat(destOffset + 8, _self02);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        dest.putFloat(destOffset + 0, _self10);
        dest.putFloat(destOffset + 4, _self11);
        dest.putFloat(destOffset + 8, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        dest.putFloat(destOffset + 0, _self20);
        dest.putFloat(destOffset + 4, _self21);
        dest.putFloat(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.invPositiveX_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.putFloat(destOffset + 0, _t6 * _t13);
        dest.putFloat(destOffset + 4, _t8 * _t13);
        dest.putFloat(destOffset + 8, _t7 * _t13);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.invPositiveX_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsByteBuffer.invPositiveX_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invPositiveX_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invPositiveX_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
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
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest.putFloat(destOffset + 0, _t21 * _t26);
            dest.putFloat(destOffset + 4, _t22 * _t26);
            dest.putFloat(destOffset + 8, _t20 * _t26);
        } else {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        float _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        float _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.invPositiveY_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.putFloat(destOffset + 0, _t6 * _t13);
        dest.putFloat(destOffset + 4, _t8 * _t13);
        dest.putFloat(destOffset + 8, _t7 * _t13);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.invPositiveY_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsByteBuffer.invPositiveY_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invPositiveY_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invPositiveY_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
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
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest.putFloat(destOffset + 0, _t22 * _t26);
            dest.putFloat(destOffset + 4, _t21 * _t26);
            dest.putFloat(destOffset + 8, _t20 * _t26);
        } else {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        float _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        float _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.invPositiveZ_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.putFloat(destOffset + 0, _t6 * _t13);
        dest.putFloat(destOffset + 4, _t8 * _t13);
        dest.putFloat(destOffset + 8, _t7 * _t13);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.invPositiveZ_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsByteBuffer.invPositiveZ_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invPositiveZ_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invPositiveZ_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
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
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest.putFloat(destOffset + 0, _t21 * _t26);
            dest.putFloat(destOffset + 4, _t22 * _t26);
            dest.putFloat(destOffset + 8, _t20 * _t26);
        } else {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self20 = src.getFloat(srcOffset + 32);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.putFloat(destOffset + 0, -(_self00 * _t3));
            dest.putFloat(destOffset + 4, -(_self10 * _t3));
            dest.putFloat(destOffset + 8, -(_self20 * _t3));
        } else {
            dest.putFloat(destOffset + 0, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.negativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self01 = src.getFloat(srcOffset + 4);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self21 = src.getFloat(srcOffset + 36);
        float _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.putFloat(destOffset + 0, -(_self01 * _t3));
            dest.putFloat(destOffset + 4, -(_self11 * _t3));
            dest.putFloat(destOffset + 8, -(_self21 * _t3));
        } else {
            dest.putFloat(destOffset + 0, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.negativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self02 = src.getFloat(srcOffset + 8);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.putFloat(destOffset + 0, -(_self02 * _t3));
            dest.putFloat(destOffset + 4, -(_self12 * _t3));
            dest.putFloat(destOffset + 8, -(_self22 * _t3));
        } else {
            dest.putFloat(destOffset + 0, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.normalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self20 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset + 0, -_self00);
        dest.putFloat(destOffset + 4, -_self10);
        dest.putFloat(destOffset + 8, -_self20);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self01 = src.getFloat(srcOffset + 4);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self21 = src.getFloat(srcOffset + 36);
        dest.putFloat(destOffset + 0, -_self01);
        dest.putFloat(destOffset + 4, -_self11);
        dest.putFloat(destOffset + 8, -_self21);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self02 = src.getFloat(srcOffset + 8);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self22 = src.getFloat(srcOffset + 40);
        dest.putFloat(destOffset + 0, -_self02);
        dest.putFloat(destOffset + 4, -_self12);
        dest.putFloat(destOffset + 8, -_self22);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self20 = src.getFloat(srcOffset + 32);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self01 = src.getFloat(srcOffset + 4);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self21 = src.getFloat(srcOffset + 36);
        dest.putFloat(destOffset + 0, _self01);
        dest.putFloat(destOffset + 4, _self11);
        dest.putFloat(destOffset + 8, _self21);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self02 = src.getFloat(srcOffset + 8);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self22 = src.getFloat(srcOffset + 40);
        dest.putFloat(destOffset + 0, _self02);
        dest.putFloat(destOffset + 4, _self12);
        dest.putFloat(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer origin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.origin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer origin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        dest.putFloat(destOffset + 0, -Math.fma(_self20, _self23, Math.fma(_self00, _self03, _self10 * _self13)));
        dest.putFloat(destOffset + 4, -Math.fma(_self21, _self23, Math.fma(_self01, _self03, _self11 * _self13)));
        dest.putFloat(destOffset + 8, -Math.fma(_self22, _self23, Math.fma(_self02, _self03, _self12 * _self13)));
        return dest;
    }

    public static java.nio.ByteBuffer positiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self20 = src.getFloat(srcOffset + 32);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.putFloat(destOffset + 0, _self00 * _t3);
            dest.putFloat(destOffset + 4, _self10 * _t3);
            dest.putFloat(destOffset + 8, _self20 * _t3);
        } else {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer positiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.positiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self01 = src.getFloat(srcOffset + 4);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self21 = src.getFloat(srcOffset + 36);
        float _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.putFloat(destOffset + 0, _self01 * _t3);
            dest.putFloat(destOffset + 4, _self11 * _t3);
            dest.putFloat(destOffset + 8, _self21 * _t3);
        } else {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer positiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.positiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self02 = src.getFloat(srcOffset + 8);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.putFloat(destOffset + 0, _self02 * _t3);
            dest.putFloat(destOffset + 4, _self12 * _t3);
            dest.putFloat(destOffset + 8, _self22 * _t3);
        } else {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static float determinant_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3x4OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static float determinant_api(java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        return Math.fma(_self02, Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(_self00, Math.fma(_self11, _self22, -(_self12 * _self21)), -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static float frobeniusNorm_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3x4OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        return (float) Math.sqrt(Math.fma(_self00, _self00, Math.fma(_self01, _self01, _self02 * _self02)) + Math.fma(_self03, _self03, Math.fma(_self10, _self10, _self11 * _self11)) + (Math.fma(_self12, _self12, Math.fma(_self13, _self13, _self20 * _self20)) + Math.fma(_self21, _self21, Math.fma(_self22, _self22, _self23 * _self23))));
    }

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t20 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t21 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t23 = Math.fma(_self02, _self21, -(_self01 * _self22));
        float _t24 = Math.fma(_self01, _self12, -(_self02 * _self11));
        float _t25 = Math.fma(_self12, _self20, -(_self10 * _self22));
        float _t26 = Math.fma(_self00, _self22, -(_self02 * _self20));
        float _t27 = Math.fma(_self02, _self10, -(_self00 * _self12));
        float _t28 = Math.fma(_self01, _self20, -(_self00 * _self21));
        float _t29 = Math.fma(_self00, _self11, -(_self01 * _self10));
        float _t34 = Math.fma(_self02, _t21, Math.fma(_self00, _t20, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(Math.abs(_t34) > 1.1754944E-38f && Math.abs(_t34) < 8.507059E37f)) return Float3x4OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t34_inv = 1.0f / _t34;
        dest.putFloat(destOffset + 0, _t20 * _t34_inv);
        dest.putFloat(destOffset + 4, _t23 * _t34_inv);
        dest.putFloat(destOffset + 8, _t24 * _t34_inv);
        dest.putFloat(destOffset + 12, -(Math.fma(_self23, _t24, Math.fma(_self03, _t20, _self13 * _t23)) * _t34_inv));
        dest.putFloat(destOffset + 16, _t25 * _t34_inv);
        dest.putFloat(destOffset + 20, _t26 * _t34_inv);
        dest.putFloat(destOffset + 24, _t27 * _t34_inv);
        dest.putFloat(destOffset + 28, -(Math.fma(_self23, _t27, Math.fma(_self03, _t25, _self13 * _t26)) * _t34_inv));
        dest.putFloat(destOffset + 32, _t21 * _t34_inv);
        dest.putFloat(destOffset + 36, _t28 * _t34_inv);
        dest.putFloat(destOffset + 40, _t29 * _t34_inv);
        dest.putFloat(destOffset + 44, -(Math.fma(_self23, _t29, Math.fma(_self03, _t21, _self13 * _t28)) * _t34_inv));
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsByteBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invert_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t2 = unitScale(_self00, _self01, _self02);
        float _t15 = _self11 * _t0;
        float _t16 = _self22 * _t1;
        float _t17 = _self12 * _t0;
        float _t18 = _self21 * _t1;
        float _t19 = _self10 * _t0;
        float _t20 = _self20 * _t1;
        float _t21 = _self02 * _t2;
        float _t22 = _self00 * _t2;
        float _t23 = _self01 * _t2;
        float _t24 = _self23 * _t1;
        float _t25 = _self13 * _t0;
        float _t26 = _self03 * _t2;
        float _t47 = Math.fma(_t15, _t16, -(_t17 * _t18));
        float _t48 = Math.fma(_t19, _t18, -(_t15 * _t20));
        float _t50 = Math.fma(_t21, _t18, -(_t23 * _t16));
        float _t51 = Math.fma(_t23, _t17, -(_t21 * _t15));
        float _t52 = Math.fma(_t17, _t20, -(_t19 * _t16));
        float _t53 = Math.fma(_t22, _t16, -(_t21 * _t20));
        float _t54 = Math.fma(_t21, _t19, -(_t22 * _t17));
        float _t55 = Math.fma(_t23, _t20, -(_t22 * _t18));
        float _t56 = Math.fma(_t22, _t15, -(_t23 * _t19));
        float _t60_inv = 1.0f / Math.fma(_t48, _t21, Math.fma(_t47, _t22, -(Math.fma(_t19, _t16, -(_t17 * _t20)) * _t23)));
        float _sp2 = _t1 * _t60_inv;
        float _sp1 = _t0 * _t60_inv;
        float _sp0 = _t2 * _t60_inv;
        dest.putFloat(destOffset + 0, _t47 * _sp0);
        dest.putFloat(destOffset + 4, _t50 * _sp1);
        dest.putFloat(destOffset + 8, _t51 * _sp2);
        dest.putFloat(destOffset + 12, -(Math.fma(_t51, _t24, Math.fma(_t50, _t25, _t47 * _t26)) * _t60_inv));
        dest.putFloat(destOffset + 16, _t52 * _sp0);
        dest.putFloat(destOffset + 20, _t53 * _sp1);
        dest.putFloat(destOffset + 24, _t54 * _sp2);
        dest.putFloat(destOffset + 28, -(Math.fma(_t54, _t24, Math.fma(_t53, _t25, _t52 * _t26)) * _t60_inv));
        dest.putFloat(destOffset + 32, _t48 * _sp0);
        dest.putFloat(destOffset + 36, _t55 * _sp1);
        dest.putFloat(destOffset + 40, _t56 * _sp2);
        dest.putFloat(destOffset + 44, -(Math.fma(_t56, _t24, Math.fma(_t55, _t25, _t48 * _t26)) * _t60_inv));
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x4OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _other00 = other.getFloat(otherOffset + 0);
        float _other01 = other.getFloat(otherOffset + 4);
        float _other02 = other.getFloat(otherOffset + 8);
        float _other03 = other.getFloat(otherOffset + 12);
        float _other10 = other.getFloat(otherOffset + 16);
        float _other11 = other.getFloat(otherOffset + 20);
        float _other12 = other.getFloat(otherOffset + 24);
        float _other13 = other.getFloat(otherOffset + 28);
        float _other20 = other.getFloat(otherOffset + 32);
        float _other21 = other.getFloat(otherOffset + 36);
        float _other22 = other.getFloat(otherOffset + 40);
        float _other23 = other.getFloat(otherOffset + 44);
        float _t24 = Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11));
        float _t25 = Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21));
        float _t26 = Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21));
        float _t27 = Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11));
        float _t28 = Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01));
        float _t29 = Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11));
        float _t30 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        float _t31 = Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01));
        float _t32 = Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01));
        float _t33 = Math.fma(_other03, _self20, Math.fma(_other13, _self21, Math.fma(_other23, _self22, _self23)));
        float _t34 = Math.fma(_other03, _self00, Math.fma(_other13, _self01, Math.fma(_other23, _self02, _self03)));
        float _t35 = Math.fma(_other03, _self10, Math.fma(_other13, _self11, Math.fma(_other23, _self12, _self13)));
        float _t56 = Math.fma(_t24, _t25, -(_t26 * _t27));
        float _t57 = Math.fma(_t29, _t26, -(_t30 * _t24));
        float _t59 = Math.fma(_t26, _t28, -(_t32 * _t25));
        float _t60 = Math.fma(_t32, _t27, -(_t24 * _t28));
        float _t61 = Math.fma(_t30, _t27, -(_t29 * _t25));
        float _t62 = Math.fma(_t31, _t25, -(_t30 * _t28));
        float _t63 = Math.fma(_t29, _t28, -(_t31 * _t27));
        float _t64 = Math.fma(_t30, _t32, -(_t31 * _t26));
        float _t65 = Math.fma(_t31, _t24, -(_t29 * _t32));
        float _t70 = Math.fma(_t28, _t57, Math.fma(_t31, _t56, -(_t32 * Math.fma(_t29, _t25, -(_t30 * _t27)))));
        if (!(Math.abs(_t70) > 1.1754944E-38f && Math.abs(_t70) < 8.507059E37f)) return Float3x4OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t70_inv = 1.0f / _t70;
        dest.putFloat(destOffset + 0, _t56 * _t70_inv);
        dest.putFloat(destOffset + 4, _t59 * _t70_inv);
        dest.putFloat(destOffset + 8, _t60 * _t70_inv);
        dest.putFloat(destOffset + 12, -(Math.fma(_t33, _t60, Math.fma(_t34, _t56, _t35 * _t59)) * _t70_inv));
        dest.putFloat(destOffset + 16, _t61 * _t70_inv);
        dest.putFloat(destOffset + 20, _t62 * _t70_inv);
        dest.putFloat(destOffset + 24, _t63 * _t70_inv);
        dest.putFloat(destOffset + 28, -(Math.fma(_t33, _t63, Math.fma(_t34, _t61, _t35 * _t62)) * _t70_inv));
        dest.putFloat(destOffset + 32, _t57 * _t70_inv);
        dest.putFloat(destOffset + 36, _t64 * _t70_inv);
        dest.putFloat(destOffset + 40, _t65 * _t70_inv);
        dest.putFloat(destOffset + 44, -(Math.fma(_t33, _t65, Math.fma(_t34, _t57, _t35 * _t64)) * _t70_inv));
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3x4OpsKernelsByteBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x4OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _other00 = other.getFloat(otherOffset + 0);
        float _other01 = other.getFloat(otherOffset + 4);
        float _other02 = other.getFloat(otherOffset + 8);
        float _other03 = other.getFloat(otherOffset + 12);
        float _other10 = other.getFloat(otherOffset + 16);
        float _other11 = other.getFloat(otherOffset + 20);
        float _other12 = other.getFloat(otherOffset + 24);
        float _other13 = other.getFloat(otherOffset + 28);
        float _other20 = other.getFloat(otherOffset + 32);
        float _other21 = other.getFloat(otherOffset + 36);
        float _other22 = other.getFloat(otherOffset + 40);
        float _other23 = other.getFloat(otherOffset + 44);
        float _t24 = Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11));
        float _t25 = Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11));
        float _t26 = Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11));
        float _t27 = Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21));
        float _t28 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        float _t29 = Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21));
        float _t30 = Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01));
        float _t31 = Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01));
        float _t32 = Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01));
        float _t36 = unitScale(_t25, _t24, _t26);
        float _t37 = unitScale(_t28, _t29, _t27);
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
        float _t60 = Math.fma(_other03, _self20, Math.fma(_other13, _self21, Math.fma(_other23, _self22, _self23))) * _t37;
        float _t61 = Math.fma(_other03, _self00, Math.fma(_other13, _self01, Math.fma(_other23, _self02, _self03))) * _t38;
        float _t62 = Math.fma(_other03, _self10, Math.fma(_other13, _self11, Math.fma(_other23, _self12, _self13))) * _t36;
        float _t83 = Math.fma(_t48, _t49, -(_t50 * _t51));
        float _t84 = Math.fma(_t52, _t50, -(_t53 * _t48));
        float _t86 = Math.fma(_t50, _t54, -(_t56 * _t49));
        float _t87 = Math.fma(_t56, _t51, -(_t48 * _t54));
        float _t88 = Math.fma(_t53, _t51, -(_t52 * _t49));
        float _t89 = Math.fma(_t55, _t49, -(_t53 * _t54));
        float _t90 = Math.fma(_t52, _t54, -(_t55 * _t51));
        float _t91 = Math.fma(_t53, _t56, -(_t55 * _t50));
        float _t92 = Math.fma(_t55, _t48, -(_t52 * _t56));
        float _t96_inv = 1.0f / Math.fma(_t84, _t54, Math.fma(_t83, _t55, -(Math.fma(_t52, _t49, -(_t53 * _t51)) * _t56)));
        float _sp2 = _t37 * _t96_inv;
        float _sp1 = _t36 * _t96_inv;
        float _sp0 = _t38 * _t96_inv;
        dest.putFloat(destOffset + 0, _t83 * _sp0);
        dest.putFloat(destOffset + 4, _t86 * _sp1);
        dest.putFloat(destOffset + 8, _t87 * _sp2);
        dest.putFloat(destOffset + 12, -(Math.fma(_t87, _t60, Math.fma(_t83, _t61, _t86 * _t62)) * _t96_inv));
        dest.putFloat(destOffset + 16, _t88 * _sp0);
        dest.putFloat(destOffset + 20, _t89 * _sp1);
        dest.putFloat(destOffset + 24, _t90 * _sp2);
        dest.putFloat(destOffset + 28, -(Math.fma(_t90, _t60, Math.fma(_t89, _t62, _t88 * _t61)) * _t96_inv));
        dest.putFloat(destOffset + 32, _t84 * _sp0);
        dest.putFloat(destOffset + 36, _t91 * _sp1);
        dest.putFloat(destOffset + 40, _t92 * _sp2);
        dest.putFloat(destOffset + 44, -(Math.fma(_t92, _t60, Math.fma(_t84, _t61, _t91 * _t62)) * _t96_inv));
        return dest;
    }

    public static java.nio.ByteBuffer transpose_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer transpose_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            float _eother = other.getFloat(otherOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, _eother + _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, scalar * _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, -_eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            float _eother = other.getFloat(otherOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, _eself - _eother);
        }
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x4OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _ev = v.getFloat(vOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, _ev);
        }
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float3x4OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        float _m00 = m.getFloat(mOffset + 0);
        float _m10 = m.getFloat(mOffset + 4);
        float _m20 = m.getFloat(mOffset + 8);
        float _m01 = m.getFloat(mOffset + 12);
        float _m11 = m.getFloat(mOffset + 16);
        float _m21 = m.getFloat(mOffset + 20);
        float _m02 = m.getFloat(mOffset + 24);
        float _m12 = m.getFloat(mOffset + 28);
        float _m22 = m.getFloat(mOffset + 32);
        dest.putFloat(destOffset + 0, _m00);
        dest.putFloat(destOffset + 4, _m01);
        dest.putFloat(destOffset + 8, _m02);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _m10);
        dest.putFloat(destOffset + 20, _m11);
        dest.putFloat(destOffset + 24, _m12);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, _m20);
        dest.putFloat(destOffset + 36, _m21);
        dest.putFloat(destOffset + 40, _m22);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer setMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float3x4OpsKernelsAddress.setMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        float _m00 = m.getFloat(mOffset + 0);
        float _m10 = m.getFloat(mOffset + 4);
        float _m20 = m.getFloat(mOffset + 8);
        float _m01 = m.getFloat(mOffset + 16);
        float _m11 = m.getFloat(mOffset + 20);
        float _m21 = m.getFloat(mOffset + 24);
        float _m02 = m.getFloat(mOffset + 32);
        float _m12 = m.getFloat(mOffset + 36);
        float _m22 = m.getFloat(mOffset + 40);
        float _m03 = m.getFloat(mOffset + 48);
        float _m13 = m.getFloat(mOffset + 52);
        float _m23 = m.getFloat(mOffset + 56);
        dest.putFloat(destOffset + 0, _m00);
        dest.putFloat(destOffset + 4, _m01);
        dest.putFloat(destOffset + 8, _m02);
        dest.putFloat(destOffset + 12, _m03);
        dest.putFloat(destOffset + 16, _m10);
        dest.putFloat(destOffset + 20, _m11);
        dest.putFloat(destOffset + 24, _m12);
        dest.putFloat(destOffset + 28, _m13);
        dest.putFloat(destOffset + 32, _m20);
        dest.putFloat(destOffset + 36, _m21);
        dest.putFloat(destOffset + 40, _m22);
        dest.putFloat(destOffset + 44, _m23);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float tX, float tY, float tZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY, tZ);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float tX, float tY, float tZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self01);
        dest.putFloat(destOffset + 8, _self02);
        dest.putFloat(destOffset + 12, tX);
        dest.putFloat(destOffset + 16, _self10);
        dest.putFloat(destOffset + 20, _self11);
        dest.putFloat(destOffset + 24, _self12);
        dest.putFloat(destOffset + 28, tY);
        dest.putFloat(destOffset + 32, _self20);
        dest.putFloat(destOffset + 36, _self21);
        dest.putFloat(destOffset + 40, _self22);
        dest.putFloat(destOffset + 44, tZ);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Float3x4OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _tx = t.getFloat(tOffset + 0);
        float _ty = t.getFloat(tOffset + 4);
        float _tz = t.getFloat(tOffset + 8);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self01);
        dest.putFloat(destOffset + 8, _self02);
        dest.putFloat(destOffset + 12, _tx);
        dest.putFloat(destOffset + 16, _self10);
        dest.putFloat(destOffset + 20, _self11);
        dest.putFloat(destOffset + 24, _self12);
        dest.putFloat(destOffset + 28, _ty);
        dest.putFloat(destOffset + 32, _self20);
        dest.putFloat(destOffset + 36, _self21);
        dest.putFloat(destOffset + 40, _self22);
        dest.putFloat(destOffset + 44, _tz);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromRigid_unsafe(java.nio.ByteBuffer dest, int destOffset, float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeFromRigid_unsafe(_destBase, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromRigid_api(java.nio.ByteBuffer dest, int destOffset, float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        dest.putFloat(destOffset + 0, Math.fma(-2.0f, Math.fma(rRY, rRY, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * Math.fma(rRX, rRY, -_t1));
        dest.putFloat(destOffset + 8, 2.0f * Math.fma(rRX, rRZ, _t2));
        dest.putFloat(destOffset + 12, rTX);
        dest.putFloat(destOffset + 16, 2.0f * Math.fma(rRX, rRY, _t1));
        dest.putFloat(destOffset + 20, Math.fma(-2.0f, Math.fma(rRX, rRX, _t0), 1.0f));
        dest.putFloat(destOffset + 24, 2.0f * Math.fma(rRY, rRZ, -(rRX * rRW)));
        dest.putFloat(destOffset + 28, rTY);
        dest.putFloat(destOffset + 32, 2.0f * Math.fma(rRX, rRZ, -_t2));
        dest.putFloat(destOffset + 36, 2.0f * Math.fma(rRX, rRW, rRY * rRZ));
        dest.putFloat(destOffset + 40, Math.fma(-2.0f, Math.fma(rRX, rRX, rRY * rRY), 1.0f));
        dest.putFloat(destOffset + 44, rTZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromTransform_unsafe(java.nio.ByteBuffer dest, int destOffset, float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeFromTransform_unsafe(_destBase, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromTransform_api(java.nio.ByteBuffer dest, int destOffset, float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        dest.putFloat(destOffset + 0, Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX));
        dest.putFloat(destOffset + 4, Math.fma(tRX, tRY, -_t4) * _t1);
        dest.putFloat(destOffset + 8, Math.fma(tRX, tRZ, _t5) * _t2);
        dest.putFloat(destOffset + 12, tTX);
        dest.putFloat(destOffset + 16, Math.fma(tRX, tRY, _t4) * _t0);
        dest.putFloat(destOffset + 20, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY));
        dest.putFloat(destOffset + 24, Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        dest.putFloat(destOffset + 28, tTY);
        dest.putFloat(destOffset + 32, Math.fma(tRX, tRZ, -_t5) * _t0);
        dest.putFloat(destOffset + 36, Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        dest.putFloat(destOffset + 40, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        dest.putFloat(destOffset + 44, tTZ);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self21);
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, _self22);
        return dest;
    }

    public static java.nio.ByteBuffer to4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.to4x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self20);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _self01);
        dest.putFloat(destOffset + 20, _self11);
        dest.putFloat(destOffset + 24, _self21);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, _self02);
        dest.putFloat(destOffset + 36, _self12);
        dest.putFloat(destOffset + 40, _self22);
        dest.putFloat(destOffset + 44, 0.0f);
        dest.putFloat(destOffset + 48, _self03);
        dest.putFloat(destOffset + 52, _self13);
        dest.putFloat(destOffset + 56, _self23);
        dest.putFloat(destOffset + 60, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = -_self23;
        float _t2 = 1.0f - _self00;
        float _t4 = _self21 - _self12;
        float _t6 = _self01 + _self10;
        float _t7 = _self02 + _self20;
        float _t8 = _self02 - _self20;
        float _t9 = _self12 + _self21;
        float _t10 = _self10 - _self01;
        float _t14 = _self22 + (_self00 + _self11);
        float _t15 = 1.0f + _t14;
        float _t16 = _self00 + (1.0f - _self11 - _self22);
        float _t17 = _self11 + (_t2 - _self22);
        float _t18 = _self22 + (_t2 - _self11);
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t15));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t17));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t18));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t16));
        float _t63, _t64, _t65, _t66;
        if (_t14 > 0.0f) {
            _t63 = _sp0 * _t4;
            _t64 = _sp0 * _t8;
            _t65 = _sp0 * _t10;
            _t66 = 0.5f * (float) Math.sqrt(_t15);
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                _t63 = 0.5f * (float) Math.sqrt(_t16);
                _t64 = _sp3 * _t6;
                _t65 = _sp3 * _t7;
                _t66 = _sp3 * _t4;
            } else {
                if (_self11 > _self22) {
                    _t63 = _sp1 * _t6;
                    _t64 = 0.5f * (float) Math.sqrt(_t17);
                    _t65 = _sp1 * _t9;
                    _t66 = _sp1 * _t8;
                } else {
                    _t63 = _sp2 * _t7;
                    _t64 = _sp2 * _t9;
                    _t65 = 0.5f * (float) Math.sqrt(_t18);
                    _t66 = _sp2 * _t10;
                }
            }
        }
        dest.putFloat(destOffset + 0, _t63);
        dest.putFloat(destOffset + 4, _t64);
        dest.putFloat(destOffset + 8, _t65);
        dest.putFloat(destOffset + 12, _t66);
        dest.putFloat(destOffset + 16, 0.5f * Math.fma(_t0, _t64, Math.fma(_self03, _t66, _self13 * _t65)));
        dest.putFloat(destOffset + 20, 0.5f * Math.fma(_self23, _t63, Math.fma(_self13, _t66, -(_self03 * _t65))));
        dest.putFloat(destOffset + 24, 0.5f * Math.fma(_self23, _t66, Math.fma(_self03, _t64, -(_self13 * _t63))));
        dest.putFloat(destOffset + 28, 0.5f * Math.fma(_t0, _t65, Math.fma(-_self13, _t64, -(_self03 * _t63))));
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.toRigid_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t15 = (1.0f / (float) Math.sqrt(_ct0));
        float _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t16 = (1.0f / (float) Math.sqrt(_ct1));
        float _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t17 = (1.0f / (float) Math.sqrt(_ct2));
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
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t63));
        float _t65 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t52));
        float _t66 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t52));
        float _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t65));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t66));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t67));
        if (Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t47)) > 0.0f) {
            dest.putFloat(destOffset + 12, _sp0 * _t35);
            dest.putFloat(destOffset + 16, _sp0 * _t56);
            dest.putFloat(destOffset + 20, _sp0 * _t57);
            dest.putFloat(destOffset + 24, 0.5f * (float) Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dest.putFloat(destOffset + 12, 0.5f * (float) Math.sqrt(_t67));
                dest.putFloat(destOffset + 16, _sp3 * _t54);
                dest.putFloat(destOffset + 20, _sp3 * _t55);
                dest.putFloat(destOffset + 24, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.putFloat(destOffset + 12, _sp1 * _t54);
                    dest.putFloat(destOffset + 16, 0.5f * (float) Math.sqrt(_t65));
                    dest.putFloat(destOffset + 20, _sp1 * _t31);
                    dest.putFloat(destOffset + 24, _sp1 * _t56);
                } else {
                    dest.putFloat(destOffset + 12, _sp2 * _t55);
                    dest.putFloat(destOffset + 16, _sp2 * _t31);
                    dest.putFloat(destOffset + 20, 0.5f * (float) Math.sqrt(_t66));
                    dest.putFloat(destOffset + 24, _sp2 * _t57);
                }
            }
        }
        dest.putFloat(destOffset + 0, _self03);
        dest.putFloat(destOffset + 4, _self13);
        dest.putFloat(destOffset + 8, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.toRigid_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsByteBuffer.toRigid_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer toRigid_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.toRigid_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toRigid_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
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
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t31 = (1.0f / (float) Math.sqrt(_t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t27));
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
        if (Math.abs(_t33) < Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (Math.abs(_t37) < Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77, _t89;
        if (Math.abs(_t40) < Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
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
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t208));
        if (_t206 > 0.0f) {
            dest.putFloat(destOffset + 12, _sp0 * _t182);
            dest.putFloat(destOffset + 16, _sp0 * _t201);
            dest.putFloat(destOffset + 20, _sp0 * _t202);
            dest.putFloat(destOffset + 24, 0.5f * (float) Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest.putFloat(destOffset + 12, 0.5f * (float) Math.sqrt(_t208));
                dest.putFloat(destOffset + 16, _sp3 * _t199);
                dest.putFloat(destOffset + 20, _sp3 * _t200);
                dest.putFloat(destOffset + 24, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.putFloat(destOffset + 12, _sp1 * _t199);
                    dest.putFloat(destOffset + 16, 0.5f * (float) Math.sqrt(_t209));
                    dest.putFloat(destOffset + 20, _sp1 * _t184);
                    dest.putFloat(destOffset + 24, _sp1 * _t201);
                } else {
                    dest.putFloat(destOffset + 12, _sp2 * _t200);
                    dest.putFloat(destOffset + 16, _sp2 * _t184);
                    dest.putFloat(destOffset + 20, 0.5f * (float) Math.sqrt(_t210));
                    dest.putFloat(destOffset + 24, _sp2 * _t202);
                }
            }
        }
        dest.putFloat(destOffset + 0, _self03);
        dest.putFloat(destOffset + 4, _self13);
        dest.putFloat(destOffset + 8, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.toTransform_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t14 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsByteBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t15 = (1.0f / (float) Math.sqrt(_t12));
        float _t16 = (1.0f / (float) Math.sqrt(_t13));
        float _t18 = (float) Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _t19 = _self10 * _t17;
        float _t20 = _self22 * _t16;
        float _t21 = _self12 * _t16;
        float _t22 = _self20 * _t17;
        float _t24 = _self21 * _t15;
        float _t25 = _self11 * _t15;
        float _t27 = _self00 * _t17;
        float _t32 = Math.fma(_self12, _t16, _t24);
        float _t36 = Math.fma(_self21, _t15, -_t21);
        float _t37 = Math.max(_t25, _t20);
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
        float _t53 = 1.0f - _t48;
        float _t55 = Math.fma(_self01, _t15, _t49);
        float _t56 = Math.fma(_self02, _t16, _t50);
        float _t57 = Math.fma(_self02, _t16, -_t50);
        float _t58 = Math.fma(-_self01, _t15, _t49);
        float _t63 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48));
        float _t64 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52));
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t64));
        float _t66 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53));
        float _t67 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53));
        float _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t66));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t67));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t68));
        dest.putFloat(destOffset + 0, _self03);
        dest.putFloat(destOffset + 4, _self13);
        dest.putFloat(destOffset + 8, _self23);
        dest.putFloat(destOffset + 12, _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        dest.putFloat(destOffset + 16, _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) Math.sqrt(_t66) : _sp2 * _t32);
        dest.putFloat(destOffset + 20, _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) Math.sqrt(_t67));
        dest.putFloat(destOffset + 24, _t63 > 0.0f ? 0.5f * (float) Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        dest.putFloat(destOffset + 28, _t47 < 0.0f ? -_t18 : _t18);
        dest.putFloat(destOffset + 32, (float) Math.sqrt(_t12));
        dest.putFloat(destOffset + 36, (float) Math.sqrt(_t13));
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.toTransform_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsByteBuffer.toTransform_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer toTransform_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.toTransform_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toTransform_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
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
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t31 = (1.0f / (float) Math.sqrt(_t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t27));
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (Math.abs(_t35) < Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (Math.abs(_t39) < Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (Math.abs(_t42) < Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
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
        float _t186 = Math.max(_t170, _t174);
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
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t211));
        dest.putFloat(destOffset + 0, _self03);
        dest.putFloat(destOffset + 4, _self13);
        dest.putFloat(destOffset + 8, _self23);
        dest.putFloat(destOffset + 12, _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.putFloat(destOffset + 16, _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) Math.sqrt(_t212) : _sp2 * _t187);
        dest.putFloat(destOffset + 20, _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) Math.sqrt(_t213));
        dest.putFloat(destOffset + 24, _t209 > 0.0f ? 0.5f * (float) Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.putFloat(destOffset + 28, _t196 < 0.0f ? -_t56 : _t56);
        dest.putFloat(destOffset + 32, _t27 <= 0.0f ? 0.0f : (float) Math.sqrt(_t27) / _t0);
        dest.putFloat(destOffset + 36, _t28 <= 0.0f ? 0.0f : (float) Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.decomposeRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeRotation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
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
        float _t20 = -Math.fma(_self22, _t7, Math.fma(_self02, _t8, _self12 * _t9));
        float _t21 = Math.fma(_t19, _t7, _self21);
        float _t22 = Math.fma(_t19, _t8, _self01);
        float _t23 = Math.fma(_t19, _t9, _self11);
        float _t29 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
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
        float _t50 = (1.0f / (float) Math.sqrt(_t49));
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
        float _t60 = _t35 - _t54;
        float _t63 = _t35 + _t54;
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
        float _t77 = _t74 + _t34;
        float _t78 = _t74 - _t34;
        float _t80 = _t75 + _t55;
        float _t81 = _t55 - _t75;
        float _t82 = _t76 + _t56;
        float _t86 = 1.0f + _t82;
        float _t87 = 1.0f + (_t73 - (_t36 + _t56));
        float _t88 = 1.0f + (_t36 - (_t73 + _t56));
        float _t89 = 1.0f + (_t56 - _t76);
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t86));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t88));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t89));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t87));
        if (_t82 > 0.0f) {
            dest.putFloat(destOffset + 0, _sp0 * _t60);
            dest.putFloat(destOffset + 4, _sp0 * _t81);
            dest.putFloat(destOffset + 8, _sp0 * _t78);
            dest.putFloat(destOffset + 12, 0.5f * (float) Math.sqrt(_t86));
        } else {
            if (_t73 > Math.max(_t36, _t56)) {
                dest.putFloat(destOffset + 0, 0.5f * (float) Math.sqrt(_t87));
                dest.putFloat(destOffset + 4, _sp3 * _t77);
                dest.putFloat(destOffset + 8, _sp3 * _t80);
                dest.putFloat(destOffset + 12, _sp3 * _t60);
            } else {
                if (_t36 > _t56) {
                    dest.putFloat(destOffset + 0, _sp1 * _t77);
                    dest.putFloat(destOffset + 4, 0.5f * (float) Math.sqrt(_t88));
                    dest.putFloat(destOffset + 8, _sp1 * _t63);
                    dest.putFloat(destOffset + 12, _sp1 * _t81);
                } else {
                    dest.putFloat(destOffset + 0, _sp2 * _t80);
                    dest.putFloat(destOffset + 4, _sp2 * _t63);
                    dest.putFloat(destOffset + 8, 0.5f * (float) Math.sqrt(_t89));
                    dest.putFloat(destOffset + 12, _sp2 * _t78);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer decomposeScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.decomposeScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t4 = (float) Math.sqrt(_t2);
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
        float _t18 = -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10));
        float _t19 = Math.fma(_t17, _t8, _self21);
        float _t20 = Math.fma(_t17, _t9, _self01);
        float _t21 = Math.fma(_t17, _t10, _self11);
        float _t27 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        float _t28 = (1.0f / (float) Math.sqrt(_t27));
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
        float _t48 = (1.0f / (float) Math.sqrt(_t47));
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
        dest.putFloat(destOffset + 0, Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0f ? -_t4 : _t4);
        dest.putFloat(destOffset + 4, (float) Math.sqrt(_t27));
        dest.putFloat(destOffset + 8, (float) Math.sqrt(_t47));
        return dest;
    }

    public static java.nio.ByteBuffer decomposeSkew_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.decomposeSkew_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeSkew_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
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
        float _t16 = -_t14;
        float _t17 = -_t15;
        float _t19 = Math.fma(_t17, _t7, _self21);
        float _t20 = Math.fma(_t17, _t8, _self01);
        float _t21 = Math.fma(_t17, _t9, _self11);
        float _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        float _t27 = (1.0f / (float) Math.sqrt(_t26));
        float _t28 = _t15 * _t27;
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
        float _t48 = (1.0f / (float) Math.sqrt(_t47));
        float _t49 = _t14 * _t48;
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
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0f) {
            dest.putFloat(destOffset + 4, -_t49);
            dest.putFloat(destOffset + 8, -_t28);
        } else {
            dest.putFloat(destOffset + 4, _t49);
            dest.putFloat(destOffset + 8, _t28);
        }
        dest.putFloat(destOffset + 0, _t37 * _t48);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeTRS_unsafe(java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.decomposeTRS_unsafe(_translationBase, _rotationBase, _scaleBase, _srcBase);
        return translation;
    }

    public static java.nio.ByteBuffer decomposeTRS_api(java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t4 = (float) Math.sqrt(_t2);
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
        float _t20 = -Math.fma(_self21, _t8, Math.fma(_self01, _t9, _self11 * _t10));
        float _t21 = -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10));
        float _t22 = Math.fma(_t20, _t8, _self21);
        float _t23 = Math.fma(_t20, _t9, _self01);
        float _t24 = Math.fma(_t20, _t10, _self11);
        float _t30 = Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24));
        float _t31 = (1.0f / (float) Math.sqrt(_t30));
        float _t35, _t36, _t37;
        if (_t30 != 0.0f) {
            _t35 = _t23 * _t31;
            _t36 = _t22 * _t31;
            _t37 = _t24 * _t31;
        } else {
            _t35 = 0.0f;
            _t36 = 0.0f;
            _t37 = 0.0f;
        }
        float _t41 = -Math.fma(Math.fma(_t21, _t8, _self22), _t36, Math.fma(Math.fma(_t21, _t9, _self02), _t35, Math.fma(_t21, _t10, _self12) * _t37));
        float _t45 = Math.fma(_t21, _t8, Math.fma(_t41, _t36, _self22));
        float _t46 = Math.fma(_t21, _t9, Math.fma(_t41, _t35, _self02));
        float _t47 = Math.fma(_t21, _t10, Math.fma(_t41, _t37, _self12));
        float _t50 = Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47));
        float _t51 = (1.0f / (float) Math.sqrt(_t50));
        float _t55, _t56, _t57;
        if (_t50 != 0.0f) {
            _t55 = _t47 * _t51;
            _t56 = _t46 * _t51;
            _t57 = _t45 * _t51;
        } else {
            _t55 = 0.0f;
            _t56 = 0.0f;
            _t57 = 0.0f;
        }
        float _t61 = _t36 - _t55;
        float _t64 = _t36 + _t55;
        float _t73 = Math.fma(Math.fma(_t35, _t55, -(_t37 * _t56)), _t8, Math.fma(Math.fma(_t37, _t57, -(_t36 * _t55)), _t9, Math.fma(_t36, _t56, -(_t35 * _t57)) * _t10));
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
        float _t78 = _t75 + _t35;
        float _t79 = _t75 - _t35;
        float _t81 = _t76 + _t56;
        float _t82 = _t56 - _t76;
        float _t83 = _t77 + _t57;
        float _t87 = 1.0f + _t83;
        float _t88 = 1.0f + (_t74 - (_t37 + _t57));
        float _t89 = 1.0f + (_t37 - (_t74 + _t57));
        float _t90 = 1.0f + (_t57 - _t77);
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t87));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t89));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t90));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t88));
        translation.putFloat(translationOffset + 0, _self03);
        translation.putFloat(translationOffset + 4, _self13);
        translation.putFloat(translationOffset + 8, _self23);
        if (_t83 > 0.0f) {
            rotation.putFloat(rotationOffset + 0, _sp0 * _t61);
            rotation.putFloat(rotationOffset + 4, _sp0 * _t82);
            rotation.putFloat(rotationOffset + 8, _sp0 * _t79);
            rotation.putFloat(rotationOffset + 12, 0.5f * (float) Math.sqrt(_t87));
        } else {
            if (_t74 > Math.max(_t37, _t57)) {
                rotation.putFloat(rotationOffset + 0, 0.5f * (float) Math.sqrt(_t88));
                rotation.putFloat(rotationOffset + 4, _sp3 * _t78);
                rotation.putFloat(rotationOffset + 8, _sp3 * _t81);
                rotation.putFloat(rotationOffset + 12, _sp3 * _t61);
            } else {
                if (_t37 > _t57) {
                    rotation.putFloat(rotationOffset + 0, _sp1 * _t78);
                    rotation.putFloat(rotationOffset + 4, 0.5f * (float) Math.sqrt(_t89));
                    rotation.putFloat(rotationOffset + 8, _sp1 * _t64);
                    rotation.putFloat(rotationOffset + 12, _sp1 * _t82);
                } else {
                    rotation.putFloat(rotationOffset + 0, _sp2 * _t81);
                    rotation.putFloat(rotationOffset + 4, _sp2 * _t64);
                    rotation.putFloat(rotationOffset + 8, 0.5f * (float) Math.sqrt(_t90));
                    rotation.putFloat(rotationOffset + 12, _sp2 * _t79);
                }
            }
        }
        scale.putFloat(scaleOffset + 0, _t73 < 0.0f ? -_t4 : _t4);
        scale.putFloat(scaleOffset + 4, (float) Math.sqrt(_t30));
        scale.putFloat(scaleOffset + 8, (float) Math.sqrt(_t50));
        return translation;
    }

    public static java.nio.ByteBuffer makeIdentity_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            float _eother = other.getFloat(otherOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, Math.fma(t, _eother - _eself, _eself));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float3x4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _right00 = right.getFloat(rightOffset + 0);
        float _right01 = right.getFloat(rightOffset + 4);
        float _right02 = right.getFloat(rightOffset + 8);
        float _right03 = right.getFloat(rightOffset + 12);
        float _right10 = right.getFloat(rightOffset + 16);
        float _right11 = right.getFloat(rightOffset + 20);
        float _right12 = right.getFloat(rightOffset + 24);
        float _right13 = right.getFloat(rightOffset + 28);
        float _right20 = right.getFloat(rightOffset + 32);
        float _right21 = right.getFloat(rightOffset + 36);
        float _right22 = right.getFloat(rightOffset + 40);
        float _right23 = right.getFloat(rightOffset + 44);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            dest.putFloat(destOffset + (_lo + 1) * 4, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            dest.putFloat(destOffset + (_lo + 2) * 4, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            dest.putFloat(destOffset + (_lo + 3) * 4, Math.fma(_right03, _eself0, Math.fma(_right13, _eself1, Math.fma(_right23, _eself2, _eself3))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float3x4OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _right00 = right.getFloat(rightOffset + 0);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, Math.fma(_right00, _eself0, _right10 * _eself1));
            dest.putFloat(destOffset + (_lo + 1) * 4, Math.fma(_right01, _eself0, _right11 * _eself1));
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float3x4OpsKernelsAddress.mulMat2x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _right00 = right.getFloat(rightOffset + 0);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        float _right02 = right.getFloat(rightOffset + 16);
        float _right12 = right.getFloat(rightOffset + 20);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, Math.fma(_right00, _eself0, _right10 * _eself1));
            dest.putFloat(destOffset + (_lo + 1) * 4, Math.fma(_right01, _eself0, _right11 * _eself1));
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, Math.fma(_right02, _eself0, Math.fma(_right12, _eself1, _eself3)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float3x4OpsKernelsAddress.mulMat3x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _right00 = right.getFloat(rightOffset + 0);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right20 = right.getFloat(rightOffset + 8);
        float _right01 = right.getFloat(rightOffset + 12);
        float _right11 = right.getFloat(rightOffset + 16);
        float _right21 = right.getFloat(rightOffset + 20);
        float _right02 = right.getFloat(rightOffset + 24);
        float _right12 = right.getFloat(rightOffset + 28);
        float _right22 = right.getFloat(rightOffset + 32);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            dest.putFloat(destOffset + (_lo + 1) * 4, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            dest.putFloat(destOffset + (_lo + 2) * 4, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float3x4OpsKernelsAddress.mulMat4x4_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        for (int _l = 0; _l < 4; _l++) {
            int _lo = _l * 4;
            float _eright0 = right.getFloat(rightOffset + _lo * 4);
            float _eright1 = right.getFloat(rightOffset + (_lo + 1) * 4);
            float _eright2 = right.getFloat(rightOffset + (_lo + 2) * 4);
            float _eright3 = right.getFloat(rightOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, Math.fma(_eright3, _self03, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01))));
            dest.putFloat(destOffset + (_lo + 1) * 4, Math.fma(_eright3, _self13, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11))));
            dest.putFloat(destOffset + (_lo + 2) * 4, Math.fma(_eright3, _self23, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21))));
            dest.putFloat(destOffset + (_lo + 3) * 4, _eright3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x4OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eother0 = other.getFloat(otherOffset + _lo * 4);
            float _eother1 = other.getFloat(otherOffset + (_lo + 1) * 4);
            float _eother2 = other.getFloat(otherOffset + (_lo + 2) * 4);
            float _eother3 = other.getFloat(otherOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, Math.fma(_eother2, _self20, Math.fma(_eother0, _self00, _eother1 * _self10)));
            dest.putFloat(destOffset + (_lo + 1) * 4, Math.fma(_eother2, _self21, Math.fma(_eother0, _self01, _eother1 * _self11)));
            dest.putFloat(destOffset + (_lo + 2) * 4, Math.fma(_eother2, _self22, Math.fma(_eother0, _self02, _eother1 * _self12)));
            dest.putFloat(destOffset + (_lo + 3) * 4, Math.fma(_eother0, _self03, Math.fma(_eother1, _self13, Math.fma(_eother2, _self23, _eother3))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x4OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _other00 = other.getFloat(otherOffset + 0);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset + 0, Math.fma(_other00, _self00, _other01 * _self10));
        dest.putFloat(destOffset + 4, Math.fma(_other00, _self01, _other01 * _self11));
        dest.putFloat(destOffset + 8, Math.fma(_other00, _self02, _other01 * _self12));
        dest.putFloat(destOffset + 12, Math.fma(_other00, _self03, _other01 * _self13));
        dest.putFloat(destOffset + 16, Math.fma(_other10, _self00, _other11 * _self10));
        dest.putFloat(destOffset + 20, Math.fma(_other10, _self01, _other11 * _self11));
        dest.putFloat(destOffset + 24, Math.fma(_other10, _self02, _other11 * _self12));
        dest.putFloat(destOffset + 28, Math.fma(_other10, _self03, _other11 * _self13));
        dest.putFloat(destOffset + 32, _self20);
        dest.putFloat(destOffset + 36, _self21);
        dest.putFloat(destOffset + 40, _self22);
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x4OpsKernelsAddress.preMulMat2x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _other00 = other.getFloat(otherOffset + 0);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
        dest.putFloat(destOffset + 0, Math.fma(_other00, _self00, _other01 * _self10));
        dest.putFloat(destOffset + 4, Math.fma(_other00, _self01, _other01 * _self11));
        dest.putFloat(destOffset + 8, Math.fma(_other00, _self02, _other01 * _self12));
        dest.putFloat(destOffset + 12, Math.fma(_other00, _self03, Math.fma(_other01, _self13, _other02)));
        dest.putFloat(destOffset + 16, Math.fma(_other10, _self00, _other11 * _self10));
        dest.putFloat(destOffset + 20, Math.fma(_other10, _self01, _other11 * _self11));
        dest.putFloat(destOffset + 24, Math.fma(_other10, _self02, _other11 * _self12));
        dest.putFloat(destOffset + 28, Math.fma(_other10, _self03, Math.fma(_other11, _self13, _other12)));
        dest.putFloat(destOffset + 32, _self20);
        dest.putFloat(destOffset + 36, _self21);
        dest.putFloat(destOffset + 40, _self22);
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x4OpsKernelsAddress.preMulMat3x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _other00 = other.getFloat(otherOffset + 0);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other20 = other.getFloat(otherOffset + 8);
        float _other01 = other.getFloat(otherOffset + 12);
        float _other11 = other.getFloat(otherOffset + 16);
        float _other21 = other.getFloat(otherOffset + 20);
        float _other02 = other.getFloat(otherOffset + 24);
        float _other12 = other.getFloat(otherOffset + 28);
        float _other22 = other.getFloat(otherOffset + 32);
        dest.putFloat(destOffset + 0, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        dest.putFloat(destOffset + 4, Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11)));
        dest.putFloat(destOffset + 8, Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12)));
        dest.putFloat(destOffset + 12, Math.fma(_other02, _self23, Math.fma(_other00, _self03, _other01 * _self13)));
        dest.putFloat(destOffset + 16, Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10)));
        dest.putFloat(destOffset + 20, Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11)));
        dest.putFloat(destOffset + 24, Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12)));
        dest.putFloat(destOffset + 28, Math.fma(_other12, _self23, Math.fma(_other10, _self03, _other11 * _self13)));
        dest.putFloat(destOffset + 32, Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10)));
        dest.putFloat(destOffset + 36, Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11)));
        dest.putFloat(destOffset + 40, Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12)));
        dest.putFloat(destOffset + 44, Math.fma(_other22, _self23, Math.fma(_other20, _self03, _other21 * _self13)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x4OpsKernelsAddress.preMulMat4x4_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _other00 = other.getFloat(otherOffset + 0);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other20 = other.getFloat(otherOffset + 8);
        float _other30 = other.getFloat(otherOffset + 12);
        float _other01 = other.getFloat(otherOffset + 16);
        float _other11 = other.getFloat(otherOffset + 20);
        float _other21 = other.getFloat(otherOffset + 24);
        float _other31 = other.getFloat(otherOffset + 28);
        float _other02 = other.getFloat(otherOffset + 32);
        float _other12 = other.getFloat(otherOffset + 36);
        float _other22 = other.getFloat(otherOffset + 40);
        float _other32 = other.getFloat(otherOffset + 44);
        float _other03 = other.getFloat(otherOffset + 48);
        float _other13 = other.getFloat(otherOffset + 52);
        float _other23 = other.getFloat(otherOffset + 56);
        float _other33 = other.getFloat(otherOffset + 60);
        dest.putFloat(destOffset + 0, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        dest.putFloat(destOffset + 4, Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10)));
        dest.putFloat(destOffset + 8, Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10)));
        dest.putFloat(destOffset + 12, Math.fma(_other32, _self20, Math.fma(_other30, _self00, _other31 * _self10)));
        dest.putFloat(destOffset + 16, Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11)));
        dest.putFloat(destOffset + 20, Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11)));
        dest.putFloat(destOffset + 24, Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11)));
        dest.putFloat(destOffset + 28, Math.fma(_other32, _self21, Math.fma(_other30, _self01, _other31 * _self11)));
        dest.putFloat(destOffset + 32, Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12)));
        dest.putFloat(destOffset + 36, Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12)));
        dest.putFloat(destOffset + 40, Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12)));
        dest.putFloat(destOffset + 44, Math.fma(_other32, _self22, Math.fma(_other30, _self02, _other31 * _self12)));
        dest.putFloat(destOffset + 48, Math.fma(_other00, _self03, Math.fma(_other01, _self13, Math.fma(_other02, _self23, _other03))));
        dest.putFloat(destOffset + 52, Math.fma(_other10, _self03, Math.fma(_other11, _self13, Math.fma(_other12, _self23, _other13))));
        dest.putFloat(destOffset + 56, Math.fma(_other20, _self03, Math.fma(_other21, _self13, Math.fma(_other22, _self23, _other23))));
        dest.putFloat(destOffset + 60, Math.fma(_other30, _self03, Math.fma(_other31, _self13, Math.fma(_other32, _self23, _other33))));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3x4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            float _eother = other.getFloat(otherOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, Math.fma(weight, _eother, _eself));
        }
        return dest;
    }

    public static java.nio.ByteBuffer composeTRS_unsafe(java.nio.ByteBuffer dest, int destOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.composeTRS_unsafe(_destBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRS_api(java.nio.ByteBuffer dest, int destOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _t0 = scaleX + scaleX;
        float _t1 = scaleY + scaleY;
        float _t2 = scaleZ + scaleZ;
        float _t3 = rotationZ * rotationZ;
        float _t4 = rotationZ * rotationW;
        float _t5 = rotationY * rotationW;
        dest.putFloat(destOffset + 0, Math.fma(-Math.fma(rotationY, rotationY, _t3), _t0, scaleX));
        dest.putFloat(destOffset + 4, Math.fma(rotationX, rotationY, -_t4) * _t1);
        dest.putFloat(destOffset + 8, Math.fma(rotationX, rotationZ, _t5) * _t2);
        dest.putFloat(destOffset + 12, translationX);
        dest.putFloat(destOffset + 16, Math.fma(rotationX, rotationY, _t4) * _t0);
        dest.putFloat(destOffset + 20, Math.fma(-Math.fma(rotationX, rotationX, _t3), _t1, scaleY));
        dest.putFloat(destOffset + 24, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t2);
        dest.putFloat(destOffset + 28, translationY);
        dest.putFloat(destOffset + 32, Math.fma(rotationX, rotationZ, -_t5) * _t0);
        dest.putFloat(destOffset + 36, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t1);
        dest.putFloat(destOffset + 40, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t2, scaleZ));
        dest.putFloat(destOffset + 44, translationZ);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRS_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        Float3x4OpsKernelsAddress.composeTRS_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRS_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset) {
        float _translationx = translation.getFloat(translationOffset + 0);
        float _translationy = translation.getFloat(translationOffset + 4);
        float _translationz = translation.getFloat(translationOffset + 8);
        float _rotationx = rotation.getFloat(rotationOffset + 0);
        float _rotationy = rotation.getFloat(rotationOffset + 4);
        float _rotationz = rotation.getFloat(rotationOffset + 8);
        float _rotationw = rotation.getFloat(rotationOffset + 12);
        float _scalex = scale.getFloat(scaleOffset + 0);
        float _scaley = scale.getFloat(scaleOffset + 4);
        float _scalez = scale.getFloat(scaleOffset + 8);
        float _t0 = _scalex + _scalex;
        float _t1 = _scaley + _scaley;
        float _t2 = _scalez + _scalez;
        float _t3 = _rotationz * _rotationz;
        float _t4 = _rotationz * _rotationw;
        float _t5 = _rotationy * _rotationw;
        dest.putFloat(destOffset + 0, Math.fma(-Math.fma(_rotationy, _rotationy, _t3), _t0, _scalex));
        dest.putFloat(destOffset + 4, Math.fma(_rotationx, _rotationy, -_t4) * _t1);
        dest.putFloat(destOffset + 8, Math.fma(_rotationx, _rotationz, _t5) * _t2);
        dest.putFloat(destOffset + 12, _translationx);
        dest.putFloat(destOffset + 16, Math.fma(_rotationx, _rotationy, _t4) * _t0);
        dest.putFloat(destOffset + 20, Math.fma(-Math.fma(_rotationx, _rotationx, _t3), _t1, _scaley));
        dest.putFloat(destOffset + 24, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t2);
        dest.putFloat(destOffset + 28, _translationy);
        dest.putFloat(destOffset + 32, Math.fma(_rotationx, _rotationz, -_t5) * _t0);
        dest.putFloat(destOffset + 36, Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t1);
        dest.putFloat(destOffset + 40, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t2, _scalez));
        dest.putFloat(destOffset + 44, _translationz);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSAround_unsafe(java.nio.ByteBuffer dest, int destOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.composeTRSAround_unsafe(_destBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSAround_api(java.nio.ByteBuffer dest, int destOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ, float pivotX, float pivotY, float pivotZ) {
        float _t0 = -pivotY;
        float _t1 = -pivotZ;
        float _t2 = -pivotX;
        float _t3 = scaleX + scaleX;
        float _t4 = scaleY + scaleY;
        float _t5 = scaleZ + scaleZ;
        float _t6 = rotationZ * rotationZ;
        float _t7 = rotationZ * rotationW;
        float _t8 = rotationY * rotationW;
        float _t15 = Math.fma(rotationY, rotationY, _t6);
        float _t18 = Math.fma(rotationX, rotationX, _t6);
        float _t20 = Math.fma(rotationX, rotationX, rotationY * rotationY);
        float _t24 = Math.fma(rotationX, rotationZ, _t8) * _t5;
        float _t25 = Math.fma(rotationX, rotationY, _t7) * _t3;
        float _t26 = Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t4;
        float _t27 = Math.fma(rotationX, rotationY, -_t7) * _t4;
        float _t28 = Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t5;
        float _t29 = Math.fma(rotationX, rotationZ, -_t8) * _t3;
        dest.putFloat(destOffset + 0, Math.fma(-_t15, _t3, scaleX));
        dest.putFloat(destOffset + 4, _t27);
        dest.putFloat(destOffset + 8, _t24);
        dest.putFloat(destOffset + 12, Math.fma(pivotX, Math.fma(_t15, _t3, 1.0f - scaleX), Math.fma(_t0, _t27, Math.fma(_t1, _t24, translationX))));
        dest.putFloat(destOffset + 16, _t25);
        dest.putFloat(destOffset + 20, Math.fma(-_t18, _t4, scaleY));
        dest.putFloat(destOffset + 24, _t28);
        dest.putFloat(destOffset + 28, Math.fma(pivotY, Math.fma(_t18, _t4, 1.0f - scaleY), Math.fma(_t2, _t25, Math.fma(_t1, _t28, translationY))));
        dest.putFloat(destOffset + 32, _t29);
        dest.putFloat(destOffset + 36, _t26);
        dest.putFloat(destOffset + 40, Math.fma(-_t20, _t5, scaleZ));
        dest.putFloat(destOffset + 44, Math.fma(pivotZ, Math.fma(_t20, _t5, 1.0f - scaleZ), Math.fma(_t2, _t29, Math.fma(_t0, _t26, translationZ))));
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x4OpsKernelsAddress.composeTRSAround_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _translationx = translation.getFloat(translationOffset + 0);
        float _translationy = translation.getFloat(translationOffset + 4);
        float _translationz = translation.getFloat(translationOffset + 8);
        float _rotationx = rotation.getFloat(rotationOffset + 0);
        float _rotationy = rotation.getFloat(rotationOffset + 4);
        float _rotationz = rotation.getFloat(rotationOffset + 8);
        float _rotationw = rotation.getFloat(rotationOffset + 12);
        float _scalex = scale.getFloat(scaleOffset + 0);
        float _scaley = scale.getFloat(scaleOffset + 4);
        float _scalez = scale.getFloat(scaleOffset + 8);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _pivotz = pivot.getFloat(pivotOffset + 8);
        float _t0 = -_pivoty;
        float _t1 = -_pivotz;
        float _t2 = -_pivotx;
        float _t3 = _scalex + _scalex;
        float _t4 = _scaley + _scaley;
        float _t5 = _scalez + _scalez;
        float _t6 = _rotationz * _rotationz;
        float _t7 = _rotationz * _rotationw;
        float _t8 = _rotationy * _rotationw;
        float _t15 = Math.fma(_rotationy, _rotationy, _t6);
        float _t18 = Math.fma(_rotationx, _rotationx, _t6);
        float _t20 = Math.fma(_rotationx, _rotationx, _rotationy * _rotationy);
        float _t24 = Math.fma(_rotationx, _rotationz, _t8) * _t5;
        float _t25 = Math.fma(_rotationx, _rotationy, _t7) * _t3;
        float _t26 = Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t4;
        float _t27 = Math.fma(_rotationx, _rotationy, -_t7) * _t4;
        float _t28 = Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t5;
        float _t29 = Math.fma(_rotationx, _rotationz, -_t8) * _t3;
        dest.putFloat(destOffset + 0, Math.fma(-_t15, _t3, _scalex));
        dest.putFloat(destOffset + 4, _t27);
        dest.putFloat(destOffset + 8, _t24);
        dest.putFloat(destOffset + 12, Math.fma(_pivotx, Math.fma(_t15, _t3, 1.0f - _scalex), Math.fma(_t0, _t27, Math.fma(_t1, _t24, _translationx))));
        dest.putFloat(destOffset + 16, _t25);
        dest.putFloat(destOffset + 20, Math.fma(-_t18, _t4, _scaley));
        dest.putFloat(destOffset + 24, _t28);
        dest.putFloat(destOffset + 28, Math.fma(_pivoty, Math.fma(_t18, _t4, 1.0f - _scaley), Math.fma(_t2, _t25, Math.fma(_t1, _t28, _translationy))));
        dest.putFloat(destOffset + 32, _t29);
        dest.putFloat(destOffset + 36, _t26);
        dest.putFloat(destOffset + 40, Math.fma(-_t20, _t5, _scalez));
        dest.putFloat(destOffset + 44, Math.fma(_pivotz, Math.fma(_t20, _t5, 1.0f - _scalez), Math.fma(_t2, _t29, Math.fma(_t0, _t26, _translationz))));
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float3x4OpsKernelsAddress.composeTRSMul_unsafe(_destBase, _mBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _m00 = m.getFloat(mOffset + 0);
        float _m01 = m.getFloat(mOffset + 4);
        float _m02 = m.getFloat(mOffset + 8);
        float _m03 = m.getFloat(mOffset + 12);
        float _m10 = m.getFloat(mOffset + 16);
        float _m11 = m.getFloat(mOffset + 20);
        float _m12 = m.getFloat(mOffset + 24);
        float _m13 = m.getFloat(mOffset + 28);
        float _m20 = m.getFloat(mOffset + 32);
        float _m21 = m.getFloat(mOffset + 36);
        float _m22 = m.getFloat(mOffset + 40);
        float _m23 = m.getFloat(mOffset + 44);
        float _t0 = scaleZ + scaleZ;
        float _t1 = scaleX + scaleX;
        float _t2 = scaleY + scaleY;
        float _t3 = rotationY * rotationW;
        float _t4 = rotationZ * rotationZ;
        float _t5 = rotationZ * rotationW;
        float _t24 = Math.fma(rotationX, rotationZ, _t3) * _t0;
        float _t25 = Math.fma(rotationX, rotationY, _t5) * _t1;
        float _t26 = Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2;
        float _t27 = Math.fma(rotationX, rotationY, -_t5) * _t2;
        float _t28 = Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0;
        float _t29 = Math.fma(rotationX, rotationZ, -_t3) * _t1;
        float _t30 = Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX);
        float _t31 = Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY);
        float _t32 = Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ);
        dest.putFloat(destOffset + 0, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        dest.putFloat(destOffset + 4, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        dest.putFloat(destOffset + 8, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        dest.putFloat(destOffset + 12, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, translationX))));
        dest.putFloat(destOffset + 16, Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31)));
        dest.putFloat(destOffset + 20, Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31)));
        dest.putFloat(destOffset + 24, Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31)));
        dest.putFloat(destOffset + 28, Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, translationY))));
        dest.putFloat(destOffset + 32, Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26)));
        dest.putFloat(destOffset + 36, Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26)));
        dest.putFloat(destOffset + 40, Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26)));
        dest.putFloat(destOffset + 44, Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, translationZ))));
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float3x4OpsKernelsAddress.composeTRSMul_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset) {
        float _translationx = translation.getFloat(translationOffset + 0);
        float _translationy = translation.getFloat(translationOffset + 4);
        float _translationz = translation.getFloat(translationOffset + 8);
        float _rotationx = rotation.getFloat(rotationOffset + 0);
        float _rotationy = rotation.getFloat(rotationOffset + 4);
        float _rotationz = rotation.getFloat(rotationOffset + 8);
        float _rotationw = rotation.getFloat(rotationOffset + 12);
        float _scalex = scale.getFloat(scaleOffset + 0);
        float _scaley = scale.getFloat(scaleOffset + 4);
        float _scalez = scale.getFloat(scaleOffset + 8);
        float _m00 = m.getFloat(mOffset + 0);
        float _m01 = m.getFloat(mOffset + 4);
        float _m02 = m.getFloat(mOffset + 8);
        float _m03 = m.getFloat(mOffset + 12);
        float _m10 = m.getFloat(mOffset + 16);
        float _m11 = m.getFloat(mOffset + 20);
        float _m12 = m.getFloat(mOffset + 24);
        float _m13 = m.getFloat(mOffset + 28);
        float _m20 = m.getFloat(mOffset + 32);
        float _m21 = m.getFloat(mOffset + 36);
        float _m22 = m.getFloat(mOffset + 40);
        float _m23 = m.getFloat(mOffset + 44);
        float _t0 = _scalez + _scalez;
        float _t1 = _scalex + _scalex;
        float _t2 = _scaley + _scaley;
        float _t3 = _rotationy * _rotationw;
        float _t4 = _rotationz * _rotationz;
        float _t5 = _rotationz * _rotationw;
        float _t24 = Math.fma(_rotationx, _rotationz, _t3) * _t0;
        float _t25 = Math.fma(_rotationx, _rotationy, _t5) * _t1;
        float _t26 = Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2;
        float _t27 = Math.fma(_rotationx, _rotationy, -_t5) * _t2;
        float _t28 = Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0;
        float _t29 = Math.fma(_rotationx, _rotationz, -_t3) * _t1;
        float _t30 = Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex);
        float _t31 = Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley);
        float _t32 = Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez);
        dest.putFloat(destOffset + 0, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        dest.putFloat(destOffset + 4, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        dest.putFloat(destOffset + 8, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        dest.putFloat(destOffset + 12, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, _translationx))));
        dest.putFloat(destOffset + 16, Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31)));
        dest.putFloat(destOffset + 20, Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31)));
        dest.putFloat(destOffset + 24, Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31)));
        dest.putFloat(destOffset + 28, Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, _translationy))));
        dest.putFloat(destOffset + 32, Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26)));
        dest.putFloat(destOffset + 36, Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26)));
        dest.putFloat(destOffset + 40, Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26)));
        dest.putFloat(destOffset + 44, Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, _translationz))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) Math.sqrt(_t4));
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
        float _t33 = (1.0f / (float) Math.sqrt(_t32));
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
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x4OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _dirx = dir.getFloat(dirOffset + 0);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset + 0);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) Math.sqrt(_t4));
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
        float _t33 = (1.0f / (float) Math.sqrt(_t32));
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
        float _t46 = Math.fma(_t39, _t12, -(_t38 * _t13));
        float _t47 = Math.fma(_t37, _t13, -(_t39 * _t11));
        float _t48 = Math.fma(_t38, _t11, -(_t37 * _t12));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.lookAt_lh_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Float3x4OpsKernelsByteBuffer.lookAt_lh_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAt_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.lookAt_lh_unsafe(_destBase, _srcBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
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
        float _t22 = Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16));
        float _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        float _t24 = Math.fma(_t23, _t14, upX);
        float _t25 = Math.fma(_t23, _t16, upY);
        float _t26 = Math.fma(_t23, _t15, upZ);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) Math.sqrt(_t38));
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
        float _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        float _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        float _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        float _t58 = Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45));
        float _t60 = Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        dest.putFloat(destOffset + 12, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        dest.putFloat(destOffset + 28, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        dest.putFloat(destOffset + 44, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.lookAt_rh_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Float3x4OpsKernelsByteBuffer.lookAt_rh_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAt_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.lookAt_rh_unsafe(_destBase, _srcBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = -_self02;
        float _t1 = -_self12;
        float _t2 = -_self22;
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) Math.sqrt(_t12));
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
        float _t25 = Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19));
        float _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        float _t27 = Math.fma(_t26, _t19, upY);
        float _t28 = Math.fma(_t26, _t17, upX);
        float _t29 = Math.fma(_t26, _t18, upZ);
        float _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) Math.sqrt(_t41));
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
        float _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        float _t61 = Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47));
        float _t63 = Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58));
        dest.putFloat(destOffset + 0, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        dest.putFloat(destOffset + 4, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        dest.putFloat(destOffset + 8, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        dest.putFloat(destOffset + 12, Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03))));
        dest.putFloat(destOffset + 16, Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57)));
        dest.putFloat(destOffset + 20, Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58)));
        dest.putFloat(destOffset + 24, Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59)));
        dest.putFloat(destOffset + 28, Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13))));
        dest.putFloat(destOffset + 32, Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57)));
        dest.putFloat(destOffset + 36, Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58)));
        dest.putFloat(destOffset + 40, Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59)));
        dest.putFloat(destOffset + 44, Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.lookAt_lh_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Float3x4OpsKernelsByteBuffer.lookAt_lh_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAt_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x4OpsKernelsAddress.lookAt_lh_unsafe(_destBase, _srcBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _eyex = eye.getFloat(eyeOffset + 0);
        float _eyey = eye.getFloat(eyeOffset + 4);
        float _eyez = eye.getFloat(eyeOffset + 8);
        float _centerx = center.getFloat(centerOffset + 0);
        float _centery = center.getFloat(centerOffset + 4);
        float _centerz = center.getFloat(centerOffset + 8);
        float _upx = up.getFloat(upOffset + 0);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t0 = _centerz - _eyez;
        float _t1 = _centerx - _eyex;
        float _t2 = _centery - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
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
        float _t22 = Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16));
        float _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        float _t24 = Math.fma(_t23, _t14, _upx);
        float _t25 = Math.fma(_t23, _t16, _upy);
        float _t26 = Math.fma(_t23, _t15, _upz);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) Math.sqrt(_t38));
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
        float _t58 = Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45));
        float _t60 = Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        dest.putFloat(destOffset + 12, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        dest.putFloat(destOffset + 28, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        dest.putFloat(destOffset + 44, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.lookAt_rh_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Float3x4OpsKernelsByteBuffer.lookAt_rh_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAt_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x4OpsKernelsAddress.lookAt_rh_unsafe(_destBase, _srcBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAt_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _eyex = eye.getFloat(eyeOffset + 0);
        float _eyey = eye.getFloat(eyeOffset + 4);
        float _eyez = eye.getFloat(eyeOffset + 8);
        float _centerx = center.getFloat(centerOffset + 0);
        float _centery = center.getFloat(centerOffset + 4);
        float _centerz = center.getFloat(centerOffset + 8);
        float _upx = up.getFloat(upOffset + 0);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t0 = -_self02;
        float _t1 = -_self12;
        float _t2 = -_self22;
        float _t3 = _centerz - _eyez;
        float _t4 = _centerx - _eyex;
        float _t5 = _centery - _eyey;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) Math.sqrt(_t12));
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
        float _t25 = Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19));
        float _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        float _t27 = Math.fma(_t26, _t19, _upy);
        float _t28 = Math.fma(_t26, _t17, _upx);
        float _t29 = Math.fma(_t26, _t18, _upz);
        float _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) Math.sqrt(_t41));
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
        float _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        float _t61 = Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47));
        float _t63 = Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58));
        dest.putFloat(destOffset + 0, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        dest.putFloat(destOffset + 4, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        dest.putFloat(destOffset + 8, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        dest.putFloat(destOffset + 12, Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03))));
        dest.putFloat(destOffset + 16, Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57)));
        dest.putFloat(destOffset + 20, Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58)));
        dest.putFloat(destOffset + 24, Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59)));
        dest.putFloat(destOffset + 28, Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13))));
        dest.putFloat(destOffset + 32, Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57)));
        dest.putFloat(destOffset + 36, Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58)));
        dest.putFloat(destOffset + 40, Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59)));
        dest.putFloat(destOffset + 44, Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardCylindrical_unsafe(java.nio.ByteBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeBillboardCylindrical_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardCylindrical_api(java.nio.ByteBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
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
        float _t32 = (1.0f / (float) Math.sqrt(_t31));
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
        float _t45 = Math.fma(upY, _t36, -(upX * _t37));
        float _t46 = Math.fma(upX, _t38, -(upZ * _t36));
        float _t47 = Math.fma(upZ, _t37, -(upY * _t38));
        float _t50 = Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47));
        float _t51 = (1.0f / (float) Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            dest.putFloat(destOffset + 8, _t47 * _t51);
            dest.putFloat(destOffset + 24, _t46 * _t51);
            dest.putFloat(destOffset + 40, _t45 * _t51);
        } else {
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 24, 0.0f);
            dest.putFloat(destOffset + 40, 0.0f);
        }
        dest.putFloat(destOffset + 0, _t36);
        dest.putFloat(destOffset + 4, upX);
        dest.putFloat(destOffset + 12, objPosX);
        dest.putFloat(destOffset + 16, _t37);
        dest.putFloat(destOffset + 20, upY);
        dest.putFloat(destOffset + 28, objPosY);
        dest.putFloat(destOffset + 32, _t38);
        dest.putFloat(destOffset + 36, upZ);
        dest.putFloat(destOffset + 44, objPosZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardCylindrical_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + objPosOffset;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + targetPosOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x4OpsKernelsAddress.makeBillboardCylindrical_unsafe(_destBase, _objPosBase, _targetPosBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardCylindrical_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset, java.nio.ByteBuffer up, int upOffset) {
        float _objPosx = objPos.getFloat(objPosOffset + 0);
        float _objPosy = objPos.getFloat(objPosOffset + 4);
        float _objPosz = objPos.getFloat(objPosOffset + 8);
        float _targetPosx = targetPos.getFloat(targetPosOffset + 0);
        float _targetPosy = targetPos.getFloat(targetPosOffset + 4);
        float _targetPosz = targetPos.getFloat(targetPosOffset + 8);
        float _upx = up.getFloat(upOffset + 0);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t3 = _targetPosz - _objPosz;
        float _t4 = _targetPosx - _objPosx;
        float _t5 = _targetPosy - _objPosy;
        float _t14 = Math.fma(_upz, _t3, Math.fma(_upx, _t4, _upy * _t5));
        float _t15 = Math.fma(-_upy, _t14, _t5);
        float _t16 = Math.fma(-_upx, _t14, _t4);
        float _t17 = Math.fma(-_upz, _t14, _t3);
        float _t26 = Math.fma(_upx, _t15, -(_upy * _t16));
        float _t27 = Math.fma(_upy, _t17, -(_upz * _t15));
        float _t28 = Math.fma(_upz, _t16, -(_upx * _t17));
        float _t31 = Math.fma(_t26, _t26, Math.fma(_t27, _t27, _t28 * _t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t31));
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
        float _t51 = (1.0f / (float) Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            dest.putFloat(destOffset + 8, _t47 * _t51);
            dest.putFloat(destOffset + 24, _t46 * _t51);
            dest.putFloat(destOffset + 40, _t45 * _t51);
        } else {
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 24, 0.0f);
            dest.putFloat(destOffset + 40, 0.0f);
        }
        dest.putFloat(destOffset + 0, _t36);
        dest.putFloat(destOffset + 4, _upx);
        dest.putFloat(destOffset + 12, _objPosx);
        dest.putFloat(destOffset + 16, _t37);
        dest.putFloat(destOffset + 20, _upy);
        dest.putFloat(destOffset + 28, _objPosy);
        dest.putFloat(destOffset + 32, _t38);
        dest.putFloat(destOffset + 36, _upz);
        dest.putFloat(destOffset + 44, _objPosz);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSpherical_unsafe(java.nio.ByteBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeBillboardSpherical_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSpherical_api(java.nio.ByteBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
        float _t0 = targetPosZ - objPosZ;
        float _t1 = targetPosX - objPosX;
        float _t2 = targetPosY - objPosY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
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
        float _t36 = (1.0f / (float) Math.sqrt(_t35));
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
        dest.putFloat(destOffset + 0, _t40);
        dest.putFloat(destOffset + 4, Math.fma(_t41, _t16, -(_t42 * _t14)));
        dest.putFloat(destOffset + 8, _t15);
        dest.putFloat(destOffset + 12, objPosX);
        dest.putFloat(destOffset + 16, _t42);
        dest.putFloat(destOffset + 20, Math.fma(_t40, _t14, -(_t41 * _t15)));
        dest.putFloat(destOffset + 24, _t16);
        dest.putFloat(destOffset + 28, objPosY);
        dest.putFloat(destOffset + 32, _t41);
        dest.putFloat(destOffset + 36, Math.fma(_t42, _t15, -(_t40 * _t16)));
        dest.putFloat(destOffset + 40, _t14);
        dest.putFloat(destOffset + 44, objPosZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSpherical_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + objPosOffset;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + targetPosOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x4OpsKernelsAddress.makeBillboardSpherical_unsafe(_destBase, _objPosBase, _targetPosBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSpherical_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset, java.nio.ByteBuffer up, int upOffset) {
        float _objPosx = objPos.getFloat(objPosOffset + 0);
        float _objPosy = objPos.getFloat(objPosOffset + 4);
        float _objPosz = objPos.getFloat(objPosOffset + 8);
        float _targetPosx = targetPos.getFloat(targetPosOffset + 0);
        float _targetPosy = targetPos.getFloat(targetPosOffset + 4);
        float _targetPosz = targetPos.getFloat(targetPosOffset + 8);
        float _upx = up.getFloat(upOffset + 0);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t0 = _targetPosz - _objPosz;
        float _t1 = _targetPosx - _objPosx;
        float _t2 = _targetPosy - _objPosy;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
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
        float _t30 = Math.fma(_t21, _t16, -(_t22 * _t15));
        float _t31 = Math.fma(_t22, _t14, -(_t23 * _t16));
        float _t32 = Math.fma(_t23, _t15, -(_t21 * _t14));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) Math.sqrt(_t35));
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
        dest.putFloat(destOffset + 0, _t40);
        dest.putFloat(destOffset + 4, Math.fma(_t41, _t16, -(_t42 * _t14)));
        dest.putFloat(destOffset + 8, _t15);
        dest.putFloat(destOffset + 12, _objPosx);
        dest.putFloat(destOffset + 16, _t42);
        dest.putFloat(destOffset + 20, Math.fma(_t40, _t14, -(_t41 * _t15)));
        dest.putFloat(destOffset + 24, _t16);
        dest.putFloat(destOffset + 28, _objPosy);
        dest.putFloat(destOffset + 32, _t41);
        dest.putFloat(destOffset + 36, Math.fma(_t42, _t15, -(_t40 * _t16)));
        dest.putFloat(destOffset + 40, _t14);
        dest.putFloat(destOffset + 44, _objPosz);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSphericalShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeBillboardSphericalShortest_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSphericalShortest_api(java.nio.ByteBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ) {
        float _t0 = targetPosZ - objPosZ;
        float _t1 = targetPosX - objPosX;
        float _t2 = targetPosY - objPosY;
        float _t3 = _t2 + _t2;
        float _t6 = Math.fma(_t1, _t1, _t2 * _t2);
        float _t9 = (float) Math.sqrt(Math.max(Math.fma(_t0, _t0, _t6), 4.7019774E-38f));
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
        dest.putFloat(destOffset + 0, _t32);
        dest.putFloat(destOffset + 4, _t29);
        dest.putFloat(destOffset + 8, _t30);
        dest.putFloat(destOffset + 12, objPosX);
        dest.putFloat(destOffset + 16, _t29);
        dest.putFloat(destOffset + 20, 1.0f - _t26);
        dest.putFloat(destOffset + 24, _t27);
        dest.putFloat(destOffset + 28, objPosY);
        dest.putFloat(destOffset + 32, -_t30);
        dest.putFloat(destOffset + 36, -_t27);
        dest.putFloat(destOffset + 40, _t32 - _t26);
        dest.putFloat(destOffset + 44, objPosZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSphericalShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + objPosOffset;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + targetPosOffset;
        Float3x4OpsKernelsAddress.makeBillboardSphericalShortest_unsafe(_destBase, _objPosBase, _targetPosBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeBillboardSphericalShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer objPos, int objPosOffset, java.nio.ByteBuffer targetPos, int targetPosOffset) {
        float _objPosx = objPos.getFloat(objPosOffset + 0);
        float _objPosy = objPos.getFloat(objPosOffset + 4);
        float _objPosz = objPos.getFloat(objPosOffset + 8);
        float _targetPosx = targetPos.getFloat(targetPosOffset + 0);
        float _targetPosy = targetPos.getFloat(targetPosOffset + 4);
        float _targetPosz = targetPos.getFloat(targetPosOffset + 8);
        float _t0 = _targetPosz - _objPosz;
        float _t1 = _targetPosx - _objPosx;
        float _t2 = _targetPosy - _objPosy;
        float _t3 = _t2 + _t2;
        float _t6 = Math.fma(_t1, _t1, _t2 * _t2);
        float _t9 = (float) Math.sqrt(Math.max(Math.fma(_t0, _t0, _t6), 4.7019774E-38f));
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
        dest.putFloat(destOffset + 0, _t32);
        dest.putFloat(destOffset + 4, _t29);
        dest.putFloat(destOffset + 8, _t30);
        dest.putFloat(destOffset + 12, _objPosx);
        dest.putFloat(destOffset + 16, _t29);
        dest.putFloat(destOffset + 20, 1.0f - _t26);
        dest.putFloat(destOffset + 24, _t27);
        dest.putFloat(destOffset + 28, _objPosy);
        dest.putFloat(destOffset + 32, -_t30);
        dest.putFloat(destOffset + 36, -_t27);
        dest.putFloat(destOffset + 40, _t32 - _t26);
        dest.putFloat(destOffset + 44, _objPosz);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_api(java.nio.ByteBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = Math.fma(-2.0f, dqRZ * dqRZ, 1.0f);
        dest.putFloat(destOffset + 0, Math.fma(-2.0f, _t0, _t6));
        dest.putFloat(destOffset + 4, Math.fma(-2.0f, _t2, _sp0 * dqRY));
        dest.putFloat(destOffset + 8, 2.0f * Math.fma(dqRX, dqRZ, _t3));
        dest.putFloat(destOffset + 12, 2.0f * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))));
        dest.putFloat(destOffset + 16, 2.0f * Math.fma(dqRX, dqRY, _t2));
        dest.putFloat(destOffset + 20, Math.fma(-2.0f, _t4, _t6));
        dest.putFloat(destOffset + 24, Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5));
        dest.putFloat(destOffset + 28, 2.0f * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))));
        dest.putFloat(destOffset + 32, Math.fma(-2.0f, _t3, _sp0 * dqRZ));
        dest.putFloat(destOffset + 36, 2.0f * Math.fma(dqRX, dqRW, _t5));
        dest.putFloat(destOffset + 40, Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f)));
        dest.putFloat(destOffset + 44, 2.0f * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))));
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_lh(java.nio.ByteBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.makeLookAt_lh_unsafe(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Float3x4OpsKernelsByteBuffer.makeLookAt_lh_api(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeLookAt_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeLookAt_lh_unsafe(_destBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_lh_api(java.nio.ByteBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
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
        float _t36 = (1.0f / (float) Math.sqrt(_t35));
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
        dest.putFloat(destOffset + 0, _t40);
        dest.putFloat(destOffset + 4, _t41);
        dest.putFloat(destOffset + 8, _t42);
        dest.putFloat(destOffset + 12, -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41)));
        dest.putFloat(destOffset + 16, _t49);
        dest.putFloat(destOffset + 20, _t50);
        dest.putFloat(destOffset + 24, _t51);
        dest.putFloat(destOffset + 28, -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50)));
        dest.putFloat(destOffset + 32, _t15);
        dest.putFloat(destOffset + 36, _t16);
        dest.putFloat(destOffset + 40, _t14);
        dest.putFloat(destOffset + 44, -Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_rh(java.nio.ByteBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.makeLookAt_rh_unsafe(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Float3x4OpsKernelsByteBuffer.makeLookAt_rh_api(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeLookAt_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeLookAt_rh_unsafe(_destBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_rh_api(java.nio.ByteBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
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
        float _t36 = (1.0f / (float) Math.sqrt(_t35));
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
        dest.putFloat(destOffset + 0, _t40);
        dest.putFloat(destOffset + 4, _t41);
        dest.putFloat(destOffset + 8, _t42);
        dest.putFloat(destOffset + 12, -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41)));
        dest.putFloat(destOffset + 16, _t49);
        dest.putFloat(destOffset + 20, _t50);
        dest.putFloat(destOffset + 24, _t51);
        dest.putFloat(destOffset + 28, -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50)));
        dest.putFloat(destOffset + 32, -_t15);
        dest.putFloat(destOffset + 36, -_t16);
        dest.putFloat(destOffset + 40, -_t14);
        dest.putFloat(destOffset + 44, Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.makeLookAt_lh_unsafe(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Float3x4OpsKernelsByteBuffer.makeLookAt_lh_api(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeLookAt_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x4OpsKernelsAddress.makeLookAt_lh_unsafe(_destBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        float _eyex = eye.getFloat(eyeOffset + 0);
        float _eyey = eye.getFloat(eyeOffset + 4);
        float _eyez = eye.getFloat(eyeOffset + 8);
        float _centerx = center.getFloat(centerOffset + 0);
        float _centery = center.getFloat(centerOffset + 4);
        float _centerz = center.getFloat(centerOffset + 8);
        float _upx = up.getFloat(upOffset + 0);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t0 = _centerz - _eyez;
        float _t1 = _centerx - _eyex;
        float _t2 = _centery - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
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
        float _t30 = Math.fma(_t21, _t16, -(_t22 * _t15));
        float _t31 = Math.fma(_t22, _t14, -(_t23 * _t16));
        float _t32 = Math.fma(_t23, _t15, -(_t21 * _t14));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) Math.sqrt(_t35));
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
        dest.putFloat(destOffset + 0, _t40);
        dest.putFloat(destOffset + 4, _t41);
        dest.putFloat(destOffset + 8, _t42);
        dest.putFloat(destOffset + 12, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        dest.putFloat(destOffset + 16, _t49);
        dest.putFloat(destOffset + 20, _t50);
        dest.putFloat(destOffset + 24, _t51);
        dest.putFloat(destOffset + 28, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        dest.putFloat(destOffset + 32, _t15);
        dest.putFloat(destOffset + 36, _t16);
        dest.putFloat(destOffset + 40, _t14);
        dest.putFloat(destOffset + 44, -Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsByteBuffer.makeLookAt_rh_unsafe(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Float3x4OpsKernelsByteBuffer.makeLookAt_rh_api(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeLookAt_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x4OpsKernelsAddress.makeLookAt_rh_unsafe(_destBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeLookAt_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer eye, int eyeOffset, java.nio.ByteBuffer center, int centerOffset, java.nio.ByteBuffer up, int upOffset) {
        float _eyex = eye.getFloat(eyeOffset + 0);
        float _eyey = eye.getFloat(eyeOffset + 4);
        float _eyez = eye.getFloat(eyeOffset + 8);
        float _centerx = center.getFloat(centerOffset + 0);
        float _centery = center.getFloat(centerOffset + 4);
        float _centerz = center.getFloat(centerOffset + 8);
        float _upx = up.getFloat(upOffset + 0);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t0 = _centerz - _eyez;
        float _t1 = _centerx - _eyex;
        float _t2 = _centery - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
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
        float _t30 = Math.fma(_t21, _t15, -(_t22 * _t16));
        float _t31 = Math.fma(_t22, _t14, -(_t23 * _t15));
        float _t32 = Math.fma(_t23, _t16, -(_t21 * _t14));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) Math.sqrt(_t35));
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
        dest.putFloat(destOffset + 0, _t40);
        dest.putFloat(destOffset + 4, _t41);
        dest.putFloat(destOffset + 8, _t42);
        dest.putFloat(destOffset + 12, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        dest.putFloat(destOffset + 16, _t49);
        dest.putFloat(destOffset + 20, _t50);
        dest.putFloat(destOffset + 24, _t51);
        dest.putFloat(destOffset + 28, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        dest.putFloat(destOffset + 32, -_t15);
        dest.putFloat(destOffset + 36, -_t16);
        dest.putFloat(destOffset + 40, -_t14);
        dest.putFloat(destOffset + 44, Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingXYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXYZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingXYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXYnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, -1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXZY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingXZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXZY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXZnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingXZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXZnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, -1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnYZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingXnYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnYZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, -1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingXnYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnYnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, -1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, -1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnZY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingXnZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnZY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, -1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnZnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingXnZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingXnZnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, -1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, -1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingYXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYXZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingYXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYXnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, -1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYZX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingYZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYZX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYZnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingYZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYZnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, -1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnXZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingYnXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnXZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, -1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingYnXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnXnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, -1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, -1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnZX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingYnZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnZX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, -1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnZnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingYnZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingYnZnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, -1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, -1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZXY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingZXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZXY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZXnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingZXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZXnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, -1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZYX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingZYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZYX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZYnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingZYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZYnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, -1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnXY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingZnXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnXY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, -1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnXnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingZnXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnXnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, -1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, -1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnYX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingZnYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnYX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, -1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnYnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingZnYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingZnYnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, -1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, -1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnXYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXYZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, -1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnXYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXYnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, -1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, -1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXZY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnXZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXZY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, -1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXZnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnXZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXZnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, -1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, -1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnYZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnXnYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnYZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, -1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, -1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnXnYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnYnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, -1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, -1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, -1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnZY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnXnZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnZY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, -1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, -1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnZnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnXnZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnXnZnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, -1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, -1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, -1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnYXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYXZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, -1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnYXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYXnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, -1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, -1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYZX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnYZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYZX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, -1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYZnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnYZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYZnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, -1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, -1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnXZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnYnXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnXZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, -1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, -1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnYnXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnXnZ_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, -1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, -1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, -1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnZX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnYnZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnZX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, -1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, -1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnZnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnYnZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnYnZnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, -1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, -1.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, -1.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZXY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnZXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZXY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZXnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnZXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZXnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, -1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZYX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnZYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZYX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZYnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnZYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZYnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, -1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnXY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnZnXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnXY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, -1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnXnY_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnZnXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnXnY_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, -1.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, -1.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnYX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnZnYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnYX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, -1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnYnX_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeMappingnZnYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeMappingnZnYnX_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, -1.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, -1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -1.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 0.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeReflection_unsafe(java.nio.ByteBuffer dest, int destOffset, float normalX, float normalY, float normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeReflection_unsafe(_destBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeReflection_api(java.nio.ByteBuffer dest, int destOffset, float normalX, float normalY, float normalZ) {
        float _sp0 = normalX + normalX;
        float _t6 = -(_sp0 * normalY);
        float _t7 = -(_sp0 * normalZ);
        float _t8 = -((normalY + normalY) * normalZ);
        dest.putFloat(destOffset + 0, Math.fma(-2.0f, normalX * normalX, 1.0f));
        dest.putFloat(destOffset + 4, _t6);
        dest.putFloat(destOffset + 8, _t7);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _t6);
        dest.putFloat(destOffset + 20, Math.fma(-2.0f, normalY * normalY, 1.0f));
        dest.putFloat(destOffset + 24, _t8);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, _t7);
        dest.putFloat(destOffset + 36, _t8);
        dest.putFloat(destOffset + 40, Math.fma(-2.0f, normalZ * normalZ, 1.0f));
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeReflection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Float3x4OpsKernelsAddress.makeReflection_unsafe(_destBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeReflection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer normal, int normalOffset) {
        float _normalx = normal.getFloat(normalOffset + 0);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _sp0 = _normalx + _normalx;
        float _t6 = -(_sp0 * _normaly);
        float _t7 = -(_sp0 * _normalz);
        float _t8 = -((_normaly + _normaly) * _normalz);
        dest.putFloat(destOffset + 0, Math.fma(-2.0f, _normalx * _normalx, 1.0f));
        dest.putFloat(destOffset + 4, _t6);
        dest.putFloat(destOffset + 8, _t7);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _t6);
        dest.putFloat(destOffset + 20, Math.fma(-2.0f, _normaly * _normaly, 1.0f));
        dest.putFloat(destOffset + 24, _t8);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, _t7);
        dest.putFloat(destOffset + 36, _t8);
        dest.putFloat(destOffset + 40, Math.fma(-2.0f, _normalz * _normalz, 1.0f));
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisY;
        float _t3 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = 1.0f - _t1;
        dest.putFloat(destOffset + 0, Math.fma(_t5, axisX * axisX, _t1));
        dest.putFloat(destOffset + 4, Math.fma(_t5, _t2, -(axisZ * _t0)));
        dest.putFloat(destOffset + 8, Math.fma(axisY, _t0, _t5 * _t3));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, Math.fma(axisZ, _t0, _t5 * _t2));
        dest.putFloat(destOffset + 20, Math.fma(_t5, axisY * axisY, _t1));
        dest.putFloat(destOffset + 24, Math.fma(_t5, _t4, -(axisX * _t0)));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, Math.fma(_t5, _t3, -(axisY * _t0)));
        dest.putFloat(destOffset + 36, Math.fma(axisX, _t0, _t5 * _t4));
        dest.putFloat(destOffset + 40, Math.fma(_t5, axisZ * axisZ, _t1));
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Float3x4OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _axisx = axis.getFloat(axisOffset + 0);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisy;
        float _t3 = _axisx * _axisz;
        float _t4 = _axisy * _axisz;
        float _t5 = 1.0f - _t1;
        dest.putFloat(destOffset + 0, Math.fma(_t5, _axisx * _axisx, _t1));
        dest.putFloat(destOffset + 4, Math.fma(_t5, _t2, -(_axisz * _t0)));
        dest.putFloat(destOffset + 8, Math.fma(_axisy, _t0, _t5 * _t3));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, Math.fma(_axisz, _t0, _t5 * _t2));
        dest.putFloat(destOffset + 20, Math.fma(_t5, _axisy * _axisy, _t1));
        dest.putFloat(destOffset + 24, Math.fma(_t5, _t4, -(_axisx * _t0)));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, Math.fma(_t5, _t3, -(_axisy * _t0)));
        dest.putFloat(destOffset + 36, Math.fma(_axisx, _t0, _t5 * _t4));
        dest.putFloat(destOffset + 40, Math.fma(_t5, _axisz * _axisz, _t1));
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) Math.sqrt(_t4));
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
        float _t33 = (1.0f / (float) Math.sqrt(_t32));
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
        dest.putFloat(destOffset + 0, _t37);
        dest.putFloat(destOffset + 4, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putFloat(destOffset + 8, _t12);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _t39);
        dest.putFloat(destOffset + 20, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putFloat(destOffset + 24, _t13);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, _t38);
        dest.putFloat(destOffset + 36, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putFloat(destOffset + 40, _t11);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        Float3x4OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset + 0);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset + 0);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) Math.sqrt(_t4));
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
        float _t33 = (1.0f / (float) Math.sqrt(_t32));
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
        dest.putFloat(destOffset + 0, _t37);
        dest.putFloat(destOffset + 4, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.putFloat(destOffset + 8, _t12);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _t39);
        dest.putFloat(destOffset + 20, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.putFloat(destOffset + 24, _t13);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, _t38);
        dest.putFloat(destOffset + 36, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.putFloat(destOffset + 40, _t11);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_api(java.nio.ByteBuffer dest, int destOffset, float qX, float qY, float qZ, float qW) {
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dest.putFloat(destOffset + 0, Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * Math.fma(qX, qY, -_t1));
        dest.putFloat(destOffset + 8, 2.0f * Math.fma(qX, qZ, _t2));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 2.0f * Math.fma(qX, qY, _t1));
        dest.putFloat(destOffset + 20, Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f));
        dest.putFloat(destOffset + 24, 2.0f * Math.fma(qY, qZ, -(qX * qW)));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 2.0f * Math.fma(qX, qZ, -_t2));
        dest.putFloat(destOffset + 36, 2.0f * Math.fma(qX, qW, qY * qZ));
        dest.putFloat(destOffset + 40, Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f));
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset;
        Float3x4OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, _qBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        float _qx = q.getFloat(qOffset + 0);
        float _qy = q.getFloat(qOffset + 4);
        float _qz = q.getFloat(qOffset + 8);
        float _qw = q.getFloat(qOffset + 12);
        float _t0 = _qz * _qz;
        float _t1 = _qz * _qw;
        float _t2 = _qy * _qw;
        dest.putFloat(destOffset + 0, Math.fma(-2.0f, Math.fma(_qy, _qy, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * Math.fma(_qx, _qy, -_t1));
        dest.putFloat(destOffset + 8, 2.0f * Math.fma(_qx, _qz, _t2));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 2.0f * Math.fma(_qx, _qy, _t1));
        dest.putFloat(destOffset + 20, Math.fma(-2.0f, Math.fma(_qx, _qx, _t0), 1.0f));
        dest.putFloat(destOffset + 24, 2.0f * Math.fma(_qy, _qz, -(_qx * _qw)));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 2.0f * Math.fma(_qx, _qz, -_t2));
        dest.putFloat(destOffset + 36, 2.0f * Math.fma(_qx, _qw, _qy * _qz));
        dest.putFloat(destOffset + 40, Math.fma(-2.0f, Math.fma(_qx, _qx, _qy * _qy), 1.0f));
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, _t1);
        dest.putFloat(destOffset + 24, -_t0);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, _t0);
        dest.putFloat(destOffset + 40, _t1);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_api(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t0;
        float _t7 = _t0 * _t5;
        dest.putFloat(destOffset + 0, _t3 * _t4);
        dest.putFloat(destOffset + 4, -(_t1 * _t3));
        dest.putFloat(destOffset + 8, _t0);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, Math.fma(_t6, _t4, _t1 * _t5));
        dest.putFloat(destOffset + 20, Math.fma(_t5, _t4, -(_t6 * _t1)));
        dest.putFloat(destOffset + 24, -(_t2 * _t3));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, Math.fma(_t2, _t1, -(_t7 * _t4)));
        dest.putFloat(destOffset + 36, Math.fma(_t7, _t1, _t2 * _t4));
        dest.putFloat(destOffset + 40, _t5 * _t3);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_api(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t1 * _t5;
        dest.putFloat(destOffset + 0, _t3 * _t4);
        dest.putFloat(destOffset + 4, -_t1);
        dest.putFloat(destOffset + 8, _t0 * _t4);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, Math.fma(_t7, _t3, _t2 * _t0));
        dest.putFloat(destOffset + 20, _t5 * _t4);
        dest.putFloat(destOffset + 24, Math.fma(_t7, _t0, -(_t2 * _t3)));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, Math.fma(_t6, _t3, -(_t0 * _t5)));
        dest.putFloat(destOffset + 36, _t2 * _t4);
        dest.putFloat(destOffset + 40, Math.fma(_t6, _t0, _t5 * _t3));
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, _t1);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, _t0);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -_t0);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, _t1);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_api(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        dest.putFloat(destOffset + 0, Math.fma(_t6, _t2, _t3 * _t4));
        dest.putFloat(destOffset + 4, Math.fma(_t6, _t4, -(_t2 * _t3)));
        dest.putFloat(destOffset + 8, _t1 * _t5);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _t2 * _t5);
        dest.putFloat(destOffset + 20, _t5 * _t4);
        dest.putFloat(destOffset + 24, -_t0);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, Math.fma(_t7, _t2, -(_t1 * _t4)));
        dest.putFloat(destOffset + 36, Math.fma(_t7, _t4, _t1 * _t2));
        dest.putFloat(destOffset + 40, _t5 * _t3);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_api(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t1 * _t3;
        dest.putFloat(destOffset + 0, _t3 * _t4);
        dest.putFloat(destOffset + 4, Math.fma(_t2, _t0, -(_t7 * _t5)));
        dest.putFloat(destOffset + 8, Math.fma(_t7, _t2, _t0 * _t5));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _t1);
        dest.putFloat(destOffset + 20, _t5 * _t4);
        dest.putFloat(destOffset + 24, -(_t2 * _t4));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -(_t0 * _t4));
        dest.putFloat(destOffset + 36, Math.fma(_t6, _t5, _t2 * _t3));
        dest.putFloat(destOffset + 40, Math.fma(_t5, _t3, -(_t6 * _t2)));
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, _t1);
        dest.putFloat(destOffset + 4, -_t0);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _t0);
        dest.putFloat(destOffset + 20, _t1);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_api(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        dest.putFloat(destOffset + 0, Math.fma(_t3, _t4, -(_t6 * _t0)));
        dest.putFloat(destOffset + 4, -(_t1 * _t5));
        dest.putFloat(destOffset + 8, Math.fma(_t6, _t3, _t0 * _t4));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, Math.fma(_t7, _t0, _t1 * _t3));
        dest.putFloat(destOffset + 20, _t5 * _t4);
        dest.putFloat(destOffset + 24, Math.fma(_t0, _t1, -(_t7 * _t3)));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -(_t0 * _t5));
        dest.putFloat(destOffset + 36, _t2);
        dest.putFloat(destOffset + 40, _t5 * _t3);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_api(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t4;
        dest.putFloat(destOffset + 0, _t3 * _t4);
        dest.putFloat(destOffset + 4, Math.fma(_t7, _t2, -(_t1 * _t5)));
        dest.putFloat(destOffset + 8, Math.fma(_t7, _t5, _t2 * _t1));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, _t1 * _t3);
        dest.putFloat(destOffset + 20, Math.fma(_t6, _t2, _t5 * _t4));
        dest.putFloat(destOffset + 24, Math.fma(_t6, _t5, -(_t2 * _t4)));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, -_t0);
        dest.putFloat(destOffset + 36, _t2 * _t3);
        dest.putFloat(destOffset + 40, _t5 * _t3);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ) {
        dest.putFloat(destOffset + 0, vX);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, vY);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, vZ);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        dest.putFloat(destOffset + 0, _vx);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, _vy);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, _vz);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, float s) {
        dest.putFloat(destOffset + 0, s);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, s);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, s);
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, vX);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, vY);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x4OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _vx);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 1.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, _vy);
        dest.putFloat(destOffset + 32, 0.0f);
        dest.putFloat(destOffset + 36, 0.0f);
        dest.putFloat(destOffset + 40, 1.0f);
        dest.putFloat(destOffset + 44, _vz);
        return dest;
    }

    public static java.nio.ByteBuffer mapXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapXYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXYnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXZnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapXZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXZnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXnYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapXnYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXnYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXnYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapXnYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXnYnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXnZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapXnZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXnZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapXnZnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapXnZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapXnZnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapYXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYXnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYZnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapYZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYZnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYnXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapYnXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYnXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYnXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapYnXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYnXnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYnZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapYnZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYnZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapYnZnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapYnZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapYnZnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZXnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapZXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZXnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZYnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapZYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZYnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZnXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapZnXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZnXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZnXnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapZnXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZnXnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZnYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapZnYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZnYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapZnYnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapZnYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapZnYnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnXYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXYnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXZnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnXZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXZnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnXnYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnYnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnXnYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnYnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnXnZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnZnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnXnZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnXnZnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnYXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYXnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYZnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnYZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYZnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnYnXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnXnZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnYnXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnXnZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnYnZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnZnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnYnZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnYnZnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZXnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnZXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZXnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZYnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnZYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZYnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnZnXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnXnY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnZnXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnXnY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnZnYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnYnX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mapnZnYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer mapnZnYnX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself2);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = -rotY;
        float _t2 = -pivotZ;
        float _t3 = -rotX;
        float _t4 = rotX + rotX;
        float _t5 = rotY + rotY;
        float _t6 = rotZ + rotZ;
        float _t7 = rotW * _t5;
        float _t8 = rotW * _t6;
        float _t9 = rotZ * _t6;
        float _t10 = rotW * _t4;
        float _t14 = Math.fma(-rotZ, _t6, 1.0f);
        float _t16 = Math.fma(rotZ, _t4, _t7);
        float _t17 = Math.fma(rotY, _t4, _t8);
        float _t18 = Math.fma(rotZ, _t5, _t10);
        float _t19 = Math.fma(rotY, _t4, -_t8);
        float _t20 = Math.fma(rotZ, _t5, -_t10);
        float _t21 = Math.fma(rotZ, _t4, -_t7);
        float _t22 = Math.fma(_t0, _t5, _t14);
        float _t23 = Math.fma(_t3, _t4, _t14);
        float _t24 = Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f));
        dest.putFloat(destOffset + 0, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putFloat(destOffset + 4, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putFloat(destOffset + 8, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putFloat(destOffset + 12, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))));
        dest.putFloat(destOffset + 16, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        dest.putFloat(destOffset + 20, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        dest.putFloat(destOffset + 24, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        dest.putFloat(destOffset + 28, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))));
        dest.putFloat(destOffset + 32, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        dest.putFloat(destOffset + 36, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        dest.putFloat(destOffset + 44, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer rot, int rotOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rotBase = UnsafeOpsHolder.U.getLong(rot, UnsafeCopy.BB_ADDRESS_OFFSET) + rotOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x4OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _rotBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer rot, int rotOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _rotx = rot.getFloat(rotOffset + 0);
        float _roty = rot.getFloat(rotOffset + 4);
        float _rotz = rot.getFloat(rotOffset + 8);
        float _rotw = rot.getFloat(rotOffset + 12);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _pivotz = pivot.getFloat(pivotOffset + 8);
        float _t0 = -_roty;
        float _t2 = -_pivotz;
        float _t3 = -_rotx;
        float _t4 = _rotx + _rotx;
        float _t5 = _roty + _roty;
        float _t6 = _rotz + _rotz;
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t9 = _rotz * _t6;
        float _t10 = _rotw * _t4;
        float _t14 = Math.fma(-_rotz, _t6, 1.0f);
        float _t16 = Math.fma(_rotz, _t4, _t7);
        float _t17 = Math.fma(_roty, _t4, _t8);
        float _t18 = Math.fma(_rotz, _t5, _t10);
        float _t19 = Math.fma(_roty, _t4, -_t8);
        float _t20 = Math.fma(_rotz, _t5, -_t10);
        float _t21 = Math.fma(_rotz, _t4, -_t7);
        float _t22 = Math.fma(_t0, _t5, _t14);
        float _t23 = Math.fma(_t3, _t4, _t14);
        float _t24 = Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f));
        dest.putFloat(destOffset + 0, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putFloat(destOffset + 4, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putFloat(destOffset + 8, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putFloat(destOffset + 12, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))));
        dest.putFloat(destOffset + 16, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        dest.putFloat(destOffset + 20, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        dest.putFloat(destOffset + 24, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        dest.putFloat(destOffset + 28, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))));
        dest.putFloat(destOffset + 32, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        dest.putFloat(destOffset + 36, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        dest.putFloat(destOffset + 44, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        float _t18 = Math.fma(_t11, axisX * axisX, _t1);
        float _t19 = Math.fma(_t11, axisY * axisY, _t1);
        float _t20 = Math.fma(_t11, axisZ * axisZ, _t1);
        float _t21 = Math.fma(axisY, _t0, _t11 * _t2);
        float _t22 = Math.fma(axisZ, _t0, _t11 * _t4);
        float _t23 = Math.fma(axisX, _t0, _t11 * _t6);
        float _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        float _t25 = Math.fma(_t11, _t6, -(axisX * _t0));
        float _t26 = Math.fma(_t11, _t2, -(axisY * _t0));
        dest.putFloat(destOffset + 0, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        dest.putFloat(destOffset + 4, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.putFloat(destOffset + 8, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.putFloat(destOffset + 12, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        dest.putFloat(destOffset + 16, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putFloat(destOffset + 20, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putFloat(destOffset + 24, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putFloat(destOffset + 28, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        dest.putFloat(destOffset + 32, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.putFloat(destOffset + 36, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        dest.putFloat(destOffset + 44, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Float3x4OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _axisx = axis.getFloat(axisOffset + 0);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisz;
        float _t4 = _axisx * _axisy;
        float _t6 = _axisy * _axisz;
        float _t11 = 1.0f - _t1;
        float _t18 = Math.fma(_t11, _axisx * _axisx, _t1);
        float _t19 = Math.fma(_t11, _axisy * _axisy, _t1);
        float _t20 = Math.fma(_t11, _axisz * _axisz, _t1);
        float _t21 = Math.fma(_axisy, _t0, _t11 * _t2);
        float _t22 = Math.fma(_axisz, _t0, _t11 * _t4);
        float _t23 = Math.fma(_axisx, _t0, _t11 * _t6);
        float _t24 = Math.fma(_t11, _t4, -(_axisz * _t0));
        float _t25 = Math.fma(_t11, _t6, -(_axisx * _t0));
        float _t26 = Math.fma(_t11, _t2, -(_axisy * _t0));
        dest.putFloat(destOffset + 0, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        dest.putFloat(destOffset + 4, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.putFloat(destOffset + 8, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.putFloat(destOffset + 12, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        dest.putFloat(destOffset + 16, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.putFloat(destOffset + 20, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.putFloat(destOffset + 24, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.putFloat(destOffset + 28, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        dest.putFloat(destOffset + 32, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.putFloat(destOffset + 36, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        dest.putFloat(destOffset + 44, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.preRotateQuat_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = -qY;
        float _t2 = -qX;
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        float _t6 = qW * _t4;
        float _t7 = qW * _t5;
        float _t8 = qW * _t3;
        float _t12 = Math.fma(-qZ, _t5, 1.0f);
        float _t14 = Math.fma(qZ, _t3, _t6);
        float _t15 = Math.fma(qY, _t3, _t7);
        float _t16 = Math.fma(qZ, _t4, _t8);
        float _t17 = Math.fma(qY, _t3, -_t7);
        float _t18 = Math.fma(qZ, _t4, -_t8);
        float _t19 = Math.fma(qZ, _t3, -_t6);
        float _t20 = Math.fma(_t0, _t4, _t12);
        float _t21 = Math.fma(_t2, _t3, _t12);
        float _t22 = Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f));
        dest.putFloat(destOffset + 0, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        dest.putFloat(destOffset + 4, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        dest.putFloat(destOffset + 8, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        dest.putFloat(destOffset + 12, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        dest.putFloat(destOffset + 16, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        dest.putFloat(destOffset + 20, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        dest.putFloat(destOffset + 24, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        dest.putFloat(destOffset + 28, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        dest.putFloat(destOffset + 32, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        dest.putFloat(destOffset + 36, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        dest.putFloat(destOffset + 44, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset;
        Float3x4OpsKernelsAddress.preRotateQuat_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _qx = q.getFloat(qOffset + 0);
        float _qy = q.getFloat(qOffset + 4);
        float _qz = q.getFloat(qOffset + 8);
        float _qw = q.getFloat(qOffset + 12);
        float _t0 = -_qy;
        float _t2 = -_qx;
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        float _t6 = _qw * _t4;
        float _t7 = _qw * _t5;
        float _t8 = _qw * _t3;
        float _t12 = Math.fma(-_qz, _t5, 1.0f);
        float _t14 = Math.fma(_qz, _t3, _t6);
        float _t15 = Math.fma(_qy, _t3, _t7);
        float _t16 = Math.fma(_qz, _t4, _t8);
        float _t17 = Math.fma(_qy, _t3, -_t7);
        float _t18 = Math.fma(_qz, _t4, -_t8);
        float _t19 = Math.fma(_qz, _t3, -_t6);
        float _t20 = Math.fma(_t0, _t4, _t12);
        float _t21 = Math.fma(_t2, _t3, _t12);
        float _t22 = Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f));
        dest.putFloat(destOffset + 0, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        dest.putFloat(destOffset + 4, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        dest.putFloat(destOffset + 8, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        dest.putFloat(destOffset + 12, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        dest.putFloat(destOffset + 16, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        dest.putFloat(destOffset + 20, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        dest.putFloat(destOffset + 24, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        dest.putFloat(destOffset + 28, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        dest.putFloat(destOffset + 32, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        dest.putFloat(destOffset + 36, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        dest.putFloat(destOffset + 44, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self01);
        dest.putFloat(destOffset + 8, _self02);
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self10, _t1, -(_self20 * _t0)));
        dest.putFloat(destOffset + 20, Math.fma(_self11, _t1, -(_self21 * _t0)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t1, -(_self22 * _t0)));
        dest.putFloat(destOffset + 28, Math.fma(_self13, _t1, -(_self23 * _t0)));
        dest.putFloat(destOffset + 32, Math.fma(_self10, _t0, _self20 * _t1));
        dest.putFloat(destOffset + 36, Math.fma(_self11, _t0, _self21 * _t1));
        dest.putFloat(destOffset + 40, Math.fma(_self12, _t0, _self22 * _t1));
        dest.putFloat(destOffset + 44, Math.fma(_self13, _t0, _self23 * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, Math.fma(_self00, _t1, _self20 * _t0));
        dest.putFloat(destOffset + 4, Math.fma(_self01, _t1, _self21 * _t0));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t1, _self22 * _t0));
        dest.putFloat(destOffset + 12, Math.fma(_self03, _t1, _self23 * _t0));
        dest.putFloat(destOffset + 16, _self10);
        dest.putFloat(destOffset + 20, _self11);
        dest.putFloat(destOffset + 24, _self12);
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self20, _t1, -(_self00 * _t0)));
        dest.putFloat(destOffset + 36, Math.fma(_self21, _t1, -(_self01 * _t0)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t1, -(_self02 * _t0)));
        dest.putFloat(destOffset + 44, Math.fma(_self23, _t1, -(_self03 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.putFloat(destOffset + 4, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t1, -(_self12 * _t0)));
        dest.putFloat(destOffset + 12, Math.fma(_self03, _t1, -(_self13 * _t0)));
        dest.putFloat(destOffset + 16, Math.fma(_self00, _t0, _self10 * _t1));
        dest.putFloat(destOffset + 20, Math.fma(_self01, _t0, _self11 * _t1));
        dest.putFloat(destOffset + 24, Math.fma(_self02, _t0, _self12 * _t1));
        dest.putFloat(destOffset + 28, Math.fma(_self03, _t0, _self13 * _t1));
        dest.putFloat(destOffset + 32, _self20);
        dest.putFloat(destOffset + 36, _self21);
        dest.putFloat(destOffset + 40, _self22);
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        dest.putFloat(destOffset + 0, _self00 * vX);
        dest.putFloat(destOffset + 4, _self01 * vX);
        dest.putFloat(destOffset + 8, _self02 * vX);
        dest.putFloat(destOffset + 12, _self03 * vX);
        dest.putFloat(destOffset + 16, _self10 * vY);
        dest.putFloat(destOffset + 20, _self11 * vY);
        dest.putFloat(destOffset + 24, _self12 * vY);
        dest.putFloat(destOffset + 28, _self13 * vY);
        dest.putFloat(destOffset + 32, _self20 * vZ);
        dest.putFloat(destOffset + 36, _self21 * vZ);
        dest.putFloat(destOffset + 40, _self22 * vZ);
        dest.putFloat(destOffset + 44, _self23 * vZ);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        dest.putFloat(destOffset + 0, _self00 * _vx);
        dest.putFloat(destOffset + 4, _self01 * _vx);
        dest.putFloat(destOffset + 8, _self02 * _vx);
        dest.putFloat(destOffset + 12, _self03 * _vx);
        dest.putFloat(destOffset + 16, _self10 * _vy);
        dest.putFloat(destOffset + 20, _self11 * _vy);
        dest.putFloat(destOffset + 24, _self12 * _vy);
        dest.putFloat(destOffset + 28, _self13 * _vy);
        dest.putFloat(destOffset + 32, _self20 * _vz);
        dest.putFloat(destOffset + 36, _self21 * _vz);
        dest.putFloat(destOffset + 40, _self22 * _vz);
        dest.putFloat(destOffset + 44, _self23 * _vz);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.getFloat(srcOffset + _i * 4);
            dest.putFloat(destOffset + _i * 4, s * _eself);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = 1.0f - s;
        dest.putFloat(destOffset + 0, s * _self00);
        dest.putFloat(destOffset + 4, s * _self01);
        dest.putFloat(destOffset + 8, s * _self02);
        dest.putFloat(destOffset + 12, Math.fma(s, _self03, pivotX * _t0));
        dest.putFloat(destOffset + 16, s * _self10);
        dest.putFloat(destOffset + 20, s * _self11);
        dest.putFloat(destOffset + 24, s * _self12);
        dest.putFloat(destOffset + 28, Math.fma(s, _self13, pivotY * _t0));
        dest.putFloat(destOffset + 32, s * _self20);
        dest.putFloat(destOffset + 36, s * _self21);
        dest.putFloat(destOffset + 40, s * _self22);
        dest.putFloat(destOffset + 44, Math.fma(s, _self23, pivotZ * _t0));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _pivotz = pivot.getFloat(pivotOffset + 8);
        float _t0 = 1.0f - s;
        dest.putFloat(destOffset + 0, s * _self00);
        dest.putFloat(destOffset + 4, s * _self01);
        dest.putFloat(destOffset + 8, s * _self02);
        dest.putFloat(destOffset + 12, Math.fma(s, _self03, _pivotx * _t0));
        dest.putFloat(destOffset + 16, s * _self10);
        dest.putFloat(destOffset + 20, s * _self11);
        dest.putFloat(destOffset + 24, s * _self12);
        dest.putFloat(destOffset + 28, Math.fma(s, _self13, _pivoty * _t0));
        dest.putFloat(destOffset + 32, s * _self20);
        dest.putFloat(destOffset + 36, s * _self21);
        dest.putFloat(destOffset + 40, s * _self22);
        dest.putFloat(destOffset + 44, Math.fma(s, _self23, _pivotz * _t0));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        dest.putFloat(destOffset + 0, sX * _self00);
        dest.putFloat(destOffset + 4, sX * _self01);
        dest.putFloat(destOffset + 8, sX * _self02);
        dest.putFloat(destOffset + 12, Math.fma(pivotX, 1.0f - sX, sX * _self03));
        dest.putFloat(destOffset + 16, sY * _self10);
        dest.putFloat(destOffset + 20, sY * _self11);
        dest.putFloat(destOffset + 24, sY * _self12);
        dest.putFloat(destOffset + 28, Math.fma(pivotY, 1.0f - sY, sY * _self13));
        dest.putFloat(destOffset + 32, sZ * _self20);
        dest.putFloat(destOffset + 36, sZ * _self21);
        dest.putFloat(destOffset + 40, sZ * _self22);
        dest.putFloat(destOffset + 44, Math.fma(pivotZ, 1.0f - sZ, sZ * _self23));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _sx = s.getFloat(sOffset + 0);
        float _sy = s.getFloat(sOffset + 4);
        float _sz = s.getFloat(sOffset + 8);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _pivotz = pivot.getFloat(pivotOffset + 8);
        dest.putFloat(destOffset + 0, _sx * _self00);
        dest.putFloat(destOffset + 4, _sx * _self01);
        dest.putFloat(destOffset + 8, _sx * _self02);
        dest.putFloat(destOffset + 12, Math.fma(_pivotx, 1.0f - _sx, _sx * _self03));
        dest.putFloat(destOffset + 16, _sy * _self10);
        dest.putFloat(destOffset + 20, _sy * _self11);
        dest.putFloat(destOffset + 24, _sy * _self12);
        dest.putFloat(destOffset + 28, Math.fma(_pivoty, 1.0f - _sy, _sy * _self13));
        dest.putFloat(destOffset + 32, _sz * _self20);
        dest.putFloat(destOffset + 36, _sz * _self21);
        dest.putFloat(destOffset + 40, _sz * _self22);
        dest.putFloat(destOffset + 44, Math.fma(_pivotz, 1.0f - _sz, _sz * _self23));
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self01);
        dest.putFloat(destOffset + 8, _self02);
        dest.putFloat(destOffset + 12, _self03 + vX);
        dest.putFloat(destOffset + 16, _self10);
        dest.putFloat(destOffset + 20, _self11);
        dest.putFloat(destOffset + 24, _self12);
        dest.putFloat(destOffset + 28, _self13 + vY);
        dest.putFloat(destOffset + 32, _self20);
        dest.putFloat(destOffset + 36, _self21);
        dest.putFloat(destOffset + 40, _self22);
        dest.putFloat(destOffset + 44, _self23 + vZ);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x4OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self01);
        dest.putFloat(destOffset + 8, _self02);
        dest.putFloat(destOffset + 12, _self03 + _vx);
        dest.putFloat(destOffset + 16, _self10);
        dest.putFloat(destOffset + 20, _self11);
        dest.putFloat(destOffset + 24, _self12);
        dest.putFloat(destOffset + 28, _self13 + _vy);
        dest.putFloat(destOffset + 32, _self20);
        dest.putFloat(destOffset + 36, _self21);
        dest.putFloat(destOffset + 40, _self22);
        dest.putFloat(destOffset + 44, _self23 + _vz);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _sp0 = normalX + normalX;
        float _t0 = -_self02;
        float _t1 = -_self12;
        float _t2 = -_self22;
        float _t9 = _sp0 * normalZ;
        float _t10 = _sp0 * normalY;
        float _t11 = (normalY + normalY) * normalZ;
        float _t12 = Math.fma(-2.0f, normalX * normalX, 1.0f);
        float _t13 = Math.fma(-2.0f, normalY * normalY, 1.0f);
        float _t14 = Math.fma(-2.0f, normalZ * normalZ, 1.0f);
        dest.putFloat(destOffset + 0, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        dest.putFloat(destOffset + 4, Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10))));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9))));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10))));
        dest.putFloat(destOffset + 20, Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10))));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9))));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10))));
        dest.putFloat(destOffset + 36, Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10))));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9))));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Float3x4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _normalx = normal.getFloat(normalOffset + 0);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _sp0 = _normalx + _normalx;
        float _t0 = -_self02;
        float _t1 = -_self12;
        float _t2 = -_self22;
        float _t9 = _sp0 * _normalz;
        float _t10 = _sp0 * _normaly;
        float _t11 = (_normaly + _normaly) * _normalz;
        float _t12 = Math.fma(-2.0f, _normalx * _normalx, 1.0f);
        float _t13 = Math.fma(-2.0f, _normaly * _normaly, 1.0f);
        float _t14 = Math.fma(-2.0f, _normalz * _normalz, 1.0f);
        dest.putFloat(destOffset + 0, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        dest.putFloat(destOffset + 4, Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10))));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9))));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10))));
        dest.putFloat(destOffset + 20, Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10))));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9))));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10))));
        dest.putFloat(destOffset + 36, Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10))));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9))));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = -rotY;
        float _t2 = -rotX;
        float _t3 = -pivotZ;
        float _t5 = rotX + rotX;
        float _t6 = rotY + rotY;
        float _t7 = rotZ + rotZ;
        float _t8 = rotW * _t6;
        float _t9 = rotW * _t7;
        float _t10 = rotW * _t5;
        float _t11 = rotZ * _t7;
        float _t16 = Math.fma(-rotZ, _t7, 1.0f);
        float _t18 = Math.fma(rotY, _t5, _t9);
        float _t19 = Math.fma(rotZ, _t6, _t10);
        float _t20 = Math.fma(rotZ, _t5, _t8);
        float _t24 = Math.fma(rotZ, _t5, -_t8);
        float _t25 = Math.fma(rotY, _t5, -_t9);
        float _t26 = Math.fma(rotZ, _t6, -_t10);
        float _t27 = Math.fma(_t0, _t6, _t16);
        float _t28 = Math.fma(_t2, _t5, _t16);
        float _t29 = Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f));
        float _t39 = Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25)));
        float _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        float _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        dest.putFloat(destOffset + 12, Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03))));
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26)));
        dest.putFloat(destOffset + 28, Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13))));
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26)));
        dest.putFloat(destOffset + 44, Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer rot, int rotOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rotBase = UnsafeOpsHolder.U.getLong(rot, UnsafeCopy.BB_ADDRESS_OFFSET) + rotOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x4OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _rotBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer rot, int rotOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _rotx = rot.getFloat(rotOffset + 0);
        float _roty = rot.getFloat(rotOffset + 4);
        float _rotz = rot.getFloat(rotOffset + 8);
        float _rotw = rot.getFloat(rotOffset + 12);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _pivotz = pivot.getFloat(pivotOffset + 8);
        float _t0 = -_roty;
        float _t2 = -_rotx;
        float _t3 = -_pivotz;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
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
        float _t29 = Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f));
        float _t39 = Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25)));
        float _t40 = Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)));
        float _t41 = Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        dest.putFloat(destOffset + 12, Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03))));
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26)));
        dest.putFloat(destOffset + 28, Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13))));
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26)));
        dest.putFloat(destOffset + 44, Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        float _t18 = Math.fma(_t11, axisX * axisX, _t1);
        float _t19 = Math.fma(_t11, axisY * axisY, _t1);
        float _t20 = Math.fma(_t11, axisZ * axisZ, _t1);
        float _t21 = Math.fma(axisZ, _t0, _t11 * _t5);
        float _t22 = Math.fma(axisX, _t0, _t11 * _t6);
        float _t23 = Math.fma(axisY, _t0, _t11 * _t2);
        float _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        float _t25 = Math.fma(_t11, _t5, -(axisZ * _t0));
        float _t26 = Math.fma(_t11, _t6, -(axisX * _t0));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Float3x4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _axisx = axis.getFloat(axisOffset + 0);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisz;
        float _t5 = _axisx * _axisy;
        float _t6 = _axisy * _axisz;
        float _t11 = 1.0f - _t1;
        float _t18 = Math.fma(_t11, _axisx * _axisx, _t1);
        float _t19 = Math.fma(_t11, _axisy * _axisy, _t1);
        float _t20 = Math.fma(_t11, _axisz * _axisz, _t1);
        float _t21 = Math.fma(_axisz, _t0, _t11 * _t5);
        float _t22 = Math.fma(_axisx, _t0, _t11 * _t6);
        float _t23 = Math.fma(_axisy, _t0, _t11 * _t2);
        float _t24 = Math.fma(_t11, _t2, -(_axisy * _t0));
        float _t25 = Math.fma(_t11, _t5, -(_axisz * _t0));
        float _t26 = Math.fma(_t11, _t6, -(_axisx * _t0));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateQuat_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.ByteBuffer rotateQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = -qY;
        float _t2 = -qX;
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        float _t6 = qW * _t4;
        float _t7 = qW * _t5;
        float _t8 = qW * _t3;
        float _t12 = Math.fma(-qZ, _t5, 1.0f);
        float _t14 = Math.fma(qY, _t3, _t7);
        float _t15 = Math.fma(qZ, _t4, _t8);
        float _t16 = Math.fma(qZ, _t3, _t6);
        float _t17 = Math.fma(qZ, _t3, -_t6);
        float _t18 = Math.fma(qY, _t3, -_t7);
        float _t19 = Math.fma(qZ, _t4, -_t8);
        float _t20 = Math.fma(_t0, _t4, _t12);
        float _t21 = Math.fma(_t2, _t3, _t12);
        float _t22 = Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19)));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19)));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19)));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset;
        Float3x4OpsKernelsAddress.rotateQuat_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _qx = q.getFloat(qOffset + 0);
        float _qy = q.getFloat(qOffset + 4);
        float _qz = q.getFloat(qOffset + 8);
        float _qw = q.getFloat(qOffset + 12);
        float _t0 = -_qy;
        float _t2 = -_qx;
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        float _t6 = _qw * _t4;
        float _t7 = _qw * _t5;
        float _t8 = _qw * _t3;
        float _t12 = Math.fma(-_qz, _t5, 1.0f);
        float _t14 = Math.fma(_qy, _t3, _t7);
        float _t15 = Math.fma(_qz, _t4, _t8);
        float _t16 = Math.fma(_qz, _t3, _t6);
        float _t17 = Math.fma(_qz, _t3, -_t6);
        float _t18 = Math.fma(_qy, _t3, -_t7);
        float _t19 = Math.fma(_qz, _t4, -_t8);
        float _t20 = Math.fma(_t0, _t4, _t12);
        float _t21 = Math.fma(_t2, _t3, _t12);
        float _t22 = Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19)));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19)));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19)));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, Math.fma(_self01, _t1, _self02 * _t0));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t1, -(_self01 * _t0)));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, _self10);
        dest.putFloat(destOffset + 20, Math.fma(_self11, _t1, _self12 * _t0));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t1, -(_self11 * _t0)));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, _self20);
        dest.putFloat(destOffset + 36, Math.fma(_self21, _t1, _self22 * _t0));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t1, -(_self21 * _t0)));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t3 = (float) Math.cosFromSin(_t0, angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        float _t10 = _t1 * _t5;
        float _t11 = _t0 * _t5;
        float _t13 = _t5 * _t4;
        float _t15 = _t3 * _t5;
        float _t18 = Math.fma(_t6, _t4, _t1 * _t3);
        float _t19 = Math.fma(_t7, _t1, _t0 * _t4);
        float _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        float _t21 = Math.fma(_t3, _t4, -(_t6 * _t1));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t20, Math.fma(_self00, _t13, _self01 * _t18)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t19, Math.fma(_self01, _t21, -(_self00 * _t10))));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t15, Math.fma(_self00, _t2, -(_self01 * _t11))));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t20, Math.fma(_self10, _t13, _self11 * _t18)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t19, Math.fma(_self11, _t21, -(_self10 * _t10))));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t15, Math.fma(_self10, _t2, -(_self11 * _t11))));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t20, Math.fma(_self20, _t13, _self21 * _t18)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t19, Math.fma(_self21, _t21, -(_self20 * _t10))));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t15, Math.fma(_self20, _t2, -(_self21 * _t11))));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t3 = (float) Math.cosFromSin(_t2, angleY);
        float _t4 = (float) Math.cosFromSin(_t0, angleX);
        float _t5 = (float) Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        float _t10 = _t0 * _t5;
        float _t11 = _t2 * _t5;
        float _t15 = _t3 * _t5;
        float _t16 = _t4 * _t5;
        float _t18 = Math.fma(_t9, _t3, _t0 * _t2);
        float _t19 = Math.fma(_t6, _t2, _t4 * _t3);
        float _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        float _t21 = Math.fma(_t9, _t2, -(_t0 * _t3));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t20, Math.fma(_self00, _t15, _self01 * _t18)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t10, Math.fma(_self01, _t16, -(_self00 * _t1))));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t19, Math.fma(_self00, _t11, _self01 * _t21)));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t20, Math.fma(_self10, _t15, _self11 * _t18)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t10, Math.fma(_self11, _t16, -(_self10 * _t1))));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t19, Math.fma(_self10, _t11, _self11 * _t21)));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t20, Math.fma(_self20, _t15, _self21 * _t18)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t10, Math.fma(_self21, _t16, -(_self20 * _t1))));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t19, Math.fma(_self20, _t11, _self21 * _t21)));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, Math.fma(_self00, _t1, -(_self02 * _t0)));
        dest.putFloat(destOffset + 4, _self01);
        dest.putFloat(destOffset + 8, Math.fma(_self00, _t0, _self02 * _t1));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self10, _t1, -(_self12 * _t0)));
        dest.putFloat(destOffset + 20, _self11);
        dest.putFloat(destOffset + 24, Math.fma(_self10, _t0, _self12 * _t1));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self20, _t1, -(_self22 * _t0)));
        dest.putFloat(destOffset + 36, _self21);
        dest.putFloat(destOffset + 40, Math.fma(_self20, _t0, _self22 * _t1));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t10 = _t2 * _t5;
        float _t12 = _t1 * _t5;
        float _t16 = _t5 * _t4;
        float _t17 = _t5 * _t3;
        float _t18 = Math.fma(_t6, _t2, _t3 * _t4);
        float _t19 = Math.fma(_t8, _t4, _t1 * _t2);
        float _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        float _t21 = Math.fma(_t6, _t4, -(_t2 * _t3));
        dest.putFloat(destOffset + 0, Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0))));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0))));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0))));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t3 = (float) Math.cosFromSin(_t1, angleZ);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        float _t9 = _t1 * _t4;
        float _t11 = _t2 * _t3;
        float _t13 = _t4 * _t3;
        float _t14 = _t5 * _t3;
        float _t18 = Math.fma(_t6, _t5, _t2 * _t4);
        float _t19 = Math.fma(_t9, _t2, _t0 * _t5);
        float _t20 = Math.fma(_t2, _t0, -(_t9 * _t5));
        float _t21 = Math.fma(_t5, _t4, -(_t6 * _t2));
        dest.putFloat(destOffset + 0, Math.fma(-_self02, _t7, Math.fma(_self00, _t13, _self01 * _t1)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t18, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t21, Math.fma(_self00, _t19, -(_self01 * _t11))));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(-_self12, _t7, Math.fma(_self10, _t13, _self11 * _t1)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t18, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t21, Math.fma(_self10, _t19, -(_self11 * _t11))));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(-_self22, _t7, Math.fma(_self20, _t13, _self21 * _t1)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t18, Math.fma(_self20, _t20, _self21 * _t14)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t21, Math.fma(_self20, _t19, -(_self21 * _t11))));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, Math.fma(_self00, _t1, _self01 * _t0));
        dest.putFloat(destOffset + 4, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.putFloat(destOffset + 8, _self02);
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(_self10, _t1, _self11 * _t0));
        dest.putFloat(destOffset + 20, Math.fma(_self11, _t1, -(_self10 * _t0)));
        dest.putFloat(destOffset + 24, _self12);
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(_self20, _t1, _self21 * _t0));
        dest.putFloat(destOffset + 36, Math.fma(_self21, _t1, -(_self20 * _t0)));
        dest.putFloat(destOffset + 40, _self22);
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t3 = (float) Math.cosFromSin(_t1, angleX);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        float _t5 = (float) Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t7 = _t0 * _t3;
        float _t8 = _t1 * _t5;
        float _t10 = _t2 * _t3;
        float _t14 = _t3 * _t5;
        float _t15 = _t3 * _t4;
        float _t18 = Math.fma(_t8, _t0, _t2 * _t4);
        float _t19 = Math.fma(_t6, _t4, _t0 * _t5);
        float _t20 = Math.fma(_t4, _t5, -(_t6 * _t0));
        float _t21 = Math.fma(_t0, _t2, -(_t8 * _t4));
        dest.putFloat(destOffset + 0, Math.fma(-_self02, _t7, Math.fma(_self00, _t20, _self01 * _t18)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t1, Math.fma(_self01, _t14, -(_self00 * _t10))));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t15, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(-_self12, _t7, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t1, Math.fma(_self11, _t14, -(_self10 * _t10))));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t15, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(-_self22, _t7, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t1, Math.fma(_self21, _t14, -(_self20 * _t10))));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t15, Math.fma(_self20, _t19, _self21 * _t21)));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t1 * _t3;
        float _t9 = _t2 * _t3;
        float _t10 = _t0 * _t4;
        float _t15 = _t3 * _t4;
        float _t17 = _t5 * _t3;
        float _t18 = Math.fma(_t6, _t2, _t5 * _t4);
        float _t19 = Math.fma(_t10, _t5, _t2 * _t1);
        float _t20 = Math.fma(_t10, _t2, -(_t1 * _t5));
        float _t21 = Math.fma(_t6, _t5, -(_t2 * _t4));
        dest.putFloat(destOffset + 0, Math.fma(-_self02, _t0, Math.fma(_self00, _t15, _self01 * _t8)));
        dest.putFloat(destOffset + 4, Math.fma(_self02, _t9, Math.fma(_self00, _t20, _self01 * _t18)));
        dest.putFloat(destOffset + 8, Math.fma(_self02, _t17, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.putFloat(destOffset + 12, _self03);
        dest.putFloat(destOffset + 16, Math.fma(-_self12, _t0, Math.fma(_self10, _t15, _self11 * _t8)));
        dest.putFloat(destOffset + 20, Math.fma(_self12, _t9, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.putFloat(destOffset + 24, Math.fma(_self12, _t17, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.putFloat(destOffset + 28, _self13);
        dest.putFloat(destOffset + 32, Math.fma(-_self22, _t0, Math.fma(_self20, _t15, _self21 * _t8)));
        dest.putFloat(destOffset + 36, Math.fma(_self22, _t9, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.putFloat(destOffset + 40, Math.fma(_self22, _t17, Math.fma(_self20, _t19, _self21 * _t21)));
        dest.putFloat(destOffset + 44, _self23);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0 * vX);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1 * vY);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2 * vZ);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0 * _vx);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1 * _vy);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2 * _vz);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, s * _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, s * _eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, s * _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, _eself3);
        }
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _t3 = pivotZ * _t0;
        dest.putFloat(destOffset + 0, s * _self00);
        dest.putFloat(destOffset + 4, s * _self01);
        dest.putFloat(destOffset + 8, s * _self02);
        dest.putFloat(destOffset + 12, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        dest.putFloat(destOffset + 16, s * _self10);
        dest.putFloat(destOffset + 20, s * _self11);
        dest.putFloat(destOffset + 24, s * _self12);
        dest.putFloat(destOffset + 28, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        dest.putFloat(destOffset + 32, s * _self20);
        dest.putFloat(destOffset + 36, s * _self21);
        dest.putFloat(destOffset + 40, s * _self22);
        dest.putFloat(destOffset + 44, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _pivotz = pivot.getFloat(pivotOffset + 8);
        float _t0 = 1.0f - s;
        float _t1 = _pivotx * _t0;
        float _t2 = _pivoty * _t0;
        float _t3 = _pivotz * _t0;
        dest.putFloat(destOffset + 0, s * _self00);
        dest.putFloat(destOffset + 4, s * _self01);
        dest.putFloat(destOffset + 8, s * _self02);
        dest.putFloat(destOffset + 12, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        dest.putFloat(destOffset + 16, s * _self10);
        dest.putFloat(destOffset + 20, s * _self11);
        dest.putFloat(destOffset + 24, s * _self12);
        dest.putFloat(destOffset + 28, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        dest.putFloat(destOffset + 32, s * _self20);
        dest.putFloat(destOffset + 36, s * _self21);
        dest.putFloat(destOffset + 40, s * _self22);
        dest.putFloat(destOffset + 44, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        dest.putFloat(destOffset + 0, sX * _self00);
        dest.putFloat(destOffset + 4, sY * _self01);
        dest.putFloat(destOffset + 8, sZ * _self02);
        dest.putFloat(destOffset + 12, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        dest.putFloat(destOffset + 16, sX * _self10);
        dest.putFloat(destOffset + 20, sY * _self11);
        dest.putFloat(destOffset + 24, sZ * _self12);
        dest.putFloat(destOffset + 28, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        dest.putFloat(destOffset + 32, sX * _self20);
        dest.putFloat(destOffset + 36, sY * _self21);
        dest.putFloat(destOffset + 40, sZ * _self22);
        dest.putFloat(destOffset + 44, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _sx = s.getFloat(sOffset + 0);
        float _sy = s.getFloat(sOffset + 4);
        float _sz = s.getFloat(sOffset + 8);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _pivotz = pivot.getFloat(pivotOffset + 8);
        float _t3 = _pivotx * (1.0f - _sx);
        float _t4 = _pivoty * (1.0f - _sy);
        float _t5 = _pivotz * (1.0f - _sz);
        dest.putFloat(destOffset + 0, _sx * _self00);
        dest.putFloat(destOffset + 4, _sy * _self01);
        dest.putFloat(destOffset + 8, _sz * _self02);
        dest.putFloat(destOffset + 12, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        dest.putFloat(destOffset + 16, _sx * _self10);
        dest.putFloat(destOffset + 20, _sy * _self11);
        dest.putFloat(destOffset + 24, _sz * _self12);
        dest.putFloat(destOffset + 28, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        dest.putFloat(destOffset + 32, _sx * _self20);
        dest.putFloat(destOffset + 36, _sy * _self21);
        dest.putFloat(destOffset + 40, _sz * _self22);
        dest.putFloat(destOffset + 44, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, Math.fma(_eself0, vX, Math.fma(_eself1, vY, Math.fma(_eself2, vZ, _eself3))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x4OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eself2 = src.getFloat(srcOffset + (_lo + 2) * 4);
            float _eself3 = src.getFloat(srcOffset + (_lo + 3) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1);
            dest.putFloat(destOffset + (_lo + 2) * 4, _eself2);
            dest.putFloat(destOffset + (_lo + 3) * 4, Math.fma(_eself0, _vx, Math.fma(_eself1, _vy, Math.fma(_eself2, _vz, _eself3))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mulVec4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ, float vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.mulVec4_unsafe(_destBase, _srcBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ, float vW) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        dest.putFloat(destOffset + 0, Math.fma(_self03, vW, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY))));
        dest.putFloat(destOffset + 4, Math.fma(_self13, vW, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY))));
        dest.putFloat(destOffset + 8, Math.fma(_self23, vW, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY))));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x4OpsKernelsAddress.mulVec4_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _vw = v.getFloat(vOffset + 12);
        dest.putFloat(destOffset + 0, Math.fma(_self03, _vw, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy))));
        dest.putFloat(destOffset + 4, Math.fma(_self13, _vw, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy))));
        dest.putFloat(destOffset + 8, Math.fma(_self23, _vw, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy))));
        return dest;
    }

    public static java.nio.ByteBuffer transformAabb_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.transformAabb_unsafe(_destBase, _srcBase, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.ByteBuffer transformAabb_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
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
        if (Math.min(Math.min(maxX - minX, maxY - minY), maxZ - minZ) < 0.0f) {
            dest.putFloat(destOffset + 0, Float.POSITIVE_INFINITY);
            dest.putFloat(destOffset + 4, Float.POSITIVE_INFINITY);
            dest.putFloat(destOffset + 8, Float.POSITIVE_INFINITY);
            dest.putFloat(destOffset + 12, Float.NEGATIVE_INFINITY);
            dest.putFloat(destOffset + 16, Float.NEGATIVE_INFINITY);
            dest.putFloat(destOffset + 20, Float.NEGATIVE_INFINITY);
        } else {
            dest.putFloat(destOffset + 0, _self03 + Math.min(_t3, _t4) + Math.min(_t5, _t6) + Math.min(_t7, _t8));
            dest.putFloat(destOffset + 4, _self13 + Math.min(_t9, _t10) + Math.min(_t11, _t12) + Math.min(_t13, _t14));
            dest.putFloat(destOffset + 8, _self23 + Math.min(_t15, _t16) + Math.min(_t17, _t18) + Math.min(_t19, _t20));
            dest.putFloat(destOffset + 12, _self03 + Math.max(_t3, _t4) + Math.max(_t5, _t6) + Math.max(_t7, _t8));
            dest.putFloat(destOffset + 16, _self13 + Math.max(_t9, _t10) + Math.max(_t11, _t12) + Math.max(_t13, _t14));
            dest.putFloat(destOffset + 20, _self23 + Math.max(_t15, _t16) + Math.max(_t17, _t18) + Math.max(_t19, _t20));
        }
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        dest.putFloat(destOffset + 0, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        dest.putFloat(destOffset + 4, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        dest.putFloat(destOffset + 8, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        dest.putFloat(destOffset + 0, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        dest.putFloat(destOffset + 4, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        dest.putFloat(destOffset + 8, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        dest.putFloat(destOffset + 0, Math.fma(_self00, vX, Math.fma(_self01, vY, Math.fma(_self02, vZ, _self03))));
        dest.putFloat(destOffset + 4, Math.fma(_self10, vX, Math.fma(_self11, vY, Math.fma(_self12, vZ, _self13))));
        dest.putFloat(destOffset + 8, Math.fma(_self20, vX, Math.fma(_self21, vY, Math.fma(_self22, vZ, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self01 = src.getFloat(srcOffset + 4);
        float _self02 = src.getFloat(srcOffset + 8);
        float _self03 = src.getFloat(srcOffset + 12);
        float _self10 = src.getFloat(srcOffset + 16);
        float _self11 = src.getFloat(srcOffset + 20);
        float _self12 = src.getFloat(srcOffset + 24);
        float _self13 = src.getFloat(srcOffset + 28);
        float _self20 = src.getFloat(srcOffset + 32);
        float _self21 = src.getFloat(srcOffset + 36);
        float _self22 = src.getFloat(srcOffset + 40);
        float _self23 = src.getFloat(srcOffset + 44);
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        dest.putFloat(destOffset + 0, Math.fma(_self00, _vx, Math.fma(_self01, _vy, Math.fma(_self02, _vz, _self03))));
        dest.putFloat(destOffset + 4, Math.fma(_self10, _vx, Math.fma(_self11, _vy, Math.fma(_self12, _vz, _self13))));
        dest.putFloat(destOffset + 8, Math.fma(_self20, _vx, Math.fma(_self21, _vy, Math.fma(_self22, _vz, _self23))));
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset;
        Float3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _matrixBase, _pointsBase, count);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int count) {
        float _m00 = matrix.getFloat(matrixOffset + 0);
        float _m01 = matrix.getFloat(matrixOffset + 4);
        float _m02 = matrix.getFloat(matrixOffset + 8);
        float _m03 = matrix.getFloat(matrixOffset + 12);
        float _m10 = matrix.getFloat(matrixOffset + 16);
        float _m11 = matrix.getFloat(matrixOffset + 20);
        float _m12 = matrix.getFloat(matrixOffset + 24);
        float _m13 = matrix.getFloat(matrixOffset + 28);
        float _m20 = matrix.getFloat(matrixOffset + 32);
        float _m21 = matrix.getFloat(matrixOffset + 36);
        float _m22 = matrix.getFloat(matrixOffset + 40);
        float _m23 = matrix.getFloat(matrixOffset + 44);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * 12;
            int _do = destOffset + _i * 12;
            float px = points.getFloat(_po + 0), py = points.getFloat(_po + 4), pz = points.getFloat(_po + 8);
            dest.putFloat(_do + 0, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            dest.putFloat(_do + 4, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            dest.putFloat(_do + 8, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, int destStride, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int pointsStride, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset;
        Float3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, destStride, _matrixBase, _pointsBase, pointsStride, count);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, int destStride, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int pointsStride, int count) {
        float _m00 = matrix.getFloat(matrixOffset + 0);
        float _m01 = matrix.getFloat(matrixOffset + 4);
        float _m02 = matrix.getFloat(matrixOffset + 8);
        float _m03 = matrix.getFloat(matrixOffset + 12);
        float _m10 = matrix.getFloat(matrixOffset + 16);
        float _m11 = matrix.getFloat(matrixOffset + 20);
        float _m12 = matrix.getFloat(matrixOffset + 24);
        float _m13 = matrix.getFloat(matrixOffset + 28);
        float _m20 = matrix.getFloat(matrixOffset + 32);
        float _m21 = matrix.getFloat(matrixOffset + 36);
        float _m22 = matrix.getFloat(matrixOffset + 40);
        float _m23 = matrix.getFloat(matrixOffset + 44);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * pointsStride;
            int _do = destOffset + _i * destStride;
            float px = points.getFloat(_po + 0), py = points.getFloat(_po + 4), pz = points.getFloat(_po + 8);
            dest.putFloat(_do + 0, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            dest.putFloat(_do + 4, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            dest.putFloat(_do + 8, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset;
        Float3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _matrixBase, _pointsBase, count);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int count) {
        float _m00 = matrix.getFloat(matrixOffset + 0);
        float _m01 = matrix.getFloat(matrixOffset + 4);
        float _m02 = matrix.getFloat(matrixOffset + 8);
        float _m10 = matrix.getFloat(matrixOffset + 16);
        float _m11 = matrix.getFloat(matrixOffset + 20);
        float _m12 = matrix.getFloat(matrixOffset + 24);
        float _m20 = matrix.getFloat(matrixOffset + 32);
        float _m21 = matrix.getFloat(matrixOffset + 36);
        float _m22 = matrix.getFloat(matrixOffset + 40);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * 12;
            int _do = destOffset + _i * 12;
            float px = points.getFloat(_po + 0), py = points.getFloat(_po + 4), pz = points.getFloat(_po + 8);
            dest.putFloat(_do + 0, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            dest.putFloat(_do + 4, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            dest.putFloat(_do + 8, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, int destStride, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int pointsStride, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset;
        Float3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, destStride, _matrixBase, _pointsBase, pointsStride, count);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, int destStride, java.nio.ByteBuffer matrix, int matrixOffset, java.nio.ByteBuffer points, int pointsOffset, int pointsStride, int count) {
        float _m00 = matrix.getFloat(matrixOffset + 0);
        float _m01 = matrix.getFloat(matrixOffset + 4);
        float _m02 = matrix.getFloat(matrixOffset + 8);
        float _m10 = matrix.getFloat(matrixOffset + 16);
        float _m11 = matrix.getFloat(matrixOffset + 20);
        float _m12 = matrix.getFloat(matrixOffset + 24);
        float _m20 = matrix.getFloat(matrixOffset + 32);
        float _m21 = matrix.getFloat(matrixOffset + 36);
        float _m22 = matrix.getFloat(matrixOffset + 40);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * pointsStride;
            int _do = destOffset + _i * destStride;
            float px = points.getFloat(_po + 0), py = points.getFloat(_po + 4), pz = points.getFloat(_po + 8);
            dest.putFloat(_do + 0, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            dest.putFloat(_do + 4, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            dest.putFloat(_do + 8, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer lerpComposeTRSMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer t1, int t1Offset, java.nio.ByteBuffer t2, int t2Offset, java.nio.ByteBuffer q1, int q1Offset, java.nio.ByteBuffer q2, int q2Offset, java.nio.ByteBuffer s1, int s1Offset, java.nio.ByteBuffer s2, int s2Offset, java.nio.ByteBuffer m, int mOffset, float alpha, int count) {
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        long _t2Base = UnsafeOpsHolder.U.getLong(t2, UnsafeCopy.BB_ADDRESS_OFFSET) + t2Offset;
        long _q1Base = UnsafeOpsHolder.U.getLong(q1, UnsafeCopy.BB_ADDRESS_OFFSET) + q1Offset;
        long _q2Base = UnsafeOpsHolder.U.getLong(q2, UnsafeCopy.BB_ADDRESS_OFFSET) + q2Offset;
        long _s1Base = UnsafeOpsHolder.U.getLong(s1, UnsafeCopy.BB_ADDRESS_OFFSET) + s1Offset;
        long _s2Base = UnsafeOpsHolder.U.getLong(s2, UnsafeCopy.BB_ADDRESS_OFFSET) + s2Offset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.lerpComposeTRSMul_unsafe(_destBase, _t1Base, _t2Base, _q1Base, _q2Base, _s1Base, _s2Base, _mBase, alpha, count);
        return dest;
    }

    public static java.nio.ByteBuffer lerpComposeTRSMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer t1, int t1Offset, java.nio.ByteBuffer t2, int t2Offset, java.nio.ByteBuffer q1, int q1Offset, java.nio.ByteBuffer q2, int q2Offset, java.nio.ByteBuffer s1, int s1Offset, java.nio.ByteBuffer s2, int s2Offset, java.nio.ByteBuffer m, int mOffset, float alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _t1o = t1Offset + _i * 12;
            int _t2o = t2Offset + _i * 12;
            int _q1o = q1Offset + _i * 16;
            int _q2o = q2Offset + _i * 16;
            int _s1o = s1Offset + _i * 12;
            int _s2o = s2Offset + _i * 12;
            int _mo = mOffset + _i * 48;
            int _do = destOffset + _i * 48;
            float _ax = t1.getFloat(_t1o + 0), _ay = t1.getFloat(_t1o + 4), _az = t1.getFloat(_t1o + 8);
            float _tx = Math.fma(alpha, t2.getFloat(_t2o + 0) - _ax, _ax);
            float _ty = Math.fma(alpha, t2.getFloat(_t2o + 4) - _ay, _ay);
            float _tz = Math.fma(alpha, t2.getFloat(_t2o + 8) - _az, _az);
            float _bx = s1.getFloat(_s1o + 0), _by = s1.getFloat(_s1o + 4), _bz = s1.getFloat(_s1o + 8);
            float _sx = Math.fma(alpha, s2.getFloat(_s2o + 0) - _bx, _bx);
            float _sy = Math.fma(alpha, s2.getFloat(_s2o + 4) - _by, _by);
            float _sz = Math.fma(alpha, s2.getFloat(_s2o + 8) - _bz, _bz);
            float _ux = q1.getFloat(_q1o + 0), _uy = q1.getFloat(_q1o + 4), _uz = q1.getFloat(_q1o + 8), _uw = q1.getFloat(_q1o + 12);
            float _vx = q2.getFloat(_q2o + 0), _vy = q2.getFloat(_q2o + 4), _vz = q2.getFloat(_q2o + 8), _vw = q2.getFloat(_q2o + 12);
            float _dot = Math.fma(_uw, _vw, Math.fma(_uz, _vz, Math.fma(_ux, _vx, _uy * _vy)));
            if (_dot < 0.0f) { _vx = -_vx; _vy = -_vy; _vz = -_vz; _vw = -_vw; }
            float _qx = Math.fma(alpha, _vx - _ux, _ux);
            float _qy = Math.fma(alpha, _vy - _uy, _uy);
            float _qz = Math.fma(alpha, _vz - _uz, _uz);
            float _qw = Math.fma(alpha, _vw - _uw, _uw);
            float _len2 = (_qx * _qx + _qy * _qy) + (_qz * _qz + _qw * _qw);
            float _ninv = _len2 > 0.0f ? 1.0f / (float) Math.sqrt(_len2) : 0.0f;
            _qx *= _ninv; _qy *= _ninv; _qz *= _ninv; _qw *= _ninv;
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            float _m00 = m.getFloat(_mo + 0), _m01 = m.getFloat(_mo + 4), _m02 = m.getFloat(_mo + 8), _m03 = m.getFloat(_mo + 12);
            float _m10 = m.getFloat(_mo + 16), _m11 = m.getFloat(_mo + 20), _m12 = m.getFloat(_mo + 24), _m13 = m.getFloat(_mo + 28);
            float _m20 = m.getFloat(_mo + 32), _m21 = m.getFloat(_mo + 36), _m22 = m.getFloat(_mo + 40), _m23 = m.getFloat(_mo + 44);
            float _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            float _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            float _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest.putFloat(_do + 0, _e00);
            dest.putFloat(_do + 4, _e01);
            dest.putFloat(_do + 8, _e02);
            dest.putFloat(_do + 12, _e03);
            dest.putFloat(_do + 16, _e10);
            dest.putFloat(_do + 20, _e11);
            dest.putFloat(_do + 24, _e12);
            dest.putFloat(_do + 28, _e13);
            dest.putFloat(_do + 32, _e20);
            dest.putFloat(_do + 36, _e21);
            dest.putFloat(_do + 40, _e22);
            dest.putFloat(_do + 44, _e23);
        }
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset, int count) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.composeTRSMul_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase, count);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _translationo = translationOffset + _i * 12;
            int _rotationo = rotationOffset + _i * 16;
            int _scaleo = scaleOffset + _i * 12;
            int _mo = mOffset + _i * 48;
            int _do = destOffset + _i * 48;
            float _tx = translation.getFloat(_translationo + 0), _ty = translation.getFloat(_translationo + 4), _tz = translation.getFloat(_translationo + 8);
            float _sx = scale.getFloat(_scaleo + 0), _sy = scale.getFloat(_scaleo + 4), _sz = scale.getFloat(_scaleo + 8);
            float _qx = rotation.getFloat(_rotationo + 0), _qy = rotation.getFloat(_rotationo + 4), _qz = rotation.getFloat(_rotationo + 8), _qw = rotation.getFloat(_rotationo + 12);
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            float _m00 = m.getFloat(_mo + 0), _m01 = m.getFloat(_mo + 4), _m02 = m.getFloat(_mo + 8), _m03 = m.getFloat(_mo + 12);
            float _m10 = m.getFloat(_mo + 16), _m11 = m.getFloat(_mo + 20), _m12 = m.getFloat(_mo + 24), _m13 = m.getFloat(_mo + 28);
            float _m20 = m.getFloat(_mo + 32), _m21 = m.getFloat(_mo + 36), _m22 = m.getFloat(_mo + 40), _m23 = m.getFloat(_mo + 44);
            float _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            float _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            float _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest.putFloat(_do + 0, _e00);
            dest.putFloat(_do + 4, _e01);
            dest.putFloat(_do + 8, _e02);
            dest.putFloat(_do + 12, _e03);
            dest.putFloat(_do + 16, _e10);
            dest.putFloat(_do + 20, _e11);
            dest.putFloat(_do + 24, _e12);
            dest.putFloat(_do + 28, _e13);
            dest.putFloat(_do + 32, _e20);
            dest.putFloat(_do + 36, _e21);
            dest.putFloat(_do + 40, _e22);
            dest.putFloat(_do + 44, _e23);
        }
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMulPadded_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3x4OpsKernelsAddress.composeTRSMulPadded_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer composeTRSMulPadded_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer translation, int translationOffset, java.nio.ByteBuffer rotation, int rotationOffset, java.nio.ByteBuffer scale, int scaleOffset, java.nio.ByteBuffer m, int mOffset) {
        float _qx = rotation.getFloat(rotationOffset + 0), _qy = rotation.getFloat(rotationOffset + 4), _qz = rotation.getFloat(rotationOffset + 8), _qw = rotation.getFloat(rotationOffset + 12);
        float _tx = translation.getFloat(translationOffset + 0), _ty = translation.getFloat(translationOffset + 4), _tz = translation.getFloat(translationOffset + 8);
        float _sx = scale.getFloat(scaleOffset + 0), _sy = scale.getFloat(scaleOffset + 4), _sz = scale.getFloat(scaleOffset + 8);
        float _m00 = m.getFloat(mOffset + 0), _m01 = m.getFloat(mOffset + 4), _m02 = m.getFloat(mOffset + 8), _m03 = m.getFloat(mOffset + 12);
        float _m10 = m.getFloat(mOffset + 16), _m11 = m.getFloat(mOffset + 20), _m12 = m.getFloat(mOffset + 24), _m13 = m.getFloat(mOffset + 28);
        float _m20 = m.getFloat(mOffset + 32), _m21 = m.getFloat(mOffset + 36), _m22 = m.getFloat(mOffset + 40), _m23 = m.getFloat(mOffset + 44);
        float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        float _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
        float _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
        float _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
        dest.putFloat(destOffset + 0, _e00);
        dest.putFloat(destOffset + 4, _e01);
        dest.putFloat(destOffset + 8, _e02);
        dest.putFloat(destOffset + 12, _e03);
        dest.putFloat(destOffset + 16, _e10);
        dest.putFloat(destOffset + 20, _e11);
        dest.putFloat(destOffset + 24, _e12);
        dest.putFloat(destOffset + 28, _e13);
        dest.putFloat(destOffset + 32, _e20);
        dest.putFloat(destOffset + 36, _e21);
        dest.putFloat(destOffset + 40, _e22);
        dest.putFloat(destOffset + 44, _e23);
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
