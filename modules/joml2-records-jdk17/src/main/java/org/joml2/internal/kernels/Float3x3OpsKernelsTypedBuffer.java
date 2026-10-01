// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3x3Ops} whose leading storage
 * parameter is a typed {@link java.nio.FloatBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float3x3Ops} and its sibling kernel units. Not public API.
 */
public final class Float3x3OpsKernelsTypedBuffer {
    private Float3x3OpsKernelsTypedBuffer() {}

    public static java.nio.FloatBuffer getColumn_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.nio.FloatBuffer getColumn_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getColumn(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, col);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getColumn_apiGet(dest, destOffset, src, srcOffset, col);
        return dest;
    }

    public static java.nio.FloatBuffer getColumn_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; _idxSw2 = _self20; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; _idxSw2 = _self21; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.put(destOffset + 0, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        dest.put(destOffset + 2, _idxSw2);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getEulerAnglesXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getEulerAnglesXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getEulerAnglesXYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t1 = Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 < Math.fma(_self02, _self02, _t1) * 1.0E-7f) {
            dest.put(destOffset + 0, (float) Math.atan2(_self21, _self11));
            dest.put(destOffset + 2, 0.0f);
        } else {
            dest.put(destOffset + 0, (float) Math.atan2(-_self12, _self22));
            dest.put(destOffset + 2, (float) Math.atan2(-_self01, _self00));
        }
        dest.put(destOffset + 1, (float) Math.atan2(_self02, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXZY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getEulerAnglesXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXZY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getEulerAnglesXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getEulerAnglesXZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXZY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t1 = Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 < Math.fma(_self01, _self01, _t1) * 1.0E-7f) {
            dest.put(destOffset + 0, (float) Math.atan2(-_self12, _self22));
            dest.put(destOffset + 1, 0.0f);
        } else {
            dest.put(destOffset + 0, (float) Math.atan2(_self21, _self11));
            dest.put(destOffset + 1, (float) Math.atan2(_self02, _self00));
        }
        dest.put(destOffset + 2, (float) Math.atan2(-_self01, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getEulerAnglesYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYXZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getEulerAnglesYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getEulerAnglesYXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 4);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t1 = Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 < Math.fma(_self12, _self12, _t1) * 1.0E-7f) {
            dest.put(destOffset + 1, (float) Math.atan2(-_self20, _self00));
            dest.put(destOffset + 2, 0.0f);
        } else {
            dest.put(destOffset + 1, (float) Math.atan2(_self02, _self22));
            dest.put(destOffset + 2, (float) Math.atan2(_self10, _self11));
        }
        dest.put(destOffset + 0, (float) Math.atan2(-_self12, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYZX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getEulerAnglesYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYZX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getEulerAnglesYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getEulerAnglesYZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYZX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 4);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t1 = Math.fma(_self11, _self11, _self12 * _self12);
        if (_t1 < Math.fma(_self10, _self10, _t1) * 1.0E-7f) {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, (float) Math.atan2(_self02, _self22));
        } else {
            dest.put(destOffset + 0, (float) Math.atan2(-_self12, _self11));
            dest.put(destOffset + 1, (float) Math.atan2(-_self20, _self00));
        }
        dest.put(destOffset + 2, (float) Math.atan2(_self10, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZXY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getEulerAnglesZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZXY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getEulerAnglesZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getEulerAnglesZXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZXY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self22 = src.get(srcOffset + 8);
        float _t1 = Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 < Math.fma(_self21, _self21, _t1) * 1.0E-7f) {
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, (float) Math.atan2(_self10, _self00));
        } else {
            dest.put(destOffset + 1, (float) Math.atan2(-_self20, _self22));
            dest.put(destOffset + 2, (float) Math.atan2(-_self01, _self11));
        }
        dest.put(destOffset + 0, (float) Math.atan2(_self21, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZYX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getEulerAnglesZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZYX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getEulerAnglesZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getEulerAnglesZYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZYX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self22 = src.get(srcOffset + 8);
        float _t1 = Math.fma(_self21, _self21, _self22 * _self22);
        if (_t1 < Math.fma(_self20, _self20, _t1) * 1.0E-7f) {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 2, (float) Math.atan2(-_self01, _self11));
        } else {
            dest.put(destOffset + 0, (float) Math.atan2(_self21, _self22));
            dest.put(destOffset + 2, (float) Math.atan2(_self10, _self00));
        }
        dest.put(destOffset + 1, (float) Math.atan2(-_self20, (float) Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.FloatBuffer getNormalizedRotation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getNormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getNormalizedRotation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getNormalizedRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getNormalizedRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getNormalizedRotation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
            dest.put(destOffset + 0, _sp0 * _t36);
            dest.put(destOffset + 1, _sp0 * _t56);
            dest.put(destOffset + 2, _sp0 * _t57);
            dest.put(destOffset + 3, 0.5f * (float) Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dest.put(destOffset + 0, 0.5f * (float) Math.sqrt(_t63));
                dest.put(destOffset + 1, _sp3 * _t53);
                dest.put(destOffset + 2, _sp3 * _t55);
                dest.put(destOffset + 3, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.put(destOffset + 0, _sp1 * _t53);
                    dest.put(destOffset + 1, 0.5f * (float) Math.sqrt(_t64));
                    dest.put(destOffset + 2, _sp1 * _t39);
                    dest.put(destOffset + 3, _sp1 * _t56);
                } else {
                    dest.put(destOffset + 0, _sp2 * _t55);
                    dest.put(destOffset + 1, _sp2 * _t39);
                    dest.put(destOffset + 2, 0.5f * (float) Math.sqrt(_t65));
                    dest.put(destOffset + 3, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer getRow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.nio.FloatBuffer getRow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getRow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getRow_apiGet(dest, destOffset, src, srcOffset, row);
        return dest;
    }

    public static java.nio.FloatBuffer getRow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            case 2: _idxSw0 = _self20; _idxSw1 = _self21; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.put(destOffset + 0, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        dest.put(destOffset + 2, _idxSw2);
        return dest;
    }

    public static java.nio.FloatBuffer getScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getScale_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, (float) Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        dest.put(destOffset + 1, (float) Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        dest.put(destOffset + 2, (float) Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    public static java.nio.FloatBuffer getTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getTranslation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getTranslation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        dest.put(destOffset + 0, _self02);
        dest.put(destOffset + 1, _self12);
        return dest;
    }

    public static java.nio.FloatBuffer getUnnormalizedRotation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.getUnnormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getUnnormalizedRotation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.getUnnormalizedRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.getUnnormalizedRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getUnnormalizedRotation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
            dest.put(destOffset + 0, _sp0 * _t1);
            dest.put(destOffset + 1, _sp0 * _t7);
            dest.put(destOffset + 2, _sp0 * _t9);
            dest.put(destOffset + 3, 0.5f * (float) Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                dest.put(destOffset + 0, 0.5f * (float) Math.sqrt(_t15));
                dest.put(destOffset + 1, _sp3 * _t4);
                dest.put(destOffset + 2, _sp3 * _t6);
                dest.put(destOffset + 3, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    dest.put(destOffset + 0, _sp1 * _t4);
                    dest.put(destOffset + 1, 0.5f * (float) Math.sqrt(_t16));
                    dest.put(destOffset + 2, _sp1 * _t8);
                    dest.put(destOffset + 3, _sp1 * _t7);
                } else {
                    dest.put(destOffset + 0, _sp2 * _t6);
                    dest.put(destOffset + 1, _sp2 * _t8);
                    dest.put(destOffset + 2, 0.5f * (float) Math.sqrt(_t17));
                    dest.put(destOffset + 3, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer cofactor_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cofactor_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.cofactor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.cofactor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cofactor_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, Math.fma(_self11, _self22, -(_self12 * _self21)));
        dest.put(destOffset + 1, Math.fma(_self02, _self21, -(_self01 * _self22)));
        dest.put(destOffset + 2, Math.fma(_self01, _self12, -(_self02 * _self11)));
        dest.put(destOffset + 3, Math.fma(_self12, _self20, -(_self10 * _self22)));
        dest.put(destOffset + 4, Math.fma(_self00, _self22, -(_self02 * _self20)));
        dest.put(destOffset + 5, Math.fma(_self02, _self10, -(_self00 * _self12)));
        dest.put(destOffset + 6, Math.fma(_self10, _self21, -(_self11 * _self20)));
        dest.put(destOffset + 7, Math.fma(_self01, _self20, -(_self00 * _self21)));
        dest.put(destOffset + 8, Math.fma(_self00, _self11, -(_self01 * _self10)));
        return dest;
    }

    public static float determinant_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3x3OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static float determinant_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            return Float3x3Ops.determinant(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float3x3OpsKernelsTypedBuffer.determinant_apiGet(src, srcOffset);
    }

    public static float determinant_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        return Math.fma(_self02, Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(_self00, Math.fma(_self11, _self22, -(_self12 * _self21)), -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static float frobeniusNorm_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            return Float3x3Ops.frobeniusNorm(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float3x3OpsKernelsTypedBuffer.frobeniusNorm_apiGet(src, srcOffset);
    }

    public static float frobeniusNorm_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        return (float) Math.sqrt(Math.fma(_self00, _self00, _self01 * _self01) + Math.fma(_self02, _self02, _self10 * _self10) + (Math.fma(_self11, _self11, _self12 * _self12) + Math.fma(_self20, _self20, Math.fma(_self21, _self21, _self22 * _self22))));
    }

    public static java.nio.FloatBuffer invert_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invert_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.invert(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.invert_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invert_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return Float3x3OpsKernelsTypedBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t13_inv = 1.0f / _t13;
        dest.put(destOffset + 0, _t6 * _t13_inv);
        dest.put(destOffset + 1, Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv);
        dest.put(destOffset + 2, _t7 * _t13_inv);
        dest.put(destOffset + 3, Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv);
        dest.put(destOffset + 4, Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv);
        dest.put(destOffset + 5, Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv);
        dest.put(destOffset + 6, Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv);
        dest.put(destOffset + 7, Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv);
        dest.put(destOffset + 8, Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv);
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsTypedBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x3OpsKernelsTypedBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer invert_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3OpsKernelsArray.invert_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.invert_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        float _sp2 = _t1 * _t33_inv;
        float _sp1 = _t0 * _t33_inv;
        float _sp0 = _t2 * _t33_inv;
        dest.put(destOffset + 0, _t27 * _sp0);
        dest.put(destOffset + 1, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0);
        dest.put(destOffset + 2, _t28 * _sp0);
        dest.put(destOffset + 3, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1);
        dest.put(destOffset + 4, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1);
        dest.put(destOffset + 5, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1);
        dest.put(destOffset + 6, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2);
        dest.put(destOffset + 7, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2);
        dest.put(destOffset + 8, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Float3x3Ops.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _other00 = other.get(otherOffset + 0);
        float _other10 = other.get(otherOffset + 1);
        float _other20 = other.get(otherOffset + 2);
        float _other01 = other.get(otherOffset + 3);
        float _other11 = other.get(otherOffset + 4);
        float _other21 = other.get(otherOffset + 5);
        float _other02 = other.get(otherOffset + 6);
        float _other12 = other.get(otherOffset + 7);
        float _other22 = other.get(otherOffset + 8);
        float _t18 = Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11));
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
        if (!(Math.abs(_t40) > 1.1754944E-38f && Math.abs(_t40) < 8.507059E37f)) return Float3x3OpsKernelsTypedBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t40_inv = 1.0f / _t40;
        dest.put(destOffset + 0, _t33 * _t40_inv);
        dest.put(destOffset + 1, Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv);
        dest.put(destOffset + 2, _t34 * _t40_inv);
        dest.put(destOffset + 3, Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv);
        dest.put(destOffset + 4, Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv);
        dest.put(destOffset + 5, Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv);
        dest.put(destOffset + 6, Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv);
        dest.put(destOffset + 7, Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv);
        dest.put(destOffset + 8, Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsTypedBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3x3OpsKernelsTypedBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Float3x3OpsKernelsArray.invertProduct_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.invertProduct_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _other00 = other.get(otherOffset + 0);
        float _other10 = other.get(otherOffset + 1);
        float _other20 = other.get(otherOffset + 2);
        float _other01 = other.get(otherOffset + 3);
        float _other11 = other.get(otherOffset + 4);
        float _other21 = other.get(otherOffset + 5);
        float _other02 = other.get(otherOffset + 6);
        float _other12 = other.get(otherOffset + 7);
        float _other22 = other.get(otherOffset + 8);
        float _t18 = Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11));
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
        float _t45 = _t26 * _t29;
        float _t46 = _t24 * _t29;
        float _t47 = _t25 * _t29;
        float _t54 = Math.fma(_t39, _t40, -(_t41 * _t42));
        float _t55 = Math.fma(_t43, _t41, -(_t44 * _t39));
        float _t60_inv = 1.0f / Math.fma(_t55, _t45, Math.fma(_t54, _t46, -(Math.fma(_t43, _t40, -(_t44 * _t42)) * _t47)));
        float _sp2 = _t28 * _t60_inv;
        float _sp1 = _t27 * _t60_inv;
        float _sp0 = _t29 * _t60_inv;
        dest.put(destOffset + 0, _t54 * _sp0);
        dest.put(destOffset + 1, Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0);
        dest.put(destOffset + 2, _t55 * _sp0);
        dest.put(destOffset + 3, Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1);
        dest.put(destOffset + 4, Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1);
        dest.put(destOffset + 5, Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1);
        dest.put(destOffset + 6, Math.fma(_t47, _t42, -(_t39 * _t45)) * _sp2);
        dest.put(destOffset + 7, Math.fma(_t43, _t45, -(_t46 * _t42)) * _sp2);
        dest.put(destOffset + 8, Math.fma(_t46, _t39, -(_t43 * _t47)) * _sp2);
        return dest;
    }

    public static java.nio.FloatBuffer normal_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normal_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.normal(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.normal_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normal_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(Math.abs(_t13) > 1.1754944E-38f && Math.abs(_t13) < 8.507059E37f)) return Float3x3OpsKernelsTypedBuffer.normal_degenerate(dest, destOffset, src, srcOffset);
        float _t13_inv = 1.0f / _t13;
        dest.put(destOffset + 0, _t6 * _t13_inv);
        dest.put(destOffset + 1, Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv);
        dest.put(destOffset + 2, Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv);
        dest.put(destOffset + 3, Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv);
        dest.put(destOffset + 4, Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv);
        dest.put(destOffset + 5, Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv);
        dest.put(destOffset + 6, _t7 * _t13_inv);
        dest.put(destOffset + 7, Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv);
        dest.put(destOffset + 8, Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv);
        return dest;
    }

    public static java.nio.FloatBuffer normal_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsTypedBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x3OpsKernelsTypedBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer normal_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normal_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3OpsKernelsArray.normal_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.normal_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normal_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        float _sp2 = _t1 * _t33_inv;
        float _sp1 = _t0 * _t33_inv;
        float _sp0 = _t2 * _t33_inv;
        dest.put(destOffset + 0, _t27 * _sp0);
        dest.put(destOffset + 1, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1);
        dest.put(destOffset + 2, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2);
        dest.put(destOffset + 3, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0);
        dest.put(destOffset + 4, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1);
        dest.put(destOffset + 5, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2);
        dest.put(destOffset + 6, _t28 * _sp0);
        dest.put(destOffset + 7, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1);
        dest.put(destOffset + 8, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2);
        return dest;
    }

    public static float trace_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3x3OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static float trace_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            return Float3x3Ops.trace(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float3x3OpsKernelsTypedBuffer.trace_apiGet(src, srcOffset);
    }

    public static float trace_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self11 = src.get(srcOffset + 4);
        float _self22 = src.get(srcOffset + 8);
        return _self22 + (_self00 + _self11);
    }

    public static java.nio.FloatBuffer transpose_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer transpose_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.transpose(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.transpose_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transpose_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, _self10);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, _self12);
        dest.put(destOffset + 6, _self20);
        dest.put(destOffset + 7, _self21);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Float3x3Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = src.get(srcOffset + _i);
            float _eother = other.get(otherOffset + _i);
            dest.put(destOffset + _i, _eother + _eself);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = src.get(srcOffset + _i);
            dest.put(destOffset + _i, scalar * _eself);
        }
        return dest;
    }

    public static java.nio.FloatBuffer negate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = src.get(srcOffset + _i);
            dest.put(destOffset + _i, -_eself);
        }
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Float3x3Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = src.get(srcOffset + _i);
            float _eother = other.get(otherOffset + _i);
            dest.put(destOffset + _i, _eself - _eother);
        }
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 9) {
            Float3x3Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        for (int _i = 0; _i < 9; _i++) {
            float _ev = v.get(vOffset + _i);
            dest.put(destOffset + _i, _ev);
        }
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 4L;
        Float3x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 4) {
            Float3x3Ops.setMat2x2(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.setMat2x2_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        float _m00 = m.get(mOffset + 0);
        float _m10 = m.get(mOffset + 1);
        float _m01 = m.get(mOffset + 2);
        float _m11 = m.get(mOffset + 3);
        dest.put(destOffset + 0, _m00);
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, _m01);
        dest.put(destOffset + 4, _m11);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 4L;
        Float3x3OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 6) {
            Float3x3Ops.setMat2x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.setMat2x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        float _m00 = m.get(mOffset + 0);
        float _m10 = m.get(mOffset + 1);
        float _m01 = m.get(mOffset + 2);
        float _m11 = m.get(mOffset + 3);
        float _m02 = m.get(mOffset + 4);
        float _m12 = m.get(mOffset + 5);
        dest.put(destOffset + 0, _m00);
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, _m01);
        dest.put(destOffset + 4, _m11);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, _m02);
        dest.put(destOffset + 7, _m12);
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 4L;
        Float3x3OpsKernelsAddress.setMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 12) {
            Float3x3Ops.setMat3x4(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.setMat3x4_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        float _m00 = m.get(mOffset + 0);
        float _m01 = m.get(mOffset + 1);
        float _m02 = m.get(mOffset + 2);
        float _m10 = m.get(mOffset + 4);
        float _m11 = m.get(mOffset + 5);
        float _m12 = m.get(mOffset + 6);
        float _m20 = m.get(mOffset + 8);
        float _m21 = m.get(mOffset + 9);
        float _m22 = m.get(mOffset + 10);
        dest.put(destOffset + 0, _m00);
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, _m20);
        dest.put(destOffset + 3, _m01);
        dest.put(destOffset + 4, _m11);
        dest.put(destOffset + 5, _m21);
        dest.put(destOffset + 6, _m02);
        dest.put(destOffset + 7, _m12);
        dest.put(destOffset + 8, _m22);
        return dest;
    }

    public static java.nio.FloatBuffer setMat4x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 4L;
        Float3x3OpsKernelsAddress.setMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer setMat4x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 16) {
            Float3x3Ops.setMat4x4(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.setMat4x4_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer setMat4x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            int _lom = _l * 4;
            float _em0 = m.get(mOffset + _lom);
            float _em1 = m.get(mOffset + _lom + 1);
            float _em2 = m.get(mOffset + _lom + 2);
            dest.put(destOffset + _lo, _em0);
            dest.put(destOffset + _lo + 1, _em1);
            dest.put(destOffset + _lo + 2, _em2);
        }
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float tX, float tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float tX, float tY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.withTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, tX, tY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.withTranslation_apiGet(dest, destOffset, src, srcOffset, tX, tY);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float tX, float tY) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, tX);
        dest.put(destOffset + 7, tY);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) tOffset * 4L;
        Float3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 2) {
            Float3x3Ops.withTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.withTranslation_apiGet(dest, destOffset, src, srcOffset, t, tOffset);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t, int tOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self22 = src.get(srcOffset + 8);
        float _tx = t.get(tOffset + 0);
        float _ty = t.get(tOffset + 1);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, _tx);
        dest.put(destOffset + 7, _ty);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromRigid_unsafe(java.nio.FloatBuffer dest, int destOffset, float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeFromRigid_unsafe(_destBase, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromRigid_api(java.nio.FloatBuffer dest, int destOffset, float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeFromRigid(dest.array(), dest.arrayOffset() + destOffset, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeFromRigid_apiGet(dest, destOffset, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromRigid_apiGet(java.nio.FloatBuffer dest, int destOffset, float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        dest.put(destOffset + 0, Math.fma(-2.0f, Math.fma(rRY, rRY, _t0), 1.0f));
        dest.put(destOffset + 1, 2.0f * Math.fma(rRX, rRY, _t1));
        dest.put(destOffset + 2, 2.0f * Math.fma(rRX, rRZ, -_t2));
        dest.put(destOffset + 3, 2.0f * Math.fma(rRX, rRY, -_t1));
        dest.put(destOffset + 4, Math.fma(-2.0f, Math.fma(rRX, rRX, _t0), 1.0f));
        dest.put(destOffset + 5, 2.0f * Math.fma(rRX, rRW, rRY * rRZ));
        dest.put(destOffset + 6, 2.0f * Math.fma(rRX, rRZ, _t2));
        dest.put(destOffset + 7, 2.0f * Math.fma(rRY, rRZ, -(rRX * rRW)));
        dest.put(destOffset + 8, Math.fma(-2.0f, Math.fma(rRX, rRX, rRY * rRY), 1.0f));
        return dest;
    }

    public static java.nio.FloatBuffer makeFromTransform_unsafe(java.nio.FloatBuffer dest, int destOffset, float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeFromTransform_unsafe(_destBase, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromTransform_api(java.nio.FloatBuffer dest, int destOffset, float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeFromTransform(dest.array(), dest.arrayOffset() + destOffset, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeFromTransform_apiGet(dest, destOffset, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromTransform_apiGet(java.nio.FloatBuffer dest, int destOffset, float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        dest.put(destOffset + 0, Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX));
        dest.put(destOffset + 1, Math.fma(tRX, tRY, _t4) * _t0);
        dest.put(destOffset + 2, Math.fma(tRX, tRZ, -_t5) * _t0);
        dest.put(destOffset + 3, Math.fma(tRX, tRY, -_t4) * _t1);
        dest.put(destOffset + 4, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY));
        dest.put(destOffset + 5, Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        dest.put(destOffset + 6, Math.fma(tRX, tRZ, _t5) * _t2);
        dest.put(destOffset + 7, Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        dest.put(destOffset + 8, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        return dest;
    }

    public static java.nio.FloatBuffer to2x2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer to2x2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.to2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.to2x2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer to2x2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        return dest;
    }

    public static java.nio.FloatBuffer to2x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer to2x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.to2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.to2x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer to2x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _loself = _l * 3;
            float _eself0 = src.get(srcOffset + _loself);
            float _eself1 = src.get(srcOffset + _loself + 1);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, _eself1);
        }
        return dest;
    }

    public static java.nio.FloatBuffer to3x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.to3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer to3x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.to3x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.to3x4_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer to3x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _self10);
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, _self12);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer to4x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.to4x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer to4x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.to4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.to4x4_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer to4x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _self01);
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, _self21);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, _self02);
        dest.put(destOffset + 9, _self12);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, 0.0f);
        dest.put(destOffset + 12, 0.0f);
        dest.put(destOffset + 13, 0.0f);
        dest.put(destOffset + 14, 0.0f);
        dest.put(destOffset + 15, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer toDualQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toDualQuat_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 8 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.toDualQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.toDualQuat_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toDualQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t1 = 1.0f - _self00;
        float _t3 = _self21 - _self12;
        float _t5 = _self01 + _self10;
        float _t6 = _self02 + _self20;
        float _t7 = _self02 - _self20;
        float _t8 = _self12 + _self21;
        float _t9 = _self10 - _self01;
        float _t13 = _self22 + (_self00 + _self11);
        float _t14 = 1.0f + _t13;
        float _t15 = _self00 + (1.0f - _self11 - _self22);
        float _t16 = _self11 + (_t1 - _self22);
        float _t17 = _self22 + (_t1 - _self11);
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t14));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t16));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t17));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t15));
        if (_t13 > 0.0f) {
            dest.put(destOffset + 0, _sp0 * _t3);
            dest.put(destOffset + 1, _sp0 * _t7);
            dest.put(destOffset + 2, _sp0 * _t9);
            dest.put(destOffset + 3, 0.5f * (float) Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                dest.put(destOffset + 0, 0.5f * (float) Math.sqrt(_t15));
                dest.put(destOffset + 1, _sp3 * _t5);
                dest.put(destOffset + 2, _sp3 * _t6);
                dest.put(destOffset + 3, _sp3 * _t3);
            } else {
                if (_self11 > _self22) {
                    dest.put(destOffset + 0, _sp1 * _t5);
                    dest.put(destOffset + 1, 0.5f * (float) Math.sqrt(_t16));
                    dest.put(destOffset + 2, _sp1 * _t8);
                    dest.put(destOffset + 3, _sp1 * _t7);
                } else {
                    dest.put(destOffset + 0, _sp2 * _t6);
                    dest.put(destOffset + 1, _sp2 * _t8);
                    dest.put(destOffset + 2, 0.5f * (float) Math.sqrt(_t17));
                    dest.put(destOffset + 3, _sp2 * _t9);
                }
            }
        }
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.toRigid_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 7 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.toRigid(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.toRigid_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t15 = (1.0f / (float) Math.sqrt(_ct0));
        float _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t16 = (1.0f / (float) Math.sqrt(_ct1));
        float _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
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
            dest.put(destOffset + 3, _sp0 * _t35);
            dest.put(destOffset + 4, _sp0 * _t56);
            dest.put(destOffset + 5, _sp0 * _t57);
            dest.put(destOffset + 6, 0.5f * (float) Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dest.put(destOffset + 3, 0.5f * (float) Math.sqrt(_t67));
                dest.put(destOffset + 4, _sp3 * _t54);
                dest.put(destOffset + 5, _sp3 * _t55);
                dest.put(destOffset + 6, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.put(destOffset + 3, _sp1 * _t54);
                    dest.put(destOffset + 4, 0.5f * (float) Math.sqrt(_t65));
                    dest.put(destOffset + 5, _sp1 * _t31);
                    dest.put(destOffset + 6, _sp1 * _t56);
                } else {
                    dest.put(destOffset + 3, _sp2 * _t55);
                    dest.put(destOffset + 4, _sp2 * _t31);
                    dest.put(destOffset + 5, 0.5f * (float) Math.sqrt(_t66));
                    dest.put(destOffset + 6, _sp2 * _t57);
                }
            }
        }
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsTypedBuffer.toRigid_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x3OpsKernelsTypedBuffer.toRigid_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer toRigid_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.toRigid_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 7 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3OpsKernelsArray.toRigid_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.toRigid_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
            dest.put(destOffset + 3, _sp0 * _t182);
            dest.put(destOffset + 4, _sp0 * _t201);
            dest.put(destOffset + 5, _sp0 * _t202);
            dest.put(destOffset + 6, 0.5f * (float) Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest.put(destOffset + 3, 0.5f * (float) Math.sqrt(_t208));
                dest.put(destOffset + 4, _sp3 * _t199);
                dest.put(destOffset + 5, _sp3 * _t200);
                dest.put(destOffset + 6, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.put(destOffset + 3, _sp1 * _t199);
                    dest.put(destOffset + 4, 0.5f * (float) Math.sqrt(_t209));
                    dest.put(destOffset + 5, _sp1 * _t184);
                    dest.put(destOffset + 6, _sp1 * _t201);
                } else {
                    dest.put(destOffset + 3, _sp2 * _t200);
                    dest.put(destOffset + 4, _sp2 * _t184);
                    dest.put(destOffset + 5, 0.5f * (float) Math.sqrt(_t210));
                    dest.put(destOffset + 6, _sp2 * _t202);
                }
            }
        }
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer toTransform_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.toTransform_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toTransform_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 10 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.toTransform(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.toTransform_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toTransform_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t14 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return Float3x3OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
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
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, _t63 > 0.0f ? _sp0 * _t36 : _t48 > _t37 ? 0.5f * (float) Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        dest.put(destOffset + 4, _t63 > 0.0f ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5f * (float) Math.sqrt(_t66) : _sp2 * _t32);
        dest.put(destOffset + 5, _t63 > 0.0f ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5f * (float) Math.sqrt(_t67));
        dest.put(destOffset + 6, _t63 > 0.0f ? 0.5f * (float) Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        dest.put(destOffset + 7, _t47 < 0.0f ? -_t18 : _t18);
        dest.put(destOffset + 8, (float) Math.sqrt(_t12));
        dest.put(destOffset + 9, (float) Math.sqrt(_t13));
        return dest;
    }

    public static java.nio.FloatBuffer toTransform_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x3OpsKernelsTypedBuffer.toTransform_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x3OpsKernelsTypedBuffer.toTransform_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer toTransform_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.toTransform_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toTransform_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 10 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3OpsKernelsArray.toTransform_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.toTransform_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toTransform_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.put(destOffset + 4, _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) Math.sqrt(_t212) : _sp2 * _t187);
        dest.put(destOffset + 5, _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) Math.sqrt(_t213));
        dest.put(destOffset + 6, _t209 > 0.0f ? 0.5f * (float) Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.put(destOffset + 7, _t196 < 0.0f ? -_t56 : _t56);
        dest.put(destOffset + 8, _t27 <= 0.0f ? 0.0f : (float) Math.sqrt(_t27) / _t0);
        dest.put(destOffset + 9, _t28 <= 0.0f ? 0.0f : (float) Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeRotation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.decomposeRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeRotation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.decomposeRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.decomposeRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeRotation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
            dest.put(destOffset + 0, _sp0 * _t60);
            dest.put(destOffset + 1, _sp0 * _t81);
            dest.put(destOffset + 2, _sp0 * _t78);
            dest.put(destOffset + 3, 0.5f * (float) Math.sqrt(_t86));
        } else {
            if (_t73 > Math.max(_t36, _t56)) {
                dest.put(destOffset + 0, 0.5f * (float) Math.sqrt(_t87));
                dest.put(destOffset + 1, _sp3 * _t77);
                dest.put(destOffset + 2, _sp3 * _t80);
                dest.put(destOffset + 3, _sp3 * _t60);
            } else {
                if (_t36 > _t56) {
                    dest.put(destOffset + 0, _sp1 * _t77);
                    dest.put(destOffset + 1, 0.5f * (float) Math.sqrt(_t88));
                    dest.put(destOffset + 2, _sp1 * _t63);
                    dest.put(destOffset + 3, _sp1 * _t81);
                } else {
                    dest.put(destOffset + 0, _sp2 * _t80);
                    dest.put(destOffset + 1, _sp2 * _t63);
                    dest.put(destOffset + 2, 0.5f * (float) Math.sqrt(_t89));
                    dest.put(destOffset + 3, _sp2 * _t78);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer decomposeScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.decomposeScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.decomposeScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.decomposeScale_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0f ? -_t4 : _t4);
        dest.put(destOffset + 1, (float) Math.sqrt(_t27));
        dest.put(destOffset + 2, (float) Math.sqrt(_t47));
        return dest;
    }

    public static java.nio.FloatBuffer decomposeSkew_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.decomposeSkew_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeSkew_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.decomposeSkew(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.decomposeSkew_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeSkew_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
            dest.put(destOffset + 1, -_t49);
            dest.put(destOffset + 2, -_t28);
        } else {
            dest.put(destOffset + 1, _t49);
            dest.put(destOffset + 2, _t28);
        }
        dest.put(destOffset + 0, _t37 * _t48);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeIdentity(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeIdentity_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Float3x3Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = src.get(srcOffset + _i);
            float _eother = other.get(otherOffset + _i);
            dest.put(destOffset + _i, Math.fma(t, _eother - _eself, _eself));
        }
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rightOffset * 4L;
        Float3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 9) {
            Float3x3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = right.get(rightOffset + _lo);
            float _eright1 = right.get(rightOffset + _lo + 1);
            float _eright2 = right.get(rightOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.put(destOffset + _lo + 1, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.put(destOffset + _lo + 2, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21)));
        }
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rightOffset * 4L;
        Float3x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 4) {
            Float3x3Ops.mulMat2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.mulMat2x2_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _right00 = right.get(rightOffset + 0);
        float _right10 = right.get(rightOffset + 1);
        float _right01 = right.get(rightOffset + 2);
        float _right11 = right.get(rightOffset + 3);
        dest.put(destOffset + 0, Math.fma(_right00, _self00, _right10 * _self01));
        dest.put(destOffset + 1, Math.fma(_right00, _self10, _right10 * _self11));
        dest.put(destOffset + 2, Math.fma(_right00, _self20, _right10 * _self21));
        dest.put(destOffset + 3, Math.fma(_right01, _self00, _right11 * _self01));
        dest.put(destOffset + 4, Math.fma(_right01, _self10, _right11 * _self11));
        dest.put(destOffset + 5, Math.fma(_right01, _self20, _right11 * _self21));
        dest.put(destOffset + 6, _self02);
        dest.put(destOffset + 7, _self12);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rightOffset * 4L;
        Float3x3OpsKernelsAddress.mulMat2x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 6) {
            Float3x3Ops.mulMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.mulMat2x3_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _right00 = right.get(rightOffset + 0);
        float _right10 = right.get(rightOffset + 1);
        float _right01 = right.get(rightOffset + 2);
        float _right11 = right.get(rightOffset + 3);
        float _right02 = right.get(rightOffset + 4);
        float _right12 = right.get(rightOffset + 5);
        dest.put(destOffset + 0, Math.fma(_right00, _self00, _right10 * _self01));
        dest.put(destOffset + 1, Math.fma(_right00, _self10, _right10 * _self11));
        dest.put(destOffset + 2, Math.fma(_right00, _self20, _right10 * _self21));
        dest.put(destOffset + 3, Math.fma(_right01, _self00, _right11 * _self01));
        dest.put(destOffset + 4, Math.fma(_right01, _self10, _right11 * _self11));
        dest.put(destOffset + 5, Math.fma(_right01, _self20, _right11 * _self21));
        dest.put(destOffset + 6, Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02)));
        dest.put(destOffset + 7, Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12)));
        dest.put(destOffset + 8, Math.fma(_right02, _self20, Math.fma(_right12, _self21, _self22)));
        return dest;
    }

    public static java.nio.FloatBuffer preMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Float3x3Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _other00 = other.get(otherOffset + 0);
        float _other10 = other.get(otherOffset + 1);
        float _other20 = other.get(otherOffset + 2);
        float _other01 = other.get(otherOffset + 3);
        float _other11 = other.get(otherOffset + 4);
        float _other21 = other.get(otherOffset + 5);
        float _other02 = other.get(otherOffset + 6);
        float _other12 = other.get(otherOffset + 7);
        float _other22 = other.get(otherOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.put(destOffset + _lo + 1, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.put(destOffset + _lo + 2, Math.fma(_other22, _eself2, Math.fma(_other20, _eself0, _other21 * _eself1)));
        }
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float3x3Ops.preMulMat2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preMulMat2x2_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _other00 = other.get(otherOffset + 0);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_other00, _eself0, _other01 * _eself1));
            dest.put(destOffset + _lo + 1, Math.fma(_other10, _eself0, _other11 * _eself1));
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x3OpsKernelsAddress.preMulMat2x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 6) {
            Float3x3Ops.preMulMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preMulMat2x3_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _other00 = other.get(otherOffset + 0);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        float _other02 = other.get(otherOffset + 4);
        float _other12 = other.get(otherOffset + 5);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.put(destOffset + _lo + 1, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Float3x3Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, weight);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        for (int _i = 0; _i < 9; _i++) {
            float _eself = src.get(srcOffset + _i);
            float _eother = other.get(otherOffset + _i);
            dest.put(destOffset + _i, Math.fma(weight, _eother, _eself));
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, float colX, float colY, float colZ, float rowX, float rowY, float rowZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, colZ, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_api(java.nio.FloatBuffer dest, int destOffset, float colX, float colY, float colZ, float rowX, float rowY, float rowZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeOuterProduct(dest.array(), dest.arrayOffset() + destOffset, colX, colY, colZ, rowX, rowY, rowZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeOuterProduct_apiGet(dest, destOffset, colX, colY, colZ, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, float colX, float colY, float colZ, float rowX, float rowY, float rowZ) {
        dest.put(destOffset + 0, colX * rowX);
        dest.put(destOffset + 1, colY * rowX);
        dest.put(destOffset + 2, colZ * rowX);
        dest.put(destOffset + 3, colX * rowY);
        dest.put(destOffset + 4, colY * rowY);
        dest.put(destOffset + 5, colZ * rowY);
        dest.put(destOffset + 6, colX * rowZ);
        dest.put(destOffset + 7, colY * rowZ);
        dest.put(destOffset + 8, colZ * rowZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer col, int colOffset, java.nio.FloatBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _colBase = UnsafeOpsHolder.U.getLong(col, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) colOffset * 4L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rowOffset * 4L;
        Float3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer col, int colOffset, java.nio.FloatBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && col.hasArray() && colOffset >= 0 && colOffset <= col.limit() - 3 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 3) {
            Float3x3Ops.makeOuterProduct(dest.array(), dest.arrayOffset() + destOffset, col.array(), col.arrayOffset() + colOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeOuterProduct_apiGet(dest, destOffset, col, colOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer col, int colOffset, java.nio.FloatBuffer row, int rowOffset) {
        float _colx = col.get(colOffset + 0);
        float _coly = col.get(colOffset + 1);
        float _colz = col.get(colOffset + 2);
        float _rowx = row.get(rowOffset + 0);
        float _rowy = row.get(rowOffset + 1);
        float _rowz = row.get(rowOffset + 2);
        dest.put(destOffset + 0, _colx * _rowx);
        dest.put(destOffset + 1, _coly * _rowx);
        dest.put(destOffset + 2, _colz * _rowx);
        dest.put(destOffset + 3, _colx * _rowy);
        dest.put(destOffset + 4, _coly * _rowy);
        dest.put(destOffset + 5, _colz * _rowy);
        dest.put(destOffset + 6, _colx * _rowz);
        dest.put(destOffset + 7, _coly * _rowz);
        dest.put(destOffset + 8, _colz * _rowz);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.lookAlong(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.lookAlong_apiGet(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        dest.put(destOffset + 1, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.put(destOffset + 2, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        dest.put(destOffset + 3, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.put(destOffset + 4, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.put(destOffset + 5, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.put(destOffset + 6, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.put(destOffset + 7, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.put(destOffset + 8, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 4L;
        Float3x3OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Float3x3Ops.lookAlong(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.lookAlong_apiGet(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _dirx = dir.get(dirOffset + 0);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset + 0);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        dest.put(destOffset + 1, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.put(destOffset + 2, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        dest.put(destOffset + 3, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.put(destOffset + 4, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.put(destOffset + 5, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.put(destOffset + 6, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.put(destOffset + 7, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.put(destOffset + 8, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        return dest;
    }

    public static java.nio.FloatBuffer makeFromDualQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromDualQuat_api(java.nio.FloatBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeFromDualQuat(dest.array(), dest.arrayOffset() + destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeFromDualQuat_apiGet(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromDualQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = Math.fma(-2.0f, dqRZ * dqRZ, 1.0f);
        dest.put(destOffset + 0, Math.fma(-2.0f, _t0, _t6));
        dest.put(destOffset + 1, 2.0f * Math.fma(dqRX, dqRY, _t2));
        dest.put(destOffset + 2, Math.fma(-2.0f, _t3, _sp0 * dqRZ));
        dest.put(destOffset + 3, Math.fma(-2.0f, _t2, _sp0 * dqRY));
        dest.put(destOffset + 4, Math.fma(-2.0f, _t4, _t6));
        dest.put(destOffset + 5, 2.0f * Math.fma(dqRX, dqRW, _t5));
        dest.put(destOffset + 6, 2.0f * Math.fma(dqRX, dqRZ, _t3));
        dest.put(destOffset + 7, Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5));
        dest.put(destOffset + 8, Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f)));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotation_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotation_api(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotation(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotation_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotation_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _t1);
        dest.put(destOffset + 1, _t0);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, -_t0);
        dest.put(destOffset + 4, _t1);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_api(java.nio.FloatBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisY;
        float _t3 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = 1.0f - _t1;
        dest.put(destOffset + 0, Math.fma(_t5, axisX * axisX, _t1));
        dest.put(destOffset + 1, Math.fma(axisZ, _t0, _t5 * _t2));
        dest.put(destOffset + 2, Math.fma(_t5, _t3, -(axisY * _t0)));
        dest.put(destOffset + 3, Math.fma(_t5, _t2, -(axisZ * _t0)));
        dest.put(destOffset + 4, Math.fma(_t5, axisY * axisY, _t1));
        dest.put(destOffset + 5, Math.fma(axisX, _t0, _t5 * _t4));
        dest.put(destOffset + 6, Math.fma(axisY, _t0, _t5 * _t3));
        dest.put(destOffset + 7, Math.fma(_t5, _t4, -(axisX * _t0)));
        dest.put(destOffset + 8, Math.fma(_t5, axisZ * axisZ, _t1));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Float3x3Ops.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        float _axisx = axis.get(axisOffset + 0);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisy;
        float _t3 = _axisx * _axisz;
        float _t4 = _axisy * _axisz;
        float _t5 = 1.0f - _t1;
        dest.put(destOffset + 0, Math.fma(_t5, _axisx * _axisx, _t1));
        dest.put(destOffset + 1, Math.fma(_axisz, _t0, _t5 * _t2));
        dest.put(destOffset + 2, Math.fma(_t5, _t3, -(_axisy * _t0)));
        dest.put(destOffset + 3, Math.fma(_t5, _t2, -(_axisz * _t0)));
        dest.put(destOffset + 4, Math.fma(_t5, _axisy * _axisy, _t1));
        dest.put(destOffset + 5, Math.fma(_axisx, _t0, _t5 * _t4));
        dest.put(destOffset + 6, Math.fma(_axisy, _t0, _t5 * _t3));
        dest.put(destOffset + 7, Math.fma(_t5, _t4, -(_axisx * _t0)));
        dest.put(destOffset + 8, Math.fma(_t5, _axisz * _axisz, _t1));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_unsafe(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_api(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotationLookAlong(dest.array(), dest.arrayOffset() + destOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationLookAlong_apiGet(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_apiGet(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        dest.put(destOffset + 0, _t37);
        dest.put(destOffset + 1, _t39);
        dest.put(destOffset + 2, _t38);
        dest.put(destOffset + 3, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.put(destOffset + 4, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.put(destOffset + 5, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.put(destOffset + 6, _t12);
        dest.put(destOffset + 7, _t13);
        dest.put(destOffset + 8, _t11);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Float3x3Ops.makeRotationLookAlong(dest.array(), dest.arrayOffset() + destOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationLookAlong_apiGet(dest, destOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        float _dirx = dir.get(dirOffset + 0);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset + 0);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
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
        dest.put(destOffset + 0, _t37);
        dest.put(destOffset + 1, _t39);
        dest.put(destOffset + 2, _t38);
        dest.put(destOffset + 3, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.put(destOffset + 4, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.put(destOffset + 5, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.put(destOffset + 6, _t12);
        dest.put(destOffset + 7, _t13);
        dest.put(destOffset + 8, _t11);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationQuat_api(java.nio.FloatBuffer dest, int destOffset, float qX, float qY, float qZ, float qW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotationQuat(dest.array(), dest.arrayOffset() + destOffset, qX, qY, qZ, qW);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationQuat_apiGet(dest, destOffset, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, float qX, float qY, float qZ, float qW) {
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dest.put(destOffset + 0, Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f));
        dest.put(destOffset + 1, 2.0f * Math.fma(qX, qY, _t1));
        dest.put(destOffset + 2, 2.0f * Math.fma(qX, qZ, -_t2));
        dest.put(destOffset + 3, 2.0f * Math.fma(qX, qY, -_t1));
        dest.put(destOffset + 4, Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f));
        dest.put(destOffset + 5, 2.0f * Math.fma(qX, qW, qY * qZ));
        dest.put(destOffset + 6, 2.0f * Math.fma(qX, qZ, _t2));
        dest.put(destOffset + 7, 2.0f * Math.fma(qY, qZ, -(qX * qW)));
        dest.put(destOffset + 8, Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) qOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, _qBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationQuat_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer q, int qOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && q.hasArray() && qOffset >= 0 && qOffset <= q.limit() - 4) {
            Float3x3Ops.makeRotationQuat(dest.array(), dest.arrayOffset() + destOffset, q.array(), q.arrayOffset() + qOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationQuat_apiGet(dest, destOffset, q, qOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer q, int qOffset) {
        float _qx = q.get(qOffset + 0);
        float _qy = q.get(qOffset + 1);
        float _qz = q.get(qOffset + 2);
        float _qw = q.get(qOffset + 3);
        float _t0 = _qz * _qz;
        float _t1 = _qz * _qw;
        float _t2 = _qy * _qw;
        dest.put(destOffset + 0, Math.fma(-2.0f, Math.fma(_qy, _qy, _t0), 1.0f));
        dest.put(destOffset + 1, 2.0f * Math.fma(_qx, _qy, _t1));
        dest.put(destOffset + 2, 2.0f * Math.fma(_qx, _qz, -_t2));
        dest.put(destOffset + 3, 2.0f * Math.fma(_qx, _qy, -_t1));
        dest.put(destOffset + 4, Math.fma(-2.0f, Math.fma(_qx, _qx, _t0), 1.0f));
        dest.put(destOffset + 5, 2.0f * Math.fma(_qx, _qw, _qy * _qz));
        dest.put(destOffset + 6, 2.0f * Math.fma(_qx, _qz, _t2));
        dest.put(destOffset + 7, 2.0f * Math.fma(_qy, _qz, -(_qx * _qw)));
        dest.put(destOffset + 8, Math.fma(-2.0f, Math.fma(_qx, _qx, _qy * _qy), 1.0f));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationX_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationX_api(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotationX(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationX_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationX_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _t1);
        dest.put(destOffset + 5, _t0);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, -_t0);
        dest.put(destOffset + 8, _t1);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXYZ_api(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotationXYZ(dest.array(), dest.arrayOffset() + destOffset, angleX, angleY, angleZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationXYZ_apiGet(dest, destOffset, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t0;
        float _t7 = _t0 * _t5;
        dest.put(destOffset + 0, _t3 * _t4);
        dest.put(destOffset + 1, Math.fma(_t6, _t4, _t1 * _t5));
        dest.put(destOffset + 2, Math.fma(_t2, _t1, -(_t7 * _t4)));
        dest.put(destOffset + 3, -(_t1 * _t3));
        dest.put(destOffset + 4, Math.fma(_t5, _t4, -(_t6 * _t1)));
        dest.put(destOffset + 5, Math.fma(_t7, _t1, _t2 * _t4));
        dest.put(destOffset + 6, _t0);
        dest.put(destOffset + 7, -(_t2 * _t3));
        dest.put(destOffset + 8, _t5 * _t3);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXZY_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXZY_api(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotationXZY(dest.array(), dest.arrayOffset() + destOffset, angleX, angleZ, angleY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationXZY_apiGet(dest, destOffset, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXZY_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t1 * _t5;
        dest.put(destOffset + 0, _t3 * _t4);
        dest.put(destOffset + 1, Math.fma(_t7, _t3, _t2 * _t0));
        dest.put(destOffset + 2, Math.fma(_t6, _t3, -(_t0 * _t5)));
        dest.put(destOffset + 3, -_t1);
        dest.put(destOffset + 4, _t5 * _t4);
        dest.put(destOffset + 5, _t2 * _t4);
        dest.put(destOffset + 6, _t0 * _t4);
        dest.put(destOffset + 7, Math.fma(_t7, _t0, -(_t2 * _t3)));
        dest.put(destOffset + 8, Math.fma(_t6, _t0, _t5 * _t3));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationY_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationY_api(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotationY(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationY_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationY_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _t1);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, -_t0);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, _t0);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, _t1);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYXZ_api(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotationYXZ(dest.array(), dest.arrayOffset() + destOffset, angleY, angleX, angleZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationYXZ_apiGet(dest, destOffset, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        dest.put(destOffset + 0, Math.fma(_t6, _t2, _t3 * _t4));
        dest.put(destOffset + 1, _t2 * _t5);
        dest.put(destOffset + 2, Math.fma(_t7, _t2, -(_t1 * _t4)));
        dest.put(destOffset + 3, Math.fma(_t6, _t4, -(_t2 * _t3)));
        dest.put(destOffset + 4, _t5 * _t4);
        dest.put(destOffset + 5, Math.fma(_t7, _t4, _t1 * _t2));
        dest.put(destOffset + 6, _t1 * _t5);
        dest.put(destOffset + 7, -_t0);
        dest.put(destOffset + 8, _t5 * _t3);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYZX_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYZX_api(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotationYZX(dest.array(), dest.arrayOffset() + destOffset, angleY, angleZ, angleX);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationYZX_apiGet(dest, destOffset, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYZX_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t1 * _t3;
        dest.put(destOffset + 0, _t3 * _t4);
        dest.put(destOffset + 1, _t1);
        dest.put(destOffset + 2, -(_t0 * _t4));
        dest.put(destOffset + 3, Math.fma(_t2, _t0, -(_t7 * _t5)));
        dest.put(destOffset + 4, _t5 * _t4);
        dest.put(destOffset + 5, Math.fma(_t6, _t5, _t2 * _t3));
        dest.put(destOffset + 6, Math.fma(_t7, _t2, _t0 * _t5));
        dest.put(destOffset + 7, -(_t2 * _t4));
        dest.put(destOffset + 8, Math.fma(_t5, _t3, -(_t6 * _t2)));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZXY_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZXY_api(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotationZXY(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleX, angleY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationZXY_apiGet(dest, destOffset, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZXY_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        dest.put(destOffset + 0, Math.fma(_t3, _t4, -(_t6 * _t0)));
        dest.put(destOffset + 1, Math.fma(_t7, _t0, _t1 * _t3));
        dest.put(destOffset + 2, -(_t0 * _t5));
        dest.put(destOffset + 3, -(_t1 * _t5));
        dest.put(destOffset + 4, _t5 * _t4);
        dest.put(destOffset + 5, _t2);
        dest.put(destOffset + 6, Math.fma(_t6, _t3, _t0 * _t4));
        dest.put(destOffset + 7, Math.fma(_t0, _t1, -(_t7 * _t3)));
        dest.put(destOffset + 8, _t5 * _t3);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZYX_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZYX_api(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeRotationZYX(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleY, angleX);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeRotationZYX_apiGet(dest, destOffset, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZYX_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t3 = (float) Math.cosFromSin(_t0, angleY);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t4;
        dest.put(destOffset + 0, _t3 * _t4);
        dest.put(destOffset + 1, _t1 * _t3);
        dest.put(destOffset + 2, -_t0);
        dest.put(destOffset + 3, Math.fma(_t7, _t2, -(_t1 * _t5)));
        dest.put(destOffset + 4, Math.fma(_t6, _t2, _t5 * _t4));
        dest.put(destOffset + 5, _t2 * _t3);
        dest.put(destOffset + 6, Math.fma(_t7, _t5, _t2 * _t1));
        dest.put(destOffset + 7, Math.fma(_t6, _t5, -(_t2 * _t4)));
        dest.put(destOffset + 8, _t5 * _t3);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, vX, vY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        dest.put(destOffset + 0, vX);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, vY);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float3x3Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, _vx);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _vy);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, float s) {
        dest.put(destOffset + 0, s);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, s);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeTranslation(dest.array(), dest.arrayOffset() + destOffset, vX, vY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeTranslation_apiGet(dest, destOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, vX);
        dest.put(destOffset + 7, vY);
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float3x3Ops.makeTranslation(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeTranslation_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, _vx);
        dest.put(destOffset + 7, _vy);
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeView_unsafe(java.nio.FloatBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.FloatBuffer makeView_api(java.nio.FloatBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Float3x3Ops.makeView(dest.array(), dest.arrayOffset() + destOffset, left, right, bottom, top);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.makeView_apiGet(dest, destOffset, left, right, bottom, top);
        return dest;
    }

    public static java.nio.FloatBuffer makeView_apiGet(java.nio.FloatBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dest.put(destOffset + 0, _t0_inv + _t0_inv);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _t1_inv + _t1_inv);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, -((left + right) * _t0_inv));
        dest.put(destOffset + 7, -((bottom + top) * _t1_inv));
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer preRotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.preRotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preRotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.put(destOffset + 1, Math.fma(_self00, _t0, _self10 * _t1));
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.put(destOffset + 4, Math.fma(_self01, _t0, _self11 * _t1));
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, Math.fma(_self02, _t1, -(_self12 * _t0)));
        dest.put(destOffset + 7, Math.fma(_self02, _t0, _self12 * _t1));
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.preRotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preRotateAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.sin(0.5f * angle);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t3 = (float) Math.cosFromSin(_t0, angle);
        float _t8 = (_t2 + _t2) * _t2;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dest.put(destOffset + 0, Math.fma(_self20, _t9, Math.fma(_self00, _t3, -(_self10 * _t0))));
        dest.put(destOffset + 1, Math.fma(_self20, _t10, Math.fma(_self00, _t0, _self10 * _t3)));
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, Math.fma(_self21, _t9, Math.fma(_self01, _t3, -(_self11 * _t0))));
        dest.put(destOffset + 4, Math.fma(_self21, _t10, Math.fma(_self01, _t0, _self11 * _t3)));
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, Math.fma(_self22, _t9, Math.fma(_self02, _t3, -(_self12 * _t0))));
        dest.put(destOffset + 7, Math.fma(_self22, _t10, Math.fma(_self02, _t0, _self12 * _t3)));
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float3x3Ops.preRotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preRotateAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.sin(0.5f * angle);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _t3 = (float) Math.cosFromSin(_t0, angle);
        float _t8 = (_t2 + _t2) * _t2;
        float _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        float _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        dest.put(destOffset + 0, Math.fma(_self20, _t9, Math.fma(_self00, _t3, -(_self10 * _t0))));
        dest.put(destOffset + 1, Math.fma(_self20, _t10, Math.fma(_self00, _t0, _self10 * _t3)));
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, Math.fma(_self21, _t9, Math.fma(_self01, _t3, -(_self11 * _t0))));
        dest.put(destOffset + 4, Math.fma(_self21, _t10, Math.fma(_self01, _t0, _self11 * _t3)));
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, Math.fma(_self22, _t9, Math.fma(_self02, _t3, -(_self12 * _t0))));
        dest.put(destOffset + 7, Math.fma(_self22, _t10, Math.fma(_self02, _t0, _self12 * _t3)));
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.preRotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preRotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        dest.put(destOffset + 1, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.put(destOffset + 2, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.put(destOffset + 3, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.put(destOffset + 4, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.put(destOffset + 5, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        dest.put(destOffset + 6, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.put(destOffset + 7, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.put(destOffset + 8, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 4L;
        Float3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Float3x3Ops.preRotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preRotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _axisx = axis.get(axisOffset + 0);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
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
        dest.put(destOffset + 0, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        dest.put(destOffset + 1, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.put(destOffset + 2, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.put(destOffset + 3, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.put(destOffset + 4, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.put(destOffset + 5, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        dest.put(destOffset + 6, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.put(destOffset + 7, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.put(destOffset + 8, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.preRotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preRotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, Math.fma(_self10, _t1, -(_self20 * _t0)));
        dest.put(destOffset + 2, Math.fma(_self10, _t0, _self20 * _t1));
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, Math.fma(_self11, _t1, -(_self21 * _t0)));
        dest.put(destOffset + 5, Math.fma(_self11, _t0, _self21 * _t1));
        dest.put(destOffset + 6, _self02);
        dest.put(destOffset + 7, Math.fma(_self12, _t1, -(_self22 * _t0)));
        dest.put(destOffset + 8, Math.fma(_self12, _t0, _self22 * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.preRotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preRotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_self00, _t1, _self20 * _t0));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, Math.fma(_self20, _t1, -(_self00 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self01, _t1, _self21 * _t0));
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, Math.fma(_self21, _t1, -(_self01 * _t0)));
        dest.put(destOffset + 6, Math.fma(_self02, _t1, _self22 * _t0));
        dest.put(destOffset + 7, _self12);
        dest.put(destOffset + 8, Math.fma(_self22, _t1, -(_self02 * _t0)));
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, _eself0 * vX);
            dest.put(destOffset + _lo + 1, _eself1 * vY);
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float3x3Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, _eself0 * _vx);
            dest.put(destOffset + _lo + 1, _eself1 * _vy);
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, s * _eself0);
            dest.put(destOffset + _lo + 1, s * _eself1);
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s, pivotX, pivotY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.put(destOffset + 0, Math.fma(s, _self00, _self20 * _t1));
        dest.put(destOffset + 1, Math.fma(s, _self10, _self20 * _t2));
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, Math.fma(s, _self01, _self21 * _t1));
        dest.put(destOffset + 4, Math.fma(s, _self11, _self21 * _t2));
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, Math.fma(s, _self02, _self22 * _t1));
        dest.put(destOffset + 7, Math.fma(s, _self12, _self22 * _t2));
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float3x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, s);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _t0 = 1.0f - s;
        float _t1 = _pivotx * _t0;
        float _t2 = _pivoty * _t0;
        dest.put(destOffset + 0, Math.fma(s, _self00, _self20 * _t1));
        dest.put(destOffset + 1, Math.fma(s, _self10, _self20 * _t2));
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, Math.fma(s, _self01, _self21 * _t1));
        dest.put(destOffset + 4, Math.fma(s, _self11, _self21 * _t2));
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, Math.fma(s, _self02, _self22 * _t1));
        dest.put(destOffset + 7, Math.fma(s, _self12, _self22 * _t2));
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sX, sY, pivotX, pivotY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.put(destOffset + 0, Math.fma(sX, _self00, _self20 * _t2));
        dest.put(destOffset + 1, Math.fma(sY, _self10, _self20 * _t3));
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, Math.fma(sX, _self01, _self21 * _t2));
        dest.put(destOffset + 4, Math.fma(sY, _self11, _self21 * _t3));
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, Math.fma(sX, _self02, _self22 * _t2));
        dest.put(destOffset + 7, Math.fma(sY, _self12, _self22 * _t3));
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) sOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && s.hasArray() && sOffset >= 0 && sOffset <= s.limit() - 2 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float3x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s.array(), s.arrayOffset() + sOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _sx = s.get(sOffset + 0);
        float _sy = s.get(sOffset + 1);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _t2 = _pivotx * (1.0f - _sx);
        float _t3 = _pivoty * (1.0f - _sy);
        dest.put(destOffset + 0, Math.fma(_sx, _self00, _self20 * _t2));
        dest.put(destOffset + 1, Math.fma(_sy, _self10, _self20 * _t3));
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, Math.fma(_sx, _self01, _self21 * _t2));
        dest.put(destOffset + 4, Math.fma(_sy, _self11, _self21 * _t3));
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, Math.fma(_sx, _self02, _self22 * _t2));
        dest.put(destOffset + 7, Math.fma(_sy, _self12, _self22 * _t3));
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.preTranslate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preTranslate_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_eself2, vX, _eself0));
            dest.put(destOffset + _lo + 1, Math.fma(_eself2, vY, _eself1));
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float3x3Ops.preTranslate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.preTranslate_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_eself2, _vx, _eself0));
            dest.put(destOffset + _lo + 1, Math.fma(_eself2, _vy, _eself1));
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.FloatBuffer rotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_self00, _t1, _self01 * _t0));
        dest.put(destOffset + 1, Math.fma(_self10, _t1, _self11 * _t0));
        dest.put(destOffset + 2, Math.fma(_self20, _t1, _self21 * _t0));
        dest.put(destOffset + 3, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.put(destOffset + 4, Math.fma(_self11, _t1, -(_self10 * _t0)));
        dest.put(destOffset + 5, Math.fma(_self21, _t1, -(_self20 * _t0)));
        dest.put(destOffset + 6, _self02);
        dest.put(destOffset + 7, _self12);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = (float) Math.sin(angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dest.put(destOffset + 0, Math.fma(_self00, _t2, _self01 * _t0));
        dest.put(destOffset + 1, Math.fma(_self10, _t2, _self11 * _t0));
        dest.put(destOffset + 2, Math.fma(_self20, _t2, _self21 * _t0));
        dest.put(destOffset + 3, Math.fma(_self01, _t2, -(_self00 * _t0)));
        dest.put(destOffset + 4, Math.fma(_self11, _t2, -(_self10 * _t0)));
        dest.put(destOffset + 5, Math.fma(_self21, _t2, -(_self20 * _t0)));
        dest.put(destOffset + 6, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        dest.put(destOffset + 7, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        dest.put(destOffset + 8, Math.fma(_self20, _t9, Math.fma(_self21, _t10, _self22)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float3x3Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        float _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        dest.put(destOffset + 0, Math.fma(_self00, _t2, _self01 * _t0));
        dest.put(destOffset + 1, Math.fma(_self10, _t2, _self11 * _t0));
        dest.put(destOffset + 2, Math.fma(_self20, _t2, _self21 * _t0));
        dest.put(destOffset + 3, Math.fma(_self01, _t2, -(_self00 * _t0)));
        dest.put(destOffset + 4, Math.fma(_self11, _t2, -(_self10 * _t0)));
        dest.put(destOffset + 5, Math.fma(_self21, _t2, -(_self20 * _t0)));
        dest.put(destOffset + 6, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        dest.put(destOffset + 7, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        dest.put(destOffset + 8, Math.fma(_self20, _t9, Math.fma(_self21, _t10, _self22)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.put(destOffset + 1, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.put(destOffset + 2, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.put(destOffset + 3, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.put(destOffset + 4, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.put(destOffset + 5, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.put(destOffset + 6, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.put(destOffset + 7, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.put(destOffset + 8, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 4L;
        Float3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Float3x3Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _axisx = axis.get(axisOffset + 0);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.put(destOffset + 1, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.put(destOffset + 2, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.put(destOffset + 3, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.put(destOffset + 4, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.put(destOffset + 5, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.put(destOffset + 6, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.put(destOffset + 7, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.put(destOffset + 8, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, Math.fma(_self01, _t1, _self02 * _t0));
        dest.put(destOffset + 4, Math.fma(_self11, _t1, _self12 * _t0));
        dest.put(destOffset + 5, Math.fma(_self21, _t1, _self22 * _t0));
        dest.put(destOffset + 6, Math.fma(_self02, _t1, -(_self01 * _t0)));
        dest.put(destOffset + 7, Math.fma(_self12, _t1, -(_self11 * _t0)));
        dest.put(destOffset + 8, Math.fma(_self22, _t1, -(_self21 * _t0)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateX180_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateX180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX180_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateX180(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateX180_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX180_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, -_self01);
        dest.put(destOffset + 4, -_self11);
        dest.put(destOffset + 5, -_self21);
        dest.put(destOffset + 6, -_self02);
        dest.put(destOffset + 7, -_self12);
        dest.put(destOffset + 8, -_self22);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX270_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateX270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX270_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateX270(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateX270_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX270_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, -_self02);
        dest.put(destOffset + 4, -_self12);
        dest.put(destOffset + 5, -_self22);
        dest.put(destOffset + 6, _self01);
        dest.put(destOffset + 7, _self11);
        dest.put(destOffset + 8, _self21);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX90_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateX90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX90_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateX90(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateX90_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX90_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, _self02);
        dest.put(destOffset + 4, _self12);
        dest.put(destOffset + 5, _self22);
        dest.put(destOffset + 6, -_self01);
        dest.put(destOffset + 7, -_self11);
        dest.put(destOffset + 8, -_self21);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXYZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleY, angleZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateXYZ_apiGet(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t20, Math.fma(_self00, _t13, _self01 * _t18)));
        dest.put(destOffset + 1, Math.fma(_self12, _t20, Math.fma(_self10, _t13, _self11 * _t18)));
        dest.put(destOffset + 2, Math.fma(_self22, _t20, Math.fma(_self20, _t13, _self21 * _t18)));
        dest.put(destOffset + 3, Math.fma(_self02, _t19, Math.fma(_self01, _t21, -(_self00 * _t10))));
        dest.put(destOffset + 4, Math.fma(_self12, _t19, Math.fma(_self11, _t21, -(_self10 * _t10))));
        dest.put(destOffset + 5, Math.fma(_self22, _t19, Math.fma(_self21, _t21, -(_self20 * _t10))));
        dest.put(destOffset + 6, Math.fma(_self02, _t15, Math.fma(_self00, _t2, -(_self01 * _t11))));
        dest.put(destOffset + 7, Math.fma(_self12, _t15, Math.fma(_self10, _t2, -(_self11 * _t11))));
        dest.put(destOffset + 8, Math.fma(_self22, _t15, Math.fma(_self20, _t2, -(_self21 * _t11))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateXZY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXZY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleZ, angleY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateXZY_apiGet(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXZY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t20, Math.fma(_self00, _t15, _self01 * _t18)));
        dest.put(destOffset + 1, Math.fma(_self12, _t20, Math.fma(_self10, _t15, _self11 * _t18)));
        dest.put(destOffset + 2, Math.fma(_self22, _t20, Math.fma(_self20, _t15, _self21 * _t18)));
        dest.put(destOffset + 3, Math.fma(_self02, _t10, Math.fma(_self01, _t16, -(_self00 * _t1))));
        dest.put(destOffset + 4, Math.fma(_self12, _t10, Math.fma(_self11, _t16, -(_self10 * _t1))));
        dest.put(destOffset + 5, Math.fma(_self22, _t10, Math.fma(_self21, _t16, -(_self20 * _t1))));
        dest.put(destOffset + 6, Math.fma(_self02, _t19, Math.fma(_self00, _t11, _self01 * _t21)));
        dest.put(destOffset + 7, Math.fma(_self12, _t19, Math.fma(_self10, _t11, _self11 * _t21)));
        dest.put(destOffset + 8, Math.fma(_self22, _t19, Math.fma(_self20, _t11, _self21 * _t21)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_self00, _t1, -(_self02 * _t0)));
        dest.put(destOffset + 1, Math.fma(_self10, _t1, -(_self12 * _t0)));
        dest.put(destOffset + 2, Math.fma(_self20, _t1, -(_self22 * _t0)));
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, Math.fma(_self00, _t0, _self02 * _t1));
        dest.put(destOffset + 7, Math.fma(_self10, _t0, _self12 * _t1));
        dest.put(destOffset + 8, Math.fma(_self20, _t0, _self22 * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer rotateY180_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateY180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY180_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateY180(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateY180_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY180_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, -_self00);
        dest.put(destOffset + 1, -_self10);
        dest.put(destOffset + 2, -_self20);
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, -_self02);
        dest.put(destOffset + 7, -_self12);
        dest.put(destOffset + 8, -_self22);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY270_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateY270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY270_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateY270(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateY270_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY270_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self02);
        dest.put(destOffset + 1, _self12);
        dest.put(destOffset + 2, _self22);
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, -_self00);
        dest.put(destOffset + 7, -_self10);
        dest.put(destOffset + 8, -_self20);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY90_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateY90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY90_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateY90(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateY90_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY90_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, -_self02);
        dest.put(destOffset + 1, -_self12);
        dest.put(destOffset + 2, -_self22);
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, _self00);
        dest.put(destOffset + 7, _self10);
        dest.put(destOffset + 8, _self20);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYXZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleX, angleZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateYXZ_apiGet(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10)));
        dest.put(destOffset + 1, Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10)));
        dest.put(destOffset + 2, Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10)));
        dest.put(destOffset + 3, Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16)));
        dest.put(destOffset + 4, Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16)));
        dest.put(destOffset + 5, Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16)));
        dest.put(destOffset + 6, Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0))));
        dest.put(destOffset + 7, Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0))));
        dest.put(destOffset + 8, Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateYZX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYZX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleZ, angleX);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateYZX_apiGet(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYZX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, Math.fma(-_self02, _t7, Math.fma(_self00, _t13, _self01 * _t1)));
        dest.put(destOffset + 1, Math.fma(-_self12, _t7, Math.fma(_self10, _t13, _self11 * _t1)));
        dest.put(destOffset + 2, Math.fma(-_self22, _t7, Math.fma(_self20, _t13, _self21 * _t1)));
        dest.put(destOffset + 3, Math.fma(_self02, _t18, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.put(destOffset + 4, Math.fma(_self12, _t18, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.put(destOffset + 5, Math.fma(_self22, _t18, Math.fma(_self20, _t20, _self21 * _t14)));
        dest.put(destOffset + 6, Math.fma(_self02, _t21, Math.fma(_self00, _t19, -(_self01 * _t11))));
        dest.put(destOffset + 7, Math.fma(_self12, _t21, Math.fma(_self10, _t19, -(_self11 * _t11))));
        dest.put(destOffset + 8, Math.fma(_self22, _t21, Math.fma(_self20, _t19, -(_self21 * _t11))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ180_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateZ180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ180_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateZ180(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateZ180_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ180_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, -_self00);
        dest.put(destOffset + 1, -_self10);
        dest.put(destOffset + 2, -_self20);
        dest.put(destOffset + 3, -_self01);
        dest.put(destOffset + 4, -_self11);
        dest.put(destOffset + 5, -_self21);
        dest.put(destOffset + 6, _self02);
        dest.put(destOffset + 7, _self12);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ270_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateZ270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ270_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateZ270(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateZ270_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ270_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, -_self01);
        dest.put(destOffset + 1, -_self11);
        dest.put(destOffset + 2, -_self21);
        dest.put(destOffset + 3, _self00);
        dest.put(destOffset + 4, _self10);
        dest.put(destOffset + 5, _self20);
        dest.put(destOffset + 6, _self02);
        dest.put(destOffset + 7, _self12);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ90_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateZ90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ90_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateZ90(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateZ90_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ90_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self01);
        dest.put(destOffset + 1, _self11);
        dest.put(destOffset + 2, _self21);
        dest.put(destOffset + 3, -_self00);
        dest.put(destOffset + 4, -_self10);
        dest.put(destOffset + 5, -_self20);
        dest.put(destOffset + 6, _self02);
        dest.put(destOffset + 7, _self12);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZXY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZXY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleX, angleY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateZXY_apiGet(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZXY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, Math.fma(-_self02, _t7, Math.fma(_self00, _t20, _self01 * _t18)));
        dest.put(destOffset + 1, Math.fma(-_self12, _t7, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.put(destOffset + 2, Math.fma(-_self22, _t7, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.put(destOffset + 3, Math.fma(_self02, _t1, Math.fma(_self01, _t14, -(_self00 * _t10))));
        dest.put(destOffset + 4, Math.fma(_self12, _t1, Math.fma(_self11, _t14, -(_self10 * _t10))));
        dest.put(destOffset + 5, Math.fma(_self22, _t1, Math.fma(_self21, _t14, -(_self20 * _t10))));
        dest.put(destOffset + 6, Math.fma(_self02, _t15, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.put(destOffset + 7, Math.fma(_self12, _t15, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.put(destOffset + 8, Math.fma(_self22, _t15, Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateZYX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZYX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.rotateZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleY, angleX);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.rotateZYX_apiGet(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZYX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, Math.fma(-_self02, _t0, Math.fma(_self00, _t15, _self01 * _t8)));
        dest.put(destOffset + 1, Math.fma(-_self12, _t0, Math.fma(_self10, _t15, _self11 * _t8)));
        dest.put(destOffset + 2, Math.fma(-_self22, _t0, Math.fma(_self20, _t15, _self21 * _t8)));
        dest.put(destOffset + 3, Math.fma(_self02, _t9, Math.fma(_self00, _t20, _self01 * _t18)));
        dest.put(destOffset + 4, Math.fma(_self12, _t9, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.put(destOffset + 5, Math.fma(_self22, _t9, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.put(destOffset + 6, Math.fma(_self02, _t17, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.put(destOffset + 7, Math.fma(_self12, _t17, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.put(destOffset + 8, Math.fma(_self22, _t17, Math.fma(_self20, _t19, _self21 * _t21)));
        return dest;
    }

    public static java.nio.FloatBuffer scale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer scale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer scale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00 * vX);
        dest.put(destOffset + 1, _self10 * vX);
        dest.put(destOffset + 2, _self20 * vX);
        dest.put(destOffset + 3, _self01 * vY);
        dest.put(destOffset + 4, _self11 * vY);
        dest.put(destOffset + 5, _self21 * vY);
        dest.put(destOffset + 6, _self02);
        dest.put(destOffset + 7, _self12);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer scale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer scale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float3x3Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer scale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, _self00 * _vx);
        dest.put(destOffset + 1, _self10 * _vx);
        dest.put(destOffset + 2, _self20 * _vx);
        dest.put(destOffset + 3, _self01 * _vy);
        dest.put(destOffset + 4, _self11 * _vy);
        dest.put(destOffset + 5, _self21 * _vy);
        dest.put(destOffset + 6, _self02);
        dest.put(destOffset + 7, _self12);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer scale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer scale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer scale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, s * _self00);
        dest.put(destOffset + 1, s * _self10);
        dest.put(destOffset + 2, s * _self20);
        dest.put(destOffset + 3, s * _self01);
        dest.put(destOffset + 4, s * _self11);
        dest.put(destOffset + 5, s * _self21);
        dest.put(destOffset + 6, _self02);
        dest.put(destOffset + 7, _self12);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s, pivotX, pivotY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.put(destOffset + 0, s * _self00);
        dest.put(destOffset + 1, s * _self10);
        dest.put(destOffset + 2, s * _self20);
        dest.put(destOffset + 3, s * _self01);
        dest.put(destOffset + 4, s * _self11);
        dest.put(destOffset + 5, s * _self21);
        dest.put(destOffset + 6, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        dest.put(destOffset + 7, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        dest.put(destOffset + 8, Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float3x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, s);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _t0 = 1.0f - s;
        float _t1 = _pivotx * _t0;
        float _t2 = _pivoty * _t0;
        dest.put(destOffset + 0, s * _self00);
        dest.put(destOffset + 1, s * _self10);
        dest.put(destOffset + 2, s * _self20);
        dest.put(destOffset + 3, s * _self01);
        dest.put(destOffset + 4, s * _self11);
        dest.put(destOffset + 5, s * _self21);
        dest.put(destOffset + 6, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        dest.put(destOffset + 7, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        dest.put(destOffset + 8, Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22)));
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sX, sY, pivotX, pivotY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.put(destOffset + 0, sX * _self00);
        dest.put(destOffset + 1, sX * _self10);
        dest.put(destOffset + 2, sX * _self20);
        dest.put(destOffset + 3, sY * _self01);
        dest.put(destOffset + 4, sY * _self11);
        dest.put(destOffset + 5, sY * _self21);
        dest.put(destOffset + 6, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        dest.put(destOffset + 7, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        dest.put(destOffset + 8, Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) sOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && s.hasArray() && sOffset >= 0 && sOffset <= s.limit() - 2 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float3x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s.array(), s.arrayOffset() + sOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _sx = s.get(sOffset + 0);
        float _sy = s.get(sOffset + 1);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _t2 = _pivotx * (1.0f - _sx);
        float _t3 = _pivoty * (1.0f - _sy);
        dest.put(destOffset + 0, _sx * _self00);
        dest.put(destOffset + 1, _sx * _self10);
        dest.put(destOffset + 2, _sx * _self20);
        dest.put(destOffset + 3, _sy * _self01);
        dest.put(destOffset + 4, _sy * _self11);
        dest.put(destOffset + 5, _sy * _self21);
        dest.put(destOffset + 6, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        dest.put(destOffset + 7, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        dest.put(destOffset + 8, Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22)));
        return dest;
    }

    public static java.nio.FloatBuffer translate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer translate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.translate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.translate_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer translate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        dest.put(destOffset + 7, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        dest.put(destOffset + 8, Math.fma(_self20, vX, Math.fma(_self21, vY, _self22)));
        return dest;
    }

    public static java.nio.FloatBuffer translate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer translate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float3x3Ops.translate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.translate_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer translate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        dest.put(destOffset + 7, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        dest.put(destOffset + 8, Math.fma(_self20, _vx, Math.fma(_self21, _vy, _self22)));
        return dest;
    }

    public static java.nio.FloatBuffer view_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.FloatBuffer view_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.view(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, left, right, bottom, top);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.view_apiGet(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return dest;
    }

    public static java.nio.FloatBuffer view_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dest.put(destOffset + 0, _sp0 * _self00);
        dest.put(destOffset + 1, _sp0 * _self10);
        dest.put(destOffset + 2, _sp0 * _self20);
        dest.put(destOffset + 3, _sp1 * _self01);
        dest.put(destOffset + 4, _sp1 * _self11);
        dest.put(destOffset + 5, _sp1 * _self21);
        dest.put(destOffset + 6, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        dest.put(destOffset + 7, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        dest.put(destOffset + 8, _self22 + (-(_self20 * _sp2) - _self21 * _sp3));
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.mulVec3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.mulVec3_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        dest.put(destOffset + 1, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        dest.put(destOffset + 2, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Float3x3Ops.mulVec3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.mulVec3_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self20 = src.get(srcOffset + 2);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self21 = src.get(srcOffset + 5);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _self22 = src.get(srcOffset + 8);
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        dest.put(destOffset + 0, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        dest.put(destOffset + 1, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        dest.put(destOffset + 2, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.transformDirection(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.transformDirection_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        dest.put(destOffset + 0, Math.fma(_self00, vX, _self01 * vY));
        dest.put(destOffset + 1, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float3x3Ops.transformDirection(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.transformDirection_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, Math.fma(_self00, _vx, _self01 * _vy));
        dest.put(destOffset + 1, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Float3x3Ops.transformPosition(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.transformPosition_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        dest.put(destOffset + 0, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        dest.put(destOffset + 1, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float3x3Ops.transformPosition(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x3OpsKernelsTypedBuffer.transformPosition_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 3);
        float _self11 = src.get(srcOffset + 4);
        float _self02 = src.get(srcOffset + 6);
        float _self12 = src.get(srcOffset + 7);
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        dest.put(destOffset + 1, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
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
