// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3x4Ops} whose leading storage
 * parameter is a typed {@link java.nio.FloatBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float3x4Ops} and its sibling kernel units. Not public API.
 */
public final class Float3x4OpsKernelsTypedBuffer {
    private Float3x4OpsKernelsTypedBuffer() {}

    public static java.nio.FloatBuffer getColumn_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.nio.FloatBuffer getColumn_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getColumn(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, col);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getColumn_apiGet(dest, destOffset, src, srcOffset, col);
        return dest;
    }

    public static java.nio.FloatBuffer getColumn_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        dest.put(destOffset + 2, _idxSw2);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.getEulerAnglesXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getEulerAnglesXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getEulerAnglesXYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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
        Float3x4OpsKernelsAddress.getEulerAnglesXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXZY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getEulerAnglesXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getEulerAnglesXZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXZY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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
        Float3x4OpsKernelsAddress.getEulerAnglesYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYXZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getEulerAnglesYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getEulerAnglesYXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self22 = src.get(srcOffset + 10);
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
        Float3x4OpsKernelsAddress.getEulerAnglesYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYZX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getEulerAnglesYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getEulerAnglesYZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYZX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self22 = src.get(srcOffset + 10);
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
        Float3x4OpsKernelsAddress.getEulerAnglesZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZXY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getEulerAnglesZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getEulerAnglesZXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZXY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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
        Float3x4OpsKernelsAddress.getEulerAnglesZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZYX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getEulerAnglesZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getEulerAnglesZYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZYX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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
        Float3x4OpsKernelsAddress.getNormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getNormalizedRotation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getNormalizedRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getNormalizedRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getNormalizedRotation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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
        Float3x4OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.nio.FloatBuffer getRow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getRow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getRow_apiGet(dest, destOffset, src, srcOffset, row);
        return dest;
    }

    public static java.nio.FloatBuffer getRow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        dest.put(destOffset + 2, _idxSw2);
        dest.put(destOffset + 3, _idxSw3);
        return dest;
    }

    public static java.nio.FloatBuffer getScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.getScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getScale_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        dest.put(destOffset + 0, (float) Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        dest.put(destOffset + 1, (float) Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        dest.put(destOffset + 2, (float) Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    public static java.nio.FloatBuffer getTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.getTranslation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getTranslation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self03 = src.get(srcOffset + 3);
        float _self13 = src.get(srcOffset + 7);
        float _self23 = src.get(srcOffset + 11);
        dest.put(destOffset + 0, _self03);
        dest.put(destOffset + 1, _self13);
        dest.put(destOffset + 2, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer getUnnormalizedRotation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.getUnnormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer getUnnormalizedRotation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.getUnnormalizedRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.getUnnormalizedRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getUnnormalizedRotation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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

    public static java.nio.FloatBuffer invNegativeX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.invNegativeX_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.put(destOffset + 0, -(_t6 * _t13));
        dest.put(destOffset + 1, -(_t8 * _t13));
        dest.put(destOffset + 2, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeX_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.invNegativeX_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsTypedBuffer.invNegativeX_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer invNegativeX_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNegativeX_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeX_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4OpsKernelsArray.invNegativeX_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNegativeX_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeX_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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
            dest.put(destOffset + 0, -(_t21 * _t26));
            dest.put(destOffset + 1, -(_t22 * _t26));
            dest.put(destOffset + 2, -(_t20 * _t26));
        } else {
            dest.put(destOffset + 0, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        float _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        float _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.invNegativeY_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.put(destOffset + 0, -(_t6 * _t13));
        dest.put(destOffset + 1, -(_t8 * _t13));
        dest.put(destOffset + 2, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeY_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.invNegativeY_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsTypedBuffer.invNegativeY_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer invNegativeY_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNegativeY_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeY_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4OpsKernelsArray.invNegativeY_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNegativeY_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeY_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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
            dest.put(destOffset + 0, -(_t22 * _t26));
            dest.put(destOffset + 1, -(_t21 * _t26));
            dest.put(destOffset + 2, -(_t20 * _t26));
        } else {
            dest.put(destOffset + 0, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        float _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        float _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.invNegativeZ_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.put(destOffset + 0, -(_t6 * _t13));
        dest.put(destOffset + 1, -(_t8 * _t13));
        dest.put(destOffset + 2, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeZ_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.invNegativeZ_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsTypedBuffer.invNegativeZ_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer invNegativeZ_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNegativeZ_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeZ_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4OpsKernelsArray.invNegativeZ_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNegativeZ_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeZ_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
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
            dest.put(destOffset + 0, -(_t21 * _t26));
            dest.put(destOffset + 1, -(_t22 * _t26));
            dest.put(destOffset + 2, -(_t20 * _t26));
        } else {
            dest.put(destOffset + 0, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNormalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invNormalizedNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNormalizedNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        dest.put(destOffset + 0, -_self00);
        dest.put(destOffset + 1, -_self01);
        dest.put(destOffset + 2, -_self02);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invNormalizedNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNormalizedNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        dest.put(destOffset + 0, -_self10);
        dest.put(destOffset + 1, -_self11);
        dest.put(destOffset + 2, -_self12);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invNormalizedNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNormalizedNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        dest.put(destOffset + 0, -_self20);
        dest.put(destOffset + 1, -_self21);
        dest.put(destOffset + 2, -_self22);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invNormalizedPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNormalizedPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invNormalizedPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNormalizedPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        dest.put(destOffset + 0, _self10);
        dest.put(destOffset + 1, _self11);
        dest.put(destOffset + 2, _self12);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invNormalizedPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invNormalizedPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        dest.put(destOffset + 0, _self20);
        dest.put(destOffset + 1, _self21);
        dest.put(destOffset + 2, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.invPositiveX_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.put(destOffset + 0, _t6 * _t13);
        dest.put(destOffset + 1, _t8 * _t13);
        dest.put(destOffset + 2, _t7 * _t13);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveX_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.invPositiveX_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsTypedBuffer.invPositiveX_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer invPositiveX_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invPositiveX_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveX_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4OpsKernelsArray.invPositiveX_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invPositiveX_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveX_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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
            dest.put(destOffset + 0, _t21 * _t26);
            dest.put(destOffset + 1, _t22 * _t26);
            dest.put(destOffset + 2, _t20 * _t26);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        float _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        float _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.invPositiveY_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.put(destOffset + 0, _t6 * _t13);
        dest.put(destOffset + 1, _t8 * _t13);
        dest.put(destOffset + 2, _t7 * _t13);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveY_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.invPositiveY_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsTypedBuffer.invPositiveY_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer invPositiveY_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invPositiveY_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveY_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4OpsKernelsArray.invPositiveY_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invPositiveY_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveY_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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
            dest.put(destOffset + 0, _t22 * _t26);
            dest.put(destOffset + 1, _t21 * _t26);
            dest.put(destOffset + 2, _t20 * _t26);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        float _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        float _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        float _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.invPositiveZ_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = (1.0f / (float) Math.sqrt(_ct0));
        dest.put(destOffset + 0, _t6 * _t13);
        dest.put(destOffset + 1, _t8 * _t13);
        dest.put(destOffset + 2, _t7 * _t13);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveZ_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.invPositiveZ_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsTypedBuffer.invPositiveZ_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer invPositiveZ_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invPositiveZ_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveZ_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4OpsKernelsArray.invPositiveZ_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invPositiveZ_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveZ_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
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
            dest.put(destOffset + 0, _t21 * _t26);
            dest.put(destOffset + 1, _t22 * _t26);
            dest.put(destOffset + 2, _t20 * _t26);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer negativeX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negativeX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.negativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.negativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negativeX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 4);
        float _self20 = src.get(srcOffset + 8);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.put(destOffset + 0, -(_self00 * _t3));
            dest.put(destOffset + 1, -(_self10 * _t3));
            dest.put(destOffset + 2, -(_self20 * _t3));
        } else {
            dest.put(destOffset + 0, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer negativeY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.negativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negativeY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.negativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.negativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negativeY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self01 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 5);
        float _self21 = src.get(srcOffset + 9);
        float _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.put(destOffset + 0, -(_self01 * _t3));
            dest.put(destOffset + 1, -(_self11 * _t3));
            dest.put(destOffset + 2, -(_self21 * _t3));
        } else {
            dest.put(destOffset + 0, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer negativeZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.negativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negativeZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.negativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.negativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negativeZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self02 = src.get(srcOffset + 2);
        float _self12 = src.get(srcOffset + 6);
        float _self22 = src.get(srcOffset + 10);
        float _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.put(destOffset + 0, -(_self02 * _t3));
            dest.put(destOffset + 1, -(_self12 * _t3));
            dest.put(destOffset + 2, -(_self22 * _t3));
        } else {
            dest.put(destOffset + 0, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.normalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.normalizedNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.normalizedNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 4);
        float _self20 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, -_self00);
        dest.put(destOffset + 1, -_self10);
        dest.put(destOffset + 2, -_self20);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.normalizedNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.normalizedNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self01 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 5);
        float _self21 = src.get(srcOffset + 9);
        dest.put(destOffset + 0, -_self01);
        dest.put(destOffset + 1, -_self11);
        dest.put(destOffset + 2, -_self21);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.normalizedNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.normalizedNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self02 = src.get(srcOffset + 2);
        float _self12 = src.get(srcOffset + 6);
        float _self22 = src.get(srcOffset + 10);
        dest.put(destOffset + 0, -_self02);
        dest.put(destOffset + 1, -_self12);
        dest.put(destOffset + 2, -_self22);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.normalizedPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.normalizedPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 4);
        float _self20 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.normalizedPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.normalizedPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self01 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 5);
        float _self21 = src.get(srcOffset + 9);
        dest.put(destOffset + 0, _self01);
        dest.put(destOffset + 1, _self11);
        dest.put(destOffset + 2, _self21);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.normalizedPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.normalizedPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self02 = src.get(srcOffset + 2);
        float _self12 = src.get(srcOffset + 6);
        float _self22 = src.get(srcOffset + 10);
        dest.put(destOffset + 0, _self02);
        dest.put(destOffset + 1, _self12);
        dest.put(destOffset + 2, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer origin_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.origin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer origin_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.origin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.origin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer origin_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        dest.put(destOffset + 0, -Math.fma(_self20, _self23, Math.fma(_self00, _self03, _self10 * _self13)));
        dest.put(destOffset + 1, -Math.fma(_self21, _self23, Math.fma(_self01, _self03, _self11 * _self13)));
        dest.put(destOffset + 2, -Math.fma(_self22, _self23, Math.fma(_self02, _self03, _self12 * _self13)));
        return dest;
    }

    public static java.nio.FloatBuffer positiveX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer positiveX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.positiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.positiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer positiveX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self10 = src.get(srcOffset + 4);
        float _self20 = src.get(srcOffset + 8);
        float _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.put(destOffset + 0, _self00 * _t3);
            dest.put(destOffset + 1, _self10 * _t3);
            dest.put(destOffset + 2, _self20 * _t3);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer positiveY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.positiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer positiveY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.positiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.positiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer positiveY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self01 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 5);
        float _self21 = src.get(srcOffset + 9);
        float _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.put(destOffset + 0, _self01 * _t3);
            dest.put(destOffset + 1, _self11 * _t3);
            dest.put(destOffset + 2, _self21 * _t3);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer positiveZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.positiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer positiveZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.positiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.positiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer positiveZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self02 = src.get(srcOffset + 2);
        float _self12 = src.get(srcOffset + 6);
        float _self22 = src.get(srcOffset + 10);
        float _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.put(destOffset + 0, _self02 * _t3);
            dest.put(destOffset + 1, _self12 * _t3);
            dest.put(destOffset + 2, _self22 * _t3);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static float determinant_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3x4OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static float determinant_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            return Float3x4Ops.determinant(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float3x4OpsKernelsTypedBuffer.determinant_apiGet(src, srcOffset);
    }

    public static float determinant_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        return Math.fma(_self02, Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(_self00, Math.fma(_self11, _self22, -(_self12 * _self21)), -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static float frobeniusNorm_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3x4OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            return Float3x4Ops.frobeniusNorm(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float3x4OpsKernelsTypedBuffer.frobeniusNorm_apiGet(src, srcOffset);
    }

    public static float frobeniusNorm_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        return (float) Math.sqrt(Math.fma(_self00, _self00, Math.fma(_self01, _self01, _self02 * _self02)) + Math.fma(_self03, _self03, Math.fma(_self10, _self10, _self11 * _self11)) + (Math.fma(_self12, _self12, Math.fma(_self13, _self13, _self20 * _self20)) + Math.fma(_self21, _self21, Math.fma(_self22, _self22, _self23 * _self23))));
    }

    public static java.nio.FloatBuffer invert_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invert_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.invert(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invert_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invert_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        if (!(Math.abs(_t34) > 1.1754944E-38f && Math.abs(_t34) < 8.507059E37f)) return Float3x4OpsKernelsTypedBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t34_inv = 1.0f / _t34;
        dest.put(destOffset + 0, _t20 * _t34_inv);
        dest.put(destOffset + 1, _t23 * _t34_inv);
        dest.put(destOffset + 2, _t24 * _t34_inv);
        dest.put(destOffset + 3, -(Math.fma(_self23, _t24, Math.fma(_self03, _t20, _self13 * _t23)) * _t34_inv));
        dest.put(destOffset + 4, _t25 * _t34_inv);
        dest.put(destOffset + 5, _t26 * _t34_inv);
        dest.put(destOffset + 6, _t27 * _t34_inv);
        dest.put(destOffset + 7, -(Math.fma(_self23, _t27, Math.fma(_self03, _t25, _self13 * _t26)) * _t34_inv));
        dest.put(destOffset + 8, _t21 * _t34_inv);
        dest.put(destOffset + 9, _t28 * _t34_inv);
        dest.put(destOffset + 10, _t29 * _t34_inv);
        dest.put(destOffset + 11, -(Math.fma(_self23, _t29, Math.fma(_self03, _t21, _self13 * _t28)) * _t34_inv));
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsTypedBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer invert_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4OpsKernelsArray.invert_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invert_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, _t47 * _sp0);
        dest.put(destOffset + 1, _t50 * _sp1);
        dest.put(destOffset + 2, _t51 * _sp2);
        dest.put(destOffset + 3, -(Math.fma(_t51, _t24, Math.fma(_t50, _t25, _t47 * _t26)) * _t60_inv));
        dest.put(destOffset + 4, _t52 * _sp0);
        dest.put(destOffset + 5, _t53 * _sp1);
        dest.put(destOffset + 6, _t54 * _sp2);
        dest.put(destOffset + 7, -(Math.fma(_t54, _t24, Math.fma(_t53, _t25, _t52 * _t26)) * _t60_inv));
        dest.put(destOffset + 8, _t48 * _sp0);
        dest.put(destOffset + 9, _t55 * _sp1);
        dest.put(destOffset + 10, _t56 * _sp2);
        dest.put(destOffset + 11, -(Math.fma(_t56, _t24, Math.fma(_t55, _t25, _t48 * _t26)) * _t60_inv));
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x4OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Float3x4Ops.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _other00 = other.get(otherOffset + 0);
        float _other01 = other.get(otherOffset + 1);
        float _other02 = other.get(otherOffset + 2);
        float _other03 = other.get(otherOffset + 3);
        float _other10 = other.get(otherOffset + 4);
        float _other11 = other.get(otherOffset + 5);
        float _other12 = other.get(otherOffset + 6);
        float _other13 = other.get(otherOffset + 7);
        float _other20 = other.get(otherOffset + 8);
        float _other21 = other.get(otherOffset + 9);
        float _other22 = other.get(otherOffset + 10);
        float _other23 = other.get(otherOffset + 11);
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
        if (!(Math.abs(_t70) > 1.1754944E-38f && Math.abs(_t70) < 8.507059E37f)) return Float3x4OpsKernelsTypedBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t70_inv = 1.0f / _t70;
        dest.put(destOffset + 0, _t56 * _t70_inv);
        dest.put(destOffset + 1, _t59 * _t70_inv);
        dest.put(destOffset + 2, _t60 * _t70_inv);
        dest.put(destOffset + 3, -(Math.fma(_t33, _t60, Math.fma(_t34, _t56, _t35 * _t59)) * _t70_inv));
        dest.put(destOffset + 4, _t61 * _t70_inv);
        dest.put(destOffset + 5, _t62 * _t70_inv);
        dest.put(destOffset + 6, _t63 * _t70_inv);
        dest.put(destOffset + 7, -(Math.fma(_t33, _t63, Math.fma(_t34, _t61, _t35 * _t62)) * _t70_inv));
        dest.put(destOffset + 8, _t57 * _t70_inv);
        dest.put(destOffset + 9, _t64 * _t70_inv);
        dest.put(destOffset + 10, _t65 * _t70_inv);
        dest.put(destOffset + 11, -(Math.fma(_t33, _t65, Math.fma(_t34, _t57, _t35 * _t64)) * _t70_inv));
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3x4OpsKernelsTypedBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x4OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Float3x4OpsKernelsArray.invertProduct_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.invertProduct_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _other00 = other.get(otherOffset + 0);
        float _other01 = other.get(otherOffset + 1);
        float _other02 = other.get(otherOffset + 2);
        float _other03 = other.get(otherOffset + 3);
        float _other10 = other.get(otherOffset + 4);
        float _other11 = other.get(otherOffset + 5);
        float _other12 = other.get(otherOffset + 6);
        float _other13 = other.get(otherOffset + 7);
        float _other20 = other.get(otherOffset + 8);
        float _other21 = other.get(otherOffset + 9);
        float _other22 = other.get(otherOffset + 10);
        float _other23 = other.get(otherOffset + 11);
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
        dest.put(destOffset + 0, _t83 * _sp0);
        dest.put(destOffset + 1, _t86 * _sp1);
        dest.put(destOffset + 2, _t87 * _sp2);
        dest.put(destOffset + 3, -(Math.fma(_t87, _t60, Math.fma(_t83, _t61, _t86 * _t62)) * _t96_inv));
        dest.put(destOffset + 4, _t88 * _sp0);
        dest.put(destOffset + 5, _t89 * _sp1);
        dest.put(destOffset + 6, _t90 * _sp2);
        dest.put(destOffset + 7, -(Math.fma(_t90, _t60, Math.fma(_t89, _t62, _t88 * _t61)) * _t96_inv));
        dest.put(destOffset + 8, _t84 * _sp0);
        dest.put(destOffset + 9, _t91 * _sp1);
        dest.put(destOffset + 10, _t92 * _sp2);
        dest.put(destOffset + 11, -(Math.fma(_t92, _t60, Math.fma(_t84, _t61, _t91 * _t62)) * _t96_inv));
        return dest;
    }

    public static java.nio.FloatBuffer transpose_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer transpose_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.transpose(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.transpose_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transpose_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.get(srcOffset + _i);
            dest.put(destOffset + _i, _eself);
        }
        return dest;
    }

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Float3x4Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.get(srcOffset + _i);
            float _eother = other.get(otherOffset + _i);
            dest.put(destOffset + _i, _eother + _eself);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.get(srcOffset + _i);
            dest.put(destOffset + _i, scalar * _eself);
        }
        return dest;
    }

    public static java.nio.FloatBuffer negate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.get(srcOffset + _i);
            dest.put(destOffset + _i, -_eself);
        }
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Float3x4Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.get(srcOffset + _i);
            float _eother = other.get(otherOffset + _i);
            dest.put(destOffset + _i, _eself - _eother);
        }
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x4OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 12) {
            Float3x4Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _ev = v.get(vOffset + _i);
            dest.put(destOffset + _i, _ev);
        }
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 4L;
        Float3x4OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 9) {
            Float3x4Ops.setMat3x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.setMat3x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        float _m00 = m.get(mOffset + 0);
        float _m10 = m.get(mOffset + 1);
        float _m20 = m.get(mOffset + 2);
        float _m01 = m.get(mOffset + 3);
        float _m11 = m.get(mOffset + 4);
        float _m21 = m.get(mOffset + 5);
        float _m02 = m.get(mOffset + 6);
        float _m12 = m.get(mOffset + 7);
        float _m22 = m.get(mOffset + 8);
        dest.put(destOffset + 0, _m00);
        dest.put(destOffset + 1, _m01);
        dest.put(destOffset + 2, _m02);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _m10);
        dest.put(destOffset + 5, _m11);
        dest.put(destOffset + 6, _m12);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, _m20);
        dest.put(destOffset + 9, _m21);
        dest.put(destOffset + 10, _m22);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer setMat4x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 4L;
        Float3x4OpsKernelsAddress.setMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer setMat4x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 16) {
            Float3x4Ops.setMat4x4(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.setMat4x4_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer setMat4x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        float _m00 = m.get(mOffset + 0);
        float _m10 = m.get(mOffset + 1);
        float _m20 = m.get(mOffset + 2);
        float _m01 = m.get(mOffset + 4);
        float _m11 = m.get(mOffset + 5);
        float _m21 = m.get(mOffset + 6);
        float _m02 = m.get(mOffset + 8);
        float _m12 = m.get(mOffset + 9);
        float _m22 = m.get(mOffset + 10);
        float _m03 = m.get(mOffset + 12);
        float _m13 = m.get(mOffset + 13);
        float _m23 = m.get(mOffset + 14);
        dest.put(destOffset + 0, _m00);
        dest.put(destOffset + 1, _m01);
        dest.put(destOffset + 2, _m02);
        dest.put(destOffset + 3, _m03);
        dest.put(destOffset + 4, _m10);
        dest.put(destOffset + 5, _m11);
        dest.put(destOffset + 6, _m12);
        dest.put(destOffset + 7, _m13);
        dest.put(destOffset + 8, _m20);
        dest.put(destOffset + 9, _m21);
        dest.put(destOffset + 10, _m22);
        dest.put(destOffset + 11, _m23);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float tX, float tY, float tZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY, tZ);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float tX, float tY, float tZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.withTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, tX, tY, tZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.withTranslation_apiGet(dest, destOffset, src, srcOffset, tX, tY, tZ);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float tX, float tY, float tZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, tX);
        dest.put(destOffset + 4, _self10);
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, _self12);
        dest.put(destOffset + 7, tY);
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, tZ);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) tOffset * 4L;
        Float3x4OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 3) {
            Float3x4Ops.withTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.withTranslation_apiGet(dest, destOffset, src, srcOffset, t, tOffset);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t, int tOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _tx = t.get(tOffset + 0);
        float _ty = t.get(tOffset + 1);
        float _tz = t.get(tOffset + 2);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, _tx);
        dest.put(destOffset + 4, _self10);
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, _self12);
        dest.put(destOffset + 7, _ty);
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, _tz);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromRigid_unsafe(java.nio.FloatBuffer dest, int destOffset, float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeFromRigid_unsafe(_destBase, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromRigid_api(java.nio.FloatBuffer dest, int destOffset, float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeFromRigid(dest.array(), dest.arrayOffset() + destOffset, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeFromRigid_apiGet(dest, destOffset, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromRigid_apiGet(java.nio.FloatBuffer dest, int destOffset, float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        dest.put(destOffset + 0, Math.fma(-2.0f, Math.fma(rRY, rRY, _t0), 1.0f));
        dest.put(destOffset + 1, 2.0f * Math.fma(rRX, rRY, -_t1));
        dest.put(destOffset + 2, 2.0f * Math.fma(rRX, rRZ, _t2));
        dest.put(destOffset + 3, rTX);
        dest.put(destOffset + 4, 2.0f * Math.fma(rRX, rRY, _t1));
        dest.put(destOffset + 5, Math.fma(-2.0f, Math.fma(rRX, rRX, _t0), 1.0f));
        dest.put(destOffset + 6, 2.0f * Math.fma(rRY, rRZ, -(rRX * rRW)));
        dest.put(destOffset + 7, rTY);
        dest.put(destOffset + 8, 2.0f * Math.fma(rRX, rRZ, -_t2));
        dest.put(destOffset + 9, 2.0f * Math.fma(rRX, rRW, rRY * rRZ));
        dest.put(destOffset + 10, Math.fma(-2.0f, Math.fma(rRX, rRX, rRY * rRY), 1.0f));
        dest.put(destOffset + 11, rTZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromTransform_unsafe(java.nio.FloatBuffer dest, int destOffset, float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeFromTransform_unsafe(_destBase, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromTransform_api(java.nio.FloatBuffer dest, int destOffset, float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeFromTransform(dest.array(), dest.arrayOffset() + destOffset, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeFromTransform_apiGet(dest, destOffset, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
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
        dest.put(destOffset + 1, Math.fma(tRX, tRY, -_t4) * _t1);
        dest.put(destOffset + 2, Math.fma(tRX, tRZ, _t5) * _t2);
        dest.put(destOffset + 3, tTX);
        dest.put(destOffset + 4, Math.fma(tRX, tRY, _t4) * _t0);
        dest.put(destOffset + 5, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY));
        dest.put(destOffset + 6, Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2);
        dest.put(destOffset + 7, tTY);
        dest.put(destOffset + 8, Math.fma(tRX, tRZ, -_t5) * _t0);
        dest.put(destOffset + 9, Math.fma(tRX, tRW, tRY * tRZ) * _t1);
        dest.put(destOffset + 10, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ));
        dest.put(destOffset + 11, tTZ);
        return dest;
    }

    public static java.nio.FloatBuffer to3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer to3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.to3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.to3x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer to3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, _self21);
        dest.put(destOffset + 6, _self02);
        dest.put(destOffset + 7, _self12);
        dest.put(destOffset + 8, _self22);
        return dest;
    }

    public static java.nio.FloatBuffer to4x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.to4x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer to4x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.to4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.to4x4_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer to4x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 12, _self03);
        dest.put(destOffset + 13, _self13);
        dest.put(destOffset + 14, _self23);
        dest.put(destOffset + 15, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer toDualQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toDualQuat_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 8 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.toDualQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.toDualQuat_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toDualQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, _t63);
        dest.put(destOffset + 1, _t64);
        dest.put(destOffset + 2, _t65);
        dest.put(destOffset + 3, _t66);
        dest.put(destOffset + 4, 0.5f * Math.fma(_t0, _t64, Math.fma(_self03, _t66, _self13 * _t65)));
        dest.put(destOffset + 5, 0.5f * Math.fma(_self23, _t63, Math.fma(_self13, _t66, -(_self03 * _t65))));
        dest.put(destOffset + 6, 0.5f * Math.fma(_self23, _t66, Math.fma(_self03, _t64, -(_self13 * _t63))));
        dest.put(destOffset + 7, 0.5f * Math.fma(_t0, _t65, Math.fma(-_self13, _t64, -(_self03 * _t63))));
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.toRigid_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 7 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.toRigid(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.toRigid_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t15 = (1.0f / (float) Math.sqrt(_ct0));
        float _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        float _t16 = (1.0f / (float) Math.sqrt(_ct1));
        float _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
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
        dest.put(destOffset + 0, _self03);
        dest.put(destOffset + 1, _self13);
        dest.put(destOffset + 2, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.toRigid_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsTypedBuffer.toRigid_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer toRigid_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.toRigid_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 7 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4OpsKernelsArray.toRigid_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.toRigid_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toRigid_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, _self03);
        dest.put(destOffset + 1, _self13);
        dest.put(destOffset + 2, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer toTransform_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.toTransform_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toTransform_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 10 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.toTransform(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.toTransform_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toTransform_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _t0 = -_self11;
        float _t1 = -_self22;
        float _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        float _t14 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return Float3x4OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
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
        dest.put(destOffset + 0, _self03);
        dest.put(destOffset + 1, _self13);
        dest.put(destOffset + 2, _self23);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.toTransform_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float3x4OpsKernelsTypedBuffer.toTransform_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer toTransform_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.toTransform_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toTransform_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 10 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4OpsKernelsArray.toTransform_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.toTransform_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toTransform_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, _self03);
        dest.put(destOffset + 1, _self13);
        dest.put(destOffset + 2, _self23);
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
        Float3x4OpsKernelsAddress.decomposeRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeRotation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.decomposeRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.decomposeRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeRotation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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
        Float3x4OpsKernelsAddress.decomposeScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.decomposeScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.decomposeScale_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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
        Float3x4OpsKernelsAddress.decomposeSkew_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeSkew_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.decomposeSkew(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.decomposeSkew_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeSkew_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
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

    public static java.nio.FloatBuffer decomposeTRS_unsafe(java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) translationOffset * 4L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rotationOffset * 4L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) scaleOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.decomposeTRS_unsafe(_translationBase, _rotationBase, _scaleBase, _srcBase);
        return translation;
    }

    public static java.nio.FloatBuffer decomposeTRS_api(java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (translation.hasArray() && translationOffset >= 0 && translationOffset <= translation.limit() - 3 && rotation.hasArray() && rotationOffset >= 0 && rotationOffset <= rotation.limit() - 4 && scale.hasArray() && scaleOffset >= 0 && scaleOffset <= scale.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.decomposeTRS(translation.array(), translation.arrayOffset() + translationOffset, rotation.array(), rotation.arrayOffset() + rotationOffset, scale.array(), scale.arrayOffset() + scaleOffset, src.array(), src.arrayOffset() + srcOffset);
            return translation;
        }
        Float3x4OpsKernelsTypedBuffer.decomposeTRS_apiGet(translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, src, srcOffset);
        return translation;
    }

    public static java.nio.FloatBuffer decomposeTRS_apiGet(java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        translation.put(translationOffset + 0, _self03);
        translation.put(translationOffset + 1, _self13);
        translation.put(translationOffset + 2, _self23);
        if (_t83 > 0.0f) {
            rotation.put(rotationOffset + 0, _sp0 * _t61);
            rotation.put(rotationOffset + 1, _sp0 * _t82);
            rotation.put(rotationOffset + 2, _sp0 * _t79);
            rotation.put(rotationOffset + 3, 0.5f * (float) Math.sqrt(_t87));
        } else {
            if (_t74 > Math.max(_t37, _t57)) {
                rotation.put(rotationOffset + 0, 0.5f * (float) Math.sqrt(_t88));
                rotation.put(rotationOffset + 1, _sp3 * _t78);
                rotation.put(rotationOffset + 2, _sp3 * _t81);
                rotation.put(rotationOffset + 3, _sp3 * _t61);
            } else {
                if (_t37 > _t57) {
                    rotation.put(rotationOffset + 0, _sp1 * _t78);
                    rotation.put(rotationOffset + 1, 0.5f * (float) Math.sqrt(_t89));
                    rotation.put(rotationOffset + 2, _sp1 * _t64);
                    rotation.put(rotationOffset + 3, _sp1 * _t82);
                } else {
                    rotation.put(rotationOffset + 0, _sp2 * _t81);
                    rotation.put(rotationOffset + 1, _sp2 * _t64);
                    rotation.put(rotationOffset + 2, 0.5f * (float) Math.sqrt(_t90));
                    rotation.put(rotationOffset + 3, _sp2 * _t79);
                }
            }
        }
        scale.put(scaleOffset + 0, _t73 < 0.0f ? -_t4 : _t4);
        scale.put(scaleOffset + 1, (float) Math.sqrt(_t30));
        scale.put(scaleOffset + 2, (float) Math.sqrt(_t50));
        return translation;
    }

    public static java.nio.FloatBuffer makeIdentity_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeIdentity(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeIdentity_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Float3x4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        for (int _i = 0; _i < 12; _i++) {
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
        Float3x4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 12) {
            Float3x4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _right00 = right.get(rightOffset + 0);
        float _right01 = right.get(rightOffset + 1);
        float _right02 = right.get(rightOffset + 2);
        float _right03 = right.get(rightOffset + 3);
        float _right10 = right.get(rightOffset + 4);
        float _right11 = right.get(rightOffset + 5);
        float _right12 = right.get(rightOffset + 6);
        float _right13 = right.get(rightOffset + 7);
        float _right20 = right.get(rightOffset + 8);
        float _right21 = right.get(rightOffset + 9);
        float _right22 = right.get(rightOffset + 10);
        float _right23 = right.get(rightOffset + 11);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            dest.put(destOffset + _lo + 1, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            dest.put(destOffset + _lo + 2, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            dest.put(destOffset + _lo + 3, Math.fma(_right03, _eself0, Math.fma(_right13, _eself1, Math.fma(_right23, _eself2, _eself3))));
        }
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rightOffset * 4L;
        Float3x4OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 4) {
            Float3x4Ops.mulMat2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mulMat2x2_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _right00 = right.get(rightOffset + 0);
        float _right10 = right.get(rightOffset + 1);
        float _right01 = right.get(rightOffset + 2);
        float _right11 = right.get(rightOffset + 3);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_right00, _eself0, _right10 * _eself1));
            dest.put(destOffset + _lo + 1, Math.fma(_right01, _eself0, _right11 * _eself1));
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rightOffset * 4L;
        Float3x4OpsKernelsAddress.mulMat2x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 6) {
            Float3x4Ops.mulMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mulMat2x3_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _right00 = right.get(rightOffset + 0);
        float _right10 = right.get(rightOffset + 1);
        float _right01 = right.get(rightOffset + 2);
        float _right11 = right.get(rightOffset + 3);
        float _right02 = right.get(rightOffset + 4);
        float _right12 = right.get(rightOffset + 5);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_right00, _eself0, _right10 * _eself1));
            dest.put(destOffset + _lo + 1, Math.fma(_right01, _eself0, _right11 * _eself1));
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, Math.fma(_right02, _eself0, Math.fma(_right12, _eself1, _eself3)));
        }
        return dest;
    }

    public static java.nio.FloatBuffer mulMat3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rightOffset * 4L;
        Float3x4OpsKernelsAddress.mulMat3x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 9) {
            Float3x4Ops.mulMat3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mulMat3x3_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _right00 = right.get(rightOffset + 0);
        float _right10 = right.get(rightOffset + 1);
        float _right20 = right.get(rightOffset + 2);
        float _right01 = right.get(rightOffset + 3);
        float _right11 = right.get(rightOffset + 4);
        float _right21 = right.get(rightOffset + 5);
        float _right02 = right.get(rightOffset + 6);
        float _right12 = right.get(rightOffset + 7);
        float _right22 = right.get(rightOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            dest.put(destOffset + _lo + 1, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            dest.put(destOffset + _lo + 2, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mulMat4x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rightOffset * 4L;
        Float3x4OpsKernelsAddress.mulMat4x4_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat4x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 16) {
            Float3x4Ops.mulMat4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mulMat4x4_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat4x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        for (int _l = 0; _l < 4; _l++) {
            int _lo = _l * 4;
            float _eright0 = right.get(rightOffset + _lo);
            float _eright1 = right.get(rightOffset + _lo + 1);
            float _eright2 = right.get(rightOffset + _lo + 2);
            float _eright3 = right.get(rightOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_eright3, _self03, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01))));
            dest.put(destOffset + _lo + 1, Math.fma(_eright3, _self13, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11))));
            dest.put(destOffset + _lo + 2, Math.fma(_eright3, _self23, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21))));
            dest.put(destOffset + _lo + 3, _eright3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x4OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Float3x4Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eother0 = other.get(otherOffset + _lo);
            float _eother1 = other.get(otherOffset + _lo + 1);
            float _eother2 = other.get(otherOffset + _lo + 2);
            float _eother3 = other.get(otherOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_eother2, _self20, Math.fma(_eother0, _self00, _eother1 * _self10)));
            dest.put(destOffset + _lo + 1, Math.fma(_eother2, _self21, Math.fma(_eother0, _self01, _eother1 * _self11)));
            dest.put(destOffset + _lo + 2, Math.fma(_eother2, _self22, Math.fma(_eother0, _self02, _eother1 * _self12)));
            dest.put(destOffset + _lo + 3, Math.fma(_eother0, _self03, Math.fma(_eother1, _self13, Math.fma(_eother2, _self23, _eother3))));
        }
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x4OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float3x4Ops.preMulMat2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preMulMat2x2_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _other00 = other.get(otherOffset + 0);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        dest.put(destOffset + 0, Math.fma(_other00, _self00, _other01 * _self10));
        dest.put(destOffset + 1, Math.fma(_other00, _self01, _other01 * _self11));
        dest.put(destOffset + 2, Math.fma(_other00, _self02, _other01 * _self12));
        dest.put(destOffset + 3, Math.fma(_other00, _self03, _other01 * _self13));
        dest.put(destOffset + 4, Math.fma(_other10, _self00, _other11 * _self10));
        dest.put(destOffset + 5, Math.fma(_other10, _self01, _other11 * _self11));
        dest.put(destOffset + 6, Math.fma(_other10, _self02, _other11 * _self12));
        dest.put(destOffset + 7, Math.fma(_other10, _self03, _other11 * _self13));
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x4OpsKernelsAddress.preMulMat2x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 6) {
            Float3x4Ops.preMulMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preMulMat2x3_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _other00 = other.get(otherOffset + 0);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        float _other02 = other.get(otherOffset + 4);
        float _other12 = other.get(otherOffset + 5);
        dest.put(destOffset + 0, Math.fma(_other00, _self00, _other01 * _self10));
        dest.put(destOffset + 1, Math.fma(_other00, _self01, _other01 * _self11));
        dest.put(destOffset + 2, Math.fma(_other00, _self02, _other01 * _self12));
        dest.put(destOffset + 3, Math.fma(_other00, _self03, Math.fma(_other01, _self13, _other02)));
        dest.put(destOffset + 4, Math.fma(_other10, _self00, _other11 * _self10));
        dest.put(destOffset + 5, Math.fma(_other10, _self01, _other11 * _self11));
        dest.put(destOffset + 6, Math.fma(_other10, _self02, _other11 * _self12));
        dest.put(destOffset + 7, Math.fma(_other10, _self03, Math.fma(_other11, _self13, _other12)));
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x4OpsKernelsAddress.preMulMat3x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Float3x4Ops.preMulMat3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preMulMat3x3_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _other00 = other.get(otherOffset + 0);
        float _other10 = other.get(otherOffset + 1);
        float _other20 = other.get(otherOffset + 2);
        float _other01 = other.get(otherOffset + 3);
        float _other11 = other.get(otherOffset + 4);
        float _other21 = other.get(otherOffset + 5);
        float _other02 = other.get(otherOffset + 6);
        float _other12 = other.get(otherOffset + 7);
        float _other22 = other.get(otherOffset + 8);
        dest.put(destOffset + 0, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        dest.put(destOffset + 1, Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11)));
        dest.put(destOffset + 2, Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12)));
        dest.put(destOffset + 3, Math.fma(_other02, _self23, Math.fma(_other00, _self03, _other01 * _self13)));
        dest.put(destOffset + 4, Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10)));
        dest.put(destOffset + 5, Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11)));
        dest.put(destOffset + 6, Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12)));
        dest.put(destOffset + 7, Math.fma(_other12, _self23, Math.fma(_other10, _self03, _other11 * _self13)));
        dest.put(destOffset + 8, Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10)));
        dest.put(destOffset + 9, Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11)));
        dest.put(destOffset + 10, Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12)));
        dest.put(destOffset + 11, Math.fma(_other22, _self23, Math.fma(_other20, _self03, _other21 * _self13)));
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat4x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x4OpsKernelsAddress.preMulMat4x4_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat4x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 16) {
            Float3x4Ops.preMulMat4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preMulMat4x4_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat4x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _other00 = other.get(otherOffset + 0);
        float _other10 = other.get(otherOffset + 1);
        float _other20 = other.get(otherOffset + 2);
        float _other30 = other.get(otherOffset + 3);
        float _other01 = other.get(otherOffset + 4);
        float _other11 = other.get(otherOffset + 5);
        float _other21 = other.get(otherOffset + 6);
        float _other31 = other.get(otherOffset + 7);
        float _other02 = other.get(otherOffset + 8);
        float _other12 = other.get(otherOffset + 9);
        float _other22 = other.get(otherOffset + 10);
        float _other32 = other.get(otherOffset + 11);
        float _other03 = other.get(otherOffset + 12);
        float _other13 = other.get(otherOffset + 13);
        float _other23 = other.get(otherOffset + 14);
        float _other33 = other.get(otherOffset + 15);
        dest.put(destOffset + 0, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        dest.put(destOffset + 1, Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10)));
        dest.put(destOffset + 2, Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10)));
        dest.put(destOffset + 3, Math.fma(_other32, _self20, Math.fma(_other30, _self00, _other31 * _self10)));
        dest.put(destOffset + 4, Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11)));
        dest.put(destOffset + 5, Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11)));
        dest.put(destOffset + 6, Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11)));
        dest.put(destOffset + 7, Math.fma(_other32, _self21, Math.fma(_other30, _self01, _other31 * _self11)));
        dest.put(destOffset + 8, Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12)));
        dest.put(destOffset + 9, Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12)));
        dest.put(destOffset + 10, Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12)));
        dest.put(destOffset + 11, Math.fma(_other32, _self22, Math.fma(_other30, _self02, _other31 * _self12)));
        dest.put(destOffset + 12, Math.fma(_other00, _self03, Math.fma(_other01, _self13, Math.fma(_other02, _self23, _other03))));
        dest.put(destOffset + 13, Math.fma(_other10, _self03, Math.fma(_other11, _self13, Math.fma(_other12, _self23, _other13))));
        dest.put(destOffset + 14, Math.fma(_other20, _self03, Math.fma(_other21, _self13, Math.fma(_other22, _self23, _other23))));
        dest.put(destOffset + 15, Math.fma(_other30, _self03, Math.fma(_other31, _self13, Math.fma(_other32, _self23, _other33))));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3x4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Float3x4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, weight);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.get(srcOffset + _i);
            float _eother = other.get(otherOffset + _i);
            dest.put(destOffset + _i, Math.fma(weight, _eother, _eself));
        }
        return dest;
    }

    public static java.nio.FloatBuffer composeTRS_unsafe(java.nio.FloatBuffer dest, int destOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.composeTRS_unsafe(_destBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRS_api(java.nio.FloatBuffer dest, int destOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.composeTRS(dest.array(), dest.arrayOffset() + destOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.composeTRS_apiGet(dest, destOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRS_apiGet(java.nio.FloatBuffer dest, int destOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _t0 = scaleX + scaleX;
        float _t1 = scaleY + scaleY;
        float _t2 = scaleZ + scaleZ;
        float _t3 = rotationZ * rotationZ;
        float _t4 = rotationZ * rotationW;
        float _t5 = rotationY * rotationW;
        dest.put(destOffset + 0, Math.fma(-Math.fma(rotationY, rotationY, _t3), _t0, scaleX));
        dest.put(destOffset + 1, Math.fma(rotationX, rotationY, -_t4) * _t1);
        dest.put(destOffset + 2, Math.fma(rotationX, rotationZ, _t5) * _t2);
        dest.put(destOffset + 3, translationX);
        dest.put(destOffset + 4, Math.fma(rotationX, rotationY, _t4) * _t0);
        dest.put(destOffset + 5, Math.fma(-Math.fma(rotationX, rotationX, _t3), _t1, scaleY));
        dest.put(destOffset + 6, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t2);
        dest.put(destOffset + 7, translationY);
        dest.put(destOffset + 8, Math.fma(rotationX, rotationZ, -_t5) * _t0);
        dest.put(destOffset + 9, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t1);
        dest.put(destOffset + 10, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t2, scaleZ));
        dest.put(destOffset + 11, translationZ);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRS_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) translationOffset * 4L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rotationOffset * 4L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) scaleOffset * 4L;
        Float3x4OpsKernelsAddress.composeTRS_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRS_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && translation.hasArray() && translationOffset >= 0 && translationOffset <= translation.limit() - 3 && rotation.hasArray() && rotationOffset >= 0 && rotationOffset <= rotation.limit() - 4 && scale.hasArray() && scaleOffset >= 0 && scaleOffset <= scale.limit() - 3) {
            Float3x4Ops.composeTRS(dest.array(), dest.arrayOffset() + destOffset, translation.array(), translation.arrayOffset() + translationOffset, rotation.array(), rotation.arrayOffset() + rotationOffset, scale.array(), scale.arrayOffset() + scaleOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.composeTRS_apiGet(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRS_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset) {
        float _translationx = translation.get(translationOffset + 0);
        float _translationy = translation.get(translationOffset + 1);
        float _translationz = translation.get(translationOffset + 2);
        float _rotationx = rotation.get(rotationOffset + 0);
        float _rotationy = rotation.get(rotationOffset + 1);
        float _rotationz = rotation.get(rotationOffset + 2);
        float _rotationw = rotation.get(rotationOffset + 3);
        float _scalex = scale.get(scaleOffset + 0);
        float _scaley = scale.get(scaleOffset + 1);
        float _scalez = scale.get(scaleOffset + 2);
        float _t0 = _scalex + _scalex;
        float _t1 = _scaley + _scaley;
        float _t2 = _scalez + _scalez;
        float _t3 = _rotationz * _rotationz;
        float _t4 = _rotationz * _rotationw;
        float _t5 = _rotationy * _rotationw;
        dest.put(destOffset + 0, Math.fma(-Math.fma(_rotationy, _rotationy, _t3), _t0, _scalex));
        dest.put(destOffset + 1, Math.fma(_rotationx, _rotationy, -_t4) * _t1);
        dest.put(destOffset + 2, Math.fma(_rotationx, _rotationz, _t5) * _t2);
        dest.put(destOffset + 3, _translationx);
        dest.put(destOffset + 4, Math.fma(_rotationx, _rotationy, _t4) * _t0);
        dest.put(destOffset + 5, Math.fma(-Math.fma(_rotationx, _rotationx, _t3), _t1, _scaley));
        dest.put(destOffset + 6, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t2);
        dest.put(destOffset + 7, _translationy);
        dest.put(destOffset + 8, Math.fma(_rotationx, _rotationz, -_t5) * _t0);
        dest.put(destOffset + 9, Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t1);
        dest.put(destOffset + 10, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t2, _scalez));
        dest.put(destOffset + 11, _translationz);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSAround_unsafe(java.nio.FloatBuffer dest, int destOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.composeTRSAround_unsafe(_destBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSAround_api(java.nio.FloatBuffer dest, int destOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.composeTRSAround(dest.array(), dest.arrayOffset() + destOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.composeTRSAround_apiGet(dest, destOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSAround_apiGet(java.nio.FloatBuffer dest, int destOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ, float pivotX, float pivotY, float pivotZ) {
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
        dest.put(destOffset + 0, Math.fma(-_t15, _t3, scaleX));
        dest.put(destOffset + 1, _t27);
        dest.put(destOffset + 2, _t24);
        dest.put(destOffset + 3, Math.fma(pivotX, Math.fma(_t15, _t3, 1.0f - scaleX), Math.fma(_t0, _t27, Math.fma(_t1, _t24, translationX))));
        dest.put(destOffset + 4, _t25);
        dest.put(destOffset + 5, Math.fma(-_t18, _t4, scaleY));
        dest.put(destOffset + 6, _t28);
        dest.put(destOffset + 7, Math.fma(pivotY, Math.fma(_t18, _t4, 1.0f - scaleY), Math.fma(_t2, _t25, Math.fma(_t1, _t28, translationY))));
        dest.put(destOffset + 8, _t29);
        dest.put(destOffset + 9, _t26);
        dest.put(destOffset + 10, Math.fma(-_t20, _t5, scaleZ));
        dest.put(destOffset + 11, Math.fma(pivotZ, Math.fma(_t20, _t5, 1.0f - scaleZ), Math.fma(_t2, _t29, Math.fma(_t0, _t26, translationZ))));
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) translationOffset * 4L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rotationOffset * 4L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) scaleOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x4OpsKernelsAddress.composeTRSAround_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _pivotBase);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && translation.hasArray() && translationOffset >= 0 && translationOffset <= translation.limit() - 3 && rotation.hasArray() && rotationOffset >= 0 && rotationOffset <= rotation.limit() - 4 && scale.hasArray() && scaleOffset >= 0 && scaleOffset <= scale.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3x4Ops.composeTRSAround(dest.array(), dest.arrayOffset() + destOffset, translation.array(), translation.arrayOffset() + translationOffset, rotation.array(), rotation.arrayOffset() + rotationOffset, scale.array(), scale.arrayOffset() + scaleOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.composeTRSAround_apiGet(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        float _translationx = translation.get(translationOffset + 0);
        float _translationy = translation.get(translationOffset + 1);
        float _translationz = translation.get(translationOffset + 2);
        float _rotationx = rotation.get(rotationOffset + 0);
        float _rotationy = rotation.get(rotationOffset + 1);
        float _rotationz = rotation.get(rotationOffset + 2);
        float _rotationw = rotation.get(rotationOffset + 3);
        float _scalex = scale.get(scaleOffset + 0);
        float _scaley = scale.get(scaleOffset + 1);
        float _scalez = scale.get(scaleOffset + 2);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _pivotz = pivot.get(pivotOffset + 2);
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
        dest.put(destOffset + 0, Math.fma(-_t15, _t3, _scalex));
        dest.put(destOffset + 1, _t27);
        dest.put(destOffset + 2, _t24);
        dest.put(destOffset + 3, Math.fma(_pivotx, Math.fma(_t15, _t3, 1.0f - _scalex), Math.fma(_t0, _t27, Math.fma(_t1, _t24, _translationx))));
        dest.put(destOffset + 4, _t25);
        dest.put(destOffset + 5, Math.fma(-_t18, _t4, _scaley));
        dest.put(destOffset + 6, _t28);
        dest.put(destOffset + 7, Math.fma(_pivoty, Math.fma(_t18, _t4, 1.0f - _scaley), Math.fma(_t2, _t25, Math.fma(_t1, _t28, _translationy))));
        dest.put(destOffset + 8, _t29);
        dest.put(destOffset + 9, _t26);
        dest.put(destOffset + 10, Math.fma(-_t20, _t5, _scalez));
        dest.put(destOffset + 11, Math.fma(_pivotz, Math.fma(_t20, _t5, 1.0f - _scalez), Math.fma(_t2, _t29, Math.fma(_t0, _t26, _translationz))));
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 4L;
        Float3x4OpsKernelsAddress.composeTRSMul_unsafe(_destBase, _mBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 12) {
            Float3x4Ops.composeTRSMul(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.composeTRSMul_apiGet(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _m00 = m.get(mOffset + 0);
        float _m01 = m.get(mOffset + 1);
        float _m02 = m.get(mOffset + 2);
        float _m03 = m.get(mOffset + 3);
        float _m10 = m.get(mOffset + 4);
        float _m11 = m.get(mOffset + 5);
        float _m12 = m.get(mOffset + 6);
        float _m13 = m.get(mOffset + 7);
        float _m20 = m.get(mOffset + 8);
        float _m21 = m.get(mOffset + 9);
        float _m22 = m.get(mOffset + 10);
        float _m23 = m.get(mOffset + 11);
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
        dest.put(destOffset + 0, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        dest.put(destOffset + 1, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        dest.put(destOffset + 2, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        dest.put(destOffset + 3, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, translationX))));
        dest.put(destOffset + 4, Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31)));
        dest.put(destOffset + 5, Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31)));
        dest.put(destOffset + 6, Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31)));
        dest.put(destOffset + 7, Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, translationY))));
        dest.put(destOffset + 8, Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26)));
        dest.put(destOffset + 9, Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26)));
        dest.put(destOffset + 10, Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26)));
        dest.put(destOffset + 11, Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, translationZ))));
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) translationOffset * 4L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rotationOffset * 4L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) scaleOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 4L;
        Float3x4OpsKernelsAddress.composeTRSMul_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && translation.hasArray() && translationOffset >= 0 && translationOffset <= translation.limit() - 3 && rotation.hasArray() && rotationOffset >= 0 && rotationOffset <= rotation.limit() - 4 && scale.hasArray() && scaleOffset >= 0 && scaleOffset <= scale.limit() - 3 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 12) {
            Float3x4Ops.composeTRSMul(dest.array(), dest.arrayOffset() + destOffset, translation.array(), translation.arrayOffset() + translationOffset, rotation.array(), rotation.arrayOffset() + rotationOffset, scale.array(), scale.arrayOffset() + scaleOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.composeTRSMul_apiGet(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer m, int mOffset) {
        float _translationx = translation.get(translationOffset + 0);
        float _translationy = translation.get(translationOffset + 1);
        float _translationz = translation.get(translationOffset + 2);
        float _rotationx = rotation.get(rotationOffset + 0);
        float _rotationy = rotation.get(rotationOffset + 1);
        float _rotationz = rotation.get(rotationOffset + 2);
        float _rotationw = rotation.get(rotationOffset + 3);
        float _scalex = scale.get(scaleOffset + 0);
        float _scaley = scale.get(scaleOffset + 1);
        float _scalez = scale.get(scaleOffset + 2);
        float _m00 = m.get(mOffset + 0);
        float _m01 = m.get(mOffset + 1);
        float _m02 = m.get(mOffset + 2);
        float _m03 = m.get(mOffset + 3);
        float _m10 = m.get(mOffset + 4);
        float _m11 = m.get(mOffset + 5);
        float _m12 = m.get(mOffset + 6);
        float _m13 = m.get(mOffset + 7);
        float _m20 = m.get(mOffset + 8);
        float _m21 = m.get(mOffset + 9);
        float _m22 = m.get(mOffset + 10);
        float _m23 = m.get(mOffset + 11);
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
        dest.put(destOffset + 0, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        dest.put(destOffset + 1, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        dest.put(destOffset + 2, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        dest.put(destOffset + 3, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, _translationx))));
        dest.put(destOffset + 4, Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31)));
        dest.put(destOffset + 5, Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31)));
        dest.put(destOffset + 6, Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31)));
        dest.put(destOffset + 7, Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, _translationy))));
        dest.put(destOffset + 8, Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26)));
        dest.put(destOffset + 9, Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26)));
        dest.put(destOffset + 10, Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26)));
        dest.put(destOffset + 11, Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, _translationz))));
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.lookAlong(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.lookAlong_apiGet(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.put(destOffset + 2, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.put(destOffset + 5, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.put(destOffset + 6, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        dest.put(destOffset + 9, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.put(destOffset + 10, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 4L;
        Float3x4OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Float3x4Ops.lookAlong(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.lookAlong_apiGet(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.put(destOffset + 2, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39)));
        dest.put(destOffset + 5, Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48)));
        dest.put(destOffset + 6, Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39)));
        dest.put(destOffset + 9, Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48)));
        dest.put(destOffset + 10, Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_lh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.lookAt_lh_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Float3x4OpsKernelsTypedBuffer.lookAt_lh_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.FloatBuffer lookAt_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.lookAt_lh_unsafe(_destBase, _srcBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_lh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4OpsKernelsArray.lookAt_lh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.lookAt_lh_apiGet(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        dest.put(destOffset + 1, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        dest.put(destOffset + 2, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        dest.put(destOffset + 3, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        dest.put(destOffset + 4, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        dest.put(destOffset + 5, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        dest.put(destOffset + 6, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        dest.put(destOffset + 7, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        dest.put(destOffset + 8, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        dest.put(destOffset + 9, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        dest.put(destOffset + 10, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        dest.put(destOffset + 11, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_rh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.lookAt_rh_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Float3x4OpsKernelsTypedBuffer.lookAt_rh_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.FloatBuffer lookAt_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.lookAt_rh_unsafe(_destBase, _srcBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_rh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4OpsKernelsArray.lookAt_rh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.lookAt_rh_apiGet(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        dest.put(destOffset + 1, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        dest.put(destOffset + 2, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        dest.put(destOffset + 3, Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03))));
        dest.put(destOffset + 4, Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57)));
        dest.put(destOffset + 5, Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58)));
        dest.put(destOffset + 6, Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59)));
        dest.put(destOffset + 7, Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13))));
        dest.put(destOffset + 8, Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57)));
        dest.put(destOffset + 9, Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58)));
        dest.put(destOffset + 10, Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59)));
        dest.put(destOffset + 11, Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_lh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.lookAt_lh_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Float3x4OpsKernelsTypedBuffer.lookAt_lh_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.FloatBuffer lookAt_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) eyeOffset * 4L;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) centerOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 4L;
        Float3x4OpsKernelsAddress.lookAt_lh_unsafe(_destBase, _srcBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_lh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && eye.hasArray() && eyeOffset >= 0 && eyeOffset <= eye.limit() - 3 && center.hasArray() && centerOffset >= 0 && centerOffset <= center.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Float3x4OpsKernelsArray.lookAt_lh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, eye.array(), eye.arrayOffset() + eyeOffset, center.array(), center.arrayOffset() + centerOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.lookAt_lh_apiGet(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _eyex = eye.get(eyeOffset + 0);
        float _eyey = eye.get(eyeOffset + 1);
        float _eyez = eye.get(eyeOffset + 2);
        float _centerx = center.get(centerOffset + 0);
        float _centery = center.get(centerOffset + 1);
        float _centerz = center.get(centerOffset + 2);
        float _upx = up.get(upOffset + 0);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        dest.put(destOffset + 1, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        dest.put(destOffset + 2, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        dest.put(destOffset + 3, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        dest.put(destOffset + 4, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        dest.put(destOffset + 5, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        dest.put(destOffset + 6, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        dest.put(destOffset + 7, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        dest.put(destOffset + 8, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        dest.put(destOffset + 9, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        dest.put(destOffset + 10, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        dest.put(destOffset + 11, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_rh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.lookAt_rh_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Float3x4OpsKernelsTypedBuffer.lookAt_rh_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.FloatBuffer lookAt_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) eyeOffset * 4L;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) centerOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 4L;
        Float3x4OpsKernelsAddress.lookAt_rh_unsafe(_destBase, _srcBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_rh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && eye.hasArray() && eyeOffset >= 0 && eyeOffset <= eye.limit() - 3 && center.hasArray() && centerOffset >= 0 && centerOffset <= center.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Float3x4OpsKernelsArray.lookAt_rh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, eye.array(), eye.arrayOffset() + eyeOffset, center.array(), center.arrayOffset() + centerOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.lookAt_rh_apiGet(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer lookAt_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _eyex = eye.get(eyeOffset + 0);
        float _eyey = eye.get(eyeOffset + 1);
        float _eyez = eye.get(eyeOffset + 2);
        float _centerx = center.get(centerOffset + 0);
        float _centery = center.get(centerOffset + 1);
        float _centerz = center.get(centerOffset + 2);
        float _upx = up.get(upOffset + 0);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
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
        dest.put(destOffset + 0, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        dest.put(destOffset + 1, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        dest.put(destOffset + 2, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        dest.put(destOffset + 3, Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03))));
        dest.put(destOffset + 4, Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57)));
        dest.put(destOffset + 5, Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58)));
        dest.put(destOffset + 6, Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59)));
        dest.put(destOffset + 7, Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13))));
        dest.put(destOffset + 8, Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57)));
        dest.put(destOffset + 9, Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58)));
        dest.put(destOffset + 10, Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59)));
        dest.put(destOffset + 11, Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardCylindrical_unsafe(java.nio.FloatBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeBillboardCylindrical_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardCylindrical_api(java.nio.FloatBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeBillboardCylindrical(dest.array(), dest.arrayOffset() + destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeBillboardCylindrical_apiGet(dest, destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardCylindrical_apiGet(java.nio.FloatBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
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
            dest.put(destOffset + 2, _t47 * _t51);
            dest.put(destOffset + 6, _t46 * _t51);
            dest.put(destOffset + 10, _t45 * _t51);
        } else {
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 6, 0.0f);
            dest.put(destOffset + 10, 0.0f);
        }
        dest.put(destOffset + 0, _t36);
        dest.put(destOffset + 1, upX);
        dest.put(destOffset + 3, objPosX);
        dest.put(destOffset + 4, _t37);
        dest.put(destOffset + 5, upY);
        dest.put(destOffset + 7, objPosY);
        dest.put(destOffset + 8, _t38);
        dest.put(destOffset + 9, upZ);
        dest.put(destOffset + 11, objPosZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardCylindrical_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer objPos, int objPosOffset, java.nio.FloatBuffer targetPos, int targetPosOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) objPosOffset * 4L;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) targetPosOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 4L;
        Float3x4OpsKernelsAddress.makeBillboardCylindrical_unsafe(_destBase, _objPosBase, _targetPosBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardCylindrical_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer objPos, int objPosOffset, java.nio.FloatBuffer targetPos, int targetPosOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && objPos.hasArray() && objPosOffset >= 0 && objPosOffset <= objPos.limit() - 3 && targetPos.hasArray() && targetPosOffset >= 0 && targetPosOffset <= targetPos.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Float3x4Ops.makeBillboardCylindrical(dest.array(), dest.arrayOffset() + destOffset, objPos.array(), objPos.arrayOffset() + objPosOffset, targetPos.array(), targetPos.arrayOffset() + targetPosOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeBillboardCylindrical_apiGet(dest, destOffset, objPos, objPosOffset, targetPos, targetPosOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardCylindrical_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer objPos, int objPosOffset, java.nio.FloatBuffer targetPos, int targetPosOffset, java.nio.FloatBuffer up, int upOffset) {
        float _objPosx = objPos.get(objPosOffset + 0);
        float _objPosy = objPos.get(objPosOffset + 1);
        float _objPosz = objPos.get(objPosOffset + 2);
        float _targetPosx = targetPos.get(targetPosOffset + 0);
        float _targetPosy = targetPos.get(targetPosOffset + 1);
        float _targetPosz = targetPos.get(targetPosOffset + 2);
        float _upx = up.get(upOffset + 0);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
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
            dest.put(destOffset + 2, _t47 * _t51);
            dest.put(destOffset + 6, _t46 * _t51);
            dest.put(destOffset + 10, _t45 * _t51);
        } else {
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 6, 0.0f);
            dest.put(destOffset + 10, 0.0f);
        }
        dest.put(destOffset + 0, _t36);
        dest.put(destOffset + 1, _upx);
        dest.put(destOffset + 3, _objPosx);
        dest.put(destOffset + 4, _t37);
        dest.put(destOffset + 5, _upy);
        dest.put(destOffset + 7, _objPosy);
        dest.put(destOffset + 8, _t38);
        dest.put(destOffset + 9, _upz);
        dest.put(destOffset + 11, _objPosz);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSpherical_unsafe(java.nio.FloatBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeBillboardSpherical_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSpherical_api(java.nio.FloatBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeBillboardSpherical(dest.array(), dest.arrayOffset() + destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeBillboardSpherical_apiGet(dest, destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSpherical_apiGet(java.nio.FloatBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) {
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
        dest.put(destOffset + 0, _t40);
        dest.put(destOffset + 1, Math.fma(_t41, _t16, -(_t42 * _t14)));
        dest.put(destOffset + 2, _t15);
        dest.put(destOffset + 3, objPosX);
        dest.put(destOffset + 4, _t42);
        dest.put(destOffset + 5, Math.fma(_t40, _t14, -(_t41 * _t15)));
        dest.put(destOffset + 6, _t16);
        dest.put(destOffset + 7, objPosY);
        dest.put(destOffset + 8, _t41);
        dest.put(destOffset + 9, Math.fma(_t42, _t15, -(_t40 * _t16)));
        dest.put(destOffset + 10, _t14);
        dest.put(destOffset + 11, objPosZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSpherical_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer objPos, int objPosOffset, java.nio.FloatBuffer targetPos, int targetPosOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) objPosOffset * 4L;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) targetPosOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 4L;
        Float3x4OpsKernelsAddress.makeBillboardSpherical_unsafe(_destBase, _objPosBase, _targetPosBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSpherical_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer objPos, int objPosOffset, java.nio.FloatBuffer targetPos, int targetPosOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && objPos.hasArray() && objPosOffset >= 0 && objPosOffset <= objPos.limit() - 3 && targetPos.hasArray() && targetPosOffset >= 0 && targetPosOffset <= targetPos.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Float3x4Ops.makeBillboardSpherical(dest.array(), dest.arrayOffset() + destOffset, objPos.array(), objPos.arrayOffset() + objPosOffset, targetPos.array(), targetPos.arrayOffset() + targetPosOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeBillboardSpherical_apiGet(dest, destOffset, objPos, objPosOffset, targetPos, targetPosOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSpherical_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer objPos, int objPosOffset, java.nio.FloatBuffer targetPos, int targetPosOffset, java.nio.FloatBuffer up, int upOffset) {
        float _objPosx = objPos.get(objPosOffset + 0);
        float _objPosy = objPos.get(objPosOffset + 1);
        float _objPosz = objPos.get(objPosOffset + 2);
        float _targetPosx = targetPos.get(targetPosOffset + 0);
        float _targetPosy = targetPos.get(targetPosOffset + 1);
        float _targetPosz = targetPos.get(targetPosOffset + 2);
        float _upx = up.get(upOffset + 0);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
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
        dest.put(destOffset + 0, _t40);
        dest.put(destOffset + 1, Math.fma(_t41, _t16, -(_t42 * _t14)));
        dest.put(destOffset + 2, _t15);
        dest.put(destOffset + 3, _objPosx);
        dest.put(destOffset + 4, _t42);
        dest.put(destOffset + 5, Math.fma(_t40, _t14, -(_t41 * _t15)));
        dest.put(destOffset + 6, _t16);
        dest.put(destOffset + 7, _objPosy);
        dest.put(destOffset + 8, _t41);
        dest.put(destOffset + 9, Math.fma(_t42, _t15, -(_t40 * _t16)));
        dest.put(destOffset + 10, _t14);
        dest.put(destOffset + 11, _objPosz);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSphericalShortest_unsafe(java.nio.FloatBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeBillboardSphericalShortest_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSphericalShortest_api(java.nio.FloatBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeBillboardSphericalShortest(dest.array(), dest.arrayOffset() + destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeBillboardSphericalShortest_apiGet(dest, destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSphericalShortest_apiGet(java.nio.FloatBuffer dest, int destOffset, float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ) {
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
        dest.put(destOffset + 0, _t32);
        dest.put(destOffset + 1, _t29);
        dest.put(destOffset + 2, _t30);
        dest.put(destOffset + 3, objPosX);
        dest.put(destOffset + 4, _t29);
        dest.put(destOffset + 5, 1.0f - _t26);
        dest.put(destOffset + 6, _t27);
        dest.put(destOffset + 7, objPosY);
        dest.put(destOffset + 8, -_t30);
        dest.put(destOffset + 9, -_t27);
        dest.put(destOffset + 10, _t32 - _t26);
        dest.put(destOffset + 11, objPosZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSphericalShortest_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer objPos, int objPosOffset, java.nio.FloatBuffer targetPos, int targetPosOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) objPosOffset * 4L;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) targetPosOffset * 4L;
        Float3x4OpsKernelsAddress.makeBillboardSphericalShortest_unsafe(_destBase, _objPosBase, _targetPosBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSphericalShortest_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer objPos, int objPosOffset, java.nio.FloatBuffer targetPos, int targetPosOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && objPos.hasArray() && objPosOffset >= 0 && objPosOffset <= objPos.limit() - 3 && targetPos.hasArray() && targetPosOffset >= 0 && targetPosOffset <= targetPos.limit() - 3) {
            Float3x4Ops.makeBillboardSphericalShortest(dest.array(), dest.arrayOffset() + destOffset, objPos.array(), objPos.arrayOffset() + objPosOffset, targetPos.array(), targetPos.arrayOffset() + targetPosOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeBillboardSphericalShortest_apiGet(dest, destOffset, objPos, objPosOffset, targetPos, targetPosOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeBillboardSphericalShortest_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer objPos, int objPosOffset, java.nio.FloatBuffer targetPos, int targetPosOffset) {
        float _objPosx = objPos.get(objPosOffset + 0);
        float _objPosy = objPos.get(objPosOffset + 1);
        float _objPosz = objPos.get(objPosOffset + 2);
        float _targetPosx = targetPos.get(targetPosOffset + 0);
        float _targetPosy = targetPos.get(targetPosOffset + 1);
        float _targetPosz = targetPos.get(targetPosOffset + 2);
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
        dest.put(destOffset + 0, _t32);
        dest.put(destOffset + 1, _t29);
        dest.put(destOffset + 2, _t30);
        dest.put(destOffset + 3, _objPosx);
        dest.put(destOffset + 4, _t29);
        dest.put(destOffset + 5, 1.0f - _t26);
        dest.put(destOffset + 6, _t27);
        dest.put(destOffset + 7, _objPosy);
        dest.put(destOffset + 8, -_t30);
        dest.put(destOffset + 9, -_t27);
        dest.put(destOffset + 10, _t32 - _t26);
        dest.put(destOffset + 11, _objPosz);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromDualQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromDualQuat_api(java.nio.FloatBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeFromDualQuat(dest.array(), dest.arrayOffset() + destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeFromDualQuat_apiGet(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
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
        dest.put(destOffset + 1, Math.fma(-2.0f, _t2, _sp0 * dqRY));
        dest.put(destOffset + 2, 2.0f * Math.fma(dqRX, dqRZ, _t3));
        dest.put(destOffset + 3, 2.0f * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))));
        dest.put(destOffset + 4, 2.0f * Math.fma(dqRX, dqRY, _t2));
        dest.put(destOffset + 5, Math.fma(-2.0f, _t4, _t6));
        dest.put(destOffset + 6, Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5));
        dest.put(destOffset + 7, 2.0f * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))));
        dest.put(destOffset + 8, Math.fma(-2.0f, _t3, _sp0 * dqRZ));
        dest.put(destOffset + 9, 2.0f * Math.fma(dqRX, dqRW, _t5));
        dest.put(destOffset + 10, Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f)));
        dest.put(destOffset + 11, 2.0f * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))));
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_lh(java.nio.FloatBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.makeLookAt_lh_unsafe(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Float3x4OpsKernelsTypedBuffer.makeLookAt_lh_api(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.FloatBuffer makeLookAt_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeLookAt_lh_unsafe(_destBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_lh_api(java.nio.FloatBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4OpsKernelsArray.makeLookAt_lh(dest.array(), dest.arrayOffset() + destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeLookAt_lh_apiGet(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
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
        dest.put(destOffset + 0, _t40);
        dest.put(destOffset + 1, _t41);
        dest.put(destOffset + 2, _t42);
        dest.put(destOffset + 3, -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41)));
        dest.put(destOffset + 4, _t49);
        dest.put(destOffset + 5, _t50);
        dest.put(destOffset + 6, _t51);
        dest.put(destOffset + 7, -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50)));
        dest.put(destOffset + 8, _t15);
        dest.put(destOffset + 9, _t16);
        dest.put(destOffset + 10, _t14);
        dest.put(destOffset + 11, -Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16)));
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_rh(java.nio.FloatBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.makeLookAt_rh_unsafe(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Float3x4OpsKernelsTypedBuffer.makeLookAt_rh_api(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.FloatBuffer makeLookAt_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeLookAt_rh_unsafe(_destBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_rh_api(java.nio.FloatBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4OpsKernelsArray.makeLookAt_rh(dest.array(), dest.arrayOffset() + destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeLookAt_rh_apiGet(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
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
        dest.put(destOffset + 0, _t40);
        dest.put(destOffset + 1, _t41);
        dest.put(destOffset + 2, _t42);
        dest.put(destOffset + 3, -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41)));
        dest.put(destOffset + 4, _t49);
        dest.put(destOffset + 5, _t50);
        dest.put(destOffset + 6, _t51);
        dest.put(destOffset + 7, -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50)));
        dest.put(destOffset + 8, -_t15);
        dest.put(destOffset + 9, -_t16);
        dest.put(destOffset + 10, -_t14);
        dest.put(destOffset + 11, Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16)));
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_lh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.makeLookAt_lh_unsafe(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Float3x4OpsKernelsTypedBuffer.makeLookAt_lh_api(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.FloatBuffer makeLookAt_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) eyeOffset * 4L;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) centerOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 4L;
        Float3x4OpsKernelsAddress.makeLookAt_lh_unsafe(_destBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_lh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && eye.hasArray() && eyeOffset >= 0 && eyeOffset <= eye.limit() - 3 && center.hasArray() && centerOffset >= 0 && centerOffset <= center.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Float3x4OpsKernelsArray.makeLookAt_lh(dest.array(), dest.arrayOffset() + destOffset, eye.array(), eye.arrayOffset() + eyeOffset, center.array(), center.arrayOffset() + centerOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeLookAt_lh_apiGet(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        float _eyex = eye.get(eyeOffset + 0);
        float _eyey = eye.get(eyeOffset + 1);
        float _eyez = eye.get(eyeOffset + 2);
        float _centerx = center.get(centerOffset + 0);
        float _centery = center.get(centerOffset + 1);
        float _centerz = center.get(centerOffset + 2);
        float _upx = up.get(upOffset + 0);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
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
        dest.put(destOffset + 0, _t40);
        dest.put(destOffset + 1, _t41);
        dest.put(destOffset + 2, _t42);
        dest.put(destOffset + 3, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        dest.put(destOffset + 4, _t49);
        dest.put(destOffset + 5, _t50);
        dest.put(destOffset + 6, _t51);
        dest.put(destOffset + 7, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        dest.put(destOffset + 8, _t15);
        dest.put(destOffset + 9, _t16);
        dest.put(destOffset + 10, _t14);
        dest.put(destOffset + 11, -Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_rh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Float3x4OpsKernelsTypedBuffer.makeLookAt_rh_unsafe(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Float3x4OpsKernelsTypedBuffer.makeLookAt_rh_api(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.FloatBuffer makeLookAt_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) eyeOffset * 4L;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) centerOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 4L;
        Float3x4OpsKernelsAddress.makeLookAt_rh_unsafe(_destBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_rh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && eye.hasArray() && eyeOffset >= 0 && eyeOffset <= eye.limit() - 3 && center.hasArray() && centerOffset >= 0 && centerOffset <= center.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Float3x4OpsKernelsArray.makeLookAt_rh(dest.array(), dest.arrayOffset() + destOffset, eye.array(), eye.arrayOffset() + eyeOffset, center.array(), center.arrayOffset() + centerOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeLookAt_rh_apiGet(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeLookAt_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer eye, int eyeOffset, java.nio.FloatBuffer center, int centerOffset, java.nio.FloatBuffer up, int upOffset) {
        float _eyex = eye.get(eyeOffset + 0);
        float _eyey = eye.get(eyeOffset + 1);
        float _eyez = eye.get(eyeOffset + 2);
        float _centerx = center.get(centerOffset + 0);
        float _centery = center.get(centerOffset + 1);
        float _centerz = center.get(centerOffset + 2);
        float _upx = up.get(upOffset + 0);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
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
        dest.put(destOffset + 0, _t40);
        dest.put(destOffset + 1, _t41);
        dest.put(destOffset + 2, _t42);
        dest.put(destOffset + 3, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        dest.put(destOffset + 4, _t49);
        dest.put(destOffset + 5, _t50);
        dest.put(destOffset + 6, _t51);
        dest.put(destOffset + 7, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        dest.put(destOffset + 8, -_t15);
        dest.put(destOffset + 9, -_t16);
        dest.put(destOffset + 10, -_t14);
        dest.put(destOffset + 11, Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingXYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXYZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingXYZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingXYZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXYnZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingXYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXYnZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingXYnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingXYnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXYnZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, -1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXZY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingXZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXZY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingXZY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingXZY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXZY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXZnY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingXZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXZnY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingXZnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingXZnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXZnY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, -1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnYZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingXnYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnYZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingXnYZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingXnYZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnYZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, -1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnYnZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingXnYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnYnZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingXnYnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingXnYnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnYnZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, -1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, -1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnZY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingXnZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnZY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingXnZY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingXnZY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnZY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, -1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnZnY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingXnZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnZnY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingXnZnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingXnZnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingXnZnY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, -1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, -1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingYXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYXZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingYXZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingYXZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYXnZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingYXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYXnZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingYXnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingYXnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYXnZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, -1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYZX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingYZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYZX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingYZX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingYZX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYZX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYZnX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingYZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYZnX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingYZnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingYZnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYZnX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, -1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnXZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingYnXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnXZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingYnXZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingYnXZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnXZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, -1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnXnZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingYnXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnXnZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingYnXnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingYnXnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnXnZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, -1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, -1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnZX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingYnZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnZX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingYnZX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingYnZX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnZX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, -1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnZnX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingYnZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnZnX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingYnZnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingYnZnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingYnZnX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, -1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, -1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZXY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingZXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZXY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingZXY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingZXY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZXY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZXnY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingZXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZXnY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingZXnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingZXnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZXnY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, -1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZYX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingZYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZYX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingZYX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingZYX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZYX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZYnX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingZYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZYnX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingZYnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingZYnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZYnX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, -1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnXY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingZnXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnXY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingZnXY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingZnXY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnXY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, -1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnXnY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingZnXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnXnY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingZnXnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingZnXnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnXnY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, -1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, -1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnYX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingZnYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnYX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingZnYX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingZnYX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnYX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, -1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnYnX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingZnYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnYnX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingZnYnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingZnYnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingZnYnX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, -1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, -1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnXYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXYZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnXYZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnXYZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, -1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXYnZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnXYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXYnZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnXYnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnXYnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXYnZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, -1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, -1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXZY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnXZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXZY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnXZY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnXZY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXZY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, -1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXZnY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnXZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXZnY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnXZnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnXZnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXZnY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, -1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, -1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnYZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnXnYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnYZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnXnYZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnXnYZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnYZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, -1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, -1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnYnZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnXnYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnYnZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnXnYnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnXnYnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnYnZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, -1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, -1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, -1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnZY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnXnZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnZY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnXnZY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnXnZY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnZY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, -1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, -1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnZnY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnXnZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnZnY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnXnZnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnXnZnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnXnZnY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, -1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, -1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, -1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnYXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYXZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnYXZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnYXZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, -1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYXnZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnYXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYXnZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnYXnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnYXnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYXnZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, -1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, -1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYZX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnYZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYZX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnYZX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnYZX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYZX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, -1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYZnX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnYZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYZnX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnYZnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnYZnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYZnX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, -1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, -1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnXZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnYnXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnXZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnYnXZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnYnXZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnXZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, -1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, -1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnXnZ_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnYnXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnXnZ_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnYnXnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnYnXnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnXnZ_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, -1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, -1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, -1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnZX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnYnZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnZX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnYnZX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnYnZX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnZX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, -1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, -1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnZnX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnYnZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnZnX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnYnZnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnYnZnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnYnZnX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, -1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, -1.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, -1.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZXY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnZXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZXY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnZXY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnZXY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZXY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZXnY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnZXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZXnY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnZXnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnZXnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZXnY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, -1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZYX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnZYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZYX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnZYX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnZYX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZYX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZYnX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnZYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZYnX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnZYnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnZYnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZYnX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, -1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnXY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnZnXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnXY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnZnXY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnZnXY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnXY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, -1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnXnY_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnZnXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnXnY_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnZnXnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnZnXnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnXnY_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, -1.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, -1.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnYX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnZnYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnYX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnZnYX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnZnYX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnYX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, -1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnYnX_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeMappingnZnYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnYnX_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeMappingnZnYnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeMappingnZnYnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeMappingnZnYnX_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, -1.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, -1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -1.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 0.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeReflection_unsafe(java.nio.FloatBuffer dest, int destOffset, float normalX, float normalY, float normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeReflection_unsafe(_destBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeReflection_api(java.nio.FloatBuffer dest, int destOffset, float normalX, float normalY, float normalZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeReflection(dest.array(), dest.arrayOffset() + destOffset, normalX, normalY, normalZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeReflection_apiGet(dest, destOffset, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeReflection_apiGet(java.nio.FloatBuffer dest, int destOffset, float normalX, float normalY, float normalZ) {
        float _sp0 = normalX + normalX;
        float _t6 = -(_sp0 * normalY);
        float _t7 = -(_sp0 * normalZ);
        float _t8 = -((normalY + normalY) * normalZ);
        dest.put(destOffset + 0, Math.fma(-2.0f, normalX * normalX, 1.0f));
        dest.put(destOffset + 1, _t6);
        dest.put(destOffset + 2, _t7);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _t6);
        dest.put(destOffset + 5, Math.fma(-2.0f, normalY * normalY, 1.0f));
        dest.put(destOffset + 6, _t8);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, _t7);
        dest.put(destOffset + 9, _t8);
        dest.put(destOffset + 10, Math.fma(-2.0f, normalZ * normalZ, 1.0f));
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeReflection_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 4L;
        Float3x4OpsKernelsAddress.makeReflection_unsafe(_destBase, _normalBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeReflection_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            Float3x4Ops.makeReflection(dest.array(), dest.arrayOffset() + destOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeReflection_apiGet(dest, destOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeReflection_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _normalx = normal.get(normalOffset + 0);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _sp0 = _normalx + _normalx;
        float _t6 = -(_sp0 * _normaly);
        float _t7 = -(_sp0 * _normalz);
        float _t8 = -((_normaly + _normaly) * _normalz);
        dest.put(destOffset + 0, Math.fma(-2.0f, _normalx * _normalx, 1.0f));
        dest.put(destOffset + 1, _t6);
        dest.put(destOffset + 2, _t7);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _t6);
        dest.put(destOffset + 5, Math.fma(-2.0f, _normaly * _normaly, 1.0f));
        dest.put(destOffset + 6, _t8);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, _t7);
        dest.put(destOffset + 9, _t8);
        dest.put(destOffset + 10, Math.fma(-2.0f, _normalz * _normalz, 1.0f));
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_api(java.nio.FloatBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, angle, axisX, axisY, axisZ);
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
        dest.put(destOffset + 1, Math.fma(_t5, _t2, -(axisZ * _t0)));
        dest.put(destOffset + 2, Math.fma(axisY, _t0, _t5 * _t3));
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, Math.fma(axisZ, _t0, _t5 * _t2));
        dest.put(destOffset + 5, Math.fma(_t5, axisY * axisY, _t1));
        dest.put(destOffset + 6, Math.fma(_t5, _t4, -(axisX * _t0)));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, Math.fma(_t5, _t3, -(axisY * _t0)));
        dest.put(destOffset + 9, Math.fma(axisX, _t0, _t5 * _t4));
        dest.put(destOffset + 10, Math.fma(_t5, axisZ * axisZ, _t1));
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Float3x4Ops.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, axis, axisOffset, angle);
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
        dest.put(destOffset + 1, Math.fma(_t5, _t2, -(_axisz * _t0)));
        dest.put(destOffset + 2, Math.fma(_axisy, _t0, _t5 * _t3));
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, Math.fma(_axisz, _t0, _t5 * _t2));
        dest.put(destOffset + 5, Math.fma(_t5, _axisy * _axisy, _t1));
        dest.put(destOffset + 6, Math.fma(_t5, _t4, -(_axisx * _t0)));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, Math.fma(_t5, _t3, -(_axisy * _t0)));
        dest.put(destOffset + 9, Math.fma(_axisx, _t0, _t5 * _t4));
        dest.put(destOffset + 10, Math.fma(_t5, _axisz * _axisz, _t1));
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_unsafe(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_api(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationLookAlong(dest.array(), dest.arrayOffset() + destOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationLookAlong_apiGet(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
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
        dest.put(destOffset + 1, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.put(destOffset + 2, _t12);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _t39);
        dest.put(destOffset + 5, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.put(destOffset + 6, _t13);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, _t38);
        dest.put(destOffset + 9, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.put(destOffset + 10, _t11);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Float3x4Ops.makeRotationLookAlong(dest.array(), dest.arrayOffset() + destOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationLookAlong_apiGet(dest, destOffset, dir, dirOffset, up, upOffset);
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
        dest.put(destOffset + 1, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.put(destOffset + 2, _t12);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _t39);
        dest.put(destOffset + 5, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.put(destOffset + 6, _t13);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, _t38);
        dest.put(destOffset + 9, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.put(destOffset + 10, _t11);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationQuat_api(java.nio.FloatBuffer dest, int destOffset, float qX, float qY, float qZ, float qW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationQuat(dest.array(), dest.arrayOffset() + destOffset, qX, qY, qZ, qW);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationQuat_apiGet(dest, destOffset, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, float qX, float qY, float qZ, float qW) {
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        dest.put(destOffset + 0, Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f));
        dest.put(destOffset + 1, 2.0f * Math.fma(qX, qY, -_t1));
        dest.put(destOffset + 2, 2.0f * Math.fma(qX, qZ, _t2));
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 2.0f * Math.fma(qX, qY, _t1));
        dest.put(destOffset + 5, Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f));
        dest.put(destOffset + 6, 2.0f * Math.fma(qY, qZ, -(qX * qW)));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 2.0f * Math.fma(qX, qZ, -_t2));
        dest.put(destOffset + 9, 2.0f * Math.fma(qX, qW, qY * qZ));
        dest.put(destOffset + 10, Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f));
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) qOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, _qBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationQuat_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer q, int qOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && q.hasArray() && qOffset >= 0 && qOffset <= q.limit() - 4) {
            Float3x4Ops.makeRotationQuat(dest.array(), dest.arrayOffset() + destOffset, q.array(), q.arrayOffset() + qOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationQuat_apiGet(dest, destOffset, q, qOffset);
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
        dest.put(destOffset + 1, 2.0f * Math.fma(_qx, _qy, -_t1));
        dest.put(destOffset + 2, 2.0f * Math.fma(_qx, _qz, _t2));
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 2.0f * Math.fma(_qx, _qy, _t1));
        dest.put(destOffset + 5, Math.fma(-2.0f, Math.fma(_qx, _qx, _t0), 1.0f));
        dest.put(destOffset + 6, 2.0f * Math.fma(_qy, _qz, -(_qx * _qw)));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 2.0f * Math.fma(_qx, _qz, -_t2));
        dest.put(destOffset + 9, 2.0f * Math.fma(_qx, _qw, _qy * _qz));
        dest.put(destOffset + 10, Math.fma(-2.0f, Math.fma(_qx, _qx, _qy * _qy), 1.0f));
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationX_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationX_api(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationX(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationX_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationX_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, _t1);
        dest.put(destOffset + 6, -_t0);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, _t0);
        dest.put(destOffset + 10, _t1);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXYZ_api(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationXYZ(dest.array(), dest.arrayOffset() + destOffset, angleX, angleY, angleZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationXYZ_apiGet(dest, destOffset, angleX, angleY, angleZ);
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
        dest.put(destOffset + 1, -(_t1 * _t3));
        dest.put(destOffset + 2, _t0);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, Math.fma(_t6, _t4, _t1 * _t5));
        dest.put(destOffset + 5, Math.fma(_t5, _t4, -(_t6 * _t1)));
        dest.put(destOffset + 6, -(_t2 * _t3));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, Math.fma(_t2, _t1, -(_t7 * _t4)));
        dest.put(destOffset + 9, Math.fma(_t7, _t1, _t2 * _t4));
        dest.put(destOffset + 10, _t5 * _t3);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXZY_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXZY_api(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationXZY(dest.array(), dest.arrayOffset() + destOffset, angleX, angleZ, angleY);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationXZY_apiGet(dest, destOffset, angleX, angleZ, angleY);
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
        dest.put(destOffset + 1, -_t1);
        dest.put(destOffset + 2, _t0 * _t4);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, Math.fma(_t7, _t3, _t2 * _t0));
        dest.put(destOffset + 5, _t5 * _t4);
        dest.put(destOffset + 6, Math.fma(_t7, _t0, -(_t2 * _t3)));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, Math.fma(_t6, _t3, -(_t0 * _t5)));
        dest.put(destOffset + 9, _t2 * _t4);
        dest.put(destOffset + 10, Math.fma(_t6, _t0, _t5 * _t3));
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationY_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationY_api(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationY(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationY_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationY_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _t1);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, _t0);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -_t0);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, _t1);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYXZ_api(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationYXZ(dest.array(), dest.arrayOffset() + destOffset, angleY, angleX, angleZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationYXZ_apiGet(dest, destOffset, angleY, angleX, angleZ);
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
        dest.put(destOffset + 1, Math.fma(_t6, _t4, -(_t2 * _t3)));
        dest.put(destOffset + 2, _t1 * _t5);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _t2 * _t5);
        dest.put(destOffset + 5, _t5 * _t4);
        dest.put(destOffset + 6, -_t0);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, Math.fma(_t7, _t2, -(_t1 * _t4)));
        dest.put(destOffset + 9, Math.fma(_t7, _t4, _t1 * _t2));
        dest.put(destOffset + 10, _t5 * _t3);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYZX_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYZX_api(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationYZX(dest.array(), dest.arrayOffset() + destOffset, angleY, angleZ, angleX);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationYZX_apiGet(dest, destOffset, angleY, angleZ, angleX);
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
        dest.put(destOffset + 1, Math.fma(_t2, _t0, -(_t7 * _t5)));
        dest.put(destOffset + 2, Math.fma(_t7, _t2, _t0 * _t5));
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _t1);
        dest.put(destOffset + 5, _t5 * _t4);
        dest.put(destOffset + 6, -(_t2 * _t4));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -(_t0 * _t4));
        dest.put(destOffset + 9, Math.fma(_t6, _t5, _t2 * _t3));
        dest.put(destOffset + 10, Math.fma(_t5, _t3, -(_t6 * _t2)));
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZ_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZ_api(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationZ(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationZ_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZ_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _t1);
        dest.put(destOffset + 1, -_t0);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _t0);
        dest.put(destOffset + 5, _t1);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZXY_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZXY_api(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationZXY(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleX, angleY);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationZXY_apiGet(dest, destOffset, angleZ, angleX, angleY);
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
        dest.put(destOffset + 1, -(_t1 * _t5));
        dest.put(destOffset + 2, Math.fma(_t6, _t3, _t0 * _t4));
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, Math.fma(_t7, _t0, _t1 * _t3));
        dest.put(destOffset + 5, _t5 * _t4);
        dest.put(destOffset + 6, Math.fma(_t0, _t1, -(_t7 * _t3)));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -(_t0 * _t5));
        dest.put(destOffset + 9, _t2);
        dest.put(destOffset + 10, _t5 * _t3);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZYX_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZYX_api(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeRotationZYX(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleY, angleX);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeRotationZYX_apiGet(dest, destOffset, angleZ, angleY, angleX);
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
        dest.put(destOffset + 1, Math.fma(_t7, _t2, -(_t1 * _t5)));
        dest.put(destOffset + 2, Math.fma(_t7, _t5, _t2 * _t1));
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, _t1 * _t3);
        dest.put(destOffset + 5, Math.fma(_t6, _t2, _t5 * _t4));
        dest.put(destOffset + 6, Math.fma(_t6, _t5, -(_t2 * _t4)));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, -_t0);
        dest.put(destOffset + 9, _t2 * _t3);
        dest.put(destOffset + 10, _t5 * _t3);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, vX, vY, vZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ) {
        dest.put(destOffset + 0, vX);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, vY);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, vZ);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Float3x4Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        dest.put(destOffset + 0, _vx);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, _vy);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, _vz);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, float s) {
        dest.put(destOffset + 0, s);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, s);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, s);
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Float3x4Ops.makeTranslation(dest.array(), dest.arrayOffset() + destOffset, vX, vY, vZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeTranslation_apiGet(dest, destOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ) {
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, vX);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, vY);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x4OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Float3x4Ops.makeTranslation(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.makeTranslation_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        dest.put(destOffset + 0, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, _vx);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 1.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, _vy);
        dest.put(destOffset + 8, 0.0f);
        dest.put(destOffset + 9, 0.0f);
        dest.put(destOffset + 10, 1.0f);
        dest.put(destOffset + 11, _vz);
        return dest;
    }

    public static java.nio.FloatBuffer mapXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapXYZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapXYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.get(srcOffset + _i);
            dest.put(destOffset + _i, _eself);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapXYnZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapXYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapXYnZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapXYnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapXYnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapXYnZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapXZY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapXZY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapXZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapXZY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapXZnY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapXZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapXZnY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapXZnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapXZnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapXZnY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapXnYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapXnYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapXnYZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapXnYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapXnYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapXnYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapXnYnZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapXnYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapXnYnZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapXnYnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapXnYnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapXnYnZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapXnZY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapXnZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapXnZY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapXnZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapXnZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapXnZY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapXnZnY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapXnZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapXnZnY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapXnZnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapXnZnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapXnZnY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapYXZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapYXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself1);
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapYXnZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapYXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapYXnZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapYXnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapYXnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapYXnZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself1);
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapYZX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapYZX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapYZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapYZX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself1);
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapYZnX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapYZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapYZnX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapYZnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapYZnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapYZnX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself1);
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapYnXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapYnXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapYnXZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapYnXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapYnXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapYnXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself1);
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapYnXnZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapYnXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapYnXnZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapYnXnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapYnXnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapYnXnZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself1);
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapYnZX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapYnZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapYnZX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapYnZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapYnZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapYnZX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself1);
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapYnZnX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapYnZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapYnZnX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapYnZnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapYnZnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapYnZnX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself1);
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapZXY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapZXY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapZXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapZXY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself2);
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapZXnY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapZXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapZXnY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapZXnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapZXnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapZXnY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself2);
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapZYX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapZYX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapZYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapZYX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself2);
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapZYnX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapZYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapZYnX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapZYnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapZYnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapZYnX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself2);
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapZnXY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapZnXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapZnXY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapZnXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapZnXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapZnXY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself2);
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapZnXnY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapZnXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapZnXnY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapZnXnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapZnXnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapZnXnY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself2);
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapZnYX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapZnYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapZnYX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapZnYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapZnYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapZnYX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself2);
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapZnYnX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapZnYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapZnYnX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapZnYnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapZnYnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapZnYnX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself2);
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXYZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnXYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself0);
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnXYnZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnXYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXYnZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnXYnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnXYnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXYnZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself0);
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnXZY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXZY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnXZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXZY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself0);
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnXZnY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnXZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXZnY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnXZnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnXZnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXZnY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself0);
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnXnYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnYZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnXnYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnXnYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself0);
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnYnZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnXnYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnYnZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnXnYnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnXnYnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnYnZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself0);
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnZY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnXnZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnZY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnXnZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnXnZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnZY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself0);
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnZnY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnXnZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnZnY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnXnZnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnXnZnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnXnZnY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself0);
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYXZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnYXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself1);
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnYXnZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnYXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYXnZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnYXnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnYXnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYXnZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself1);
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnYZX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYZX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnYZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYZX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself1);
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnYZnX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnYZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYZnX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnYZnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnYZnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYZnX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself1);
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnYnXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnXZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnYnXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnYnXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself1);
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnXnZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnYnXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnXnZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnYnXnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnYnXnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnXnZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself1);
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnZX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnYnZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnZX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnYnZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnYnZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnZX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself1);
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnZnX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnYnZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnZnX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnYnZnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnYnZnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnYnZnX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself1);
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnZXY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZXY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnZXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZXY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself2);
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnZXnY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnZXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZXnY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnZXnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnZXnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZXnY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself2);
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnZYX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZYX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnZYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZYX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself2);
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnZYnX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnZYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZYnX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnZYnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnZYnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZYnX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself2);
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnXY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnZnXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnXY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnZnXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnZnXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnXY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself2);
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnXnY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnZnXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnXnY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnZnXnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnZnXnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnXnY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself2);
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnYX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnZnYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnYX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnZnYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnZnYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnYX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself2);
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnYnX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mapnZnYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnYnX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mapnZnYnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mapnZnYnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mapnZnYnX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -_eself2);
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.preRotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preRotateAround_apiGet(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.put(destOffset + 1, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.put(destOffset + 2, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.put(destOffset + 3, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))));
        dest.put(destOffset + 4, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        dest.put(destOffset + 5, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        dest.put(destOffset + 6, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        dest.put(destOffset + 7, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))));
        dest.put(destOffset + 8, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        dest.put(destOffset + 9, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        dest.put(destOffset + 10, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        dest.put(destOffset + 11, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer rot, int rotOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _rotBase = UnsafeOpsHolder.U.getLong(rot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rotOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x4OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _rotBase, _pivotBase);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer rot, int rotOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && rot.hasArray() && rotOffset >= 0 && rotOffset <= rot.limit() - 4 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3x4Ops.preRotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rot.array(), rot.arrayOffset() + rotOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preRotateAround_apiGet(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer rot, int rotOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _rotx = rot.get(rotOffset + 0);
        float _roty = rot.get(rotOffset + 1);
        float _rotz = rot.get(rotOffset + 2);
        float _rotw = rot.get(rotOffset + 3);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _pivotz = pivot.get(pivotOffset + 2);
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
        dest.put(destOffset + 0, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.put(destOffset + 1, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.put(destOffset + 2, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.put(destOffset + 3, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))));
        dest.put(destOffset + 4, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        dest.put(destOffset + 5, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        dest.put(destOffset + 6, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        dest.put(destOffset + 7, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))));
        dest.put(destOffset + 8, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        dest.put(destOffset + 9, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        dest.put(destOffset + 10, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        dest.put(destOffset + 11, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.preRotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preRotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.put(destOffset + 2, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.put(destOffset + 3, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        dest.put(destOffset + 4, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.put(destOffset + 5, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.put(destOffset + 6, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.put(destOffset + 7, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        dest.put(destOffset + 8, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.put(destOffset + 9, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        dest.put(destOffset + 10, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        dest.put(destOffset + 11, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 4L;
        Float3x4OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Float3x4Ops.preRotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preRotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.put(destOffset + 2, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.put(destOffset + 3, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        dest.put(destOffset + 4, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.put(destOffset + 5, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.put(destOffset + 6, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.put(destOffset + 7, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        dest.put(destOffset + 8, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.put(destOffset + 9, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        dest.put(destOffset + 10, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        dest.put(destOffset + 11, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.preRotateQuat_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateQuat_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.preRotateQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, qX, qY, qZ, qW);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preRotateQuat_apiGet(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        dest.put(destOffset + 1, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        dest.put(destOffset + 2, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        dest.put(destOffset + 3, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        dest.put(destOffset + 4, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        dest.put(destOffset + 5, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        dest.put(destOffset + 6, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        dest.put(destOffset + 7, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        dest.put(destOffset + 8, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        dest.put(destOffset + 9, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        dest.put(destOffset + 10, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        dest.put(destOffset + 11, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) qOffset * 4L;
        Float3x4OpsKernelsAddress.preRotateQuat_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateQuat_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && q.hasArray() && qOffset >= 0 && qOffset <= q.limit() - 4) {
            Float3x4Ops.preRotateQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, q.array(), q.arrayOffset() + qOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preRotateQuat_apiGet(dest, destOffset, src, srcOffset, q, qOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _qx = q.get(qOffset + 0);
        float _qy = q.get(qOffset + 1);
        float _qz = q.get(qOffset + 2);
        float _qw = q.get(qOffset + 3);
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
        dest.put(destOffset + 0, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        dest.put(destOffset + 1, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        dest.put(destOffset + 2, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        dest.put(destOffset + 3, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        dest.put(destOffset + 4, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        dest.put(destOffset + 5, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        dest.put(destOffset + 6, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        dest.put(destOffset + 7, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        dest.put(destOffset + 8, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        dest.put(destOffset + 9, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        dest.put(destOffset + 10, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        dest.put(destOffset + 11, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.preRotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preRotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self10, _t1, -(_self20 * _t0)));
        dest.put(destOffset + 5, Math.fma(_self11, _t1, -(_self21 * _t0)));
        dest.put(destOffset + 6, Math.fma(_self12, _t1, -(_self22 * _t0)));
        dest.put(destOffset + 7, Math.fma(_self13, _t1, -(_self23 * _t0)));
        dest.put(destOffset + 8, Math.fma(_self10, _t0, _self20 * _t1));
        dest.put(destOffset + 9, Math.fma(_self11, _t0, _self21 * _t1));
        dest.put(destOffset + 10, Math.fma(_self12, _t0, _self22 * _t1));
        dest.put(destOffset + 11, Math.fma(_self13, _t0, _self23 * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.preRotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preRotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_self00, _t1, _self20 * _t0));
        dest.put(destOffset + 1, Math.fma(_self01, _t1, _self21 * _t0));
        dest.put(destOffset + 2, Math.fma(_self02, _t1, _self22 * _t0));
        dest.put(destOffset + 3, Math.fma(_self03, _t1, _self23 * _t0));
        dest.put(destOffset + 4, _self10);
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, _self12);
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self20, _t1, -(_self00 * _t0)));
        dest.put(destOffset + 9, Math.fma(_self21, _t1, -(_self01 * _t0)));
        dest.put(destOffset + 10, Math.fma(_self22, _t1, -(_self02 * _t0)));
        dest.put(destOffset + 11, Math.fma(_self23, _t1, -(_self03 * _t0)));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.preRotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preRotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.put(destOffset + 1, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.put(destOffset + 2, Math.fma(_self02, _t1, -(_self12 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self03, _t1, -(_self13 * _t0)));
        dest.put(destOffset + 4, Math.fma(_self00, _t0, _self10 * _t1));
        dest.put(destOffset + 5, Math.fma(_self01, _t0, _self11 * _t1));
        dest.put(destOffset + 6, Math.fma(_self02, _t0, _self12 * _t1));
        dest.put(destOffset + 7, Math.fma(_self03, _t0, _self13 * _t1));
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        dest.put(destOffset + 0, _self00 * vX);
        dest.put(destOffset + 1, _self01 * vX);
        dest.put(destOffset + 2, _self02 * vX);
        dest.put(destOffset + 3, _self03 * vX);
        dest.put(destOffset + 4, _self10 * vY);
        dest.put(destOffset + 5, _self11 * vY);
        dest.put(destOffset + 6, _self12 * vY);
        dest.put(destOffset + 7, _self13 * vY);
        dest.put(destOffset + 8, _self20 * vZ);
        dest.put(destOffset + 9, _self21 * vZ);
        dest.put(destOffset + 10, _self22 * vZ);
        dest.put(destOffset + 11, _self23 * vZ);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Float3x4Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        dest.put(destOffset + 0, _self00 * _vx);
        dest.put(destOffset + 1, _self01 * _vx);
        dest.put(destOffset + 2, _self02 * _vx);
        dest.put(destOffset + 3, _self03 * _vx);
        dest.put(destOffset + 4, _self10 * _vy);
        dest.put(destOffset + 5, _self11 * _vy);
        dest.put(destOffset + 6, _self12 * _vy);
        dest.put(destOffset + 7, _self13 * _vy);
        dest.put(destOffset + 8, _self20 * _vz);
        dest.put(destOffset + 9, _self21 * _vz);
        dest.put(destOffset + 10, _self22 * _vz);
        dest.put(destOffset + 11, _self23 * _vz);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        for (int _i = 0; _i < 12; _i++) {
            float _eself = src.get(srcOffset + _i);
            dest.put(destOffset + _i, s * _eself);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _t0 = 1.0f - s;
        dest.put(destOffset + 0, s * _self00);
        dest.put(destOffset + 1, s * _self01);
        dest.put(destOffset + 2, s * _self02);
        dest.put(destOffset + 3, Math.fma(s, _self03, pivotX * _t0));
        dest.put(destOffset + 4, s * _self10);
        dest.put(destOffset + 5, s * _self11);
        dest.put(destOffset + 6, s * _self12);
        dest.put(destOffset + 7, Math.fma(s, _self13, pivotY * _t0));
        dest.put(destOffset + 8, s * _self20);
        dest.put(destOffset + 9, s * _self21);
        dest.put(destOffset + 10, s * _self22);
        dest.put(destOffset + 11, Math.fma(s, _self23, pivotZ * _t0));
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3x4Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, s);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _pivotz = pivot.get(pivotOffset + 2);
        float _t0 = 1.0f - s;
        dest.put(destOffset + 0, s * _self00);
        dest.put(destOffset + 1, s * _self01);
        dest.put(destOffset + 2, s * _self02);
        dest.put(destOffset + 3, Math.fma(s, _self03, _pivotx * _t0));
        dest.put(destOffset + 4, s * _self10);
        dest.put(destOffset + 5, s * _self11);
        dest.put(destOffset + 6, s * _self12);
        dest.put(destOffset + 7, Math.fma(s, _self13, _pivoty * _t0));
        dest.put(destOffset + 8, s * _self20);
        dest.put(destOffset + 9, s * _self21);
        dest.put(destOffset + 10, s * _self22);
        dest.put(destOffset + 11, Math.fma(s, _self23, _pivotz * _t0));
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        dest.put(destOffset + 0, sX * _self00);
        dest.put(destOffset + 1, sX * _self01);
        dest.put(destOffset + 2, sX * _self02);
        dest.put(destOffset + 3, Math.fma(pivotX, 1.0f - sX, sX * _self03));
        dest.put(destOffset + 4, sY * _self10);
        dest.put(destOffset + 5, sY * _self11);
        dest.put(destOffset + 6, sY * _self12);
        dest.put(destOffset + 7, Math.fma(pivotY, 1.0f - sY, sY * _self13));
        dest.put(destOffset + 8, sZ * _self20);
        dest.put(destOffset + 9, sZ * _self21);
        dest.put(destOffset + 10, sZ * _self22);
        dest.put(destOffset + 11, Math.fma(pivotZ, 1.0f - sZ, sZ * _self23));
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) sOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && s.hasArray() && sOffset >= 0 && sOffset <= s.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3x4Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s.array(), s.arrayOffset() + sOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _sx = s.get(sOffset + 0);
        float _sy = s.get(sOffset + 1);
        float _sz = s.get(sOffset + 2);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _pivotz = pivot.get(pivotOffset + 2);
        dest.put(destOffset + 0, _sx * _self00);
        dest.put(destOffset + 1, _sx * _self01);
        dest.put(destOffset + 2, _sx * _self02);
        dest.put(destOffset + 3, Math.fma(_pivotx, 1.0f - _sx, _sx * _self03));
        dest.put(destOffset + 4, _sy * _self10);
        dest.put(destOffset + 5, _sy * _self11);
        dest.put(destOffset + 6, _sy * _self12);
        dest.put(destOffset + 7, Math.fma(_pivoty, 1.0f - _sy, _sy * _self13));
        dest.put(destOffset + 8, _sz * _self20);
        dest.put(destOffset + 9, _sz * _self21);
        dest.put(destOffset + 10, _sz * _self22);
        dest.put(destOffset + 11, Math.fma(_pivotz, 1.0f - _sz, _sz * _self23));
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.preTranslate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preTranslate_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, _self03 + vX);
        dest.put(destOffset + 4, _self10);
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, _self12);
        dest.put(destOffset + 7, _self13 + vY);
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, _self23 + vZ);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x4OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Float3x4Ops.preTranslate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.preTranslate_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, _self03 + _vx);
        dest.put(destOffset + 4, _self10);
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, _self12);
        dest.put(destOffset + 7, _self13 + _vy);
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, _self23 + _vz);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        dest.put(destOffset + 1, Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10))));
        dest.put(destOffset + 2, Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9))));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10))));
        dest.put(destOffset + 5, Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10))));
        dest.put(destOffset + 6, Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9))));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10))));
        dest.put(destOffset + 9, Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10))));
        dest.put(destOffset + 10, Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9))));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 4L;
        Float3x4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            Float3x4Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _normalx = normal.get(normalOffset + 0);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
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
        dest.put(destOffset + 0, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        dest.put(destOffset + 1, Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10))));
        dest.put(destOffset + 2, Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9))));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10))));
        dest.put(destOffset + 5, Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10))));
        dest.put(destOffset + 6, Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9))));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10))));
        dest.put(destOffset + 9, Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10))));
        dest.put(destOffset + 10, Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9))));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        dest.put(destOffset + 1, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        dest.put(destOffset + 2, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        dest.put(destOffset + 3, Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03))));
        dest.put(destOffset + 4, Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18)));
        dest.put(destOffset + 5, Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28)));
        dest.put(destOffset + 6, Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26)));
        dest.put(destOffset + 7, Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13))));
        dest.put(destOffset + 8, Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18)));
        dest.put(destOffset + 9, Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28)));
        dest.put(destOffset + 10, Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26)));
        dest.put(destOffset + 11, Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer rot, int rotOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _rotBase = UnsafeOpsHolder.U.getLong(rot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rotOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x4OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _rotBase, _pivotBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer rot, int rotOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && rot.hasArray() && rotOffset >= 0 && rotOffset <= rot.limit() - 4 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3x4Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rot.array(), rot.arrayOffset() + rotOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer rot, int rotOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _rotx = rot.get(rotOffset + 0);
        float _roty = rot.get(rotOffset + 1);
        float _rotz = rot.get(rotOffset + 2);
        float _rotw = rot.get(rotOffset + 3);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _pivotz = pivot.get(pivotOffset + 2);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        dest.put(destOffset + 1, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        dest.put(destOffset + 2, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        dest.put(destOffset + 3, Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03))));
        dest.put(destOffset + 4, Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18)));
        dest.put(destOffset + 5, Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28)));
        dest.put(destOffset + 6, Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26)));
        dest.put(destOffset + 7, Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13))));
        dest.put(destOffset + 8, Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18)));
        dest.put(destOffset + 9, Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28)));
        dest.put(destOffset + 10, Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26)));
        dest.put(destOffset + 11, Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.put(destOffset + 2, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.put(destOffset + 5, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.put(destOffset + 6, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.put(destOffset + 9, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.put(destOffset + 10, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 4L;
        Float3x4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Float3x4Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.put(destOffset + 2, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.put(destOffset + 5, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.put(destOffset + 6, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.put(destOffset + 9, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.put(destOffset + 10, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateQuat_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.FloatBuffer rotateQuat_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, qX, qY, qZ, qW);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateQuat_apiGet(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.FloatBuffer rotateQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.put(destOffset + 1, Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.put(destOffset + 2, Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.put(destOffset + 5, Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.put(destOffset + 6, Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14)));
        dest.put(destOffset + 9, Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.put(destOffset + 10, Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) qOffset * 4L;
        Float3x4OpsKernelsAddress.rotateQuat_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateQuat_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && q.hasArray() && qOffset >= 0 && qOffset <= q.limit() - 4) {
            Float3x4Ops.rotateQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, q.array(), q.arrayOffset() + qOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateQuat_apiGet(dest, destOffset, src, srcOffset, q, qOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _qx = q.get(qOffset + 0);
        float _qy = q.get(qOffset + 1);
        float _qz = q.get(qOffset + 2);
        float _qw = q.get(qOffset + 3);
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
        dest.put(destOffset + 0, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.put(destOffset + 1, Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.put(destOffset + 2, Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.put(destOffset + 5, Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.put(destOffset + 6, Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14)));
        dest.put(destOffset + 9, Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.put(destOffset + 10, Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, Math.fma(_self01, _t1, _self02 * _t0));
        dest.put(destOffset + 2, Math.fma(_self02, _t1, -(_self01 * _t0)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, _self10);
        dest.put(destOffset + 5, Math.fma(_self11, _t1, _self12 * _t0));
        dest.put(destOffset + 6, Math.fma(_self12, _t1, -(_self11 * _t0)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, Math.fma(_self21, _t1, _self22 * _t0));
        dest.put(destOffset + 10, Math.fma(_self22, _t1, -(_self21 * _t0)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXYZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleY, angleZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateXYZ_apiGet(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self02, _t19, Math.fma(_self01, _t21, -(_self00 * _t10))));
        dest.put(destOffset + 2, Math.fma(_self02, _t15, Math.fma(_self00, _t2, -(_self01 * _t11))));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self12, _t20, Math.fma(_self10, _t13, _self11 * _t18)));
        dest.put(destOffset + 5, Math.fma(_self12, _t19, Math.fma(_self11, _t21, -(_self10 * _t10))));
        dest.put(destOffset + 6, Math.fma(_self12, _t15, Math.fma(_self10, _t2, -(_self11 * _t11))));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self22, _t20, Math.fma(_self20, _t13, _self21 * _t18)));
        dest.put(destOffset + 9, Math.fma(_self22, _t19, Math.fma(_self21, _t21, -(_self20 * _t10))));
        dest.put(destOffset + 10, Math.fma(_self22, _t15, Math.fma(_self20, _t2, -(_self21 * _t11))));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXZY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXZY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleZ, angleY);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateXZY_apiGet(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXZY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self02, _t10, Math.fma(_self01, _t16, -(_self00 * _t1))));
        dest.put(destOffset + 2, Math.fma(_self02, _t19, Math.fma(_self00, _t11, _self01 * _t21)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self12, _t20, Math.fma(_self10, _t15, _self11 * _t18)));
        dest.put(destOffset + 5, Math.fma(_self12, _t10, Math.fma(_self11, _t16, -(_self10 * _t1))));
        dest.put(destOffset + 6, Math.fma(_self12, _t19, Math.fma(_self10, _t11, _self11 * _t21)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self22, _t20, Math.fma(_self20, _t15, _self21 * _t18)));
        dest.put(destOffset + 9, Math.fma(_self22, _t10, Math.fma(_self21, _t16, -(_self20 * _t1))));
        dest.put(destOffset + 10, Math.fma(_self22, _t19, Math.fma(_self20, _t11, _self21 * _t21)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_self00, _t1, -(_self02 * _t0)));
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, Math.fma(_self00, _t0, _self02 * _t1));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self10, _t1, -(_self12 * _t0)));
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, Math.fma(_self10, _t0, _self12 * _t1));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self20, _t1, -(_self22 * _t0)));
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, Math.fma(_self20, _t0, _self22 * _t1));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYXZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleX, angleZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateYXZ_apiGet(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16)));
        dest.put(destOffset + 2, Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0))));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10)));
        dest.put(destOffset + 5, Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16)));
        dest.put(destOffset + 6, Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0))));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10)));
        dest.put(destOffset + 9, Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16)));
        dest.put(destOffset + 10, Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0))));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYZX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYZX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleZ, angleX);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateYZX_apiGet(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYZX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self02, _t18, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.put(destOffset + 2, Math.fma(_self02, _t21, Math.fma(_self00, _t19, -(_self01 * _t11))));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(-_self12, _t7, Math.fma(_self10, _t13, _self11 * _t1)));
        dest.put(destOffset + 5, Math.fma(_self12, _t18, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.put(destOffset + 6, Math.fma(_self12, _t21, Math.fma(_self10, _t19, -(_self11 * _t11))));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(-_self22, _t7, Math.fma(_self20, _t13, _self21 * _t1)));
        dest.put(destOffset + 9, Math.fma(_self22, _t18, Math.fma(_self20, _t20, _self21 * _t14)));
        dest.put(destOffset + 10, Math.fma(_self22, _t21, Math.fma(_self20, _t19, -(_self21 * _t11))));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_self00, _t1, _self01 * _t0));
        dest.put(destOffset + 1, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self10, _t1, _self11 * _t0));
        dest.put(destOffset + 5, Math.fma(_self11, _t1, -(_self10 * _t0)));
        dest.put(destOffset + 6, _self12);
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self20, _t1, _self21 * _t0));
        dest.put(destOffset + 9, Math.fma(_self21, _t1, -(_self20 * _t0)));
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZXY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZXY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleX, angleY);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateZXY_apiGet(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZXY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self02, _t1, Math.fma(_self01, _t14, -(_self00 * _t10))));
        dest.put(destOffset + 2, Math.fma(_self02, _t15, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(-_self12, _t7, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.put(destOffset + 5, Math.fma(_self12, _t1, Math.fma(_self11, _t14, -(_self10 * _t10))));
        dest.put(destOffset + 6, Math.fma(_self12, _t15, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(-_self22, _t7, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.put(destOffset + 9, Math.fma(_self22, _t1, Math.fma(_self21, _t14, -(_self20 * _t10))));
        dest.put(destOffset + 10, Math.fma(_self22, _t15, Math.fma(_self20, _t19, _self21 * _t21)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZYX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZYX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.rotateZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleY, angleX);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.rotateZYX_apiGet(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZYX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset + 1, Math.fma(_self02, _t9, Math.fma(_self00, _t20, _self01 * _t18)));
        dest.put(destOffset + 2, Math.fma(_self02, _t17, Math.fma(_self00, _t19, _self01 * _t21)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(-_self12, _t0, Math.fma(_self10, _t15, _self11 * _t8)));
        dest.put(destOffset + 5, Math.fma(_self12, _t9, Math.fma(_self10, _t20, _self11 * _t18)));
        dest.put(destOffset + 6, Math.fma(_self12, _t17, Math.fma(_self10, _t19, _self11 * _t21)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(-_self22, _t0, Math.fma(_self20, _t15, _self21 * _t8)));
        dest.put(destOffset + 9, Math.fma(_self22, _t9, Math.fma(_self20, _t20, _self21 * _t18)));
        dest.put(destOffset + 10, Math.fma(_self22, _t17, Math.fma(_self20, _t19, _self21 * _t21)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.FloatBuffer scale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer scale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer scale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0 * vX);
            dest.put(destOffset + _lo + 1, _eself1 * vY);
            dest.put(destOffset + _lo + 2, _eself2 * vZ);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer scale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer scale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Float3x4Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer scale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0 * _vx);
            dest.put(destOffset + _lo + 1, _eself1 * _vy);
            dest.put(destOffset + _lo + 2, _eself2 * _vz);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer scale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer scale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer scale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, s * _eself0);
            dest.put(destOffset + _lo + 1, s * _eself1);
            dest.put(destOffset + _lo + 2, s * _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _t3 = pivotZ * _t0;
        dest.put(destOffset + 0, s * _self00);
        dest.put(destOffset + 1, s * _self01);
        dest.put(destOffset + 2, s * _self02);
        dest.put(destOffset + 3, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        dest.put(destOffset + 4, s * _self10);
        dest.put(destOffset + 5, s * _self11);
        dest.put(destOffset + 6, s * _self12);
        dest.put(destOffset + 7, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        dest.put(destOffset + 8, s * _self20);
        dest.put(destOffset + 9, s * _self21);
        dest.put(destOffset + 10, s * _self22);
        dest.put(destOffset + 11, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3x4Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, s);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _pivotz = pivot.get(pivotOffset + 2);
        float _t0 = 1.0f - s;
        float _t1 = _pivotx * _t0;
        float _t2 = _pivoty * _t0;
        float _t3 = _pivotz * _t0;
        dest.put(destOffset + 0, s * _self00);
        dest.put(destOffset + 1, s * _self01);
        dest.put(destOffset + 2, s * _self02);
        dest.put(destOffset + 3, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        dest.put(destOffset + 4, s * _self10);
        dest.put(destOffset + 5, s * _self11);
        dest.put(destOffset + 6, s * _self12);
        dest.put(destOffset + 7, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        dest.put(destOffset + 8, s * _self20);
        dest.put(destOffset + 9, s * _self21);
        dest.put(destOffset + 10, s * _self22);
        dest.put(destOffset + 11, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        dest.put(destOffset + 0, sX * _self00);
        dest.put(destOffset + 1, sY * _self01);
        dest.put(destOffset + 2, sZ * _self02);
        dest.put(destOffset + 3, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        dest.put(destOffset + 4, sX * _self10);
        dest.put(destOffset + 5, sY * _self11);
        dest.put(destOffset + 6, sZ * _self12);
        dest.put(destOffset + 7, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        dest.put(destOffset + 8, sX * _self20);
        dest.put(destOffset + 9, sY * _self21);
        dest.put(destOffset + 10, sZ * _self22);
        dest.put(destOffset + 11, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) sOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && s.hasArray() && sOffset >= 0 && sOffset <= s.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3x4Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s.array(), s.arrayOffset() + sOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _sx = s.get(sOffset + 0);
        float _sy = s.get(sOffset + 1);
        float _sz = s.get(sOffset + 2);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _pivotz = pivot.get(pivotOffset + 2);
        float _t3 = _pivotx * (1.0f - _sx);
        float _t4 = _pivoty * (1.0f - _sy);
        float _t5 = _pivotz * (1.0f - _sz);
        dest.put(destOffset + 0, _sx * _self00);
        dest.put(destOffset + 1, _sy * _self01);
        dest.put(destOffset + 2, _sz * _self02);
        dest.put(destOffset + 3, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        dest.put(destOffset + 4, _sx * _self10);
        dest.put(destOffset + 5, _sy * _self11);
        dest.put(destOffset + 6, _sz * _self12);
        dest.put(destOffset + 7, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        dest.put(destOffset + 8, _sx * _self20);
        dest.put(destOffset + 9, _sy * _self21);
        dest.put(destOffset + 10, _sz * _self22);
        dest.put(destOffset + 11, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer translate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer translate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.translate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.translate_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer translate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, Math.fma(_eself0, vX, Math.fma(_eself1, vY, Math.fma(_eself2, vZ, _eself3))));
        }
        return dest;
    }

    public static java.nio.FloatBuffer translate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x4OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer translate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Float3x4Ops.translate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.translate_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer translate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eself2 = src.get(srcOffset + _lo + 2);
            float _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, Math.fma(_eself0, _vx, Math.fma(_eself1, _vy, Math.fma(_eself2, _vz, _eself3))));
        }
        return dest;
    }

    public static java.nio.FloatBuffer mulVec4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.mulVec4_unsafe(_destBase, _srcBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.mulVec4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ, vW);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mulVec4_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        dest.put(destOffset + 0, Math.fma(_self03, vW, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY))));
        dest.put(destOffset + 1, Math.fma(_self13, vW, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY))));
        dest.put(destOffset + 2, Math.fma(_self23, vW, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY))));
        return dest;
    }

    public static java.nio.FloatBuffer mulVec4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x4OpsKernelsAddress.mulVec4_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4) {
            Float3x4Ops.mulVec4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.mulVec4_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        float _vw = v.get(vOffset + 3);
        dest.put(destOffset + 0, Math.fma(_self03, _vw, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy))));
        dest.put(destOffset + 1, Math.fma(_self13, _vw, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy))));
        dest.put(destOffset + 2, Math.fma(_self23, _vw, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy))));
        return dest;
    }

    public static java.nio.FloatBuffer transformAabb_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.transformAabb_unsafe(_destBase, _srcBase, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.FloatBuffer transformAabb_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.transformAabb(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.transformAabb_apiGet(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.FloatBuffer transformAabb_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
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
            dest.put(destOffset + 0, Float.POSITIVE_INFINITY);
            dest.put(destOffset + 1, Float.POSITIVE_INFINITY);
            dest.put(destOffset + 2, Float.POSITIVE_INFINITY);
            dest.put(destOffset + 3, Float.NEGATIVE_INFINITY);
            dest.put(destOffset + 4, Float.NEGATIVE_INFINITY);
            dest.put(destOffset + 5, Float.NEGATIVE_INFINITY);
        } else {
            dest.put(destOffset + 0, _self03 + Math.min(_t3, _t4) + Math.min(_t5, _t6) + Math.min(_t7, _t8));
            dest.put(destOffset + 1, _self13 + Math.min(_t9, _t10) + Math.min(_t11, _t12) + Math.min(_t13, _t14));
            dest.put(destOffset + 2, _self23 + Math.min(_t15, _t16) + Math.min(_t17, _t18) + Math.min(_t19, _t20));
            dest.put(destOffset + 3, _self03 + Math.max(_t3, _t4) + Math.max(_t5, _t6) + Math.max(_t7, _t8));
            dest.put(destOffset + 4, _self13 + Math.max(_t9, _t10) + Math.max(_t11, _t12) + Math.max(_t13, _t14));
            dest.put(destOffset + 5, _self23 + Math.max(_t15, _t16) + Math.max(_t17, _t18) + Math.max(_t19, _t20));
        }
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.transformDirection(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.transformDirection_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        dest.put(destOffset + 0, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        dest.put(destOffset + 1, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        dest.put(destOffset + 2, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Float3x4Ops.transformDirection(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.transformDirection_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        dest.put(destOffset + 0, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        dest.put(destOffset + 1, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        dest.put(destOffset + 2, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Float3x4Ops.transformPosition(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.transformPosition_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        dest.put(destOffset + 0, Math.fma(_self00, vX, Math.fma(_self01, vY, Math.fma(_self02, vZ, _self03))));
        dest.put(destOffset + 1, Math.fma(_self10, vX, Math.fma(_self11, vY, Math.fma(_self12, vZ, _self13))));
        dest.put(destOffset + 2, Math.fma(_self20, vX, Math.fma(_self21, vY, Math.fma(_self22, vZ, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Float3x4Ops.transformPosition(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float3x4OpsKernelsTypedBuffer.transformPosition_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self00 = src.get(srcOffset + 0);
        float _self01 = src.get(srcOffset + 1);
        float _self02 = src.get(srcOffset + 2);
        float _self03 = src.get(srcOffset + 3);
        float _self10 = src.get(srcOffset + 4);
        float _self11 = src.get(srcOffset + 5);
        float _self12 = src.get(srcOffset + 6);
        float _self13 = src.get(srcOffset + 7);
        float _self20 = src.get(srcOffset + 8);
        float _self21 = src.get(srcOffset + 9);
        float _self22 = src.get(srcOffset + 10);
        float _self23 = src.get(srcOffset + 11);
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        dest.put(destOffset + 0, Math.fma(_self00, _vx, Math.fma(_self01, _vy, Math.fma(_self02, _vz, _self03))));
        dest.put(destOffset + 1, Math.fma(_self10, _vx, Math.fma(_self11, _vy, Math.fma(_self12, _vz, _self13))));
        dest.put(destOffset + 2, Math.fma(_self20, _vx, Math.fma(_self21, _vy, Math.fma(_self22, _vz, _self23))));
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer matrix, int matrixOffset, java.nio.FloatBuffer points, int pointsOffset, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matrixOffset * 4L;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pointsOffset * 4L;
        Float3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _matrixBase, _pointsBase, count);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer matrix, int matrixOffset, java.nio.FloatBuffer points, int pointsOffset, int count) {
        float _m00 = matrix.get(matrixOffset + 0);
        float _m01 = matrix.get(matrixOffset + 1);
        float _m02 = matrix.get(matrixOffset + 2);
        float _m03 = matrix.get(matrixOffset + 3);
        float _m10 = matrix.get(matrixOffset + 4);
        float _m11 = matrix.get(matrixOffset + 5);
        float _m12 = matrix.get(matrixOffset + 6);
        float _m13 = matrix.get(matrixOffset + 7);
        float _m20 = matrix.get(matrixOffset + 8);
        float _m21 = matrix.get(matrixOffset + 9);
        float _m22 = matrix.get(matrixOffset + 10);
        float _m23 = matrix.get(matrixOffset + 11);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * 3;
            int _do = destOffset + _i * 3;
            float px = points.get(_po + 0), py = points.get(_po + 1), pz = points.get(_po + 2);
            dest.put(_do + 0, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            dest.put(_do + 1, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            dest.put(_do + 2, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_unsafe(java.nio.FloatBuffer dest, int destOffset, int destStride, java.nio.FloatBuffer matrix, int matrixOffset, java.nio.FloatBuffer points, int pointsOffset, int pointsStride, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matrixOffset * 4L;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pointsOffset * 4L;
        Float3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, (long) destStride * 4L, _matrixBase, _pointsBase, (long) pointsStride * 4L, count);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_api(java.nio.FloatBuffer dest, int destOffset, int destStride, java.nio.FloatBuffer matrix, int matrixOffset, java.nio.FloatBuffer points, int pointsOffset, int pointsStride, int count) {
        float _m00 = matrix.get(matrixOffset + 0);
        float _m01 = matrix.get(matrixOffset + 1);
        float _m02 = matrix.get(matrixOffset + 2);
        float _m03 = matrix.get(matrixOffset + 3);
        float _m10 = matrix.get(matrixOffset + 4);
        float _m11 = matrix.get(matrixOffset + 5);
        float _m12 = matrix.get(matrixOffset + 6);
        float _m13 = matrix.get(matrixOffset + 7);
        float _m20 = matrix.get(matrixOffset + 8);
        float _m21 = matrix.get(matrixOffset + 9);
        float _m22 = matrix.get(matrixOffset + 10);
        float _m23 = matrix.get(matrixOffset + 11);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * pointsStride;
            int _do = destOffset + _i * destStride;
            float px = points.get(_po + 0), py = points.get(_po + 1), pz = points.get(_po + 2);
            dest.put(_do + 0, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            dest.put(_do + 1, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            dest.put(_do + 2, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer matrix, int matrixOffset, java.nio.FloatBuffer points, int pointsOffset, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matrixOffset * 4L;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pointsOffset * 4L;
        Float3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _matrixBase, _pointsBase, count);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer matrix, int matrixOffset, java.nio.FloatBuffer points, int pointsOffset, int count) {
        float _m00 = matrix.get(matrixOffset + 0);
        float _m01 = matrix.get(matrixOffset + 1);
        float _m02 = matrix.get(matrixOffset + 2);
        float _m10 = matrix.get(matrixOffset + 4);
        float _m11 = matrix.get(matrixOffset + 5);
        float _m12 = matrix.get(matrixOffset + 6);
        float _m20 = matrix.get(matrixOffset + 8);
        float _m21 = matrix.get(matrixOffset + 9);
        float _m22 = matrix.get(matrixOffset + 10);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * 3;
            int _do = destOffset + _i * 3;
            float px = points.get(_po + 0), py = points.get(_po + 1), pz = points.get(_po + 2);
            dest.put(_do + 0, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            dest.put(_do + 1, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            dest.put(_do + 2, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, int destStride, java.nio.FloatBuffer matrix, int matrixOffset, java.nio.FloatBuffer points, int pointsOffset, int pointsStride, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matrixOffset * 4L;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pointsOffset * 4L;
        Float3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, (long) destStride * 4L, _matrixBase, _pointsBase, (long) pointsStride * 4L, count);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_api(java.nio.FloatBuffer dest, int destOffset, int destStride, java.nio.FloatBuffer matrix, int matrixOffset, java.nio.FloatBuffer points, int pointsOffset, int pointsStride, int count) {
        float _m00 = matrix.get(matrixOffset + 0);
        float _m01 = matrix.get(matrixOffset + 1);
        float _m02 = matrix.get(matrixOffset + 2);
        float _m10 = matrix.get(matrixOffset + 4);
        float _m11 = matrix.get(matrixOffset + 5);
        float _m12 = matrix.get(matrixOffset + 6);
        float _m20 = matrix.get(matrixOffset + 8);
        float _m21 = matrix.get(matrixOffset + 9);
        float _m22 = matrix.get(matrixOffset + 10);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * pointsStride;
            int _do = destOffset + _i * destStride;
            float px = points.get(_po + 0), py = points.get(_po + 1), pz = points.get(_po + 2);
            dest.put(_do + 0, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            dest.put(_do + 1, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            dest.put(_do + 2, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
        return dest;
    }

    public static java.nio.FloatBuffer lerpComposeTRSMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer t1, int t1Offset, java.nio.FloatBuffer t2, int t2Offset, java.nio.FloatBuffer q1, int q1Offset, java.nio.FloatBuffer q2, int q2Offset, java.nio.FloatBuffer s1, int s1Offset, java.nio.FloatBuffer s2, int s2Offset, java.nio.FloatBuffer m, int mOffset, float alpha, int count) {
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t1Offset * 4L;
        long _t2Base = UnsafeOpsHolder.U.getLong(t2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t2Offset * 4L;
        long _q1Base = UnsafeOpsHolder.U.getLong(q1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) q1Offset * 4L;
        long _q2Base = UnsafeOpsHolder.U.getLong(q2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) q2Offset * 4L;
        long _s1Base = UnsafeOpsHolder.U.getLong(s1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) s1Offset * 4L;
        long _s2Base = UnsafeOpsHolder.U.getLong(s2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) s2Offset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 4L;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.lerpComposeTRSMul_unsafe(_destBase, _t1Base, _t2Base, _q1Base, _q2Base, _s1Base, _s2Base, _mBase, alpha, count);
        return dest;
    }

    public static java.nio.FloatBuffer lerpComposeTRSMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer t1, int t1Offset, java.nio.FloatBuffer t2, int t2Offset, java.nio.FloatBuffer q1, int q1Offset, java.nio.FloatBuffer q2, int q2Offset, java.nio.FloatBuffer s1, int s1Offset, java.nio.FloatBuffer s2, int s2Offset, java.nio.FloatBuffer m, int mOffset, float alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _t1o = t1Offset + _i * 3;
            int _t2o = t2Offset + _i * 3;
            int _q1o = q1Offset + _i * 4;
            int _q2o = q2Offset + _i * 4;
            int _s1o = s1Offset + _i * 3;
            int _s2o = s2Offset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            float _ax = t1.get(_t1o + 0), _ay = t1.get(_t1o + 1), _az = t1.get(_t1o + 2);
            float _tx = Math.fma(alpha, t2.get(_t2o + 0) - _ax, _ax);
            float _ty = Math.fma(alpha, t2.get(_t2o + 1) - _ay, _ay);
            float _tz = Math.fma(alpha, t2.get(_t2o + 2) - _az, _az);
            float _bx = s1.get(_s1o + 0), _by = s1.get(_s1o + 1), _bz = s1.get(_s1o + 2);
            float _sx = Math.fma(alpha, s2.get(_s2o + 0) - _bx, _bx);
            float _sy = Math.fma(alpha, s2.get(_s2o + 1) - _by, _by);
            float _sz = Math.fma(alpha, s2.get(_s2o + 2) - _bz, _bz);
            float _ux = q1.get(_q1o + 0), _uy = q1.get(_q1o + 1), _uz = q1.get(_q1o + 2), _uw = q1.get(_q1o + 3);
            float _vx = q2.get(_q2o + 0), _vy = q2.get(_q2o + 1), _vz = q2.get(_q2o + 2), _vw = q2.get(_q2o + 3);
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
            float _m00 = m.get(_mo + 0), _m01 = m.get(_mo + 1), _m02 = m.get(_mo + 2), _m03 = m.get(_mo + 3);
            float _m10 = m.get(_mo + 4), _m11 = m.get(_mo + 5), _m12 = m.get(_mo + 6), _m13 = m.get(_mo + 7);
            float _m20 = m.get(_mo + 8), _m21 = m.get(_mo + 9), _m22 = m.get(_mo + 10), _m23 = m.get(_mo + 11);
            float _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            float _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            float _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest.put(_do + 0, _e00);
            dest.put(_do + 1, _e01);
            dest.put(_do + 2, _e02);
            dest.put(_do + 3, _e03);
            dest.put(_do + 4, _e10);
            dest.put(_do + 5, _e11);
            dest.put(_do + 6, _e12);
            dest.put(_do + 7, _e13);
            dest.put(_do + 8, _e20);
            dest.put(_do + 9, _e21);
            dest.put(_do + 10, _e22);
            dest.put(_do + 11, _e23);
        }
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer m, int mOffset, int count) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) translationOffset * 4L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rotationOffset * 4L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) scaleOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 4L;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.composeTRSMul_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase, count);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer m, int mOffset, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _translationo = translationOffset + _i * 3;
            int _rotationo = rotationOffset + _i * 4;
            int _scaleo = scaleOffset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            float _tx = translation.get(_translationo + 0), _ty = translation.get(_translationo + 1), _tz = translation.get(_translationo + 2);
            float _sx = scale.get(_scaleo + 0), _sy = scale.get(_scaleo + 1), _sz = scale.get(_scaleo + 2);
            float _qx = rotation.get(_rotationo + 0), _qy = rotation.get(_rotationo + 1), _qz = rotation.get(_rotationo + 2), _qw = rotation.get(_rotationo + 3);
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            float _m00 = m.get(_mo + 0), _m01 = m.get(_mo + 1), _m02 = m.get(_mo + 2), _m03 = m.get(_mo + 3);
            float _m10 = m.get(_mo + 4), _m11 = m.get(_mo + 5), _m12 = m.get(_mo + 6), _m13 = m.get(_mo + 7);
            float _m20 = m.get(_mo + 8), _m21 = m.get(_mo + 9), _m22 = m.get(_mo + 10), _m23 = m.get(_mo + 11);
            float _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            float _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            float _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest.put(_do + 0, _e00);
            dest.put(_do + 1, _e01);
            dest.put(_do + 2, _e02);
            dest.put(_do + 3, _e03);
            dest.put(_do + 4, _e10);
            dest.put(_do + 5, _e11);
            dest.put(_do + 6, _e12);
            dest.put(_do + 7, _e13);
            dest.put(_do + 8, _e20);
            dest.put(_do + 9, _e21);
            dest.put(_do + 10, _e22);
            dest.put(_do + 11, _e23);
        }
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSMulPadded_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer m, int mOffset) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) translationOffset * 4L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rotationOffset * 4L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) scaleOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 4L;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3x4OpsKernelsAddress.composeTRSMulPadded_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer composeTRSMulPadded_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer translation, int translationOffset, java.nio.FloatBuffer rotation, int rotationOffset, java.nio.FloatBuffer scale, int scaleOffset, java.nio.FloatBuffer m, int mOffset) {
        float _qx = rotation.get(rotationOffset + 0), _qy = rotation.get(rotationOffset + 1), _qz = rotation.get(rotationOffset + 2), _qw = rotation.get(rotationOffset + 3);
        float _tx = translation.get(translationOffset + 0), _ty = translation.get(translationOffset + 1), _tz = translation.get(translationOffset + 2);
        float _sx = scale.get(scaleOffset + 0), _sy = scale.get(scaleOffset + 1), _sz = scale.get(scaleOffset + 2);
        float _m00 = m.get(mOffset + 0), _m01 = m.get(mOffset + 1), _m02 = m.get(mOffset + 2), _m03 = m.get(mOffset + 3);
        float _m10 = m.get(mOffset + 4), _m11 = m.get(mOffset + 5), _m12 = m.get(mOffset + 6), _m13 = m.get(mOffset + 7);
        float _m20 = m.get(mOffset + 8), _m21 = m.get(mOffset + 9), _m22 = m.get(mOffset + 10), _m23 = m.get(mOffset + 11);
        float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        float _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
        float _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
        float _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
        dest.put(destOffset + 0, _e00);
        dest.put(destOffset + 1, _e01);
        dest.put(destOffset + 2, _e02);
        dest.put(destOffset + 3, _e03);
        dest.put(destOffset + 4, _e10);
        dest.put(destOffset + 5, _e11);
        dest.put(destOffset + 6, _e12);
        dest.put(destOffset + 7, _e13);
        dest.put(destOffset + 8, _e20);
        dest.put(destOffset + 9, _e21);
        dest.put(destOffset + 10, _e22);
        dest.put(destOffset + 11, _e23);
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
