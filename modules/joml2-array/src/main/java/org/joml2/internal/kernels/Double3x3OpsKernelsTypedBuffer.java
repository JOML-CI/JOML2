// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3x3Ops} whose leading storage
 * parameter is a typed {@link java.nio.DoubleBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double3x3Ops} and its sibling kernel units. Not public API.
 */
public final class Double3x3OpsKernelsTypedBuffer {
    private Double3x3OpsKernelsTypedBuffer() {}

    public static java.nio.DoubleBuffer getColumn_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.nio.DoubleBuffer getColumn_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getColumn(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, col);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getColumn_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, col);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getColumn_apiGet(dest, destOffset, src, srcOffset, col);
        return dest;
    }

    public static java.nio.DoubleBuffer getColumn_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
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

    public static java.nio.DoubleBuffer getEulerAnglesXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getEulerAnglesXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXYZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getEulerAnglesXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getEulerAnglesXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getEulerAnglesXYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t1 = Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 < Math.fma(_self02, _self02, _t1) * 1.0E-15) {
            dest.put(destOffset + 0, Math.atan2(_self21, _self11));
            dest.put(destOffset + 2, 0.0);
        } else {
            dest.put(destOffset + 0, Math.atan2(-_self12, _self22));
            dest.put(destOffset + 2, Math.atan2(-_self01, _self00));
        }
        dest.put(destOffset + 1, Math.atan2(_self02, Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getEulerAnglesXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXZY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getEulerAnglesXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getEulerAnglesXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getEulerAnglesXZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t1 = Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 < Math.fma(_self01, _self01, _t1) * 1.0E-15) {
            dest.put(destOffset + 0, Math.atan2(-_self12, _self22));
            dest.put(destOffset + 1, 0.0);
        } else {
            dest.put(destOffset + 0, Math.atan2(_self21, _self11));
            dest.put(destOffset + 1, Math.atan2(_self02, _self00));
        }
        dest.put(destOffset + 2, Math.atan2(-_self01, Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getEulerAnglesYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYXZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getEulerAnglesYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getEulerAnglesYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getEulerAnglesYXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 4);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t1 = Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 < Math.fma(_self12, _self12, _t1) * 1.0E-15) {
            dest.put(destOffset + 1, Math.atan2(-_self20, _self00));
            dest.put(destOffset + 2, 0.0);
        } else {
            dest.put(destOffset + 1, Math.atan2(_self02, _self22));
            dest.put(destOffset + 2, Math.atan2(_self10, _self11));
        }
        dest.put(destOffset + 0, Math.atan2(-_self12, Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getEulerAnglesYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYZX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getEulerAnglesYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getEulerAnglesYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getEulerAnglesYZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 4);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t1 = Math.fma(_self11, _self11, _self12 * _self12);
        if (_t1 < Math.fma(_self10, _self10, _t1) * 1.0E-15) {
            dest.put(destOffset + 0, 0.0);
            dest.put(destOffset + 1, Math.atan2(_self02, _self22));
        } else {
            dest.put(destOffset + 0, Math.atan2(-_self12, _self11));
            dest.put(destOffset + 1, Math.atan2(-_self20, _self00));
        }
        dest.put(destOffset + 2, Math.atan2(_self10, Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getEulerAnglesZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZXY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getEulerAnglesZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getEulerAnglesZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getEulerAnglesZXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self22 = src.get(srcOffset + 8);
        double _t1 = Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 < Math.fma(_self21, _self21, _t1) * 1.0E-15) {
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, Math.atan2(_self10, _self00));
        } else {
            dest.put(destOffset + 1, Math.atan2(-_self20, _self22));
            dest.put(destOffset + 2, Math.atan2(-_self01, _self11));
        }
        dest.put(destOffset + 0, Math.atan2(_self21, Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getEulerAnglesZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZYX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getEulerAnglesZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getEulerAnglesZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getEulerAnglesZYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self22 = src.get(srcOffset + 8);
        double _t1 = Math.fma(_self21, _self21, _self22 * _self22);
        if (_t1 < Math.fma(_self20, _self20, _t1) * 1.0E-15) {
            dest.put(destOffset + 0, 0.0);
            dest.put(destOffset + 2, Math.atan2(-_self01, _self11));
        } else {
            dest.put(destOffset + 0, Math.atan2(_self21, _self22));
            dest.put(destOffset + 2, Math.atan2(_self10, _self00));
        }
        dest.put(destOffset + 1, Math.atan2(-_self20, Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getNormalizedRotation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getNormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer getNormalizedRotation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getNormalizedRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getNormalizedRotation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getNormalizedRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getNormalizedRotation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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
            dest.put(destOffset + 0, _sp0 * _t36);
            dest.put(destOffset + 1, _sp0 * _t56);
            dest.put(destOffset + 2, _sp0 * _t57);
            dest.put(destOffset + 3, 0.5 * Math.sqrt(_t62));
        } else {
            if (_t49 > Math.max(_t23, _t26)) {
                dest.put(destOffset + 0, 0.5 * Math.sqrt(_t63));
                dest.put(destOffset + 1, _sp3 * _t53);
                dest.put(destOffset + 2, _sp3 * _t55);
                dest.put(destOffset + 3, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.put(destOffset + 0, _sp1 * _t53);
                    dest.put(destOffset + 1, 0.5 * Math.sqrt(_t64));
                    dest.put(destOffset + 2, _sp1 * _t39);
                    dest.put(destOffset + 3, _sp1 * _t56);
                } else {
                    dest.put(destOffset + 0, _sp2 * _t55);
                    dest.put(destOffset + 1, _sp2 * _t39);
                    dest.put(destOffset + 2, 0.5 * Math.sqrt(_t65));
                    dest.put(destOffset + 3, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer getRow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.nio.DoubleBuffer getRow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getRow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getRow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, row);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getRow_apiGet(dest, destOffset, src, srcOffset, row);
        return dest;
    }

    public static java.nio.DoubleBuffer getRow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
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

    public static java.nio.DoubleBuffer getScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer getScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getScale_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        dest.put(destOffset + 1, Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        dest.put(destOffset + 2, Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    public static java.nio.DoubleBuffer getTranslation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getTranslation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer getTranslation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getTranslation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getTranslation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        dest.put(destOffset + 0, _self02);
        dest.put(destOffset + 1, _self12);
        return dest;
    }

    public static java.nio.DoubleBuffer getUnnormalizedRotation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.getUnnormalizedRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer getUnnormalizedRotation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.getUnnormalizedRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.getUnnormalizedRotation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.getUnnormalizedRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getUnnormalizedRotation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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
            dest.put(destOffset + 0, _sp0 * _t1);
            dest.put(destOffset + 1, _sp0 * _t7);
            dest.put(destOffset + 2, _sp0 * _t9);
            dest.put(destOffset + 3, 0.5 * Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                dest.put(destOffset + 0, 0.5 * Math.sqrt(_t15));
                dest.put(destOffset + 1, _sp3 * _t4);
                dest.put(destOffset + 2, _sp3 * _t6);
                dest.put(destOffset + 3, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    dest.put(destOffset + 0, _sp1 * _t4);
                    dest.put(destOffset + 1, 0.5 * Math.sqrt(_t16));
                    dest.put(destOffset + 2, _sp1 * _t8);
                    dest.put(destOffset + 3, _sp1 * _t7);
                } else {
                    dest.put(destOffset + 0, _sp2 * _t6);
                    dest.put(destOffset + 1, _sp2 * _t8);
                    dest.put(destOffset + 2, 0.5 * Math.sqrt(_t17));
                    dest.put(destOffset + 3, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer cofactor_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cofactor_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.cofactor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.cofactor_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.cofactor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cofactor_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static double determinant_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double3x3OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static double determinant_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            return Double3x3Ops.determinant(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double3x3OpsKernelsSegment.determinant_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double3x3OpsKernelsTypedBuffer.determinant_apiGet(src, srcOffset);
    }

    public static double determinant_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        return Math.fma(_self02, Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(_self00, Math.fma(_self11, _self22, -(_self12 * _self21)), -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static double frobeniusNorm_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double3x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            return Double3x3Ops.frobeniusNorm(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double3x3OpsKernelsSegment.frobeniusNorm_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double3x3OpsKernelsTypedBuffer.frobeniusNorm_apiGet(src, srcOffset);
    }

    public static double frobeniusNorm_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        return Math.sqrt(Math.fma(_self00, _self00, _self01 * _self01) + Math.fma(_self02, _self02, _self10 * _self10) + (Math.fma(_self11, _self11, _self12 * _self12) + Math.fma(_self20, _self20, Math.fma(_self21, _self21, _self22 * _self22))));
    }

    public static java.nio.DoubleBuffer invert_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.invert(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.invert_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.invert_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(Math.abs(_t13) > 2.2250738585072014E-308 && Math.abs(_t13) < 4.49423283715579E307)) return Double3x3OpsKernelsTypedBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t13_inv = 1.0 / _t13;
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

    public static java.nio.DoubleBuffer invert_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer invert_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3OpsKernelsArray.invert_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.invert_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.invert_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer invertProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double3x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Double3x3Ops.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.invertProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other20 = other.get(otherOffset + 2);
        double _other01 = other.get(otherOffset + 3);
        double _other11 = other.get(otherOffset + 4);
        double _other21 = other.get(otherOffset + 5);
        double _other02 = other.get(otherOffset + 6);
        double _other12 = other.get(otherOffset + 7);
        double _other22 = other.get(otherOffset + 8);
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
        if (!(Math.abs(_t40) > 2.2250738585072014E-308 && Math.abs(_t40) < 4.49423283715579E307)) return Double3x3OpsKernelsTypedBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t40_inv = 1.0 / _t40;
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

    public static java.nio.DoubleBuffer invertProduct_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsTypedBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double3x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Double3x3OpsKernelsArray.invertProduct_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.invertProduct_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.invertProduct_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other20 = other.get(otherOffset + 2);
        double _other01 = other.get(otherOffset + 3);
        double _other11 = other.get(otherOffset + 4);
        double _other21 = other.get(otherOffset + 5);
        double _other02 = other.get(otherOffset + 6);
        double _other12 = other.get(otherOffset + 7);
        double _other22 = other.get(otherOffset + 8);
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

    public static java.nio.DoubleBuffer normal_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.normal(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.normal_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.normal_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(Math.abs(_t13) > 2.2250738585072014E-308 && Math.abs(_t13) < 4.49423283715579E307)) return Double3x3OpsKernelsTypedBuffer.normal_degenerate(dest, destOffset, src, srcOffset);
        double _t13_inv = 1.0 / _t13;
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

    public static java.nio.DoubleBuffer normal_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer normal_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3OpsKernelsArray.normal_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.normal_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.normal_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static double trace_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double3x3OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static double trace_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            return Double3x3Ops.trace(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double3x3OpsKernelsSegment.trace_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double3x3OpsKernelsTypedBuffer.trace_apiGet(src, srcOffset);
    }

    public static double trace_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self11 = src.get(srcOffset + 4);
        double _self22 = src.get(srcOffset + 8);
        return _self22 + (_self00 + _self11);
    }

    public static java.nio.DoubleBuffer transpose_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer transpose_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.transpose(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.transpose_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.transpose_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer transpose_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer add_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double3x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Double3x3Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.add_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer add_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.get(srcOffset + _i);
            double _eother = other.get(otherOffset + _i);
            dest.put(destOffset + _i, _eother + _eself);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, scalar);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.get(srcOffset + _i);
            dest.put(destOffset + _i, scalar * _eself);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer negate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.negate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.get(srcOffset + _i);
            dest.put(destOffset + _i, -_eself);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer sub_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double3x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Double3x3Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.sub_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.get(srcOffset + _i);
            double _eother = other.get(otherOffset + _i);
            dest.put(destOffset + _i, _eself - _eother);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double3x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 9) {
            Double3x3Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        for (int _i = 0; _i < 9; _i++) {
            double _ev = v.get(vOffset + _i);
            dest.put(destOffset + _i, _ev);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 8L;
        Double3x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 4) {
            Double3x3Ops.setMat2x2(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.setMat2x2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), (long) mOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.setMat2x2_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m00 = m.get(mOffset + 0);
        double _m10 = m.get(mOffset + 1);
        double _m01 = m.get(mOffset + 2);
        double _m11 = m.get(mOffset + 3);
        dest.put(destOffset + 0, _m00);
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, _m01);
        dest.put(destOffset + 4, _m11);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 8L;
        Double3x3OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 6) {
            Double3x3Ops.setMat2x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.setMat2x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), (long) mOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.setMat2x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m00 = m.get(mOffset + 0);
        double _m10 = m.get(mOffset + 1);
        double _m01 = m.get(mOffset + 2);
        double _m11 = m.get(mOffset + 3);
        double _m02 = m.get(mOffset + 4);
        double _m12 = m.get(mOffset + 5);
        dest.put(destOffset + 0, _m00);
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, _m01);
        dest.put(destOffset + 4, _m11);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, _m02);
        dest.put(destOffset + 7, _m12);
        dest.put(destOffset + 8, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 8L;
        Double3x3OpsKernelsAddress.setMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 12) {
            Double3x3Ops.setMat3x4(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.setMat3x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), (long) mOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.setMat3x4_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m00 = m.get(mOffset + 0);
        double _m01 = m.get(mOffset + 1);
        double _m02 = m.get(mOffset + 2);
        double _m10 = m.get(mOffset + 4);
        double _m11 = m.get(mOffset + 5);
        double _m12 = m.get(mOffset + 6);
        double _m20 = m.get(mOffset + 8);
        double _m21 = m.get(mOffset + 9);
        double _m22 = m.get(mOffset + 10);
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

    public static java.nio.DoubleBuffer setMat4x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 8L;
        Double3x3OpsKernelsAddress.setMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat4x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 16) {
            Double3x3Ops.setMat4x4(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.setMat4x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), (long) mOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.setMat4x4_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat4x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            int _lom = _l * 4;
            double _em0 = m.get(mOffset + _lom);
            double _em1 = m.get(mOffset + _lom + 1);
            double _em2 = m.get(mOffset + _lom + 2);
            dest.put(destOffset + _lo, _em0);
            dest.put(destOffset + _lo + 1, _em1);
            dest.put(destOffset + _lo + 2, _em2);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer withTranslation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double tX, double tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.nio.DoubleBuffer withTranslation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double tX, double tY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.withTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, tX, tY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.withTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, tX, tY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.withTranslation_apiGet(dest, destOffset, src, srcOffset, tX, tY);
        return dest;
    }

    public static java.nio.DoubleBuffer withTranslation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double tX, double tY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer withTranslation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) tOffset * 8L;
        Double3x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.DoubleBuffer withTranslation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 2) {
            Double3x3Ops.withTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && t.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.withTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(t.duplicate().position(0)), (long) tOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.withTranslation_apiGet(dest, destOffset, src, srcOffset, t, tOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer withTranslation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t, int tOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self22 = src.get(srcOffset + 8);
        double _tx = t.get(tOffset + 0);
        double _ty = t.get(tOffset + 1);
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

    public static java.nio.DoubleBuffer makeFromRigid_unsafe(java.nio.DoubleBuffer dest, int destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeFromRigid_unsafe(_destBase, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromRigid_api(java.nio.DoubleBuffer dest, int destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeFromRigid(dest.array(), dest.arrayOffset() + destOffset, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeFromRigid_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeFromRigid_apiGet(dest, destOffset, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromRigid_apiGet(java.nio.DoubleBuffer dest, int destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        dest.put(destOffset + 0, Math.fma(-2.0, Math.fma(rRY, rRY, _t0), 1.0));
        dest.put(destOffset + 1, 2.0 * Math.fma(rRX, rRY, _t1));
        dest.put(destOffset + 2, 2.0 * Math.fma(rRX, rRZ, -_t2));
        dest.put(destOffset + 3, 2.0 * Math.fma(rRX, rRY, -_t1));
        dest.put(destOffset + 4, Math.fma(-2.0, Math.fma(rRX, rRX, _t0), 1.0));
        dest.put(destOffset + 5, 2.0 * Math.fma(rRX, rRW, rRY * rRZ));
        dest.put(destOffset + 6, 2.0 * Math.fma(rRX, rRZ, _t2));
        dest.put(destOffset + 7, 2.0 * Math.fma(rRY, rRZ, -(rRX * rRW)));
        dest.put(destOffset + 8, Math.fma(-2.0, Math.fma(rRX, rRX, rRY * rRY), 1.0));
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromTransform_unsafe(java.nio.DoubleBuffer dest, int destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeFromTransform_unsafe(_destBase, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromTransform_api(java.nio.DoubleBuffer dest, int destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeFromTransform(dest.array(), dest.arrayOffset() + destOffset, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeFromTransform_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeFromTransform_apiGet(dest, destOffset, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromTransform_apiGet(java.nio.DoubleBuffer dest, int destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
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

    public static java.nio.DoubleBuffer to2x2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer to2x2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.to2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.to2x2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.to2x2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer to2x2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        return dest;
    }

    public static java.nio.DoubleBuffer to2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer to2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.to2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.to2x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.to2x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer to2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _loself = _l * 3;
            double _eself0 = src.get(srcOffset + _loself);
            double _eself1 = src.get(srcOffset + _loself + 1);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, _eself1);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer to3x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.to3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer to3x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.to3x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.to3x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.to3x4_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer to3x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _self10);
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, _self12);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer to4x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.to4x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer to4x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.to4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.to4x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.to4x4_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer to4x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _self01);
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, _self21);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, _self02);
        dest.put(destOffset + 9, _self12);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, 0.0);
        dest.put(destOffset + 12, 0.0);
        dest.put(destOffset + 13, 0.0);
        dest.put(destOffset + 14, 0.0);
        dest.put(destOffset + 15, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer toDualQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toDualQuat_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 8 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.toDualQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.toDualQuat_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.toDualQuat_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toDualQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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
            dest.put(destOffset + 0, _sp0 * _t3);
            dest.put(destOffset + 1, _sp0 * _t7);
            dest.put(destOffset + 2, _sp0 * _t9);
            dest.put(destOffset + 3, 0.5 * Math.sqrt(_t14));
        } else {
            if (_self00 > Math.max(_self11, _self22)) {
                dest.put(destOffset + 0, 0.5 * Math.sqrt(_t15));
                dest.put(destOffset + 1, _sp3 * _t5);
                dest.put(destOffset + 2, _sp3 * _t6);
                dest.put(destOffset + 3, _sp3 * _t3);
            } else {
                if (_self11 > _self22) {
                    dest.put(destOffset + 0, _sp1 * _t5);
                    dest.put(destOffset + 1, 0.5 * Math.sqrt(_t16));
                    dest.put(destOffset + 2, _sp1 * _t8);
                    dest.put(destOffset + 3, _sp1 * _t7);
                } else {
                    dest.put(destOffset + 0, _sp2 * _t6);
                    dest.put(destOffset + 1, _sp2 * _t8);
                    dest.put(destOffset + 2, 0.5 * Math.sqrt(_t17));
                    dest.put(destOffset + 3, _sp2 * _t9);
                }
            }
        }
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.toRigid_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 7 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.toRigid(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.toRigid_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.toRigid_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t0 = -_self11;
        double _t1 = -_self22;
        double _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _t15 = (1.0 / Math.sqrt(_ct0));
        double _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _t16 = (1.0 / Math.sqrt(_ct1));
        double _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
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
            dest.put(destOffset + 3, _sp0 * _t35);
            dest.put(destOffset + 4, _sp0 * _t56);
            dest.put(destOffset + 5, _sp0 * _t57);
            dest.put(destOffset + 6, 0.5 * Math.sqrt(_t63));
        } else {
            if (_t47 > Math.max(_t24, _t19)) {
                dest.put(destOffset + 3, 0.5 * Math.sqrt(_t67));
                dest.put(destOffset + 4, _sp3 * _t54);
                dest.put(destOffset + 5, _sp3 * _t55);
                dest.put(destOffset + 6, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.put(destOffset + 3, _sp1 * _t54);
                    dest.put(destOffset + 4, 0.5 * Math.sqrt(_t65));
                    dest.put(destOffset + 5, _sp1 * _t31);
                    dest.put(destOffset + 6, _sp1 * _t56);
                } else {
                    dest.put(destOffset + 3, _sp2 * _t55);
                    dest.put(destOffset + 4, _sp2 * _t31);
                    dest.put(destOffset + 5, 0.5 * Math.sqrt(_t66));
                    dest.put(destOffset + 6, _sp2 * _t57);
                }
            }
        }
        dest.put(destOffset + 0, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.toRigid_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.toRigid_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer toRigid_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.toRigid_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 7 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3OpsKernelsArray.toRigid_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.toRigid_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.toRigid_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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
            dest.put(destOffset + 3, _sp0 * _t182);
            dest.put(destOffset + 4, _sp0 * _t201);
            dest.put(destOffset + 5, _sp0 * _t202);
            dest.put(destOffset + 6, 0.5 * Math.sqrt(_t207));
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest.put(destOffset + 3, 0.5 * Math.sqrt(_t208));
                dest.put(destOffset + 4, _sp3 * _t199);
                dest.put(destOffset + 5, _sp3 * _t200);
                dest.put(destOffset + 6, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.put(destOffset + 3, _sp1 * _t199);
                    dest.put(destOffset + 4, 0.5 * Math.sqrt(_t209));
                    dest.put(destOffset + 5, _sp1 * _t184);
                    dest.put(destOffset + 6, _sp1 * _t201);
                } else {
                    dest.put(destOffset + 3, _sp2 * _t200);
                    dest.put(destOffset + 4, _sp2 * _t184);
                    dest.put(destOffset + 5, 0.5 * Math.sqrt(_t210));
                    dest.put(destOffset + 6, _sp2 * _t202);
                }
            }
        }
        dest.put(destOffset + 0, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.toTransform_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 10 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.toTransform(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.toTransform_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.toTransform_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t0 = -_self11;
        double _t1 = -_self22;
        double _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t14 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
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
        dest.put(destOffset + 0, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        dest.put(destOffset + 4, _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * Math.sqrt(_t66) : _sp2 * _t32);
        dest.put(destOffset + 5, _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * Math.sqrt(_t67));
        dest.put(destOffset + 6, _t63 > 0.0 ? 0.5 * Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        dest.put(destOffset + 7, _t47 < 0.0 ? -_t18 : _t18);
        dest.put(destOffset + 8, Math.sqrt(_t12));
        dest.put(destOffset + 9, Math.sqrt(_t13));
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.toTransform_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.toTransform_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer toTransform_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.toTransform_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 10 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3OpsKernelsArray.toTransform_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.toTransform_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.toTransform_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.put(destOffset + 4, _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * Math.sqrt(_t212) : _sp2 * _t187);
        dest.put(destOffset + 5, _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * Math.sqrt(_t213));
        dest.put(destOffset + 6, _t209 > 0.0 ? 0.5 * Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.put(destOffset + 7, _t196 < 0.0 ? -_t56 : _t56);
        dest.put(destOffset + 8, _t27 <= 0.0 ? 0.0 : Math.sqrt(_t27) / _t0);
        dest.put(destOffset + 9, _t28 <= 0.0 ? 0.0 : Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeRotation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.decomposeRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeRotation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.decomposeRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.decomposeRotation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.decomposeRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeRotation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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
            dest.put(destOffset + 0, _sp0 * _t60);
            dest.put(destOffset + 1, _sp0 * _t81);
            dest.put(destOffset + 2, _sp0 * _t78);
            dest.put(destOffset + 3, 0.5 * Math.sqrt(_t86));
        } else {
            if (_t73 > Math.max(_t36, _t56)) {
                dest.put(destOffset + 0, 0.5 * Math.sqrt(_t87));
                dest.put(destOffset + 1, _sp3 * _t77);
                dest.put(destOffset + 2, _sp3 * _t80);
                dest.put(destOffset + 3, _sp3 * _t60);
            } else {
                if (_t36 > _t56) {
                    dest.put(destOffset + 0, _sp1 * _t77);
                    dest.put(destOffset + 1, 0.5 * Math.sqrt(_t88));
                    dest.put(destOffset + 2, _sp1 * _t63);
                    dest.put(destOffset + 3, _sp1 * _t81);
                } else {
                    dest.put(destOffset + 0, _sp2 * _t80);
                    dest.put(destOffset + 1, _sp2 * _t63);
                    dest.put(destOffset + 2, 0.5 * Math.sqrt(_t89));
                    dest.put(destOffset + 3, _sp2 * _t78);
                }
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.decomposeScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.decomposeScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.decomposeScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.decomposeScale_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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
        dest.put(destOffset + 0, Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0 ? -_t4 : _t4);
        dest.put(destOffset + 1, Math.sqrt(_t27));
        dest.put(destOffset + 2, Math.sqrt(_t47));
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeSkew_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.decomposeSkew_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeSkew_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.decomposeSkew(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.decomposeSkew_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.decomposeSkew_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeSkew_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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
            dest.put(destOffset + 1, -_t49);
            dest.put(destOffset + 2, -_t28);
        } else {
            dest.put(destOffset + 1, _t49);
            dest.put(destOffset + 2, _t28);
        }
        dest.put(destOffset + 0, _t37 * _t48);
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeIdentity(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeIdentity_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeIdentity_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double3x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Double3x3Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L, t);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.get(srcOffset + _i);
            double _eother = other.get(otherOffset + _i);
            dest.put(destOffset + _i, Math.fma(t, _eother - _eself, _eself));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rightOffset * 8L;
        Double3x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 9) {
            Double3x3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && right.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(right.duplicate().position(0)), (long) rightOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = right.get(rightOffset + _lo);
            double _eright1 = right.get(rightOffset + _lo + 1);
            double _eright2 = right.get(rightOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.put(destOffset + _lo + 1, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.put(destOffset + _lo + 2, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21)));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat2x2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rightOffset * 8L;
        Double3x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat2x2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 4) {
            Double3x3Ops.mulMat2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && right.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.mulMat2x2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(right.duplicate().position(0)), (long) rightOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.mulMat2x2_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat2x2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _right00 = right.get(rightOffset + 0);
        double _right10 = right.get(rightOffset + 1);
        double _right01 = right.get(rightOffset + 2);
        double _right11 = right.get(rightOffset + 3);
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

    public static java.nio.DoubleBuffer mulMat2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rightOffset * 8L;
        Double3x3OpsKernelsAddress.mulMat2x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 6) {
            Double3x3Ops.mulMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && right.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.mulMat2x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(right.duplicate().position(0)), (long) rightOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.mulMat2x3_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _right00 = right.get(rightOffset + 0);
        double _right10 = right.get(rightOffset + 1);
        double _right01 = right.get(rightOffset + 2);
        double _right11 = right.get(rightOffset + 3);
        double _right02 = right.get(rightOffset + 4);
        double _right12 = right.get(rightOffset + 5);
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

    public static java.nio.DoubleBuffer preMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double3x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Double3x3Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preMul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other20 = other.get(otherOffset + 2);
        double _other01 = other.get(otherOffset + 3);
        double _other11 = other.get(otherOffset + 4);
        double _other21 = other.get(otherOffset + 5);
        double _other02 = other.get(otherOffset + 6);
        double _other12 = other.get(otherOffset + 7);
        double _other22 = other.get(otherOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.put(destOffset + _lo + 1, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.put(destOffset + _lo + 2, Math.fma(_other22, _eself2, Math.fma(_other20, _eself0, _other21 * _eself1)));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double3x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double3x3Ops.preMulMat2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preMulMat2x2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preMulMat2x2_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_other00, _eself0, _other01 * _eself1));
            dest.put(destOffset + _lo + 1, Math.fma(_other10, _eself0, _other11 * _eself1));
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double3x3OpsKernelsAddress.preMulMat2x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 6) {
            Double3x3Ops.preMulMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preMulMat2x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preMulMat2x3_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        double _other02 = other.get(otherOffset + 4);
        double _other12 = other.get(otherOffset + 5);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1)));
            dest.put(destOffset + _lo + 1, Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1)));
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double3x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Double3x3Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, weight);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L, weight);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src.get(srcOffset + _i);
            double _eother = other.get(otherOffset + _i);
            dest.put(destOffset + _i, Math.fma(weight, _eother, _eself));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, colZ, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_api(java.nio.DoubleBuffer dest, int destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeOuterProduct(dest.array(), dest.arrayOffset() + destOffset, colX, colY, colZ, rowX, rowY, rowZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeOuterProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, colX, colY, colZ, rowX, rowY, rowZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeOuterProduct_apiGet(dest, destOffset, colX, colY, colZ, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
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

    public static java.nio.DoubleBuffer makeOuterProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer col, int colOffset, java.nio.DoubleBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _colBase = UnsafeOpsHolder.U.getLong(col, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) colOffset * 8L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rowOffset * 8L;
        Double3x3OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer col, int colOffset, java.nio.DoubleBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && col.hasArray() && colOffset >= 0 && colOffset <= col.limit() - 3 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 3) {
            Double3x3Ops.makeOuterProduct(dest.array(), dest.arrayOffset() + destOffset, col.array(), col.arrayOffset() + colOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && col.order() == java.nio.ByteOrder.nativeOrder() && row.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.makeOuterProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(col.duplicate().position(0)), (long) colOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(row.duplicate().position(0)), (long) rowOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeOuterProduct_apiGet(dest, destOffset, col, colOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer col, int colOffset, java.nio.DoubleBuffer row, int rowOffset) {
        double _colx = col.get(colOffset + 0);
        double _coly = col.get(colOffset + 1);
        double _colz = col.get(colOffset + 2);
        double _rowx = row.get(rowOffset + 0);
        double _rowy = row.get(rowOffset + 1);
        double _rowz = row.get(rowOffset + 2);
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

    public static java.nio.DoubleBuffer lookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.lookAlong(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.lookAlong_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.lookAlong_apiGet(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer lookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) dirOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 8L;
        Double3x3OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Double3x3Ops.lookAlong(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.lookAlong_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(dir.duplicate().position(0)), (long) dirOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), (long) upOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.lookAlong_apiGet(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _dirx = dir.get(dirOffset + 0);
        double _diry = dir.get(dirOffset + 1);
        double _dirz = dir.get(dirOffset + 2);
        double _upx = up.get(upOffset + 0);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
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

    public static java.nio.DoubleBuffer makeFromDualQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromDualQuat_api(java.nio.DoubleBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeFromDualQuat(dest.array(), dest.arrayOffset() + destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeFromDualQuat_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeFromDualQuat_apiGet(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromDualQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = Math.fma(-2.0, dqRZ * dqRZ, 1.0);
        dest.put(destOffset + 0, Math.fma(-2.0, _t0, _t6));
        dest.put(destOffset + 1, 2.0 * Math.fma(dqRX, dqRY, _t2));
        dest.put(destOffset + 2, Math.fma(-2.0, _t3, _sp0 * dqRZ));
        dest.put(destOffset + 3, Math.fma(-2.0, _t2, _sp0 * dqRY));
        dest.put(destOffset + 4, Math.fma(-2.0, _t4, _t6));
        dest.put(destOffset + 5, 2.0 * Math.fma(dqRX, dqRW, _t5));
        dest.put(destOffset + 6, 2.0 * Math.fma(dqRX, dqRZ, _t3));
        dest.put(destOffset + 7, Math.fma(-2.0, dqRX * dqRW, _t5 + _t5));
        dest.put(destOffset + 8, Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0)));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotation_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotation_api(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotation(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotation_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotation_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _t1);
        dest.put(destOffset + 1, _t0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, -_t0);
        dest.put(destOffset + 4, _t1);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_api(java.nio.DoubleBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotationAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, angle, axisX, axisY, axisZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
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

    public static java.nio.DoubleBuffer makeRotationAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Double3x3Ops.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && axis.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.makeRotationAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(axis.duplicate().position(0)), (long) axisOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        double _axisx = axis.get(axisOffset + 0);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisy;
        double _t3 = _axisx * _axisz;
        double _t4 = _axisy * _axisz;
        double _t5 = 1.0 - _t1;
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

    public static java.nio.DoubleBuffer makeRotationLookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_api(java.nio.DoubleBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotationLookAlong(dest.array(), dest.arrayOffset() + destOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotationLookAlong_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationLookAlong_apiGet(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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

    public static java.nio.DoubleBuffer makeRotationLookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) dirOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Double3x3Ops.makeRotationLookAlong(dest.array(), dest.arrayOffset() + destOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.makeRotationLookAlong_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(dir.duplicate().position(0)), (long) dirOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), (long) upOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationLookAlong_apiGet(dest, destOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _dirx = dir.get(dirOffset + 0);
        double _diry = dir.get(dirOffset + 1);
        double _dirz = dir.get(dirOffset + 2);
        double _upx = up.get(upOffset + 0);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
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

    public static java.nio.DoubleBuffer makeRotationQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationQuat_api(java.nio.DoubleBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotationQuat(dest.array(), dest.arrayOffset() + destOffset, qX, qY, qZ, qW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotationQuat_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, qX, qY, qZ, qW);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationQuat_apiGet(dest, destOffset, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        dest.put(destOffset + 0, Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0));
        dest.put(destOffset + 1, 2.0 * Math.fma(qX, qY, _t1));
        dest.put(destOffset + 2, 2.0 * Math.fma(qX, qZ, -_t2));
        dest.put(destOffset + 3, 2.0 * Math.fma(qX, qY, -_t1));
        dest.put(destOffset + 4, Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0));
        dest.put(destOffset + 5, 2.0 * Math.fma(qX, qW, qY * qZ));
        dest.put(destOffset + 6, 2.0 * Math.fma(qX, qZ, _t2));
        dest.put(destOffset + 7, 2.0 * Math.fma(qY, qZ, -(qX * qW)));
        dest.put(destOffset + 8, Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) qOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, _qBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationQuat_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer q, int qOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && q.hasArray() && qOffset >= 0 && qOffset <= q.limit() - 4) {
            Double3x3Ops.makeRotationQuat(dest.array(), dest.arrayOffset() + destOffset, q.array(), q.arrayOffset() + qOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && q.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.makeRotationQuat_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(q.duplicate().position(0)), (long) qOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationQuat_apiGet(dest, destOffset, q, qOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer q, int qOffset) {
        double _qx = q.get(qOffset + 0);
        double _qy = q.get(qOffset + 1);
        double _qz = q.get(qOffset + 2);
        double _qw = q.get(qOffset + 3);
        double _t0 = _qz * _qz;
        double _t1 = _qz * _qw;
        double _t2 = _qy * _qw;
        dest.put(destOffset + 0, Math.fma(-2.0, Math.fma(_qy, _qy, _t0), 1.0));
        dest.put(destOffset + 1, 2.0 * Math.fma(_qx, _qy, _t1));
        dest.put(destOffset + 2, 2.0 * Math.fma(_qx, _qz, -_t2));
        dest.put(destOffset + 3, 2.0 * Math.fma(_qx, _qy, -_t1));
        dest.put(destOffset + 4, Math.fma(-2.0, Math.fma(_qx, _qx, _t0), 1.0));
        dest.put(destOffset + 5, 2.0 * Math.fma(_qx, _qw, _qy * _qz));
        dest.put(destOffset + 6, 2.0 * Math.fma(_qx, _qz, _t2));
        dest.put(destOffset + 7, 2.0 * Math.fma(_qy, _qz, -(_qx * _qw)));
        dest.put(destOffset + 8, Math.fma(-2.0, Math.fma(_qx, _qx, _qy * _qy), 1.0));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationX_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationX_api(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotationX(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotationX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationX_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationX_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _t1);
        dest.put(destOffset + 5, _t0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, -_t0);
        dest.put(destOffset + 8, _t1);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXYZ_api(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotationXYZ(dest.array(), dest.arrayOffset() + destOffset, angleX, angleY, angleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotationXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, angleX, angleY, angleZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationXYZ_apiGet(dest, destOffset, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t0;
        double _t7 = _t0 * _t5;
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

    public static java.nio.DoubleBuffer makeRotationXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXZY_api(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotationXZY(dest.array(), dest.arrayOffset() + destOffset, angleX, angleZ, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotationXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, angleX, angleZ, angleY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationXZY_apiGet(dest, destOffset, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t1 * _t5;
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

    public static java.nio.DoubleBuffer makeRotationY_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationY_api(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotationY(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotationY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationY_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationY_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _t1);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, -_t0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, _t0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, _t1);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYXZ_api(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotationYXZ(dest.array(), dest.arrayOffset() + destOffset, angleY, angleX, angleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotationYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, angleY, angleX, angleZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationYXZ_apiGet(dest, destOffset, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
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

    public static java.nio.DoubleBuffer makeRotationYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYZX_api(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotationYZX(dest.array(), dest.arrayOffset() + destOffset, angleY, angleZ, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotationYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, angleY, angleZ, angleX);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationYZX_apiGet(dest, destOffset, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t1 * _t3;
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

    public static java.nio.DoubleBuffer makeRotationZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZXY_api(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotationZXY(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleX, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotationZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, angleZ, angleX, angleY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationZXY_apiGet(dest, destOffset, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
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

    public static java.nio.DoubleBuffer makeRotationZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZYX_api(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeRotationZYX(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleY, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeRotationZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, angleZ, angleY, angleX);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeRotationZYX_apiGet(dest, destOffset, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t4;
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

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, vX, vY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        dest.put(destOffset + 0, vX);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, vY);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double3x3Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, _vx);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _vy);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, s);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, double s) {
        dest.put(destOffset + 0, s);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, s);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_unsafe(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_api(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeTranslation(dest.array(), dest.arrayOffset() + destOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, vX, vY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeTranslation_apiGet(dest, destOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_apiGet(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        dest.put(destOffset + 0, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, vX);
        dest.put(destOffset + 7, vY);
        dest.put(destOffset + 8, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double3x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double3x3Ops.makeTranslation(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.makeTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeTranslation_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, _vx);
        dest.put(destOffset + 7, _vy);
        dest.put(destOffset + 8, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeView_unsafe(java.nio.DoubleBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double3x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.DoubleBuffer makeView_api(java.nio.DoubleBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            Double3x3Ops.makeView(dest.array(), dest.arrayOffset() + destOffset, left, right, bottom, top);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x3OpsKernelsSegment.makeView_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, left, right, bottom, top);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.makeView_apiGet(dest, destOffset, left, right, bottom, top);
        return dest;
    }

    public static java.nio.DoubleBuffer makeView_apiGet(java.nio.DoubleBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dest.put(destOffset + 0, _t0_inv + _t0_inv);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _t1_inv + _t1_inv);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, -((left + right) * _t0_inv));
        dest.put(destOffset + 7, -((bottom + top) * _t1_inv));
        dest.put(destOffset + 8, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.preRotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preRotate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preRotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
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

    public static java.nio.DoubleBuffer preRotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.preRotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preRotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle, pivotX, pivotY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preRotateAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.sin(0.5 * angle);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t3 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t2 + _t2) * _t2;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
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

    public static java.nio.DoubleBuffer preRotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 8L;
        Double3x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Double3x3Ops.preRotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preRotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preRotateAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.sin(0.5 * angle);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _pivotx = pivot.get(pivotOffset + 0);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _t3 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t2 + _t2) * _t2;
        double _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        double _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
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

    public static java.nio.DoubleBuffer preRotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.preRotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preRotateAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle, axisX, axisY, axisZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preRotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer preRotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 8L;
        Double3x3OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Double3x3Ops.preRotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preRotateAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(axis.duplicate().position(0)), (long) axisOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preRotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _axisx = axis.get(axisOffset + 0);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
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

    public static java.nio.DoubleBuffer preRotateX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.preRotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preRotateX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preRotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
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

    public static java.nio.DoubleBuffer preRotateY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.preRotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preRotateY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preRotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
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

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, vX, vY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, _eself0 * vX);
            dest.put(destOffset + _lo + 1, _eself1 * vY);
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double3x3Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, _eself0 * _vx);
            dest.put(destOffset + _lo + 1, _eself1 * _vy);
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, s);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, s * _eself0);
            dest.put(destOffset + _lo + 1, s * _eself1);
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preScaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, s, pivotX, pivotY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
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

    public static java.nio.DoubleBuffer preScaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 8L;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Double3x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preScaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 8L, s);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _pivotx = pivot.get(pivotOffset + 0);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _t0 = 1.0 - s;
        double _t1 = _pivotx * _t0;
        double _t2 = _pivoty * _t0;
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

    public static java.nio.DoubleBuffer preScaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sX, sY, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preScaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, sX, sY, pivotX, pivotY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
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

    public static java.nio.DoubleBuffer preScaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) sOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 8L;
        Double3x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && s.hasArray() && sOffset >= 0 && sOffset <= s.limit() - 2 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Double3x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s.array(), s.arrayOffset() + sOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preScaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(s.duplicate().position(0)), (long) sOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _sx = s.get(sOffset + 0);
        double _sy = s.get(sOffset + 1);
        double _pivotx = pivot.get(pivotOffset + 0);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _t2 = _pivotx * (1.0 - _sx);
        double _t3 = _pivoty * (1.0 - _sy);
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

    public static java.nio.DoubleBuffer preTranslate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer preTranslate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.preTranslate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preTranslate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, vX, vY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preTranslate_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer preTranslate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_eself2, vX, _eself0));
            dest.put(destOffset + _lo + 1, Math.fma(_eself2, vY, _eself1));
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer preTranslate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double3x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preTranslate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double3x3Ops.preTranslate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.preTranslate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.preTranslate_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preTranslate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_eself2, _vx, _eself0));
            dest.put(destOffset + _lo + 1, Math.fma(_eself2, _vy, _eself1));
            dest.put(destOffset + _lo + 2, _eself2);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
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

    public static java.nio.DoubleBuffer rotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle, pivotX, pivotY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t3 = Math.sin(0.5 * angle);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
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

    public static java.nio.DoubleBuffer rotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 8L;
        Double3x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Double3x3Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t3 = Math.sin(0.5 * angle);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _pivotx = pivot.get(pivotOffset + 0);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        double _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
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

    public static java.nio.DoubleBuffer rotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle, axisX, axisY, axisZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 8L;
        Double3x3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Double3x3Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(axis.duplicate().position(0)), (long) axisOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _axisx = axis.get(axisOffset + 0);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
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

    public static java.nio.DoubleBuffer rotateX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
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

    public static java.nio.DoubleBuffer rotateX180_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateX180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX180_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateX180(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateX180_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateX180_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX180_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateX270_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateX270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX270_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateX270(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateX270_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateX270_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX270_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateX90_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateX90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX90_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateX90(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateX90_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateX90_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX90_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXYZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleY, angleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angleX, angleY, angleZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateXYZ_apiGet(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXZY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleZ, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angleX, angleZ, angleY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateXZY_apiGet(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
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

    public static java.nio.DoubleBuffer rotateY180_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateY180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY180_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateY180(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateY180_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateY180_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY180_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateY270_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateY270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY270_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateY270(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateY270_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateY270_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY270_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateY90_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateY90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY90_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateY90(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateY90_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateY90_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY90_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYXZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleX, angleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angleY, angleX, angleZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateYXZ_apiGet(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYZX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleZ, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angleY, angleZ, angleX);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateYZX_apiGet(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateZ180_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateZ180_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ180_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateZ180(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateZ180_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateZ180_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ180_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateZ270_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateZ270_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ270_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateZ270(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateZ270_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateZ270_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ270_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateZ90_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateZ90_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ90_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateZ90(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateZ90_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateZ90_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ90_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZXY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleX, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angleZ, angleX, angleY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateZXY_apiGet(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer rotateZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZYX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.rotateZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleY, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.rotateZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angleZ, angleY, angleX);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.rotateZYX_apiGet(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer scale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.scale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, vX, vY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer scale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double3x3Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.scale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
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

    public static java.nio.DoubleBuffer scale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.scale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, s);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer scaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.scaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, s, pivotX, pivotY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
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

    public static java.nio.DoubleBuffer scaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 8L;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Double3x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.scaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 8L, s);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _pivotx = pivot.get(pivotOffset + 0);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _t0 = 1.0 - s;
        double _t1 = _pivotx * _t0;
        double _t2 = _pivoty * _t0;
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

    public static java.nio.DoubleBuffer scaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sX, sY, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.scaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, sX, sY, pivotX, pivotY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
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

    public static java.nio.DoubleBuffer scaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) sOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 8L;
        Double3x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && s.hasArray() && sOffset >= 0 && sOffset <= s.limit() - 2 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Double3x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s.array(), s.arrayOffset() + sOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.scaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(s.duplicate().position(0)), (long) sOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _sx = s.get(sOffset + 0);
        double _sy = s.get(sOffset + 1);
        double _pivotx = pivot.get(pivotOffset + 0);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _t2 = _pivotx * (1.0 - _sx);
        double _t3 = _pivoty * (1.0 - _sy);
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

    public static java.nio.DoubleBuffer translate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer translate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.translate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.translate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, vX, vY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.translate_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer translate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
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

    public static java.nio.DoubleBuffer translate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double3x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer translate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double3x3Ops.translate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.translate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.translate_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer translate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
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

    public static java.nio.DoubleBuffer view_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.DoubleBuffer view_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.view(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, left, right, bottom, top);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.view_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, left, right, bottom, top);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.view_apiGet(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return dest;
    }

    public static java.nio.DoubleBuffer view_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
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

    public static java.nio.DoubleBuffer mulVec3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.mulVec3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.mulVec3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, vX, vY, vZ);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.mulVec3_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        dest.put(destOffset + 0, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        dest.put(destOffset + 1, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        dest.put(destOffset + 2, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double3x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Double3x3Ops.mulVec3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.mulVec3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.mulVec3_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self20 = src.get(srcOffset + 2);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self21 = src.get(srcOffset + 5);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _self22 = src.get(srcOffset + 8);
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        dest.put(destOffset + 0, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        dest.put(destOffset + 1, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        dest.put(destOffset + 2, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.transformDirection(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.transformDirection_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, vX, vY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.transformDirection_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        dest.put(destOffset + 0, Math.fma(_self00, vX, _self01 * vY));
        dest.put(destOffset + 1, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double3x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double3x3Ops.transformDirection(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.transformDirection_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.transformDirection_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, Math.fma(_self00, _vx, _self01 * _vy));
        dest.put(destOffset + 1, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            Double3x3Ops.transformPosition(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.transformPosition_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, vX, vY);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.transformPosition_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        dest.put(destOffset + 0, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        dest.put(destOffset + 1, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double3x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double3x3Ops.transformPosition(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x3OpsKernelsSegment.transformPosition_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double3x3OpsKernelsTypedBuffer.transformPosition_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 4);
        double _self02 = src.get(srcOffset + 6);
        double _self12 = src.get(srcOffset + 7);
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
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
